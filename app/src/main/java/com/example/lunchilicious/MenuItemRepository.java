package com.example.lunchilicious;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MenuItemRepository {
    private MenuItemDao menuItemDao;
    private LiveData<List<ExItem>> menuItemData;
    private int last;

    public MenuItemRepository(Application application){
        LunchiliciousDatabase database = LunchiliciousDatabase.getInstance(application);
        menuItemDao = database.menuItemDao();
        menuItemData = menuItemDao.getAllMenuItems();
        //last = menuItemDao.getAllMenuItems().getValue().size() + 1;

    }
    //public int lastId = menuItemDao.getAllMenuItems().getValue().size() + 1;

    public void insert(ExItem menuItem){
        new InsertItemAsyncTask(menuItemDao).execute(menuItem);
    }

    public void delete(ExItem menuItem){
        new DeleteItemAsyncTask(menuItemDao).execute(menuItem);
    }

    public void deleteAll(){
        new DeleteAllItemAsyncTask(menuItemDao).execute();
    }

    public LiveData<List<ExItem>> getAllMenuItems(){
        return menuItemData;
    }



    private static class InsertItemAsyncTask extends AsyncTask<ExItem, Void, Void>{
        private MenuItemDao menuItemDao;

        private InsertItemAsyncTask(MenuItemDao menuItemDao){
            this.menuItemDao = menuItemDao;
        }
        @Override
        protected Void doInBackground(ExItem... menuItems){
            menuItemDao.insert(menuItems[0]);
            return null;
        }
    }

    private static class DeleteItemAsyncTask extends AsyncTask<ExItem, Void, Void>{
        private MenuItemDao menuItemDao;

        private DeleteItemAsyncTask(MenuItemDao menuItemDao){
            this.menuItemDao = menuItemDao;
        }
        @Override
        protected Void doInBackground(ExItem... menuItems){
            menuItemDao.delete(menuItems[0]);
            return null;
        }
    }

    private static class DeleteAllItemAsyncTask extends AsyncTask<Void, Void, Void>{
        private MenuItemDao menuItemDao;

        private DeleteAllItemAsyncTask(MenuItemDao menuItemDao){
            this.menuItemDao = menuItemDao;
        }
        @Override
        protected Void doInBackground(Void...voids){
            menuItemDao.deleteAllItems();
            return null;
        }
    }

}
