package org.apache.hadoop.hive.shims;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.DefaultFileAccess;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.hive.io.HiveIOExceptionHandlerUtil;
import org.apache.hadoop.mapred.ClusterStatus;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.lib.CombineFileInputFormat;
import org.apache.hadoop.mapred.lib.CombineFileSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.util.Progressable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HadoopShimsSecure implements HadoopShims {
   static final Logger LOG = LoggerFactory.getLogger(HadoopShimsSecure.class);

   public abstract HadoopShims.JobTrackerState getJobTrackerState(ClusterStatus var1) throws Exception;

   public abstract TaskAttemptContext newTaskAttemptContext(Configuration var1, Progressable var2);

   public abstract JobContext newJobContext(Job var1);

   public abstract boolean isLocalMode(Configuration var1);

   public abstract void setJobLauncherRpcAddress(Configuration var1, String var2);

   public abstract String getJobLauncherHttpAddress(Configuration var1);

   public abstract String getJobLauncherRpcAddress(Configuration var1);

   public abstract short getDefaultReplication(FileSystem var1, Path var2);

   public abstract long getDefaultBlockSize(FileSystem var1, Path var2);

   public abstract FileSystem createProxyFileSystem(FileSystem var1, URI var2);

   public abstract FileSystem getNonCachedFileSystem(URI var1, Configuration var2) throws IOException;

   private static String[] dedup(String[] locations) throws IOException {
      Set<String> dedup = new HashSet();
      Collections.addAll(dedup, locations);
      return (String[])dedup.toArray(new String[dedup.size()]);
   }

   public void checkFileAccess(FileSystem fs, FileStatus stat, FsAction action) throws IOException, AccessControlException, Exception {
      DefaultFileAccess.checkFileAccess(fs, stat, action);
   }

   public abstract void addDelegationTokens(FileSystem var1, Credentials var2, String var3) throws IOException;

   public static class InputSplitShim extends CombineFileSplit {
      long shrinkedLength;
      boolean _isShrinked = false;

      public InputSplitShim() {
      }

      public InputSplitShim(JobConf conf, Path[] paths, long[] startOffsets, long[] lengths, String[] locations) throws IOException {
         super(conf, paths, startOffsets, lengths, HadoopShimsSecure.dedup(locations));
      }

      public void shrinkSplit(long length) {
         this._isShrinked = true;
         this.shrinkedLength = length;
      }

      public boolean isShrinked() {
         return this._isShrinked;
      }

      public long getShrinkedLength() {
         return this.shrinkedLength;
      }

      public void readFields(DataInput in) throws IOException {
         super.readFields(in);
         this._isShrinked = in.readBoolean();
         if (this._isShrinked) {
            this.shrinkedLength = in.readLong();
         }

      }

      public void write(DataOutput out) throws IOException {
         super.write(out);
         out.writeBoolean(this._isShrinked);
         if (this._isShrinked) {
            out.writeLong(this.shrinkedLength);
         }

      }
   }

   public static class CombineFileRecordReader implements RecordReader {
      static final Class[] constructorSignature = new Class[]{InputSplit.class, Configuration.class, Reporter.class, Integer.class, RecordReader.class};
      protected CombineFileSplit split;
      protected JobConf jc;
      protected Reporter reporter;
      protected Class rrClass;
      protected Constructor rrConstructor;
      protected FileSystem fs;
      protected int idx;
      protected long progress;
      protected RecordReader curReader;
      protected boolean isShrinked;
      protected long shrinkedLength;

      public boolean next(Object key, Object value) throws IOException {
         while(this.curReader == null || !this.doNextWithExceptionHandler(((CombineHiveKey)key).getKey(), value)) {
            if (!this.initNextRecordReader(key)) {
               return false;
            }
         }

         return true;
      }

      public Object createKey() {
         K newKey = (K)this.curReader.createKey();
         return new CombineHiveKey(newKey);
      }

      public Object createValue() {
         return this.curReader.createValue();
      }

      public long getPos() throws IOException {
         return this.progress;
      }

      public void close() throws IOException {
         if (this.curReader != null) {
            this.curReader.close();
            this.curReader = null;
         }

      }

      public float getProgress() throws IOException {
         return Math.min(1.0F, (float)this.progress / (float)this.split.getLength());
      }

      public CombineFileRecordReader(JobConf job, CombineFileSplit split, Reporter reporter, Class rrClass) throws IOException {
         this.split = split;
         this.jc = job;
         this.rrClass = rrClass;
         this.reporter = reporter;
         this.idx = 0;
         this.curReader = null;
         this.progress = 0L;
         this.isShrinked = false;

         assert split instanceof InputSplitShim;

         if (((InputSplitShim)split).isShrinked()) {
            this.isShrinked = true;
            this.shrinkedLength = ((InputSplitShim)split).getShrinkedLength();
         }

         try {
            this.rrConstructor = rrClass.getDeclaredConstructor(constructorSignature);
            this.rrConstructor.setAccessible(true);
         } catch (Exception e) {
            throw new RuntimeException(rrClass.getName() + " does not have valid constructor", e);
         }

         this.initNextRecordReader((Object)null);
      }

      private boolean doNextWithExceptionHandler(Object key, Object value) throws IOException {
         try {
            return this.curReader.next(key, value);
         } catch (Exception e) {
            return HiveIOExceptionHandlerUtil.handleRecordReaderNextException(e, this.jc);
         }
      }

      protected boolean initNextRecordReader(Object key) throws IOException {
         RecordReader preReader = this.curReader;
         if (this.curReader != null) {
            this.curReader.close();
            this.curReader = null;
            if (this.idx > 0) {
               this.progress += this.split.getLength(this.idx - 1);
            }
         }

         if (this.idx != this.split.getNumPaths() && (!this.isShrinked || this.progress <= this.shrinkedLength)) {
            try {
               this.curReader = (RecordReader)this.rrConstructor.newInstance(this.split, this.jc, this.reporter, this.idx, preReader);
               if (key != null) {
                  K newKey = (K)this.curReader.createKey();
                  ((CombineHiveKey)key).setKey(newKey);
               }

               this.jc.set("map.input.file", this.split.getPath(this.idx).toString());
               this.jc.setLong("map.input.start", this.split.getOffset(this.idx));
               this.jc.setLong("map.input.length", this.split.getLength(this.idx));
            } catch (Exception e) {
               this.curReader = HiveIOExceptionHandlerUtil.handleRecordReaderCreationException(e, this.jc);
            }

            ++this.idx;
            return true;
         } else {
            return false;
         }
      }
   }

   public abstract static class CombineFileInputFormatShim extends CombineFileInputFormat implements HadoopShims.CombineFileInputFormatShim {
      public Path[] getInputPathsShim(JobConf conf) {
         try {
            return FileInputFormat.getInputPaths(conf);
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      public void createPool(JobConf conf, PathFilter... filters) {
         super.createPool(conf, filters);
      }

      public CombineFileSplit[] getSplits(JobConf job, int numSplits) throws IOException {
         long minSize = job.getLong("mapreduce.input.fileinputformat.split.minsize", 0L);
         if (job.getLong("mapreduce.input.fileinputformat.split.minsize.per.node", 0L) == 0L) {
            super.setMinSplitSizeNode(minSize);
         }

         if (job.getLong("mapreduce.input.fileinputformat.split.minsize.per.rack", 0L) == 0L) {
            super.setMinSplitSizeRack(minSize);
         }

         if (job.getLong("mapreduce.input.fileinputformat.split.maxsize", 0L) == 0L) {
            super.setMaxSplitSize(minSize);
         }

         InputSplit[] splits = super.getSplits(job, numSplits);
         ArrayList<InputSplitShim> inputSplitShims = new ArrayList();

         for(int pos = 0; pos < splits.length; ++pos) {
            CombineFileSplit split = (CombineFileSplit)splits[pos];
            if (split.getPaths().length > 0) {
               inputSplitShims.add(new InputSplitShim(job, split.getPaths(), split.getStartOffsets(), split.getLengths(), split.getLocations()));
            }
         }

         return (CombineFileSplit[])inputSplitShims.toArray(new InputSplitShim[inputSplitShims.size()]);
      }

      public InputSplitShim getInputSplitShim() throws IOException {
         return new InputSplitShim();
      }

      public RecordReader getRecordReader(JobConf job, CombineFileSplit split, Reporter reporter, Class rrClass) throws IOException {
         return new CombineFileRecordReader(job, split, reporter, rrClass);
      }
   }
}
