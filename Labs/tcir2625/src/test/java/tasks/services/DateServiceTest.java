package tasks.services;

import org.junit.jupiter.api.*;
import tasks.repository.ArrayTaskList;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class DateServiceTest {
    private ArrayTaskList arrayTaskList;
    private TasksService tasksService;
    private DateService dateService;

    @BeforeEach
    void setUp() {
        arrayTaskList = new ArrayTaskList();
        tasksService = new TasksService(arrayTaskList);
        dateService = new DateService(tasksService);
    }

    @AfterEach
    void tearDown() {
        tasksService = null;
        dateService = null;
        arrayTaskList = null;
    }

    @DisplayName("Test getDateMergedWithTime for invalid time string")
    @Tag("ECP")
    @Test
    void test_ecp_getDateMergedWithTime_test_invalid_time_string() {
        Date date = new Date();
        String time = ":";

        assertThrows(IllegalArgumentException.class, () -> {
            dateService.getDateMergedWithTime(time, date);
        });
    }

    @RepeatedTest(value=3, name="Test getDateMergedWithTime for valid time string")
    @Tag("ECP")
    void test_ecp_getDateMergedWithTime_test_valid_time_string() {
        Date date = new Date();
        String time = "00:00";

        assertDoesNotThrow(() -> {
            dateService.getDateMergedWithTime(time, date);
        });
    }

    @Tag("ECP")
    @Test
    void test_ecp_getDateMergedWithTime_test_invalid_time_hour_number() {
        Date date = new Date();
        String time = "aa:00";

        assertThrows(IllegalArgumentException.class, () -> {
            dateService.getDateMergedWithTime(time, date);
        });
    }

    @Tag("ECP")
    @Test
    void test_ecp_getDateMergedWithTime_test_invalid_time_minutes_number() {
        Date date = new Date();
        String time = "00:aa";

        assertThrows(IllegalArgumentException.class, () -> {
            dateService.getDateMergedWithTime(time, date);
        });
    }

    @Tag("ECP")
    @Test
    void test_ecp_getDateMergedWithTime_test_invalid_time_hour_range() {
        Date date = new Date();
        String time = "30:00";

        assertThrows(IllegalArgumentException.class, () -> {
            dateService.getDateMergedWithTime(time, date);
        });
    }

    @Tag("ECP")
    @Test
    void test_ecp_getDateMergedWithTime_test_invalid_time_minutes_range() {
        Date date = new Date();
        String time = "00:80";

        assertThrows(IllegalArgumentException.class, () -> {
            dateService.getDateMergedWithTime(time, date);
        });
    }

    @Tag("BVA")
    @Test
    void test_bva_getDateMergedWithTime_test_invalid_time_hour_below_lower_bound() {
        Date date = new Date();
        String time = "-1:00";

        assertThrows(IllegalArgumentException.class, () -> {
            dateService.getDateMergedWithTime(time, date);
        });
    }

    @Tag("BVA")
    @Test
    void test_bva_getDateMergedWithTime_test_invalid_time_hour_equal_lower_bound() {
        Date date = new Date();
        String time = "00:00";

        assertDoesNotThrow(() -> {
            dateService.getDateMergedWithTime(time, date);
        });
    }

    @Tag("BVA")
    @Test
    void test_bva_getDateMergedWithTime_test_invalid_time_hour_above_lower_bound() {
        Date date = new Date();
        String time = "01:00";

        assertDoesNotThrow(() -> {
            dateService.getDateMergedWithTime(time, date);
        });
    }

    @Tag("BVA")
    @Test
    void test_bva_getDateMergedWithTime_test_invalid_time_hour_below_upper_bound() {
        Date date = new Date();
        String time = "22:00";

        assertDoesNotThrow(() -> {
            dateService.getDateMergedWithTime(time, date);
        });
    }

    @Tag("BVA")
    @Test
    void test_bva_getDateMergedWithTime_test_invalid_time_hour_equal_upper_bound() {
        Date date = new Date();
        String time = "23:00";

        assertDoesNotThrow(() -> {
            dateService.getDateMergedWithTime(time, date);
        });
    }

    @Tag("BVA")
    @Test
    void test_bva_getDateMergedWithTime_test_invalid_time_hour_above_upper_bound() {
        Date date = new Date();
        String time = "24:00";

        assertThrows(IllegalArgumentException.class, () -> {
            dateService.getDateMergedWithTime(time, date);
        });
    }
}
