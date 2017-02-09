package sergio.retrofit2test.model;

import java.util.Date;

/**
 * @author s.ruiz
 */

public class Repo {
    private Owner owner;
    private String name;
    private String svn_url;
    private String language;
    private Date created_at;
    private int stargazers_count;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return svn_url;
    }

    public String getLanguage() {
        return language;
    }

    public int getStars() {
        return stargazers_count;
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
                "\n Url: " + svn_url +
                "\n";
    }
}
