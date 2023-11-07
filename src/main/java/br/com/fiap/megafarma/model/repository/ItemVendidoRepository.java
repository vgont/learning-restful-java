/* VINICIUS GONTIJO SANTOS
 * RM550657
 * */
package br.com.fiap.megafarma.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.megafarma.model.entity.ItemVendido;


public class ItemVendidoRepository extends Repository{
	public static ArrayList<ItemVendido> findAllItensVendidos(){
		ArrayList<ItemVendido> itens = new ArrayList<ItemVendido>();
		String sql = "select * from tb_itens_vendidos";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					ItemVendido item = new ItemVendido();
					item.setIdVenda(rs.getLong("venda"));
					item.setIdRemedio(rs.getLong("remedio"));
					item.setQuantidade(rs.getInt("quantidade"));
					itens.add(item);
				}
				return itens;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar itens vendidos: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
	
	public static ItemVendido findAllByIdVendaAndRemedio(Long idVenda, Long idRemedio) {		
		String sql = "select * from tb_itens_vendidos where venda=? and remedio=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, idVenda);
			ps.setLong(2, idRemedio);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				ItemVendido item = new ItemVendido();
				item.setIdVenda(rs.getLong("venda"));
				item.setIdRemedio(rs.getLong("remedio"));
				item.setQuantidade(rs.getInt("quantidade"));		
				return item;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar item vendido: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
	
	public static ItemVendido save(ItemVendido item) {
		String sql = "insert into tb_itens_vendidos (venda, remedio, quantidade) values(?, ?, ?)";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, item.getIdVenda());
			ps.setLong(2, item.getIdRemedio());
			ps.setInt(3, item.getQuantidade());
			
			if(ps.executeUpdate()>0) {
				return item;				
			} 									
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar item vendido: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
	
	public static boolean delete(Long idVenda, Long idRemedio) {
		String sql = "delete from tb_itens_vendidos where venda=? and remedio=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, idVenda);
			ps.setLong(2, idRemedio);
			
			if(ps.executeUpdate()>0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao deletar item vendido: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return false;
	}
	
	public static ItemVendido update(ItemVendido item) {
		String sql = "update tb_itens_vendidos set quantidade=? where venda=? and remedio=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			
			ps.setLong(1, item.getQuantidade());
			ps.setLong(2, item.getIdVenda());
			ps.setLong(3, item.getIdRemedio());
			
			if(ps.executeUpdate()>0) {
				return item;
			}			
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar item vendido: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
}
