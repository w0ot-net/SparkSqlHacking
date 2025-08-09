package org.apache.hadoop.hive.shims;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.AccessControlException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.security.auth.Subject;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.crypto.CipherSuite;
import org.apache.hadoop.crypto.key.KeyProvider;
import org.apache.hadoop.crypto.key.KeyProviderCryptoExtension;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.DefaultFileAccess;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.fs.ProxyFileSystem;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.fs.TrashPolicy;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hdfs.DFSClient;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.apache.hadoop.hdfs.MiniDFSNNTopology;
import org.apache.hadoop.hdfs.client.HdfsAdmin;
import org.apache.hadoop.hdfs.protocol.DirectoryListing;
import org.apache.hadoop.hdfs.protocol.EncryptionZone;
import org.apache.hadoop.hdfs.protocol.HdfsFileStatus;
import org.apache.hadoop.hdfs.protocol.HdfsLocatedFileStatus;
import org.apache.hadoop.hive.shims.HadoopShims.JobTrackerState;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.ClusterStatus;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MiniMRCluster;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TaskAttemptContextImpl;
import org.apache.hadoop.mapred.WebHCatJTShim23;
import org.apache.hadoop.mapred.lib.TotalOrderPartitioner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.JobID;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.TaskID;
import org.apache.hadoop.mapreduce.TaskType;
import org.apache.hadoop.mapreduce.task.JobContextImpl;
import org.apache.hadoop.net.NetUtils;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.authentication.util.KerberosName;
import org.apache.hadoop.tools.DistCp;
import org.apache.hadoop.tools.DistCpOptions;
import org.apache.hadoop.tools.DistCpOptions.FileAttribute;
import org.apache.hadoop.util.Progressable;
import org.apache.tez.test.MiniTezCluster;

public class Hadoop23Shims extends HadoopShimsSecure {
   HadoopShims.MiniDFSShim cluster = null;
   final boolean storagePolicy;
   private volatile HadoopShims.HCatHadoopShims hcatShimInstance;
   protected static final Method accessMethod;
   protected static final Method getPasswordMethod;
   private static Boolean hdfsEncryptionSupport;
   private static final Method getSubjectMethod;
   private static final Constructor ugiCtor;
   private static final String ugiCloneError;

   public Hadoop23Shims() {
      boolean storage = false;

      try {
         Class.forName("org.apache.hadoop.hdfs.protocol.BlockStoragePolicy", false, ShimLoader.class.getClassLoader());
         storage = true;
      } catch (ClassNotFoundException var3) {
      }

      this.storagePolicy = storage;
   }

   public HadoopShims.CombineFileInputFormatShim getCombineFileInputFormat() {
      return new HadoopShimsSecure.CombineFileInputFormatShim() {
         public RecordReader getRecordReader(InputSplit split, JobConf job, Reporter reporter) throws IOException {
            throw new IOException("CombineFileInputFormat.getRecordReader not needed.");
         }

         protected List listStatus(JobContext job) throws IOException {
            List<FileStatus> result = super.listStatus(job);
            Iterator<FileStatus> it = result.iterator();

            while(it.hasNext()) {
               FileStatus stat = (FileStatus)it.next();
               if (!stat.isFile() || stat.getLen() == 0L && !stat.getPath().toUri().getScheme().equals("nullscan")) {
                  it.remove();
               }
            }

            return result;
         }
      };
   }

   public String getTaskAttemptLogUrl(JobConf conf, String taskTrackerHttpAddress, String taskAttemptId) throws MalformedURLException {
      if (conf.get("mapreduce.framework.name") != null && conf.get("mapreduce.framework.name").equals("yarn")) {
         LOG.warn("Can't fetch tasklog: TaskLogServlet is not supported in MR2 mode.");
         return null;
      } else {
         LOG.warn("Can't fetch tasklog: TaskLogServlet is not supported in MR1 mode.");
         return null;
      }
   }

   public HadoopShims.JobTrackerState getJobTrackerState(ClusterStatus clusterStatus) throws Exception {
      switch (clusterStatus.getJobTrackerStatus()) {
         case INITIALIZING:
            return JobTrackerState.INITIALIZING;
         case RUNNING:
            return JobTrackerState.RUNNING;
         default:
            String errorMsg = "Unrecognized JobTracker state: " + clusterStatus.getJobTrackerStatus();
            throw new Exception(errorMsg);
      }
   }

   public TaskAttemptContext newTaskAttemptContext(Configuration conf, final Progressable progressable) {
      TaskAttemptID taskAttemptId = TaskAttemptID.forName(conf.get("mapreduce.task.attempt.id"));
      if (taskAttemptId == null) {
         taskAttemptId = new TaskAttemptID();
      }

      return new org.apache.hadoop.mapreduce.task.TaskAttemptContextImpl(conf, taskAttemptId) {
         public void progress() {
            progressable.progress();
         }
      };
   }

   public TaskAttemptID newTaskAttemptID(JobID jobId, boolean isMap, int taskId, int id) {
      return new TaskAttemptID(jobId.getJtIdentifier(), jobId.getId(), isMap ? TaskType.MAP : TaskType.REDUCE, taskId, id);
   }

