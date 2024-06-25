package com.example.cadastroaluno.Service;

import com.example.cadastroaluno.DTO.AlunosDTO;
import com.example.cadastroaluno.Exepcion.NotfoundException;
import com.example.cadastroaluno.Models.AlunosModel;
import com.example.cadastroaluno.Repositorio.AlunosRepositori;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunosServicoRepositorio {

    @Autowired
    AlunosRepositori alunosRepositori;
    AlunosServicoRepositorio(AlunosRepositori alunosRepositori){
        this.alunosRepositori = alunosRepositori;
    }
    // Chama a lista de alunos
    public List<AlunosModel> todosAlunos(){
        return alunosRepositori.findAll();
    }
    public List<AlunosModel> bucarMatrucilaPendenste(){
        return alunosRepositori.buscarMatriculaPendente();

    }
    //Busca pela serie
    public List<AlunosModel> buscarPorSerie(String serieAno) {
        return alunosRepositori.findBySerieAno(serieAno);
    }

    public  AlunosModel umAluno(Long id){
        Optional<AlunosModel> alun0 = alunosRepositori.findById(id);
        return alun0.orElseThrow(() -> new NotfoundException("Não Encontrado"));

    }
    @Transactional(rollbackOn = Exception.class)
    public AlunosModel inserir(AlunosDTO alunosDTO){
        var alunosModel = new AlunosModel();
        //Transformando em Model
        BeanUtils.copyProperties(alunosDTO, alunosModel);
            if (alunosModel.getEmailResposavel() == null ||
                    (alunosModel.getEmailResposavel() != null && alunosModel.getEmailResposavel().length() < 2) ||
                    alunosModel.getCpfPai() == null ||
                    (alunosModel.getCpfPai() != null && alunosModel.getCpfPai().length() < 2) ||
                    alunosModel.getCpfMae() == null ||
                    (alunosModel.getCpfMae() != null && alunosModel.getCpfMae().length() < 2) ||
                    alunosModel.getRgMae() == null ||
                    (alunosModel.getRgMae() != null && alunosModel.getRgMae().length() < 2) ||
                    alunosModel.getRgPai() == null ||
                    (alunosModel.getRgPai() != null && alunosModel.getRgPai().length() < 2) ||
                    alunosModel.getEndereco() == null ||
                    (alunosModel.getEndereco() != null && alunosModel.getEndereco().length() < 2)) {
                alunosModel.setAlunoStatus("Pendente");
            } else {
                alunosModel.setAlunoStatus("Concluido");
            }
           return alunosRepositori.save(alunosModel);


    }
        // Ferifica se na alteração a matricula foi completa
    public AlunosModel alterar(AlunosModel alunosModel) {
        if ((alunosModel.getEmailResposavel() != null && alunosModel.getEmailResposavel().length() > 1) &&
                (alunosModel.getCpfPai() != null && alunosModel.getCpfPai().length() > 1) &&
                (alunosModel.getCpfMae() != null && alunosModel.getCpfMae().length() > 1) &&
                (alunosModel.getRgMae() != null && alunosModel.getRgMae().length() > 1) &&
                (alunosModel.getRgPai() != null && alunosModel.getRgPai().length() > 1) &&
                (alunosModel.getEndereco() != null && alunosModel.getEndereco().length() > 1)) {
            alunosModel.setAlunoStatus("Concluido");
        }
        return alunosRepositori.save(alunosModel);
    }

    public void excluir(Long id) {
        Optional<AlunosModel> alunoExist = alunosRepositori.findById(id);

        AlunosModel alunosModel = alunoExist.orElseThrow(() -> new NotfoundException("Aluno Sumido"));
        alunosRepositori.delete(alunosModel);
    }

}


