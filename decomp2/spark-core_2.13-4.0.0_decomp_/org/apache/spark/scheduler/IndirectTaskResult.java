package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.storage.BlockId;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed!B\r\u001b\u0001r\u0011\u0003\u0002C#\u0001\u0005+\u0007I\u0011\u0001$\t\u00115\u0003!\u0011#Q\u0001\n\u001dC\u0001B\u0014\u0001\u0003\u0016\u0004%\ta\u0014\u0005\t'\u0002\u0011\t\u0012)A\u0005!\")A\u000b\u0001C\u0001+\"9\u0011\fAA\u0001\n\u0003Q\u0006bB1\u0001#\u0003%\tA\u0019\u0005\b_\u0002\t\n\u0011\"\u0001q\u0011\u001d!\b!!A\u0005BUDq\u0001 \u0001\u0002\u0002\u0013\u0005Q\u0010C\u0005\u0002\u0004\u0001\t\t\u0011\"\u0001\u0002\u0006!I\u00111\u0002\u0001\u0002\u0002\u0013\u0005\u0013Q\u0002\u0005\n\u00037\u0001\u0011\u0011!C\u0001\u0003;A\u0011\"a\n\u0001\u0003\u0003%\t%!\u000b\t\u0013\u00055\u0002!!A\u0005B\u0005=\u0002\"CA\u0019\u0001\u0005\u0005I\u0011IA\u001a\u0011%\t)\u0004AA\u0001\n\u0003\n9d\u0002\u0006\u0002<i\t\t\u0011#\u0001\u001d\u0003{1\u0011\"\u0007\u000e\u0002\u0002#\u0005A$a\u0010\t\rQ\u001bB\u0011AA!\u0011%\t\tdEA\u0001\n\u000b\n\u0019\u0004C\u0005\u0002DM\t\t\u0011\"!\u0002F!I\u00111K\n\u0002\u0002\u0013\u0005\u0015Q\u000b\u0005\n\u0003_\u001a\u0012\u0011!C\u0005\u0003c\u0012!#\u00138eSJ,7\r\u001e+bg.\u0014Vm];mi*\u00111\u0004H\u0001\ng\u000eDW\rZ;mKJT!!\b\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0001\u0013AB1qC\u000eDWMC\u0001\"\u0003\ry'oZ\u000b\u0003GA\u001aR\u0001\u0001\u0013+u\t\u0003\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u0012a!\u00118z%\u00164\u0007cA\u0016-]5\t!$\u0003\u0002.5\tQA+Y:l%\u0016\u001cX\u000f\u001c;\u0011\u0005=\u0002D\u0002\u0001\u0003\u0006c\u0001\u0011\ra\r\u0002\u0002)\u000e\u0001\u0011C\u0001\u001b8!\t)S'\u0003\u00027M\t9aj\u001c;iS:<\u0007CA\u00139\u0013\tIdEA\u0002B]f\u0004\"a\u000f!\u000e\u0003qR!!\u0010 \u0002\u0005%|'\"A \u0002\t)\fg/Y\u0005\u0003\u0003r\u0012AbU3sS\u0006d\u0017N_1cY\u0016\u0004\"!J\"\n\u0005\u00113#a\u0002)s_\u0012,8\r^\u0001\bE2|7m[%e+\u00059\u0005C\u0001%L\u001b\u0005I%B\u0001&\u001d\u0003\u001d\u0019Ho\u001c:bO\u0016L!\u0001T%\u0003\u000f\tcwnY6JI\u0006A!\r\\8dW&#\u0007%\u0001\u0003tSj,W#\u0001)\u0011\u0005\u0015\n\u0016B\u0001*'\u0005\u0011auN\\4\u0002\u000bML'0\u001a\u0011\u0002\rqJg.\u001b;?)\r1v\u000b\u0017\t\u0004W\u0001q\u0003\"B#\u0006\u0001\u00049\u0005\"\u0002(\u0006\u0001\u0004\u0001\u0016\u0001B2paf,\"a\u00170\u0015\u0007q{\u0006\rE\u0002,\u0001u\u0003\"a\f0\u0005\u000bE2!\u0019A\u001a\t\u000f\u00153\u0001\u0013!a\u0001\u000f\"9aJ\u0002I\u0001\u0002\u0004\u0001\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0003G:,\u0012\u0001\u001a\u0016\u0003\u000f\u0016\\\u0013A\u001a\t\u0003O2l\u0011\u0001\u001b\u0006\u0003S*\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005-4\u0013AC1o]>$\u0018\r^5p]&\u0011Q\u000e\u001b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!B\u0019\b\u0005\u0004\u0019\u0014AD2paf$C-\u001a4bk2$HEM\u000b\u0003cN,\u0012A\u001d\u0016\u0003!\u0016$Q!\r\u0005C\u0002M\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001<\u0011\u0005]TX\"\u0001=\u000b\u0005et\u0014\u0001\u00027b]\u001eL!a\u001f=\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005q\bCA\u0013\u0000\u0013\r\t\tA\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004o\u0005\u001d\u0001\u0002CA\u0005\u0017\u0005\u0005\t\u0019\u0001@\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\u0001E\u0003\u0002\u0012\u0005]q'\u0004\u0002\u0002\u0014)\u0019\u0011Q\u0003\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u001a\u0005M!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\b\u0002&A\u0019Q%!\t\n\u0007\u0005\rbEA\u0004C_>dW-\u00198\t\u0011\u0005%Q\"!AA\u0002]\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019a/a\u000b\t\u0011\u0005%a\"!AA\u0002y\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002}\u0006AAo\\*ue&tw\rF\u0001w\u0003\u0019)\u0017/^1mgR!\u0011qDA\u001d\u0011!\tI!EA\u0001\u0002\u00049\u0014AE%oI&\u0014Xm\u0019;UCN\\'+Z:vYR\u0004\"aK\n\u0014\u0007M!#\b\u0006\u0002\u0002>\u0005)\u0011\r\u001d9msV!\u0011qIA')\u0019\tI%a\u0014\u0002RA!1\u0006AA&!\ry\u0013Q\n\u0003\u0006cY\u0011\ra\r\u0005\u0006\u000bZ\u0001\ra\u0012\u0005\u0006\u001dZ\u0001\r\u0001U\u0001\bk:\f\u0007\u000f\u001d7z+\u0011\t9&!\u001c\u0015\t\u0005e\u0013Q\r\t\u0006K\u0005m\u0013qL\u0005\u0004\u0003;2#AB(qi&|g\u000eE\u0003&\u0003C:\u0005+C\u0002\u0002d\u0019\u0012a\u0001V;qY\u0016\u0014\u0004\"CA4/\u0005\u0005\t\u0019AA5\u0003\rAH\u0005\r\t\u0005W\u0001\tY\u0007E\u00020\u0003[\"Q!M\fC\u0002M\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u001d\u0011\u0007]\f)(C\u0002\u0002xa\u0014aa\u00142kK\u000e$\b"
)
public class IndirectTaskResult implements TaskResult, Serializable, Product {
   private final BlockId blockId;
   private final long size;

