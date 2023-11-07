/* VINICIUS GONTIJO SANTOS
 * RM550657
 * */
package br.com.fiap.megafarma.model.entity;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class Venda {
	private Long id;
	@NotNull
	private Long idCliente;
	@PastOrPresent
	private LocalDate dataDaVenda;
	
	public Venda() {
	
	}

	public Venda(Long id, @NotNull Long idCliente, @PastOrPresent LocalDate dataDaVenda) {
		this.id = id;
		this.idCliente = idCliente;
		this.dataDaVenda = dataDaVenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public LocalDate getDataDaVenda() {
		return dataDaVenda;
	}

	public void setDataDaVenda(LocalDate dataDaVenda) {
		this.dataDaVenda = dataDaVenda;
	}
}
