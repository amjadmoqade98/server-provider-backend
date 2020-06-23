package com.Exalt.MultiThreading;

import com.Exalt.MultiThreading.Domain.Dom.ServerDom;
import com.Exalt.MultiThreading.Domain.Dom.ServerProvider;
import com.Exalt.MultiThreading.Domain.Mapper.ServerMapper;
import com.Exalt.MultiThreading.Infrastructure.Repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class MultiThreadingApplication implements CommandLineRunner {

    @Autowired
    ServerRepository serverRepository ;

    @Autowired
    ServerMapper serverMapper ;

    public static void main(String[] args) {
        SpringApplication.run(MultiThreadingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //load the servers from the database to the local storage
        serverRepository.findAll().forEach(serverDao -> {
            ServerDom serverDom = serverMapper.serverDaoToDom(serverDao);
            ServerProvider.serversLocal.put(serverDom.getId(),serverDom);
        });
    }
}