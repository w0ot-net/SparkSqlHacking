package org.apache.spark.status;

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
   bytes = "\u0006\u0005\u0005eb!\u0002\f\u0018\u0001fy\u0002\u0002\u0003\u001c\u0001\u0005+\u0007I\u0011A\u001c\t\u0011m\u0002!\u0011#Q\u0001\naBQ\u0001\u0010\u0001\u0005\u0002uBq!\u0011\u0001\u0002\u0002\u0013\u0005!\tC\u0004E\u0001E\u0005I\u0011A#\t\u000fA\u0003\u0011\u0011!C!#\"9!\fAA\u0001\n\u0003Y\u0006bB0\u0001\u0003\u0003%\t\u0001\u0019\u0005\bM\u0002\t\t\u0011\"\u0011h\u0011\u001dq\u0007!!A\u0005\u0002=Dq\u0001\u001e\u0001\u0002\u0002\u0013\u0005S\u000fC\u0004x\u0001\u0005\u0005I\u0011\t=\t\u000fe\u0004\u0011\u0011!C!u\"91\u0010AA\u0001\n\u0003bx\u0001\u0003@\u0018\u0003\u0003E\t!G@\u0007\u0013Y9\u0012\u0011!E\u00013\u0005\u0005\u0001B\u0002\u001f\u0011\t\u0003\tI\u0002C\u0004z!\u0005\u0005IQ\t>\t\u0013\u0005m\u0001#!A\u0005\u0002\u0006u\u0001\"CA\u0011!\u0005\u0005I\u0011QA\u0012\u0011%\ty\u0003EA\u0001\n\u0013\t\tD\u0001\fBaB\u001cF/\u0019;vgN#xN]3NKR\fG-\u0019;b\u0015\tA\u0012$\u0001\u0004ti\u0006$Xo\u001d\u0006\u00035m\tQa\u001d9be.T!\u0001H\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0012aA8sON!\u0001\u0001\t\u0014*!\t\tC%D\u0001#\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0005\u0019\te.\u001f*fMB\u0011\u0011eJ\u0005\u0003Q\t\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002+g9\u00111&\r\b\u0003YAj\u0011!\f\u0006\u0003]=\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002G%\u0011!GI\u0001\ba\u0006\u001c7.Y4f\u0013\t!TG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00023E\u00059a/\u001a:tS>tW#\u0001\u001d\u0011\u0005\u0005J\u0014B\u0001\u001e#\u0005\u0011auN\\4\u0002\u0011Y,'o]5p]\u0002\na\u0001P5oSRtDC\u0001 A!\ty\u0004!D\u0001\u0018\u0011\u001514\u00011\u00019\u0003\u0011\u0019w\u000e]=\u0015\u0005y\u001a\u0005b\u0002\u001c\u0005!\u0003\u0005\r\u0001O\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u00051%F\u0001\u001dHW\u0005A\u0005CA%O\u001b\u0005Q%BA&M\u0003%)hn\u00195fG.,GM\u0003\u0002NE\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005=S%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u0015\t\u0003'bk\u0011\u0001\u0016\u0006\u0003+Z\u000bA\u0001\\1oO*\tq+\u0001\u0003kCZ\f\u0017BA-U\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tA\f\u0005\u0002\";&\u0011aL\t\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003C\u0012\u0004\"!\t2\n\u0005\r\u0014#aA!os\"9Q\rCA\u0001\u0002\u0004a\u0016a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001i!\rIG.Y\u0007\u0002U*\u00111NI\u0001\u000bG>dG.Z2uS>t\u0017BA7k\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005A\u001c\bCA\u0011r\u0013\t\u0011(EA\u0004C_>dW-\u00198\t\u000f\u0015T\u0011\u0011!a\u0001C\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\t\u0011f\u000fC\u0004f\u0017\u0005\u0005\t\u0019\u0001/\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001X\u0001\ti>\u001cFO]5oOR\t!+\u0001\u0004fcV\fGn\u001d\u000b\u0003avDq!\u001a\b\u0002\u0002\u0003\u0007\u0011-\u0001\fBaB\u001cF/\u0019;vgN#xN]3NKR\fG-\u0019;b!\ty\u0004cE\u0003\u0011\u0003\u0007\ty\u0001\u0005\u0004\u0002\u0006\u0005-\u0001HP\u0007\u0003\u0003\u000fQ1!!\u0003#\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u0004\u0002\b\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005E\u0011qC\u0007\u0003\u0003'Q1!!\u0006W\u0003\tIw.C\u00025\u0003'!\u0012a`\u0001\u0006CB\u0004H.\u001f\u000b\u0004}\u0005}\u0001\"\u0002\u001c\u0014\u0001\u0004A\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003K\tY\u0003\u0005\u0003\"\u0003OA\u0014bAA\u0015E\t1q\n\u001d;j_:D\u0001\"!\f\u0015\u0003\u0003\u0005\rAP\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u001a!\r\u0019\u0016QG\u0005\u0004\u0003o!&AB(cU\u0016\u001cG\u000f"
)
public class AppStatusStoreMetadata implements Product, Serializable {
   private final long version;

   public static Option unapply(final AppStatusStoreMetadata x$0) {
      return AppStatusStoreMetadata$.MODULE$.unapply(x$0);
   }

   public static AppStatusStoreMetadata apply(final long version) {
      return AppStatusStoreMetadata$.MODULE$.apply(version);
   }

   public static Function1 andThen(final Function1 g) {
      return AppStatusStoreMetadata$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return AppStatusStoreMetadata$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long version() {
      return this.version;
   }

   public AppStatusStoreMetadata copy(final long version) {
      return new AppStatusStoreMetadata(version);
   }

   public long copy$default$1() {
      return this.version();
   }

   public String productPrefix() {
      return "AppStatusStoreMetadata";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.version());
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
      return x$1 instanceof AppStatusStoreMetadata;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "version";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.version()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof AppStatusStoreMetadata) {
               AppStatusStoreMetadata var4 = (AppStatusStoreMetadata)x$1;
               if (this.version() == var4.version() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public AppStatusStoreMetadata(final long version) {
      this.version = version;
      Product.$init$(this);
   }
}
