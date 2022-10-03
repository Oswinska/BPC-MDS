package mds.uvodni;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Basic")
public class BasicController
{
    @GetMapping
    public String testBasic()
    { return "<b>Hello, World!</b>"; }

    @GetMapping("List")
    public List<String> testList()
    { return List.of("Hello", "World", "Lmao"); }

    @GetMapping("Param")
    public String testParam(String name)
    {
        return String.format("Hello %s", name);
    }

    @GetMapping("Param2")
    public String testParam2(@RequestParam(defaultValue = "Mašťák") String name)
    {
        return String.format("Hello %s", name);
    }

    @GetMapping("Param3")
    public String testParam3(@RequestParam(defaultValue = "Opisovač Jeden Githubový", name = "n") String name)
    {
        return String.format("Hello %s", name);
    }

    @GetMapping("Param4")
    public String testParam4(@RequestParam(defaultValue = "Opisovač Jeden Githubový", name = "n") String name,
                             @RequestParam(defaultValue = "1234") int id)
    {
        return String.format("Hello %s %d", name, id);
    }

    @GetMapping("Param5")
    public String testParam5(@RequestParam List<String> id)
    {
        String  n = "";
        for (String s : id)
            {
                n += s;
            }
        return n;
    }

    @GetMapping("Form")
    public String helloForm()
    {
        String html =
                "<html>" +
                    "<body>" +
                        "<form method='post' action='hello'>" +
                            "<input type='text' name='name' />" +
                            "<input type='submit' value='pozdrav'/>" +
                        "</form>" +
                    "</body>" +
                "</html>";
        return html;
    }

    @RequestMapping(value = "hello", method = {RequestMethod.POST, RequestMethod.GET})
    public String testParamForm(@RequestParam(defaultValue = "Mašťák") String name)
    { return String.format("Hello %s", name); }

}
