package info.dsandrade.info.LocalStack.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.dsandrade.info.LocalStack.client.ViacepClient;
import info.dsandrade.info.LocalStack.converter.TopicMessage;
import info.dsandrade.info.LocalStack.model.Contrato;
import info.dsandrade.info.LocalStack.model.Endereco;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
public class QueueMessageHandler {
    private static final Logger LOG = LoggerFactory.getLogger(QueueMessageHandler.class);

    @Autowired
    private ViacepClient client;

    @SqsListener(value = {"${spring.cloud.sqs.contratacao}"})
    public void processMessage(Message<TopicMessage> message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Contrato contrato = objectMapper.readValue(message.getPayload().body(), Contrato.class);

        Endereco endereco = client.consultaCep(contrato.cep());
        LOG.info("Fila 1 " + endereco.toString());
    }


    @SqsListener(value = {"${spring.cloud.sqs.bemVindo}"})
    public void processMessageTwo(Message<TopicMessage> message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Contrato contrato = objectMapper.readValue(message.getPayload().body(), Contrato.class);

        Endereco endereco = client.consultaCep(contrato.cep());
        LOG.info("Fila bem vindo " + endereco.toString());
    }
}
