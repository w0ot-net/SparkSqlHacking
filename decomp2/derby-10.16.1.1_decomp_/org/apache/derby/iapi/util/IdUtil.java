package org.apache.derby.iapi.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Properties;
import java.util.Vector;
import org.apache.derby.shared.common.error.StandardException;

public abstract class IdUtil {
   public static final int DBCP_SCHEMA_NAME = 0;
   public static final int DBCP_SQL_JAR_NAME = 1;

   public static String normalToDelimited(String var0) {
      return StringUtil.quoteString(var0, '"');
   }

   public static String mkQualifiedName(String var0, String var1) {
      if (null == var0) {
         return normalToDelimited(var1);
      } else {
         String var10000 = normalToDelimited(var0);
         return var10000 + "." + normalToDelimited(var1);
      }
   }

   public static String mkQualifiedName(String[] var0) {
      StringBuffer var1 = new StringBuffer();

      for(int var2 = 0; var2 < var0.length; ++var2) {
         if (var2 != 0) {
            var1.append(".");
         }

         var1.append(normalToDelimited(var0[var2]));
      }

      return var1.toString();
   }

   public static String[] parseMultiPartSQLIdentifier(String var0) throws StandardException {
      StringReader var1 = new StringReader(var0);
      String[] var2 = parseMultiPartSQLIdentifier(var1);
      verifyEmpty(var1);
      return var2;
   }

   private static String[] parseMultiPartSQLIdentifier(StringReader var0) throws StandardException {
      Vector var1 = new Vector();

      while(true) {
         String var2 = parseId(var0, true);
         var1.add(var2);

         try {
            var0.mark(0);
            int var3 = var0.read();
            if (var3 != 46) {
               if (var3 != -1) {
                  var0.reset();
               }
               break;
            }
         } catch (IOException var5) {
            throw StandardException.newException("XCXA0.S", var5, new Object[0]);
         }
      }

      String[] var6 = new String[var1.size()];
      var1.copyInto(var6);
      return var6;
   }

   public static String parseSQLIdentifier(String var0) throws StandardException {
      StringReader var1 = new StringReader(var0);
      String var2 = parseId(var1, true);
      verifyEmpty(var1);
      return var2;
   }

   private static String parseId(StringReader var0, boolean var1) throws StandardException {
      try {
         var0.mark(0);
         int var2 = var0.read();
         if (var2 == -1) {
            throw StandardException.newException("XCXA0.S", new Object[0]);
         } else {
            var0.reset();
            return var2 == 34 ? parseQId(var0, var1) : parseUnQId(var0, var1);
         }
      } catch (IOException var3) {
         throw StandardException.newException("XCXA0.S", var3, new Object[0]);
      }
   }

   public static String SQLIdentifier2CanonicalPropertyUsername(String var0) {
      boolean var1 = false;

      for(int var3 = 0; var3 < var0.length(); ++var3) {
         char var4 = var0.charAt(var3);
         if ((var4 < 'A' || var4 > 'Z') && var4 != '_' && (var3 <= 0 || var4 < '0' || var4 > '9')) {
            var1 = true;
            break;
         }
      }

      String var2;
      if (!var1) {
         var2 = var0.toLowerCase();
      } else {
         var2 = normalToDelimited(var0);
      }

      return var2;
   }

   private static String parseUnQId(StringReader var0, boolean var1) throws IOException, StandardException {
      StringBuffer var2 = new StringBuffer();
      boolean var4 = true;

      while(true) {
         var0.mark(0);
         int var3;
         if (!idChar(var4, var3 = var0.read())) {
            if (var3 != -1) {
               var0.reset();
            }

            String var5 = var2.toString();
            return var1 ? StringUtil.SQLToUpperCase(var5) : var5;
         }

         var2.append((char)var3);
         var4 = false;
      }
   }

   private static boolean idChar(boolean var0, int var1) {
      if ((var1 < 97 || var1 > 122) && (var1 < 65 || var1 > 90) && (var0 || var1 < 48 || var1 > 57) && (var0 || var1 != 95)) {
         if (Character.isLetter((char)var1)) {
            return true;
         } else {
            return !var0 && Character.isDigit((char)var1);
         }
      } else {
         return true;
      }
   }

