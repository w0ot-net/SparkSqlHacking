package org.datanucleus.metadata.annotations;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ClassConstants;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.PackageMetaData;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class AnnotationManagerImpl implements AnnotationManager {
   protected final MetaDataManager metadataMgr;
   Map annotationReaderLookup = new HashMap();
   Map annotationReaders = new HashMap();
   Set classAnnotationHandlerAnnotations = null;
   Map classAnnotationHandlers = null;
   Set memberAnnotationHandlerAnnotations = null;
   Map memberAnnotationHandlers = null;

   public AnnotationManagerImpl(MetaDataManager metadataMgr) {
      this.metadataMgr = metadataMgr;
      PluginManager pluginMgr = metadataMgr.getNucleusContext().getPluginManager();
      ConfigurationElement[] elems = pluginMgr.getConfigurationElementsForExtension("org.datanucleus.annotations", (String)null, (String)null);
      if (elems != null) {
         for(int i = 0; i < elems.length; ++i) {
            this.annotationReaderLookup.put(elems[i].getAttribute("annotation-class"), elems[i].getAttribute("reader"));
         }
      }

      elems = pluginMgr.getConfigurationElementsForExtension("org.datanucleus.class_annotation_handler", (String)null, (String)null);
      if (elems != null && elems.length > 0) {
         this.classAnnotationHandlerAnnotations = new HashSet(elems.length);
         this.classAnnotationHandlers = new HashMap(elems.length);

         for(int i = 0; i < elems.length; ++i) {
            this.classAnnotationHandlerAnnotations.add(elems[i].getAttribute("annotation-class"));
         }
      }

      elems = pluginMgr.getConfigurationElementsForExtension("org.datanucleus.member_annotation_handler", (String)null, (String)null);
      if (elems != null && elems.length > 0) {
         this.memberAnnotationHandlerAnnotations = new HashSet(elems.length);
         this.memberAnnotationHandlers = new HashMap(elems.length);

         for(int i = 0; i < elems.length; ++i) {
            this.memberAnnotationHandlerAnnotations.add(elems[i].getAttribute("annotation-class"));
         }
      }

   }

   public AbstractClassMetaData getMetaDataForClass(Class cls, PackageMetaData pmd, ClassLoaderResolver clr) {
      if (cls == null) {
         return null;
      } else {
         Annotation[] annotations = cls.getAnnotations();
         if (annotations != null && annotations.length != 0) {
            String readerClassName = null;

            for(int i = 0; i < annotations.length; ++i) {
               String reader = (String)this.annotationReaderLookup.get(annotations[i].annotationType().getName());
               if (reader != null) {
                  readerClassName = reader;
                  break;
               }
            }

            if (readerClassName == null) {
               NucleusLogger.METADATA.debug(Localiser.msg("044202", cls.getName()));
               return null;
            } else {
               AnnotationReader reader = (AnnotationReader)this.annotationReaders.get(readerClassName);
               if (reader == null) {
                  try {
                     Class[] ctrArgs = new Class[]{ClassConstants.METADATA_MANAGER};
                     Object[] ctrParams = new Object[]{this.metadataMgr};
                     PluginManager pluginMgr = this.metadataMgr.getNucleusContext().getPluginManager();
                     reader = (AnnotationReader)pluginMgr.createExecutableExtension("org.datanucleus.annotations", "reader", readerClassName, "reader", ctrArgs, ctrParams);
                     this.annotationReaders.put(readerClassName, reader);
                  } catch (Exception var10) {
                     NucleusLogger.METADATA.warn(Localiser.msg("MetaData.AnnotationReaderNotFound", readerClassName));
                     return null;
                  }
               }

               return reader.getMetaDataForClass(cls, pmd, clr);
            }
         } else {
            return null;
         }
      }
   }

   public boolean getClassAnnotationHasHandler(String annotationName) {
      return this.classAnnotationHandlerAnnotations != null && this.classAnnotationHandlerAnnotations.contains(annotationName);
   }

   public boolean getMemberAnnotationHasHandler(String annotationName) {
      return this.memberAnnotationHandlerAnnotations != null && this.memberAnnotationHandlerAnnotations.contains(annotationName);
   }

   public ClassAnnotationHandler getHandlerForClassAnnotation(String annotationName) {
      if (this.classAnnotationHandlerAnnotations != null && this.classAnnotationHandlerAnnotations.contains(annotationName)) {
         ClassAnnotationHandler handler = (ClassAnnotationHandler)this.classAnnotationHandlers.get(annotationName);
         if (handler == null) {
            try {
               PluginManager pluginMgr = this.metadataMgr.getNucleusContext().getPluginManager();
               handler = (ClassAnnotationHandler)pluginMgr.createExecutableExtension("org.datanucleus.class_annotation_handler", (String)"annotation-class", (String)annotationName, "handler", (Class[])null, (Object[])null);
               this.classAnnotationHandlers.put(annotationName, handler);
            } catch (Exception var4) {
               NucleusLogger.METADATA.warn(Localiser.msg("MetaData.ClassAnnotationHandlerNotFound", annotationName));
               return null;
            }
         }

         return handler;
      } else {
         return null;
      }
   }

   public MemberAnnotationHandler getHandlerForMemberAnnotation(String annotationName) {
      if (this.memberAnnotationHandlerAnnotations != null && this.memberAnnotationHandlerAnnotations.contains(annotationName)) {
         MemberAnnotationHandler handler = (MemberAnnotationHandler)this.memberAnnotationHandlers.get(annotationName);
         if (handler == null) {
            try {
               PluginManager pluginMgr = this.metadataMgr.getNucleusContext().getPluginManager();
               handler = (MemberAnnotationHandler)pluginMgr.createExecutableExtension("org.datanucleus.member_annotation_handler", (String)"annotation-class", (String)annotationName, "handler", (Class[])null, (Object[])null);
               this.memberAnnotationHandlers.put(annotationName, handler);
            } catch (Exception var4) {
               NucleusLogger.METADATA.warn(Localiser.msg("MetaData.MemberAnnotationHandlerNotFound", annotationName));
               return null;
            }
         }

         return handler;
      } else {
         return null;
      }
   }
}
