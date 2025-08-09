package org.datanucleus.store.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.store.types.converters.TypeConverter;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class TypeManagerImpl implements TypeManager, Serializable {
   private static final long serialVersionUID = 8217508318434539002L;
   protected NucleusContext nucCtx;
   protected transient ClassLoaderResolver clr;
   protected Map javaTypes = new ConcurrentHashMap();
   protected Map convertersByName = null;
   protected Map autoApplyConvertersByType = null;
   protected Map typeConverterMap = null;
   private static Comparator ALPHABETICAL_ORDER = new Comparator() {
      public int compare(Class cls1, Class cls2) {
         int res = String.CASE_INSENSITIVE_ORDER.compare(cls1.getName(), cls2.getName());
         if (res == 0) {
            res = cls1.getName().compareTo(cls2.getName());
         }

         return res;
      }
   };
   private static Comparator ALPHABETICAL_ORDER_STRING = new Comparator() {
      public int compare(String cls1, String cls2) {
         int res = String.CASE_INSENSITIVE_ORDER.compare(cls1, cls2);
         if (res == 0) {
            res = cls1.compareTo(cls2);
         }

         return res;
      }
   };

   public TypeManagerImpl(NucleusContext nucCtx) {
      this.nucCtx = nucCtx;
      this.loadJavaTypes(nucCtx.getPluginManager());
      this.loadTypeConverters(nucCtx.getPluginManager());
   }

   protected ClassLoaderResolver getClassLoaderResolver() {
      if (this.clr == null) {
         this.clr = this.nucCtx.getClassLoaderResolver((ClassLoader)null);
      }

      return this.clr;
   }

   public Set getSupportedSecondClassTypes() {
      return new HashSet(this.javaTypes.keySet());
   }

   public boolean isSupportedSecondClassType(String className) {
      if (className == null) {
         return false;
      } else {
         JavaType type = (JavaType)this.javaTypes.get(className);
         if (type == null) {
            try {
               Class cls = this.getClassLoaderResolver().classForName(className);
               type = this.findJavaTypeForClass(cls);
               return type != null;
            } catch (Exception var4) {
               return false;
            }
         } else {
            return true;
         }
      }
   }

   public String[] filterOutSupportedSecondClassNames(String[] inputClassNames) {
      int filteredClasses = 0;

      for(int i = 0; i < inputClassNames.length; ++i) {
         if (this.isSupportedSecondClassType(inputClassNames[i])) {
            inputClassNames[i] = null;
            ++filteredClasses;
         }
      }

      if (filteredClasses == 0) {
         return inputClassNames;
      } else {
         String[] restClasses = new String[inputClassNames.length - filteredClasses];
         int m = 0;

         for(int i = 0; i < inputClassNames.length; ++i) {
            if (inputClassNames[i] != null) {
               restClasses[m++] = inputClassNames[i];
            }
         }

         return restClasses;
      }
   }

   public boolean isDefaultPersistent(Class c) {
      if (c == null) {
         return false;
      } else {
         JavaType type = (JavaType)this.javaTypes.get(c.getName());
         if (type != null) {
            return true;
         } else {
            type = this.findJavaTypeForClass(c);
            return type != null;
         }
      }
   }

   public boolean isDefaultFetchGroup(Class c) {
      if (c == null) {
         return false;
      } else if (this.nucCtx.getApiAdapter().isPersistable(c)) {
         return this.nucCtx.getApiAdapter().getDefaultDFGForPersistableField();
      } else {
         JavaType type = (JavaType)this.javaTypes.get(c.getName());
         if (type != null) {
            return type.dfg;
         } else {
            type = this.findJavaTypeForClass(c);
            return type != null ? type.dfg : false;
         }
      }
   }

   public boolean isDefaultFetchGroupForCollection(Class c, Class genericType) {
      if (c != null && genericType == null) {
         return this.isDefaultFetchGroup(c);
      } else if (c == null) {
         return false;
      } else {
         String name = c.getName() + "<" + genericType.getName() + ">";
         JavaType type = (JavaType)this.javaTypes.get(name);
         if (type != null) {
            return type.dfg;
         } else {
            type = this.findJavaTypeForCollectionClass(c, genericType);
            return type != null ? type.dfg : false;
         }
      }
   }

   public boolean isDefaultEmbeddedType(Class c) {
      if (c == null) {
         return false;
      } else {
         JavaType type = (JavaType)this.javaTypes.get(c.getName());
         if (type != null) {
            return type.embedded;
         } else {
            type = this.findJavaTypeForClass(c);
            return type != null ? type.embedded : false;
         }
      }
   }

   public boolean isSecondClassMutableType(String className) {
      return this.getWrapperTypeForType(className) != null;
   }

   public Class getWrapperTypeForType(String className) {
      if (className == null) {
         return null;
      } else {
         JavaType type = (JavaType)this.javaTypes.get(className);
         return type == null ? null : type.wrapperType;
      }
   }

   public Class getWrappedTypeBackedForType(String className) {
      if (className == null) {
         return null;
      } else {
         JavaType type = (JavaType)this.javaTypes.get(className);
         return type == null ? null : type.wrapperTypeBacked;
      }
   }

   public boolean isSecondClassWrapper(String className) {
      if (className == null) {
         return false;
      } else {
         for(JavaType type : this.javaTypes.values()) {
            if (type.wrapperType != null && type.wrapperType.getName().equals(className)) {
               return true;
            }

            if (type.wrapperTypeBacked != null && type.wrapperTypeBacked.getName().equals(className)) {
               return true;
            }
         }

         return false;
      }
   }

   public Class getTypeForSecondClassWrapper(String className) {
      for(JavaType type : this.javaTypes.values()) {
         if (type.wrapperType != null && type.wrapperType.getName().equals(className)) {
            return type.cls;
         }

         if (type.wrapperTypeBacked != null && type.wrapperTypeBacked.getName().equals(className)) {
            return type.cls;
         }
      }

      return null;
   }

   public TypeConverter getTypeConverterForName(String converterName) {
      return this.convertersByName != null && converterName != null ? (TypeConverter)this.convertersByName.get(converterName) : null;
   }

   public void registerConverter(String name, TypeConverter converter, boolean autoApply, String autoApplyType) {
      if (this.convertersByName == null) {
         this.convertersByName = new ConcurrentHashMap();
      }

      this.convertersByName.put(name, converter);
      if (autoApply) {
         if (this.autoApplyConvertersByType == null) {
            this.autoApplyConvertersByType = new ConcurrentHashMap();
         }

         this.autoApplyConvertersByType.put(autoApplyType, converter);
      }

   }

   public void registerConverter(String name, TypeConverter converter) {
      this.registerConverter(name, converter, false, (String)null);
   }

   public TypeConverter getAutoApplyTypeConverterForType(Class memberType) {
      return this.autoApplyConvertersByType == null ? null : (TypeConverter)this.autoApplyConvertersByType.get(memberType.getName());
   }

   public TypeConverter getDefaultTypeConverterForType(Class memberType) {
      JavaType javaType = (JavaType)this.javaTypes.get(memberType.getName());
      if (javaType == null) {
         return null;
      } else {
         String typeConverterName = javaType.typeConverterName;
         return typeConverterName == null ? null : this.getTypeConverterForName(typeConverterName);
      }
   }

   public TypeConverter getTypeConverterForType(Class memberType, Class datastoreType) {
      if (this.typeConverterMap != null && memberType != null) {
         Map<Class, TypeConverter> convertersForMember = (Map)this.typeConverterMap.get(memberType);
         return convertersForMember == null ? null : (TypeConverter)convertersForMember.get(datastoreType);
      } else {
         return null;
      }
   }

   public Collection getTypeConvertersForType(Class memberType) {
      if (this.typeConverterMap != null && memberType != null) {
         Map<Class, TypeConverter> convertersForMember = (Map)this.typeConverterMap.get(memberType);
         return convertersForMember == null ? null : convertersForMember.values();
      } else {
         return null;
      }
   }

   protected JavaType findJavaTypeForClass(Class cls) {
      if (cls == null) {
         return null;
      } else {
         JavaType type = (JavaType)this.javaTypes.get(cls.getName());
         if (type != null) {
            return type;
         } else {
            for(JavaType var6 : new HashSet(this.javaTypes.values())) {
               if (var6.cls == cls && var6.genericType == null) {
                  return var6;
               }

               if (!var6.cls.getName().equals("java.lang.Object") && !var6.cls.getName().equals("java.io.Serializable")) {
                  Class componentCls = cls.isArray() ? cls.getComponentType() : null;
                  if (componentCls != null) {
                     if (var6.cls.isArray() && var6.cls.getComponentType().isAssignableFrom(componentCls)) {
                        this.javaTypes.put(cls.getName(), var6);
                        if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                           NucleusLogger.PERSISTENCE.debug(Localiser.msg("016001", cls.getName(), var6.cls.getName()));
                        }

                        return var6;
                     }
                  } else if (var6.cls.isAssignableFrom(cls) && var6.genericType == null) {
                     this.javaTypes.put(cls.getName(), var6);
                     if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                        NucleusLogger.PERSISTENCE.debug(Localiser.msg("016001", cls.getName(), var6.cls.getName()));
                     }

                     return var6;
                  }
               }
            }

            return null;
         }
      }
   }

   protected JavaType findJavaTypeForCollectionClass(Class cls, Class genericType) {
      if (cls == null) {
         return null;
      } else if (genericType == null) {
         return this.findJavaTypeForClass(cls);
      } else {
         String typeName = cls.getName() + "<" + genericType.getName() + ">";
         JavaType type = (JavaType)this.javaTypes.get(typeName);
         if (type != null) {
            return type;
         } else {
            for(JavaType var7 : new HashSet(this.javaTypes.values())) {
               if (var7.cls.isAssignableFrom(cls) && var7.genericType != null && var7.genericType.isAssignableFrom(genericType)) {
                  this.javaTypes.put(typeName, var7);
                  return var7;
               }
            }

            return this.findJavaTypeForClass(cls);
         }
      }
   }

   private void loadJavaTypes(PluginManager mgr) {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("016003"));
      }

      ClassLoaderResolver clr = this.getClassLoaderResolver();
      ConfigurationElement[] elems = mgr.getConfigurationElementsForExtension("org.datanucleus.java_type", (String)null, (String)null);
      if (elems != null) {
         for(int i = 0; i < elems.length; ++i) {
            String javaName = elems[i].getAttribute("name").trim();
            String genericTypeName = elems[i].getAttribute("generic-type");
            String embeddedString = elems[i].getAttribute("embedded");
            String dfgString = elems[i].getAttribute("dfg");
            String wrapperType = elems[i].getAttribute("wrapper-type");
            String wrapperTypeBacked = elems[i].getAttribute("wrapper-type-backed");
            String typeConverterName = elems[i].getAttribute("converter-name");
            boolean embedded = false;
            if (embeddedString != null && embeddedString.equalsIgnoreCase("true")) {
               embedded = true;
            }

            boolean dfg = false;
            if (dfgString != null && dfgString.equalsIgnoreCase("true")) {
               dfg = true;
            }

            if (!StringUtils.isWhitespace(wrapperType)) {
               wrapperType = wrapperType.trim();
            } else {
               wrapperType = null;
            }

            if (!StringUtils.isWhitespace(wrapperTypeBacked)) {
               wrapperTypeBacked = wrapperTypeBacked.trim();
            } else {
               wrapperTypeBacked = null;
            }

            try {
               Class cls = clr.classForName(javaName);
               Class genericType = null;
               String javaTypeName = cls.getName();
               if (!StringUtils.isWhitespace(genericTypeName)) {
                  genericType = clr.classForName(genericTypeName);
                  javaTypeName = javaTypeName + "<" + genericTypeName + ">";
               }

               if (!this.javaTypes.containsKey(javaTypeName)) {
                  Class wrapperClass = null;
                  if (wrapperType != null) {
                     try {
                        wrapperClass = mgr.loadClass(elems[i].getExtension().getPlugin().getSymbolicName(), wrapperType);
                     } catch (NucleusException var21) {
                        NucleusLogger.PERSISTENCE.error(Localiser.msg("016004", wrapperType));
                        throw new NucleusException(Localiser.msg("016004", wrapperType));
                     }
                  }

                  Class wrapperClassBacked = null;
                  if (wrapperTypeBacked != null) {
                     try {
                        wrapperClassBacked = mgr.loadClass(elems[i].getExtension().getPlugin().getSymbolicName(), wrapperTypeBacked);
                     } catch (NucleusException var20) {
                        NucleusLogger.PERSISTENCE.error(Localiser.msg("016004", wrapperTypeBacked));
                        throw new NucleusException(Localiser.msg("016004", wrapperTypeBacked));
                     }
                  }

                  String typeName = cls.getName();
                  if (genericType != null) {
                     typeName = typeName + "<" + genericType.getName() + ">";
                  }

                  this.javaTypes.put(typeName, new JavaType(cls, genericType, embedded, dfg, wrapperClass, wrapperClassBacked, typeConverterName));
               }
            } catch (ClassNotResolvedException var22) {
               NucleusLogger.PERSISTENCE.debug("Not enabling java type support for " + javaName + " : java type not present in CLASSPATH");
            } catch (Exception e) {
               NucleusLogger.PERSISTENCE.debug("Not enabling java type support for " + javaName + " : " + e.getMessage());
            }
         }
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         List<String> typesList = new ArrayList(this.javaTypes.keySet());
         Collections.sort(typesList, ALPHABETICAL_ORDER_STRING);
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("016006", StringUtils.collectionToString(typesList)));
      }

   }

   private void loadTypeConverters(PluginManager mgr) {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("016007"));
      }

      ClassLoaderResolver clr = this.getClassLoaderResolver();
      ConfigurationElement[] elems = mgr.getConfigurationElementsForExtension("org.datanucleus.type_converter", (String)null, (String)null);
      if (elems != null) {
         for(int i = 0; i < elems.length; ++i) {
            String name = elems[i].getAttribute("name").trim();
            String memberTypeName = elems[i].getAttribute("member-type").trim();
            String datastoreTypeName = elems[i].getAttribute("datastore-type").trim();
            String converterClsName = elems[i].getAttribute("converter-class").trim();
            Class memberType = null;

            try {
               TypeConverter conv = (TypeConverter)mgr.createExecutableExtension("org.datanucleus.type_converter", (String)"name", (String)name, "converter-class", (Class[])null, (Object[])null);
               this.registerConverter(name, conv);
               if (this.typeConverterMap == null) {
                  this.typeConverterMap = new ConcurrentHashMap();
               }

               memberType = clr.classForName(memberTypeName);
               Class datastoreType = clr.classForName(datastoreTypeName);
               Map<Class, TypeConverter> convertersForMember = (Map)this.typeConverterMap.get(memberType);
               if (convertersForMember == null) {
                  convertersForMember = new ConcurrentHashMap();
                  this.typeConverterMap.put(memberType, convertersForMember);
               }

               convertersForMember.put(datastoreType, conv);
            } catch (Exception var13) {
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  if (memberType != null) {
                     NucleusLogger.PERSISTENCE.debug("TypeConverter for " + memberTypeName + "<->" + datastoreTypeName + " using " + converterClsName + " not instantiable (missing dependencies?) so ignoring");
                  } else {
                     NucleusLogger.PERSISTENCE.debug("TypeConverter for " + memberTypeName + "<->" + datastoreTypeName + " ignored since java type not present in CLASSPATH");
                  }
               }
            }
         }
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("016008"));
         if (this.typeConverterMap != null) {
            List<Class> typesList = new ArrayList(this.typeConverterMap.keySet());
            Collections.sort(typesList, ALPHABETICAL_ORDER);

            for(Class javaType : typesList) {
               Set<Class> datastoreTypes = ((Map)this.typeConverterMap.get(javaType)).keySet();
               StringBuilder str = new StringBuilder();

               for(Class datastoreCls : datastoreTypes) {
                  if (str.length() > 0) {
                     str.append(',');
                  }

                  str.append(StringUtils.getNameOfClass(datastoreCls));
               }

               NucleusLogger.PERSISTENCE.debug("TypeConverter(s) available for " + StringUtils.getNameOfClass(javaType) + " to : " + str.toString());
            }
         }
      }

   }

   static class JavaType implements Serializable {
      private static final long serialVersionUID = -811442140006259453L;
      final Class cls;
      final Class genericType;
      final boolean embedded;
      final boolean dfg;
      final Class wrapperType;
      final Class wrapperTypeBacked;
      final String typeConverterName;

      public JavaType(Class cls, Class genericType, boolean embedded, boolean dfg, Class wrapperType, Class wrapperTypeBacked, String typeConverterName) {
         this.cls = cls;
         this.genericType = genericType;
         this.embedded = embedded;
         this.dfg = dfg;
         this.wrapperType = wrapperType;
         this.wrapperTypeBacked = wrapperTypeBacked != null ? wrapperTypeBacked : wrapperType;
         this.typeConverterName = typeConverterName;
      }
   }
}
