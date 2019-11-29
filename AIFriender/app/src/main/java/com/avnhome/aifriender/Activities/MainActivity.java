package com.avnhome.aifriender.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.avnhome.aifriender.Fragments.ChartFragment;
import com.avnhome.aifriender.Fragments.DescriptionChartFragment;
import com.avnhome.aifriender.Fragments.DescriptionUserFragment;
import com.avnhome.aifriender.IBMFriender.FrienderManager;
import com.avnhome.aifriender.IBMFriender.IBMFrienderApiClient;
import com.avnhome.aifriender.Model.PersonalityOfChart;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.Model.UserTwitter;
import com.avnhome.aifriender.R;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.avnhome.aifriender.IBMFriender.IBMFrienderApiClient.getIBMService;

public class MainActivity extends AppCompatActivity implements SlidingUpPanelLayout.PanelSlideListener, View.OnClickListener {
    private Button logoutBtn;
    private FirebaseAuth auth;
    private SessionManager<TwitterSession> sessionManager;
    private TwitterSession twitterSession;


    private SlidingUpPanelLayout mLayout;

    private ChartFragment chartFragment;
    private DescriptionChartFragment descriptionChartFragment;
    private DescriptionUserFragment descriptionUserFragment;

    private User user;


//    private final WorkManager workManager = WorkManager.getInstance();

    private int backButtonCount = 0;


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

        chartFragment = ChartFragment.newInstance("Test1", "Test2");;
        descriptionChartFragment = DescriptionChartFragment.newInstance("Test1", "Test2");
        descriptionUserFragment = DescriptionUserFragment.newInstance("Test1", "Test2");
        loadChartFragment();

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
        TestAPI();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestAPI();
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
        }
    }

    @Override
    protected void onResume() {
        TestAPI();
        super.onResume();
    }

    @Override
    protected void onPause() {
        TestAPI();
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
        chartFragment = ChartFragment.newInstance("Test1", "Test2");
        descriptionChartFragment = DescriptionChartFragment.newInstance("Test1", "Test2");
        descriptionUserFragment = DescriptionUserFragment.newInstance("Test1", "Test2");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_chart_frg, chartFragment);
        fragmentTransaction.add(R.id.content_description_chart, descriptionChartFragment);
        fragmentTransaction.add(R.id.content_description_User, descriptionUserFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
    }

    @Override
    public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
        if (user != null){
            Log.e("TIEN", "TestAPI: " + user.toString());
            TestAPI();
        }else {
            Log.e("TIEN", "TestAPI: NULL OBJECT");
            TestAPI();
        }
    }

    private void TestAPI(){
        user = FrienderManager.getUser(twitterSession);
        System.out.println("TIEN: " + user.toString());
    }
}
