package org.apache.logging.log4j.core.appender.rolling;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.core.appender.rolling.action.Action;
import org.apache.logging.log4j.core.appender.rolling.action.CompositeAction;
import org.apache.logging.log4j.core.appender.rolling.action.FileRenameAction;
import org.apache.logging.log4j.core.appender.rolling.action.PathCondition;
import org.apache.logging.log4j.core.appender.rolling.action.PosixViewAttributeAction;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;
import org.apache.logging.log4j.core.util.Integers;

@Plugin(
   name = "DirectWriteRolloverStrategy",
   category = "Core",
   printObject = true
)
public class DirectWriteRolloverStrategy extends AbstractRolloverStrategy implements DirectFileRolloverStrategy {
   private static final int DEFAULT_MAX_FILES = 7;
   private final int maxFiles;
   private final int compressionLevel;
   private final List customActions;
   private final boolean stopCustomActionsOnError;
   private volatile String currentFileName;
   private int nextIndex;
   private final PatternProcessor tempCompressedFilePattern;
   private volatile boolean usePrevTime;

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   /** @deprecated */
   @Deprecated
   @PluginFactory
   public static DirectWriteRolloverStrategy createStrategy(@PluginAttribute("maxFiles") final String maxFiles, @PluginAttribute("compressionLevel") final String compressionLevelStr, @PluginElement("Actions") final Action[] customActions, @PluginAttribute(value = "stopCustomActionsOnError",defaultBoolean = true) final boolean stopCustomActionsOnError, @PluginConfiguration final Configuration config) {
      return newBuilder().withMaxFiles(maxFiles).withCompressionLevelStr(compressionLevelStr).withCustomActions(customActions).withStopCustomActionsOnError(stopCustomActionsOnError).withConfig(config).build();
   }

   /** @deprecated */
   @Deprecated
   protected DirectWriteRolloverStrategy(final int maxFiles, final int compressionLevel, final StrSubstitutor strSubstitutor, final Action[] customActions, final boolean stopCustomActionsOnError) {
      this(maxFiles, compressionLevel, strSubstitutor, customActions, stopCustomActionsOnError, (String)null);
   }

   protected DirectWriteRolloverStrategy(final int maxFiles, final int compressionLevel, final StrSubstitutor strSubstitutor, final Action[] customActions, final boolean stopCustomActionsOnError, final String tempCompressedFilePatternString) {
      super(strSubstitutor);
      this.nextIndex = -1;
      this.usePrevTime = false;
      this.maxFiles = maxFiles;
      this.compressionLevel = compressionLevel;
      this.stopCustomActionsOnError = stopCustomActionsOnError;
      this.customActions = customActions == null ? Collections.emptyList() : Arrays.asList(customActions);
      this.tempCompressedFilePattern = tempCompressedFilePatternString != null ? new PatternProcessor(tempCompressedFilePatternString) : null;
   }

   public int getCompressionLevel() {
      return this.compressionLevel;
   }

   public List getCustomActions() {
      return this.customActions;
   }

   public int getMaxFiles() {
      return this.maxFiles;
   }

   public boolean isStopCustomActionsOnError() {
      return this.stopCustomActionsOnError;
   }

   public PatternProcessor getTempCompressedFilePattern() {
      return this.tempCompressedFilePattern;
   }

   private int purge(final RollingFileManager manager) {
      SortedMap<Integer, Path> eligibleFiles = this.getEligibleFiles(manager);
      LOGGER.debug("Found {} eligible files, max is  {}", eligibleFiles.size(), this.maxFiles);

      while(eligibleFiles.size() >= this.maxFiles) {
         try {
            Integer key = (Integer)eligibleFiles.firstKey();
            Files.delete((Path)eligibleFiles.get(key));
            eligibleFiles.remove(key);
         } catch (IOException ioe) {
            LOGGER.error("Unable to delete {}", eligibleFiles.firstKey(), ioe);
            break;
         }
      }

      return eligibleFiles.size() > 0 ? (Integer)eligibleFiles.lastKey() : 1;
   }

   public String getCurrentFileName(final RollingFileManager manager) {
      if (this.currentFileName == null) {
         SortedMap<Integer, Path> eligibleFiles = this.getEligibleFiles(manager);
         int fileIndex = eligibleFiles.size() > 0 ? (this.nextIndex > 0 ? this.nextIndex : (Integer)eligibleFiles.lastKey()) : 1;
         StringBuilder buf = new StringBuilder(255);
         manager.getPatternProcessor().setCurrentFileTime(System.currentTimeMillis());
         manager.getPatternProcessor().formatFileName(this.strSubstitutor, buf, true, fileIndex);
         int suffixLength = this.suffixLength(buf.toString());
         String name = suffixLength > 0 ? buf.substring(0, buf.length() - suffixLength) : buf.toString();
         this.currentFileName = name;
      }

      return this.currentFileName;
   }

