package br.comp.zeus.controller;

import br.comp.zeus.model.Membro;
import br.comp.zeus.repository.MembroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/membros")
public class MembroController {

    @Autowired
    private MembroRepository membroRepository;

    private static String uploadDir = "uploads/";

    // Listar membros
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("membros", membroRepository.findAll());
        return "Membro/lista";
    }

    // Formulário de cadastro
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("membro", new Membro());
        return "Membro/form";
    }

    // Salvar membro
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Membro membro,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            return "Membro/form";
        }

        membroRepository.save(membro);
        return "redirect:/membros";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Membro membro = membroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("membro", membro);
        return "Membro/form";
    }

    // Excluir
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        membroRepository.deleteById(id);
        return "redirect:/membros";
    }
}
