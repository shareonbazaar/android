package eu.shareonbazaar.dev.bazaar.currentuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.currentuser.CurrentUser;
import eu.shareonbazaar.dev.bazaar.model.currentuser.CurrentUserProfile;
import eu.shareonbazaar.dev.bazaar.utilities.RoundImageTransformation;

import static eu.shareonbazaar.dev.bazaar.utilities.Constants.CURRENT_USER;

public class CurrentUserDetailsActivity extends AppCompatActivity
        implements CurrentUserContract.View{

    private CurrentUser currentUser;
    private CurrentUserContract.Presenter mPresenter;

    @BindView(R.id.tl_current_user_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img_current_user_image)
    ImageView mProfileImage;
    @BindView(R.id.tv_current_user_name)
    TextView mUserName;
    @BindView(R.id.tv_current_user_location)
    TextView mLocation;
    @BindView(R.id.tv_current_user_about)
    TextView mAbout;
    @BindView(R.id.rv_current_user_skills)
    RecyclerView mSkills;
    @BindView(R.id.rv_current_user_interests)
    RecyclerView mInterests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_user_details);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        if(savedInstanceState != null){
            currentUser = savedInstanceState.getParcelable(CURRENT_USER);
        }else{
            Intent parentIntent = getIntent();
            currentUser = parentIntent.getExtras().getParcelable(CURRENT_USER);
        }
        showCurrentUser();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void setPresenter(CurrentUserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showCurrentUser() {
        CurrentUserProfile profile = currentUser.getUserProfile();
        String userName = profile.getUserName();
        String tempLocation = profile.getUserLocation();
        String hometown = profile.getUserHometown();
        String about = currentUser.getUserDescription();
        String imageUrl = profile.getUserImageUrl();
        String location = "";

        mUserName.setText(userName);

        Picasso.with(getApplicationContext())
                .load(imageUrl)
                .error(R.drawable.ic_account_circle_24dp)
                .placeholder(R.drawable.ic_account_circle_24dp)
                .transform(new RoundImageTransformation())
                .into(mProfileImage);

        if(tempLocation != null && !tempLocation.isEmpty()){
            location += tempLocation;
        }
        if(hometown != null && !hometown.isEmpty()){
            location += ", " + hometown;
        }
        if(location.isEmpty()){
            location = "Not set";
        }
        mLocation.setText(location);

        if(about == null || about.isEmpty()){
            mAbout.setVisibility(View.GONE);
        }else {
            mAbout.setText(about);
        }
    }

    @Override
    public void showEditUi() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CURRENT_USER, currentUser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.current_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_current_user:
                //showSearchDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
