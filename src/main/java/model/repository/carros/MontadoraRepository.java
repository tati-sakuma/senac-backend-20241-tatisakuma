package model.repository.carros;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.carros.Montadora;
import model.repository.Banco;
import model.repository.BaseRepository;

public class MontadoraRepository implements BaseRepository<Montadora> {

	@Override
	public Montadora salvar(Montadora novaMontadora) {
		String query = " INSERT INTO exemplos.montadora "
				+ " (NOME, PAIS, NOME_PRESIDENTE, DATA_FUNDACAO) "
				+ " VALUES (?, ?, ?, ?) ";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novaMontadora.getNome());
			pstmt.setString(2, novaMontadora.getPaisFundacao());
			pstmt.setString(3, novaMontadora.getNomePresidente());
			pstmt.setDate(4, Date.valueOf(novaMontadora.getDataFundacao()));
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novaMontadora.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar nova montadora");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return novaMontadora;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Montadora entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Montadora consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Montadora montadora = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM MONTADORA WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				montadora = new Montadora();
				montadora.setId(resultado.getInt("ID"));
				montadora.setNome(resultado.getString("NOME"));
				montadora.setPaisFundacao(resultado.getString("PAIS"));
				
				montadora.setNomePresidente(resultado.getString("NOME_PRESIDENTE"));
				montadora.setDataFundacao(resultado.getDate("DATA_FUNDACAO").toLocalDate()); 
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar montadora com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return montadora;
	}

	@Override
	public ArrayList<Montadora> consultarTodos() {
		ArrayList<Montadora> montadoras = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM MONTADORA ";
		
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Montadora montadora = new Montadora();
				montadora.setId(resultado.getInt("ID"));
				montadora.setNome(resultado.getString("NOME"));
				montadora.setPaisFundacao(resultado.getString("PAIS"));
				montadora.setNomePresidente(resultado.getString("NOME_PRESIDENTE"));
				montadora.setDataFundacao(resultado.getDate("DATA_FUNDACAO").toLocalDate()); 

				montadoras.add(montadora);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todas as montadoras");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return montadoras;
	}

}
