package com.google.elloribon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

/**
 * Created by Roman on 06.10.2015.
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check internet connection
        if (isOnline() == false) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getResources().getString(R.string.titleAlertDialog))

                    .setMessage(getResources().getString(R.string.messageAlertDialog))
                    .setCancelable(false)
                    .setNegativeButton(getResources().getString(R.string.nameNegativeButton),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MainActivity.this.finish();
                                }
                            });

            AlertDialog alert = builder.create();
            alert.show();

        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
