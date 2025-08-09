package org.apache.ivy.core.publish;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.event.EventManager;
import org.apache.ivy.core.event.publish.EndArtifactPublishEvent;
import org.apache.ivy.core.event.publish.StartArtifactPublishEvent;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.MDArtifact;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.parser.xml.UpdateOptions;
import org.apache.ivy.plugins.parser.xml.XmlModuleDescriptorParser;
import org.apache.ivy.plugins.parser.xml.XmlModuleDescriptorUpdater;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.util.ConfigurationUtils;
import org.apache.ivy.util.Message;
import org.xml.sax.SAXException;

public class PublishEngine {
   private PublishEngineSettings settings;
   private EventManager eventManager;

   public PublishEngine(PublishEngineSettings settings, EventManager eventManager) {
      this.settings = settings;
      this.eventManager = eventManager;
   }

   public Collection publish(ModuleRevisionId mrid, Collection srcArtifactPattern, String resolverName, PublishOptions options) throws IOException {
      Message.info(":: publishing :: " + mrid.getModuleId());
      Message.verbose("\tvalidate = " + options.isValidate());
      long start = System.currentTimeMillis();
      options.setSrcIvyPattern(this.settings.substitute(options.getSrcIvyPattern()));
      if (options.getPubBranch() == null) {
         options.setPubbranch(mrid.getBranch());
      }

      if (options.getPubrevision() == null) {
         options.setPubrevision(mrid.getRevision());
      }

      ModuleRevisionId pubmrid = ModuleRevisionId.newInstance(mrid, options.getPubBranch(), options.getPubrevision());
      ModuleDescriptor md = null;
      if (options.getSrcIvyPattern() != null) {
         File ivyFile = this.settings.resolveFile(IvyPatternHelper.substitute(options.getSrcIvyPattern(), DefaultArtifact.newIvyArtifact(pubmrid, new Date())));
         if (!ivyFile.exists()) {
            throw new IllegalArgumentException("ivy file to publish not found for " + mrid + ": call deliver before (" + ivyFile + ")");
         }

         URL ivyFileURL = ivyFile.toURI().toURL();

         try {
            md = XmlModuleDescriptorParser.getInstance().parseDescriptor(this.settings, ivyFileURL, false);
            if (options.isUpdate()) {
               File tmp = File.createTempFile("ivy", ".xml");
               tmp.deleteOnExit();
               String[] confs = ConfigurationUtils.replaceWildcards(options.getConfs(), md);
               Set<String> confsToRemove = new HashSet(Arrays.asList(md.getConfigurationsNames()));
               confsToRemove.removeAll(Arrays.asList(confs));

               try {
                  XmlModuleDescriptorUpdater.update(ivyFileURL, tmp, (new UpdateOptions()).setSettings(this.settings).setStatus(options.getStatus() == null ? md.getStatus() : options.getStatus()).setRevision(options.getPubrevision()).setBranch(options.getPubBranch()).setPubdate(options.getPubdate() == null ? new Date() : options.getPubdate()).setMerge(options.isMerge()).setMergedDescriptor(md).setConfsToExclude((String[])confsToRemove.toArray(new String[confsToRemove.size()])));
                  md = XmlModuleDescriptorParser.getInstance().parseDescriptor(this.settings, tmp.toURI().toURL(), false);
                  options.setSrcIvyPattern(tmp.getAbsolutePath());
               } catch (SAXException e) {
                  throw new IllegalStateException("bad ivy file for " + mrid + ": " + ivyFile + ": " + e);
               }
            } else if (!options.getPubrevision().equals(md.getModuleRevisionId().getRevision())) {
               throw new IllegalArgumentException("cannot publish " + ivyFile + " as " + options.getPubrevision() + ": bad revision found in ivy file (Revision: " + md.getModuleRevisionId().getRevision() + "). Use forcedeliver or update.");
            }
         } catch (ParseException e) {
            throw new IllegalStateException("bad ivy file for " + mrid + ": " + ivyFile + ": " + e);
         }
      } else {
         ResolutionCacheManager cacheManager = this.settings.getResolutionCacheManager();

         try {
            md = cacheManager.getResolvedModuleDescriptor(mrid);
         } catch (ParseException e) {
            throw new IllegalStateException("bad ivy file in cache for " + mrid + ": " + e);
         }

         md.setResolvedModuleRevisionId(pubmrid);
      }

      DependencyResolver resolver = this.settings.getResolver(resolverName);
      if (resolver == null) {
         throw new IllegalArgumentException("unknown resolver " + resolverName);
      } else {
         Collection<Artifact> missing = this.publish(md, srcArtifactPattern, resolver, options);
         Message.verbose("\tpublish done (" + (System.currentTimeMillis() - start) + "ms)");
         return missing;
      }
   }

