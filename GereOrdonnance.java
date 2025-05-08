package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import DAO.OrdonnanceDAO;
import model.Ligne_med;
import model.Medicament;
import model.Ordo;

public class GereOrdonnance extends JFrame {
    
	private static final long serialVersionUID = 1L;
	// Déclaration des composants
    private JTextField txtIdOrd, txtDescription, txtIdMed, txtQuantite;
    private JButton btnAjouter, btnSupprimer, btnEnregistrer, btnFermer;
    private JTable table;
    private DefaultTableModel tableModel;
    }
    
    try {
        dao.affiche(tableModel);} 
    catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erreur chargement données : " + e.getMessage());
    }

    
    public GereOrdonnance() {
        // Paramétrage de la fenêtre
        setTitle("Gestion Ordonnance");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);}
        
        private OrdonnanceDAO dao = new OrdonnanceDAO();
        
        // Fond de l'interface
        JPanel background = new JPanel();
        background.setLayout(null);
        background.setBackground(Color.WHITE);
        void setContentPane(background);

        // Ajout du logo
        JLabel logoLabel = new JLabel(new ImageIcon("path_to_logo.png"));
        logoLabel.setBounds(10, 10, 80, 80);
        background.add(logoLabel);

        // Création des labels et des champs de saisie
        JLabel lblIdOrd = new JLabel("ID Ordonnance");
        lblIdOrd.setBounds(100, 30, 100, 20);
        background.add(lblIdOrd);
        txtIdOrd = new JTextField();
        txtIdOrd.setBounds(210, 30, 150, 20);
        background.add(txtIdOrd);

        JLabel lblDescription = new JLabel("Description");
        lblDescription.setBounds(100, 60, 100, 20);
        background.add(lblDescription);
        txtDescription = new JTextField();
        txtDescription.setBounds(210, 60, 150, 20);
        background.add(txtDescription);

        JLabel lblIdMed = new JLabel("ID Médicament");
        lblIdMed.setBounds(100, 90, 100, 20);
        background.add(lblIdMed);
        txtIdMed = new JTextField();
        txtIdMed.setBounds(210, 90, 150, 20);
        background.add(txtIdMed);

        JLabel lblQuantite = new JLabel("Quantité");
        lblQuantite.setBounds(100, 120, 100, 20);
        background.add(lblQuantite);
        txtQuantite = new JTextField();
        txtQuantite.setBounds(210, 120, 150, 20);
        background.add(txtQuantite);

        // Création du tableau pour afficher les ordonnances
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID Ordonnance");
        tableModel.addColumn("Description");
        tableModel.addColumn("ID Médicament");
        tableModel.addColumn("Nom Médicament");
        tableModel.addColumn("Stock");
        
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 160, 400, 150);
        background.add(scrollPane);

        // Création des boutons
        btnAjouter = new JButton("Ajouter");
        btnAjouter.setBounds(50, 320, 120, 30);
        background.add(btnAjouter);

        btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setBounds(180, 320, 120, 30);
        background.add(btnSupprimer);

        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setBounds(310, 320, 120, 30);
        background.add(btnEnregistrer);

        btnFermer = new JButton("Fermer");
        btnFermer.setBounds(440, 320, 120, 30);
        background.add(btnFermer);

        // Liaisons entre les boutons et les méthodes
        btnAjouter.addActionListener(e -> ajouter());
        btnSupprimer.addActionListener(e -> supprimer());
        btnEnregistrer.addActionListener(e -> enregistrer());
        btnFermer.addActionListener(e -> fermer());
    }

    // Méthodes de gestion des ordonnances et de l'interface

    public void ajouter() {
        try {
            int idOrd = Integer.parseInt(txtIdOrd.getText().trim());
            String description = txtDescription.getText().trim();
            int idMed = Integer.parseInt(txtIdMed.getText().trim());
            int quantite = Integer.parseInt(txtQuantite.getText().trim());

            Ordo o1 = new Ordo(idOrd, description);
            Ordo o2 = o1;  // Pour respecter l’entête de la méthode
            Medicament med = new Medicament(idMed, description, quantite);
            Ligne_med ligne = new Ligne_med(idOrd, idMed, quantite);

            OrdonnanceDAO service = new OrdonnanceDAO();
            service.ajouter(o1, o2, med, ligne, tableModel);  // Appel à la méthode ajouter

            JOptionPane.showMessageDialog(this, "Ordonnance ajoutée avec succès.");
            viderChamps();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
        }
    }

    public void supprimer() {
        try {
            int idOrd = Integer.parseInt(txtIdOrd.getText().trim());
            String description = txtDescription.getText().trim();
            int idMed = Integer.parseInt(txtIdMed.getText().trim());
            int quantite = Integer.parseInt(txtQuantite.getText().trim());

            Ordo o1 = new Ordo(idOrd, description);
            Ordo o2 = o1;  // Respect de l'entête
            Medicament med = new Medicament(idMed);
            Ligne_med ligne = new Ligne_med(idOrd, idMed, quantite);

            OrdonnanceDAO service = new OrdonnanceDAO ();
            service.supprimer(o1, o2, med, ligne, tableModel);  // Appel à la méthode supprimer

            JOptionPane.showMessageDialog(this, "Ordonnance supprimée avec succès.");
            viderChamps();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
        }
    }

    public void enregistrer() {
        try {
            int idOrd = Integer.parseInt(txtIdOrd.getText().trim());
            String description = txtDescription.getText().trim();
            int idMed = Integer.parseInt(txtIdMed.getText().trim());
            int quantite = Integer.parseInt(txtQuantite.getText().trim());

            Ordo o1 = new Ordo(idOrd, description);
            Ordo o2 = o1;  // Respect de l'entête
            Medicament med = new Medicament(idMed);
            Ligne_med ligne = new Ligne_med(idOrd, idMed, quantite);

            OrdonnanceDAO service = new OrdonnanceDAO();
            service.enregistrer(o1, o2, med, ligne, tableModel);  // Appel à la méthode enregistrer

            JOptionPane.showMessageDialog(this, "Ordonnance enregistrée avec succès.");
            viderChamps();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
        }
    }

    public void fermer() {
        dispose();  // Ferme l'interface
    }

    private void viderChamps() {
        txtIdOrd.setText("");
        txtDescription.setText("");
        txtIdMed.setText("");
        txtQuantite.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GereOrdonnance().setVisible(true);
        });
    


	/**private static final long serialVersionUID = 1L;
	private JPanel contentPane;**/

	
	/**public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GereOrdonnance frame = new GereOrdonnance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}**/

	/**
	 * Create the frame.
	 */
	/**public GereOrdonnance() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}**/

