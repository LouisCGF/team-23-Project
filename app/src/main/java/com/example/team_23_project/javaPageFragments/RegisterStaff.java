package com.example.team_23_project.javaPageFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.team_23_project.R;
import com.example.team_23_project.databinding.RegisterStaffBinding;

public class RegisterStaff extends Fragment{

    private RegisterStaffBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = RegisterStaffBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.submitStaffRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(binding.firstNameStaffTxt.getText());
                System.out.println(binding.lastNameStaffTxt.getText());
                System.out.println(binding.adminTxt.getText());
                System.out.println(binding.passwordStaffRegTxt.getText());
                System.out.println(binding.passwordConfirmStaffRegTxt.getText());

                NavHostFragment.findNavController(RegisterStaff.this)
                        .navigate(R.id.action_registerStaff_to_SecondFragment);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
            }
        });

        binding.backToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(RegisterStaff.this)
                        .navigate(R.id.action_registerStaff_to_FirstFragment);
            }
        });

    }

}
