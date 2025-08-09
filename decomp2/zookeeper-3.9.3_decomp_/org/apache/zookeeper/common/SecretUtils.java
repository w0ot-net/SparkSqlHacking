package org.apache.zookeeper.common;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SecretUtils {
   private static final Logger LOG = LoggerFactory.getLogger(SecretUtils.class);

   private SecretUtils() {
   }

   public static char[] readSecret(String pathToFile) {
      LOG.info("Reading secret from {}", pathToFile);

      try {
         String secretValue = new String(Files.readAllBytes(Paths.get(pathToFile)), StandardCharsets.UTF_8);
         return secretValue.endsWith(System.lineSeparator()) ? secretValue.substring(0, secretValue.length() - System.lineSeparator().length()).toCharArray() : secretValue.toCharArray();
      } catch (Throwable e) {
         LOG.error("Exception occurred when reading secret from file {}", pathToFile, e);
         throw new IllegalStateException("Exception occurred when reading secret from file " + pathToFile, e);
      }
   }
}
