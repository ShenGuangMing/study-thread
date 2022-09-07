package p2;


import lombok.extern.slf4j.Slf4j;
import p2.util.FileReader;

@Slf4j
public class Sync {
    public static void main(String[] args) {
        FileReader.read("D:\\Work Space.rar");//同步的,代码是逐个执行的
        log.info("做其他事");
    }
}
