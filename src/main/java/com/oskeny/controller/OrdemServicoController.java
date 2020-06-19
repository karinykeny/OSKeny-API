package com.oskeny.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oskeny.domain.model.OrdemServico;
import com.oskeny.domain.repository.OrdemServicoRepository;
import com.oskeny.domain.service.OrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico criar(@Valid @RequestBody OrdemServico os) {
		return ordemServicoService.criar(os);
	}
	
	@GetMapping
	public List<OrdemServico> listar() {
		return ordemServicoRepository.findAll();
	}
	
	@GetMapping("/{osId}")
	public ResponseEntity<OrdemServico> buscar(@PathVariable Long osId) {
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(osId);
		
		if (ordemServico.isPresent()) {
			return ResponseEntity.ok(ordemServico.get());
		}
		
		return ResponseEntity.notFound().build();
	}

}
