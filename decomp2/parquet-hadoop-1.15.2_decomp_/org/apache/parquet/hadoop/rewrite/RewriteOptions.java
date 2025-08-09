package org.apache.parquet.hadoop.rewrite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.Preconditions;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.crypto.FileEncryptionProperties;
import org.apache.parquet.hadoop.IndexCache;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.hadoop.util.HadoopOutputFile;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.OutputFile;

public class RewriteOptions {
   private final ParquetConfiguration conf;
   private final List inputFiles;
   private final List inputFilesToJoin;
   private final OutputFile outputFile;
   private final List pruneColumns;
   private final CompressionCodecName newCodecName;
   private final Map maskColumns;
   private final Map renameColumns;
   private final List encryptColumns;
   private final FileEncryptionProperties fileEncryptionProperties;
   private final IndexCache.CacheStrategy indexCacheStrategy;
   private final boolean overwriteInputWithJoinColumns;
   private final boolean ignoreJoinFilesMetadata;

   private RewriteOptions(ParquetConfiguration conf, List inputFiles, List inputFilesToJoin, OutputFile outputFile, List pruneColumns, CompressionCodecName newCodecName, Map maskColumns, Map renameColumns, List encryptColumns, FileEncryptionProperties fileEncryptionProperties, IndexCache.CacheStrategy indexCacheStrategy, boolean overwriteInputWithJoinColumns, boolean ignoreJoinFilesMetadata) {
      this.conf = conf;
      this.inputFiles = inputFiles;
      this.inputFilesToJoin = inputFilesToJoin;
      this.outputFile = outputFile;
      this.pruneColumns = pruneColumns;
      this.newCodecName = newCodecName;
      this.maskColumns = maskColumns;
      this.renameColumns = renameColumns;
      this.encryptColumns = encryptColumns;
      this.fileEncryptionProperties = fileEncryptionProperties;
      this.indexCacheStrategy = indexCacheStrategy;
      this.overwriteInputWithJoinColumns = overwriteInputWithJoinColumns;
      this.ignoreJoinFilesMetadata = ignoreJoinFilesMetadata;
   }

   public Configuration getConf() {
      return ConfigurationUtil.createHadoopConfiguration(this.conf);
   }

   public ParquetConfiguration getParquetConfiguration() {
      return this.conf;
   }

   public List getInputFiles() {
      return (List)this.inputFiles.stream().map((f) -> {
         if (f instanceof HadoopOutputFile) {
            HadoopOutputFile hadoopOutputFile = (HadoopOutputFile)f;
            return new Path(hadoopOutputFile.getPath());
         } else {
            throw new RuntimeException("The input files do not all have an associated Hadoop Path.");
         }
      }).collect(Collectors.toList());
   }

   public List getInputFilesToJoin() {
      return (List)this.inputFilesToJoin.stream().map((f) -> {
         if (f instanceof HadoopOutputFile) {
            HadoopOutputFile hadoopOutputFile = (HadoopOutputFile)f;
            return new Path(hadoopOutputFile.getPath());
         } else {
            throw new RuntimeException("The input files to join do not all have an associated Hadoop Path.");
         }
      }).collect(Collectors.toList());
   }

   public List getParquetInputFiles() {
      return this.inputFiles;
   }

   public List getParquetInputFilesToJoin() {
      return this.inputFilesToJoin;
   }

   public Path getOutputFile() {
      if (this.outputFile instanceof HadoopOutputFile) {
         HadoopOutputFile hadoopOutputFile = (HadoopOutputFile)this.outputFile;
         return new Path(hadoopOutputFile.getPath());
      } else {
         throw new RuntimeException("The output file does not have an associated Hadoop Path.");
      }
   }

   public OutputFile getParquetOutputFile() {
      return this.outputFile;
   }

   public List getPruneColumns() {
      return this.pruneColumns;
   }

   public CompressionCodecName getNewCodecName() {
      return this.newCodecName;
   }

   public Map getMaskColumns() {
      return this.maskColumns;
   }

   public Map getRenameColumns() {
      return this.renameColumns;
   }

   public List getEncryptColumns() {
      return this.encryptColumns;
   }

   public FileEncryptionProperties getFileEncryptionProperties() {
      return this.fileEncryptionProperties;
   }

   public IndexCache.CacheStrategy getIndexCacheStrategy() {
      return this.indexCacheStrategy;
   }

   public boolean getOverwriteInputWithJoinColumns() {
      return this.overwriteInputWithJoinColumns;
   }

   public boolean getIgnoreJoinFilesMetadata() {
      return this.ignoreJoinFilesMetadata;
   }

