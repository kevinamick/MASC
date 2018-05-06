package Data;

public class Region {
    public Integer id;
    public String name;
    public String code;

    public Region() {}

    public Region(Integer id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        String other = "";
        if (object instanceof Region) {
            other = ((Region)object).code;
        } else if(object instanceof String){
            other = (String)object;
        } else {
            return false;
        }

        if ((this.code == null && other != null) || (this.code != null && !this.code.equals(other))) {
            return false;
        }
        return true;
    }
}
