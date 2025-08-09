package org.apache.ivy;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.deliver.DeliverOptions;
import org.apache.ivy.core.deliver.PublishingDependencyRevisionResolver;
import org.apache.ivy.core.install.InstallOptions;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.publish.PublishOptions;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.DownloadOptions;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.retrieve.RetrieveOptions;
import org.apache.ivy.core.search.ModuleEntry;
import org.apache.ivy.core.search.OrganisationEntry;
import org.apache.ivy.core.search.RevisionEntry;
import org.apache.ivy.core.sort.SilentNonMatchingVersionReporter;
import org.apache.ivy.core.sort.SortOptions;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.filter.Filter;
import org.apache.ivy.util.filter.FilterHelper;

public class Ivy14 {
   private Ivy ivy;

   public Ivy14() {
      this(Ivy.newInstance());
   }

   public Ivy14(Ivy ivy) {
      this.ivy = ivy;
   }

   public boolean check(URL ivyFile, String resolvername) {
      return this.ivy.check(ivyFile, resolvername);
   }

   public void configure(File settingsFile) throws ParseException, IOException {
      this.ivy.configure(settingsFile);
   }

   public void configure(URL settingsURL) throws ParseException, IOException {
      this.ivy.configure(settingsURL);
   }

   public void configureDefault() throws ParseException, IOException {
      this.ivy.configureDefault();
   }

   public void deliver(ModuleRevisionId mrid, String revision, File cache, String destIvyPattern, String status, Date pubdate, PublishingDependencyRevisionResolver pdrResolver, boolean validate, boolean resolveDynamicRevisions) throws IOException, ParseException {
      this.ivy.deliver(mrid, revision, destIvyPattern, new DeliverOptions(status, pubdate, pdrResolver, validate, resolveDynamicRevisions, (String[])null));
   }

   public void deliver(ModuleRevisionId mrid, String revision, File cache, String destIvyPattern, String status, Date pubdate, PublishingDependencyRevisionResolver pdrResolver, boolean validate) throws IOException, ParseException {
      this.deliver(mrid, revision, cache, destIvyPattern, status, pubdate, pdrResolver, validate, true);
   }

   public Map determineArtifactsToCopy(ModuleId moduleId, String[] confs, File cache, String destFilePattern, String destIvyPattern, Filter artifactFilter) throws ParseException, IOException {
      return this.ivy.getRetrieveEngine().determineArtifactsToCopy(new ModuleRevisionId(moduleId, Ivy.getWorkingRevision()), destFilePattern, (new RetrieveOptions()).setConfs(confs).setDestIvyPattern(destIvyPattern).setArtifactFilter(artifactFilter));
   }

   public Map determineArtifactsToCopy(ModuleId moduleId, String[] confs, File cache, String destFilePattern, String destIvyPattern) throws ParseException, IOException {
      return this.ivy.getRetrieveEngine().determineArtifactsToCopy(new ModuleRevisionId(moduleId, Ivy.getWorkingRevision()), destFilePattern, (new RetrieveOptions()).setConfs(confs).setDestIvyPattern(destIvyPattern));
   }

   public ArtifactDownloadReport download(Artifact artifact, File cache, boolean useOrigin) {
      Message.deprecated("using cache and useOrigin when calling download is not supported anymore");
      return this.ivy.getResolveEngine().download(artifact, new DownloadOptions());
   }

   public ResolvedModuleRevision findModule(ModuleRevisionId id) {
      ResolveOptions options = new ResolveOptions();
      options.setValidate(false);
      return this.ivy.getResolveEngine().findModule(id, options);
   }

   public IvyNode[] getDependencies(ModuleDescriptor md, String[] confs, File cache, Date date, ResolveReport report, boolean validate, boolean transitive) {
      return this.ivy.getResolveEngine().getDependencies(md, this.newResolveOptions(confs, (String)null, cache, date, validate, false, transitive, false, true, true, FilterHelper.NO_FILTER), report);
   }

   public IvyNode[] getDependencies(ModuleDescriptor md, String[] confs, File cache, Date date, ResolveReport report, boolean validate) {
      return this.ivy.getResolveEngine().getDependencies(md, this.newResolveOptions(confs, (String)null, cache, date, validate, false, true, false, true, true, FilterHelper.NO_FILTER), report);
   }

   public IvyNode[] getDependencies(URL ivySource, String[] confs, File cache, Date date, boolean validate) throws ParseException, IOException {
      return this.ivy.getResolveEngine().getDependencies(ivySource, this.newResolveOptions(confs, (String)null, cache, date, validate, false, true, false, true, true, FilterHelper.NO_FILTER));
   }

