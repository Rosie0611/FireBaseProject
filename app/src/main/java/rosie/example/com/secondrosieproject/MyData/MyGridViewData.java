package rosie.example.com.secondrosieproject.MyData;

/**
 * Created by skyki on 2017-08-26.
 */

public class MyGridViewData {

    private String Photoid;

   public MyGridViewData() {

    }

    public MyGridViewData(String photoid) {
        Photoid = photoid;
    }

    public String getPhotoid() {
        return Photoid;
    }

    public void setPhotoid(String photoid) {
        Photoid = photoid;
    }
}
