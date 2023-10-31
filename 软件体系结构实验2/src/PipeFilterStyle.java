
import java.io.File;
import java.io.IOException;

class PipeFilterStyle {
    public static void process(File inputFile, File outputFile) throws IOException {
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        Input input = new Input(inputFile, pipe1);
        Shift shift = new Shift(pipe1, pipe2);
        Alphabetizer alphabetizer = new Alphabetizer(pipe2, pipe3);
        Output output = new Output(outputFile, pipe3);
        input.transform();
        shift.transform();
        alphabetizer.transform();
        output.transform();
    }
}

