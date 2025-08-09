package io.vertx.core.impl.launcher.commands;

import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.spi.launcher.DefaultCommand;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Name("version")
@Summary("Displays the version.")
@Description("Prints the vert.x core version used by the application.")
public class VersionCommand extends DefaultCommand {
   private static final Logger log = LoggerFactory.getLogger(VersionCommand.class);
   private static String version;

   public void run() throws CLIException {
      log.info(getVersion());
   }

   public static String getVersion() {
      if (version != null) {
         return version;
      } else {
         try {
            InputStream is = VersionCommand.class.getClassLoader().getResourceAsStream("META-INF/vertx/vertx-version.txt");
            Throwable var1 = null;

            Object var4;
            try {
               if (is == null) {
                  throw new IllegalStateException("Cannot find vertx-version.txt on classpath");
               }

               Scanner scanner = (new Scanner(is, "UTF-8")).useDelimiter("\\A");
               Throwable var3 = null;

               try {
                  var4 = version = scanner.hasNext() ? scanner.next().trim() : "";
               } catch (Throwable var29) {
                  var4 = var29;
                  var3 = var29;
                  throw var29;
               } finally {
                  if (scanner != null) {
                     if (var3 != null) {
                        try {
                           scanner.close();
                        } catch (Throwable var28) {
                           var3.addSuppressed(var28);
                        }
                     } else {
                        scanner.close();
                     }
                  }

               }
            } catch (Throwable var31) {
               var1 = var31;
               throw var31;
            } finally {
               if (is != null) {
                  if (var1 != null) {
                     try {
                        is.close();
                     } catch (Throwable var27) {
                        var1.addSuppressed(var27);
                     }
                  } else {
                     is.close();
                  }
               }

            }

            return (String)var4;
         } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
         }
      }
   }
}
