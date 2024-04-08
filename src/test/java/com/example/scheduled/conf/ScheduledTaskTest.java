package com.example.scheduled.conf;

import com.example.scheduled.repositorio.KeyVauleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        properties = {
                "cron.expresion.tiempoScheduled=*/1 * * * * *"
        })
@ActiveProfiles("default")
public class ScheduledTaskTest {
    @MockBean
    KeyVauleRepository<String, String> rocksDB;

    @Test
    void validarLlamadoDeScheduled(){
        await().atMost(4, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    verify(rocksDB, times(2)).delete("1");
                });
    }
}
