package com.example.kartty.Fragment;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kartty.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment {


    public ResetPasswordFragment() {
        // Required empty public constructor
    }

    private EditText registeredEmail;
    private Button resetPasswordButton;
    private TextView goback,emailIconText;
    private FrameLayout parentFrameLayout;
    private FirebaseAuth firebaseAuth;
    private ViewGroup emailIconContainer;
    private ImageView emailIcon;
    private ProgressBar progressBar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        emailIconContainer = view.findViewById(R.id.email_icon_container);
        emailIconText = view.findViewById(R.id.forget_pass_text);
        emailIcon = view.findViewById(R.id.forget_pass_icon);
        progressBar = view.findViewById(R.id.forget_progressBar);
        registeredEmail = view.findViewById(R.id.reset_email);
        resetPasswordButton = view.findViewById(R.id.reset_btn);
        goback = view.findViewById(R.id.reset_back);

        firebaseAuth = FirebaseAuth.getInstance();
        parentFrameLayout = getActivity().findViewById(R.id.register_framelayout);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registeredEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
 
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                TransitionManager.beginDelayedTransition(emailIconContainer);
                emailIcon.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                resetPasswordButton.setEnabled(false);
                resetPasswordButton.setTextColor(Color.argb(50,255,255,255));
                firebaseAuth.sendPasswordResetEmail(registeredEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){

                                    Toast.makeText(getActivity(), "Email sent successfully", Toast.LENGTH_SHORT).show();

                                }else {
                                    String error = task.getException().getMessage();
                                    progressBar.setVisibility(View.GONE);
                                    TransitionManager.beginDelayedTransition(emailIconContainer);
                                    emailIconText.setText(error);
                                    emailIconText.setTextColor(getResources().getColor(R.color.colorPrimary));
                                    emailIconText.setVisibility(View.VISIBLE);


                                    //      Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

                                }
                                resetPasswordButton.setEnabled(true);
                                resetPasswordButton.setTextColor(Color.rgb(255,255,255));
                            }
                        });
            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());

            }
        });
    }

    private void checkInput() {
        if (TextUtils.isEmpty(registeredEmail.getText()))
        {
            resetPasswordButton.setEnabled(false);
            resetPasswordButton.setTextColor(Color.argb(50,255,255,255));

        }else {
            resetPasswordButton.setEnabled(true);
            resetPasswordButton.setTextColor(Color.rgb(255,255,255));

        }

    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slidepout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();


    }
}
