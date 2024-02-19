package io.serock;


abstract class Shit {
    void print() {};
}

public class Main extends Shit {
    @Override
    void print() {
        super.print();
    }
}