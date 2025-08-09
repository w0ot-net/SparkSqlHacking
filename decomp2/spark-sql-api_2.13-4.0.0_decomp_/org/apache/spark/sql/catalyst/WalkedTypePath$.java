package org.apache.spark.sql.catalyst;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class WalkedTypePath$ extends AbstractFunction1 implements Serializable {
   public static final WalkedTypePath$ MODULE$ = new WalkedTypePath$();

   public Seq $lessinit$greater$default$1() {
      return .MODULE$;
   }

   public final String toString() {
      return "WalkedTypePath";
   }

   public WalkedTypePath apply(final Seq walkedPaths) {
      return new WalkedTypePath(walkedPaths);
   }

   public Seq apply$default$1() {
      return .MODULE$;
   }

   public Option unapply(final WalkedTypePath x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.org$apache$spark$sql$catalyst$WalkedTypePath$$walkedPaths()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WalkedTypePath$.class);
   }

   private WalkedTypePath$() {
   }
}
