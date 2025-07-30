package com.reactiveProgramming.examples;

import java.time.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Mvcontroller {

  @GetMapping("/demo")
  public Mono<String> greeting() {
    return computeMessage().zipWith(getNameFromDb())
        .map(tuple -> tuple.getT1() + " " + tuple.getT2());
  }

  private Mono<String> computeMessage(){
    return Mono.just("Hello, World!")
        .delayElement(Duration.ofSeconds(5));
  }

  private Mono<String> getNameFromDb(){
    return Mono.just("John Doe")
        .delayElement(Duration.ofSeconds(5));

  }
}
