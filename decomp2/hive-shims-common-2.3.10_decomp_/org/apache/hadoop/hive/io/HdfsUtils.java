package org.apache.hadoop.hive.io;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsShell;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.AclEntry;
import org.apache.hadoop.fs.permission.AclEntryScope;
import org.apache.hadoop.fs.permission.AclEntryType;
import org.apache.hadoop.fs.permission.AclStatus;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HdfsUtils {
   private static final Logger LOG = LoggerFactory.getLogger("shims.HdfsUtils");
   private static final String HDFS_ID_PATH_PREFIX = "/.reserved/.inodes/";

   public static Path getFileIdPath(FileSystem fileSystem, Path path, long fileId) {
      return fileSystem instanceof DistributedFileSystem ? new Path("/.reserved/.inodes/" + fileId) : path;
   }

   public static void setFullFileStatus(Configuration conf, HadoopFileStatus sourceStatus, FileSystem fs, Path target, boolean recursion) {
      setFullFileStatus(conf, sourceStatus, (String)null, fs, target, recursion);
   }

   public static void setFullFileStatus(Configuration conf, HadoopFileStatus sourceStatus, String targetGroup, FileSystem fs, Path target, boolean recursion) {
      setFullFileStatus(conf, sourceStatus, targetGroup, fs, target, recursion, recursion ? new FsShell() : null);
   }

   @VisibleForTesting
   static void setFullFileStatus(Configuration conf, HadoopFileStatus sourceStatus, String targetGroup, FileSystem fs, Path target, boolean recursion, FsShell fsShell) {
      try {
         FileStatus fStatus = sourceStatus.getFileStatus();
         String group = fStatus.getGroup();
         boolean aclEnabled = Objects.equal(conf.get("dfs.namenode.acls.enabled"), "true");
         FsPermission sourcePerm = fStatus.getPermission();
         List<AclEntry> aclEntries = null;
         if (aclEnabled && sourceStatus.getAclEntries() != null) {
            LOG.trace(sourceStatus.getAclStatus().toString());
            aclEntries = new ArrayList(sourceStatus.getAclEntries());
            removeBaseAclEntries(aclEntries);
            aclEntries.add(newAclEntry(AclEntryScope.ACCESS, AclEntryType.USER, sourcePerm.getUserAction()));
            aclEntries.add(newAclEntry(AclEntryScope.ACCESS, AclEntryType.GROUP, sourcePerm.getGroupAction()));
            aclEntries.add(newAclEntry(AclEntryScope.ACCESS, AclEntryType.OTHER, sourcePerm.getOtherAction()));
         }

         if (recursion) {
            fsShell.setConf(conf);
            if (group != null && !group.isEmpty()) {
               run(fsShell, new String[]{"-chgrp", "-R", group, target.toString()});
            }

            if (aclEnabled) {
               if (null != aclEntries) {
                  try {
                     String aclEntry = Joiner.on(",").join(aclEntries);
                     run(fsShell, new String[]{"-setfacl", "-R", "--set", aclEntry, target.toString()});
                  } catch (Exception e) {
                     LOG.info("Skipping ACL inheritance: File system for path " + target + " does not support ACLs but dfs.namenode.acls.enabled is set to true. ");
                     LOG.debug("The details are: " + e, e);
                  }
               }
            } else {
               String permission = Integer.toString(sourcePerm.toShort(), 8);
               run(fsShell, new String[]{"-chmod", "-R", permission, target.toString()});
            }
         } else {
            if (group != null && !group.isEmpty() && (targetGroup == null || !group.equals(targetGroup))) {
               fs.setOwner(target, (String)null, group);
            }

            if (aclEnabled) {
               if (null != aclEntries) {
                  fs.setAcl(target, aclEntries);
               }
            } else {
               fs.setPermission(target, sourcePerm);
            }
         }
      } catch (Exception e) {
         LOG.warn("Unable to inherit permissions for file " + target + " from file " + sourceStatus.getFileStatus().getPath(), e.getMessage());
         LOG.debug("Exception while inheriting permissions", e);
      }

   }

   private static AclEntry newAclEntry(AclEntryScope scope, AclEntryType type, FsAction permission) {
      return (new AclEntry.Builder()).setScope(scope).setType(type).setPermission(permission).build();
   }

   private static void removeBaseAclEntries(List entries) {
      Iterables.removeIf(entries, new Predicate() {
         public boolean apply(AclEntry input) {
            return input.getName() == null;
         }

         public boolean test(AclEntry input) {
            return this.apply(input);
         }
      });
   }

   private static void run(FsShell shell, String[] command) throws Exception {
      LOG.debug(ArrayUtils.toString(command));
      int retval = shell.run(command);
      LOG.debug("Return value is :" + retval);
   }

   public static class HadoopFileStatus {
      private final FileStatus fileStatus;
      private final AclStatus aclStatus;

      public HadoopFileStatus(Configuration conf, FileSystem fs, Path file) throws IOException {
         FileStatus fileStatus = fs.getFileStatus(file);
         AclStatus aclStatus = null;
         if (Objects.equal(conf.get("dfs.namenode.acls.enabled"), "true")) {
            try {
               aclStatus = fs.getAclStatus(file);
            } catch (Exception e) {
               HdfsUtils.LOG.info("Skipping ACL inheritance: File system for path " + file + " does not support ACLs but dfs.namenode.acls.enabled is set to true. ");
               HdfsUtils.LOG.debug("The details are: " + e, e);
            }
         }

         this.fileStatus = fileStatus;
         this.aclStatus = aclStatus;
      }

      public FileStatus getFileStatus() {
         return this.fileStatus;
      }

      public List getAclEntries() {
         return this.aclStatus == null ? null : Collections.unmodifiableList(this.aclStatus.getEntries());
      }

      @VisibleForTesting
      AclStatus getAclStatus() {
         return this.aclStatus;
      }
   }
}
