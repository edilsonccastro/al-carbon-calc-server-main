package br.com.actionlabs.carboncalc.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.actionlabs.carboncalc.dto.*;
import br.com.actionlabs.carboncalc.service.OpenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/open")
@RequiredArgsConstructor
@Slf4j
public class OpenRestController {

  @Autowired
  private OpenService service;

  @PostMapping("start-calc")
  public ResponseEntity<StartCalcResponseDTO> startCalculation(
      @Valid @RequestBody StartCalcRequestDTO request) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(
        new StartCalcResponseDTO(
          service.saveUserInfo(request)));
  }

  @PutMapping("info")
  public ResponseEntity<UpdateCalcInfoResponseDTO> updateInfo(
      @Valid @RequestBody UpdateCalcInfoRequestDTO request) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(
        new UpdateCalcInfoResponseDTO(
          service.updateCalcInfo(request)));
  }

  @GetMapping("result/{id}")
  public ResponseEntity<CarbonCalculationResultDTO> getResult(
      @PathVariable String id) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(service.calculateCarbon(id));
  }
}
