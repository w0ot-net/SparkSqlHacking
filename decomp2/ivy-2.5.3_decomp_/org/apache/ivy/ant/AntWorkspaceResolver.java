package org.apache.ivy.ant;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.DownloadReport;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.core.resolve.DownloadOptions;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.plugins.parser.ModuleDescriptorParserRegistry;
import org.apache.ivy.plugins.resolver.AbstractWorkspaceResolver;
import org.apache.ivy.util.Message;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileResource;

public class AntWorkspaceResolver extends DataType {
   private List allResources = new ArrayList();
   private boolean haltOnError = true;
   private Resolver resolver;
   private String name;
   private List artifacts = new ArrayList();

   public void setName(String name) {
      this.name = name;
   }

   public void setHaltonerror(boolean haltOnError) {
      this.haltOnError = haltOnError;
   }

   public void addConfigured(ResourceCollection resources) {
      if (!resources.isFilesystemOnly()) {
         throw new BuildException("Only filesystem resource collection is supported");
      } else {
         this.allResources.add(resources);
      }
   }

   public WorkspaceArtifact createArtifact() {
      WorkspaceArtifact a = new WorkspaceArtifact();
      this.artifacts.add(a);
      return a;
   }

   public Resolver getResolver() {
      if (this.resolver == null) {
         if (this.name == null) {
            throw new BuildException("A name is required");
         }

         this.resolver = new Resolver();
         this.resolver.setName(this.name);
      }

      return this.resolver;
   }

   private String getProjectName(File ivyFile) {
      return ivyFile.getParentFile().getName();
   }

   public static final class WorkspaceArtifact {
      private String name;
      private String type;
      private String ext;
      private String path;

      public void setName(String name) {
         this.name = name;
      }

      public void setType(String type) {
         this.type = type;
      }

      public void setExt(String ext) {
         this.ext = ext;
      }

      public void setPath(String path) {
         this.path = path;
      }
   }

   private class Resolver extends AbstractWorkspaceResolver {
      private Map md2IvyFile;

      private Resolver() {
      }

      private synchronized Map getModuleDescriptors() {
         if (this.md2IvyFile == null) {
            this.md2IvyFile = new HashMap();

            for(ResourceCollection resources : AntWorkspaceResolver.this.allResources) {
               for(Resource resource : resources) {
                  File ivyFile = ((FileResource)resource).getFile();

                  try {
                     ModuleDescriptor md = ModuleDescriptorParserRegistry.getInstance().parseDescriptor(this.getParserSettings(), ivyFile.toURI().toURL(), this.isValidate());
                     this.md2IvyFile.put(md, ivyFile);
                     Message.debug("Add " + md.getModuleRevisionId().getModuleId());
                  } catch (Exception ex) {
                     if (AntWorkspaceResolver.this.haltOnError) {
                        throw new BuildException("impossible to parse ivy file " + ivyFile + " exception=" + ex, ex);
                     }

                     Message.warn("impossible to parse ivy file " + ivyFile + " exception=" + ex.getMessage());
                  }
               }
            }
         }

         return this.md2IvyFile;
      }

      public ResolvedModuleRevision getDependency(DependencyDescriptor dd, ResolveData data) throws ParseException {
         for(Map.Entry md : this.getModuleDescriptors().entrySet()) {
            ResolvedModuleRevision rmr = this.checkCandidate(dd, (ModuleDescriptor)md.getKey(), AntWorkspaceResolver.this.getProjectName((File)md.getValue()));
            if (rmr != null) {
               return rmr;
            }
         }

         return null;
      }

      protected List createWorkspaceArtifacts(ModuleDescriptor md) {
         List<Artifact> res = new ArrayList();

         for(WorkspaceArtifact wa : AntWorkspaceResolver.this.artifacts) {
            String name = wa.name;
            String type = wa.type;
            String ext = wa.ext;
            String path = wa.path;
            if (name == null) {
               name = md.getModuleRevisionId().getName();
            }

            if (type == null) {
               type = "jar";
            }

            if (ext == null) {
               ext = "jar";
            }

            if (path == null) {
               path = "target" + File.separator + "dist" + File.separator + type + "s" + File.separator + name + "." + ext;
            }

            File ivyFile = (File)this.md2IvyFile.get(md);
            File artifactFile = new File(ivyFile.getParentFile(), path);

            URL url;
            try {
               url = artifactFile.toURI().toURL();
            } catch (MalformedURLException e) {
               throw new RuntimeException("Unsupported file path : " + artifactFile, e);
            }

            res.add(new DefaultArtifact(md.getModuleRevisionId(), new Date(), name, type, ext, url, (Map)null));
         }

         return res;
      }

      public DownloadReport download(Artifact[] artifacts, DownloadOptions options) {
         DownloadReport dr = new DownloadReport();

         for(Artifact artifact : artifacts) {
            ArtifactDownloadReport adr = new ArtifactDownloadReport(artifact);
            dr.addArtifactReport(adr);
            URL url = artifact.getUrl();
            if (url == null || !url.getProtocol().equals("file")) {
               adr.setDownloadStatus(DownloadStatus.FAILED);
               return dr;
            }

            File f;
            try {
               f = new File(url.toURI());
            } catch (URISyntaxException var12) {
               f = new File(url.getPath());
            }

            adr.setLocalFile(f);
            adr.setDownloadStatus(DownloadStatus.NO);
            adr.setSize(0L);
            Message.verbose("\t[IN WORKSPACE] " + artifact);
         }

         return dr;
      }
   }
}
