package eu.shareonbazaar.dev.bazaar.bookmark;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.shareonbazaar.dev.bazaar.R;

/**
 * Fragment to display and edit current user's profile
 */
public class BookmarkFragment extends Fragment {

    /**
     * requires empty constructor
     */
    public BookmarkFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Sets up the fragment
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return container layout
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
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
