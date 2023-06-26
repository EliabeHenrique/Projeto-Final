package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.model.filmes.DadosAlteracaoFilme;
import br.com.alura.screenmatch.model.filmes.DadosCadastroFilme;
import br.com.alura.screenmatch.model.filmes.Filme;
import br.com.alura.screenmatch.model.filmes.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filmes")
public class FilmesController {
    @Autowired
    private FilmeRepository repository;
    @GetMapping("/formulario")
    public String carregarFormulario(Long id, Model model) {
        if (id != null) {
            var filme = repository.getReferenceById(id);
            model.addAttribute("filme",filme);
        }
        return "filmes/formulario";
    }
    @GetMapping
    public String carregarLista(Model model) {
        model.addAttribute("lista",repository.findAll());
        return "filmes/listaDeFilmes";
    }
    @PostMapping
    @Transactional
    public String cadastrarFilme(DadosCadastroFilme dados) {
        var filme = new Filme(dados);
        repository.save(filme);

        return "redirect:/filmes";
    }

    @PutMapping
    @Transactional
    public String alterarFilme(DadosAlteracaoFilme dados) {
        var filme = repository.getReferenceById(dados.id());

        filme.atualizarDados(dados);

        return "redirect:/filmes";
    }
    @DeleteMapping
    @Transactional
    public String removeFilme(Long id) {
        System.out.println(id);
        repository.deleteById(id);
        return "redirect:/filmes";
    }
}
