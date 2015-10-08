package com.google.elloribon;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.elloribon.model.Data;
import com.google.elloribon.model.DataAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Roman on 06.10.2015.
 */
public class MainListFragment extends ListFragment {

    ArrayList<Data> items_list;
    ProgressDialog progressDialog;

    private static final String URL = "http://ellotv.bigdig.com.ua/api/home/video";
    private static final String TAG_DATA = "data";
    private static final String TAG_ARRAY_ITEMS = "items";
    private static final String TAG_TITLE = "title";
    private static final String TAG_VIEW_COUNT = "view_count";
    private static final String TAG_IMAGE = "picture";
    private static final String TAG_ARRAY_ARTISTS = "artists";
    private static final String TAG_NAME = "name";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final String messageProgressDialog = getResources().getString(R.string.messageProgressDialog);

        new MyAsyncTaskLoader(getActivity(), URL, progressDialog, messageProgressDialog) {

            @Override
            protected void onPostExecute(String strJson) {

                items_list = new ArrayList();

                try {

                    JSONObject jsonobject = new JSONObject(strJson);
                    JSONObject jsonObjData = jsonobject.getJSONObject(TAG_DATA);
                    JSONArray jsonArrayItems = jsonObjData.getJSONArray(TAG_ARRAY_ITEMS);

                    String titleArtist = null;
                    for (int j = 0; j < jsonArrayItems.length(); j++) {

                        JSONObject jsonObjItems = jsonArrayItems.getJSONObject(j);
                        String titleClip = jsonObjItems.getString(TAG_TITLE);
                        String countViews = jsonObjItems.getString(TAG_VIEW_COUNT);
                        String urlImage = jsonObjItems.getString(TAG_IMAGE);

                        JSONArray jsonArrayArtist = jsonObjItems.getJSONArray(TAG_ARRAY_ARTISTS);
                        for (int k = 0; k < jsonArrayArtist.length(); k++) {
                            JSONObject jsonObjArtists = jsonArrayArtist.getJSONObject(k);
                            titleArtist = jsonObjArtists.getString(TAG_NAME);

                        }
                        items_list.add(new Data(titleClip, titleArtist, countViews, urlImage));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressDialog.dismiss();

                DataAdapter adapter = new DataAdapter(getActivity(), items_list);
                setListAdapter(adapter);

            }
        }.execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, null);
    }

}
