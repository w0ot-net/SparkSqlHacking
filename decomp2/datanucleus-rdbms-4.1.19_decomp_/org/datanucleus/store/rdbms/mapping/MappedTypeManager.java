package org.datanucleus.store.rdbms.mapping;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.store.types.TypeManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class MappedTypeManager {
   protected final NucleusContext nucleusCtx;
   protected final ClassLoaderResolver clr;
   Map mappedTypes = new ConcurrentHashMap();

   public MappedTypeManager(NucleusContext nucleusCtx) {
      this.nucleusCtx = nucleusCtx;
      this.clr = nucleusCtx.getClassLoaderResolver((ClassLoader)null);
      this.loadMappings(nucleusCtx.getPluginManager(), this.clr);
   }

   public boolean isSupportedMappedType(String className) {
      if (className == null) {
         return false;
      } else {
         MappedType type = this.getMappedType(className);
         if (type != null) {
            return type.javaMappingType != null;
         } else {
            try {
               Class cls = this.clr.classForName(className);
               type = this.findMappedTypeForClass(cls);
               return type != null && type.javaMappingType != null;
            } catch (Exception var4) {
               return false;
            }
         }
      }
   }

   public Class getMappingType(String className) {
      if (className == null) {
         return null;
      } else {
         MappedType type = this.getMappedType(className);
         if (type == null) {
            TypeManager typeMgr = this.nucleusCtx.getTypeManager();
            Class cls = typeMgr.getTypeForSecondClassWrapper(className);
            if (cls != null) {
               type = this.getMappedType(cls.getName());
               if (type != null) {
                  return type.javaMappingType;
               }
            }

            try {
               cls = this.clr.classForName(className);
               type = this.findMappedTypeForClass(cls);
               return type.javaMappingType;
            } catch (Exception var6) {
               return null;
            }
         } else {
            return type.javaMappingType;
         }
      }
   }

   private void loadMappings(PluginManager mgr, ClassLoaderResolver clr) {
      ConfigurationElement[] elems = mgr.getConfigurationElementsForExtension("org.datanucleus.store.rdbms.java_mapping", (String)null, (String)null);
      if (elems != null) {
         for(int i = 0; i < elems.length; ++i) {
            String javaName = elems[i].getAttribute("java-type").trim();
            String mappingClassName = elems[i].getAttribute("mapping-class");
            if (!this.mappedTypes.containsKey(javaName)) {
               this.addMappedType(mgr, elems[i].getExtension().getPlugin().getSymbolicName(), javaName, mappingClassName, clr);
            }
         }
      }

   }

   private void addMappedType(PluginManager mgr, String pluginId, String className, String mappingClassName, ClassLoaderResolver clr) {
      if (className != null) {
         Class mappingType = null;
         if (!StringUtils.isWhitespace(mappingClassName)) {
            try {
               mappingType = mgr.loadClass(pluginId, mappingClassName);
            } catch (NucleusException var9) {
               NucleusLogger.PERSISTENCE.error(Localiser.msg("016004", new Object[]{mappingClassName}));
               return;
            }
         }

         try {
            Class cls = clr.classForName(className);
            if (cls != null) {
               this.mappedTypes.put(className, new MappedType(cls, mappingType));
            }
         } catch (Exception var8) {
         }

      }
   }

   protected MappedType findMappedTypeForClass(Class cls) {
      MappedType type = this.getMappedType(cls.getName());
      if (type != null) {
         return type;
      } else {
         Class componentCls = cls.isArray() ? cls.getComponentType() : null;

         for(MappedType var6 : new HashSet(this.mappedTypes.values())) {
            if (var6.cls == cls) {
               return var6;
            }

            if (!var6.cls.getName().equals("java.lang.Object") && !var6.cls.getName().equals("java.io.Serializable")) {
               if (componentCls != null) {
                  if (var6.cls.isArray() && var6.cls.getComponentType().isAssignableFrom(componentCls)) {
                     this.mappedTypes.put(cls.getName(), var6);
                     return var6;
                  }
               } else if (var6.cls.isAssignableFrom(cls)) {
                  this.mappedTypes.put(cls.getName(), var6);
                  return var6;
               }
            }
         }

         return null;
      }
   }

   protected MappedType getMappedType(String className) {
      return className == null ? null : (MappedType)this.mappedTypes.get(className);
   }

   static class MappedType {
      final Class cls;
      final Class javaMappingType;

      public MappedType(Class cls, Class mappingType) {
         this.cls = cls;
         this.javaMappingType = mappingType;
      }

      public String toString() {
         StringBuilder str = new StringBuilder("MappedType " + this.cls.getName() + " [");
         if (this.javaMappingType != null) {
            str.append(" mapping=" + this.javaMappingType);
         }

         str.append("]");
         return str.toString();
      }
   }
}
