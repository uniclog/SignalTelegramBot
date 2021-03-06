package local.uniclog.service;

import local.uniclog.model.TelegramUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataManagementTest {
    private final DataManagement management = new DataManagement("data_test.json");
    private final ArrayList<TelegramUser> data = new ArrayList<>();

    @BeforeEach
    void setUp() {
        management.setData(data);
        management.save();
    }

    @Test
    @DisplayName("проверка save метода сохранение в файл")
    void save() {
        assertTrue(management.save());
    }

    @Test
    @DisplayName("проверка load метода выгрузка из файла")
    void load() {
        assertTrue(management.load());
        assertEquals(data, management.getData());

        var dataAddNewUser = management.getData();
        dataAddNewUser.add(TelegramUser.builder().id(1231231231L).userName("Name3").subscriber(false).build());
        management.setData(dataAddNewUser);

        assertTrue(management.load());
        assertTrue(management.load());
        assertEquals(dataAddNewUser, management.getData());
    }

    @Test
    @DisplayName("проверка add метода")
    void add() {
        assertTrue(management.save());

        var newUser = TelegramUser.builder().id(5675675757L).userName("Name5").subscriber(true).build();
        assertTrue(management.add(newUser));
        assertTrue(management.load());
        assertEquals(management.getData().get(0), newUser);
    }

    @Test
    @DisplayName("проверка remove метода")
    void remove() {
        var newUser = TelegramUser.builder().id(5675675757L).userName("Name1").subscriber(true).build();
        assertTrue(management.add(newUser));
        assertTrue(management.load());
        assertEquals(management.getData().get(0), newUser);
        assertTrue(management.remove(newUser));
        assertTrue(management.load());
        assertEquals(management.getData(), new ArrayList<>());
    }
}