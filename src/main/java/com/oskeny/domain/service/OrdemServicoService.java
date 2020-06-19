package com.oskeny.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oskeny.domain.exception.NegocioExcepition;
import com.oskeny.domain.model.Cliente;
import com.oskeny.domain.model.OrdemServico;
import com.oskeny.domain.model.StatusOrdemServico;
import com.oskeny.domain.repository.ClienteRepository;
import com.oskeny.domain.repository.OrdemServicoRepository;

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
