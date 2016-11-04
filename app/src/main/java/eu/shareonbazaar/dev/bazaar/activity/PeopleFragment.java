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
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import eu.shareonbazaar.dev.bazaar.ui.RecyclerAdapter;
import eu.shareonbazaar.dev.bazaar.utility.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment to display a list of people on Bazaar
 */
public class PeopleFragment extends Fragment implements RecyclerAdapter.ClickListener {

    public static final String TOKEN = "TOKEN";
    public static final String USER_ID = "USER_ID";

    private FrameLayout frameLayout;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    /**
     * requires empty constructor
     */
    public PeopleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates a <code>RecyclerAdapter</code> and adds the list of <code>users</code>
     *
     * @param users
     */
    private void loadUsers(List<User> users) {
        recyclerAdapter = new RecyclerAdapter(getActivity(), users);
        recyclerAdapter.setClickListener(this);
        recyclerView.setAdapter(recyclerAdapter);
    }


    /**
     * Starts new activity <code>IndividualProfile</code> for the clicked person
     *
     * @param view
     * @param position position in RecyclerView
     */
    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), IndividualProfile.class);
        intent.putExtra(USER_ID, recyclerAdapter.getUserByPos(position).getId());
        startActivity(intent);
    }

    /**
     * Sets up the fragment and populates RecyclerView to display a list of
     * all people on Bazaar
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return container layout of RecyclerView
     */
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

    /**
     * Populates RecyclerView by making an API call to retrieve a list of users
     */
    private void populateRecyclerView() {
        SharedPreference sharedPreference = new SharedPreference(getContext());
        String token = sharedPreference.retrieveToken(TOKEN);

        UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.getUsers(token, new HashMap<String, String>())
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        loadUsers(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Snackbar snackbar = Snackbar
                                .make(frameLayout, "Data retrieval failed!", Snackbar.LENGTH_LONG);
                        snackbar.show();

                        Log.d("LOG_TAG", t.toString());
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
