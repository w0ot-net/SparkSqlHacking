package io.vertx.core.impl.launcher.commands;

import io.vertx.core.cli.annotations.Argument;
import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Hidden;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Option;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.spi.launcher.DefaultCommand;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Name("stop")
@Summary("Stop a vert.x application")
@Description("This command stops a vert.x application started with the `start` command. The command requires the application id as argument. Use the `list` command to get the list of applications")
public class StopCommand extends DefaultCommand {
   private String id;
   private boolean redeploy;
   private static final Pattern PS = Pattern.compile("([0-9]+)\\s.*-Dvertx.id=.*");

   @Argument(
      index = 0,
      argName = "vertx.id",
      required = false
   )
   @Description("The vert.x application id")
   public void setApplicationId(String id) {
      this.id = id;
   }

   @Option(
      longName = "redeploy",
      flag = true
   )
   @Hidden
   public void setRedeploy(boolean redeploy) {
      this.redeploy = redeploy;
   }

   public void run() {
      if (this.id == null) {
         this.out.println("Application id not specified...");
         this.executionContext.execute("list");
      } else {
         this.out.println("Stopping vert.x application '" + this.id + "'");
         if (ExecUtils.isWindows()) {
            this.terminateWindowsApplication();
         } else {
            this.terminateLinuxApplication();
         }

      }
   }

   private void terminateLinuxApplication() {
      String pid = this.pid();
      if (pid == null) {
         this.out.println("Cannot find process for application using the id '" + this.id + "'.");
         if (!this.redeploy) {
            ExecUtils.exitBecauseOfProcessIssue();
         }

      } else {
         List<String> cmd = new ArrayList();
         cmd.add("kill");
         cmd.add(pid);

         try {
            int result = (new ProcessBuilder(cmd)).start().waitFor();
            this.out.println("Application '" + this.id + "' terminated with status " + result);
            if (!this.redeploy) {
               ExecUtils.exit(result);
            }
         } catch (Exception e) {
            this.out.println("Failed to stop application '" + this.id + "'");
            e.printStackTrace(this.out);
            if (!this.redeploy) {
               ExecUtils.exitBecauseOfProcessIssue();
            }
         }

      }
   }

   private void terminateWindowsApplication() {
      String filter = "Name LIKE 'java%' AND CommandLine LIKE '%-Dvertx.id=" + this.id + "%'";
      String command = "\"Get-CimInstance -ClassName Win32_Process -Filter \\\"" + filter + "\\\" | Invoke-CimMethod -MethodName Terminate\"";
      List<String> cmd = Arrays.asList("powershell", "-Command", command);

      try {
         Process process = (new ProcessBuilder(cmd)).start();
         int result = process.waitFor();
         this.out.println("Application '" + this.id + "' terminated with status " + result);
         if (!this.redeploy) {
            ExecUtils.exit(result);
         }
      } catch (Exception e) {
         this.out.println("Failed to stop application '" + this.id + "'");
         e.printStackTrace(this.out);
         if (!this.redeploy) {
            ExecUtils.exitBecauseOfProcessIssue();
         }
      }

   }

   private String pid() {
      try {
         Process process = (new ProcessBuilder(Arrays.asList("sh", "-c", "ps ax | grep \"" + this.id + "\""))).start();
         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
         Throwable var3 = null;

         try {
            String line;
            while((line = reader.readLine()) != null) {
               Matcher matcher = PS.matcher(line);
               if (matcher.find()) {
                  String var6 = matcher.group(1);
                  return var6;
               }
            }

            process.waitFor();
            return null;
         } catch (Throwable var18) {
            var3 = var18;
            throw var18;
         } finally {
            if (reader != null) {
               if (var3 != null) {
                  try {
                     reader.close();
                  } catch (Throwable var17) {
                     var3.addSuppressed(var17);
                  }
               } else {
                  reader.close();
               }
            }

         }
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         e.printStackTrace(this.out);
      } catch (Exception e) {
         e.printStackTrace(this.out);
      }

      return null;
   }
}
