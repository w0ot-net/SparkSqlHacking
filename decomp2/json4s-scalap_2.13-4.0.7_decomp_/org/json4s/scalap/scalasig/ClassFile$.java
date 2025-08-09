package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public final class ClassFile$ extends AbstractFunction4 implements Serializable {
   public static final ClassFile$ MODULE$ = new ClassFile$();

   public final String toString() {
      return "ClassFile";
   }

   public ClassFile apply(final ClassFileHeader header, final Seq fields, final Seq methods, final Seq attributes) {
      return new ClassFile(header, fields, methods, attributes);
   }

   public Option unapply(final ClassFile x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.header(), x$0.fields(), x$0.methods(), x$0.attributes())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClassFile$.class);
   }

   private ClassFile$() {
   }
}