   private static String parseQId(StringReader var0, boolean var1) throws IOException, StandardException {
      StringBuffer var2 = new StringBuffer();
      int var3 = var0.read();
      if (var3 != 34) {
         throw StandardException.newException("XCXA0.S", new Object[0]);
      } else {
         while(true) {
            var3 = var0.read();
            if (var3 == 34) {
               var0.mark(0);
               int var4 = var0.read();
               if (var4 != 34) {
                  if (var4 != -1) {
                     var0.reset();
                  }

                  if (var2.length() == 0) {
                     throw StandardException.newException("XCXA0.S", new Object[0]);
                  }

                  if (var1) {
                     return var2.toString();
                  }

                  return normalToDelimited(var2.toString());
               }
            } else if (var3 == -1) {
               throw StandardException.newException("XCXA0.S", new Object[0]);
            }

            var2.append((char)var3);
         }
      }
   }

   private static void verifyEmpty(Reader var0) throws StandardException {
      try {
         if (var0.read() != -1) {
            throw StandardException.newException("XCXA0.S", new Object[0]);
         }
      } catch (IOException var2) {
         throw StandardException.newException("XCXA0.S", var2, new Object[0]);
      }
   }

   public static String[][] parseDbClassPath(String var0) throws StandardException {
      if (var0.length() == 0) {
         return new String[0][];
      } else {
         Vector var1 = new Vector();
         StringReader var2 = new StringReader(var0);

         while(true) {
            try {
               String[] var3 = parseMultiPartSQLIdentifier(var2);
               if (var3.length != 2) {
                  throw StandardException.newException("XCXB0.S", new Object[]{var0});
               }

               var1.add(var3);
               int var4 = var2.read();
               if (var4 == 58) {
                  continue;
               }

               if (var4 != -1) {
                  throw StandardException.newException("XCXB0.S", new Object[]{var0});
               }
            } catch (StandardException var5) {
               if (var5.getMessageId().equals("XCXA0.S")) {
                  throw StandardException.newException("XCXB0.S", var5, new Object[]{var0});
               }

               throw var5;
            } catch (IOException var6) {
               throw StandardException.newException("XCXB0.S", var6, new Object[]{var0});
            }

            String[][] var7 = new String[var1.size()][];
            var1.copyInto(var7);
            return var7;
         }
      }
   }

   public static String[] parseIdList(String var0) throws StandardException {
      if (var0 == null) {
         return null;
      } else {
         StringReader var1 = new StringReader(var0);
         String[] var2 = parseIdList(var1, true);
         verifyEmpty(var1);
         return var2;
      }
   }

   private static String[] parseIdList(StringReader var0, boolean var1) throws StandardException {
      Vector var2 = new Vector();

      while(true) {
         try {
            String var4 = parseId(var0, var1);
            var2.add(var4);
            var0.mark(0);
            int var3 = var0.read();
            if (var3 != 44) {
               if (var3 != -1) {
                  var0.reset();
               }
               break;
            }
         } catch (StandardException var5) {
            if (var5.getMessageId().equals("XCXC0.S")) {
               throw StandardException.newException("XCXC0.S", var5, new Object[0]);
            }

            throw var5;
         } catch (IOException var6) {
            throw StandardException.newException("XCXC0.S", var6, new Object[0]);
         }
      }

      if (var2.size() == 0) {
         return null;
      } else {
         String[] var7 = new String[var2.size()];
         var2.copyInto(var7);
         return var7;
      }
   }

   public static String intersect(String[] var0, String[] var1) {
      if (var0 != null && var1 != null) {
         HashSet var2 = new HashSet();

         for(int var3 = 0; var3 < var1.length; ++var3) {
            var2.add(var1[var3]);
         }

         Vector var5 = new Vector();

         for(int var4 = 0; var4 < var0.length; ++var4) {
            if (var2.contains(var0[var4])) {
               var5.add(var0[var4]);
            }
         }

         return vectorToIdList(var5, true);
      } else {
         return null;
      }
   }

