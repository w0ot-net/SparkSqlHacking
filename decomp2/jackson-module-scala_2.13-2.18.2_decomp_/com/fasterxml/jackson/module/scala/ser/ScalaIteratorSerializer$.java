package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ScalaIteratorSerializer$ extends AbstractFunction6 implements Serializable {
   public static final ScalaIteratorSerializer$ MODULE$ = new ScalaIteratorSerializer$();

   public final String toString() {
      return "ScalaIteratorSerializer";
   }

   public ScalaIteratorSerializer apply(final JavaType elemType, final boolean staticTyping, final TypeSerializer vts, final BeanProperty property, final JsonSerializer valueSerializer, final Boolean unwrapSingle) {
      return new ScalaIteratorSerializer(elemType, staticTyping, vts, property, valueSerializer, unwrapSingle);
   }

   public Option unapply(final ScalaIteratorSerializer x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.elemType(), BoxesRunTime.boxToBoolean(x$0.staticTyping()), x$0.vts(), x$0.property(), x$0.valueSerializer(), x$0.unwrapSingle())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ScalaIteratorSerializer$.class);
   }

   private ScalaIteratorSerializer$() {
   }
}
