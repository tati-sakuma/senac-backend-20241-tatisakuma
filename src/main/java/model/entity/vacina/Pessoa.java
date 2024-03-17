package model.entity.vacina;

import java.time.LocalDate;
import java.util.List;

import model.entity.enums.x1.TipoPessoa;

public class Pessoa {
	private int id;
	private String nome;
	private String cpf;
	private String sexo;
	private LocalDate dataNascimento;
	private TipoPessoa tipo;
	private List <Vacinacao> vacinacoes;
	
	
	public Pessoa() {
		super();
	}


	public Pessoa(int id, String nome, String cpf, String sexo, LocalDate dataNascimento, TipoPessoa tipo,
			List<Vacinacao> vacinacoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.tipo = tipo;
		this.vacinacoes = vacinacoes;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public TipoPessoa getTipo() {
		return tipo;
	}


	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}


	public List<Vacinacao> getVacinacoes() {
		return vacinacoes;
	}


	public void setVacinacoes(List<Vacinacao> vacinacoes) {
		this.vacinacoes = vacinacoes;
	}
}

