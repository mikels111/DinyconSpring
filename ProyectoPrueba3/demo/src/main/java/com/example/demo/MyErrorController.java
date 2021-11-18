package com.example.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model,HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        model.addAttribute("nombre", "Error");
        model.addAttribute("content", "../fragments/errorContent");

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
        
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("mensaje", "Página no encontrada");
            }
            if(statusCode == HttpStatus.FORBIDDEN.value()){
                model.addAttribute("mensaje", "Acceso denegado");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("mensaje", "Algo ha ido mal");
            }
        }
        return "index";
    }
    
}
