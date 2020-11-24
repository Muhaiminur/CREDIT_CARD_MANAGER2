package com.debit_credit_card.creditcardmanager.DATABASE.REPOSITORY;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.debit_credit_card.creditcardmanager.DATABASE.CARD_MANAGER_DATABASE;
import com.debit_credit_card.creditcardmanager.DATABASE.DAO.CARD_DAO;
import com.debit_credit_card.creditcardmanager.DATABASE.TABLE.CARD_TABLE;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CARD_REPOSITORY {
    CARD_DAO card_dao;

    public CARD_REPOSITORY(Application application) {
        CARD_MANAGER_DATABASE db = CARD_MANAGER_DATABASE.getDatabase(application);
        card_dao = db.card_dao();
    }

    public void insert_single_card_data(CARD_TABLE cardTable) {
        new insert_single_AsyncTask(card_dao).execute(cardTable);
    }

    private static class insert_single_AsyncTask extends AsyncTask<CARD_TABLE, Void, Void> {
        private CARD_DAO mAsyncTaskDao;

        insert_single_AsyncTask(CARD_DAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CARD_TABLE... params) {
            mAsyncTaskDao.insert_card(params[0]);
            return null;
        }
    }

    public void delete_card_data(String id) {
        new delete_single_AsyncTask(card_dao, id).execute("");
    }

    private static class delete_single_AsyncTask extends AsyncTask<String, Void, Void> {
        private CARD_DAO mAsyncTaskDao;
        String product_id;

        delete_single_AsyncTask(CARD_DAO dao, String i) {
            mAsyncTaskDao = dao;
            product_id = i;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.delete_card_single(product_id);
            return null;
        }
    }

    public void deleteall_card_data() {
        new deleteall_single_AsyncTask(card_dao).execute("");
    }

    private static class deleteall_single_AsyncTask extends AsyncTask<String, Void, Void> {
        private CARD_DAO mAsyncTaskDao;

        deleteall_single_AsyncTask(CARD_DAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.delete_card_all();
            return null;
        }
    }

    public LiveData<List<CARD_TABLE>> get_all_Card() throws ExecutionException, InterruptedException {
        Callable<LiveData<List<CARD_TABLE>>> callable = new Callable<LiveData<List<CARD_TABLE>>>() {
            @Override
            public LiveData<List<CARD_TABLE>> call() throws Exception {
                return card_dao.get_all_card();
            }
        };
        Future<LiveData<List<CARD_TABLE>>> future = Executors.newSingleThreadExecutor().submit(callable);
        return future.get();
    }

    public CARD_TABLE find_single_Card(String id) throws ExecutionException, InterruptedException {

        Callable<CARD_TABLE> callable = new Callable<CARD_TABLE>() {
            @Override
            public CARD_TABLE call() throws Exception {
                return card_dao.find_card_single(id);
            }
        };

        Future<CARD_TABLE> future = Executors.newSingleThreadExecutor().submit(callable);

        return future.get();
    }

    public void update_card_data(String id, String amount) {
        new update_card_AsyncTask(card_dao, id, amount).execute("");
    }

    private static class update_card_AsyncTask extends AsyncTask<String, Void, Void> {
        private CARD_DAO mAsyncTaskDao;
        String product_id;
        String amount;

        update_card_AsyncTask(CARD_DAO dao, String i, String amo) {
            mAsyncTaskDao = dao;
            product_id = i;
            amount = amo;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.update_card_amount(product_id, amount);
            return null;
        }
    }


    public void update_whole_card(CARD_TABLE card_table) {
        new update_wholecard_AsyncTask(card_dao, card_table).execute("");
    }

    private static class update_wholecard_AsyncTask extends AsyncTask<String, Void, Void> {
        private CARD_DAO mAsyncTaskDao;
        CARD_TABLE c;

        update_wholecard_AsyncTask(CARD_DAO dao, CARD_TABLE card_table) {
            mAsyncTaskDao = dao;
            c = card_table;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.update_whole_card(c);
            return null;
        }
    }
}
