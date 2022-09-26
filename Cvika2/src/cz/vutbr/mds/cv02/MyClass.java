package cz.vutbr.mds.cv02;

import java.util.ArrayList;

public class MyClass implements Isum
{
    private static int count = 0;

    private ArrayList<Integer> list = new ArrayList<Integer>();

    public static int getCount()
    {
        return count;
    }

    public static MyClass createUnited(MyClass prvni, MyClass druhy)
    {
        MyClass newObject = new MyClass();

        newObject.list.addAll(prvni.list);
        newObject.list.addAll(druhy.list);

        return newObject;
    }

    public MyClass()
    {
        count ++;
    }

    public MyClass(int...numbers) throws IllegalArgumentException
    {
        this();

        for (int i: numbers)
            {
                addInteger(i);
            }
    }

    public void addInteger(int i) throws IllegalArgumentException
    {
        if (i > 0)
            {
                list.add(i);
            }
        else
            {
                throw new IllegalArgumentException("Zadejte kladné číslo");
            }
    }

    public boolean integerExists(int i)
    {
        return list.contains(i);
    }

    public void print()
    {
        System.out.print("Pole (" + list.size() + ")" );

        for (int i: list)
            {
                System.out.print(i + " ");
            }
    }

    @Override
    public int sum()
    {
        int sum = 0;

        for (int i: list)
            {
                sum += i;
            }
        return sum;
    }

    @Override
    public String toString()
    {
        return "Pole o velikosti " + list.size() + " Se součtem " + sum();
    }
}
