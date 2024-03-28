package service.vacina;

import java.util.ArrayList;

import exception.vacina.ControleVacinasException;
import model.entity.enums.x1.TipoPessoa;
import model.entity.vacina.Pessoa;
import model.entity.vacina.Vacina;
import model.repository.vacina.VacinaRepository;
import model.repository.vacina.VacinacaoRepository;

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

	public boolean excluir(int id) throws ControleVacinasException {
		this.verificarSeVacinaJaAplicada(id);
		return repository.excluir(id);
	}
	
	private void verificarSeVacinaJaAplicada(int idVacina) throws ControleVacinasException {
		VacinacaoRepository vacinacaoRepository = new VacinacaoRepository ();
		
		if(!vacinacaoRepository.vacinacoesPorIdVacina(idVacina).isEmpty()) {
			throw new ControleVacinasException("\nVacina não pode ser excluída, pois já foi aplicada!");
		}
	}
	
	public boolean alterar(Vacina vacina) {
		return false;
	}

	public Vacina consultarPorId(Integer id) {
				
		return repository.consultarPorId(id);
	}

	public ArrayList<Vacina> consultarTodas() {
		return repository.consultarTodos();
	}
	
	

}
