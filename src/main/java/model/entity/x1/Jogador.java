package model.entity.x1;

import java.time.LocalDate;

public class Jogador {
	private String nome;
	private String email;
	// private String senha;
	private LocalDate dataNascimento;
	private int totalPartidas;
	private double percentualVitorias;

	public Jogador() {
		super();
	}

	public Jogador(String nome, String email, LocalDate dataNascimento, int totalPartidas, double percentualVitorias) {
		super();
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.totalPartidas = totalPartidas;
		this.percentualVitorias = percentualVitorias;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getTotalPartidas() {
		return totalPartidas;
	}

	public void setTotalPartidas(int totalPartidas) {
		this.totalPartidas = totalPartidas;
	}

	public double getPercentualVitorias() {
		return percentualVitorias;
	}

	public void setPercentualVitorias(double percentualVitorias) {
		this.percentualVitorias = percentualVitorias;
	}

}
