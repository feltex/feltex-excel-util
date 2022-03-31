package br.com.feltex.excel;

import br.com.feltex.excel.modelo.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CriaArquivoExcelTest {

    private final List<Cliente> clientes = new ArrayList<>();

    @BeforeEach
    public void setup() {
        clientes.add(new Cliente(1, "Jose da Silva", "jose@feltex.com.br", "2199988-777441"));
        clientes.add(new Cliente(2, "Maria da Silva", "maria@feltex.com.br", "2155547-3341"));
        clientes.add(new Cliente(3, "Joana dos Santos", "joanas@feltex.com.br", "55224-147523"));
        clientes.add(new Cliente(4, "Joao da Silva", "joao@feltex.com.br", "55224-00000"));
    }


    @Test
    void criarArquivo() {
        var criaArquivoExcel = new CriaArquivoExcel();
        assertNotNull(criaArquivoExcel);
        criaArquivoExcel.criarArquivo("clientes.xlsx", clientes);
    }
}