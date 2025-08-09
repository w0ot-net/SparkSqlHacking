package com.fasterxml.jackson.module.scala.introspect;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.module.scala.JacksonModule$;
import com.fasterxml.jackson.module.scala.util.Implicits$;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala..less.colon.less.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

public final class ScalaAnnotationIntrospector$ extends NopAnnotationIntrospector implements ValueInstantiators {
   public static final ScalaAnnotationIntrospector$ MODULE$ = new ScalaAnnotationIntrospector$();

   public Option propertyFor(final Annotated a) {
      if (a instanceof AnnotatedParameter) {
         AnnotatedParameter var4 = (AnnotatedParameter)a;
         return this._descriptorFor(var4.getDeclaringClass()).flatMap((d) -> d.properties().find((p) -> BoxesRunTime.boxToBoolean($anonfun$propertyFor$2(var4, p))));
      } else if (a instanceof AnnotatedMember) {
         AnnotatedMember var5 = (AnnotatedMember)a;
         return this._descriptorFor(var5.getDeclaringClass()).flatMap((d) -> d.properties().find((p) -> BoxesRunTime.boxToBoolean($anonfun$propertyFor$5(a, p))));
      } else {
         throw new MatchError(a);
      }
   }

   public String findImplicitPropertyName(final AnnotatedMember member) {
      if (member instanceof AnnotatedField) {
         AnnotatedField var4 = (AnnotatedField)member;
         return (String)this.fieldName(var4).orNull(.MODULE$.refl());
      } else if (member instanceof AnnotatedMethod) {
         AnnotatedMethod var5 = (AnnotatedMethod)member;
         return (String)this.methodName(var5).orNull(.MODULE$.refl());
      } else if (member instanceof AnnotatedParameter) {
         AnnotatedParameter var6 = (AnnotatedParameter)member;
         return (String)this.paramName(var6).orNull(.MODULE$.refl());
      } else {
         return (String)scala.None..MODULE$.orNull(.MODULE$.refl());
      }
   }

