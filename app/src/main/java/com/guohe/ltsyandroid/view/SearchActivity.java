package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 水寒 on 2017/7/22.
 */

public class SearchActivity extends BaseActivity {

    private FloatingSearchView mSearchView;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mSearchView = getView(R.id.floating_search_view);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                List<SearchSuggestion> suggestions = new ArrayList<SearchSuggestion>();
                for(int i = 0; i < 10; i++){
                    SearchSuggestion suggestion = new SearchSuggestion() {
                        @Override
                        public int describeContents() {
                            return 0;
                        }

                        @Override
                        public void writeToParcel(Parcel dest, int flags) {

                        }

                        @Override
                        public String getBody() {
                            return "suggestion";
                        }
                    };
                    suggestions.add(suggestion);
                }
                mSearchView.swapSuggestions(suggestions);
            }
        });
    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
}
