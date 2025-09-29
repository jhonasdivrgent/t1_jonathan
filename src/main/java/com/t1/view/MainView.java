package com.t1.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {
        setTitle("Sistema de Compras - T1 Jonathan");
        setSize(900, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        // Contenedor de pestañas
        JTabbedPane tabs = new JTabbedPane();

        // Pestaña Clientes
        JPanel clientePanel = new ClienteView();
        tabs.addTab("Clientes", clientePanel);

        // Pestaña Pedidos
        JPanel pedidoPanel = new PedidoView();
        tabs.addTab("Pedidos", pedidoPanel);

        // Pestaña Listado de Pedidos 
        JPanel listadoPanel = new JPanel(new BorderLayout());
        JTable table = new JTable(); // solo placeholder
        listadoPanel.add(new JScrollPane(table), BorderLayout.CENTER);

      
        tabs.addTab("Listado Pedidos", new ListadoPedido().getContentPane());

       
        add(tabs, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainView().setVisible(true);
        });
    }
}
