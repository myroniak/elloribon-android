package com.google.elloribon;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.elloribon.model.Data;

import java.util.ArrayList;

/**
 * Created by Roman on 06.10.2015.
 */
public class MainActivity extends Activity {
    private int mLastFirstVisibleItem;
    private boolean mIsScrollingUp;
    ArrayList<Data> item;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
