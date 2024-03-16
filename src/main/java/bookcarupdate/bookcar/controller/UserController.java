package bookcarupdate.bookcar.controller;

import bookcarupdate.bookcar.dto.CreateOrderDTO;
import bookcarupdate.bookcar.dto.UpdateOrderDTO;
import bookcarupdate.bookcar.models.Order;
import bookcarupdate.bookcar.models.Product;
import bookcarupdate.bookcar.models.Store;
import bookcarupdate.bookcar.repositories.ProductRepository;
import bookcarupdate.bookcar.repositories.StoreRepository;
import bookcarupdate.bookcar.services.OrderService;
import bookcarupdate.bookcar.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserController {
    private final ProductRepository productRepository;
    private final OrderService orderService;
    private final ProductService productService;
    private final StoreRepository storeRepository;

    //    Product
    @GetMapping("/get-all-product")
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productRepository.findAll2());
    }

    @GetMapping("/get-product/{id}")
    private ResponseEntity<Optional<Product>> getRoleUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productRepository.findById(id));
    }

    @PostMapping("/create-order")
    private ResponseEntity<Order> createOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        return ResponseEntity.ok(orderService.createOrder(createOrderDTO));
    }

    @GetMapping("/get-order/{id}")
    private ResponseEntity<Optional<Order>> getOrder(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PutMapping("/update-order/{id}")
    private ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody UpdateOrderDTO updateOrderDTO) {
        return ResponseEntity.ok(orderService.updateOrder(updateOrderDTO, id));
    }

    @GetMapping("/get-all-order-by-email-user/{email}")
    private ResponseEntity<List<Order>> getOrderByEmailUser(@PathVariable("email") String email) {
        return ResponseEntity.ok(orderService.getAllOrderByEmailUser(email));
    }

    @GetMapping("/search")
    private ResponseEntity<List<Product>> findByKeyWord(@RequestParam(value = "key", required = false) String key, @RequestParam(value = "start_time", required = false) LocalTime start_time, @RequestParam(value = "start_address", required = false) String start_address, @RequestParam(value = "end_address", required = false) String end_address) {
        System.out.println(start_address + " " + end_address + " " + start_time);
        if (key != null) {
            return ResponseEntity.ok(productService.findByKeyWord(key));
        }
//        return null;
        return ResponseEntity.ok(productService.findByManyKeyWord(start_time, start_address, end_address));
    }

    @GetMapping("/get-owner-name")
    private ResponseEntity<List<Store>> getAllStore() {
        return ResponseEntity.ok(storeRepository.findAll());
    }
}
