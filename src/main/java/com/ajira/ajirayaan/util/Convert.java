package com.ajira.ajirayaan.util;

import com.ajira.ajirayaan.entity.AreaMap;
import com.ajira.ajirayaan.model.EnvironmentConfig;
import com.ajira.ajirayaan.model.TerrainType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Convert {

    public static List<AreaMap> mapToString(List<List<TerrainType>> areaMap){
        List<AreaMap> areaMapList = new ArrayList<>();
        for(int row = 0; row < areaMap.size(); row++){
            int rowSize = areaMap.get(row).size();
            String mapAsString = "";
            for(int col = 0; col < rowSize; col++){
                mapAsString += areaMap.get(row).get(col);
                mapAsString += ",";
            }
            AreaMap map = new AreaMap();
            map.setRowNo(row);
            map.setRowLimit(areaMap.size());
            map.setColLimit(rowSize);
            map.setRow(mapAsString);
            areaMapList.add(map);
        }
        return areaMapList;
    }

    public static List<List<String>> stringToMap(List<AreaMap> rows){
        List<List<String>> map = new ArrayList<>();
        rows.forEach(row->{
            List<String> pos = Arrays.asList(row.getRow().split(","));
            map.add(pos);
        });
        return map;
    }

}
