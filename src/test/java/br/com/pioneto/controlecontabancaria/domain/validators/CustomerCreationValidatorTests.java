package br.com.pioneto.controlecontabancaria.domain.validators;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.pioneto.controlecontabancaria.api.model.CustomerCreationDto;
import br.com.pioneto.controlecontabancaria.domain.customer.CustomerCreationValidator;
import br.com.pioneto.controlecontabancaria.domain.validators.seeds.CustomerCreationSeeds;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCreationValidatorTests {

    @InjectMocks
    private CustomerCreationValidator customerCreationValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest(name="{index} - {0}")
    @MethodSource()
    void validateCustomerCreationTest(String description,
                                      CustomerCreationDto customerCreation,
                                      boolean expected,
                                      List<String> errorMessages){
        ValidationResult result = customerCreationValidator.validate(customerCreation);

        assertEquals(expected, result.isValid());
        assertEquals(errorMessages.size(), result.getErrors().size());
    }

    private static Stream<Arguments> validateCustomerCreationTest(){
        return Stream.of(
                Arguments.of("WHEN customer properties is empty, EXPECT three error messages.",
                        CustomerCreationSeeds.getEmptyCustomCreation(),
                        false,
                        Arrays.asList("Customer must have a name.",
                                "Customer must have an email address.",
                                "Customer must have a cpf.")
                ),
                Arguments.of("WHEN customer contains a name, EXPECT two error messages.",
                        CustomerCreationSeeds.getCustomerCreationWithName(),
                        false,
                        Arrays.asList("Customer must have an email address.",
                                "Customer must have a cpf.")
                ),
                Arguments.of("WHEN customer contains a name and an email, EXPECT one error message.",
                        CustomerCreationSeeds.getCustomerCreationWithNameAndEmail(),
                        false,
                        Arrays.asList("Customer must have a cpf.")
                ),
                Arguments.of("WHEN customer contains all properties, EXPECT no error messages.",
                        CustomerCreationSeeds.getCustomerCreation(),
                        true,
                        Arrays.asList()
                )
        );
    }
}
