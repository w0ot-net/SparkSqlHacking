package org.apache.spark.streaming.ui;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.streaming.scheduler.OutputOperationInfo;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055h!\u0002\u0014(\u0001\u001e\n\u0004\u0002\u0003%\u0001\u0005+\u0007I\u0011A%\t\u0011m\u0003!\u0011#Q\u0001\n)C\u0001\u0002\u0018\u0001\u0003\u0016\u0004%\t!\u0018\u0005\tM\u0002\u0011\t\u0012)A\u0005=\"Aq\r\u0001BK\u0002\u0013\u0005Q\f\u0003\u0005i\u0001\tE\t\u0015!\u0003_\u0011!I\u0007A!f\u0001\n\u0003Q\u0007\u0002C9\u0001\u0005#\u0005\u000b\u0011B6\t\u0011I\u0004!Q3A\u0005\u0002)D\u0001b\u001d\u0001\u0003\u0012\u0003\u0006Ia\u001b\u0005\ti\u0002\u0011)\u001a!C\u0001k\"Aq\u000f\u0001B\tB\u0003%a\u000fC\u0003y\u0001\u0011\u0005\u0011\u0010\u0003\u0004\u0002\u0006\u0001!\tA\u001b\u0005\n\u0003\u000f\u0001\u0011\u0011!C\u0001\u0003\u0013A\u0011\"a\u0006\u0001#\u0003%\t!!\u0007\t\u0013\u0005=\u0002!%A\u0005\u0002\u0005E\u0002\"CA\u001b\u0001E\u0005I\u0011AA\u0019\u0011%\t9\u0004AI\u0001\n\u0003\tI\u0004C\u0005\u0002>\u0001\t\n\u0011\"\u0001\u0002:!I\u0011q\b\u0001\u0012\u0002\u0013\u0005\u0011\u0011\t\u0005\n\u0003\u000b\u0002\u0011\u0011!C!\u0003\u000fB\u0011\"a\u0016\u0001\u0003\u0003%\t!!\u0017\t\u0013\u0005\u0005\u0004!!A\u0005\u0002\u0005\r\u0004\"CA8\u0001\u0005\u0005I\u0011IA9\u0011%\ty\bAA\u0001\n\u0003\t\t\tC\u0005\u0002\f\u0002\t\t\u0011\"\u0011\u0002\u000e\"I\u0011\u0011\u0013\u0001\u0002\u0002\u0013\u0005\u00131\u0013\u0005\n\u0003+\u0003\u0011\u0011!C!\u0003/C\u0011\"!'\u0001\u0003\u0003%\t%a'\b\u0011\u0005}u\u0005#\u0001(\u0003C3qAJ\u0014\t\u0002\u001d\n\u0019\u000b\u0003\u0004yA\u0011\u0005\u0011q\u0016\u0005\b\u0003c\u0003C\u0011AAZ\u0011%\t\t\fIA\u0001\n\u0003\u000b)\rC\u0005\u0002T\u0002\n\t\u0011\"!\u0002V\"I\u00111\u001d\u0011\u0002\u0002\u0013%\u0011Q\u001d\u0002\u0016\u001fV$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]VKE)\u0019;b\u0015\tA\u0013&\u0001\u0002vS*\u0011!fK\u0001\ngR\u0014X-Y7j]\u001eT!\u0001L\u0017\u0002\u000bM\u0004\u0018M]6\u000b\u00059z\u0013AB1qC\u000eDWMC\u00011\u0003\ry'oZ\n\u0005\u0001IB4\b\u0005\u00024m5\tAGC\u00016\u0003\u0015\u00198-\u00197b\u0013\t9DG\u0001\u0004B]f\u0014VM\u001a\t\u0003geJ!A\u000f\u001b\u0003\u000fA\u0013x\u000eZ;diB\u0011A(\u0012\b\u0003{\rs!A\u0010\"\u000e\u0003}R!\u0001Q!\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!N\u0005\u0003\tR\nq\u0001]1dW\u0006<W-\u0003\u0002G\u000f\na1+\u001a:jC2L'0\u00192mK*\u0011A\tN\u0001\u0003S\u0012,\u0012A\u0013\t\u0003\u0017bs!\u0001\u0014,\u000f\u00055+fB\u0001(U\u001d\ty5K\u0004\u0002Q%:\u0011a(U\u0005\u0002a%\u0011afL\u0005\u0003Y5J!AK\u0016\n\u0005!J\u0013BA,(\u0003q\u0019FO]3b[&twMS8c!J|wM]3tg2K7\u000f^3oKJL!!\u0017.\u0003\u0015=+H\u000f];u\u001fBLEM\u0003\u0002XO\u0005\u0019\u0011\u000e\u001a\u0011\u0002\t9\fW.Z\u000b\u0002=B\u0011ql\u0019\b\u0003A\u0006\u0004\"A\u0010\u001b\n\u0005\t$\u0014A\u0002)sK\u0012,g-\u0003\u0002eK\n11\u000b\u001e:j]\u001eT!A\u0019\u001b\u0002\u000b9\fW.\u001a\u0011\u0002\u0017\u0011,7o\u0019:jaRLwN\\\u0001\rI\u0016\u001c8M]5qi&|g\u000eI\u0001\ngR\f'\u000f\u001e+j[\u0016,\u0012a\u001b\t\u0004g1t\u0017BA75\u0005\u0019y\u0005\u000f^5p]B\u00111g\\\u0005\u0003aR\u0012A\u0001T8oO\u0006Q1\u000f^1siRKW.\u001a\u0011\u0002\u000f\u0015tG\rV5nK\u0006AQM\u001c3US6,\u0007%A\u0007gC&dWO]3SK\u0006\u001cxN\\\u000b\u0002mB\u00191\u0007\u001c0\u0002\u001d\u0019\f\u0017\u000e\\;sKJ+\u0017m]8oA\u00051A(\u001b8jiz\"\u0012B\u001f?~}~\f\t!a\u0001\u0011\u0005m\u0004Q\"A\u0014\t\u000b!k\u0001\u0019\u0001&\t\u000bqk\u0001\u0019\u00010\t\u000b\u001dl\u0001\u0019\u00010\t\u000b%l\u0001\u0019A6\t\u000bIl\u0001\u0019A6\t\u000bQl\u0001\u0019\u0001<\u0002\u0011\u0011,(/\u0019;j_:\fAaY8qsRi!0a\u0003\u0002\u000e\u0005=\u0011\u0011CA\n\u0003+Aq\u0001S\b\u0011\u0002\u0003\u0007!\nC\u0004]\u001fA\u0005\t\u0019\u00010\t\u000f\u001d|\u0001\u0013!a\u0001=\"9\u0011n\u0004I\u0001\u0002\u0004Y\u0007b\u0002:\u0010!\u0003\u0005\ra\u001b\u0005\bi>\u0001\n\u00111\u0001w\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!a\u0007+\u0007)\u000bib\u000b\u0002\u0002 A!\u0011\u0011EA\u0016\u001b\t\t\u0019C\u0003\u0003\u0002&\u0005\u001d\u0012!C;oG\",7m[3e\u0015\r\tI\u0003N\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0017\u0003G\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!a\r+\u0007y\u000bi\"\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u00111\b\u0016\u0004W\u0006u\u0011AD2paf$C-\u001a4bk2$H%N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00137+\t\t\u0019EK\u0002w\u0003;\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA%!\u0011\tY%!\u0016\u000e\u0005\u00055#\u0002BA(\u0003#\nA\u0001\\1oO*\u0011\u00111K\u0001\u0005U\u00064\u0018-C\u0002e\u0003\u001b\nA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\u0017\u0011\u0007M\ni&C\u0002\u0002`Q\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u001a\u0002lA\u00191'a\u001a\n\u0007\u0005%DGA\u0002B]fD\u0011\"!\u001c\u0019\u0003\u0003\u0005\r!a\u0017\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\u0019\b\u0005\u0004\u0002v\u0005m\u0014QM\u0007\u0003\u0003oR1!!\u001f5\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003{\n9H\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAB\u0003\u0013\u00032aMAC\u0013\r\t9\t\u000e\u0002\b\u0005>|G.Z1o\u0011%\tiGGA\u0001\u0002\u0004\t)'\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA%\u0003\u001fC\u0011\"!\u001c\u001c\u0003\u0003\u0005\r!a\u0017\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u0017\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u0013\u0002\r\u0015\fX/\u00197t)\u0011\t\u0019)!(\t\u0013\u00055d$!AA\u0002\u0005\u0015\u0014!F(viB,Ho\u00149fe\u0006$\u0018n\u001c8V\u0013\u0012\u000bG/\u0019\t\u0003w\u0002\u001aB\u0001\t\u001a\u0002&B!\u0011qUAW\u001b\t\tIK\u0003\u0003\u0002,\u0006E\u0013AA5p\u0013\r1\u0015\u0011\u0016\u000b\u0003\u0003C\u000bQ!\u00199qYf$2A_A[\u0011\u001d\t9L\ta\u0001\u0003s\u000b1c\\;uaV$x\n]3sCRLwN\\%oM>\u0004B!a/\u0002B6\u0011\u0011Q\u0018\u0006\u0004\u0003\u007fK\u0013!C:dQ\u0016$W\u000f\\3s\u0013\u0011\t\u0019-!0\u0003'=+H\u000f];u\u001fB,'/\u0019;j_:LeNZ8\u0015\u001bi\f9-!3\u0002L\u00065\u0017qZAi\u0011\u0015A5\u00051\u0001K\u0011\u0015a6\u00051\u0001_\u0011\u001597\u00051\u0001_\u0011\u0015I7\u00051\u0001l\u0011\u0015\u00118\u00051\u0001l\u0011\u0015!8\u00051\u0001w\u0003\u001d)h.\u00199qYf$B!a6\u0002`B!1\u0007\\Am!%\u0019\u00141\u001c&_=.\\g/C\u0002\u0002^R\u0012a\u0001V;qY\u00164\u0004\u0002CAqI\u0005\u0005\t\u0019\u0001>\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002hB!\u00111JAu\u0013\u0011\tY/!\u0014\u0003\r=\u0013'.Z2u\u0001"
)
public class OutputOperationUIData implements Product, Serializable {
   private final int id;
   private final String name;
   private final String description;
   private final Option startTime;
   private final Option endTime;
   private final Option failureReason;

