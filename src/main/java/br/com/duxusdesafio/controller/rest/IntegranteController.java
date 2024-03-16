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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/integrante")
public class IntegranteController {
    private final IntegranteService itService;

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<IntegranteView>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(itService.findAll());
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<IntegranteView> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(itService.findById(id));
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<IntegranteView> saveOne(@RequestBody IntegranteInput itInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itService.saveOne(itInput));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOne(@PathVariable Long id, @RequestBody IntegranteInput itInput) {
        itService.updateOne(id, itInput);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        itService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
