package breeze.linalg;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala..less.colon.less.;
import scala.collection.Iterator;
import scala.collection.Set;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ecaB\f\u0019!\u0003\r\t!\b\u0005\u0006\u0015\u0002!\ta\u0013\u0005\u0006\u001f\u00021\t\u0001\u0015\u0005\u0006;\u00021\tA\u0018\u0005\u0006?\u0002!\t\u0001\u0019\u0005\u0006K\u0002!\tA\u001a\u0005\u0006O\u0002!\t\u0005\u001b\u0005\u0006Y\u0002!\t\u0001\u001b\u0005\u0006[\u0002!\tA\u001c\u0005\u0006e\u0002!\ta\u001d\u0005\u0006m\u0002!\te\u001e\u0005\u0006s\u0002!\tA\u001f\u0005\u0006}\u0002!\ta \u0005\b\u0003\u0013\u0001A\u0011IA\u0006\u0011\u001d\t\u0019\u0002\u0001C!\u0003+Aq!!\u0007\u0001\t\u0003\nY\u0002C\u0004\u0002&\u0001!\t!a\u0007\t\u000f\u0005\u001d\u0002\u0001\"\u0001\u0002\u0016!9\u0011\u0011\u0006\u0001\u0005\u0002\u0005-\u0001bBA\u0016\u0001\u0011\u0005\u0013Q\u0006\u0005\b\u0003\u007f\u0001A\u0011IA!\u0011\u001d\t9\u0005\u0001C!\u0003\u0013Bq!a\u0013\u0001\t\u0003\tiEA\u0006D_VtG/\u001a:MS.,'BA\r\u001b\u0003\u0019a\u0017N\\1mO*\t1$\u0001\u0004ce\u0016,'0Z\u0002\u0001+\u0015q2&\u000e*9'\u0011\u0001q$\n \u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0003\t\nQa]2bY\u0006L!\u0001J\u0011\u0003\r\u0005s\u0017PU3g!\u00151s%\u000b\u001b8\u001b\u0005A\u0012B\u0001\u0015\u0019\u0005)!VM\\:pe2K7.\u001a\t\u0003U-b\u0001\u0001B\u0003-\u0001\t\u0007QFA\u0001L#\tq\u0013\u0007\u0005\u0002!_%\u0011\u0001'\t\u0002\b\u001d>$\b.\u001b8h!\t\u0001#'\u0003\u00024C\t\u0019\u0011I\\=\u0011\u0005)*D!\u0002\u001c\u0001\u0005\u0004i#!\u0001,\u0011\u0005)BDAB\u001d\u0001\t\u000b\u0007!H\u0001\u0003UQ&\u001c\u0018C\u0001\u0018<!\u00111C(\u000b\u001b\n\u0005uB\"aB\"pk:$XM\u001d\t\u0003\u007f\u001ds!\u0001Q#\u000f\u0005\u0005#U\"\u0001\"\u000b\u0005\rc\u0012A\u0002\u001fs_>$h(C\u0001#\u0013\t1\u0015%A\u0004qC\u000e\\\u0017mZ3\n\u0005!K%\u0001D*fe&\fG.\u001b>bE2,'B\u0001$\"\u0003\u0019!\u0013N\\5uIQ\tA\n\u0005\u0002!\u001b&\u0011a*\t\u0002\u0005+:LG/\u0001\u0003eCR\fW#A)\u0011\u0005)\u0012FAB*\u0001\t\u000b\u0007AKA\u0001N#\tqS\u000b\u0005\u0003W7&\"T\"A,\u000b\u0005aK\u0016aB7vi\u0006\u0014G.\u001a\u0006\u00035\u0006\n!bY8mY\u0016\u001cG/[8o\u0013\tavKA\u0002NCB\fq\u0001Z3gCVdG/F\u00015\u0003\u0019YW-_*fiV\t\u0011\rE\u0002cG&j\u0011!W\u0005\u0003If\u00131aU3u\u0003\u0011\u0011X\r\u001d:\u0016\u0003]\nAa]5{KV\t\u0011\u000e\u0005\u0002!U&\u00111.\t\u0002\u0004\u0013:$\u0018AC1di&4XmU5{K\u00069\u0011n]#naRLX#A8\u0011\u0005\u0001\u0002\u0018BA9\"\u0005\u001d\u0011un\u001c7fC:\f\u0001bY8oi\u0006Lgn\u001d\u000b\u0003_RDQ!^\u0005A\u0002%\n\u0011a[\u0001\u0006CB\u0004H.\u001f\u000b\u0003iaDQ!\u001e\u0006A\u0002%\na!\u001e9eCR,Gc\u0001'|y\")Qo\u0003a\u0001S!)Qp\u0003a\u0001i\u0005\ta/A\u0002hKR$B!!\u0001\u0002\bA!\u0001%a\u00015\u0013\r\t)!\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u000bUd\u0001\u0019A\u0015\u0002\u0019-,\u0017p]%uKJ\fGo\u001c:\u0016\u0005\u00055\u0001\u0003\u00022\u0002\u0010%J1!!\u0005Z\u0005!IE/\u001a:bi>\u0014\u0018A\u0004<bYV,7/\u0013;fe\u0006$xN]\u000b\u0003\u0003/\u0001BAYA\bi\u0005A\u0011\u000e^3sCR|'/\u0006\u0002\u0002\u001eA)!-a\u0004\u0002 A)\u0001%!\t*i%\u0019\u00111E\u0011\u0003\rQ+\b\u000f\\33\u00039\t7\r^5wK&#XM]1u_J\fA#Y2uSZ,g+\u00197vKNLE/\u001a:bi>\u0014\u0018AE1di&4XmS3zg&#XM]1u_J\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003_\u0001B!!\r\u0002:9!\u00111GA\u001b!\t\t\u0015%C\u0002\u00028\u0005\na\u0001\u0015:fI\u00164\u0017\u0002BA\u001e\u0003{\u0011aa\u0015;sS:<'bAA\u001cC\u00051Q-];bYN$2a\\A\"\u0011\u0019\t)\u0005\u0006a\u0001c\u0005\u0011\u0001/M\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011.A\u0003u_6\u000b\u0007/\u0006\u0002\u0002PA1\u0011\u0011KA,SQj!!a\u0015\u000b\u0007\u0005U\u0013,A\u0005j[6,H/\u00192mK&\u0019A,a\u0015"
)
public interface CounterLike extends TensorLike, Serializable {
   Map data();

