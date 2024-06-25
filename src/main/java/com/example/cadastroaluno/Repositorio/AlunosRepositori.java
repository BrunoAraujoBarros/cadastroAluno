package com.example.cadastroaluno.Repositorio;

import com.example.cadastroaluno.Models.AlunosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunosRepositori extends JpaRepository<AlunosModel, Long> {
    //Realiza a busca no banco de dados
    @Query("select s from AlunosModel s where s.emailResposavel is null or s.cpfPai is null " +
            "or s.cpfMae is null or s.rgPai is null or s.rgMae is null or s.endereco is null")
    List<AlunosModel> buscarMatriculaPendente();
    //Metodo que faz a busca
    List<AlunosModel> findBySerieAno(String serieAno);
    }

