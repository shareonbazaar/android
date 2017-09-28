package eu.shareonbazaar.dev.bazaar.people;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.people.PeopleJSON;
import eu.shareonbazaar.dev.bazaar.model.people.User;
import eu.shareonbazaar.dev.bazaar.network.ConnectionSetup;
import eu.shareonbazaar.dev.bazaar.peopledetails.PeopleDetailsActivity;
import eu.shareonbazaar.dev.bazaar.utilities.SharedPreference;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static eu.shareonbazaar.dev.bazaar.utilities.Constants.TOKEN;

public class PeopleFragment extends Fragment implements PeopleAdapter.UserAdapterClickListener{

    private RecyclerView recyclerView;
    private LinearLayout networkErrorContainer;
    private ProgressBar loadingIndicator;
    private PeopleAdapter recyclerAdapter;
    private FloatingActionButton fab;

    public PeopleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemClicked(User currentUser) {
        Intent intent = new Intent(getActivity(), PeopleDetailsActivity.class);
        intent.putExtra("User", currentUser);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_people, container, false);

        loadingIndicator = (ProgressBar) rootView.findViewById(R.id.pb_loading_indicator);
        networkErrorContainer = (LinearLayout) rootView.findViewById(R.id.ll_network_error);

        TextView retryConnection = (TextView) rootView.findViewById(R.id.tv_retry);
        retryConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateRecyclerView();
                hideNetworkError();
            }
        });

        recyclerView = (RecyclerView) rootView.findViewById(R.id.user_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        populateRecyclerView();
        hideNetworkError();

        return rootView;
    }

    private void populateRecyclerView() {
        SharedPreference sharedPreference = new SharedPreference(getActivity().getApplicationContext());
        String token = sharedPreference.retrieveToken(TOKEN);
        Log.d("TOKEN", token);

        //TODO: Check if Token is null or not

        PeopleService service = ConnectionSetup.retrofit.create(PeopleService.class);

        Disposable disposable = service.getUsers(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loadUsers, this::showNetworkError);
    }

    private void loadUsers(PeopleJSON peopleJSON) {
        hideProgressBar();

        ArrayList<User> users = peopleJSON.getUsers();
        recyclerAdapter = new PeopleAdapter(this);
        recyclerAdapter.setUserData(users);
        recyclerView.setAdapter(recyclerAdapter);
    }

//    private void hideFab(){
//        fab.setVisibility(View.INVISIBLE);
//    }
//    private void showFab(){
//        fab.setVisibility(View.VISIBLE);
//    }

    private void hideProgressBar(){
        loadingIndicator.setVisibility(View.INVISIBLE);
    }

    private void hideNetworkError(){
        networkErrorContainer.setVisibility(View.INVISIBLE);
        loadingIndicator.setVisibility(View.VISIBLE);
//        hideFab();
    }

    private void showNetworkError(Throwable throwable){
        networkErrorContainer.setVisibility(View.VISIBLE);
        hideProgressBar();
//        hideFab();
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
