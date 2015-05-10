package com.tchat.tchat;

import android.app.Application;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TG;
import org.drinkless.td.libcore.telegram.TdApi;

/**
 * Created by dmitriy on 10.05.15.
 */
public class TChatApplication extends Application {

    private static Client client = null;

    @Override
    public void onCreate() {
        super.onCreate();
        TG.setDir(getFilesDir().toString());
        TG.setUpdatesHandler(new TChatUpdateHandler());
        client = TG.getClientInstance();
        client.send(new TdApi.AuthReset(), new Client.ResultHandler() {
            @Override
            public void onResult(TdApi.TLObject object) {

            }
        });
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
