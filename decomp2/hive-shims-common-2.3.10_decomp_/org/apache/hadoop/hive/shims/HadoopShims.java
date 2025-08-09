package org.apache.hadoop.hive.shims;

import com.google.common.annotations.VisibleForTesting;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.AccessControlException;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.mapred.ClusterStatus;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobProfile;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.lib.CombineFileSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.JobID;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.TaskID;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.Progressable;

public interface HadoopShims {
   String getTaskAttemptLogUrl(JobConf var1, String var2, String var3) throws MalformedURLException;

   MiniMrShim getMiniMrCluster(Configuration var1, int var2, String var3, int var4) throws IOException;

   MiniMrShim getMiniTezCluster(Configuration var1, int var2, String var3, boolean var4) throws IOException;

   MiniMrShim getLocalMiniTezCluster(Configuration var1, boolean var2);

   MiniMrShim getMiniSparkCluster(Configuration var1, int var2, String var3, int var4) throws IOException;

   MiniDFSShim getMiniDfs(Configuration var1, int var2, boolean var3, String[] var4) throws IOException;

   MiniDFSShim getMiniDfs(Configuration var1, int var2, boolean var3, String[] var4, boolean var5) throws IOException;

   CombineFileInputFormatShim getCombineFileInputFormat();

   JobTrackerState getJobTrackerState(ClusterStatus var1) throws Exception;

   TaskAttemptContext newTaskAttemptContext(Configuration var1, Progressable var2);

   TaskAttemptID newTaskAttemptID(JobID var1, boolean var2, int var3, int var4);

   JobContext newJobContext(Job var1);

   boolean isLocalMode(Configuration var1);

   String getJobLauncherRpcAddress(Configuration var1);

   void setJobLauncherRpcAddress(Configuration var1, String var2);

   String getJobLauncherHttpAddress(Configuration var1);

   long getDefaultBlockSize(FileSystem var1, Path var2);

   short getDefaultReplication(FileSystem var1, Path var2);

   void refreshDefaultQueue(Configuration var1, String var2) throws IOException;

   void setTotalOrderPartitionFile(JobConf var1, Path var2);

   Comparator getLongComparator();

   List listLocatedHdfsStatus(FileSystem var1, Path var2, PathFilter var3) throws IOException;

   BlockLocation[] getLocations(FileSystem var1, FileStatus var2) throws IOException;

   TreeMap getLocationsWithOffset(FileSystem var1, FileStatus var2) throws IOException;

   void hflush(FSDataOutputStream var1) throws IOException;

   HCatHadoopShims getHCatShim();

   WebHCatJTShim getWebHCatShim(Configuration var1, UserGroupInformation var2) throws IOException;

   FileSystem createProxyFileSystem(FileSystem var1, URI var2);

   StoragePolicyShim getStoragePolicyShim(FileSystem var1);

   Configuration getConfiguration(JobContext var1);

   JobConf getJobConf(org.apache.hadoop.mapred.JobContext var1);

   FileSystem getNonCachedFileSystem(URI var1, Configuration var2) throws IOException;

   void getMergedCredentials(JobConf var1) throws IOException;

   void mergeCredentials(JobConf var1, JobConf var2) throws IOException;

   void checkFileAccess(FileSystem var1, FileStatus var2, FsAction var3) throws IOException, AccessControlException, Exception;

   String getPassword(Configuration var1, String var2) throws IOException;

   boolean supportStickyBit();

   boolean hasStickyBit(FsPermission var1);

   boolean supportTrashFeature();

   Path getCurrentTrashPath(Configuration var1, FileSystem var2);

   boolean isDirectory(FileStatus var1);

   KerberosNameShim getKerberosNameShim(String var1) throws IOException;

   boolean runDistCp(Path var1, Path var2, Configuration var3) throws IOException;

   HdfsEncryptionShim createHdfsEncryptionShim(FileSystem var1, Configuration var2) throws IOException;

   Path getPathWithoutSchemeAndAuthority(Path var1);

   int readByteBuffer(FSDataInputStream var1, ByteBuffer var2) throws IOException;

   void addDelegationTokens(FileSystem var1, Credentials var2, String var3) throws IOException;

   long getFileId(FileSystem var1, String var2) throws IOException;

   UserGroupInformation cloneUgi(UserGroupInformation var1) throws IOException;

   public static enum JobTrackerState {
      INITIALIZING,
      RUNNING;
   }

   public static enum StoragePolicyValue {
      MEMORY,
      SSD,
      DEFAULT;

