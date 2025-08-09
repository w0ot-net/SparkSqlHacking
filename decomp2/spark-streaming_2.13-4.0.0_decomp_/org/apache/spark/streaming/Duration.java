package org.apache.spark.streaming;

import java.io.Serializable;
import org.apache.spark.util.Utils.;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005a\u0001\u0002\u00180\u0001bB\u0001B\u0014\u0001\u0003\u0006\u0004%Ia\u0014\u0005\t'\u0002\u0011\t\u0012)A\u0005!\")A\u000b\u0001C\u0001+\")\u0011\f\u0001C\u00015\")\u0001\r\u0001C\u0001C\")1\r\u0001C\u0001I\")a\r\u0001C\u0001O\")\u0011\u000e\u0001C\u0001U\")A\u000e\u0001C\u0001[\")q\u000e\u0001C\u0001a\")a\u000f\u0001C\u0001o\")A\u0010\u0001C\u0001{\"1q\u0010\u0001C\u0001\u0003\u0003Aq!!\u0002\u0001\t\u0003\t9\u0001C\u0004\u0002\f\u0001!\t!!\u0004\t\u000f\u0005E\u0001\u0001\"\u0001\u0002\u0014!9\u0011q\u0003\u0001\u0005\u0002\u0005e\u0001B\u0002:\u0001\t\u0003\ti\u0002C\u0004\u0002\"\u0001!\t!a\t\t\u000f\u0005\u001d\u0002\u0001\"\u0001\u0002*!9\u0011Q\u0006\u0001\u0005\u0002\u0005=\u0002bBA\u001a\u0001\u0011\u0005\u0011Q\u0007\u0005\b\u0003s\u0001A\u0011AA\u001e\u0011\u001d\ti\u0004\u0001C!\u0003\u007fAq!!\u0015\u0001\t\u0003\t\u0019\u0006\u0003\u0004\u0002V\u0001!\ta\u0014\u0005\b\u0003/\u0002A\u0011AA*\u0011%\tI\u0006AA\u0001\n\u0003\tY\u0006C\u0005\u0002`\u0001\t\n\u0011\"\u0001\u0002b!A\u0011q\u000f\u0001\f\u0002\u0013\u0005q\nC\u0005\u0002z\u0001\t\t\u0011\"\u0011\u0002|!I\u00111\u0012\u0001\u0002\u0002\u0013\u0005\u0011Q\u0012\u0005\n\u0003\u001f\u0003\u0011\u0011!C\u0001\u0003#C\u0011\"!(\u0001\u0003\u0003%\t%a(\t\u0013\u00055\u0006!!A\u0005\u0002\u0005=\u0006\"CAZ\u0001\u0005\u0005I\u0011IA[\u0011%\tI\fAA\u0001\n\u0003\nY\fC\u0005\u0002>\u0002\t\t\u0011\"\u0011\u0002@\u001eI\u00111Y\u0018\u0002\u0002#\u0005\u0011Q\u0019\u0004\t]=\n\t\u0011#\u0001\u0002H\"1A\u000b\u000bC\u0001\u0003?D\u0011\"!\u0010)\u0003\u0003%)%!9\t\u0013\u0005\r\b&!A\u0005\u0002\u0006\u0015\b\"CAuQ\u0005\u0005I\u0011QAv\u0011%\t9\u0010KA\u0001\n\u0013\tIP\u0001\u0005EkJ\fG/[8o\u0015\t\u0001\u0014'A\u0005tiJ,\u0017-\\5oO*\u0011!gM\u0001\u0006gB\f'o\u001b\u0006\u0003iU\na!\u00199bG\",'\"\u0001\u001c\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001ItH\u0011\t\u0003uuj\u0011a\u000f\u0006\u0002y\u0005)1oY1mC&\u0011ah\u000f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005i\u0002\u0015BA!<\u0005\u001d\u0001&o\u001c3vGR\u0004\"aQ&\u000f\u0005\u0011KeBA#I\u001b\u00051%BA$8\u0003\u0019a$o\\8u}%\tA(\u0003\u0002Kw\u00059\u0001/Y2lC\u001e,\u0017B\u0001'N\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tQ5(\u0001\u0004nS2d\u0017n]\u000b\u0002!B\u0011!(U\u0005\u0003%n\u0012A\u0001T8oO\u00069Q.\u001b7mSN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002W1B\u0011q\u000bA\u0007\u0002_!)aj\u0001a\u0001!\u0006)A\u0005\\3tgR\u00111L\u0018\t\u0003uqK!!X\u001e\u0003\u000f\t{w\u000e\\3b]\")q\f\u0002a\u0001-\u0006!A\u000f[1u\u0003!!C.Z:tI\u0015\fHCA.c\u0011\u0015yV\u00011\u0001W\u0003!!sM]3bi\u0016\u0014HCA.f\u0011\u0015yf\u00011\u0001W\u0003-!sM]3bi\u0016\u0014H%Z9\u0015\u0005mC\u0007\"B0\b\u0001\u00041\u0016!\u0002\u0013qYV\u001cHC\u0001,l\u0011\u0015y\u0006\u00021\u0001W\u0003\u0019!S.\u001b8vgR\u0011aK\u001c\u0005\u0006?&\u0001\rAV\u0001\u0007IQLW.Z:\u0015\u0005Y\u000b\b\"\u0002:\u000b\u0001\u0004\u0019\u0018!\u0002;j[\u0016\u001c\bC\u0001\u001eu\u0013\t)8HA\u0002J]R\fA\u0001\n3jmR\u0011\u0001p\u001f\t\u0003ueL!A_\u001e\u0003\r\u0011{WO\u00197f\u0011\u0015y6\u00021\u0001W\u0003\u0011aWm]:\u0015\u0005ms\b\"B0\r\u0001\u00041\u0016A\u00027fgN,\u0015\u000fF\u0002\\\u0003\u0007AQaX\u0007A\u0002Y\u000bqa\u001a:fCR,'\u000fF\u0002\\\u0003\u0013AQa\u0018\bA\u0002Y\u000b\u0011b\u001a:fCR,'/R9\u0015\u0007m\u000by\u0001C\u0003`\u001f\u0001\u0007a+\u0001\u0003qYV\u001cHc\u0001,\u0002\u0016!)q\f\u0005a\u0001-\u0006)Q.\u001b8vgR\u0019a+a\u0007\t\u000b}\u000b\u0002\u0019\u0001,\u0015\u0007Y\u000by\u0002C\u0003s%\u0001\u00071/A\u0002eSZ$2\u0001_A\u0013\u0011\u0015y6\u00031\u0001W\u00031I7/T;mi&\u0004H.Z(g)\rY\u00161\u0006\u0005\u0006?R\u0001\rAV\u0001\u0004[&tGc\u0001,\u00022!)q,\u0006a\u0001-\u0006\u0019Q.\u0019=\u0015\u0007Y\u000b9\u0004C\u0003`-\u0001\u0007a+\u0001\u0004jgj+'o\\\u000b\u00027\u0006AAo\\*ue&tw\r\u0006\u0002\u0002BA!\u00111IA&\u001d\u0011\t)%a\u0012\u0011\u0005\u0015[\u0014bAA%w\u00051\u0001K]3eK\u001aLA!!\u0014\u0002P\t11\u000b\u001e:j]\u001eT1!!\u0013<\u0003E!xNR8s[\u0006$H/\u001a3TiJLgnZ\u000b\u0003\u0003\u0003\nA\"\\5mY&\u001cXmY8oIN\f1\u0002\u001d:fiRL\bK]5oi\u0006!1m\u001c9z)\r1\u0016Q\f\u0005\b\u001dr\u0001\n\u00111\u0001Q\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!a\u0019+\u0007A\u000b)g\u000b\u0002\u0002hA!\u0011\u0011NA:\u001b\t\tYG\u0003\u0003\u0002n\u0005=\u0014!C;oG\",7m[3e\u0015\r\t\thO\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA;\u0003W\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003=i\u0017\u000e\u001c7jg\u0012\n7mY3tg\u0012\u0002\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002~A!\u0011qPAE\u001b\t\t\tI\u0003\u0003\u0002\u0004\u0006\u0015\u0015\u0001\u00027b]\u001eT!!a\"\u0002\t)\fg/Y\u0005\u0005\u0003\u001b\n\t)\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001t\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a%\u0002\u001aB\u0019!(!&\n\u0007\u0005]5HA\u0002B]fD\u0001\"a'\"\u0003\u0003\u0005\ra]\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\u0005\u0006CBAR\u0003S\u000b\u0019*\u0004\u0002\u0002&*\u0019\u0011qU\u001e\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002,\u0006\u0015&\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$2aWAY\u0011%\tYjIA\u0001\u0002\u0004\t\u0019*\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA?\u0003oC\u0001\"a'%\u0003\u0003\u0005\ra]\u0001\tQ\u0006\u001c\bnQ8eKR\t1/\u0001\u0004fcV\fGn\u001d\u000b\u00047\u0006\u0005\u0007\"CANM\u0005\u0005\t\u0019AAJ\u0003!!UO]1uS>t\u0007CA,)'\u0015A\u0013\u0011ZAk!\u0019\tY-!5Q-6\u0011\u0011Q\u001a\u0006\u0004\u0003\u001f\\\u0014a\u0002:v]RLW.Z\u0005\u0005\u0003'\fiMA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!a6\u0002^6\u0011\u0011\u0011\u001c\u0006\u0005\u00037\f))\u0001\u0002j_&\u0019A*!7\u0015\u0005\u0005\u0015GCAA?\u0003\u0015\t\u0007\u000f\u001d7z)\r1\u0016q\u001d\u0005\u0006\u001d.\u0002\r\u0001U\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti/a=\u0011\ti\ny\u000fU\u0005\u0004\u0003c\\$AB(qi&|g\u000e\u0003\u0005\u0002v2\n\t\u00111\u0001W\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003w\u0004B!a \u0002~&!\u0011q`AA\u0005\u0019y%M[3di\u0002"
)
public class Duration implements Product, Serializable {
   private final long millis;

