package eu.shareonbazaar.dev.bazaar.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.activity.UsersActivity;
import eu.shareonbazaar.dev.bazaar.model.Authentication;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        startActivity(new Intent(LoginActivity.this, UsersActivity.class));
    }

    private void onLoginWithGoogleButtonClicked() {
        // TODO: make a network call to check credentials
    }

    private void onLoginButtonClicked() {
        if (etEmail.getText().toString().equals("")) {
            etEmail.setError(getString(R.string.err_et_email));
            return;
        }
        if (etPassword.getText().toString().equals("")) {
            etPassword.setError(getString(R.string.err_et_pass));
            return;
        }

        UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.loginUser(etEmail.getText().toString(),
                etPassword.getText().toString())
                .enqueue(new Callback<Authentication>() {
                    @Override
                    public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                        try {
                            String token = response.body().getToken();
                            loginSuccess();
                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, R.string.login_fail, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Authentication> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /*private void authenticate(Authentication authentication){
        int status = authentication.getStatus();
        String error = authentication.getError();
        String token = authentication.getToken();

        if(status == 200){
            SharedPreference sharedPreference = new SharedPreference(getActivity());
            sharedPreference.writeToSharedPreference("TOKEN", token);

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }else{
            Snackbar snackbar = Snackbar
                    .make(mFrameLayout, error, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }*/
}