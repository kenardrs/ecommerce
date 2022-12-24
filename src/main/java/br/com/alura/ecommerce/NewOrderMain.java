package br.com.alura.ecommerce;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var dispatcher = new KafkaDispatcher()) {
            for (int i = 0; i < 10; i++) {
                var key = UUID.randomUUID().toString();
                var value = key + ",67523,7894589745";
                dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);

                var email = "Thanks for your order, we are finishing processing";
                dispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);
            }
        }
    }
}
