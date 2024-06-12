package com.example.cadastroaluno.Controler;

import com.example.cadastroaluno.DTO.AlunosDTO;
import com.example.cadastroaluno.Models.AlunosModel;
import com.example.cadastroaluno.Models.ClassesModel;
import com.example.cadastroaluno.Service.AlunosServicoRepositorio;
import com.example.cadastroaluno.Service.ClasesServicoRepositorio;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@NoArgsConstructor
@RestController
@RequestMapping("/api/alunos")
public class AlunosControle {
    @Autowired
    AlunosServicoRepositorio alunosServicoRepositorio;
    AlunosControle(AlunosServicoRepositorio alunosServicoRepositorio){
        this.alunosServicoRepositorio = alunosServicoRepositorio;
    }
    @Autowired
    ClasesServicoRepositorio clasesServicoRepositorio;
    AlunosControle(ClasesServicoRepositorio clasesServicoRepositorio){
        this.clasesServicoRepositorio = clasesServicoRepositorio;}


    @GetMapping("/")
    public ResponseEntity<List<AlunosModel>> todosAlunos(){
        List<AlunosModel> alunos = alunosServicoRepositorio.todosAlunos();

        return ResponseEntity.ok(alunos);
    }
    @GetMapping("/matriculaPendente")
    public ResponseEntity<List<AlunosModel>> bucarMatrucilaPendenste(){
        List<AlunosModel> alunos = alunosServicoRepositorio.bucarMatrucilaPendenste();
        return ResponseEntity.ok(alunos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AlunosModel> umAluno(@PathVariable(value = "id")long id) {
        AlunosModel alunosModel = alunosServicoRepositorio.umAluno(id);
        return ResponseEntity.ok(alunosModel);
    }

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
    private static final List<String> SERIES_VALIDAS = Arrays.asList(
            "maternal", "jardim1", "jardim2", "ano1", "ano2", "ano3", "ano4", "ano5",
            "ano6", "ano7", "ano8", "ano9"
    );
    @GetMapping("/alunos")
    public List<AlunosModel> buscarAlunosPorSerie(@RequestParam String serieAno) {
        if (!SERIES_VALIDAS.contains(serieAno)) {
            throw new IllegalArgumentException("Série inválida: " + serieAno);
        }
        return alunosServicoRepositorio.buscarPorSerie(serieAno);
    }

    @GetMapping("/verificar-cpf")
    public ResponseEntity<Map<String, Boolean>> verificarCpf(@RequestParam Long cpf) {
        boolean exists = alunosServicoRepositorio.existsByCpf(cpf);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
}
