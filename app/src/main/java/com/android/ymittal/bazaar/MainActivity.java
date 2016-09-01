package com.android.ymittal.bazaar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.ymittal.bazaar.model.User;
import com.android.ymittal.bazaar.network.RetrofitTemplate;
import com.android.ymittal.bazaar.network.UserService;

import java.util.Arrays;
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

        UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.getUsers(new HashMap<String, String>())
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        TextView textView = (TextView) findViewById(R.id.textView);
                        textView.setText(response.body().get(0).getId());
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.d("LOG_TAG", "Data retrieval failed!");
                    }
                });
    }
}
