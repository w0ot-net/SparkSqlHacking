package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.storage.BlockManagerId;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005mg\u0001B\u0014)\u0001>B\u0001\"\u0013\u0001\u0003\u0016\u0004%\tA\u0013\u0005\t#\u0002\u0011\t\u0012)A\u0005\u0017\"A!\u000b\u0001BK\u0002\u0013\u00051\u000b\u0003\u0005X\u0001\tE\t\u0015!\u0003U\u0011!A\u0006A!f\u0001\n\u0003I\u0006\u0002C/\u0001\u0005#\u0005\u000b\u0011\u0002.\t\u0011y\u0003!Q3A\u0005\u0002MC\u0001b\u0018\u0001\u0003\u0012\u0003\u0006I\u0001\u0016\u0005\tA\u0002\u0011)\u001a!C\u0001'\"A\u0011\r\u0001B\tB\u0003%A\u000b\u0003\u0005c\u0001\tU\r\u0011\"\u0001d\u0011!a\u0007A!E!\u0002\u0013!\u0007\"B7\u0001\t\u0003q\u0007\"\u0002<\u0001\t\u0003\u001a\u0007\"B<\u0001\t\u0003B\bb\u0002?\u0001\u0003\u0003%\t! \u0005\n\u0003\u0013\u0001\u0011\u0013!C\u0001\u0003\u0017A\u0011\"!\t\u0001#\u0003%\t!a\t\t\u0013\u0005\u001d\u0002!%A\u0005\u0002\u0005%\u0002\"CA\u0017\u0001E\u0005I\u0011AA\u0012\u0011%\ty\u0003AI\u0001\n\u0003\t\u0019\u0003C\u0005\u00022\u0001\t\n\u0011\"\u0001\u00024!I\u0011q\u0007\u0001\u0002\u0002\u0013\u0005\u0013\u0011\b\u0005\t\u0003\u0013\u0002\u0011\u0011!C\u0001'\"I\u00111\n\u0001\u0002\u0002\u0013\u0005\u0011Q\n\u0005\n\u00033\u0002\u0011\u0011!C!\u00037B\u0011\"!\u001b\u0001\u0003\u0003%\t!a\u001b\t\u0013\u0005=\u0004!!A\u0005B\u0005E\u0004\"CA;\u0001\u0005\u0005I\u0011IA<\u0011%\tI\bAA\u0001\n\u0003\nY\bC\u0005\u0002~\u0001\t\t\u0011\"\u0011\u0002\u0000\u001dI\u0011q\u0012\u0015\u0002\u0002#\u0005\u0011\u0011\u0013\u0004\tO!\n\t\u0011#\u0001\u0002\u0014\"1Q.\tC\u0001\u0003WC\u0011\"!\u001f\"\u0003\u0003%)%a\u001f\t\u0013\u00055\u0016%!A\u0005\u0002\u0006=\u0006\"CA_C\u0005\u0005I\u0011QA`\u0011%\t\t.IA\u0001\n\u0013\t\u0019NA\u0006GKR\u001c\u0007NR1jY\u0016$'BA\u0015+\u0003\u0015\u0019\b/\u0019:l\u0015\tYC&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002[\u0005\u0019qN]4\u0004\u0001M)\u0001\u0001\r\u001c;{A\u0011\u0011\u0007N\u0007\u0002e)\t1'A\u0003tG\u0006d\u0017-\u0003\u00026e\t1\u0011I\\=SK\u001a\u0004\"a\u000e\u001d\u000e\u0003!J!!\u000f\u0015\u0003!Q\u000b7o\u001b$bS2,GMU3bg>t\u0007CA\u0019<\u0013\ta$GA\u0004Qe>$Wo\u0019;\u0011\u0005y2eBA E\u001d\t\u00015)D\u0001B\u0015\t\u0011e&\u0001\u0004=e>|GOP\u0005\u0002g%\u0011QIM\u0001\ba\u0006\u001c7.Y4f\u0013\t9\u0005J\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Fe\u0005I!-\\!eIJ,7o]\u000b\u0002\u0017B\u0011AjT\u0007\u0002\u001b*\u0011a\nK\u0001\bgR|'/Y4f\u0013\t\u0001VJ\u0001\bCY>\u001c7.T1oC\u001e,'/\u00133\u0002\u0015\tl\u0017\t\u001a3sKN\u001c\b%A\u0005tQV4g\r\\3JIV\tA\u000b\u0005\u00022+&\u0011aK\r\u0002\u0004\u0013:$\u0018AC:ik\u001a4G.Z%eA\u0005)Q.\u00199JIV\t!\f\u0005\u000227&\u0011AL\r\u0002\u0005\u0019>tw-\u0001\u0004nCBLE\rI\u0001\t[\u0006\u0004\u0018J\u001c3fq\u0006IQ.\u00199J]\u0012,\u0007\u0010I\u0001\te\u0016$WoY3JI\u0006I!/\u001a3vG\u0016LE\rI\u0001\b[\u0016\u001c8/Y4f+\u0005!\u0007CA3j\u001d\t1w\r\u0005\u0002Ae%\u0011\u0001NM\u0001\u0007!J,G-\u001a4\n\u0005)\\'AB*ue&twM\u0003\u0002ie\u0005AQ.Z:tC\u001e,\u0007%\u0001\u0004=S:LGO\u0010\u000b\b_B\f(o\u001d;v!\t9\u0004\u0001C\u0003J\u001b\u0001\u00071\nC\u0003S\u001b\u0001\u0007A\u000bC\u0003Y\u001b\u0001\u0007!\fC\u0003_\u001b\u0001\u0007A\u000bC\u0003a\u001b\u0001\u0007A\u000bC\u0003c\u001b\u0001\u0007A-A\u0007u_\u0016\u0013(o\u001c:TiJLgnZ\u0001\u0019G>,h\u000e\u001e+po\u0006\u0014Hm\u001d+bg.4\u0015-\u001b7ve\u0016\u001cX#A=\u0011\u0005ER\u0018BA>3\u0005\u001d\u0011un\u001c7fC:\fAaY8qsRYqN`@\u0002\u0002\u0005\r\u0011QAA\u0004\u0011\u001dI\u0005\u0003%AA\u0002-CqA\u0015\t\u0011\u0002\u0003\u0007A\u000bC\u0004Y!A\u0005\t\u0019\u0001.\t\u000fy\u0003\u0002\u0013!a\u0001)\"9\u0001\r\u0005I\u0001\u0002\u0004!\u0006b\u00022\u0011!\u0003\u0005\r\u0001Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tiAK\u0002L\u0003\u001fY#!!\u0005\u0011\t\u0005M\u0011QD\u0007\u0003\u0003+QA!a\u0006\u0002\u001a\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u00037\u0011\u0014AC1o]>$\u0018\r^5p]&!\u0011qDA\u000b\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\t)CK\u0002U\u0003\u001f\tabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002,)\u001a!,a\u0004\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%i\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012*\u0014AD2paf$C-\u001a4bk2$HEN\u000b\u0003\u0003kQ3\u0001ZA\b\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u00111\b\t\u0005\u0003{\t9%\u0004\u0002\u0002@)!\u0011\u0011IA\"\u0003\u0011a\u0017M\\4\u000b\u0005\u0005\u0015\u0013\u0001\u00026bm\u0006L1A[A \u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0014\u0002VA\u0019\u0011'!\u0015\n\u0007\u0005M#GA\u0002B]fD\u0001\"a\u0016\u001a\u0003\u0003\u0005\r\u0001V\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005u\u0003CBA0\u0003K\ny%\u0004\u0002\u0002b)\u0019\u00111\r\u001a\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002h\u0005\u0005$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$2!_A7\u0011%\t9fGA\u0001\u0002\u0004\ty%\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u001e\u0003gB\u0001\"a\u0016\u001d\u0003\u0003\u0005\r\u0001V\u0001\tQ\u0006\u001c\bnQ8eKR\tA+\u0001\u0005u_N#(/\u001b8h)\t\tY$\u0001\u0004fcV\fGn\u001d\u000b\u0004s\u0006\u0005\u0005\"CA,?\u0005\u0005\t\u0019AA(Q\r\u0001\u0011Q\u0011\t\u0005\u0003\u000f\u000bY)\u0004\u0002\u0002\n*\u0019\u00111\u0004\u0015\n\t\u00055\u0015\u0011\u0012\u0002\r\t\u00164X\r\\8qKJ\f\u0005/[\u0001\f\r\u0016$8\r\u001b$bS2,G\r\u0005\u00028CM)\u0011%!&\u0002\"BY\u0011qSAO\u0017RSF\u000b\u00163p\u001b\t\tIJC\u0002\u0002\u001cJ\nqA];oi&lW-\u0003\u0003\u0002 \u0006e%!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8omA!\u00111UAU\u001b\t\t)K\u0003\u0003\u0002(\u0006\r\u0013AA5p\u0013\r9\u0015Q\u0015\u000b\u0003\u0003#\u000bQ!\u00199qYf$Rb\\AY\u0003g\u000b),a.\u0002:\u0006m\u0006\"B%%\u0001\u0004Y\u0005\"\u0002*%\u0001\u0004!\u0006\"\u0002-%\u0001\u0004Q\u0006\"\u00020%\u0001\u0004!\u0006\"\u00021%\u0001\u0004!\u0006\"\u00022%\u0001\u0004!\u0017aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u0003\fi\rE\u00032\u0003\u0007\f9-C\u0002\u0002FJ\u0012aa\u00149uS>t\u0007#C\u0019\u0002J.#&\f\u0016+e\u0013\r\tYM\r\u0002\u0007)V\u0004H.\u001a\u001c\t\u0011\u0005=W%!AA\u0002=\f1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u000e\u0005\u0003\u0002>\u0005]\u0017\u0002BAm\u0003\u007f\u0011aa\u00142kK\u000e$\b"
)
public class FetchFailed implements TaskFailedReason, Product, Serializable {
   private final BlockManagerId bmAddress;
   private final int shuffleId;
   private final long mapId;
   private final int mapIndex;
   private final int reduceId;
   private final String message;

