package eu.shareonbazaar.dev.bazaar.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.login.LoginActivity;
import eu.shareonbazaar.dev.bazaar.mainactivity.MainActivity;
import eu.shareonbazaar.dev.bazaar.model.login.Authentication;
import eu.shareonbazaar.dev.bazaar.network.ConnectionSetup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPass;

    public static final int MIN_PASSWORD_LENGTH = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void registerUser(){
        onRegisterButtonClicked();
    }

    @OnClick(R.id.ll_back_to_signin)
    public void showSignInInterface(){
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }

    private void onRegisterButtonClicked() {
        validateInput();

        SignUpService service = ConnectionSetup.retrofit.create(SignUpService.class);
        service.signUpUser(etEmail.getText().toString(),
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
                            Toast.makeText(SignUpActivity.this, R.string.signup_fail, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Authentication> call, Throwable t) {

                    }
                });
    }

    private void signupSuccess() {
        Toast.makeText(SignUpActivity.this, R.string.signup_success, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
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