   public String getVariable(String name) {
      return this.ivy.getVariable(name);
   }

   public ResolveReport install(ModuleRevisionId mrid, String from, String to, boolean transitive, boolean validate, boolean overwrite, Filter artifactFilter, File cache, String matcherName) throws IOException {
      return this.ivy.install(mrid, from, to, (new InstallOptions()).setTransitive(transitive).setValidate(validate).setOverwrite(overwrite).setArtifactFilter(artifactFilter).setMatcherName(matcherName));
   }

   public void interrupt() {
      this.ivy.interrupt();
   }

   public void interrupt(Thread operatingThread) {
      this.ivy.interrupt(operatingThread);
   }

   public boolean isInterrupted() {
      return this.ivy.isInterrupted();
   }

   public ModuleEntry[] listModuleEntries(OrganisationEntry org) {
      return this.ivy.listModuleEntries(org);
   }

   public ModuleId[] listModules(ModuleId criteria, PatternMatcher matcher) {
      return this.ivy.listModules(criteria, matcher);
   }

   public ModuleRevisionId[] listModules(ModuleRevisionId criteria, PatternMatcher matcher) {
      return this.ivy.listModules(criteria, matcher);
   }

   public String[] listModules(String org) {
      return this.ivy.listModules(org);
   }

   public OrganisationEntry[] listOrganisationEntries() {
      return this.ivy.listOrganisationEntries();
   }

   public String[] listOrganisations() {
      return this.ivy.listOrganisations();
   }

   public RevisionEntry[] listRevisionEntries(ModuleEntry module) {
      return this.ivy.listRevisionEntries(module);
   }

   public String[] listRevisions(String org, String module) {
      return this.ivy.listRevisions(org, module);
   }

   public String[] listTokenValues(String token, Map otherTokenValues) {
      return this.ivy.listTokenValues(token, otherTokenValues);
   }

   public Collection publish(ModuleDescriptor md, DependencyResolver resolver, Collection srcArtifactPattern, String srcIvyPattern, Artifact[] extraArtifacts, boolean overwrite, String conf) throws IOException {
      return this.ivy.getPublishEngine().publish(md, srcArtifactPattern, resolver, (new PublishOptions()).setSrcIvyPattern(srcIvyPattern).setExtraArtifacts(extraArtifacts).setOverwrite(overwrite).setConfs(StringUtils.splitToArray(conf)));
   }

   public Collection publish(ModuleRevisionId mrid, String pubrevision, File cache, Collection srcArtifactPattern, String resolverName, String srcIvyPattern, String status, Date pubdate, Artifact[] extraArtifacts, boolean validate, boolean overwrite, boolean update, String conf) throws IOException {
      return this.ivy.publish(mrid, srcArtifactPattern, resolverName, (new PublishOptions()).setStatus(status).setPubdate(pubdate).setPubrevision(pubrevision).setSrcIvyPattern(srcIvyPattern).setExtraArtifacts(extraArtifacts).setUpdate(update).setValidate(validate).setOverwrite(overwrite).setConfs(StringUtils.splitToArray(conf)));
   }

   public Collection publish(ModuleRevisionId mrid, String pubrevision, File cache, String srcArtifactPattern, String resolverName, String srcIvyPattern, boolean validate, boolean overwrite) throws IOException {
      return this.ivy.publish(mrid, Collections.singleton(srcArtifactPattern), resolverName, (new PublishOptions()).setPubrevision(pubrevision).setSrcIvyPattern(srcIvyPattern).setValidate(validate).setOverwrite(overwrite));
   }

   public Collection publish(ModuleRevisionId mrid, String pubrevision, File cache, String srcArtifactPattern, String resolverName, String srcIvyPattern, boolean validate) throws IOException {
      return this.ivy.publish(mrid, Collections.singleton(srcArtifactPattern), resolverName, (new PublishOptions()).setPubrevision(pubrevision).setSrcIvyPattern(srcIvyPattern).setValidate(validate));
   }

   public Collection publish(ModuleRevisionId mrid, String pubrevision, File cache, String srcArtifactPattern, String resolverName, String srcIvyPattern, String status, Date pubdate, Artifact[] extraArtifacts, boolean validate, boolean overwrite, boolean update, String conf) throws IOException {
      return this.ivy.publish(mrid, Collections.singleton(srcArtifactPattern), resolverName, (new PublishOptions()).setStatus(status).setPubdate(pubdate).setPubrevision(pubrevision).setSrcIvyPattern(srcIvyPattern).setExtraArtifacts(extraArtifacts).setUpdate(update).setValidate(validate).setOverwrite(overwrite).setConfs(StringUtils.splitToArray(conf)));
   }

