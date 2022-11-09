package mds.streamingserver;

import mds.streamingserver.components.MyResourceHttpRequestHandler;
import mds.streamingserver.model.MovieLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import org.thymeleaf.util.StringUtils;
import org.jcodec.api.JCodecException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static mds.streamingserver.FilePaths.*;

@ServletComponentScan
@Controller
public class WebControl
{

    private MyResourceHttpRequestHandler handler;

    @Autowired
    public WebControl(MyResourceHttpRequestHandler handler)
    {
        this.handler = handler;
    }
    private final static File mp4 = new File("/path/to/file");


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
    public void byterange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, mp4);
        handler.handleRequest(request, response);
    }



    @GetMapping("index")
    public String index()
    {
        return "index";
    }

    @RequestMapping(value="/player", method = {RequestMethod.POST})
    public String player(
            Model model, @RequestParam String video_url,
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


    private final static String DASH_PATH = "/path/to/MPEG-DASH/";
    private final static String HLS_PATH = "/path/to/HLS/";

    @RequestMapping(value = {"/dash/{file}", "/hls/{file}", "/hls/{quality}/{file}"}, method = RequestMethod.GET)
    public void adaptive_streaming(
            @PathVariable String file,
            @PathVariable(value = "quality", required = false) String quality,
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        File STREAM_FILE = null;

        String handle = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        switch(handle)
            {
                case "/dash/{file}":
                    STREAM_FILE = new File(DASH_PATH + file);
                    break;
                case "/hls/{file}":
                    STREAM_FILE = new File(HLS_PATH + file);
                    break;
                case "hls/{quality}/{file}":
                    if(!StringUtils.isEmpty(quality))
                        {
                            STREAM_FILE = new File(HLS_PATH + quality + "/" + file);
                        }
                    break;
                default:
                    break;
            }

        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, STREAM_FILE);
        handler.handleRequest(request,response);
    }

    @RequestMapping(value = "dashPlayer", method = {RequestMethod.GET, RequestMethod.POST})
    public String dashPlayer(
            Model model,
            @RequestParam String url)
    {
        model.addAttribute("url", url);
        return "dashPlayer";
    }

    private MovieLibrary library;

    @RequestMapping(value = "/gallery")
    public String gallery(Model model) throws IOException, JCodecException
    {
        if (library == null)
            {
                library = new MovieLibrary(IMAGES_DIRECTORY, MP4_DIRECTORY, SUFFIX, 150);
            }
        model.addAttribute("library", library);
        System.out.print("hejlou");
        return "gallery";
    }

    @RequestMapping(value = {"/video/{file}"}, method = RequestMethod.GET)
    public String showVideo(@PathVariable("file") String file, Model model)
    {
        model.addAttribute("file", file);
        return "video";
    }

    @RequestMapping(value = {"/getvideo/{file}"}, method = RequestMethod.GET)
    public void getVideo(HttpServletRequest request, HttpServletResponse response, @PathVariable("file") String file) throws ServletException, IOException
    {
        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, new File(MP4_DIRECTORY, file));
        handler.handleRequest(request,response);
    }
}
