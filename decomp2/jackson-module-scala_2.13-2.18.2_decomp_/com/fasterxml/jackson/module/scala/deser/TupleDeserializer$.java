package com.fasterxml.jackson.module.scala.deser;

import java.io.Serializable;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.runtime.ModuleSerializationProxy;

public final class TupleDeserializer$ implements Serializable {
   public static final TupleDeserializer$ MODULE$ = new TupleDeserializer$();

   public Seq $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public Seq $lessinit$greater$default$4() {
      return .MODULE$;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TupleDeserializer$.class);
   }

   private TupleDeserializer$() {
   }
}
