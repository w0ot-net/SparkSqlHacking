package org.apache.hadoop.hive.metastore;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileChecksum;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.fs.Trash;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReplChangeManager {
   private static final Logger LOG = LoggerFactory.getLogger(ReplChangeManager.class);
   private static ReplChangeManager instance;
   private static boolean inited = false;
   private static boolean enabled = false;
   private static Path cmroot;
   private static HiveConf hiveConf;
   private String msUser;
   private String msGroup;
   private FileSystem fs;
   public static final String ORIG_LOC_TAG = "user.original-loc";
   public static final String REMAIN_IN_TRASH_TAG = "user.remain-in-trash";
   public static final String URI_FRAGMENT_SEPARATOR = "#";
   private static final PathFilter hiddenFileFilter = new PathFilter() {
      public boolean accept(Path p) {
         return !p.getName().startsWith(".");
      }
   };

   public static ReplChangeManager getInstance(HiveConf hiveConf) throws MetaException {
      if (instance == null) {
         instance = new ReplChangeManager(hiveConf);
      }

      return instance;
   }

   ReplChangeManager(HiveConf hiveConf) throws MetaException {
      try {
         if (!inited) {
            if (hiveConf.getBoolVar(ConfVars.REPLCMENABLED)) {
               enabled = true;
               cmroot = new Path(hiveConf.get(ConfVars.REPLCMDIR.varname));
               ReplChangeManager.hiveConf = hiveConf;
               this.fs = cmroot.getFileSystem(hiveConf);
               if (!this.fs.exists(cmroot)) {
                  this.fs.mkdirs(cmroot);
                  this.fs.setPermission(cmroot, new FsPermission("700"));
               }

               UserGroupInformation usergroupInfo = UserGroupInformation.getCurrentUser();
               this.msUser = usergroupInfo.getShortUserName();
               this.msGroup = usergroupInfo.getPrimaryGroupName();
            }

            inited = true;
         }

      } catch (IOException e) {
         throw new MetaException(StringUtils.stringifyException(e));
      }
   }

   public int recycle(Path path, boolean ifPurge) throws MetaException {
      if (!enabled) {
         return 0;
      } else {
         try {
            int count = 0;
            if (this.fs.isDirectory(path)) {
               FileStatus[] files = this.fs.listStatus(path, hiddenFileFilter);

               for(FileStatus file : files) {
                  count += this.recycle(file.getPath(), ifPurge);
               }
            } else {
               Path cmPath = getCMPath(path, hiveConf, getChksumString(path, this.fs));
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Moving " + path.toString() + " to " + cmPath.toString());
               }

               long now = System.currentTimeMillis();
               this.fs.setTimes(path, now, now);
               boolean succ = this.fs.rename(path, cmPath);
               if (!succ) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("A file with the same content of " + path.toString() + " already exists, ignore");
                  }

                  this.fs.setTimes(cmPath, now, now);
               } else {
                  this.fs.setOwner(cmPath, this.msUser, this.msGroup);

                  try {
                     this.fs.setXAttr(cmPath, "user.original-loc", path.toString().getBytes());
                  } catch (UnsupportedOperationException var10) {
                     LOG.warn("Error setting xattr for " + path.toString());
                  }

                  ++count;
               }

               if (!ifPurge) {
                  try {
                     this.fs.setXAttr(cmPath, "user.remain-in-trash", new byte[]{0});
                  } catch (UnsupportedOperationException var9) {
                     LOG.warn("Error setting xattr for " + cmPath.toString());
                  }
               }
            }

            return count;
         } catch (IOException e) {
            throw new MetaException(StringUtils.stringifyException(e));
         }
      }
   }

   public static String getChksumString(Path path, FileSystem fs) throws IOException {
      String checksumString = null;
      FileChecksum checksum = fs.getFileChecksum(path);
      if (checksum != null) {
         checksumString = StringUtils.byteToHexString(checksum.getBytes(), 0, checksum.getLength());
      }

      return checksumString;
   }

   public static void setCmRoot(Path cmRoot) {
      cmroot = cmRoot;
   }

   public static Path getCMPath(Path path, Configuration conf, String chksum) throws IOException, MetaException {
      String newFileName = chksum;
      int maxLength = conf.getInt("dfs.namenode.fs-limits.max-component-length", 255);
      if (chksum.length() > maxLength) {
         newFileName = chksum.substring(0, maxLength - 1);
      }

      Path cmPath = new Path(cmroot, newFileName);
      return cmPath;
   }

   public static FileStatus getFileStatus(Path src, String chksumString, HiveConf conf) throws MetaException {
      try {
         FileSystem srcFs = src.getFileSystem(conf);
         if (chksumString == null) {
            return srcFs.getFileStatus(src);
         } else if (!srcFs.exists(src)) {
            return srcFs.getFileStatus(getCMPath(src, conf, chksumString));
         } else {
            String currentChksumString = getChksumString(src, srcFs);
            return currentChksumString != null && !chksumString.equals(currentChksumString) ? srcFs.getFileStatus(getCMPath(src, conf, chksumString)) : srcFs.getFileStatus(src);
         }
      } catch (IOException e) {
         throw new MetaException(StringUtils.stringifyException(e));
      }
   }

   public static String encodeFileUri(String fileUriStr, String fileChecksum) {
      return fileChecksum != null ? fileUriStr + "#" + fileChecksum : fileUriStr;
   }

   public static String[] getFileWithChksumFromURI(String fileURIStr) {
      String[] uriAndFragment = fileURIStr.split("#");
      String[] result = new String[2];
      result[0] = uriAndFragment[0];
      if (uriAndFragment.length > 1) {
         result[1] = uriAndFragment[1];
      }

      return result;
   }

   public static void scheduleCMClearer(HiveConf hiveConf) {
      if (HiveConf.getBoolVar(hiveConf, ConfVars.REPLCMENABLED)) {
         ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor((new BasicThreadFactory.Builder()).namingPattern("cmclearer-%d").daemon(true).build());
         executor.scheduleAtFixedRate(new CMClearer(hiveConf.get(ConfVars.REPLCMDIR.varname), hiveConf.getTimeVar(ConfVars.REPLCMRETIAN, TimeUnit.SECONDS), hiveConf), 0L, hiveConf.getTimeVar(ConfVars.REPLCMINTERVAL, TimeUnit.SECONDS), TimeUnit.SECONDS);
      }

   }

   static class CMClearer implements Runnable {
      private Path cmroot;
      private long secRetain;
      private HiveConf hiveConf;

      CMClearer(String cmrootString, long secRetain, HiveConf hiveConf) {
         this.cmroot = new Path(cmrootString);
         this.secRetain = secRetain;
         this.hiveConf = hiveConf;
      }

      public void run() {
         try {
            ReplChangeManager.LOG.info("CMClearer started");
            long now = System.currentTimeMillis();
            FileSystem fs = this.cmroot.getFileSystem(this.hiveConf);
            FileStatus[] files = fs.listStatus(this.cmroot);

            for(FileStatus file : files) {
               long modifiedTime = file.getModificationTime();
               if (now - modifiedTime > this.secRetain * 1000L) {
                  try {
                     if (fs.getXAttrs(file.getPath()).containsKey("user.remain-in-trash")) {
                        boolean succ = Trash.moveToAppropriateTrash(fs, file.getPath(), this.hiveConf);
                        if (succ) {
                           if (ReplChangeManager.LOG.isDebugEnabled()) {
                              ReplChangeManager.LOG.debug("Move " + file.toString() + " to trash");
                           }
                        } else {
                           ReplChangeManager.LOG.warn("Fail to move " + file.toString() + " to trash");
                        }
                     } else {
                        boolean succ = fs.delete(file.getPath(), false);
                        if (succ) {
                           if (ReplChangeManager.LOG.isDebugEnabled()) {
                              ReplChangeManager.LOG.debug("Remove " + file.toString());
                           }
                        } else {
                           ReplChangeManager.LOG.warn("Fail to remove " + file.toString());
                        }
                     }
                  } catch (UnsupportedOperationException var12) {
                     ReplChangeManager.LOG.warn("Error getting xattr for " + file.getPath().toString());
                  }
               }
            }
         } catch (IOException e) {
            ReplChangeManager.LOG.error("Exception when clearing cmroot:" + StringUtils.stringifyException(e));
         }

      }
   }
}
