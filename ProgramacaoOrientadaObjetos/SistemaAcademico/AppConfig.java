package br.univali.kob.poo1.aula05;

import java.time.format.DateTimeFormatter;

/**
 * @author Odolir Junior <odolir@edu.univali.br>
 * @date   16/09/2017
 */
public class AppConfig {

    
    public static final String APP_NAME ;
    
    public static final String APP_VERSION;
    
    public static final DateTimeFormatter DATE_FORMAT; 
    
    public static final String NEW_LINE;
    
    
    private static final Object SETTINGS[] = new Object[10];
    
    static{
        loadSettings();
        APP_NAME = (String)SETTINGS[0];
        APP_VERSION = (String)SETTINGS[1];
        DATE_FORMAT = (DateTimeFormatter)SETTINGS[2];
        NEW_LINE = (String)SETTINGS[3];
    }
    
    private static void loadSettings(){
        SETTINGS[0] = "Sistema Academico";
        SETTINGS[1] = "1.0";
        SETTINGS[2] = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SETTINGS[3] = System.getProperty("line.separator");
    }
}
