package eu.shareonbazaar.dev.bazaar.currentuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.login.Authentication;
import eu.shareonbazaar.dev.bazaar.model.currentuser.CurrentUserProfile;

public class CurrentUserDetailsActivity extends AppCompatActivity {

    private static final String AUTHENTICATION_OBJECT = "Personal profile";
    private Authentication authentication;

    @BindView(R.id.tv_personal_name)
    TextView mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_user_details);
        ButterKnife.bind(this);

        Intent parentIntent = getIntent();
        authentication = parentIntent.getExtras().getParcelable(AUTHENTICATION_OBJECT);
        CurrentUserProfile profile = authentication.getCurrentUser().getUserProfile();
        String name = "Hi, " + profile.getUserName() + " work in progress.";

        mUsername.setText(name);
    }
}
