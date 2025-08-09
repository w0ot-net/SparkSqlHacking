package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=h\u0001B\u0015+\u0001VB\u0001b\u0013\u0001\u0003\u0016\u0004%\t\u0001\u0014\u0005\t#\u0002\u0011\t\u0012)A\u0005\u001b\"A!\u000b\u0001BK\u0002\u0013\u00051\u000b\u0003\u0005X\u0001\tE\t\u0015!\u0003U\u0011!A\u0006A!f\u0001\n\u0003I\u0006\u0002\u00032\u0001\u0005#\u0005\u000b\u0011\u0002.\t\u0011\r\u0004!Q3A\u0005\u0002eC\u0001\u0002\u001a\u0001\u0003\u0012\u0003\u0006IA\u0017\u0005\tK\u0002\u0011)\u001a!C\u0001M\"AQ\u000e\u0001B\tB\u0003%q\r\u0003\u0005o\u0001\tU\r\u0011\"\u0001g\u0011!y\u0007A!E!\u0002\u00139\u0007\u0002\u00039\u0001\u0005+\u0007I\u0011A9\t\u0011M\u0004!\u0011#Q\u0001\nIDQ\u0001\u001e\u0001\u0005\u0002UDQa \u0001\u0005\u0002\u0019D\u0011\"!\u0001\u0001\u0003\u0003%\t!a\u0001\t\u0013\u0005M\u0001!%A\u0005\u0002\u0005U\u0001\"CA\u0016\u0001E\u0005I\u0011AA\u0017\u0011%\t\t\u0004AI\u0001\n\u0003\t\u0019\u0004C\u0005\u00028\u0001\t\n\u0011\"\u0001\u00024!I\u0011\u0011\b\u0001\u0012\u0002\u0013\u0005\u00111\b\u0005\n\u0003\u007f\u0001\u0011\u0013!C\u0001\u0003wA\u0011\"!\u0011\u0001#\u0003%\t!a\u0011\t\u0013\u0005\u001d\u0003!!A\u0005B\u0005%\u0003\u0002CA-\u0001\u0005\u0005I\u0011A*\t\u0013\u0005m\u0003!!A\u0005\u0002\u0005u\u0003\"CA5\u0001\u0005\u0005I\u0011IA6\u0011%\tI\bAA\u0001\n\u0003\tY\bC\u0005\u0002\u0006\u0002\t\t\u0011\"\u0011\u0002\b\"I\u00111\u0012\u0001\u0002\u0002\u0013\u0005\u0013Q\u0012\u0005\n\u0003\u001f\u0003\u0011\u0011!C!\u0003#C\u0011\"a%\u0001\u0003\u0003%\t%!&\b\u0013\u0005\u0015&&!A\t\u0002\u0005\u001df\u0001C\u0015+\u0003\u0003E\t!!+\t\rQ\u001cC\u0011AAa\u0011%\tyiIA\u0001\n\u000b\n\t\nC\u0005\u0002D\u000e\n\t\u0011\"!\u0002F\"I\u0011Q[\u0012\u0002\u0002\u0013\u0005\u0015q\u001b\u0005\n\u0003K\u001c\u0013\u0011!C\u0005\u0003O\u00141cT;uaV$x\n]3sCRLwN\\%oM>T!a\u000b\u0017\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(BA\u0017/\u0003%\u0019HO]3b[&twM\u0003\u00020a\u0005)1\u000f]1sW*\u0011\u0011GM\u0001\u0007CB\f7\r[3\u000b\u0003M\n1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u001c=\u007fA\u0011qGO\u0007\u0002q)\t\u0011(A\u0003tG\u0006d\u0017-\u0003\u0002<q\t1\u0011I\\=SK\u001a\u0004\"aN\u001f\n\u0005yB$a\u0002)s_\u0012,8\r\u001e\t\u0003\u0001\"s!!\u0011$\u000f\u0005\t+U\"A\"\u000b\u0005\u0011#\u0014A\u0002\u001fs_>$h(C\u0001:\u0013\t9\u0005(A\u0004qC\u000e\\\u0017mZ3\n\u0005%S%\u0001D*fe&\fG.\u001b>bE2,'BA$9\u0003%\u0011\u0017\r^2i)&lW-F\u0001N!\tqu*D\u0001-\u0013\t\u0001FF\u0001\u0003US6,\u0017A\u00032bi\u000eDG+[7fA\u0005\u0011\u0011\u000eZ\u000b\u0002)B\u0011q'V\u0005\u0003-b\u00121!\u00138u\u0003\rIG\rI\u0001\u0005]\u0006lW-F\u0001[!\tYvL\u0004\u0002];B\u0011!\tO\u0005\u0003=b\na\u0001\u0015:fI\u00164\u0017B\u00011b\u0005\u0019\u0019FO]5oO*\u0011a\fO\u0001\u0006]\u0006lW\rI\u0001\fI\u0016\u001c8M]5qi&|g.\u0001\u0007eKN\u001c'/\u001b9uS>t\u0007%A\u0005ti\u0006\u0014H\u000fV5nKV\tq\rE\u00028Q*L!!\u001b\u001d\u0003\r=\u0003H/[8o!\t94.\u0003\u0002mq\t!Aj\u001c8h\u0003)\u0019H/\u0019:u)&lW\rI\u0001\bK:$G+[7f\u0003!)g\u000e\u001a+j[\u0016\u0004\u0013!\u00044bS2,(/\u001a*fCN|g.F\u0001s!\r9\u0004NW\u0001\u000fM\u0006LG.\u001e:f%\u0016\f7o\u001c8!\u0003\u0019a\u0014N\\5u}QAa\u000f_={wrlh\u0010\u0005\u0002x\u00015\t!\u0006C\u0003L\u001f\u0001\u0007Q\nC\u0003S\u001f\u0001\u0007A\u000bC\u0003Y\u001f\u0001\u0007!\fC\u0003d\u001f\u0001\u0007!\fC\u0003f\u001f\u0001\u0007q\rC\u0003o\u001f\u0001\u0007q\rC\u0003q\u001f\u0001\u0007!/\u0001\u0005ekJ\fG/[8o\u0003\u0011\u0019w\u000e]=\u0015\u001fY\f)!a\u0002\u0002\n\u0005-\u0011QBA\b\u0003#AqaS\t\u0011\u0002\u0003\u0007Q\nC\u0004S#A\u0005\t\u0019\u0001+\t\u000fa\u000b\u0002\u0013!a\u00015\"91-\u0005I\u0001\u0002\u0004Q\u0006bB3\u0012!\u0003\u0005\ra\u001a\u0005\b]F\u0001\n\u00111\u0001h\u0011\u001d\u0001\u0018\u0003%AA\u0002I\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\u0018)\u001aQ*!\u0007,\u0005\u0005m\u0001\u0003BA\u000f\u0003Oi!!a\b\u000b\t\u0005\u0005\u00121E\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\n9\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003S\tyBA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u00020)\u001aA+!\u0007\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011Q\u0007\u0016\u00045\u0006e\u0011AD2paf$C-\u001a4bk2$H\u0005N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00136+\t\tiDK\u0002h\u00033\tabY8qs\u0012\"WMZ1vYR$c'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0016\u0005\u0005\u0015#f\u0001:\u0002\u001a\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!a\u0013\u0011\t\u00055\u0013qK\u0007\u0003\u0003\u001fRA!!\u0015\u0002T\u0005!A.\u00198h\u0015\t\t)&\u0001\u0003kCZ\f\u0017b\u00011\u0002P\u0005a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA0\u0003K\u00022aNA1\u0013\r\t\u0019\u0007\u000f\u0002\u0004\u0003:L\b\u0002CA47\u0005\u0005\t\u0019\u0001+\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ti\u0007\u0005\u0004\u0002p\u0005U\u0014qL\u0007\u0003\u0003cR1!a\u001d9\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003o\n\tH\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA?\u0003\u0007\u00032aNA@\u0013\r\t\t\t\u000f\u0002\b\u0005>|G.Z1o\u0011%\t9'HA\u0001\u0002\u0004\ty&\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA&\u0003\u0013C\u0001\"a\u001a\u001f\u0003\u0003\u0005\r\u0001V\u0001\tQ\u0006\u001c\bnQ8eKR\tA+\u0001\u0005u_N#(/\u001b8h)\t\tY%\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003{\n9\nC\u0005\u0002h\u0005\n\t\u00111\u0001\u0002`!\u001a\u0001!a'\u0011\t\u0005u\u0015\u0011U\u0007\u0003\u0003?S1!!\n/\u0013\u0011\t\u0019+a(\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002'=+H\u000f];u\u001fB,'/\u0019;j_:LeNZ8\u0011\u0005]\u001c3#B\u0012\u0002,\u0006]\u0006\u0003DAW\u0003gkEK\u0017.hOJ4XBAAX\u0015\r\t\t\fO\u0001\beVtG/[7f\u0013\u0011\t),a,\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tw\u0007\u0005\u0003\u0002:\u0006}VBAA^\u0015\u0011\ti,a\u0015\u0002\u0005%|\u0017bA%\u0002<R\u0011\u0011qU\u0001\u0006CB\u0004H.\u001f\u000b\u0010m\u0006\u001d\u0017\u0011ZAf\u0003\u001b\fy-!5\u0002T\")1J\na\u0001\u001b\")!K\na\u0001)\")\u0001L\na\u00015\")1M\na\u00015\")QM\na\u0001O\")aN\na\u0001O\")\u0001O\na\u0001e\u00069QO\\1qa2LH\u0003BAm\u0003C\u0004Ba\u000e5\u0002\\BQq'!8N)jSvm\u001a:\n\u0007\u0005}\u0007H\u0001\u0004UkBdWm\u000e\u0005\t\u0003G<\u0013\u0011!a\u0001m\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005%\b\u0003BA'\u0003WLA!!<\u0002P\t1qJ\u00196fGR\u0004"
)
public class OutputOperationInfo implements Product, Serializable {
   private final Time batchTime;
   private final int id;
   private final String name;
   private final String description;
   private final Option startTime;
   private final Option endTime;
   private final Option failureReason;

