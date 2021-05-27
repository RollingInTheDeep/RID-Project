package com.example.rid_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rid_project.R;
import com.example.rid_project.databinding.ActivitySigninBinding;
import com.example.rid_project.viemodel.MainViewModel;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import org.jetbrains.annotations.NotNull;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener  {

    private ActivitySigninBinding binding;
    private SignInButton btnSignIn;
    private MainViewModel mainViewModel;
    private FirebaseAuth auth;  //  파이버 베이스 인증 객체
    private GoogleApiClient googleApiClient;  //  구글 API 클라이언트 객체
    private static final int RED_SIGN_GOOGLE = 100;  //  구글 로그인 결과 코드


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        btnSignIn = binding.btnMainSignIn;  //  로그인 버튼
        // mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);  //  뷰모델 초기화 과정
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();
        auth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(view1 -> {  // 구글로그인
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, RED_SIGN_GOOGLE);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  // 구글 로그인 인증을 요청 했을 때 결과 값을 되돌려 받는 곳
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RED_SIGN_GOOGLE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){  // 인증결과가 성공이면
                GoogleSignInAccount account = result.getSignInAccount();  //  account 라는 데이터는 구글 로그인 정보를 담고있다.
                Log.d("login", "firebaseAuthWithGoogle:" + account.getId());
                resultLogin(account);  // 로그인 결과 값 출력 수행 메소드
            }
        }
    }

    private void resultLogin(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){  //  로그인 성공
                            Toast.makeText(SignInActivity.this,"로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            String idToken = task.getResult().getUser().getUid();
                            //User user = new User(idToken, account.getGivenName()); // 사용자 정보를 담고 있는 User 객체
                            //mainViewModel.setUser(user);
                            intent.putExtra("userName", account.getGivenName());
                            intent.putExtra("userID", idToken);
                            startActivity(intent);
                        }else{
                            Toast.makeText(SignInActivity.this,"로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull @NotNull ConnectionResult connectionResult) {

    }
}