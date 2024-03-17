package br.com.duxusdesafio.controller.rest;

import br.com.duxusdesafio.config.ModelMapperCf;
import br.com.duxusdesafio.model.domain.Integrante;
import br.com.duxusdesafio.model.domain.Time;
import br.com.duxusdesafio.model.input.IntegranteInput;
import br.com.duxusdesafio.model.view.IntegranteView;
import br.com.duxusdesafio.model.view.TimeView;
import br.com.duxusdesafio.service.IntegranteService;
import br.com.duxusdesafio.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.http2.HpackDecoder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Integrante controlador
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/integrante")
public class IntegranteController {
    private final IntegranteService itService;
    private final TimeService tmService;
    private final ModelMapperCf modelMapper;

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

    /**
     * Busca integrante mais usado nas composicoes
     *
     * @param data1 Data de inicio
     * @param data2 Data final
     * @return Integrante
     */
    @ResponseBody
    @GetMapping("it-mais-usado")
    public ResponseEntity<IntegranteView> integranteMaisUsadoOp(
            @RequestParam("data1") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data1,
            @RequestParam("data2") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data2) {
        List<Time> tms = modelMapper.mapList(tmService.findAll(), Time.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(itService.integranteMaisUsado(
                        data1,
                        data2,
                        tms
                ), IntegranteView.class));
    }

    /**
     * Busca nome de todos os integrantes nas composicoes do periodo
     *
     * @param data1 Data de inicio
     * @param data2 Data final
     * @return Lista de Strings
     */
    @ResponseBody
    @GetMapping("its-nome")
    public ResponseEntity<List<String>> timeMaisComumOp(
            @RequestParam("data1") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data1,
            @RequestParam("data2") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data2) {
        List<Time> tms = modelMapper.mapList(tmService.findAll(), Time.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(itService.timeMaisComum(data1, data2, tms));
    }

    /**
     * Busca funcao mais comum das composicoes
     *
     * @param data1 Data de inicio
     * @param data2 Data final
     * @return String
     */
    @ResponseBody
    @GetMapping("funcao-mais-comum")
    public ResponseEntity<String> funcaoMaisComum(
            @RequestParam("data1") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data1,
            @RequestParam("data2") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data2) {
        List<Time> tms = modelMapper.mapList(tmService.findAll(), Time.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(itService.funcaoMaisComum(data1, data2, tms));
    }

    /**
     * Busca franquia mais famosa das composicoes
     *
     * @param data1 Data de inicio
     * @param data2 Data final
     * @return String
     */
    @ResponseBody
    @GetMapping("franquia-mais-famosa")
    public ResponseEntity<String> franquiaMaisFamosa(
            @RequestParam("data1") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data1,
            @RequestParam("data2") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data2) {
        List<Time> tms = modelMapper.mapList(tmService.findAll(), Time.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(itService.franquiaMaisFamosa(data1, data2, tms));
    }

    /**
     * Busca a franquia mais famosa e sua contagem
     *
     * @param data1 Data de inicio
     * @param data2 Data final
     * @return HashMap com contagem de ocorrencias
     */
    @ResponseBody
    @GetMapping("contagem-franquia")
    public ResponseEntity<Map<String, Long>> contagemPorFranquia(
            @RequestParam("data1") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data1,
            @RequestParam("data2") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data2) {
        List<Time> tms = modelMapper.mapList(tmService.findAll(), Time.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(itService.contagemPorFranquia(data1, data2, tms));
    }

    /**
     * Busca a funcao mais famosa e sua contagem
     *
     * @param data1 Data de inicio
     * @param data2 Data final
     * @return HashMap com contagem de ocorrencias
     */
    @ResponseBody
    @GetMapping("contagem-funcao")
    public ResponseEntity<Map<String, Long>> contagemPorFuncao(
            @RequestParam("data1") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data1,
            @RequestParam("data2") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data2) {
        List<Time> tms = modelMapper.mapList(tmService.findAll(), Time.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(itService.contagemPorFuncao(data1, data2, tms));
    }
}
