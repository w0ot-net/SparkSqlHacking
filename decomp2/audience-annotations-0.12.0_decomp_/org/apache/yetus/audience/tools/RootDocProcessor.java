package org.apache.yetus.audience.tools;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationTypeDoc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.Doc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.ProgramElementDoc;
import com.sun.javadoc.RootDoc;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.yetus.audience.InterfaceAudience;
import org.apache.yetus.audience.InterfaceStability;

class RootDocProcessor {
   static String stability = "-unstable";
   static boolean treatUnannotatedClassesAsPrivate = false;
   private static Map proxies = new WeakHashMap();

   public static RootDoc process(RootDoc root) {
      return (RootDoc)process(root, RootDoc.class);
   }

   private static Object process(Object obj, Class type) {
      if (obj == null) {
         return null;
      } else {
         Class<?> cls = obj.getClass();
         if (cls.getName().startsWith("com.sun.")) {
            return getProxy(obj);
         } else if (!(obj instanceof Object[])) {
            return obj;
         } else {
            Class<?> componentType = type.isArray() ? type.getComponentType() : cls.getComponentType();
            Object[] array = obj;
            Object[] newArray = Array.newInstance(componentType, array.length);

            for(int i = 0; i < array.length; ++i) {
               newArray[i] = process(array[i], componentType);
            }

            return newArray;
         }
      }
   }

   private static Object getProxy(Object obj) {
      Object proxy = proxies.get(obj);
      if (proxy == null) {
         proxy = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new ExcludeHandler(obj));
         proxies.put(obj, proxy);
      }

      return proxy;
   }

   private static class ExcludeHandler implements InvocationHandler {
      private Object target;

      public ExcludeHandler(Object target) {
         this.target = target;
      }

      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         String methodName = method.getName();
         if (this.target instanceof Doc) {
            if (methodName.equals("isIncluded")) {
               Doc doc = (Doc)this.target;
               return !exclude(doc) && doc.isIncluded();
            }

            if (this.target instanceof RootDoc) {
               switch (methodName) {
                  case "classes":
                     return filter(((RootDoc)this.target).classes(), ClassDoc.class);
                  case "specifiedClasses":
                     return filter(((RootDoc)this.target).specifiedClasses(), ClassDoc.class);
                  case "specifiedPackages":
                     return filter(((RootDoc)this.target).specifiedPackages(), PackageDoc.class);
               }
            } else if (this.target instanceof ClassDoc) {
               if (this.isFiltered(args)) {
                  switch (methodName) {
                     case "methods":
                        return filter(((ClassDoc)this.target).methods(true), MethodDoc.class);
                     case "fields":
                        return filter(((ClassDoc)this.target).fields(true), FieldDoc.class);
                     case "innerClasses":
                        return filter(((ClassDoc)this.target).innerClasses(true), ClassDoc.class);
                     case "constructors":
                        return filter(((ClassDoc)this.target).constructors(true), ConstructorDoc.class);
                  }
               }
            } else if (this.target instanceof PackageDoc) {
               switch (methodName) {
                  case "allClasses":
                     if (this.isFiltered(args)) {
                        return filter(((PackageDoc)this.target).allClasses(true), ClassDoc.class);
                     }

                     return filter(((PackageDoc)this.target).allClasses(), ClassDoc.class);
                  case "annotationTypes":
                     return filter(((PackageDoc)this.target).annotationTypes(), AnnotationTypeDoc.class);
                  case "enums":
                     return filter(((PackageDoc)this.target).enums(), ClassDoc.class);
                  case "errors":
                     return filter(((PackageDoc)this.target).errors(), ClassDoc.class);
                  case "exceptions":
                     return filter(((PackageDoc)this.target).exceptions(), ClassDoc.class);
                  case "interfaces":
                     return filter(((PackageDoc)this.target).interfaces(), ClassDoc.class);
                  case "ordinaryClasses":
                     return filter(((PackageDoc)this.target).ordinaryClasses(), ClassDoc.class);
               }
            }
         }

         if (args != null && (methodName.equals("compareTo") || methodName.equals("equals") || methodName.equals("overrides") || methodName.equals("subclassOf"))) {
            args[0] = this.unwrap(args[0]);
         }

         try {
            return RootDocProcessor.process(method.invoke(this.target, args), method.getReturnType());
         } catch (InvocationTargetException e) {
            throw e.getTargetException();
         }
      }

      private static boolean exclude(Doc doc) {
         AnnotationDesc[] annotations = null;
         if (doc instanceof ProgramElementDoc) {
            annotations = ((ProgramElementDoc)doc).annotations();
         } else if (doc instanceof PackageDoc) {
            annotations = ((PackageDoc)doc).annotations();
         }

         if (annotations != null) {
            for(AnnotationDesc annotation : annotations) {
               String qualifiedTypeName = annotation.annotationType().qualifiedTypeName();
               if (qualifiedTypeName.equals(InterfaceAudience.Private.class.getCanonicalName()) || qualifiedTypeName.equals(InterfaceAudience.LimitedPrivate.class.getCanonicalName())) {
                  return true;
               }

               if (RootDocProcessor.stability.equals("-evolving") && qualifiedTypeName.equals(InterfaceStability.Unstable.class.getCanonicalName())) {
                  return true;
               }

               if (RootDocProcessor.stability.equals("-stable") && (qualifiedTypeName.equals(InterfaceStability.Unstable.class.getCanonicalName()) || qualifiedTypeName.equals(InterfaceStability.Evolving.class.getCanonicalName()))) {
                  return true;
               }
            }

            for(AnnotationDesc annotation : annotations) {
               String qualifiedTypeName = annotation.annotationType().qualifiedTypeName();
               if (qualifiedTypeName.equals(InterfaceAudience.Public.class.getCanonicalName())) {
                  return false;
               }
            }
         }

         if (!RootDocProcessor.treatUnannotatedClassesAsPrivate) {
            return false;
         } else {
            return doc.isClass() || doc.isInterface() || doc.isAnnotationType();
         }
      }

      private static Object[] filter(Doc[] array, Class componentType) {
         if (array != null && array.length != 0) {
            List<Object> list = new ArrayList(array.length);

            for(Doc entry : array) {
               if (!exclude(entry)) {
                  list.add(RootDocProcessor.process(entry, componentType));
               }
            }

            return list.toArray(Array.newInstance(componentType, list.size()));
         } else {
            return array;
         }
      }

      private Object unwrap(Object proxy) {
         return proxy instanceof Proxy ? ((ExcludeHandler)Proxy.getInvocationHandler(proxy)).target : proxy;
      }

      private boolean isFiltered(Object[] args) {
         return args != null && Boolean.TRUE.equals(args[0]);
      }
   }
}
