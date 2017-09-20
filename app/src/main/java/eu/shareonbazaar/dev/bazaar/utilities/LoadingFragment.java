package eu.shareonbazaar.dev.bazaar.utilities;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;

import static eu.shareonbazaar.dev.bazaar.utilities.Constants.LOADING_DIALOG_TEXT;

public class LoadingFragment extends DialogFragment {

    @BindView(R.id.tv_loading_text)
    TextView mLoading;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_loading, null);
        ButterKnife.bind(this, dialogView);

        String loadingMessage = getArguments().getString(LOADING_DIALOG_TEXT);
        mLoading.setText(loadingMessage);

        builder.setView(dialogView);
        setCancelable(false);

        return builder.create();
    }

}
