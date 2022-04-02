package com.bootcamp.wallet.resource;

import com.bootcamp.wallet.dto.PaymentDto;
import com.bootcamp.wallet.entity.Movement;
import com.bootcamp.wallet.entity.Payment;
import com.bootcamp.wallet.request.MovementRequest;
import com.bootcamp.wallet.service.PaymentServiceImpl;
import com.bootcamp.wallet.util.MapperUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class PaymentResource extends MapperUtil {

    @Autowired
    private PaymentServiceImpl paymentService;

    public Mono<PaymentDto> create(PaymentDto paymentDto) {
        Payment payment = map(paymentDto, Payment.class);
        payment.setId(new ObjectId().toString());
        payment.setCreatedAt(LocalDateTime.now());
        Mono<Payment> entity = paymentService.save(payment);
        return entity.map(x -> map(x, PaymentDto.class));
    }

    public Mono<PaymentDto> update(PaymentDto paymentDto) {
        return paymentService.findById(paymentDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(p -> {
                    Payment payment = map(paymentDto, Payment.class);
                    payment.setUpdateAt(LocalDateTime.now());
                    return  paymentService.save(payment)
                            .map(x -> map(x, PaymentDto.class));
                });
    }

    public Mono<PaymentDto> findById(String id) {
        return paymentService.findById(id)
                .switchIfEmpty(Mono.error(new Exception()))
                .map(x -> map(x, PaymentDto.class));
    }

    public Flux<PaymentDto> findAll(){
        return paymentService.findAll().map(x -> map(x, PaymentDto.class));
    }

    public Mono<Void> delete(PaymentDto paymentDto) {
        return paymentService.findById(paymentDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> paymentService.deleteById(paymentDto.getId()));
    }

    public Mono<PaymentDto> addMovement(MovementRequest movementRequest){
        return paymentService.findById(movementRequest.getIdWallet())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap( x -> {
                    Movement movement = new Movement();
                    movement.setAmount(movementRequest.getAmount());
                    movement.setType(movementRequest.getType());
                    movement.setClient(movementRequest.getClient());
                    movement.setDate(LocalDateTime.now());
                    if (x.getMovementList() != null) {
                        x.getMovementList().add(movement);
                    }else {
                        ArrayList<Movement> movementArrayList = new ArrayList<>();
                        movementArrayList.add(movement);
                        x.setMovementList(movementArrayList);
                    }
                    return paymentService.save(x)
                            .map(y -> map(x, PaymentDto.class));
                });
    }
}
