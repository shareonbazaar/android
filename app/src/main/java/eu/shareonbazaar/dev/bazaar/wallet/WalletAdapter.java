package eu.shareonbazaar.dev.bazaar.wallet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.model.wallet.Participant;
import eu.shareonbazaar.dev.bazaar.model.wallet.Profile;
import eu.shareonbazaar.dev.bazaar.model.wallet.Transaction;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletAdapterViewHolder> {

    private static WalletAdapterClickListener mClickListener;
    private ArrayList<Transaction> mTransactions;
    private Context context;

    WalletAdapter(WalletAdapterClickListener clickListener) {
        mClickListener = clickListener;
    }

    @Override
    public WalletAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        int layoutIdForListItem = R.layout.wallet_list;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new WalletAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WalletAdapterViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        if(mTransactions == null)
            return 0;
        return mTransactions.size();
    }

    private Transaction getTransaction(int position) {
        return mTransactions.get(position);
    }

    void setTransaction(ArrayList<Transaction> transactions){
        mTransactions = transactions;
        notifyDataSetChanged();
    }

    interface WalletAdapterClickListener {
        void onItemClicked(Transaction transaction);
    }

    class WalletAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_participant_image)
        ImageView mParticipantImage;
        @BindView(R.id.tv_participant_name)
        TextView mParticipantName;
        @BindView(R.id.tv_transaction_action)
        TextView mTransactionAction;
        @BindView(R.id.tv_transaction_skill)
        TextView mTransactionSkill;
        @BindView(R.id.tv_transaction_time)
        TextView mTransactionTime;

        WalletAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bindView(int position){
            Transaction transaction = mTransactions.get(position);
            Participant participant = transaction.getParticipants().get(0);
            Profile profile = participant.getProfile();
            String imageUrl = profile.getImageUrl();
            String skill = transaction.getService().getLanguages().getEnglish();
            String name = profile.getName();
            String requestType = transaction.getRequestType();
            String createdAt = String.valueOf(getDays(transaction.getCreatedAt())) + " days ago";

            Picasso.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_account_circle_24dp)
                    .error(R.drawable.ic_account_circle_24dp)
                    .into(mParticipantImage);

            mParticipantName.setText(name);
            String action = requestType.equals("receive") ? "You will receive" : "You will give";
            mTransactionAction.setText(action);
            mTransactionSkill.setText(skill);
            mTransactionTime.setText(createdAt);
        }

        long getDays(String createdAt){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            try {
                // date = format.parse(createdAt.replaceAll("Z$", ""));
                date = format.parse(createdAt.substring(0, createdAt.indexOf('.')));

            } catch (ParseException e) {
                e.printStackTrace();
            }

            Date currentDate = calendar.getTime();

            long date1 = currentDate.getTime() - date.getTime();
            Log.d("DATE", String.valueOf(date1));
            return (currentDate.getTime() - date.getTime()) / (1000 * 60 * 60 * 24);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            if (mClickListener != null) {
                mClickListener.onItemClicked(getTransaction(adapterPosition));
            }
        }
    }
}
