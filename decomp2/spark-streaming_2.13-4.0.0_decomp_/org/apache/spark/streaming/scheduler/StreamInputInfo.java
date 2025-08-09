package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005f\u0001\u0002\u0011\"\u00012B\u0001B\u0011\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\u000f\u0002\u0011\t\u0012)A\u0005\t\"A\u0001\n\u0001BK\u0002\u0013\u0005\u0011\n\u0003\u0005N\u0001\tE\t\u0015!\u0003K\u0011!q\u0005A!f\u0001\n\u0003y\u0005\u0002\u00030\u0001\u0005#\u0005\u000b\u0011\u0002)\t\u000b}\u0003A\u0011\u00011\t\u000b\u0019\u0004A\u0011A4\t\u000f-\u0004\u0011\u0011!C\u0001Y\"9\u0001\u000fAI\u0001\n\u0003\t\bb\u0002?\u0001#\u0003%\t! \u0005\t\u007f\u0002\t\n\u0011\"\u0001\u0002\u0002!I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\t\u0003/\u0001\u0011\u0011!C\u0001\u0007\"I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00111\u0004\u0005\n\u0003C\u0001\u0011\u0011!C!\u0003GA\u0011\"!\r\u0001\u0003\u0003%\t!a\r\t\u0013\u0005u\u0002!!A\u0005B\u0005}\u0002\"CA\"\u0001\u0005\u0005I\u0011IA#\u0011%\t9\u0005AA\u0001\n\u0003\nI\u0005C\u0005\u0002L\u0001\t\t\u0011\"\u0011\u0002N\u001d9\u0011QL\u0011\t\u0002\u0005}cA\u0002\u0011\"\u0011\u0003\t\t\u0007\u0003\u0004`/\u0011\u0005\u0011Q\u000e\u0005\n\u0003_:\"\u0019!C\u0001\u0003cBq!a\u001d\u0018A\u0003%\u0001\fC\u0005\u0002v]\t\t\u0011\"!\u0002x!I\u0011qP\f\u0012\u0002\u0013\u0005\u0011\u0011\u0001\u0005\n\u0003\u0003;\u0012\u0011!CA\u0003\u0007C\u0011\"!%\u0018#\u0003%\t!!\u0001\t\u0013\u0005Mu#!A\u0005\n\u0005U%aD*ue\u0016\fW.\u00138qkRLeNZ8\u000b\u0005\t\u001a\u0013!C:dQ\u0016$W\u000f\\3s\u0015\t!S%A\u0005tiJ,\u0017-\\5oO*\u0011aeJ\u0001\u0006gB\f'o\u001b\u0006\u0003Q%\na!\u00199bG\",'\"\u0001\u0016\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001i3G\u000e\t\u0003]Ej\u0011a\f\u0006\u0002a\u0005)1oY1mC&\u0011!g\f\u0002\u0007\u0003:L(+\u001a4\u0011\u00059\"\u0014BA\u001b0\u0005\u001d\u0001&o\u001c3vGR\u0004\"aN \u000f\u0005ajdBA\u001d=\u001b\u0005Q$BA\u001e,\u0003\u0019a$o\\8u}%\t\u0001'\u0003\u0002?_\u00059\u0001/Y2lC\u001e,\u0017B\u0001!B\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tqt&A\u0007j]B,Ho\u0015;sK\u0006l\u0017\nZ\u000b\u0002\tB\u0011a&R\u0005\u0003\r>\u00121!\u00138u\u00039Ig\u000e];u'R\u0014X-Y7JI\u0002\n!B\\;n%\u0016\u001cwN\u001d3t+\u0005Q\u0005C\u0001\u0018L\u0013\tauF\u0001\u0003M_:<\u0017a\u00038v[J+7m\u001c:eg\u0002\n\u0001\"\\3uC\u0012\fG/Y\u000b\u0002!B!\u0011+\u0016-\\\u001d\t\u00116\u000b\u0005\u0002:_%\u0011AkL\u0001\u0007!J,G-\u001a4\n\u0005Y;&aA'ba*\u0011Ak\f\t\u0003#fK!AW,\u0003\rM#(/\u001b8h!\tqC,\u0003\u0002^_\t\u0019\u0011I\\=\u0002\u00135,G/\u00193bi\u0006\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003bG\u0012,\u0007C\u00012\u0001\u001b\u0005\t\u0003\"\u0002\"\b\u0001\u0004!\u0005\"\u0002%\b\u0001\u0004Q\u0005b\u0002(\b!\u0003\u0005\r\u0001U\u0001\u0014[\u0016$\u0018\rZ1uC\u0012+7o\u0019:jaRLwN\\\u000b\u0002QB\u0019a&\u001b-\n\u0005)|#AB(qi&|g.\u0001\u0003d_BLH\u0003B1n]>DqAQ\u0005\u0011\u0002\u0003\u0007A\tC\u0004I\u0013A\u0005\t\u0019\u0001&\t\u000f9K\u0001\u0013!a\u0001!\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001:+\u0005\u0011\u001b8&\u0001;\u0011\u0005UTX\"\u0001<\u000b\u0005]D\u0018!C;oG\",7m[3e\u0015\tIx&\u0001\u0006b]:|G/\u0019;j_:L!a\u001f<\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003yT#AS:\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u00111\u0001\u0016\u0003!N\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0005!\u0011\tY!!\u0006\u000e\u0005\u00055!\u0002BA\b\u0003#\tA\u0001\\1oO*\u0011\u00111C\u0001\u0005U\u00064\u0018-C\u0002[\u0003\u001b\tA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002\\\u0003;A\u0001\"a\b\u0010\u0003\u0003\u0005\r\u0001R\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\u0015\u0002#BA\u0014\u0003[YVBAA\u0015\u0015\r\tYcL\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u0018\u0003S\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QGA\u001e!\rq\u0013qG\u0005\u0004\u0003sy#a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003?\t\u0012\u0011!a\u00017\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\tI!!\u0011\t\u0011\u0005}!#!AA\u0002\u0011\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\t\u0006AAo\\*ue&tw\r\u0006\u0002\u0002\n\u00051Q-];bYN$B!!\u000e\u0002P!A\u0011qD\u000b\u0002\u0002\u0003\u00071\fK\u0002\u0001\u0003'\u0002B!!\u0016\u0002Z5\u0011\u0011q\u000b\u0006\u0003s\u0016JA!a\u0017\u0002X\taA)\u001a<fY>\u0004XM]!qS\u0006y1\u000b\u001e:fC6Le\u000e];u\u0013:4w\u000e\u0005\u0002c/M!q#LA2!\u0011\t)'a\u001b\u000e\u0005\u0005\u001d$\u0002BA5\u0003#\t!![8\n\u0007\u0001\u000b9\u0007\u0006\u0002\u0002`\u0005AR*\u0012+B\t\u0006#\u0016iX&F3~#UiU\"S\u0013B#\u0016j\u0014(\u0016\u0003a\u000b\u0011$T#U\u0003\u0012\u000bE+Q0L\u000bf{F)R*D%&\u0003F+S(OA\u0005)\u0011\r\u001d9msR9\u0011-!\u001f\u0002|\u0005u\u0004\"\u0002\"\u001c\u0001\u0004!\u0005\"\u0002%\u001c\u0001\u0004Q\u0005b\u0002(\u001c!\u0003\u0005\r\u0001U\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u00059QO\\1qa2LH\u0003BAC\u0003\u001b\u0003BAL5\u0002\bB1a&!#E\u0015BK1!a#0\u0005\u0019!V\u000f\u001d7fg!A\u0011qR\u000f\u0002\u0002\u0003\u0007\u0011-A\u0002yIA\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001a\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAL!\u0011\tY!!'\n\t\u0005m\u0015Q\u0002\u0002\u0007\u001f\nTWm\u0019;)\u0007]\t\u0019\u0006K\u0002\u0017\u0003'\u0002"
)
public class StreamInputInfo implements Product, Serializable {
   private final int inputStreamId;
   private final long numRecords;
   private final Map metadata;

