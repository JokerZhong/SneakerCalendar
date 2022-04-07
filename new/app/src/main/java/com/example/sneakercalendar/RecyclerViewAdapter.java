package com.example.sneakercalendar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.like.LikeButton;
import com.like.OnAnimationEndListener;
import com.like.OnLikeListener;



import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.sneakercalendar.DataServer.DBOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Menu> list;//数据
    private List<Integer> heightList;//装产出的随机数


    public RecyclerViewAdapter(Context context, List<Menu> list) {
        this.context = context;
        this.list = list;
        //记录为每个控件产生的随机高度,避免滑回到顶部出现空白
        heightList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int height = new Random().nextInt(300) + 500;//[100,300)的随机数
            heightList.add(height);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //找到item的布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(view);//将布局设置给holder
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemClickListener likeOnItemClickListener;
    private OnItemClickListener unlikeOnItemClickListener;



    public interface OnItemClickListener{
        void onItemclick(View view,int position);
    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;

    }

    public void setOnlikeClickListener(OnItemClickListener onItemClickListener){
        likeOnItemClickListener = onItemClickListener;

    }
    public void setOnunlikeClickListener(OnItemClickListener onItemClickListener){
        unlikeOnItemClickListener = onItemClickListener;

    }




    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //填充数据
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round) //预加载图片
                .error(R.drawable.ic_launcher_foreground) //加载失败图片
                .priority(Priority.HIGH) //优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE) //缓存
                .transform(new GlideRoundTransform(5)); //圆角
        Glide.with(context).load(list.get(position).getImage()).apply(options).into(holder.imageView);
        holder.menu.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
        holder.date.setText(list.get(position).getDate());

        int mlike = list.get(position).getLike();

        if (mlike == 1) {
            holder.like.setLiked(true);
        }else{
            holder.like.setLiked(false);
        }







        //由于需要实现瀑布流的效果,所以就需要动态的改变控件的高度了
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.height = heightList.get(position);
        holder.imageView.setLayoutParams(params);




        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();

                mOnItemClickListener.onItemclick(view,position);

            }
        });


        holder.like.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                int position = holder.getAdapterPosition();
                likeOnItemClickListener.onItemclick(likeButton,position);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                int position = holder.getAdapterPosition();
                unlikeOnItemClickListener.onItemclick(likeButton,position);
            }

        });

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView menu,price,date;
        LikeButton like;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            menu = (TextView)itemView.findViewById(R.id.text_menu);
            price = (TextView)itemView.findViewById(R.id.text_money);
            date = (TextView)itemView.findViewById(R.id.text_date);
            like = (LikeButton)itemView.findViewById(R.id.star_button);







        }
    }
}