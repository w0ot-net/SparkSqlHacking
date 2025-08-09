package org.apache.logging.log4j.core.util;

import java.util.ArrayList;
import java.util.List;

public enum ExtensionLanguageMapping {
   JS("js", "JavaScript"),
   JAVASCRIPT("javascript", "JavaScript"),
   GVY("gvy", "Groovy"),
   GROOVY("groovy", "Groovy"),
   BSH("bsh", "beanshell"),
   BEANSHELL("beanshell", "beanshell"),
   JY("jy", "jython"),
   JYTHON("jython", "jython"),
   FTL("ftl", "freemarker"),
   FREEMARKER("freemarker", "freemarker"),
   VM("vm", "velocity"),
   VELOCITY("velocity", "velocity"),
   AWK("awk", "awk"),
   EJS("ejs", "ejs"),
   TCL("tcl", "tcl"),
   HS("hs", "jaskell"),
   JELLY("jelly", "jelly"),
   JEP("jep", "jep"),
   JEXL("jexl", "jexl"),
   JEXL2("jexl2", "jexl2"),
   RB("rb", "ruby"),
   RUBY("ruby", "ruby"),
   JUDO("judo", "judo"),
   JUDI("judi", "judo"),
   SCALA("scala", "scala"),
   CLJ("clj", "Clojure");

   private final String extension;
   private final String language;

   private ExtensionLanguageMapping(final String extension, final String language) {
      this.extension = extension;
      this.language = language;
   }

   public String getExtension() {
      return this.extension;
   }

   public String getLanguage() {
      return this.language;
   }

   public static ExtensionLanguageMapping getByExtension(final String extension) {
      for(ExtensionLanguageMapping mapping : values()) {
         if (mapping.extension.equals(extension)) {
            return mapping;
         }
      }

      return null;
   }

   public static List getByLanguage(final String language) {
      List<ExtensionLanguageMapping> list = new ArrayList();

      for(ExtensionLanguageMapping mapping : values()) {
         if (mapping.language.equals(language)) {
            list.add(mapping);
         }
      }

      return list;
   }

   // $FF: synthetic method
   private static ExtensionLanguageMapping[] $values() {
      return new ExtensionLanguageMapping[]{JS, JAVASCRIPT, GVY, GROOVY, BSH, BEANSHELL, JY, JYTHON, FTL, FREEMARKER, VM, VELOCITY, AWK, EJS, TCL, HS, JELLY, JEP, JEXL, JEXL2, RB, RUBY, JUDO, JUDI, SCALA, CLJ};
   }
}
