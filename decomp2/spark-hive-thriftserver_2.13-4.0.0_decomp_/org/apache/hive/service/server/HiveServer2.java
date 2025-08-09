package org.apache.hive.service.server;

import java.util.Properties;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.hadoop.hive.common.JvmPauseMonitor;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hive.common.util.HiveStringUtils;
import org.apache.hive.service.CompositeService;
import org.apache.hive.service.cli.CLIService;
import org.apache.hive.service.cli.thrift.ThriftBinaryCLIService;
import org.apache.hive.service.cli.thrift.ThriftCLIService;
import org.apache.hive.service.cli.thrift.ThriftHttpCLIService;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.NUM_RETRY.;
import org.apache.spark.util.ShutdownHookManager;
import org.apache.spark.util.SparkExitCode;
import scala.runtime.AbstractFunction0;
import scala.runtime.BoxedUnit;

public class HiveServer2 extends CompositeService {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(HiveServer2.class);
   private CLIService cliService;
   private ThriftCLIService thriftCLIService;

   public HiveServer2() {
      super(HiveServer2.class.getSimpleName());
      HiveConf.setLoadHiveServer2Config(true);
   }

   public synchronized void init(HiveConf hiveConf) {
      this.cliService = new CLIService(this);
      this.addService(this.cliService);
      if (isHTTPTransportMode(hiveConf)) {
         this.thriftCLIService = new ThriftHttpCLIService(this.cliService);
      } else {
         this.thriftCLIService = new ThriftBinaryCLIService(this.cliService);
      }

      this.addService(this.thriftCLIService);
      super.init(hiveConf);
      ShutdownHookManager.addShutdownHook(new AbstractFunction0() {
         public BoxedUnit apply() {
            try {
               HiveServer2.LOG.info("Hive Server Shutdown hook invoked");
               HiveServer2.this.stop();
            } catch (Throwable e) {
               HiveServer2.LOG.warn("Ignoring Exception while stopping Hive Server from shutdown hook", e);
            }

            return BoxedUnit.UNIT;
         }
      });
   }

   public static boolean isHTTPTransportMode(HiveConf hiveConf) {
      String transportMode = System.getenv("HIVE_SERVER2_TRANSPORT_MODE");
      if (transportMode == null) {
         transportMode = hiveConf.getVar(ConfVars.HIVE_SERVER2_TRANSPORT_MODE);
      }

      return transportMode != null && transportMode.equalsIgnoreCase("http");
   }

   public synchronized void start() {
      super.start();
   }

   public synchronized void stop() {
      LOG.info("Shutting down HiveServer2");
      super.stop();
   }

   private static void startHiveServer2() throws Throwable {
      long attempts = 0L;
      long maxAttempts = 1L;

      while(true) {
         LOG.info("Starting HiveServer2");
         HiveConf hiveConf = new HiveConf();
         maxAttempts = hiveConf.getLongVar(ConfVars.HIVE_SERVER2_MAX_START_ATTEMPTS);
         HiveServer2 server = null;

         try {
            server = new HiveServer2();
            server.init(hiveConf);
            server.start();

            try {
               JvmPauseMonitor pauseMonitor = new JvmPauseMonitor(hiveConf);
               pauseMonitor.start();
            } catch (Throwable t) {
               LOG.warn("Could not initiate the JvmPauseMonitor thread.", t);
            }

            return;
         } catch (Throwable throwable) {
            if (server != null) {
               try {
                  server.stop();
               } catch (Throwable t) {
                  LOG.info("Exception caught when calling stop of HiveServer2 before retrying start", t);
               } finally {
                  HiveServer2 var20 = null;
               }
            }

            if (++attempts >= maxAttempts) {
               throw new Error("Max start attempts " + maxAttempts + " exhausted", throwable);
            }

            LOG.warn("Error starting HiveServer2 on attempt {}, will retry in 60 seconds", throwable, new MDC[]{MDC.of(.MODULE$, attempts)});

            try {
               Thread.sleep(60000L);
            } catch (InterruptedException var15) {
               Thread.currentThread().interrupt();
            }
         }
      }
   }

   public static void main(String[] args) {
      HiveConf.setLoadHiveServer2Config(true);
      ServerOptionsProcessor oproc = new ServerOptionsProcessor("hiveserver2");
      ServerOptionsProcessorResponse oprocResponse = oproc.parse(args);
      HiveStringUtils.startupShutdownMessage(HiveServer2.class, args, LOG.getSlf4jLogger());
      oprocResponse.getServerOptionsExecutor().execute();
   }

   public static class ServerOptionsProcessor {
      private final Options options = new Options();
      private CommandLine commandLine;
      private final String serverName;
      private final StringBuilder debugMessage = new StringBuilder();

      public ServerOptionsProcessor(String serverName) {
         this.serverName = serverName;
         Options var10000 = this.options;
         OptionBuilder.withValueSeparator();
         OptionBuilder.hasArgs(2);
         OptionBuilder.withArgName("property=value");
         OptionBuilder.withLongOpt("hiveconf");
         OptionBuilder.withDescription("Use value for given property");
         var10000.addOption(OptionBuilder.create());
         this.options.addOption(new Option("H", "help", false, "Print help information"));
      }

      public ServerOptionsProcessorResponse parse(String[] argv) {
         try {
            this.commandLine = (new GnuParser()).parse(this.options, argv);
            Properties confProps = this.commandLine.getOptionProperties("hiveconf");

            for(String propKey : confProps.stringPropertyNames()) {
               this.debugMessage.append("Setting " + propKey + "=" + confProps.getProperty(propKey) + ";\n");
               System.setProperty(propKey, confProps.getProperty(propKey));
            }

            if (this.commandLine.hasOption('H')) {
               return new ServerOptionsProcessorResponse(new HelpOptionExecutor(this.serverName, this.options));
            }
         } catch (ParseException e) {
            System.err.println("Error starting HiveServer2 with given arguments: ");
            System.err.println(e.getMessage());
            System.exit(-1);
         }

         return new ServerOptionsProcessorResponse(new StartOptionExecutor());
      }

      StringBuilder getDebugMessage() {
         return this.debugMessage;
      }
   }

   static class ServerOptionsProcessorResponse {
      private final ServerOptionsExecutor serverOptionsExecutor;

      ServerOptionsProcessorResponse(ServerOptionsExecutor serverOptionsExecutor) {
         this.serverOptionsExecutor = serverOptionsExecutor;
      }

      ServerOptionsExecutor getServerOptionsExecutor() {
         return this.serverOptionsExecutor;
      }
   }

   static class HelpOptionExecutor implements ServerOptionsExecutor {
      private final Options options;
      private final String serverName;

      HelpOptionExecutor(String serverName, Options options) {
         this.options = options;
         this.serverName = serverName;
      }

      public void execute() {
         (new HelpFormatter()).printHelp(this.serverName, this.options);
         System.exit(SparkExitCode.EXIT_SUCCESS());
      }
   }

   static class StartOptionExecutor implements ServerOptionsExecutor {
      public void execute() {
         try {
            HiveServer2.startHiveServer2();
         } catch (Throwable t) {
            HiveServer2.LOG.error("Error starting HiveServer2", t);
            System.exit(-1);
         }

      }
   }

   interface ServerOptionsExecutor {
      void execute();
   }
}
