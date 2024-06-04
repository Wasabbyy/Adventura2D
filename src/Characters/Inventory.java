package Characters;

import java.util.ArrayList;

public class Inventory {

    public ArrayList<String> items;
    public Inventory(){
        this.items = new ArrayList<>();

    }

    public void addItems(String item){
        if(item!=null) {
            items.add(item);
        }
    }
    public String listOfItems(){
        for (String item : items){

            System.out.println(item);
        }
        return"";
    }
}
