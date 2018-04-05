package homework.part2;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Writer writer = new Writer(storage, "writer");
        int readersCount = 10;
        Reader[] readers = new Reader[readersCount];

        for (int i = 0; i < readersCount; i++) {
            readers[i] = new Reader(storage, "reader" + i);
        }

        for (Reader reader : readers){
            reader.start();
        }

        writer.start();

    }
}
