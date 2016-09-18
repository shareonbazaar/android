package eu.shareonbazaar.dev.bazaar.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import eu.shareonbazaar.dev.bazaar.R;

public class LoginActivity extends AppCompatActivity {
    private TextView tvRegister;
    private TextView tvRestore;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private CheckBox checkboxRemember;
    private Button btnFacebook;
    private Button btnGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvRestore = (TextView) findViewById(R.id.tvRestore);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnFacebook = (Button) findViewById(R.id.btnFacebook);
        btnGoogle = (Button) findViewById(R.id.btnGoogle);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonClicked();
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        tvRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RestorePasswordActivity.class));
            }
        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginWithFacebookButtonClicked();
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginWithGoogleButtonClicked();
            }
        });
    }

    public void onLoginWithFacebookButtonClicked() {
        // TODO: make a network call to check credentials
    }

    private void loginSuccess() {
        // TODO: go to home screen
    }

    private void onLoginWithGoogleButtonClicked() {
        // TODO: make a network call to check credentials
    }

    private void onLoginButtonClicked() {
        // TODO: make a network call to check credentials
    }
}