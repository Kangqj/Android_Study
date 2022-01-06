package Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloword.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyArrayAdapter extends ArrayAdapter {
    private Activity mContext = null;
    private int mResourceID;
    ArrayList<Map<String, Object>> mItems;

    public MyArrayAdapter(Activity context, int resId, ArrayList<Map<String, Object>> items) {
        super(context, resId, items);

        mContext = context;
        mResourceID = resId;
        mItems = items;
    }

    static class ViewHolder
    {
        public ImageView imgView;
        public TextView titleView;
        public TextView infoView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = mContext.getLayoutInflater();
            convertView = inflater.inflate(mResourceID, null);
            holder.titleView = (TextView)convertView.findViewById(R.id.textView2);
            holder.infoView = (TextView)convertView.findViewById(R.id.textView3);
            holder.imgView = (ImageView)convertView.findViewById(R.id.imageView5);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Map map = mItems.get(position);
        holder.titleView.setText(map.get("title").toString());
        holder.infoView.setText(map.get("info").toString());
        holder.imgView.setImageResource((Integer) map.get("img"));

        return convertView;
    }
}
