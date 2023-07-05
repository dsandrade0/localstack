package info.dsandrade.info.LocalStack.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "${client.viacep.url}")
public interface ViacepClient {

    @GetMapping("/{cep}/json")
    public String consultaCep(@PathVariable String cep);
}
