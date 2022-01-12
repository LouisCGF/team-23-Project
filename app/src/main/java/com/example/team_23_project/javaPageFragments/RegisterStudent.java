package com.example.team_23_project.javaPageFragments;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.team_23_project.R;
import com.example.team_23_project.databinding.RegisterStudentBinding;

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
                System.out.println(binding.courseStudentTxt.getText());
                System.out.println(binding.passwordStudentRegTxt.getText());
                System.out.println(binding.passwordConfirmStudentRegTxt.getText());

                NavHostFragment.findNavController(RegisterStudent.this)
                        .navigate(R.id.action_registerStudent_to_SecondFragment);
                getActivity().overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

    }

    public void changeStatusBarColour(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
}