   public static Option unapply(final OutputOperationUIData x$0) {
      return OutputOperationUIData$.MODULE$.unapply(x$0);
   }

   public static OutputOperationUIData apply(final int id, final String name, final String description, final Option startTime, final Option endTime, final Option failureReason) {
      return OutputOperationUIData$.MODULE$.apply(id, name, description, startTime, endTime, failureReason);
   }

   public static OutputOperationUIData apply(final OutputOperationInfo outputOperationInfo) {
      return OutputOperationUIData$.MODULE$.apply(outputOperationInfo);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int id() {
      return this.id;
   }

   public String name() {
      return this.name;
   }

   public String description() {
      return this.description;
   }

   public Option startTime() {
      return this.startTime;
   }

   public Option endTime() {
      return this.endTime;
   }

   public Option failureReason() {
      return this.failureReason;
   }

   public Option duration() {
      return this.startTime().flatMap((s) -> $anonfun$duration$1(this, BoxesRunTime.unboxToLong(s)));
   }

   public OutputOperationUIData copy(final int id, final String name, final String description, final Option startTime, final Option endTime, final Option failureReason) {
      return new OutputOperationUIData(id, name, description, startTime, endTime, failureReason);
   }

   public int copy$default$1() {
      return this.id();
   }

   public String copy$default$2() {
      return this.name();
   }

   public String copy$default$3() {
      return this.description();
   }

   public Option copy$default$4() {
      return this.startTime();
   }

   public Option copy$default$5() {
      return this.endTime();
   }

   public Option copy$default$6() {
      return this.failureReason();
   }

   public String productPrefix() {
      return "OutputOperationUIData";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.id());
         }
         case 1 -> {
            return this.name();
         }
         case 2 -> {
            return this.description();
         }
         case 3 -> {
            return this.startTime();
         }
         case 4 -> {
            return this.endTime();
         }
         case 5 -> {
            return this.failureReason();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof OutputOperationUIData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         case 1 -> {
            return "name";
         }
         case 2 -> {
            return "description";
         }
         case 3 -> {
            return "startTime";
         }
         case 4 -> {
            return "endTime";
         }
         case 5 -> {
            return "failureReason";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.id());
      var1 = Statics.mix(var1, Statics.anyHash(this.name()));
      var1 = Statics.mix(var1, Statics.anyHash(this.description()));
      var1 = Statics.mix(var1, Statics.anyHash(this.startTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.endTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.failureReason()));
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var14;
      if (this != x$1) {
         label83: {
            if (x$1 instanceof OutputOperationUIData) {
               OutputOperationUIData var4 = (OutputOperationUIData)x$1;
               if (this.id() == var4.id()) {
                  label76: {
                     String var10000 = this.name();
                     String var5 = var4.name();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label76;
                     }

                     var10000 = this.description();
                     String var6 = var4.description();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label76;
                     }

                     Option var11 = this.startTime();
                     Option var7 = var4.startTime();
                     if (var11 == null) {
                        if (var7 != null) {
                           break label76;
                        }
                     } else if (!var11.equals(var7)) {
                        break label76;
                     }

                     var11 = this.endTime();
                     Option var8 = var4.endTime();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label76;
                        }
                     } else if (!var11.equals(var8)) {
                        break label76;
                     }

                     var11 = this.failureReason();
                     Option var9 = var4.failureReason();
                     if (var11 == null) {
                        if (var9 != null) {
                           break label76;
                        }
                     } else if (!var11.equals(var9)) {
                        break label76;
                     }

                     if (var4.canEqual(this)) {
                        break label83;
                     }
                  }
               }
            }

            var14 = false;
            return var14;
         }
      }

      var14 = true;
      return var14;
   }

   // $FF: synthetic method
   public static final Option $anonfun$duration$1(final OutputOperationUIData $this, final long s) {
      return $this.endTime().map((JFunction1.mcJJ.sp)(e) -> e - s);
   }

   public OutputOperationUIData(final int id, final String name, final String description, final Option startTime, final Option endTime, final Option failureReason) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.startTime = startTime;
      this.endTime = endTime;
      this.failureReason = failureReason;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
