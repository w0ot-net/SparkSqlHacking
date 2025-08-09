package org.apache.ivy.tools.analyser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.resolver.util.FileURLLister;
import org.apache.ivy.plugins.resolver.util.ResolverHelper;
import org.apache.ivy.plugins.resolver.util.URLLister;
import org.apache.ivy.util.Message;

public class JarModuleFinder {
   private String pattern;
   private String filePattern;

   public JarModuleFinder(String pattern) {
      this.pattern = "file:///" + pattern;
      this.filePattern = pattern;
   }

   public JarModule[] findJarModules() {
      List<JarModule> ret = new ArrayList();
      URLLister lister = new FileURLLister();

      try {
         for(String org : ResolverHelper.listTokenValues(lister, this.pattern, "organisation")) {
            String orgPattern = IvyPatternHelper.substituteToken(this.pattern, "organisation", org);

            for(String module : ResolverHelper.listTokenValues(lister, orgPattern, "module")) {
               String modPattern = IvyPatternHelper.substituteToken(orgPattern, "module", module);

               for(String rev : ResolverHelper.listTokenValues(lister, modPattern, "revision")) {
                  File jar = new File(IvyPatternHelper.substitute(this.filePattern, org, module, rev, module, "jar", "jar"));
                  if (jar.exists()) {
                     ret.add(new JarModule(ModuleRevisionId.newInstance(org, module, rev), jar));
                  }
               }
            }
         }
      } catch (Exception e) {
         Message.debug((Throwable)e);
      }

      return (JarModule[])ret.toArray(new JarModule[ret.size()]);
   }

   public static void main(String[] args) {
      JarModule[] mods = (new JarModuleFinder("D:/temp/test2/ivyrep/[organisation]/[module]/[revision]/[artifact].[ext]")).findJarModules();

      for(JarModule mod : mods) {
         System.out.println(mod);
      }

   }
}
