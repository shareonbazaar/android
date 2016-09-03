package eu.shareonbazaar.dev.bazaar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.User;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testBtn = (Button)findViewById(R.id.test_btn);

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UsersActivity.class);
                startActivity(intent);
            }
        });

        /*UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.getUsers(new HashMap<String, String>())
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        TextView textView = (TextView) findViewById(R.id.textView);
                        textView.setText(response.body().get(0).getStatus());
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.d("LOG_TAG", "Data retrieval failed!");
                    }
                });*/
    }
}
