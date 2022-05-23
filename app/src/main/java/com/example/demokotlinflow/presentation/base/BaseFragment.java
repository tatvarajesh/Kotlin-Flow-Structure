package com.example.demokotlinflow.presentation.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    protected Activity activity;
    protected View view;
    private int contentId;
    protected Bundle bundle;
    protected T binding;

    protected abstract void initViews();
    protected abstract void initObservers();
    protected abstract void apiCalls();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, contentId, container, false);
            initViews();
            initObservers();
            if (savedInstanceState == null) {
                apiCalls();
            }
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (contentId == 0) {
            bundle = getArguments();
            contentId = ActivityFragmentAnnotationManager.check(this);
            this.activity = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
