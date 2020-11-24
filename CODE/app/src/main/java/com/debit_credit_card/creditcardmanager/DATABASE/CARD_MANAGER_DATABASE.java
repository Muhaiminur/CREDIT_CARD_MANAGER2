package com.debit_credit_card.creditcardmanager.DATABASE;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.debit_credit_card.creditcardmanager.DATABASE.DAO.CARD_DAO;
import com.debit_credit_card.creditcardmanager.DATABASE.TABLE.CARD_TABLE;

@Database(entities = {CARD_TABLE.class}, version = 1, exportSchema = false)
public abstract class CARD_MANAGER_DATABASE extends RoomDatabase {
    public abstract CARD_DAO card_dao();

    private static CARD_MANAGER_DATABASE INSTANCE;

    public static CARD_MANAGER_DATABASE getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CARD_MANAGER_DATABASE.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CARD_MANAGER_DATABASE.class, "cardmanager_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
