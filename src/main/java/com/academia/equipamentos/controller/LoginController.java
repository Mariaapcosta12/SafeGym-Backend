package com.academia.equipamentos.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    /**
     * Exibe a página de login.
     *
     * @param error   indica se houve erro de autenticação
     * @param logout  indica se o logout foi bem-sucedido
     * @param model   o modelo para passar informações para a view
     * @return o nome do template de login
     */
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Usuário ou senha inválidos!");
        }

        if (logout != null) {
            model.addAttribute("message", "Logout realizado com sucesso!");
        }

        return "login"; // Nome do template HTML (login.html)
    }

    /**
     * Roteamento para a página de login após autenticação.
     *
     * @param authentication objeto que contém os detalhes do usuário autenticado
     * @return redireciona para a página inicial ou a página desejada após o login.
     */
    @GetMapping("/login-success")
    public String loginSuccess(Authentication authentication) {
        // Aqui você pode acessar os detalhes do usuário autenticado, por exemplo:
        // String username = authentication.getName();

        // Redirecionar para a página inicial ou página após o login
        return "redirect:/home"; // Redireciona para uma página inicial ou página específica após o login
    }
}
