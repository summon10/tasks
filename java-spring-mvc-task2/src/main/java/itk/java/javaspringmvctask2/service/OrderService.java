package itk.java.javaspringmvctask2.service;

import itk.java.javaspringmvctask2.entity.Order;
import itk.java.javaspringmvctask2.exception.ResourceNotFoundException;
import itk.java.javaspringmvctask2.repository.OrderRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepo orderRepo;

    public Order createNewOrder(Order order) {
        return orderRepo.save(order);
    }


    public Object getOrderById(Long orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
}
