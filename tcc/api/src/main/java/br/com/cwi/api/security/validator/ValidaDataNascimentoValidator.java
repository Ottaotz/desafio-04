package br.com.cwi.api.security.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;

import static java.time.LocalDate.now;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Component
public class ValidaDataNascimentoValidator {

    public void validar(LocalDate dataNascimento) {
        if (calculaIdade(dataNascimento) < 16) {
            throw new ResponseStatusException(NOT_ACCEPTABLE,
                    "Idade mínima para realizar o cadastro é de 16 anos.");
        }
    }

    private int calculaIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, now()).getYears();
    }
}
