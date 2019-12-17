package com.avnhome.aifriender.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.avnhome.aifriender.Fragments.ChartFragment;
import com.avnhome.aifriender.Fragments.CompareFragment;
import com.avnhome.aifriender.Fragments.DescriptionCompareFragment;
import com.avnhome.aifriender.Fragments.DescriptionUserFragment;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;
import com.avnhome.aifriender.Twitter.TwitterManager;
import com.google.firebase.auth.FirebaseAuth;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

public class CompareActivity extends AppCompatActivity implements SlidingUpPanelLayout.PanelSlideListener,
        View.OnClickListener
{


    public static final String USER = "user";
    public static final String FRIEND = "friend";

    private Button logoutBtn;
    private Button addFriendBtn;

    private FirebaseAuth auth;
    private SessionManager<TwitterSession> sessionManager;
    private TwitterSession twitterSession;

    private SlidingUpPanelLayout mLayout;
    private View contentUser;
    private View contentDescChart;

    private ChartFragment chartFragment;
    private CompareFragment compareFragment;
    private DescriptionUserFragment descriptionUserFragment;
    private DescriptionCompareFragment descriptionCompareFragment;

    private User user;
    private User friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterManager.initialize(this);

        setContentView(R.layout.activity_main);
        sessionManager = TwitterCore.getInstance().getSessionManager();
        twitterSession = sessionManager.getActiveSession();
        auth = FirebaseAuth.getInstance();

        user = (User) getIntent().getSerializableExtra(USER);
        friend = (User) getIntent().getSerializableExtra(FRIEND);

        if (user == null && friend == null) finish();

        findViewByIds();

        loadChartFragment();
    }

    private void findViewByIds(){
        logoutBtn = findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(this);
        addFriendBtn = findViewById(R.id.find_add_friend_btn);
        addFriendBtn.setText(R.string.add_friend_button);
        addFriendBtn.setOnClickListener(this);
        mLayout = findViewById(R.id.sliding_layout);
        mLayout.addPanelSlideListener(this);
        mLayout.setFadeOnClickListener(this);
        contentUser = findViewById(R.id.content_description_User);
        contentUser.setOnClickListener(this);
        contentDescChart = findViewById(R.id.content_description_chart);
    }

    public void loadChartFragment(){
        chartFragment = ChartFragment.newInstance(user, friend);
        compareFragment = CompareFragment.newInstance(user, friend);
        descriptionCompareFragment = DescriptionCompareFragment.newInstance("","");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_chart_frg, chartFragment);
        fragmentTransaction.add(R.id.content_description_chart, compareFragment);
        fragmentTransaction.add(R.id.content_description_User, descriptionCompareFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    @Override
    public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

    }
}
