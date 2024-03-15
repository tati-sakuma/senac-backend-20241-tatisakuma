package model.repository.vacina;

import java.util.ArrayList;

import model.entity.vacina.Vacinacao;
import model.repository.x1.BaseRepository;

public class VacinacaoRepository implements BaseRepository<Vacinacao> {

	@Override
	public Vacinacao salvar(Vacinacao novaVacinacao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Vacinacao vacinacao) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vacinacao consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Vacinacao> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
