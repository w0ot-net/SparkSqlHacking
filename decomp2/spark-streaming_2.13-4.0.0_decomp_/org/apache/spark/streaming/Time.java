package org.apache.spark.streaming;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichLong;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t-a\u0001\u0002\u00180\u0001bB\u0001B\u0014\u0001\u0003\u0006\u0004%Ia\u0014\u0005\t'\u0002\u0011\t\u0012)A\u0005!\")A\u000b\u0001C\u0001+\")\u0011\f\u0001C\u0001\u001f\")!\f\u0001C\u00017\")\u0011\r\u0001C\u0001E\")A\r\u0001C\u0001K\")q\r\u0001C\u0001Q\")!\u000e\u0001C\u0001W\")\u0001\u000f\u0001C\u0001c\")\u0001\u000f\u0001C\u0001g\")Q\u000f\u0001C\u0001m\")\u0001\u0010\u0001C\u0001s\")1\u0010\u0001C\u0001y\")a\u0010\u0001C\u0001\u007f\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\u0005\u0001\u0011\u0005\u00111\u0002\u0005\b\u0003\u0013\u0001A\u0011AA\b\u0011\u001d\t\u0019\u0002\u0001C\u0001\u0003+Aq!a\u0005\u0001\t\u0003\tI\u0002C\u0004\u0002\"\u0001!\t!a\t\t\u000f\u0005\u001d\u0002\u0001\"\u0001\u0002*!9\u0011Q\u0006\u0001\u0005\u0002\u0005=\u0002bBA\u001a\u0001\u0011\u0005\u0011Q\u0007\u0005\b\u0003\u0007\u0002A\u0011AA#\u0011\u001d\tY\u0005\u0001C!\u0003\u001bB\u0011\"a\u0018\u0001\u0003\u0003%\t!!\u0019\t\u0013\u0005\u0015\u0004!%A\u0005\u0002\u0005\u001d\u0004\u0002CA?\u0001-\u0005I\u0011A(\t\u0013\u0005}\u0004!!A\u0005B\u0005\u0005\u0005\"CAI\u0001\u0005\u0005I\u0011AAJ\u0011%\tY\nAA\u0001\n\u0003\ti\nC\u0005\u0002*\u0002\t\t\u0011\"\u0011\u0002,\"I\u0011\u0011\u0018\u0001\u0002\u0002\u0013\u0005\u00111\u0018\u0005\n\u0003\u007f\u0003\u0011\u0011!C!\u0003\u0003D\u0011\"!2\u0001\u0003\u0003%\t%a2\t\u0013\u0005%\u0007!!A\u0005B\u0005-waBAh_!\u0005\u0011\u0011\u001b\u0004\u0007]=B\t!a5\t\rQ;C\u0011AAp\u0011%\t\to\nb\u0001\n\u0007\t\u0019\u000f\u0003\u0005\u0002l\u001e\u0002\u000b\u0011BAs\u0011%\tioJA\u0001\n\u0003\u000by\u000fC\u0005\u0002t\u001e\n\t\u0011\"!\u0002v\"I!\u0011A\u0014\u0002\u0002\u0013%!1\u0001\u0002\u0005)&lWM\u0003\u00021c\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003eM\nQa\u001d9be.T!\u0001N\u001b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0014aA8sO\u000e\u00011\u0003\u0002\u0001:\u007f\t\u0003\"AO\u001f\u000e\u0003mR\u0011\u0001P\u0001\u0006g\u000e\fG.Y\u0005\u0003}m\u0012a!\u00118z%\u00164\u0007C\u0001\u001eA\u0013\t\t5HA\u0004Qe>$Wo\u0019;\u0011\u0005\r[eB\u0001#J\u001d\t)\u0005*D\u0001G\u0015\t9u'\u0001\u0004=e>|GOP\u0005\u0002y%\u0011!jO\u0001\ba\u0006\u001c7.Y4f\u0013\taUJ\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Kw\u00051Q.\u001b7mSN,\u0012\u0001\u0015\t\u0003uEK!AU\u001e\u0003\t1{gnZ\u0001\b[&dG.[:!\u0003\u0019a\u0014N\\5u}Q\u0011a\u000b\u0017\t\u0003/\u0002i\u0011a\f\u0005\u0006\u001d\u000e\u0001\r\u0001U\u0001\r[&dG.[:fG>tGm]\u0001\u0006I1,7o\u001d\u000b\u00039~\u0003\"AO/\n\u0005y[$a\u0002\"p_2,\u0017M\u001c\u0005\u0006A\u0016\u0001\rAV\u0001\u0005i\"\fG/\u0001\u0005%Y\u0016\u001c8\u000fJ3r)\ta6\rC\u0003a\r\u0001\u0007a+\u0001\u0005%OJ,\u0017\r^3s)\taf\rC\u0003a\u000f\u0001\u0007a+A\u0006%OJ,\u0017\r^3sI\u0015\fHC\u0001/j\u0011\u0015\u0001\u0007\u00021\u0001W\u0003\u0015!\u0003\u000f\\;t)\t1F\u000eC\u0003a\u0013\u0001\u0007Q\u000e\u0005\u0002X]&\u0011qn\f\u0002\t\tV\u0014\u0018\r^5p]\u00061A%\\5okN$\"!\u001c:\t\u000b\u0001T\u0001\u0019\u0001,\u0015\u0005Y#\b\"\u00021\f\u0001\u0004i\u0017\u0001\u00027fgN$\"\u0001X<\t\u000b\u0001d\u0001\u0019\u0001,\u0002\r1,7o]#r)\ta&\u0010C\u0003a\u001b\u0001\u0007a+A\u0004he\u0016\fG/\u001a:\u0015\u0005qk\b\"\u00021\u000f\u0001\u00041\u0016!C4sK\u0006$XM]#r)\ra\u0016\u0011\u0001\u0005\u0006A>\u0001\rAV\u0001\u0005a2,8\u000fF\u0002W\u0003\u000fAQ\u0001\u0019\tA\u00025\fQ!\\5okN$2!\\A\u0007\u0011\u0015\u0001\u0017\u00031\u0001W)\r1\u0016\u0011\u0003\u0005\u0006AJ\u0001\r!\\\u0001\u0006M2|wN\u001d\u000b\u0004-\u0006]\u0001\"\u00021\u0014\u0001\u0004iG#\u0002,\u0002\u001c\u0005u\u0001\"\u00021\u0015\u0001\u0004i\u0007BBA\u0010)\u0001\u0007a+\u0001\u0005{KJ|G+[7f\u00031I7/T;mi&\u0004H.Z(g)\ra\u0016Q\u0005\u0005\u0006AV\u0001\r!\\\u0001\u0004[&tGc\u0001,\u0002,!)\u0001M\u0006a\u0001-\u0006\u0019Q.\u0019=\u0015\u0007Y\u000b\t\u0004C\u0003a/\u0001\u0007a+A\u0003v]RLG\u000e\u0006\u0004\u00028\u0005u\u0012q\b\t\u0005\u0007\u0006eb+C\u0002\u0002<5\u00131aU3r\u0011\u0015\u0001\u0007\u00041\u0001W\u0011\u0019\t\t\u0005\u0007a\u0001[\u0006A\u0011N\u001c;feZ\fG.\u0001\u0002u_R1\u0011qGA$\u0003\u0013BQ\u0001Y\rA\u0002YCa!!\u0011\u001a\u0001\u0004i\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005=\u0003\u0003BA)\u00033rA!a\u0015\u0002VA\u0011QiO\u0005\u0004\u0003/Z\u0014A\u0002)sK\u0012,g-\u0003\u0003\u0002\\\u0005u#AB*ue&twMC\u0002\u0002Xm\nAaY8qsR\u0019a+a\u0019\t\u000f9[\u0002\u0013!a\u0001!\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA5U\r\u0001\u00161N\u0016\u0003\u0003[\u0002B!a\u001c\u0002z5\u0011\u0011\u0011\u000f\u0006\u0005\u0003g\n)(A\u0005v]\u000eDWmY6fI*\u0019\u0011qO\u001e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002|\u0005E$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006yQ.\u001b7mSN$\u0013mY2fgN$\u0003'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003\u0007\u0003B!!\"\u0002\u00106\u0011\u0011q\u0011\u0006\u0005\u0003\u0013\u000bY)\u0001\u0003mC:<'BAAG\u0003\u0011Q\u0017M^1\n\t\u0005m\u0013qQ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003+\u00032AOAL\u0013\r\tIj\u000f\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003?\u000b)\u000bE\u0002;\u0003CK1!a)<\u0005\r\te.\u001f\u0005\n\u0003O\u0003\u0013\u0011!a\u0001\u0003+\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAW!\u0019\ty+!.\u0002 6\u0011\u0011\u0011\u0017\u0006\u0004\u0003g[\u0014AC2pY2,7\r^5p]&!\u0011qWAY\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007q\u000bi\fC\u0005\u0002(\n\n\t\u00111\u0001\u0002 \u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t\u0019)a1\t\u0013\u0005\u001d6%!AA\u0002\u0005U\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005U\u0015AB3rk\u0006d7\u000fF\u0002]\u0003\u001bD\u0011\"a*&\u0003\u0003\u0005\r!a(\u0002\tQKW.\u001a\t\u0003/\u001e\u001aBaJ\u001d\u0002VB!\u0011q[Ao\u001b\t\tIN\u0003\u0003\u0002\\\u0006-\u0015AA5p\u0013\ra\u0015\u0011\u001c\u000b\u0003\u0003#\f\u0001b\u001c:eKJLgnZ\u000b\u0003\u0003K\u0004BaQAt-&\u0019\u0011\u0011^'\u0003\u0011=\u0013H-\u001a:j]\u001e\f\u0011b\u001c:eKJLgn\u001a\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007Y\u000b\t\u0010C\u0003OW\u0001\u0007\u0001+A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005]\u0018Q \t\u0005u\u0005e\b+C\u0002\u0002|n\u0012aa\u00149uS>t\u0007\u0002CA\u0000Y\u0005\u0005\t\u0019\u0001,\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003\u0006A!\u0011Q\u0011B\u0004\u0013\u0011\u0011I!a\"\u0003\r=\u0013'.Z2u\u0001"
)
public class Time implements Product, Serializable {
   private final long org$apache$spark$streaming$Time$$millis;

   public static Option unapply(final Time x$0) {
      return Time$.MODULE$.unapply(x$0);
   }

   public static Time apply(final long millis) {
      return Time$.MODULE$.apply(millis);
   }

   public static Ordering ordering() {
      return Time$.MODULE$.ordering();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long millis$access$0() {
      return this.org$apache$spark$streaming$Time$$millis;
   }

   public long org$apache$spark$streaming$Time$$millis() {
      return this.org$apache$spark$streaming$Time$$millis;
   }

   public long milliseconds() {
      return this.org$apache$spark$streaming$Time$$millis();
   }

   public boolean $less(final Time that) {
      return this.org$apache$spark$streaming$Time$$millis() < that.org$apache$spark$streaming$Time$$millis();
   }

   public boolean $less$eq(final Time that) {
      return this.org$apache$spark$streaming$Time$$millis() <= that.org$apache$spark$streaming$Time$$millis();
   }

   public boolean $greater(final Time that) {
      return this.org$apache$spark$streaming$Time$$millis() > that.org$apache$spark$streaming$Time$$millis();
   }

   public boolean $greater$eq(final Time that) {
      return this.org$apache$spark$streaming$Time$$millis() >= that.org$apache$spark$streaming$Time$$millis();
   }

   public Time $plus(final Duration that) {
      return new Time(this.org$apache$spark$streaming$Time$$millis() + that.milliseconds());
   }

   public Duration $minus(final Time that) {
      return new Duration(this.org$apache$spark$streaming$Time$$millis() - that.org$apache$spark$streaming$Time$$millis());
   }

   public Time $minus(final Duration that) {
      return new Time(this.org$apache$spark$streaming$Time$$millis() - that.milliseconds());
   }

   public boolean less(final Time that) {
      return this.$less(that);
   }

   public boolean lessEq(final Time that) {
      return this.$less$eq(that);
   }

   public boolean greater(final Time that) {
      return this.$greater(that);
   }

   public boolean greaterEq(final Time that) {
      return this.$greater$eq(that);
   }

   public Time plus(final Duration that) {
      return this.$plus(that);
   }

   public Duration minus(final Time that) {
      return this.$minus(that);
   }

   public Time minus(final Duration that) {
      return this.$minus(that);
   }

   public Time floor(final Duration that) {
      long t = that.milliseconds();
      return new Time(this.org$apache$spark$streaming$Time$$millis() / t * t);
   }

   public Time floor(final Duration that, final Time zeroTime) {
      long t = that.milliseconds();
      return new Time((this.org$apache$spark$streaming$Time$$millis() - zeroTime.milliseconds()) / t * t + zeroTime.milliseconds());
   }

   public boolean isMultipleOf(final Duration that) {
      return this.org$apache$spark$streaming$Time$$millis() % that.milliseconds() == 0L;
   }

   public Time min(final Time that) {
      return this.$less(that) ? this : that;
   }

   public Time max(final Time that) {
      return this.$greater(that) ? this : that;
   }

   public Seq until(final Time that, final Duration interval) {
      return (Seq)(new RichLong(.MODULE$.longWrapper(this.milliseconds()))).until(BoxesRunTime.boxToLong(that.milliseconds())).by(BoxesRunTime.boxToLong(interval.milliseconds())).map((x$1) -> $anonfun$until$1(BoxesRunTime.unboxToLong(x$1)));
   }

   public Seq to(final Time that, final Duration interval) {
      return (Seq)(new RichLong(.MODULE$.longWrapper(this.milliseconds()))).to(BoxesRunTime.boxToLong(that.milliseconds())).by(BoxesRunTime.boxToLong(interval.milliseconds())).map((x$2) -> $anonfun$to$1(BoxesRunTime.unboxToLong(x$2)));
   }

   public String toString() {
      return Long.toString(this.org$apache$spark$streaming$Time$$millis()) + " ms";
   }

   public Time copy(final long millis) {
      return new Time(millis);
   }

   public long copy$default$1() {
      return this.org$apache$spark$streaming$Time$$millis();
   }

   public String productPrefix() {
      return "Time";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.millis$access$0());
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Time;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "millis";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.millis$access$0()));
      return Statics.finalizeHash(var1, 1);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof Time) {
               Time var4 = (Time)x$1;
               if (this.millis$access$0() == var4.millis$access$0() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final Time $anonfun$until$1(final long x$1) {
      return new Time(x$1);
   }

   // $FF: synthetic method
   public static final Time $anonfun$to$1(final long x$2) {
      return new Time(x$2);
   }

   public Time(final long millis) {
      this.org$apache$spark$streaming$Time$$millis = millis;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
