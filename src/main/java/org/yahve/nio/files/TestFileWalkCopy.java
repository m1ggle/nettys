package org.yahve.nio.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFileWalkCopy {
    public static void main(String[] args) throws IOException {
        String source = "D:\\pythonProject";
        String target = "D:\\pythonProject_bak";

        Files.walk(Paths.get(source)).forEach(path -> {
            try {
                String targetDirName = path.toString().replace(source, target);
                if (Files.isDirectory(path)){
                    Files.createDirectory(Paths.get(targetDirName));
                } else if (Files.isRegularFile(path)) {
                    Files.copy(path, Paths.get(targetDirName));

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
