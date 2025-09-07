package com.supermarket.mvcsupermarket.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "Preço é obrigatório")
    @Pattern(regexp = "\\d+\\.\\d{2}", message = "Preço deve estar no formato decimal, por exemplo, 1234.56")
    private String preco;

    @NotBlank(message = "SKU é obrigatório")
    private String SKU;

    @NotBlank(message = "Data de fabricação é obrigatória")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Data de fabricação deve estar no formato yyyy-MM-dd")
    private String fabricacao;
}
