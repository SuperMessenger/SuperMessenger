package com.tchat.tchat;

import android.util.Log;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;

/**
 * Created by dmitriy on 10.05.15.
 */
public class TChatUpdateHandler implements Client.ResultHandler {

    private static final String TAG = "TChatUpdateHandler";

    @Override
    public void onResult(TdApi.TLObject object) {
        Log.i(TAG, object.toString());
    }

}
