package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.package.;

public final class ReflectionUtils$ {
   public static final ReflectionUtils$ MODULE$ = new ReflectionUtils$();

   public void setSuperField(final Object obj, final String fieldName, final Object fieldValue) {
      this.setAncestorField(obj, 1, fieldName, fieldValue);
   }

   public void setAncestorField(final Object obj, final int level, final String fieldName, final Object fieldValue) {
      Class ancestor = (Class).MODULE$.Iterator().iterate(obj.getClass(), (x$1) -> x$1.getSuperclass()).drop(level).next();
      Field field = ancestor.getDeclaredField(fieldName);
      field.setAccessible(true);
      field.set(obj, fieldValue);
   }

   public Object getSuperField(final Object obj, final String fieldName) {
      return this.getAncestorField(obj, 1, fieldName);
   }

   public Object getAncestorField(final Object clazz, final int level, final String fieldName) {
      Class ancestor = (Class).MODULE$.Iterator().iterate(clazz.getClass(), (x$2) -> x$2.getSuperclass()).drop(level).next();
      Field field = ancestor.getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.get(clazz);
   }

   public Object invokeStatic(final Class clazz, final String methodName, final Seq args) {
      return this.invoke(clazz, (Object)null, methodName, args);
   }

   public Object invoke(final Class clazz, final Object obj, final String methodName, final Seq args) {
      Tuple2 var7 = args.unzip(scala.Predef..MODULE$.$conforms());
      if (var7 != null) {
         Seq types = (Seq)var7._1();
         Seq values = (Seq)var7._2();
         Tuple2 var6 = new Tuple2(types, values);
         Seq types = (Seq)var6._1();
         Seq values = (Seq)var6._2();
         Method method = clazz.getDeclaredMethod(methodName, (Class[])types.toArray(scala.reflect.ClassTag..MODULE$.apply(Class.class)));
         method.setAccessible(true);
         return method.invoke(obj, values.toArray(scala.reflect.ClassTag..MODULE$.AnyRef()));
      } else {
         throw new MatchError(var7);
      }
   }

   private ReflectionUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
