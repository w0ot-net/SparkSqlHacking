package org.datanucleus.util;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusUserException;

public class Imports {
   private Map primitives = new HashMap();
   private Map importedClassesByName = new HashMap();
   private Set importedPackageNames = new HashSet();

   public Imports() {
      this.primitives.put("boolean", Boolean.TYPE);
      this.primitives.put("byte", Byte.TYPE);
      this.primitives.put("char", Character.TYPE);
      this.primitives.put("short", Short.TYPE);
      this.primitives.put("int", Integer.TYPE);
      this.primitives.put("long", Long.TYPE);
      this.primitives.put("float", Float.TYPE);
      this.primitives.put("double", Double.TYPE);
      this.importedPackageNames.add("java.lang");
   }

   public void importPackage(String className) {
      int lastDot = className.lastIndexOf(46);
      if (lastDot > 0) {
         this.importedPackageNames.add(className.substring(0, lastDot));
      }

   }

   public void importClass(String className) {
      int lastDot = className.lastIndexOf(46);
      if (lastDot > 0) {
         this.importedClassesByName.put(className.substring(lastDot + 1), className);
      }

   }

   public void parseImports(String imports) {
      StringTokenizer t1 = new StringTokenizer(imports, ";");

      while(t1.hasMoreTokens()) {
         String importDecl = t1.nextToken().trim();
         if (importDecl.length() == 0 && !t1.hasMoreTokens()) {
            break;
         }

         StringTokenizer t2 = new StringTokenizer(importDecl, " ");
         if (t2.countTokens() != 2 || !t2.nextToken().equals("import")) {
            throw new NucleusUserException(Localiser.msg("021002", importDecl));
         }

         String importName = t2.nextToken();
         int lastDot = importName.lastIndexOf(".");
         String lastPart = importName.substring(lastDot + 1);
         if (lastPart.equals("*")) {
            if (lastDot < 1) {
               throw new NucleusUserException(Localiser.msg("021003", importName));
            }

            this.importedPackageNames.add(importName.substring(0, lastDot));
         } else if (this.importedClassesByName.put(lastPart, importName) != null) {
            NucleusLogger.QUERY.info(Localiser.msg("021004", importName));
         }
      }

   }

   public Class resolveClassDeclaration(String classDecl, ClassLoaderResolver clr, ClassLoader primaryClassLoader) {
      boolean isArray = classDecl.indexOf(91) >= 0;
      if (isArray) {
         classDecl = classDecl.substring(0, classDecl.indexOf(91));
      }

      Class c;
      if (classDecl.indexOf(46) < 0) {
         c = (Class)this.primitives.get(classDecl);
         if (c == null) {
            String cd = (String)this.importedClassesByName.get(classDecl);
            if (cd != null) {
               c = clr.classForName(cd, primaryClassLoader);
            }
         }

         if (c == null) {
            for(String packageName : this.importedPackageNames) {
               try {
                  Class c1 = clr.classForName(packageName + '.' + classDecl, primaryClassLoader);
                  if (c != null && c1 != null) {
                     throw new NucleusUserException(Localiser.msg("021008", c.getName(), c1.getName()));
                  }

                  c = c1;
               } catch (ClassNotResolvedException var9) {
               }
            }

            if (c == null) {
               throw new ClassNotResolvedException(classDecl);
            }

            if (NucleusLogger.GENERAL.isDebugEnabled()) {
               NucleusLogger.GENERAL.debug(Localiser.msg("021010", classDecl, c.getName()));
            }
         }
      } else {
         c = clr.classForName(classDecl, primaryClassLoader);
      }

      if (isArray) {
         c = Array.newInstance(c, 0).getClass();
      }

      return c;
   }
}