   public static Option unapply(final FetchFailed x$0) {
      return FetchFailed$.MODULE$.unapply(x$0);
   }

   public static FetchFailed apply(final BlockManagerId bmAddress, final int shuffleId, final long mapId, final int mapIndex, final int reduceId, final String message) {
      return FetchFailed$.MODULE$.apply(bmAddress, shuffleId, mapId, mapIndex, reduceId, message);
   }

   public static Function1 tupled() {
      return FetchFailed$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return FetchFailed$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public BlockManagerId bmAddress() {
      return this.bmAddress;
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public long mapId() {
      return this.mapId;
   }

   public int mapIndex() {
      return this.mapIndex;
   }

   public int reduceId() {
      return this.reduceId;
   }

   public String message() {
      return this.message;
   }

   public String toErrorString() {
      String bmAddressString = this.bmAddress() == null ? "null" : this.bmAddress().toString();
      String mapIndexString = this.mapIndex() == Integer.MIN_VALUE ? "Unknown" : Integer.toString(this.mapIndex());
      return "FetchFailed(" + bmAddressString + ", shuffleId=" + this.shuffleId() + ", mapIndex=" + mapIndexString + ", mapId=" + this.mapId() + ", reduceId=" + this.reduceId() + ", message=\n" + this.message() + "\n)";
   }

   public boolean countTowardsTaskFailures() {
      return false;
   }

   public FetchFailed copy(final BlockManagerId bmAddress, final int shuffleId, final long mapId, final int mapIndex, final int reduceId, final String message) {
      return new FetchFailed(bmAddress, shuffleId, mapId, mapIndex, reduceId, message);
   }

   public BlockManagerId copy$default$1() {
      return this.bmAddress();
   }

   public int copy$default$2() {
      return this.shuffleId();
   }

   public long copy$default$3() {
      return this.mapId();
   }

   public int copy$default$4() {
      return this.mapIndex();
   }

   public int copy$default$5() {
      return this.reduceId();
   }

   public String copy$default$6() {
      return this.message();
   }

   public String productPrefix() {
      return "FetchFailed";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.bmAddress();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.shuffleId());
         }
         case 2 -> {
            return BoxesRunTime.boxToLong(this.mapId());
         }
         case 3 -> {
            return BoxesRunTime.boxToInteger(this.mapIndex());
         }
         case 4 -> {
            return BoxesRunTime.boxToInteger(this.reduceId());
         }
         case 5 -> {
            return this.message();
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
      return x$1 instanceof FetchFailed;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "bmAddress";
         }
         case 1 -> {
            return "shuffleId";
         }
         case 2 -> {
            return "mapId";
         }
         case 3 -> {
            return "mapIndex";
         }
         case 4 -> {
            return "reduceId";
         }
         case 5 -> {
            return "message";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.bmAddress()));
      var1 = Statics.mix(var1, this.shuffleId());
      var1 = Statics.mix(var1, Statics.longHash(this.mapId()));
      var1 = Statics.mix(var1, this.mapIndex());
      var1 = Statics.mix(var1, this.reduceId());
      var1 = Statics.mix(var1, Statics.anyHash(this.message()));
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof FetchFailed) {
               FetchFailed var4 = (FetchFailed)x$1;
               if (this.shuffleId() == var4.shuffleId() && this.mapId() == var4.mapId() && this.mapIndex() == var4.mapIndex() && this.reduceId() == var4.reduceId()) {
                  label64: {
                     BlockManagerId var10000 = this.bmAddress();
                     BlockManagerId var5 = var4.bmAddress();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label64;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label64;
                     }

                     String var7 = this.message();
                     String var6 = var4.message();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label64;
                        }
                     } else if (!var7.equals(var6)) {
                        break label64;
                     }

                     if (var4.canEqual(this)) {
                        break label71;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public FetchFailed(final BlockManagerId bmAddress, final int shuffleId, final long mapId, final int mapIndex, final int reduceId, final String message) {
      this.bmAddress = bmAddress;
      this.shuffleId = shuffleId;
      this.mapId = mapId;
      this.mapIndex = mapIndex;
      this.reduceId = reduceId;
      this.message = message;
      TaskFailedReason.$init$(this);
      Product.$init$(this);
   }
}
