package org.antlr.v4.runtime.misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;

public class InterpreterDataReader {
   public static InterpreterData parseFile(String fileName) {
      InterpreterData result = new InterpreterData();
      result.ruleNames = new ArrayList();

      try {
         BufferedReader br = new BufferedReader(new FileReader(fileName));

         try {
            List<String> literalNames = new ArrayList();
            List<String> symbolicNames = new ArrayList();
            String line = br.readLine();
            if (!line.equals("token literal names:")) {
               throw new RuntimeException("Unexpected data entry");
            }

            while((line = br.readLine()) != null && !line.isEmpty()) {
               literalNames.add(line.equals("null") ? "" : line);
            }

            line = br.readLine();
            if (!line.equals("token symbolic names:")) {
               throw new RuntimeException("Unexpected data entry");
            }

            while((line = br.readLine()) != null && !line.isEmpty()) {
               symbolicNames.add(line.equals("null") ? "" : line);
            }

            result.vocabulary = new VocabularyImpl((String[])literalNames.toArray(new String[0]), (String[])symbolicNames.toArray(new String[0]));
            line = br.readLine();
            if (!line.equals("rule names:")) {
               throw new RuntimeException("Unexpected data entry");
            }

            while((line = br.readLine()) != null && !line.isEmpty()) {
               result.ruleNames.add(line);
            }

            line = br.readLine();
            if (line.equals("channel names:")) {
               result.channels = new ArrayList();

               while((line = br.readLine()) != null && !line.isEmpty()) {
                  result.channels.add(line);
               }

               line = br.readLine();
               if (!line.equals("mode names:")) {
                  throw new RuntimeException("Unexpected data entry");
               }

               result.modes = new ArrayList();

               while((line = br.readLine()) != null && !line.isEmpty()) {
                  result.modes.add(line);
               }
            }

            line = br.readLine();
            if (!line.equals("atn:")) {
               throw new RuntimeException("Unexpected data entry");
            }

            line = br.readLine();
            String[] elements = line.substring(1, line.length() - 1).split(",");
            int[] serializedATN = new int[elements.length];

            for(int i = 0; i < elements.length; ++i) {
               serializedATN[i] = Integer.parseInt(elements[i].trim());
            }

            ATNDeserializer deserializer = new ATNDeserializer();
            result.atn = deserializer.deserialize(serializedATN);
         } catch (Throwable var10) {
            try {
               br.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }

            throw var10;
         }

         br.close();
      } catch (IOException var11) {
      }

      return result;
   }

   public static class InterpreterData {
      ATN atn;
      Vocabulary vocabulary;
      List ruleNames;
      List channels;
      List modes;
   }
}
