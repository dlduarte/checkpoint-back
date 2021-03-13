package br.com.dld.checkpoint.util;

import br.com.dld.checkpoint.forms.auth.RegisterForm;
import br.com.dld.checkpoint.entities.Account;
import br.com.dld.checkpoint.repositories.AccountRepository;
import br.com.dld.checkpoint.services.email.dto.Credential;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author David Duarte
 */
public class AuthenticationHandler {

    public static class Register {

        public static List<String> validate(RegisterForm form, AccountRepository autenticacaoRepository) throws Exception {
            List<String> erros = Password.validate(form.getPassword());

            Optional<Account> optional = autenticacaoRepository.findByUsername(form.getUsername());
            if (optional.isPresent()) {
                erros.add("Usuário já em uso");
            }

            optional = autenticacaoRepository.findByEmail(form.getEmail());
            if (optional.isPresent()) {
                erros.add("Email já em uso");
            }

            return erros;
        }
    }

    public static class Password {

        public static List<String> validate(String password) throws Exception {
            boolean hasDigit = false, hasLow = false, hasUpper = false;
            List<String> errors = new ArrayList();

            if (password.length() >= 8) {
                for (int i = 0; i < password.length(); i++) {
                    char x = password.charAt(i);
                    if (Character.isLetter(x)) {
                        if (Character.isLowerCase(x)) {
                            hasLow = true;
                        } else if (Character.isUpperCase(x)) {
                            hasUpper = true;
                        }
                    } else if (Character.isDigit(x)) {
                        hasDigit = true;
                    }
                    if (hasDigit && hasLow && hasUpper) {
                        break;
                    }
                }

                if (!hasDigit) {
                    errors.add("A senha deve conter números.");
                }

                if (!hasLow) {
                    errors.add(" A senha deve conter letras minúsculas.");
                }

                if (!hasUpper) {
                    errors.add(" A senha deve conter letras maiúsculas.");
                }

                return errors;
            }

            errors.add("A senha deve conter ao menos 8 caracteres.");
            return errors;
        }

        public static String encode(String password) throws Exception {
            return new BCryptPasswordEncoder().encode(password);
        }
    }

    public static class Email {

        public static Credential credential() {
            Credential credential = new Credential();
            credential.setHost("smtp.gmail.com");
            credential.setRequiresSSL(false);
            credential.setRequiresTLS(true);
            credential.setPort(587);
            credential.setUsername("Checkpoint APP");
            credential.setEmail("dldduarte.dev@gmail.com");
            credential.setPassword("@David1995");

            return credential;
        }

        public static String mask(String email) {
            String[] split = email.split("@");
            String before = split[0];
            String after = split[1];
            int length = before.length();

            String first = "";
            if (length > 4) {
                first = before.substring(0, 4);
            } else {
                first = before;
            }

            int count = first.length();
            while (count < length) {
                first += "*";
                count++;
            }

            return first + "@" + after;
        }
    }
}
