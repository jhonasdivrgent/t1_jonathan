package com.t1.view;

import com.t1.dao.PedidoDAO;
import com.t1.model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListadoPedido extends JFrame {

    private JTable tablePedidos;
    private DefaultTableModel tableModel;
    private PedidoDAO pedidoDAO = new PedidoDAO();

    public ListadoPedido() {
        setTitle("Listado de Pedidos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnas = {"ID Pedido", "Cliente", "Items", "Precio", "Fecha"};
        tableModel = new DefaultTableModel(columnas, 0);
        tablePedidos = new JTable(tableModel);

        cargarPedidos();

        add(new JScrollPane(tablePedidos), BorderLayout.CENTER);
    }

    private void cargarPedidos() {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ListadoPedido().setVisible(true);
        });
    }
}
