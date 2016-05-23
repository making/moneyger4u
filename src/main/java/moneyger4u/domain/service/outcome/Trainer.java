package moneyger4u.domain.service.outcome;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;

@Component
@Slf4j
public class Trainer {
    @Autowired
    AsyncRestTemplate restTemplate;

    public void train(String outcomeName, Integer categoryId) {
        try {
            restTemplate.postForEntity(UriComponentsBuilder.fromUriString("https://predict.cfapps.pez.pivotal.io/api/train")
                            .queryParam("outcomeName", UriUtils.encodeQueryParam(outcomeName, "UTF-8"))
                            .queryParam("categoryId", categoryId).build(true).toUri(),
                    HttpEntity.EMPTY, String.class)
                    .addCallback(s -> log.info("success : {}", s), e -> log.error("error", e));
        } catch (UnsupportedEncodingException e) {
            log.error("error", e);
        }
    }
}
