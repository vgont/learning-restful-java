/* VINICIUS GONTIJO SANTOS
 * RM550657
 * */
package br.com.fiap.megafarma.model.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.megafarma.model.entity.Venda;

public class VendaRepository extends Repository{
	
	public static ArrayList<Venda> findAllVendas(){
		ArrayList<Venda> vendas = new ArrayList<Venda>();
		String sql = "select * from tb_vendas";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					Venda venda = new Venda();
					venda.setId(rs.getLong("id"));
					venda.setIdCliente(rs.getLong("cliente"));
					venda.setDataDaVenda(rs.getDate("data_da_venda").toLocalDate());
					vendas.add(venda);
				}
				return vendas;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar vendas: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
	
	public static Venda findAllByIdVenda(Long id) {		
		String sql = "select * from tb_vendas where id=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getLong("id"));
				venda.setIdCliente(rs.getLong("id_cliente"));
				venda.setDataDaVenda(rs.getDate("data_da_venda").toLocalDate());			
				return venda;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar venda: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
	
	public static Venda save(Venda venda) {
		String sql = "insert into tb_vendas (cliente, data_da_venda) values(?, ?)";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, venda.getIdCliente());
			ps.setDate(2, Date.valueOf(venda.getDataDaVenda()));
			
			if(ps.executeUpdate()>0) {
				return venda;				
			} 									
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar venda: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
	
	public static boolean delete(Long id) {
		String sql = "delete from tb_vendas where id=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			
			if(ps.executeUpdate()>0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao deletar venda: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return false;
	}
	
	public static Venda update(Venda venda) {
		String sql = "update tb_vendas set cliente=?, data_da_venda=? where id=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			
			ps.setLong(1, venda.getIdCliente());
			ps.setDate(2, Date.valueOf(venda.getDataDaVenda()));
			
			if(ps.executeUpdate()>0) {
				return venda;
			}			
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar venda: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
}







































