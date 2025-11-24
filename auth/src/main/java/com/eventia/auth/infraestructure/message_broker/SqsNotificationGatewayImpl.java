package com.eventia.auth.infraestructure.message_broker;

import com.eventia.auth.domain.model.Notificacion;
import com.eventia.auth.domain.model.gateway.NotificationGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
@RequiredArgsConstructor
public class SqsNotificationGatewayImpl implements NotificationGateway {

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;

    private final String queueUrl = "https://sqs.us-east-1.amazonaws.com/540261961779/menssagenotiEventia";

    @Override
    public void enviarMensaje(Notificacion mensajeCola) {
        System.out.println("Enviando mensaje a SQS: " + mensajeCola);
        try {
            String mensaje = objectMapper.writeValueAsString(mensajeCola);
            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(mensaje)
                    .build();

            sqsClient.sendMessage(request);
            System.out.println("Mensaje enviado a SQS exitosamente");
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializando evento SQS", e);
        }
    }
}
