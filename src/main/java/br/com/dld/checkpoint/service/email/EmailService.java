package br.com.dld.checkpoint.service.email;

import br.com.dld.checkpoint.service.email.dto.Email;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author David Duarte
 */
@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private static String url;

    @Value("${mail.service.endpoint}")
    public void setUrl(String url) {
        EmailService.url = url;
    }

    public static HttpStatus send(Email email) throws Exception {

        logger.info("Chamada para api de email");
        logger.info("Solicitado para: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity request = new HttpEntity(email, headers);
        ResponseEntity response = new RestTemplate().exchange(
                url + "/send",
                HttpMethod.POST,
                request,
                String.class
        );

        logger.info("Resultado: " + response.getStatusCode().value() + " " + response.getStatusCode().name());
        return response.getStatusCode();
    }

}
