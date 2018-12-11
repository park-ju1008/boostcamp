package com.example.juyoung.boostcamp.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.juyoung.boostcamp.R;
import com.example.juyoung.boostcamp.databinding.ActivityMainBinding;
import com.example.juyoung.boostcamp.viewmodel.MainViewModel;


public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this,mainActivityNavigator);
        binding.setMainViewModel(mainViewModel);

        binding.recyclerviewMainMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mainViewModel.OnScroll(recyclerView,dx,dy);
            }
        });

    }

    private MainViewModel.MainActivityNavigator mainActivityNavigator=new MainViewModel.MainActivityNavigator() {
        @Override
        public String getText() {
            return binding.edittextMainSearch.getText().toString();
        }

        @Override
        public void onProgress() {
            progressON();
        }

        @Override
        public void offProgress() {
            progressOFF();
        }

        @Override
        public Boolean isNetWork() {
            return isNetwork();
        }
    };


    //네트워크 연결상태 확인
    private Boolean isNetwork() {
        ConnectivityManager connectivityManager = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}
