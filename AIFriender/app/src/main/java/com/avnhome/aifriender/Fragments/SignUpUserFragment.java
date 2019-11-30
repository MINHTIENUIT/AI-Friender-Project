package com.avnhome.aifriender.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpUserFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String USER_PARAM = "USER";

    // TODO: Rename and change types of parameters
    private User user;

    private TextInputEditText emailET;
    private TextInputEditText userNameET;
    private TextInputEditText dateOfBirthET;
    private TextInputEditText twitterET;


    public SignUpUserFragment() {
        // Required empty public constructor
    }

    public static SignUpUserFragment newInstance(User user) {
        SignUpUserFragment fragment = new SignUpUserFragment();
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
        View root = inflater.inflate(R.layout.fragment_sign_up_user, container, false);
        findViewByIds(root);
        return root;
    }

    public User getUser() {
        user = new User.UserBuilder(user.getTwitterId())
                .withEmail(emailET.getText().toString())
                .withUserName(userNameET.getText().toString())
                .withDOB(dateOfBirthET.getText().toString())
                .build();
        return user;
    }

    private void findViewByIds(View view){
        twitterET = view.findViewById(R.id.twitter_id);
        twitterET.setText(user.getTwitterId());
        emailET = view.findViewById(R.id.email_et);
        userNameET = view.findViewById(R.id.user_name_et);
        dateOfBirthET = view.findViewById(R.id.dob_et);
        dateOfBirthET.setOnClickListener(this);
    }

    private void pickDate(){
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText(R.string.et_birthday);
        builder.setSelection(new Date().getTime());
        final MaterialDatePicker<Long> picker = builder.build();
        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                dateOfBirthET.setText(DateFormat.getDateInstance().format(new Date(picker.getSelection())));
            }
        });
        assert getFragmentManager() != null;
        picker.show(getFragmentManager(),picker.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dob_et:
                pickDate();
                break;
        }
    }
}
