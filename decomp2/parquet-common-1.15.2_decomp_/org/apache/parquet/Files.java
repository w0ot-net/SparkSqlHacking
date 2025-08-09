package org.apache.parquet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/** @deprecated */
@Deprecated
public final class Files {
   private Files() {
   }

   public static List readAllLines(File file, Charset charset) throws IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));

      try {
         List<String> result = new ArrayList();

         while(true) {
            String line = reader.readLine();
            if (line == null) {
               Object var8 = result;
               return (List)var8;
            }

            result.add(line);
         }
      } finally {
         reader.close();
      }
   }
}
