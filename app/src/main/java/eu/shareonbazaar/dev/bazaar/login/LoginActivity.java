package eu.shareonbazaar.dev.bazaar.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.mainactivity.MainActivity;
import eu.shareonbazaar.dev.bazaar.retrieveuser.RestorePasswordActivity;
import eu.shareonbazaar.dev.bazaar.signup.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    @BindView(R.id.tb_back_to_signup)
    Toolbar backToSignup;
    @BindView(R.id.cl_login_mainLayout)
    ConstraintLayout mainLayout;
    @BindView(R.id.tv_login_error)
    TextView mErrorMessage;
    @BindView(R.id.et_email_field)
    EditText mEmailField;
    @BindView(R.id.et_password_field)
    EditText mPasswordField;
    @BindView(R.id.tv_restore_password)
    TextView mRestorePassword;
    @BindView(R.id.iv_facebook_login)
    ImageView mFacebookLogin;
    @BindView(R.id.iv_gplus_login)
    ImageView mGooglePlusLogin;

    private LoginPresenter loginPresenter;
    private LoginContract.Presenter mPresenter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter(this);

        mPresenter.start(getApplicationContext());
    }

    @OnClick(R.id.btn_login)
    public void login(View view) {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        Log.d("Email", email);
        mPresenter.loginWithEmail(email, password);
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void displayConnectionError(String error) {
        mErrorMessage.setVisibility(View.VISIBLE);
        mErrorMessage.setText(error);
    }

    @Override
    public void displayEmailError(String error) {

    }

    @Override
    public void displayPasswordError(String error) {

    }

    @Override
    public void displayAuthenticationError(String error) {
        mErrorMessage.setVisibility(View.VISIBLE);
        mErrorMessage.setText(error);
    }

    @Override
    public void displayLoadingProgress() {
        mErrorMessage.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showAllUsersUi() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void showSignupUi() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @Override
    public void showRetrieveUserUi() {
        startActivity(new Intent(LoginActivity.this, RestorePasswordActivity.class));
    }
}