package org.apache.ivy.ant;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import org.apache.ivy.core.module.descriptor.DefaultDependencyArtifactDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.tools.ant.BuildException;

public class IvyDependencyArtifact {
   private String name;
   private String type;
   private String ext;
   private String url;

   public void setName(String name) {
      this.name = name;
   }

   public void setType(String type) {
      this.type = type;
   }

   public void setExt(String ext) {
      this.ext = ext;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   void addArtifact(DefaultDependencyDescriptor dd, String masterConf) {
      String typePattern = this.type == null ? "*" : this.type;
      String extPattern = this.ext == null ? typePattern : this.ext;

      URL u;
      try {
         u = this.url == null ? null : new URL(this.url);
      } catch (MalformedURLException e) {
         throw new BuildException("Malformed url in the artifact: " + e.getMessage(), e);
      }

      DefaultDependencyArtifactDescriptor dad = new DefaultDependencyArtifactDescriptor(dd, this.name, typePattern, extPattern, u, (Map)null);
      dd.addDependencyArtifact(masterConf, dad);
   }
}
