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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getShirt() {
        return shirt;
    }

    public void setShirt(String shirt) {
        this.shirt = shirt;
    }

    public Integer getRoleId() {
        return role_id;
    }

    public void setRoleId(Integer role_id) {
        this.role_id = role_id;
    }

    public String getDietPrefs() {
        return diet_prefs;
    }

    public void setDietPrefs(String diet_prefs) {
        this.diet_prefs = diet_prefs;
    }

    public Integer getInvoiceId() {
        return invoice_id;
    }

    public void setInvoiceId(Integer invoice_id) {
        this.invoice_id = invoice_id;
    }

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
