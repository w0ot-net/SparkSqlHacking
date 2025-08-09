package org.apache.zookeeper.cli;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.annotation.Nullable;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class CommandUsageHelper {
   public static String getUsage(String commandSyntax, @Nullable Options options) {
      StringBuilder buffer = new StringBuilder();
      buffer.append(commandSyntax);
      if (options != null && !options.getOptions().isEmpty()) {
         buffer.append(System.lineSeparator());
         StringWriter out = new StringWriter();
         HelpFormatter formatter = new HelpFormatter();
         formatter.printOptions(new PrintWriter(out), formatter.getWidth(), options, formatter.getLeftPadding(), formatter.getDescPadding());
         buffer.append(out);
      }

      return buffer.toString();
   }
}
