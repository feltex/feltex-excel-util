package br.com.feltex.excel;

import br.com.feltex.excel.modelo.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LerArquivoExcel {

    public List<Cliente> lerArquivo(final String nomeArquivo) {
        log.info("Lendo arquivo {}", nomeArquivo);
        List<Cliente> clientes = new ArrayList<>();

        try (FileInputStream excelFile = new FileInputStream(nomeArquivo)) {
            var workbook = new XSSFWorkbook(excelFile);
            var primeiraAba = workbook.getSheetAt(0);

            int contadorLinha = 0;
            for (Row linha : primeiraAba) {
                if (++contadorLinha == 1) continue;
                var cliente = Cliente.builder()
                        .id((int) linha.getCell(0).getNumericCellValue())
                        .nome(linha.getCell(1).getStringCellValue())
                        .email(linha.getCell(2).getStringCellValue())
                        .telefone(linha.getCell(3).getStringCellValue())
                        .build();
                clientes.add(cliente);
                log.info("Lendo cliente {}", cliente);
            }

        } catch (FileNotFoundException e) {
            log.error("Arquivo não encontrado {}", nomeArquivo);
        } catch (IOException e) {
            log.error("Erro ao processar o arquivo {}", nomeArquivo);
        }
        log.info("Total de clientes lidos {}", clientes.size());
        return clientes;
    }
}