   public static Map $lessinit$greater$default$3() {
      return StreamInputInfo$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final StreamInputInfo x$0) {
      return StreamInputInfo$.MODULE$.unapply(x$0);
   }

   public static Map apply$default$3() {
      return StreamInputInfo$.MODULE$.apply$default$3();
   }

   public static StreamInputInfo apply(final int inputStreamId, final long numRecords, final Map metadata) {
      return StreamInputInfo$.MODULE$.apply(inputStreamId, numRecords, metadata);
   }

   public static String METADATA_KEY_DESCRIPTION() {
      return StreamInputInfo$.MODULE$.METADATA_KEY_DESCRIPTION();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int inputStreamId() {
      return this.inputStreamId;
   }

   public long numRecords() {
      return this.numRecords;
   }

   public Map metadata() {
      return this.metadata;
   }

   public Option metadataDescription() {
      return this.metadata().get(StreamInputInfo$.MODULE$.METADATA_KEY_DESCRIPTION()).map((x$1) -> x$1.toString());
   }

   public StreamInputInfo copy(final int inputStreamId, final long numRecords, final Map metadata) {
      return new StreamInputInfo(inputStreamId, numRecords, metadata);
   }

   public int copy$default$1() {
      return this.inputStreamId();
   }

   public long copy$default$2() {
      return this.numRecords();
   }

   public Map copy$default$3() {
      return this.metadata();
   }

   public String productPrefix() {
      return "StreamInputInfo";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.inputStreamId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.numRecords());
         }
         case 2 -> {
            return this.metadata();
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
      return x$1 instanceof StreamInputInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "inputStreamId";
         }
         case 1 -> {
            return "numRecords";
         }
         case 2 -> {
            return "metadata";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.inputStreamId());
      var1 = Statics.mix(var1, Statics.longHash(this.numRecords()));
      var1 = Statics.mix(var1, Statics.anyHash(this.metadata()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof StreamInputInfo) {
               StreamInputInfo var4 = (StreamInputInfo)x$1;
               if (this.inputStreamId() == var4.inputStreamId() && this.numRecords() == var4.numRecords()) {
                  label48: {
                     Map var10000 = this.metadata();
                     Map var5 = var4.metadata();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
                     }
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public StreamInputInfo(final int inputStreamId, final long numRecords, final Map metadata) {
      this.inputStreamId = inputStreamId;
      this.numRecords = numRecords;
      this.metadata = metadata;
      Product.$init$(this);
      scala.Predef..MODULE$.require(numRecords >= 0L, () -> "numRecords must not be negative");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
