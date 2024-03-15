package model.repository.vacina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.vacina.Vacina;
import model.repository.x1.Banco;
import model.repository.x1.BaseRepository;

public class VacinaRepository implements BaseRepository <Vacina> {

	@Override
	public Vacina salvar(Vacina novaVacina) {
		String query = "INSERT INTO Vacina (nome, pais_origem, id_pesquisador, DATA_INICIO_PESQUISA, estagio) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novaVacina.getNome());
			pstmt.setString(2, novaVacina.getPaisOrigem());
			pstmt.setInt(3, novaVacina.getPesquisadorResponsavel().getId());
			pstmt.setDate(4, Date.valueOf(novaVacina.getDataInicioPesquisa()));
			pstmt.setInt(5, novaVacina.getEstagio());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			
			if(resultado.next()) {
				novaVacina.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar nova vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return novaVacina;
	}
	
	

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Vacina vacina) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vacina consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Vacina> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
