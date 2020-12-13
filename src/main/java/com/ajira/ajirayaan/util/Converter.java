package com.ajira.ajirayaan.util;

import com.ajira.ajirayaan.entity.AreaMap;
import com.ajira.ajirayaan.model.request.TerrainType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Convert have static methods to convert 2D map to string
 * and string to 2D map
 */
public class Converter {

    /**
     * Converting given 2D map into String format
     * @param areaMap 2D map
     * @return list of flat area map
     */
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

    /**
     * Converting rows of String to 2D map
     * @param rows list of flat area map
     * @return 2D area map
     */
    public static List<List<String>> stringToMap(List<AreaMap> rows){
        List<List<String>> map = new ArrayList<>();
        rows.forEach(row->{
            List<String> pos = Arrays.asList(row.getRow().split(","));
            map.add(pos);
        });
        return map;
    }

}
