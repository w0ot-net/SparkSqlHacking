package org.apache.ivy.core.module.descriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.util.extendable.DefaultExtendableItem;

public class Configuration extends DefaultExtendableItem implements InheritableItem {
   private String name;
   private String description;
   private Set extendsFrom;
   private Visibility visibility;
   private boolean transitive;
   private String deprecated;
   private ModuleRevisionId sourceModule;

   public static Collection findConfigurationExtending(String conf, Configuration[] confs) {
      Collection<Configuration> extendingConfs = new ArrayList();

      for(Configuration cf : confs) {
         if (cf != null && Arrays.asList(cf.getExtends()).contains(conf)) {
            extendingConfs.add(cf);
            extendingConfs.addAll(findConfigurationExtending(cf.getName(), confs));
         }
      }

      return extendingConfs;
   }

   public Configuration(String name) {
      this(name, Configuration.Visibility.PUBLIC, (String)null, (String[])null, true, (String)null);
   }

   public Configuration(Configuration source, ModuleRevisionId sourceModule) {
      this(source.getAttributes(), source.getQualifiedExtraAttributes(), source.getName(), source.getVisibility(), source.getDescription(), source.getExtends(), source.isTransitive(), source.getDeprecated(), sourceModule);
   }

   public Configuration(String name, Visibility visibility, String description, String[] ext, boolean transitive, String deprecated) {
      this((Map)null, (Map)null, name, visibility, description, ext, transitive, deprecated, (ModuleRevisionId)null);
   }

   private Configuration(Map attributes, Map extraAttributes, String name, Visibility visibility, String description, String[] exts, boolean transitive, String deprecated, ModuleRevisionId sourceModule) {
      super(attributes, extraAttributes);
      this.transitive = true;
      if (name == null) {
         throw new NullPointerException("null configuration name not allowed");
      } else if (visibility == null) {
         throw new NullPointerException("null visibility not allowed");
      } else {
         this.name = name;
         this.visibility = visibility;
         this.description = description;
         if (exts == null) {
            this.extendsFrom = Collections.emptySet();
         } else {
            this.extendsFrom = new LinkedHashSet();

            for(String ext : exts) {
               this.extendsFrom.add(ext.trim());
            }
         }

         this.transitive = transitive;
         this.deprecated = deprecated;
         this.sourceModule = sourceModule;
      }
   }

   public String getDeprecated() {
      return this.deprecated;
   }

   public String getDescription() {
      return this.description;
   }

   public String[] getExtends() {
      return (String[])this.extendsFrom.toArray(new String[this.extendsFrom.size()]);
   }

   public String getName() {
      return this.name;
   }

   public Visibility getVisibility() {
      return this.visibility;
   }

   public final boolean isTransitive() {
      return this.transitive;
   }

   public ModuleRevisionId getSourceModule() {
      return this.sourceModule;
   }

   public String toString() {
      return this.name;
   }

   public boolean equals(Object obj) {
      return obj instanceof Configuration && ((Configuration)obj).getName().equals(this.getName());
   }

   public int hashCode() {
      return this.getName().hashCode();
   }

   public void replaceWildcards(ModuleDescriptor md) {
      if (this != md.getConfiguration(this.name)) {
         throw new IllegalArgumentException("The given ModuleDescriptor doesn't own this configuration!");
      } else {
         Configuration[] configs = md.getConfigurations();
         Set<String> newExtends = new LinkedHashSet();

         for(String extend : this.extendsFrom) {
            switch (extend) {
               case "*":
                  this.addOther(configs, (Visibility)null, newExtends);
                  break;
               case "*(public)":
                  this.addOther(configs, Configuration.Visibility.PUBLIC, newExtends);
                  break;
               case "*(private)":
                  this.addOther(configs, Configuration.Visibility.PRIVATE, newExtends);
                  break;
               default:
                  newExtends.add(extend);
            }
         }

         this.extendsFrom = newExtends;
      }
   }

   private void addOther(Configuration[] allConfigs, Visibility visibility, Set configs) {
      for(Configuration allConfig : allConfigs) {
         String currentName = allConfig.getName();
         if (!this.name.equals(currentName) && (visibility == null || visibility.equals(allConfig.getVisibility()))) {
            configs.add(currentName);
         }
      }

   }

   public static final class Visibility {
      public static final Visibility PUBLIC = new Visibility("public");
      public static final Visibility PRIVATE = new Visibility("private");
      private String name;

      public static Visibility getVisibility(String name) {
         switch (name) {
            case "private":
               return PRIVATE;
            case "public":
               return PUBLIC;
            default:
               throw new IllegalArgumentException("unknown visibility " + name);
         }
      }

      private Visibility(String name) {
         this.name = name;
      }

      public String toString() {
         return this.name;
      }
   }
}
