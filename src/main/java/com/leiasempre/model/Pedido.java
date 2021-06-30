package com.leiasempre.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant pagamento;
	//private Instant envio;
	//private Instant entrega;
	//private Instant cancelado;
	
	@ManyToOne
	@JoinColumn(name = "clientId")
	private Usuario client;
	
	
	public Pedido() {
	
	}
	
	
	public Pedido(Long id, Instant pagamento, Usuario client) {
		super();
		this.id = id;
		this.pagamento = pagamento;
		this.client = client;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Instant getPagamento() {
		return pagamento;
	}


	public void setPagamento(Instant pagamento) {
		this.pagamento = pagamento;
	}
	

	public Usuario getClient() {
		return client;
	}


	public void setClient(Usuario client) {
		this.client = client;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
		
	

}
 