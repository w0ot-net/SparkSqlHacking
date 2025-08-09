package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Enumeration;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tEc!B\u00181\u0001JR\u0004\u0002C)\u0001\u0005+\u0007I\u0011\u0001*\t\u0011Y\u0003!\u0011#Q\u0001\nMC\u0001b\u0016\u0001\u0003\u0016\u0004%\t\u0001\u0017\u0005\tU\u0002\u0011\t\u0012)A\u00053\"A1\u000e\u0001BK\u0002\u0013\u0005A\u000e\u0003\u0005y\u0001\tE\t\u0015!\u0003n\u0011!I\bA!f\u0001\n\u0003Q\b\u0002C@\u0001\u0005#\u0005\u000b\u0011B>\t\u0015\u0005\u0005\u0001A!f\u0001\n\u0003\t\u0019\u0001\u0003\u0006\u0002\u0018\u0001\u0011\t\u0012)A\u0005\u0003\u000bA!\"!\u0007\u0001\u0005+\u0007I\u0011AA\u000e\u0011)\tY\u0003\u0001B\tB\u0003%\u0011Q\u0004\u0005\u000b\u0003[\u0001!Q3A\u0005\u0002\u0005=\u0002BCA\u001e\u0001\tE\t\u0015!\u0003\u00022!9\u0011Q\b\u0001\u0005\u0002\u0005}\u0002bBA)\u0001\u0011\u0005\u00111\u000b\u0005\n\u00037\u0002\u0011\u0011!C\u0001\u0003;B\u0011\"!\u001c\u0001#\u0003%\t!a\u001c\t\u0013\u0005\u0015\u0005!%A\u0005\u0002\u0005\u001d\u0005\"CAF\u0001E\u0005I\u0011AAG\u0011%\t\t\nAI\u0001\n\u0003\t\u0019\nC\u0005\u0002\u0018\u0002\t\n\u0011\"\u0001\u0002\u001a\"I\u0011Q\u0014\u0001\u0012\u0002\u0013\u0005\u0011q\u0014\u0005\n\u0003G\u0003\u0011\u0013!C\u0001\u0003KC\u0011\"!+\u0001\u0003\u0003%\t%a+\t\u0011\u0005m\u0006!!A\u0005\u0002IC\u0011\"!0\u0001\u0003\u0003%\t!a0\t\u0013\u0005-\u0007!!A\u0005B\u00055\u0007\"CAn\u0001\u0005\u0005I\u0011AAo\u0011%\t9\u000fAA\u0001\n\u0003\nI\u000fC\u0005\u0002n\u0002\t\t\u0011\"\u0011\u0002p\"I\u0011\u0011\u001f\u0001\u0002\u0002\u0013\u0005\u00131\u001f\u0005\n\u0003k\u0004\u0011\u0011!C!\u0003o<!\"a?1\u0003\u0003E\tAMA\u007f\r%y\u0003'!A\t\u0002I\ny\u0010C\u0004\u0002>\r\"\tAa\u0006\t\u0013\u0005E8%!A\u0005F\u0005M\b\"\u0003B\rG\u0005\u0005I\u0011\u0011B\u000e\u0011%\u0011YcII\u0001\n\u0003\tI\nC\u0005\u0003.\r\n\n\u0011\"\u0001\u0002 \"I!qF\u0012\u0012\u0002\u0013\u0005\u0011Q\u0015\u0005\n\u0005c\u0019\u0013\u0011!CA\u0005gA\u0011B!\u0011$#\u0003%\t!!'\t\u0013\t\r3%%A\u0005\u0002\u0005}\u0005\"\u0003B#GE\u0005I\u0011AAS\u0011%\u00119eIA\u0001\n\u0013\u0011IE\u0001\u000bSK\u000e,\u0017N^3s)J\f7m[5oO&sgm\u001c\u0006\u0003cI\n\u0011b]2iK\u0012,H.\u001a:\u000b\u0005M\"\u0014!C:ue\u0016\fW.\u001b8h\u0015\t)d'A\u0003ta\u0006\u00148N\u0003\u00028q\u00051\u0011\r]1dQ\u0016T\u0011!O\u0001\u0004_J<7\u0003\u0002\u0001<\u0003\u0012\u0003\"\u0001P \u000e\u0003uR\u0011AP\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0001v\u0012a!\u00118z%\u00164\u0007C\u0001\u001fC\u0013\t\u0019UHA\u0004Qe>$Wo\u0019;\u0011\u0005\u0015seB\u0001$M\u001d\t95*D\u0001I\u0015\tI%*\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005q\u0014BA'>\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0014)\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u00055k\u0014A\u0003:fG\u0016Lg/\u001a:JIV\t1\u000b\u0005\u0002=)&\u0011Q+\u0010\u0002\u0004\u0013:$\u0018a\u0003:fG\u0016Lg/\u001a:JI\u0002\nQa\u001d;bi\u0016,\u0012!\u0017\t\u00035\u001et!aW3\u000f\u0005q#gBA/d\u001d\tq&M\u0004\u0002`C:\u0011q\tY\u0005\u0002s%\u0011q\u0007O\u0005\u0003kYJ!a\r\u001b\n\u0005E\u0012\u0014B\u000141\u00035\u0011VmY3jm\u0016\u00148\u000b^1uK&\u0011\u0001.\u001b\u0002\u000e%\u0016\u001cW-\u001b<feN#\u0018\r^3\u000b\u0005\u0019\u0004\u0014AB:uCR,\u0007%\u0001\ntG\",G-\u001e7fI2{7-\u0019;j_:\u001cX#A7\u0011\u0007qr\u0007/\u0003\u0002p{\t1q\n\u001d;j_:\u00042!R9t\u0013\t\u0011\bKA\u0002TKF\u0004\"\u0001\u001e<\u000e\u0003UT!!\r\u001b\n\u0005],(\u0001\u0004+bg.dunY1uS>t\u0017aE:dQ\u0016$W\u000f\\3e\u0019>\u001c\u0017\r^5p]N\u0004\u0013a\u0004:v]:LgnZ#yK\u000e,Ho\u001c:\u0016\u0003m\u00042\u0001\u00108}!\t!X0\u0003\u0002\u007fk\nIR\t_3dkR|'oQ1dQ\u0016$\u0016m]6M_\u000e\fG/[8o\u0003A\u0011XO\u001c8j]\u001e,\u00050Z2vi>\u0014\b%\u0001\u0003oC6,WCAA\u0003!\u0011ad.a\u0002\u0011\t\u0005%\u0011\u0011\u0003\b\u0005\u0003\u0017\ti\u0001\u0005\u0002H{%\u0019\u0011qB\u001f\u0002\rA\u0013X\rZ3g\u0013\u0011\t\u0019\"!\u0006\u0003\rM#(/\u001b8h\u0015\r\ty!P\u0001\u0006]\u0006lW\rI\u0001\tK:$\u0007o\\5oiV\u0011\u0011Q\u0004\t\u0005y9\fy\u0002\u0005\u0003\u0002\"\u0005\u001dRBAA\u0012\u0015\r\t)\u0003N\u0001\u0004eB\u001c\u0017\u0002BA\u0015\u0003G\u0011aB\u00159d\u000b:$\u0007o\\5oiJ+g-A\u0005f]\u0012\u0004x.\u001b8uA\u0005IQM\u001d:pe&sgm\\\u000b\u0003\u0003c\u0001B\u0001\u00108\u00024A!\u0011QGA\u001c\u001b\u0005\u0001\u0014bAA\u001da\t\t\"+Z2fSZ,'/\u0012:s_JLeNZ8\u0002\u0015\u0015\u0014(o\u001c:J]\u001a|\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0011\u0003\u0003\n\u0019%!\u0012\u0002H\u0005%\u00131JA'\u0003\u001f\u00022!!\u000e\u0001\u0011\u0015\tv\u00021\u0001T\u0011\u00159v\u00021\u0001Z\u0011\u0015Yw\u00021\u0001n\u0011\u0015Ix\u00021\u0001|\u0011%\t\ta\u0004I\u0001\u0002\u0004\t)\u0001C\u0005\u0002\u001a=\u0001\n\u00111\u0001\u0002\u001e!I\u0011QF\b\u0011\u0002\u0003\u0007\u0011\u0011G\u0001\u000fi>\u0014VmY3jm\u0016\u0014\u0018J\u001c4p+\t\t)\u0006\u0005\u0003\u00026\u0005]\u0013bAA-a\ta!+Z2fSZ,'/\u00138g_\u0006!1m\u001c9z)A\t\t%a\u0018\u0002b\u0005\r\u0014QMA4\u0003S\nY\u0007C\u0004R#A\u0005\t\u0019A*\t\u000f]\u000b\u0002\u0013!a\u00013\"91.\u0005I\u0001\u0002\u0004i\u0007bB=\u0012!\u0003\u0005\ra\u001f\u0005\n\u0003\u0003\t\u0002\u0013!a\u0001\u0003\u000bA\u0011\"!\u0007\u0012!\u0003\u0005\r!!\b\t\u0013\u00055\u0012\u0003%AA\u0002\u0005E\u0012AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003cR3aUA:W\t\t)\b\u0005\u0003\u0002x\u0005\u0005UBAA=\u0015\u0011\tY(! \u0002\u0013Ut7\r[3dW\u0016$'bAA@{\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\r\u0015\u0011\u0010\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003\u0013S3!WA:\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!a$+\u00075\f\u0019(\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0005U%fA>\u0002t\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012*TCAANU\u0011\t)!a\u001d\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%mU\u0011\u0011\u0011\u0015\u0016\u0005\u0003;\t\u0019(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0016\u0005\u0005\u001d&\u0006BA\u0019\u0003g\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAAW!\u0011\ty+!/\u000e\u0005\u0005E&\u0002BAZ\u0003k\u000bA\u0001\\1oO*\u0011\u0011qW\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u0014\u0005E\u0016\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u0003\f9\rE\u0002=\u0003\u0007L1!!2>\u0005\r\te.\u001f\u0005\t\u0003\u0013\\\u0012\u0011!a\u0001'\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a4\u0011\r\u0005E\u0017q[Aa\u001b\t\t\u0019NC\u0002\u0002Vv\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\tI.a5\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003?\f)\u000fE\u0002=\u0003CL1!a9>\u0005\u001d\u0011un\u001c7fC:D\u0011\"!3\u001e\u0003\u0003\u0005\r!!1\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003[\u000bY\u000f\u0003\u0005\u0002Jz\t\t\u00111\u0001T\u0003!A\u0017m\u001d5D_\u0012,G#A*\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!,\u0002\r\u0015\fX/\u00197t)\u0011\ty.!?\t\u0013\u0005%\u0017%!AA\u0002\u0005\u0005\u0017\u0001\u0006*fG\u0016Lg/\u001a:Ue\u0006\u001c7.\u001b8h\u0013:4w\u000eE\u0002\u00026\r\u001aRa\tB\u0001\u0005\u001b\u0001\u0002Ca\u0001\u0003\nMKVn_A\u0003\u0003;\t\t$!\u0011\u000e\u0005\t\u0015!b\u0001B\u0004{\u00059!/\u001e8uS6,\u0017\u0002\u0002B\u0006\u0005\u000b\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c88!\u0011\u0011yA!\u0006\u000e\u0005\tE!\u0002\u0002B\n\u0003k\u000b!![8\n\u0007=\u0013\t\u0002\u0006\u0002\u0002~\u0006)\u0011\r\u001d9msR\u0001\u0012\u0011\tB\u000f\u0005?\u0011\tCa\t\u0003&\t\u001d\"\u0011\u0006\u0005\u0006#\u001a\u0002\ra\u0015\u0005\u0006/\u001a\u0002\r!\u0017\u0005\u0006W\u001a\u0002\r!\u001c\u0005\u0006s\u001a\u0002\ra\u001f\u0005\n\u0003\u00031\u0003\u0013!a\u0001\u0003\u000bA\u0011\"!\u0007'!\u0003\u0005\r!!\b\t\u0013\u00055b\u0005%AA\u0002\u0005E\u0012aD1qa2LH\u0005Z3gCVdG\u000fJ\u001b\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIY\nq\"\u00199qYf$C-\u001a4bk2$HeN\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011)D!\u0010\u0011\tqr'q\u0007\t\u000ey\te2+W7|\u0003\u000b\ti\"!\r\n\u0007\tmRH\u0001\u0004UkBdWm\u000e\u0005\n\u0005\u007fQ\u0013\u0011!a\u0001\u0003\u0003\n1\u0001\u001f\u00131\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%k\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIY\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012:\u0014\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B&!\u0011\tyK!\u0014\n\t\t=\u0013\u0011\u0017\u0002\u0007\u001f\nTWm\u0019;"
)
public class ReceiverTrackingInfo implements Product, Serializable {
   private final int receiverId;
   private final Enumeration.Value state;
   private final Option scheduledLocations;
   private final Option runningExecutor;
   private final Option name;
   private final Option endpoint;
   private final Option errorInfo;

