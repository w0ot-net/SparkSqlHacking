package org.apache.ivy.ant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.retrieve.RetrieveOptions;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.XMLHelper;
import org.apache.tools.ant.BuildException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class IvyArtifactReport extends IvyPostResolveTask {
   private File tofile;
   private String pattern;

   public File getTofile() {
      return this.tofile;
   }

   public void setTofile(File aFile) {
      this.tofile = aFile;
   }

   public String getPattern() {
      return this.pattern;
   }

   public void setPattern(String aPattern) {
      this.pattern = aPattern;
   }

   public void doExecute() throws BuildException {
      this.prepareAndCheck();
      if (this.tofile == null) {
         throw new BuildException("no destination file name: please provide it through parameter 'tofile'");
      } else {
         this.pattern = this.getProperty(this.pattern, this.getSettings(), "ivy.retrieve.pattern");

         try {
            String[] confs = StringUtils.splitToArray(this.getConf());
            ModuleDescriptor md = null;
            if (this.getResolveId() == null) {
               md = (ModuleDescriptor)this.getResolvedDescriptor(this.getOrganisation(), this.getModule(), false);
            } else {
               md = (ModuleDescriptor)this.getResolvedDescriptor(this.getResolveId());
            }

            IvyNode[] dependencies = this.getIvyInstance().getResolveEngine().getDependencies(md, ((ResolveOptions)(new ResolveOptions()).setLog(this.getLog())).setConfs(confs).setResolveId(this.getResolveId()).setValidate(this.doValidate(this.getSettings())), (ResolveReport)null);
            Map<ArtifactDownloadReport, Set<String>> artifactsToCopy = this.getIvyInstance().getRetrieveEngine().determineArtifactsToCopy(ModuleRevisionId.newInstance(this.getOrganisation(), this.getModule(), this.getRevision()), this.pattern, ((RetrieveOptions)(new RetrieveOptions()).setLog(this.getLog())).setConfs(confs).setResolveId(this.getResolveId()));
            Map<ModuleRevisionId, Set<ArtifactDownloadReport>> moduleRevToArtifactsMap = new HashMap();

            for(ArtifactDownloadReport artifact : artifactsToCopy.keySet()) {
               Set<ArtifactDownloadReport> moduleRevArtifacts = (Set)moduleRevToArtifactsMap.get(artifact.getArtifact().getModuleRevisionId());
               if (moduleRevArtifacts == null) {
                  moduleRevArtifacts = new HashSet();
                  moduleRevToArtifactsMap.put(artifact.getArtifact().getModuleRevisionId(), moduleRevArtifacts);
               }

               moduleRevArtifacts.add(artifact);
            }

            this.generateXml(dependencies, moduleRevToArtifactsMap, artifactsToCopy);
         } catch (ParseException e) {
            this.log(e.getMessage(), 0);
            throw new BuildException("syntax errors in ivy file: " + e, e);
         } catch (IOException e) {
            throw new BuildException("impossible to generate report: " + e, e);
         }
      }
   }

   private void generateXml(IvyNode[] dependencies, Map moduleRevToArtifactsMap, Map artifactsToCopy) {
      try {
         FileOutputStream fileOutputStream = new FileOutputStream(this.tofile);
         Throwable var5 = null;

         try {
            TransformerHandler saxHandler = this.createTransformerHandler(fileOutputStream);
            saxHandler.startDocument();
            saxHandler.startElement((String)null, "modules", "modules", new AttributesImpl());

            for(IvyNode dependency : dependencies) {
               if (dependency.getModuleRevision() != null && !dependency.isCompletelyEvicted()) {
                  this.startModule(saxHandler, dependency);
                  Set<ArtifactDownloadReport> artifactsOfModuleRev = (Set)moduleRevToArtifactsMap.get(dependency.getModuleRevision().getId());
                  if (artifactsOfModuleRev != null) {
                     for(ArtifactDownloadReport artifact : artifactsOfModuleRev) {
                        RepositoryCacheManager cache = dependency.getModuleRevision().getArtifactResolver().getRepositoryCacheManager();
                        this.startArtifact(saxHandler, artifact.getArtifact());
                        this.writeOriginLocationIfPresent(cache, saxHandler, artifact);
                        this.writeCacheLocationIfPresent(cache, saxHandler, artifact);

                        for(String artifactDestPath : (Set)artifactsToCopy.get(artifact)) {
                           this.writeRetrieveLocation(saxHandler, artifactDestPath);
                        }

                        saxHandler.endElement((String)null, "artifact", "artifact");
                     }
                  }

                  saxHandler.endElement((String)null, "module", "module");
               }
            }

            saxHandler.endElement((String)null, "modules", "modules");
            saxHandler.endDocument();
         } catch (Throwable var25) {
            var5 = var25;
            throw var25;
         } finally {
            if (fileOutputStream != null) {
               if (var5 != null) {
                  try {
                     fileOutputStream.close();
                  } catch (Throwable var24) {
                     var5.addSuppressed(var24);
                  }
               } else {
                  fileOutputStream.close();
               }
            }

         }

      } catch (IOException | TransformerConfigurationException | SAXException e) {
         throw new BuildException("impossible to generate report", e);
      }
   }

   private TransformerHandler createTransformerHandler(FileOutputStream fileOutputStream) throws TransformerConfigurationException {
      TransformerHandler saxHandler = XMLHelper.getTransformerHandler();
      saxHandler.getTransformer().setOutputProperty("encoding", "UTF-8");
      saxHandler.getTransformer().setOutputProperty("indent", "yes");
      saxHandler.setResult(new StreamResult(fileOutputStream));
      return saxHandler;
   }

   private void startModule(TransformerHandler saxHandler, IvyNode dependency) throws SAXException {
      AttributesImpl moduleAttrs = new AttributesImpl();
      moduleAttrs.addAttribute((String)null, "organisation", "organisation", "CDATA", dependency.getModuleId().getOrganisation());
      moduleAttrs.addAttribute((String)null, "name", "name", "CDATA", dependency.getModuleId().getName());
      ResolvedModuleRevision moduleRevision = dependency.getModuleRevision();
      moduleAttrs.addAttribute((String)null, "rev", "rev", "CDATA", moduleRevision.getId().getRevision());
      moduleAttrs.addAttribute((String)null, "status", "status", "CDATA", moduleRevision.getDescriptor().getStatus());
      saxHandler.startElement((String)null, "module", "module", moduleAttrs);
   }

   private void startArtifact(TransformerHandler saxHandler, Artifact artifact) throws SAXException {
      AttributesImpl artifactAttrs = new AttributesImpl();
      artifactAttrs.addAttribute((String)null, "name", "name", "CDATA", artifact.getName());
      artifactAttrs.addAttribute((String)null, "ext", "ext", "CDATA", artifact.getExt());
      artifactAttrs.addAttribute((String)null, "type", "type", "CDATA", artifact.getType());
      saxHandler.startElement((String)null, "artifact", "artifact", artifactAttrs);
   }

   private void writeOriginLocationIfPresent(RepositoryCacheManager cache, TransformerHandler saxHandler, ArtifactDownloadReport artifact) throws SAXException {
      ArtifactOrigin origin = artifact.getArtifactOrigin();
      if (!ArtifactOrigin.isUnknown(origin)) {
         String originName = origin.getLocation();
         boolean isOriginLocal = origin.isLocal();
         AttributesImpl originLocationAttrs = new AttributesImpl();
         String originLocation;
         if (isOriginLocal) {
            originLocationAttrs.addAttribute((String)null, "is-local", "is-local", "CDATA", "true");
            originLocation = originName.replace('\\', '/');
         } else {
            originLocationAttrs.addAttribute((String)null, "is-local", "is-local", "CDATA", "false");
            originLocation = originName;
         }

         saxHandler.startElement((String)null, "origin-location", "origin-location", originLocationAttrs);
         char[] originLocationAsChars = originLocation.toCharArray();
         saxHandler.characters(originLocationAsChars, 0, originLocationAsChars.length);
         saxHandler.endElement((String)null, "origin-location", "origin-location");
      }

   }

   private void writeCacheLocationIfPresent(RepositoryCacheManager cache, TransformerHandler saxHandler, ArtifactDownloadReport artifact) throws SAXException {
      File archiveInCache = artifact.getLocalFile();
      if (archiveInCache != null) {
         saxHandler.startElement((String)null, "cache-location", "cache-location", new AttributesImpl());
         char[] archiveInCacheAsChars = archiveInCache.getPath().replace('\\', '/').toCharArray();
         saxHandler.characters(archiveInCacheAsChars, 0, archiveInCacheAsChars.length);
         saxHandler.endElement((String)null, "cache-location", "cache-location");
      }

   }

   private void writeRetrieveLocation(TransformerHandler saxHandler, String artifactDestPath) throws SAXException {
      artifactDestPath = this.removeLeadingPath(this.getProject().getBaseDir(), new File(artifactDestPath));
      saxHandler.startElement((String)null, "retrieve-location", "retrieve-location", new AttributesImpl());
      char[] artifactDestPathAsChars = artifactDestPath.replace('\\', '/').toCharArray();
      saxHandler.characters(artifactDestPathAsChars, 0, artifactDestPathAsChars.length);
      saxHandler.endElement((String)null, "retrieve-location", "retrieve-location");
   }

   public String removeLeadingPath(File leading, File path) {
      String l = leading.getAbsolutePath();
      String p = path.getAbsolutePath();
      if (l.equals(p)) {
         return "";
      } else {
         if (!l.endsWith(File.separator)) {
            l = l + File.separator;
         }

         return p.startsWith(l) ? p.substring(l.length()) : p;
      }
   }
}
