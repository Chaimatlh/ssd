package application;
public class Participant {
    private String id;
    private String pseudo;
    private String password;

    public Participant(String id, String pseudo, String password) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }
}