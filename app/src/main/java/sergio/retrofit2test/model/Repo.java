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
                "| Language: " + language +
                "\n Created at: " + created_at +
                "\n Url: " + url +
                "\n";
    }
}
