package org.apache.spark.launcher;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class SparkSubmitOptionParser {
   protected final String CLASS = "--class";
   protected final String CONF = "--conf";
   protected final String DEPLOY_MODE = "--deploy-mode";
   protected final String DRIVER_CLASS_PATH = "--driver-class-path";
   protected final String DRIVER_DEFAULT_CLASS_PATH = "--driver-default-class-path";
   protected final String DRIVER_CORES = "--driver-cores";
   protected final String DRIVER_JAVA_OPTIONS = "--driver-java-options";
   protected final String DRIVER_LIBRARY_PATH = "--driver-library-path";
   protected final String DRIVER_MEMORY = "--driver-memory";
   protected final String EXECUTOR_MEMORY = "--executor-memory";
   protected final String FILES = "--files";
   protected final String JARS = "--jars";
   protected final String KILL_SUBMISSION = "--kill";
   protected final String MASTER = "--master";
   protected final String REMOTE = "--remote";
   protected final String NAME = "--name";
   protected final String PACKAGES = "--packages";
   protected final String PACKAGES_EXCLUDE = "--exclude-packages";
   protected final String PROPERTIES_FILE = "--properties-file";
   protected final String LOAD_SPARK_DEFAULTS = "--load-spark-defaults";
   protected final String PROXY_USER = "--proxy-user";
   protected final String PY_FILES = "--py-files";
   protected final String REPOSITORIES = "--repositories";
   protected final String STATUS = "--status";
   protected final String TOTAL_EXECUTOR_CORES = "--total-executor-cores";
   protected final String HELP = "--help";
   protected final String SUPERVISE = "--supervise";
   protected final String USAGE_ERROR = "--usage-error";
   protected final String VERBOSE = "--verbose";
   protected final String VERSION = "--version";
   protected final String ARCHIVES = "--archives";
   protected final String EXECUTOR_CORES = "--executor-cores";
   protected final String KEYTAB = "--keytab";
   protected final String NUM_EXECUTORS = "--num-executors";
   protected final String PRINCIPAL = "--principal";
   protected final String QUEUE = "--queue";
   final String[][] opts = new String[][]{{"--archives"}, {"--class"}, {"--conf", "-c"}, {"--deploy-mode"}, {"--driver-class-path"}, {"--driver-cores"}, {"--driver-default-class-path"}, {"--driver-java-options"}, {"--driver-library-path"}, {"--driver-memory"}, {"--executor-cores"}, {"--executor-memory"}, {"--files"}, {"--jars"}, {"--keytab"}, {"--kill"}, {"--master"}, {"--remote"}, {"--name"}, {"--num-executors"}, {"--packages"}, {"--exclude-packages"}, {"--principal"}, {"--properties-file"}, {"--proxy-user"}, {"--py-files"}, {"--queue"}, {"--repositories"}, {"--status"}, {"--total-executor-cores"}};
   final String[][] switches = new String[][]{{"--help", "-h"}, {"--supervise"}, {"--usage-error"}, {"--verbose", "-v"}, {"--version"}, {"--load-spark-defaults"}};

   protected final void parse(List args) {
      Pattern eqSeparatedOpt = Pattern.compile("(--[^=]+)=(.+)");
      int idx = 0;

      for(idx = 0; idx < args.size(); ++idx) {
         String arg = (String)args.get(idx);
         String value = null;
         Matcher m = eqSeparatedOpt.matcher(arg);
         if (m.matches()) {
            arg = m.group(1);
            value = m.group(2);
         }

         String name = this.findCliOption(arg, this.opts);
         if (name != null) {
            if (value == null) {
               if (idx == args.size() - 1) {
                  throw new IllegalArgumentException(String.format("Missing argument for option '%s'.", arg));
               }

               ++idx;
               value = (String)args.get(idx);
            }

            if (!this.handle(name, value)) {
               break;
            }
         } else {
            name = this.findCliOption(arg, this.switches);
            if (name != null) {
               if (!this.handle(name, (String)null)) {
                  break;
               }
            } else if (!this.handleUnknown(arg)) {
               break;
            }
         }
      }

      if (idx < args.size()) {
         ++idx;
      }

      this.handleExtraArgs(args.subList(idx, args.size()));
   }

   protected boolean handle(String opt, String value) {
      throw new UnsupportedOperationException();
   }

   protected boolean handleUnknown(String opt) {
      throw new UnsupportedOperationException();
   }

   protected void handleExtraArgs(List extra) {
      throw new UnsupportedOperationException();
   }

   private String findCliOption(String name, String[][] available) {
      for(String[] candidates : available) {
         for(String candidate : candidates) {
            if (candidate.equals(name)) {
               return candidates[0];
            }
         }
      }

      return null;
   }
}
