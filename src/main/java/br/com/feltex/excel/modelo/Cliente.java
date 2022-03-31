package br.com.feltex.excel.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Cliente {
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
}
