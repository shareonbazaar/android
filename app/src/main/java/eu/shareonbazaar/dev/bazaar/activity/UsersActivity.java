package eu.shareonbazaar.dev.bazaar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

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
                       /* TextView textView = (TextView)findViewById(R.id.test);
                        for (int i = 0; i < response.body().size(); i++) {
                            textView.append(response.body().get(i).getName());
                        }*/
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.d("LOG_TAG", "Data retrieval failed!");
                    }
                });
    }

    private void loadUsers(List<User> users){
        adapter = new RecyclerAdapter(users);
        recyclerView.setAdapter(adapter);
    }
}
