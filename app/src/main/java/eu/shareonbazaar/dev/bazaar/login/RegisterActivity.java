package eu.shareonbazaar.dev.bazaar.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.activity.UsersActivity;
import eu.shareonbazaar.dev.bazaar.model.Authentication;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    public static final int MIN_PASSWORD_LENGTH = 4;
    private EditText etEmail;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPassword;
    private EditText etConfirmPass;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initUI();
    }

    private void initUI() {
        etEmail = (EditText) findViewById(R.id.etEmail);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPass = (EditText) findViewById(R.id.etConfirmPass);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterButtonClicked();
            }
        });
    }

    private void onRegisterButtonClicked() {
        validateInput();

        UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.signupUser(etEmail.getText().toString(),
                etPassword.getText().toString(),
                etConfirmPass.getText().toString(),
                etFirstName.getText().toString(),
                etLastName.getText().toString())
                .enqueue(new Callback<Authentication>() {
                    @Override
                    public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                        try {
                            String token = response.body().getToken();
                            signupSuccess();
                        } catch (Exception e) {
                            Toast.makeText(RegisterActivity.this, R.string.signup_fail, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Authentication> call, Throwable t) {

                    }
                });
    }

    private void signupSuccess() {
        Toast.makeText(RegisterActivity.this, R.string.signup_success, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this, UsersActivity.class));
    }

    private void validateInput() {
        if (etEmail.getText().toString().equals("")) {
            etEmail.setError(getString(R.string.err_et_email));
            return;
        }
        if (etFirstName.getText().toString().equals("")) {
            etFirstName.setError(getString(R.string.err_et_first_name));
            return;
        }
        if (etLastName.getText().toString().equals("")) {
            etLastName.setError(getString(R.string.err_et_last_name));
            return;
        }
        if (etPassword.getText().toString().length() < MIN_PASSWORD_LENGTH) {
            etPassword.setError(getString(R.string.err_et_pass));
            return;
        }
        if (!etPassword.getText().toString().equals(etConfirmPass.getText().toString())) {
            etConfirmPass.setError(getString(R.string.err_et_confirm_pass));
            return;
        }
    }
}