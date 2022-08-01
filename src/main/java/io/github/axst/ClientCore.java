package io.github.axst;

import lombok.Getter;

public class ClientCore {

    @Getter
    public static final ClientCore instance = new ClientCore();

    public void initializeClient() {
        System.out.println("My First Injection!");
    }
}
