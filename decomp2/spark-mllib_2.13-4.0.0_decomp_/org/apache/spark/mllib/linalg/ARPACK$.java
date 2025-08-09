package org.apache.spark.mllib.linalg;

import dev.ludovic.netlib.arpack.JavaARPACK;
import dev.ludovic.netlib.arpack.NativeARPACK;
import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ARPACK$ implements Serializable {
   public static final ARPACK$ MODULE$ = new ARPACK$();
   private static transient dev.ludovic.netlib.arpack.ARPACK _javaARPACK;
   private static transient dev.ludovic.netlib.arpack.ARPACK _nativeARPACK;

   private dev.ludovic.netlib.arpack.ARPACK _javaARPACK() {
      return _javaARPACK;
   }

   private void _javaARPACK_$eq(final dev.ludovic.netlib.arpack.ARPACK x$1) {
      _javaARPACK = x$1;
   }

   private dev.ludovic.netlib.arpack.ARPACK _nativeARPACK() {
      return _nativeARPACK;
   }

   private void _nativeARPACK_$eq(final dev.ludovic.netlib.arpack.ARPACK x$1) {
      _nativeARPACK = x$1;
   }

   public dev.ludovic.netlib.arpack.ARPACK javaARPACK() {
      if (this._javaARPACK() == null) {
         this._javaARPACK_$eq(JavaARPACK.getInstance());
      }

      return this._javaARPACK();
   }

   public dev.ludovic.netlib.arpack.ARPACK nativeARPACK() {
      if (this._nativeARPACK() == null) {
         this._nativeARPACK_$eq(this.liftedTree1$1());
      }

      return this._nativeARPACK();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ARPACK$.class);
   }

   // $FF: synthetic method
   private final dev.ludovic.netlib.arpack.ARPACK liftedTree1$1() {
      Object var10000;
      try {
         var10000 = NativeARPACK.getInstance();
      } catch (Throwable var1) {
         var10000 = this.javaARPACK();
      }

      return (dev.ludovic.netlib.arpack.ARPACK)var10000;
   }

   private ARPACK$() {
   }
}
