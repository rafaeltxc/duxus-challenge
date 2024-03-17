package br.com.duxusdesafio.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/doc")
public class Documentacao {

  @GetMapping
  public RedirectView index() {
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("/swagger-ui.html");
    return redirectView;
  }

}