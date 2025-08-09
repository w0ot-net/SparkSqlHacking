package org.apache.spark.storage;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
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
   bytes = "\u0006\u0005\u0005\u0015e\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005I\u0001\tE\t\u0015!\u0003F\u0011!I\u0005A!f\u0001\n\u0003q\u0004\u0002\u0003&\u0001\u0005#\u0005\u000b\u0011B \t\u000b-\u0003A\u0011\u0001'\t\u000bE\u0003A\u0011\t*\t\u000fm\u0003\u0011\u0011!C\u00019\"9\u0001\rAI\u0001\n\u0003\t\u0007b\u00027\u0001#\u0003%\t!\u001c\u0005\b_\u0002\t\n\u0011\"\u0001b\u0011\u001d\u0001\b!!A\u0005BEDq!\u001f\u0001\u0002\u0002\u0013\u0005a\bC\u0004{\u0001\u0005\u0005I\u0011A>\t\u0013\u0005\r\u0001!!A\u0005B\u0005\u0015\u0001\"CA\n\u0001\u0005\u0005I\u0011AA\u000b\u0011%\ty\u0002AA\u0001\n\u0003\n\t\u0003C\u0005\u0002&\u0001\t\t\u0011\"\u0011\u0002(!I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00131F\u0004\n\u0003wi\u0012\u0011!E\u0001\u0003{1\u0001\u0002H\u000f\u0002\u0002#\u0005\u0011q\b\u0005\u0007\u0017Z!\t!a\u0016\t\u0013\u0005ec#!A\u0005F\u0005m\u0003\"CA/-\u0005\u0005I\u0011QA0\u0011%\t9GFA\u0001\n\u0003\u000bI\u0007C\u0005\u0002|Y\t\t\u0011\"\u0003\u0002~\tq1\u000b[;gM2,'\t\\8dW&#'B\u0001\u0010 \u0003\u001d\u0019Ho\u001c:bO\u0016T!\u0001I\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\t\u001a\u0013AB1qC\u000eDWMC\u0001%\u0003\ry'oZ\u0002\u0001'\u0011\u0001qeK\u0019\u0011\u0005!JS\"A\u000f\n\u0005)j\"a\u0002\"m_\u000e\\\u0017\n\u001a\t\u0003Y=j\u0011!\f\u0006\u0002]\u0005)1oY1mC&\u0011\u0001'\f\u0002\b!J|G-^2u!\t\u0011$H\u0004\u00024q9\u0011AgN\u0007\u0002k)\u0011a'J\u0001\u0007yI|w\u000e\u001e \n\u00039J!!O\u0017\u0002\u000fA\f7m[1hK&\u00111\b\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003s5\n\u0011b\u001d5vM\u001adW-\u00133\u0016\u0003}\u0002\"\u0001\f!\n\u0005\u0005k#aA%oi\u0006Q1\u000f[;gM2,\u0017\n\u001a\u0011\u0002\u000b5\f\u0007/\u00133\u0016\u0003\u0015\u0003\"\u0001\f$\n\u0005\u001dk#\u0001\u0002'p]\u001e\fa!\\1q\u0013\u0012\u0004\u0013\u0001\u0003:fIV\u001cW-\u00133\u0002\u0013I,G-^2f\u0013\u0012\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003N\u001d>\u0003\u0006C\u0001\u0015\u0001\u0011\u0015it\u00011\u0001@\u0011\u0015\u0019u\u00011\u0001F\u0011\u0015Iu\u00011\u0001@\u0003\u0011q\u0017-\\3\u0016\u0003M\u0003\"\u0001\u0016-\u000f\u0005U3\u0006C\u0001\u001b.\u0013\t9V&\u0001\u0004Qe\u0016$WMZ\u0005\u00033j\u0013aa\u0015;sS:<'BA,.\u0003\u0011\u0019w\u000e]=\u0015\t5kfl\u0018\u0005\b{%\u0001\n\u00111\u0001@\u0011\u001d\u0019\u0015\u0002%AA\u0002\u0015Cq!S\u0005\u0011\u0002\u0003\u0007q(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\tT#aP2,\u0003\u0011\u0004\"!\u001a6\u000e\u0003\u0019T!a\u001a5\u0002\u0013Ut7\r[3dW\u0016$'BA5.\u0003)\tgN\\8uCRLwN\\\u0005\u0003W\u001a\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012A\u001c\u0016\u0003\u000b\u000e\fabY8qs\u0012\"WMZ1vYR$3'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002eB\u00111\u000f_\u0007\u0002i*\u0011QO^\u0001\u0005Y\u0006twMC\u0001x\u0003\u0011Q\u0017M^1\n\u0005e#\u0018\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003y~\u0004\"\u0001L?\n\u0005yl#aA!os\"A\u0011\u0011A\b\u0002\u0002\u0003\u0007q(A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u000f\u0001R!!\u0003\u0002\u0010ql!!a\u0003\u000b\u0007\u00055Q&\u0001\u0006d_2dWm\u0019;j_:LA!!\u0005\u0002\f\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9\"!\b\u0011\u00071\nI\"C\u0002\u0002\u001c5\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u0002E\t\t\u00111\u0001}\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007I\f\u0019\u0003\u0003\u0005\u0002\u0002I\t\t\u00111\u0001@\u0003!A\u0017m\u001d5D_\u0012,G#A \u0002\r\u0015\fX/\u00197t)\u0011\t9\"!\f\t\u0011\u0005\u0005A#!AA\u0002qD3\u0001AA\u0019!\u0011\t\u0019$a\u000e\u000e\u0005\u0005U\"BA5 \u0013\u0011\tI$!\u000e\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002\u001dMCWO\u001a4mK\ncwnY6JIB\u0011\u0001FF\n\u0006-\u0005\u0005\u0013Q\n\t\t\u0003\u0007\nIeP#@\u001b6\u0011\u0011Q\t\u0006\u0004\u0003\u000fj\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u0017\n)EA\tBEN$(/Y2u\rVt7\r^5p]N\u0002B!a\u0014\u0002V5\u0011\u0011\u0011\u000b\u0006\u0004\u0003'2\u0018AA5p\u0013\rY\u0014\u0011\u000b\u000b\u0003\u0003{\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002e\u0006)\u0011\r\u001d9msR9Q*!\u0019\u0002d\u0005\u0015\u0004\"B\u001f\u001a\u0001\u0004y\u0004\"B\"\u001a\u0001\u0004)\u0005\"B%\u001a\u0001\u0004y\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003W\n9\bE\u0003-\u0003[\n\t(C\u0002\u0002p5\u0012aa\u00149uS>t\u0007C\u0002\u0017\u0002t}*u(C\u0002\u0002v5\u0012a\u0001V;qY\u0016\u001c\u0004\u0002CA=5\u0005\u0005\t\u0019A'\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0000A\u00191/!!\n\u0007\u0005\rEO\u0001\u0004PE*,7\r\u001e"
)
public class ShuffleBlockId extends BlockId implements Product, Serializable {
   private final int shuffleId;
   private final long mapId;
   private final int reduceId;

