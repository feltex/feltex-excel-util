package br.com.feltex.excel;

import br.com.feltex.excel.modelo.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class CriaArquivoExcel {

    public void criarArquivo(final String nomeArquivo, final List<Cliente> clientes) {
        log.info("Gerando o arquivo {}", nomeArquivo);

        try (var workbook = new XSSFWorkbook();
             var outputStream = new FileOutputStream(nomeArquivo)) {
            var planilha = workbook.createSheet("Lista de Clientes");
            int numeroDaLinha = 0;

            adicionarCabecalho(planilha, numeroDaLinha++);

            for (Cliente cliente : clientes) {
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula(linha, 0, cliente.getId());
                adicionarCelula(linha, 1, cliente.getNome());
                adicionarCelula(linha, 2, cliente.getEmail());
                adicionarCelula(linha, 3, cliente.getTelefone());
            }

            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            log.error("Arquivo não encontrado: {}", nomeArquivo);
        } catch (IOException e) {
            log.error("Erro ao processar o arquivo: {} ", nomeArquivo);
        }
        log.info("Arquivo gerado com sucesso!");
    }

    private void adicionarCabecalho(XSSFSheet planilha, int numeroLinha) {
        var linha = planilha.createRow(numeroLinha);
        adicionarCelula(linha, 0, "Id");
        adicionarCelula(linha, 1, "Nome");
        adicionarCelula(linha, 2, "Email");
        adicionarCelula(linha, 3, "Telefone");
    }

    private void adicionarCelula(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
    }

    private void adicionarCelula(Row linha, int coluna, int valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
    }
}
