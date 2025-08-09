package com.fasterxml.jackson.module.scala.deser;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class EitherDeserializer$ implements Serializable {
   public static final EitherDeserializer$ MODULE$ = new EitherDeserializer$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(EitherDeserializer$.class);
   }

   private EitherDeserializer$() {
   }
}
