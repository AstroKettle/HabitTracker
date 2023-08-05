package com.example.habbittracker;


import static androidx.browser.customtabs.CustomTabsClient.getPackageName;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.bumptech.glide.Glide;
import com.google.android.gms.dynamic.SupportFragmentWrapper;

import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {
    private Context context;
    private List<DataClass> dataList;

    public HabitAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
//    private SortedList<DataClass> contacts = new SortedList<>(DataClass.class, new SortedList.Callback<DataClass>() {
//        @Override
//        public int compare(DataClass contact, DataClass contact2) {
//            return contact.getDataTitle().compareTo(contact2.getDataTitle());
//        }
//
//        @Override
//        public boolean areContentsTheSame(DataClass contact, DataClass contact2) {
//            return contact2.equals(contact);
//        }
//
//        @Override
//        public boolean areItemsTheSame(DataClass contact, DataClass contact2) {
//            return contact2.getDataTitle().equals(contact.getDataTitle());
//        }
//
//        @Override
//        public void onChanged(int pos, int count) {
//            notifyItemRangeChanged(pos, count);
//        }
//
//        @Override
//        public void onInserted(int pos, int count) {
//            notifyItemRangeInserted(pos, count);
//        }
//
//        @Override
//        public void onRemoved(int pos, int count) {
//            notifyItemRangeRemoved(pos, count);
//        }
//
//        @Override
//        public void onMoved(int pos, int count) {
//            notifyItemMoved(pos, count);
//        }
//    });

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new HabitViewHolder(view);
    }

    public class HabitViewHolder extends RecyclerView.ViewHolder{
        ImageView recImage;
        TextView recTitle, recDesc, recLang;
        CardView recCard;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            recImage = itemView.findViewById(R.id.recImage);
            recCard = itemView.findViewById(R.id.recCard);
            recDesc = itemView.findViewById(R.id.recDesc);
            recLang = itemView.findViewById(R.id.recPriority);
            recTitle = itemView.findViewById(R.id.recTitle);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {

        holder.recImage.setImageResource(R.drawable.asdrt);
        holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recDesc.setText("Дней: " + dataList.get(position).getDataProg());
        holder.recLang.setText(dataList.get(position).getDataReg());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
//                bundle.putString("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                bundle.putString("Description", dataList.get(holder.getAdapterPosition()).getDataDesc());
                bundle.putString("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
                bundle.putString("Key",dataList.get(holder.getAdapterPosition()).getKey());
                bundle.putString("Language", dataList.get(holder.getAdapterPosition()).getDataReg());
                bundle.putInt("prog", dataList.get(holder.getAdapterPosition()).getDataProg());
                Navigation.findNavController(view).navigate(R.id.action_habitsList_to_detailFragment, bundle);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void putItem(DataClass contact) {
        dataList.add(contact);
    }

//    public void removeItemAt(int index) {
//        dataList.removeItemAt(index);
//    }


}