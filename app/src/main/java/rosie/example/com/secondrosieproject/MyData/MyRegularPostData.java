package rosie.example.com.secondrosieproject.MyData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by skyki on 2017-08-17.
 */

public class MyRegularPostData {

    private String user, postContents, time;

    public MyRegularPostData() {
    }

    public MyRegularPostData(String user,String postContents,String time ) {
        this.user = user;
        this.postContents = postContents;
        this.time = time;

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPostContents() {
        return postContents;
    }

    public void setPostContents(String postContents) {
        this.postContents = postContents;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
