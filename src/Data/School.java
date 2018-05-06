package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class School {
    public SimpleIntegerProperty id;
    public SimpleStringProperty name;
    public SimpleIntegerProperty region_id;

    public School() {
        id = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        region_id = new SimpleIntegerProperty();
    }

    public int getSchoolId() {
        return id.get();
    }

    public void setSchoolId(int schoolId) {
        this.id.set(schoolId);
    }

    public String getSchoolName() {
        return name.get();
    }

    public void setSchoolName(String schoolName) {
        this.name.set(schoolName);
    }

    public int getRegionId() {
        return region_id.get();
    }

    public void setRegionId(int regionId) {
        this.region_id.set(regionId);
    }

    public Region getRegion() {
        RegionDAO regionDao = new RegionDAO();

        return regionDao.getRegion(this.getRegionId());
    }

    public String toString() { return this.name.get(); }

    @Override
    public boolean equals(Object object) {
        int other;

        if (object instanceof School) {
            other = ((School)object).getSchoolId();
        } else {
            return false;
        }

        if (this.getSchoolId() != other) {
            return false;
        }
        return true;
    }
}
