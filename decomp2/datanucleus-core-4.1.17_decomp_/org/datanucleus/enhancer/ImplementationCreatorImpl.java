package org.datanucleus.enhancer;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ImplementationCreator;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.InterfaceMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;

public class ImplementationCreatorImpl implements Serializable, ImplementationCreator {
   private static final long serialVersionUID = 4581773672324439549L;
   protected final MetaDataManager metaDataMgr;
   protected final EnhancerClassLoader loader;

   public ImplementationCreatorImpl(MetaDataManager mmgr) {
      this.metaDataMgr = mmgr;
      this.loader = new EnhancerClassLoader();
   }

   public ClassLoader getClassLoader() {
      return this.loader;
   }

   public Object newInstance(Class cls, ClassLoaderResolver clr) {
      try {
         if (Persistable.class.isAssignableFrom(cls)) {
            if (Modifier.isAbstract(cls.getModifiers())) {
               ClassMetaData cmd = (ClassMetaData)this.metaDataMgr.getMetaDataForClass(cls, clr);
               if (cmd == null) {
                  throw (new NucleusException("Could not find metadata for class " + cls.getName())).setFatal();
               } else {
                  Object obj = this.newInstance(cmd, clr);
                  if (!this.metaDataMgr.hasMetaDataForClass(obj.getClass().getName())) {
                     this.metaDataMgr.registerImplementationOfAbstractClass(cmd, obj.getClass(), clr);
                  }

                  return obj;
               }
            } else {
               return cls.newInstance();
            }
         } else {
            InterfaceMetaData imd = this.metaDataMgr.getMetaDataForInterface(cls, clr);
            if (imd == null) {
               throw (new NucleusException("Could not find metadata for class/interface " + cls.getName())).setFatal();
            } else {
               Object obj = this.newInstance(imd, clr);
               if (!this.metaDataMgr.hasMetaDataForClass(obj.getClass().getName())) {
                  this.metaDataMgr.registerPersistentInterface(imd, obj.getClass(), clr);
               }

               return obj;
            }
         }
      } catch (ClassNotFoundException e) {
         throw new NucleusUserException(e.toString(), e);
      } catch (InstantiationException e) {
         throw new NucleusUserException(e.toString(), e);
      } catch (IllegalAccessException e) {
         throw new NucleusUserException(e.toString(), e);
      }
   }

   protected Persistable newInstance(InterfaceMetaData imd, ClassLoaderResolver clr) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      Class cls = clr.classForName(imd.getFullClassName());
      Method[] methods = cls.getDeclaredMethods();

      for(int i = 0; i < methods.length; ++i) {
         String methodName = methods[i].getName();
         if (!methods[i].isBridge() && !methodName.startsWith(this.metaDataMgr.getEnhancedMethodNamePrefix())) {
            String propertyName = methodName;
            if (methodName.startsWith("set")) {
               propertyName = ClassUtils.getFieldNameForJavaBeanSetter(methodName);
            } else if (methodName.startsWith("get")) {
               propertyName = ClassUtils.getFieldNameForJavaBeanGetter(methodName);
            }

            if (imd.getMetaDataForMember(propertyName) == null) {
               throw new NucleusUserException(Localiser.msg("011003", imd.getFullClassName(), methodName));
            }
         }
      }

      String implClassName = imd.getName() + "Impl";
      String implFullClassName = imd.getPackageName() + '.' + implClassName;

      try {
         this.loader.loadClass(implFullClassName);
      } catch (ClassNotFoundException var12) {
         ImplementationGenerator gen = this.getGenerator(imd, implClassName);
         gen.enhance(clr);
         this.loader.defineClass(implFullClassName, gen.getBytes(), clr);
      }

      Object instance = this.loader.loadClass(implFullClassName).newInstance();
      if (instance instanceof Persistable) {
         return (Persistable)instance;
      } else {
         Class[] interfaces = instance.getClass().getInterfaces();
         StringBuilder implementedInterfacesMsg = new StringBuilder("[");
         String classLoaderPCMsg = "";

         for(int i = 0; i < interfaces.length; ++i) {
            implementedInterfacesMsg.append(interfaces[i].getName());
            if (i < interfaces.length - 1) {
               implementedInterfacesMsg.append(",");
            }

            if (interfaces[i].getName().equals(Persistable.class.getName())) {
               classLoaderPCMsg = Localiser.msg("011000", interfaces[i].getClassLoader(), Persistable.class.getClassLoader());
            }
         }

         implementedInterfacesMsg.append("]");
         throw new NucleusException(Localiser.msg("011001", implFullClassName, classLoaderPCMsg, implementedInterfacesMsg.toString()));
      }
   }

   protected Persistable newInstance(ClassMetaData cmd, ClassLoaderResolver clr) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      Class cls = clr.classForName(cmd.getFullClassName());
      Method[] methods = cls.getDeclaredMethods();

      for(int i = 0; i < methods.length; ++i) {
         String methodName = methods[i].getName();
         if (Modifier.isAbstract(methods[i].getModifiers()) && !methodName.startsWith(this.metaDataMgr.getEnhancedMethodNamePrefix())) {
            String propertyName = methodName;
            if (methodName.startsWith("set")) {
               propertyName = ClassUtils.getFieldNameForJavaBeanSetter(methodName);
            } else if (methodName.startsWith("get")) {
               propertyName = ClassUtils.getFieldNameForJavaBeanGetter(methodName);
            }

            if (cmd.getMetaDataForMember(propertyName) == null) {
               throw new NucleusUserException(Localiser.msg("011002", cmd.getFullClassName(), methodName));
            }
         }
      }

      String implClassName = cmd.getName() + "Impl";
      String implFullClassName = cmd.getPackageName() + '.' + implClassName;

      try {
         this.loader.loadClass(implFullClassName);
      } catch (ClassNotFoundException var12) {
         ImplementationGenerator gen = this.getGenerator(cmd, implClassName);
         gen.enhance(clr);
         this.loader.defineClass(implFullClassName, gen.getBytes(), clr);
      }

      Object instance = this.loader.loadClass(implFullClassName).newInstance();
      if (instance instanceof Persistable) {
         return (Persistable)instance;
      } else {
         Class[] interfaces = instance.getClass().getInterfaces();
         StringBuilder implementedInterfacesMsg = new StringBuilder("[");
         String classLoaderPCMsg = "";

         for(int i = 0; i < interfaces.length; ++i) {
            implementedInterfacesMsg.append(interfaces[i].getName());
            if (i < interfaces.length - 1) {
               implementedInterfacesMsg.append(",");
            }

            if (interfaces[i].getName().equals(Persistable.class.getName())) {
               classLoaderPCMsg = Localiser.msg("ImplementationCreator.DifferentClassLoader", interfaces[i].getClassLoader(), Persistable.class.getClassLoader());
            }
         }

         implementedInterfacesMsg.append("]");
         throw new NucleusException(Localiser.msg("011001", implFullClassName, classLoaderPCMsg, implementedInterfacesMsg.toString()));
      }
   }

   protected ImplementationGenerator getGenerator(AbstractClassMetaData acmd, String implClassName) {
      if (acmd instanceof InterfaceMetaData) {
         return new ImplementationGenerator((InterfaceMetaData)acmd, implClassName, this.metaDataMgr);
      } else {
         return acmd instanceof ClassMetaData ? new ImplementationGenerator((ClassMetaData)acmd, implClassName, this.metaDataMgr) : null;
      }
   }
}