   public JobContext newJobContext(Job job) {
      return new JobContextImpl(job.getConfiguration(), job.getJobID());
   }

   public boolean isLocalMode(Configuration conf) {
      return "local".equals(conf.get("mapreduce.framework.name"));
   }

   public String getJobLauncherRpcAddress(Configuration conf) {
      return conf.get("yarn.resourcemanager.address");
   }

   public void setJobLauncherRpcAddress(Configuration conf, String val) {
      if (val.equals("local")) {
         conf.set("mapreduce.framework.name", val);
         conf.set("mapreduce.jobtracker.address", val);
      } else {
         conf.set("mapreduce.framework.name", "yarn");
         conf.set("yarn.resourcemanager.address", val);
      }

   }

   public String getJobLauncherHttpAddress(Configuration conf) {
      return conf.get("yarn.resourcemanager.webapp.address");
   }

   public long getDefaultBlockSize(FileSystem fs, Path path) {
      return fs.getDefaultBlockSize(path);
   }

   public short getDefaultReplication(FileSystem fs, Path path) {
      return fs.getDefaultReplication(path);
   }

   public void setTotalOrderPartitionFile(JobConf jobConf, Path partitionFile) {
      TotalOrderPartitioner.setPartitionFile(jobConf, partitionFile);
   }

   public Comparator getLongComparator() {
      return new Comparator() {
         public int compare(LongWritable o1, LongWritable o2) {
            return o1.compareTo(o2);
         }
      };
   }

   public void refreshDefaultQueue(Configuration conf, String userName) throws IOException {
      if (StringUtils.isNotBlank(userName) && this.isFairScheduler(conf)) {
         ShimLoader.getSchedulerShims().refreshDefaultQueue(conf, userName);
      }

   }

   private boolean isFairScheduler(Configuration conf) {
      return "org.apache.hadoop.yarn.server.resourcemanager.scheduler.fair.FairScheduler".equalsIgnoreCase(conf.get("yarn.resourcemanager.scheduler.class"));
   }

   public MiniMrShim getMiniMrCluster(Configuration conf, int numberOfTaskTrackers, String nameNode, int numDir) throws IOException {
      return new MiniMrShim(conf, numberOfTaskTrackers, nameNode, numDir);
   }

   public HadoopShims.MiniMrShim getLocalMiniTezCluster(Configuration conf, boolean usingLlap) {
      return new MiniTezLocalShim(conf, usingLlap);
   }

   public MiniMrShim getMiniTezCluster(Configuration conf, int numberOfTaskTrackers, String nameNode, boolean usingLlap) throws IOException {
      return new MiniTezShim(conf, numberOfTaskTrackers, nameNode, usingLlap);
   }

   private void configureImpersonation(Configuration conf) {
      String user;
      try {
         user = Utils.getUGI().getShortUserName();
      } catch (Exception e) {
         String msg = "Cannot obtain username: " + e;
         throw new IllegalStateException(msg, e);
      }

      conf.set("hadoop.proxyuser." + user + ".groups", "*");
      conf.set("hadoop.proxyuser." + user + ".hosts", "*");
   }

   public MiniMrShim getMiniSparkCluster(Configuration conf, int numberOfTaskTrackers, String nameNode, int numDir) throws IOException {
      return new MiniSparkShim(conf, numberOfTaskTrackers, nameNode, numDir);
   }

   public HadoopShims.MiniDFSShim getMiniDfs(Configuration conf, int numDataNodes, boolean format, String[] racks) throws IOException {
      return this.getMiniDfs(conf, numDataNodes, format, racks, false);
   }

   public HadoopShims.MiniDFSShim getMiniDfs(Configuration conf, int numDataNodes, boolean format, String[] racks, boolean isHA) throws IOException {
      this.configureImpersonation(conf);
      MiniDFSCluster miniDFSCluster;
      if (isHA) {
         MiniDFSNNTopology topo = (new MiniDFSNNTopology()).addNameservice((new MiniDFSNNTopology.NSConf("minidfs")).addNN(new MiniDFSNNTopology.NNConf("nn1")).addNN(new MiniDFSNNTopology.NNConf("nn2")));
         miniDFSCluster = (new MiniDFSCluster.Builder(conf)).numDataNodes(numDataNodes).format(format).racks(racks).nnTopology(topo).build();
         miniDFSCluster.waitActive();
         miniDFSCluster.transitionToActive(0);
      } else {
         miniDFSCluster = (new MiniDFSCluster.Builder(conf)).numDataNodes(numDataNodes).format(format).racks(racks).build();
      }

      KeyProviderCryptoExtension keyProvider = miniDFSCluster.getNameNode(0).getNamesystem().getProvider();
      if (keyProvider != null) {
         try {
            setKeyProvider(miniDFSCluster.getFileSystem(0).getClient(), keyProvider);
         } catch (Exception err) {
            throw new IOException(err);
         }
      }

      this.cluster = new MiniDFSShim(miniDFSCluster);
      return this.cluster;
   }

