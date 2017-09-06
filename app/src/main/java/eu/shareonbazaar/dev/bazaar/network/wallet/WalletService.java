package eu.shareonbazaar.dev.bazaar.network.wallet;

import java.util.ArrayList;

import eu.shareonbazaar.dev.bazaar.model.WalletJsonResponse;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface WalletService {
    @POST("api/transactions")
    Call<ArrayList<WalletJsonResponse>> getTransactions(@Header("token") String token);
}
