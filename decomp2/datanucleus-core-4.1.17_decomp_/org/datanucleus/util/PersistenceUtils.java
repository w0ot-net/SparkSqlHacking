package org.datanucleus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.datanucleus.Configuration;
import org.datanucleus.exceptions.NucleusUserException;

public class PersistenceUtils {
   public static synchronized Properties setPropertiesUsingFile(String filename) {
      if (filename == null) {
         return null;
      } else {
         Properties props = new Properties();
         File file = new File(filename);
         if (file.exists()) {
            try {
               InputStream is = new FileInputStream(file);
               props.load(is);
               is.close();
            } catch (FileNotFoundException e) {
               throw (new NucleusUserException(Localiser.msg("008014", filename), e)).setFatal();
            } catch (IOException e) {
               throw (new NucleusUserException(Localiser.msg("008014", filename), e)).setFatal();
            }
         } else {
            try {
               InputStream is = Configuration.class.getClassLoader().getResourceAsStream(filename);
               props.load(is);
               is.close();
            } catch (Exception e) {
               throw (new NucleusUserException(Localiser.msg("008014", filename), e)).setFatal();
            }
         }

         return props;
      }
   }
}
