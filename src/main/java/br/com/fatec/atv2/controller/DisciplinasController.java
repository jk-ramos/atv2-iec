package br.com.fatec.atv2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/disciplinas")
@Tag(name = "Gestão de Matérias", description = "Conjunto de operações para administração do cadastro de matérias universitárias")    
public class DisciplinasController {

    private final Map<Integer, Map<String,String>> DisciplinasBD = new HashMap<>();

    public DisciplinasController(){
        DisciplinasBD.put(1, Map.of("id", "1", "name", "Integração e entrega contínua"));
        DisciplinasBD.put(2, Map.of("id", "2", "name", "Internet das coisas e Aplicações"));
    }

    // Consulta o conjunto completo de matérias
    @Operation(summary = "Recuperar relação de matérias", description = "Apresenta um inventário completo com todas as matérias atualmente registradas no banco de dados")
    @ApiResponse(responseCode = "200", description = "Consulta executada com êxito - Lista disponibilizada")
    @GetMapping
    public List<Map<String, String>> getAllDisciplinas() {
        return new ArrayList<>(DisciplinasBD.values());
    }

    // Localiza matéria específica mediante código identificador
    @Operation(summary = "Pesquisar matéria por código", description = "Realiza uma busca individualizada de matéria utilizando seu número de identificação único como referência")
    @ApiResponse(responseCode = "200", description = "Pesquisa concluída - Registro localizado")
    @Parameter(name = "id", description = "Código numérico exclusivo que identifica cada matéria", required = true)
    @GetMapping("/{id}")
    public Map<String, String> getDisciplineById(@PathVariable int id){
        return DisciplinasBD.get(id);
    }

    // Insere novo registro de matéria no sistema
    @Operation(summary = "Registrar nova matéria", description = "Executa a inclusão de um novo item no catálogo de matérias com base nos dados submetidos")
    @ApiResponse(responseCode = "201", description = "Criação bem-sucedida - Matéria incorporada ao acervo")
    @PostMapping
    public Map<String, String> createDiscipline(@RequestBody Map<String, String> discipline) {
        Map<String, String> newDiscipline = new HashMap<>();

        newDiscipline.put("id", String.valueOf(DisciplinasBD.size() + 1));
        newDiscipline.put("name", discipline.get("name"));

        DisciplinasBD.put(DisciplinasBD.size() + 1, newDiscipline);
        return newDiscipline;
    }
    
}