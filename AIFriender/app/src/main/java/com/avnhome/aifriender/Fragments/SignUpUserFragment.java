package com.avnhome.aifriender.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpUserFragment extends Fragment implements View.OnClickListener, Validator.ValidationListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String USER_PARAM = "USER";

    // TODO: Rename and change types of parameters
    private User user;

    private Validator validator;
    private boolean validateSuccess;

    @NotEmpty
    @Email
    private TextInputEditText emailET;

    @NotEmpty
    private TextInputEditText userNameET;

    @NotEmpty
    private TextInputEditText dateOfBirthET;

    @Pattern(regex = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Input does not phone number")
    private TextInputEditText phoneNumberET;

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

        try {
            user = new User.UserBuilder(user.getTwitterId())
                    .withEmail(emailET.getText().toString())
                    .withUserName(userNameET.getText().toString())
                    .withDOB(String.valueOf(DateFormat.getDateInstance().parse(dateOfBirthET.getText().toString()).getTime()))
                    .withPhoneNumber(phoneNumberET.getText().toString())
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Validator getValidator() {
        return validator;
    }

    public boolean isValidateSuccess() {
        return validateSuccess;
    }

    private void findViewByIds(View view){
        TextInputEditText twitterET = view.findViewById(R.id.twitter_id);
        twitterET.setText(user.getTwitterId());
        emailET = view.findViewById(R.id.email_et);
        userNameET = view.findViewById(R.id.user_name_et);
        dateOfBirthET = view.findViewById(R.id.dob_et);
        dateOfBirthET.setOnClickListener(this);
        phoneNumberET = view.findViewById(R.id.phone_et);
        validator = new Validator(this);
        validator.setValidationListener(this);
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

    @Override
    public void onValidationSucceeded() {
        validateSuccess = true;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        validateSuccess = false;
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());

            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            } else {
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
