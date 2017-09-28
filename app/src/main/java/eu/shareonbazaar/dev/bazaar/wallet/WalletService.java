package eu.shareonbazaar.dev.bazaar.wallet;

import java.util.ArrayList;

import eu.shareonbazaar.dev.bazaar.model.wallet.Transaction;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface WalletService {
    @GET("api/transactions")
    Observable<ArrayList<Transaction>> getTransactions(@Header("token") String token);
}
