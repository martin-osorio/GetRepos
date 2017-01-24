package com.martinosorio.restpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReposView extends AppCompatActivity {
    private List<ResultAPI> resultAPIList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ResultAPIAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_view);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ResultAPIAdapter(resultAPIList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        Observable<List<ResultAPI>> resultObservable = RetrofitHelper.Factory.create(getIntent().getStringExtra("username"));
        resultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ResultAPI>>() {
                    @Override
                    public void onCompleted() {
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "ERROR: probably wrong user", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(List<ResultAPI> resultAPIs) {
                        resultAPIList.addAll(resultAPIs);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setAdapter(mAdapter);
    }
}
