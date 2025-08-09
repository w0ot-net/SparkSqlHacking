package com.fasterxml.jackson.module.scala.deser;

import java.io.Serializable;
import scala.Option;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class OptionDeserializer$ implements Serializable {
   public static final OptionDeserializer$ MODULE$ = new OptionDeserializer$();

   public Option $lessinit$greater$default$4() {
      return .MODULE$;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OptionDeserializer$.class);
   }

   private OptionDeserializer$() {
   }
}
