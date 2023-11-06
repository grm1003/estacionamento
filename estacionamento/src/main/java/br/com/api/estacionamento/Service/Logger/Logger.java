package br.com.api.estacionamento.Service.Logger;

public class Logger {
    private Logger() {} // proibir chamada new
    private static Logger instance;

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void println(String message) {
        System.out.println(message);
    }
}
