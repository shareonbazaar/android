package eu.shareonbazaar.dev.bazaar.userInterface;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.User;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<User> users;
    private Context context;
    private static ClickListener clickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView userName;
        TextView userLocation;
        ImageView userPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            userName = (TextView)itemView.findViewById(R.id.user_name);
            userLocation = (TextView)itemView.findViewById(R.id.user_location);
            userPicture = (ImageView)itemView.findViewById(R.id.user_picture);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null)
            {
                clickListener.itemClicked(view, this.getLayoutPosition());

            }
        }
    }

    public RecyclerAdapter(Context context, List<User> users)
    {
        this.context = context;
        this.users = users;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                                .inflate(R.layout.user_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.userName.setText(String.valueOf(users.get(position).getName()));
        holder.userLocation.setText(String.valueOf(users.get(position).getLocation()));
        Picasso.with(context)
                .load(users.get(position).getPicture())
                .resize(50,50)
                .transform(new RoundImageTransformation())
                .into(holder.userPicture);
    }

    public void setClickListener(ClickListener clickListener)
    {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount()
    {
        return users.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface ClickListener
    {
        void itemClicked(View view, int position);
    }
}

