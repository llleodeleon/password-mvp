package com.leodeleon.password.views.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leodeleon.password.R;
import com.leodeleon.password.base.BaseFragment;
import com.leodeleon.password.views.main.mvp.MainContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends BaseFragment implements MainContract.View {
    private  static final String EDIT_STATE = "EDIT";
    private  static final String SWITCH_STATE = "SWITCH";

    @Inject
    MainContract.Presenter mPresenter;

    @BindView(R.id.password)
    EditText mPasswordEditText;
    @BindView(R.id.secure_switch)
    SwitchCompat mSwitch;
    @BindView(R.id.save)
    AppCompatButton mButton;
    private Group mSecureGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main,container, false);
        mUnbinder = ButterKnife.bind(this, root);
        mSecureGroup = root.findViewById(R.id.secure_group);
        setViews();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EDIT_STATE, mPasswordEditText.getText().toString());
        outState.putBoolean(SWITCH_STATE, mSwitch.isChecked());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            String text = savedInstanceState.getString(EDIT_STATE,"");
            mPasswordEditText.setText(text);
            mPasswordEditText.setSelection(text.length());
            Boolean checked = savedInstanceState.getBoolean(SWITCH_STATE);
            if(checked){
                revealFields();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this);
        mPresenter.checkSecureState();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    @Override
    public void showPasswordSaved() {
        Toast.makeText(getContext(),R.string.password_saved, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void revealFields() {
        mSwitch.setChecked(true);
        mSecureGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void clearFields() {
        mPasswordEditText.getText().clear();
        mPasswordEditText.clearFocus();
        hideInputMethod(mPasswordEditText);
        mButton.setEnabled(false);
    }

    private void setViews() {
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mSecureGroup.setVisibility(View.VISIBLE);
                } else{
                    mSecureGroup.setVisibility(View.GONE);
                    mPresenter.disableSecureMode();
                }
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.enableSecureMode();
                mPresenter.savePassword(mPasswordEditText.getText().toString());
            }
        });

        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                boolean enable = s.length() == getResources().getInteger(R.integer.max_length);
                mButton.setEnabled(enable);
            }
        });

        mPasswordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
    }
}
