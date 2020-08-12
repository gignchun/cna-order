package OrderShipping;

import OrderShipping.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    /*  20200812 사용자 배송상태 Update */
    @StreamListener(KafkaProcessor.INPUT)
    public void onShipped(@Payload Shipped shipped) {

        Optional<Order> orderOptional = orderRepository.findById(shipped.getOrderId());
        Order order = orderOptional.get();
        order.setStatus(shipped.getStatus());

        orderRepository.save(order);
    }





}
