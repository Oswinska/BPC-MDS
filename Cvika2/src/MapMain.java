import cz.vutbr.mds.cv02.part2.MapClass;

public class MapMain
{
    public static void main(String[] args)
    {
        MapClass mapa = new MapClass();
        try
            {
                mapa.store(100, "stovecka");
                mapa.store(1, "jedna");
            }
        catch(ArrayStoreException e)
            {
                System.err.print("Prvek již existuje");
            }

        mapa.getSize();

        try
            {
                mapa.deleteKey(2);
                mapa.deleteKey(1);
            }
            catch (NoSuchFieldException e)
        {
            System.err.print("Prvek Neexistuje");
        }


        mapa.store(200, "dvojstovka");

        mapa.print();
    }
}
