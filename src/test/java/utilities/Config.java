package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {


    //The reason why we have this Class is because, so we don't have to write this all the time:
    //Properties properties = new Properties();
    //FileInputStream file = new FileInputStream("review.properties");
    //properties.load(file);

    private static Properties properties = new Properties();            //this will be used in this class

    static {           //static block bc we want this to run first

        String path = "configuration.properties";              //creating the path; HERE YOU PUT WHICH PROPERTY YOU WANT TO USE

        try {                                                    //handling the exception
            FileInputStream file = new FileInputStream(path);
            properties.load(file);       //loading the properties file into the properties object
            file.close();              //you are closing the Stream; it is more professional
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found.");
        }

    }

    public static String getProperty(String keyword) {
        return properties.getProperty(keyword);                //reading the values using this method
    }

}
