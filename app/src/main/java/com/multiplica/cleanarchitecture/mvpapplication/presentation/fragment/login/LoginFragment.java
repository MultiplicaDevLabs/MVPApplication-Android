package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.login;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.multiplica.cleanarchitecture.mvpapplication.R;
import com.multiplica.cleanarchitecture.mvpapplication.domain.callback.IOnDialogResultListener;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.LoginEntity;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.activity.MainActivity;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.dialog.CreateUserDialog;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.AttachFragment;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.login.presenter.ILoginPresenter;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.login.presenter.LoginPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.multiplica.cleanarchitecture.mvpapplication.domain.constant.Constants.REQUEST_USER_PASSWORD;

/**
 * Created by user on 03/08/18.
 */

public class LoginFragment extends AttachFragment implements ILoginPresenter.View,
        View.OnClickListener, IOnDialogResultListener {

    private View rootView;

    @BindView(R.id.user_text_input_layout)
    TextInputLayout userTextInputLayout;
    @BindView(R.id.password_text_input_layout)
    TextInputLayout passwordTextInputLayout;

    @BindView(R.id.user_edit_text)
    EditText userEditText;
    @BindView(R.id.password_edit_text)
    EditText passwordEditText;

    @BindView(R.id.login_button)
    Button loginButton;

    @BindView(R.id.login_text_view)
    TextView loginTextView;

    private LoginPresenterImpl presenter;


    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setHasOptionsMenu(true);
        return loginFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(this, rootView);

        initResources();
        initPresenter();

        return rootView;
    }

    private void initResources(){
        loginButton.setOnClickListener(this);
    }

    private void initPresenter(){
        presenter = new LoginPresenterImpl();
        presenter.setView(this);
        presenter.initialize();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                    presenter.onLoginUser(userEditText.getText().toString(), passwordEditText.getText().toString());
                break;

        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void sucessLogin() {

        loginTextView.setText("Login Success!!!");

        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void failedLogin() {
        loginTextView.setText("Login Failed!!!");
    }

    @Override
    public void showCreteUserDialog() {
        CreateUserDialog dialogConfirmInfo = CreateUserDialog.newInstance(null);
        dialogConfirmInfo.setOnDialogListener(this);
        dialogConfirmInfo.show(getActivity().getSupportFragmentManager(), "dialog_create_user");
    }

    @Override
    public void showCreateUserSuccessfully() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setTitle("MESSAGE");

        alertDialogBuilder
                .setMessage("User was created successfully!!!")
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();

    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, Object data) {

        if (resultCode == Activity.RESULT_OK){
            switch (requestCode) {

                case REQUEST_USER_PASSWORD:

                    LoginEntity loginEntity = (LoginEntity) data;

                    if(loginEntity!=null){
                        presenter.onCreateUser(loginEntity);
                    }

                default:
            }

        }
    }
}
