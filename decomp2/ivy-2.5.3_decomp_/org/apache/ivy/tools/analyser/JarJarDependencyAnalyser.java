package org.apache.ivy.tools.analyser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.util.Message;

public class JarJarDependencyAnalyser implements DependencyAnalyser {
   private File jarjarjarLocation;

   public JarJarDependencyAnalyser(File jarjarjarLocation) {
      this.jarjarjarLocation = jarjarjarLocation;
   }

   public ModuleDescriptor[] analyze(JarModule[] modules) {
      StringBuilder jarjarCmd = (new StringBuilder("java -jar \"")).append(this.jarjarjarLocation.getAbsolutePath()).append("\" --find --level=jar ");
      Map<String, JarModule> jarModulesMap = new HashMap();
      Map<ModuleRevisionId, DefaultModuleDescriptor> mds = new HashMap();

      for(JarModule jarModule : modules) {
         jarModulesMap.put(jarModule.getJar().getAbsolutePath(), jarModule);
         DefaultModuleDescriptor md = DefaultModuleDescriptor.newBasicInstance(jarModule.getMrid(), new Date(jarModule.getJar().lastModified()));
         mds.put(jarModule.getMrid(), md);
         jarjarCmd.append("\"").append(jarModule.getJar().getAbsolutePath()).append("\"");
         jarjarCmd.append(File.pathSeparator);
      }

      if (modules.length > 0) {
         jarjarCmd.setLength(jarjarCmd.length() - 1);
      }

      Message.verbose("jarjar command: " + jarjarCmd);

      try {
         Process p = Runtime.getRuntime().exec(jarjarCmd.toString());
         BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

         String line;
         while((line = r.readLine()) != null) {
            String[] deps = line.split(" -> ");
            JarModule module = (JarModule)jarModulesMap.get(deps[0]);
            JarModule dependency = (JarModule)jarModulesMap.get(deps[1]);
            if (!module.getMrid().getModuleId().equals(dependency.getMrid().getModuleId())) {
               Message.verbose(module.getMrid() + " depends on " + dependency.getMrid());
               DefaultModuleDescriptor md = (DefaultModuleDescriptor)mds.get(module.getMrid());
               DefaultDependencyDescriptor dd = new DefaultDependencyDescriptor(md, dependency.getMrid(), false, false, true);
               dd.addDependencyConfiguration("default", "default");
               md.addDependency(dd);
            }
         }
      } catch (IOException e) {
         Message.debug((Throwable)e);
      }

      return (ModuleDescriptor[])mds.values().toArray(new ModuleDescriptor[mds.values().size()]);
   }

   public static void main(String[] args) {
      JarJarDependencyAnalyser a = new JarJarDependencyAnalyser(new File("D:/temp/test2/jarjar-0.7.jar"));
      a.analyze((new JarModuleFinder("D:/temp/test2/ivyrep/[organisation]/[module]/[revision]/[artifact].[ext]")).findJarModules());
   }
}
