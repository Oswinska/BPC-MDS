package mds.uvodni.samostatny;


public class Student
{
    String surname = "";
    String name = "";
    int id = 0;
    int year = 0;

    public Student(String surname,String name,int id, int year)
    {
        this.surname= surname;
        this.name = name;
        this.id = id;
        this.year = year;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }
}
