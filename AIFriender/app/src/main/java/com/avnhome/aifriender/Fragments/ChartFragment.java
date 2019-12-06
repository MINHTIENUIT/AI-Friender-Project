package com.avnhome.aifriender.Fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avnhome.aifriender.Model.PersonalityOfChart;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;
import com.avnhome.aifriender.Views.RadarChartView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PERSONALITY_PARAM1 = "PERSONALITY";

    private RadarChartView radarChartView;
    // TODO: Rename and change types of parameters
    private User user;


    public ChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param personality Parameter 1.
     * @return A new instance of fragment ChartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChartFragment newInstance(User personality) {
        ChartFragment fragment = new ChartFragment();
        Bundle args = new Bundle();
        args.putSerializable(PERSONALITY_PARAM1, personality);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(PERSONALITY_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        radarChartView = view.findViewById(R.id.radar_char);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PersonalityOfChart p1 = new PersonalityOfChart(80d/100,40d/100,30d/100,20d/100,40d/100);
        PersonalityOfChart p2 = new PersonalityOfChart(40,20,30,40,50);
        User user1 = new User.UserBuilder("Jack").withPersonality(p1).build();
        User user2 = new User.UserBuilder("Alita").withPersonality(p2).build();
        try {
            radarChartView.setUserForChart(user, user1);
            radarChartView.invalidate();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
