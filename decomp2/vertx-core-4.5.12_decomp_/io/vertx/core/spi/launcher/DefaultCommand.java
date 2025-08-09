package io.vertx.core.spi.launcher;

import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Hidden;
import io.vertx.core.cli.annotations.Option;
import java.io.File;
import java.io.PrintStream;
import java.util.List;

public abstract class DefaultCommand implements Command {
   private File cwd;
   protected List systemProperties;
   protected ExecutionContext executionContext;
   protected PrintStream out;

   public File getCwd() {
      return this.cwd != null ? this.cwd : new File(".");
   }

   @Option(
      longName = "cwd",
      argName = "dir"
   )
   @Description("Specifies the current working directory for this command, default set to the Java current directory")
   @Hidden
   public void setCwd(File cwd) {
      this.cwd = cwd;
   }

   @Option(
      longName = "systemProperty",
      shortName = "D",
      argName = "key>=<value"
   )
   @Description("Set a system property")
   @Hidden
   public void setSystemProps(List props) {
      this.systemProperties = props;
   }

   public void setUp(ExecutionContext ec) throws CLIException {
      this.executionContext = ec;
      this.out = this.executionContext.getPrintStream();
      this.applySystemProperties();
   }

   public PrintStream out() {
      return this.executionContext.getPrintStream();
   }

   public void tearDown() throws CLIException {
   }

   protected void applySystemProperties() {
      if (this.systemProperties != null) {
         for(String prop : this.systemProperties) {
            int p = prop.indexOf(61);
            if (p > 0) {
               String key = prop.substring(0, p);
               String val = prop.substring(p + 1);
               System.setProperty(key, val);
            }
         }
      }

   }
}
