package com.example.rid_project.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rid_project.databinding.ActivitySelectTextBinding;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Random;



public class SelectTextActivity extends AppCompatActivity {

    private String userUid = "";
    private int cnt;
    private Random random = new Random();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private Button btnNext;
    private ActivitySelectTextBinding binding;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private FirebaseFunctions mFunctions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectTextBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);




        cb1 = binding.cb1;
        cb2 = binding.cb2;
        cb3 = binding.cb3;
        cb4 = binding.cb4;

        btnNext = binding.btnNext;
        btnNext.setOnClickListener(view1 -> {
            Intent intent = new Intent(SelectTextActivity.this,ReadTextActivity.class);
            intent.putExtra("check",Checked(view1));
            startActivity(intent);
            finish();
        });

        Intent getIntent = new Intent(this.getIntent());
        userUid = getIntent.getStringExtra("userUid");


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityResult.launch(intent);
    }

    public String Checked(View v) {
        String resultText = ""; // 체크되었을 때 값을 저장할 스트링 값
        if (cb1.isChecked()) {
            resultText += cb1.getText()+".";
        }
        if (cb2.isChecked()) {
            resultText += cb2.getText()+".";
        }
        if (cb3.isChecked()) {
            resultText += cb3.getText()+".";
        }
        if (cb4.isChecked()) {
            resultText += cb4.getText()+".";
        }
        return resultText;
    }


    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        // cloud storage에 올린 코드
//                        cnt = random.nextInt(1000);
//                        StorageReference storageRef = storage.getReference(userUid.substring(0, 2) + Integer.toString(cnt));


                        // 아래 4줄만 보면 됨 -> 카메라로 촬영한 이미지는 bitmap 형태로 제공
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 4;

                        Bundle extras = result.getData().getExtras();
                        Bitmap bitmap = (Bitmap) extras.get("data");





                        // 여기서부터 Cloud Function 사용한 코드
//                        bitmap = scaleBitmapDown(bitmap, 640);
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                        byte[] data = baos.toByteArray();
//                        String base64encoded = Base64.encodeToString(data, Base64.NO_WRAP);
//
//                        mFunctions = FirebaseFunctions.getInstance();
//
//                        // Create json request to cloud vision
//                        JsonObject request = new JsonObject();
//                        // Add image to request
//                        JsonObject image = new JsonObject();
//                        image.add("content", new JsonPrimitive(base64encoded));
//                        request.add("image", image);
//                        //Add features to the request
//                        JsonObject feature = new JsonObject();
//                        feature.add("type", new JsonPrimitive("TEXT_DETECTION"));
//
//                        annotateImage(request.toString())
//                                .addOnCompleteListener(task -> {
//                                    if (!task.isSuccessful()) {
//                                        Log.e("task","fail");
//                                        // Task failed with an exception
//                                        // ...
//                                    } else {
//                                        Log.e("task","success");
//                                        JsonObject annotation = task.getResult().getAsJsonArray().get(0).getAsJsonObject().get("fullTextAnnotation").getAsJsonObject();
//                                        System.out.format("%nComplete annotation:%n");
//                                        System.out.format("%s%n", annotation.get("text").getAsString());
//                                        // Task completed successfully
//                                        // ...
//                                    }
//                                });



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
//                            }
//                        });


                    }
                }
            });

//    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {
//        int originalWidth = bitmap.getWidth();
//        int originalHeight = bitmap.getHeight();
//        int resizedWidth = maxDimension;
//        int resizedHeight = maxDimension;
//
//        if (originalHeight > originalWidth) {
//            resizedHeight = maxDimension;
//            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
//        } else if (originalWidth > originalHeight) {
//            resizedWidth = maxDimension;
//            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
//        } else if (originalHeight == originalWidth) {
//            resizedHeight = maxDimension;
//            resizedWidth = maxDimension;
//        }
//        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
//    }
//
//    private Task<JsonElement> annotateImage(String requestJson) {
//        return mFunctions
//                .getHttpsCallable("annotateImage")
//                .call(requestJson)
//                .continueWith(new Continuation<HttpsCallableResult, JsonElement>() {
//                    @Override
//                    public JsonElement then(@NonNull Task<HttpsCallableResult> task) {
//                        // This continuation runs on either success or failure, but if the task
//                        // has failed then getResult() will throw an Exception which will be
//                        // propagated down.
//                        return JsonParser.parseString(new Gson().toJson(task.getResult().getData()));
//                    }
//                });
//    }
}