package com.google.elloribon;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.elloribon.model.Data;
import com.google.elloribon.model.DataAdapter;
import com.google.elloribon.model.ShortThousand;
import com.google.elloribon.parallax.BaseListView;
import com.google.elloribon.parallax.OnDetectScrollListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Roman on 06.10.2015.
 */
public class MainListFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener {

    ArrayList<Data> items_list;
    ProgressDialog progressDialog;
    BaseListView baseListView;

    private SwipeRefreshLayout swipeRefreshLayout;

    private static final String URL = "http://ellotv.bigdig.com.ua/api/home/video";
    private static final String TAG_DATA = "data";
    private static final String TAG_ARRAY_ITEMS = "items";
    private static final String TAG_TITLE = "title";
    private static final String TAG_VIEW_COUNT = "view_count";
    private static final String TAG_IMAGE = "picture";
    private static final String TAG_ARRAY_ARTISTS = "artists";
    private static final String TAG_NAME = "name";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        baseListView = (BaseListView) getListView();
        baseListView.setItemsCanFocus(false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        loadMyAsyncTaskLoader();

        baseListView.setOnDetectScrollListener(new OnDetectScrollListener() {
            Matrix imageMatrix;

            @Override
            public void onUpScrolling() {
                int first = baseListView.getFirstVisiblePosition();
                int last = baseListView.getLastVisiblePosition();
                for (int i = 0; i < (last - first); i++) {
                    ImageView imageView = ((DataAdapter.ViewHolder) baseListView.getChildAt(i).getTag()).imageView;
                    imageMatrix = imageView.getImageMatrix();
                    imageMatrix.postTranslate(0, -0.5f);
                    imageView.setImageMatrix(imageMatrix);
                    imageView.invalidate();
                }
            }

            @Override
            public void onDownScrolling() {
                int first = baseListView.getFirstVisiblePosition();
                int last = baseListView.getLastVisiblePosition();
                for (int i = 0; i < (last - first); i++) {
                    ImageView imageView = ((DataAdapter.ViewHolder) baseListView.getChildAt(i).getTag()).imageView;
                    imageMatrix = imageView.getImageMatrix();
                    imageMatrix.postTranslate(0, 0.5f);
                    imageView.setImageMatrix(imageMatrix);
                    imageView.invalidate();
                }
            }
        });

    }

    private void loadMyAsyncTaskLoader() {
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
                        String urlImage = jsonObjItems.getString(TAG_IMAGE);
                        int countViews = jsonObjItems.getInt(TAG_VIEW_COUNT);
                        JSONArray jsonArrayArtist = jsonObjItems.getJSONArray(TAG_ARRAY_ARTISTS);
                        for (int k = 0; k < jsonArrayArtist.length(); k++) {
                            JSONObject jsonObjArtists = jsonArrayArtist.getJSONObject(k);
                            titleArtist = jsonObjArtists.getString(TAG_NAME);
                        }
                        items_list.add(new Data(titleClip, titleArtist, ShortThousand.format(countViews), urlImage));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressDialog.dismiss();

                DataAdapter adapter = new DataAdapter(getActivity(), items_list);
                setListAdapter(adapter);

            }
        }.execute();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        loadMyAsyncTaskLoader();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, null);
    }

}
