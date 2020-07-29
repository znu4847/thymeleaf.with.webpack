package znu.practice.ie.with.webpack.demo.controller.mv;

import java.util.List;

import com.google.common.collect.Lists;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoPageController {

  @ModelAttribute("cmnAttr")
  public List<String> commonAttr() {
    return Lists.newArrayList("cmn attr sample-1", "cmn attr sample-2");
  }

  @GetMapping("/demo1")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    List<String> messages = Lists.newArrayList();
    messages.add("hi");
    messages.add("hello");
    messages.add("안녕");
    mv.addObject("messages", messages);
    mv.setViewName("demo/demo1");
    return mv;
  }

  @GetMapping("/demo2")
  public String index2(Model model) {
    List<String> messages = Lists.newArrayList();
    messages.add("fuck you");
    messages.add("mother fucker");
    messages.add("좆까");
    model.addAttribute("messages", messages);
    return "demo/demo1";
  }

}