package com.example.team_23_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.team_23_project.databinding.FaqPageBinding;

public class FAQandQA extends Fragment {

    private FaqPageBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FaqPageBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.backToMainFaqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(binding.titleFaqTxt.getText());
                System.out.println(binding.infoFaqTxt.getText());

                NavHostFragment.findNavController(FAQandQA.this)
                        .navigate(R.id.action_FAQandQA_to_FirstFragment);
            }
        });

        binding.contactFromFaqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FAQandQA.this)
                        .navigate(R.id.action_FAQandQA_to_contactUs);
            }
        });
    }
}
