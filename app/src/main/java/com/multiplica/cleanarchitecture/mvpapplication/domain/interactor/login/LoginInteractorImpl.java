package com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.login;

import android.util.Base64;
import android.util.Log;

import com.multiplica.cleanarchitecture.mvpapplication.data.preference.SettingsData;
import com.multiplica.cleanarchitecture.mvpapplication.data.repository.implementation.LoginDataRepositoryImpl;
import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponse;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.LoginEntity;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.activity.LoginActivity;
import com.multiplica.cleanarchitecture.mvpapplication.utils.DecryptUtils;
import com.multiplica.cleanarchitecture.mvpapplication.utils.EncryptUtils;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by user on 03/08/18.
 */

public class LoginInteractorImpl implements ILoginInteractor{

    LoginDataRepositoryImpl repository = LoginDataRepositoryImpl.init();

    private EncryptUtils encryptor;
    private DecryptUtils decryptor;

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String USER_ALIAS = "user_alias";
    private static final String PASSWORD_ALIAS = "password_alias";

    private byte[] ivUser;
    private byte[] ivPassword;

    public LoginInteractorImpl(){
        encryptor = new EncryptUtils();

        try {
            decryptor = new DecryptUtils();
        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | NoSuchProviderException |
                IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginUser(String user, String password, BaseResponse callback) {


        LoginEntity login = repository.read(0);

        ivUser = SettingsData.getInstance().getIV_USER();
        ivPassword = SettingsData.getInstance().getIV_PASSWORD();


        String userStore = decryptData(login.getUser(), ivUser, USER_ALIAS);
        String passwordStore = decryptData(login.getPassword(), ivPassword, PASSWORD_ALIAS);

        Log.i(TAG, "Decrypted user value: " + ivUser.length);
        Log.i(TAG, "Decrypted password value: " + ivPassword.length);



        if(user.equals(userStore) &&
                password.equals(passwordStore)){
            callback.onSuccess();
        }else {
            callback.onError(new Exception("Login Failed"));
        }
    }

    @Override
    public void createUser(LoginEntity loginEntity, final BaseResponse callback) {

        LoginEntity login = new LoginEntity();

        login.setUser(encryptData(loginEntity.getUser(),USER_ALIAS));
        ivUser = encryptor.getIv();
        SettingsData.getInstance().setIV_USER(ivUser);
        login.setPassword(encryptData(loginEntity.getPassword(),PASSWORD_ALIAS));
        ivPassword = encryptor.getIv();
        SettingsData.getInstance().setIV_PASSWORD(ivPassword);

        repository.create(login, new BaseResponse() {
            @Override
            public void onSuccess() {
                SettingsData.getInstance().setUserCreated(true);

                callback.onSuccess();
            }
        });


    }

    @Override
    public boolean isUserCreated() {

        return SettingsData.getInstance().isUserCreated();
    }

    private String encryptData(String data, String alias){

        String encryptData = "";

        Log.i(TAG, "No Encrypted value: " + data);

        try {
            final byte[] encryptedText = encryptor
                    .encryptText(alias, data);
            encryptData = Base64.encodeToString(encryptedText, Base64.DEFAULT);


        } catch (UnrecoverableEntryException | NoSuchAlgorithmException | NoSuchProviderException |
                KeyStoreException | IOException | NoSuchPaddingException | InvalidKeyException e) {
            Log.e(TAG, "onClick() called with: " + e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException | SignatureException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "Encrypted value: " + encryptData);


        return encryptData;
    }

    private String decryptData(String data, byte[] iv, String alias){

        byte[] dataArray = Base64.decode(data.getBytes(),Base64.DEFAULT);

        String decryptData = "";

        try {
            decryptData = decryptor
                    .decryptData(alias, dataArray, iv);
        } catch (UnrecoverableEntryException | NoSuchAlgorithmException |
                KeyStoreException | NoSuchPaddingException | NoSuchProviderException |
                IOException | InvalidKeyException e) {
            Log.e(TAG, "decryptData() called with: " + e.getMessage(), e);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return decryptData;
    }
}
