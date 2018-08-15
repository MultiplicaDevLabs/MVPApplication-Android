package com.multiplica.cleanarchitecture.mvpapplication.data.model.item;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by user on 03/08/18.
 */

public class LoginRealm extends RealmObject {

    @PrimaryKey
    private int id;
    private String user;
    private String password;

    public static final String ID = "id";
    public static final String USER = "user";
    public static final String PASSWORD = "password";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
