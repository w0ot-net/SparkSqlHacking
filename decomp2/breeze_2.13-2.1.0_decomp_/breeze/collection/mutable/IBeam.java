package breeze.collection.mutable;

import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Cloneable;
import scala.collection.mutable.Shrinkable;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A4q!\u0003\u0006\u0011\u0002\u0007\u0005\u0011\u0003C\u0003>\u0001\u0011\u0005a\bC\u0003C\u0001\u0011\u00053\tC\u0003H\u0001\u0019E\u0001\nC\u0003V\u0001\u0019\u0005a\u000bC\u0003a\u0001\u0011\u0005\u0011\rC\u0003e\u0001\u0011ES\rC\u0003l\u0001\u0011EC\u000eC\u0003o\u0001\u0011\u0005sNA\u0003J\u0005\u0016\fWN\u0003\u0002\f\u0019\u00059Q.\u001e;bE2,'BA\u0007\u000f\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u001f\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0002\u0013AM9\u0001aE\r*cQJ\u0004C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\rE\u0002\u001b9yi\u0011a\u0007\u0006\u0003\u001bUI!!H\u000e\u0003\u0011%#XM]1cY\u0016\u0004\"a\b\u0011\r\u0001\u0011)\u0011\u0005\u0001b\u0001E\t\tA+\u0005\u0002$MA\u0011A\u0003J\u0005\u0003KU\u0011qAT8uQ&tw\r\u0005\u0002\u0015O%\u0011\u0001&\u0006\u0002\u0004\u0003:L\b\u0003\u0002\u0016-=9j\u0011a\u000b\u0006\u0003\u0017mI!!L\u0016\u0003\u000f\t+\u0018\u000e\u001c3feB\u0019!d\f\u0010\n\u0005AZ\"AC%oI\u0016DX\rZ*fcB\u0019!F\r\u0010\n\u0005MZ#AC*ie&t7.\u00192mKB\u0019!&N\u001c\n\u0005YZ#!C\"m_:,\u0017M\u00197f!\rA\u0004AH\u0007\u0002\u0015A)!D\u000f\u0010=o%\u00111h\u0007\u0002\f\u0013R,'/\u00192mK>\u00038\u000f\u0005\u0002\u001b9\u00051A%\u001b8ji\u0012\"\u0012a\u0010\t\u0003)\u0001K!!Q\u000b\u0003\tUs\u0017\u000e^\u0001\nW:|wO\\*ju\u0016,\u0012\u0001\u0012\t\u0003)\u0015K!AR\u000b\u0003\u0007%sG/\u0001\u0005pe\u0012,'/\u001b8h+\u0005I\u0005c\u0001&S=9\u00111\n\u0015\b\u0003\u0019>k\u0011!\u0014\u0006\u0003\u001dB\ta\u0001\u0010:p_Rt\u0014\"\u0001\f\n\u0005E+\u0012a\u00029bG.\fw-Z\u0005\u0003'R\u0013\u0001b\u0014:eKJLgn\u001a\u0006\u0003#V\t!b\u00195fG.,G-\u00113e)\t9f\fE\u0002Y7zq!\u0001O-\n\u0005iS\u0011\u0001\u0002\"fC6L!\u0001X/\u0003\u0015\t+\u0017-\u001c*fgVdGO\u0003\u0002[\u0015!)q\f\u0002a\u0001=\u0005\t\u00010\u0001\u0004bI\u0012|e.\u001a\u000b\u0003E\u000el\u0011\u0001\u0001\u0005\u0006?\u0016\u0001\rAH\u0001\rMJ|Wn\u00159fG&4\u0017n\u0019\u000b\u0003o\u0019DQa\u001a\u0004A\u0002!\fAaY8mYB\u0019!$\u001b\u0010\n\u0005)\\\"\u0001D%uKJ\f'\r\\3P]\u000e,\u0017A\u00058foN\u0003XmY5gS\u000e\u0014U/\u001b7eKJ,\u0012!\u001c\t\u0005U1rr'A\u0003f[B$\u00180F\u00018\u0001"
)
public interface IBeam extends Iterable, Builder, Shrinkable, Cloneable {
   // $FF: synthetic method
   static int knownSize$(final IBeam $this) {
      return $this.knownSize();
   }

   default int knownSize() {
      return this.size();
   }

   Ordering ordering();

   Beam.BeamResult checkedAdd(final Object x);

   // $FF: synthetic method
   static IBeam addOne$(final IBeam $this, final Object x) {
      return $this.addOne(x);
   }

   default IBeam addOne(final Object x) {
      this.checkedAdd(x);
      return this;
   }

   // $FF: synthetic method
   static IBeam fromSpecific$(final IBeam $this, final IterableOnce coll) {
      return $this.fromSpecific(coll);
   }

   default IBeam fromSpecific(final IterableOnce coll) {
      throw .MODULE$.$qmark$qmark$qmark();
   }

   // $FF: synthetic method
   static Builder newSpecificBuilder$(final IBeam $this) {
      return $this.newSpecificBuilder();
   }

   default Builder newSpecificBuilder() {
      throw .MODULE$.$qmark$qmark$qmark();
   }

   // $FF: synthetic method
   static IBeam empty$(final IBeam $this) {
      return $this.empty();
   }

   default IBeam empty() {
      throw .MODULE$.$qmark$qmark$qmark();
   }

   static void $init$(final IBeam $this) {
   }
}