   public static Option unapply(final IndirectTaskResult x$0) {
      return IndirectTaskResult$.MODULE$.unapply(x$0);
   }

   public static IndirectTaskResult apply(final BlockId blockId, final long size) {
      return IndirectTaskResult$.MODULE$.apply(blockId, size);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public BlockId blockId() {
      return this.blockId;
   }

   public long size() {
      return this.size;
   }

   public IndirectTaskResult copy(final BlockId blockId, final long size) {
      return new IndirectTaskResult(blockId, size);
   }

   public BlockId copy$default$1() {
      return this.blockId();
   }

   public long copy$default$2() {
      return this.size();
   }

   public String productPrefix() {
      return "IndirectTaskResult";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.blockId();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.size());
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
      return x$1 instanceof IndirectTaskResult;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "blockId";
         }
         case 1 -> {
            return "size";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.blockId()));
      var1 = Statics.mix(var1, Statics.longHash(this.size()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof IndirectTaskResult) {
               IndirectTaskResult var4 = (IndirectTaskResult)x$1;
               if (this.size() == var4.size()) {
                  label44: {
                     BlockId var10000 = this.blockId();
                     BlockId var5 = var4.blockId();
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

   public IndirectTaskResult(final BlockId blockId, final long size) {
      this.blockId = blockId;
      this.size = size;
      Product.$init$(this);
   }
}
