package com.example.singinsingup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public MainActivity activity;

    EditText et_email_in;
    EditText et_passwd_in;
    Button btn_registrarse;
    EditText et_email_up;
    EditText et_passwd_up;
    Button btn_entrar;
    Context contexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_email_in=findViewById(R.id.et_email_in);
        et_passwd_in=findViewById(R.id.et_passwd_in);
        btn_registrarse=findViewById(R.id.btn_registrarse);
        et_email_up=findViewById(R.id.et_email_up);
        et_passwd_up=findViewById(R.id.et_passwd_up);
        btn_entrar=findViewById(R.id.btn_entrar);


        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=et_email_in.getText().toString();
                String passwd=et_passwd_in.getText().toString();
                crearUsuario(email,passwd);
            }
        });

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2=et_email_up.getText().toString();
                String passwd2=et_passwd_up.getText().toString();
                verificarUsuario(email2,passwd2);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser usuario_autenticado=mAuth.getCurrentUser();

    }

    private void verificarUsuario(String email2, String passwd2) {
        mAuth.signInWithEmailAndPassword(email2,passwd2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser usuario_autenticado=mAuth.getCurrentUser();
                    String email_autentificar=usuario_autenticado.getEmail();
                    Intent i=new Intent(contexto,MainActivity_2.class);
                    i.putExtra("email2",email_autentificar);
                    contexto.startActivity(i);

                }else{
                    Log.d("USUARIO",task.getException().getMessage());
                }
            }
        });
    }

    private void crearUsuario(String email, String passwd){
        mAuth.createUserWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser usuario_autenticado=mAuth.getCurrentUser();
                    Log.d("USUARIO",usuario_autenticado.getEmail());
                }else{
                    Log.d("ERROR",task.getException().getMessage());
                }

            }
        });
    }

}