   public static Option unapply(final OutputOperationInfo x$0) {
      return OutputOperationInfo$.MODULE$.unapply(x$0);
   }

   public static OutputOperationInfo apply(final Time batchTime, final int id, final String name, final String description, final Option startTime, final Option endTime, final Option failureReason) {
      return OutputOperationInfo$.MODULE$.apply(batchTime, id, name, description, startTime, endTime, failureReason);
   }

   public static Function1 tupled() {
      return OutputOperationInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return OutputOperationInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Time batchTime() {
      return this.batchTime;
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

   public OutputOperationInfo copy(final Time batchTime, final int id, final String name, final String description, final Option startTime, final Option endTime, final Option failureReason) {
      return new OutputOperationInfo(batchTime, id, name, description, startTime, endTime, failureReason);
   }

   public Time copy$default$1() {
      return this.batchTime();
   }

   public int copy$default$2() {
      return this.id();
   }

   public String copy$default$3() {
      return this.name();
   }

   public String copy$default$4() {
      return this.description();
   }

   public Option copy$default$5() {
      return this.startTime();
   }

   public Option copy$default$6() {
      return this.endTime();
   }

   public Option copy$default$7() {
      return this.failureReason();
   }

   public String productPrefix() {
      return "OutputOperationInfo";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.batchTime();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.id());
         }
         case 2 -> {
            return this.name();
         }
         case 3 -> {
            return this.description();
         }
         case 4 -> {
            return this.startTime();
         }
         case 5 -> {
            return this.endTime();
         }
         case 6 -> {
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
      return x$1 instanceof OutputOperationInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "batchTime";
         }
         case 1 -> {
            return "id";
         }
         case 2 -> {
            return "name";
         }
         case 3 -> {
            return "description";
         }
         case 4 -> {
            return "startTime";
         }
         case 5 -> {
            return "endTime";
         }
         case 6 -> {
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
      var1 = Statics.mix(var1, Statics.anyHash(this.batchTime()));
      var1 = Statics.mix(var1, this.id());
      var1 = Statics.mix(var1, Statics.anyHash(this.name()));
      var1 = Statics.mix(var1, Statics.anyHash(this.description()));
      var1 = Statics.mix(var1, Statics.anyHash(this.startTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.endTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.failureReason()));
      return Statics.finalizeHash(var1, 7);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var16;
      if (this != x$1) {
         label91: {
            if (x$1 instanceof OutputOperationInfo) {
               OutputOperationInfo var4 = (OutputOperationInfo)x$1;
               if (this.id() == var4.id()) {
                  label84: {
                     Time var10000 = this.batchTime();
                     Time var5 = var4.batchTime();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label84;
                     }

                     String var11 = this.name();
                     String var6 = var4.name();
                     if (var11 == null) {
                        if (var6 != null) {
                           break label84;
                        }
                     } else if (!var11.equals(var6)) {
                        break label84;
                     }

                     var11 = this.description();
                     String var7 = var4.description();
                     if (var11 == null) {
                        if (var7 != null) {
                           break label84;
                        }
                     } else if (!var11.equals(var7)) {
                        break label84;
                     }

                     Option var13 = this.startTime();
                     Option var8 = var4.startTime();
                     if (var13 == null) {
                        if (var8 != null) {
                           break label84;
                        }
                     } else if (!var13.equals(var8)) {
                        break label84;
                     }

                     var13 = this.endTime();
                     Option var9 = var4.endTime();
                     if (var13 == null) {
                        if (var9 != null) {
                           break label84;
                        }
                     } else if (!var13.equals(var9)) {
                        break label84;
                     }

                     var13 = this.failureReason();
                     Option var10 = var4.failureReason();
                     if (var13 == null) {
                        if (var10 != null) {
                           break label84;
                        }
                     } else if (!var13.equals(var10)) {
                        break label84;
                     }

                     if (var4.canEqual(this)) {
                        break label91;
                     }
                  }
               }
            }

            var16 = false;
            return var16;
         }
      }

      var16 = true;
      return var16;
   }

   // $FF: synthetic method
   public static final Option $anonfun$duration$1(final OutputOperationInfo $this, final long s) {
      return $this.endTime().map((JFunction1.mcJJ.sp)(e) -> e - s);
   }

   public OutputOperationInfo(final Time batchTime, final int id, final String name, final String description, final Option startTime, final Option endTime, final Option failureReason) {
      this.batchTime = batchTime;
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
