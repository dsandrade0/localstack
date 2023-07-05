package info.dsandrade.info.LocalStack.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.dsandrade.info.LocalStack.client.ViacepClient;
import info.dsandrade.info.LocalStack.model.Contrato;
import info.dsandrade.info.LocalStack.model.Endereco;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.model.Message;

@Component
public class QueueMessageHandler {

    @Autowired
    private ViacepClient client;

    @SqsListener({"DEV_CONTRATACAO"})
    public void processMessage(Message message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Contrato contrato = objectMapper.readValue(message.body(), Contrato.class);

        String response = client.consultaCep(contrato.cep());
        Endereco endereco = objectMapper.readValue(response, Endereco.class);
        System.out.println(endereco);
    }
}
