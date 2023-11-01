package com.rafaros.service.impl;

import com.rafaros.domain.Product;
import com.rafaros.repository.ProductRepository;
import com.rafaros.service.ProductService;
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
 * Service Implementation for managing {@link Product}.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        log.debug("Request to save Product : {}", product);
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        log.debug("Request to update Product : {}", product);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> partialUpdate(Product product) {
        log.debug("Request to partially update Product : {}", product);

        return productRepository
            .findById(product.getId())
            .map(existingProduct -> {
                if (product.getObject() != null) {
                    existingProduct.setObject(product.getObject());
                }
                if (product.getActive() != null) {
                    existingProduct.setActive(product.getActive());
                }
                if (product.getCreated() != null) {
                    existingProduct.setCreated(product.getCreated());
                }
                if (product.getDefaultPrice() != null) {
                    existingProduct.setDefaultPrice(product.getDefaultPrice());
                }
                if (product.getDescription() != null) {
                    existingProduct.setDescription(product.getDescription());
                }
                if (product.getLivemode() != null) {
                    existingProduct.setLivemode(product.getLivemode());
                }
                if (product.getName() != null) {
                    existingProduct.setName(product.getName());
                }
                if (product.getShippable() != null) {
                    existingProduct.setShippable(product.getShippable());
                }
                if (product.getStatementDescriptor() != null) {
                    existingProduct.setStatementDescriptor(product.getStatementDescriptor());
                }
                if (product.getTaxCode() != null) {
                    existingProduct.setTaxCode(product.getTaxCode());
                }
                if (product.getUnitLabel() != null) {
                    existingProduct.setUnitLabel(product.getUnitLabel());
                }
                if (product.getUpdated() != null) {
                    existingProduct.setUpdated(product.getUpdated());
                }
                if (product.getUrl() != null) {
                    existingProduct.setUrl(product.getUrl());
                }
                if (product.getSku() != null) {
                    existingProduct.setSku(product.getSku());
                }
                if (product.getTitle() != null) {
                    existingProduct.setTitle(product.getTitle());
                }
                if (product.getLinkToGenericPhotoFile() != null) {
                    existingProduct.setLinkToGenericPhotoFile(product.getLinkToGenericPhotoFile());
                }
                if (product.getAvailableSizes() != null) {
                    existingProduct.setAvailableSizes(product.getAvailableSizes());
                }
                if (product.getCurrencyFormat() != null) {
                    existingProduct.setCurrencyFormat(product.getCurrencyFormat());
                }
                if (product.getIsFreeShipping() != null) {
                    existingProduct.setIsFreeShipping(product.getIsFreeShipping());
                }
                if (product.getPrice() != null) {
                    existingProduct.setPrice(product.getPrice());
                }
                if (product.getStyle() != null) {
                    existingProduct.setStyle(product.getStyle());
                }
                if (product.getInstallments() != null) {
                    existingProduct.setInstallments(product.getInstallments());
                }

                return existingProduct;
            })
            .map(productRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll(pageable);
    }

    /**
     *  Get all the products where LineItem is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Product> findAllWhereLineItemIsNull() {
        log.debug("Request to get all products where LineItem is null");
        return StreamSupport
            .stream(productRepository.findAll().spliterator(), false)
            .filter(product -> product.getLineItem() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllwp() {
        return productRepository.findAll();
    }
}
