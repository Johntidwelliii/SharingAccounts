package com.alorma.secondmodule;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private static final int RETURN_CODE = 242;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, RETURN_CODE);
            }
        });

        listView = (ListView) findViewById(R.id.list);

        showAccounts();
    }

    private void showAccounts() {
        Account[] accounts = getAccounts();

        AccountsAdapter accountsAdapter = new AccountsAdapter(this, accounts);

        listView.setAdapter(accountsAdapter);
    }

    public Account[] getAccounts() {
        return AccountManager.get(this).getAccountsByType(getString(R.string.account_type));
    }

    private class AccountsAdapter extends ArrayAdapter<Account> {

        private final LayoutInflater mInflater;

        public AccountsAdapter(Context context, Account[] objects) {
            super(context, 0, objects);
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = this.mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            TextView textView = (TextView) view.findViewById(android.R.id.text1);

            textView.setText(getItem(position).name);

            return view;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == RETURN_CODE) {
          showAccounts();
        }
    }
}
