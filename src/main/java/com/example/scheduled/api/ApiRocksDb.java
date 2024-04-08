package com.example.scheduled.api;

import com.example.scheduled.repositorio.KeyVauleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/rocks")
public class ApiRocksDb {
    private final KeyVauleRepository<String, String> rocksDB;

    public ApiRocksDb(KeyVauleRepository<String, String> rocksDB) {
        this.rocksDB = rocksDB;
    }

    @PostMapping(value = "/{key}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> save(@PathVariable("key") String key, @RequestBody String value) {
        log.info("RocksApi.save");
        rocksDB.save(key, value);
        return ResponseEntity.ok(value);
    }

    @GetMapping(value = "/{key}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> find(@PathVariable("key") String key) {
        log.info("RocksApi.find");
        var result = rocksDB.find(key);
        if(result == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{key}")
    public ResponseEntity<String> delete(@PathVariable("key") String key) {
        log.info("RocksApi.delete");
        rocksDB.delete(key);
        return ResponseEntity.ok(key);
    }
}
