package com.example.lunchilicious;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ExItem.class}, version = 1, exportSchema = false)
public abstract class LunchiliciousDatabase extends RoomDatabase {
    private static LunchiliciousDatabase instance;

    public abstract MenuItemDao menuItemDao();

    private static final int NUM_THREADS = 2;
    public static ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUM_THREADS);

    public static synchronized LunchiliciousDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LunchiliciousDatabase.class, "Lunchilicious_Database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDb(instance).execute();
        }
    };

    /*private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute( () -> {
               MenuItemDao menuItemDao = instance.menuItemDao();

                menuItemDao.deleteAllItems();

                ArrayList<ExItem> menuItem = createMenuItems();
                menuItemDao.insertLst(menuItem);

            });
        }
    };

    private static ArrayList<ExItem> createMenuItems() {
            ArrayList<ExItem> items = new ArrayList<>();
            int i = 0;
            items.add(new ExItem(1000 + i++, "Hoagie", "BLT Hoagie", "Cold, Onion, lettuce, tomato", (float) 6.95));
            items.add(new ExItem(1000 + i++, "Hoagie", "Cheese Hoagie", "Cheese, mayos, lettuce, tomato", (float) 6.95));
            items.add(new ExItem(1000 + i++, "Hoagie", "Combo Hoagies", "Cold, Onion, lettuce, tomato", (float) 6.95));
            items.add(new ExItem(1000 + i++, "Hoagie", "Ham & Cheese", "Cold, union, lettuce, tomato", (float) 6.95));
            items.add(new ExItem(1000 + i++, "Hoagie", "Italian Hoagie", "Cheese, ham, hot pepper lettuce, tomato", (float) 6.95));
            items.add(new ExItem(1000 + i++, "Pizza", "Plain", "cheese and tomato", (float) 9.50));
            items.add(new ExItem(1000 + i++, "Pizza", "Tomato Pizza", "Cheese and a lot of tomato", (float) 6.95));
            items.add(new ExItem(1000 + i++, "Pizza", "House Special Pizza", "mushroom, green pepper, tomato", (float) 7.95));
            items.add(new ExItem(1000 + i++, "Pizza", "Round White Pizza", "American cheese, lettuce, tomato", (float) 9.95));
            items.add(new ExItem(1000 + i++, "Pizza", "Hot Wing Pizza", "chicken, hot sauce, lettuce, tomato", (float) 4.95));
            items.add(new ExItem(1000 + i++, "Side", "Fries", "large hot fries", (float) 2.95));
            items.add(new ExItem(1000 + i++, "Side", "Gravy Fries", "Fries with gravy on top", (float) 3.95));
            items.add(new ExItem(1000 + i++, "Side", "Cheese Fries", "Fries with melt cheese", (float) 4.95));
            items.add(new ExItem(1000 + i++, "Side", "Onion Rings", "Deep fried onion rings", (float) 3.95));
            items.add(new ExItem(1000 + i++, "Side", "Cheese Sticks", "Mozzarella cheese sticks", (float) 5.95));
            return items;
        }
    }*/
    private static class PopulateDb extends AsyncTask<Void, Void, Void> {
        private MenuItemDao menuItemDao;

        private PopulateDb(LunchiliciousDatabase db){
            menuItemDao = db.menuItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            menuItemDao.insert(new ExItem(1, "Hoagie", "BLT Hoagie", "Cold, Onion, lettuce, tomato", (float) 6.95));
            menuItemDao.insert(new ExItem(2, "Hoagie", "Cheese Hoagie", "Cheese, mayos, lettuce, tomato", (float) 6.95));
            menuItemDao.insert(new ExItem(3, "Hoagie", "Combo Hoagies", "Cold, Onion, lettuce, tomato", (float) 6.95));
            menuItemDao.insert(new ExItem(4, "Hoagie", "Ham & Cheese", "Cold, union, lettuce, tomato", (float) 6.95));
            menuItemDao.insert(new ExItem(5, "Hoagie", "Italian Hoagie", "Cheese, ham, hot pepper lettuce, tomato", (float) 6.95));
            menuItemDao.insert(new ExItem(6, "Pizza", "Plain", "cheese and tomato", (float) 9.50));
            menuItemDao.insert(new ExItem(7, "Pizza", "Tomato Pizza", "Cheese and a lot of tomato", (float) 6.95));
            menuItemDao.insert(new ExItem(8, "Pizza", "House Special Pizza", "mushroom, green pepper, tomato", (float) 7.95));
            menuItemDao.insert(new ExItem(9, "Pizza", "Round White Pizza", "American cheese, lettuce, tomato", (float) 9.95));
            menuItemDao.insert(new ExItem(10, "Pizza", "Hot Wing Pizza", "chicken, hot sauce, lettuce, tomato", (float) 4.95));
            menuItemDao.insert(new ExItem(11, "Side", "Fries", "large hot fries", (float) 2.95));
            menuItemDao.insert(new ExItem(12, "Side", "Gravy Fries", "Fries with gravy on top", (float) 3.95));
            menuItemDao.insert(new ExItem(13, "Side", "Cheese Fries", "Fries with melt cheese", (float) 4.95));
            menuItemDao.insert(new ExItem(14, "Side", "Onion Rings", "Deep fried onion rings", (float) 3.95));
            menuItemDao.insert(new ExItem(15, "Side", "Cheese Sticks", "Mozzarella cheese sticks", (float) 5.95));
            return null;
        }


    }
}
