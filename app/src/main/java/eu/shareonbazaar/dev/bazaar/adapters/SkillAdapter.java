package eu.shareonbazaar.dev.bazaar.adapters;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.Language;
import eu.shareonbazaar.dev.bazaar.model.skill.Skill;

public class SkillAdapter {

    private ArrayList<Skill> skills;

    class SkillAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_custom_text)
        TextView mText;

        SkillAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int position){
        }
    }
}
