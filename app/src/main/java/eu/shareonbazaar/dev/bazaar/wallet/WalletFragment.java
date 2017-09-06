package eu.shareonbazaar.dev.bazaar.wallet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.WalletJsonResponse;
import eu.shareonbazaar.dev.bazaar.network.ConnectionSetup;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import eu.shareonbazaar.dev.bazaar.network.wallet.WalletService;
import eu.shareonbazaar.dev.bazaar.utilities.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletFragment extends Fragment {

    public static final String TOKEN = "TOKEN";

    @BindView(R.id.tv_wallet)
    TextView mJSON;

    public WalletFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wallet, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    private void fetchTransation(){
        SharedPreference sharedPreference = new SharedPreference(getContext());
        String token = sharedPreference.retrieveToken(TOKEN);

        //TODO: Check if Token is null or not

        WalletService service = ConnectionSetup.retrofit.create(WalletService.class);
        service.getTransactions(token)
                .enqueue(new Callback<ArrayList<WalletJsonResponse>>() {

                    @Override
                    public void onResponse(Call<ArrayList<WalletJsonResponse>> call,
                                           Response<ArrayList<WalletJsonResponse>> response) {
//                        mJSON.setText(response.body());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<WalletJsonResponse>> call, Throwable t) {
                        Log.d("LOG_TAG", t.getMessage());
                    }
                });
    }

}
