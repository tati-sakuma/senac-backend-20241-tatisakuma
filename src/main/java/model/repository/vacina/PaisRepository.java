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
	
	/*public boolean verificarSePaisJaCadastrado(String pais, String sigla) {
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
	} */
	
	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM pais WHERE id = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir Vacina.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Pais pais) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pais consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		Pais pais = new Pais();
		String query = " SELECT * FROM pais WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				pais.setIdPais(id);
				pais.setPais(resultado.getString("NOME"));
				pais.setSigla(resultado.getNString("SIGLA"));
			}
		} catch (SQLException erro){
			System.out.println("Erro ao executar consultar País com id (" + id + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pais;
	}

	@Override
	public ArrayList<Pais> consultarTodos() {
		ArrayList<Pais> paises = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = "SELECT * FROM pais;";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Pais pais = new Pais();

				pais.setIdPais(resultado.getInt("ID"));
				pais.setPais(resultado.getString("NOME"));
				pais.setSigla(resultado.getString("SIGLA"));

				paises.add(pais);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar todos os países.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return paises;
	}
}
