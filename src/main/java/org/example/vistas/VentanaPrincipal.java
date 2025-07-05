package org.example.vistas;


import org.example.aplicacion.Coordinador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame implements ActionListener {
    private JPanel panelContenido;
    private JLabel etiquetaTitulo;
    private JButton botonRegistrar, botonListar, botonConsultar;
    private Coordinador coordinador;

    public VentanaPrincipal() {
        setTitle("Sistema de Mascotas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configurarComponentes();
    }

    private void configurarComponentes() {
        panelContenido = new JPanel(new GridBagLayout());
        panelContenido.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        etiquetaTitulo = new JLabel("Menú Principal");
        etiquetaTitulo.setFont(new Font("Montserrat", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 30, 0);
        gbc.gridwidth = 2;
        panelContenido.add(etiquetaTitulo, gbc);

        botonRegistrar = new JButton("Registrar Mascota");
        botonRegistrar.addActionListener(this);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenido.add(botonRegistrar, gbc);

        botonListar = new JButton("Consultar Lista");
        botonListar.addActionListener(this);
        gbc.gridy = 2;
        panelContenido.add(botonListar, gbc);

        botonConsultar = new JButton("Consultar Mascota");
        botonConsultar.addActionListener(this);
        gbc.gridy = 3;
        panelContenido.add(botonConsultar, gbc);

        add(panelContenido);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonRegistrar) {
            coordinador.mostrarVentanaRegistrar();
        } else if (e.getSource() == botonListar) {
            coordinador.mostrarVentanaConsultarLista();
        } else if (e.getSource() == botonConsultar) {
            coordinador.mostrarVentanaConsultar();
        }
    }

    public void setMiCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

}
