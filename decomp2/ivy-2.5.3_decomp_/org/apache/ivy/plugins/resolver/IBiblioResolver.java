package org.apache.ivy.plugins.resolver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.DownloadReport;
import org.apache.ivy.core.resolve.DownloadOptions;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.search.ModuleEntry;
import org.apache.ivy.core.search.OrganisationEntry;
import org.apache.ivy.core.search.RevisionEntry;
import org.apache.ivy.plugins.repository.Repository;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.plugins.version.MavenTimedSnapshotVersionMatcher;
import org.apache.ivy.util.ContextualSAXHandler;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.XMLHelper;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public class IBiblioResolver extends URLResolver {
   private static final String M2_PER_MODULE_PATTERN = "[revision]/[artifact]-[revision](-[classifier]).[ext]";
   private static final String M2_PATTERN = "[organisation]/[module]/[revision]/[artifact]-[revision](-[classifier]).[ext]";
   /** @deprecated */
   @Deprecated
   public static final String DEFAULT_PATTERN = "[module]/[type]s/[artifact]-[revision].[ext]";
   /** @deprecated */
   @Deprecated
   public static final String DEFAULT_ROOT = "https://www.ibiblio.org/maven/";
   public static final String DEFAULT_M2_ROOT = "https://repo1.maven.org/maven2/";
   private String root = null;
   private String pattern = null;
   private boolean usepoms = true;
   private boolean useMavenMetadata = true;

   public IBiblioResolver() {
      this.setChangingMatcher("regexp");
      this.setChangingPattern(".*-SNAPSHOT");
   }

   public ResolvedResource findIvyFileRef(DependencyDescriptor dd, ResolveData data) {
      if (this.isM2compatible() && this.isUsepoms()) {
         ModuleRevisionId mrid = dd.getDependencyRevisionId();
         mrid = this.convertM2IdForResourceSearch(mrid);
         String revision = dd.getDependencyRevisionId().getRevision();
         MavenTimedSnapshotVersionMatcher.MavenSnapshotRevision snapshotRevision = MavenTimedSnapshotVersionMatcher.computeIfSnapshot(revision);
         if (snapshotRevision != null) {
            ResolvedResource rres = this.findSnapshotDescriptor(dd, data, mrid, snapshotRevision);
            if (rres != null) {
               return rres;
            }
         }

         return this.findResourceUsingPatterns(mrid, this.getIvyPatterns(), DefaultArtifact.newPomArtifact(mrid, data.getDate()), this.getRMDParser(dd, data), data.getDate());
      } else {
         return null;
      }
   }

   public ResolvedResource findArtifactRef(Artifact artifact, Date date) {
      this.ensureConfigured(this.getSettings());
      ModuleRevisionId mrid = artifact.getModuleRevisionId();
      if (this.isM2compatible()) {
         mrid = this.convertM2IdForResourceSearch(mrid);
      }

      String revision = artifact.getId().getRevision();
      MavenTimedSnapshotVersionMatcher.MavenSnapshotRevision snapshotRevision = MavenTimedSnapshotVersionMatcher.computeIfSnapshot(revision);
      if (snapshotRevision != null) {
         ResolvedResource rres = this.findSnapshotArtifact(artifact, date, mrid, snapshotRevision);
         if (rres != null) {
            return rres;
         }
      }

      return this.findResourceUsingPatterns(mrid, this.getArtifactPatterns(), artifact, this.getDefaultRMDParser(artifact.getModuleRevisionId().getModuleId()), date);
   }

   private ResolvedResource findSnapshotArtifact(Artifact artifact, Date date, ModuleRevisionId mrid, MavenTimedSnapshotVersionMatcher.MavenSnapshotRevision snapshotRevision) {
      if (!this.isM2compatible()) {
         return null;
      } else {
         String snapshotArtifactPattern;
         if (snapshotRevision.isTimestampedSnapshot()) {
            Message.debug(mrid + " has been identified as a (Maven) timestamped snapshot revision");
            String inferredSnapshotRevision = snapshotRevision.getBaseRevision() + "-SNAPSHOT";
            snapshotArtifactPattern = this.getWholePattern().replaceFirst("/\\[revision\\]", "/" + inferredSnapshotRevision);
         } else {
            String timestampedRev = this.findTimestampedSnapshotVersion(mrid);
            if (timestampedRev == null) {
               return null;
            }

            Message.verbose(mrid + " has been identified as a snapshot revision which has a timestamped snapshot revision " + timestampedRev);
            snapshotArtifactPattern = this.getWholePattern().replaceFirst("\\-\\[revision\\]", "-" + timestampedRev);
         }

         return this.findResourceUsingPattern(mrid, snapshotArtifactPattern, artifact, this.getDefaultRMDParser(artifact.getModuleRevisionId().getModuleId()), date);
      }
   }

   private ResolvedResource findSnapshotDescriptor(DependencyDescriptor dd, ResolveData data, ModuleRevisionId mrid, MavenTimedSnapshotVersionMatcher.MavenSnapshotRevision snapshotRevision) {
      if (!this.isM2compatible()) {
         return null;
      } else {
         String snapshotDescriptorPattern;
         if (snapshotRevision.isTimestampedSnapshot()) {
            Message.debug(mrid + " has been identified as a (Maven) timestamped snapshot revision");
            String inferredSnapshotRevision = snapshotRevision.getBaseRevision() + "-SNAPSHOT";
            snapshotDescriptorPattern = this.getWholePattern().replaceFirst("/\\[revision\\]", "/" + inferredSnapshotRevision);
         } else {
            String timestampedRev = this.findTimestampedSnapshotVersion(mrid);
            if (timestampedRev == null) {
               return null;
            }

            Message.verbose(mrid + " has been identified as a snapshot revision which has a timestamped snapshot revision " + timestampedRev);
            snapshotDescriptorPattern = this.getWholePattern().replaceFirst("\\-\\[revision\\]", "-" + timestampedRev);
         }

         return this.findResourceUsingPattern(mrid, snapshotDescriptorPattern, DefaultArtifact.newPomArtifact(mrid, data.getDate()), this.getRMDParser(dd, data), data.getDate());
      }
   }

   private String findTimestampedSnapshotVersion(ModuleRevisionId mrid) {
      if (!this.isM2compatible()) {
         return null;
      } else if (!this.shouldUseMavenMetadata(this.getWholePattern())) {
         return null;
      } else {
         try {
            String metadataLocation = IvyPatternHelper.substitute(this.root + "[organisation]/[module]/[revision]/maven-metadata.xml", mrid);
            Resource metadata = this.getRepository().getResource(metadataLocation);
            if (!metadata.exists()) {
               Message.verbose("\tmaven-metadata not available for: " + mrid);
               return null;
            } else {
               InputStream metadataStream = metadata.openStream();
               Throwable var5 = null;

               String var9;
               try {
                  final StringBuilder timestamp = new StringBuilder();
                  final StringBuilder buildNumber = new StringBuilder();
                  XMLHelper.parse((InputStream)metadataStream, (URL)null, new ContextualSAXHandler() {
                     public void endElement(String uri, String localName, String qName) throws SAXException {
                        if ("metadata/versioning/snapshot/timestamp".equals(this.getContext())) {
                           timestamp.append(this.getText());
                        }

                        if ("metadata/versioning/snapshot/buildNumber".equals(this.getContext())) {
                           buildNumber.append(this.getText());
                        }

                        super.endElement(uri, localName, qName);
                     }
                  }, (LexicalHandler)null);
                  if (timestamp.length() <= 0) {
                     return null;
                  }

                  String rev = mrid.getRevision();
                  rev = rev.substring(0, rev.length() - "SNAPSHOT".length());
                  rev = rev + timestamp.toString() + "-" + buildNumber.toString();
                  var9 = rev;
               } catch (Throwable var20) {
                  var5 = var20;
                  throw var20;
               } finally {
                  if (metadataStream != null) {
                     if (var5 != null) {
                        try {
                           metadataStream.close();
                        } catch (Throwable var19) {
                           var5.addSuppressed(var19);
                        }
                     } else {
                        metadataStream.close();
                     }
                  }

               }

               return var9;
            }
         } catch (SAXException | ParserConfigurationException | IOException e) {
            Message.debug("impossible to access maven metadata file, ignored", e);
            return null;
         }
      }
   }

   public void setM2compatible(boolean m2compatible) {
      super.setM2compatible(m2compatible);
      if (m2compatible) {
         if (this.root == null) {
            this.root = "https://repo1.maven.org/maven2/";
         }

         if (this.pattern == null) {
            this.pattern = "[organisation]/[module]/[revision]/[artifact]-[revision](-[classifier]).[ext]";
         }

         this.updateWholePattern();
      }

   }

   public void ensureConfigured(ResolverSettings settings) {
      if (settings != null && (this.root == null || this.pattern == null)) {
         if (this.root == null) {
            String root = settings.getVariable("ivy.ibiblio.default.artifact.root");
            if (root != null) {
               this.root = root;
            } else {
               settings.configureRepositories(true);
               this.root = settings.getVariable("ivy.ibiblio.default.artifact.root");
            }
         }

         if (this.pattern == null) {
            String pattern = settings.getVariable("ivy.ibiblio.default.artifact.pattern");
            if (pattern != null) {
               this.pattern = pattern;
            } else {
               settings.configureRepositories(false);
               this.pattern = settings.getVariable("ivy.ibiblio.default.artifact.pattern");
            }
         }

         this.updateWholePattern();
      }

   }

   protected String getModuleDescriptorExtension() {
      return "pom";
   }

   private String getWholePattern() {
      return this.root + this.pattern;
   }

   public String getPattern() {
      return this.pattern;
   }

   public void setPattern(String pattern) {
      if (pattern == null) {
         throw new NullPointerException("pattern must not be null");
      } else {
         this.pattern = pattern;
         this.ensureConfigured(this.getSettings());
         this.updateWholePattern();
      }
   }

   public String getRoot() {
      return this.root;
   }

   public void setRoot(String root) {
      if (root == null) {
         throw new NullPointerException("root must not be null");
      } else {
         if (!root.endsWith("/")) {
            this.root = root + "/";
         } else {
            this.root = root;
         }

         this.ensureConfigured(this.getSettings());
         this.updateWholePattern();
      }
   }

   private void updateWholePattern() {
      if (this.isM2compatible() && this.isUsepoms()) {
         this.setIvyPatterns(Collections.singletonList(this.getWholePattern()));
      } else {
         this.setIvyPatterns(Collections.emptyList());
      }

      this.setArtifactPatterns(Collections.singletonList(this.getWholePattern()));
   }

   public void publish(Artifact artifact, File src) {
      throw new UnsupportedOperationException("publish not supported by IBiblioResolver");
   }

   public String[] listTokenValues(String token, Map otherTokenValues) {
      if ("organisation".equals(token)) {
         return new String[0];
      } else if ("module".equals(token) && !this.isM2compatible()) {
         return new String[0];
      } else {
         this.ensureConfigured(this.getSettings());
         return super.listTokenValues(token, otherTokenValues);
      }
   }

   protected String[] listTokenValues(String pattern, String token) {
      if ("organisation".equals(token)) {
         return new String[0];
      } else if ("module".equals(token) && !this.isM2compatible()) {
         return new String[0];
      } else {
         this.ensureConfigured(this.getSettings());
         if ("revision".equals(token) && this.shouldUseMavenMetadata(this.getWholePattern())) {
            String partiallyResolvedM2PerModulePattern = IvyPatternHelper.substituteTokens("[revision]/[artifact]-[revision](-[classifier]).[ext]", Collections.singletonMap("ext", "pom"));
            if (!pattern.endsWith(partiallyResolvedM2PerModulePattern)) {
               return new String[0];
            }

            String metadataLocation = pattern.substring(0, pattern.lastIndexOf(partiallyResolvedM2PerModulePattern)) + "maven-metadata.xml";
            List<String> revs = this.listRevisionsWithMavenMetadata(this.getRepository(), metadataLocation);
            if (revs != null) {
               return (String[])revs.toArray(new String[revs.size()]);
            }
         }

         return super.listTokenValues(pattern, token);
      }
   }

   public OrganisationEntry[] listOrganisations() {
      return new OrganisationEntry[0];
   }

   public ModuleEntry[] listModules(OrganisationEntry org) {
      if (this.isM2compatible()) {
         this.ensureConfigured(this.getSettings());
         return super.listModules(org);
      } else {
         return new ModuleEntry[0];
      }
   }

   public RevisionEntry[] listRevisions(ModuleEntry mod) {
      this.ensureConfigured(this.getSettings());
      return super.listRevisions(mod);
   }

   protected ResolvedResource[] listResources(Repository repository, ModuleRevisionId mrid, String pattern, Artifact artifact) {
      if (this.shouldUseMavenMetadata(pattern)) {
         List<String> revs = this.listRevisionsWithMavenMetadata(repository, mrid.getModuleId().getAttributes());
         if (revs != null) {
            Message.debug("\tfound revs: " + revs);
            List<ResolvedResource> rres = new ArrayList();

            for(String rev : revs) {
               ModuleRevisionId historicalMrid = ModuleRevisionId.newInstance(mrid, rev);
               String patternForRev = pattern;
               if (rev.endsWith("SNAPSHOT")) {
                  String snapshotVersion = this.findTimestampedSnapshotVersion(historicalMrid);
                  if (snapshotVersion != null) {
                     patternForRev = pattern.replaceFirst("\\-\\[revision\\]", "-" + snapshotVersion);
                  }
               }

               String resolvedPattern = IvyPatternHelper.substitute(patternForRev, historicalMrid, artifact);

               try {
                  Resource res = repository.getResource(resolvedPattern);
                  if (res != null) {
                     rres.add(new ResolvedResource(res, rev));
                  }
               } catch (IOException e) {
                  Message.warn("impossible to get resource from name listed by maven-metadata.xml:" + rres, e);
               }
            }

            return (ResolvedResource[])rres.toArray(new ResolvedResource[rres.size()]);
         } else {
            return super.listResources(repository, mrid, pattern, artifact);
         }
      } else {
         return super.listResources(repository, mrid, pattern, artifact);
      }
   }

   private List listRevisionsWithMavenMetadata(Repository repository, Map tokenValues) {
      String metadataLocation = IvyPatternHelper.substituteTokens(this.root + "[organisation]/[module]/maven-metadata.xml", tokenValues);
      return this.listRevisionsWithMavenMetadata(repository, metadataLocation);
   }

   private List listRevisionsWithMavenMetadata(Repository repository, String metadataLocation) {
      List<String> revs = null;
      InputStream metadataStream = null;

      try {
         Resource metadata = repository.getResource(metadataLocation);
         if (metadata.exists()) {
            Message.verbose("\tlisting revisions from maven-metadata: " + metadata);
            final List<String> metadataRevs = new ArrayList();
            metadataStream = metadata.openStream();
            XMLHelper.parse((InputStream)metadataStream, (URL)null, new ContextualSAXHandler() {
               public void endElement(String uri, String localName, String qName) throws SAXException {
                  if ("metadata/versioning/versions/version".equals(this.getContext())) {
                     metadataRevs.add(this.getText().trim());
                  }

                  super.endElement(uri, localName, qName);
               }
            }, (LexicalHandler)null);
            revs = metadataRevs;
         } else {
            Message.verbose("\tmaven-metadata not available: " + metadata);
         }
      } catch (IOException e) {
         Message.verbose("impossible to access maven metadata file, ignored", e);
      } catch (ParserConfigurationException | SAXException e) {
         Message.verbose("impossible to parse maven metadata file, ignored", e);
      } finally {
         if (metadataStream != null) {
            try {
               metadataStream.close();
            } catch (IOException var16) {
            }
         }

      }

      return revs;
   }

   protected void findTokenValues(Collection names, List patterns, Map tokenValues, String token) {
      if ("revision".equals(token) && this.shouldUseMavenMetadata(this.getWholePattern())) {
         List<String> revs = this.listRevisionsWithMavenMetadata(this.getRepository(), tokenValues);
         if (revs != null) {
            names.addAll(this.filterNames(revs));
            return;
         }
      }

      super.findTokenValues(names, patterns, tokenValues, token);
   }

   private boolean shouldUseMavenMetadata(String pattern) {
      return this.isUseMavenMetadata() && this.isM2compatible() && pattern.endsWith("[organisation]/[module]/[revision]/[artifact]-[revision](-[classifier]).[ext]");
   }

   public String getTypeName() {
      return "ibiblio";
   }

   public ResolvedModuleRevision getDependency(DependencyDescriptor dd, ResolveData data) throws ParseException {
      this.ensureConfigured(data.getSettings());
      return super.getDependency(dd, data);
   }

   public DownloadReport download(Artifact[] artifacts, DownloadOptions options) {
      this.ensureConfigured(this.getSettings());
      return super.download(artifacts, options);
   }

   public boolean exists(Artifact artifact) {
      this.ensureConfigured(this.getSettings());
      return super.exists(artifact);
   }

   public ArtifactOrigin locate(Artifact artifact) {
      this.ensureConfigured(this.getSettings());
      return super.locate(artifact);
   }

   public List getArtifactPatterns() {
      this.ensureConfigured(this.getSettings());
      return super.getArtifactPatterns();
   }

   public boolean isUsepoms() {
      return this.usepoms;
   }

   public void setUsepoms(boolean usepoms) {
      this.usepoms = usepoms;
      this.updateWholePattern();
   }

   public boolean isUseMavenMetadata() {
      return this.useMavenMetadata;
   }

   public void setUseMavenMetadata(boolean useMavenMetadata) {
      this.useMavenMetadata = useMavenMetadata;
   }

   public void dumpSettings() {
      this.ensureConfigured(this.getSettings());
      super.dumpSettings();
      Message.debug("\t\troot: " + this.getRoot());
      Message.debug("\t\tpattern: " + this.getPattern());
      Message.debug("\t\tusepoms: " + this.usepoms);
      Message.debug("\t\tuseMavenMetadata: " + this.useMavenMetadata);
   }
}
