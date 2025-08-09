package org.apache.ivy.core.module.descriptor;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.extendable.UnmodifiableExtendableItem;

public class DefaultDependencyArtifactDescriptor extends UnmodifiableExtendableItem implements DependencyArtifactDescriptor, ConfigurationAware {
   private final Collection confs = new ArrayList();
   private URL url;
   private String name;
   private String type;
   private String ext;
   private DependencyDescriptor dd;

   public DefaultDependencyArtifactDescriptor(DependencyDescriptor dd, String name, String type, String ext, URL url, Map extraAttributes) {
      super((Map)null, extraAttributes);
      Checks.checkNotNull(dd, "dd");
      Checks.checkNotNull(name, "name");
      Checks.checkNotNull(type, "type");
      Checks.checkNotNull(ext, "ext");
      this.dd = dd;
      this.name = name;
      this.type = type;
      this.ext = ext;
      this.url = url;
      this.initStandardAttributes();
   }

   private void initStandardAttributes() {
      this.setStandardAttribute("artifact", this.getName());
      this.setStandardAttribute("type", this.getType());
      this.setStandardAttribute("ext", this.getExt());
      this.setStandardAttribute("url", this.url != null ? String.valueOf(this.url) : "");
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof DependencyArtifactDescriptor)) {
         return false;
      } else {
         DependencyArtifactDescriptor dad = (DependencyArtifactDescriptor)obj;
         return this.getAttributes().equals(dad.getAttributes());
      }
   }

   public int hashCode() {
      return this.getAttributes().hashCode();
   }

   public void addConfiguration(String conf) {
      this.confs.add(conf);
   }

   public DependencyDescriptor getDependencyDescriptor() {
      return this.dd;
   }

   public String getName() {
      return this.name;
   }

   public String getType() {
      return this.type;
   }

   public String getExt() {
      return this.ext;
   }

   public String[] getConfigurations() {
      return (String[])this.confs.toArray(new String[this.confs.size()]);
   }

   public URL getUrl() {
      return this.url;
   }

   public String toString() {
      return "DA:" + this.name + "." + this.ext + "(" + this.type + ") (" + this.confs + ")" + (this.url == null ? "" : this.url.toString());
   }
}
