package com.oskeny.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.oskeny.api.domain.model.OrdemServico;
import com.oskeny.api.domain.repository.OrdemServicoRepository;
import com.oskeny.api.domain.service.OrdemServicoService;
import com.oskeny.api.model.OrdemServicoModel;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoModel criar(@Valid @RequestBody OrdemServico os) {
		return toModel(ordemServicoService.criar(os));
	}
	
	@GetMapping
	public List<OrdemServicoModel> listar() {
		return toCollectionModel(ordemServicoRepository.findAll());
	}
	
	@GetMapping("/{osId}")
	public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long osId) {
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(osId);
		
		if (ordemServico.isPresent()) {
			OrdemServicoModel model = toModel(ordemServico.get());
			return ResponseEntity.ok(model);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	private OrdemServicoModel toModel(OrdemServico os) {
		return modelMapper.map(os, OrdemServicoModel.class);
	}
	
	private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> osList){
		return osList.stream()
				.map(os -> toModel(os))
				.collect(Collectors.toList());
	}

}