   public Collection publish(ModuleDescriptor md, Collection srcArtifactPattern, DependencyResolver resolver, PublishOptions options) throws IOException {
      Collection<Artifact> missing = new ArrayList();
      Set<Artifact> artifactsSet = new LinkedHashSet();

      for(String conf : ConfigurationUtils.replaceWildcards(options.getConfs(), md)) {
         artifactsSet.addAll(Arrays.asList(md.getArtifacts(conf)));
      }

      Artifact[] extraArtifacts = options.getExtraArtifacts();
      if (extraArtifacts != null) {
         for(Artifact extraArtifact : extraArtifacts) {
            artifactsSet.add(new MDArtifact(md, extraArtifact.getName(), extraArtifact.getType(), extraArtifact.getExt(), extraArtifact.getUrl(), extraArtifact.getQualifiedExtraAttributes()));
         }
      }

      Map<Artifact, File> artifactsFiles = new LinkedHashMap();

      for(Artifact artifact : artifactsSet) {
         for(String pattern : srcArtifactPattern) {
            File artifactFile = this.settings.resolveFile(IvyPatternHelper.substitute(this.settings.substitute(pattern), artifact));
            if (artifactFile.exists()) {
               artifactsFiles.put(artifact, artifactFile);
               break;
            }
         }

         if (!artifactsFiles.containsKey(artifact)) {
            StringBuilder sb = new StringBuilder();
            sb.append("missing artifact ").append(artifact).append(":\n");

            for(String pattern : srcArtifactPattern) {
               sb.append("\t").append(this.settings.resolveFile(IvyPatternHelper.substitute(pattern, artifact))).append(" file does not exist\n");
            }

            if (!options.isWarnOnMissing() && !options.isHaltOnMissing()) {
               Message.verbose(sb.toString());
            } else {
               Message.warn(sb.toString());
            }

            if (options.isHaltOnMissing()) {
               throw new IOException("missing artifact " + artifact);
            }

            missing.add(artifact);
         }
      }

      if (options.getSrcIvyPattern() != null) {
         Artifact artifact = MDArtifact.newIvyArtifact(md);
         File artifactFile = this.settings.resolveFile(IvyPatternHelper.substitute(options.getSrcIvyPattern(), artifact));
         if (!artifactFile.exists()) {
            String msg = "missing ivy file for " + md.getModuleRevisionId() + ": \n" + artifactFile + " file does not exist";
            if (!options.isWarnOnMissing() && !options.isHaltOnMissing()) {
               Message.verbose(msg);
            } else {
               Message.warn(msg);
            }

            if (options.isHaltOnMissing()) {
               throw new IOException("missing ivy artifact " + artifact);
            }

            missing.add(artifact);
         } else {
            artifactsFiles.put(artifact, artifactFile);
         }
      }

      boolean successfullyPublished = false;

      try {
         resolver.beginPublishTransaction(md.getModuleRevisionId(), options.isOverwrite());

         for(Map.Entry entry : artifactsFiles.entrySet()) {
            this.publish((Artifact)entry.getKey(), (File)entry.getValue(), resolver, options.isOverwrite());
         }

         resolver.commitPublishTransaction();
         successfullyPublished = true;
      } finally {
         if (!successfullyPublished) {
            resolver.abortPublishTransaction();
         }

      }

      return missing;
   }

   private void publish(Artifact artifact, File src, DependencyResolver resolver, boolean overwrite) throws IOException {
      IvyContext.getContext().checkInterrupted();
      this.eventManager.fireIvyEvent(new StartArtifactPublishEvent(resolver, artifact, src, overwrite));
      boolean successful = false;

      try {
         if (src.exists()) {
            resolver.publish(artifact, src, overwrite);
            successful = true;
         }
      } finally {
         this.eventManager.fireIvyEvent(new EndArtifactPublishEvent(resolver, artifact, src, overwrite, successful));
      }

   }
}
