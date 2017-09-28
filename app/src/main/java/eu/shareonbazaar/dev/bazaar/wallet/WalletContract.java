package eu.shareonbazaar.dev.bazaar.wallet;

import java.util.ArrayList;

import eu.shareonbazaar.dev.bazaar.base.BasePresenter;
import eu.shareonbazaar.dev.bazaar.base.BaseView;
import eu.shareonbazaar.dev.bazaar.model.wallet.Transaction;

public interface WalletContract {
    interface View extends BaseView<Presenter> {
        void showTransactions(ArrayList<Transaction> transactions);
        void showFilteredTransaction(ArrayList<Transaction> transactions);
        void showError(String error);
    }

    interface Presenter extends BasePresenter {
        void fetchTransactions();
        void validateTransactions(ArrayList<Transaction> transactions);
        void handleError(Throwable error);
        Transaction filterTransaction();
    }
}
