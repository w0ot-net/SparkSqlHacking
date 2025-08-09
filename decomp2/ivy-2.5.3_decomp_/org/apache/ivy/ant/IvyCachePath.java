package org.apache.ivy.ant;

import java.io.File;
import java.util.List;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.core.ManifestParser;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Path;

public class IvyCachePath extends IvyCacheTask {
   private String pathid;
   private String id;
   private boolean osgi = false;

   public String getPathid() {
      return this.pathid;
   }

   public void setPathid(String id) {
      this.pathid = id;
   }

   public void setOsgi(boolean osgi) {
      this.osgi = osgi;
   }

   /** @deprecated */
   @Deprecated
   public void setId(String id) {
      this.id = id;
   }

   public void doExecute() throws BuildException {
      this.prepareAndCheck();
      if (this.pathid == null) {
         if (this.id == null) {
            throw new BuildException("pathid is required in ivy classpath");
         }

         this.pathid = this.id;
         this.log("ID IS DEPRECATED, PLEASE USE PATHID INSTEAD", 1);
      }

      try {
         Path path = new Path(this.getProject());
         this.getProject().addReference(this.pathid, path);

         for(ArtifactDownloadReport adr : this.getArtifactReports()) {
            File f = adr.getLocalFile();
            if (adr.getUnpackedLocalFile() != null) {
               f = adr.getUnpackedLocalFile();
            }

            this.addToPath(path, f);
         }

      } catch (Exception ex) {
         throw new BuildException("impossible to build ivy path: " + ex, ex);
      }
   }

   protected void addToPath(Path path, File f) throws Exception {
      if (this.osgi && f.isDirectory()) {
         File manifest = new File(f, "META-INF/MANIFEST.MF");
         if (!manifest.exists()) {
            path.createPathElement().setLocation(f);
         } else {
            BundleInfo bundleInfo = ManifestParser.parseManifest(manifest);
            List<String> cp = bundleInfo.getClasspath();
            if (cp == null) {
               path.createPathElement().setLocation(f);
            } else {
               for(String p : cp) {
                  if (p.equals(".")) {
                     path.createPathElement().setLocation(f);
                  } else {
                     path.createPathElement().setLocation(new File(f, p));
                  }
               }

            }
         }
      } else {
         path.createPathElement().setLocation(f);
      }
   }
}
