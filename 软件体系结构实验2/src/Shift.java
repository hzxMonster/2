
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Shift extends Filter {
    public Shift(Pipe input, Pipe output) {
        super(input, output);
    }

    @Override
    protected void transform() throws IOException {
        while (input.hashNextLine()) {
            StringTokenizer token = new StringTokenizer(input.readerLine());
            ArrayList<String> tokens = new ArrayList<String>();
            int i = 0;
            int count = token.countTokens();
            while (i < count) {
                tokens.add(token.nextToken());
                i++;
            }

            for (i = 0; i < count; i++) {
                StringBuffer lineBuffer = new StringBuffer();
                int index = i;
                for (int f = 0; f < count; f++) {
                    if (index >= count)
                        index = 0;
                    lineBuffer.append(tokens.get(index));
                    lineBuffer.append(" ");
                    index++;
                }
                String tmp = lineBuffer.toString();
                output.writerLine(tmp);
            }
        }
        input.closeReader();
        output.closeWriter();
    }
}
