package jodd.format;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Scanf {
   protected static BufferedReader in;

   public static String scanf() {
      try {
         return in.readLine();
      } catch (IOException var1) {
         return null;
      }
   }

   static {
      in = new BufferedReader(new InputStreamReader(System.in));
   }
}
