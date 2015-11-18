package com.haikuowuya.sport.util;

import com.haikuowuya.sport.model.GymItem;

import java.util.LinkedList;
import java.util.List;

/**
 * 自己建造测试数据
 */
public class DataUtils
{
    public static List<GymItem> genMockGymItems()
    {
        List<GymItem> gymItems = new LinkedList<>();
        for (int i = 0; i < 10; i++)
        {
            GymItem gymItem = new GymItem();
            gymItem.gymName = "石路健身中心";
            gymItem.perPrice = "62";
            gymItem.rate = "5";
            gymItem.circleBusiness="李公堤南 500m";
            gymItem.usableCount = "2";
            gymItems.add(gymItem);
        }
        return gymItems;
    }
}
