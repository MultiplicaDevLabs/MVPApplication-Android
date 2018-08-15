package com.multiplica.cleanarchitecture.mvpapplication.data.preference;

import android.util.Base64;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BasePreferences;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Created by user on 18/07/18.
 */

public class SettingsData extends BasePreferences{

    private static SettingsData singleton;

    public static SettingsData getInstance() {
        if (singleton == null)
            singleton = new SettingsData();
        return singleton;
    }

    private final String PREFER_NAME = "settings_data";
    private final String KEY_ENCRYPTION = "encryption_key";
    private final String USER_CREATED = "user_created";
    private final String IV_USER = "iv_user";
    private final String IV_PASSWORD = "iv_password";

    @Override
    public String onPreferName() {
        return PREFER_NAME;
    }

    public byte[] getEncryptionKey(){
        if (getPreferences().contains(KEY_ENCRYPTION)==false){
            byte[] secretKey32 = new byte[64];
            new SecureRandom().nextBytes(secretKey32);
            getEditor().putString(KEY_ENCRYPTION, Arrays.toString(secretKey32));
            getEditor().commit();
        }
        String stringArray = getPreferences().getString(KEY_ENCRYPTION, null);
        String[] split = stringArray.substring(1, stringArray.length()-1).split(", ");
        byte[] secretKey = new byte[split.length];
        for (int i = 0; i < split.length; i++) {
            secretKey[i] = Byte.parseByte(split[i]);
        }
        return secretKey;
    }

    public void setUserCreated(Boolean updatedApp) {
        getEditor().putBoolean(USER_CREATED, updatedApp);
        getEditor().commit();
    }

    public Boolean isUserCreated() {
        return getPreferences().getBoolean(USER_CREATED, false);
    }

    public void setIV_USER(byte[] ivUser){
        String ivString = Base64.encodeToString(ivUser,Base64.DEFAULT);
        getEditor().putString(IV_USER,ivString);
    }

    public byte[] getIV_USER(){

        String ivString = getPreferences().getString(IV_USER,"");
        return Base64.decode(ivString.getBytes(),Base64.DEFAULT);
    }

    public void setIV_PASSWORD(byte[] ivUser){
        String ivString = Base64.encodeToString(ivUser,Base64.DEFAULT);
        getEditor().putString(IV_PASSWORD,ivString);
    }

    public byte[] getIV_PASSWORD(){

        String ivString = getPreferences().getString(IV_PASSWORD,"");
        return Base64.decode(ivString.getBytes(),Base64.DEFAULT);
    }
}
