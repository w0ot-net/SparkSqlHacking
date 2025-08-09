package breeze.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.io.Source;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015eaB\f\u0019!\u0003\r\t!\b\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0015\u00021\ta\u0013\u0005\u0006\u0019\u00021\t!\u0014\u0005\u0006!\u00021\t!\u0015\u0005\u0006/\u0002!\t\u0001\u0017\u0005\u0006;\u0002!\tA\u0018\u0005\u0006C\u0002!\tA\u0019\u0005\u0006I\u00021\t!\u001a\u0005\u0006Y\u0002!\t!\u001c\u0005\u0006_\u0002!\t\u0005\u001d\u0005\tg\u0002A)\u0019!C\t\u0017\")A\u000f\u0001C!k\")a\u000f\u0001C!o\"9\u0011\u0011\u0001\u0001\u0005\u0002\u0005\rqaBA\u000e1!\u0005\u0011Q\u0004\u0004\u0007/aA\t!a\b\t\u000f\u0005=\u0002\u0003\"\u0001\u00022!1A\n\u0005C\u0001\u0003gAa\u0001\u0014\t\u0005\u0002\u0005\u0005\u0003B\u0002'\u0011\t\u0003\t\t\u0006C\u0004\u0002bA!\t!a\u0019\t\u0013\u0005U\u0004#!A\u0005\n\u0005]$!B%oI\u0016D(BA\r\u001b\u0003\u0011)H/\u001b7\u000b\u0003m\taA\u0019:fKj,7\u0001A\u000b\u0003=M\u001aR\u0001A\u0010&y\t\u0003\"\u0001I\u0012\u000e\u0003\u0005R\u0011AI\u0001\u0006g\u000e\fG.Y\u0005\u0003I\u0005\u0012a!\u00118z%\u00164\u0007c\u0001\u0014/c9\u0011q\u0005\f\b\u0003Q-j\u0011!\u000b\u0006\u0003Uq\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0012\n\u00055\n\u0013a\u00029bG.\fw-Z\u0005\u0003_A\u0012\u0001\"\u0013;fe\u0006\u0014G.\u001a\u0006\u0003[\u0005\u0002\"AM\u001a\r\u0001\u0011)A\u0007\u0001b\u0001k\t\tA+\u0005\u00027sA\u0011\u0001eN\u0005\u0003q\u0005\u0012qAT8uQ&tw\r\u0005\u0002!u%\u00111(\t\u0002\u0004\u0003:L\b\u0003\u0002\u0011>c}J!AP\u0011\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001\u0011A\u0013\t\t\u0015EA\u0002J]R\u0004\"AJ\"\n\u0005\u0011\u0003$\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u0013j]&$H\u0005F\u0001H!\t\u0001\u0003*\u0003\u0002JC\t!QK\\5u\u0003\u0011\u0019\u0018N_3\u0016\u0003}\nQ!\u00199qYf$\"a\u0010(\t\u000b=\u001b\u0001\u0019A\u0019\u0002\u0003Q\fq!\u001e8baBd\u0017\u0010\u0006\u0002S+B\u0019\u0001eU\u0019\n\u0005Q\u000b#AB(qi&|g\u000eC\u0003W\t\u0001\u0007q(A\u0001j\u0003!\u0019wN\u001c;bS:\u001cHCA-]!\t\u0001#,\u0003\u0002\\C\t9!i\\8mK\u0006t\u0007\"B(\u0006\u0001\u0004\t\u0014\u0001C5oI\u0016Dx\n\u001d;\u0015\u0005}\u0003\u0007c\u0001\u0011T\u007f!)qJ\u0002a\u0001c\u00059\u0011N\u001c3fq>3GCA d\u0011\u0015yu\u00011\u00012\u0003\u0015\u0001\u0018-\u001b:t+\u00051\u0007c\u0001\u0014hS&\u0011\u0001\u000e\r\u0002\t\u0013R,'/\u0019;peB!\u0001E[\u0019@\u0013\tY\u0017E\u0001\u0004UkBdWMM\u0001\u0004O\u0016$HCA\u0019o\u0011\u00151\u0016\u00021\u0001@\u0003\u0019)\u0017/^1mgR\u0011\u0011,\u001d\u0005\u0006e*\u0001\r!O\u0001\u0006_RDWM]\u0001\u0010I\u00164\u0017-\u001e7u\u0011\u0006\u001c\bnQ8eK\u0006A\u0001.Y:i\u0007>$W\rF\u0001@\u0003!!xn\u0015;sS:<G#\u0001=\u0011\u0005elhB\u0001>|!\tA\u0013%\u0003\u0002}C\u00051\u0001K]3eK\u001aL!A`@\u0003\rM#(/\u001b8h\u0015\ta\u0018%\u0001\u0003%E\u0006\u0014X\u0003BA\u0003\u0003#!B!a\u0002\u0002\u0016A9\u0011\u0011BA\u0006c\u0005=Q\"\u0001\r\n\u0007\u00055\u0001DA\u0006FSRDWM]%oI\u0016D\bc\u0001\u001a\u0002\u0012\u00111\u00111\u0003\bC\u0002U\u0012\u0011!\u0016\u0005\b\u0003/q\u0001\u0019AA\r\u0003\u0015\u0011\u0018n\u001a5u!\u0015\tI\u0001AA\b\u0003\u0015Ie\u000eZ3y!\r\tI\u0001E\n\u0005!}\t\t\u0003\u0005\u0003\u0002$\u00055RBAA\u0013\u0015\u0011\t9#!\u000b\u0002\u0005%|'BAA\u0016\u0003\u0011Q\u0017M^1\n\u0007\u0011\u000b)#\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003;)B!!\u000e\u0002@Q\u0011\u0011q\u0007\t\u0007\u0003\u0013\tI$!\u0010\n\u0007\u0005m\u0002D\u0001\u0007NkR\f'\r\\3J]\u0012,\u0007\u0010E\u00023\u0003\u007f!Q\u0001\u000e\nC\u0002U*B!a\u0011\u0002JQ!\u0011QIA&!\u0015\tI\u0001AA$!\r\u0011\u0014\u0011\n\u0003\u0006iM\u0011\r!\u000e\u0005\b\u0003\u001b\u001a\u0002\u0019AA(\u0003!IG/\u001a:bi>\u0014\b\u0003\u0002\u0014h\u0003\u000f*B!a\u0015\u0002ZQ!\u0011QKA.!\u0015\tI\u0001AA,!\r\u0011\u0014\u0011\f\u0003\u0006iQ\u0011\r!\u000e\u0005\b\u0003;\"\u0002\u0019AA0\u0003!IG/\u001a:bE2,\u0007\u0003\u0002\u0014/\u0003/\nA\u0001\\8bIR!\u0011QMA4!\u0011\tI\u0001\u0001=\t\u000f\u0005%T\u00031\u0001\u0002l\u000511o\\;sG\u0016\u0004B!!\u001c\u0002r5\u0011\u0011q\u000e\u0006\u0004\u0003O\t\u0013\u0002BA:\u0003_\u0012aaU8ve\u000e,\u0017\u0001D<sSR,'+\u001a9mC\u000e,GCAA=!\u0011\tY(!!\u000e\u0005\u0005u$\u0002BA@\u0003S\tA\u0001\\1oO&!\u00111QA?\u0005\u0019y%M[3di\u0002"
)
public interface Index extends Iterable, Function1, Serializable {
   static Index load(final Source source) {
      return Index$.MODULE$.load(source);
   }

