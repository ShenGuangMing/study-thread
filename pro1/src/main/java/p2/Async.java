package p2;

import lombok.extern.slf4j.Slf4j;
import p2.util.FileReader;

@Slf4j
public class Async {
    public static void main(String[] args) {
        new Thread(() -> FileReader.read("D:\\a1.txt")).start();
        log.info("做其他事");
    }
}
