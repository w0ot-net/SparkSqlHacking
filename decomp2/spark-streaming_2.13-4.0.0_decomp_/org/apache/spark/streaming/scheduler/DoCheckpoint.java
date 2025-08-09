package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd!B\r\u001b\u0001j!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011\u0015\u0003!\u0011#Q\u0001\n\u0005C\u0001B\u0012\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0011\")A\n\u0001C\u0001\u001b\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006bB+\u0001#\u0003%\tA\u0016\u0005\bC\u0002\t\n\u0011\"\u0001c\u0011\u001d!\u0007!!A\u0005B\u0015DqA\u001c\u0001\u0002\u0002\u0013\u0005q\u000eC\u0004t\u0001\u0005\u0005I\u0011\u0001;\t\u000fi\u0004\u0011\u0011!C!w\"I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0011q\u0001\u0005\n\u0003\u0017\u0001\u0011\u0011!C!\u0003\u001bA\u0011\"!\u0005\u0001\u0003\u0003%\t%a\u0005\t\u0013\u0005U\u0001!!A\u0005B\u0005]\u0001\"CA\r\u0001\u0005\u0005I\u0011IA\u000e\u000f)\tyBGA\u0001\u0012\u0003Q\u0012\u0011\u0005\u0004\n3i\t\t\u0011#\u0001\u001b\u0003GAa\u0001T\n\u0005\u0002\u0005m\u0002\"CA\u000b'\u0005\u0005IQIA\f\u0011%\tidEA\u0001\n\u0003\u000by\u0004C\u0005\u0002FM\t\t\u0011\"!\u0002H!I\u0011\u0011L\n\u0002\u0002\u0013%\u00111\f\u0002\r\t>\u001c\u0005.Z2la>Lg\u000e\u001e\u0006\u00037q\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005uq\u0012!C:ue\u0016\fW.\u001b8h\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7#\u0002\u0001&W=\u0012\u0004C\u0001\u0014*\u001b\u00059#\"\u0001\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005):#AB!osJ+g\r\u0005\u0002-[5\t!$\u0003\u0002/5\t\t\"j\u001c2HK:,'/\u0019;pe\u00163XM\u001c;\u0011\u0005\u0019\u0002\u0014BA\u0019(\u0005\u001d\u0001&o\u001c3vGR\u0004\"a\r\u001f\u000f\u0005QRdBA\u001b:\u001b\u00051$BA\u001c9\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u0015\n\u0005m:\u0013a\u00029bG.\fw-Z\u0005\u0003{y\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aO\u0014\u0002\tQLW.Z\u000b\u0002\u0003B\u0011!iQ\u0007\u00029%\u0011A\t\b\u0002\u0005)&lW-A\u0003uS6,\u0007%\u0001\rdY\u0016\f'o\u00115fG.\u0004x.\u001b8u\t\u0006$\u0018\rT1uKJ,\u0012\u0001\u0013\t\u0003M%K!AS\u0014\u0003\u000f\t{w\u000e\\3b]\u0006I2\r\\3be\u000eCWmY6q_&tG\u000fR1uC2\u000bG/\u001a:!\u0003\u0019a\u0014N\\5u}Q\u0019aj\u0014)\u0011\u00051\u0002\u0001\"B \u0006\u0001\u0004\t\u0005\"\u0002$\u0006\u0001\u0004A\u0015\u0001B2paf$2AT*U\u0011\u001dyd\u0001%AA\u0002\u0005CqA\u0012\u0004\u0011\u0002\u0003\u0007\u0001*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003]S#!\u0011-,\u0003e\u0003\"AW0\u000e\u0003mS!\u0001X/\u0002\u0013Ut7\r[3dW\u0016$'B\u00010(\u0003)\tgN\\8uCRLwN\\\u0005\u0003An\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012a\u0019\u0016\u0003\u0011b\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00014\u0011\u0005\u001ddW\"\u00015\u000b\u0005%T\u0017\u0001\u00027b]\u001eT\u0011a[\u0001\u0005U\u00064\u0018-\u0003\u0002nQ\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012\u0001\u001d\t\u0003MEL!A]\u0014\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005UD\bC\u0001\u0014w\u0013\t9xEA\u0002B]fDq!_\u0006\u0002\u0002\u0003\u0007\u0001/A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002yB!Q0!\u0001v\u001b\u0005q(BA@(\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0004\u0003\u0007q(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$2\u0001SA\u0005\u0011\u001dIX\"!AA\u0002U\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019a-a\u0004\t\u000fet\u0011\u0011!a\u0001a\u0006A\u0001.Y:i\u0007>$W\rF\u0001q\u0003!!xn\u0015;sS:<G#\u00014\u0002\r\u0015\fX/\u00197t)\rA\u0015Q\u0004\u0005\bsF\t\t\u00111\u0001v\u00031!un\u00115fG.\u0004x.\u001b8u!\ta3cE\u0003\u0014\u0003K\t\t\u0004E\u0004\u0002(\u00055\u0012\t\u0013(\u000e\u0005\u0005%\"bAA\u0016O\u00059!/\u001e8uS6,\u0017\u0002BA\u0018\u0003S\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\t\u0019$!\u000f\u000e\u0005\u0005U\"bAA\u001cU\u0006\u0011\u0011n\\\u0005\u0004{\u0005UBCAA\u0011\u0003\u0015\t\u0007\u000f\u001d7z)\u0015q\u0015\u0011IA\"\u0011\u0015yd\u00031\u0001B\u0011\u00151e\u00031\u0001I\u0003\u001d)h.\u00199qYf$B!!\u0013\u0002VA)a%a\u0013\u0002P%\u0019\u0011QJ\u0014\u0003\r=\u0003H/[8o!\u00151\u0013\u0011K!I\u0013\r\t\u0019f\n\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005]s#!AA\u00029\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\u0006E\u0002h\u0003?J1!!\u0019i\u0005\u0019y%M[3di\u0002"
)
public class DoCheckpoint implements JobGeneratorEvent, Product, Serializable {
   private final Time time;
   private final boolean clearCheckpointDataLater;

