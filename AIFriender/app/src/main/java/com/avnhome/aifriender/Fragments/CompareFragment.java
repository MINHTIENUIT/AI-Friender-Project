package com.avnhome.aifriender.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avnhome.aifriender.Interfaces.OnLoadedListener;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;
import com.avnhome.aifriender.Twitter.TwitterManager;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompareFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompareFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PERSONALITY_USER = "PERSONALITY";
    private static final String PERSONALITY_FRIEND = "PERSONALITY_Friend";

    // TODO: Rename and change types of parameters
    private User user;
    private User friend;

    private ImageView avatarUser;
    private ImageView avatarFriend;
    private TextView percentCompare;

    private final OnLoadedListener<com.twitter.sdk.android.core.models.User> onLoadedUserListener =
            new OnLoadedListener<com.twitter.sdk.android.core.models.User>() {
                @Override
                public void onComplete(com.twitter.sdk.android.core.models.User user) {
                    Picasso.with(getContext()).load(user.profileImageUrlHttps).into(avatarUser);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("CompareFragment", "onFailure: Load avatar user", t);
                }
            };

    private final OnLoadedListener<com.twitter.sdk.android.core.models.User> onLoadedFriendListener =
            new OnLoadedListener<com.twitter.sdk.android.core.models.User>() {
                @Override
                public void onComplete(com.twitter.sdk.android.core.models.User user) {
                    Picasso.with(getContext()).load(user.profileImageUrlHttps).into(avatarFriend);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("CompareFragment", "onFailure: Load avatar Friend", t);
                }
            };


    public CompareFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param personality_user Parameter 1.
     * @param personality_friend Parameter 2.
     * @return A new instance of fragment CompareFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompareFragment newInstance(User personality_user, User personality_friend) {
        CompareFragment fragment = new CompareFragment();
        Bundle args = new Bundle();
        args.putSerializable(PERSONALITY_USER, personality_user);
        args.putSerializable(PERSONALITY_FRIEND, personality_friend);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(PERSONALITY_USER);
            friend = (User) getArguments().getSerializable(PERSONALITY_FRIEND);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compare, container, false);
        findViewByIds(view);
        return view;
    }

    private void findViewByIds(View view){
        avatarUser = view.findViewById(R.id.avatar_user);
        avatarFriend = view.findViewById(R.id.avatar_friend);
        percentCompare = view.findViewById(R.id.percent_compare);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TwitterManager.getLookupUser(user.getTwitterId(),onLoadedUserListener);
        TwitterManager.getLookupUser(friend.getTwitterId(), onLoadedFriendListener);

        percentCompare.setText(compare() + "%");
    }

    private int compare(){
        double sum = 0;
        for (int i = 0; i < 5; i++){
            sum += (100 - (Math.abs(user.getPersonalities().get(i) - friend.getPersonalities().get(i))));
        }
        return Math.round(Math.round(sum/5));
    }
}
