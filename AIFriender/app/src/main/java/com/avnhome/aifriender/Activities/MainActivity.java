package com.avnhome.aifriender.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.avnhome.aifriender.Fragments.ChartFragment;
import com.avnhome.aifriender.Fragments.DescriptionChartFragment;
import com.avnhome.aifriender.Fragments.DescriptionUserFragment;
import com.avnhome.aifriender.IBMFriender.FrienderManager;
import com.avnhome.aifriender.Interfaces.OnLoadedListener;
import com.avnhome.aifriender.Model.PersonalityOfChart;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;
import com.avnhome.aifriender.Twitter.CustomTwitterApiClient;
import com.avnhome.aifriender.Twitter.TwitterManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SlidingUpPanelLayout.PanelSlideListener,
        View.OnClickListener{
    private Button logoutBtn;
    private FirebaseAuth auth;
    private SessionManager<TwitterSession> sessionManager;
    private TwitterSession twitterSession;


    private User user;
    private int backButtonCount = 0;

    private OnLoadedListener<User> onLoadedUserListener = new OnLoadedListener<User>() {
        @Override
        public void onComplete(User user) {
            MainActivity.this.user = user;
            if (user == null){
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }else{
                if (user.getPersonalities().get(0) == -100){
                    FrienderManager.getPersonality("@realDonaldTrump", user.getId(), onLoadedPersonalityListener);
                }else {
                    System.out.println("TIEN: " + user.getPersonalities().get(0));
                    loadChartFragment();
                }
            }
        }

        @Override
        public void onFailure(Throwable t) {
            Log.w(getPackageName(), "onFailure: Load User", t);
        }
    };

    private OnLoadedListener<PersonalityOfChart> onLoadedPersonalityListener = new OnLoadedListener<PersonalityOfChart>() {
        @Override
        public void onComplete(PersonalityOfChart personality) {
            user = new User.UserBuilder(user)
                    .withPersonality(personality)
                    .build();
            loadChartFragment();
        }

        @Override
        public void onFailure(Throwable t) {
            Log.w(getPackageName(), "onFailure: Load Personality", t);
        }
    };


    private SlidingUpPanelLayout mLayout;
    private View contentUser;
    private View contentDescChart;

    private ChartFragment chartFragment;
    private DescriptionChartFragment descriptionChartFragment;
    private DescriptionUserFragment descriptionUserFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterManager.initialize(this);

        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        sessionManager = TwitterCore.getInstance().getSessionManager();
        twitterSession = sessionManager.getActiveSession();
        auth = FirebaseAuth.getInstance();

        findViewByIds();

        mLayout.addPanelSlideListener(this);
        mLayout.setFadeOnClickListener(this);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (twitterSession != null){
                    sessionManager.clearActiveSession();
                }

                auth.signOut();
                updateLoginUI();

            }
        });


//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button_background, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void findViewByIds(){
        logoutBtn = findViewById(R.id.logout_btn);
        mLayout = findViewById(R.id.sliding_layout);
        contentUser = findViewById(R.id.content_description_User);
        contentUser.setOnClickListener(this);
        contentDescChart = findViewById(R.id.content_description_chart);
    }

    private void updateLoginUI(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null){
            updateLoginUI();
        } else
            FrienderManager.getUser("@realDonaldTrump",onLoadedUserListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {

        if (backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            backButtonCount = 0;
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    public void loadChartFragment(){
        chartFragment = ChartFragment.newInstance(user);
        descriptionChartFragment = DescriptionChartFragment.newInstance(user);
        descriptionUserFragment = DescriptionUserFragment.newInstance(user);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_chart_frg, chartFragment);
        fragmentTransaction.add(R.id.content_description_chart, descriptionChartFragment);
        fragmentTransaction.add(R.id.content_description_User, descriptionUserFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.content_description_chart:
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                break;
            case R.id.content_description_User:
                contentDescChart.setVisibility(View.GONE);
                descriptionUserFragment.setVisiableDescDetail(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
        contentDescChart.setVisibility(View.VISIBLE);
        descriptionUserFragment.setVisiableDescDetail(View.GONE);
    }

    @Override
    public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

    }
}
