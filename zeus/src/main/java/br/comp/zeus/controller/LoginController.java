package br.comp.zeus.controller;

import br.comp.zeus.model.Usuario;
import br.comp.zeus.repository.UsuarioRepository;
import br.comp.zeus.service.CookieService;
import br.comp.zeus.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class LoginController {

    // Armazena codigos para recuperaçao de email
    private Map<String,String> codigos = new HashMap<>();


    @Autowired
    private EmailService emailService;


    @Autowired
    private UsuarioRepository ur;


    @GetMapping("/login")
    public String login(){
        return "Login/login";
    } //////


    @GetMapping("/")
    public String dashboard(Model model, HttpServletRequest request) throws UnsupportedEncodingException{
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        return "Login/index";//////
    }


    @PostMapping("/logar")
    public String loginUsuario(Usuario usuario, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
        Usuario usuarioLogado = this.ur.login(usuario.getEmail(), usuario.getSenha());
        if(usuarioLogado != null) {
            CookieService.setCookie(response, "usuarioId", String.valueOf(usuarioLogado.getId()), 10000);
            CookieService.setCookie(response, "nomeUsuario", String.valueOf(usuarioLogado.getNome()), 10000);
            return "redirect:/";
        }

        model.addAttribute("erro", "E-mail e(ou) senha inválido(s).");
        return "Login/login"; //////
    }


    @GetMapping ("/sair")
    public String sair(HttpServletResponse response) throws UnsupportedEncodingException {
        CookieService.setCookie(response, "nomeUsuario", "", 0);
        return "Login/login";///////
    }


    @GetMapping("/cadastroUsuario")
    public String cadastro(){
        return "Login/cadastro";
    }//////////


    @RequestMapping(value = "/cadastroUsuario", method = RequestMethod.POST)
    public String cadastroUsuario(@Valid Usuario usuario, BindingResult result){

        if(result.hasErrors()){
            return "redirect:/cadastroUsuario";
        }

        ur.save(usuario);
        return "redirect:/login";
    }


    @GetMapping("/recuperarSenha")
    public String formEsqueciSenha() {
        return "Login/recuperar-senha"; ////////
    }


    @PostMapping("/enviarCodigo")
    public String enviarCodigo(@RequestParam String email, Model model, HttpSession session) {

        Usuario usuario = ur.findByEmail(email);

        if (usuario == null){
            model.addAttribute("erro", "E-mail nao encontrado.");
            return "Login/recuperar-senha";////////
        }

        String codigo = String.format("%06d", new Random().nextInt(1_000_000));
        codigos.put(email,codigo);
        emailService.enviarCodigoRecuperacao(email, codigo);
        session.setAttribute("codigoRecuperacao", codigo);
        session.setAttribute("emailRecuperacao", email);

        model.addAttribute("email", email);
        return "Login/verificar-codigo";///////
    }


    @GetMapping("/verificarCodigo")
    public String verificarCodigo(@RequestParam String email, @RequestParam String codigo, Model model) {

        String correto = codigos.get(email);
        if (correto != null && correto.equals(codigo)) {
            model.addAttribute("email", email);
            return "Login/nova-senha";/////////////
        }
        model.addAttribute("erro", "Codigo invalido.");
        model.addAttribute("email", email);
        return "Login/verificar-codigo";///////////
    }

    @PostMapping("/verificarCodigo")
    public String verificarCodigo(@RequestParam String codigo, HttpSession session, Model model) {

        String codigoEnviado = (String) session.getAttribute("codigoRecuperacao");

        if (codigoEnviado != null && codigoEnviado.equals(codigo)) {
            return "Login/nova-senha";
        } else {
            model.addAttribute("erro", "Codigo invalido.");
            return "Login/verificar-codigo";/////////
        }
    }


    @PostMapping("/novaSenha")
    public String novaSenha(@RequestParam String email, @RequestParam String senha, @RequestParam String confirmarSenha, Model model, HttpSession session) {

        if (!senha.equals(confirmarSenha)) {
            model.addAttribute("erro", "As senhas não coincidem.");
            model.addAttribute("email", email);
            return "Login/nova-senha";/////////////////
        }

        String emailSessao = (String) session.getAttribute("emailRecuperacao");
        Usuario usuario = ur.findByEmail(emailSessao);
        System.out.println("Nome do usuario associado ao email: " + usuario.getNome());
        usuario.setSenha(senha);
        ur.save(usuario);
        codigos.remove(email);
        return "redirect:/login";
    }


}
