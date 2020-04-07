package com.example.lunchilicious;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class MenuViewModel extends ViewModel {
    ArrayList<ExItem> vMenu;
    public ArrayList<ExItem> getMenuItems() {
        if(vMenu == null){
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
            return items;
        }
        return vMenu;
    }
}
