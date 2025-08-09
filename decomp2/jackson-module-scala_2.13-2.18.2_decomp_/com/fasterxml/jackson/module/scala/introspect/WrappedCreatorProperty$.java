package com.fasterxml.jackson.module.scala.introspect;

import com.fasterxml.jackson.databind.deser.CreatorProperty;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class WrappedCreatorProperty$ extends AbstractFunction2 implements Serializable {
   public static final WrappedCreatorProperty$ MODULE$ = new WrappedCreatorProperty$();

   public final String toString() {
      return "WrappedCreatorProperty";
   }

   public WrappedCreatorProperty apply(final CreatorProperty creatorProperty, final ClassHolder refHolder) {
      return new WrappedCreatorProperty(creatorProperty, refHolder);
   }

   public Option unapply(final WrappedCreatorProperty x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.creatorProperty(), x$0.refHolder())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WrappedCreatorProperty$.class);
   }

   private WrappedCreatorProperty$() {
   }
}
