package org.apache.spark.deploy;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction6;
import scala.runtime.ModuleSerializationProxy;

public final class Command$ extends AbstractFunction6 implements Serializable {
   public static final Command$ MODULE$ = new Command$();

   public final String toString() {
      return "Command";
   }

   public Command apply(final String mainClass, final Seq arguments, final Map environment, final Seq classPathEntries, final Seq libraryPathEntries, final Seq javaOpts) {
      return new Command(mainClass, arguments, environment, classPathEntries, libraryPathEntries, javaOpts);
   }

   public Option unapply(final Command x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.mainClass(), x$0.arguments(), x$0.environment(), x$0.classPathEntries(), x$0.libraryPathEntries(), x$0.javaOpts())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Command$.class);
   }

   private Command$() {
   }
}
