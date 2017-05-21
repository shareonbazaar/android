package eu.shareonbazaar.dev.bazaar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.utilities.RoundImageTransformation;
import eu.shareonbazaar.dev.bazaar.models.User;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder> {

    private static UserAdapterClickListener mClickListener;
    private List<User> mUsers;
    private Context context;

    public UserAdapter(UserAdapterClickListener clickListener) {
        mClickListener = clickListener;
    }

    @Override
    public UserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        int layoutIdForListItem = R.layout.user_card;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new UserAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapterViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        if(mUsers == null)
            return 0;
        return mUsers.size();
    }

    private User getUserByPosition(int position) {
        return mUsers.get(position);
    }

    public void setUserData(List<User> users){
        mUsers = users;
        notifyDataSetChanged();
    }

    public interface UserAdapterClickListener {
        void onItemClicked(User user);
    }

    class UserAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        @BindView(R.id.user_name)
        TextView mUserName;
        @BindView(R.id.user_location)
        TextView mUserLocation;
        @BindView(R.id.user_picture)
        ImageView mUserPicture;
        @BindView(R.id.tv_skill_count)
        TextView mSkillCount;
        @BindView(R.id.tv_skill_count_label)
        TextView mSkillCountLabel;

        UserAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bindView(int position){
            User user = mUsers.get(position);
            String userName = user.getName();
            String userLocation = user.getLocation();
            String userImageUrl = user.getPicture();
            int skillCount = user.getSkills().size();
            String skillCountLabel = itemView.getResources()
                    .getQuantityString(R.plurals.skill_count_label, skillCount, skillCount);

            Log.d("SKILL COUNT", skillCountLabel);

            mUserName.setText(userName);
            mUserLocation.setText(userLocation);
            Picasso.with(context)
                    .load(userImageUrl)
                    .transform(new RoundImageTransformation())
                    .into(mUserPicture);
            mSkillCount.setText(String.valueOf(skillCount));
            mSkillCountLabel.setText(skillCountLabel);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            if (mClickListener != null) {
                mClickListener.onItemClicked(getUserByPosition(adapterPosition));
            }
        }
    }
}