   public static Option unapply(final ShuffleBlockId x$0) {
      return ShuffleBlockId$.MODULE$.unapply(x$0);
   }

   public static ShuffleBlockId apply(final int shuffleId, final long mapId, final int reduceId) {
      return ShuffleBlockId$.MODULE$.apply(shuffleId, mapId, reduceId);
   }

   public static Function1 tupled() {
      return ShuffleBlockId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShuffleBlockId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public long mapId() {
      return this.mapId;
   }

   public int reduceId() {
      return this.reduceId;
   }

   public String name() {
      int var10000 = this.shuffleId();
      return "shuffle_" + var10000 + "_" + this.mapId() + "_" + this.reduceId();
   }

   public ShuffleBlockId copy(final int shuffleId, final long mapId, final int reduceId) {
      return new ShuffleBlockId(shuffleId, mapId, reduceId);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public long copy$default$2() {
      return this.mapId();
   }

   public int copy$default$3() {
      return this.reduceId();
   }

   public String productPrefix() {
      return "ShuffleBlockId";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.shuffleId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.mapId());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.reduceId());
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
      return x$1 instanceof ShuffleBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "shuffleId";
         }
         case 1 -> {
            return "mapId";
         }
         case 2 -> {
            return "reduceId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.shuffleId());
      var1 = Statics.mix(var1, Statics.longHash(this.mapId()));
      var1 = Statics.mix(var1, this.reduceId());
      return Statics.finalizeHash(var1, 3);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label40: {
            if (x$1 instanceof ShuffleBlockId) {
               ShuffleBlockId var4 = (ShuffleBlockId)x$1;
               if (this.shuffleId() == var4.shuffleId() && this.mapId() == var4.mapId() && this.reduceId() == var4.reduceId() && var4.canEqual(this)) {
                  break label40;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public ShuffleBlockId(final int shuffleId, final long mapId, final int reduceId) {
      this.shuffleId = shuffleId;
      this.mapId = mapId;
      this.reduceId = reduceId;
      Product.$init$(this);
   }
}
