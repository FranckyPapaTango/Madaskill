package com.rafaros.service.impl;

import com.rafaros.domain.Commande;
import com.rafaros.repository.CommandeRepository;
import com.rafaros.service.CommandeService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Commande}.
 */
@Service
@Transactional
public class CommandeServiceImpl implements CommandeService {

    private final Logger log = LoggerFactory.getLogger(CommandeServiceImpl.class);

    private final CommandeRepository commandeRepository;

    public CommandeServiceImpl(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Commande save(Commande commande) {
        log.debug("Request to save Commande : {}", commande);
        return commandeRepository.save(commande);
    }

    @Override
    public Commande update(Commande commande) {
        log.debug("Request to update Commande : {}", commande);
        return commandeRepository.save(commande);
    }

    @Override
    public Optional<Commande> partialUpdate(Commande commande) {
        log.debug("Request to partially update Commande : {}", commande);

        return commandeRepository
            .findById(commande.getId())
            .map(existingCommande -> {
                if (commande.getDateCommande() != null) {
                    existingCommande.setDateCommande(commande.getDateCommande());
                }
                if (commande.getTva() != null) {
                    existingCommande.setTva(commande.getTva());
                }
                if (commande.getTaxesTotales() != null) {
                    existingCommande.setTaxesTotales(commande.getTaxesTotales());
                }
                if (commande.getMontantAmountTtc() != null) {
                    existingCommande.setMontantAmountTtc(commande.getMontantAmountTtc());
                }
                if (commande.getIsPayedIsFacture() != null) {
                    existingCommande.setIsPayedIsFacture(commande.getIsPayedIsFacture());
                }

                return existingCommande;
            })
            .map(commandeRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Commande> findAll(Pageable pageable) {
        log.debug("Request to get all Commandes");
        return commandeRepository.findAll(pageable);
    }

    /**
     *  Get all the commandes where Customer is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Commande> findAllWhereCustomerIsNull() {
        log.debug("Request to get all commandes where Customer is null");
        return StreamSupport
            .stream(commandeRepository.findAll().spliterator(), false)
            .filter(commande -> commande.getCustomer() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Commande> findOne(Long id) {
        log.debug("Request to get Commande : {}", id);
        return commandeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Commande : {}", id);
        commandeRepository.deleteById(id);
    }
}
