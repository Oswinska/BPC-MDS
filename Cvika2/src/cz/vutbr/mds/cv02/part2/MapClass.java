package cz.vutbr.mds.cv02.part2;

import java.util.HashMap;
import java.util.Map;

public class MapClass
{
    private Map<Integer,String> pole = null;

    public MapClass()
    {
        pole = new HashMap<>();

    }

    public void store(int id, String value) throws ArrayStoreException
    {
        pole.put(id,value);
    }

    public String getValue(int id) throws NoSuchFieldException
    {
        return pole.get(id);
    }

    public void deleteKey(int id) throws NoSuchFieldException
    {
        pole.remove(id);
    }

    public int getSize()
    {
        return pole.size();
    }

    public void print()
    {
        for (int i: pole.keySet())
            {
                System.out.println("<"+ i +">-><" + pole.get(i) + ">");
            }
    }

}

