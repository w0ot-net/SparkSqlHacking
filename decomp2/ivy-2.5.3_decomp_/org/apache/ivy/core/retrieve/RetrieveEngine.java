package org.apache.ivy.core.retrieve;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.event.EventManager;
import org.apache.ivy.core.event.retrieve.EndRetrieveArtifactEvent;
import org.apache.ivy.core.event.retrieve.EndRetrieveEvent;
import org.apache.ivy.core.event.retrieve.StartRetrieveArtifactEvent;
import org.apache.ivy.core.event.retrieve.StartRetrieveEvent;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ArtifactRevisionId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.pack.PackagingManager;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.plugins.report.XmlReportParser;
import org.apache.ivy.util.CopyProgressListener;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.Message;

public class RetrieveEngine {
   private static final int KILO = 1024;
   private RetrieveEngineSettings settings;
   private EventManager eventManager;

   public RetrieveEngine(RetrieveEngineSettings settings, EventManager eventManager) {
      this.settings = settings;
      this.eventManager = eventManager;
   }

   /** @deprecated */
   @Deprecated
   public int retrieve(ModuleRevisionId mrid, String destFilePattern, RetrieveOptions options) throws IOException {
      RetrieveOptions retrieveOptions = new RetrieveOptions(options);
      retrieveOptions.setDestArtifactPattern(destFilePattern);
      RetrieveReport result = this.retrieve(mrid, retrieveOptions);
      return result.getNbrArtifactsCopied();
   }

