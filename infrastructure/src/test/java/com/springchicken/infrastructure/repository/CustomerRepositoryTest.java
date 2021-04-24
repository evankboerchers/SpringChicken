package com.springchicken.infrastructure.repository;
import com.springchicken.infrastructure.entities.CustomersEntity;
import com.springchicken.infrastructure.repos.CustomersRepository;
import com.springchicken.infrastructure.InfrastructureTestConfiguration;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.math.BigDecimal;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ContextConfiguration(classes = InfrastructureTestConfiguration.class)
public class CustomerRepositoryTest
{

    @Autowired
    CustomersRepository repository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    DataSource dataSource;


    @Test
    public void injectedComponentsAreNotNull()
    {
        assertThat(dataSource).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(repository).isNotNull();
    }

    @Test
    public void emptyRepositoryTest()
    {
        assertThat(repository.findAll().isEmpty()).isTrue();
    }


    @Test
    public void testRepository()
    {
        String testName = "testName";
        CustomersEntity testEntity = new CustomersEntity(123, testName, "testFirst",
                "testLast", "testPhone", "testAddress1", "testAddress2", "testCity", "testState",
                "testPostal", "testCountry", new BigDecimal("0.1"));

        repository.save(testEntity);
        AssertionsForClassTypes.assertThat(repository.findAll().get(0).getCustomerName()).isEqualTo(testName);
    }
}
