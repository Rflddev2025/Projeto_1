import dao.ClienteMapDAO;
import dao.IClienteDAO;
import domain.Cliente;

import java.util.Collection;

public class App {
    public static void main(String[] args) {
        IClienteDAO clienteDAO = new ClienteMapDAO();

        Cliente cliente1 = new Cliente("João Silva", 12345678901L, 11987654321L,
                "Rua A", 100, "São Paulo", "SP");

        Cliente cliente2 = new Cliente("Maria Oliveira", 98765432100L, 21999998888L,
                "Av. Central", 200, "Rio de Janeiro", "RJ");

        clienteDAO.cadastrar(cliente1);
        clienteDAO.cadastrar(cliente2);

        System.out.println("Lista de clientes:");
        Collection<Cliente> clientes = clienteDAO.buscarTodos();
        clientes.forEach(c -> System.out.println(c.getNome() + " - " + c.getCpf()));

        System.out.println("\nConsultando CPF 12345678901:");
        Cliente consultado = clienteDAO.consultar(12345678901L);
        System.out.println(consultado.getNome() + " - " + consultado.getCidade());

        cliente1.setCidade("Campinas");
        clienteDAO.alterar(cliente1);
        System.out.println("\nApós alteração:");
        Cliente alterado = clienteDAO.consultar(12345678901L);
        System.out.println(alterado.getNome() + " - " + alterado.getCidade());

        clienteDAO.excluir(98765432100L);
        System.out.println("\nApós exclusão:");
        clientes = clienteDAO.buscarTodos();
        clientes.forEach(c -> System.out.println(c.getNome() + " - " + c.getCpf()));
    }
}
