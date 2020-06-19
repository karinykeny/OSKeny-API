package com.oskeny.api.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oskeny.api.domain.exception.NegocioExcepition;
import com.oskeny.api.domain.model.Cliente;
import com.oskeny.api.domain.model.OrdemServico;
import com.oskeny.api.domain.model.StatusOrdemServico;
import com.oskeny.api.domain.repository.ClienteRepository;
import com.oskeny.api.domain.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public OrdemServico criar(OrdemServico os) {
		Cliente cliente = clienteRepository.findById(os.getCliente().getId())
				.orElseThrow(() -> new NegocioExcepition("Cliente não encontrado"));
		
		os.setCliente(cliente);
		os.setStatus(StatusOrdemServico.ABERTA);
		os.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(os);
	}
}
