package eu.shareonbazaar.dev.bazaar.wallet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.wallet.Transaction;

public class WalletFragment extends Fragment implements WalletContract.View{

    private WalletContract.Presenter mPresenter;
    private WalletPresenter mWalletPresenter;

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
        mJSON.setText(transactions.toString());
    }

    @Override
    public void showFilteredTransaction(ArrayList<Transaction> transactions) {

    }

    @Override
    public void showError(String error) {

    }
}
