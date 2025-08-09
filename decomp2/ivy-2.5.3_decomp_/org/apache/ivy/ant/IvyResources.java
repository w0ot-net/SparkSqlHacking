package org.apache.ivy.ant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.BaseResourceCollectionWrapper;
import org.apache.tools.ant.types.resources.FileResource;

public class IvyResources extends IvyCacheTask implements ResourceCollection {
   private IvyBaseResourceCollectionWrapper wrapper = new IvyBaseResourceCollectionWrapper();

   public void setLocation(Location location) {
      super.setLocation(location);
      this.wrapper.setLocation(location);
   }

   public void setProject(Project project) {
      super.setProject(project);
      this.wrapper.setProject(project);
   }

   public void setDescription(String desc) {
      super.setDescription(desc);
      this.wrapper.setDescription(desc);
   }

   public void setRefid(Reference ref) {
      this.wrapper.setRefid(ref);
   }

   public void setCache(boolean b) {
      this.wrapper.setCache(b);
   }

   public boolean isFilesystemOnly() {
      return true;
   }

   public Iterator iterator() {
      return this.wrapper.iterator();
   }

   public int size() {
      return this.wrapper.size();
   }

   private Collection resolveResources(String id) throws BuildException {
      this.prepareAndCheck();

      try {
         List<Resource> resources = new ArrayList();
         if (id != null) {
            this.getProject().addReference(id, this);
         }

         for(ArtifactDownloadReport adr : this.getArtifactReports()) {
            resources.add(new FileResource(adr.getLocalFile()));
         }

         return resources;
      } catch (Exception ex) {
         throw new BuildException("impossible to build ivy resources: " + ex, ex);
      }
   }

   public void doExecute() throws BuildException {
      throw new BuildException("ivy:resources should not be used as a Ant Task");
   }

   private class IvyBaseResourceCollectionWrapper extends BaseResourceCollectionWrapper {
      private IvyBaseResourceCollectionWrapper() {
      }

      protected Collection getCollection() {
         return IvyResources.this.resolveResources((String)null);
      }
   }
}
