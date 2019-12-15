package com.avnhome.aifriender.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.avnhome.aifriender.Interfaces.OnLoadedListener;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;
import com.avnhome.aifriender.Twitter.CustomTwitterApiClient;
import com.avnhome.aifriender.Twitter.TwitterManager;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import agency.tango.android.avatarview.IImageLoader;
import agency.tango.android.avatarview.loader.PicassoLoader;
import agency.tango.android.avatarview.views.AvatarView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link DescriptionUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescriptionUserFragment extends Fragment implements OnLoadedListener<com.twitter.sdk.android.core.models.User> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String USER_PARAM = "USER";

    // TODO: Rename and change types of parameters
    private User user;
    private AvatarView avatarView;
    private IImageLoader imageLoader;
    private ImageView backgroundView;
    private View descDetail;


    public DescriptionUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user Parameter 1.
     * @return A new instance of fragment DescriptionUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescriptionUserFragment newInstance(User user) {
        DescriptionUserFragment fragment = new DescriptionUserFragment();
        Bundle args = new Bundle();
        args.putSerializable(USER_PARAM, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(USER_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description_user, container, false);
        findViewByIds(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (user != null){
            TwitterManager.getLookupUser(user.getTwitterId(),this);
        }
    }

    private void findViewByIds(View view){
        avatarView = view.findViewById(R.id.avatar_iv);
        backgroundView = view.findViewById(R.id.background_iv);
        descDetail = view.findViewById(R.id.desc_detail);
    }

    public void setVisiableDescDetail(int visibility){
        descDetail.setVisibility(visibility);
    };

    @Override
    public void onComplete(com.twitter.sdk.android.core.models.User user) {
        imageLoader = new PicassoLoader();
        imageLoader.loadImage(avatarView, user.profileImageUrlHttps,this.user.getTwitterId());
        Picasso.with(getContext()).load(user.profileBannerUrl).into(backgroundView);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("DescriptionUserFragment", "onFailure: Load avatar: ",t);
    }
}
