package service.vacina;

import java.util.ArrayList;

import model.entity.vacina.Vacinacao;
import model.repository.vacina.VacinacaoRepository;
import model.repository.x1.BaseRepository;

public class VacinacaoService implements BaseRepository<Vacinacao>{
	
	VacinacaoRepository repository = new VacinacaoRepository();
	
	@Override
	public Vacinacao salvar(Vacinacao novaVacinacao) {
		
		novaVacinacao = repository.salvar(novaVacinacao);
		return novaVacinacao;
	}

	@Override
	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	@Override
	public boolean alterar(Vacinacao entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vacinacao consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	@Override
	public ArrayList<Vacinacao> consultarTodos() {
		return repository.consultarTodos();
	}
	
}
