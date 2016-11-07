package eu.shareonbazaar.dev.bazaar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.User;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import eu.shareonbazaar.dev.bazaar.utility.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity to display public information of any individual's profile
 */
public class IndividualProfile extends AppCompatActivity {

    public static final String TOKEN = "TOKEN";
    public static final String USER_ID = "USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_profile);

        Toast.makeText(this, "Individual profile page!", Toast.LENGTH_SHORT).show();
        initView();
    }

    private void initView() {
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
    }
}