   public static Option $lessinit$greater$default$7() {
      return ReceiverTrackingInfo$.MODULE$.$lessinit$greater$default$7();
   }

   public static Option $lessinit$greater$default$6() {
      return ReceiverTrackingInfo$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option $lessinit$greater$default$5() {
      return ReceiverTrackingInfo$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option unapply(final ReceiverTrackingInfo x$0) {
      return ReceiverTrackingInfo$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$7() {
      return ReceiverTrackingInfo$.MODULE$.apply$default$7();
   }

   public static Option apply$default$6() {
      return ReceiverTrackingInfo$.MODULE$.apply$default$6();
   }

   public static Option apply$default$5() {
      return ReceiverTrackingInfo$.MODULE$.apply$default$5();
   }

   public static ReceiverTrackingInfo apply(final int receiverId, final Enumeration.Value state, final Option scheduledLocations, final Option runningExecutor, final Option name, final Option endpoint, final Option errorInfo) {
      return ReceiverTrackingInfo$.MODULE$.apply(receiverId, state, scheduledLocations, runningExecutor, name, endpoint, errorInfo);
   }

   public static Function1 tupled() {
      return ReceiverTrackingInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ReceiverTrackingInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int receiverId() {
      return this.receiverId;
   }

   public Enumeration.Value state() {
      return this.state;
   }

   public Option scheduledLocations() {
      return this.scheduledLocations;
   }

   public Option runningExecutor() {
      return this.runningExecutor;
   }

   public Option name() {
      return this.name;
   }

   public Option endpoint() {
      return this.endpoint;
   }

   public Option errorInfo() {
      return this.errorInfo;
   }

   public ReceiverInfo toReceiverInfo() {
      boolean var2;
      ReceiverInfo var10000;
      int var10002;
      String var10003;
      label17: {
         label16: {
            var10000 = new ReceiverInfo;
            var10002 = this.receiverId();
            var10003 = (String)this.name().getOrElse(() -> "");
            Enumeration.Value var10004 = this.state();
            Enumeration.Value var1 = ReceiverState$.MODULE$.ACTIVE();
            if (var10004 == null) {
               if (var1 == null) {
                  break label16;
               }
            } else if (var10004.equals(var1)) {
               break label16;
            }

            var2 = false;
            break label17;
         }

         var2 = true;
      }

      var10000.<init>(var10002, var10003, var2, (String)this.runningExecutor().map((x$1) -> x$1.host()).getOrElse(() -> ""), (String)this.runningExecutor().map((x$2) -> x$2.executorId()).getOrElse(() -> ""), (String)this.errorInfo().map((x$3) -> x$3.lastErrorMessage()).getOrElse(() -> ""), (String)this.errorInfo().map((x$4) -> x$4.lastError()).getOrElse(() -> ""), BoxesRunTime.unboxToLong(this.errorInfo().map((x$5) -> BoxesRunTime.boxToLong($anonfun$toReceiverInfo$10(x$5))).getOrElse((JFunction0.mcJ.sp)() -> -1L)));
      return var10000;
   }

   public ReceiverTrackingInfo copy(final int receiverId, final Enumeration.Value state, final Option scheduledLocations, final Option runningExecutor, final Option name, final Option endpoint, final Option errorInfo) {
      return new ReceiverTrackingInfo(receiverId, state, scheduledLocations, runningExecutor, name, endpoint, errorInfo);
   }

   public int copy$default$1() {
      return this.receiverId();
   }

   public Enumeration.Value copy$default$2() {
      return this.state();
   }

   public Option copy$default$3() {
      return this.scheduledLocations();
   }

   public Option copy$default$4() {
      return this.runningExecutor();
   }

   public Option copy$default$5() {
      return this.name();
   }

   public Option copy$default$6() {
      return this.endpoint();
   }

   public Option copy$default$7() {
      return this.errorInfo();
   }

   public String productPrefix() {
      return "ReceiverTrackingInfo";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.receiverId());
         }
         case 1 -> {
            return this.state();
         }
         case 2 -> {
            return this.scheduledLocations();
         }
         case 3 -> {
            return this.runningExecutor();
         }
         case 4 -> {
            return this.name();
         }
         case 5 -> {
            return this.endpoint();
         }
         case 6 -> {
            return this.errorInfo();
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
      return x$1 instanceof ReceiverTrackingInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "receiverId";
         }
         case 1 -> {
            return "state";
         }
         case 2 -> {
            return "scheduledLocations";
         }
         case 3 -> {
            return "runningExecutor";
         }
         case 4 -> {
            return "name";
         }
         case 5 -> {
            return "endpoint";
         }
         case 6 -> {
            return "errorInfo";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.receiverId());
      var1 = Statics.mix(var1, Statics.anyHash(this.state()));
      var1 = Statics.mix(var1, Statics.anyHash(this.scheduledLocations()));
      var1 = Statics.mix(var1, Statics.anyHash(this.runningExecutor()));
      var1 = Statics.mix(var1, Statics.anyHash(this.name()));
      var1 = Statics.mix(var1, Statics.anyHash(this.endpoint()));
      var1 = Statics.mix(var1, Statics.anyHash(this.errorInfo()));
      return Statics.finalizeHash(var1, 7);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var16;
      if (this != x$1) {
         label91: {
            if (x$1 instanceof ReceiverTrackingInfo) {
               ReceiverTrackingInfo var4 = (ReceiverTrackingInfo)x$1;
               if (this.receiverId() == var4.receiverId()) {
                  label84: {
                     Enumeration.Value var10000 = this.state();
                     Enumeration.Value var5 = var4.state();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label84;
                     }

                     Option var11 = this.scheduledLocations();
                     Option var6 = var4.scheduledLocations();
                     if (var11 == null) {
                        if (var6 != null) {
                           break label84;
                        }
                     } else if (!var11.equals(var6)) {
                        break label84;
                     }

                     var11 = this.runningExecutor();
                     Option var7 = var4.runningExecutor();
                     if (var11 == null) {
                        if (var7 != null) {
                           break label84;
                        }
                     } else if (!var11.equals(var7)) {
                        break label84;
                     }

                     var11 = this.name();
                     Option var8 = var4.name();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label84;
                        }
                     } else if (!var11.equals(var8)) {
                        break label84;
                     }

                     var11 = this.endpoint();
                     Option var9 = var4.endpoint();
                     if (var11 == null) {
                        if (var9 != null) {
                           break label84;
                        }
                     } else if (!var11.equals(var9)) {
                        break label84;
                     }

                     var11 = this.errorInfo();
                     Option var10 = var4.errorInfo();
                     if (var11 == null) {
                        if (var10 != null) {
                           break label84;
                        }
                     } else if (!var11.equals(var10)) {
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
   public static final long $anonfun$toReceiverInfo$10(final ReceiverErrorInfo x$5) {
      return x$5.lastErrorTime();
   }

   public ReceiverTrackingInfo(final int receiverId, final Enumeration.Value state, final Option scheduledLocations, final Option runningExecutor, final Option name, final Option endpoint, final Option errorInfo) {
      this.receiverId = receiverId;
      this.state = state;
      this.scheduledLocations = scheduledLocations;
      this.runningExecutor = runningExecutor;
      this.name = name;
      this.endpoint = endpoint;
      this.errorInfo = errorInfo;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
