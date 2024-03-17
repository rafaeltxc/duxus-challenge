package br.com.duxusdesafio.controller.rest;

import br.com.duxusdesafio.config.ModelMapperCf;
import br.com.duxusdesafio.model.domain.Time;
import br.com.duxusdesafio.model.input.TimeInput;
import br.com.duxusdesafio.model.view.TimeView;
import br.com.duxusdesafio.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Time controlador
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/time")
public class TimeController {
    private final TimeService tmService;
    private final ModelMapperCf modelMapper;

    /**
     * Busca todos os times na base de dados
     *
     * @return Todas os times achados
     */
    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<TimeView>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tmService.findAll());
    }

    /**
     * Busca um unico time na base de dados com base no identificador dado
     *
     * @param id Itentificador unico
     * @return Time
     */
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<TimeView> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tmService.findById(id));
    }

    /**
     * Salva um novo time na base de dados
     *
     * @param tmInput Time a ser salvo
     * @return Time salvo
     */
    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<TimeView> saveOne(@RequestBody TimeInput tmInput) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tmService.saveOne(tmInput));
    }

    /**
     * Atualiza um time da base de dados com base no identificador dado
     *
     * @param id Identificador unico
     * @param tmInput Time com as devidas alteracoes
     * @return Status code No Content
     */
    @PutMapping("/{id}")
    public ResponseEntity updateOne(@PathVariable Long id, @RequestBody TimeInput tmInput) {
        tmService.updateOne(id, tmInput);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Deleta um time da base de dados com base no idetificador dado
     *
     * @param id Identificador unico
     * @return Status code No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        tmService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Busca o primeiro time com data correspondente a data dada
     *
     * @param data Data
     * @return Time
     */
    @ResponseBody
    @GetMapping("/time-da-data")
    public ResponseEntity<TimeView> timeDaDataOp(@RequestParam("data")
                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
        List<Time> tms = modelMapper.mapList(tmService.findAll(), Time.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(tmService.timeDaData(data, tms), TimeView.class));
    }
}
