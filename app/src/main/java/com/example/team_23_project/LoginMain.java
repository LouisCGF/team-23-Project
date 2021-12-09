package com.example.team_23_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.team_23_project.databinding.FragmentFirstBinding;

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

                NavHostFragment.findNavController(LoginMain.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        binding.regStuBtn.setOnClickListener(new View.OnClickListener() { // <- Event handler for register for student
            @Override
            public void onClick(View v) {
                //NavHostFragment.findNavController(LoginMain.this)
                //        .navigate(R.id.action_FirstFragment_to_RegisterForStudent); <- Need to
                //        create new action 'FirstFragement_to_RegisterForStudent' once a register
                //        for student page gets created
            }
        });
        binding.regStaBtn.setOnClickListener(new View.OnClickListener() { // <- Event handler for register for staff
            @Override
            public void onClick(View v) {
                //NavHostFragment.findNavController(LoginMain.this)
                //      .navigate(R.id.action_FirstFragment_to_RegisterForStaff); <- Need to
                //      create new action 'FirstFragment_to_RegisterForStaff' once a register
                //      for staff page gets created
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}