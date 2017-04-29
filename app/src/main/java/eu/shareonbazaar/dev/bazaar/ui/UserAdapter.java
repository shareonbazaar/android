package eu.shareonbazaar.dev.bazaar.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.lib.RoundImageTransformation;
import eu.shareonbazaar.dev.bazaar.model.User;


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
        User user = mUsers.get(position);
        String userName = user.getName();
        String userLocation = user.getLocation();
        String userImageUrl = user.getPicture();

        holder.userName.setText(userName);
        holder.userLocation.setText(userLocation);
        Picasso.with(context)
                .load(userImageUrl)
                .transform(new RoundImageTransformation())
                .into(holder.userPicture);
    }

    @Override
    public int getItemCount() {
        if(mUsers == null)
            return 0;
        return mUsers.size();
    }

    public User getUserByPosition(int position) {
        return mUsers.get(position);
    }

    public void setUserData(List<User> users){
        mUsers = users;
        notifyDataSetChanged();
    }

    public interface UserAdapterClickListener {
        void onItemClicked(View view, int position);
    }

    public static class UserAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        TextView userName;
        TextView userLocation;
        ImageView userPicture;

        public UserAdapterViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            userLocation = (TextView) itemView.findViewById(R.id.user_location);
            userPicture = (ImageView) itemView.findViewById(R.id.user_picture);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            if (mClickListener != null) {
                mClickListener.onItemClicked(view, adapterPosition);
            }
        }
    }
}

