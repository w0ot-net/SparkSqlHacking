package org.apache.spark.mllib.linalg;

import dev.ludovic.netlib.lapack.JavaLAPACK;
import dev.ludovic.netlib.lapack.NativeLAPACK;
import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class LAPACK$ implements Serializable {
   public static final LAPACK$ MODULE$ = new LAPACK$();
   private static transient dev.ludovic.netlib.lapack.LAPACK _javaLAPACK;
   private static transient dev.ludovic.netlib.lapack.LAPACK _nativeLAPACK;

   private dev.ludovic.netlib.lapack.LAPACK _javaLAPACK() {
      return _javaLAPACK;
   }

   private void _javaLAPACK_$eq(final dev.ludovic.netlib.lapack.LAPACK x$1) {
      _javaLAPACK = x$1;
   }

   private dev.ludovic.netlib.lapack.LAPACK _nativeLAPACK() {
      return _nativeLAPACK;
   }

   private void _nativeLAPACK_$eq(final dev.ludovic.netlib.lapack.LAPACK x$1) {
      _nativeLAPACK = x$1;
   }

   public dev.ludovic.netlib.lapack.LAPACK javaLAPACK() {
      if (this._javaLAPACK() == null) {
         this._javaLAPACK_$eq(JavaLAPACK.getInstance());
      }

      return this._javaLAPACK();
   }

   public dev.ludovic.netlib.lapack.LAPACK nativeLAPACK() {
      if (this._nativeLAPACK() == null) {
         this._nativeLAPACK_$eq(this.liftedTree1$1());
      }

      return this._nativeLAPACK();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LAPACK$.class);
   }

   // $FF: synthetic method
   private final dev.ludovic.netlib.lapack.LAPACK liftedTree1$1() {
      Object var10000;
      try {
         var10000 = NativeLAPACK.getInstance();
      } catch (Throwable var1) {
         var10000 = this.javaLAPACK();
      }

      return (dev.ludovic.netlib.lapack.LAPACK)var10000;
   }

   private LAPACK$() {
   }
}
