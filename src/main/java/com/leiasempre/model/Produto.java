package com.leiasempre.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.Order;
import javax.validation.constraints.NotNull; //VERIFICAR NO CÓDIGO DO PROFESSOR QUAL A DEPÊNCIA CORRETA DO POM.XML
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 100)
	private String nome;
	
	private String sumario;
	private String qntdPaginas;
	private String categoria;
	private String imgURL;

	@NotNull(message = "Preço do produto não pode ser nulo")
	private double preco;

	@ManyToMany
	@JoinTable(name = "produto_fornecedor", joinColumns = @JoinColumn(name = "produtoId"), inverseJoinColumns = @JoinColumn(name = "fornecedorId"))
	private List<Fornecedor> supplier;
	
	@OneToMany(mappedBy = "id.produto")
	private Set<PedidoProduto> items = new HashSet<>();

	public Produto() {

	}

	public Produto(Long id, String nome, String sumario, String qntdPaginas, String categoria, double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.sumario = sumario;
		this.qntdPaginas = qntdPaginas;
		this.categoria = categoria;
		this.preco = preco;
	}
	
	
	public Produto(Long id, @Size(max = 100) String nome, String sumario, String qntdPaginas, String categoria,
			String imgURL, @NotNull(message = "Preço do produto não pode ser nulo") double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.sumario = sumario;
		this.qntdPaginas = qntdPaginas;
		this.categoria = categoria;
		this.imgURL = imgURL;
		this.preco = preco;
	}
	
	

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSumario() {
		return sumario;
	}

	public void setSumario(String sumario) {
		this.sumario = sumario;
	}

	public String getQntdPaginas() {
		return qntdPaginas;
	}

	public void setQntdPaginas(String qntdPaginas) {
		this.qntdPaginas = qntdPaginas;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public List<Fornecedor> getSupplier() {
		return supplier;
	}

	public void setSupplier(List<Fornecedor> supplier) {
		this.supplier = supplier;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