   public static class Builder {
      private final ParquetConfiguration conf;
      private final List inputFiles;
      private final List inputFilesToJoin;
      private final OutputFile outputFile;
      private List pruneColumns;
      private CompressionCodecName newCodecName;
      private Map maskColumns;
      private Map renameColumns;
      private List encryptColumns;
      private FileEncryptionProperties fileEncryptionProperties;
      private IndexCache.CacheStrategy indexCacheStrategy;
      private boolean overwriteInputWithJoinColumns;
      private boolean ignoreJoinFilesMetadata;

      public Builder(Configuration conf, Path inputFile, Path inputFileToJoin, Path outputFile) {
         this((ParquetConfiguration)(new HadoopParquetConfiguration(conf)), (InputFile)HadoopInputFile.fromPathUnchecked(inputFile, conf), (InputFile)HadoopInputFile.fromPathUnchecked(inputFileToJoin, conf), (OutputFile)HadoopOutputFile.fromPathUnchecked(outputFile, conf));
      }

      public Builder(Configuration conf, Path inputFile, Path outputFile) {
         this((ParquetConfiguration)(new HadoopParquetConfiguration(conf)), (InputFile)HadoopInputFile.fromPathUnchecked(inputFile, conf), (OutputFile)HadoopOutputFile.fromPathUnchecked(outputFile, conf));
      }

      public Builder(ParquetConfiguration conf, InputFile inputFile, OutputFile outputFile) {
         this((ParquetConfiguration)conf, (List)Collections.singletonList(inputFile), (List)null, (OutputFile)outputFile);
      }

      public Builder(ParquetConfiguration conf, InputFile inputFile, InputFile inputFileToJoin, OutputFile outputFile) {
         this(conf, Collections.singletonList(inputFile), Collections.singletonList(inputFileToJoin), outputFile);
      }

      public Builder(Configuration conf, List inputFiles, Path outputFile) {
         this.indexCacheStrategy = IndexCache.CacheStrategy.NONE;
         this.overwriteInputWithJoinColumns = false;
         this.ignoreJoinFilesMetadata = false;
         this.conf = new HadoopParquetConfiguration(conf);
         this.inputFiles = new ArrayList(inputFiles.size());

         for(Path inputFile : inputFiles) {
            this.inputFiles.add(HadoopInputFile.fromPathUnchecked(inputFile, conf));
         }

         this.inputFilesToJoin = new ArrayList();
         this.outputFile = HadoopOutputFile.fromPathUnchecked(outputFile, conf);
      }

      public Builder(ParquetConfiguration conf, List inputFiles, OutputFile outputFile) {
         this.indexCacheStrategy = IndexCache.CacheStrategy.NONE;
         this.overwriteInputWithJoinColumns = false;
         this.ignoreJoinFilesMetadata = false;
         this.conf = conf;
         this.inputFiles = inputFiles;
         this.inputFilesToJoin = new ArrayList();
         this.outputFile = outputFile;
      }

      public Builder(Configuration conf, List inputFiles, List inputFilesToJoin, Path outputFile) {
         this.indexCacheStrategy = IndexCache.CacheStrategy.NONE;
         this.overwriteInputWithJoinColumns = false;
         this.ignoreJoinFilesMetadata = false;
         this.conf = new HadoopParquetConfiguration(conf);
         this.inputFiles = new ArrayList(inputFiles.size());

         for(Path inputFile : inputFiles) {
            this.inputFiles.add(HadoopInputFile.fromPathUnchecked(inputFile, conf));
         }

         this.inputFilesToJoin = new ArrayList(inputFilesToJoin.size());

         for(Path inputFile : inputFilesToJoin) {
            this.inputFilesToJoin.add(HadoopInputFile.fromPathUnchecked(inputFile, conf));
         }

         this.outputFile = HadoopOutputFile.fromPathUnchecked(outputFile, conf);
      }

      public Builder(ParquetConfiguration conf, List inputFiles, List inputFilesToJoin, OutputFile outputFile) {
         this.indexCacheStrategy = IndexCache.CacheStrategy.NONE;
         this.overwriteInputWithJoinColumns = false;
         this.ignoreJoinFilesMetadata = false;
         this.conf = conf;
         this.inputFiles = inputFiles;
         this.inputFilesToJoin = inputFilesToJoin;
         this.outputFile = outputFile;
      }

      public Builder prune(List columns) {
         this.pruneColumns = columns;
         return this;
      }

      public Builder transform(CompressionCodecName newCodecName) {
         this.newCodecName = newCodecName;
         return this;
      }

      public Builder mask(Map maskColumns) {
         this.maskColumns = maskColumns;
         return this;
      }

