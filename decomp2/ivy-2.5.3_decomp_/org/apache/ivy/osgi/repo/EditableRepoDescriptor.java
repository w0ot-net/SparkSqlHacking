package org.apache.ivy.osgi.repo;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.osgi.core.BundleCapability;
import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.core.ExecutionEnvironmentProfileProvider;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.util.Message;

public class EditableRepoDescriptor extends RepoDescriptor {
   private final Map moduleByCapabilities = new HashMap();
   private final Set modules = new HashSet();
   private final ExecutionEnvironmentProfileProvider profileProvider;
   private final URI baseUri;
   private int logLevel = 2;

   public EditableRepoDescriptor(URI baseUri, ExecutionEnvironmentProfileProvider profileProvider) {
      this.baseUri = baseUri;
      this.profileProvider = profileProvider;
   }

   public void setLogLevel(int logLevel) {
      this.logLevel = logLevel;
   }

   public int getLogLevel() {
      return this.logLevel;
   }

   public URI getBaseUri() {
      return this.baseUri;
   }

   public Iterator getModules() {
      return this.modules.iterator();
   }

   public Set getCapabilities() {
      return this.moduleByCapabilities.keySet();
   }

   public Set findModules(String requirement, String value) {
      Map<String, Set<ModuleDescriptorWrapper>> modules = (Map)this.moduleByCapabilities.get(requirement);
      return modules == null ? null : (Set)modules.get(value);
   }

   public ModuleDescriptorWrapper findModule(String symbolicName, Version version) {
      Set<ModuleDescriptorWrapper> modules = this.findModules("bundle", symbolicName);
      if (modules == null) {
         return null;
      } else {
         for(ModuleDescriptorWrapper module : modules) {
            if (module.getBundleInfo().getVersion().equals(version)) {
               return module;
            }
         }

         return null;
      }
   }

   public Set getCapabilityValues(String capabilityName) {
      Map<String, Set<ModuleDescriptorWrapper>> modules = (Map)this.moduleByCapabilities.get(capabilityName);
      return modules == null ? Collections.emptySet() : modules.keySet();
   }

   private void add(String type, String value, ModuleDescriptorWrapper md) {
      this.modules.add(md);
      Map<String, Set<ModuleDescriptorWrapper>> map = (Map)this.moduleByCapabilities.get(type);
      if (map == null) {
         map = new HashMap();
         this.moduleByCapabilities.put(type, map);
      }

      Set<ModuleDescriptorWrapper> bundleReferences = (Set)map.get(value);
      if (bundleReferences == null) {
         bundleReferences = new HashSet();
         map.put(value, bundleReferences);
      }

      if (!bundleReferences.add(md) && this.logLevel <= 4) {
         Message.debug("Duplicate module in the repo " + this.baseUri + " for " + type + " " + value + ": " + md.getBundleInfo().getSymbolicName() + "#" + md.getBundleInfo().getVersion());
      }

   }

   public void addBundle(BundleInfo bundleInfo) {
      ModuleDescriptorWrapper module = this.findModule(bundleInfo.getSymbolicName(), bundleInfo.getVersion());
      if (module != null) {
         Message.debug("Duplicate module " + bundleInfo.getSymbolicName() + "@" + bundleInfo.getVersion());
      } else {
         ModuleDescriptorWrapper md = new ModuleDescriptorWrapper(bundleInfo, this.baseUri, this.profileProvider);
         this.add("bundle", bundleInfo.getSymbolicName(), md);

         for(BundleCapability capability : bundleInfo.getCapabilities()) {
            this.add(capability.getType(), capability.getName(), md);
         }

      }
   }

   public String toString() {
      return this.modules.toString();
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.modules == null ? 0 : this.modules.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof EditableRepoDescriptor)) {
         return false;
      } else if (this == obj) {
         return true;
      } else {
         EditableRepoDescriptor other = (EditableRepoDescriptor)obj;
         return this.modules == null ? other.modules == null : this.modules.equals(other.modules);
      }
   }
}
