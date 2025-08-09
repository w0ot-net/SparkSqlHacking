package scala;

import scala.collection.IndexedSeq;
import scala.collection.StringOps$;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map$;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set$;
import scala.reflect.Manifest;
import scala.reflect.Manifest$;
import scala.reflect.NoManifest$;
import scala.reflect.OptManifest;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

public final class Predef$ extends LowPriorityImplicits {
   public static final Predef$ MODULE$ = new Predef$();
   private static final Map$ Map;
   private static final Set$ Set;
   private static final Tuple2$ $minus$greater;
   private static final Manifest$ Manifest;
   private static final NoManifest$ NoManifest;

   static {
      package$ var10000 = package$.MODULE$;
      List$ var0 = List$.MODULE$;
      Map = Map$.MODULE$;
      Set = Set$.MODULE$;
      $minus$greater = Tuple2$.MODULE$;
      Manifest = Manifest$.MODULE$;
      NoManifest = NoManifest$.MODULE$;
   }

   public Class classOf() {
      return null;
   }

   public Object valueOf(final Object vt) {
      return vt;
   }

   public Map$ Map() {
      return Map;
   }

   public Set$ Set() {
      return Set;
   }

   public Tuple2$ $minus$greater() {
      return $minus$greater;
   }

   public Manifest$ Manifest() {
      return Manifest;
   }

   public NoManifest$ NoManifest() {
      return NoManifest;
   }

   public Manifest manifest(final Manifest m) {
      return m;
   }

   public OptManifest optManifest(final OptManifest m) {
      return m;
   }

   public Object identity(final Object x) {
      return x;
   }

   public Object implicitly(final Object e) {
      return e;
   }

   public Object locally(final Object x) {
      return x;
   }

   public void assert(final boolean assertion) {
      if (!assertion) {
         throw new AssertionError("assertion failed");
      }
   }

   public final void assert(final boolean assertion, final Function0 message) {
      if (!assertion) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append(message.apply()).toString());
      }
   }

   public void assume(final boolean assumption) {
      if (!assumption) {
         throw new AssertionError("assumption failed");
      }
   }

   public final void assume(final boolean assumption, final Function0 message) {
      if (!assumption) {
         throw new AssertionError((new StringBuilder(19)).append("assumption failed: ").append(message.apply()).toString());
      }
   }

   public void require(final boolean requirement) {
      if (!requirement) {
         throw new IllegalArgumentException("requirement failed");
      }
   }

   public final void require(final boolean requirement, final Function0 message) {
      if (!requirement) {
         throw new IllegalArgumentException((new StringBuilder(20)).append("requirement failed: ").append(message.apply()).toString());
      }
   }

   public Nothing$ $qmark$qmark$qmark() {
      throw new NotImplementedError();
   }

   public final Object ArrowAssoc(final Object self) {
      return self;
   }

   public final Object Ensuring(final Object self) {
      return self;
   }

   public final Object StringFormat(final Object self) {
      return self;
   }

   /** @deprecated */
   public final Object any2stringadd(final Object self) {
      return self;
   }

   public Predef.SeqCharSequence SeqCharSequence(final IndexedSeq sequenceOfChars) {
      return new Predef.SeqCharSequence(sequenceOfChars);
   }

   public Predef.ArrayCharSequence ArrayCharSequence(final char[] arrayOfChars) {
      return new Predef.ArrayCharSequence(arrayOfChars);
   }

   public String augmentString(final String x) {
      return x;
   }

   public void print(final Object x) {
      Console$.MODULE$.print(x);
   }

   public void println() {
      Console$.MODULE$.println();
   }

   public void println(final Object x) {
      Console$.MODULE$.println(x);
   }

   public void printf(final String text, final Seq xs) {
      Console$.MODULE$.print(StringOps$.MODULE$.format$extension(text, xs));
   }

   public Tuple2 tuple2ToZippedOps(final Tuple2 x) {
      return x;
   }

   public Tuple3 tuple3ToZippedOps(final Tuple3 x) {
      return x;
   }

   public Object genericArrayOps(final Object xs) {
      return xs;
   }

   public Object booleanArrayOps(final boolean[] xs) {
      return xs;
   }

   public Object byteArrayOps(final byte[] xs) {
      return xs;
   }

   public Object charArrayOps(final char[] xs) {
      return xs;
   }

   public Object doubleArrayOps(final double[] xs) {
      return xs;
   }

   public Object floatArrayOps(final float[] xs) {
      return xs;
   }

   public Object intArrayOps(final int[] xs) {
      return xs;
   }

   public Object longArrayOps(final long[] xs) {
      return xs;
   }

   public Object refArrayOps(final Object[] xs) {
      return xs;
   }

   public Object shortArrayOps(final short[] xs) {
      return xs;
   }

   public Object unitArrayOps(final BoxedUnit[] xs) {
      return xs;
   }

   public java.lang.Byte byte2Byte(final byte x) {
      return x;
   }

   public java.lang.Short short2Short(final short x) {
      return x;
   }

   public Character char2Character(final char x) {
      return x;
   }

   public Integer int2Integer(final int x) {
      return x;
   }

   public java.lang.Long long2Long(final long x) {
      return x;
   }

   public java.lang.Float float2Float(final float x) {
      return x;
   }

   public java.lang.Double double2Double(final double x) {
      return x;
   }

   public java.lang.Boolean boolean2Boolean(final boolean x) {
      return x;
   }

   public byte Byte2byte(final java.lang.Byte x) {
      return BoxesRunTime.unboxToByte(x);
   }

   public short Short2short(final java.lang.Short x) {
      return BoxesRunTime.unboxToShort(x);
   }

   public char Character2char(final Character x) {
      return BoxesRunTime.unboxToChar(x);
   }

   public int Integer2int(final Integer x) {
      return BoxesRunTime.unboxToInt(x);
   }

   public long Long2long(final java.lang.Long x) {
      return BoxesRunTime.unboxToLong(x);
   }

   public float Float2float(final java.lang.Float x) {
      return BoxesRunTime.unboxToFloat(x);
   }

   public double Double2double(final java.lang.Double x) {
      return BoxesRunTime.unboxToDouble(x);
   }

   public boolean Boolean2boolean(final java.lang.Boolean x) {
      return BoxesRunTime.unboxToBoolean(x);
   }

   public Function1 $conforms() {
      return $less$colon$less$.MODULE$.refl();
   }

   private Predef$() {
   }
}
