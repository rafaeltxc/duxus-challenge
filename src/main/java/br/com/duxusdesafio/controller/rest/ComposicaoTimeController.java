package br.com.duxusdesafio.controller.rest;

import br.com.duxusdesafio.model.input.ComposicaoTimeInput;
import br.com.duxusdesafio.model.view.ComposicaoTimeView;
import br.com.duxusdesafio.service.ComposicaoTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/composicao")
public class ComposicaoTimeController {
    private final ComposicaoTimeService cpService;

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<ComposicaoTimeView>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cpService.findAll());
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<ComposicaoTimeView> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cpService.findById(id));
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<ComposicaoTimeView> saveOne(@RequestBody ComposicaoTimeInput cpInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cpService.saveOne(cpInput));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOne(@PathVariable Long id, @RequestBody ComposicaoTimeInput cpInput) {
        cpService.updateOne(id, cpInput);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        cpService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
