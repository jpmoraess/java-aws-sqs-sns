package br.com.jpmoraess.order.service.api;

import br.com.jpmoraess.order.service.api.request.CreateOrderRequest;
import br.com.jpmoraess.order.service.api.response.CreateOrderResponse;
import br.com.jpmoraess.order.service.application.ports.input.CreateOrderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order API", description = "Order API")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderApi {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderApi(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @Operation(summary = "Create a new order",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Order created successfully",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CreateOrderResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid request data",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            })
    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        CreateOrderUseCase.CreateOrderInput input = CreateOrderUseCase
                .CreateOrderInput.of(request.customerId(), request.products());
        CreateOrderUseCase.CreateOrderOutput output = createOrderUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateOrderResponse.of(output.id()));
    }
}
