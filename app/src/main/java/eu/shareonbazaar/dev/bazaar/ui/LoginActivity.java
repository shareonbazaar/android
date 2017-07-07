package eu.shareonbazaar.dev.bazaar.ui;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.models.Authentication;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import eu.shareonbazaar.dev.bazaar.utilities.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.tb_back_to_signup)
    Toolbar backToSignup;
    @BindView(R.id.cl_login_mainLayout)
    ConstraintLayout mainLayout;
    @BindView(R.id.et_email_field)
    EditText mEmailField;
    @BindView(R.id.et_password_field)
    EditText mPasswordField;
    @BindView(R.id.btn_login)
    Button mLogin;
    @BindView(R.id.tv_restore_password)
    TextView mRestorePassword;
    @BindView(R.id.iv_facebook_login)
    ImageView mFacebookLogin;
    @BindView(R.id.iv_gplus_login)
    ImageView mGooglePlusLogin;

    private static final int REQUEST_INTERNET_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        backToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        /*SharedPreference sharedPreference = new SharedPreference(getApplicationContext());
        String token = sharedPreference.retrieveToken("TOKEN");*/

        /*if(token.length() != 0){
            loginSuccess();
        }else {
            initViews();
        }*/
        initViews();
    }

    private void initViews() {

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonClicked();
            }
        });

        mRestorePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RestorePasswordActivity.class));
            }
        });
        mFacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginWithFacebookButtonClicked();
            }
        });

        mGooglePlusLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginWithGoogleButtonClicked();
            }
        });
    }

    public void onLoginWithFacebookButtonClicked() {
        // TODO: make a network call to check credentials
    }

    private void loginSuccess(Authentication authentication) {
        Intent userActivityIntent = new Intent(LoginActivity.this, UsersActivity.class);
        // userActivityIntent.putExtra("Personal profile", authentication);
        startActivity(userActivityIntent);

        finish();
    }

    private void onLoginWithGoogleButtonClicked() {
        // TODO: make a network call to check credentials
    }

    private void onLoginButtonClicked() {
        // Reset errors.
        mEmailField.setError(null);
        mPasswordField.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordField.setError(getString(R.string.err_et_pass));
            focusView = mPasswordField;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError(getString(R.string.err_et_email));
            focusView = mEmailField;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailField.setError(getString(R.string.error_invalid_email));
            focusView = mEmailField;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            if(isGreaterOrEqualVersionM()){
                requestPermission(Manifest.permission.INTERNET, REQUEST_INTERNET_PERMISSION);
            }
            else {
                login();
            }
        }
    }

    private void login(){
        UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.loginUser(mEmailField.getText().toString(),
                mPasswordField.getText().toString())
                .enqueue(new Callback<Authentication>() {
                    @Override
                    public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                        try {
                            authenticate(response.body());

                        } catch (Exception e) {
                            Snackbar.make(mainLayout, R.string.login_fail, Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Authentication> call, Throwable t) {
                        Snackbar.make(mainLayout, t.toString(), Snackbar.LENGTH_SHORT).show();
                        Log.d("NETWORK", t.toString());
                    }
                });
    }

    private boolean isGreaterOrEqualVersionM(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private void requestPermission(String permission, int requestCode){
        if (ContextCompat.checkSelfPermission(LoginActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);
            }
            else {
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);
            }
        }
        else {
            switch (requestCode){
                case REQUEST_INTERNET_PERMISSION:
                    login();
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        int permissionGranted = PackageManager.PERMISSION_GRANTED;
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == permissionGranted){
            switch (requestCode){
                case REQUEST_INTERNET_PERMISSION:
                    login();
                    break;
            }
        }
    }

    private boolean isEmailValid(String email) {
        // TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        // TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private void authenticate(Authentication authentication){
        int status = authentication.getStatus();
        String error = authentication.getError();
        String token = authentication.getToken();

        if(status == 200){
            /*SharedPreferences.Editor editor = getSharedPreferences("PREF", MODE_PRIVATE).edit();
            editor.putString("TOKEN", token);
            editor.apply();*/
            SharedPreference sharedPreference = new SharedPreference(getApplicationContext());
            sharedPreference.saveToken("TOKEN", token);

            //TODO: Save user data

            loginSuccess(authentication);
        }else{
            Snackbar.make(mainLayout, error, Snackbar.LENGTH_LONG).show();
        }
    }
}