   public static Option unapply(final DoCheckpoint x$0) {
      return DoCheckpoint$.MODULE$.unapply(x$0);
   }

   public static DoCheckpoint apply(final Time time, final boolean clearCheckpointDataLater) {
      return DoCheckpoint$.MODULE$.apply(time, clearCheckpointDataLater);
   }

   public static Function1 tupled() {
      return DoCheckpoint$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return DoCheckpoint$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Time time() {
      return this.time;
   }

   public boolean clearCheckpointDataLater() {
      return this.clearCheckpointDataLater;
   }

   public DoCheckpoint copy(final Time time, final boolean clearCheckpointDataLater) {
      return new DoCheckpoint(time, clearCheckpointDataLater);
   }

   public Time copy$default$1() {
      return this.time();
   }

   public boolean copy$default$2() {
      return this.clearCheckpointDataLater();
   }

   public String productPrefix() {
      return "DoCheckpoint";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.time();
         }
         case 1 -> {
            return BoxesRunTime.boxToBoolean(this.clearCheckpointDataLater());
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
      return x$1 instanceof DoCheckpoint;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "clearCheckpointDataLater";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.time()));
      var1 = Statics.mix(var1, this.clearCheckpointDataLater() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof DoCheckpoint) {
               DoCheckpoint var4 = (DoCheckpoint)x$1;
               if (this.clearCheckpointDataLater() == var4.clearCheckpointDataLater()) {
                  label44: {
                     Time var10000 = this.time();
                     Time var5 = var4.time();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
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

   public DoCheckpoint(final Time time, final boolean clearCheckpointDataLater) {
      this.time = time;
      this.clearCheckpointDataLater = clearCheckpointDataLater;
      Product.$init$(this);
   }
}
