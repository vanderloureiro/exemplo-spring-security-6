package dev.vanderloureiro.exemplosecurity.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExemploController {

    @GetMapping("/publica")
    public ResponseEntity<String> rotaPublica() {
        return ResponseEntity.ok("Rota pública");
    }

    @GetMapping("/privada")
    public ResponseEntity<String> rotaPrivada() {
        return ResponseEntity.ok("Rota privada");
    }

}
