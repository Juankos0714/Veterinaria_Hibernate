package org.example.vistas;


import org.example.aplicacion.Coordinador;
import org.example.entidades.Mascota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaConsultarMascota extends JFrame implements ActionListener {
    private JPanel panelContenido;
    private JLabel etiquetaTitulo, etiquetaId, etiquetaNombre, etiquetaRaza, etiquetaColor, etiquetaSexo;
    private JTextField campoId, campoNombre, campoRaza, campoColor, campoSexo;
    private JButton botonConsultar, botonActualizar, botonEliminar;
    private Coordinador coordinador;

    public VentanaConsultarMascota() {
        setTitle("Consultar Mascota");
        setSize(450, 400);
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

        etiquetaTitulo = new JLabel("Consultar Mascota por ID");
        etiquetaTitulo.setFont(new Font("Montserrat", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 0, 20, 0);
        panelContenido.add(etiquetaTitulo, gbc);

        etiquetaId = new JLabel("ID:");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelContenido.add(etiquetaId, gbc);

        campoId = new JTextField(10);
        gbc.gridx = 1;
        panelContenido.add(campoId, gbc);

        botonConsultar = new JButton("Consultar");
        botonConsultar.addActionListener(this);
        gbc.gridx = 2;
        panelContenido.add(botonConsultar, gbc);

        etiquetaNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelContenido.add(etiquetaNombre, gbc);

        campoNombre = new JTextField(15);
        campoNombre.setEditable(false);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panelContenido.add(campoNombre, gbc);

        etiquetaRaza = new JLabel("Raza:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panelContenido.add(etiquetaRaza, gbc);

        campoRaza = new JTextField(15);
        campoRaza.setEditable(false);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panelContenido.add(campoRaza, gbc);

        etiquetaColor = new JLabel("Color:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panelContenido.add(etiquetaColor, gbc);

        campoColor = new JTextField(15);
        campoColor.setEditable(false);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panelContenido.add(campoColor, gbc);

        etiquetaSexo = new JLabel("Sexo:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelContenido.add(etiquetaSexo, gbc);

        campoSexo = new JTextField(15);
        campoSexo.setEditable(false);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panelContenido.add(campoSexo, gbc);

        botonActualizar = new JButton("Actualizar");
        botonActualizar.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 5, 5, 5);
        panelContenido.add(botonActualizar, gbc);

        botonEliminar = new JButton("Eliminar");
        botonEliminar.addActionListener(this);
        gbc.gridx = 2;
        panelContenido.add(botonEliminar, gbc);

        add(panelContenido);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonConsultar) {
            consultarMascota();
        } else if (e.getSource() == botonActualizar) {
            actualizarMascota();
        } else if (e.getSource() == botonEliminar) {
            eliminarMascota();
        }
    }

    private void consultarMascota() {
        try {
            Long id = Long.parseLong(campoId.getText().trim());
            Mascota mascota = coordinador.consultarMascota(id);

            if (mascota != null) {
                campoNombre.setText(mascota.getNombre());
                campoRaza.setText(mascota.getRaza());
                campoColor.setText(mascota.getColorMascota());
                campoSexo.setText(mascota.getSexo());

                campoNombre.setEditable(true);
                campoRaza.setEditable(true);
                campoColor.setEditable(true);
                campoSexo.setEditable(true);
            } else {
                JOptionPane.showMessageDialog(this, "No existe mascota con ese ID");
                limpiarCampos();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido");
        }
    }

    private void actualizarMascota() {
        try {
            Mascota mascota = new Mascota();
            mascota.setIdMascota(Long.parseLong(campoId.getText().trim()));
            mascota.setNombre(campoNombre.getText());
            mascota.setRaza(campoRaza.getText());
            mascota.setColorMascota(campoColor.getText());
            mascota.setSexo(campoSexo.getText());

            String resultado = coordinador.ActualizarMascota(mascota);
            JOptionPane.showMessageDialog(this, resultado);
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID no válido");
        }
    }

    private void eliminarMascota() {
        try {
            Mascota mascota = new Mascota();
            mascota.setIdMascota(Long.parseLong(campoId.getText().trim()));

            String resultado = coordinador.eliminarMascota(mascota);
            JOptionPane.showMessageDialog(this, resultado);
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID no válido");
        }
    }

    private void limpiarCampos() {
        campoNombre.setText("");
        campoRaza.setText("");
        campoColor.setText("");
        campoSexo.setText("");
        campoNombre.setEditable(false);
        campoRaza.setEditable(false);
        campoColor.setEditable(false);
        campoSexo.setEditable(false);
    }

    public void setMiCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }


}
