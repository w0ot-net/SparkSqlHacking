package org.apache.ivy.core.check;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolveEngine;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.plugins.parser.ModuleDescriptorParserRegistry;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.util.Message;

public class CheckEngine {
   private CheckEngineSettings settings;
   private ResolveEngine resolveEngine;

   public CheckEngine(CheckEngineSettings settings, ResolveEngine resolveEngine) {
      this.settings = settings;
      this.resolveEngine = resolveEngine;
   }

   public boolean check(URL ivyFile, String resolvername) {
      try {
         boolean result = true;
         ModuleDescriptor md = ModuleDescriptorParserRegistry.getInstance().parseDescriptor(this.settings, ivyFile, this.settings.doValidate());
         if (resolvername != null) {
            DependencyResolver resolver = this.settings.getResolver(resolvername);
            Set<Artifact> artifacts = new HashSet();

            for(String conf : md.getConfigurationsNames()) {
               artifacts.addAll(Arrays.asList(md.getArtifacts(conf)));
            }

            for(Artifact artifact : artifacts) {
               if (!resolver.exists(artifact)) {
                  Message.info("declared publication not found: " + artifact);
                  result = false;
               }
            }
         }

         ResolveData data = new ResolveData(this.resolveEngine, new ResolveOptions());

         for(DependencyDescriptor dd : md.getDependencies()) {
            for(String masterConf : dd.getModuleConfigurations()) {
               if (!"*".equals(masterConf.trim()) && md.getConfiguration(masterConf) == null) {
                  Message.info("dependency required in non existing conf for " + ivyFile + " \n\tin " + dd + ": " + masterConf);
                  result = false;
               }
            }

            DependencyResolver resolver = this.settings.getResolver(dd.getDependencyRevisionId());
            ResolvedModuleRevision rmr = resolver.getDependency(dd, data);
            if (rmr == null) {
               Message.info("dependency not found in " + ivyFile + ":\n\t" + dd);
               result = false;
            } else {
               for(String depConf : dd.getDependencyConfigurations(md.getConfigurationsNames())) {
                  if (!Arrays.asList(rmr.getDescriptor().getConfigurationsNames()).contains(depConf)) {
                     Message.info("dependency configuration is missing for " + ivyFile + "\n\tin " + dd + ": " + depConf);
                     result = false;
                  }

                  for(Artifact art : rmr.getDescriptor().getArtifacts(depConf)) {
                     if (!resolver.exists(art)) {
                        Message.info("dependency artifact is missing for " + ivyFile + "\n\t in " + dd + ": " + art);
                        result = false;
                     }
                  }
               }
            }
         }

         return result;
      } catch (ParseException e) {
         Message.info("parse problem on " + ivyFile, e);
         return false;
      } catch (IOException e) {
         Message.info("io problem on " + ivyFile, e);
         return false;
      } catch (Exception e) {
         Message.info("problem on " + ivyFile, e);
         return false;
      }
   }
}
