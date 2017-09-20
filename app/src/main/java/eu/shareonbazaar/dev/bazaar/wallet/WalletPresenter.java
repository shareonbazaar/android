package eu.shareonbazaar.dev.bazaar.wallet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import eu.shareonbazaar.dev.bazaar.model.wallet.Transaction;
import eu.shareonbazaar.dev.bazaar.network.ConnectionSetup;
import eu.shareonbazaar.dev.bazaar.utilities.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static eu.shareonbazaar.dev.bazaar.utilities.Constants.TOKEN;

public class WalletPresenter implements WalletContract.Presenter{

    private Context mContext;
    private WalletContract.View mWalletView;

    public WalletPresenter(@NonNull WalletContract.View walletView){
        mWalletView = walletView;
        mWalletView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        mContext = context;
        fetchTransactions();
    }

    @Override
    public void fetchTransactions() {
        SharedPreference sharedPreference = new SharedPreference(mContext);
        String token = sharedPreference.retrieveToken(TOKEN);

        //TODO: Check if Token is null or not

        WalletService service = ConnectionSetup.retrofit.create(WalletService.class);
        service.getTransactions(token)
                .enqueue(new Callback<ArrayList<Transaction>>() {

                    @Override
                    public void onResponse(Call<ArrayList<Transaction>> call,
                                           Response<ArrayList<Transaction>> response) {
                        validateTransactions(response.body());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Transaction>> call, Throwable t) {
                        Log.d("ERROR: ", t.toString());
                        handleError("Could not fetch transactions.");
                    }
                });
    }

    @Override
    public void validateTransactions(ArrayList<Transaction> transactions) {
        Log.d("Transaction", transactions.size() + ">");
        if(transactions.size() <= 0){
            // TODO: handle empty transaction
            handleError("Empty transaction");
        }else {
            mWalletView.showTransactions(transactions);
        }
    }

    @Override
    public void handleError(String error) {
        Log.d("ERROR", error);
    }

    @Override
    public Transaction filterTransaction() {
        return null;
    }
}