   public ResolveReport resolve(File ivySource) throws ParseException, IOException {
      return this.ivy.resolve(ivySource);
   }

   public ResolveReport resolve(ModuleDescriptor md, String[] confs, File cache, Date date, boolean validate, boolean useCacheOnly, boolean transitive, boolean useOrigin, boolean download, boolean outputReport, Filter artifactFilter) throws ParseException, IOException {
      return this.ivy.resolve(md, this.newResolveOptions(confs, (String)null, cache, date, validate, useCacheOnly, transitive, useOrigin, download, outputReport, artifactFilter));
   }

   private ResolveOptions newResolveOptions(String[] confs, String revision, File cache, Date date, boolean validate, boolean useCacheOnly, boolean transitive, boolean useOrigin, boolean download, boolean outputReport, Filter artifactFilter) {
      if (useOrigin) {
         this.ivy.getSettings().useDeprecatedUseOrigin();
      }

      return (new ResolveOptions()).setConfs(confs).setRevision(revision).setValidate(validate).setUseCacheOnly(useCacheOnly).setTransitive(transitive).setDownload(download).setOutputReport(outputReport).setArtifactFilter(artifactFilter);
   }

   public ResolveReport resolve(ModuleDescriptor md, String[] confs, File cache, Date date, boolean validate, boolean useCacheOnly, boolean transitive, boolean download, boolean outputReport, Filter artifactFilter) throws ParseException, IOException {
      return this.ivy.resolve(md, this.newResolveOptions(confs, (String)null, cache, date, validate, useCacheOnly, transitive, false, download, outputReport, artifactFilter));
   }

   public ResolveReport resolve(ModuleDescriptor md, String[] confs, File cache, Date date, boolean validate, boolean useCacheOnly, boolean transitive, Filter artifactFilter) throws ParseException, IOException {
      return this.ivy.resolve(md, this.newResolveOptions(confs, (String)null, cache, date, validate, useCacheOnly, transitive, false, true, true, artifactFilter));
   }

   public ResolveReport resolve(ModuleDescriptor md, String[] confs, File cache, Date date, boolean validate, boolean useCacheOnly, Filter artifactFilter) throws ParseException, IOException {
      return this.ivy.resolve(md, this.newResolveOptions(confs, (String)null, cache, date, validate, useCacheOnly, true, false, true, true, artifactFilter));
   }

   public ResolveReport resolve(ModuleRevisionId mrid, String[] confs, boolean transitive, boolean changing, File cache, Date date, boolean validate, boolean useCacheOnly, boolean useOrigin, Filter artifactFilter) throws ParseException, IOException {
      return this.ivy.resolve(mrid, this.newResolveOptions(confs, (String)null, cache, date, validate, useCacheOnly, transitive, useOrigin, true, true, artifactFilter), changing);
   }

   public ResolveReport resolve(ModuleRevisionId mrid, String[] confs, boolean transitive, boolean changing, File cache, Date date, boolean validate, boolean useCacheOnly, Filter artifactFilter) throws ParseException, IOException {
      return this.ivy.resolve(mrid, this.newResolveOptions(confs, (String)null, cache, date, validate, useCacheOnly, transitive, false, true, true, artifactFilter), changing);
   }

   public ResolveReport resolve(ModuleRevisionId mrid, String[] confs) throws ParseException, IOException {
      return this.ivy.resolve(mrid, this.newResolveOptions(confs, (String)null, this.ivy.getSettings().getDefaultCache(), (Date)null, true, false, true, false, true, true, FilterHelper.NO_FILTER), false);
   }

   public ResolveReport resolve(URL ivySource, String revision, String[] confs, File cache, Date date, boolean validate, boolean useCacheOnly, boolean transitive, boolean useOrigin, Filter artifactFilter) throws ParseException, IOException {
      return this.ivy.resolve(ivySource, this.newResolveOptions(confs, revision, cache, date, validate, useCacheOnly, transitive, useOrigin, true, true, artifactFilter));
   }

