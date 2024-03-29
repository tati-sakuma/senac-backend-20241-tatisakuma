package model.entity.vacina;

import java.time.LocalDate;

public class Vacina {
	private int id;
	private String nome;
	private Pais paisOrigem;
	private Pessoa pesquisadorResponsavel;
	private int estagio;
	private LocalDate dataInicioPesquisa;
	private double mediaAvaliacao;

	public Vacina() {
		super();
	}

	public Vacina(int id, String nome, Pais paisOrigem, Pessoa pesquisadorResponsavel, int estagio,
			LocalDate dataInicioPesquisa, double mediaAvaliacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.paisOrigem = paisOrigem;
		this.pesquisadorResponsavel = pesquisadorResponsavel;
		this.estagio = estagio;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.mediaAvaliacao = mediaAvaliacao;
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

	public Pais getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(Pais paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Pessoa getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pessoa pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public int getEstagio() {
		return estagio;
	}

	public void setEstagio(int estagio) {
		this.estagio = estagio;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public double getMediaAvaliacao() {
		return mediaAvaliacao;
	}

	public void setMediaAvaliacao(double mediaAvaliacao) {
		this.mediaAvaliacao = mediaAvaliacao;
	}

}
