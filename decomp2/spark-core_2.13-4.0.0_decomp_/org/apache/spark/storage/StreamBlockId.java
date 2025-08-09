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
   bytes = "\u0006\u0005\u0005Md\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005y!A\u0001\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005F\u0001\tE\t\u0015!\u0003C\u0011\u00151\u0005\u0001\"\u0001H\u0011\u0015Y\u0005\u0001\"\u0011M\u0011\u001d)\u0006!!A\u0005\u0002YCq!\u0017\u0001\u0012\u0002\u0013\u0005!\fC\u0004f\u0001E\u0005I\u0011\u00014\t\u000f!\u0004\u0011\u0011!C!S\"9\u0011\u000fAA\u0001\n\u0003Y\u0004b\u0002:\u0001\u0003\u0003%\ta\u001d\u0005\bs\u0002\t\t\u0011\"\u0011{\u0011%\t\u0019\u0001AA\u0001\n\u0003\t)\u0001C\u0005\u0002\u0010\u0001\t\t\u0011\"\u0011\u0002\u0012!I\u0011Q\u0003\u0001\u0002\u0002\u0013\u0005\u0013q\u0003\u0005\n\u00033\u0001\u0011\u0011!C!\u000379\u0011\"a\u000b\u001b\u0003\u0003E\t!!\f\u0007\u0011eQ\u0012\u0011!E\u0001\u0003_AaAR\n\u0005\u0002\u0005\u001d\u0003\"CA%'\u0005\u0005IQIA&\u0011%\tieEA\u0001\n\u0003\u000by\u0005C\u0005\u0002VM\t\t\u0011\"!\u0002X!I\u0011\u0011N\n\u0002\u0002\u0013%\u00111\u000e\u0002\u000e'R\u0014X-Y7CY>\u001c7.\u00133\u000b\u0005ma\u0012aB:u_J\fw-\u001a\u0006\u0003;y\tQa\u001d9be.T!a\b\u0011\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0013aA8sO\u000e\u00011\u0003\u0002\u0001%Q9\u0002\"!\n\u0014\u000e\u0003iI!a\n\u000e\u0003\u000f\tcwnY6JIB\u0011\u0011\u0006L\u0007\u0002U)\t1&A\u0003tG\u0006d\u0017-\u0003\u0002.U\t9\u0001K]8ek\u000e$\bCA\u00188\u001d\t\u0001TG\u0004\u00022i5\t!G\u0003\u00024E\u00051AH]8pizJ\u0011aK\u0005\u0003m)\nq\u0001]1dW\u0006<W-\u0003\u00029s\ta1+\u001a:jC2L'0\u00192mK*\u0011aGK\u0001\tgR\u0014X-Y7JIV\tA\b\u0005\u0002*{%\u0011aH\u000b\u0002\u0004\u0013:$\u0018!C:ue\u0016\fW.\u00133!\u0003!)h.[9vK&#W#\u0001\"\u0011\u0005%\u001a\u0015B\u0001#+\u0005\u0011auN\\4\u0002\u0013Ut\u0017.];f\u0013\u0012\u0004\u0013A\u0002\u001fj]&$h\bF\u0002I\u0013*\u0003\"!\n\u0001\t\u000bi*\u0001\u0019\u0001\u001f\t\u000b\u0001+\u0001\u0019\u0001\"\u0002\t9\fW.Z\u000b\u0002\u001bB\u0011aJ\u0015\b\u0003\u001fB\u0003\"!\r\u0016\n\u0005ES\u0013A\u0002)sK\u0012,g-\u0003\u0002T)\n11\u000b\u001e:j]\u001eT!!\u0015\u0016\u0002\t\r|\u0007/\u001f\u000b\u0004\u0011^C\u0006b\u0002\u001e\b!\u0003\u0005\r\u0001\u0010\u0005\b\u0001\u001e\u0001\n\u00111\u0001C\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u0017\u0016\u0003yq[\u0013!\u0018\t\u0003=\u000el\u0011a\u0018\u0006\u0003A\u0006\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\tT\u0013AC1o]>$\u0018\r^5p]&\u0011Am\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002O*\u0012!\tX\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003)\u0004\"a\u001b9\u000e\u00031T!!\u001c8\u0002\t1\fgn\u001a\u0006\u0002_\u0006!!.\u0019<b\u0013\t\u0019F.\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005Q<\bCA\u0015v\u0013\t1(FA\u0002B]fDq\u0001\u001f\u0007\u0002\u0002\u0003\u0007A(A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002wB\u0019Ap ;\u000e\u0003uT!A \u0016\u0002\u0015\r|G\u000e\\3di&|g.C\u0002\u0002\u0002u\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011qAA\u0007!\rI\u0013\u0011B\u0005\u0004\u0003\u0017Q#a\u0002\"p_2,\u0017M\u001c\u0005\bq:\t\t\u00111\u0001u\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007)\f\u0019\u0002C\u0004y\u001f\u0005\u0005\t\u0019\u0001\u001f\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001P\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u001d\u0011Q\u0004\u0005\bqF\t\t\u00111\u0001uQ\r\u0001\u0011\u0011\u0005\t\u0005\u0003G\t9#\u0004\u0002\u0002&)\u0011!\rH\u0005\u0005\u0003S\t)C\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018.A\u0007TiJ,\u0017-\u001c\"m_\u000e\\\u0017\n\u001a\t\u0003KM\u0019RaEA\u0019\u0003{\u0001r!a\r\u0002:q\u0012\u0005*\u0004\u0002\u00026)\u0019\u0011q\u0007\u0016\u0002\u000fI,h\u000e^5nK&!\u00111HA\u001b\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003\u007f\t)%\u0004\u0002\u0002B)\u0019\u00111\t8\u0002\u0005%|\u0017b\u0001\u001d\u0002BQ\u0011\u0011QF\u0001\ti>\u001cFO]5oOR\t!.A\u0003baBd\u0017\u0010F\u0003I\u0003#\n\u0019\u0006C\u0003;-\u0001\u0007A\bC\u0003A-\u0001\u0007!)A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005e\u0013Q\r\t\u0006S\u0005m\u0013qL\u0005\u0004\u0003;R#AB(qi&|g\u000eE\u0003*\u0003Cb$)C\u0002\u0002d)\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CA4/\u0005\u0005\t\u0019\u0001%\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002nA\u00191.a\u001c\n\u0007\u0005EDN\u0001\u0004PE*,7\r\u001e"
)
public class StreamBlockId extends BlockId implements Product, Serializable {
   private final int streamId;
   private final long uniqueId;

