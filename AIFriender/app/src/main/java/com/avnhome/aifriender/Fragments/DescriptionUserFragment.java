package com.avnhome.aifriender.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link DescriptionUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescriptionUserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String USER_PARAM = "USER";

    // TODO: Rename and change types of parameters
    private User user;

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

        if (user != null){
            System.out.println("Description User: " + user.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description_user, container, false);

        return view;
    }

    private void findViewByIds(View view){

    }

}
