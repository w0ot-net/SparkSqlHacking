package org.apache.spark.streaming.api.python;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.apache.spark.SparkException;
import scala.Predef.;

public final class PythonTransformFunctionSerializer$ {
   public static final PythonTransformFunctionSerializer$ MODULE$ = new PythonTransformFunctionSerializer$();
   private static PythonTransformFunctionSerializer serializer;

   private PythonTransformFunctionSerializer serializer() {
      return serializer;
   }

   private void serializer_$eq(final PythonTransformFunctionSerializer x$1) {
      serializer = x$1;
   }

   public synchronized void register(final PythonTransformFunctionSerializer ser) {
      this.serializer_$eq(ser);
   }

   public synchronized byte[] serialize(final PythonTransformFunction func) {
      .MODULE$.require(this.serializer() != null, () -> "Serializer has not been registered!");
      InvocationHandler h = Proxy.getInvocationHandler((Proxy)func);
      Field f = h.getClass().getDeclaredField("id");
      f.setAccessible(true);
      String id = (String)f.get(h);
      byte[] results = this.serializer().dumps(id);
      String failure = this.serializer().getLastFailure();
      if (failure != null) {
         throw new SparkException("An exception was raised by Python:\n" + failure);
      } else {
         return results;
      }
   }

   public synchronized PythonTransformFunction deserialize(final byte[] bytes) {
      .MODULE$.require(this.serializer() != null, () -> "Serializer has not been registered!");
      PythonTransformFunction pfunc = this.serializer().loads(bytes);
      String failure = this.serializer().getLastFailure();
      if (failure != null) {
         throw new SparkException("An exception was raised by Python:\n" + failure);
      } else {
         return pfunc;
      }
   }

   private PythonTransformFunctionSerializer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
