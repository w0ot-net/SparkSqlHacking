package org.apache.ivy.osgi.updatesite.xml;

import java.util.ArrayList;
import java.util.List;
import org.apache.ivy.osgi.util.Version;

public class EclipseFeature {
   private String id;
   private Version version;
   private List plugins = new ArrayList();
   private List requires = new ArrayList();
   private String url;
   private String description;
   private String license;

   public EclipseFeature(String id, Version version) {
      this.id = id;
      this.version = version;
      this.url = "features/" + id + '_' + version + ".jar";
   }

   public void setURL(String url) {
      this.url = url;
   }

   public String getUrl() {
      return this.url;
   }

   public void setType(String type) {
   }

   public String getId() {
      return this.id;
   }

   public Version getVersion() {
      return this.version;
   }

   public void setLabel(String label) {
   }

   public void setOS(String os) {
   }

   public void setWS(String ws) {
   }

   public void setNL(String nl) {
   }

   public void setArch(String arch) {
   }

   public void setPatch(String patch) {
   }

   public void addCategory(String name) {
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return this.description;
   }

   public void setCopyright(String trim) {
   }

   public void setLicense(String license) {
      this.license = license;
   }

   public String getLicense() {
      return this.license;
   }

   public void addPlugin(EclipsePlugin plugin) {
      this.plugins.add(plugin);
   }

   public List getPlugins() {
      return this.plugins;
   }

   public void addRequire(Require require) {
      this.requires.add(require);
   }

   public List getRequires() {
      return this.requires;
   }

   public void setApplication(String value) {
   }

   public void setPlugin(String value) {
   }

   public void setExclusive(boolean booleanValue) {
   }

   public void setPrimary(boolean booleanValue) {
   }

   public void setColocationAffinity(String value) {
   }

   public void setProviderName(String value) {
   }

   public void setImage(String value) {
   }

   public String toString() {
      return this.id + "#" + this.version;
   }
}
