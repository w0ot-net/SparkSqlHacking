package com.twitter.chill;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class MeatLocker$ implements Serializable {
   public static final MeatLocker$ MODULE$ = new MeatLocker$();

   public MeatLocker apply(final Object t) {
      return new MeatLocker(t);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MeatLocker$.class);
   }

   private MeatLocker$() {
   }
}
