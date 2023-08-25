package br.delivery.controllers;

import br.delivery.dtos.ProdutoRecordsDto;
import br.delivery.entidades.ProdutoModels;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.delivery.persistencia.ProdutoRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProdutoController {

    @Autowired
    ProdutoRepositorio produtoRepositorio; // Ponto de injeção


    @GetMapping("/produto")
    public ResponseEntity<List<ProdutoModels>> getAllProduto(){
        List<ProdutoModels> produtoList = produtoRepositorio.findAll();
        if(!produtoList.isEmpty()) {
            for(ProdutoModels produto : produtoList) {
                UUID id = produto.getIdProduto();
                produto.add(linkTo(methodOn(ProdutoController.class).getOneProduto(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoList);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Object> getOneProduto(@PathVariable(value="id") UUID id){
        Optional<ProdutoModels> produto0 = produtoRepositorio.findById(id);
        if(produto0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        produto0.get().add(linkTo(methodOn(ProdutoController.class).getAllProduto()).withRel("Products List"));
        return ResponseEntity.status(HttpStatus.OK).body(produto0.get());
    }

    @PostMapping("/produto")
    public ResponseEntity<ProdutoModels> saveProduto(@RequestBody @Valid ProdutoRecordsDto produtoRecordDto) {
        var produtoModels = new ProdutoModels();
        BeanUtils.copyProperties(produtoRecordDto, produtoModels);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepositorio.save(produtoModels));
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable(value="id") UUID id) {
        Optional<ProdutoModels> produto0 = produtoRepositorio.findById(id);
        if(produto0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        produtoRepositorio.delete(produto0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ProdutoRecordsDto produtoRecordDto) {
        Optional<ProdutoModels> produto0 = produtoRepositorio.findById(id);
        if(produto0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var produtoModels = produto0.get();
        BeanUtils.copyProperties(produtoRecordDto, produtoModels);
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepositorio.save(produtoModels));
    }
}
