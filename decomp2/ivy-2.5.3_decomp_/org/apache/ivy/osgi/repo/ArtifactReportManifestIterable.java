package org.apache.ivy.osgi.repo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.util.Message;

public class ArtifactReportManifestIterable implements Iterable {
   private final Map artifactReports = new HashMap();
   private List sourceTypes;

   public ArtifactReportManifestIterable(List reports, List sourceTypes) {
      this.sourceTypes = sourceTypes;

      for(ArtifactDownloadReport report : reports) {
         ModuleRevisionId mrid = report.getArtifact().getModuleRevisionId();
         List<ArtifactDownloadReport> moduleReports = (List)this.artifactReports.get(mrid);
         if (moduleReports == null) {
            moduleReports = new ArrayList();
            this.artifactReports.put(mrid, moduleReports);
         }

         moduleReports.add(report);
      }

   }

   public Iterator iterator() {
      return new ArtifactReportManifestIterator();
   }

   class ArtifactReportManifestIterator implements Iterator {
      private ManifestAndLocation next = null;
      private Iterator it;

      public ArtifactReportManifestIterator() {
         this.it = ArtifactReportManifestIterable.this.artifactReports.keySet().iterator();
      }

      public boolean hasNext() {
         while(this.next == null && this.it.hasNext()) {
            ModuleRevisionId mrid = (ModuleRevisionId)this.it.next();
            ArtifactDownloadReport jar = null;
            ArtifactDownloadReport source = null;

            for(ArtifactDownloadReport report : (List)ArtifactReportManifestIterable.this.artifactReports.get(mrid)) {
               if (ArtifactReportManifestIterable.this.sourceTypes != null && ArtifactReportManifestIterable.this.sourceTypes.contains(report.getArtifact().getType())) {
                  source = report;
               } else {
                  jar = report;
               }
            }

            if (jar != null) {
               URI sourceURI = null;
               if (source != null) {
                  if (source.getUnpackedLocalFile() != null) {
                     sourceURI = source.getUnpackedLocalFile().toURI();
                  } else {
                     sourceURI = source.getLocalFile().toURI();
                  }
               }

               if (jar.getUnpackedLocalFile() != null && jar.getUnpackedLocalFile().isDirectory()) {
                  try {
                     FileInputStream in = new FileInputStream(new File(jar.getUnpackedLocalFile(), "META-INF/MANIFEST.MF"));
                     Throwable var50 = null;

                     boolean var51;
                     try {
                        this.next = new ManifestAndLocation(new Manifest(in), jar.getUnpackedLocalFile().toURI(), sourceURI);
                        var51 = true;
                     } catch (Throwable var39) {
                        var50 = var39;
                        throw var39;
                     } finally {
                        if (in != null) {
                           if (var50 != null) {
                              try {
                                 in.close();
                              } catch (Throwable var38) {
                                 var50.addSuppressed(var38);
                              }
                           } else {
                              in.close();
                           }
                        }

                     }

                     return var51;
                  } catch (FileNotFoundException e) {
                     Message.debug("Bundle directory file just removed: " + jar.getUnpackedLocalFile(), e);
                  } catch (IOException e) {
                     Message.debug("The Manifest in the bundle directory could not be read: " + jar.getUnpackedLocalFile(), e);
                  }
               } else {
                  File artifact;
                  if (jar.getUnpackedLocalFile() != null) {
                     artifact = jar.getUnpackedLocalFile();
                  } else {
                     artifact = jar.getLocalFile();
                  }

                  try {
                     JarInputStream in = new JarInputStream(new FileInputStream(artifact));
                     Throwable var7 = null;

                     boolean var9;
                     try {
                        Manifest manifest = in.getManifest();
                        if (manifest == null) {
                           Message.debug("No manifest in jar: " + artifact);
                           continue;
                        }

                        this.next = new ManifestAndLocation(manifest, artifact.toURI(), sourceURI);
                        var9 = true;
                     } catch (Throwable var43) {
                        var7 = var43;
                        throw var43;
                     } finally {
                        if (in != null) {
                           if (var7 != null) {
                              try {
                                 in.close();
                              } catch (Throwable var37) {
                                 var7.addSuppressed(var37);
                              }
                           } else {
                              in.close();
                           }
                        }

                     }

                     return var9;
                  } catch (FileNotFoundException e) {
                     Message.debug("Jar file just removed: " + artifact, e);
                  } catch (IOException e) {
                     Message.warn("Unreadable jar: " + artifact, e);
                  }
               }
            }
         }

         return this.next != null;
      }

      public ManifestAndLocation next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            ManifestAndLocation manifest = this.next;
            this.next = null;
            return manifest;
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
