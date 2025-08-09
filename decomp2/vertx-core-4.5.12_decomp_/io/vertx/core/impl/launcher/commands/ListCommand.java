package io.vertx.core.impl.launcher.commands;

import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.spi.launcher.DefaultCommand;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Name("list")
@Summary("List vert.x applications")
@Description("List all vert.x applications launched with the `start` command")
public class ListCommand extends DefaultCommand {
   private static final Pattern PS = Pattern.compile("-Dvertx.id=(.*)\\s*");
   private static final Pattern FAT_JAR_EXTRACTION = Pattern.compile("-jar (\\S*)");
   private static final Pattern VERTICLE_EXTRACTION = Pattern.compile("run (\\S*)");

   public void run() {
      this.out.println("Listing vert.x applications...");
      List<String> cmd = new ArrayList();
      if (!ExecUtils.isWindows()) {
         try {
            cmd.add("sh");
            cmd.add("-c");
            cmd.add("ps ax | grep \"vertx.id=\"");
            this.dumpFoundVertxApplications(cmd);
         } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace(this.out);
         } catch (Exception e) {
            e.printStackTrace(this.out);
         }
      } else {
         try {
            cmd.add("WMIC");
            cmd.add("PROCESS");
            cmd.add("WHERE");
            cmd.add("CommandLine like '%java.exe%'");
            cmd.add("GET");
            cmd.add("CommandLine");
            cmd.add("/VALUE");
            this.dumpFoundVertxApplications(cmd);
         } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace(this.out);
         } catch (Exception e) {
            e.printStackTrace(this.out);
         }
      }

   }

   private void dumpFoundVertxApplications(List cmd) throws IOException, InterruptedException {
      boolean none = true;
      Process process = (new ProcessBuilder(cmd)).start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      Throwable var5 = null;

      try {
         String line;
         while((line = reader.readLine()) != null) {
            Matcher matcher = PS.matcher(line);
            if (matcher.find()) {
               String id = matcher.group(1);
               String details = extractApplicationDetails(line);
               this.out.println(id + "\t" + details);
               none = false;
            }
         }

         process.waitFor();
      } catch (Throwable var17) {
         var5 = var17;
         throw var17;
      } finally {
         if (reader != null) {
            if (var5 != null) {
               try {
                  reader.close();
               } catch (Throwable var16) {
                  var5.addSuppressed(var16);
               }
            } else {
               reader.close();
            }
         }

      }

      if (none) {
         this.out.println("No vert.x application found.");
      }

   }

   protected static String extractApplicationDetails(String line) {
      Matcher matcher = FAT_JAR_EXTRACTION.matcher(line);
      if (matcher.find()) {
         return matcher.group(1);
      } else {
         matcher = VERTICLE_EXTRACTION.matcher(line);
         return matcher.find() ? matcher.group(1) : "";
      }
   }
}