      public Builder renameColumns(Map renameColumns) {
         this.renameColumns = renameColumns;
         return this;
      }

      public Builder encrypt(List encryptColumns) {
         this.encryptColumns = encryptColumns;
         return this;
      }

      public Builder encryptionProperties(FileEncryptionProperties fileEncryptionProperties) {
         this.fileEncryptionProperties = fileEncryptionProperties;
         return this;
      }

      public Builder addInputFile(Path path) {
         this.inputFiles.add(HadoopInputFile.fromPathUnchecked(path, ConfigurationUtil.createHadoopConfiguration(this.conf)));
         return this;
      }

      public Builder addInputFileToJoinColumns(Path path) {
         this.inputFilesToJoin.add(HadoopInputFile.fromPathUnchecked(path, ConfigurationUtil.createHadoopConfiguration(this.conf)));
         return this;
      }

      public Builder addInputFile(InputFile inputFile) {
         this.inputFiles.add(inputFile);
         return this;
      }

      public Builder addInputFilesToJoin(InputFile fileToJoin) {
         this.inputFilesToJoin.add(fileToJoin);
         return this;
      }

      public Builder indexCacheStrategy(IndexCache.CacheStrategy cacheStrategy) {
         this.indexCacheStrategy = cacheStrategy;
         return this;
      }

      public Builder overwriteInputWithJoinColumns(boolean overwriteInputWithJoinColumns) {
         this.overwriteInputWithJoinColumns = overwriteInputWithJoinColumns;
         return this;
      }

      public Builder ignoreJoinFilesMetadata(boolean ignoreJoinFilesMetadata) {
         this.ignoreJoinFilesMetadata = ignoreJoinFilesMetadata;
         return this;
      }

      public RewriteOptions build() {
         this.checkPreconditions();
         return new RewriteOptions(this.conf, this.inputFiles, (List)(this.inputFilesToJoin != null ? this.inputFilesToJoin : new ArrayList()), this.outputFile, this.pruneColumns, this.newCodecName, this.maskColumns, (Map)(this.renameColumns == null ? new HashMap() : (Map)this.renameColumns.entrySet().stream().collect(Collectors.toMap((x) -> ((String)x.getKey()).trim(), (x) -> ((String)x.getValue()).trim()))), this.encryptColumns, this.fileEncryptionProperties, this.indexCacheStrategy, this.overwriteInputWithJoinColumns, this.ignoreJoinFilesMetadata);
      }

      private void checkPreconditions() {
         Preconditions.checkArgument(this.inputFiles != null && !this.inputFiles.isEmpty(), "Input file is required");
         Preconditions.checkArgument(this.outputFile != null, "Output file is required");
         if (this.pruneColumns != null) {
            if (this.maskColumns != null) {
               for(String pruneColumn : this.pruneColumns) {
                  Preconditions.checkArgument(!this.maskColumns.containsKey(pruneColumn), "Cannot prune and mask same column");
               }
            }

            if (this.encryptColumns != null) {
               for(String pruneColumn : this.pruneColumns) {
                  Preconditions.checkArgument(!this.encryptColumns.contains(pruneColumn), "Cannot prune and encrypt same column");
               }
            }
         }

         if (this.renameColumns != null) {
            Set<String> nullifiedColumns = (Set<String>)(this.maskColumns == null ? new HashSet() : (Set)this.maskColumns.entrySet().stream().filter((x) -> x.getValue() == MaskMode.NULLIFY).map(Map.Entry::getKey).collect(Collectors.toSet()));
            this.renameColumns.forEach((colSrc, colDst) -> {
               Preconditions.checkArgument(colSrc != null && !colSrc.trim().isEmpty(), "Renamed column source name can't be empty");
               Preconditions.checkArgument(colDst != null && !colDst.trim().isEmpty(), "Renamed column target name can't be empty");
               Preconditions.checkArgument(!nullifiedColumns.contains(colSrc), "Cannot nullify and rename the same column");
               Preconditions.checkArgument(!colSrc.contains(".") && !colDst.contains("."), "Renamed column can't be nested, in case of GroupType column only a top level column can be renamed");
            });
         }

         if (this.encryptColumns != null && !this.encryptColumns.isEmpty()) {
            Preconditions.checkArgument(this.fileEncryptionProperties != null, "FileEncryptionProperties is required when encrypting columns");
         }

         if (this.fileEncryptionProperties != null) {
            Preconditions.checkArgument(this.encryptColumns != null && !this.encryptColumns.isEmpty(), "Encrypt columns is required when FileEncryptionProperties is set");
         }

      }
   }
}
