package br.com.fiap.megafarma.model.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ItemVendido {
	 @NotNull
	 private Long idVenda;
	 @NotNull
	 private Long idRemedio;
	 @Positive
	 @NotNull
	 private Integer Quantidade;
	 
	public ItemVendido() {
	
	}

	public ItemVendido(@NotNull Long idVenda, @NotNull Long idRemedio, @Positive @NotNull Integer quantidade) {
		this.idVenda = idVenda;
		this.idRemedio = idRemedio;
		Quantidade = quantidade;
	}
	
	public Long getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}
	public Long getIdRemedio() {
		return idRemedio;
	}
	public void setIdRemedio(Long idRemedio) {
		this.idRemedio = idRemedio;
	}
	public Integer getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		Quantidade = quantidade;
	}
	 
	 
	 
}
