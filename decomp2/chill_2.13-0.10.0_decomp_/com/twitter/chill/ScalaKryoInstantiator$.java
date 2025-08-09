package com.twitter.chill;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ScalaKryoInstantiator$ implements Serializable {
   public static final ScalaKryoInstantiator$ MODULE$ = new ScalaKryoInstantiator$();
   private static final Object mutex = new Serializable() {
   };
   private static transient KryoPool kpool = null;

   private Object mutex() {
      return mutex;
   }

   private KryoPool kpool() {
      return kpool;
   }

   private void kpool_$eq(final KryoPool x$1) {
      kpool = x$1;
   }

   public KryoPool defaultPool() {
      synchronized(this.mutex()){}

      KryoPool var2;
      try {
         if (this.kpool() == null) {
            this.kpool_$eq(KryoPool.withByteArrayOutputStream(this.guessThreads(), new ScalaKryoInstantiator()));
         }

         var2 = this.kpool();
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private int guessThreads() {
      int cores = Runtime.getRuntime().availableProcessors();
      int GUESS_THREADS_PER_CORE = 4;
      return GUESS_THREADS_PER_CORE * cores;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ScalaKryoInstantiator$.class);
   }

   private ScalaKryoInstantiator$() {
   }
}
