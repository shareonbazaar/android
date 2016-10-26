package eu.shareonbazaar.dev.bazaar.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.List;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.User;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import eu.shareonbazaar.dev.bazaar.ui.RecyclerAdapter;
import eu.shareonbazaar.dev.bazaar.utility.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleFragment extends Fragment implements RecyclerAdapter.ClickListener{

    private FrameLayout frameLayout;
    private RecyclerView recyclerView;

    public PeopleFragment() {
        // Required empty public constructor
    }

    private void loadUsers(List<User> users){
        RecyclerAdapter adapter = new RecyclerAdapter(getActivity(), users);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void itemClicked(View view, int position) {
        Snackbar snackbar = Snackbar
                .make(frameLayout, "Click working", Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frameLayout = (FrameLayout)inflater.inflate(R.layout.fragment_people, container, false);

        SharedPreference sharedPreference = new SharedPreference(getContext());
        String token = sharedPreference.retrieveToken("TOKEN");

        recyclerView = (RecyclerView)frameLayout.findViewById(R.id.user_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
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
                                .make(frameLayout, "Data retrieval failed!", Snackbar.LENGTH_LONG);

                        snackbar.show();
                        String message = t.toString();
                        Log.d("LOG_TAG", message);
                    }
                });

        return frameLayout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
