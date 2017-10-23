package rosie.example.com.secondrosieproject.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import rosie.example.com.secondrosieproject.Activity.Activity_Complete_MyPhoto_GridView;
import rosie.example.com.secondrosieproject.MyData.MyGridViewData;
import rosie.example.com.secondrosieproject.MyData.MyPhotoData;
import rosie.example.com.secondrosieproject.R;

/**
 * Created by skyki on 2017-08-26.
 */

public class GridviewAdapter extends BaseAdapter {

    Activity_Complete_MyPhoto_GridView context;
    int iResID;
    ArrayList<MyGridViewData> arrData = new ArrayList<>();

    public GridviewAdapter(Activity_Complete_MyPhoto_GridView context, int iResID, ArrayList<MyGridViewData> arrData) {
        this.context = context;
        this.iResID = iResID;
        this.arrData = arrData;
    }

    public class holder {
        ImageView imageView;

        public holder(ImageView imageView) {
            this.imageView = imageView;
        }
    }

    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public Object getItem(int position) {
        return arrData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        holder holder = null;
        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(iResID, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_gridView_item);
            int columnWidth = ((GridView) parent).getColumnWidth();
            imageView.getLayoutParams().width = columnWidth;
            imageView.getLayoutParams().height = columnWidth;

            holder = new holder(imageView);

            view.setTag(holder);
        } else {
            holder = (holder) view.getTag();
        }

        Glide.with(context).load(arrData.get(position).getPhotoid()).into(holder.imageView);

        return view;
    }
}
