package controllers;

import dtos.CalcadaDTO;
import services.CalcadasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/calcadas")
public class CalcadasController {
    private final CalcadasService service;

    public CalcadasController(CalcadasService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CalcadaDTO>> porRua(@RequestParam("rua") String rua) {
        return ResponseEntity.ok(service.buscarPorRua(rua));
    }
}
