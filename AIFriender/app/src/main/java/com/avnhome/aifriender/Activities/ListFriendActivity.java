package com.avnhome.aifriender.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.avnhome.aifriender.Adapter.FriendAdapter;
import com.avnhome.aifriender.IBMFriender.FrienderManager;
import com.avnhome.aifriender.Interfaces.OnLoadedListener;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;

import java.util.ArrayList;
import java.util.List;

public class ListFriendActivity extends AppCompatActivity implements OnLoadedListener<List<User>>,
FriendAdapter.OnItemClickListener{

    public static final String USER_ID = "USER_ID";

    private User user;

    ArrayList<User> friends = new ArrayList<>();

    RecyclerView rvFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_friend);

        user = (User) getIntent().getSerializableExtra(USER_ID);

        findViewByIds();

        Log.i("ListFriendActivity", "onCreate: User Id " + user.getId());
        FrienderManager.getFriend(user.getId(), this);

    }

    private void findViewByIds(){
        rvFriend = findViewById(R.id.friend_rv);
    }

    @Override
    public void onComplete(List<User> users) {
        friends.clear();
        friends.addAll(users);
        FriendAdapter.setClickListener(this);
        FriendAdapter adapter = new FriendAdapter(friends);
        rvFriend.setAdapter(adapter);
        rvFriend.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e(getLocalClassName(), "onFailure: Load Friend", t);
    }

    @Override
    public void onItemClick(int position, View v) {
        Intent intent = new Intent(ListFriendActivity.this, CompareActivity.class);
        intent.putExtra(CompareActivity.USER, user);
        intent.putExtra(CompareActivity.FRIEND, friends.get(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position, View v) {

    }
}
