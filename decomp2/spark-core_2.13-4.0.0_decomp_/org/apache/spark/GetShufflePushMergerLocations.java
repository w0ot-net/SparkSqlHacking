package org.apache.spark;

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
   bytes = "\u0006\u0005\u0005Mb!\u0002\f\u0018\u0001^i\u0002\u0002\u0003\u001d\u0001\u0005+\u0007I\u0011A\u001d\t\u0011u\u0002!\u0011#Q\u0001\niBQA\u0010\u0001\u0005\u0002}BqA\u0011\u0001\u0002\u0002\u0013\u00051\tC\u0004F\u0001E\u0005I\u0011\u0001$\t\u000fE\u0003\u0011\u0011!C!%\"91\fAA\u0001\n\u0003I\u0004b\u0002/\u0001\u0003\u0003%\t!\u0018\u0005\bG\u0002\t\t\u0011\"\u0011e\u0011\u001dY\u0007!!A\u0005\u00021Dq!\u001d\u0001\u0002\u0002\u0013\u0005#\u000fC\u0004u\u0001\u0005\u0005I\u0011I;\t\u000fY\u0004\u0011\u0011!C!o\"9\u0001\u0010AA\u0001\n\u0003Jx\u0001C>\u0018\u0003\u0003E\ta\u0006?\u0007\u0011Y9\u0012\u0011!E\u0001/uDaA\u0010\t\u0005\u0002\u0005M\u0001b\u0002<\u0011\u0003\u0003%)e\u001e\u0005\n\u0003+\u0001\u0012\u0011!CA\u0003/A\u0011\"a\u0007\u0011\u0003\u0003%\t)!\b\t\u0013\u0005%\u0002#!A\u0005\n\u0005-\"!H$fiNCWO\u001a4mKB+8\u000f['fe\u001e,'\u000fT8dCRLwN\\:\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e\u001cR\u0001\u0001\u0010%Q-\u0002\"a\b\u0012\u000e\u0003\u0001R\u0011!I\u0001\u0006g\u000e\fG.Y\u0005\u0003G\u0001\u0012a!\u00118z%\u00164\u0007CA\u0013'\u001b\u00059\u0012BA\u0014\u0018\u0005]i\u0015\r](viB,H\u000f\u0016:bG.,'/T3tg\u0006<W\r\u0005\u0002 S%\u0011!\u0006\t\u0002\b!J|G-^2u!\taSG\u0004\u0002.g9\u0011aFM\u0007\u0002_)\u0011\u0001'M\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0011%\u0003\u00025A\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001c8\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t!\u0004%A\u0005tQV4g\r\\3JIV\t!\b\u0005\u0002 w%\u0011A\b\t\u0002\u0004\u0013:$\u0018AC:ik\u001a4G.Z%eA\u00051A(\u001b8jiz\"\"\u0001Q!\u0011\u0005\u0015\u0002\u0001\"\u0002\u001d\u0004\u0001\u0004Q\u0014\u0001B2paf$\"\u0001\u0011#\t\u000fa\"\u0001\u0013!a\u0001u\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A$+\u0005iB5&A%\u0011\u0005){U\"A&\u000b\u00051k\u0015!C;oG\",7m[3e\u0015\tq\u0005%\u0001\u0006b]:|G/\u0019;j_:L!\u0001U&\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002'B\u0011A+W\u0007\u0002+*\u0011akV\u0001\u0005Y\u0006twMC\u0001Y\u0003\u0011Q\u0017M^1\n\u0005i+&AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005y\u000b\u0007CA\u0010`\u0013\t\u0001\u0007EA\u0002B]fDqA\u0019\u0005\u0002\u0002\u0003\u0007!(A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002KB\u0019a-\u001b0\u000e\u0003\u001dT!\u0001\u001b\u0011\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002kO\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\ti\u0007\u000f\u0005\u0002 ]&\u0011q\u000e\t\u0002\b\u0005>|G.Z1o\u0011\u001d\u0011'\"!AA\u0002y\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u00111k\u001d\u0005\bE.\t\t\u00111\u0001;\u0003!A\u0017m\u001d5D_\u0012,G#\u0001\u001e\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012aU\u0001\u0007KF,\u0018\r\\:\u0015\u00055T\bb\u00022\u000f\u0003\u0003\u0005\rAX\u0001\u001e\u000f\u0016$8\u000b[;gM2,\u0007+^:i\u001b\u0016\u0014x-\u001a:M_\u000e\fG/[8ogB\u0011Q\u0005E\n\u0005!y\fI\u0001E\u0003\u0000\u0003\u000bQ\u0004)\u0004\u0002\u0002\u0002)\u0019\u00111\u0001\u0011\u0002\u000fI,h\u000e^5nK&!\u0011qAA\u0001\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003\u0017\t\t\"\u0004\u0002\u0002\u000e)\u0019\u0011qB,\u0002\u0005%|\u0017b\u0001\u001c\u0002\u000eQ\tA0A\u0003baBd\u0017\u0010F\u0002A\u00033AQ\u0001O\nA\u0002i\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002 \u0005\u0015\u0002\u0003B\u0010\u0002\"iJ1!a\t!\u0005\u0019y\u0005\u000f^5p]\"A\u0011q\u0005\u000b\u0002\u0002\u0003\u0007\u0001)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\f\u0011\u0007Q\u000by#C\u0002\u00022U\u0013aa\u00142kK\u000e$\b"
)
public class GetShufflePushMergerLocations implements MapOutputTrackerMessage, Product, Serializable {
   private final int shuffleId;

   public static Option unapply(final GetShufflePushMergerLocations x$0) {
      return GetShufflePushMergerLocations$.MODULE$.unapply(x$0);
   }

   public static GetShufflePushMergerLocations apply(final int shuffleId) {
      return GetShufflePushMergerLocations$.MODULE$.apply(shuffleId);
   }

   public static Function1 andThen(final Function1 g) {
      return GetShufflePushMergerLocations$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return GetShufflePushMergerLocations$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public GetShufflePushMergerLocations copy(final int shuffleId) {
      return new GetShufflePushMergerLocations(shuffleId);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public String productPrefix() {
      return "GetShufflePushMergerLocations";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.shuffleId());
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
      return x$1 instanceof GetShufflePushMergerLocations;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "shuffleId";
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
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof GetShufflePushMergerLocations) {
               GetShufflePushMergerLocations var4 = (GetShufflePushMergerLocations)x$1;
               if (this.shuffleId() == var4.shuffleId() && var4.canEqual(this)) {
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

   public GetShufflePushMergerLocations(final int shuffleId) {
      this.shuffleId = shuffleId;
      Product.$init$(this);
   }
}
