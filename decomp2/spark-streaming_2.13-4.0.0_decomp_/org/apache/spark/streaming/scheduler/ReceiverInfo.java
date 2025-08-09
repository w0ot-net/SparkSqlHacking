package org.apache.spark.streaming.scheduler;

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
   bytes = "\u0006\u0005\t\ra\u0001B\u00193\u0001vB\u0001b\u0015\u0001\u0003\u0016\u0004%\t\u0001\u0016\u0005\t1\u0002\u0011\t\u0012)A\u0005+\"A\u0011\f\u0001BK\u0002\u0013\u0005!\f\u0003\u0005d\u0001\tE\t\u0015!\u0003\\\u0011!!\u0007A!f\u0001\n\u0003)\u0007\u0002C5\u0001\u0005#\u0005\u000b\u0011\u00024\t\u0011)\u0004!Q3A\u0005\u0002iC\u0001b\u001b\u0001\u0003\u0012\u0003\u0006Ia\u0017\u0005\tY\u0002\u0011)\u001a!C\u00015\"AQ\u000e\u0001B\tB\u0003%1\f\u0003\u0005o\u0001\tU\r\u0011\"\u0001[\u0011!y\u0007A!E!\u0002\u0013Y\u0006\u0002\u00039\u0001\u0005+\u0007I\u0011\u0001.\t\u0011E\u0004!\u0011#Q\u0001\nmC\u0001B\u001d\u0001\u0003\u0016\u0004%\ta\u001d\u0005\to\u0002\u0011\t\u0012)A\u0005i\")\u0001\u0010\u0001C\u0001s\"I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00111\u0002\u0005\n\u0003;\u0001\u0011\u0013!C\u0001\u0003?A\u0011\"!\u000e\u0001#\u0003%\t!a\u000e\t\u0013\u0005m\u0002!%A\u0005\u0002\u0005u\u0002\"CA!\u0001E\u0005I\u0011AA\u001c\u0011%\t\u0019\u0005AI\u0001\n\u0003\t9\u0004C\u0005\u0002F\u0001\t\n\u0011\"\u0001\u00028!I\u0011q\t\u0001\u0012\u0002\u0013\u0005\u0011q\u0007\u0005\n\u0003\u0013\u0002\u0011\u0013!C\u0001\u0003\u0017B\u0011\"a\u0014\u0001\u0003\u0003%\t%!\u0015\t\u0011\u0005\u0005\u0004!!A\u0005\u0002QC\u0011\"a\u0019\u0001\u0003\u0003%\t!!\u001a\t\u0013\u0005E\u0004!!A\u0005B\u0005M\u0004\"CAA\u0001\u0005\u0005I\u0011AAB\u0011%\t9\tAA\u0001\n\u0003\nI\tC\u0005\u0002\u000e\u0002\t\t\u0011\"\u0011\u0002\u0010\"I\u0011\u0011\u0013\u0001\u0002\u0002\u0013\u0005\u00131\u0013\u0005\n\u0003+\u0003\u0011\u0011!C!\u0003/;\u0011\"a*3\u0003\u0003E\t!!+\u0007\u0011E\u0012\u0014\u0011!E\u0001\u0003WCa\u0001_\u0013\u0005\u0002\u0005\r\u0007\"CAIK\u0005\u0005IQIAJ\u0011%\t)-JA\u0001\n\u0003\u000b9\rC\u0005\u0002Z\u0016\n\n\u0011\"\u0001\u00028!I\u00111\\\u0013\u0012\u0002\u0013\u0005\u0011q\u0007\u0005\n\u0003;,\u0013\u0013!C\u0001\u0003\u0017B\u0011\"a8&\u0003\u0003%\t)!9\t\u0013\u0005MX%%A\u0005\u0002\u0005]\u0002\"CA{KE\u0005I\u0011AA\u001c\u0011%\t90JI\u0001\n\u0003\tY\u0005C\u0005\u0002z\u0016\n\t\u0011\"\u0003\u0002|\na!+Z2fSZ,'/\u00138g_*\u00111\u0007N\u0001\ng\u000eDW\rZ;mKJT!!\u000e\u001c\u0002\u0013M$(/Z1nS:<'BA\u001c9\u0003\u0015\u0019\b/\u0019:l\u0015\tI$(\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002w\u0005\u0019qN]4\u0004\u0001M!\u0001A\u0010#H!\ty$)D\u0001A\u0015\u0005\t\u0015!B:dC2\f\u0017BA\"A\u0005\u0019\te.\u001f*fMB\u0011q(R\u0005\u0003\r\u0002\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002I!:\u0011\u0011J\u0014\b\u0003\u00156k\u0011a\u0013\u0006\u0003\u0019r\na\u0001\u0010:p_Rt\u0014\"A!\n\u0005=\u0003\u0015a\u00029bG.\fw-Z\u0005\u0003#J\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!a\u0014!\u0002\u0011M$(/Z1n\u0013\u0012,\u0012!\u0016\t\u0003\u007fYK!a\u0016!\u0003\u0007%sG/A\u0005tiJ,\u0017-\\%eA\u0005!a.Y7f+\u0005Y\u0006C\u0001/a\u001d\tif\f\u0005\u0002K\u0001&\u0011q\fQ\u0001\u0007!J,G-\u001a4\n\u0005\u0005\u0014'AB*ue&twM\u0003\u0002`\u0001\u0006)a.Y7fA\u00051\u0011m\u0019;jm\u0016,\u0012A\u001a\t\u0003\u007f\u001dL!\u0001\u001b!\u0003\u000f\t{w\u000e\\3b]\u00069\u0011m\u0019;jm\u0016\u0004\u0013\u0001\u00037pG\u0006$\u0018n\u001c8\u0002\u00131|7-\u0019;j_:\u0004\u0013AC3yK\u000e,Ho\u001c:JI\u0006YQ\r_3dkR|'/\u00133!\u0003Aa\u0017m\u001d;FeJ|'/T3tg\u0006<W-A\tmCN$XI\u001d:pe6+7o]1hK\u0002\n\u0011\u0002\\1ti\u0016\u0013(o\u001c:\u0002\u00151\f7\u000f^#se>\u0014\b%A\u0007mCN$XI\u001d:peRKW.Z\u000b\u0002iB\u0011q(^\u0005\u0003m\u0002\u0013A\u0001T8oO\u0006qA.Y:u\u000bJ\u0014xN\u001d+j[\u0016\u0004\u0013A\u0002\u001fj]&$h\bF\u0007{yvtx0!\u0001\u0002\u0004\u0005\u0015\u0011q\u0001\t\u0003w\u0002i\u0011A\r\u0005\u0006'F\u0001\r!\u0016\u0005\u00063F\u0001\ra\u0017\u0005\u0006IF\u0001\rA\u001a\u0005\u0006UF\u0001\ra\u0017\u0005\u0006YF\u0001\ra\u0017\u0005\b]F\u0001\n\u00111\u0001\\\u0011\u001d\u0001\u0018\u0003%AA\u0002mCqA]\t\u0011\u0002\u0003\u0007A/\u0001\u0003d_BLH#\u0005>\u0002\u000e\u0005=\u0011\u0011CA\n\u0003+\t9\"!\u0007\u0002\u001c!91K\u0005I\u0001\u0002\u0004)\u0006bB-\u0013!\u0003\u0005\ra\u0017\u0005\bIJ\u0001\n\u00111\u0001g\u0011\u001dQ'\u0003%AA\u0002mCq\u0001\u001c\n\u0011\u0002\u0003\u00071\fC\u0004o%A\u0005\t\u0019A.\t\u000fA\u0014\u0002\u0013!a\u00017\"9!O\u0005I\u0001\u0002\u0004!\u0018AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003CQ3!VA\u0012W\t\t)\u0003\u0005\u0003\u0002(\u0005ERBAA\u0015\u0015\u0011\tY#!\f\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0018\u0001\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005M\u0012\u0011\u0006\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003sQ3aWA\u0012\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!a\u0010+\u0007\u0019\f\u0019#\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%k\u0005q1m\u001c9zI\u0011,g-Y;mi\u00122\u0014AD2paf$C-\u001a4bk2$HeN\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00139+\t\tiEK\u0002u\u0003G\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA*!\u0011\t)&a\u0018\u000e\u0005\u0005]#\u0002BA-\u00037\nA\u0001\\1oO*\u0011\u0011QL\u0001\u0005U\u00064\u0018-C\u0002b\u0003/\nA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002h\u00055\u0004cA \u0002j%\u0019\u00111\u000e!\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002pu\t\t\u00111\u0001V\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u000f\t\u0007\u0003o\ni(a\u001a\u000e\u0005\u0005e$bAA>\u0001\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005}\u0014\u0011\u0010\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000eF\u0002g\u0003\u000bC\u0011\"a\u001c \u0003\u0003\u0005\r!a\u001a\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003'\nY\t\u0003\u0005\u0002p\u0001\n\t\u00111\u0001V\u0003!A\u0017m\u001d5D_\u0012,G#A+\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0015\u0002\r\u0015\fX/\u00197t)\r1\u0017\u0011\u0014\u0005\n\u0003_\u001a\u0013\u0011!a\u0001\u0003OB3\u0001AAO!\u0011\ty*a)\u000e\u0005\u0005\u0005&bAA\u0018m%!\u0011QUAQ\u00051!UM^3m_B,'/\u00119j\u00031\u0011VmY3jm\u0016\u0014\u0018J\u001c4p!\tYXeE\u0003&\u0003[\u000bI\fE\u0007\u00020\u0006UVk\u00174\\7n[FO_\u0007\u0003\u0003cS1!a-A\u0003\u001d\u0011XO\u001c;j[\u0016LA!a.\u00022\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001d\u0011\t\u0005m\u0016\u0011Y\u0007\u0003\u0003{SA!a0\u0002\\\u0005\u0011\u0011n\\\u0005\u0004#\u0006uFCAAU\u0003\u0015\t\u0007\u000f\u001d7z)EQ\u0018\u0011ZAf\u0003\u001b\fy-!5\u0002T\u0006U\u0017q\u001b\u0005\u0006'\"\u0002\r!\u0016\u0005\u00063\"\u0002\ra\u0017\u0005\u0006I\"\u0002\rA\u001a\u0005\u0006U\"\u0002\ra\u0017\u0005\u0006Y\"\u0002\ra\u0017\u0005\b]\"\u0002\n\u00111\u0001\\\u0011\u001d\u0001\b\u0006%AA\u0002mCqA\u001d\u0015\u0011\u0002\u0003\u0007A/A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00137\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012:\u0014aD1qa2LH\u0005Z3gCVdG\u000f\n\u001d\u0002\u000fUt\u0017\r\u001d9msR!\u00111]Ax!\u0015y\u0014Q]Au\u0013\r\t9\u000f\u0011\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0017}\nY/V.g7n[6\f^\u0005\u0004\u0003[\u0004%A\u0002+va2,\u0007\b\u0003\u0005\u0002r2\n\t\u00111\u0001{\u0003\rAH\u0005M\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001c\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00138\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%q\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q \t\u0005\u0003+\ny0\u0003\u0003\u0003\u0002\u0005]#AB(cU\u0016\u001cG\u000f"
)
public class ReceiverInfo implements Product, Serializable {
   private final int streamId;
   private final String name;
   private final boolean active;
   private final String location;
   private final String executorId;
   private final String lastErrorMessage;
   private final String lastError;
   private final long lastErrorTime;

