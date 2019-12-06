package com.avnhome.aifriender.Fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avnhome.aifriender.Model.PersonalityOfChart;
import com.avnhome.aifriender.R;
import com.avnhome.aifriender.Views.DescriptionChartView;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescriptionChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescriptionChartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PERSONALITY_PARAM1 = "PERSONALITY";

    // TODO: Rename and change types of parameters
    private PersonalityOfChart personality;

    private DescriptionChartView descChartView;


    public DescriptionChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param personality Parameter 1.
     * @return A new instance of fragment DescriptionChartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescriptionChartFragment newInstance(PersonalityOfChart personality) {
        DescriptionChartFragment fragment = new DescriptionChartFragment();
        Bundle args = new Bundle();
        args.putSerializable(PERSONALITY_PARAM1, personality);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            personality = (PersonalityOfChart) getArguments().getSerializable(PERSONALITY_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description_chart, container, false);
        descChartView = view.findViewById(R.id.desc_chart_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        descChartView.setPercents(personality.getPersonalities());
        descChartView.setPercents(Arrays.asList(10d,20d,30d,40d,50d));
    }
}
