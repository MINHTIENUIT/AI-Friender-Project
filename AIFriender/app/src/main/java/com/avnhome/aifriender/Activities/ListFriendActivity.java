package com.avnhome.aifriender.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.avnhome.aifriender.Adapter.FriendAdapter;
import com.avnhome.aifriender.IBMFriender.FrienderManager;
import com.avnhome.aifriender.Interfaces.OnLoadedListener;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;

import java.util.ArrayList;
import java.util.List;

public class ListFriendActivity extends AppCompatActivity implements OnLoadedListener<List<User>> {

    public static final String USER_ID = "USER_ID";

    private String userId;

    ArrayList<User> friends = new ArrayList<>();

    RecyclerView rvFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_friend);

        userId = getIntent().getStringExtra(USER_ID);

        findViewByIds();

        Log.i("ListFriendActivity", "onCreate: User Id " + userId);
        FrienderManager.getFriend(userId, this);

    }

    private void findViewByIds(){
        rvFriend = findViewById(R.id.friend_rv);
    }

    @Override
    public void onComplete(List<User> users) {
        FriendAdapter adapter = new FriendAdapter(users);
        rvFriend.setAdapter(adapter);
        rvFriend.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e(getLocalClassName(), "onFailure: Load Friend", t);
    }
}
