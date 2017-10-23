package rosie.example.com.secondrosieproject.MyData;

import android.net.Uri;

/**
 * Created by skyki on 2017-08-12.
 */

public class MyPhotoData {

    private String iPhoto, strExplain, userid, date;


    public MyPhotoData() {
    }

    public MyPhotoData(String iPhoto) {
        this.iPhoto = iPhoto;
    }

    public MyPhotoData(String iPhoto, String strExplain) {
        this.iPhoto = iPhoto;
        this.strExplain = strExplain;
    }

    public MyPhotoData(String iPhoto, String strExplain, String userid, String date) {
        this.iPhoto = iPhoto;
        this.strExplain = strExplain;
        this.userid = userid;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getiPhoto() {
        return iPhoto;
    }

    public void setiPhoto(String iPhoto) {
        this.iPhoto = iPhoto;
    }

    public String getStrExplain() {
        return strExplain;
    }

    public void setStrExplain(String strExplain) {
        this.strExplain = strExplain;
    }
}
