package com.twitter.chill;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import org.apache.xbean.asm7.ClassReader;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.collection.mutable.Map;
import scala.collection.mutable.Stack;
import scala.collection.mutable.Map.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import sun.reflect.ReflectionFactory;

public final class ClosureCleaner$ {
   public static final ClosureCleaner$ MODULE$ = new ClosureCleaner$();
   private static final String OUTER = "$outer";
   private static final Map outerFields;
   private static final Map outerClassHier;
   private static final Map innerClasses;
   private static final Map accessedFieldsMap;

   static {
      outerFields = (Map).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      outerClassHier = (Map).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      innerClasses = (Map).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      accessedFieldsMap = (Map).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   public String OUTER() {
      return OUTER;
   }

   private Map outerFields() {
      return outerFields;
   }

   private Map outerClassHier() {
      return outerClassHier;
   }

   private Map innerClasses() {
      return innerClasses;
   }

   private Map accessedFieldsMap() {
      return accessedFieldsMap;
   }

   public byte[] serialize(final Object t) {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream out = new ObjectOutputStream(bos);

      byte[] var10000;
      try {
         out.writeObject(t);
         var10000 = bos.toByteArray();
      } finally {
         out.close();
         bos.close();
      }

      return var10000;
   }

   public Object deserialize(final byte[] bytes) {
      ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
      ObjectInputStream in = new ObjectInputStream(bis);

      Object var10000;
      try {
         var10000 = in.readObject();
      } finally {
         bis.close();
         in.close();
      }

      return var10000;
   }

   public Option outerFieldOf(final Class c) {
      return (Option)this.outerFields().getOrElseUpdate(c, () -> scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps((Object[])c.getDeclaredFields()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$outerFieldOf$2(x$1))));
   }

   private List getOuterClassesFn(final Class cls, final List hierarchy) {
      while(true) {
         Option var5 = this.outerFieldOf(cls);
         if (scala.None..MODULE$.equals(var5)) {
            return hierarchy;
         }

         if (!(var5 instanceof Some)) {
            throw new MatchError(var5);
         }

         Some var6 = (Some)var5;
         Field f = (Field)var6.value();
         Class next = f.getType();
         hierarchy = hierarchy.$colon$colon(next);
         cls = next;
      }
   }

   private List getOuterClassesFn$default$2() {
      return scala.package..MODULE$.Nil();
   }

   public List outerClassesOf(final Class cls) {
      return (List)this.outerClassHier().getOrElseUpdate(cls, () -> MODULE$.getOuterClassesFn(cls, MODULE$.getOuterClassesFn$default$2()));
   }

   public List getOutersOf(final Object obj, final List hierarchy) {
      while(true) {
         Option var5 = this.outerFieldOf(obj.getClass());
         if (scala.None..MODULE$.equals(var5)) {
            return hierarchy;
         }

         if (!(var5 instanceof Some)) {
            throw new MatchError(var5);
         }

         Some var6 = (Some)var5;
         Field f = (Field)var6.value();
         f.setAccessible(true);
         Object myOuter = f.get(obj);
         Class outerType = myOuter.getClass();
         Tuple2 var10 = new Tuple2(outerType, myOuter);
         hierarchy = hierarchy.$colon$colon(var10);
         obj = myOuter;
      }
   }

   public List getOutersOf$default$2() {
      return scala.package..MODULE$.Nil();
   }

