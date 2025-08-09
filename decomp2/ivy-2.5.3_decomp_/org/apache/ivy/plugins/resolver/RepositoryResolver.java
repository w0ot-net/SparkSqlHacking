package org.apache.ivy.plugins.resolver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.event.EventManager;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.DownloadReport;
import org.apache.ivy.core.resolve.DownloadOptions;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.parser.ModuleDescriptorParserRegistry;
import org.apache.ivy.plugins.repository.AbstractRepository;
import org.apache.ivy.plugins.repository.Repository;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.plugins.resolver.util.ResolverHelper;
import org.apache.ivy.plugins.resolver.util.ResourceMDParser;
import org.apache.ivy.plugins.signer.SignatureGenerator;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.util.ChecksumHelper;
import org.apache.ivy.util.CopyProgressListener;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;

public class RepositoryResolver extends AbstractPatternsBasedResolver {
   private Repository repository;
   private Boolean alwaysCheckExactRevision = null;
   private String signerName = null;

   public Repository getRepository() {
      return this.repository;
   }

   public void setRepository(Repository repository) {
      this.repository = repository;
   }

   public void setName(String name) {
      super.setName(name);
      if (this.repository instanceof AbstractRepository) {
         ((AbstractRepository)this.repository).setName(name);
      }

   }

   public void setSigner(String signerName) {
      this.signerName = signerName;
   }

   protected ResolvedResource findResourceUsingPattern(ModuleRevisionId mrid, String pattern, Artifact artifact, ResourceMDParser rmdparser, Date date) {
      String name = this.getName();
      VersionMatcher versionMatcher = this.getSettings().getVersionMatcher();

      try {
         if (versionMatcher.isDynamic(mrid) && !this.isAlwaysCheckExactRevision()) {
            return this.findDynamicResourceUsingPattern(rmdparser, mrid, pattern, artifact, date);
         } else {
            String resourceName = IvyPatternHelper.substitute(pattern, mrid, artifact);
            Message.debug("\t trying " + resourceName);
            this.logAttempt(resourceName);
            Resource res = this.repository.getResource(resourceName);
            boolean reachable = res.exists();
            if (!reachable) {
               if (versionMatcher.isDynamic(mrid)) {
                  return this.findDynamicResourceUsingPattern(rmdparser, mrid, pattern, artifact, date);
               } else {
                  Message.debug("\t" + name + ": resource not reachable for " + mrid + ": res=" + res);
                  return null;
               }
            } else {
               String revision;
               if (pattern.contains("revision")) {
                  revision = mrid.getRevision();
               } else if (!"ivy".equals(artifact.getType()) && !"pom".equals(artifact.getType())) {
                  revision = "working@" + name;
               } else {
                  File temp = File.createTempFile("ivy", artifact.getExt());
                  temp.deleteOnExit();
                  this.repository.get(res.getName(), temp);
                  ModuleDescriptorParser parser = ModuleDescriptorParserRegistry.getInstance().getParser(res);
                  ModuleDescriptor md = parser.parseDescriptor(this.getParserSettings(), temp.toURI().toURL(), res, false);
                  revision = md.getRevision();
                  if (StringUtils.isNullOrEmpty(revision)) {
                     revision = "working@" + name;
                  }
               }

               return new ResolvedResource(res, revision);
            }
         }
      } catch (ParseException | IOException ex) {
         throw new RuntimeException(name + ": unable to get resource for " + mrid + ": res=" + IvyPatternHelper.substitute(pattern, mrid, artifact) + ": " + ex, ex);
      }
   }

   private ResolvedResource findDynamicResourceUsingPattern(ResourceMDParser rmdparser, ModuleRevisionId mrid, String pattern, Artifact artifact, Date date) {
      String name = this.getName();
      this.logAttempt(IvyPatternHelper.substitute(pattern, ModuleRevisionId.newInstance(mrid, IvyPatternHelper.getTokenString("revision")), artifact));
      ResolvedResource[] rress = this.listResources(this.repository, mrid, pattern, artifact);
      if (rress == null) {
         Message.debug("\t" + name + ": unable to list resources for " + mrid + ": pattern=" + pattern);
         return null;
      } else {
         ResolvedResource found = this.findResource(rress, rmdparser, mrid, date);
         if (found == null) {
            Message.debug("\t" + name + ": no resource found for " + mrid + ": pattern=" + pattern);
         }

         return found;
      }
   }

   protected Resource getResource(String source) throws IOException {
      return this.repository.getResource(source);
   }

   protected ResolvedResource[] listResources(Repository repository, ModuleRevisionId mrid, String pattern, Artifact artifact) {
      return ResolverHelper.findAll(repository, mrid, pattern, artifact);
   }

   protected long get(Resource resource, File dest) throws IOException {
      Message.verbose("\t" + this.getName() + ": downloading " + resource.getName());
      Message.debug("\t\tto " + dest);
      if (dest.getParentFile() != null) {
         dest.getParentFile().mkdirs();
      }

      this.repository.get(resource.getName(), dest);
      return dest.length();
   }

