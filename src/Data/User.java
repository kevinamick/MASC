package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    public SimpleIntegerProperty id;
    public SimpleStringProperty fname;
    public SimpleStringProperty lname;
    public SimpleIntegerProperty school_id;
    public SimpleStringProperty email;
    public SimpleStringProperty password;
    public SimpleIntegerProperty type_id;

    public User() {
        this.id = new SimpleIntegerProperty();
        this.fname = new SimpleStringProperty();
        this.lname = new SimpleStringProperty();
        this.school_id = new SimpleIntegerProperty();
        this.email = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.type_id = new SimpleIntegerProperty();
    }

    public User(int id, String fname, String lname, int school_id,
                String email, String password, int type_id) {
        this();
        this.id.set(id);
        this.fname.set(fname);
        this.lname.set(lname);
        this.school_id.set(school_id);
        this.email.set(email);
        this.password.set(password);
        this.type_id.set(type_id);
    }

    public UserType getUserType() {
        UserTypeDAO userTypeDao = new UserTypeDAO();

        return userTypeDao.getUserType(this.type_id.get());
    }

    public int getUserId() {
        return id.get();
    }

    public void setUserId(int userId) {
        this.id.set(userId);
    }

    public String getFname() {
        return fname.get();
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public String getLname() {
        return lname.get();
    }

    public void setLname(String lname) {
        this.lname.set(lname);
    }

    public int getSchool_id() {
        return school_id.get();
    }

    public void setSchool_id(int school_id) {
        this.school_id.set(school_id);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public int getType_id() {
        return type_id.get();
    }

    public void setType_id(int type_id) {
        this.type_id.set(type_id);
    }
}
