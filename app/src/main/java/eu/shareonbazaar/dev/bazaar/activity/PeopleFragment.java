package eu.shareonbazaar.dev.bazaar.activity;

import android.content.Context;
import android.content.Intent;
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

import java.util.HashMap;
import java.util.List;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.User;
import eu.shareonbazaar.dev.bazaar.model.UsersJsonResponse;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import eu.shareonbazaar.dev.bazaar.ui.UserAdapter;
import eu.shareonbazaar.dev.bazaar.utility.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleFragment extends Fragment implements UserAdapter.UserAdapterClickListener {

    public static final String TOKEN = "TOKEN";
    public static final String USER_ID = "USER_ID";

    private FrameLayout frameLayout;
    private RecyclerView recyclerView;
    private UserAdapter recyclerAdapter;

    public PeopleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadUsers(UsersJsonResponse usersJson) {
        List<User> users = usersJson.getUsers();
        recyclerAdapter = new UserAdapter(this);
        recyclerAdapter.setUserData(users);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onItemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), IndividualProfile.class);
        intent.putExtra(USER_ID, recyclerAdapter.getUserByPosition(position).getId());
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for this fragment
        frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_people, container, false);

        recyclerView = (RecyclerView) frameLayout.findViewById(R.id.user_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        populateRecyclerView();

        return frameLayout;
    }

    private void populateRecyclerView() {
        SharedPreference sharedPreference = new SharedPreference(getContext());
        String token = sharedPreference.retrieveToken(TOKEN);

        UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.getUsers(token)
                .enqueue(new Callback<UsersJsonResponse>() {
                    @Override
                    public void onResponse(Call<UsersJsonResponse> call,
                                           Response<UsersJsonResponse> response) {
                        loadUsers(response.body());
                        //Log.d("SUCCESS", "Im done!!!");
                    }

                    @Override
                    public void onFailure(Call<UsersJsonResponse> call, Throwable t) {
                        Snackbar snackbar = Snackbar
                                .make(frameLayout, "Data retrieval failed!", Snackbar.LENGTH_LONG);
                        snackbar.show();

                        Log.d("LOG_TAG", t.getMessage());
                    }
                });
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
