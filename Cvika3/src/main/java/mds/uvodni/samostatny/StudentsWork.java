package mds.uvodni.samostatny;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping
public class StudentsWork
{
    public ArrayList<Student> students = new ArrayList();
    {
        students.add(new Student("Fiala","Marek",211477,1998));
        students.add(new Student("Nanko","Matej",220823,1999));
        students.add(new Student("Viktora","David",226415,2000));
        students.add(new Student("Suchý","Daniel",227061,2000));
        students.add(new Student("Turčanová","Klára",227248,2000));
        students.add(new Student("Wagner","Filip",230351,2001));
        students.add(new Student("Čeleš","Peter",230540,2000));
        students.add(new Student("Texl","Filip",230688,2000));
        students.add(new Student("Čížek","Šimon",230792,1999));
        students.add(new Student("Děcký","Martin",230800,2000));
        students.add(new Student("Šťastná","Ariela",231151,2000));
        students.add(new Student("Klimková","Natálie",231242,2000));
        students.add(new Student("Kociská","Vanesa",231244,2001));
        students.add(new Student("Miková","Timea",231256,2001));
        students.add(new Student("Sabota","Dominik",231274,2000));
        students.add(new Student("Schiller","Vojtěch",231279,2000));
        students.add(new Student("Stejskal","Michal",231282,2000));
        students.add(new Student("Szüč","Martin",231284,2001));
        students.add(new Student("Šíma","Jindřich",231287,2001));
        students.add(new Student("Šrámek","Michal",231288,2001));
        students.add(new Student("Tománek","Stanislav",231293,2000));
        students.add(new Student("Veľký","Roman",231298,2000));
        students.add(new Student("Zeman","David",231304,2001));
        students.add(new Student("Žigrai","Martin",231307,2001));
        students.add(new Student("Kohout","David",195823,1996));
        students.add(new Student("Číka","Petr",10,1982));
        students.add(new Student("Masaryk","Tomáš",123456,1850));
    }

    @GetMapping("student")
    public String druhy(@RequestParam(defaultValue = "Stanislav") String name,
                             @RequestParam(defaultValue = "231293") int id)
    {
        return String.format("Student: <b>%s</b> ID: <b>%d</b>", name, id);
    }

    @GetMapping("students")
    public String treti(@RequestParam(defaultValue = "-1") int vutid)
    {
        if (vutid == -1)
            {
                String ret = "";
                for (Student student : students)
                    {
                        ret += "Student: <b>" + student.name + "</b>, ID: <b>" + student.id + "</b>.";
                    }
                return ret;
            } else
            {
                for (Student student : students)
                    {
                        if (student.getId() == vutid)
                            {
                                return "Student: <b>" + student.name + "</b>, ID: <b>" + student.id + "</b>.";
                            }

                    }
                return "Neexistuje takový student";
            }
    }
}
