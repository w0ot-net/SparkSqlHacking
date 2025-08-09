package org.apache.spark.storage.memory;

import java.io.Serializable;
import org.apache.spark.memory.MemoryMode;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dh\u0001B\u0010!\t.B\u0001\"\u0015\u0001\u0003\u0016\u0004%\tA\u0015\u0005\t-\u0002\u0011\t\u0012)A\u0005'\"Aq\u000b\u0001BK\u0002\u0013\u0005\u0001\f\u0003\u0005]\u0001\tE\t\u0015!\u0003Z\u0011!i\u0006A!f\u0001\n\u0003q\u0006\u0002\u00033\u0001\u0005#\u0005\u000b\u0011B0\t\u0011\u0015\u0004!Q3A\u0005\u0002\u0019D\u0001\"\u001c\u0001\u0003\u0012\u0003\u0006Ia\u001a\u0005\u0006]\u0002!\ta\u001c\u0005\bk\u0002\t\t\u0011\"\u0001w\u0011%\t\u0019\u0001AI\u0001\n\u0003\t)\u0001C\u0005\u0002 \u0001\t\n\u0011\"\u0001\u0002\"!I\u0011\u0011\u0006\u0001\u0012\u0002\u0013\u0005\u00111\u0006\u0005\n\u0003g\u0001\u0011\u0013!C\u0001\u0003kA\u0011\"!\u0010\u0001\u0003\u0003%\t%a\u0010\t\u0013\u0005E\u0003!!A\u0005\u0002\u0005M\u0003\"CA.\u0001\u0005\u0005I\u0011AA/\u0011%\t\u0019\u0007AA\u0001\n\u0003\n)\u0007C\u0005\u0002t\u0001\t\t\u0011\"\u0001\u0002v!I\u0011q\u0010\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0011\u0005\n\u0003\u000b\u0003\u0011\u0011!C!\u0003\u000fC\u0011\"!#\u0001\u0003\u0003%\t%a#\t\u0013\u00055\u0005!!A\u0005B\u0005=u!CAJA\u0005\u0005\t\u0012BAK\r!y\u0002%!A\t\n\u0005]\u0005B\u00028\u001a\t\u0003\t\u0019\u000bC\u0005\u0002\nf\t\t\u0011\"\u0012\u0002\f\"I\u0011QU\r\u0002\u0002\u0013\u0005\u0015q\u0015\u0005\n\u0003{K\u0012\u0011!CA\u0003\u007fC\u0011\"!8\u001a\u0003\u0003%I!a8\u0003/\u0011+7/\u001a:jC2L'0\u001a3NK6|'/_#oiJL(BA\u0011#\u0003\u0019iW-\\8ss*\u00111\u0005J\u0001\bgR|'/Y4f\u0015\t)c%A\u0003ta\u0006\u00148N\u0003\u0002(Q\u00051\u0011\r]1dQ\u0016T\u0011!K\u0001\u0004_J<7\u0001A\u000b\u0003Ye\u001aR\u0001A\u00174\u0005\u0016\u0003\"AL\u0019\u000e\u0003=R\u0011\u0001M\u0001\u0006g\u000e\fG.Y\u0005\u0003e=\u0012a!\u00118z%\u00164\u0007c\u0001\u001b6o5\t\u0001%\u0003\u00027A\tYQ*Z7pef,e\u000e\u001e:z!\tA\u0014\b\u0004\u0001\u0005\u000bi\u0002!\u0019A\u001e\u0003\u0003Q\u000b\"\u0001P \u0011\u00059j\u0014B\u0001 0\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\f!\n\u0005\u0005{#aA!osB\u0011afQ\u0005\u0003\t>\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002G\u001d:\u0011q\t\u0014\b\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015*\na\u0001\u0010:p_Rt\u0014\"\u0001\u0019\n\u00055{\u0013a\u00029bG.\fw-Z\u0005\u0003\u001fB\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!T\u0018\u0002\u000bY\fG.^3\u0016\u0003M\u00032A\f+8\u0013\t)vFA\u0003BeJ\f\u00170\u0001\u0004wC2,X\rI\u0001\u0005g&TX-F\u0001Z!\tq#,\u0003\u0002\\_\t!Aj\u001c8h\u0003\u0015\u0019\u0018N_3!\u0003)iW-\\8ss6{G-Z\u000b\u0002?B\u0011\u0001MY\u0007\u0002C*\u0011\u0011\u0005J\u0005\u0003G\u0006\u0014!\"T3n_JLXj\u001c3f\u0003-iW-\\8ss6{G-\u001a\u0011\u0002\u0011\rd\u0017m]:UC\u001e,\u0012a\u001a\t\u0004Q.<T\"A5\u000b\u0005)|\u0013a\u0002:fM2,7\r^\u0005\u0003Y&\u0014\u0001b\u00117bgN$\u0016mZ\u0001\nG2\f7o\u001d+bO\u0002\na\u0001P5oSRtD#\u00029reN$\bc\u0001\u001b\u0001o!)\u0011+\u0003a\u0001'\")q+\u0003a\u00013\")Q,\u0003a\u0001?\")Q-\u0003a\u0001O\u0006!1m\u001c9z+\t9(\u0010F\u0003ywvtx\u0010E\u00025\u0001e\u0004\"\u0001\u000f>\u0005\u000biR!\u0019A\u001e\t\u000fES\u0001\u0013!a\u0001yB\u0019a\u0006V=\t\u000f]S\u0001\u0013!a\u00013\"9QL\u0003I\u0001\u0002\u0004y\u0006\u0002C3\u000b!\u0003\u0005\r!!\u0001\u0011\u0007!\\\u00170\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\t\u0005\u001d\u0011QD\u000b\u0003\u0003\u0013Q3aUA\u0006W\t\ti\u0001\u0005\u0003\u0002\u0010\u0005eQBAA\t\u0015\u0011\t\u0019\"!\u0006\u0002\u0013Ut7\r[3dW\u0016$'bAA\f_\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005m\u0011\u0011\u0003\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!\u0002\u001e\f\u0005\u0004Y\u0014AD2paf$C-\u001a4bk2$HEM\u000b\u0005\u0003G\t9#\u0006\u0002\u0002&)\u001a\u0011,a\u0003\u0005\u000bib!\u0019A\u001e\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU!\u0011QFA\u0019+\t\tyCK\u0002`\u0003\u0017!QAO\u0007C\u0002m\nabY8qs\u0012\"WMZ1vYR$C'\u0006\u0003\u00028\u0005mRCAA\u001dU\r9\u00171\u0002\u0003\u0006u9\u0011\raO\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005\u0005\u0003\u0003BA\"\u0003\u001bj!!!\u0012\u000b\t\u0005\u001d\u0013\u0011J\u0001\u0005Y\u0006twM\u0003\u0002\u0002L\u0005!!.\u0019<b\u0013\u0011\ty%!\u0012\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t)\u0006E\u0002/\u0003/J1!!\u00170\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\ry\u0014q\f\u0005\n\u0003C\n\u0012\u0011!a\u0001\u0003+\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA4!\u0015\tI'a\u001c@\u001b\t\tYGC\u0002\u0002n=\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\t(a\u001b\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003o\ni\bE\u0002/\u0003sJ1!a\u001f0\u0005\u001d\u0011un\u001c7fC:D\u0001\"!\u0019\u0014\u0003\u0003\u0005\raP\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002B\u0005\r\u0005\"CA1)\u0005\u0005\t\u0019AA+\u0003!A\u0017m\u001d5D_\u0012,GCAA+\u0003!!xn\u0015;sS:<GCAA!\u0003\u0019)\u0017/^1mgR!\u0011qOAI\u0011!\t\tgFA\u0001\u0002\u0004y\u0014a\u0006#fg\u0016\u0014\u0018.\u00197ju\u0016$W*Z7pef,e\u000e\u001e:z!\t!\u0014d\u0005\u0003\u001a[\u0005e\u0005\u0003BAN\u0003Ck!!!(\u000b\t\u0005}\u0015\u0011J\u0001\u0003S>L1aTAO)\t\t)*A\u0003baBd\u00170\u0006\u0003\u0002*\u0006=FCCAV\u0003c\u000b),a.\u0002:B!A\u0007AAW!\rA\u0014q\u0016\u0003\u0006uq\u0011\ra\u000f\u0005\u0007#r\u0001\r!a-\u0011\t9\"\u0016Q\u0016\u0005\u0006/r\u0001\r!\u0017\u0005\u0006;r\u0001\ra\u0018\u0005\u0007Kr\u0001\r!a/\u0011\t!\\\u0017QV\u0001\bk:\f\u0007\u000f\u001d7z+\u0011\t\t-a5\u0015\t\u0005\r\u0017q\u001b\t\u0006]\u0005\u0015\u0017\u0011Z\u0005\u0004\u0003\u000f|#AB(qi&|g\u000eE\u0005/\u0003\u0017\fy-W0\u0002V&\u0019\u0011QZ\u0018\u0003\rQ+\b\u000f\\35!\u0011qC+!5\u0011\u0007a\n\u0019\u000eB\u0003;;\t\u00071\b\u0005\u0003iW\u0006E\u0007\"CAm;\u0005\u0005\t\u0019AAn\u0003\rAH\u0005\r\t\u0005i\u0001\t\t.\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002bB!\u00111IAr\u0013\u0011\t)/!\u0012\u0003\r=\u0013'.Z2u\u0001"
)
public class DeserializedMemoryEntry implements MemoryEntry, Product, Serializable {
   private final Object value;
   private final long size;
   private final MemoryMode memoryMode;
   private final ClassTag classTag;

