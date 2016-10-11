package eu.shareonbazaar.dev.bazaar.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.User;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import eu.shareonbazaar.dev.bazaar.ui.RecyclerAdapter;
import eu.shareonbazaar.dev.bazaar.utility.SharedPreference;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity implements RecyclerAdapter.ClickListener  {

    private LinearLayout linearLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        SharedPreference sharedPreference = new SharedPreference(getApplication());
        String token = sharedPreference.retrieveToken("TOKEN");

        linearLayout = (LinearLayout)findViewById(R.id.user_layout);
        recyclerView = (RecyclerView) findViewById(R.id.user_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.getUsers(token, new HashMap<String, String>())
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        //Log.d("LOG_TAG", response.body().get(0).getName());
                        loadUsers(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Snackbar snackbar = Snackbar
                                .make(linearLayout, "Data retrieval failed!", Snackbar.LENGTH_LONG);

                        snackbar.show();
                        String message = t.toString();
                        Log.d("LOG_TAG", message);
                    }
                });
    }

    private void loadUsers(List<User> users){
        RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(), users);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void itemClicked(View view, int position) {
        Snackbar snackbar = Snackbar
                .make(linearLayout, "Click working", Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
