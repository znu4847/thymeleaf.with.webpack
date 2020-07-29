package znu.practice.ie.with.webpack.demo.controller.mv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
  @GetMapping("/index")
  public String index() {
    return "index";
  }
}