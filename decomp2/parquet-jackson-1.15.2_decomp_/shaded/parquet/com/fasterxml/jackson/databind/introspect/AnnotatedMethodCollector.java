package shaded.parquet.com.fasterxml.jackson.databind.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import shaded.parquet.com.fasterxml.jackson.databind.AnnotationIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.type.TypeFactory;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;

public class AnnotatedMethodCollector extends CollectorBase {
   private final ClassIntrospector.MixInResolver _mixInResolver;
   private final boolean _collectAnnotations;

   AnnotatedMethodCollector(AnnotationIntrospector intr, ClassIntrospector.MixInResolver mixins, boolean collectAnnotations) {
      super(intr);
      this._mixInResolver = intr == null ? null : mixins;
      this._collectAnnotations = collectAnnotations;
   }

   public static AnnotatedMethodMap collectMethods(AnnotationIntrospector intr, TypeResolutionContext tc, ClassIntrospector.MixInResolver mixins, TypeFactory types, JavaType type, List superTypes, Class primaryMixIn, boolean collectAnnotations) {
      return (new AnnotatedMethodCollector(intr, mixins, collectAnnotations)).collect(types, tc, type, superTypes, primaryMixIn);
   }

   AnnotatedMethodMap collect(TypeFactory typeFactory, TypeResolutionContext tc, JavaType mainType, List superTypes, Class primaryMixIn) {
      Map<MemberKey, MethodBuilder> methods = new LinkedHashMap();
      this._addMemberMethods(tc, mainType.getRawClass(), methods, primaryMixIn);

      for(JavaType type : superTypes) {
         Class<?> mixin = this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(type.getRawClass());
         this._addMemberMethods(new TypeResolutionContext.Basic(typeFactory, type.getBindings()), type.getRawClass(), methods, mixin);
      }

      boolean checkJavaLangObject = false;
      if (this._mixInResolver != null) {
         Class<?> mixin = this._mixInResolver.findMixInClassFor(Object.class);
         if (mixin != null) {
            this._addMethodMixIns(tc, mainType.getRawClass(), methods, mixin);
            checkJavaLangObject = true;
         }
      }

      if (checkJavaLangObject && this._intr != null && !methods.isEmpty()) {
         for(Map.Entry entry : methods.entrySet()) {
            MemberKey k = (MemberKey)entry.getKey();
            if ("hashCode".equals(k.getName()) && 0 == k.argCount()) {
               try {
                  Method m = Object.class.getDeclaredMethod(k.getName());
                  MethodBuilder b = (MethodBuilder)entry.getValue();
                  b.annotations = this.collectDefaultAnnotations(b.annotations, m.getDeclaredAnnotations());
                  b.method = m;
               } catch (Exception var13) {
               }
            }
         }
      }

      if (methods.isEmpty()) {
         return new AnnotatedMethodMap();
      } else {
         Map<MemberKey, AnnotatedMethod> actual = new LinkedHashMap(methods.size());

         for(Map.Entry entry : methods.entrySet()) {
            AnnotatedMethod am = ((MethodBuilder)entry.getValue()).build();
            if (am != null) {
               actual.put(entry.getKey(), am);
            }
         }

         return new AnnotatedMethodMap(actual);
      }
   }

   private void _addMemberMethods(TypeResolutionContext tc, Class cls, Map methods, Class mixInCls) {
      if (mixInCls != null) {
         this._addMethodMixIns(tc, cls, methods, mixInCls);
      }

      if (cls != null) {
         for(Method m : ClassUtil.getClassMethods(cls)) {
            if (_isIncludableMemberMethod(m)) {
               MemberKey key = new MemberKey(m);
               MethodBuilder b = (MethodBuilder)methods.get(key);
               if (b == null) {
                  AnnotationCollector c = this._intr == null ? AnnotationCollector.emptyCollector() : this.collectAnnotations(m.getDeclaredAnnotations());
                  methods.put(key, new MethodBuilder(tc, m, c));
               } else {
                  if (this._collectAnnotations) {
                     b.annotations = this.collectDefaultAnnotations(b.annotations, m.getDeclaredAnnotations());
                  }

                  Method old = b.method;
                  if (old == null) {
                     b.method = m;
                  } else if (Modifier.isAbstract(old.getModifiers()) && !Modifier.isAbstract(m.getModifiers())) {
                     b.method = m;
                     b.typeContext = tc;
                  }
               }
            }
         }

      }
   }

   protected void _addMethodMixIns(TypeResolutionContext tc, Class targetClass, Map methods, Class mixInCls) {
      if (this._intr != null) {
         for(Class mixin : ClassUtil.findRawSuperTypes(mixInCls, targetClass, true)) {
            for(Method m : mixin.getDeclaredMethods()) {
               if (_isIncludableMemberMethod(m)) {
                  MemberKey key = new MemberKey(m);
                  MethodBuilder b = (MethodBuilder)methods.get(key);
                  Annotation[] anns = m.getDeclaredAnnotations();
                  if (b == null) {
                     methods.put(key, new MethodBuilder(tc, (Method)null, this.collectAnnotations(anns)));
                  } else {
                     b.annotations = this.collectDefaultAnnotations(b.annotations, anns);
                  }
               }
            }
         }

      }
   }

   private static boolean _isIncludableMemberMethod(Method m) {
      if (!Modifier.isStatic(m.getModifiers()) && !m.isSynthetic() && !m.isBridge()) {
         return m.getParameterCount() <= 2;
      } else {
         return false;
      }
   }

   private static final class MethodBuilder {
      public TypeResolutionContext typeContext;
      public Method method;
      public AnnotationCollector annotations;

      public MethodBuilder(TypeResolutionContext tc, Method m, AnnotationCollector ann) {
         this.typeContext = tc;
         this.method = m;
         this.annotations = ann;
      }

      public AnnotatedMethod build() {
         return this.method == null ? null : new AnnotatedMethod(this.typeContext, this.method, this.annotations.asAnnotationMap(), (AnnotationMap[])null);
      }
   }
}
