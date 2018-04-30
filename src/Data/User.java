package Data;

public class User {
    public Integer id;
    public String fname;
    public String lname;
    public Integer school_id;
    public String email;
    public String password;
    public Integer type_id;

    public User() {}

    public User(Integer id, String fname, String lname, Integer school_id, String email, String password, Integer type_id) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.school_id = school_id;
        this.email = email;
        this.password = password;
        this.type_id = type_id;
    }
}
