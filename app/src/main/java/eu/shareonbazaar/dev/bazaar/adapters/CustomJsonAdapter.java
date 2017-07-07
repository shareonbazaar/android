package eu.shareonbazaar.dev.bazaar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.models.JsonObject;
import eu.shareonbazaar.dev.bazaar.models.Languages;

public class CustomJsonAdapter extends RecyclerView.Adapter<CustomJsonAdapter.CustomJsonAdapterViewHolder>{

    private ArrayList<JsonObject> json;

    public CustomJsonAdapter(){}

    @Override
    public CustomJsonAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.people_custom_list;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new CustomJsonAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomJsonAdapterViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        if(json == null)
            return 0;
        return json.size();
    }

    public void setJsonData(ArrayList<JsonObject> json){
        this.json = json;
        notifyDataSetChanged();
    }

    class CustomJsonAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_custom_text)
        TextView mText;

        CustomJsonAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int position){
            JsonObject jsonObject = json.get(position);
            Languages language = jsonObject.getLabel();
            String label = language.getEn();

            Log.d("LABEL", label);

            mText.setText(label);
        }
    }
}
