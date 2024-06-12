package com.example.cadastroaluno.DTO;

import jakarta.annotation.Nullable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class AlunosDTO {

    @NotNull(message = "CPF é obrigatório")
    @Digits(integer = 11, fraction = 0, message = "CPF deve ter 11 dígitos")
    private Long cpf;

    @NotBlank(message = "RG do Aluno é obrigatório")
    private String rgAluno;

    @NotBlank(message = "NIS é obrigatório")
    private String nis;

   // @NotBlank(message = "Número da Certidão é obrigatório")
    private String numeroCertidao;

    @NotBlank(message = "Série/Ano é obrigatório")
    private String serieAno;

    @NotBlank(message = "Nome do Aluno é obrigatório")
    private String nomeAluno;

    @NotBlank(message = "Naturalidade (Estado) é obrigatório")
    private String naturnalidadeEstado;

    @NotBlank(message = "Nacionalidade é obrigatório")
    private String nacionalidade;

    @NotBlank(message = "Tipo Sanguíneo é obrigatório")
    private String tipoSanguinio;

    @NotBlank(message = "Gênero é obrigatório")
    private String genero;

    //@NotBlank(message = "Data de Nascimento é obrigatória")
    private String dataNascimento;

    // Campos não obrigatórios
    private String nomePai;
    private String nomeMae;
    private String emailResposavel;
    private String cpfPai;
    private String cpfMae;
    private String rgPai;
    private String rgMae;
    private String endereco;
    private String alunoStatus;

}