   public static Option unapply(final DeserializedMemoryEntry x$0) {
      return DeserializedMemoryEntry$.MODULE$.unapply(x$0);
   }

   public static DeserializedMemoryEntry apply(final Object value, final long size, final MemoryMode memoryMode, final ClassTag classTag) {
      return DeserializedMemoryEntry$.MODULE$.apply(value, size, memoryMode, classTag);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object value() {
      return this.value;
   }

   public long size() {
      return this.size;
   }

   public MemoryMode memoryMode() {
      return this.memoryMode;
   }

   public ClassTag classTag() {
      return this.classTag;
   }

   public DeserializedMemoryEntry copy(final Object value, final long size, final MemoryMode memoryMode, final ClassTag classTag) {
      return new DeserializedMemoryEntry(value, size, memoryMode, classTag);
   }

   public Object copy$default$1() {
      return this.value();
   }

   public long copy$default$2() {
      return this.size();
   }

   public MemoryMode copy$default$3() {
      return this.memoryMode();
   }

   public ClassTag copy$default$4() {
      return this.classTag();
   }

   public String productPrefix() {
      return "DeserializedMemoryEntry";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.value();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.size());
         }
         case 2 -> {
            return this.memoryMode();
         }
         case 3 -> {
            return this.classTag();
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
      return x$1 instanceof DeserializedMemoryEntry;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "value";
         }
         case 1 -> {
            return "size";
         }
         case 2 -> {
            return "memoryMode";
         }
         case 3 -> {
            return "classTag";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.value()));
      var1 = Statics.mix(var1, Statics.longHash(this.size()));
      var1 = Statics.mix(var1, Statics.anyHash(this.memoryMode()));
      var1 = Statics.mix(var1, Statics.anyHash(this.classTag()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof DeserializedMemoryEntry) {
               DeserializedMemoryEntry var4 = (DeserializedMemoryEntry)x$1;
               if (this.size() == var4.size() && BoxesRunTime.equals(this.value(), var4.value())) {
                  label56: {
                     MemoryMode var10000 = this.memoryMode();
                     MemoryMode var5 = var4.memoryMode();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label56;
                     }

                     ClassTag var7 = this.classTag();
                     ClassTag var6 = var4.classTag();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label56;
                        }
                     } else if (!var7.equals(var6)) {
                        break label56;
                     }

                     if (var4.canEqual(this)) {
                        break label63;
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

   public DeserializedMemoryEntry(final Object value, final long size, final MemoryMode memoryMode, final ClassTag classTag) {
      this.value = value;
      this.size = size;
      this.memoryMode = memoryMode;
      this.classTag = classTag;
      Product.$init$(this);
   }
}
