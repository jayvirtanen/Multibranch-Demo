package com.sample;

import com.sample.model.LiquorType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasun on 5/24/17.
 */
public class ListService {

    public List getAvailableTypes(ItemType type){

        List types = new ArrayList();

        if(type.equals(LiquorType.BIRDS)){
            types.add("Ducks");
            types.add(("Chickens"));

        }else if(type.equals(LiquorType.FISH)){
            types.add("Salmon");
            types.add("Cod");

        }else if(type.equals(LiquorType.MAMMALS)){
            types.add("Cows");

        }else {
            brands.add("No Brand Available");
        }
    return brands;
    }
}
