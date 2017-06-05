package eu.shareonbazaar.dev.bazaar.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.models.Authentication;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import eu.shareonbazaar.dev.bazaar.utilities.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    private TextView tvRegister;
    private TextView tvRestore;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private CheckBox checkboxRemember;
    private Button btnFacebook;
    private Button btnGoogle;

    private static final int REQUEST_INTERNET_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Toolbar backToSignup = (Toolbar) findViewById(R.id.back_to_signup);

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
        relativeLayout = (RelativeLayout)findViewById(R.id.login_mainLayout);
        //tvRegister = (TextView) findViewById(R.id.tvRegister);
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
        /*tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });*/
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

    private void loginSuccess(Authentication authentication) {
        Intent userActivityIntent = new Intent(LoginActivity.this, UsersActivity.class);
        userActivityIntent.putExtra("Personal profile", authentication);
        startActivity(userActivityIntent);

        finish();
    }

    private void onLoginWithGoogleButtonClicked() {
        // TODO: make a network call to check credentials
    }

    private void onLoginButtonClicked() {
        // Reset errors.
        etEmail.setError(null);
        etPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            etPassword.setError(getString(R.string.err_et_pass));
            focusView = etPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.err_et_email));
            focusView = etEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            etEmail.setError(getString(R.string.error_invalid_email));
            focusView = etEmail;
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
        service.loginUser(etEmail.getText().toString(),
                etPassword.getText().toString())
                .enqueue(new Callback<Authentication>() {
                    @Override
                    public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                        try {
                            authenticate(response.body());

                        } catch (Exception e) {
                            Snackbar.make(relativeLayout, R.string.login_fail, Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Authentication> call, Throwable t) {
                        Snackbar.make(relativeLayout, t.toString(), Snackbar.LENGTH_SHORT).show();
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
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
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
        String id = authentication.getId();

        if(status == 200){
            SharedPreference sharedPreference = new SharedPreference(getApplicationContext());
            sharedPreference.saveToken("TOKEN", token);

            //TODO: Save user id

            loginSuccess(authentication);
        }else{
            Snackbar.make(relativeLayout, error, Snackbar.LENGTH_LONG).show();
        }
    }
}