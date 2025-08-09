package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import java.io.Serializable;
import scala.Option;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class EitherSerializer$ implements Serializable {
   public static final EitherSerializer$ MODULE$ = new EitherSerializer$();

   public Option $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public PropertySerializerMap $lessinit$greater$default$5() {
      return PropertySerializerMap.emptyForProperties();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EitherSerializer$.class);
   }

   private EitherSerializer$() {
   }
}
