package com.fasterxml.jackson.module.scala.ser;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class EitherDetails$ extends AbstractFunction3 implements Serializable {
   public static final EitherDetails$ MODULE$ = new EitherDetails$();

   public final String toString() {
      return "EitherDetails";
   }

   public EitherDetails apply(final Option typ, final Option valueTypeSerializer, final Option valueSerializer) {
      return new EitherDetails(typ, valueTypeSerializer, valueSerializer);
   }

   public Option unapply(final EitherDetails x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.typ(), x$0.valueTypeSerializer(), x$0.valueSerializer())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EitherDetails$.class);
   }

   private EitherDetails$() {
   }
}
