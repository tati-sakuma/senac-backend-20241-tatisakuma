package model.entity.vacina;

public class Pais {
	private int idPais;
	private String pais;
	private String sigla;
	
	public Pais () {

	}
	
	public Pais (int idPais, String pais, String sigla) {
		super ();
		this.idPais = idPais;
		this.pais = pais;
		this.sigla = sigla;
	}
	
	public int getIdPais(){
		return idPais;
	}
	public void setIdPais(int idPais) {
 		this.idPais = idPais;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
