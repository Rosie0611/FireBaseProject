package rosie.example.com.secondrosieproject.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rosie.example.com.secondrosieproject.R;

public class Activity_Board_Main extends AppCompatActivity {

    @BindView(R.id.bt_writePost)
    Button bt_writePost;
    @BindView(R.id.bt_Map)
    Button bt_Map;
    @BindView(R.id.bt_UploadPhoto)
    Button bt_uploadphoto;
    @BindView(R.id.bt_checkAllPost)
    Button bt_checkAllPost;
    @BindView(R.id.bt_checkMyPhoto)
    Button bt_checkMyPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_writePost)
    public void Move_Board_writePost() {
        startActivity(new Intent(Activity_Board_Main.this, Activity_WritePost.class));

    }

    @OnClick(R.id.bt_Map)
    public void Move_Board_Map() {
        startActivity(new Intent(Activity_Board_Main.this, Activity_googleMap.class));

    }

    @OnClick(R.id.bt_UploadPhoto)
    public void Move_Board_uploadphoto() {
        startActivity(new Intent(Activity_Board_Main.this, Activity_UploadPhoto.class));
    }

    @OnClick(R.id.bt_checkAllPost)
    public void Check_Uploaded_All_Post() {
        startActivity(new Intent(Activity_Board_Main.this, Activity_Complete_Photo_List.class));
    }

    @OnClick(R.id.bt_checkMyPhoto)
    public void Check_Uploaded_My_Post() {
        startActivity(new Intent(Activity_Board_Main.this, Activity_Complete_MyPhoto_GridView.class));
    }

}
