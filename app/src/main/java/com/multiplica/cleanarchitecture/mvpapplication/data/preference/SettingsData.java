package com.multiplica.cleanarchitecture.mvpapplication.data.preference;

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
    private final String KEY_ENCRYPTION = "encryptionKey";

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

}
