package scala.xml;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.mutable.StringBuilder;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%t!\u0002\u000e\u001c\u0011\u0003\u0001c!\u0002\u0012\u001c\u0011\u0003\u0019\u0003\"\u0002\u0019\u0002\t\u0003\t\u0004\"\u0002\u001a\u0002\t\u0003\u0019\u0004BB1\u0002\t\u0003\t\t\u0004\u0003\u0004b\u0003\u0011\u0005\u0011\u0011\b\u0005\u0007C\u0006!\t!a\u0011\t\r\u0005\fA\u0011AA'\u0011%\tI&AA\u0001\n\u0013\tYFB\u0004#7A\u0005\u0019\u0011\u0001+\t\u000bUKA\u0011\u0001,\t\u000biKa\u0011A.\t\u000fqK!\u0019!D!7\"9Q,\u0003b\u0001\u000e\u0003r\u0006bB0\n\u0005\u00045\t\u0005\u0019\u0005\u0006C&1\tE\u0019\u0005\u0006C&1\t\u0005\u001a\u0005\u0006[&1\tE\u001c\u0005\u0006a&!\t%\u001d\u0005\u0006a&!\te\u001d\u0005\u0006o&!\t\u0005\u001f\u0005\u0006y&1\t% \u0005\b\u0003\u0003IA\u0011IA\u0002\u0011\u001d\t9!\u0003C!\u0003\u0013Aq!!\u0007\n\t\u0003\nY\u0002C\u0004\u0002$%!\t&!\n\u0002\u0013\u0005#HO]5ckR,'B\u0001\u000f\u001e\u0003\rAX\u000e\u001c\u0006\u0002=\u0005)1oY1mC\u000e\u0001\u0001CA\u0011\u0002\u001b\u0005Y\"!C!uiJL'-\u001e;f'\r\tA\u0005\u000b\t\u0003K\u0019j\u0011!H\u0005\u0003Ou\u0011a!\u00118z%\u00164\u0007CA\u0015/\u001b\u0005Q#BA\u0016-\u0003\tIwNC\u0001.\u0003\u0011Q\u0017M^1\n\u0005=R#\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001!\u0003\u001d)h.\u00199qYf$\"\u0001N)\u0011\u0007\u0015*t'\u0003\u00027;\t1q\n\u001d;j_:\u0004R!\n\u001d;\u000b:K!!O\u000f\u0003\rQ+\b\u000f\\34!\tY$I\u0004\u0002=\u0001B\u0011Q(H\u0007\u0002})\u0011qhH\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005k\u0012A\u0002)sK\u0012,g-\u0003\u0002D\t\n11\u000b\u001e:j]\u001eT!!Q\u000f\u0011\u0007\u0019K5*D\u0001H\u0015\tAU$\u0001\u0006d_2dWm\u0019;j_:L!AS$\u0003\u0007M+\u0017\u000f\u0005\u0002\"\u0019&\u0011Qj\u0007\u0002\u0005\u001d>$W\r\u0005\u0002\"\u001f&\u0011\u0001k\u0007\u0002\t\u001b\u0016$\u0018\rR1uC\")!k\u0001a\u0001'\u0006\t\u0001\u0010\u0005\u0002\"\u0013M\u0011\u0011BT\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003]\u0003\"!\n-\n\u0005ek\"\u0001B+oSR\f1\u0001\u001d:f+\u0005Q\u0014aA6fs\u0006)a/\u00197vKV\tQ)\u0001\u0003oKb$X#\u0001(\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005\u0015\u001b\u0007\"\u0002/\u0010\u0001\u0004QD\u0003B#fO2DQA\u001a\tA\u0002i\n\u0011B\\1nKN\u0004\u0018mY3\t\u000b!\u0004\u0002\u0019A5\u0002\u000bM\u001cw\u000e]3\u0011\u0005\u0005R\u0017BA6\u001c\u0005Aq\u0015-\\3ta\u0006\u001cWMQ5oI&tw\rC\u0003]!\u0001\u0007!(\u0001\u0003d_BLHCA*p\u0011\u0015y\u0016\u00031\u0001O\u0003\u0019\u0011X-\\8wKR\u0011aJ\u001d\u0005\u00069J\u0001\rA\u000f\u000b\u0005\u001dR,h\u000fC\u0003g'\u0001\u0007!\bC\u0003i'\u0001\u0007\u0011\u000eC\u0003]'\u0001\u0007!(\u0001\u0006jgB\u0013XMZ5yK\u0012,\u0012!\u001f\t\u0003KiL!a_\u000f\u0003\u000f\t{w\u000e\\3b]\u0006aq-\u001a;OC6,7\u000f]1dKR\u0011!H \u0005\u0006\u007fV\u0001\raS\u0001\u0006_^tWM]\u0001\u000bo\u0016dGNZ8s[\u0016$GcA=\u0002\u0006!)\u0001N\u0006a\u0001S\u0006A\u0011\u000e^3sCR|'/\u0006\u0002\u0002\fA)\u0011QBA\n\u001d:\u0019Q%a\u0004\n\u0007\u0005EQ$A\u0004qC\u000e\\\u0017mZ3\n\t\u0005U\u0011q\u0003\u0002\t\u0013R,'/\u0019;pe*\u0019\u0011\u0011C\u000f\u0002\tML'0Z\u000b\u0003\u0003;\u00012!JA\u0010\u0013\r\t\t#\b\u0002\u0004\u0013:$\u0018!\u0003;p'R\u0014\u0018N\\42)\r9\u0016q\u0005\u0005\b\u0003SI\u0002\u0019AA\u0016\u0003\t\u0019(\r\u0005\u0003\u0002\u000e\u00055\u0012\u0002BA\u0018\u0003/\u0011Qb\u0015;sS:<')^5mI\u0016\u0014HcB*\u00024\u0005U\u0012q\u0007\u0005\u00069\u0012\u0001\rA\u000f\u0005\u0006;\u0012\u0001\r!\u0012\u0005\u0006?\u0012\u0001\rA\u0014\u000b\n'\u0006m\u0012QHA \u0003\u0003BQAW\u0003A\u0002iBQ\u0001X\u0003A\u0002iBQ!X\u0003A\u0002iBQaX\u0003A\u00029#\u0012bUA#\u0003\u000f\nI%a\u0013\t\u000bi3\u0001\u0019\u0001\u001e\t\u000bq3\u0001\u0019\u0001\u001e\t\u000bu3\u0001\u0019A#\t\u000b}3\u0001\u0019\u0001(\u0015\u0013M\u000by%a\u0015\u0002V\u0005]\u0003B\u0002.\b\u0001\u0004\t\t\u0006E\u0002&kiBQ\u0001X\u0004A\u0002iBQ!X\u0004A\u0002\u0015CQaX\u0004A\u00029\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0018\u0011\t\u0005}\u0013QM\u0007\u0003\u0003CR1!a\u0019-\u0003\u0011a\u0017M\\4\n\t\u0005\u001d\u0014\u0011\r\u0002\u0007\u001f\nTWm\u0019;"
)
public interface Attribute {
   static Option unapply(final Attribute x) {
      return Attribute$.MODULE$.unapply(x);
   }

