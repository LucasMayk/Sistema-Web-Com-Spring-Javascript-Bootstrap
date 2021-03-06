package com.algaworks.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.helper.cerveja.CervejasQueries;

// Interface que implementa outras interfaces - Repositório Cevejas.
@Repository
public interface Cervejas extends JpaRepository<Cerveja, Long>, CervejasQueries {

}
