package com.example.homework;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> { //Adapter class
    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener; // Adapter variables
    String redColor = "#0000FF";
    String blueColor = "#FF0000";

    RecyclerViewAdapter(Context context, List<String> data) { //Adapter constructor
        this.mInflater = LayoutInflater.from(context);
        Log.i("TAG", String.valueOf(data.size()));
        this.mData = data;

    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //method OnBindViewHolder
        holder.myTextView.setText(mData.get(position));
        if (position % 2 == 0){
            holder.myTextView.setTextColor(Color.parseColor(redColor));
        }
        else
            holder.myTextView.setTextColor(Color.parseColor(blueColor));
    }

    @Override
    public int getItemCount() {                                               //method GetItemCount
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { //ViewHolder
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.info_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition()); //ViewHolder OnClickListerner
        }
    }

    String getItem(int id) { //Arapter method getItem
        return mData.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    public interface ItemClickListener {    //Interface ItemClickListerner
        void onItemClick(View view, int position);
    }

    public void AddItem(){ //adding to the end of list
        mData.add("Added");
    }


}
