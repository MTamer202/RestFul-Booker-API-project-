package utiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FilesUtils {
    private FilesUtils(){
        super();
    }

    public static File getLatestFile(String folderPath){
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if(files==null || files.length==0){
            LogsUtils.warn("Failed to list files in it"+folderPath);
            return null;
        }
        File latestFile = files[0];
        for(File file : files){
            if (file.lastModified()>latestFile.lastModified()){
                latestFile = file;
            }

        }
    return latestFile;

    }

public static void deleteFiles(File dirPath){
        if (dirPath == null || !dirPath.exists()){
            LogsUtils.warn("Directory does not exists: "+dirPath);
        }
        File[] filesList = dirPath.listFiles();
        if(filesList==null){
            LogsUtils.warn("Failed to list files in it"+dirPath);
            return;
        }
        for(File file : filesList){
            if(file.isDirectory()){
                deleteFiles(file);
            }
            else {
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    LogsUtils.error("Failed to delete file: "+file);
            }
            }
        }

}

}
