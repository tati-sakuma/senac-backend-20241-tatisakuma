package model.entity.x1;

import java.time.LocalDate;

import model.entity.enums.x1.TipoPessoa;

public class Pessoa {
	private int id;
	private String nome;
	private String cpf;
	private String sexo;
	private LocalDate dataNascimento;
	private TipoPessoa tipo;
	
	
	public Pessoa() {
		super();
	}


	public Pessoa(int id, String nome, String cpf, String sexo, LocalDate dataNascimento, TipoPessoa tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.tipo = tipo;
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




}

