package com.avnhome.aifriender.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avnhome.aifriender.Fragments.SignUpUserFragment;
import com.avnhome.aifriender.IBMFriender.IBMFrienderApiClient;
import com.avnhome.aifriender.Model.ErrorCode;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;
import com.avnhome.aifriender.Twitter.TwitterManager;
import com.google.firebase.auth.FirebaseAuth;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth auth;
    private SessionManager<TwitterSession> sessionManager;
    private TwitterSession twitterSession;

    private int backButtonCount = 0;

    private Button signupBtn;
    private TextView logoutBtn;

    private SignUpUserFragment signUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterManager.initialize(this);

        setContentView(R.layout.activity_sign_up);

        sessionManager = TwitterCore.getInstance().getSessionManager();
        twitterSession = sessionManager.getActiveSession();
        auth = FirebaseAuth.getInstance();

        findViewByIds();
        User user = new User.UserBuilder(twitterSession.getUserName()).build();

        signUpFragment = SignUpUserFragment.newInstance(user);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_signup_frg,signUpFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {

        if (backButtonCount >= 1)
        {
            logout();
            backButtonCount = 0;
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to logout Twitter.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    private void logout(){
        if (twitterSession != null){
            sessionManager.clearActiveSession();
        }

        auth.signOut();
        updateLoginUI();
    }

    private void signUp(){
        Call<ErrorCode> call = IBMFrienderApiClient.getIBMService().createUser(signUpFragment.getUser());
        call.enqueue(new Callback<ErrorCode>() {
            @Override
            public void onResponse(Call<ErrorCode> call, Response<ErrorCode> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ErrorCode> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Create User Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateLoginUI() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void findViewByIds(){
        signupBtn = findViewById(R.id.sign_up_btn);
        signupBtn.setOnClickListener(this);
        logoutBtn = findViewById(R.id.logout_account_text_sa);
        logoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_up_btn:
                signUpFragment.getValidator().validate();
                if (signUpFragment.isValidateSuccess()){
                    signUp();
                }
                break;
            case R.id.logout_account_text_sa:
                logout();
                break;
        }
    }

}