   public RetrieveReport retrieve(ModuleRevisionId mrid, RetrieveOptions options) throws IOException {
      RetrieveReport report = new RetrieveReport();
      ModuleId moduleId = mrid.getModuleId();
      if ("default".equals(options.getLog())) {
         Message.info(":: retrieving :: " + moduleId + (options.isSync() ? " [sync]" : ""));
      } else {
         Message.verbose(":: retrieving :: " + moduleId + (options.isSync() ? " [sync]" : ""));
      }

      Message.verbose("\tcheckUpToDate=" + this.settings.isCheckUpToDate());
      long start = System.currentTimeMillis();
      String destFilePattern = IvyPatternHelper.substituteVariables(options.getDestArtifactPattern(), this.settings.getVariables());
      String destIvyPattern = IvyPatternHelper.substituteVariables(options.getDestIvyPattern(), this.settings.getVariables());
      String[] confs = this.getConfs(mrid, options);
      if ("default".equals(options.getLog())) {
         Message.info("\tconfs: " + Arrays.asList(confs));
      } else {
         Message.verbose("\tconfs: " + Arrays.asList(confs));
      }

      if (this.eventManager != null) {
         this.eventManager.fireIvyEvent(new StartRetrieveEvent(mrid, confs, options));
      }

      try {
         Map<ArtifactDownloadReport, Set<String>> artifactsToCopy = this.determineArtifactsToCopy(mrid, destFilePattern, options);
         File fileRetrieveRoot = this.settings.resolveFile(IvyPatternHelper.getTokenRoot(destFilePattern));
         report.setRetrieveRoot(fileRetrieveRoot);
         File ivyRetrieveRoot = destIvyPattern == null ? null : this.settings.resolveFile(IvyPatternHelper.getTokenRoot(destIvyPattern));
         Collection<File> targetArtifactsStructure = new HashSet();
         Collection<File> targetIvysStructure = new HashSet();
         long totalCopiedSize = 0L;

         for(Map.Entry artifactAndPaths : artifactsToCopy.entrySet()) {
            ArtifactDownloadReport artifact = (ArtifactDownloadReport)artifactAndPaths.getKey();
            File archive = artifact.getLocalFile();
            if (artifact.getUnpackedLocalFile() != null) {
               archive = artifact.getUnpackedLocalFile();
            }

            if (archive == null) {
               Message.verbose("\tno local file available for " + artifact + ": skipping");
            } else {
               Message.verbose("\tretrieving " + archive);

               for(String path : (Set)artifactAndPaths.getValue()) {
                  IvyContext.getContext().checkInterrupted();
                  File destFile = this.settings.resolveFile(path);
                  if (this.settings.isCheckUpToDate() && this.upToDate(archive, destFile, options)) {
                     Message.verbose("\t\tto " + destFile + " [NOT REQUIRED]");
                     report.addUpToDateFile(destFile, artifact);
                  } else {
                     Message.verbose("\t\tto " + destFile);
                     if (this.eventManager != null) {
                        this.eventManager.fireIvyEvent(new StartRetrieveArtifactEvent(artifact, destFile));
                     }

                     if (options.isMakeSymlinks()) {
                        boolean symlinkCreated;
                        try {
                           symlinkCreated = FileUtil.symlink(archive, destFile, true);
                        } catch (IOException ioe) {
                           symlinkCreated = false;
                           Message.warn("symlink creation failed at path " + destFile, ioe);
                        }

                        if (!symlinkCreated) {
                           Message.info("Attempting a copy operation (since symlink creation failed) at path " + destFile);
                           FileUtil.copy((File)archive, (File)destFile, (CopyProgressListener)null, true);
                        }
                     } else {
                        FileUtil.copy((File)archive, (File)destFile, (CopyProgressListener)null, true);
                     }

                     if (this.eventManager != null) {
                        this.eventManager.fireIvyEvent(new EndRetrieveArtifactEvent(artifact, destFile));
                     }

                     totalCopiedSize += FileUtil.getFileLength(destFile);
                     report.addCopiedFile(destFile, artifact);
                  }

                  if ("ivy".equals(artifact.getType())) {
                     targetIvysStructure.addAll(FileUtil.getPathFiles(ivyRetrieveRoot, destFile));
                  } else {
                     for(File file : FileUtil.listAll(destFile, Collections.emptyList())) {
                        targetArtifactsStructure.addAll(FileUtil.getPathFiles(fileRetrieveRoot, file));
                     }
                  }
               }
            }
         }

         if (options.isSync()) {
            Message.verbose("\tsyncing...");
            String[] ignorableFilenames = this.settings.getIgnorableFilenames();
            Collection<String> ignoreList = Arrays.asList(ignorableFilenames);
            Collection<File> existingArtifacts = FileUtil.listAll(fileRetrieveRoot, ignoreList);
            Collection<File> existingIvys = ivyRetrieveRoot == null ? null : FileUtil.listAll(ivyRetrieveRoot, ignoreList);
            if (fileRetrieveRoot.equals(ivyRetrieveRoot)) {
               targetArtifactsStructure.addAll(targetIvysStructure);
               existingArtifacts.addAll(existingIvys);
               this.sync(targetArtifactsStructure, existingArtifacts);
            } else {
               this.sync(targetArtifactsStructure, existingArtifacts);
               if (existingIvys != null) {
                  this.sync(targetIvysStructure, existingIvys);
               }
            }
         }

         long elapsedTime = System.currentTimeMillis() - start;
         String msg = "\t" + report.getNbrArtifactsCopied() + " artifacts copied" + (this.settings.isCheckUpToDate() ? ", " + report.getNbrArtifactsUpToDate() + " already retrieved" : "") + " (" + totalCopiedSize / 1024L + "kB/" + elapsedTime + "ms)";
         if ("default".equals(options.getLog())) {
            Message.info(msg);
         } else {
            Message.verbose(msg);
         }

         Message.verbose("\tretrieve done (" + elapsedTime + "ms)");
         if (this.eventManager != null) {
            this.eventManager.fireIvyEvent(new EndRetrieveEvent(mrid, confs, elapsedTime, report.getNbrArtifactsCopied(), report.getNbrArtifactsUpToDate(), totalCopiedSize, options));
         }

         return report;
      } catch (Exception ex) {
         throw new RuntimeException("problem during retrieve of " + moduleId + ": " + ex, ex);
      }
   }

   private String[] getConfs(ModuleRevisionId mrid, RetrieveOptions options) throws IOException {
      String[] confs = options.getConfs();
      if (confs == null || confs.length == 1 && "*".equals(confs[0])) {
         try {
            ModuleDescriptor md = this.getCache().getResolvedModuleDescriptor(mrid);
            Message.verbose("no explicit confs given for retrieve, using ivy file: " + md.getResource().getName());
            confs = md.getConfigurationsNames();
            options.setConfs(confs);
         } catch (IOException e) {
            throw e;
         } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
         }
      }

