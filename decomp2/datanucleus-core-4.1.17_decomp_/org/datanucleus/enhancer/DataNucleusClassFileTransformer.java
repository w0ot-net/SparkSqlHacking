package org.datanucleus.enhancer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Map;
import org.datanucleus.util.CommandLine;

public class DataNucleusClassFileTransformer implements ClassFileTransformer {
   protected RuntimeEnhancer enhancer;
   private CommandLine cmd = new CommandLine();

   public DataNucleusClassFileTransformer(String arguments, Map contextProps) {
      this.cmd.addOption("api", "api", "api", "api");
      this.cmd.addOption("generatePK", "generatePK", "<generate-pk>", "Generate PK class where needed?");
      this.cmd.addOption("generateConstructor", "generateConstructor", "<generate-constructor>", "Generate default constructor where needed?");
      this.cmd.addOption("detachListener", "detachListener", "<detach-listener>", "Use Detach Listener?");
      if (arguments != null) {
         this.cmd.parse(arguments.split("[\\s,=]+"));
      }

      String api = this.cmd.getOptionArg("api") != null ? this.cmd.getOptionArg("api") : null;
      if (api == null) {
         api = "JDO";
         DataNucleusEnhancer.LOGGER.debug("ClassFileTransformer API not specified so falling back to JDO. You should specify '-api={API}' when specifying the javaagent");
      }

      this.enhancer = new RuntimeEnhancer(api, contextProps);
      if (this.cmd.hasOption("generateConstructor")) {
         String val = this.cmd.getOptionArg("generateConstructor");
         if (val.equalsIgnoreCase("false")) {
            this.enhancer.unsetClassEnhancerOption("generate-default-constructor");
         }
      }

      if (this.cmd.hasOption("generatePK")) {
         String val = this.cmd.getOptionArg("generatePK");
         if (val.equalsIgnoreCase("false")) {
            this.enhancer.unsetClassEnhancerOption("generate-primary-key");
         }
      }

      if (this.cmd.hasOption("detachListener")) {
         String val = this.cmd.getOptionArg("detachListener");
         if (val.equalsIgnoreCase("true")) {
            this.enhancer.setClassEnhancerOption("generate-detach-listener");
         }
      }

   }

   public static void premain(String agentArguments, Instrumentation instrumentation) {
      instrumentation.addTransformer(new DataNucleusClassFileTransformer(agentArguments, (Map)null));
   }

   public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
      String name = className.replace('/', '.');
      if (name.startsWith("java.")) {
         return null;
      } else if (name.startsWith("javax.")) {
         return null;
      } else if (name.startsWith("org.datanucleus.") && !name.startsWith("org.datanucleus.samples") && !name.startsWith("org.datanucleus.test")) {
         return null;
      } else if (this.cmd.getDefaultArgs() != null && this.cmd.getDefaultArgs().length > 0) {
         String[] classes = this.cmd.getDefaultArgs();

         for(int i = 0; i < classes.length; ++i) {
            if (name.startsWith(classes[i])) {
               return this.enhancer.enhance(name, classfileBuffer, loader);
            }
         }

         return null;
      } else {
         return this.enhancer.enhance(name, classfileBuffer, loader);
      }
   }
}
