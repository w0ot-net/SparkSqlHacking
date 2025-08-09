package org.apache.derby.security;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.derby.shared.common.security.SystemPermission;

public final class DatabasePermission extends Permission {
   public static final String URL_PROTOCOL_DIRECTORY = "directory:";
   public static final String URL_PATH_INCLUSIVE_STRING = "<<ALL FILES>>";
   public static final char URL_PATH_INCLUSIVE_CHAR = 'I';
   public static final char URL_PATH_SEPARATOR_CHAR = '/';
   public static final char URL_PATH_RELATIVE_CHAR = '.';
   public static final char URL_PATH_WILDCARD_CHAR = '*';
   public static final char URL_PATH_RECURSIVE_CHAR = '-';
   public static final String URL_PATH_SEPARATOR_STRING = String.valueOf('/');
   public static final String URL_PATH_RELATIVE_STRING = String.valueOf('.');
   public static final String URL_PATH_RELATIVE_PREFIX;
   public static final String URL_PATH_WILDCARD_STRING;
   public static final String URL_PATH_WILDCARD_SUFFIX;
   public static final String URL_PATH_RECURSIVE_STRING;
   public static final String URL_PATH_RECURSIVE_SUFFIX;
   public static final String CREATE = "create";
   protected static final List LEGAL_ACTIONS;
   private String actions;
   private transient String path;
   private transient String parentPath;
   private transient char pathType;

   public DatabasePermission(String var1, String var2) throws IOException {
      super(var1);
      this.initActions(var2);
      this.initLocation(var1);
   }

   protected void initActions(String var1) {
      if (var1 == null) {
         throw new NullPointerException("actions can't be null");
      } else if (var1.length() == 0) {
         throw new IllegalArgumentException("actions can't be empty");
      } else {
         Set var2 = SystemPermission.parseActions(var1);

         for(String var4 : var2) {
            if (!LEGAL_ACTIONS.contains(var4)) {
               String var5 = "Illegal action '" + var4 + "'";
               throw new IllegalArgumentException(var5);
            }
         }

         ArrayList var6 = new ArrayList(LEGAL_ACTIONS);
         var6.retainAll(var2);
         this.actions = SystemPermission.buildActionsString(var6);
      }
   }

   protected void initLocation(String var1) throws IOException {
      if (var1 == null) {
         throw new NullPointerException("URL can't be null");
      } else if (var1.length() == 0) {
         throw new IllegalArgumentException("URL can't be empty");
      } else if (!var1.startsWith("directory:")) {
         String var5 = "Unsupported protocol in URL '" + var1 + "'";
         throw new IllegalArgumentException(var5);
      } else {
         String var2 = var1.substring("directory:".length());
         if (var2.equals("<<ALL FILES>>")) {
            this.pathType = 'I';
         } else if (var2.equals(URL_PATH_RECURSIVE_STRING)) {
            this.pathType = '-';
            var2 = URL_PATH_RELATIVE_PREFIX;
         } else if (var2.equals(URL_PATH_WILDCARD_STRING)) {
            this.pathType = '*';
            var2 = URL_PATH_RELATIVE_PREFIX;
         } else if (var2.endsWith(URL_PATH_RECURSIVE_SUFFIX)) {
            this.pathType = '-';
            var2 = var2.substring(0, var2.length() - 1);
         } else if (var2.endsWith(URL_PATH_WILDCARD_SUFFIX)) {
            this.pathType = '*';
            var2 = var2.substring(0, var2.length() - 1);
         } else {
            this.pathType = '/';
         }

         if (this.pathType == 'I') {
            this.path = "<<ALL FILES>>";
         } else {
            if (var2.startsWith(URL_PATH_RELATIVE_PREFIX)) {
               String var3 = System.getProperty("user.dir");
               var2 = var3 + URL_PATH_SEPARATOR_STRING + var2;
            }

            File var4 = (new File(var2)).getCanonicalFile();
            this.path = var4.getPath();
            this.parentPath = this.pathType != '/' ? this.path : var4.getParent();
         }

      }
   }

   public boolean implies(Permission var1) {
      if (!(var1 instanceof DatabasePermission var2)) {
         return false;
      } else if (this.pathType == 'I') {
         return true;
      } else if (var2.pathType == 'I') {
         return false;
      } else if (this.pathType != '-') {
         if (var2.pathType == '-') {
            return false;
         } else if (this.pathType == '*') {
            return this.path.equals(var2.parentPath);
         } else {
            return var2.pathType == '*' ? false : this.path.equals(var2.path);
         }
      } else {
         return var2.parentPath != null && var2.parentPath.startsWith(this.path);
      }
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof DatabasePermission)) {
         return false;
      } else {
         DatabasePermission var2 = (DatabasePermission)var1;
         return this.pathType == var2.pathType && this.path.equals(var2.path);
      }
   }

   public int hashCode() {
      return this.path.hashCode() ^ this.pathType;
   }

   public String getActions() {
      return this.actions;
   }

   private void writeObject(ObjectOutputStream var1) throws IOException {
      var1.defaultWriteObject();
   }

   private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
      var1.defaultReadObject();
      this.initLocation(this.getName());
      this.initActions(this.getActions());
   }

   static {
      URL_PATH_RELATIVE_PREFIX = URL_PATH_RELATIVE_STRING + "/";
      URL_PATH_WILDCARD_STRING = String.valueOf('*');
      URL_PATH_WILDCARD_SUFFIX = URL_PATH_SEPARATOR_STRING + "*";
      URL_PATH_RECURSIVE_STRING = String.valueOf('-');
      URL_PATH_RECURSIVE_SUFFIX = URL_PATH_SEPARATOR_STRING + "-";
      LEGAL_ACTIONS = new ArrayList();
      LEGAL_ACTIONS.add("create");
   }
}
