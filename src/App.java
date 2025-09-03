import dao.ClienteMapDAO;
import dao.IClienteDAO;
import domain.Cliente;

import javax.swing.*;

public class App {

    private static IClienteDAO iClienteDAO;

    public static void main(String[] args) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(
                null,
                "Digite 1 para cadastro, 2 para consulta, 3 para exclusão, 4 para alteração ou 5 para sair",
                "Menu",
                JOptionPane.INFORMATION_MESSAGE
        );

        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(
                    null,
                    "Opção inválida! Digite 1, 2, 3, 4 ou 5",
                    "Menu",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if ("1".equals(opcao)) { 
                String dados = JOptionPane.showInputDialog(
                        null,
                        "Digite os dados do cliente separados por vírgula:\n" +
                                "Nome, CPF, Telefone, Endereço, Número, Cidade, Estado",
                        "Cadastro",
                        JOptionPane.INFORMATION_MESSAGE
                );
                cadastrar(dados);
            } else if ("2".equals(opcao)) { 
                String cpf = JOptionPane.showInputDialog(
                        null,
                        "Digite o CPF do cliente:",
                        "Consultar",
                        JOptionPane.INFORMATION_MESSAGE
                );
                consultar(cpf);
            } else if ("3".equals(opcao)) { 
                String cpf = JOptionPane.showInputDialog(
                        null,
                        "Digite o CPF do cliente para exclusão:",
                        "Excluir",
                        JOptionPane.INFORMATION_MESSAGE
                );
                excluir(cpf);
            } else if ("4".equals(opcao)) { 
                String cpf = JOptionPane.showInputDialog(
                        null,
                        "Digite o CPF do cliente para alteração:",
                        "Alterar",
                        JOptionPane.INFORMATION_MESSAGE
                );
                alterar(cpf);
            }

            opcao = JOptionPane.showInputDialog(
                    null,
                    "Digite 1 para cadastro, 2 para consulta, 3 para exclusão, 4 para alteração ou 5 para sair",
                    "Menu",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(
                dadosSeparados[0],
                dadosSeparados[1],
                dadosSeparados[2],
                dadosSeparados[3],
                dadosSeparados[4],
                dadosSeparados[5],
                dadosSeparados[6]
        );
        iClienteDAO.cadastrar(cliente);
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
    }

    private static void consultar(String cpf) {
        Cliente cliente = iClienteDAO.consultar(Long.valueOf(cpf));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        }
    }

    private static void excluir(String cpf) {
        iClienteDAO.excluir(Long.valueOf(cpf));
        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
    }

    private static void alterar(String cpf) {
        Cliente cliente = iClienteDAO.consultar(Long.valueOf(cpf));
        if (cliente != null) {
            String novosDados = JOptionPane.showInputDialog(
                    null,
                    "Digite os novos dados do cliente separados por vírgula:\n" +
                            "Nome, CPF, Telefone, Endereço, Número, Cidade, Estado",
                    "Alterar",
                    JOptionPane.INFORMATION_MESSAGE
            );
            String[] dadosSeparados = novosDados.split(",");
            Cliente clienteNovo = new Cliente(
                    dadosSeparados[0],
                    dadosSeparados[1],
                    dadosSeparados[2],
                    dadosSeparados[3],
                    dadosSeparados[4],
                    dadosSeparados[5],
                    dadosSeparados[6]
            );
            iClienteDAO.alterar(clienteNovo);
            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        }
    }

    private static boolean isOpcaoValida(String opcao) {
        return "1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao);
    }

    private static boolean isOpcaoSair(String opcao) {
        return "5".equals(opcao);
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo!");
        System.exit(0);
    }
}
