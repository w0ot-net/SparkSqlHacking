package breeze.linalg;

import breeze.linalg.operators.HasOps;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.Set;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}aaB\u0007\u000f!\u0003\r\ta\u0005\u0005\u0006C\u0001!\tA\t\u0005\u0006M\u00011\ta\n\u0005\u00061\u00021\t!\u0017\u0005\u0006;\u00021\tA\u0018\u0005\u0006K\u00021\tA\u001a\u0005\u0006c\u00021\tA\u001a\u0005\u0006e\u00021\ta\u001d\u0005\u0006k\u00021\ta\u001d\u0005\u0006m\u00021\ta\u001e\u0005\u0006s\u00021\ta\u001e\u0005\u0006u\u0002!\ta\u001f\u0005\b\u0003+\u0001A\u0011IA\f\u0005-\tV/Y:j)\u0016t7o\u001c:\u000b\u0005=\u0001\u0012A\u00027j]\u0006dwMC\u0001\u0012\u0003\u0019\u0011'/Z3{K\u000e\u0001Qc\u0001\u000bTUM\u0019\u0001!F\u000e\u0011\u0005YIR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\r\u0005s\u0017PU3g!\tar$D\u0001\u001e\u0015\tqb\"A\u0005pa\u0016\u0014\u0018\r^8sg&\u0011\u0001%\b\u0002\u0007\u0011\u0006\u001cx\n]:\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0003C\u0001\f%\u0013\t)sC\u0001\u0003V]&$\u0018!B1qa2LHC\u0001\u0015Q!\tI#\u0006\u0004\u0001\u0005\u0013-\u0002\u0001\u0015!A\u0001\u0006\u0004a#!\u0001,\u0012\u00055\u0002\u0004C\u0001\f/\u0013\tysCA\u0004O_RD\u0017N\\4\u0011\u0005Y\t\u0014B\u0001\u001a\u0018\u0005\r\te.\u001f\u0015\u0007UQ:\u0014IR&\u0011\u0005Y)\u0014B\u0001\u001c\u0018\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rB\u0014h\u000f\u001e\u000f\u0005YI\u0014B\u0001\u001e\u0018\u0003\u0019!u.\u001e2mKF\"A\u0005\u0010!\u0019\u001d\ti\u0004)D\u0001?\u0015\ty$#\u0001\u0004=e>|GOP\u0005\u00021E*1EQ\"F\t:\u0011acQ\u0005\u0003\t^\t1!\u00138uc\u0011!C\b\u0011\r2\u000b\r:\u0005JS%\u000f\u0005YA\u0015BA%\u0018\u0003\u00151En\\1uc\u0011!C\b\u0011\r2\u000b\rbUj\u0014(\u000f\u0005Yi\u0015B\u0001(\u0018\u0003\u0011auN\\42\t\u0011b\u0004\t\u0007\u0005\u0006#\n\u0001\rAU\u0001\u0002SB\u0011\u0011f\u0015\u0003\n)\u0002\u0001\u000b\u0011!AC\u00021\u0012\u0011a\u0013\u0015\u0004'R2\u0016'B\u0012C\u0007^#\u0015\u0007\u0002\u0013=\u0001b\ta!\u001e9eCR,GcA\u0012[7\")\u0011k\u0001a\u0001%\")Al\u0001a\u0001Q\u0005\ta/\u0001\u0004lKf\u001cV\r^\u000b\u0002?B\u0019\u0001m\u0019*\u000e\u0003\u0005T!AY\f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002eC\n\u00191+\u001a;\u0002\u0011%$XM]1u_J,\u0012a\u001a\t\u0004Q.tgB\u0001\u001fj\u0013\tQw#A\u0004qC\u000e\\\u0017mZ3\n\u00051l'\u0001C%uKJ\fGo\u001c:\u000b\u0005)<\u0002\u0003\u0002\fp%\"J!\u0001]\f\u0003\rQ+\b\u000f\\33\u00039\t7\r^5wK&#XM]1u_J\faB^1mk\u0016\u001c\u0018\n^3sCR|'/F\u0001u!\rA7\u000eK\u0001\u0015C\u000e$\u0018N^3WC2,Xm]%uKJ\fGo\u001c:\u0002\u0019-,\u0017p]%uKJ\fGo\u001c:\u0016\u0003a\u00042\u0001[6S\u0003I\t7\r^5wK.+\u0017p]%uKJ\fGo\u001c:\u0002\u000f\u0019Lg\u000eZ!mYR\u0019A0!\u0002\u0011\tu\f\tAU\u0007\u0002}*\u0011q0Y\u0001\nS6lW\u000f^1cY\u0016L1!a\u0001\u007f\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\u0005\b\u0003\u000fY\u0001\u0019AA\u0005\u0003\u00051\u0007C\u0002\f\u0002\f!\ny!C\u0002\u0002\u000e]\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0007Y\t\t\"C\u0002\u0002\u0014]\u0011qAQ8pY\u0016\fg.\u0001\u0005iCND7i\u001c3f)\t\tI\u0002E\u0002\u0017\u00037I1!!\b\u0018\u0005\rIe\u000e\u001e"
)
public interface QuasiTensor extends HasOps {
   Object apply(final Object i);

