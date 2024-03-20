package model.repository.vacina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;


import model.entity.vacina.Pais;
import model.repository.x1.Banco;
import model.repository.x1.BaseRepository;

public class PaisRepository implements BaseRepository<Pais> {

	@Override
	public Pais salvar(Pais novoPais) {
		String query = "INSERT INTO pais(NOME, SIGLA)VALUES(?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			pstmt.setString(1, novoPais.getPais());
			pstmt.setString(2, novoPais.getSigla());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			
			if(resultado.next()) {
				novoPais.setIdPais(resultado.getInt(1));
			}
			
		}catch (SQLException erro) {
			System.out.println("Erro ao salvar nova pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return novoPais;
	}
	
	public boolean verificarSePaisJaCadastrado(String pais, String sigla) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		String query = "SELECT NOME, SIGLA FROM pais WHERE NOME = '" 
				+ pais + "' OR SIGLA = '" + sigla;
		boolean retorno = false;
		
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				retorno = true;
			}
			
		} catch(SQLException erro) {
			System.out.println("Erro ao executar a query do método verificarSePaisJaCadastrado!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return retorno;
	}
	
	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Pais pais) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pais consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Pais> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
