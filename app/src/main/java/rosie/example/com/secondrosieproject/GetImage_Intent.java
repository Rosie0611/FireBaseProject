package rosie.example.com.secondrosieproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import rosie.example.com.secondrosieproject.MyData.MyPhotoData;

public class GetImage_Intent extends AppCompatActivity {

    TextView tv_Explain;
    ImageView iv_imageView;
    MyPhotoData myPhotoData = new MyPhotoData();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
//            case PHOTO_SENT :
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getimage_layout);
        tv_Explain = (TextView)findViewById(R.id.tv_imageExplain);
        iv_imageView = (ImageView)findViewById(R.id.iv_imageView);





        Intent intent = getIntent();
        String strEx = intent.getStringExtra("Explain");

//        iv_imageView.setDrawingCacheEnabled(true);
//        iv_imageView.buildDrawingCache();
//        String strExplain = intent.getStringExtra("Explain");
//        byte[] bytes = intent.getByteArrayExtra("data");
//        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
//        bytes.

        tv_Explain.setText(strEx);
//        iv_imageView.setImageResource(iPhoto);
    }
}
