package com.example.android.final_work.data;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FoodHistoryDao_Impl implements FoodHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FoodString> __insertionAdapterOfFoodString;

  private final EntityDeletionOrUpdateAdapter<FoodString> __deletionAdapterOfFoodString;

  public FoodHistoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFoodString = new EntityInsertionAdapter<FoodString>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `foodName` (`foodName`) VALUES (?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FoodString value) {
        if (value.foodName == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.foodName);
        }
      }
    };
    this.__deletionAdapterOfFoodString = new EntityDeletionOrUpdateAdapter<FoodString>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `foodName` WHERE `foodName` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FoodString value) {
        if (value.foodName == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.foodName);
        }
      }
    };
  }

  @Override
  public void insert(final FoodString name) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFoodString.insert(name);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final FoodString name) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfFoodString.handle(name);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<FoodString>> getAllRepos() {
    final String _sql = "SELECT * FROM foodName";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"foodName"}, false, new Callable<List<FoodString>>() {
      @Override
      public List<FoodString> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfFoodName = CursorUtil.getColumnIndexOrThrow(_cursor, "foodName");
          final List<FoodString> _result = new ArrayList<FoodString>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FoodString _item;
            _item = new FoodString();
            _item.foodName = _cursor.getString(_cursorIndexOfFoodName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
