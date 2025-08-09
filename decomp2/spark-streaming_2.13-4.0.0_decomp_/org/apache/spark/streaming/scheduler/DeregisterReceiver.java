package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed!\u0002\u000f\u001e\u0001~9\u0003\u0002\u0003\"\u0001\u0005+\u0007I\u0011A\"\t\u0011\u001d\u0003!\u0011#Q\u0001\n\u0011C\u0001\u0002\u0013\u0001\u0003\u0016\u0004%\t!\u0013\u0005\t%\u0002\u0011\t\u0012)A\u0005\u0015\"A1\u000b\u0001BK\u0002\u0013\u0005\u0011\n\u0003\u0005U\u0001\tE\t\u0015!\u0003K\u0011\u0015)\u0006\u0001\"\u0001W\u0011\u001dY\u0006!!A\u0005\u0002qCq\u0001\u0019\u0001\u0012\u0002\u0013\u0005\u0011\rC\u0004m\u0001E\u0005I\u0011A7\t\u000f=\u0004\u0011\u0013!C\u0001[\"9\u0001\u000fAA\u0001\n\u0003\n\bbB=\u0001\u0003\u0003%\ta\u0011\u0005\bu\u0002\t\t\u0011\"\u0001|\u0011%\t\u0019\u0001AA\u0001\n\u0003\n)\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0001\u0002\u0016!I\u0011q\u0004\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0005\u0005\n\u0003K\u0001\u0011\u0011!C!\u0003OA\u0011\"!\u000b\u0001\u0003\u0003%\t%a\u000b\t\u0013\u00055\u0002!!A\u0005B\u0005=rACA\u001a;\u0005\u0005\t\u0012A\u0010\u00026\u0019IA$HA\u0001\u0012\u0003y\u0012q\u0007\u0005\u0007+Z!\t!a\u0014\t\u0013\u0005%b#!A\u0005F\u0005-\u0002\"CA)-\u0005\u0005I\u0011QA*\u0011%\tYFFA\u0001\n\u0003\u000bi\u0006C\u0005\u0002pY\t\t\u0011\"\u0003\u0002r\t\u0011B)\u001a:fO&\u001cH/\u001a:SK\u000e,\u0017N^3s\u0015\tqr$A\u0005tG\",G-\u001e7fe*\u0011\u0001%I\u0001\ngR\u0014X-Y7j]\u001eT!AI\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0011*\u0013AB1qC\u000eDWMC\u0001'\u0003\ry'oZ\n\u0006\u0001!r#'\u000e\t\u0003S1j\u0011A\u000b\u0006\u0002W\u0005)1oY1mC&\u0011QF\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005=\u0002T\"A\u000f\n\u0005Ej\"A\u0006*fG\u0016Lg/\u001a:Ue\u0006\u001c7.\u001a:NKN\u001c\u0018mZ3\u0011\u0005%\u001a\u0014B\u0001\u001b+\u0005\u001d\u0001&o\u001c3vGR\u0004\"AN \u000f\u0005]jdB\u0001\u001d=\u001b\u0005I$B\u0001\u001e<\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0016\n\u0005yR\u0013a\u00029bG.\fw-Z\u0005\u0003\u0001\u0006\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!A\u0010\u0016\u0002\u0011M$(/Z1n\u0013\u0012,\u0012\u0001\u0012\t\u0003S\u0015K!A\u0012\u0016\u0003\u0007%sG/A\u0005tiJ,\u0017-\\%eA\u0005\u0019Qn]4\u0016\u0003)\u0003\"aS(\u000f\u00051k\u0005C\u0001\u001d+\u0013\tq%&\u0001\u0004Qe\u0016$WMZ\u0005\u0003!F\u0013aa\u0015;sS:<'B\u0001(+\u0003\u0011i7o\u001a\u0011\u0002\u000b\u0015\u0014(o\u001c:\u0002\r\u0015\u0014(o\u001c:!\u0003\u0019a\u0014N\\5u}Q!q\u000bW-[!\ty\u0003\u0001C\u0003C\u000f\u0001\u0007A\tC\u0003I\u000f\u0001\u0007!\nC\u0003T\u000f\u0001\u0007!*\u0001\u0003d_BLH\u0003B,^=~CqA\u0011\u0005\u0011\u0002\u0003\u0007A\tC\u0004I\u0011A\u0005\t\u0019\u0001&\t\u000fMC\u0001\u0013!a\u0001\u0015\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00012+\u0005\u0011\u001b7&\u00013\u0011\u0005\u0015TW\"\u00014\u000b\u0005\u001dD\u0017!C;oG\",7m[3e\u0015\tI'&\u0001\u0006b]:|G/\u0019;j_:L!a\u001b4\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u00039T#AS2\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u001d\t\u0003gbl\u0011\u0001\u001e\u0006\u0003kZ\fA\u0001\\1oO*\tq/\u0001\u0003kCZ\f\u0017B\u0001)u\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001`@\u0011\u0005%j\u0018B\u0001@+\u0005\r\te.\u001f\u0005\t\u0003\u0003q\u0011\u0011!a\u0001\t\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0002\u0011\u000b\u0005%\u0011q\u0002?\u000e\u0005\u0005-!bAA\u0007U\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005E\u00111\u0002\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0018\u0005u\u0001cA\u0015\u0002\u001a%\u0019\u00111\u0004\u0016\u0003\u000f\t{w\u000e\\3b]\"A\u0011\u0011\u0001\t\u0002\u0002\u0003\u0007A0\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u0001:\u0002$!A\u0011\u0011A\t\u0002\u0002\u0003\u0007A)\u0001\u0005iCND7i\u001c3f)\u0005!\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003I\fa!Z9vC2\u001cH\u0003BA\f\u0003cA\u0001\"!\u0001\u0015\u0003\u0003\u0005\r\u0001`\u0001\u0013\t\u0016\u0014XmZ5ti\u0016\u0014(+Z2fSZ,'\u000f\u0005\u00020-M)a#!\u000f\u0002FAA\u00111HA!\t*Su+\u0004\u0002\u0002>)\u0019\u0011q\b\u0016\u0002\u000fI,h\u000e^5nK&!\u00111IA\u001f\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u0003\u000f\ni%\u0004\u0002\u0002J)\u0019\u00111\n<\u0002\u0005%|\u0017b\u0001!\u0002JQ\u0011\u0011QG\u0001\u0006CB\u0004H.\u001f\u000b\b/\u0006U\u0013qKA-\u0011\u0015\u0011\u0015\u00041\u0001E\u0011\u0015A\u0015\u00041\u0001K\u0011\u0015\u0019\u0016\u00041\u0001K\u0003\u001d)h.\u00199qYf$B!a\u0018\u0002lA)\u0011&!\u0019\u0002f%\u0019\u00111\r\u0016\u0003\r=\u0003H/[8o!\u0019I\u0013q\r#K\u0015&\u0019\u0011\u0011\u000e\u0016\u0003\rQ+\b\u000f\\34\u0011!\tiGGA\u0001\u0002\u00049\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u000f\t\u0004g\u0006U\u0014bAA<i\n1qJ\u00196fGR\u0004"
)
public class DeregisterReceiver implements ReceiverTrackerMessage, Product, Serializable {
   private final int streamId;
   private final String msg;
   private final String error;

