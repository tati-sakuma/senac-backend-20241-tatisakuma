package model.seletor.vacina;

public class VacinacaoSeletor extends BasePaginacaoSeletor{
	private String nomePessoa;
	private String nomeVacina;
	
	public VacinacaoSeletor() {
		super();
	}
	
	public VacinacaoSeletor(String nomePessoa, String nomeVacina) {
		super();
		this.nomePessoa = nomePessoa;
		this.nomeVacina = nomeVacina;
	}
	
	public String getNomePessoa() {
		return nomePessoa;
	}
	
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	
	public String getNomeVacina() {
		return nomeVacina;
	}
	
	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}
	
	
	

}
