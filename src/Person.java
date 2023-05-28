public abstract class Person implements java.io.Serializable{
    private String username;
    private String password;
    private int id;
    private Gender gender;


    public Person(String username, String password, int id, Gender gender) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.gender=gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract void display();
}