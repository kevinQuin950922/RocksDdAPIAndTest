package com.example.scheduled.conf;

import com.example.scheduled.repositorio.KeyVauleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final KeyVauleRepository<String, String> rocksDB;

    public ScheduledTasks(KeyVauleRepository<String, String> rocksDB) {
        this.rocksDB = rocksDB;
    }
    @Autowired
    private Environment env;

    @Scheduled(cron = "${cron.expresion.tiempoScheduled}") // Ejecutar cada minuto
    public void ejecutarTareaProgramada() {
        // Agrega aquí la lógica de tu tarea programada
        System.out.println("Tarea programada ejecutada.");
        System.out.println(env.getProperty("cron.expresion.tiempoScheduled"));
        rocksDB.delete("1");
    }
}