   public void publish(Artifact artifact, File src, boolean overwrite) throws IOException {
      String destPattern;
      if ("ivy".equals(artifact.getType()) && !this.getIvyPatterns().isEmpty()) {
         destPattern = (String)this.getIvyPatterns().get(0);
      } else {
         if (this.getArtifactPatterns().isEmpty()) {
            throw new IllegalStateException("impossible to publish " + artifact + " using " + this + ": no artifact pattern defined");
         }

         destPattern = (String)this.getArtifactPatterns().get(0);
      }

      ModuleRevisionId mrid = artifact.getModuleRevisionId();
      if (this.isM2compatible()) {
         mrid = this.convertM2IdForResourceSearch(mrid);
      }

      String dest = this.getDestination(destPattern, artifact, mrid);
      this.put(artifact, src, dest, overwrite);
      Message.info("\tpublished " + artifact.getName() + " to " + this.hidePassword(this.repository.standardize(dest)));
   }

   protected String getDestination(String pattern, Artifact artifact, ModuleRevisionId mrid) {
      return IvyPatternHelper.substitute(pattern, mrid, artifact);
   }

   protected void put(Artifact artifact, File src, String dest, boolean overwrite) throws IOException {
      String[] checksums = this.getChecksumAlgorithms();

      for(String checksum : checksums) {
         if (!ChecksumHelper.isKnownAlgorithm(checksum)) {
            throw new IllegalArgumentException("Unknown checksum algorithm: " + checksum);
         }
      }

      this.repository.put(artifact, src, dest, overwrite);

      for(String checksum : checksums) {
         this.putChecksum(artifact, src, dest, overwrite, checksum);
      }

      if (this.signerName != null) {
         this.putSignature(artifact, src, dest, overwrite);
      }

   }

   protected void putChecksum(Artifact artifact, File src, String dest, boolean overwrite, String algorithm) throws IOException {
      File csFile = File.createTempFile("ivytemp", algorithm);

      try {
         FileUtil.copy((InputStream)(new ByteArrayInputStream(ChecksumHelper.computeAsString(src, algorithm).getBytes())), (File)csFile, (CopyProgressListener)null);
         this.repository.put(DefaultArtifact.cloneWithAnotherTypeAndExt(artifact, algorithm, artifact.getExt() + "." + algorithm), csFile, this.chopQuery(dest, algorithm), overwrite);
      } finally {
         csFile.delete();
      }

   }

   protected void putSignature(Artifact artifact, File src, String dest, boolean overwrite) throws IOException {
      SignatureGenerator gen = this.getSettings().getSignatureGenerator(this.signerName);
      if (gen == null) {
         throw new IllegalArgumentException("Couldn't sign the artifacts! Unknown signer name: '" + this.signerName + "'");
      } else {
         File tempFile = File.createTempFile("ivytemp", gen.getExtension());

         try {
            gen.sign(src, tempFile);
            this.repository.put(DefaultArtifact.cloneWithAnotherTypeAndExt(artifact, gen.getExtension(), artifact.getExt() + "." + gen.getExtension()), tempFile, this.chopQuery(dest, gen.getExtension()), overwrite);
         } finally {
            tempFile.delete();
         }

      }
   }

   private String chopQuery(String dest, String algorithm) {
      if (!dest.contains("?")) {
         return dest + "." + algorithm;
      } else {
         try {
            URL url = new URL(dest);
            String query = url.getQuery();
            if (query == null) {
               query = "";
            }

            return dest.replace("?" + query, "") + "." + algorithm;
         } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
         }
      }
   }

   public DownloadReport download(Artifact[] artifacts, DownloadOptions options) {
      EventManager eventManager = this.getEventManager();

      DownloadReport var4;
      try {
         if (eventManager != null) {
            this.repository.addTransferListener(eventManager);
         }

         var4 = super.download(artifacts, options);
      } finally {
         if (eventManager != null) {
            this.repository.removeTransferListener(eventManager);
         }

      }

      return var4;
   }

   protected void findTokenValues(Collection names, List patterns, Map tokenValues, String token) {
      for(String pattern : patterns) {
         String partiallyResolvedPattern = IvyPatternHelper.substituteTokens(pattern, tokenValues);
         String[] values = ResolverHelper.listTokenValues(this.repository, partiallyResolvedPattern, token);
         if (values != null) {
            names.addAll(this.filterNames(new ArrayList(Arrays.asList(values))));
         }
      }

   }

   protected String[] listTokenValues(String pattern, String token) {
      return ResolverHelper.listTokenValues(this.repository, pattern, token);
   }

   protected boolean exist(String path) {
      try {
         Resource resource = this.repository.getResource(path);
         return resource.exists();
      } catch (IOException e) {
         Message.debug((Throwable)e);
         return false;
      }
   }

   public String getTypeName() {
      return "repository";
   }

   public void dumpSettings() {
      super.dumpSettings();
      Message.debug("\t\trepository: " + this.getRepository());
   }

   public void setSettings(ResolverSettings settings) {
      super.setSettings(settings);
      if (settings != null && this.alwaysCheckExactRevision == null) {
         this.alwaysCheckExactRevision = Boolean.valueOf(settings.getVariable("ivy.default.always.check.exact.revision"));
      }

   }

   public boolean isAlwaysCheckExactRevision() {
      return this.alwaysCheckExactRevision == null || this.alwaysCheckExactRevision;
   }

   public void setAlwaysCheckExactRevision(boolean alwaysCheckExactRevision) {
      this.alwaysCheckExactRevision = alwaysCheckExactRevision;
   }
}
