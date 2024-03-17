package br.com.duxusdesafio.controller.rest;

import br.com.duxusdesafio.model.domain.Integrante;
import br.com.duxusdesafio.model.input.IntegranteInput;
import br.com.duxusdesafio.model.view.IntegranteView;
import br.com.duxusdesafio.service.IntegranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Integrante controlador
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/integrante")
public class IntegranteController {
    private final IntegranteService itService;

    /**
     * Busca todos os integrantes na base de dados
     *
     * @return Todas os integrantes achados
     */
    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<IntegranteView>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(itService.findAll());
    }

    /**
     * Busca um unico integrante na base de dados com base no identificador dado
     *
     * @param id Itentificador unico
     * @return Integrante
     */
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<IntegranteView> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(itService.findById(id));
    }

    /**
     * Salva um novo integrante na base de dados
     *
     * @param itInput Integrante a ser salvo
     * @return Integrante salvo
     */
    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<IntegranteView> saveOne(@RequestBody IntegranteInput itInput) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itService.saveOne(itInput));
    }

    /**
     * Atualiza um integrante da base de dados com base no identificador dado
     *
     * @param id Identificador unico
     * @param itInput Integrante com as devidas alteracoes
     * @return Status code No Content
     */
    @PutMapping("/{id}")
    public ResponseEntity updateOne(@PathVariable Long id, @RequestBody IntegranteInput itInput) {
        itService.updateOne(id, itInput);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Deleta um integrante da base de dados com base no idetificador dado
     *
     * @param id Identificador unico
     * @return Status code No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        itService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
