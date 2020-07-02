package com.example.myapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.myapplication.data.remote.response.GitMember;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class RxSearchWidget {

    private CompositeDisposable disposable = new CompositeDisposable();
    private PublishSubject<String> subject;

    public RxSearchWidget(SearchView search, final RxSearchListener listener) {
        Observable<String> searchObservable = fromSearchView(search);
        handleSearchObserver(searchObservable, listener);
    }

    public RxSearchWidget(EditText search, RxSearchListener listener) {
        Observable<String> searchObservable = fromEditTextView(search);
        handleSearchObserver(searchObservable, listener);
    }

    public RxSearchWidget() {
    }

    public void textSearch(String keyword, RxSearchListener listener) {
        if (subject == null) {
            subject = PublishSubject.create();
        }
        subject.onNext(keyword);
        handleSearchObserver(subject, listener);
    }

    private void handleSearchObserver(Observable<String> searchObservable, final RxSearchListener listener) {
        Disposable d = searchObservable
                .debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                //.switchMapSingle((Function<String, SingleSource<ArrayList<BeatModel>>>) s -> null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
                /*.subscribe(result -> {
                    Log.e("Consumer", result);
                    if (listener != null) {
                        listener.onSearchTextChange(result);
                    } else {
                        Log.e("Consumer", "Listener not null");
                    }
                });*/
        disposable.add(d);

        DisposableObserver<ArrayList<GitMember>> observer = getSearchObserver();
    }

    private DisposableObserver<ArrayList<GitMember>> getSearchObserver() {
        return new DisposableObserver<ArrayList<GitMember>>() {
            @Override
            public void onNext(ArrayList<GitMember> contacts) {
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private Observable<String> fromEditTextView(EditText searchView) {
        final PublishSubject<String> subject = PublishSubject.create();
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                subject.onNext(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        return subject;
    }

    private Observable<String> fromSearchView(SearchView searchView) {

        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                subject.onNext(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                subject.onNext(text);
                return true;
            }
        });

        return subject;
    }

    public void clear() {
        if (disposable != null) {
            disposable.clear();
        }
    }

    public interface RxSearchListener {
        void onSearchTextChange(String query);
    }

}

