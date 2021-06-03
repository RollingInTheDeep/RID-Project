package com.example.rid_project.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rid_project.R;
import com.example.rid_project.data.User;
import com.example.rid_project.databinding.ActivityMainBinding;
import com.example.rid_project.databinding.ActivityReadTextBinding;
import com.example.rid_project.viewModel.MainViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Random;

public class ReadTextActivity extends AppCompatActivity {

    private EditText etText;
    private String userUid = "";
    private int cnt;
    private Random random = new Random();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private MainViewModel mainViewModel;
    private ActivityReadTextBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_text);

        binding = ActivityReadTextBinding.inflate(getLayoutInflater());

        // main view model 대신에 새로운 뷰모델 만들어야 할 듯. firestore는 그대로 쓰고.
//        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
//        binding.setViewModel(mainViewModel);
//        binding.setLifecycleOwner(this);

//        Intent getIntent = new Intent(this.getIntent());
//        userUid = getIntent.getStringExtra("userUid");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityResult.launch(intent);

        // text 관리하는 클래스 만들어서 main View model에 함수 하나 정의하기
//        mainViewModel.getLiveData().observe((LifecycleOwner) getLifecycle(), new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//
//            }
//        });
    }

    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        cnt = random.nextInt(1000);
//                        StorageReference storageRef = storage.getReference(userUid.substring(0, 2) + Integer.toString(cnt));
//                        String imageRef = storageRef.getBucket();
//                        -> rid-project-cb50d.appspot.com

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 4;
                        Bundle extras = result.getData().getExtras();
                        Bitmap bitmap = (Bitmap) extras.get("data");

                        try{
                            if(bitmap != null) {
                                Log.e("tttttt", "recognizer 전");

                                TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                                Log.e("tttttt", "recognizer 후");

                                Frame imageFrame = new Frame.Builder()
                                        .setBitmap(bitmap)
                                        .build();

                                SparseArray<TextBlock> textBlocks = textRecognizer.detect(imageFrame);


                                for (int i = 0; i < textBlocks.size(); i++) {

                                    TextBlock textBlock = textBlocks.get(textBlocks.keyAt(i));

                                    String text = textBlock.getValue();
                                    Log.e("text", text);
                                    etText.setText(text);

                                }
                            }
                        }catch(Exception e){

                        }







//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                        byte[] data = baos.toByteArray();

//                        UploadTask uploadTask = storageRef.putBytes(data);
//                        uploadTask.addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception exception) {
//                                Log.e("fail", "upload fail");
//                            }
//                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                Log.e("success", "upload success");
//
//                                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
//                                // ...
//                            }
//                        });

                        
                    }
                }
            });

}