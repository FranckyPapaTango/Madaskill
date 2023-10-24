package com.rafaros.service.impl;

import com.rafaros.domain.Customer;
import com.rafaros.repository.CustomerRepository;
import com.rafaros.service.CustomerService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Customer}.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        log.debug("Request to save Customer : {}", customer);
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        log.debug("Request to update Customer : {}", customer);
        customer.setIsPersisted();
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> partialUpdate(Customer customer) {
        log.debug("Request to partially update Customer : {}", customer);

        return customerRepository
            .findById(customer.getId())
            .map(existingCustomer -> {
                if (customer.getObject() != null) {
                    existingCustomer.setObject(customer.getObject());
                }
                if (customer.getAddress() != null) {
                    existingCustomer.setAddress(customer.getAddress());
                }
                if (customer.getBalance() != null) {
                    existingCustomer.setBalance(customer.getBalance());
                }
                if (customer.getCreated() != null) {
                    existingCustomer.setCreated(customer.getCreated());
                }
                if (customer.getCurrency() != null) {
                    existingCustomer.setCurrency(customer.getCurrency());
                }
                if (customer.getDefaultSource() != null) {
                    existingCustomer.setDefaultSource(customer.getDefaultSource());
                }
                if (customer.getDelinquent() != null) {
                    existingCustomer.setDelinquent(customer.getDelinquent());
                }
                if (customer.getDescription() != null) {
                    existingCustomer.setDescription(customer.getDescription());
                }
                if (customer.getDiscount() != null) {
                    existingCustomer.setDiscount(customer.getDiscount());
                }
                if (customer.getEmail() != null) {
                    existingCustomer.setEmail(customer.getEmail());
                }
                if (customer.getInvoicePrefix() != null) {
                    existingCustomer.setInvoicePrefix(customer.getInvoicePrefix());
                }
                if (customer.getLivemode() != null) {
                    existingCustomer.setLivemode(customer.getLivemode());
                }
                if (customer.getName() != null) {
                    existingCustomer.setName(customer.getName());
                }
                if (customer.getNextInvoiceSequence() != null) {
                    existingCustomer.setNextInvoiceSequence(customer.getNextInvoiceSequence());
                }
                if (customer.getPhone() != null) {
                    existingCustomer.setPhone(customer.getPhone());
                }
                if (customer.getShipping() != null) {
                    existingCustomer.setShipping(customer.getShipping());
                }
                if (customer.getTaxExempt() != null) {
                    existingCustomer.setTaxExempt(customer.getTaxExempt());
                }
                if (customer.getTestClock() != null) {
                    existingCustomer.setTestClock(customer.getTestClock());
                }

                return existingCustomer;
            })
            .map(customerRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> findAll(Pageable pageable) {
        log.debug("Request to get all Customers");
        return customerRepository.findAll(pageable);
    }

    public Page<Customer> findAllWithEagerRelationships(Pageable pageable) {
        return customerRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findOne(String id) {
        log.debug("Request to get Customer : {}", id);
        return customerRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Customer : {}", id);
        customerRepository.deleteById(id);
    }
}
