package org.apache.hadoop.hive.common.cli;

import java.io.BufferedReader;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IOUtils;

public abstract class HiveFileProcessor implements IHiveFileProcessor {
   public int processFile(String fileName) throws IOException {
      BufferedReader bufferedReader = null;

      int var3;
      try {
         bufferedReader = this.loadFile(fileName);
         var3 = this.processReader(bufferedReader);
      } finally {
         IOUtils.closeStream(bufferedReader);
      }

      return var3;
   }

   protected abstract BufferedReader loadFile(String var1) throws IOException;

   protected int processReader(BufferedReader reader) throws IOException {
      StringBuilder qsb = new StringBuilder();

      String line;
      while((line = reader.readLine()) != null) {
         if (!line.startsWith("--")) {
            qsb.append(line);
         }
      }

      return this.processLine(qsb.toString());
   }

   protected int processLine(String line) {
      int lastRet = 0;
      int ret = 0;
      String command = "";

      for(String oneCmd : line.split(";")) {
         if (StringUtils.indexOf(oneCmd, "\\") != -1) {
            command = command + StringUtils.join(oneCmd.split("\\\\"));
         } else {
            command = command + oneCmd;
         }

         if (!StringUtils.isBlank(command)) {
            ret = this.processCmd(command);
            command = "";
            lastRet = ret;
            if (ret != 0) {
               return ret;
            }
         }
      }

      return lastRet;
   }

   protected abstract int processCmd(String var1);
}