   private static void setKeyProvider(DFSClient dfsClient, KeyProviderCryptoExtension provider) throws Exception {
      Method setKeyProviderHadoop27Method = null;

      try {
         setKeyProviderHadoop27Method = DFSClient.class.getMethod("setKeyProvider", KeyProvider.class);
      } catch (NoSuchMethodException var4) {
      }

      if (setKeyProviderHadoop27Method != null) {
         setKeyProviderHadoop27Method.invoke(dfsClient, provider);
      } else {
         dfsClient.setKeyProvider(provider);
      }

   }

   public HadoopShims.HCatHadoopShims getHCatShim() {
      if (this.hcatShimInstance == null) {
         this.hcatShimInstance = new HCatHadoopShims23();
      }

      return this.hcatShimInstance;
   }

   public HadoopShims.WebHCatJTShim getWebHCatShim(Configuration conf, UserGroupInformation ugi) throws IOException {
      return new WebHCatJTShim23(conf, ugi);
   }

   public List listLocatedHdfsStatus(FileSystem fs, Path p, PathFilter filter) throws IOException {
      DistributedFileSystem dfs = this.ensureDfs(fs);
      DFSClient dfsc = dfs.getClient();
      String src = p.toUri().getPath();
      DirectoryListing current = dfsc.listPaths(src, HdfsFileStatus.EMPTY_NAME, true);
      if (current == null) {
         throw new FileNotFoundException("File " + p + " does not exist.");
      } else {
         URI fsUri = fs.getUri();

         List<HadoopShims.HdfsFileStatusWithId> result;
         for(result = new ArrayList(current.getPartialListing().length); current != null; current = current.hasMore() ? dfsc.listPaths(src, current.getLastName(), true) : null) {
            HdfsFileStatus[] hfss = current.getPartialListing();

            for(int i = 0; i < hfss.length; ++i) {
               HdfsLocatedFileStatus next = (HdfsLocatedFileStatus)hfss[i];
               if (filter != null) {
                  Path filterPath = next.getFullPath(p).makeQualified(fsUri, (Path)null);
                  if (!filter.accept(filterPath)) {
                     continue;
                  }
               }

               LocatedFileStatus lfs = next.makeQualifiedLocated(fsUri, p);
               result.add(new HdfsFileStatusWithIdImpl(lfs, next.getFileId()));
            }
         }

         return result;
      }
   }

   private DistributedFileSystem ensureDfs(FileSystem fs) {
      if (!(fs instanceof DistributedFileSystem)) {
         throw new UnsupportedOperationException("Only supported for DFS; got " + fs.getClass());
      } else {
         DistributedFileSystem dfs = (DistributedFileSystem)fs;
         return dfs;
      }
   }

   public BlockLocation[] getLocations(FileSystem fs, FileStatus status) throws IOException {
      return status instanceof LocatedFileStatus ? ((LocatedFileStatus)status).getBlockLocations() : fs.getFileBlockLocations(status, 0L, status.getLen());
   }

   public TreeMap getLocationsWithOffset(FileSystem fs, FileStatus status) throws IOException {
      TreeMap<Long, BlockLocation> offsetBlockMap = new TreeMap();
      BlockLocation[] locations = this.getLocations(fs, status);

      for(BlockLocation location : locations) {
         offsetBlockMap.put(location.getOffset(), location);
      }

      return offsetBlockMap;
   }

   public void hflush(FSDataOutputStream stream) throws IOException {
      stream.hflush();
   }

   public FileSystem createProxyFileSystem(FileSystem fs, URI uri) {
      return new ProxyFileSystem23(fs, uri);
   }

   public Configuration getConfiguration(JobContext context) {
      return context.getConfiguration();
   }

   public JobConf getJobConf(org.apache.hadoop.mapred.JobContext context) {
      return context.getJobConf();
   }

   public FileSystem getNonCachedFileSystem(URI uri, Configuration conf) throws IOException {
      return FileSystem.newInstance(uri, conf);
   }

   public void getMergedCredentials(JobConf jobConf) throws IOException {
      jobConf.getCredentials().mergeAll(UserGroupInformation.getCurrentUser().getCredentials());
   }

   public void mergeCredentials(JobConf dest, JobConf src) throws IOException {
      dest.getCredentials().mergeAll(src.getCredentials());
   }

   public void checkFileAccess(FileSystem fs, FileStatus stat, FsAction action) throws IOException, AccessControlException, Exception {
      try {
         if (accessMethod == null) {
            DefaultFileAccess.checkFileAccess(fs, stat, action);
         } else {
            accessMethod.invoke(fs, stat.getPath(), action);
         }

      } catch (Exception err) {
         throw wrapAccessException(err);
      }
   }

