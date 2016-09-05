package eu.shareonbazaar.dev.bazaar.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.User;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import eu.shareonbazaar.dev.bazaar.userInterface.RecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        linearLayout = (LinearLayout)findViewById(R.id.user_layout);
        recyclerView = (RecyclerView) findViewById(R.id.user_list);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.getUsers(new HashMap<String, String>())
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        loadUsers(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Snackbar snackbar = Snackbar
                                .make(linearLayout, "Data retrieval failed!", Snackbar.LENGTH_LONG);

                        snackbar.show();
                        Log.d("LOG_TAG", "Data retrieval failed!");
                    }
                });
    }

    private void loadUsers(List<User> users){
        adapter = new RecyclerAdapter(getApplicationContext(), users);
        recyclerView.setAdapter(adapter);
    }



}
