package org.apache.hive.beeline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputFile {
   final File file;
   final PrintWriter out;

   public OutputFile(String filename) throws IOException {
      this.file = new File(filename);
      this.out = new PrintWriter(new FileWriter(this.file));
   }

   public String toString() {
      return this.file.getAbsolutePath();
   }

   public void addLine(String command) {
      this.out.println(command);
   }

   public void println(String command) {
      this.out.println(command);
   }

   public void print(String command) {
      this.out.print(command);
   }

   public void close() throws IOException {
      this.out.close();
   }
}
