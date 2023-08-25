package br.delivery.persistencia;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.delivery.entidades.ProdutoModels;

@Repository
public interface ProdutoRepositorio extends JpaRepository<ProdutoModels,UUID>{
    
}