package org.apache.hadoop.fs;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.security.auth.login.LoginException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hive.shims.Utils;
import org.apache.hadoop.security.UserGroupInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultFileAccess {
   private static Logger LOG = LoggerFactory.getLogger(DefaultFileAccess.class);
   private static List emptyGroups = new ArrayList(0);

   public static void checkFileAccess(FileSystem fs, FileStatus stat, FsAction action) throws IOException, AccessControlException, LoginException {
      UserGroupInformation currentUgi = Utils.getUGI();
      checkFileAccess(fs, stat, action, currentUgi.getShortUserName(), Arrays.asList(currentUgi.getGroupNames()));
   }

   public static void checkFileAccess(FileSystem fs, FileStatus stat, FsAction action, String user, List groups) throws IOException, AccessControlException {
      if (groups == null) {
         groups = emptyGroups;
      }

      String superGroupName = getSuperGroupName(fs.getConf());
      if (userBelongsToSuperGroup(superGroupName, groups)) {
         LOG.info("User \"" + user + "\" belongs to super-group \"" + superGroupName + "\". Permission granted for action: " + action + ".");
      } else {
         FsPermission dirPerms = stat.getPermission();
         String grp = stat.getGroup();
         if (user.equals(stat.getOwner())) {
            if (dirPerms.getUserAction().implies(action)) {
               return;
            }
         } else if (groups.contains(grp)) {
            if (dirPerms.getGroupAction().implies(action)) {
               return;
            }
         } else if (dirPerms.getOtherAction().implies(action)) {
            return;
         }

         throw new AccessControlException("action " + action + " not permitted on path " + stat.getPath() + " for user " + user);
      }
   }

   private static String getSuperGroupName(Configuration configuration) {
      return configuration.get("dfs.permissions.supergroup", "");
   }

   private static boolean userBelongsToSuperGroup(String superGroupName, List groups) {
      return groups.contains(superGroupName);
   }
}