   public boolean hasIgnoreMarker(final AnnotatedMember m) {
      boolean var10000;
      label22: {
         String name = m.getName();
         String var3 = "0bitmap$1";
         if (name == null) {
            if (var3 == null) {
               break label22;
            }
         } else if (name.equals(var3)) {
            break label22;
         }

         if (!name.endsWith("$lzy1") && !name.contains("$default$") && !super.hasIgnoreMarker(m)) {
            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public boolean hasCreatorAnnotation(final Annotated a) {
      PartialFunction jsonCreators = new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Annotation x1, final Function1 default) {
            if (x1 instanceof JsonCreator) {
               JsonCreator var5 = (JsonCreator)x1;
               return var5;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Annotation x1) {
            return x1 instanceof JsonCreator;
         }
      };
      boolean var4 = false;
      AnnotatedConstructor var5 = null;
      if (a instanceof AnnotatedConstructor) {
         var4 = true;
         var5 = (AnnotatedConstructor)a;
         if (!this.isScala(var5)) {
            return false;
         }
      }

      if (!var4) {
         return false;
      } else {
         boolean annotatedFound = BoxesRunTime.unboxToBoolean(this._descriptorFor(var5.getDeclaringClass()).map((d) -> BoxesRunTime.boxToBoolean($anonfun$hasCreatorAnnotation$1(var5, d))).getOrElse((JFunction0.mcZ.sp)() -> false));
         Option annotatedConstructor = scala.collection.ArrayOps..MODULE$.headOption$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])var5.getDeclaringClass().getDeclaredConstructors()), (constructor) -> (Constructor[])scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.collect$extension(scala.Predef..MODULE$.refArrayOps((Object[])constructor.getAnnotations()), jsonCreators, scala.reflect.ClassTag..MODULE$.apply(JsonCreator.class))), (check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$hasCreatorAnnotation$6(check$ifrefutable$1))).withFilter((annotation) -> BoxesRunTime.boxToBoolean($anonfun$hasCreatorAnnotation$7(annotation))).map((annotation) -> constructor, scala.reflect.ClassTag..MODULE$.apply(Constructor.class)), (xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs), scala.reflect.ClassTag..MODULE$.apply(Constructor.class))));
         boolean isDisabled = scala.collection.ArrayOps..MODULE$.exists$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.collect$extension(scala.Predef..MODULE$.refArrayOps((Object[])var5.getAnnotated().getAnnotations()), jsonCreators, scala.reflect.ClassTag..MODULE$.apply(JsonCreator.class))), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$hasCreatorAnnotation$10(x$4)));
         return annotatedFound && annotatedConstructor.forall((x$5) -> BoxesRunTime.boxToBoolean($anonfun$hasCreatorAnnotation$11(var5, x$5))) && !isDisabled;
      }
   }

   public JsonCreator.Mode findCreatorAnnotation(final MapperConfig config, final Annotated a) {
      if (this.hasCreatorAnnotation(a)) {
         Option var4 = scala.Option..MODULE$.apply(this.findCreatorBinding(a));
         if (var4 instanceof Some) {
            Some var5 = (Some)var4;
            JsonCreator.Mode mode = (JsonCreator.Mode)var5.value();
            return mode;
         } else {
            return Mode.DEFAULT;
         }
      } else {
         return (JsonCreator.Mode)scala.None..MODULE$.orNull(.MODULE$.refl());
      }
   }

   public JsonCreator.Mode findCreatorBinding(final Annotated a) {
      Option var3 = scala.Option..MODULE$.apply(this._findAnnotation(a, JsonCreator.class));
      if (var3 instanceof Some) {
         Some var4 = (Some)var3;
         JsonCreator ann = (JsonCreator)var4.value();
         return ann.mode();
      } else {
         return this.isScala(a) && this.hasCreatorAnnotation(a) ? Mode.PROPERTIES : (JsonCreator.Mode)scala.None..MODULE$.orNull(.MODULE$.refl());
      }
   }

   public Version version() {
      return JacksonModule$.MODULE$.version();
   }

   public CreatorProperty com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospector$$applyOverrides(final CreatorProperty creator, final String propertyName, final Map overrides) {
      Option var5 = overrides.get(propertyName);
      if (var5 instanceof Some) {
         Some var6 = (Some)var5;
         ClassHolder refHolder = (ClassHolder)var6.value();
         return new WrappedCreatorProperty(creator, refHolder);
      } else {
         return creator;
      }
   }

   public ValueInstantiator findValueInstantiator(final DeserializationConfig config, final BeanDescription beanDesc, final ValueInstantiator defaultInstantiator) {
      return this.isMaybeScalaBeanType(beanDesc.getBeanClass()) ? (ValueInstantiator)this._descriptorFor(beanDesc.getBeanClass()).map((descriptor) -> {
         if (!ScalaAnnotationIntrospectorModule$.MODULE$.overrideMap().contains(beanDesc.getBeanClass().getName()) && !descriptor.properties().exists((x$9) -> BoxesRunTime.boxToBoolean($anonfun$findValueInstantiator$2(x$9)))) {
            return defaultInstantiator;
         } else if (defaultInstantiator instanceof StdValueInstantiator) {
            StdValueInstantiator var6 = (StdValueInstantiator)defaultInstantiator;
            return new ScalaAnnotationIntrospector.ScalaValueInstantiator(ScalaAnnotationIntrospectorModule$.MODULE$, var6, config, descriptor);
         } else {
            throw new IllegalArgumentException((new StringBuilder(45)).append("Cannot customise a non StdValueInstantiator: ").append(defaultInstantiator.getClass()).toString());
         }
      }).getOrElse(() -> defaultInstantiator) : defaultInstantiator;
   }

   private Option _descriptorFor(final Class clz) {
      String key = clz.getName();
      Option var6 = scala.Option..MODULE$.apply(ScalaAnnotationIntrospectorModule$.MODULE$._scalaTypeCache().get(key));
      boolean var10000;
      if (var6 instanceof Some) {
         Some var7 = (Some)var6;
         boolean result = BoxesRunTime.unboxToBoolean(var7.value());
         var10000 = result;
      } else {
         boolean result = Implicits$.MODULE$.mkClassW(() -> clz).extendsScalaClass(ScalaAnnotationIntrospectorModule$.MODULE$.shouldSupportScala3Classes()) || Implicits$.MODULE$.mkClassW(() -> clz).hasSignature();
         ScalaAnnotationIntrospectorModule$.MODULE$._scalaTypeCache().put(key, BoxesRunTime.boxToBoolean(result));
         var10000 = result;
      }

      boolean isScala = var10000;
      if (isScala) {
         Option var10 = scala.Option..MODULE$.apply(ScalaAnnotationIntrospectorModule$.MODULE$._descriptorCache().get(key));
         if (var10 instanceof Some) {
            Some var11 = (Some)var10;
            BeanDescriptor result = (BeanDescriptor)var11.value();
            return new Some(result);
         } else {
            BeanDescriptor introspector = BeanIntrospector$.MODULE$.apply(clz);
            ScalaAnnotationIntrospectorModule$.MODULE$._descriptorCache().put(key, introspector);
            return new Some(introspector);
         }
      } else {
         return scala.None..MODULE$;
      }
   }

   private Option fieldName(final AnnotatedField af) {
      return this._descriptorFor(af.getDeclaringClass()).flatMap((d) -> d.properties().find((p) -> BoxesRunTime.boxToBoolean($anonfun$fieldName$2(af, p))).map((x$12) -> x$12.name()));
   }

   private Option methodName(final AnnotatedMethod am) {
      return this._descriptorFor(am.getDeclaringClass()).flatMap((d) -> {
         Option getterSetter = d.properties().find((p) -> BoxesRunTime.boxToBoolean($anonfun$methodName$2(am, p))).map((x$14) -> x$14.name());
         if (getterSetter instanceof Some) {
            Some var5 = (Some)getterSetter;
            String s = (String)var5.value();
            return new Some(s);
         } else {
            return d.properties().find((p) -> BoxesRunTime.boxToBoolean($anonfun$methodName$5(am, p))).map((x$15) -> x$15.name());
         }
      });
   }

   private Option paramName(final AnnotatedParameter ap) {
      return this._descriptorFor(ap.getDeclaringClass()).flatMap((d) -> d.properties().find((p) -> BoxesRunTime.boxToBoolean($anonfun$paramName$2(ap, p))).map((x$16) -> x$16.name()));
   }

   private boolean isScalaPackage(final Option pkg) {
      return pkg.exists((x$17) -> BoxesRunTime.boxToBoolean($anonfun$isScalaPackage$1(x$17)));
   }

   public boolean isMaybeScalaBeanType(final Class cls) {
      String key = cls.getName();
      Option var5 = scala.Option..MODULE$.apply(ScalaAnnotationIntrospectorModule$.MODULE$._scalaTypeCache().get(key));
      boolean var10000;
      if (var5 instanceof Some) {
         Some var6 = (Some)var5;
         boolean flag = BoxesRunTime.unboxToBoolean(var6.value());
         var10000 = flag;
      } else {
         boolean flag = Implicits$.MODULE$.mkClassW(() -> cls).extendsScalaClass(ScalaAnnotationIntrospectorModule$.MODULE$.shouldSupportScala3Classes()) || Implicits$.MODULE$.mkClassW(() -> cls).hasSignature();
         ScalaAnnotationIntrospectorModule$.MODULE$._scalaTypeCache().put(key, BoxesRunTime.boxToBoolean(flag));
         var10000 = flag;
      }

      boolean flag = var10000;
      return flag && !this.isScalaPackage(scala.Option..MODULE$.apply(cls.getPackage()));
   }

   private boolean isScala(final Annotated a) {
      if (a instanceof AnnotatedClass) {
         AnnotatedClass var4 = (AnnotatedClass)a;
         return this.isMaybeScalaBeanType(var4.getAnnotated());
      } else if (a instanceof AnnotatedMember) {
         AnnotatedMember var5 = (AnnotatedMember)a;
         return this.isMaybeScalaBeanType(var5.getDeclaringClass());
      } else {
         throw new MatchError(a);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ScalaAnnotationIntrospector$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$propertyFor$3(final AnnotatedParameter x2$1, final ConstructorParameter cp) {
      boolean var3;
      label18: {
         Constructor var10000 = cp.constructor();
         AnnotatedElement var2 = x2$1.getOwner().getAnnotated();
         if (var10000 == null) {
            if (var2 != null) {
               break label18;
            }
         } else if (!var10000.equals(var2)) {
            break label18;
         }

         if (cp.index() == x2$1.getIndex()) {
            var3 = true;
            return var3;
         }
      }

      var3 = false;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$propertyFor$2(final AnnotatedParameter x2$1, final PropertyDescriptor p) {
      return p.param().exists((cp) -> BoxesRunTime.boxToBoolean($anonfun$propertyFor$3(x2$1, cp)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$propertyFor$6(final Annotated a$1, final Object x$1) {
      boolean var10000;
      label23: {
         AnnotatedElement var2 = a$1.getAnnotated();
         if (x$1 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (x$1.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$propertyFor$5(final Annotated a$1, final PropertyDescriptor p) {
      return ((IterableOnceOps)((IterableOps)((IterableOps)((IterableOps)((IterableOps)scala.Option..MODULE$.option2Iterable(p.field()).$plus$plus(p.getter())).$plus$plus(p.setter())).$plus$plus(p.param())).$plus$plus(p.beanGetter())).$plus$plus(p.beanSetter())).exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$propertyFor$6(a$1, x$1)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasCreatorAnnotation$3(final AnnotatedConstructor x2$2, final ConstructorParameter x$3) {
      boolean var3;
      label23: {
         Constructor var10000 = x$3.constructor();
         Constructor var2 = x2$2.getAnnotated();
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasCreatorAnnotation$1(final AnnotatedConstructor x2$2, final BeanDescriptor d) {
      return ((IterableOnceOps)d.properties().flatMap((x$2) -> x$2.param())).exists((x$3) -> BoxesRunTime.boxToBoolean($anonfun$hasCreatorAnnotation$3(x2$2, x$3)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasCreatorAnnotation$6(final JsonCreator check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasCreatorAnnotation$7(final JsonCreator annotation) {
      boolean var2;
      label23: {
         JsonCreator.Mode var10000 = annotation.mode();
         JsonCreator.Mode var1 = Mode.DISABLED;
         if (var10000 == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasCreatorAnnotation$10(final JsonCreator x$4) {
      boolean var2;
      label23: {
         JsonCreator.Mode var10000 = x$4.mode();
         JsonCreator.Mode var1 = Mode.DISABLED;
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasCreatorAnnotation$11(final AnnotatedConstructor x2$2, final Constructor x$5) {
      boolean var10000;
      label23: {
         Constructor var2 = x2$2.getAnnotated();
         if (x$5 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (x$5.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findValueInstantiator$3(final ConstructorParameter x$10) {
      return x$10.defaultValue().isDefined();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findValueInstantiator$2(final PropertyDescriptor x$9) {
      return x$9.param().exists((x$10) -> BoxesRunTime.boxToBoolean($anonfun$findValueInstantiator$3(x$10)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fieldName$3(final AnnotatedField af$1, final Field x$11) {
      boolean var10000;
      label23: {
         Field var2 = af$1.getAnnotated();
         if (x$11 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (x$11.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fieldName$2(final AnnotatedField af$1, final PropertyDescriptor p) {
      return p.field().exists((x$11) -> BoxesRunTime.boxToBoolean($anonfun$fieldName$3(af$1, x$11)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$methodName$3(final AnnotatedMethod am$1, final Method x$13) {
      boolean var10000;
      label23: {
         Method var2 = am$1.getAnnotated();
         if (x$13 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (x$13.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$methodName$2(final AnnotatedMethod am$1, final PropertyDescriptor p) {
      return ((IterableOnceOps)scala.Option..MODULE$.option2Iterable(p.getter()).$plus$plus(p.setter())).exists((x$13) -> BoxesRunTime.boxToBoolean($anonfun$methodName$3(am$1, x$13)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$methodName$5(final AnnotatedMethod am$1, final PropertyDescriptor p) {
      boolean var3;
      label23: {
         String var10000 = p.name();
         String var2 = am$1.getName();
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$paramName$3(final AnnotatedParameter ap$1, final ConstructorParameter cp) {
      boolean var3;
      label18: {
         Constructor var10000 = cp.constructor();
         AnnotatedElement var2 = ap$1.getOwner().getAnnotated();
         if (var10000 == null) {
            if (var2 != null) {
               break label18;
            }
         } else if (!var10000.equals(var2)) {
            break label18;
         }

         if (cp.index() == ap$1.getIndex()) {
            var3 = true;
            return var3;
         }
      }

      var3 = false;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$paramName$2(final AnnotatedParameter ap$1, final PropertyDescriptor p) {
      return p.param().exists((cp) -> BoxesRunTime.boxToBoolean($anonfun$paramName$3(ap$1, cp)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isScalaPackage$1(final Package x$17) {
      return x$17.getName().startsWith("scala.");
   }

   private ScalaAnnotationIntrospector$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
