package com.tchat.tchat;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;


public class LoginActivity extends ActionBarActivity {

    private static final String TAG = "LoginActivity";

    private static final int PHONE_FRAGMENT = 1;
    private static final int CODE_FRAGMENT = 2;
    private static int currentFragment = PHONE_FRAGMENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PhoneFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.send_code) {
            if (currentFragment == PHONE_FRAGMENT) {
                sendPhoneNumber();
                showCodeFragment();
            } else if (currentFragment == CODE_FRAGMENT) {
                sendCode();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendPhoneNumber() {
        EditText phoneEditText = (EditText) findViewById(R.id.phoneText);
        String phoneNumber = phoneEditText.getText().toString();
        TChatApplication.getTelegramClient().send(new TdApi.AuthSetPhoneNumber(phoneNumber), new Client.ResultHandler() {
            @Override
            public void onResult(TdApi.TLObject object) {
                Log.i(TAG, object.toString());
            }
        });
    }

    private void sendCode() {
        EditText codeEditText = (EditText) findViewById(R.id.codeText);
        String code = codeEditText.getText().toString();
        TChatApplication.getTelegramClient().send(new TdApi.AuthSetCode(code), new Client.ResultHandler() {
            @Override
            public void onResult(TdApi.TLObject object) {
                Log.i(TAG, object.toString());
            }
        });
    }

    private void showCodeFragment() {
        currentFragment = CODE_FRAGMENT;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, new CodeFragment());
        ft.commit();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PhoneFragment extends Fragment {

        public PhoneFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_login, container, false);
            return rootView;
        }
    }

    public static class CodeFragment extends Fragment {

        public CodeFragment() {

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.code_login, container, false);
            return rootView;
        }
    }
}
