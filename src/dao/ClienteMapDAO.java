package dao;

import domain.Cliente;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO {

    private Map<Long, Cliente> map;

    public ClienteMapDAO() {
        this.map = new HashMap<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (this.map.containsKey(cliente.getCpf())) {
            return false;
        }
        this.map.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public void excluir(Long cpf) {
        this.map.remove(cpf);
    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente existente = this.map.get(cliente.getCpf());
        if (existente != null) {
            existente.setNome(cliente.getNome());
            existente.setTelefone(cliente.getTelefone());
            existente.setNumero(cliente.getNumero());
            existente.setEndereco(cliente.getEndereco());
            existente.setCidade(cliente.getCidade());
            existente.setEstado(cliente.getEstado());
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }
}
