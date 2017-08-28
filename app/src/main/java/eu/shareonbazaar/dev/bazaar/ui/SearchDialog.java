package eu.shareonbazaar.dev.bazaar.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.models.SkillCategory;
import eu.shareonbazaar.dev.bazaar.models.UsersJsonResponse;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchDialog extends DialogFragment{

    @BindView(R.id.tv_search_surprise)
    TextView mSurprise;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_dialog, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    private void fetchSkills(){
        UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.getSkills()
                .enqueue(new Callback<ArrayList<SkillCategory>>() {

                    @Override
                    public void onResponse(Call<ArrayList<SkillCategory>> call,
                                           Response<ArrayList<SkillCategory>> response) {
                        // loadUsers(response.body());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SkillCategory>> call, Throwable t) {
                        Log.d("LOG_TAG", t.getMessage());
                    }
                });
    }

    @OnClick(R.id.iv_close_dialog)
    public void closeDialog() {
        this.dismiss();
    }

}
