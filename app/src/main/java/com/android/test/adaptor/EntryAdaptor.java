package com.android.test.adaptor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.test.R;
import com.android.test.model.Category;
import com.android.test.model.Entry;

import java.io.File;
import java.util.List;

/**
 * Created by Manish on 9/2/17.
 */

public class EntryAdaptor extends RecyclerView.Adapter<EntryAdaptor.ViewHolder> {
    private List<Entry> categoryList;
    private Context mContext;
    private ViewHolder viewHolder;

    public EntryAdaptor(Context context, List<Entry> arralist) {
        this.categoryList = arralist;
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry_row, parent, false);
        view.setTag(view);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Entry entry = categoryList.get(position);
        holder.mtitle.setText(entry.getTitle());
        holder.mDesc.setText(entry.getTitle());

       // File file = new File(DOUBT_DIR + path);
       if (entry.getImage()!=null){
           Log.v("ssssssss",entry.getImage());
           Bitmap bmp = BitmapFactory.decodeFile(entry.getImage());

           holder.mImage.setImageBitmap(bmp);
       }

    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mDesc;
        public final TextView mtitle;
        private final ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mtitle = (TextView) mView.findViewById(R.id.name_tv);
            mDesc = (TextView) mView.findViewById(R.id.desc_tv);
            mImage = (ImageView) mView.findViewById(R.id.image_iv);
        }
    }
}
