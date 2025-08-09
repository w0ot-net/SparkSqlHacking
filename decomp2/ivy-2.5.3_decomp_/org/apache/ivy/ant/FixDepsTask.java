package org.apache.ivy.ant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.plugins.parser.xml.XmlModuleDescriptorWriter;
import org.apache.tools.ant.BuildException;

public class FixDepsTask extends IvyPostResolveTask {
   private File dest;
   private List keeps = new ArrayList();

   public void setToFile(File dest) {
      this.dest = dest;
   }

   public Keep createKeep() {
      Keep k = new Keep();
      this.keeps.add(k);
      return k;
   }

   public void doExecute() throws BuildException {
      this.prepareAndCheck();
      if (this.dest == null) {
         throw new BuildException("Missing required parameter 'tofile'");
      } else if (this.dest.exists() && this.dest.isDirectory()) {
         throw new BuildException("The destination file '" + this.dest.getAbsolutePath() + "' already exist and is a folder");
      } else {
         ResolveReport report = this.getResolvedReport();
         List<ModuleId> midToKeep = new ArrayList();

         for(Keep keep : this.keeps) {
            midToKeep.add(ModuleId.newInstance(keep.org, keep.module));
         }

         ModuleDescriptor md = report.toFixedModuleDescriptor(this.getSettings(), midToKeep);

         try {
            XmlModuleDescriptorWriter.write(md, this.dest);
         } catch (IOException e) {
            throw new BuildException("Failed to write into the file " + this.dest.getAbsolutePath() + " (" + e.getMessage() + ")", e);
         }
      }
   }

   public static class Keep {
      private String org;
      private String module;

      public void setOrg(String org) {
         this.org = org;
      }

      public void setModule(String module) {
         this.module = module;
      }
   }
}
