package serverSide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;

public class FileLoader {
    private File flatFile;

    public FileLoader(final String flatFileName) throws IllegalArgumentException, URISyntaxException {
        URL flatFileUrl = ClassLoader.getSystemResource(flatFileName);
        if (flatFileUrl == null) {
            throw new IllegalArgumentException("flat file: "+flatFileName+" doesn't exist, check available files under classpath repository." );
        }

        flatFile = new File(flatFileUrl.toURI());
    }

    public BufferedReader generateBufferedReader() {
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(flatFile);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            return bufferedReader;
        }
    }

    public File getFlatFile() {
        return flatFile;
    }
}
