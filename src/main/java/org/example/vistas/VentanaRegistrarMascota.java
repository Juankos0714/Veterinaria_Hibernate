package org.example.vistas;



import org.example.aplicacion.Coordinador;
import org.example.entidades.Mascota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistrarMascota extends JFrame implements ActionListener {
    private JPanel panelContenido;
    private JLabel etiquetaTitulo, etiquetaNombre, etiquetaRaza, etiquetaColor, etiquetaSexo;
    private JTextField campoNombre, campoRaza, campoColor;
    private JComboBox<String> selectorSexo;
    private JButton botonGuardar, botonCancelar;
    private Coordinador coordinador;

    public VentanaRegistrarMascota() {
        setTitle("Registrar Mascota");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configurarComponentes();
    }

    private void configurarComponentes() {
        panelContenido = new JPanel(new GridBagLayout());
        panelContenido.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        etiquetaTitulo = new JLabel("Registrar Mascota");
        etiquetaTitulo.setFont(new Font("Montserrat", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        panelContenido.add(etiquetaTitulo, gbc);

        etiquetaNombre = new JLabel("Nombre:");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelContenido.add(etiquetaNombre, gbc);

        campoNombre = new JTextField(15);
        gbc.gridx = 1;
        panelContenido.add(campoNombre, gbc);

        etiquetaRaza = new JLabel("Raza:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelContenido.add(etiquetaRaza, gbc);

        campoRaza = new JTextField(15);
        gbc.gridx = 1;
        panelContenido.add(campoRaza, gbc);

        etiquetaColor = new JLabel("Color:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelContenido.add(etiquetaColor, gbc);

        campoColor = new JTextField(15);
        gbc.gridx = 1;
        panelContenido.add(campoColor, gbc);

        etiquetaSexo = new JLabel("Sexo:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelContenido.add(etiquetaSexo, gbc);

        selectorSexo = new JComboBox<>(new String[]{"", "Macho", "Hembra"});
        gbc.gridx = 1;
        panelContenido.add(selectorSexo, gbc);

        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(20, 5, 5, 5);
        panelContenido.add(botonGuardar, gbc);

        botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(this);
        gbc.gridx = 1;
        panelContenido.add(botonCancelar, gbc);

        add(panelContenido);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonGuardar) {
            registrarMascota();
        } else if (e.getSource() == botonCancelar) {
            dispose();
        }
    }

    private void registrarMascota() {
        Mascota mascota = new Mascota();
        mascota.setNombre(campoNombre.getText());
        mascota.setRaza(campoRaza.getText());
        mascota.setColorMascota(campoColor.getText());
        mascota.setSexo(selectorSexo.getSelectedItem().toString());

        String resultado = coordinador.registrarMascota(mascota);
        if (resultado.equals("si")) {
            JOptionPane.showMessageDialog(this, "Mascota registrada con éxito");
            dispose();
        }
    }

    public void setMiCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }
}
