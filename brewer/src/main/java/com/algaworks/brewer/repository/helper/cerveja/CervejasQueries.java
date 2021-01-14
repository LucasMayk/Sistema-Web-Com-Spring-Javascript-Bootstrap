package com.algaworks.brewer.repository.helper.cerveja;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.brewer.dto.CervejaDTO;
import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.filter.CervejaFilter;

public interface CervejasQueries {

	// List para escrever uma consultar Bean do pesquisar - Page. 
	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
	
	// List para listar na novaVenda.html.
	public List<CervejaDTO> porSkuOuNome(String skuOuNome);
	
}
