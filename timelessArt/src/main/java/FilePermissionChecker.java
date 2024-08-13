import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePermissionChecker {
    public static void main(String[] args) {
        checkFilePermissions("src/main/resources/META-INF/resources/privateKey.pem");
        checkFilePermissions("src/main/resources/META-INF/resources/publicKey.pem");
    }

    private static void checkFilePermissions(String filePath) {
        Path path = Paths.get(filePath);
        if (Files.exists(path) && Files.isReadable(path)) {
            System.out.println(filePath + " is readable");
        } else {
            System.out.println(filePath + " is not readable or does not exist");
        }
    }
}
