package br.com.duxusdesafio.controller.rest;

import br.com.duxusdesafio.model.input.TimeInput;
import br.com.duxusdesafio.model.view.TimeView;
import br.com.duxusdesafio.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/time")
public class TimeController {
    private final TimeService tmService;

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<TimeView>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tmService.findAll());
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<TimeView> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tmService.findById(id));
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<TimeView> saveOne(@RequestBody TimeInput tmInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tmService.saveOne(tmInput));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOne(@PathVariable Long id, @RequestBody TimeInput tmInput) {
        tmService.updateOne(id, tmInput);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        tmService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
