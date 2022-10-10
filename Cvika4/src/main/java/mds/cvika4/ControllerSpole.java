package mds.cvika4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerSpole
{
    @GetMapping("static")
    public String staticPage()
    {
        return "blank";
    }

    @GetMapping("dynamic")
    public String dynamicPage(Model model)
    {
        model.addAttribute("name","David");
        model.addAttribute("id","321987");
        return "dyn";
    }


    @GetMapping("myself")
    public String myselfPage(Model model)
    {
        return "myself";
    }

    @GetMapping("alice")
    public String alicePage(Model model)
    {
        model.addAttribute("name","Alice");
        model.addAttribute("id","123456789");
        return "alibob";
    }
    @GetMapping("bob")
    public String bobPage(Model model)
    {
        model.addAttribute("name","Bob");
        model.addAttribute("id","321654987");
        return "alibob";
    }
}
