package DAO;

import model.Ligne_med;
import model.Medicament;
import model.Ordo;


import java.sql.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;
	
	public class OrdonnanceDAO implements IDAOClientMedicament <Ordo>{

	    
	    private Connection con;

	    public OrdonnanceDAO() {
	        try {
	            // Connexion à la base (à adapter selon votre configuration)
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "java2");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void ajouter(Ordo ele1, Ordo ele2, Medicament ele3, Ligne_med ele4, DefaultTableModel model) throws SQLException {
	        String sqlOrdo = "INSERT INTO ordonnance(id_ord, description) VALUES (?, ?)";
	        PreparedStatement pstOrdo = con.prepareStatement(sqlOrdo);
	        pstOrdo.setInt(1, ele1.id_ord());
	        pstOrdo.setString(2, ele1.description());
	        pstOrdo.executeUpdate();
	        pstOrdo.close();

	        String sqlLigne = "INSERT INTO ligne_med(id_ord, id_med, quantite) VALUES (?, ?, ?)";
	        PreparedStatement pstLigne = con.prepareStatement(sqlLigne);
	        pstLigne.setInt(1, ele4.id_ord());
	        pstLigne.setInt(2, ele4.id_med());
	        pstLigne.setInt(3, ele4.quantite());
	        pstLigne.executeUpdate();
	        pstLigne.close();

	        String sqlStock = "UPDATE medicament SET stock = stock - ? WHERE id_med = ?";
	        PreparedStatement pstStock = con.prepareStatement(sqlStock);
	        pstStock.setInt(1, ele4.quantite());
	        pstStock.setInt(2, ele4.id_med());
	        pstStock.executeUpdate();
	        pstStock.close();

	        affiche(model);
	    }

	    public void supprimer(Ordo ele1, Ordo ele2, Medicament ele3, Ligne_med ele4, DefaultTableModel model) throws SQLException {
	        String sqlDeleteLigne = "DELETE FROM ligne_med WHERE id_ord = ? AND id_med = ?";
	        PreparedStatement pstLigne = con.prepareStatement(sqlDeleteLigne);
	        pstLigne.setInt(1, ele4.id_ord());
	        pstLigne.setInt(2, ele4.id_med());
	        pstLigne.executeUpdate();
	        pstLigne.close();

	        String sqlDeleteOrdo = "DELETE FROM ordonnance WHERE id_ord = ?";
	        PreparedStatement pstOrdo = con.prepareStatement(sqlDeleteOrdo);
	        pstOrdo.setInt(1, ele1.id_ord());
	        pstOrdo.executeUpdate();
	        pstOrdo.close();

	        String sqlStock = "UPDATE medicament SET stock = stock + ? WHERE id_med = ?";
	        PreparedStatement pstStock = con.prepareStatement(sqlStock);
	        pstStock.setInt(1, ele4.quantite());
	        pstStock.setInt(2, ele4.id_med());
	        pstStock.executeUpdate();
	        pstStock.close();

	        affiche(model);
	    }

	    public void enregistrer(Ordo ele1, Ordo ele2, Medicament ele3, Ligne_med ele4, DefaultTableModel model) throws SQLException {
	        // Supposons que cette méthode soit similaire à ajouter mais dans une autre table ou logique future.
	        // Pour l’instant, on la fait identique à ajouter pour cohérence.
	        ajouter(ele1, ele2, ele3, ele4, model);
	    }

	    public void affiche(DefaultTableModel model) throws SQLException {
	        String sql = """
	            SELECT o.id_ord, o.description, m.id_med, m.nom_med, m.stock
	            FROM ordonnance o
	            JOIN ligne_med l ON o.id_ord = l.id_ord
	            JOIN medicament m ON l.id_med = m.id_med
	        """;

	        PreparedStatement pst = con.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();

	        model.setRowCount(0); // Vider le tableau

	        while (rs.next()) {
	            int idOrd = rs.getInt("id_ord");
	            String description = rs.getString("description");
	            int idMed = rs.getInt("id_med");
	            String nomMed = rs.getString("nom_med");
	            int stock = rs.getInt("stock");

	            model.addRow(new Object[]{idOrd, description, idMed, nomMed, stock});
	        }

	        rs.close();
	        pst.close();
	    }

		@Override
		public List<Ordo> getAll() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
	}


