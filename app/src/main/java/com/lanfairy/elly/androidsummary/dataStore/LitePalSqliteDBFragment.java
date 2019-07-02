package com.lanfairy.elly.androidsummary.dataStore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanfairy.elly.androidsummary.dataStore.model.Book;
import com.lanfairy.elly.androidsummary.dataStore.util.UtilTools;
import com.lanfairy.elly.androidsummary.R;

import org.litepal.LitePal;

import java.util.List;

public class LitePalSqliteDBFragment extends Fragment {
    private static final String TAG = "LitePalSqliteDBFragment";

    public LitePalSqliteDBFragment() {
    }

    public static LitePalSqliteDBFragment getInstance() {
        return new LitePalSqliteDBFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_litepalsqliteda, container, false);
        rootView.findViewById(R.id.create_database).setOnClickListener(v -> {
            LitePal.getDatabase();
        });
        //添加
        rootView.findViewById(R.id.add_data).setOnClickListener(v -> {
            Book book = null;
            for (int i = 0; i < 10; i++) {
                book = new Book();
                double price = UtilTools.reserveDecimalDouble(Math.random() * 100 + 1, 2);
                int pages = (int) (Math.random() * 600) + 100;
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(pages);
                book.setPrice(price);
                book.setPress("Unknow");
                book.save();
            }
        });
//        异步保存
        rootView.findViewById(R.id.async_save_data).setOnClickListener(v -> {
           Book book = new Book();
            double price = 19.99;
            int pages = 635;
            book.setName("Just use saveAsync() instead of save()");
            book.setAuthor("Dan saveAsync");
            book.setPages(pages);
            book.setPrice(price);
            book.setPress("大英帝国图书出版社");
            book.saveAsync().listen(success -> {
                Log.i(TAG, "onCreateView: 异步保存数据成功");
            });
        });
        //更新
        rootView.findViewById(R.id.update_data).setOnClickListener(v -> {
            Book book = new Book();
            book.setPrice(20.99);
            book.setPress("中央人民教育出版社");
            book.update(1);
        });
//        删除
        rootView.findViewById(R.id.delete_data).setOnClickListener(v -> {
            LitePal.delete(Book.class, 2);
        });
        //查询
        rootView.findViewById(R.id.check_data).setOnClickListener(v -> {
            List<Book> bookList = LitePal.findAll(Book.class);
            for (Book book : bookList) {
                Log.i(TAG, "onCreateView: " + book.toString());
            }
        });
        //异步查询
        rootView.findViewById(R.id.async_check_data).setOnClickListener(v -> {
            LitePal.findAllAsync(Book.class).listen((List<Book> list) -> {
                Log.i(TAG, "onCreateView: 异步查询数据成功");
            });
        });

        return rootView;
    }
}
