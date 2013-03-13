package am.ik.moneyger4u.domain.repository.outcome;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import am.ik.moneyger4u.domain.model.DailyOutcomeCategory;
import am.ik.moneyger4u.domain.model.ParentOutcomeCategory;

import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ParentOutcomeCategoryRepositoryTest {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected ParentOutcomeCategoryRepository parentOutcomeCategoryRepository;

    private DateTime now = new DateTime();

    @Before
    @Rollback(value = false)
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFindAll01() {
        List<ParentOutcomeCategory> categories = parentOutcomeCategoryRepository
                .findAll();
        for (ParentOutcomeCategory c : categories) {
            System.out.println(c.getCategoryName());
            for (DailyOutcomeCategory dc : c.getDailyOutcomeCategoryList()) {
                System.out.println("\t" + dc.getCategoryName());
            }
        }
    }

}