   public static long $lessinit$greater$default$8() {
      return ReceiverInfo$.MODULE$.$lessinit$greater$default$8();
   }

   public static String $lessinit$greater$default$7() {
      return ReceiverInfo$.MODULE$.$lessinit$greater$default$7();
   }

   public static String $lessinit$greater$default$6() {
      return ReceiverInfo$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option unapply(final ReceiverInfo x$0) {
      return ReceiverInfo$.MODULE$.unapply(x$0);
   }

   public static long apply$default$8() {
      return ReceiverInfo$.MODULE$.apply$default$8();
   }

   public static String apply$default$7() {
      return ReceiverInfo$.MODULE$.apply$default$7();
   }

   public static String apply$default$6() {
      return ReceiverInfo$.MODULE$.apply$default$6();
   }

   public static ReceiverInfo apply(final int streamId, final String name, final boolean active, final String location, final String executorId, final String lastErrorMessage, final String lastError, final long lastErrorTime) {
      return ReceiverInfo$.MODULE$.apply(streamId, name, active, location, executorId, lastErrorMessage, lastError, lastErrorTime);
   }

   public static Function1 tupled() {
      return ReceiverInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ReceiverInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int streamId() {
      return this.streamId;
   }

   public String name() {
      return this.name;
   }

   public boolean active() {
      return this.active;
   }

   public String location() {
      return this.location;
   }

   public String executorId() {
      return this.executorId;
   }

   public String lastErrorMessage() {
      return this.lastErrorMessage;
   }

   public String lastError() {
      return this.lastError;
   }

   public long lastErrorTime() {
      return this.lastErrorTime;
   }

   public ReceiverInfo copy(final int streamId, final String name, final boolean active, final String location, final String executorId, final String lastErrorMessage, final String lastError, final long lastErrorTime) {
      return new ReceiverInfo(streamId, name, active, location, executorId, lastErrorMessage, lastError, lastErrorTime);
   }

   public int copy$default$1() {
      return this.streamId();
   }

   public String copy$default$2() {
      return this.name();
   }

   public boolean copy$default$3() {
      return this.active();
   }

   public String copy$default$4() {
      return this.location();
   }

   public String copy$default$5() {
      return this.executorId();
   }

   public String copy$default$6() {
      return this.lastErrorMessage();
   }

   public String copy$default$7() {
      return this.lastError();
   }

   public long copy$default$8() {
      return this.lastErrorTime();
   }

   public String productPrefix() {
      return "ReceiverInfo";
   }

   public int productArity() {
      return 8;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.streamId());
         }
         case 1 -> {
            return this.name();
         }
         case 2 -> {
            return BoxesRunTime.boxToBoolean(this.active());
         }
         case 3 -> {
            return this.location();
         }
         case 4 -> {
            return this.executorId();
         }
         case 5 -> {
            return this.lastErrorMessage();
         }
         case 6 -> {
            return this.lastError();
         }
         case 7 -> {
            return BoxesRunTime.boxToLong(this.lastErrorTime());
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
      return x$1 instanceof ReceiverInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "streamId";
         }
         case 1 -> {
            return "name";
         }
         case 2 -> {
            return "active";
         }
         case 3 -> {
            return "location";
         }
         case 4 -> {
            return "executorId";
         }
         case 5 -> {
            return "lastErrorMessage";
         }
         case 6 -> {
            return "lastError";
         }
         case 7 -> {
            return "lastErrorTime";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.streamId());
      var1 = Statics.mix(var1, Statics.anyHash(this.name()));
      var1 = Statics.mix(var1, this.active() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.location()));
      var1 = Statics.mix(var1, Statics.anyHash(this.executorId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.lastErrorMessage()));
      var1 = Statics.mix(var1, Statics.anyHash(this.lastError()));
      var1 = Statics.mix(var1, Statics.longHash(this.lastErrorTime()));
      return Statics.finalizeHash(var1, 8);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var14;
      if (this != x$1) {
         label91: {
            if (x$1 instanceof ReceiverInfo) {
               ReceiverInfo var4 = (ReceiverInfo)x$1;
               if (this.streamId() == var4.streamId() && this.active() == var4.active() && this.lastErrorTime() == var4.lastErrorTime()) {
                  label84: {
                     String var10000 = this.name();
                     String var5 = var4.name();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label84;
                     }

                     var10000 = this.location();
                     String var6 = var4.location();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label84;
                     }

                     var10000 = this.executorId();
                     String var7 = var4.executorId();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label84;
                     }

                     var10000 = this.lastErrorMessage();
                     String var8 = var4.lastErrorMessage();
                     if (var10000 == null) {
                        if (var8 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var8)) {
                        break label84;
                     }

                     var10000 = this.lastError();
                     String var9 = var4.lastError();
                     if (var10000 == null) {
                        if (var9 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var9)) {
                        break label84;
                     }

                     if (var4.canEqual(this)) {
                        break label91;
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

   public ReceiverInfo(final int streamId, final String name, final boolean active, final String location, final String executorId, final String lastErrorMessage, final String lastError, final long lastErrorTime) {
      this.streamId = streamId;
      this.name = name;
      this.active = active;
      this.location = location;
      this.executorId = executorId;
      this.lastErrorMessage = lastErrorMessage;
      this.lastError = lastError;
      this.lastErrorTime = lastErrorTime;
      Product.$init$(this);
   }
}
