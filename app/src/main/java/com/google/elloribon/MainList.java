package com.google.elloribon;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.elloribon.model.BaseListView;
import com.google.elloribon.model.Data;
import com.google.elloribon.model.DataAdapter;

import java.util.ArrayList;

/**
 * Created by Roman on 06.10.2015.
 */
public class MainList extends ListFragment {
    ArrayList<Data> item;
    private int mLastFirstVisibleItem;
    private boolean mIsScrollingUp;
    BaseListView baseListView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseListView = new BaseListView(getActivity());

        item = new ArrayList<>();

        item.add(new Data("Romko", "EnglisH", "Bomko", R.drawable.image1));
        item.add(new Data("Romko", "EnglisH", "Bomko", R.drawable.image2));
        item.add(new Data("Romko", "EnglisH", "Bomko", R.drawable.image3));
        item.add(new Data("Romko", "EnglisH", "Bomko", R.drawable.image4));
        item.add(new Data("Romko", "EnglisH", "Bomko", R.drawable.image5));
        item.add(new Data("Romko", "EnglisH", "Bomko", R.drawable.image6));
        item.add(new Data("Romko", "EnglisH", "Bomko", R.drawable.image7));
        item.add(new Data("Romko", "EnglisH", "Bomko", R.drawable.image8));
        item.add(new Data("Romko", "EnglisH", "Bomko", R.drawable.image9));
        item.add(new Data("Romko", "EnglisH", "Bomko", R.drawable.image10));

        DataAdapter adapter = new DataAdapter(getActivity(), item);
        setListAdapter(adapter);

       /* baseListView.setOnDetectScrollListener(new OnDetectScrollListener() {


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
                    ImageView imageView = ((ImageListAdapter.ViewHolder) baseListView.getChildAt(i).getTag()).imageView;
                    imageMatrix = imageView.getImageMatrix();
                    imageMatrix.postTranslate(0, 0.5f);
                    imageView.setImageMatrix(imageMatrix);
                    imageView.invalidate();
                }
            }
        });
        */

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, null);
    }


}
