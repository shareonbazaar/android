package eu.shareonbazaar.dev.bazaar.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.shareonbazaar.dev.bazaar.R;

public class SearchDialog extends DialogFragment{

    private static SearchDialogListener mListener;

    public SearchDialog() {
    }

    public static SearchDialog newInstance(SearchDialogListener listener){
        SearchDialog searchDialog = new SearchDialog();
        Bundle args = new Bundle();
        args.putString("title", "Search");
        searchDialog.setArguments(args);

        mListener = listener;
        return searchDialog;
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface SearchDialogListener {
        public void onDialogPositiveClick(SearchDialog dialog);
        public void onDialogNegativeClick(SearchDialog dialog);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_dialog, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View viewInflater = inflater.inflate(R.layout.search_dialog, null);
        //final EditText mDecryptionKey = (EditText)viewInflater.findViewById(R.id.et_decryption_code);

        builder.setView(viewInflater)
                .setPositiveButton(R.string.dialog_surprise_me, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(SearchDialog.this);
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(SearchDialog.this);
                    }
                });
        return builder.create();
    }
}
