package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.receiver.Receiver;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005e!\u0002\f\u0018\u0001f\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011!\u0003!\u0011#Q\u0001\nyBQ\u0001\u0015\u0001\u0005\u0002ECq\u0001\u0017\u0001\u0002\u0002\u0013\u0005\u0011\fC\u0004\\\u0001E\u0005I\u0011\u0001/\t\u000f-\u0004\u0011\u0011!C!Y\"9Q\u000fAA\u0001\n\u00031\bb\u0002>\u0001\u0003\u0003%\ta\u001f\u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\ti\u0001AA\u0001\n\u0003\ty\u0001C\u0005\u0002\u001a\u0001\t\t\u0011\"\u0011\u0002\u001c!I\u0011q\u0004\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0005\u0005\n\u0003G\u0001\u0011\u0011!C!\u0003KA\u0011\"a\n\u0001\u0003\u0003%\t%!\u000b\b\u0015\u00055r#!A\t\u0002e\tyCB\u0005\u0017/\u0005\u0005\t\u0012A\r\u00022!1\u0001\u000b\u0005C\u0001\u0003#B\u0011\"a\t\u0011\u0003\u0003%)%!\n\t\u0013\u0005M\u0003#!A\u0005\u0002\u0006U\u0003\"CA1!\u0005\u0005I\u0011QA2\u0011%\t9\bEA\u0001\n\u0013\tIHA\bSKN$\u0018M\u001d;SK\u000e,\u0017N^3s\u0015\tA\u0012$A\u0005tG\",G-\u001e7fe*\u0011!dG\u0001\ngR\u0014X-Y7j]\u001eT!\u0001H\u000f\u0002\u000bM\u0004\u0018M]6\u000b\u0005yy\u0012AB1qC\u000eDWMC\u0001!\u0003\ry'oZ\n\u0006\u0001\tBCf\f\t\u0003G\u0019j\u0011\u0001\n\u0006\u0002K\u0005)1oY1mC&\u0011q\u0005\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005%RS\"A\f\n\u0005-:\"a\u0007*fG\u0016Lg/\u001a:Ue\u0006\u001c7.\u001a:M_\u000e\fG.T3tg\u0006<W\r\u0005\u0002$[%\u0011a\u0006\n\u0002\b!J|G-^2u!\t\u0001\u0014H\u0004\u00022o9\u0011!GN\u0007\u0002g)\u0011A'N\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tQ%\u0003\u00029I\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001e<\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tAD%\u0001\u0005sK\u000e,\u0017N^3s+\u0005q\u0004GA G!\r\u0001%\tR\u0007\u0002\u0003*\u0011A(G\u0005\u0003\u0007\u0006\u0013\u0001BU3dK&4XM\u001d\t\u0003\u000b\u001ac\u0001\u0001B\u0005H\u0005\u0005\u0005\t\u0011!B\u0001\u0013\n\u0019q\fJ\u0019\u0002\u0013I,7-Z5wKJ\u0004\u0013C\u0001&N!\t\u00193*\u0003\u0002MI\t9aj\u001c;iS:<\u0007CA\u0012O\u0013\tyEEA\u0002B]f\fa\u0001P5oSRtDC\u0001*T!\tI\u0003\u0001C\u0003=\u0007\u0001\u0007A\u000b\r\u0002V/B\u0019\u0001I\u0011,\u0011\u0005\u0015;F!C$T\u0003\u0003\u0005\tQ!\u0001J\u0003\u0011\u0019w\u000e]=\u0015\u0005IS\u0006b\u0002\u001f\u0005!\u0003\u0005\r\u0001V\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005i\u0006G\u00010bU\ty&\rE\u0002A\u0005\u0002\u0004\"!R1\u0005\u0013\u001d+\u0011\u0011!A\u0001\u0006\u0003I5&A2\u0011\u0005\u0011LW\"A3\u000b\u0005\u0019<\u0017!C;oG\",7m[3e\u0015\tAG%\u0001\u0006b]:|G/\u0019;j_:L!A[3\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002[B\u0011an]\u0007\u0002_*\u0011\u0001/]\u0001\u0005Y\u0006twMC\u0001s\u0003\u0011Q\u0017M^1\n\u0005Q|'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001x!\t\u0019\u00030\u0003\u0002zI\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011Q\n \u0005\b{\"\t\t\u00111\u0001x\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0001\t\u0006\u0003\u0007\tI!T\u0007\u0003\u0003\u000bQ1!a\u0002%\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u0017\t)A\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\t\u0003/\u00012aIA\n\u0013\r\t)\u0002\n\u0002\b\u0005>|G.Z1o\u0011\u001di(\"!AA\u00025\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019Q.!\b\t\u000fu\\\u0011\u0011!a\u0001o\u0006A\u0001.Y:i\u0007>$W\rF\u0001x\u0003!!xn\u0015;sS:<G#A7\u0002\r\u0015\fX/\u00197t)\u0011\t\t\"a\u000b\t\u000fut\u0011\u0011!a\u0001\u001b\u0006y!+Z:uCJ$(+Z2fSZ,'\u000f\u0005\u0002*!M)\u0001#a\r\u0002HA9\u0011QGA\u001e\u0003\u007f\u0011VBAA\u001c\u0015\r\tI\u0004J\u0001\beVtG/[7f\u0013\u0011\ti$a\u000e\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\r\u0003\u0002B\u0005\u0015\u0003\u0003\u0002!C\u0003\u0007\u00022!RA#\t%9\u0005#!A\u0001\u0002\u000b\u0005\u0011\n\u0005\u0003\u0002J\u0005=SBAA&\u0015\r\ti%]\u0001\u0003S>L1AOA&)\t\ty#A\u0003baBd\u0017\u0010F\u0002S\u0003/Ba\u0001P\nA\u0002\u0005e\u0003\u0007BA.\u0003?\u0002B\u0001\u0011\"\u0002^A\u0019Q)a\u0018\u0005\u0015\u001d\u000b9&!A\u0001\u0002\u000b\u0005\u0011*A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u0015\u00141\u000f\t\u0006G\u0005\u001d\u00141N\u0005\u0004\u0003S\"#AB(qi&|g\u000e\r\u0003\u0002n\u0005E\u0004\u0003\u0002!C\u0003_\u00022!RA9\t%9E#!A\u0001\u0002\u000b\u0005\u0011\n\u0003\u0005\u0002vQ\t\t\u00111\u0001S\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003w\u00022A\\A?\u0013\r\tyh\u001c\u0002\u0007\u001f\nTWm\u0019;"
)
public class RestartReceiver implements ReceiverTrackerLocalMessage, Product, Serializable {
   private final Receiver receiver;

   public static Option unapply(final RestartReceiver x$0) {
      return RestartReceiver$.MODULE$.unapply(x$0);
   }

   public static RestartReceiver apply(final Receiver receiver) {
      return RestartReceiver$.MODULE$.apply(receiver);
   }

   public static Function1 andThen(final Function1 g) {
      return RestartReceiver$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return RestartReceiver$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Receiver receiver() {
      return this.receiver;
   }

   public RestartReceiver copy(final Receiver receiver) {
      return new RestartReceiver(receiver);
   }

   public Receiver copy$default$1() {
      return this.receiver();
   }

   public String productPrefix() {
      return "RestartReceiver";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.receiver();
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
      return x$1 instanceof RestartReceiver;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "receiver";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof RestartReceiver) {
               label40: {
                  RestartReceiver var4 = (RestartReceiver)x$1;
                  Receiver var10000 = this.receiver();
                  Receiver var5 = var4.receiver();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
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

   public RestartReceiver(final Receiver receiver) {
      this.receiver = receiver;
      Product.$init$(this);
   }
}
