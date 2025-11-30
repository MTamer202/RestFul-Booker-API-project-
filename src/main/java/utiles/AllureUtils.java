package utiles;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static utiles.LogsUtils.Logs_Path;

public class AllureUtils {

    public static final String Allure_Results_Path = "test-outputs/allure-results";
    private AllureUtils(){
        super();
    }
    public static void attatchLogsToAllureReport(){
        try {
            File logFile = FilesUtils.getLatestFile(Logs_Path);
            if(!logFile.exists()){
                LogsUtils.warn("Log file doesn't exist: "+Logs_Path);
                return;
            }
            Allure.addAttachment("Logs.log", Files.readString(logFile.toPath()));
            LogsUtils.info("Logs attatched To allure report");
        } catch (Exception e) {
            LogsUtils.error("Failed To attach logs To allure report "+e.getMessage());
        }
    }

}
