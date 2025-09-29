package com.t1;


import com.t1.dao.ClienteDAO;
import com.t1.dao.PedidoDAO;
import com.t1.model.Cliente;
import com.t1.model.Pedido;

import java.util.List;

public class AppMain {
    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        // ===== Crear un cliente con pedidos =====
        Cliente cliente1 = new Cliente("Jonathan", "Ramirez", "999888777");

        Pedido p1 = new Pedido(2, 30.50);
        Pedido p2 = new Pedido(1, 15.00);

        // Asociamos los pedidos al cliente
        cliente1.addPedido(p1);
        cliente1.addPedido(p2);

        // Guardamos cliente y sus pedidos
        clienteDAO.guardar(cliente1);

        System.out.println("Cliente y pedidos guardados correctamente ✅");

        // ===== Listar todos los clientes =====
        List<Cliente> clientes = clienteDAO.listarTodos();
        for (Cliente c : clientes) {
            System.out.println("ID: " + c.getIdcliente() + " | Nombre: " + c.getNombres() + " " + c.getApellidos());
            c.getPedidos().forEach(p -> {
                System.out.println("   Pedido #" + p.getIdpedido() +
                                   " -> Items: " + p.getTotalItems() +
                                   ", Precio: " + p.getPrecio());
            });
        }

        // ===== Crear un pedido suelto para cliente existente =====
        Cliente clienteExistente = clienteDAO.buscarPorId(1); // busca cliente con id=1
        if (clienteExistente != null) {
            Pedido nuevoPedido = new Pedido(3, 60.00);
            clienteExistente.addPedido(nuevoPedido);

            clienteDAO.actualizar(clienteExistente); // merge actualiza cliente y pedidos
            System.out.println("Se añadió un nuevo pedido al cliente existente.");
        }
    }
}
