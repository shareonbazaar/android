package eu.shareonbazaar.dev.bazaar.peopledetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.adapters.CustomJsonAdapter;
import eu.shareonbazaar.dev.bazaar.model.JsonObject;
import eu.shareonbazaar.dev.bazaar.model.User;
import eu.shareonbazaar.dev.bazaar.utilities.RoundImageTransformation;

public class PeopleDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tb_general_toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_profile_user_image)
    ImageView profileImage;
    @BindView(R.id.tv_profile_user_name)
    TextView userNameTextView;
    @BindView(R.id.tv_profile_location)
    TextView locationTextView;
    @BindView(R.id.tv_profile_hometown)
    TextView hometownTextView;
    @BindView(R.id.tv_about_me)
    TextView aboutMeTextView;
    @BindView(R.id.rv_skills_list)
    RecyclerView skillsList;
    @BindView(R.id.rv_interest_list)
    RecyclerView interestList;
    @BindView(R.id.iv_bookmark_user)
    ImageView bookmarkUser;

    private CustomJsonAdapter customJsonAdapter;
    public static final String TOKEN = "TOKEN";
    public static final String USER_ID = "USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        skillsList.setHasFixedSize(true);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        Intent parentIntent = getIntent();
        User user = parentIntent.getExtras().getParcelable("User");
        initView(user);

        bookmarkUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Api needed here", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initView(User user){
        String emptyFieldPlaceholder = "Not set";
        if (user != null) {
            String imageUrl = user.getPicture();
            Picasso.with(getApplicationContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.image_placeholder_detail)
                    .error(R.drawable.image_placeholder_detail_error)
                    .transform(new RoundImageTransformation())
                    .into(profileImage);
            String userName = user.getName();
            userNameTextView.setText(userName);
            String location = (user.getLocation().length() > 0) ? user.getLocation() : emptyFieldPlaceholder;
            locationTextView.setText(location);
            String hometown = (user.getHometown().length() > 0) ? user.getHometown() : emptyFieldPlaceholder;
            hometownTextView.setText(hometown);
            String aboutMe = (user.getAboutMe() != null) ? user.getAboutMe() : emptyFieldPlaceholder;
            aboutMeTextView.setText(aboutMe);

            ArrayList<JsonObject> skills = user.getSkills();
            Log.d("SIZE", ">" + user.getSkills().size());
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            customJsonAdapter = new CustomJsonAdapter();
            customJsonAdapter.setJsonData(skills);
            skillsList.setLayoutManager(layoutManager);
            skillsList.setAdapter(customJsonAdapter);
        }
    }
}
