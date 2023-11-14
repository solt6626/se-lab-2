package hu.bme.mit.spaceship;

import java.util.HashMap;
import java.util.Map;


public class EnvVariableProvider{
    // Add values here you would like to test with
    public static Map<String, String> test = new HashMap<>();

    public static String getEnvVariable(String variableName) {
        if(test.containsKey(variableName)){
            return test.get(variableName);
        }
        else{
            return System.getenv(variableName);
        }
    }
}
