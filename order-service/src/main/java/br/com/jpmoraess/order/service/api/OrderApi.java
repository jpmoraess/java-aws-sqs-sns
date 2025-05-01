package br.com.jpmoraess.order.service.api;

import br.com.jpmoraess.order.service.api.request.CreateOrderRequest;
import br.com.jpmoraess.order.service.api.response.CreateOrderResponse;
import br.com.jpmoraess.order.service.application.ports.input.CreateOrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderApi {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderApi(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        CreateOrderUseCase.CreateOrderInput input = CreateOrderUseCase
                .CreateOrderInput.of(request.customerId(), request.products());
        CreateOrderUseCase.CreateOrderOutput output = createOrderUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateOrderResponse.of(output.id()));
    }
}
