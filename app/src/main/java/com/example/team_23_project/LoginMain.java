package com.example.team_23_project;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.team_23_project.databinding.FragmentFirstBinding;

import java.util.Objects;

public class LoginMain extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(binding.emailTxt.getText());
                System.out.println(binding.passwordTxt.getText());
                //if (binding.emailTxt == )
                NavHostFragment.findNavController(LoginMain.this)
                        .navigate(R.id.action_FirstFragment_to_searchBar);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });
        binding.regStuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                NavHostFragment.findNavController(LoginMain.this)
                        .navigate(R.id.action_FirstFragment_to_registerStudent);
            }
        });
        binding.regStaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                NavHostFragment.findNavController(LoginMain.this)
                        .navigate(R.id.action_FirstFragment_to_registerStaff);
            }
        });
        binding.faqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                NavHostFragment.findNavController(LoginMain.this)
                        .navigate(R.id.action_FirstFragment_to_FAQandQA);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
