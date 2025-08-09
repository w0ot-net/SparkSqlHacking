package org.apache.ivy.ant;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.PatternSet;

public class IvyCacheFileset extends IvyCacheTask {
   private String setid;

   public String getSetid() {
      return this.setid;
   }

   public void setSetid(String id) {
      this.setid = id;
   }

   public void setUseOrigin(boolean useOrigin) {
      if (useOrigin) {
         throw new UnsupportedOperationException("the cachefileset task does not support the useOrigin mode, since filesets require to have only one root directory. Please use the the cachepath task instead");
      }
   }

   public void doExecute() throws BuildException {
      this.prepareAndCheck();
      if (this.setid == null) {
         throw new BuildException("setid is required in ivy cachefileset");
      } else {
         try {
            List<ArtifactDownloadReport> artifactDownloadReports = this.getArtifactReports();
            if (artifactDownloadReports.isEmpty()) {
               FileSet emptyFileSet = new EmptyFileSet();
               emptyFileSet.setProject(this.getProject());
               this.getProject().addReference(this.setid, emptyFileSet);
            } else {
               File baseDir = this.requireCommonBaseDir(artifactDownloadReports);
               FileSet fileset = new FileSet();
               fileset.setDir(baseDir);
               fileset.setProject(this.getProject());

               for(ArtifactDownloadReport artifactDownloadReport : artifactDownloadReports) {
                  if (artifactDownloadReport.getLocalFile() != null) {
                     PatternSet.NameEntry ne = fileset.createInclude();
                     ne.setName(this.getPath(baseDir, artifactDownloadReport.getLocalFile()));
                  }
               }

               this.getProject().addReference(this.setid, fileset);
            }
         } catch (Exception ex) {
            throw new BuildException("impossible to build ivy cache fileset: " + ex, ex);
         }
      }
   }

   File requireCommonBaseDir(List artifactDownloadReports) {
      File base = null;

      for(ArtifactDownloadReport artifactDownloadReport : artifactDownloadReports) {
         if (artifactDownloadReport.getLocalFile() != null) {
            if (base == null) {
               base = artifactDownloadReport.getLocalFile().getParentFile().getAbsoluteFile();
            } else {
               base = this.getBaseDir(base, artifactDownloadReport.getLocalFile());
               if (base == null) {
                  throw new BuildException("Cannot find a common base directory, from resolved artifacts, for generating a cache fileset");
               }
            }
         }
      }

      if (base == null) {
         throw new BuildException("Cannot find a common base directory, from resolved artifacts, for generating a cache fileset");
      } else {
         return base;
      }
   }

   private String getPath(File base, File file) {
      String absoluteBasePath = base.getAbsolutePath();
      int beginIndex = absoluteBasePath.length();
      if (!absoluteBasePath.endsWith(File.separator)) {
         ++beginIndex;
      }

      return file.getAbsolutePath().substring(beginIndex);
   }

   File getBaseDir(File file1, File file2) {
      if (file1 != null && file2 != null) {
         Iterator<File> file1Parents = this.getParents(file1).iterator();
         Iterator<File> file2Parents = this.getParents(file2.getAbsoluteFile()).iterator();

         File result;
         File next;
         for(result = null; file1Parents.hasNext() && file2Parents.hasNext(); result = next) {
            next = (File)file1Parents.next();
            if (!next.equals(file2Parents.next())) {
               break;
            }
         }

         return result;
      } else {
         return null;
      }
   }

   private LinkedList getParents(File file) {
      LinkedList<File> r;
      for(r = new LinkedList(); file != null; file = file.getParentFile()) {
         r.addFirst(file);
      }

      return r;
   }
}
