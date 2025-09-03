package dao;

import domain.Cliente;

public interface IClienteDAO {
    Boolean cadastrar(Cliente cliente);

    void excluir(Long cpf);

    void alterar(Cliente cliente);

    Cliente consultar(Long cpf);
}