   public void clearCurrentFileName() {
      this.currentFileName = null;
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The name of the accessed files is based on a configuration value."
   )
   public RolloverDescription rollover(final RollingFileManager manager) throws SecurityException {
      LOGGER.debug("Rolling " + this.currentFileName);
      if (this.maxFiles < 0) {
         return null;
      } else {
         long startNanos = System.nanoTime();
         int fileIndex = this.purge(manager);
         if (LOGGER.isTraceEnabled()) {
            double durationMillis = (double)TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanos);
            LOGGER.trace("DirectWriteRolloverStrategy.purge() took {} milliseconds", durationMillis);
         }

         Action compressAction = null;
         String sourceName = this.getCurrentFileName(manager);
         String compressedName = sourceName;
         this.currentFileName = null;
         this.nextIndex = fileIndex + 1;
         FileExtension fileExtension = manager.getFileExtension();
         if (fileExtension != null) {
            compressedName = sourceName + fileExtension.getExtension();
            if (this.tempCompressedFilePattern != null) {
               StringBuilder buf = new StringBuilder();
               this.tempCompressedFilePattern.formatFileName(this.strSubstitutor, buf, fileIndex);
               String tmpCompressedName = buf.toString();
               File tmpCompressedNameFile = new File(tmpCompressedName);
               File parentFile = tmpCompressedNameFile.getParentFile();
               if (parentFile != null) {
                  parentFile.mkdirs();
               }

               compressAction = new CompositeAction(Arrays.asList(fileExtension.createCompressAction(sourceName, tmpCompressedName, true, this.compressionLevel), new FileRenameAction(tmpCompressedNameFile, new File(compressedName), true)), true);
            } else {
               compressAction = fileExtension.createCompressAction(sourceName, compressedName, true, this.compressionLevel);
            }
         }

         if (compressAction != null && manager.isAttributeViewEnabled()) {
            Action posixAttributeViewAction = PosixViewAttributeAction.newBuilder().withBasePath(compressedName).withFollowLinks(false).withMaxDepth(1).withPathConditions(PathCondition.EMPTY_ARRAY).withSubst(this.getStrSubstitutor()).withFilePermissions(manager.getFilePermissions()).withFileOwner(manager.getFileOwner()).withFileGroup(manager.getFileGroup()).build();
            compressAction = new CompositeAction(Arrays.asList(compressAction, posixAttributeViewAction), false);
         }

         Action asyncAction = this.merge(compressAction, this.customActions, this.stopCustomActionsOnError);
         return new RolloverDescriptionImpl(sourceName, false, (Action)null, asyncAction);
      }
   }

   public String toString() {
      return "DirectWriteRolloverStrategy(maxFiles=" + this.maxFiles + ')';
   }

   public static class Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute("maxFiles")
      private String maxFiles;
      @PluginBuilderAttribute("compressionLevel")
      private String compressionLevelStr;
      @PluginElement("Actions")
      private Action[] customActions;
      @PluginBuilderAttribute("stopCustomActionsOnError")
      private boolean stopCustomActionsOnError = true;
      @PluginBuilderAttribute("tempCompressedFilePattern")
      private String tempCompressedFilePattern;
      @PluginConfiguration
      private Configuration config;

      public DirectWriteRolloverStrategy build() {
         int maxIndex = Integer.MAX_VALUE;
         if (this.maxFiles != null) {
            maxIndex = Integers.parseInt(this.maxFiles);
            if (maxIndex < 0) {
               maxIndex = Integer.MAX_VALUE;
            } else if (maxIndex < 2) {
               AbstractRolloverStrategy.LOGGER.error("Maximum files too small. Limited to 7");
               maxIndex = 7;
            }
         }

         int compressionLevel = Integers.parseInt(this.compressionLevelStr, -1);
         return new DirectWriteRolloverStrategy(maxIndex, compressionLevel, this.config.getStrSubstitutor(), this.customActions, this.stopCustomActionsOnError, this.tempCompressedFilePattern);
      }

      public String getMaxFiles() {
         return this.maxFiles;
      }

      public Builder withMaxFiles(final String maxFiles) {
         this.maxFiles = maxFiles;
         return this;
      }

      public String getCompressionLevelStr() {
         return this.compressionLevelStr;
      }

      public Builder withCompressionLevelStr(final String compressionLevelStr) {
         this.compressionLevelStr = compressionLevelStr;
         return this;
      }

      public Action[] getCustomActions() {
         return this.customActions;
      }

      public Builder withCustomActions(final Action[] customActions) {
         this.customActions = customActions;
         return this;
      }

      public boolean isStopCustomActionsOnError() {
         return this.stopCustomActionsOnError;
      }

      public Builder withStopCustomActionsOnError(final boolean stopCustomActionsOnError) {
         this.stopCustomActionsOnError = stopCustomActionsOnError;
         return this;
      }

      public String getTempCompressedFilePattern() {
         return this.tempCompressedFilePattern;
      }

      public Builder withTempCompressedFilePattern(final String tempCompressedFilePattern) {
         this.tempCompressedFilePattern = tempCompressedFilePattern;
         return this;
      }

      public Configuration getConfig() {
         return this.config;
      }

      public Builder withConfig(final Configuration config) {
         this.config = config;
         return this;
      }
   }
}
