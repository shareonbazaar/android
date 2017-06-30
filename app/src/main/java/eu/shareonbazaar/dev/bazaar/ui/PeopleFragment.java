package eu.shareonbazaar.dev.bazaar.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.models.User;
import eu.shareonbazaar.dev.bazaar.models.UsersJsonResponse;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import eu.shareonbazaar.dev.bazaar.adapters.UserAdapter;
import eu.shareonbazaar.dev.bazaar.utilities.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleFragment extends Fragment implements UserAdapter.UserAdapterClickListener,
        SearchDialog.SearchDialogListener{

    public static final String TOKEN = "TOKEN";

    private RecyclerView recyclerView;
    private LinearLayout networkErrorContainer;
    private ProgressBar loadingIndicator;
    private UserAdapter recyclerAdapter;
    private FloatingActionButton fab;

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
    public void onItemClicked(User currentUser) {
        Intent intent = new Intent(getActivity(), IndividualProfile.class);
        intent.putExtra("User", currentUser);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for this fragment
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_people, container, false);

        loadingIndicator = (ProgressBar) frameLayout.findViewById(R.id.pb_loading_indicator);
        networkErrorContainer = (LinearLayout) frameLayout.findViewById(R.id.ll_network_error);

        Button retryConnection = (Button) frameLayout.findViewById(R.id.btn_retry);
        retryConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateRecyclerView();
                hideNetworkError();
            }
        });

        fab = (FloatingActionButton) frameLayout.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        recyclerView = (RecyclerView) frameLayout.findViewById(R.id.user_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        populateRecyclerView();
        hideNetworkError();

        return frameLayout;
    }

    private void showDialog(){
        DialogFragment dialog = SearchDialog.newInstance(this);
        dialog.show(getFragmentManager(), "SearchDialog");
    }

    private void populateRecyclerView() {
        SharedPreference sharedPreference = new SharedPreference(getContext());
        String token = sharedPreference.retrieveToken(TOKEN);

        //TODO: Check if Token is null or not

        UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.getUsers(token)
                .enqueue(new Callback<UsersJsonResponse>() {

                    @Override
                    public void onResponse(Call<UsersJsonResponse> call,
                                           Response<UsersJsonResponse> response) {
                        loadUsers(response.body());
                        //Log.d("SUCCESS", "Im done!!!");
                        hideProgressBar();
                        showFab();
                    }

                    @Override
                    public void onFailure(Call<UsersJsonResponse> call, Throwable t) {
                        showNetworkError();
                        Log.d("LOG_TAG", t.getMessage());
                    }
                });
    }
    private void hideFab(){
        fab.setVisibility(View.INVISIBLE);
    }
    private void showFab(){
        fab.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        loadingIndicator.setVisibility(View.INVISIBLE);
    }

    private void hideNetworkError(){
        networkErrorContainer.setVisibility(View.INVISIBLE);
        loadingIndicator.setVisibility(View.VISIBLE);
        hideFab();
    }

    private void showNetworkError(){
        networkErrorContainer.setVisibility(View.VISIBLE);
        hideProgressBar();
        hideFab();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDialogPositiveClick(SearchDialog dialog) {
        Toast.makeText(getContext(), "I was clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(SearchDialog dialog) {
    }
}
