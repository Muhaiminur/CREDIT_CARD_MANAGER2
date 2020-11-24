package com.debit_credit_card.creditcardmanager.DATABASE.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.debit_credit_card.creditcardmanager.DATABASE.TABLE.CARD_TABLE;

import java.util.List;

@Dao
public interface CARD_DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert_card(CARD_TABLE card_table);

    @Query("DELETE FROM CARD_TABLE WHERE card_id = :card_id")
    void delete_card_single(String card_id);

    @Query("DELETE FROM card_table")
    void delete_card_all();

    @Query("SELECT * from card_table ORDER BY card_id ASC")
    LiveData<List<CARD_TABLE>> get_all_card();

    @Query("SELECT * FROM card_table WHERE card_id =:c")
    CARD_TABLE find_card_single(String c);

    @Query("UPDATE card_table SET card_amount=:new_amount WHERE card_id = :card_id")
    void update_card_amount(String card_id, String new_amount);

    @Update
    void update_whole_card(CARD_TABLE card_table);
}
