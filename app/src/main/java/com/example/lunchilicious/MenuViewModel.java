package com.example.lunchilicious;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MenuViewModel extends AndroidViewModel {
    private MenuItemRepository repository;
    private LiveData<List<ExItem>> itemData;

    public MenuViewModel(@NonNull Application application) {
        super(application);
        repository = new MenuItemRepository(application);
        itemData = repository.getAllMenuItems();
    }
    public void update(ExItem menuItem){ repository.update(menuItem);}

    public void insert(ExItem menuItem){
        repository.insert(menuItem);
    }

    public void delete(ExItem menuItem){
        repository.delete(menuItem);
    }

    public void deleteAllItems(){
        repository.deleteAll();
    }

    public void updateMenuItems(){};

    public LiveData<List<ExItem>> getItemData(){
        return itemData;
    }

}
/*Reserve Code in case I need it in the future
public MutableLiveData<ArrayList<ExItem>> getMenuItemsLiveData() {
        if(itemData == null){
            itemData = new MutableLiveData<>();
            ArrayList<ExItem> items = new ArrayList<>();
            items.add(new ExItem(1, "Hoagie", "BLT Hoagie", "Cold, Onion, lettuce, tomato", (float) 6.95));
            items.add(new ExItem(2, "Hoagie", "Cheese Hoagie", "Cheese, mayos, lettuce, tomato", (float) 6.95));
            items.add(new ExItem(3, "Hoagie", "Combo Hoagies", "Cold, Onion, lettuce, tomato", (float) 6.95));
            items.add(new ExItem(4, "Hoagie", "Ham & Cheese", "Cold, union, lettuce, tomato", (float) 6.95));
            items.add(new ExItem(5, "Hoagie", "Italian Hoagie", "Cheese, ham, hot pepper lettuce, tomato", (float) 6.95));
            items.add(new ExItem(6, "Pizza", "Plain", "cheese and tomato", (float) 9.50));
            items.add(new ExItem(7, "Pizza", "Tomato Pizza", "Cheese and a lot of tomato", (float) 6.95));
            items.add(new ExItem(8, "Pizza", "House Special Pizza", "mushroom, green pepper, tomato", (float) 7.95));
            items.add(new ExItem(9, "Pizza", "Round White Pizza", "American cheese, lettuce, tomato", (float) 9.95));
            items.add(new ExItem(10, "Pizza", "Hot Wing Pizza", "chicken, hot sauce, lettuce, tomato", (float) 4.95));
            items.add(new ExItem(11, "Side", "Fries", "large hot fries", (float) 2.95));
            items.add(new ExItem(12, "Side", "Gravy Fries", "Fries with gravy on top", (float) 3.95));
            items.add(new ExItem(13, "Side", "Cheese Fries", "Fries with melt cheese", (float) 4.95));
            items.add(new ExItem(14, "Side", "Onion Rings", "Deep fried onion rings", (float) 3.95));
            items.add(new ExItem(15, "Side", "Cheese Sticks", "Mozzarella cheese sticks", (float) 5.95));
            lastId = items.size();
            vMenu = items;
            itemData.setValue(items);
            return itemData;
        }
        return itemData;
    }
    public MenuViewModel(){}

    public int getLastId(){
        return lastId;
    }

    public void addMenuItem(ExItem menuItem) {
        // add the menuItem to menuItems
        int Iid = menuItem.getId();
        String Itype = menuItem.getmType();
        String Iname = menuItem.getmName();
        String Idesc = menuItem.getmDescription();
        Float Iprice = menuItem.getmPrice();
        ExItem item = new ExItem(Iid, Itype, Iname, Idesc, Iprice);
        // put the updated list to your MutableLiveData;
        vMenu.add(menuItem);
        itemData.setValue(vMenu);

    }*/