   public ResolveReport resolve(URL ivySource, String revision, String[] confs, File cache, Date date, boolean validate, boolean useCacheOnly, boolean transitive, Filter artifactFilter) throws ParseException, IOException {
      return this.ivy.resolve(ivySource, this.newResolveOptions(confs, revision, cache, date, validate, useCacheOnly, transitive, false, true, true, artifactFilter));
   }

   public ResolveReport resolve(URL ivySource, String revision, String[] confs, File cache, Date date, boolean validate, boolean useCacheOnly, Filter artifactFilter) throws ParseException, IOException {
      return this.ivy.resolve(ivySource, this.newResolveOptions(confs, revision, cache, date, validate, useCacheOnly, true, false, true, true, artifactFilter));
   }

   public ResolveReport resolve(URL ivySource, String revision, String[] confs, File cache, Date date, boolean validate, boolean useCacheOnly) throws ParseException, IOException {
      return this.ivy.resolve(ivySource, this.newResolveOptions(confs, revision, cache, date, validate, useCacheOnly, true, false, true, true, FilterHelper.NO_FILTER));
   }

   public ResolveReport resolve(URL ivySource, String revision, String[] confs, File cache, Date date, boolean validate) throws ParseException, IOException {
      return this.ivy.resolve(ivySource, this.newResolveOptions(confs, revision, cache, date, validate, false, true, false, true, true, FilterHelper.NO_FILTER));
   }

   public ResolveReport resolve(URL ivySource) throws ParseException, IOException {
      return this.ivy.resolve(ivySource);
   }

   public int retrieve(ModuleId moduleId, String[] confs, File cache, String destFilePattern, String destIvyPattern, Filter artifactFilter, boolean sync, boolean useOrigin, boolean makeSymlinks) {
      try {
         return this.ivy.retrieve(new ModuleRevisionId(moduleId, Ivy.getWorkingRevision()), (new RetrieveOptions()).setConfs(confs).setDestArtifactPattern(destFilePattern).setDestIvyPattern(destIvyPattern).setArtifactFilter(artifactFilter).setSync(sync).setUseOrigin(useOrigin).setMakeSymlinks(makeSymlinks)).getNbrArtifactsCopied();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public int retrieve(ModuleId moduleId, String[] confs, File cache, String destFilePattern, String destIvyPattern, Filter artifactFilter, boolean sync, boolean useOrigin) {
      try {
         return this.ivy.retrieve(new ModuleRevisionId(moduleId, Ivy.getWorkingRevision()), (new RetrieveOptions()).setConfs(confs).setDestArtifactPattern(destFilePattern).setDestIvyPattern(destIvyPattern).setArtifactFilter(artifactFilter).setSync(sync).setUseOrigin(useOrigin)).getNbrArtifactsCopied();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public int retrieve(ModuleId moduleId, String[] confs, File cache, String destFilePattern, String destIvyPattern, Filter artifactFilter) {
      try {
         return this.ivy.retrieve(new ModuleRevisionId(moduleId, Ivy.getWorkingRevision()), (new RetrieveOptions()).setConfs(confs).setDestArtifactPattern(destFilePattern).setDestIvyPattern(destIvyPattern).setArtifactFilter(artifactFilter)).getNbrArtifactsCopied();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public int retrieve(ModuleId moduleId, String[] confs, File cache, String destFilePattern, String destIvyPattern) {
      try {
         return this.ivy.retrieve(new ModuleRevisionId(moduleId, Ivy.getWorkingRevision()), (new RetrieveOptions()).setConfs(confs).setDestArtifactPattern(destFilePattern).setDestIvyPattern(destIvyPattern)).getNbrArtifactsCopied();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public int retrieve(ModuleId moduleId, String[] confs, File cache, String destFilePattern) {
      try {
         return this.ivy.retrieve(new ModuleRevisionId(moduleId, Ivy.getWorkingRevision()), (new RetrieveOptions()).setConfs(confs).setDestArtifactPattern(destFilePattern)).getNbrArtifactsCopied();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public void setVariable(String varName, String value) {
      this.ivy.setVariable(varName, value);
   }

   public List sortModuleDescriptors(Collection moduleDescriptors) {
      return this.ivy.sortModuleDescriptors(moduleDescriptors, (new SortOptions()).setNonMatchingVersionReporter(new SilentNonMatchingVersionReporter()));
   }

   public List sortNodes(Collection nodes) {
      return this.ivy.sortNodes(nodes, (new SortOptions()).setNonMatchingVersionReporter(new SilentNonMatchingVersionReporter()));
   }

   public String substitute(String str) {
      return this.ivy.substitute(str);
   }
}