   private Set getInnerClassesFn(final Class inCls) {
      scala.collection.mutable.Set seen = (scala.collection.mutable.Set)scala.collection.mutable.Set..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{inCls})));
      Stack stack = (Stack)scala.collection.mutable.Stack..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{inCls})));

      while(stack.nonEmpty()) {
         scala.collection.mutable.Set set = (scala.collection.mutable.Set)scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         AsmUtil$.MODULE$.classReader((Class)stack.pop()).foreach((cr) -> {
            $anonfun$getInnerClassesFn$1(set, cr);
            return BoxedUnit.UNIT;
         });
         set.$minus$minus(seen).foreach((cls) -> {
            seen.$plus$eq(cls);
            return stack.push(cls);
         });
      }

      return seen.$minus(inCls).toSet();
   }

   public Set innerClassesOf(final Class cls) {
      return (Set)this.innerClasses().getOrElseUpdate(cls, () -> MODULE$.getInnerClassesFn(cls));
   }

   private scala.collection.immutable.Map getAccessedFields(final Class cls) {
      Map accessedFields = (Map)this.outerClassesOf(cls).foldLeft(.MODULE$.apply(scala.collection.immutable.Nil..MODULE$), (m, clsx) -> (Map)m.$plus$eq(new Tuple2(clsx, scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$))));
      this.innerClassesOf(cls).$plus(cls).foreach((x$2) -> {
         $anonfun$getAccessedFields$2(accessedFields, x$2);
         return BoxedUnit.UNIT;
      });
      return accessedFields.iterator().map((x0$1) -> {
         if (x0$1 != null) {
            Class cls = (Class)x0$1._1();
            scala.collection.mutable.Set mset = (scala.collection.mutable.Set)x0$1._2();
            Set set = mset.toSet();
            Tuple2 var1 = new Tuple2(cls, toF$1(set, cls));
            return var1;
         } else {
            throw new MatchError(x0$1);
         }
      }).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public Set accessedFieldsOf(final Class cls) {
      Option var3 = this.accessedFieldsMap().get(cls);
      Set var2;
      if (var3 instanceof Some) {
         Some var4 = (Some)var3;
         Set s = (Set)var4.value();
         var2 = s;
      } else {
         if (!scala.None..MODULE$.equals(var3)) {
            throw new MatchError(var3);
         }

         scala.collection.immutable.Map af = this.getAccessedFields(cls);
         this.accessedFieldsMap().$plus$plus$eq(af);
         var2 = (Set)af.getOrElse(cls, () -> scala.Predef..MODULE$.Set().empty());
      }

      return var2;
   }

   public Object clean(final Object obj) {
      return scala.util.Try..MODULE$.apply(() -> MODULE$.deserialize(MODULE$.serialize((Serializable)obj))).getOrElse(() -> {
         Object newCleanedOuter = MODULE$.allocCleanedOuter(obj);
         MODULE$.setOuter(obj, newCleanedOuter);
         return obj;
      });
   }

   public void apply(final Object obj) {
      this.clean(obj);
   }

   public boolean isOuterField(final Field f) {
      boolean var3;
      label23: {
         String var10000 = f.getName();
         String var2 = this.OUTER();
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

   private Object allocCleanedOuter(final Object in) {
      this.accessedFieldsOf(in.getClass());
      return this.getOutersOf(in, this.getOutersOf$default$2()).foldLeft((Object)null, (prevOuter, clsData) -> {
         if (clsData != null) {
            Class thisOuterCls = (Class)clsData._1();
            Object realOuter = clsData._2();
            Tuple2 var2 = new Tuple2(thisOuterCls, realOuter);
            Class thisOuterCls = (Class)var2._1();
            Object realOuterx = var2._2();
            Object nextOuter = MODULE$.instantiateClass(thisOuterCls);
            Set af = MODULE$.accessedFieldsOf(thisOuterCls);
            af.foreach((x$4) -> {
               $anonfun$allocCleanedOuter$2(realOuterx, nextOuter, x$4);
               return BoxedUnit.UNIT;
            });
            Object parent = af.find((f) -> BoxesRunTime.boxToBoolean($anonfun$allocCleanedOuter$3(f))).map((x$5) -> prevOuter).orNull(scala..less.colon.less..MODULE$.refl());
            MODULE$.setOuter(nextOuter, parent);
            return nextOuter;
         } else {
            throw new MatchError(clsData);
         }
      });
   }

   private void setFromTo(final Field f, final Object old, final Object newv) {
      f.setAccessible(true);
      Object accessedValue = f.get(old);
      f.set(newv, accessedValue);
   }

   private void setOuter(final Object obj, final Object outer) {
      if (outer != null) {
         this.outerFieldOf(obj.getClass()).foreach((field) -> {
            $anonfun$setOuter$1(obj, outer, field);
            return BoxedUnit.UNIT;
         });
      }

   }

   public Object instantiateClass(final Class cls) {
      Constructor objectCtor = Object.class.getDeclaredConstructor();
      return ReflectionFactory.getReflectionFactory().newConstructorForSerialization(cls, objectCtor).newInstance();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$outerFieldOf$2(final Field x$1) {
      boolean var2;
      label23: {
         String var10000 = x$1.getName();
         String var1 = MODULE$.OUTER();
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
   public static final void $anonfun$getInnerClassesFn$1(final scala.collection.mutable.Set set$1, final ClassReader cr) {
      cr.accept(new InnerClosureFinder(set$1), 0);
   }

   // $FF: synthetic method
   public static final void $anonfun$getAccessedFields$3(final Map accessedFields$1, final ClassReader cr) {
      cr.accept(new FieldAccessFinder(accessedFields$1, FieldAccessFinder$.MODULE$.$lessinit$greater$default$2(), FieldAccessFinder$.MODULE$.$lessinit$greater$default$3()), 0);
   }

   // $FF: synthetic method
   public static final void $anonfun$getAccessedFields$2(final Map accessedFields$1, final Class x$2) {
      AsmUtil$.MODULE$.classReader(x$2).foreach((cr) -> {
         $anonfun$getAccessedFields$3(accessedFields$1, cr);
         return BoxedUnit.UNIT;
      });
   }

   private static final Set toF$1(final Set ss, final Class cls$3) {
      return (Set)ss.map((x$1) -> cls$3.getDeclaredField(x$1));
   }

   // $FF: synthetic method
   public static final void $anonfun$allocCleanedOuter$2(final Object realOuter$1, final Object nextOuter$1, final Field x$4) {
      MODULE$.setFromTo(x$4, realOuter$1, nextOuter$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$allocCleanedOuter$3(final Field f) {
      return MODULE$.isOuterField(f);
   }

   // $FF: synthetic method
   public static final void $anonfun$setOuter$1(final Object obj$2, final Object outer$1, final Field field) {
      field.setAccessible(true);
      field.set(obj$2, outer$1);
   }

   private ClosureCleaner$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
