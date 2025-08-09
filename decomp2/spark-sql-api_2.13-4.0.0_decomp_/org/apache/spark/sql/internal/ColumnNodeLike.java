package org.apache.spark.sql.internal;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005U3qa\u0002\u0005\u0011\u0002\u0007\u00051\u0003C\u0003\u001b\u0001\u0011\u00051\u0004\u0003\u0004 \u0001\u0011\u0005\u0001\u0002\t\u0005\u0007\u0017\u00011\t\u0001C\u0012\t\r=\u0002a\u0011\u0001\u00051\u0011\u0019Q\u0004\u0001\"\u0001\u000bw!1\u0011\t\u0001C\u0001\u0015\t\u0013abQ8mk6tgj\u001c3f\u0019&\\WM\u0003\u0002\n\u0015\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\f\u0019\u0005\u00191/\u001d7\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001)A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\u000f\u0011\u0005Ui\u0012B\u0001\u0010\u0017\u0005\u0011)f.\u001b;\u0002\u00139|'/\\1mSj,G#A\u0011\u0011\u0005\t\u0002Q\"\u0001\u0005\u0016\u0003\u0011\u0002\"!\n\u0017\u000f\u0005\u0019R\u0003CA\u0014\u0017\u001b\u0005A#BA\u0015\u0013\u0003\u0019a$o\\8u}%\u00111FF\u0001\u0007!J,G-\u001a4\n\u00055r#AB*ue&twM\u0003\u0002,-\u0005A1\r[5mIJ,g.F\u00012!\r\u0011t'\t\b\u0003gUr!a\n\u001b\n\u0003]I!A\u000e\f\u0002\u000fA\f7m[1hK&\u0011\u0001(\u000f\u0002\u0004'\u0016\f(B\u0001\u001c\u0017\u0003\u001d1wN]3bG\"$\"\u0001\b\u001f\t\u000bu*\u0001\u0019\u0001 \u0002\u0003\u0019\u0004B!F \"9%\u0011\u0001I\u0006\u0002\n\rVt7\r^5p]F\nqaY8mY\u0016\u001cG/\u0006\u0002D\u000fR\u0011A\t\u0015\t\u0004e]*\u0005C\u0001$H\u0019\u0001!Q\u0001\u0013\u0004C\u0002%\u0013\u0011!Q\t\u0003\u00156\u0003\"!F&\n\u000513\"a\u0002(pi\"Lgn\u001a\t\u0003+9K!a\u0014\f\u0003\u0007\u0005s\u0017\u0010C\u0003R\r\u0001\u0007!+\u0001\u0002qMB!QcU\u0011F\u0013\t!fCA\bQCJ$\u0018.\u00197Gk:\u001cG/[8o\u0001"
)
public interface ColumnNodeLike {
   // $FF: synthetic method
   static ColumnNodeLike normalize$(final ColumnNodeLike $this) {
      return $this.normalize();
   }

   default ColumnNodeLike normalize() {
      return this;
   }

   String sql();

   Seq children();

   // $FF: synthetic method
   static void foreach$(final ColumnNodeLike $this, final Function1 f) {
      $this.foreach(f);
   }

   default void foreach(final Function1 f) {
      f.apply(this);
      this.children().foreach((x$1) -> {
         $anonfun$foreach$1(f, x$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static Seq collect$(final ColumnNodeLike $this, final PartialFunction pf) {
      return $this.collect(pf);
   }

   default Seq collect(final PartialFunction pf) {
      ArrayBuffer ret = new ArrayBuffer();
      Function1 lifted = pf.lift();
      this.foreach((node) -> {
         $anonfun$collect$1(lifted, ret, node);
         return BoxedUnit.UNIT;
      });
      return ret.toSeq();
   }

   // $FF: synthetic method
   static void $anonfun$foreach$1(final Function1 f$1, final ColumnNodeLike x$1) {
      x$1.foreach(f$1);
   }

   // $FF: synthetic method
   static void $anonfun$collect$1(final Function1 lifted$1, final ArrayBuffer ret$1, final ColumnNodeLike node) {
      ((Option)lifted$1.apply(node)).foreach((elem) -> (ArrayBuffer)ret$1.$plus$eq(elem));
   }

   static void $init$(final ColumnNodeLike $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
