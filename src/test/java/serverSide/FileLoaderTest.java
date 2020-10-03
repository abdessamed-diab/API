package serverSide;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileLoaderTest {
    private FileLoader fileReader;

    @BeforeEach
    public void runBeforeTest() {
        try {
            fileReader = new FileLoader("serverSideFlatFiles/users.csv");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFlatFile() {
        File flatFile = fileReader.getFlatFile();
        assertTrue(flatFile.exists() && flatFile.isFile() && flatFile.canRead() && !flatFile.isHidden());
    }

}