   void update(final Object i, final Object v);

   Set keySet();

   Iterator iterator();

   Iterator activeIterator();

   Iterator valuesIterator();

   Iterator activeValuesIterator();

   Iterator keysIterator();

   Iterator activeKeysIterator();

   // $FF: synthetic method
   static IndexedSeq findAll$(final QuasiTensor $this, final Function1 f) {
      return $this.findAll(f);
   }

   default IndexedSeq findAll(final Function1 f) {
      return this.activeIterator().filter((p) -> BoxesRunTime.boxToBoolean($anonfun$findAll$1(f, p))).map((x$1) -> x$1._1()).toIndexedSeq();
   }

   // $FF: synthetic method
   static int hashCode$(final QuasiTensor $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      IntRef hash = IntRef.create(43);
      this.activeValuesIterator().foreach((v) -> {
         $anonfun$hashCode$1(hash, v);
         return BoxedUnit.UNIT;
      });
      return hash.elem;
   }

   // $FF: synthetic method
   static double apply$mcID$sp$(final QuasiTensor $this, final int i) {
      return $this.apply$mcID$sp(i);
   }

   default double apply$mcID$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(i)));
   }

   // $FF: synthetic method
   static float apply$mcIF$sp$(final QuasiTensor $this, final int i) {
      return $this.apply$mcIF$sp(i);
   }

   default float apply$mcIF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(i)));
   }

   // $FF: synthetic method
   static int apply$mcII$sp$(final QuasiTensor $this, final int i) {
      return $this.apply$mcII$sp(i);
   }

   default int apply$mcII$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(i)));
   }

   // $FF: synthetic method
   static long apply$mcIJ$sp$(final QuasiTensor $this, final int i) {
      return $this.apply$mcIJ$sp(i);
   }

   default long apply$mcIJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.apply(BoxesRunTime.boxToInteger(i)));
   }

   // $FF: synthetic method
   static void update$mcID$sp$(final QuasiTensor $this, final int i, final double v) {
      $this.update$mcID$sp(i, v);
   }

   default void update$mcID$sp(final int i, final double v) {
      this.update(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToDouble(v));
   }

   // $FF: synthetic method
   static void update$mcIF$sp$(final QuasiTensor $this, final int i, final float v) {
      $this.update$mcIF$sp(i, v);
   }

   default void update$mcIF$sp(final int i, final float v) {
      this.update(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToFloat(v));
   }

   // $FF: synthetic method
   static void update$mcII$sp$(final QuasiTensor $this, final int i, final int v) {
      $this.update$mcII$sp(i, v);
   }

   default void update$mcII$sp(final int i, final int v) {
      this.update(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToInteger(v));
   }

   // $FF: synthetic method
   static void update$mcIJ$sp$(final QuasiTensor $this, final int i, final long v) {
      $this.update$mcIJ$sp(i, v);
   }

   default void update$mcIJ$sp(final int i, final long v) {
      this.update(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToLong(v));
   }

   // $FF: synthetic method
   static IndexedSeq findAll$mcD$sp$(final QuasiTensor $this, final Function1 f) {
      return $this.findAll$mcD$sp(f);
   }

   default IndexedSeq findAll$mcD$sp(final Function1 f) {
      return this.findAll(f);
   }

   // $FF: synthetic method
   static IndexedSeq findAll$mcF$sp$(final QuasiTensor $this, final Function1 f) {
      return $this.findAll$mcF$sp(f);
   }

   default IndexedSeq findAll$mcF$sp(final Function1 f) {
      return this.findAll(f);
   }

   // $FF: synthetic method
   static IndexedSeq findAll$mcI$sp$(final QuasiTensor $this, final Function1 f) {
      return $this.findAll$mcI$sp(f);
   }

   default IndexedSeq findAll$mcI$sp(final Function1 f) {
      return this.findAll(f);
   }

   // $FF: synthetic method
   static IndexedSeq findAll$mcJ$sp$(final QuasiTensor $this, final Function1 f) {
      return $this.findAll$mcJ$sp(f);
   }

   default IndexedSeq findAll$mcJ$sp(final Function1 f) {
      return this.findAll(f);
   }

   // $FF: synthetic method
   static boolean $anonfun$findAll$1(final Function1 f$1, final Tuple2 p) {
      return BoxesRunTime.unboxToBoolean(f$1.apply(p._2()));
   }

   // $FF: synthetic method
   static void $anonfun$hashCode$1(final IntRef hash$1, final Object v) {
      int hh = Statics.anyHash(v);
      if (hh != 0) {
         hash$1.elem = .MODULE$.mix(hash$1.elem, hh);
      }

   }

   static void $init$(final QuasiTensor $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
