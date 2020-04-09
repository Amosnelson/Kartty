package com.example.kartty.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kartty.MainActivity;
import com.example.kartty.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }

    private TextView alreadyHaveAnAct;
    private FrameLayout parentFrameLayout;

    private EditText email,fullname,password,confirmPassword;

    private ImageButton closeBtn;
    private Button signUpBtn;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private String emailPattern="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private FirebaseFirestore firebaseFirestore;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_sign_up, container, false);
        alreadyHaveAnAct = view.findViewById(R.id.already_have_acct);

        email = view.findViewById(R.id.sign_up_email);
        fullname = view.findViewById(R.id.sign_up_name);
        password = view.findViewById(R.id.sign_up_password);
        confirmPassword = view.findViewById(R.id.sign_up_confirm_password);
        closeBtn = view.findViewById(R.id.sign_up_close);
        signUpBtn = view.findViewById(R.id.sign_up_btn);
        progressBar = view.findViewById(R.id.sign_up_progressBar);

        parentFrameLayout = getActivity().findViewById(R.id.register_framelayout);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseFirestore = FirebaseFirestore.getInstance();



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyHaveAnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mainIntent();
            }
        });

        email.addTextChangedListener(new TextWatcher() {
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

        fullname.addTextChangedListener(new TextWatcher() {
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

        password.addTextChangedListener(new TextWatcher() {
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

        confirmPassword.addTextChangedListener(new TextWatcher() {
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




        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkEmailAndPassword();
            }
        });
    }

    private void checkEmailAndPassword()
    {
        Drawable customErrorIcon = getResources().getDrawable(R.drawable.error);
        customErrorIcon.setBounds(0,0,customErrorIcon.getIntrinsicWidth(),customErrorIcon.getIntrinsicHeight());
        if (email.getText().toString().matches(emailPattern))
        {
           if (password.getText().toString().equals(confirmPassword.getText().toString()))
           {
               progressBar.setVisibility(View.VISIBLE);
               signUpBtn.setEnabled(false);
               signUpBtn.setTextColor(Color.argb(50,255,255,255));


               firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {

                               if (task.isSuccessful())
                               {

                                   Map<Object,String>userdata = new HashMap<>();
                                   userdata.put("fullname",fullname.getText().toString());
                                   firebaseFirestore.collection("USERS")
                                           .add(userdata)
                                           .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                               @Override
                                               public void onComplete(@NonNull Task<DocumentReference> task)
                                               {
                                                if (task.isSuccessful())
                                                {
                                                    mainIntent();
                                                }else {
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                    signUpBtn.setEnabled(true);
                                                    signUpBtn.setTextColor(Color.rgb(255,255,255));
                                                    String error = task.getException().getMessage();
                                                    Toast.makeText(getActivity(),error,Toast.LENGTH_LONG).show();

                                                }

                                               }
                                           });

                               }else
                               {
                                   progressBar.setVisibility(View.INVISIBLE);

                                   signUpBtn.setEnabled(true);
                                   signUpBtn.setTextColor(Color.rgb(255,255,255));

                                   String error = task.getException().getMessage();
                                   Toast.makeText(getActivity(),error,Toast.LENGTH_LONG).show();
                               }
                           }
                       });
           }else {
               confirmPassword.setError("Password doesn't matched",customErrorIcon);

           }
        }else {

            email.setError("Invalid Email",customErrorIcon);

        }

    }

    private void checkInput()
    {
        if (!TextUtils.isEmpty(email.getText()))
        {
            if (!TextUtils.isEmpty(fullname.getText()))
            {
                if (!TextUtils.isEmpty(password.getText())&& password.length()>=8)
                {
                    if (!TextUtils.isEmpty(confirmPassword.getText()))
                    {
                        signUpBtn.setEnabled(true);
                        signUpBtn.setTextColor(Color.rgb(255,255,255));
                    }else {
                        signUpBtn.setEnabled(false);
                        signUpBtn.setTextColor(Color.argb(50,255,255,255));


                    }
                }else {
                    signUpBtn.setEnabled(false);
                    signUpBtn.setTextColor(Color.argb(50,255,255,255));


                }
            }else {
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.argb(50,255,255,255));


            }
        }else
        {
            signUpBtn.setEnabled(false);
            signUpBtn.setTextColor(Color.argb(50,255,255,255));

        }
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slidepout_from_right );
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void mainIntent(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
