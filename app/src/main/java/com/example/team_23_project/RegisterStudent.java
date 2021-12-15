package com.example.team_23_project;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.team_23_project.databinding.RegisterStudentBinding;
import com.example.team_23_project.InsertData;

public class RegisterStudent extends Fragment {

    private RegisterStudentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = RegisterStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.submitStudentRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(binding.firstNameStudentTxt.getText());
                System.out.println(binding.lastNameStudentTxt.getText());
                System.out.println(binding.studentNumberTxt.getText());
                System.out.println(binding.courseStudentTxt.getText());
                System.out.println(binding.passwordStudentRegTxt.getText());
                System.out.println(binding.passwordConfirmStudentRegTxt.getText());
                Editable firstName = binding.firstNameStudentTxt.getText();
                Editable lastName = binding.lastNameStudentTxt.getText();
//                if (firstName != null && lastName != null) {
//                    addRecordUser
//                }

                NavHostFragment.findNavController(RegisterStudent.this)
                        .navigate(R.id.action_registerStudent_to_SecondFragment);
            }
        });

    }
}
