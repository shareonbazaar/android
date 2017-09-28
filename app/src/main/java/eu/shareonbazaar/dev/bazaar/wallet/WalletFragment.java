package eu.shareonbazaar.dev.bazaar.wallet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.wallet.Transaction;

public class WalletFragment extends Fragment implements WalletContract.View,
        WalletAdapter.WalletAdapterClickListener{

    private WalletContract.Presenter mPresenter;
    private WalletPresenter mWalletPresenter;

    @BindView(R.id.rv_transaction_list)
    RecyclerView mTransactions;

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

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mTransactions.setLayoutManager(mLayoutManager);

        mWalletPresenter = new WalletPresenter(this);
        mPresenter.start(getActivity().getApplicationContext());

        return rootView;
    }

    @Override
    public void setPresenter(WalletContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showTransactions(ArrayList<Transaction> transactions) {
        WalletAdapter walletAdapter = new WalletAdapter(this);
        walletAdapter.setTransaction(transactions);
        mTransactions.setAdapter(walletAdapter);
    }

    @Override
    public void showFilteredTransaction(ArrayList<Transaction> transactions) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onItemClicked(Transaction transaction) {

    }
}
