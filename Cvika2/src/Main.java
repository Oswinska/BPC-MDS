import cz.vutbr.mds.cv02.MyClass;

public class Main
{
    public static void main(String[] args)
    {
        MyClass prvni = new MyClass();
        MyClass druhy = new MyClass();
        MyClass treti = null;

        try
            {
                treti = new MyClass(1,2,3,4,5,6);

                prvni.addInteger(20);
                prvni.addInteger(30);
                prvni.addInteger(40);

                druhy.addInteger(5);
                druhy.addInteger(60);
                druhy.addInteger(15);
            }
        catch(IllegalArgumentException e)
            {
                System.err.println("Chyba :" + e.getMessage());
            }
        System.out.println("Počet tříd " + MyClass.getCount());
        System.out.println("Existuje 4: " + treti.integerExists(4));
        System.out.println("Prvni objekt " + prvni.toString());

        MyClass united = MyClass.createUnited(prvni, druhy);
        united.print();
    }
}