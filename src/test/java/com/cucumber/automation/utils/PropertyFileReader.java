package com.cucumber.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is to write a function 'returnPropVal' to access the properties file.
 * The function 'returnVal' will be passed a key from some other class and will return the value for that key. *
 * @ propertyFilePath - should be absolute from root folder of the project till .properties file
 *                      i.e for automation.properties - "automation"
 *                      for ObjectRepository.properties - "ObjectRepository"
 *
 *    How to use it:
 *    final PropertyFileReader propObj = new PropertyFileReader();
      final String str = propObj.returnPropVal("Key");
 */

public class PropertyFileReader
{
    // get a new properties object:
    final Properties properties = new Properties();
    String value = null;
    
    public String returnPropVal(String key){
          try {

              properties.load(new FileInputStream("src/test/resources/properties/tribune.properties"));
              // get property value based on key:
              value = properties.getProperty( key );

        }
        catch ( final FileNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch ( final IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return value;
        }
}
