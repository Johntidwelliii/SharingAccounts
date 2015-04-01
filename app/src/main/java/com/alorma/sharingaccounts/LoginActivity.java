package com.alorma.sharingaccounts;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Bernat on 01/04/2015.
 */
public class LoginActivity extends AccountAuthenticatorActivity {
    public static final String ADDING_FROM_ACCOUNTS = "ADDING_FROM_ACCOUNTS";
    public static final String ARG_ACCOUNT_TYPE = "ARG_ACCOUNT_TYPE";
    public static final String ARG_AUTH_TYPE = "ARG_AUTH_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        final EditText editText = (EditText) findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    addAccount(text);
                }
            }
        });
    }

    private void addAccount(String text) {
        AccountManager accountManager = AccountManager.get(this);

        Account account = new Account(text, getString(R.string.account_type));
        accountManager.addAccountExplicitly(account, null, null);

        Bundle result = new Bundle();
        result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
        result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);

        setAccountAuthenticatorResult(result);

        Intent intent = new Intent();
        intent.putExtras(result);
        setResult(RESULT_OK, intent);
        finish();
    }
}
