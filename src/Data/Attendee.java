package Data;

public class Attendee {
    public Integer id;
    public String fname;
    public String lname;
    public String gender;
    public String shirt;
    public Integer role_id;
    public String diet_prefs;
    public Integer invoice_id;

    public Attendee() {}

    public Attendee(Integer id, String fname, String lname, String gender, String shirt, Integer role_id, String diet_prefs, Integer invoice_id) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.shirt = shirt;
        this.role_id = role_id;
        this.diet_prefs = diet_prefs;
        this.invoice_id = invoice_id;
    }
}
