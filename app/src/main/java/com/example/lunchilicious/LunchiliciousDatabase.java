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
    private static final int NUM_THREADS = 1;

    public static ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUM_THREADS);

    public static LunchiliciousDatabase getDatabase(final Context context){
        synchronized (LunchiliciousDatabase.class){
            if(instance == null){
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        LunchiliciousDatabase.class,
                        "university_database")
                        .addCallback(roomCallBack).build();
            }
        }
        return instance;
    }
    public abstract MenuItemDao menuItemDao();


    public static synchronized LunchiliciousDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LunchiliciousDatabase.class, "lunchilicious_Database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack).build();
        }
        return instance;
    }

    //private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        //@Override
        //public void onCreate(@NonNull SupportSQLiteDatabase db){
            //super.onCreate(db);
            //new PopulateDb(instance).execute();
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                MenuItemDao menuItemDao = instance.menuItemDao();
                menuItemDao.deleteAllItems();
                ArrayList<ExItem> instructors = createMenu();
                for (ExItem instructor : instructors) {
                    menuItemDao.insert(instructor);
                }
            });
        };
    };

    /*private class PopulateDb extends AsyncTask<Void, Void, Void> {
        private MenuItemDao menuItemDao;

        private PopulateDb(LunchiliciousDatabase db){
            menuItemDao = db.menuItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            menuItemDao.insert(new ExItem("Hoagie", "BLT Hoagie", "Cold, Onion, lettuce, tomato", (float) 6.95));
            menuItemDao.insert(new ExItem("Hoagie", "Cheese Hoagie", "Cheese, mayos, lettuce, tomato", (float) 6.95));
            menuItemDao.insert(new ExItem( "Hoagie", "Combo Hoagies", "Cold, Onion, lettuce, tomato", (float) 6.95));
            menuItemDao.insert(new ExItem( "Hoagie", "Ham & Cheese", "Cold, union, lettuce, tomato", (float) 6.95));
            menuItemDao.insert(new ExItem( "Hoagie", "Italian Hoagie", "Cheese, ham, hot pepper lettuce, tomato", (float) 6.95));
            menuItemDao.insert(new ExItem( "Pizza", "Plain", "cheese and tomato", (float) 9.50));
            menuItemDao.insert(new ExItem( "Pizza", "Tomato Pizza", "Cheese and a lot of tomato", (float) 6.95));
            menuItemDao.insert(new ExItem( "Pizza", "House Special Pizza", "mushroom, green pepper, tomato", (float) 7.95));
            menuItemDao.insert(new ExItem( "Pizza", "Round White Pizza", "American cheese, lettuce, tomato", (float) 9.95));
            menuItemDao.insert(new ExItem( "Pizza", "Hot Wing Pizza", "chicken, hot sauce, lettuce, tomato", (float) 4.95));
            menuItemDao.insert(new ExItem( "Side", "Fries", "large hot fries", (float) 2.95));
            menuItemDao.insert(new ExItem( "Side", "Gravy Fries", "Fries with gravy on top", (float) 3.95));
            menuItemDao.insert(new ExItem( "Side", "Cheese Fries", "Fries with melt cheese", (float) 4.95));
            menuItemDao.insert(new ExItem( "Side", "Onion Rings", "Deep fried onion rings", (float) 3.95));
            menuItemDao.insert(new ExItem( "Side", "Cheese Sticks", "Mozzarella cheese sticks", (float) 5.95));
            return null;
        }
    }*/

    private static ArrayList<ExItem> createMenu()
    {
        ArrayList<ExItem> menu = new ArrayList<>();

        menu.add(new ExItem("Hoagie", "BLT Hoagie", "Cold, Onion, lettuce, tomato", (float) 6.95));
        menu.add(new ExItem("Hoagie", "Cheese Hoagie", "Cheese, mayos, lettuce, tomato", (float) 6.95));
        menu.add(new ExItem("Hoagie", "Combo Hoagies", "Cold, Onion, lettuce, tomato", (float) 6.95));
        menu.add(new ExItem("Hoagie", "Ham & Cheese", "Cold, union, lettuce, tomato", (float) 6.95));
        menu.add(new ExItem("Hoagie", "Italian Hoagie", "Cheese, ham, hot pepper lettuce, tomato", (float) 6.95));
        menu.add(new ExItem("Pizza", "Plain", "cheese and tomato", (float) 9.50));
        menu.add(new ExItem("Pizza", "Tomato Pizza", "Cheese and a lot of tomato", (float) 6.95));
        menu.add(new ExItem("Pizza", "House Special Pizza", "mushroom, green pepper, tomato", (float) 7.95));
        menu.add(new ExItem("Pizza", "Round White Pizza", "American cheese, lettuce, tomato", (float) 9.95));
        menu.add(new ExItem("Pizza", "Hot Wing Pizza", "chicken, hot sauce, lettuce, tomato", (float) 4.95));
        menu.add(new ExItem("Side", "Fries", "large hot fries", (float) 2.95));
        menu.add(new ExItem("Side", "Gravy Fries", "Fries with gravy on top", (float) 3.95));
        menu.add(new ExItem("Side", "Cheese Fries", "Fries with melt cheese", (float) 4.95));
        menu.add(new ExItem("Side", "Onion Rings", "Deep fried onion rings", (float) 3.95));
        menu.add(new ExItem("Side", "Cheese Sticks", "Mozzarella cheese sticks", (float) 5.95));

        return menu;
    }
}
