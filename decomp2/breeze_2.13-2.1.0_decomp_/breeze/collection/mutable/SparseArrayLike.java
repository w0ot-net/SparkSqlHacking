package breeze.collection.mutable;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Array.;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001daaB\b\u0011!\u0003\r\ta\u0006\u0005\u0006?\u0001!\t\u0001\t\u0005\u0006I\u00011\t!\n\u0005\u0006m\u00011\ta\u000e\u0005\u0006w\u00011\t\u0001\u0010\u0005\u0006\u0013\u00021\tA\u0013\u0005\u0006\u0019\u00021\t!\u0014\u0005\u0006\u001d\u00021\t!\u0014\u0005\u0006\u001f\u0002!\t!\u0014\u0005\u0006!\u0002!\t!\u0015\u0005\u00067\u0002!\t\u0001\u0018\u0005\u0006I\u0002!\t!\u001a\u0005\u0006k\u0002!\tA\u001e\u0005\u0006{\u0002!\tA\u001e\u0005\u0006}\u0002!\ta \u0002\u0010'B\f'o]3BeJ\f\u0017\u0010T5lK*\u0011\u0011CE\u0001\b[V$\u0018M\u00197f\u0015\t\u0019B#\u0001\u0006d_2dWm\u0019;j_:T\u0011!F\u0001\u0007EJ,WM_3\u0004\u0001U\u0011\u0001\u0004K\n\u0003\u0001e\u0001\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\"!\tQ\"%\u0003\u0002$7\t!QK\\5u\u0003\u0015\t\u0007\u000f\u001d7z)\t1\u0013\u0007\u0005\u0002(Q1\u0001A!B\u0015\u0001\u0005\u0004Q#!\u0001,\u0012\u0005-r\u0003C\u0001\u000e-\u0013\ti3DA\u0004O_RD\u0017N\\4\u0011\u0005iy\u0013B\u0001\u0019\u001c\u0005\r\te.\u001f\u0005\u0006e\t\u0001\raM\u0001\u0002SB\u0011!\u0004N\u0005\u0003km\u00111!\u00138u\u0003\u0019)\b\u000fZ1uKR\u0019\u0011\u0005O\u001d\t\u000bI\u001a\u0001\u0019A\u001a\t\u000bi\u001a\u0001\u0019\u0001\u0014\u0002\u0003Q\faB^1mk\u0016\u001c\u0018\n^3sCR|'/F\u0001>!\rqdI\n\b\u0003\u007f\u0011s!\u0001Q\"\u000e\u0003\u0005S!A\u0011\f\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0012BA#\u001c\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0012%\u0003\u0011%#XM]1u_JT!!R\u000e\u0002\u0019-,\u0017p]%uKJ\fGo\u001c:\u0016\u0003-\u00032A\u0010$4\u0003)\t7\r^5wKNK'0Z\u000b\u0002g\u0005!1/\u001b>f\u0003\u0019aWM\\4uQ\u00069am\u001c:fC\u000eDWC\u0001*Z)\t\t3\u000bC\u0003U\u0013\u0001\u0007Q+A\u0001g!\u0011QbK\n-\n\u0005][\"!\u0003$v]\u000e$\u0018n\u001c82!\t9\u0013\fB\u0003[\u0013\t\u0007!FA\u0001V\u0003!IG/\u001a:bi>\u0014X#A/\u0011\u0007y\u0003\u0017-D\u0001`\u0015\t\u00192$\u0003\u0002H?B!!DY\u001a'\u0013\t\u00197D\u0001\u0004UkBdWMM\u0001\bi>\f%O]1z+\t17\u000e\u0006\u0002h[B\u0019!\u0004\u001b6\n\u0005%\\\"!B!se\u0006L\bCA\u0014l\t\u0015Q6B1\u0001m#\t1c\u0006C\u0004o\u0017\u0005\u0005\t9A8\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002qg*l\u0011!\u001d\u0006\u0003en\tqA]3gY\u0016\u001cG/\u0003\u0002uc\nA1\t\\1tgR\u000bw-\u0001\u0004u_2K7\u000f^\u000b\u0002oB\u0019\u0001p\u001f\u0014\u000e\u0003eT!A_0\u0002\u0013%lW.\u001e;bE2,\u0017B\u0001?z\u0005\u0011a\u0015n\u001d;\u0002\u0019Q|\u0017J\u001c3fq\u0016$7+Z9\u0002\u000bQ|W*\u00199\u0016\u0005\u0005\u0005\u0001#\u0002=\u0002\u0004M2\u0013bAA\u0003s\n\u0019Q*\u00199"
)
public interface SparseArrayLike {
   Object apply(final int i);

   void update(final int i, final Object t);

   Iterator valuesIterator();

   Iterator keysIterator();

   int activeSize();

   int size();

   // $FF: synthetic method
   static int length$(final SparseArrayLike $this) {
      return $this.length();
   }

   default int length() {
      return this.size();
   }

   // $FF: synthetic method
   static void foreach$(final SparseArrayLike $this, final Function1 f) {
      $this.foreach(f);
   }

   default void foreach(final Function1 f) {
      this.valuesIterator().foreach(f);
   }

   // $FF: synthetic method
   static Iterator iterator$(final SparseArrayLike $this) {
      return $this.iterator();
   }

   default Iterator iterator() {
      return this.keysIterator().zip(this.valuesIterator());
   }

   // $FF: synthetic method
   static Object toArray$(final SparseArrayLike $this, final ClassTag evidence$1) {
      return $this.toArray(evidence$1);
   }

   default Object toArray(final ClassTag evidence$1) {
      return .MODULE$.tabulate(this.length(), (i) -> $anonfun$toArray$1(this, BoxesRunTime.unboxToInt(i)), evidence$1);
   }

   // $FF: synthetic method
   static List toList$(final SparseArrayLike $this) {
      return $this.toList();
   }

   default List toList() {
      return (List)scala.package..MODULE$.List().tabulate(this.length(), (i) -> $anonfun$toList$1(this, BoxesRunTime.unboxToInt(i)));
   }

   // $FF: synthetic method
   static List toIndexedSeq$(final SparseArrayLike $this) {
      return $this.toIndexedSeq();
   }

   default List toIndexedSeq() {
      return (List)scala.package..MODULE$.List().tabulate(this.length(), (i) -> $anonfun$toIndexedSeq$1(this, BoxesRunTime.unboxToInt(i)));
   }

   // $FF: synthetic method
   static Map toMap$(final SparseArrayLike $this) {
      return $this.toMap();
   }

   default Map toMap() {
      return this.keysIterator().zip(this.valuesIterator()).toMap(scala..less.colon.less..MODULE$.refl());
   }

   // $FF: synthetic method
   static Object $anonfun$toArray$1(final SparseArrayLike $this, final int i) {
      return $this.apply(i);
   }

   // $FF: synthetic method
   static Object $anonfun$toList$1(final SparseArrayLike $this, final int i) {
      return $this.apply(i);
   }

   // $FF: synthetic method
   static Object $anonfun$toIndexedSeq$1(final SparseArrayLike $this, final int i) {
      return $this.apply(i);
   }

   static void $init$(final SparseArrayLike $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
