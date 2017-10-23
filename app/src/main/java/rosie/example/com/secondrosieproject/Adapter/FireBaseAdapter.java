package rosie.example.com.secondrosieproject.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import rosie.example.com.secondrosieproject.Activity.Activity_Complete_Photo_List;
import rosie.example.com.secondrosieproject.MyData.MyPhotoData;
import rosie.example.com.secondrosieproject.R;

/**
 * Created by skyki on 2017-08-16.
 */

public class FireBaseAdapter extends ArrayAdapter<MyPhotoData> {

    private Activity_Complete_Photo_List context;
    private int resID;
    private List<MyPhotoData> list ;

    FirebaseUser user;

    public FireBaseAdapter(@NonNull Activity_Complete_Photo_List context, @LayoutRes int resource, @NonNull List<MyPhotoData> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resID = resource;
        list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(resID,null);
        TextView tv_explain  = (TextView)v.findViewById(R.id.tv_imageExplain);
        ImageView iv_pics = (ImageView)v.findViewById(R.id.iv_imageView);
        TextView tv_userid = (TextView) v.findViewById(R.id.tv_userid);
        TextView tv_date = (TextView) v.findViewById(R.id.tv_date);

        user = FirebaseAuth.getInstance().getCurrentUser();

        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM d yyyy", Locale.ENGLISH);
        String format = simpleDateFormat.format(new Date(date));

        tv_explain.setText(list.get(position).getStrExplain());
        Glide.with(context).load(list.get(position).getiPhoto()).into(iv_pics);
        tv_userid.setText(list.get(position).getUserid());
        tv_date.setText(list.get(position).getDate());

        return v;
    }
}
