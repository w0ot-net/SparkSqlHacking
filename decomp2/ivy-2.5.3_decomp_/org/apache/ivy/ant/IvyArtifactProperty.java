package org.apache.ivy.ant;

import java.io.File;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.plugins.report.XmlReportParser;
import org.apache.ivy.util.StringUtils;
import org.apache.tools.ant.BuildException;

public class IvyArtifactProperty extends IvyPostResolveTask {
   private String name;
   private String value;
   private boolean overwrite = false;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public void setOverwrite(boolean overwrite) {
      this.overwrite = overwrite;
   }

   public void doExecute() throws BuildException {
      this.prepareAndCheck();

      try {
         ResolutionCacheManager cacheMgr = this.getIvyInstance().getResolutionCacheManager();
         String resolveId = this.getResolveId();
         if (resolveId == null) {
            resolveId = ResolveOptions.getDefaultResolveId(this.getResolvedModuleId());
         }

         XmlReportParser parser = new XmlReportParser();

         for(String conf : StringUtils.splitToArray(this.getConf())) {
            File report = cacheMgr.getConfigurationResolveReportInCache(resolveId, conf);
            parser.parse(report);

            for(Artifact artifact : parser.getArtifacts()) {
               String name = IvyPatternHelper.substitute(this.getSettings().substitute(this.getName()), artifact, conf);
               String value = IvyPatternHelper.substitute(this.getSettings().substitute(this.getValue()), artifact, conf);
               this.setProperty(name, value);
            }
         }

      } catch (Exception ex) {
         throw new BuildException("impossible to add artifact properties: " + ex, ex);
      }
   }

   private void setProperty(String name, String value) {
      if (this.overwrite) {
         this.getProject().setProperty(name, value);
      } else {
         this.getProject().setNewProperty(name, value);
      }

   }
}
