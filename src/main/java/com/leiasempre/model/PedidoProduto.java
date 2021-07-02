package com.leiasempre.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.leiasempre.model.PK.PedidoProdutoPK;

@Entity
@Table(name = "pedido_produto")
public class PedidoProduto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PedidoProdutoPK id = new PedidoProdutoPK();
	
	private Integer quantity;
	private Double price;
	
	
	
	public PedidoProduto() {
		
	}

	public PedidoProduto(PedidoProdutoPK id) {
		super();
		this.id = id;
	}
	

	public PedidoProduto(Pedido pedido, Produto produto, Integer quantity, Double preco) {
		id.setPedido(pedido);
		id.setProduto(produto);
		this.quantity = quantity;
		this.price = preco;
	}
	
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public double getSubTotal() {
		return price * quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoProduto other = (PedidoProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
