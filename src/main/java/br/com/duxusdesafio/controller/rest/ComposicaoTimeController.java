package br.com.duxusdesafio.controller.rest;

import br.com.duxusdesafio.model.input.ComposicaoTimeInput;
import br.com.duxusdesafio.model.view.ComposicaoTimeView;
import br.com.duxusdesafio.service.ComposicaoTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ComposicaoTime controlador
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/composicao")
public class ComposicaoTimeController {
    private final ComposicaoTimeService cpService;

    /**
     * Busca todos as composicoes na base de dados
     *
     * @return Todas as composicoes achadas
     */
    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<ComposicaoTimeView>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cpService.findAll());
    }

    /**
     * Busca uma unica composicao na base de dados com base no identificador dado
     *
     * @param id Itentificador unico
     * @return ComposicaoTime
     */
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<ComposicaoTimeView> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cpService.findById(id));
    }

    /**
     * Salva uma nova composicao na base de dados
     *
     * @param cpInput Composicao a ser salva
     * @return Composicao salva
     */
    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<ComposicaoTimeView> saveOne(@RequestBody ComposicaoTimeInput cpInput) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cpService.saveOne(cpInput));
    }

    @ResponseBody
    @PostMapping("/save-many")
    public ResponseEntity<List<ComposicaoTimeView>> saveMany(@RequestBody List<ComposicaoTimeInput> cpInputs) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cpService.saveMany(cpInputs));
    }

    /**
     * Atualiza uma composicao da base de dados com base no identificador dado
     *
     * @param id Identificador unico
     * @param cpInput Composicao com as devidas alteracoes
     * @return Status code No Content
     */
    @PutMapping("/{id}")
    public ResponseEntity updateOne(@PathVariable Long id, @RequestBody ComposicaoTimeInput cpInput) {
        cpService.updateOne(id, cpInput);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Deleta uma composicao da base de dados com base no idetificador dado
     *
     * @param id Identificador unico
     * @return Status code No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        cpService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
