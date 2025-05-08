package DAO;

import java.sql.SQLException;
import java.util.List;

import model.LigneMedicament;
import model.Ligne_med;
import model.Medicament;
import model.Ordo;
import model.Ordonnance;

public interface IDAOOrdonnance<T> {
	void ajouter(Ordo ele1,Ordo ele2, Medicament ele3, Ligne_med ele4) throws SQLException;
	void supprimer(Ordo ele1,Ordo ele2, Medicament ele3, Ligne_med ele4) throws SQLException;
	void enregistrer(List<Ordo> liste) throws SQLException;
	void fermer();
}
