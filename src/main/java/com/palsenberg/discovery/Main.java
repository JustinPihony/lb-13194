package com.palsenberg.discovery;

import akka.actor.ActorSystem;
import akka.cluster.Cluster;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import akka.management.javadsl.AkkaManagement;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.compat.java8.FutureConverters;

public class Main {
    private final Config config;

    private Main(String[] args) {
        this.config = ConfigFactory.load();

        final ClassLoader classLoader = this.getClass().getClassLoader();
        final ActorSystem system = ActorSystem.create("demo", config, classLoader);

        AkkaManagement.get(system).start();
        ClusterBootstrap.get(system).start();

        Cluster.get(system).registerOnMemberUp(() -> {
            System.out.println("CLUSTER UP");
        });

        FutureConverters.toJava(system.whenTerminated()).thenAccept(r -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        new Main(args);
    }
}