      return confs;
   }

   private ResolutionCacheManager getCache() {
      return this.settings.getResolutionCacheManager();
   }

   private void sync(Collection target, Collection existing) {
      Collection<File> toRemove = new HashSet();

      for(File file : existing) {
         toRemove.add(file.getAbsoluteFile());
      }

      for(File file : target) {
         toRemove.remove(file.getAbsoluteFile());
      }

      for(File file : toRemove) {
         if (file.exists()) {
            Message.verbose("\t\tdeleting " + file);
            FileUtil.forceDelete(file);
         }
      }

   }

   public Map determineArtifactsToCopy(ModuleRevisionId mrid, String destFilePattern, RetrieveOptions options) throws ParseException, IOException {
      ModuleId moduleId = mrid.getModuleId();
      if (options.getResolveId() == null) {
         options.setResolveId(ResolveOptions.getDefaultResolveId(moduleId));
      }

      ResolutionCacheManager cacheManager = this.getCache();
      String[] confs = this.getConfs(mrid, options);
      String destIvyPattern = IvyPatternHelper.substituteVariables(options.getDestIvyPattern(), this.settings.getVariables());
      File fileRetrieveRoot = this.settings.resolveFile(IvyPatternHelper.getTokenRoot(destFilePattern));
      File ivyRetrieveRoot = destIvyPattern == null ? null : this.settings.resolveFile(IvyPatternHelper.getTokenRoot(destIvyPattern));
      Map<ArtifactDownloadReport, Set<String>> artifactsToCopy = new HashMap();
      Map<String, Set<ArtifactRevisionId>> conflictsMap = new HashMap();
      Map<String, Set<ArtifactDownloadReport>> conflictsReportsMap = new HashMap();
      Map<String, Set<String>> conflictsConfMap = new HashMap();
      XmlReportParser parser = new XmlReportParser();

      for(String conf : confs) {
         File report = cacheManager.getConfigurationResolveReportInCache(options.getResolveId(), conf);
         parser.parse(report);
         Collection<ArtifactDownloadReport> artifacts = new ArrayList(Arrays.asList(parser.getArtifactReports()));
         if (destIvyPattern != null) {
            for(ModuleRevisionId rmrid : parser.getRealDependencyRevisionIds()) {
               artifacts.add(parser.getMetadataArtifactReport(rmrid));
            }
         }

         PackagingManager packagingManager = new PackagingManager();
         packagingManager.setSettings(IvyContext.getContext().getSettings());

         for(ArtifactDownloadReport adr : artifacts) {
            Artifact artifact = adr.getArtifact();
            String ext;
            if (adr.getUnpackedLocalFile() == null) {
               ext = artifact.getExt();
            } else {
               Artifact unpackedArtifact;
               if (adr.getUnpackedArtifact() != null) {
                  unpackedArtifact = adr.getUnpackedArtifact();
               } else {
                  unpackedArtifact = packagingManager.getUnpackedArtifact(artifact);
               }

               if (unpackedArtifact == null) {
                  throw new RuntimeException("Could not determine unpacked artifact for " + artifact + " while determining artifacts to copy for module " + mrid);
               }

               ext = unpackedArtifact.getExt();
            }

            String destPattern = "ivy".equals(adr.getType()) ? destIvyPattern : destFilePattern;
            File root = "ivy".equals(adr.getType()) ? ivyRetrieveRoot : fileRetrieveRoot;
            if ("ivy".equals(adr.getType()) || options.getArtifactFilter().accept(adr.getArtifact())) {
               ModuleRevisionId aMrid = artifact.getModuleRevisionId();
               String destFileName = IvyPatternHelper.substitute(destPattern, aMrid.getOrganisation(), aMrid.getName(), aMrid.getBranch(), aMrid.getRevision(), artifact.getName(), artifact.getType(), ext, conf, adr.getArtifactOrigin(), aMrid.getQualifiedExtraAttributes(), artifact.getQualifiedExtraAttributes());
               Set<String> dest = (Set)artifactsToCopy.get(adr);
               if (dest == null) {
                  dest = new HashSet();
                  artifactsToCopy.put(adr, dest);
               }

               File copyDestFile = this.settings.resolveFile(destFileName).getAbsoluteFile();
               if (root != null && !FileUtil.isLeadingPath(root, copyDestFile)) {
                  Message.warn("not retrieving artifact " + artifact + " as its destination " + copyDestFile + " is not inside " + root);
               } else {
                  String copyDest = copyDestFile.getPath();
                  String[] destinations = new String[]{copyDest};
                  if (options.getMapper() != null) {
                     destinations = options.getMapper().mapFileName(copyDest);
                  }

                  for(String destination : destinations) {
                     dest.add(destination);
                     Set<ArtifactRevisionId> conflicts = (Set)conflictsMap.get(destination);
                     Set<ArtifactDownloadReport> conflictsReports = (Set)conflictsReportsMap.get(destination);
                     Set<String> conflictsConf = (Set)conflictsConfMap.get(destination);
                     if (conflicts == null) {
                        conflicts = new HashSet();
                        conflictsMap.put(destination, conflicts);
                     }

                     if (conflictsReports == null) {
                        conflictsReports = new HashSet();
                        conflictsReportsMap.put(destination, conflictsReports);
                     }

                     if (conflictsConf == null) {
                        conflictsConf = new HashSet();
                        conflictsConfMap.put(destination, conflictsConf);
                     }

                     if (conflicts.add(artifact.getId())) {
                        conflictsReports.add(adr);
                        conflictsConf.add(conf);
                     }
                  }
               }
            }
         }
      }

      for(Map.Entry entry : conflictsMap.entrySet()) {
         String copyDest = (String)entry.getKey();
         Set<ArtifactRevisionId> artifacts = (Set)entry.getValue();
         Set<String> conflictsConfs = (Set)conflictsConfMap.get(copyDest);
         if (artifacts.size() > 1) {
            List<ArtifactDownloadReport> artifactsList = new ArrayList((Collection)conflictsReportsMap.get(copyDest));
            Collections.sort(artifactsList, this.getConflictResolvingPolicy());
            ArtifactDownloadReport winner = (ArtifactDownloadReport)artifactsList.get(artifactsList.size() - 1);
            ModuleRevisionId winnerMD = winner.getArtifact().getModuleRevisionId();

            for(int i = artifactsList.size() - 2; i >= 0; --i) {
               ArtifactDownloadReport current = (ArtifactDownloadReport)artifactsList.get(i);
               if (winnerMD.equals(current.getArtifact().getModuleRevisionId())) {
                  throw new RuntimeException("Multiple artifacts of the module " + winnerMD + " are retrieved to the same file! Update the retrieve pattern to fix this error.");
               }
            }

            Message.info("\tconflict on " + copyDest + " in " + conflictsConfs + ": " + winnerMD.getRevision() + " won");

            for(int i = artifactsList.size() - 2; i >= 0; --i) {
               ArtifactDownloadReport looser = (ArtifactDownloadReport)artifactsList.get(i);
               Message.verbose("\t\tremoving conflict looser artifact: " + looser.getArtifact());
               Set<String> dest = (Set)artifactsToCopy.get(looser);
               dest.remove(copyDest);
               if (dest.isEmpty()) {
                  artifactsToCopy.remove(looser);
               }
            }
         }
      }

      return artifactsToCopy;
   }

   private boolean upToDate(File source, File target, RetrieveOptions options) {
      if (!target.exists()) {
         return false;
      } else {
         String overwriteMode = options.getOverwriteMode();
         if ("always".equals(overwriteMode)) {
            return false;
         } else if ("never".equals(overwriteMode)) {
            return true;
         } else if ("newer".equals(overwriteMode)) {
            return source.lastModified() <= target.lastModified();
         } else if ("different".equals(overwriteMode)) {
            return source.lastModified() == target.lastModified();
         } else {
            return false;
         }
      }
   }

   private Comparator getConflictResolvingPolicy() {
      return new Comparator() {
         public int compare(ArtifactDownloadReport o1, ArtifactDownloadReport o2) {
            Artifact a1 = o1.getArtifact();
            Artifact a2 = o2.getArtifact();
            if (a1.getPublicationDate().after(a2.getPublicationDate())) {
               return 1;
            } else {
               return a1.getPublicationDate().before(a2.getPublicationDate()) ? -1 : 0;
            }
         }
      };
   }
}
