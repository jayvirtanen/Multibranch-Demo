package com.sample;

import com.sample.model.ItemType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasun on 5/24/17.
 */
public class ListService {

    public List getAvailableTypes(ItemType type){

        List types = new ArrayList();

        if(type.equals(ItemType.BIRDS)){
            types.add("Ducks");
            types.add(("Chickens"));

        }else if(type.equals(ItemType.FISH)){
            types.add("Salmon");
            types.add("Cod");

        }else if(type.equals(ItemType.MAMMALS)){
            types.add("Cows");

        }else {
            types.add("No Brand Available");
        }
    return types;
    }
}
