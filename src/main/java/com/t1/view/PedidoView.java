package com.t1.view;

import com.t1.dao.PedidoDAO;
import com.t1.dao.ClienteDAO;
import com.t1.model.Cliente;
import com.t1.model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PedidoView extends JPanel {

    private JTextField txtIdCliente;
    private JTextField txtTotalItems;
    private JTextField txtPrecio;
    private JTable tablePedidos;
    private DefaultTableModel tableModel;
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    public PedidoView() {
        setLayout(new BorderLayout());

        // ===== Formulario =====
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        formPanel.add(new JLabel("ID Cliente:"));
        txtIdCliente = new JTextField();
        formPanel.add(txtIdCliente);

        formPanel.add(new JLabel("Total Items:"));
        txtTotalItems = new JTextField();
        formPanel.add(txtTotalItems);

        formPanel.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        formPanel.add(txtPrecio);

        // Botón guardar
        JButton btnGuardar = new JButton("Guardar Pedido");
        btnGuardar.addActionListener(e -> guardarPedido());
        formPanel.add(btnGuardar);

        // Botón listar
        JButton btnListar = new JButton("Listar Pedidos");
        btnListar.addActionListener(e -> cargarPedidos());
        formPanel.add(btnListar);

        // Botón actualizar
        JButton btnActualizar = new JButton("Actualizar Pedido");
        btnActualizar.addActionListener(e -> actualizarPedido());
        formPanel.add(btnActualizar);

        add(formPanel, BorderLayout.NORTH);

        // ===== Tabla =====
        String[] columnas = {"ID Pedido", "ID Cliente", "Items", "Precio", "Fecha"};
        tableModel = new DefaultTableModel(columnas, 0);
        tablePedidos = new JTable(tableModel);

        // Listener para seleccionar fila y pasar datos al formulario
        tablePedidos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablePedidos.getSelectedRow() != -1) {
                int row = tablePedidos.getSelectedRow();
                txtIdCliente.setText(tableModel.getValueAt(row, 1).toString());
                txtTotalItems.setText(tableModel.getValueAt(row, 2).toString());
                txtPrecio.setText(tableModel.getValueAt(row, 3).toString());
            }
        });

        add(new JScrollPane(tablePedidos), BorderLayout.CENTER);
    }

    // Guarda pedido en BD
    private void guardarPedido() {
        try {
            int idCliente = Integer.parseInt(txtIdCliente.getText());
            int totalItems = Integer.parseInt(txtTotalItems.getText());
            double precio = Double.parseDouble(txtPrecio.getText());

            Cliente cliente = clienteDAO.buscarPorId(idCliente);
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado ❌");
                return;
            }

            Pedido p = new Pedido(totalItems, precio);
            p.setCliente(cliente);

            pedidoDAO.guardar(p);

            JOptionPane.showMessageDialog(this, "Pedido guardado ✅");
            cargarPedidos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // Carga pedidos en tabla
    private void cargarPedidos() {
        tableModel.setRowCount(0); // limpia tabla
        List<Pedido> pedidos = pedidoDAO.listarTodos();

        for (Pedido p : pedidos) {
            tableModel.addRow(new Object[]{
                    p.getIdpedido(),
                    p.getCliente().getIdcliente(),
                    p.getTotalItems(),
                    p.getPrecio(),
                    p.getCreatedAt()
            });
        }
    }

    // Actualiza pedido seleccionado
    private void actualizarPedido() {
        int row = tablePedidos.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un pedido para actualizar");
            return;
        }

        try {
            int idPedido = (int) tableModel.getValueAt(row, 0);
            int idCliente = Integer.parseInt(txtIdCliente.getText());
            int totalItems = Integer.parseInt(txtTotalItems.getText());
            double precio = Double.parseDouble(txtPrecio.getText());

            Cliente cliente = clienteDAO.buscarPorId(idCliente);
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado ❌");
                return;
            }

            Pedido p = new Pedido(totalItems, precio);
            p.setIdpedido(idPedido);
            p.setCliente(cliente);

            pedidoDAO.actualizar(p);

            JOptionPane.showMessageDialog(this, "Pedido actualizado ✅");
            cargarPedidos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}

