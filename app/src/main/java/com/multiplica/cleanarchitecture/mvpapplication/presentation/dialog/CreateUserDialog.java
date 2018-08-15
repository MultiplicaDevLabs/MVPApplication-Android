package com.multiplica.cleanarchitecture.mvpapplication.presentation.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.multiplica.cleanarchitecture.mvpapplication.R;
import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseDialog;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.LoginEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.multiplica.cleanarchitecture.mvpapplication.domain.constant.Constants.DATA;
import static com.multiplica.cleanarchitecture.mvpapplication.domain.constant.Constants.REQUEST_USER_PASSWORD;

/**
 * Created by user on 06/08/18.
 */

public class CreateUserDialog extends BaseDialog implements View.OnClickListener{

    @BindView(R.id.dialog_content)
    View dialogContent;

    @BindView(R.id.create_user_text_input_layout)
    TextInputLayout createUserTextInputLayout;
    @BindView(R.id.create_user_edit_text)
    EditText createUserEditText;

    @BindView(R.id.create_password_text_input_layout)
    TextInputLayout createPasswordTextInputLayout;
    @BindView(R.id.create_password_edit_text)
    EditText createPasswordEditText;

    @BindView(R.id.confirm_password_text_input_layout)
    TextInputLayout confirmPasswordTextInputLayout;
    @BindView(R.id.confirm_password_edit_text)
    EditText confirmPasswordEditText;

    @BindView(R.id.create_user_button)
    Button createUserButton;

    @BindView(R.id.create_user_text_view)
    TextView createUserTextView;

    private View rootView;


    public static CreateUserDialog newInstance(Bundle bundle) {
        CreateUserDialog dialogCoachConfirmInfo = new CreateUserDialog();
        dialogCoachConfirmInfo.setHasOptionsMenu(true);
        return dialogCoachConfirmInfo;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                dismissAndContentAnimation(dialogContent, R.anim.shrink);
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_create_user, container, false);
        ButterKnife.bind(this, rootView);
        initResources();
        return rootView;
    }

    private void initResources(){
        createUserButton.setOnClickListener(this);
        createUserEditText.addTextChangedListener(getTextWatcher());
        createPasswordEditText.addTextChangedListener(getTextWatcher());
        confirmPasswordEditText.addTextChangedListener(getTextWatcher());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_user_button:

                if(validateConfirmPassword()){

                    LoginEntity loginEntity = new LoginEntity();
                    loginEntity.setUser(createUserEditText.getText().toString());
                    loginEntity.setPassword(createPasswordEditText.getText().toString());

                    Bundle args = new Bundle();
                    args.putParcelable(DATA, loginEntity);
                    result.onDialogResult(REQUEST_USER_PASSWORD, Activity.RESULT_OK, loginEntity);
                    dismissAndContentAnimation(dialogContent, R.anim.shrink);

                }else{
                    createUserTextView.setText("Password doesn't match, verify your password");
                }

                break;
        }
    }

    private TextWatcher getTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(createUserEditText.getText().toString().trim().length()>0 &&
                        createPasswordEditText.getText().toString().trim().length()>0 &&
                        confirmPasswordEditText.getText().toString().trim().length()>0){
                    createUserButton.setEnabled(true);
                }else{
                    createUserButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    private boolean validateConfirmPassword(){
        return createPasswordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString());
    }
}
