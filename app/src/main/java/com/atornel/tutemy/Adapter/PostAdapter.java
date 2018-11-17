package com.atornel.tutemy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.atornel.tutemy.Activity.Torrent.PostActivity;
import com.atornel.tutemy.Interface.ItemClickListener;
import com.atornel.tutemy.Model.PostModel.PostObject;
import com.atornel.tutemy.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.sufficientlysecure.htmltextview.HtmlTextView;


class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public HtmlTextView titleView;
    public ImageView thumb;

    private ItemClickListener itemClickListener;

    public PostViewHolder(View itemView) {
        super(itemView);

        titleView = (HtmlTextView) itemView.findViewById(R.id.post_title);
        thumb = (ImageView) itemView.findViewById(R.id.post_thumb);

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

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder>{

    private PostObject postObject;
    private Context mContext;
    private LayoutInflater inflater;


    public PostAdapter(PostObject postObject, Context mContext) {
        this.postObject = postObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = inflater.inflate(R.layout.post_row,parent,false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.titleView
                .setText(postObject
                        .items
                        .get(position)
                        .getTitle()
                        .getRendered());


        try {
            String img = postObject.items.get(position).getContent().getRendered();
            Document doc = Jsoup.parse(img);
            Element link = doc.select("img").first();
            String imgSrc = link.attr("src");

            Log.i("ImageURLS", imgSrc);
            Picasso.with(mContext)
                    .load(imgSrc)
                    .into(holder.thumb);
        }catch (Exception e){

            Log.e("Thumb error", String.valueOf(e));
            Toast.makeText(mContext, "No Thumbnails found", Toast.LENGTH_SHORT).show();
        }




        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                mContext.startActivity(new Intent(mContext, PostActivity.class)
                        .putExtra("title", postObject.items.get(position).getTitle().getRendered())
                        .putExtra("content", postObject.items.get(position).getContent().getRendered())
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return postObject.items.size();
    }


}
