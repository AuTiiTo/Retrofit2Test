package sergio.retrofit2test.model;

import java.util.Date;

/**
 * @author s.ruiz
 */

public class Repo {
    private Owner owner;
    private String name;
    private String url;
    private String language;
    private Date created_at;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getLanguage() {
        return language;
    }

    public Date getCreated() {
        return created_at;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Repo: " + name +
                "\nLanguage: " + language +
                "\nCreated at: " + created_at +
                "\nUrl: " + url +
                "\n";
    }
}
