package jp.ac.anan_nct.smaoni_elide.model;

/**
 * Created by skriulle on 2015/03/15.
 */
public class User {
    public String account, name, password;

    public User(){
        account = "";
        password = "";
        name = "";
    }
    public User(String account, String password, String name){
        this.account = account;
        this.password = password;
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getAccount() {
        return account+" ";
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
