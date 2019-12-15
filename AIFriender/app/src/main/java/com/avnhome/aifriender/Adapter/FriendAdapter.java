package com.avnhome.aifriender.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avnhome.aifriender.Interfaces.OnLoadedListener;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;
import com.avnhome.aifriender.Twitter.TwitterManager;
import com.avnhome.aifriender.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder>{

    private static OnItemClickListener clickListener;
    private List<User> userList;

    public FriendAdapter(List<User> userList) {
        this.userList = userList;
    }

    public static void setClickListener(OnItemClickListener clickListener) {
        FriendAdapter.clickListener = clickListener;
    }

    @NonNull
    @Override
    public FriendAdapter.FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View friendView = inflater.inflate(R.layout.item_friend,parent,false);
        return new FriendViewHolder(friendView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.FriendViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder implements OnLoadedListener<com.twitter.sdk.android.core.models.User>,
    View.OnClickListener, View.OnLongClickListener{
        private User user;
        private ImageView avatarFriend;
        private TextView nameFriend;
        private TextView genderFriend;
        private TextView ageFriend;
        private Context context;

        public FriendViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            this.context = context;
            avatarFriend = itemView.findViewById(R.id.avatar_fr_iv);
            nameFriend = itemView.findViewById(R.id.name_friend);
            ageFriend = itemView.findViewById(R.id.age_friend);
        }

        public void onBind(int position){
            try {
                user = userList.get(position);
                TwitterManager.getLookupUser(user.getTwitterId(), this);
                nameFriend.setText(user.getName());
                long birthday = Long.parseLong(user.getDob());
                ageFriend.setText("Age: " + Utils.AgeCalculator(birthday));
            }catch (Exception e){
                Log.e("FriendAdapter", "onBind: ", e);
                Toast.makeText(context, "Load Friend: Failed", Toast.LENGTH_SHORT);
            }
        }

        @Override
        public void onComplete(com.twitter.sdk.android.core.models.User user) {
            Picasso.with(context).load(user.profileImageUrlHttps).into(avatarFriend);
        }

        @Override
        public void onFailure(Throwable t) {
            Log.e("FriendAdapter", "onBind: ", t);
            Toast.makeText(context, "Load Avatar Friend: Failed", Toast.LENGTH_SHORT);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }



    public interface OnItemClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}
