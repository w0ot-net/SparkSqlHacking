package org.apache.hive.common.util;

import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Private
public class ACLConfigurationParser {
   private static final Logger LOG = LoggerFactory.getLogger(ACLConfigurationParser.class);
   private static final String WILDCARD_ACL_VALUE = "*";
   private static final Pattern splitPattern = Pattern.compile("\\s+");
   private final Set allowedUsers = Sets.newLinkedHashSet();
   private final Set allowedGroups = Sets.newLinkedHashSet();

   public ACLConfigurationParser(Configuration conf, String confPropertyName) {
      this.parse(conf, confPropertyName);
   }

   private boolean isWildCard(String aclStr) {
      return aclStr.trim().equals("*");
   }

   private void parse(Configuration conf, String configProperty) {
      String aclsStr = conf.get(configProperty);
      if (aclsStr != null && !aclsStr.isEmpty()) {
         if (this.isWildCard(aclsStr)) {
            this.allowedUsers.add("*");
         } else {
            String[] splits = splitPattern.split(aclsStr);
            int counter = -1;
            String userListStr = null;
            String groupListStr = null;

            for(String s : splits) {
               if (!s.isEmpty() || userListStr == null) {
                  ++counter;
                  if (counter == 0) {
                     userListStr = s;
                  } else {
                     if (counter != 1) {
                        LOG.warn("Invalid configuration specified for " + configProperty + ", ignoring configured ACLs, value=" + aclsStr);
                        return;
                     }

                     groupListStr = s;
                  }
               }
            }

            if (userListStr != null) {
               if (userListStr.length() >= 1) {
                  this.allowedUsers.addAll(StringUtils.getTrimmedStringCollection(userListStr));
               }

               if (groupListStr != null && groupListStr.length() >= 1) {
                  this.allowedGroups.addAll(StringUtils.getTrimmedStringCollection(groupListStr));
               }

            }
         }
      }
   }

   public Set getAllowedUsers() {
      return Collections.unmodifiableSet(this.allowedUsers);
   }

   public Set getAllowedGroups() {
      return Collections.unmodifiableSet(this.allowedGroups);
   }

   public void addAllowedUser(String user) {
      if (!org.apache.commons.lang3.StringUtils.isBlank(user)) {
         if (!this.allowedUsers.contains("*")) {
            if (user.equals("*")) {
               this.allowedUsers.clear();
               this.allowedGroups.clear();
            }

            this.allowedUsers.add(user);
         }
      }
   }

   public void addAllowedGroup(String group) {
      this.allowedGroups.add(group);
   }

   public String toAclString() {
      return this.toString();
   }

   public String toString() {
      if (this.getAllowedUsers().contains("*")) {
         return "*";
      } else if (this.allowedUsers.size() == 0 && this.allowedGroups.size() == 0) {
         return " ";
      } else {
         String userString = this.constructCsv(this.allowedUsers);
         String groupString = "";
         if (this.allowedGroups.size() > 0) {
            groupString = " " + this.constructCsv(this.allowedGroups);
         }

         return userString + groupString;
      }
   }

   private String constructCsv(Set inSet) {
      StringBuilder sb = new StringBuilder();
      if (inSet != null) {
         boolean isFirst = true;

         for(String s : inSet) {
            if (!isFirst) {
               sb.append(",");
            } else {
               isFirst = false;
            }

            sb.append(s);
         }
      }

      return sb.toString();
   }
}
