package org.apache.spark.internal.io;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class FileNameSpec$ extends AbstractFunction2 implements Serializable {
   public static final FileNameSpec$ MODULE$ = new FileNameSpec$();

   public final String toString() {
      return "FileNameSpec";
   }

   public FileNameSpec apply(final String prefix, final String suffix) {
      return new FileNameSpec(prefix, suffix);
   }

   public Option unapply(final FileNameSpec x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.prefix(), x$0.suffix())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FileNameSpec$.class);
   }

   private FileNameSpec$() {
   }
}
