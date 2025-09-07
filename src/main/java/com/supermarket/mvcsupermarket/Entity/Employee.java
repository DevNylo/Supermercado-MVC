package com.supermarket.mvcsupermarket.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Data de nascimento é obrigatória")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Data de nascimento deve estar no formato yyyy-MM-dd")
    private String data_nascimento;

    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "Cargo é obrigatório")
    private String cargo;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @NotBlank(message = "Salário é obrigatório")
    @Pattern(regexp = "\\d+\\.\\d{2}", message = "Salário deve estar no formato decimal, por exemplo, 1234.56")
    private String salario;

    @NotBlank(message = "Departamento é obrigatório")
    private String departamento;

    @NotBlank(message = "Data de contratação é obrigatória")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Data de contratação deve estar no formato yyyy-MM-dd")
    private String data_contratacao;

    @NotBlank(message = "Horário de trabalho é obrigatório")
    private String horario_trabalho;
}
