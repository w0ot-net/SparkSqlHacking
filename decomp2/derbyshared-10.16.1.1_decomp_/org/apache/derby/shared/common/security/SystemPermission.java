package org.apache.derby.shared.common.security;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.BasicPermission;
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;

public final class SystemPermission extends BasicPermission {
   private static final long serialVersionUID = 1965420504091489898L;
   public static final String SERVER = "server";
   public static final String ENGINE = "engine";
   public static final String JMX = "jmx";
   public static final String SHUTDOWN = "shutdown";
   public static final String CONTROL = "control";
   public static final String MONITOR = "monitor";
   public static final String USE_DERBY_INTERNALS = "usederbyinternals";
   private static final Set LEGAL_NAMES = new HashSet();
   private static final List LEGAL_ACTIONS;
   public static final SystemPermission ENGINE_MONITOR;
   private String actions;
   private transient int actionMask;

   public SystemPermission(String var1, String var2) {
      super(var1);
      this.validateNameAndActions(var1, var2);
   }

   private void validateNameAndActions(String var1, String var2) {
      if (!LEGAL_NAMES.contains(var1)) {
         throw new IllegalArgumentException("Unknown permission " + var1);
      } else {
         this.actions = getCanonicalForm(var2);
         this.actionMask = getActionMask(this.actions);
      }
   }

   public String getActions() {
      return this.actions;
   }

   public PermissionCollection newPermissionCollection() {
      return new SystemPermissionCollection();
   }

   private static String getCanonicalForm(String var0) {
      Set var1 = parseActions(var0);
      ArrayList var2 = new ArrayList(LEGAL_ACTIONS);
      var2.retainAll(var1);
      return buildActionsString(var2);
   }

   public static Set parseActions(String var0) {
      HashSet var1 = new HashSet();

      for(String var5 : var0.split(",", -1)) {
         var1.add(var5.trim().toLowerCase(Locale.ENGLISH));
      }

      return var1;
   }

   public static String buildActionsString(Iterable var0) {
      StringBuilder var1 = new StringBuilder();

      for(String var3 : var0) {
         if (var1.length() > 0) {
            var1.append(',');
         }

         var1.append(var3);
      }

      return var1.toString();
   }

   public boolean equals(Object var1) {
      if (!super.equals(var1)) {
         return false;
      } else {
         SystemPermission var2 = (SystemPermission)var1;
         return this.actionMask == var2.actionMask;
      }
   }

   public boolean implies(Permission var1) {
      if (!super.implies(var1)) {
         return false;
      } else {
         int var2 = this.actionMask;
         int var3 = ((SystemPermission)var1).actionMask;
         return (var2 & var3) == var3;
      }
   }

   private static int getActionMask(String var0) {
      int var1 = 0;
      StringTokenizer var2 = new StringTokenizer(var0, ",");

      while(var2.hasMoreTokens()) {
         int var3 = LEGAL_ACTIONS.indexOf(var2.nextElement());
         if (var3 != -1) {
            var1 |= 1 << var3;
         }
      }

      return var1;
   }

   private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
      var1.defaultReadObject();
      this.validateNameAndActions(this.getName(), this.getActions());
   }

   public String toString() {
      String var10000 = this.getClass().getName();
      return var10000 + "( " + this.doubleQuote(this.getName()) + ", " + this.doubleQuote(this.actions) + " )";
   }

   private String doubleQuote(String var1) {
      return "\"" + var1 + "\"";
   }

   static {
      LEGAL_NAMES.add("server");
      LEGAL_NAMES.add("engine");
      LEGAL_NAMES.add("jmx");
      LEGAL_ACTIONS = new ArrayList();
      LEGAL_ACTIONS.add("control");
      LEGAL_ACTIONS.add("monitor");
      LEGAL_ACTIONS.add("shutdown");
      LEGAL_ACTIONS.add("usederbyinternals");
      ENGINE_MONITOR = new SystemPermission("engine", "monitor");
   }

   private static class SystemPermissionCollection extends PermissionCollection {
      private static final long serialVersionUID = 0L;
      private HashMap permissions = new HashMap();

      public void add(Permission var1) {
         if (!(var1 instanceof SystemPermission)) {
            throw new IllegalArgumentException();
         } else if (this.isReadOnly()) {
            throw new SecurityException();
         } else {
            String var2 = var1.getName();
            synchronized(this) {
               Permission var4 = (Permission)this.permissions.get(var2);
               if (var4 == null) {
                  this.permissions.put(var2, var1);
               } else {
                  String var10000 = var4.getActions();
                  String var5 = var10000 + "," + var1.getActions();
                  this.permissions.put(var2, new SystemPermission(var2, var5));
               }

            }
         }
      }

      public boolean implies(Permission var1) {
         if (!(var1 instanceof SystemPermission)) {
            return false;
         } else {
            String var2 = var1.getName();
            Permission var3;
            synchronized(this) {
               var3 = (Permission)this.permissions.get(var2);
            }

            return var3 != null && var3.implies(var1);
         }
      }

      public synchronized Enumeration elements() {
         return Collections.enumeration(this.permissions.values());
      }

      private void writeObject(ObjectOutputStream var1) throws IOException {
         ArrayList var2;
         synchronized(this) {
            var2 = new ArrayList(this.permissions.values());
         }

         ObjectOutputStream.PutField var3 = var1.putFields();
         var3.put("permissions", var2);
         var1.writeFields();
      }

      private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
         ObjectInputStream.GetField var2 = var1.readFields();
         List var3 = (List)var2.get("permissions", (Object)null);
         this.permissions = new HashMap();

         for(Object var5 : var3) {
            SystemPermission var6 = (SystemPermission)var5;
            this.permissions.put(var6.getName(), var6);
         }

      }
   }
}
