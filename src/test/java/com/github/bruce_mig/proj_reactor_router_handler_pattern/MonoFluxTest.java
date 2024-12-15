package com.github.bruce_mig.proj_reactor_router_handler_pattern;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
        Mono<?> monoString = Mono.just("hello")
                .then(Mono.error(new RuntimeException("Exception occured")))
                .log();
        monoString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
        Flux<String> fluxString = Flux.just("hello", "world", "bonjour", "tout le monde")
                .concatWithValues("bienvenue")
                .concatWith(Flux.error(new RuntimeException("Exception occured")))
                .concatWithValues("cloud")
                .log();
        fluxString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }
}
