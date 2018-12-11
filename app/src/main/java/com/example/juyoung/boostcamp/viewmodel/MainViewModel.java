package com.example.juyoung.boostcamp.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.juyoung.boostcamp.adpter.MainRecyclerViewAdapter;
import com.example.juyoung.boostcamp.model.Movie;
import com.example.juyoung.boostcamp.model.RetrofitService;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MainRecyclerViewAdapter adapter;
    private MainActivityNavigator mainActivityNavigator;
    private int display = 30;
    private int page = 1;
    private Boolean endLine = false;
    private String searchText;

    public MainViewModel(Context context, MainActivityNavigator mainActivityNavigator) {
        adapter = new MainRecyclerViewAdapter(context);
        this.mainActivityNavigator = mainActivityNavigator;
    }

    public MainRecyclerViewAdapter getAdapter() {
        return adapter;
    }


    public void onButtonClick(View view) {

        if (mainActivityNavigator.isNetWork()) {
            mainActivityNavigator.onProgress();
            searchText = mainActivityNavigator.getText();
            if (searchText.isEmpty()) {
                Toast.makeText(view.getContext(), "검색어를 입력하세요.", Toast.LENGTH_SHORT).show();
            } else {
                page = 1;
                List<Movie> movies = RetrofitService.getInstance().getMoviewList(searchText, display, page);
                if (movies.isEmpty()) {
                    Toast.makeText(view.getContext(), "'" + searchText + "'검색결과가 없습니다..", Toast.LENGTH_SHORT).show();
                } else {
                    page += movies.size();
                    endLine = false;
                }
                this.adapter.updateItems(movies);
                mainActivityNavigator.offProgress();
            }
        }else{
            Toast.makeText(view.getContext(), "휴대폰의 네트워크 연결상태를 확인하세요.", Toast.LENGTH_SHORT).show();
        }

    }

    public void OnScroll(RecyclerView recyclerView, int dx, int dy) {
        LinearLayoutManager llManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (!endLine && dy > 0 && llManager.findLastCompletelyVisibleItemPosition() > (adapter.getItemCount() - display / 2)) {
            List<Movie> movies = RetrofitService.getInstance().getMoviewList(searchText, display, page);
            if (movies.size() == display) {
                page += display;
            } else {
                endLine = true;
            }
            adapter.addItems(movies);
        }
    }

    public interface MainActivityNavigator {
        String getText();

        void onProgress();

        void offProgress();

        Boolean isNetWork();
    }


}