      public static StoragePolicyValue lookup(String name) {
         return name == null ? DEFAULT : valueOf(name.toUpperCase().trim());
      }
   }

   public static class NoopHdfsEncryptionShim implements HdfsEncryptionShim {
      public boolean isPathEncrypted(Path path) throws IOException {
         return false;
      }

      public boolean arePathsOnSameEncryptionZone(Path path1, Path path2) throws IOException {
         return true;
      }

      public boolean arePathsOnSameEncryptionZone(Path path1, Path path2, HdfsEncryptionShim encryptionShim2) throws IOException {
         return true;
      }

      public int comparePathKeyStrength(Path path1, Path path2) throws IOException {
         return 0;
      }

      public void createEncryptionZone(Path path, String keyName) {
      }

      public void createKey(String keyName, int bitLength) {
      }

      public void deleteKey(String keyName) throws IOException {
      }

      public List getKeys() throws IOException {
         return null;
      }
   }

   public interface CombineFileInputFormatShim {
      Path[] getInputPathsShim(JobConf var1);

      void createPool(JobConf var1, PathFilter... var2);

      CombineFileSplit[] getSplits(JobConf var1, int var2) throws IOException;

      CombineFileSplit getInputSplitShim() throws IOException;

      RecordReader getRecordReader(JobConf var1, CombineFileSplit var2, Reporter var3, Class var4) throws IOException;
   }

   public interface HCatHadoopShims {
      TaskID createTaskID();

      TaskAttemptID createTaskAttemptID();

      TaskAttemptContext createTaskAttemptContext(Configuration var1, TaskAttemptID var2);

      org.apache.hadoop.mapred.TaskAttemptContext createTaskAttemptContext(JobConf var1, org.apache.hadoop.mapred.TaskAttemptID var2, Progressable var3);

      JobContext createJobContext(Configuration var1, JobID var2);

      org.apache.hadoop.mapred.JobContext createJobContext(JobConf var1, JobID var2, Progressable var3);

      void commitJob(OutputFormat var1, Job var2) throws IOException;

      void abortJob(OutputFormat var1, Job var2) throws IOException;

      InetSocketAddress getResourceManagerAddress(Configuration var1);

      String getPropertyName(PropertyName var1);

      boolean isFileInHDFS(FileSystem var1, Path var2) throws IOException;

      public static enum PropertyName {
         CACHE_ARCHIVES,
         CACHE_FILES,
         CACHE_SYMLINK,
         CLASSPATH_ARCHIVES,
         CLASSPATH_FILES;
      }
   }

   public interface HdfsEncryptionShim {
      boolean isPathEncrypted(Path var1) throws IOException;

      boolean arePathsOnSameEncryptionZone(Path var1, Path var2) throws IOException;

      boolean arePathsOnSameEncryptionZone(Path var1, Path var2, HdfsEncryptionShim var3) throws IOException;

      int comparePathKeyStrength(Path var1, Path var2) throws IOException;

      @VisibleForTesting
      void createEncryptionZone(Path var1, String var2) throws IOException;

      @VisibleForTesting
      void createKey(String var1, int var2) throws IOException, NoSuchAlgorithmException;

      @VisibleForTesting
      void deleteKey(String var1) throws IOException;

      @VisibleForTesting
      List getKeys() throws IOException;
   }

   public interface HdfsFileStatusWithId {
      FileStatus getFileStatus();

      Long getFileId();
   }

   public interface KerberosNameShim {
      String getDefaultRealm();

      String getServiceName();

      String getHostName();

      String getRealm();

      String getShortName() throws IOException;
   }

   public interface MiniDFSShim {
      FileSystem getFileSystem() throws IOException;

      void shutdown() throws IOException;
   }

   public interface MiniMrShim {
      int getJobTrackerPort() throws UnsupportedOperationException;

      void shutdown() throws IOException;

      void setupConfiguration(Configuration var1);
   }

   public interface StoragePolicyShim {
      void setStoragePolicy(Path var1, StoragePolicyValue var2) throws IOException;
   }

   public interface WebHCatJTShim {
      JobProfile getJobProfile(org.apache.hadoop.mapred.JobID var1) throws IOException;

      JobStatus getJobStatus(org.apache.hadoop.mapred.JobID var1) throws IOException;

      void killJob(org.apache.hadoop.mapred.JobID var1) throws IOException;

      JobStatus[] getAllJobs() throws IOException;

      void close();

      void addCacheFile(URI var1, Job var2);

      void killJobs(String var1, long var2);

      Set getJobs(String var1, long var2);
   }
}
