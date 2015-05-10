package com.tchat.tchat;

import android.app.Application;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TG;

/**
 * Created by dmitriy on 10.05.15.
 */
public class TChatApplication extends Application {

    private static final String TAG = "TChatApplication";

    private static Client client = null;

    @Override
    public void onCreate() {
        super.onCreate();
        TG.setDir(getFilesDir().toString());
        TG.setUpdatesHandler(new TChatUpdateHandler());
        client = TG.getClientInstance();
    }

    public static Client getTelegramClient() {
        return client;
    }

    @Override
    public void onTerminate() {
        TG.stopClient();
        super.onTerminate();
    }
}
