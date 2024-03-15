package service.vacina;

import java.util.ArrayList;

import exception.vacina.ControleVacinasException;
import model.entity.enums.x1.TipoPessoa;
import model.entity.vacina.Pessoa;
import model.entity.vacina.Vacina;
import model.repository.vacina.VacinaRepository;

public class VacinaService {
	VacinaRepository repository = new VacinaRepository();

	public Vacina salvar(Vacina novaVacina) throws ControleVacinasException {
		
		if(!isPesquisador(novaVacina.getPesquisadorResponsavel())) {
			throw new ControleVacinasException("\nPessoa não é PESQUISADOR!");
		}
		
		novaVacina = repository.salvar(novaVacina);
		return novaVacina;
	}

	private boolean isPesquisador(Pessoa pessoa) {
		if (pessoa.getTipo() == TipoPessoa.PESQUISADOR) {
			return true;
		} else {
			return false;
		}
	}

	public boolean excluir(int id) {
		return false;
	}

	public boolean alterar(Vacina vacina) {
		return false;
	}

	public Object consultarPorId(int id) {
		return null;
	}

	public ArrayList<Vacina> consultarTodos() {
		return null;
	}

}