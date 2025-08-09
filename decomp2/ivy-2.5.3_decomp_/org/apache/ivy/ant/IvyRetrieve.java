package org.apache.ivy.ant;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.retrieve.RetrieveOptions;
import org.apache.ivy.core.retrieve.RetrieveReport;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.filter.Filter;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.util.FileNameMapper;

public class IvyRetrieve extends IvyPostResolveTask {
   private static final Collection OVERWRITEMODE_VALUES = Arrays.asList("always", "never", "newer", "different");
   private String pattern;
   private String ivypattern = null;
   private boolean sync = false;
   private boolean symlink = false;
   private boolean symlinkmass = false;
   private String overwriteMode = "newer";
   private String pathId = null;
   private String setId = null;
   private Mapper mapper = null;

   public String getPattern() {
      return this.pattern;
   }

   public void setPattern(String pattern) {
      this.pattern = pattern;
   }

   public String getPathId() {
      return this.pathId;
   }

   public void setPathId(String pathId) {
      this.pathId = pathId;
   }

   public String getSetId() {
      return this.setId;
   }

   public void setSetId(String setId) {
      this.setId = setId;
   }

   public void doExecute() throws BuildException {
      this.prepareAndCheck();
      if (!this.getAllowedLogOptions().contains(this.getLog())) {
         throw new BuildException("invalid option for 'log': " + this.getLog() + ". Available options are " + this.getAllowedLogOptions());
      } else {
         this.pattern = this.getProperty(this.pattern, this.getSettings(), "ivy.retrieve.pattern");

         try {
            Filter<Artifact> artifactFilter = this.getArtifactFilter();
            RetrieveOptions retrieveOptions = (RetrieveOptions)(new RetrieveOptions()).setLog(this.getLog());
            retrieveOptions.setConfs(StringUtils.splitToArray(this.getConf())).setDestArtifactPattern(this.pattern).setDestIvyPattern(this.ivypattern).setArtifactFilter(artifactFilter).setSync(this.sync).setOverwriteMode(this.getOverwriteMode()).setUseOrigin(this.isUseOrigin()).setMakeSymlinks(this.symlink).setResolveId(this.getResolveId()).setMapper(this.mapper == null ? null : new MapperAdapter(this.mapper));
            if (this.symlinkmass) {
               retrieveOptions.setMakeSymlinksInMass(this.symlinkmass);
            }

            RetrieveReport report = this.getIvyInstance().retrieve(this.getResolvedMrid(), retrieveOptions);
            int targetsCopied = report.getNbrArtifactsCopied();
            boolean haveTargetsBeenCopied = targetsCopied > 0;
            this.getProject().setProperty("ivy.nb.targets.copied", String.valueOf(targetsCopied));
            this.getProject().setProperty("ivy.targets.copied", String.valueOf(haveTargetsBeenCopied));
            if (this.getPathId() != null) {
               Path path = new Path(this.getProject());
               this.getProject().addReference(this.getPathId(), path);

               for(File file : report.getRetrievedFiles()) {
                  path.createPathElement().setLocation(file);
               }
            }

            if (this.getSetId() != null) {
               Collection<File> retrievedFiles = report.getRetrievedFiles();
               FileSet fileset;
               if (retrievedFiles.isEmpty()) {
                  fileset = new EmptyFileSet();
                  fileset.setProject(this.getProject());
               } else {
                  fileset = new FileSet();
                  fileset.setProject(this.getProject());
                  fileset.setDir(report.getRetrieveRoot());

                  for(File file : retrievedFiles) {
                     PatternSet.NameEntry ne = fileset.createInclude();
                     ne.setName(this.getPath(report.getRetrieveRoot(), file));
                  }
               }

               this.getProject().addReference(this.getSetId(), fileset);
            }

         } catch (Exception ex) {
            throw new BuildException("impossible to ivy retrieve: " + ex, ex);
         }
      }
   }

   protected Collection getAllowedLogOptions() {
      return Arrays.asList("default", "download-only", "quiet");
   }

   public String getIvypattern() {
      return this.ivypattern;
   }

   public void setIvypattern(String ivypattern) {
      this.ivypattern = ivypattern;
   }

   public boolean isSync() {
      return this.sync;
   }

   public void setSync(boolean sync) {
      this.sync = sync;
   }

   public void setSymlink(boolean symlink) {
      this.symlink = symlink;
   }

   /** @deprecated */
   @Deprecated
   public void setSymlinkmass(boolean symlinkmass) {
      this.symlinkmass = symlinkmass;
   }

   public void setOverwriteMode(String overwriteMode) {
      if (!OVERWRITEMODE_VALUES.contains(overwriteMode)) {
         throw new IllegalArgumentException("invalid overwriteMode value '" + overwriteMode + "'. Valid values are " + OVERWRITEMODE_VALUES);
      } else {
         this.overwriteMode = overwriteMode;
      }
   }

   public String getOverwriteMode() {
      return this.overwriteMode;
   }

   public void addMapper(Mapper mapper) {
      if (this.mapper != null) {
         throw new IllegalArgumentException("Cannot define more than one mapper");
      } else {
         this.mapper = mapper;
      }
   }

   public void add(FileNameMapper fileNameMapper) {
      Mapper m = new Mapper(this.getProject());
      m.add(fileNameMapper);
      this.addMapper(m);
   }

   private String getPath(File base, File file) {
      String absoluteBasePath = base.getAbsolutePath();
      int beginIndex = absoluteBasePath.length();
      if (!absoluteBasePath.endsWith(File.separator)) {
         ++beginIndex;
      }

      return file.getAbsolutePath().substring(beginIndex);
   }
}
