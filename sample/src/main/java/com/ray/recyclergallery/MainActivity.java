package com.ray.recyclergallery;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ray.lib.GalleryRecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GalleryRecyclerView galleryRecyclerViewV = findViewById(R.id.recycler_v);
        galleryRecyclerViewV.setOrientation(LinearLayoutManager.VERTICAL);
        galleryRecyclerViewV.setAdapter(new MyAdapter());
        GalleryRecyclerView galleryRecyclerView = findViewById(R.id.recycler);
        galleryRecyclerView.setScaleFactor(0.8f);
        galleryRecyclerView.setItemShowRatio(0.2f);
        galleryRecyclerView.setAdapter(new MyAdapter());
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MViewHolder> {

        static int[] sResIds = new int[]{R.drawable.test1, R.drawable.test2};

        @NonNull
        @Override
        public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MViewHolder holder, int position) {
            holder.mImageView.setImageResource(sResIds[position%sResIds.length]);
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        static class MViewHolder extends RecyclerView.ViewHolder {

            ImageView mImageView;

            MViewHolder(View itemView) {
                super(itemView);
                mImageView = itemView.findViewById(R.id.iv);
            }
        }

    }

}
