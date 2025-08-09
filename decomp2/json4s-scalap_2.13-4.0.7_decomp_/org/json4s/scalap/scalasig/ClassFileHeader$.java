package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction7;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ClassFileHeader$ extends AbstractFunction7 implements Serializable {
   public static final ClassFileHeader$ MODULE$ = new ClassFileHeader$();

   public final String toString() {
      return "ClassFileHeader";
   }

   public ClassFileHeader apply(final int minor, final int major, final ConstantPool constants, final int flags, final int classIndex, final int superClassIndex, final Seq interfaces) {
      return new ClassFileHeader(minor, major, constants, flags, classIndex, superClassIndex, interfaces);
   }

   public Option unapply(final ClassFileHeader x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple7(BoxesRunTime.boxToInteger(x$0.minor()), BoxesRunTime.boxToInteger(x$0.major()), x$0.constants(), BoxesRunTime.boxToInteger(x$0.flags()), BoxesRunTime.boxToInteger(x$0.classIndex()), BoxesRunTime.boxToInteger(x$0.superClassIndex()), x$0.interfaces())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClassFileHeader$.class);
   }

   private ClassFileHeader$() {
   }
}
