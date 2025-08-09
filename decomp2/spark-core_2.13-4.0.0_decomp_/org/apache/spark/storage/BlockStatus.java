package org.apache.spark.storage;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud\u0001B\u000f\u001f\u0001\u001eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005\u007f!AA\t\u0001BK\u0002\u0013\u0005Q\t\u0003\u0005J\u0001\tE\t\u0015!\u0003G\u0011!Q\u0005A!f\u0001\n\u0003)\u0005\u0002C&\u0001\u0005#\u0005\u000b\u0011\u0002$\t\u000b1\u0003A\u0011A'\t\u000bI\u0003A\u0011A*\t\u000f]\u0003\u0011\u0011!C\u00011\"9A\fAI\u0001\n\u0003i\u0006b\u00025\u0001#\u0003%\t!\u001b\u0005\bW\u0002\t\n\u0011\"\u0001j\u0011\u001da\u0007!!A\u0005B5DqA\u001e\u0001\u0002\u0002\u0013\u0005q\u000fC\u0004|\u0001\u0005\u0005I\u0011\u0001?\t\u0013\u0005\u0015\u0001!!A\u0005B\u0005\u001d\u0001\"CA\u000b\u0001\u0005\u0005I\u0011AA\f\u0011%\tY\u0002AA\u0001\n\u0003\ni\u0002C\u0005\u0002\"\u0001\t\t\u0011\"\u0011\u0002$!I\u0011Q\u0005\u0001\u0002\u0002\u0013\u0005\u0013q\u0005\u0005\n\u0003S\u0001\u0011\u0011!C!\u0003W9q!a\u000f\u001f\u0011\u0003\tiD\u0002\u0004\u001e=!\u0005\u0011q\b\u0005\u0007\u0019^!\t!a\u0013\t\u000f\u00055s\u0003\"\u0001\u0002P!I\u0011\u0011K\f\u0002\u0002\u0013\u0005\u00151\u000b\u0005\n\u00037:\u0012\u0011!CA\u0003;B\u0011\"a\u001c\u0018\u0003\u0003%I!!\u001d\u0003\u0017\tcwnY6Ti\u0006$Xo\u001d\u0006\u0003?\u0001\nqa\u001d;pe\u0006<WM\u0003\u0002\"E\u0005)1\u000f]1sW*\u00111\u0005J\u0001\u0007CB\f7\r[3\u000b\u0003\u0015\n1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u0015/cA\u0011\u0011\u0006L\u0007\u0002U)\t1&A\u0003tG\u0006d\u0017-\u0003\u0002.U\t1\u0011I\\=SK\u001a\u0004\"!K\u0018\n\u0005AR#a\u0002)s_\u0012,8\r\u001e\t\u0003eir!a\r\u001d\u000f\u0005Q:T\"A\u001b\u000b\u0005Y2\u0013A\u0002\u001fs_>$h(C\u0001,\u0013\tI$&A\u0004qC\u000e\\\u0017mZ3\n\u0005mb$\u0001D*fe&\fG.\u001b>bE2,'BA\u001d+\u00031\u0019Ho\u001c:bO\u0016dUM^3m+\u0005y\u0004C\u0001!B\u001b\u0005q\u0012B\u0001\"\u001f\u00051\u0019Fo\u001c:bO\u0016dUM^3m\u00035\u0019Ho\u001c:bO\u0016dUM^3mA\u00059Q.Z7TSj,W#\u0001$\u0011\u0005%:\u0015B\u0001%+\u0005\u0011auN\\4\u0002\u00115,WnU5{K\u0002\n\u0001\u0002Z5tWNK'0Z\u0001\nI&\u001c8nU5{K\u0002\na\u0001P5oSRtD\u0003\u0002(P!F\u0003\"\u0001\u0011\u0001\t\u000bu:\u0001\u0019A \t\u000b\u0011;\u0001\u0019\u0001$\t\u000b);\u0001\u0019\u0001$\u0002\u0011%\u001c8)Y2iK\u0012,\u0012\u0001\u0016\t\u0003SUK!A\u0016\u0016\u0003\u000f\t{w\u000e\\3b]\u0006!1m\u001c9z)\u0011q\u0015LW.\t\u000fuJ\u0001\u0013!a\u0001\u007f!9A)\u0003I\u0001\u0002\u00041\u0005b\u0002&\n!\u0003\u0005\rAR\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005q&FA `W\u0005\u0001\u0007CA1g\u001b\u0005\u0011'BA2e\u0003%)hn\u00195fG.,GM\u0003\u0002fU\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u001d\u0014'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00016+\u0005\u0019{\u0016AD2paf$C-\u001a4bk2$HeM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u00039\u0004\"a\u001c;\u000e\u0003AT!!\u001d:\u0002\t1\fgn\u001a\u0006\u0002g\u0006!!.\u0019<b\u0013\t)\bO\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002qB\u0011\u0011&_\u0005\u0003u*\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2!`A\u0001!\tIc0\u0003\u0002\u0000U\t\u0019\u0011I\\=\t\u0011\u0005\rq\"!AA\u0002a\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0005!\u0015\tY!!\u0005~\u001b\t\tiAC\u0002\u0002\u0010)\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\u0019\"!\u0004\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004)\u0006e\u0001\u0002CA\u0002#\u0005\u0005\t\u0019A?\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004]\u0006}\u0001\u0002CA\u0002%\u0005\u0005\t\u0019\u0001=\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001_\u0001\ti>\u001cFO]5oOR\ta.\u0001\u0004fcV\fGn\u001d\u000b\u0004)\u00065\u0002\u0002CA\u0002+\u0005\u0005\t\u0019A?)\u0007\u0001\t\t\u0004\u0005\u0003\u00024\u0005]RBAA\u001b\u0015\t)\u0007%\u0003\u0003\u0002:\u0005U\"\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017a\u0003\"m_\u000e\\7\u000b^1ukN\u0004\"\u0001Q\f\u0014\t]A\u0013\u0011\t\t\u0005\u0003\u0007\nI%\u0004\u0002\u0002F)\u0019\u0011q\t:\u0002\u0005%|\u0017bA\u001e\u0002FQ\u0011\u0011QH\u0001\u0006K6\u0004H/_\u000b\u0002\u001d\u0006)\u0011\r\u001d9msR9a*!\u0016\u0002X\u0005e\u0003\"B\u001f\u001b\u0001\u0004y\u0004\"\u0002#\u001b\u0001\u00041\u0005\"\u0002&\u001b\u0001\u00041\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003?\nY\u0007E\u0003*\u0003C\n)'C\u0002\u0002d)\u0012aa\u00149uS>t\u0007CB\u0015\u0002h}2e)C\u0002\u0002j)\u0012a\u0001V;qY\u0016\u001c\u0004\u0002CA77\u0005\u0005\t\u0019\u0001(\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002tA\u0019q.!\u001e\n\u0007\u0005]\u0004O\u0001\u0004PE*,7\r\u001e\u0015\u0004/\u0005E\u0002f\u0001\f\u00022\u0001"
)
public class BlockStatus implements Product, Serializable {
   private final StorageLevel storageLevel;
   private final long memSize;
   private final long diskSize;