   String pre();

   String key();

   Seq value();

   MetaData next();

   Seq apply(final String key);

   Seq apply(final String namespace, final NamespaceBinding scope, final String key);

   Attribute copy(final MetaData next);

   // $FF: synthetic method
   static MetaData remove$(final Attribute $this, final String key) {
      return $this.remove(key);
   }

   default MetaData remove(final String key) {
      if (!this.isPrefixed()) {
         String var10000 = this.key();
         if (var10000 == null) {
            if (key == null) {
               return this.next();
            }
         } else if (var10000.equals(key)) {
            return this.next();
         }
      }

      return (MetaData)this.copy(this.next().remove(key));
   }

   // $FF: synthetic method
   static MetaData remove$(final Attribute $this, final String namespace, final NamespaceBinding scope, final String key) {
      return $this.remove(namespace, scope, key);
   }

   default MetaData remove(final String namespace, final NamespaceBinding scope, final String key) {
      String var10000 = this.key();
      if (var10000 == null) {
         if (key != null) {
            return (MetaData)this.copy(this.next().remove(namespace, scope, key));
         }
      } else if (!var10000.equals(key)) {
         return (MetaData)this.copy(this.next().remove(namespace, scope, key));
      }

      var10000 = scope.getURI(this.pre());
      if (var10000 == null) {
         if (namespace == null) {
            return this.next();
         }
      } else if (var10000.equals(namespace)) {
         return this.next();
      }

      return (MetaData)this.copy(this.next().remove(namespace, scope, key));
   }

   // $FF: synthetic method
   static boolean isPrefixed$(final Attribute $this) {
      return $this.isPrefixed();
   }

   default boolean isPrefixed() {
      return this.pre() != null;
   }

   String getNamespace(final Node owner);

   // $FF: synthetic method
   static boolean wellformed$(final Attribute $this, final NamespaceBinding scope) {
      return $this.wellformed(scope);
   }

   default boolean wellformed(final NamespaceBinding scope) {
      String arg = this.isPrefixed() ? scope.getURI(this.pre()) : null;
      return this.next().apply(arg, scope, this.key()) == null && this.next().wellformed(scope);
   }

   // $FF: synthetic method
   static Iterator iterator$(final Attribute $this) {
      return $this.iterator();
   }

   default Iterator iterator() {
      return this.value() == null ? this.next().iterator() : .MODULE$.Iterator().single(this).$plus$plus(() -> this.next().iterator());
   }

   // $FF: synthetic method
   static int size$(final Attribute $this) {
      return $this.size();
   }

   default int size() {
      return this.value() == null ? this.next().size() : 1 + this.next().size();
   }

   // $FF: synthetic method
   static void toString1$(final Attribute $this, final StringBuilder sb) {
      $this.toString1(sb);
   }

   default void toString1(final StringBuilder sb) {
      if (this.value() != null) {
         if (this.isPrefixed()) {
            sb.append((new java.lang.StringBuilder(1)).append(this.pre()).append(":").toString());
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         sb.append((new java.lang.StringBuilder(1)).append(this.key()).append("=").toString());
         StringBuilder sb2 = new StringBuilder();
         Utility$.MODULE$.sequenceToXML(this.value(), TopScope$.MODULE$, sb2, true, Utility$.MODULE$.sequenceToXML$default$5(), Utility$.MODULE$.sequenceToXML$default$6(), Utility$.MODULE$.sequenceToXML$default$7());
         Utility$.MODULE$.appendQuoted(sb2.toString(), sb);
      }
   }

   static void $init$(final Attribute $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
