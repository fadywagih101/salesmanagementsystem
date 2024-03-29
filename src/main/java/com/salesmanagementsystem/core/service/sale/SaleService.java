package com.salesmanagementsystem.core.service.sale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanagementsystem.core.data.repository.SaleRepository;
import com.salesmanagementsystem.core.data.repository.TransactionRepository;
import com.salesmanagementsystem.core.data.repository.ClientRepository;
import com.salesmanagementsystem.core.data.repository.ProductRepository;
import com.salesmanagementsystem.core.data.repository.UserRepository;
import com.salesmanagementsystem.core.model.client.Client;
import com.salesmanagementsystem.core.model.product.Product;
import com.salesmanagementsystem.core.model.sale.Sale;
import com.salesmanagementsystem.core.model.sale.SaleRequestDTO;
import com.salesmanagementsystem.core.model.transaction.Transaction;
import com.salesmanagementsystem.core.model.transaction.TransactionRequestDTO;
import com.salesmanagementsystem.core.model.user.User;
import com.salesmanagementsystem.core.service.client.ClientService;
import com.salesmanagementsystem.core.service.product.ProductService;

@Service
public class SaleService {

    // @Autowired
    // private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final ClientService clientService;
    private final TransactionRepository transactionRepository;

    public SaleService(SaleRepository saleRepository, ProductService productService, ClientService clientService, TransactionRepository transactionRepository) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.clientService = clientService;
        this.transactionRepository = transactionRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long saleId) {
        return saleRepository.findById(saleId).orElse(null);
    }

    public Sale createSale(SaleRequestDTO saleDTO) {
        Sale sale = new Sale();
        sale.setCreationDate(new Date());
        sale.setClient(clientRepository.getReferenceById(saleDTO.getClientId()));
        sale.setSeller(userRepository.getReferenceById(saleDTO.getSellerId()));
        sale.setTotal(0.0); // Initialize total to zero

        List<TransactionRequestDTO> transactionDTOs = saleDTO.getTransactions();
        for (TransactionRequestDTO transactionDTO : transactionDTOs) {
            Product product = productRepository.getReferenceById(transactionDTO.getProductId());
            if (product.getInitialQuantity() < transactionDTO.getQuantity()) {
                throw new IllegalArgumentException("Insufficient quantity for product: " + product.getName());
            }

            Transaction transaction = new Transaction();
            transaction.setSale(sale);
            transaction.setProduct(product);
            transaction.setQuantity(transactionDTO.getQuantity());
            transaction.setPrice(product.getPrice());
            sale.getTransactions().add(transaction);

            double transactionTotal = transactionDTO.getQuantity() * product.getPrice();
            sale.setTotal(sale.getTotal() + transactionTotal);

            product.setInitialQuantity(product.getInitialQuantity() - transactionDTO.getQuantity());
            productRepository.save(product);
        }

        return saleRepository.save(sale);
    }

    public void deleteSale(Long saleId) {
        saleRepository.deleteById(saleId);
    }

    public int getTotalNumberOfSales(LocalDate startDate, LocalDate endDate) {
        return saleRepository.getTotalNumberOfSales(startDate, endDate);
    }

    public double getTotalRevenue(LocalDate startDate, LocalDate endDate) {
        return saleRepository.getTotalRevenue(startDate, endDate);
    }

    public List<Object[]> getTopSellingProducts(LocalDate startDate, LocalDate endDate) {
        return saleRepository.getTopSellingProducts(startDate, endDate);
    }

    public List<Object[]> getTopPerformingSellers(LocalDate startDate, LocalDate endDate) {
        return saleRepository.getTopPerformingSellers(startDate, endDate);
    }
}
