package br.com.alura.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data = LocalDate.now();
	private BigDecimal valorTotal = BigDecimal.ZERO;
	@ManyToOne
	private Cliente cliente;
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens = new ArrayList<>();


	public Pedido() {
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void adicionarItem(ItemPedido item){
		item.setPedido(this);
		this.itens.add(item);
		this.valorTotal = this.valorTotal.add(item.getValor());

	}
}
