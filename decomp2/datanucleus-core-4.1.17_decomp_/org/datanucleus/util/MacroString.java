package org.datanucleus.util;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusUserException;

public class MacroString {
   private final String thisClassName;
   private final Imports imports;
   private final String macroString;

   public MacroString(String className, String importsString, String macroString) {
      this.thisClassName = className;
      this.macroString = macroString;
      this.imports = new Imports();
      if (this.thisClassName != null) {
         this.imports.importPackage(this.thisClassName);
      }

      if (importsString != null) {
         this.imports.parseImports(importsString);
      }

   }

   public String substituteMacros(MacroHandler mh, ClassLoaderResolver clr) {
      StringBuilder outBuf = new StringBuilder();

      int right;
      for(int curr = 0; curr < this.macroString.length(); curr = right + 1) {
         int left;
         if ((left = this.macroString.indexOf(123, curr)) < 0) {
            outBuf.append(this.macroString.substring(curr));
            break;
         }

         outBuf.append(this.macroString.substring(curr, left));
         if ((right = this.macroString.indexOf(125, left + 1)) < 0) {
            throw new NucleusUserException(Localiser.msg("031000", this.macroString));
         }

         IdentifierMacro im = this.parseIdentifierMacro(this.macroString.substring(left + 1, right), clr);
         mh.onIdentifierMacro(im);
         outBuf.append(im.value);
      }

      String tmpString = outBuf.toString();
      outBuf = new StringBuilder();

      for(int curr = 0; curr < tmpString.length(); curr = right + 1) {
         int left;
         if ((left = tmpString.indexOf(63, curr)) < 0) {
            outBuf.append(tmpString.substring(curr));
            break;
         }

         outBuf.append(tmpString.substring(curr, left));
         if ((right = tmpString.indexOf(63, left + 1)) < 0) {
            throw new NucleusUserException(Localiser.msg("031001", tmpString));
         }

         ParameterMacro pm = new ParameterMacro(tmpString.substring(left + 1, right));
         mh.onParameterMacro(pm);
         outBuf.append('?');
      }

      return outBuf.toString();
   }

   private Class resolveClassDeclaration(String className, ClassLoaderResolver clr) {
      try {
         return className.equals("this") ? clr.classForName(this.thisClassName, (ClassLoader)null) : this.imports.resolveClassDeclaration(className, clr, (ClassLoader)null);
      } catch (ClassNotResolvedException var4) {
         return null;
      }
   }

   private IdentifierMacro parseIdentifierMacro(String unparsed, ClassLoaderResolver clr) {
      String className = null;
      String fieldName = null;
      String subfieldName = null;
      Class c = this.resolveClassDeclaration(unparsed, clr);
      if (c == null) {
         int lastDot = unparsed.lastIndexOf(46);
         if (lastDot < 0) {
            throw new NucleusUserException(Localiser.msg("031002", unparsed));
         }

         fieldName = unparsed.substring(lastDot + 1);
         className = unparsed.substring(0, lastDot);
         c = this.resolveClassDeclaration(className, clr);
         if (c == null) {
            int lastDot2 = unparsed.lastIndexOf(46, lastDot - 1);
            if (lastDot2 < 0) {
               throw new NucleusUserException(Localiser.msg("031002", unparsed));
            }

            subfieldName = fieldName;
            fieldName = unparsed.substring(lastDot2 + 1, lastDot);
            className = unparsed.substring(0, lastDot2);
            c = this.resolveClassDeclaration(className, clr);
            if (c == null) {
               throw new NucleusUserException(Localiser.msg("031002", unparsed));
            }
         }
      }

      return new IdentifierMacro(unparsed, c.getName(), fieldName, subfieldName);
   }

   public static class IdentifierMacro {
      public final String unparsed;
      public final String className;
      public final String fieldName;
      public final String subfieldName;
      public String value;

      IdentifierMacro(String unparsed, String className, String fieldName, String subfieldName) {
         this.unparsed = unparsed;
         this.className = className;
         this.fieldName = fieldName;
         this.subfieldName = subfieldName;
         this.value = null;
      }

      public String toString() {
         return "{" + this.unparsed + "}";
      }
   }

   public static class ParameterMacro {
      public final String parameterName;

      ParameterMacro(String parameterName) {
         this.parameterName = parameterName;
      }

      public String toString() {
         return "?" + this.parameterName + "?";
      }
   }

   public interface MacroHandler {
      void onIdentifierMacro(IdentifierMacro var1);

      void onParameterMacro(ParameterMacro var1);
   }
}
