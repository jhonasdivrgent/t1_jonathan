package com.t1.view;

import com.t1.dao.ClienteDAO;
import com.t1.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteView extends JPanel {

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCelular;
    private JTable tableClientes;
    private DefaultTableModel tableModel;
    private ClienteDAO clienteDAO = new ClienteDAO();

    public ClienteView() {
        setLayout(new BorderLayout());

        // ===== Formulario =====
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        formPanel.add(new JLabel("Nombres:"));
        txtNombre = new JTextField();
        formPanel.add(txtNombre);

        formPanel.add(new JLabel("Apellidos:"));
        txtApellido = new JTextField();
        formPanel.add(txtApellido);

        formPanel.add(new JLabel("Celular:"));
        txtCelular = new JTextField();
        formPanel.add(txtCelular);

        // Botón guardar
        JButton btnGuardar = new JButton("Guardar Cliente");
        btnGuardar.addActionListener(e -> guardarCliente());
        formPanel.add(btnGuardar);

        // Botón listar
        JButton btnListar = new JButton("Listar Clientes");
        btnListar.addActionListener(e -> cargarClientes());
        formPanel.add(btnListar);

        // Botón actualizar
        JButton btnActualizar = new JButton("Actualizar Cliente");
        btnActualizar.addActionListener(e -> actualizarCliente());
        formPanel.add(btnActualizar);

        add(formPanel, BorderLayout.NORTH);

        // ===== Tabla =====
        String[] columnas = {"ID", "Nombres", "Apellidos", "Celular"};
        tableModel = new DefaultTableModel(columnas, 0);
        tableClientes = new JTable(tableModel);

        // Listener para cargar datos al seleccionar fila
        tableClientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tableClientes.getSelectedRow() != -1) {
                int row = tableClientes.getSelectedRow();
                txtNombre.setText(tableModel.getValueAt(row, 1).toString());
                txtApellido.setText(tableModel.getValueAt(row, 2).toString());
                txtCelular.setText(tableModel.getValueAt(row, 3).toString());
            }
        });

        add(new JScrollPane(tableClientes), BorderLayout.CENTER);
    }

    // Guarda cliente en BD
    private void guardarCliente() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String celular = txtCelular.getText();

        Cliente c = new Cliente(nombre, apellido, celular);
        clienteDAO.guardar(c);

        JOptionPane.showMessageDialog(this, "Cliente guardado ✅");
        cargarClientes();
    }

    // Carga clientes en tabla
    private void cargarClientes() {
        tableModel.setRowCount(0); // limpia tabla
        List<Cliente> clientes = clienteDAO.listarTodos();

        for (Cliente c : clientes) {
            tableModel.addRow(new Object[]{
                    c.getIdcliente(),
                    c.getNombres(),
                    c.getApellidos(),
                    c.getCelular()
            });
        }
    }

    // Actualiza cliente seleccionado
    private void actualizarCliente() {
        int row = tableClientes.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente para actualizar");
            return;
        }

        int id = (int) tableModel.getValueAt(row, 0);
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String celular = txtCelular.getText();

        Cliente c = new Cliente(nombre, apellido, celular);
        c.setIdcliente(id);

        clienteDAO.actualizar(c);

        JOptionPane.showMessageDialog(this, "Cliente actualizado ✅");
        cargarClientes();
    }
}
