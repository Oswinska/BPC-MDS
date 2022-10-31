package mds.streamingserver;

import mds.streamingserver.components.MyResourceHttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class WebControl
{

    private MyResourceHttpRequestHandler handler;

    @Autowired
    public WebControl(MyResourceHttpRequestHandler handler)
    {
        this.handler = handler;
    }
    private final static File mp4 = new File("path/to/file");


    @GetMapping("video")
    public String video()
    {
        return "videomp4";
    }

    @GetMapping(path = "file", produces = "video/mp4")
    @ResponseBody
    public FileSystemResource file()
    {
        return new FileSystemResource(mp4);
    }

    @GetMapping("byterange")
    public void byterange(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException
    {
        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, mp4);
        handler.handleRequest(request, reponse);
    }



    @GetMapping("index")
    public String index()
    {
        return "index";
    }

    @RequestMapping(value="/player", method = {RequestMethod.POST})
    public String player(
            Model model,
            @RequestParam String video_url,
            @RequestParam(defaultValue = "false") boolean muted,
            @RequestParam(defaultValue = "false") boolean autoplay,
            @RequestParam(defaultValue = "1000px") String width)
    {
        if (StringUtils.isEmpty(video_url))
            {
                model.addAttribute("error", "true");
            }
        model.addAttribute("video_url", video_url);
        model.addAttribute("muted", muted ? "true" : "");
        model.addAttribute("width", width);
        model.addAttribute("autoplay", autoplay);

        return "player";
    }
}
