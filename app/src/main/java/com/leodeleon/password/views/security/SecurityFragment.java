package com.leodeleon.password.views.security;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import com.leodeleon.password.MainActivity;
import com.leodeleon.password.R;
import com.leodeleon.password.base.BaseFragment;
import com.leodeleon.password.views.main.MainFragment;
import com.leodeleon.password.views.security.mvp.SecurityContract;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SecurityFragment extends BaseFragment implements SecurityContract.View {
    private  static final String EDIT_STATE = "EDIT";

    @Inject
    SecurityContract.Presenter mPresenter;

    @BindView(R.id.password)
    EditText mPasswordEditText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_security, container, false);
        mUnbinder = ButterKnife.bind(this,root);
        setViews();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            String text = savedInstanceState.getString(EDIT_STATE,"");
            mPasswordEditText.setText(text);
            mPasswordEditText.setSelection(text.length());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EDIT_STATE, mPasswordEditText.getText().toString());
    }

    @Override
    public void showWrongPasswordAlert() {
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setCancelable(false)
                .setTitle(R.string.wrong_password)
                .setMessage(R.string.try_again)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPasswordEditText.getText().clear();
                        mPasswordEditText.requestFocus();
                        showInputMethod();
                    }
                }).create();
        dialog.show();
    }

    @Override
    public void showMainView() {
        if(getActivity() != null){
            ((MainActivity) getActivity()).replaceFragment(new MainFragment());
        }
    }

    private void setViews(){
        mPasswordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    mPresenter.submitPassword(v.getText().toString());
                    return false;
                }
                return false;
            }
        });
    }
}
