package org.apache.ivy.osgi.p2;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.osgi.core.BundleArtifact;
import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.core.ExecutionEnvironmentProfileProvider;
import org.apache.ivy.osgi.repo.EditableRepoDescriptor;
import org.apache.ivy.osgi.repo.ModuleDescriptorWrapper;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.util.Message;

public class P2Descriptor extends EditableRepoDescriptor {
   private Map sourceTargetBundles = new HashMap();
   private Map sourceBundles = new HashMap();

   public P2Descriptor(URI repoUri, ExecutionEnvironmentProfileProvider profileProvider) {
      super(repoUri, profileProvider);
   }

   public void addBundle(BundleInfo bundleInfo) {
      if (bundleInfo.isSource()) {
         if (bundleInfo.getSymbolicNameTarget() != null && bundleInfo.getVersionTarget() != null) {
            Map<Version, BundleInfo> byVersion = (Map)this.sourceBundles.get(bundleInfo.getSymbolicName());
            if (byVersion == null) {
               byVersion = new HashMap();
               this.sourceBundles.put(bundleInfo.getSymbolicName(), byVersion);
            }

            byVersion.put(bundleInfo.getVersion(), bundleInfo);
            Map<Version, BundleInfo> byTargetVersion = (Map)this.sourceTargetBundles.get(bundleInfo.getSymbolicNameTarget());
            if (byTargetVersion == null) {
               byTargetVersion = new HashMap();
               this.sourceTargetBundles.put(bundleInfo.getSymbolicNameTarget(), byTargetVersion);
            }

            BundleInfo old = (BundleInfo)byTargetVersion.put(bundleInfo.getVersionTarget(), bundleInfo);
            if (old != null && !old.equals(bundleInfo) && this.getLogLevel() <= 3) {
               Message.verbose("Duplicate source for the bundle " + bundleInfo.getSymbolicNameTarget() + "@" + bundleInfo.getVersionTarget() + " : " + bundleInfo + " is replacing " + old);
            }

         } else {
            if (this.getLogLevel() <= 3) {
               Message.verbose("The source bundle " + bundleInfo.getSymbolicName() + " did not declare its target. Ignoring it");
            }

         }
      } else {
         super.addBundle(bundleInfo);
      }
   }

   public void finish() {
      this.sourceBundles = null;
      Set<String> bundleIds = this.getCapabilityValues("bundle");
      if (bundleIds != null) {
         for(String bundleId : bundleIds) {
            for(ModuleDescriptorWrapper mdw : this.findModules("bundle", bundleId)) {
               String symbolicName = mdw.getBundleInfo().getSymbolicName();
               Map<Version, BundleInfo> byVersion = (Map)this.sourceTargetBundles.get(symbolicName);
               if (byVersion != null) {
                  BundleInfo source = (BundleInfo)byVersion.get(mdw.getBundleInfo().getVersion());
                  if (source != null) {
                     for(BundleArtifact artifact : source.getArtifacts()) {
                        mdw.getBundleInfo().addArtifact(artifact);
                     }
                  }
               }
            }
         }

         this.sourceTargetBundles = null;
      }
   }

   public void addArtifactUrl(String classifier, String id, Version version, URI uri, String format) {
      if (classifier.equals("osgi.bundle")) {
         ModuleDescriptorWrapper module = this.findModule(id, version);
         if (module != null) {
            this.addArtifact(module.getBundleInfo(), new BundleArtifact(false, uri, format));
         } else {
            Map<Version, BundleInfo> byVersion = (Map)this.sourceBundles.get(id);
            if (byVersion != null) {
               BundleInfo source = (BundleInfo)byVersion.get(version);
               if (source != null) {
                  this.addArtifact(source, new BundleArtifact(true, uri, format));
               }
            }
         }
      }
   }

   private void addArtifact(BundleInfo bundle, BundleArtifact artifact) {
      BundleArtifact same = null;

      for(BundleArtifact a : bundle.getArtifacts()) {
         if (a.isSource() == artifact.isSource()) {
            same = a;
            break;
         }
      }

      if (same != null) {
         if (artifact.getFormat() == null || same.getFormat() != null) {
            return;
         }

         bundle.removeArtifact(same);
      }

      bundle.addArtifact(artifact);
   }
}
