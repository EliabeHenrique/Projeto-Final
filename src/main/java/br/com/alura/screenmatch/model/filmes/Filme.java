package br.com.alura.screenmatch.model.filmes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "filmes")
@ToString
@Getter
@NoArgsConstructor
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer duracaoEmMinutos;
    private Integer anoLancamento;
    private String genero;

    public Filme(DadosCadastroFilme dados) {
        this.nome = dados.nome();
        this.duracaoEmMinutos = dados.duracao();
        this.genero = dados.genero();
        this.anoLancamento = dados.ano();
    }

    public void atualizarDados(DadosAlteracaoFilme dados) {
        this.nome = dados.nome();
        this.duracaoEmMinutos = dados.duracao();
        this.anoLancamento = dados.ano();
        this.genero = dados.genero();
    }
}