   public static Option unapply(final BlockStatus x$0) {
      return BlockStatus$.MODULE$.unapply(x$0);
   }

   public static BlockStatus apply(final StorageLevel storageLevel, final long memSize, final long diskSize) {
      return BlockStatus$.MODULE$.apply(storageLevel, memSize, diskSize);
   }

   public static BlockStatus empty() {
      return BlockStatus$.MODULE$.empty();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public StorageLevel storageLevel() {
      return this.storageLevel;
   }

   public long memSize() {
      return this.memSize;
   }

   public long diskSize() {
      return this.diskSize;
   }

   public boolean isCached() {
      return this.memSize() + this.diskSize() > 0L;
   }

   public BlockStatus copy(final StorageLevel storageLevel, final long memSize, final long diskSize) {
      return new BlockStatus(storageLevel, memSize, diskSize);
   }

   public StorageLevel copy$default$1() {
      return this.storageLevel();
   }

   public long copy$default$2() {
      return this.memSize();
   }

   public long copy$default$3() {
      return this.diskSize();
   }

   public String productPrefix() {
      return "BlockStatus";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.storageLevel();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.memSize());
         }
         case 2 -> {
            return BoxesRunTime.boxToLong(this.diskSize());
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
      return x$1 instanceof BlockStatus;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "storageLevel";
         }
         case 1 -> {
            return "memSize";
         }
         case 2 -> {
            return "diskSize";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.storageLevel()));
      var1 = Statics.mix(var1, Statics.longHash(this.memSize()));
      var1 = Statics.mix(var1, Statics.longHash(this.diskSize()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof BlockStatus) {
               BlockStatus var4 = (BlockStatus)x$1;
               if (this.memSize() == var4.memSize() && this.diskSize() == var4.diskSize()) {
                  label48: {
                     StorageLevel var10000 = this.storageLevel();
                     StorageLevel var5 = var4.storageLevel();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
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

   public BlockStatus(final StorageLevel storageLevel, final long memSize, final long diskSize) {
      this.storageLevel = storageLevel;
      this.memSize = memSize;
      this.diskSize = diskSize;
      Product.$init$(this);
   }
}
