package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Attendee {
    public SimpleIntegerProperty id;
    public SimpleStringProperty fname;
    public SimpleStringProperty lname;
    public SimpleStringProperty gender;
    public SimpleStringProperty shirt;
    public SimpleIntegerProperty role_id;
    public SimpleStringProperty diet_prefs;
    public SimpleIntegerProperty invoice_id;

    public Attendee() {
        this.id = new SimpleIntegerProperty();
        this.fname = new SimpleStringProperty();
        this.lname = new SimpleStringProperty();
        this.gender = new SimpleStringProperty();
        this.shirt = new SimpleStringProperty();
        this.role_id = new SimpleIntegerProperty();
        this.diet_prefs = new SimpleStringProperty();
        this.invoice_id = new SimpleIntegerProperty();
    }

    public Attendee(SimpleIntegerProperty id, SimpleStringProperty fname, SimpleStringProperty lname, SimpleStringProperty gender,
                    SimpleStringProperty shirt, SimpleIntegerProperty role_id, SimpleStringProperty diet_prefs,
                    SimpleIntegerProperty invoice_id) {
        if(this.id == null) {
            this.id = new SimpleIntegerProperty();
            this.fname = new SimpleStringProperty();
            this.lname = new SimpleStringProperty();
            this.gender = new SimpleStringProperty();
            this.shirt = new SimpleStringProperty();
            this.role_id = new SimpleIntegerProperty();
            this.diet_prefs = new SimpleStringProperty();
            this.invoice_id = new SimpleIntegerProperty();
        }
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.shirt = shirt;
        this.role_id = role_id;

        this.diet_prefs = diet_prefs;
        this.invoice_id = invoice_id;
    }

    public Role getRole() {
        RoleDAO roleDao = new RoleDAO();

        return roleDao.getRole(this.getAttendeeRoleId());
    }

    public String getFullName() {
        return this.getAttendeeFname() + " " + this.getAttendeeLname();
    }

    public int getAttendeeId() {
        return id.get();
    }

    public void setAttendeeId(int id) {
        this.id.set(id);
    }

    public String getAttendeeFname() {
        return fname.get();
    }

    public void setAttendeeFname(String fname) {
        this.fname.set(fname);
    }

    public String getAttendeeLname() {
        return lname.get();
    }

    public void setAttendeeLname(String lname) {
        this.lname.set(lname);
    }

    public String getAttendeeGender() {
        return gender.get();
    }

    public void setAttendeeGender(String gender) {
        this.gender.set(gender);
    }

    public String getAttendeeShirt() {
        return shirt.get();
    }

    public void setAttendeeShirt(String shirt) {
        this.shirt.set(shirt);
    }

    public int getAttendeeRoleId() {
        return role_id.get();
    }

    public void setAttendeeRoleId(int role_id) {
        this.role_id.set(role_id);
    }

    public String getAttendeeDietPrefs() {
        return diet_prefs.get();
    }

    public void setAttendeeDietPrefs(String diet_prefs) {
        this.diet_prefs.set(diet_prefs);
    }

    public int getAttendeeInvoiceId() {
        return invoice_id.get();
    }

    public void setAttendeeInvoiceId(int invoice_id) {
        this.invoice_id.set(invoice_id);
    }
}
