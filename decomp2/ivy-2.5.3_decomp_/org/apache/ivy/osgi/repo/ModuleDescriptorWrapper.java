package org.apache.ivy.osgi.repo;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.core.BundleInfoAdapter;
import org.apache.ivy.osgi.core.ExecutionEnvironmentProfileProvider;
import org.apache.ivy.osgi.core.OSGiManifestParser;

public class ModuleDescriptorWrapper {
   private BundleInfo bundleInfo;
   private volatile DefaultModuleDescriptor md;
   private URI baseUri;
   private ExecutionEnvironmentProfileProvider profileProvider;

   public ModuleDescriptorWrapper(BundleInfo bundleInfo, URI baseUri, ExecutionEnvironmentProfileProvider profileProvider) {
      this.bundleInfo = bundleInfo;
      this.baseUri = baseUri;
      this.profileProvider = profileProvider;
   }

   public BundleInfo getBundleInfo() {
      return this.bundleInfo;
   }

   public DefaultModuleDescriptor getModuleDescriptor() {
      if (this.md == null) {
         synchronized(this) {
            if (this.md != null) {
               return this.md;
            }

            this.md = BundleInfoAdapter.toModuleDescriptor(OSGiManifestParser.getInstance(), this.baseUri, this.bundleInfo, this.profileProvider);
         }
      }

      return this.md;
   }

   public static Collection unwrap(Collection collection) {
      if (collection == null) {
         return null;
      } else if (collection.isEmpty()) {
         return Collections.emptyList();
      } else {
         List<ModuleDescriptor> unwrapped = new ArrayList();

         for(ModuleDescriptorWrapper wrapped : collection) {
            unwrapped.add(wrapped.getModuleDescriptor());
         }

         return unwrapped;
      }
   }

   public int hashCode() {
      return this.bundleInfo.hashCode();
   }

   public boolean equals(Object obj) {
      return obj instanceof ModuleDescriptorWrapper && this.bundleInfo.equals(((ModuleDescriptorWrapper)obj).bundleInfo);
   }

   public String toString() {
      return this.getModuleDescriptor().toString();
   }
}
