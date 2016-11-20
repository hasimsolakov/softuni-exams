package softuni.io;

import org.springframework.stereotype.Component;

@Component
public class ConsoleWriter implements Writer {
    @Override
    public void printLine(String line) {
        System.out.println(line);
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }
}