   public static Option unapply(final Duration x$0) {
      return Duration$.MODULE$.unapply(x$0);
   }

   public static Duration apply(final long millis) {
      return Duration$.MODULE$.apply(millis);
   }

   public static Function1 andThen(final Function1 g) {
      return Duration$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return Duration$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long millis$access$0() {
      return this.millis;
   }

   private long millis() {
      return this.millis;
   }

   public boolean $less(final Duration that) {
      return this.millis() < that.millis();
   }

   public boolean $less$eq(final Duration that) {
      return this.millis() <= that.millis();
   }

   public boolean $greater(final Duration that) {
      return this.millis() > that.millis();
   }

   public boolean $greater$eq(final Duration that) {
      return this.millis() >= that.millis();
   }

   public Duration $plus(final Duration that) {
      return new Duration(this.millis() + that.millis());
   }

   public Duration $minus(final Duration that) {
      return new Duration(this.millis() - that.millis());
   }

   public Duration $times(final int times) {
      return new Duration(this.millis() * (long)times);
   }

   public double $div(final Duration that) {
      return (double)this.millis() / (double)that.millis();
   }

   public boolean less(final Duration that) {
      return this.$less(that);
   }

   public boolean lessEq(final Duration that) {
      return this.$less$eq(that);
   }

   public boolean greater(final Duration that) {
      return this.$greater(that);
   }

   public boolean greaterEq(final Duration that) {
      return this.$greater$eq(that);
   }

   public Duration plus(final Duration that) {
      return this.$plus(that);
   }

   public Duration minus(final Duration that) {
      return this.$minus(that);
   }

   public Duration times(final int times) {
      return this.$times(times);
   }

   public double div(final Duration that) {
      return this.$div(that);
   }

   public boolean isMultipleOf(final Duration that) {
      return this.millis() % that.millis() == 0L;
   }

   public Duration min(final Duration that) {
      return this.$less(that) ? this : that;
   }

   public Duration max(final Duration that) {
      return this.$greater(that) ? this : that;
   }

   public boolean isZero() {
      return this.millis() == 0L;
   }

   public String toString() {
      return Long.toString(this.millis()) + " ms";
   }

   public String toFormattedString() {
      return Long.toString(this.millis());
   }

   public long milliseconds() {
      return this.millis();
   }

   public String prettyPrint() {
      return .MODULE$.msDurationToString(this.millis());
   }

   public Duration copy(final long millis) {
      return new Duration(millis);
   }

   public long copy$default$1() {
      return this.millis();
   }

   public String productPrefix() {
      return "Duration";
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
      return x$1 instanceof Duration;
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
            if (x$1 instanceof Duration) {
               Duration var4 = (Duration)x$1;
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

   public Duration(final long millis) {
      this.millis = millis;
      Product.$init$(this);
   }
}
