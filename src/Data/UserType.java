package Data;

public class UserType {
    public Integer id;
    public String name;
    public Integer access;

    public UserType() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public UserType(Integer id, String name, Integer access) {
        this.id = id;
        this.name = name;

        this.access = access;
    }

    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object object) {
        int other;

        if (object instanceof UserType) {
            other = ((UserType)object).getId();
        } else {
            return false;
        }

        if (this.getId() != other) {
            return false;
        }
        return true;
    }
}
