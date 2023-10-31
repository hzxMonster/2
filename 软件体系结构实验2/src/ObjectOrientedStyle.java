
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class ObjectOrientedStyle {
    public static void process(File inputFile, File outputFile) throws IOException {
        ArrayList<String> lineTxt = new ArrayList<String>();
        BufferedReader inputFileReader = null;
        try {
            inputFileReader = new BufferedReader(new FileReader(inputFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = inputFileReader.readLine()) != null) {
                lineTxt.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputFileReader.close();

        ArrayList<String> kwicList = new ArrayList<String>();
        for (String txt : lineTxt) {
            StringTokenizer token = new StringTokenizer(txt);
            ArrayList<String> tokens = new ArrayList<String>();
            int i = 0;
            int count = token.countTokens();
            while (i < count) {
                tokens.add(token.nextToken());
                i++;
            }

            for (int j = 0; j < count; j++) {
                StringBuffer lineBuffer = new StringBuffer();
                int index = j;
                for (int f = 0; f < count; f++) {
                    if (index >= count)
                        index = 0;
                    lineBuffer.append(tokens.get(index));
                    lineBuffer.append(" ");
                    index++;
                }
                String tmp = lineBuffer.toString();
                kwicList.add(tmp);
            }
        }

        Collections.sort(kwicList, new AlphabetizerComparator());

        BufferedWriter outputFileWriter = null;
        try {
            outputFileWriter = new BufferedWriter(new FileWriter(outputFile));
            for (String kwicLine : kwicList) {
                outputFileWriter.write(kwicLine + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputFileWriter != null) {
                    outputFileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class AlphabetizerComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1 == null && o2 == null) {
                throw new NullPointerException();
            }
            int compareValue = 0;
            char o1c = o1.toLowerCase().charAt(0); //忽略大小写
            char o2c = o2.toLowerCase().charAt(0); //忽略大小写
            compareValue = o1c - o2c;
            return compareValue;
        }
    }
}
