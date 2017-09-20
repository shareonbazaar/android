package eu.shareonbazaar.dev.bazaar.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.mainactivity.MainActivity;
import eu.shareonbazaar.dev.bazaar.retrieveuser.RestorePasswordActivity;
import eu.shareonbazaar.dev.bazaar.signup.SignUpActivity;
import eu.shareonbazaar.dev.bazaar.utilities.LoadingFragment;

import static eu.shareonbazaar.dev.bazaar.utilities.Constants.LOADING_DIALOG_TEXT;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

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

        loginPresenter.start(getApplicationContext());
        // mPresenter.start(getApplicationContext());
    }

    @OnClick(R.id.btn_login)
    public void login(View view) {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        Log.d("Email", email);
        loginPresenter.loginWithEmail(email, password);
        // mPresenter.loginWithEmail(email, password);
        displayLoadingProgress();
    }

    @OnClick(R.id.ll_back_to_signup)
    public void showSignUpInterface(){
        showSignupUi();
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
        //mPresenter = presenter;
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

        Bundle bundle = new Bundle();
        bundle.putString(LOADING_DIALOG_TEXT, "Logging in...");

        LoadingFragment dialog = new LoadingFragment();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "LoadingFragment");
    }

    @Override
    public void showAllUsersUi() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void showSignupUi() {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    @Override
    public void showRetrieveUserUi() {
        startActivity(new Intent(LoginActivity.this, RestorePasswordActivity.class));
    }
}