   public static Option unapply(final StreamBlockId x$0) {
      return StreamBlockId$.MODULE$.unapply(x$0);
   }

   public static StreamBlockId apply(final int streamId, final long uniqueId) {
      return StreamBlockId$.MODULE$.apply(streamId, uniqueId);
   }

   public static Function1 tupled() {
      return StreamBlockId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return StreamBlockId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int streamId() {
      return this.streamId;
   }

   public long uniqueId() {
      return this.uniqueId;
   }

   public String name() {
      int var10000 = this.streamId();
      return "input-" + var10000 + "-" + this.uniqueId();
   }

   public StreamBlockId copy(final int streamId, final long uniqueId) {
      return new StreamBlockId(streamId, uniqueId);
   }

   public int copy$default$1() {
      return this.streamId();
   }

   public long copy$default$2() {
      return this.uniqueId();
   }

   public String productPrefix() {
      return "StreamBlockId";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.streamId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.uniqueId());
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
      return x$1 instanceof StreamBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "streamId";
         }
         case 1 -> {
            return "uniqueId";
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
      var1 = Statics.mix(var1, Statics.longHash(this.uniqueId()));
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof StreamBlockId) {
               StreamBlockId var4 = (StreamBlockId)x$1;
               if (this.streamId() == var4.streamId() && this.uniqueId() == var4.uniqueId() && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public StreamBlockId(final int streamId, final long uniqueId) {
      this.streamId = streamId;
      this.uniqueId = uniqueId;
      Product.$init$(this);
   }
}
