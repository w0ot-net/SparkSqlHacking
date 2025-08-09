package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ConstantPool$ extends AbstractFunction1 implements Serializable {
   public static final ConstantPool$ MODULE$ = new ConstantPool$();

   public final String toString() {
      return "ConstantPool";
   }

   public ConstantPool apply(final int len) {
      return new ConstantPool(len);
   }

   public Option unapply(final ConstantPool x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.len())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ConstantPool$.class);
   }

   private ConstantPool$() {
   }
}