   int size();

   int apply(final Object t);

   Option unapply(final int i);

   // $FF: synthetic method
   static boolean contains$(final Index $this, final Object t) {
      return $this.contains(t);
   }

   default boolean contains(final Object t) {
      return this.apply(t) >= 0;
   }

   // $FF: synthetic method
   static Option indexOpt$(final Index $this, final Object t) {
      return $this.indexOpt(t);
   }

   default Option indexOpt(final Object t) {
      int i = this.apply(t);
      return (Option)(i >= 0 ? new Some(BoxesRunTime.boxToInteger(i)) : .MODULE$);
   }

   // $FF: synthetic method
   static int indexOf$(final Index $this, final Object t) {
      return $this.indexOf(t);
   }

   default int indexOf(final Object t) {
      return this.apply(t);
   }

   Iterator pairs();

   // $FF: synthetic method
   static Object get$(final Index $this, final int i) {
      return $this.get(i);
   }

   default Object get(final int i) {
      return this.unapply(i).getOrElse(() -> {
         throw new IndexOutOfBoundsException();
      });
   }

   // $FF: synthetic method
   static boolean equals$(final Index $this, final Object other) {
      return $this.equals(other);
   }

   default boolean equals(final Object other) {
      boolean var2;
      if (other instanceof Index) {
         Index var4 = (Index)other;
         if (this.size() == var4.size()) {
            var2 = scala.collection.IterableOnceExtensionMethods..MODULE$.sameElements$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(this), var4);
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   // $FF: synthetic method
   static int defaultHashCode$(final Index $this) {
      return $this.defaultHashCode();
   }

   default int defaultHashCode() {
      return BoxesRunTime.unboxToInt(this.foldLeft(BoxesRunTime.boxToInteger(17), (x$1, x$2) -> BoxesRunTime.boxToInteger($anonfun$defaultHashCode$1(BoxesRunTime.unboxToInt(x$1), x$2))));
   }

   // $FF: synthetic method
   static int hashCode$(final Index $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      return this.defaultHashCode();
   }

   // $FF: synthetic method
   static String toString$(final Index $this) {
      return $this.toString();
   }

   default String toString() {
      return this.iterator().mkString("Index(", ",", ")");
   }

   // $FF: synthetic method
   static EitherIndex $bar$(final Index $this, final Index right) {
      return $this.$bar(right);
   }

   default EitherIndex $bar(final Index right) {
      return new EitherIndex(this, right);
   }

   // $FF: synthetic method
   static int $anonfun$defaultHashCode$1(final int x$1, final Object x$2) {
      return x$1 * 41 + x$2.hashCode();
   }

   static void $init$(final Index $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