   private static String vectorToIdList(Vector var0, boolean var1) {
      if (var0.size() == 0) {
         return null;
      } else {
         String[] var2 = new String[var0.size()];
         var0.copyInto(var2);
         return var1 ? mkIdList(var2) : mkIdListAsEntered(var2);
      }
   }

   public static String getUserAuthorizationId(String var0) throws StandardException {
      try {
         if (var0 != null) {
            return parseSQLIdentifier(var0);
         }
      } catch (StandardException var2) {
      }

      throw StandardException.newException("28502", new Object[]{var0});
   }

   public static String getUserNameFromURLProps(Properties var0) {
      String var1 = var0.getProperty("user", "APP");
      if (var1.equals("")) {
         var1 = "APP";
      }

      return var1;
   }

   public static String dups(String[] var0) {
      if (var0 == null) {
         return null;
      } else {
         HashSet var1 = new HashSet();
         Vector var2 = new Vector();

         for(int var3 = 0; var3 < var0.length; ++var3) {
            if (!var1.contains(var0[var3])) {
               var1.add(var0[var3]);
            } else {
               var2.add(var0[var3]);
            }
         }

         return vectorToIdList(var2, true);
      }
   }

   public static String pruneDups(String var0) throws StandardException {
      if (var0 == null) {
         return null;
      } else {
         String[] var1 = parseIdList(var0);
         StringReader var2 = new StringReader(var0);
         String[] var3 = parseIdList(var2, false);
         HashSet var4 = new HashSet();
         Vector var5 = new Vector();

         for(int var6 = 0; var6 < var1.length; ++var6) {
            if (!var4.contains(var1[var6])) {
               var4.add(var1[var6]);
               var5.add(var3[var6]);
            }
         }

         return vectorToIdList(var5, false);
      }
   }

   public static String mkIdList(String[] var0) {
      StringBuffer var1 = new StringBuffer();

      for(int var2 = 0; var2 < var0.length; ++var2) {
         if (var2 != 0) {
            var1.append(",");
         }

         var1.append(normalToDelimited(var0[var2]));
      }

      return var1.toString();
   }

   private static String mkIdListAsEntered(String[] var0) {
      StringBuffer var1 = new StringBuffer();

      for(int var2 = 0; var2 < var0.length; ++var2) {
         if (var2 != 0) {
            var1.append(",");
         }

         var1.append(var0[var2]);
      }

      return var1.toString();
   }

   public static boolean idOnList(String var0, String var1) throws StandardException {
      if (var1 == null) {
         return false;
      } else {
         String[] var2 = parseIdList(var1);

         for(int var3 = 0; var3 < var2.length; ++var3) {
            if (var0.equals(var2[var3])) {
               return true;
            }
         }

         return false;
      }
   }

   public static String deleteId(String var0, String var1) throws StandardException {
      if (var1 == null) {
         return null;
      } else {
         Vector var2 = new Vector();
         StringReader var3 = new StringReader(var1);
         String[] var4 = parseIdList(var3, false);

         for(int var5 = 0; var5 < var4.length; ++var5) {
            if (!var0.equals(parseSQLIdentifier(var4[var5]))) {
               var2.add(var4[var5]);
            }
         }

         if (var2.size() == 0) {
            return null;
         } else {
            return vectorToIdList(var2, false);
         }
      }
   }

   public static String appendNormalToList(String var0, String var1) throws StandardException {
      String var2 = normalToDelimited(var0);
      return var1 == null ? var2 : var1 + "," + var2;
   }

   public static String parseRoleId(String var0) throws StandardException {
      var0 = var0.trim();
      if (StringUtil.SQLToUpperCase(var0).equals("NONE")) {
         throw StandardException.newException("XCXA0.S", new Object[0]);
      } else {
         var0 = parseSQLIdentifier(var0);
         checkIdentifierLengthLimit(var0, 128);
         return var0;
      }
   }

   public static void checkIdentifierLengthLimit(String var0, int var1) throws StandardException {
      if (var0.length() > var1) {
         throw StandardException.newException("42622", new Object[]{var0, String.valueOf(var1)});
      }
   }
}
