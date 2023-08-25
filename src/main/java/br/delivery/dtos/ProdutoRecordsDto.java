package br.delivery.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRecordsDto(@NotBlank String nome,@NotNull BigDecimal valor) {
}