   private static Exception wrapAccessException(Exception err) {
      int maxDepth = 20;
      Throwable curErr = err;
      int idx = 0;

      while(true) {
         if (curErr != null && idx < 20) {
            if (!(curErr instanceof org.apache.hadoop.security.AccessControlException) && !curErr.getClass().getName().equals("org.apache.hadoop.fs.permission.AccessControlException")) {
               curErr = curErr.getCause();
               ++idx;
               continue;
            }

            Exception newErr = new AccessControlException(curErr.getMessage());
            newErr.initCause(err);
            return newErr;
         }

         return err;
      }
   }

   public String getPassword(Configuration conf, String name) throws IOException {
      if (getPasswordMethod == null) {
         return conf.get(name);
      } else {
         try {
            char[] pw = (char[])getPasswordMethod.invoke(conf, name);
            return pw == null ? null : new String(pw);
         } catch (Exception err) {
            throw new IOException(err.getMessage(), err);
         }
      }
   }

   public boolean supportStickyBit() {
      return true;
   }

   public boolean hasStickyBit(FsPermission permission) {
      return permission.getStickyBit();
   }

   public boolean supportTrashFeature() {
      return true;
   }

   public Path getCurrentTrashPath(Configuration conf, FileSystem fs) {
      TrashPolicy tp = TrashPolicy.getInstance(conf, fs, fs.getHomeDirectory());
      return tp.getCurrentTrashDir();
   }

   public KerberosNameShim getKerberosNameShim(String name) throws IOException {
      return new KerberosNameShim(name);
   }

   public boolean isDirectory(FileStatus fileStatus) {
      return fileStatus.isDirectory();
   }

   public HadoopShims.StoragePolicyShim getStoragePolicyShim(FileSystem fs) {
      if (!this.storagePolicy) {
         return null;
      } else {
         try {
            return new StoragePolicyShim((DistributedFileSystem)fs);
         } catch (ClassCastException var3) {
            return null;
         }
      }
   }

   public boolean runDistCp(Path src, Path dst, Configuration conf) throws IOException {
      DistCpOptions options = new DistCpOptions(Collections.singletonList(src), dst);
      options.setSyncFolder(true);
      options.setSkipCRC(true);
      options.preserve(FileAttribute.BLOCKSIZE);
      String[] params = new String[]{"-update", "-skipcrccheck", src.toString(), dst.toString()};

      boolean var7;
      try {
         conf.setBoolean("mapred.mapper.new-api", true);
         DistCp distcp = new DistCp(conf, options);
         if (distcp.run(params) != 0) {
            var7 = false;
            return var7;
         }

         var7 = true;
      } catch (Exception e) {
         throw new IOException("Cannot execute DistCp process: " + e, e);
      } finally {
         conf.setBoolean("mapred.mapper.new-api", false);
      }

      return var7;
   }

   public static boolean isHdfsEncryptionSupported() {
      if (hdfsEncryptionSupport == null) {
         Method m = null;

         try {
            m = HdfsAdmin.class.getMethod("getEncryptionZoneForPath", Path.class);
         } catch (NoSuchMethodException var2) {
         }

         hdfsEncryptionSupport = m != null;
      }

      return hdfsEncryptionSupport;
   }

   public HadoopShims.HdfsEncryptionShim createHdfsEncryptionShim(FileSystem fs, Configuration conf) throws IOException {
      if (isHdfsEncryptionSupported()) {
         URI uri = fs.getUri();
         if ("hdfs".equals(uri.getScheme())) {
            return new HdfsEncryptionShim(uri, conf);
         }
      }

      return new HadoopShims.NoopHdfsEncryptionShim();
   }

   public Path getPathWithoutSchemeAndAuthority(Path path) {
      return Path.getPathWithoutSchemeAndAuthority(path);
   }

   public int readByteBuffer(FSDataInputStream file, ByteBuffer dest) throws IOException {
      int pos = dest.position();
      int result = file.read(dest);
      if (result > 0) {
         dest.position(pos + result);
      }

      return result;
   }

   public void addDelegationTokens(FileSystem fs, Credentials cred, String uname) throws IOException {
      fs.addDelegationTokens(uname, cred);
   }

   public long getFileId(FileSystem fs, String path) throws IOException {
      return this.ensureDfs(fs).getClient().getFileInfo(path).getFileId();
   }

