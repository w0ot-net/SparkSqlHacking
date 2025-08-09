package com.fasterxml.jackson.module.scala.introspect;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.module.scala.util.ClassW$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.IterableOps;
import scala.collection.LinearSeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Nil.;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

public final class BeanIntrospector$ {
   public static final BeanIntrospector$ MODULE$ = new BeanIntrospector$();

   public BeanDescriptor apply(final Class cls) {
      Seq hierarchy = this.next$1(cls, .MODULE$);
      Regex privateRegex = scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("(.*)\\$\\$(.*)"));
      Seq fields = (Seq)hierarchy.flatMap((clsx) -> scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])clsx.getDeclaredFields()), (field) -> {
            boolean isScalaObject = ClassW$.MODULE$.apply(() -> clsx).isScalaObject() || isScalaCaseObject$1(clsx);
            String name = maybePrivateName$1(field, privateRegex);
            return new Tuple3(field, BoxesRunTime.boxToBoolean(isScalaObject), name);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class))), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$apply$19(x$2))).withFilter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$apply$20(x$3))).map((x$5) -> {
            if (x$5 != null) {
               String name = (String)x$5._3();
               Option beanGetter = findBeanGetter$1(clsx, name);
               Option beanSetter = findBeanSetter$1(clsx, name);
               return new Tuple3(x$5, beanGetter, beanSetter);
            } else {
               throw new MatchError(x$5);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class))), (x$6) -> {
            if (x$6 != null) {
               Tuple3 var6 = (Tuple3)x$6._1();
               Option beanGetter = (Option)x$6._2();
               Option beanSetter = (Option)x$6._3();
               if (var6 != null) {
                  Field field = (Field)var6._1();
                  String name = (String)var6._3();
                  return new PropertyDescriptor(name, this.findConstructorParam$1((Class)hierarchy.head(), name), new Some(field), findGetter$1(clsx, name), findSetter$1(clsx, name), beanGetter, beanSetter);
               }
            }

            throw new MatchError(x$6);
         }, scala.reflect.ClassTag..MODULE$.apply(PropertyDescriptor.class))));
      Seq methods = (Seq)hierarchy.flatMap((clsx) -> scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])clsx.getDeclaredMethods()), (getter) -> {
            String name = scala.reflect.NameTransformer..MODULE$.decode(getter.getName());
            return new Tuple2(getter, name);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), (x$8) -> BoxesRunTime.boxToBoolean($anonfun$apply$25(x$8))).withFilter((x$9) -> BoxesRunTime.boxToBoolean($anonfun$apply$26(clsx, x$9))).withFilter((x$10) -> BoxesRunTime.boxToBoolean($anonfun$apply$27(x$10))).withFilter((x$11) -> BoxesRunTime.boxToBoolean($anonfun$apply$28(fields, x$11))).map((x$13) -> {
            if (x$13 != null) {
               Method getter = (Method)x$13._1();
               String name = (String)x$13._2();
               Option getterProperty = scala.Option..MODULE$.apply(getter.getAnnotation(JsonProperty.class));
               Option setter = findSetter$1(clsx, name);
               return new Tuple3(x$13, getterProperty, setter);
            } else {
               throw new MatchError(x$13);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class))), (x$14) -> BoxesRunTime.boxToBoolean($anonfun$apply$31(x$14))).map((x$16) -> {
            if (x$16 != null) {
               Tuple2 var4 = (Tuple2)x$16._1();
               if (var4 != null) {
                  String name = (String)var4._2();
                  Option beanGetter = findBeanGetter$1(clsx, name);
                  Option beanSetter = findBeanSetter$1(clsx, name);
                  return new Tuple3(x$16, beanGetter, beanSetter);
               }
            }

            throw new MatchError(x$16);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class))), (x$17) -> {
            if (x$17 != null) {
               Tuple3 var3 = (Tuple3)x$17._1();
               Option beanGetter = (Option)x$17._2();
               Option beanSetter = (Option)x$17._3();
               if (var3 != null) {
                  Tuple2 var6 = (Tuple2)var3._1();
                  Option setter = (Option)var3._3();
                  if (var6 != null) {
                     Method getter = (Method)var6._1();
                     String name = (String)var6._2();
                     return new PropertyDescriptor(name, scala.None..MODULE$, scala.None..MODULE$, new Some(getter), setter, beanGetter, beanSetter);
                  }
               }
            }

            throw new MatchError(x$17);
         }, scala.reflect.ClassTag..MODULE$.apply(PropertyDescriptor.class))));
      String lazySuffix = "$lzy1";
      Seq lazyValMethods = (Seq)hierarchy.flatMap((hcls) -> {
         Set fieldNames = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])hcls.getDeclaredFields()), (x$18) -> x$18.getName(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (x$19) -> BoxesRunTime.boxToBoolean($anonfun$apply$36(lazySuffix, x$19)))), (s) -> s.substring(0, s.length() - lazySuffix.length()), scala.reflect.ClassTag..MODULE$.apply(String.class))).toSet();
         return scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])hcls.getDeclaredMethods()), (getter) -> {
            String name = scala.reflect.NameTransformer..MODULE$.decode(getter.getName());
            return new Tuple2(getter, name);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), (x$20) -> BoxesRunTime.boxToBoolean($anonfun$apply$39(x$20))).withFilter((x$21) -> BoxesRunTime.boxToBoolean($anonfun$apply$40(fieldNames, x$21))).withFilter((x$22) -> BoxesRunTime.boxToBoolean($anonfun$apply$41(x$22))).map((x$24) -> {
            if (x$24 != null) {
               String name = (String)x$24._2();
               Option setter = findSetter$1(cls, name);
               Option beanGetter = findBeanGetter$1(cls, name);
               Option beanSetter = findBeanSetter$1(cls, name);
               return new Tuple4(x$24, setter, beanGetter, beanSetter);
            } else {
               throw new MatchError(x$24);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple4.class))), (x$25) -> {
            if (x$25 != null) {
               Tuple2 var3 = (Tuple2)x$25._1();
               Option setter = (Option)x$25._2();
               Option beanGetter = (Option)x$25._3();
               Option beanSetter = (Option)x$25._4();
               if (var3 != null) {
                  Method getter = (Method)var3._1();
                  String name = (String)var3._2();
                  return new PropertyDescriptor(name, scala.None..MODULE$, scala.None..MODULE$, new Some(getter), setter, beanGetter, beanSetter);
               }
            }

            throw new MatchError(x$25);
         }, scala.reflect.ClassTag..MODULE$.apply(PropertyDescriptor.class)));
      });
      return new BeanDescriptor(cls, (Seq)((IterableOps)fields.$plus$plus(methods)).$plus$plus(lazyValMethods));
   }

   private Seq getCtorParams(final Constructor ctor) {
      IndexedSeq names = JavaParameterIntrospector$.MODULE$.getCtorParamNames(ctor);
      return (Seq)names.map((name0) -> scala.reflect.NameTransformer..MODULE$.decode(name0));
   }

   private final Option findConstructorParam$1(final Class c, final String name) {
      while(c != null) {
         Class var4 = Object.class;
         if (c == null) {
            if (var4 == null) {
               break;
            }
         } else if (c.equals(var4)) {
            break;
         }

         Option primaryConstructor = scala.collection.ArrayOps..MODULE$.headOption$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])c.getConstructors()), (cx) -> new Tuple2(BoxesRunTime.boxToInteger(-cx.getParameterCount()), cx.toString()), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.String..MODULE$))));
         IndexedSeq debugCtorParamNames = (IndexedSeq)scala.Option..MODULE$.option2Iterable(primaryConstructor).toIndexedSeq().flatMap((ctor) -> MODULE$.getCtorParams(ctor));
         int index = debugCtorParamNames.indexOf(name);
         Option companion = findCompanionObject$1(c);
         if (index >= 0) {
            return new Some(new ConstructorParameter((Constructor)primaryConstructor.get(), index, findConstructorDefaultValue$1(companion, index)));
         }

         Class var10000 = c.getSuperclass();
         name = name;
         c = var10000;
      }

      return scala.None..MODULE$;
   }

   private static final Option findConstructorDefaultValue$1(final Option maybeCompanion, final int index) {
      String methodName = (new StringBuilder(26)).append("$lessinit$greater$default$").append(index + 1).toString();
      return maybeCompanion.flatMap((companion) -> scala.collection.ArrayOps..MODULE$.collectFirst$extension(scala.Predef..MODULE$.refArrayOps((Object[])companion.getClass().getMethods()), new Serializable(methodName, companion) {
            private static final long serialVersionUID = 0L;
            private final String methodName$1;
            private final Object companion$1;

            public final Object applyOrElse(final Method x1, final Function1 default) {
               String var10000 = x1.getName();
               String var5 = this.methodName$1;
               if (var10000 == null) {
                  if (var5 != null) {
                     return default.apply(x1);
                  }
               } else if (!var10000.equals(var5)) {
                  return default.apply(x1);
               }

               if (x1.getParameterTypes().length == 0) {
                  return (Function0)() -> x1.invoke(this.companion$1);
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Method x1) {
               String var10000 = x1.getName();
               String var4 = this.methodName$1;
               if (var10000 == null) {
                  if (var4 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var4)) {
                  return false;
               }

               if (x1.getParameterTypes().length == 0) {
                  return true;
               } else {
                  return false;
               }
            }

            public {
               this.methodName$1 = methodName$1;
               this.companion$1 = companion$1;
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         }));
   }

   private static final Option findCompanionObject$1(final Class c) {
      Object var10000;
      try {
         var10000 = new Some(c.getClassLoader().loadClass((new StringBuilder(1)).append(c.getName()).append("$").toString()).getDeclaredField("MODULE$").get((Object)null));
      } catch (Exception var2) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   private final List next$1(final Class c, final List acc) {
      while(c != null) {
         Class var4 = Object.class;
         if (c == null) {
            if (var4 == null) {
               break;
            }
         } else if (c.equals(var4)) {
            break;
         }

         Class var10000 = c.getSuperclass();
         acc = (List)acc.$colon$plus(c);
         c = var10000;
      }

      return acc;
   }

   private static final LazyList listMethods$1(final Class cls) {
      if (cls == null) {
         return LazyListSupport$.MODULE$.empty();
      } else {
         Class var3 = Object.class;
         if (cls == null) {
            if (var3 == null) {
               return LazyListSupport$.MODULE$.empty();
            }
         } else if (cls.equals(var3)) {
            return LazyListSupport$.MODULE$.empty();
         }

         LazyList var4 = LazyListSupport$.MODULE$.fromArray(cls.getDeclaredMethods());
         return scala.collection.immutable.LazyList.Deferrer..MODULE$.$hash$colon$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> listMethods$1(cls.getSuperclass())), var4);
      }
   }

   private static final boolean isNotSyntheticOrBridge$1(final Method m) {
      return !m.isBridge() && !m.isSynthetic();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$5(final Method m) {
      return isNotSyntheticOrBridge$1(m);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$6(final String name$1, final Method m) {
      boolean var3;
      label23: {
         String var10000 = scala.reflect.NameTransformer..MODULE$.decode(m.getName());
         if (var10000 == null) {
            if (name$1 == null) {
               break label23;
            }
         } else if (var10000.equals(name$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   private static final Seq findMethod$1(final Class cls, final String name) {
      return listMethods$1(cls).filter((m) -> BoxesRunTime.boxToBoolean($anonfun$apply$5(m))).filter((m) -> BoxesRunTime.boxToBoolean($anonfun$apply$6(name, m)));
   }

   private static final LazyList listFields$1(final Class cls) {
      if (cls == null) {
         return LazyListSupport$.MODULE$.empty();
      } else {
         Class var3 = Object.class;
         if (cls == null) {
            if (var3 == null) {
               return LazyListSupport$.MODULE$.empty();
            }
         } else if (cls.equals(var3)) {
            return LazyListSupport$.MODULE$.empty();
         }

         LazyList var4 = LazyListSupport$.MODULE$.fromArray(cls.getDeclaredFields());
         return scala.collection.immutable.LazyList.Deferrer..MODULE$.$hash$colon$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> listFields$1(cls.getSuperclass())), var4);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$8(final Field x$1) {
      return !x$1.isSynthetic();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$9(final String fieldName$1, final Field f) {
      boolean var3;
      label23: {
         String var10000 = scala.reflect.NameTransformer..MODULE$.decode(f.getName());
         if (var10000 == null) {
            if (fieldName$1 == null) {
               break label23;
            }
         } else if (var10000.equals(fieldName$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   private static final Option findField$1(final Class cls, final String fieldName) {
      return scala.Option..MODULE$.option2Iterable(listFields$1(cls).filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$8(x$1))).find((f) -> BoxesRunTime.boxToBoolean($anonfun$apply$9(fieldName, f)))).headOption();
   }

   private static final boolean isAcceptableField$1(final Field field) {
      int modifiers = field.getModifiers();
      return !Modifier.isStatic(modifiers) && !Modifier.isVolatile(modifiers) && !Modifier.isTransient(modifiers) && !field.isSynthetic();
   }

   private static final boolean isAcceptableMethod$1(final Method method) {
      return !Modifier.isStatic(method.getModifiers()) && isNotSyntheticOrBridge$1(method);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$10(final Method m) {
      return isAcceptableGetter$1(m);
   }

   private static final Option findGetter$1(final Class cls, final String propertyName) {
      return findMethod$1(cls, propertyName).find((m) -> BoxesRunTime.boxToBoolean($anonfun$apply$10(m)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$11(final Method m) {
      return isAcceptableGetter$1(m);
   }

   private static final Option findBeanGetter$1(final Class cls, final String propertyName) {
      return findMethod$1(cls, (new StringBuilder(3)).append("get").append(scala.collection.StringOps..MODULE$.capitalize$extension(scala.Predef..MODULE$.augmentString(propertyName))).toString()).find((m) -> BoxesRunTime.boxToBoolean($anonfun$apply$11(m)));
   }

   private static final boolean isAcceptableGetter$1(final Method m) {
      boolean var2;
      label27: {
         if (isAcceptableMethod$1(m) && m.getParameterTypes().length == 0) {
            Class var10000 = m.getReturnType();
            Class var1 = Void.TYPE;
            if (var10000 == null) {
               if (var1 != null) {
                  break label27;
               }
            } else if (!var10000.equals(var1)) {
               break label27;
            }
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$12(final Method m) {
      return isAcceptableSetter$1(m);
   }

   private static final Option findSetter$1(final Class cls, final String propertyName) {
      return findMethod$1(cls, (new StringBuilder(2)).append(propertyName).append("_=").toString()).find((m) -> BoxesRunTime.boxToBoolean($anonfun$apply$12(m)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$13(final Method m) {
      return isAcceptableSetter$1(m);
   }

   private static final Option findBeanSetter$1(final Class cls, final String propertyName) {
      return findMethod$1(cls, (new StringBuilder(3)).append("set").append(scala.collection.StringOps..MODULE$.capitalize$extension(scala.Predef..MODULE$.augmentString(propertyName))).toString()).find((m) -> BoxesRunTime.boxToBoolean($anonfun$apply$13(m)));
   }

   private static final boolean isAcceptableSetter$1(final Method m) {
      boolean var2;
      label27: {
         if (isAcceptableMethod$1(m) && m.getParameterTypes().length == 1) {
            Class var10000 = m.getReturnType();
            Class var1 = Void.TYPE;
            if (var10000 == null) {
               if (var1 == null) {
                  break label27;
               }
            } else if (var10000.equals(var1)) {
               break label27;
            }
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   private static final String maybePrivateName$1(final Field field, final Regex privateRegex$1) {
      String definedName = scala.reflect.NameTransformer..MODULE$.decode(field.getName());

      Object var10000;
      try {
         var10000 = scala.Option..MODULE$.apply(field.getDeclaringClass().getCanonicalName());
      } catch (InternalError var5) {
         var10000 = scala.None..MODULE$;
      }

      Option canonicalName = (Option)var10000;
      return (String)canonicalName.flatMap((cn) -> {
         String PrivateName = cn.replace('.', '$');
         if (definedName != null) {
            Option var6 = privateRegex$1.unapplySeq(definedName);
            if (!var6.isEmpty() && var6.get() != null && ((List)var6.get()).lengthCompare(2) == 0) {
               String var7 = (String)((LinearSeqOps)var6.get()).apply(0);
               String rest = (String)((LinearSeqOps)var6.get()).apply(1);
               if (PrivateName == null) {
                  if (var7 == null) {
                     return new Some(rest);
                  }
               } else if (PrivateName.equals(var7)) {
                  return new Some(rest);
               }
            }
         }

         return scala.None..MODULE$;
      }).getOrElse(() -> definedName);
   }

   private static final boolean isScalaCaseObject$1(final Class cls) {
      return ClassW$.MODULE$.productClass().isAssignableFrom(cls) && cls.getName().endsWith("$");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$19(final Tuple3 x$2) {
      if (x$2 != null) {
         String name = (String)x$2._3();
         return !scala.collection.StringOps..MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(name), '$');
      } else {
         throw new MatchError(x$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$20(final Tuple3 x$3) {
      if (x$3 == null) {
         throw new MatchError(x$3);
      } else {
         Field field = (Field)x$3._1();
         boolean isScalaObject = BoxesRunTime.unboxToBoolean(x$3._2());
         return isScalaObject || isAcceptableField$1(field);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$25(final Tuple2 x$8) {
      if (x$8 != null) {
         Method getter = (Method)x$8._1();
         return isAcceptableGetter$1(getter);
      } else {
         throw new MatchError(x$8);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$26(final Class cls$2, final Tuple2 x$9) {
      if (x$9 != null) {
         String name = (String)x$9._2();
         return findField$1(cls$2, name).isEmpty();
      } else {
         throw new MatchError(x$9);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$27(final Tuple2 x$10) {
      if (x$10 != null) {
         String name = (String)x$10._2();
         return !scala.collection.StringOps..MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(name), '$');
      } else {
         throw new MatchError(x$10);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$29(final String name$2, final PropertyDescriptor x$7) {
      boolean var3;
      label23: {
         String var10000 = x$7.name();
         if (var10000 == null) {
            if (name$2 == null) {
               break label23;
            }
         } else if (var10000.equals(name$2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$28(final Seq fields$1, final Tuple2 x$11) {
      if (x$11 != null) {
         String name = (String)x$11._2();
         return !fields$1.exists((x$7) -> BoxesRunTime.boxToBoolean($anonfun$apply$29(name, x$7)));
      } else {
         throw new MatchError(x$11);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$31(final Tuple3 x$14) {
      if (x$14 != null) {
         Tuple2 var3 = (Tuple2)x$14._1();
         Option getterProperty = (Option)x$14._2();
         Option setter = (Option)x$14._3();
         if (var3 != null) {
            boolean var7;
            if (!setter.isDefined()) {
               label39: {
                  if (getterProperty.isDefined()) {
                     String var10000 = ((JsonProperty)getterProperty.get()).value();
                     String var6 = "";
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label39;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label39;
                     }
                  }

                  var7 = false;
                  return var7;
               }
            }

            var7 = true;
            return var7;
         }
      }

      throw new MatchError(x$14);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$36(final String lazySuffix$1, final String x$19) {
      return x$19.endsWith(lazySuffix$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$39(final Tuple2 x$20) {
      if (x$20 != null) {
         String name = (String)x$20._2();
         return !scala.collection.StringOps..MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(name), '$');
      } else {
         throw new MatchError(x$20);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$40(final Set fieldNames$1, final Tuple2 x$21) {
      if (x$21 != null) {
         String name = (String)x$21._2();
         return fieldNames$1.contains(name);
      } else {
         throw new MatchError(x$21);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$41(final Tuple2 x$22) {
      if (x$22 != null) {
         Method getter = (Method)x$22._1();
         return isAcceptableGetter$1(getter);
      } else {
         throw new MatchError(x$22);
      }
   }

   private BeanIntrospector$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