   Object default();

   // $FF: synthetic method
   static Set keySet$(final CounterLike $this) {
      return $this.keySet();
   }

   default Set keySet() {
      return this.data().keySet();
   }

   // $FF: synthetic method
   static Counter repr$(final CounterLike $this) {
      return $this.repr();
   }

   default Counter repr() {
      return (Counter)this;
   }

   // $FF: synthetic method
   static int size$(final CounterLike $this) {
      return $this.size();
   }

   default int size() {
      return this.data().size();
   }

   // $FF: synthetic method
   static int activeSize$(final CounterLike $this) {
      return $this.activeSize();
   }

   default int activeSize() {
      return this.data().size();
   }

   // $FF: synthetic method
   static boolean isEmpty$(final CounterLike $this) {
      return $this.isEmpty();
   }

   default boolean isEmpty() {
      return this.data().isEmpty();
   }

   // $FF: synthetic method
   static boolean contains$(final CounterLike $this, final Object k) {
      return $this.contains(k);
   }

   default boolean contains(final Object k) {
      return this.data().contains(k);
   }

   // $FF: synthetic method
   static Object apply$(final CounterLike $this, final Object k) {
      return $this.apply(k);
   }

   default Object apply(final Object k) {
      return this.data().getOrElse(k, () -> this.default());
   }

   // $FF: synthetic method
   static void update$(final CounterLike $this, final Object k, final Object v) {
      $this.update(k, v);
   }

   default void update(final Object k, final Object v) {
      this.data().update(k, v);
   }

   // $FF: synthetic method
   static Option get$(final CounterLike $this, final Object k) {
      return $this.get(k);
   }

   default Option get(final Object k) {
      return this.data().get(k);
   }

   // $FF: synthetic method
   static Iterator keysIterator$(final CounterLike $this) {
      return $this.keysIterator();
   }

   default Iterator keysIterator() {
      return this.data().keysIterator();
   }

   // $FF: synthetic method
   static Iterator valuesIterator$(final CounterLike $this) {
      return $this.valuesIterator();
   }

   default Iterator valuesIterator() {
      return this.data().valuesIterator();
   }

   // $FF: synthetic method
   static Iterator iterator$(final CounterLike $this) {
      return $this.iterator();
   }

   default Iterator iterator() {
      return this.data().iterator();
   }

   // $FF: synthetic method
   static Iterator activeIterator$(final CounterLike $this) {
      return $this.activeIterator();
   }

   default Iterator activeIterator() {
      return this.iterator();
   }

   // $FF: synthetic method
   static Iterator activeValuesIterator$(final CounterLike $this) {
      return $this.activeValuesIterator();
   }

   default Iterator activeValuesIterator() {
      return this.valuesIterator();
   }

   // $FF: synthetic method
   static Iterator activeKeysIterator$(final CounterLike $this) {
      return $this.activeKeysIterator();
   }

   default Iterator activeKeysIterator() {
      return this.keysIterator();
   }

   // $FF: synthetic method
   static String toString$(final CounterLike $this) {
      return $this.toString();
   }

   default String toString() {
      return this.data().mkString("Counter(", ", ", ")");
   }

   // $FF: synthetic method
   static boolean equals$(final CounterLike $this, final Object p1) {
      return $this.equals(p1);
   }

   default boolean equals(final Object p1) {
      boolean var2;
      if (p1 instanceof Counter) {
         boolean var6;
         label21: {
            label20: {
               Counter var4 = (Counter)p1;
               Map var10000 = var4.data();
               Map var5 = this.data();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label20;
                  }
               } else if (var10000.equals(var5)) {
                  break label20;
               }

               var6 = false;
               break label21;
            }

            var6 = true;
         }

         var2 = var6;
      } else {
         var2 = false;
      }

      return var2;
   }

   // $FF: synthetic method
   static int hashCode$(final CounterLike $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      return this.data().hashCode();
   }

   // $FF: synthetic method
   static scala.collection.immutable.Map toMap$(final CounterLike $this) {
      return $this.toMap();
   }

   default scala.collection.immutable.Map toMap() {
      return this.data().toMap(.MODULE$.refl());
   }

   static void $init$(final CounterLike $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
