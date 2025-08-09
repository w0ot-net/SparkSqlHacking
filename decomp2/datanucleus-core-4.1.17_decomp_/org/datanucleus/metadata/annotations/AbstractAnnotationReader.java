package org.datanucleus.metadata.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassPersistenceModifier;
import org.datanucleus.metadata.FieldMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.PackageMetaData;
import org.datanucleus.metadata.PropertyMetaData;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class AbstractAnnotationReader implements AnnotationReader {
   protected MetaDataManager mgr;
   protected String[] supportedPackages;

   public AbstractAnnotationReader(MetaDataManager mgr) {
      this.mgr = mgr;
   }

   public String[] getSupportedAnnotationPackages() {
      return this.supportedPackages;
   }

   protected void setSupportedAnnotationPackages(String[] packages) {
      this.supportedPackages = packages;
   }

   protected boolean isSupportedAnnotation(String annotationClassName) {
      if (this.supportedPackages == null) {
         return false;
      } else {
         boolean supported = false;

         for(int j = 0; j < this.supportedPackages.length; ++j) {
            if (annotationClassName.startsWith(this.supportedPackages[j])) {
               supported = true;
               break;
            }
         }

         return supported;
      }
   }

   public AbstractClassMetaData getMetaDataForClass(Class cls, PackageMetaData pmd, ClassLoaderResolver clr) {
      AnnotationObject[] classAnnotations = this.getClassAnnotationsForClass(cls);
      AbstractClassMetaData cmd = this.processClassAnnotations(pmd, cls, classAnnotations, clr);
      if (cmd != null) {
         AnnotationManager annMgr = this.mgr.getAnnotationManager();

         for(int i = 0; i < classAnnotations.length; ++i) {
            String annName = classAnnotations[i].getName();
            ClassAnnotationHandler handler = annMgr.getHandlerForClassAnnotation(annName);
            if (handler != null) {
               handler.processClassAnnotation(classAnnotations[i], cmd, clr);
            }
         }

         if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
            Collection<AnnotatedMember> annotatedFields = this.getFieldAnnotationsForClass(cls);
            Collection<AnnotatedMember> annotatedMethods = this.getJavaBeanAccessorAnnotationsForClass(cls);

            for(AnnotatedMember field : annotatedFields) {
               Iterator<AnnotatedMember> methodIter = annotatedMethods.iterator();

               while(methodIter.hasNext()) {
                  AnnotatedMember method = (AnnotatedMember)methodIter.next();
                  if (field.getName().equals(method.getName())) {
                     NucleusLogger.METADATA.info("Processable annotations specified on both field and getter for " + cls.getName() + "." + field.getName() + " so using as FIELD");
                     field.addAnnotations(method.getAnnotations());
                     methodIter.remove();
                     break;
                  }
               }
            }

            boolean propertyAccessor = false;
            if (cmd.getAccessViaField() != null) {
               if (cmd.getAccessViaField() == Boolean.FALSE) {
                  propertyAccessor = true;
               }
            } else {
               for(AnnotatedMember method : annotatedMethods) {
                  if (method.getAnnotations().length > 0) {
                     propertyAccessor = true;
                  }
               }
            }

            for(AnnotatedMember method : annotatedMethods) {
               AnnotationObject[] annotations = method.getAnnotations();
               AbstractMemberMetaData mmd = this.processMemberAnnotations(cmd, method.getMember(), annotations, propertyAccessor);
               if (annotations != null && annotations.length > 0) {
                  for(int i = 0; i < annotations.length; ++i) {
                     String annName = annotations[i].getName();
                     MemberAnnotationHandler handler = annMgr.getHandlerForMemberAnnotation(annName);
                     if (handler != null) {
                        if (mmd == null) {
                           mmd = new PropertyMetaData(cmd, method.getMember().getName());
                           cmd.addMember(mmd);
                        }

                        handler.processMemberAnnotation(annotations[i], mmd, clr);
                     }
                  }
               }
            }

            for(AnnotatedMember field : annotatedFields) {
               AnnotationObject[] annotations = field.getAnnotations();
               AbstractMemberMetaData mmd = this.processMemberAnnotations(cmd, field.getMember(), annotations, propertyAccessor);
               if (annotations != null && annotations.length > 0) {
                  for(int i = 0; i < annotations.length; ++i) {
                     String annName = annotations[i].getName();
                     MemberAnnotationHandler handler = annMgr.getHandlerForMemberAnnotation(annName);
                     if (handler != null) {
                        if (mmd == null) {
                           mmd = new FieldMetaData(cmd, field.getMember().getName());
                           cmd.addMember(mmd);
                        }

                        handler.processMemberAnnotation(annotations[i], mmd, clr);
                     }
                  }
               }
            }

            Method[] methods = cls.getDeclaredMethods();
            int numberOfMethods = methods.length;

            for(int i = 0; i < numberOfMethods; ++i) {
               this.processMethodAnnotations(cmd, methods[i]);
            }
         }
      }

      return cmd;
   }

   protected abstract AbstractClassMetaData processClassAnnotations(PackageMetaData var1, Class var2, AnnotationObject[] var3, ClassLoaderResolver var4);

   protected abstract AbstractMemberMetaData processMemberAnnotations(AbstractClassMetaData var1, Member var2, AnnotationObject[] var3, boolean var4);

   protected abstract void processMethodAnnotations(AbstractClassMetaData var1, Method var2);

   protected AnnotationObject[] getClassAnnotationsForClass(Class cls) {
      Annotation[] annotations = cls.getAnnotations();
      List<Annotation> supportedAnnots = new ArrayList();
      if (annotations != null && annotations.length > 0) {
         AnnotationManager annMgr = this.mgr.getAnnotationManager();

         for(int j = 0; j < annotations.length; ++j) {
            String annName = annotations[j].annotationType().getName();
            if (this.isSupportedAnnotation(annName) || annMgr.getClassAnnotationHasHandler(annName)) {
               supportedAnnots.add(annotations[j]);
            }
         }
      }

      return this.getAnnotationObjectsForAnnotations(cls.getName(), (Annotation[])supportedAnnots.toArray(new Annotation[supportedAnnots.size()]));
   }

   protected Collection getJavaBeanAccessorAnnotationsForClass(Class cls) {
      Collection<AnnotatedMember> annotatedMethods = new HashSet();
      Method[] methods = cls.getDeclaredMethods();
      int numberOfMethods = methods.length;

      for(int i = 0; i < numberOfMethods; ++i) {
         String methodName = methods[i].getName();
         if (!methods[i].isBridge() && (methodName.startsWith("get") && methodName.length() > 3 || methodName.startsWith("is") && methodName.length() > 2)) {
            Annotation[] annotations = methods[i].getAnnotations();
            if (annotations != null && annotations.length > 0) {
               List<Annotation> supportedAnnots = new ArrayList();
               AnnotationManager annMgr = this.mgr.getAnnotationManager();

               for(int j = 0; j < annotations.length; ++j) {
                  String annName = annotations[j].annotationType().getName();
                  if (this.isSupportedAnnotation(annName) || annMgr.getMemberAnnotationHasHandler(annName)) {
                     supportedAnnots.add(annotations[j]);
                  }
               }

               if (!supportedAnnots.isEmpty()) {
                  AnnotationObject[] objects = this.getAnnotationObjectsForAnnotations(cls.getName(), (Annotation[])supportedAnnots.toArray(new Annotation[supportedAnnots.size()]));
                  AnnotatedMember annMember = new AnnotatedMember(new Member(methods[i]), objects);
                  annotatedMethods.add(annMember);
               }
            }
         }
      }

      return annotatedMethods;
   }

   protected Collection getFieldAnnotationsForClass(Class cls) {
      Collection<AnnotatedMember> annotatedFields = new HashSet();
      Field[] fields = cls.getDeclaredFields();
      int numberOfFields = fields.length;

      for(int i = 0; i < numberOfFields; ++i) {
         Annotation[] annotations = fields[i].getAnnotations();
         List<Annotation> supportedAnnots = new ArrayList();
         if (annotations != null && annotations.length > 0) {
            AnnotationManager annMgr = this.mgr.getAnnotationManager();

            for(int j = 0; j < annotations.length; ++j) {
               String annName = annotations[j].annotationType().getName();
               if (this.isSupportedAnnotation(annName) || annMgr.getMemberAnnotationHasHandler(annName)) {
                  supportedAnnots.add(annotations[j]);
               }
            }
         }

         if (!supportedAnnots.isEmpty()) {
            AnnotationObject[] objects = this.getAnnotationObjectsForAnnotations(cls.getName(), (Annotation[])supportedAnnots.toArray(new Annotation[supportedAnnots.size()]));
            AnnotatedMember annField = new AnnotatedMember(new Member(fields[i]), objects);
            annotatedFields.add(annField);
         }
      }

      return annotatedFields;
   }

   protected AnnotationObject[] getAnnotationObjectsForAnnotations(String clsName, Annotation[] annotations) {
      if (annotations == null) {
         return null;
      } else {
         AnnotationObject[] objects = new AnnotationObject[annotations.length];
         int numberOfAnns = annotations.length;

         for(int i = 0; i < numberOfAnns; ++i) {
            Map<String, Object> map = new HashMap();
            Method[] annMethods = annotations[i].annotationType().getDeclaredMethods();
            int numberOfAnnotateMethods = annMethods.length;

            for(int j = 0; j < numberOfAnnotateMethods; ++j) {
               try {
                  map.put(annMethods[j].getName(), annMethods[j].invoke(annotations[i]));
               } catch (Exception var11) {
                  NucleusLogger.METADATA.warn(Localiser.msg("044201", clsName, annotations[i].annotationType().getName(), annMethods[j].getName()));
               }
            }

            objects[i] = new AnnotationObject(annotations[i].annotationType().getName(), map);
         }

         return objects;
      }
   }
}
