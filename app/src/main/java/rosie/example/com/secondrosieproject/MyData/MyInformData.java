package rosie.example.com.secondrosieproject.MyData;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by skyki on 2017-08-12.
 */

@IgnoreExtraProperties
public class MyInformData {

    private String UserID, name, gender, address, phone1, phone2, phone3;

    public MyInformData(){}

    public MyInformData(String userID, String name, String gender, String address, String phone1, String phone2, String phone3) {

        this.UserID = userID;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }
}
