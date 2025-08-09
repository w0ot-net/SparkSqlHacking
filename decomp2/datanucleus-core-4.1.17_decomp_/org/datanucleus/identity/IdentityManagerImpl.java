package org.datanucleus.identity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.ClassConstants;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.PersistenceNucleusContext;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancer.EnhancementHelper;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class IdentityManagerImpl implements IdentityManager {
   protected Class datastoreIdClass = null;
   protected IdentityStringTranslator idStringTranslator = null;
   protected IdentityKeyTranslator idKeyTranslator = null;
   private Map constructorCache = new ConcurrentHashMap();
   protected PersistenceNucleusContext nucCtx;

   public IdentityManagerImpl(PersistenceNucleusContext nucCtx) {
      this.nucCtx = nucCtx;
      String dsidName = nucCtx.getConfiguration().getStringProperty("datanucleus.datastoreIdentityType");
      String datastoreIdentityClassName = nucCtx.getPluginManager().getAttributeValueForExtension("org.datanucleus.store_datastoreidentity", "name", dsidName, "class-name");
      if (datastoreIdentityClassName == null) {
         throw (new NucleusUserException(Localiser.msg("002001", dsidName))).setFatal();
      } else {
         ClassLoaderResolver clr = nucCtx.getClassLoaderResolver((ClassLoader)null);

         try {
            this.datastoreIdClass = clr.classForName(datastoreIdentityClassName, ClassConstants.NUCLEUS_CONTEXT_LOADER);
         } catch (ClassNotResolvedException var10) {
            throw (new NucleusUserException(Localiser.msg("002002", dsidName, datastoreIdentityClassName))).setFatal();
         }

         String keyTranslatorType = nucCtx.getConfiguration().getStringProperty("datanucleus.identityKeyTranslatorType");
         if (keyTranslatorType != null) {
            try {
               this.idKeyTranslator = (IdentityKeyTranslator)nucCtx.getPluginManager().createExecutableExtension("org.datanucleus.identity_key_translator", (String)"name", (String)keyTranslatorType, "class-name", (Class[])null, (Object[])null);
            } catch (Exception var9) {
               throw (new NucleusUserException(Localiser.msg("002001", keyTranslatorType))).setFatal();
            }
         }

         String stringTranslatorType = nucCtx.getConfiguration().getStringProperty("datanucleus.identityStringTranslatorType");
         if (stringTranslatorType != null) {
            try {
               this.idStringTranslator = (IdentityStringTranslator)nucCtx.getPluginManager().createExecutableExtension("org.datanucleus.identity_string_translator", (String)"name", (String)stringTranslatorType, "class-name", (Class[])null, (Object[])null);
            } catch (Exception var8) {
               throw (new NucleusUserException(Localiser.msg("002001", stringTranslatorType))).setFatal();
            }
         }

      }
   }

   protected String getConstructorNameForCache(Class type, Class[] ctrArgTypes) {
      StringBuilder name = new StringBuilder(type.getName());
      if (ctrArgTypes != null) {
         for(int i = 0; i < ctrArgTypes.length; ++i) {
            name.append("-").append(ctrArgTypes[i].getName());
         }
      }

      return name.toString();
   }

   public Class getDatastoreIdClass() {
      return this.datastoreIdClass;
   }

   public IdentityStringTranslator getIdentityStringTranslator() {
      return this.idStringTranslator;
   }

   public IdentityKeyTranslator getIdentityKeyTranslator() {
      return this.idKeyTranslator;
   }

   public DatastoreId getDatastoreId(String className, Object value) {
      DatastoreId id;
      if (this.datastoreIdClass == ClassConstants.IDENTITY_DATASTORE_IMPL) {
         id = new DatastoreIdImpl(className, value);
      } else {
         try {
            Class[] ctrArgTypes = new Class[]{String.class, Object.class};
            String ctrName = this.getConstructorNameForCache(this.datastoreIdClass, ctrArgTypes);
            Constructor ctr = (Constructor)this.constructorCache.get(ctrName);
            if (ctr == null) {
               ctr = this.datastoreIdClass.getConstructor(ctrArgTypes);
               this.constructorCache.put(ctrName, ctr);
            }

            id = (DatastoreId)ctr.newInstance(className, value);
         } catch (Exception e) {
            throw new NucleusException("Error encountered while creating datastore instance for class \"" + className + "\"", e);
         }
      }

      return id;
   }

   public DatastoreId getDatastoreId(long value) {
      DatastoreId id;
      if (this.datastoreIdClass == DatastoreUniqueLongId.class) {
         id = new DatastoreUniqueLongId(value);
      } else {
         try {
            Class[] ctrArgTypes = new Class[]{Long.class};
            String ctrName = this.getConstructorNameForCache(this.datastoreIdClass, ctrArgTypes);
            Constructor ctr = (Constructor)this.constructorCache.get(ctrName);
            if (ctr == null) {
               ctr = this.datastoreIdClass.getConstructor(ctrArgTypes);
               this.constructorCache.put(ctrName, ctr);
            }

            id = (DatastoreId)ctr.newInstance(value);
         } catch (Exception e) {
            throw new NucleusException("Error encountered while creating datastore instance for unique value \"" + value + "\"", e);
         }
      }

      return id;
   }

   public DatastoreId getDatastoreId(String idString) {
      DatastoreId id;
      if (this.datastoreIdClass == ClassConstants.IDENTITY_DATASTORE_IMPL) {
         id = new DatastoreIdImpl(idString);
      } else {
         try {
            Class[] ctrArgTypes = new Class[]{String.class};
            String ctrName = this.getConstructorNameForCache(this.datastoreIdClass, ctrArgTypes);
            Constructor ctr = (Constructor)this.constructorCache.get(ctrName);
            if (ctr == null) {
               ctr = this.datastoreIdClass.getConstructor(ctrArgTypes);
               this.constructorCache.put(ctrName, ctr);
            }

            id = (DatastoreId)ctr.newInstance(idString);
         } catch (Exception e) {
            throw new NucleusException("Error encountered while creating datastore instance for string \"" + idString + "\"", e);
         }
      }

      return id;
   }

   public SingleFieldId getSingleFieldId(Class idType, Class pcType, Object key) {
      if (idType == null) {
         throw (new NucleusException(Localiser.msg("029001", pcType))).setFatal();
      } else if (pcType == null) {
         throw (new NucleusException(Localiser.msg("029000", idType))).setFatal();
      } else if (key == null) {
         throw (new NucleusException(Localiser.msg("029003", idType, pcType))).setFatal();
      } else if (!SingleFieldId.class.isAssignableFrom(idType)) {
         throw (new NucleusException(Localiser.msg("029002", idType.getName(), pcType.getName()))).setFatal();
      } else {
         SingleFieldId id = null;
         Class keyType = null;
         if (idType == ClassConstants.IDENTITY_SINGLEFIELD_LONG) {
            keyType = Long.class;
            if (!(key instanceof Long)) {
               throw (new NucleusException(Localiser.msg("029004", idType.getName(), pcType.getName(), key.getClass().getName(), "Long"))).setFatal();
            }
         } else if (idType == ClassConstants.IDENTITY_SINGLEFIELD_INT) {
            keyType = Integer.class;
            if (!(key instanceof Integer)) {
               throw (new NucleusException(Localiser.msg("029004", idType.getName(), pcType.getName(), key.getClass().getName(), "Integer"))).setFatal();
            }
         } else if (idType == ClassConstants.IDENTITY_SINGLEFIELD_STRING) {
            keyType = String.class;
            if (!(key instanceof String)) {
               throw (new NucleusException(Localiser.msg("029004", idType.getName(), pcType.getName(), key.getClass().getName(), "String"))).setFatal();
            }
         } else if (idType == ClassConstants.IDENTITY_SINGLEFIELD_BYTE) {
            keyType = Byte.class;
            if (!(key instanceof Byte)) {
               throw (new NucleusException(Localiser.msg("029004", idType.getName(), pcType.getName(), key.getClass().getName(), "Byte"))).setFatal();
            }
         } else if (idType == ClassConstants.IDENTITY_SINGLEFIELD_SHORT) {
            keyType = Short.class;
            if (!(key instanceof Short)) {
               throw (new NucleusException(Localiser.msg("029004", idType.getName(), pcType.getName(), key.getClass().getName(), "Short"))).setFatal();
            }
         } else if (idType == ClassConstants.IDENTITY_SINGLEFIELD_CHAR) {
            keyType = Character.class;
            if (!(key instanceof Character)) {
               throw (new NucleusException(Localiser.msg("029004", idType.getName(), pcType.getName(), key.getClass().getName(), "Character"))).setFatal();
            }
         } else {
            keyType = Object.class;
         }

         try {
            Class[] ctrArgs = new Class[]{Class.class, keyType};
            String ctrName = this.getConstructorNameForCache(idType, ctrArgs);
            Constructor ctr = (Constructor)this.constructorCache.get(ctrName);
            if (ctr == null) {
               ctr = idType.getConstructor(ctrArgs);
               this.constructorCache.put(ctrName, ctr);
            }

            id = (SingleFieldId)ctr.newInstance(pcType, key);
            return id;
         } catch (Exception e) {
            NucleusLogger.PERSISTENCE.error("Error encountered while creating SingleFieldIdentity instance of type \"" + idType.getName() + "\"", e);
            return null;
         }
      }
   }

   public Object getApplicationId(ClassLoaderResolver clr, AbstractClassMetaData acmd, String keyToString) {
      if (acmd.getIdentityType() != IdentityType.APPLICATION) {
         throw new NucleusException("This class (" + acmd.getFullClassName() + ") doesn't use application-identity!");
      } else {
         Class targetClass = clr.classForName(acmd.getFullClassName());
         Class idType = clr.classForName(acmd.getObjectidClass());
         Object id = null;
         if (acmd.usesSingleFieldIdentityClass()) {
            try {
               Class[] ctrArgTypes;
               if (ClassConstants.IDENTITY_SINGLEFIELD_OBJECT.isAssignableFrom(idType)) {
                  ctrArgTypes = new Class[]{Class.class, Object.class};
               } else {
                  ctrArgTypes = new Class[]{Class.class, String.class};
               }

               String ctrName = this.getConstructorNameForCache(idType, ctrArgTypes);
               Constructor ctr = (Constructor)this.constructorCache.get(ctrName);
               if (ctr == null) {
                  ctr = idType.getConstructor(ctrArgTypes);
                  this.constructorCache.put(ctrName, ctr);
               }

               id = ctr.newInstance(targetClass, keyToString);
            } catch (Exception e) {
               throw new NucleusException("Error encountered while creating single-field identity instance with key \"" + keyToString + "\"", e);
            }
         } else if (Modifier.isAbstract(targetClass.getModifiers()) && acmd.getObjectidClass() != null) {
            try {
               Class type = clr.classForName(acmd.getObjectidClass());
               Class[] ctrArgTypes = new Class[]{String.class};
               String ctrName = this.getConstructorNameForCache(type, ctrArgTypes);
               Constructor ctr = (Constructor)this.constructorCache.get(ctrName);
               if (ctr == null) {
                  ctr = type.getConstructor(ctrArgTypes);
                  this.constructorCache.put(ctrName, ctr);
               }

               id = ctr.newInstance(keyToString);
            } catch (Exception e) {
               String msg = Localiser.msg("010030", acmd.getObjectidClass(), acmd.getFullClassName());
               NucleusLogger.PERSISTENCE.error(msg, e);
               throw new NucleusUserException(msg);
            }
         } else {
            clr.classForName(targetClass.getName(), true);
            id = EnhancementHelper.getInstance().newObjectIdInstance(targetClass, keyToString);
         }

         return id;
      }
   }

   public Object getApplicationId(Object pc, AbstractClassMetaData cmd) {
      if (pc != null && cmd != null) {
         try {
            Object id = ((Persistable)pc).dnNewObjectIdInstance();
            if (!cmd.usesSingleFieldIdentityClass()) {
               ((Persistable)pc).dnCopyKeyFieldsToObjectId(id);
            }

            return id;
         } catch (Exception var4) {
            return null;
         }
      } else {
         return null;
      }
   }

   public Object getApplicationId(Class cls, Object key) {
      return EnhancementHelper.getInstance().newObjectIdInstance(cls, key);
   }
}
