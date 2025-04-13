package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Optional<Cliente> buscarPorIdComBloqueio(Integer cdCliente, Integer cdEmpresa) {
        try {
            ClienteId id = new ClienteId(cdCliente, cdEmpresa);
            logger.info("Buscando cliente com bloqueio: cdCliente={}, cdEmpresa={}", cdCliente, cdEmpresa);

            // Forçar bloqueio com um UPDATE dummy
            int updated = entityManager.createQuery(
                "UPDATE Cliente c SET c.nome = c.nome WHERE c.id.cdCliente = :cdCliente AND c.id.cdEmpresa = :cdEmpresa")
                .setParameter("cdCliente", cdCliente)
                .setParameter("cdEmpresa", cdEmpresa)
                .executeUpdate();

            if (updated == 0) {
                logger.warn("Cliente não encontrado: cdCliente={}, cdEmpresa={}", cdCliente, cdEmpresa);
                return Optional.empty();
            }

            // Carregar o cliente após o bloqueio
            Cliente cliente = entityManager.find(Cliente.class, id);
            if (cliente != null) {
                logger.info("Cliente encontrado e bloqueado: {}", cliente.getNome());
                return Optional.of(cliente);
            } else {
                logger.warn("Cliente não encontrado após bloqueio: cdCliente={}, cdEmpresa={}", cdCliente, cdEmpresa);
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error("Erro ao buscar cliente com bloqueio: cdCliente={}, cdEmpresa={}, erro={}", 
                        cdCliente, cdEmpresa, e.getMessage(), e);
            return Optional.empty();
        }
    }

    public Cliente getClienteById(Integer cdCliente, Integer cdEmpresa) {
        ClienteId id = new ClienteId(cdCliente, cdEmpresa);
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.orElse(null);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void salvar(Cliente cliente) {
        logger.info("Salvando cliente: cdCliente={}, cdEmpresa={}, nome={}", 
                   cliente.getCdCliente(), cliente.getCdEmpresa(), cliente.getNome());
        clienteRepository.save(cliente);
        logger.info("Cliente salvo com sucesso.");
    }

    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        logger.info("Chamando clienteRepository.findAll()");
        List<Cliente> clientes = clienteRepository.findAll();
        logger.info("Resultado de findAll: {} clientes encontrados", clientes.size());
        return clientes;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void cancelarEdicao(Integer cdCliente, Integer cdEmpresa) {
        logger.info("Cancelando edição do cliente: cdCliente={}, cdEmpresa={}", cdCliente, cdEmpresa);
        // Rollback da transação será feito automaticamente, liberando o bloqueio
    }
}