   public UserGroupInformation cloneUgi(UserGroupInformation baseUgi) throws IOException {
      if (getSubjectMethod == null) {
         throw new IOException("The UGI method was not found: " + ugiCloneError);
      } else {
         try {
            Subject origSubject = (Subject)getSubjectMethod.invoke(baseUgi);
            Subject subject = new Subject(false, origSubject.getPrincipals(), cloneCredentials(origSubject.getPublicCredentials()), cloneCredentials(origSubject.getPrivateCredentials()));
            return (UserGroupInformation)ugiCtor.newInstance(subject);
         } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new IOException(e);
         }
      }
   }

   private static Set cloneCredentials(Set old) {
      Set<Object> set = new HashSet();

      for(Object o : old) {
         set.add(o instanceof Credentials ? new Credentials((Credentials)o) : o);
      }

      return set;
   }

   static {
      Method m = null;

      try {
         m = FileSystem.class.getMethod("access", Path.class, FsAction.class);
      } catch (NoSuchMethodException var7) {
      }

      accessMethod = m;

      try {
         m = Configuration.class.getMethod("getPassword", String.class);
      } catch (NoSuchMethodException var6) {
         m = null;
      }

      getPasswordMethod = m;
      Class<UserGroupInformation> clazz = UserGroupInformation.class;
      Method method = null;
      String error = null;

      Constructor<UserGroupInformation> ctor;
      try {
         method = clazz.getDeclaredMethod("getSubject");
         method.setAccessible(true);
         ctor = clazz.getDeclaredConstructor(Subject.class);
         ctor.setAccessible(true);
      } catch (Throwable t) {
         error = t.getMessage();
         method = null;
         ctor = null;
         LOG.error("Cannot create UGI reflection methods", t);
      }

      getSubjectMethod = method;
      ugiCtor = ctor;
      ugiCloneError = error;
   }

   public class MiniMrShim implements HadoopShims.MiniMrShim {
      private final MiniMRCluster mr;
      private final Configuration conf;

      public MiniMrShim() {
         this.mr = null;
         this.conf = null;
      }

      public MiniMrShim(Configuration conf, int numberOfTaskTrackers, String nameNode, int numDir) throws IOException {
         this.conf = conf;
         JobConf jConf = new JobConf(conf);
         jConf.set("yarn.scheduler.capacity.root.queues", "default");
         jConf.set("yarn.scheduler.capacity.root.default.capacity", "100");
         jConf.setInt("mapreduce.map.memory.mb", 128);
         jConf.setInt("mapreduce.reduce.memory.mb", 128);
         jConf.setInt("yarn.app.mapreduce.am.resource.mb", 128);
         jConf.setInt("yarn.minicluster.yarn.nodemanager.resource.memory-mb", 512);
         jConf.setInt("yarn.scheduler.minimum-allocation-mb", 128);
         jConf.setInt("yarn.scheduler.maximum-allocation-mb", 512);
         this.mr = new MiniMRCluster(numberOfTaskTrackers, nameNode, numDir, (String[])null, (String[])null, jConf);
      }

      public int getJobTrackerPort() throws UnsupportedOperationException {
         String address = this.conf.get("yarn.resourcemanager.address");
         address = StringUtils.substringAfterLast(address, ":");
         if (StringUtils.isBlank(address)) {
            throw new IllegalArgumentException("Invalid YARN resource manager port.");
         } else {
            return Integer.parseInt(address);
         }
      }

      public void shutdown() throws IOException {
         this.mr.shutdown();
      }

      public void setupConfiguration(Configuration conf) {
         for(Map.Entry pair : this.mr.createJobConf()) {
            conf.set((String)pair.getKey(), (String)pair.getValue());
         }

         conf.setInt("mapreduce.map.memory.mb", 128);
         conf.setInt("mapreduce.reduce.memory.mb", 128);
         conf.setInt("yarn.app.mapreduce.am.resource.mb", 128);
      }
   }

   public class MiniTezLocalShim extends MiniMrShim {
      private final Configuration conf;
      private final boolean isLlap;

      public MiniTezLocalShim(Configuration conf, boolean usingLlap) {
         this.conf = conf;
         this.isLlap = usingLlap;
         this.setupConfiguration(conf);
      }

      public int getJobTrackerPort() throws UnsupportedOperationException {
         throw new UnsupportedOperationException("No JobTracker port for local mode");
      }

      public void setupConfiguration(Configuration conf) {
         conf.setBoolean("tez.local.mode", true);
         conf.setBoolean("tez.runtime.optimize.local.fetch", true);
         conf.setBoolean("tez.ignore.lib.uris", true);
         if (!this.isLlap) {
            conf.setBoolean("hive.llap.io.enabled", false);
         } else {
            conf.set("hive.llap.execution.mode", "only");
         }

      }

      public void shutdown() throws IOException {
      }
   }

   public class MiniTezShim extends MiniMrShim {
      private final MiniTezCluster mr;
      private final Configuration conf;
      private final boolean isLlap;

      public MiniTezShim(Configuration conf, int numberOfTaskTrackers, String nameNode, boolean usingLlap) throws IOException {
         this.mr = new MiniTezCluster("hive", numberOfTaskTrackers);
         conf.setInt("yarn.minicluster.yarn.nodemanager.resource.memory-mb", 512);
         conf.setInt("yarn.scheduler.minimum-allocation-mb", 128);
         conf.setInt("yarn.scheduler.maximum-allocation-mb", 512);
         conf.setInt("hive.tez.container.size", 128);
         conf.setInt("tez.am.resource.memory.mb", 128);
         conf.setInt("tez.task.resource.memory.mb", 128);
         conf.setInt("tez.runtime.io.sort.mb", 24);
         conf.setInt("tez.runtime.unordered.output.buffer.size-mb", 10);
         conf.setFloat("tez.runtime.shuffle.fetch.buffer.percent", 0.4F);
         conf.set("fs.defaultFS", nameNode);
         conf.set("tez.am.log.level", "DEBUG");
         conf.set("yarn.app.mapreduce.am.staging-dir", "/apps_staging_dir");
         this.mr.init(conf);
         this.mr.start();
         this.conf = this.mr.getConfig();
         this.isLlap = usingLlap;
      }

      public int getJobTrackerPort() throws UnsupportedOperationException {
         String address = this.conf.get("yarn.resourcemanager.address");
         address = StringUtils.substringAfterLast(address, ":");
         if (StringUtils.isBlank(address)) {
            throw new IllegalArgumentException("Invalid YARN resource manager port.");
         } else {
            return Integer.parseInt(address);
         }
      }

      public void shutdown() throws IOException {
         this.mr.stop();
      }

      public void setupConfiguration(Configuration conf) {
         for(Map.Entry pair : this.mr.getConfig()) {
            conf.set((String)pair.getKey(), (String)pair.getValue());
         }

         conf.setInt("hive.tez.container.size", 128);
         conf.setInt("tez.am.resource.memory.mb", 128);
         conf.setInt("tez.task.resource.memory.mb", 128);
         conf.setInt("tez.runtime.io.sort.mb", 24);
         conf.setInt("tez.runtime.unordered.output.buffer.size-mb", 10);
         conf.setFloat("tez.runtime.shuffle.fetch.buffer.percent", 0.4F);
         if (this.isLlap) {
            conf.set("hive.llap.execution.mode", "all");
         }

      }
   }

   public class MiniSparkShim extends MiniMrShim {
      private final MiniSparkOnYARNCluster mr = new MiniSparkOnYARNCluster("sparkOnYarn");
      private final Configuration conf;

      public MiniSparkShim(Configuration conf, int numberOfTaskTrackers, String nameNode, int numDir) throws IOException {
         conf.set("fs.defaultFS", nameNode);
         conf.set("yarn.resourcemanager.scheduler.class", "org.apache.hadoop.yarn.server.resourcemanager.scheduler.fair.FairScheduler");
         conf.setBoolean("yarn.minicluster.control-resource-monitoring", false);
         conf.setInt("yarn.minicluster.yarn.nodemanager.resource.memory-mb", 2048);
         conf.setInt("yarn.scheduler.minimum-allocation-mb", 512);
         conf.setInt("yarn.scheduler.maximum-allocation-mb", 2048);
         Hadoop23Shims.this.configureImpersonation(conf);
         this.mr.init(conf);
         this.mr.start();
         this.conf = this.mr.getConfig();
      }

      public int getJobTrackerPort() throws UnsupportedOperationException {
         String address = this.conf.get("yarn.resourcemanager.address");
         address = StringUtils.substringAfterLast(address, ":");
         if (StringUtils.isBlank(address)) {
            throw new IllegalArgumentException("Invalid YARN resource manager port.");
         } else {
            return Integer.parseInt(address);
         }
      }

      public void shutdown() throws IOException {
         this.mr.stop();
      }

      public void setupConfiguration(Configuration conf) {
         for(Map.Entry pair : this.mr.getConfig()) {
            conf.set((String)pair.getKey(), (String)pair.getValue());
         }

      }
   }

   public class MiniDFSShim implements HadoopShims.MiniDFSShim {
      private final MiniDFSCluster cluster;

      public MiniDFSShim(MiniDFSCluster cluster) {
         this.cluster = cluster;
      }

      public FileSystem getFileSystem() throws IOException {
         return this.cluster.getFileSystem(0);
      }

      public void shutdown() {
         this.cluster.shutdown();
      }
   }

   private final class HCatHadoopShims23 implements HadoopShims.HCatHadoopShims {
      private HCatHadoopShims23() {
      }

      public TaskID createTaskID() {
         return new TaskID("", 0, TaskType.MAP, 0);
      }

      public TaskAttemptID createTaskAttemptID() {
         return new TaskAttemptID("", 0, TaskType.MAP, 0, 0);
      }

      public TaskAttemptContext createTaskAttemptContext(Configuration conf, TaskAttemptID taskId) {
         return new org.apache.hadoop.mapreduce.task.TaskAttemptContextImpl((Configuration)(conf instanceof JobConf ? new JobConf(conf) : conf), taskId);
      }

      public org.apache.hadoop.mapred.TaskAttemptContext createTaskAttemptContext(JobConf conf, org.apache.hadoop.mapred.TaskAttemptID taskId, Progressable progressable) {
         org.apache.hadoop.mapred.TaskAttemptContext newContext = null;

         try {
            Constructor<TaskAttemptContextImpl> construct = TaskAttemptContextImpl.class.getDeclaredConstructor(JobConf.class, org.apache.hadoop.mapred.TaskAttemptID.class, Reporter.class);
            construct.setAccessible(true);
            newContext = (org.apache.hadoop.mapred.TaskAttemptContext)construct.newInstance(new JobConf(conf), taskId, progressable);
            return newContext;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      public JobContext createJobContext(Configuration conf, JobID jobId) {
         return new JobContextImpl((Configuration)(conf instanceof JobConf ? new JobConf(conf) : conf), jobId);
      }

      public org.apache.hadoop.mapred.JobContext createJobContext(JobConf conf, JobID jobId, Progressable progressable) {
         return new org.apache.hadoop.mapred.JobContextImpl(new JobConf(conf), jobId, progressable);
      }

      public void commitJob(OutputFormat outputFormat, Job job) throws IOException {
      }

      public void abortJob(OutputFormat outputFormat, Job job) throws IOException {
      }

      public InetSocketAddress getResourceManagerAddress(Configuration conf) {
         String addr = conf.get("yarn.resourcemanager.address", "localhost:8032");
         return NetUtils.createSocketAddr(addr);
      }

      public String getPropertyName(HadoopShims.HCatHadoopShims.PropertyName name) {
         switch (name) {
            case CACHE_ARCHIVES:
               return "mapreduce.job.cache.archives";
            case CACHE_FILES:
               return "mapreduce.job.cache.files";
            case CACHE_SYMLINK:
               return "mapreduce.job.cache.symlink.create";
            case CLASSPATH_ARCHIVES:
               return "mapreduce.job.classpath.archives";
            case CLASSPATH_FILES:
               return "mapreduce.job.classpath.files";
            default:
               return "";
         }
      }

      public boolean isFileInHDFS(FileSystem fs, Path path) throws IOException {
         return "hdfs".equals(fs.resolvePath(path).toUri().getScheme());
      }
   }

   private static final class HdfsFileStatusWithIdImpl implements HadoopShims.HdfsFileStatusWithId {
      private final LocatedFileStatus lfs;
      private final long fileId;

      public HdfsFileStatusWithIdImpl(LocatedFileStatus lfs, long fileId) {
         this.lfs = lfs;
         this.fileId = fileId;
      }

      public FileStatus getFileStatus() {
         return this.lfs;
      }

      public Long getFileId() {
         return this.fileId;
      }
   }

   class ProxyFileSystem23 extends ProxyFileSystem {
      public ProxyFileSystem23(FileSystem fs) {
         super(fs);
      }

      public ProxyFileSystem23(FileSystem fs, URI uri) {
         super(fs, uri);
      }

      public RemoteIterator listLocatedStatus(final Path f) throws FileNotFoundException, IOException {
         return new RemoteIterator() {
            private final RemoteIterator stats = Hadoop23Shims.ProxyFileSystem23.super.listLocatedStatus(Hadoop23Shims.ProxyFileSystem23.super.swizzleParamPath(f));

            public boolean hasNext() throws IOException {
               return this.stats.hasNext();
            }

            public LocatedFileStatus next() throws IOException {
               LocatedFileStatus result = (LocatedFileStatus)this.stats.next();
               return new LocatedFileStatus(Hadoop23Shims.ProxyFileSystem23.super.swizzleFileStatus(result, false), result.getBlockLocations());
            }
         };
      }

      public void access(Path path, FsAction action) throws AccessControlException, FileNotFoundException, IOException {
         Path underlyingFsPath = this.swizzleParamPath(path);
         FileStatus underlyingFsStatus = this.fs.getFileStatus(underlyingFsPath);

         try {
            if (Hadoop23Shims.accessMethod != null) {
               Hadoop23Shims.accessMethod.invoke(this.fs, underlyingFsPath, action);
            } else {
               DefaultFileAccess.checkFileAccess(this.fs, underlyingFsStatus, action);
            }

         } catch (AccessControlException err) {
            throw err;
         } catch (FileNotFoundException err) {
            throw err;
         } catch (IOException err) {
            throw err;
         } catch (Exception err) {
            throw new RuntimeException(err.getMessage(), err);
         }
      }
   }

   public class KerberosNameShim implements HadoopShims.KerberosNameShim {
      private final KerberosName kerberosName;

      public KerberosNameShim(String name) {
         this.kerberosName = new KerberosName(name);
      }

      public String getDefaultRealm() {
         return this.kerberosName.getDefaultRealm();
      }

      public String getServiceName() {
         return this.kerberosName.getServiceName();
      }

      public String getHostName() {
         return this.kerberosName.getHostName();
      }

      public String getRealm() {
         return this.kerberosName.getRealm();
      }

      public String getShortName() throws IOException {
         return this.kerberosName.getShortName();
      }
   }

   public static class StoragePolicyShim implements HadoopShims.StoragePolicyShim {
      private final DistributedFileSystem dfs;

      public StoragePolicyShim(DistributedFileSystem fs) {
         this.dfs = fs;
      }

      public void setStoragePolicy(Path path, HadoopShims.StoragePolicyValue policy) throws IOException {
         switch (policy) {
            case MEMORY:
               this.dfs.setStoragePolicy(path, "LAZY_PERSIST");
               break;
            case SSD:
               this.dfs.setStoragePolicy(path, "ALL_SSD");
            case DEFAULT:
               break;
            default:
               throw new IllegalArgumentException("Unknown storage policy " + policy);
         }

      }
   }

   public class HdfsEncryptionShim implements HadoopShims.HdfsEncryptionShim {
      private final String HDFS_SECURITY_DEFAULT_CIPHER = "AES/CTR/NoPadding";
      private HdfsAdmin hdfsAdmin = null;
      private KeyProvider keyProvider = null;
      private final Configuration conf;

      public HdfsEncryptionShim(URI uri, Configuration conf) throws IOException {
         DistributedFileSystem dfs = (DistributedFileSystem)FileSystem.get(uri, conf);
         this.conf = conf;
         this.keyProvider = dfs.getClient().getKeyProvider();
         this.hdfsAdmin = new HdfsAdmin(uri, conf);
      }

      public boolean isPathEncrypted(Path path) throws IOException {
         Path fullPath;
         if (path.isAbsolute()) {
            fullPath = path;
         } else {
            fullPath = path.getFileSystem(this.conf).makeQualified(path);
         }

         if (!"hdfs".equalsIgnoreCase(path.toUri().getScheme())) {
            return false;
         } else {
            try {
               return this.hdfsAdmin.getEncryptionZoneForPath(fullPath) != null;
            } catch (FileNotFoundException fnfe) {
               HadoopShimsSecure.LOG.debug("Failed to get EZ for non-existent path: " + fullPath, fnfe);
               return false;
            }
         }
      }

      public boolean arePathsOnSameEncryptionZone(Path path1, Path path2) throws IOException {
         return this.equivalentEncryptionZones(this.hdfsAdmin.getEncryptionZoneForPath(path1), this.hdfsAdmin.getEncryptionZoneForPath(path2));
      }

      private boolean equivalentEncryptionZones(EncryptionZone zone1, EncryptionZone zone2) {
         if (zone1 == null && zone2 == null) {
            return true;
         } else {
            return zone1 != null && zone2 != null ? zone1.equals(zone2) : false;
         }
      }

      public boolean arePathsOnSameEncryptionZone(Path path1, Path path2, HadoopShims.HdfsEncryptionShim encryptionShim2) throws IOException {
         if (!(encryptionShim2 instanceof HdfsEncryptionShim)) {
            HadoopShimsSecure.LOG.warn("EncryptionShim for path2 (" + path2 + ") is of unexpected type: " + encryptionShim2.getClass() + ". Assuming path2 is on the same EncryptionZone as path1(" + path1 + ").");
            return true;
         } else {
            return this.equivalentEncryptionZones(this.hdfsAdmin.getEncryptionZoneForPath(path1), ((HdfsEncryptionShim)encryptionShim2).hdfsAdmin.getEncryptionZoneForPath(path2));
         }
      }

      public int comparePathKeyStrength(Path path1, Path path2) throws IOException {
         EncryptionZone zone1 = this.hdfsAdmin.getEncryptionZoneForPath(path1);
         EncryptionZone zone2 = this.hdfsAdmin.getEncryptionZoneForPath(path2);
         if (zone1 == null && zone2 == null) {
            return 0;
         } else if (zone1 == null) {
            return -1;
         } else {
            return zone2 == null ? 1 : this.compareKeyStrength(zone1, zone2);
         }
      }

      public void createEncryptionZone(Path path, String keyName) throws IOException {
         this.hdfsAdmin.createEncryptionZone(path, keyName);
      }

      public void createKey(String keyName, int bitLength) throws IOException, NoSuchAlgorithmException {
         this.checkKeyProvider();
         if (this.keyProvider.getMetadata(keyName) == null) {
            KeyProvider.Options options = new KeyProvider.Options(this.conf);
            options.setCipher("AES/CTR/NoPadding");
            options.setBitLength(bitLength);
            this.keyProvider.createKey(keyName, options);
            this.keyProvider.flush();
         } else {
            throw new IOException("key '" + keyName + "' already exists");
         }
      }

      public void deleteKey(String keyName) throws IOException {
         this.checkKeyProvider();
         if (this.keyProvider.getMetadata(keyName) != null) {
            this.keyProvider.deleteKey(keyName);
            this.keyProvider.flush();
         } else {
            throw new IOException("key '" + keyName + "' does not exist.");
         }
      }

      public List getKeys() throws IOException {
         this.checkKeyProvider();
         return this.keyProvider.getKeys();
      }

      private void checkKeyProvider() throws IOException {
         if (this.keyProvider == null) {
            throw new IOException("HDFS security key provider is not configured on your server.");
         }
      }

      private int compareKeyStrength(EncryptionZone zone1, EncryptionZone zone2) throws IOException {
         assert zone1 != null && zone2 != null : "Neither EncryptionZone under comparison can be null.";

         CipherSuite suite1 = zone1.getSuite();
         CipherSuite suite2 = zone2.getSuite();
         if (suite1 == null && suite2 == null) {
            return 0;
         } else if (suite1 == null) {
            return -1;
         } else {
            return suite2 == null ? 1 : Integer.compare(suite1.getAlgorithmBlockSize(), suite2.getAlgorithmBlockSize());
         }
      }
   }
}
