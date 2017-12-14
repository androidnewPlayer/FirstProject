package com.hmkj.lazyloadfragment.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Administrator on 2017/11/30.
 */

public abstract class LazyLoadFragment extends Fragment {

    protected View rootView;
    private boolean mIsDataLoaded = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        rootView = null;
        mIsDataLoaded = false;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        if (rootView == null)
        {
            rootView = view;
        }
        if (getUserVisibleHint())
        {
            if (!mIsDataLoaded)
            {
                mIsDataLoaded = true;
                initData();
            }
        }

        super.onViewCreated(rootView, savedInstanceState);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if (rootView == null) return;
        if (isVisibleToUser)
        {
            if (!mIsDataLoaded)
            {
                mIsDataLoaded = true;
                initData();
            }
        }else
        {
            if (isRefreshDataEveryTimeIn())
            {
                mIsDataLoaded = false;
            }
        }
    }

    @Override
    public void onDestroyView()
    {
        rootView = null;
        mIsDataLoaded = false;
        super.onDestroyView();
    }

    public abstract void initData();

    protected boolean isRefreshDataEveryTimeIn()
    {
        return false;
    }
}
