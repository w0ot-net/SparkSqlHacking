package org.apache.spark.ui.flamegraph;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.spark.status.api.v1.ThreadStackTrace;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;

public final class FlamegraphNode$ implements Serializable {
   public static final FlamegraphNode$ MODULE$ = new FlamegraphNode$();

   public FlamegraphNode apply(final ThreadStackTrace[] stacks) {
      FlamegraphNode root = new FlamegraphNode("root");
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(stacks), (stack) -> {
         $anonfun$apply$1(root, stack);
         return BoxedUnit.UNIT;
      });
      return root;
   }

   public FlamegraphNode apply(final String name) {
      return new FlamegraphNode(name);
   }

   public Option unapply(final FlamegraphNode x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.name()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FlamegraphNode$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$2(final ObjectRef cur$1, final String e) {
      String head = (String).MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])e.split("\n")));
      String name = StringEscapeUtils.escapeJson(head);
      cur$1.elem = (FlamegraphNode)((FlamegraphNode)cur$1.elem).org$apache$spark$ui$flamegraph$FlamegraphNode$$children().getOrElseUpdate(name, () -> new FlamegraphNode(name));
      FlamegraphNode var4 = (FlamegraphNode)cur$1.elem;
      var4.org$apache$spark$ui$flamegraph$FlamegraphNode$$value_$eq(var4.org$apache$spark$ui$flamegraph$FlamegraphNode$$value() + 1);
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$1(final FlamegraphNode root$1, final ThreadStackTrace stack) {
      root$1.org$apache$spark$ui$flamegraph$FlamegraphNode$$value_$eq(root$1.org$apache$spark$ui$flamegraph$FlamegraphNode$$value() + 1);
      ObjectRef cur = ObjectRef.create(root$1);
      ((IterableOnceOps)stack.stackTrace().elems().reverse()).foreach((e) -> {
         $anonfun$apply$2(cur, e);
         return BoxedUnit.UNIT;
      });
   }

   private FlamegraphNode$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
