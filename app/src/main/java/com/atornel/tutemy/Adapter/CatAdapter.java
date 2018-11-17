package com.atornel.tutemy.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atornel.tutemy.Activity.Torrent.CatActivity;
import com.atornel.tutemy.Interface.ItemClickListener;
import com.atornel.tutemy.Model.CatModel.CatObject;
import com.atornel.tutemy.R;

import org.sufficientlysecure.htmltextview.HtmlTextView;


class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public HtmlTextView titleView, countView;

    private ItemClickListener itemClickListener;

    public CatViewHolder(View itemView) {
        super(itemView);

        titleView = (HtmlTextView) itemView.findViewById(R.id.cat_name);
        countView = (HtmlTextView) itemView.findViewById(R.id.cat_count);

        //EVENT
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);

    }

    @Override
    public boolean onLongClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}

public class CatAdapter extends RecyclerView.Adapter<CatViewHolder>{

    private CatObject catObject;
    private Context mContext;
    private LayoutInflater inflater;


    public CatAdapter(CatObject catObject, Context mContext) {
        this.catObject = catObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = inflater.inflate(R.layout.cat_row,parent,false);
        return new CatViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        holder.titleView
                .setText(catObject
                        .items
                        .get(position)
                        .getName());

        holder.countView
                .setText(Integer.toString(catObject
                        .items
                        .get(position)
                        .getCount()));

        final String cat_id = Integer.toString(catObject.items.get(position).getId());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                mContext.startActivity(new Intent(mContext, CatActivity.class)
                        .putExtra("title", catObject.items.get(position).getName())
                        .putExtra("cat_id", cat_id)
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return catObject.items.size();
    }


}