   public static Option unapply(final DeregisterReceiver x$0) {
      return DeregisterReceiver$.MODULE$.unapply(x$0);
   }

   public static DeregisterReceiver apply(final int streamId, final String msg, final String error) {
      return DeregisterReceiver$.MODULE$.apply(streamId, msg, error);
   }

   public static Function1 tupled() {
      return DeregisterReceiver$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return DeregisterReceiver$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int streamId() {
      return this.streamId;
   }

   public String msg() {
      return this.msg;
   }

   public String error() {
      return this.error;
   }

   public DeregisterReceiver copy(final int streamId, final String msg, final String error) {
      return new DeregisterReceiver(streamId, msg, error);
   }

   public int copy$default$1() {
      return this.streamId();
   }

   public String copy$default$2() {
      return this.msg();
   }

   public String copy$default$3() {
      return this.error();
   }

   public String productPrefix() {
      return "DeregisterReceiver";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.streamId());
         }
         case 1 -> {
            return this.msg();
         }
         case 2 -> {
            return this.error();
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
      return x$1 instanceof DeregisterReceiver;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "streamId";
         }
         case 1 -> {
            return "msg";
         }
         case 2 -> {
            return "error";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.msg()));
      var1 = Statics.mix(var1, Statics.anyHash(this.error()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof DeregisterReceiver) {
               DeregisterReceiver var4 = (DeregisterReceiver)x$1;
               if (this.streamId() == var4.streamId()) {
                  label52: {
                     String var10000 = this.msg();
                     String var5 = var4.msg();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     var10000 = this.error();
                     String var6 = var4.error();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
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

   public DeregisterReceiver(final int streamId, final String msg, final String error) {
      this.streamId = streamId;
      this.msg = msg;
      this.error = error;
      Product.$init$(this);
   }
}
