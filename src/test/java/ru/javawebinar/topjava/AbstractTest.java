package ru.javawebinar.topjava;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by apyreev on 14-Apr-17.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractTest {

    static {
        SLF4JBridgeHandler.install();
    }

    private static Map<String, String> testMap = new ConcurrentHashMap<>();

    private class TimeRule implements TestRule {

        @Override
        public Statement apply(Statement base, Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    LocalDateTime startTime = LocalDateTime.now();
                    base.evaluate();
                    long duration = startTime.until(LocalDateTime.now(), ChronoUnit.MILLIS);
                    String className = description.getClassName();
                    String testName = description.getMethodName();
                    System.out.println(
                            String.format("Test finished. \n * Class - %s. Methjd - %s. Duration - %d ms.",
                                    className, testName, duration)
                    );
                    String test = className + '-' + testName;
                    String durationString = duration + "ms";
                    testMap.put(test, durationString);
                }
            };
        }
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Rule
    public final TimeRule timeRule = new TimeRule();

    @AfterClass
    public static void after() {
        System.out.println("Test duration report.");
        for (Map.Entry pair : testMap.entrySet()) {
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }
        System.out.println("Finish.");
    }
}
