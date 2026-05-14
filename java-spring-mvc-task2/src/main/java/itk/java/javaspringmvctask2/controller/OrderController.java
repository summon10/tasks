package itk.java.javaspringmvctask2.controller;

import itk.java.javaspringmvctask2.entity.Order;
import itk.java.javaspringmvctask2.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @PostMapping("/orders/newOrder")
    public ResponseEntity <Order> newOrder (
            @Valid @RequestBody String order
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderService.createNewOrder(objectMapper.readValue(order, Order.class)));


    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<String>  getOrderInfo (
            @Valid @PathVariable Long orderId
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(objectMapper.writeValueAsString(orderService.getOrderById(orderId)));
    }
}


