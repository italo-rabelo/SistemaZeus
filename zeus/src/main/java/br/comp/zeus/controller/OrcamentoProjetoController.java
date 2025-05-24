package br.comp.zeus.controller;

import br.comp.zeus.model.OrcamentoProjeto;
import br.comp.zeus.model.StatusOrcamento;
import br.comp.zeus.repository.OrcamentoProjetoRepository;
import br.comp.zeus.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/orcamentos")
public class OrcamentoProjetoController {

    @Autowired
    private OrcamentoProjetoRepository orcamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Rota para lista todos os orçamentos
    @GetMapping
    public String listar(Model model) {

        List<OrcamentoProjeto> lista = orcamentoRepository.findAll();
        model.addAttribute("orcamentos", lista);
        return "Orcamento/lista";
    }

    // Mostrar formulário de novo orçamento
    @GetMapping("/novo")
    public String novo(Model model) {

        model.addAttribute("orcamento", new OrcamentoProjeto());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("statusOrcamento", StatusOrcamento.values());
        return "Orcamento/form";
    }

    // Salvar novo orçamento ou edição e redirecionar para a listagem de orçamentos
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute OrcamentoProjeto orcamento, Model model) {
        orcamentoRepository.save(orcamento);
        model.addAttribute("mensagem", "Orçamento salvo com sucesso.");
        return "redirect:/orcamentos";
    }

    // Editar orçamento
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable long id, Model model) {

        OrcamentoProjeto orcamento = orcamentoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("orcamento", orcamento);
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("statusOrcamento", StatusOrcamento.values());
        return "Orcamento/form";
    }

    // Excluir orçamento
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable long id, RedirectAttributes redirectAttributes) {

        orcamentoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Orçamento excluído com sucesso.");
        return "redirect:/orcamentos";
    }

}
