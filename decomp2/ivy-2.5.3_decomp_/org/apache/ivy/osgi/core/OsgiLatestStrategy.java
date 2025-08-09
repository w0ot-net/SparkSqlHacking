package org.apache.ivy.osgi.core;

import java.text.ParseException;
import java.util.Comparator;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.plugins.latest.ArtifactInfo;
import org.apache.ivy.plugins.latest.ComparatorLatestStrategy;
import org.apache.ivy.plugins.resolver.util.MDResolvedResource;
import org.apache.ivy.plugins.version.VersionMatcher;

public class OsgiLatestStrategy extends ComparatorLatestStrategy {
   private final Comparator mridComparator = new MridComparator();
   private final Comparator artifactInfoComparator = new ArtifactInfoComparator();

   public OsgiLatestStrategy() {
      this.setComparator(this.artifactInfoComparator);
      this.setName("latest-osgi");
   }

   final class MridComparator implements Comparator {
      public int compare(ModuleRevisionId o1, ModuleRevisionId o2) {
         Version v1 = new Version(o1.getRevision());
         Version v2 = new Version(o2.getRevision());

         try {
            return v1.compareTo(v2);
         } catch (RuntimeException e) {
            if (e.getCause() instanceof ParseException) {
               throw new RuntimeException("Uncomparable versions:" + o1.getRevision() + " and " + o2.getRevision() + " (" + e.getMessage() + ")");
            } else {
               throw e;
            }
         }
      }
   }

   final class ArtifactInfoComparator implements Comparator {
      public int compare(ArtifactInfo o1, ArtifactInfo o2) {
         String rev1 = o1.getRevision();
         String rev2 = o2.getRevision();
         VersionMatcher vmatcher = IvyContext.getContext().getSettings().getVersionMatcher();
         ModuleRevisionId mrid1 = ModuleRevisionId.newInstance("", "", rev1);
         ModuleRevisionId mrid2 = ModuleRevisionId.newInstance("", "", rev2);
         if (vmatcher.isDynamic(mrid1)) {
            int c = vmatcher.compare(mrid1, mrid2, OsgiLatestStrategy.this.mridComparator);
            return c >= 0 ? 1 : -1;
         } else if (vmatcher.isDynamic(mrid2)) {
            int c = vmatcher.compare(mrid2, mrid1, OsgiLatestStrategy.this.mridComparator);
            return c >= 0 ? -1 : 1;
         } else {
            int res = OsgiLatestStrategy.this.mridComparator.compare(mrid1, mrid2);
            if (res == 0) {
               ModuleRevisionId implMrid1 = this.getImplMrid(o1);
               ModuleRevisionId implMrid2 = this.getImplMrid(o2);
               if (implMrid1 != null && implMrid2 != null) {
                  if (implMrid1.getModuleId().equals(implMrid2.getModuleId())) {
                     res = OsgiLatestStrategy.this.mridComparator.compare(implMrid1, implMrid2);
                  } else {
                     res = implMrid1.getModuleId().compareTo(implMrid2.getModuleId());
                  }
               }
            }

            return res;
         }
      }

      private ModuleRevisionId getImplMrid(ArtifactInfo o) {
         if (!(o instanceof MDResolvedResource)) {
            return null;
         } else {
            MDResolvedResource mdrr = (MDResolvedResource)o;
            ResolvedModuleRevision rmr = mdrr.getResolvedModuleRevision();
            if (rmr == null) {
               return null;
            } else {
               ModuleDescriptor md = rmr.getDescriptor();
               if (md == null) {
                  return null;
               } else if (!md.getModuleRevisionId().getOrganisation().equals("package")) {
                  return null;
               } else {
                  DependencyDescriptor[] dds = md.getDependencies();
                  return dds != null && dds.length == 1 ? dds[0].getDependencyRevisionId() : null;
               }
            }
         }
      }
   }
}
