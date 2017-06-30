package eu.shareonbazaar.dev.bazaar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.utilities.RoundImageTransformation;
import eu.shareonbazaar.dev.bazaar.models.User;

public class IndividualProfile extends AppCompatActivity {

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

    public static final String TOKEN = "TOKEN";
    public static final String USER_ID = "USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_profile);
        ButterKnife.bind(this);

        Intent parentIntent = getIntent();
        User user = parentIntent.getExtras().getParcelable("User");
        initView(user);
    }

    private void initView(User user){
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
            String location = user.getLocation();
            locationTextView.setText(location);
            String hometown = user.getHometown();
            hometownTextView.setText(hometown);
            String aboutMe = user.getAboutMe();
            aboutMeTextView.setText(aboutMe);
        }
    }

    /*private void initView() {
        SharedPreference sharedPreference = new SharedPreference(getApplicationContext());

        final String token = sharedPreference.retrieveToken(TOKEN);
        final String id = getIntent().getStringExtra(USER_ID);

        UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.getUser(token, id)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        // /api/users/{id} seems to return nothing -> NullPointerException
                        // Toast.makeText(IndividualProfile.this, response.body().getId(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("LOG_TAG", t.toString());
                    }
                });
    }*/
}
