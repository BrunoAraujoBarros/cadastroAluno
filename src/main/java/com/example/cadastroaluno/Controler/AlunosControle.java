package com.example.cadastroaluno.Controler;

import com.example.cadastroaluno.DTO.AlunosDTO;
import com.example.cadastroaluno.Models.AlunosModel;
import com.example.cadastroaluno.Service.AlunosServicoRepositorio;
import com.example.cadastroaluno.Service.ClasesServicoRepositorio;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@NoArgsConstructor
@RestController
@RequestMapping("/api/alunos")
public class AlunosControle {

    ClasesServicoRepositorio clasesServicoRepositorio;
    AlunosServicoRepositorio alunosServicoRepositorio;
    @Autowired
    AlunosControle(AlunosServicoRepositorio alunosServicoRepositorio, ClasesServicoRepositorio clasesServicoRepositorio){
        this.alunosServicoRepositorio = alunosServicoRepositorio;
        this.clasesServicoRepositorio = clasesServicoRepositorio;
    }
    // Exibir alunos
    @GetMapping("/")
    public ResponseEntity<List<AlunosModel>> todosAlunos(){
        List<AlunosModel> alunos = alunosServicoRepositorio. todosAlunos();

        return ResponseEntity.ok(alunos);
    }
    // Retorna os alunos que no metodo POST ficaram pendentes
    @GetMapping("/matriculaPendente")
    public ResponseEntity<List<AlunosModel>> bucarMatrucilaPendenste(){
        List<AlunosModel> alunos = alunosServicoRepositorio.bucarMatrucilaPendenste();
        return ResponseEntity.ok(alunos);
    }
    //Pesquisa aluno por id
    @GetMapping("/{id}")
    public ResponseEntity<AlunosModel> umAluno(@PathVariable(value = "id")long id) {
        AlunosModel alunosModel = alunosServicoRepositorio.umAluno(id);
        return ResponseEntity.ok(alunosModel);
    }
    // Criação do aluno
    @PostMapping("/")
    public ResponseEntity<AlunosModel> inserir(@RequestBody @Valid AlunosDTO alunosDTO) {
        AlunosModel alunosModel = alunosServicoRepositorio.inserir(alunosDTO);

        return ResponseEntity.status(201).body(alunosModel);
    }
    @PutMapping("/")
    public AlunosModel alterar(@RequestBody AlunosModel alunosModel){
        return alunosServicoRepositorio.alterar(alunosModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable(value = "id") Long id){
        alunosServicoRepositorio.excluir(id);
       return ResponseEntity.ok().build();
    }
    //Array com os valores de busca
    private static final List<String> SERIES_VALIDAS = Arrays.asList(
            "maternal", "jardim1", "jardim2", "ano1", "ano2", "ano3", "ano4", "ano5",
            "ano6", "ano7", "ano8", "ano9"
    );
    //Faz a busca
    @GetMapping("/alunos")
    public List<AlunosModel> buscarAlunosPorSerie(@RequestParam String serieAno) {
        if (!SERIES_VALIDAS.contains(serieAno)) {
            throw new IllegalArgumentException("Série inválida: " + serieAno);
        }
        return alunosServicoRepositorio.buscarPorSerie(serieAno);
    }

}
