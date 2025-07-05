package org.example.vistas;


import org.example.aplicacion.Coordinador;
import org.example.entidades.Mascota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaConsultarLista extends JFrame implements ActionListener {
    private JPanel panelContenido;
    private JLabel etiquetaTitulo;
    private JButton botonListarTodo, botonFiltrarSexo;
    private JTable tablaMascotas;
    private JScrollPane scrollTabla;
    private DefaultTableModel modeloTabla;
    private Coordinador coordinador;
    private JComboBox<String> selectorSexo;

    public VentanaConsultarLista() {
        setTitle("Consultar Lista de Mascotas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configurarComponentes();
    }

    private void configurarComponentes() {
        panelContenido = new JPanel(new GridBagLayout());
        panelContenido.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        etiquetaTitulo = new JLabel("Consultar Listas");
        etiquetaTitulo.setFont(new Font("Montserrat", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        panelContenido.add(etiquetaTitulo, gbc);

        botonListarTodo = new JButton("Listar Todas");
        botonListarTodo.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelContenido.add(botonListarTodo, gbc);

        selectorSexo = new JComboBox<>(new String[]{"Hembra", "Macho"});
        gbc.gridx = 1;
        panelContenido.add(selectorSexo, gbc);

        botonFiltrarSexo = new JButton("Filtrar por Sexo");
        botonFiltrarSexo.addActionListener(this);
        gbc.gridx = 2;
        panelContenido.add(botonFiltrarSexo, gbc);

        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{"ID", "Nombre", "Raza", "Color", "Sexo"});

        tablaMascotas = new JTable(modeloTabla);
        tablaMascotas.setEnabled(false);

        scrollTabla = new JScrollPane(tablaMascotas);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panelContenido.add(scrollTabla, gbc);

        add(panelContenido);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonListarTodo) {
            listarTodasMascotas();
        } else if (e.getSource() == botonFiltrarSexo) {
            filtrarPorSexo();
        }
    }

    private void listarTodasMascotas() {
        List<Mascota> mascotas = coordinador.consultarListaMascotas(null);
        if (mascotas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay mascotas registradas");
        } else {
            actualizarTabla(mascotas);
        }
    }

    private void filtrarPorSexo() {
        String sexo = selectorSexo.getSelectedItem().toString();
        List<Mascota> mascotas = coordinador.consultarListaMascotasSexo(sexo);

        if (mascotas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay mascotas de sexo " + sexo);
        } else {
            actualizarTabla(mascotas);
        }
    }

    private void actualizarTabla(List<Mascota> mascotas) {
        modeloTabla.setRowCount(0);

        for (Mascota mascota : mascotas) {
            modeloTabla.addRow(new Object[]{
                    mascota.getIdMascota(),
                    mascota.getNombre(),
                    mascota.getRaza(),
                    mascota.getColorMascota(),
                    mascota.getSexo()
            });
        }
    }

    public void setMiCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }
}
