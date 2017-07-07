package eu.shareonbazaar.dev.bazaar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.models.Authentication;
import eu.shareonbazaar.dev.bazaar.models.LoggedInUserProfile;

public class PersonalProfileActivity extends AppCompatActivity {

    private static final String AUTHENTICATION_OBJECT = "Personal profile";
    private Authentication authentication;

    @BindView(R.id.tv_personal_name)
    TextView mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        ButterKnife.bind(this);

        Intent parentIntent = getIntent();
        authentication = parentIntent.getExtras().getParcelable(AUTHENTICATION_OBJECT);
        LoggedInUserProfile profile = authentication.getLoggedInUser().getUserProfile();
        String name = "Hi, " + profile.getUserName() + " work in progress.";

        mUsername.setText(name);
    }
}
