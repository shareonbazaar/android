package eu.shareonbazaar.dev.bazaar.adapters;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.Language;
import eu.shareonbazaar.dev.bazaar.model.people.User;
import eu.shareonbazaar.dev.bazaar.model.skill.Skill;
import eu.shareonbazaar.dev.bazaar.utilities.LabelView;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillAdapterViewHolder>{

    private ArrayList<Skill> skills;

    @Override
    public SkillAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.label_list;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new SkillAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SkillAdapterViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        if(skills == null)
            return 0;
        return skills.size();
    }

    class SkillAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lv_label_initial)
        LabelView mLabelInitial;
        @BindView(R.id.tv_label_name)
        TextView mLabelName;

        SkillAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int position){
            Language language = skills.get(position).getLanguages();
            String label = language.getEnglish();

            mLabelInitial.setLabel(label.charAt(0));
            mLabelName.setText(label);
        }
    }
}
