package local.uniclog.service;

import com.google.gson.reflect.TypeToken;
import local.uniclog.model.TelegramUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataProviderTest {
    DataProvider data;

    @BeforeEach
    void setUp() {
        data = new DataProvider();
    }

    @Test
    @DisplayName("DataProvider save/load тест с объектом")
    void dataProviderTestWithObject() {
        var user = TelegramUser.builder().id(1231231231L).userName("Name").subscriber(true).build();
        var json = data.save(user);
        System.out.println("Json = " + json);
        var obj = data.load(json, TelegramUser.class);
        System.out.println("Object = " + obj.toString());

        assertEquals(user, obj);
    }

    @Test
    @DisplayName("DataProvider save/load тест со списком")
    void dataProviderTestWithObjectList() {
        var userList = List.of(
                TelegramUser.builder().id(1231231231L).userName("Name1").subscriber(true).build(),
                TelegramUser.builder().id(1231231231L).userName("Name2").subscriber(false).build()
        );
        var json = data.save(userList);
        System.out.println("Json = " + json);
        var list =  data.load(json, new TypeToken<List<TelegramUser>>() {}.getType());
        System.out.println("Object = " + list.toString());

        assertEquals(userList, list);
    }
}