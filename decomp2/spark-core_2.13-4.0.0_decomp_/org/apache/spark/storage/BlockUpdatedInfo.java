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
   bytes = "\u0006\u0005\u0005=g\u0001\u0002\u0012$\u00012B\u0001B\u0011\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\u0011\u0002\u0011\t\u0012)A\u0005\t\"A\u0011\n\u0001BK\u0002\u0013\u0005!\n\u0003\u0005O\u0001\tE\t\u0015!\u0003L\u0011!y\u0005A!f\u0001\n\u0003\u0001\u0006\u0002\u0003+\u0001\u0005#\u0005\u000b\u0011B)\t\u0011U\u0003!Q3A\u0005\u0002YC\u0001B\u0017\u0001\u0003\u0012\u0003\u0006Ia\u0016\u0005\t7\u0002\u0011)\u001a!C\u0001-\"AA\f\u0001B\tB\u0003%q\u000bC\u0003^\u0001\u0011\u0005a\fC\u0004f\u0001\u0005\u0005I\u0011\u00014\t\u000f1\u0004\u0011\u0013!C\u0001[\"9\u0001\u0010AI\u0001\n\u0003I\bbB>\u0001#\u0003%\t\u0001 \u0005\b}\u0002\t\n\u0011\"\u0001\u0000\u0011!\t\u0019\u0001AI\u0001\n\u0003y\b\"CA\u0003\u0001\u0005\u0005I\u0011IA\u0004\u0011%\tI\u0002AA\u0001\n\u0003\tY\u0002C\u0005\u0002$\u0001\t\t\u0011\"\u0001\u0002&!I\u0011\u0011\u0007\u0001\u0002\u0002\u0013\u0005\u00131\u0007\u0005\n\u0003\u0003\u0002\u0011\u0011!C\u0001\u0003\u0007B\u0011\"!\u0014\u0001\u0003\u0003%\t%a\u0014\t\u0013\u0005M\u0003!!A\u0005B\u0005U\u0003\"CA,\u0001\u0005\u0005I\u0011IA-\u0011%\tY\u0006AA\u0001\n\u0003\nif\u0002\u0005\u0002n\rB\t!JA8\r\u001d\u00113\u0005#\u0001&\u0003cBa!\u0018\u000f\u0005\u0002\u0005u\u0004\u0002CA@9\u0011\u0005Q%!!\t\u0013\u0005}D$!A\u0005\u0002\u0006\u0015\u0006\"CAY9\u0005\u0005I\u0011QAZ\u0011%\t)\rHA\u0001\n\u0013\t9M\u0001\tCY>\u001c7.\u00169eCR,G-\u00138g_*\u0011A%J\u0001\bgR|'/Y4f\u0015\t1s%A\u0003ta\u0006\u00148N\u0003\u0002)S\u00051\u0011\r]1dQ\u0016T\u0011AK\u0001\u0004_J<7\u0001A\n\u0005\u00015\u001ad\u0007\u0005\u0002/c5\tqFC\u00011\u0003\u0015\u00198-\u00197b\u0013\t\u0011tF\u0001\u0004B]f\u0014VM\u001a\t\u0003]QJ!!N\u0018\u0003\u000fA\u0013x\u000eZ;diB\u0011qg\u0010\b\u0003qur!!\u000f\u001f\u000e\u0003iR!aO\u0016\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0014B\u0001 0\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001Q!\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005yz\u0013A\u00042m_\u000e\\W*\u00198bO\u0016\u0014\u0018\nZ\u000b\u0002\tB\u0011QIR\u0007\u0002G%\u0011qi\t\u0002\u000f\u00052|7m['b]\u0006<WM]%e\u0003=\u0011Gn\\2l\u001b\u0006t\u0017mZ3s\u0013\u0012\u0004\u0013a\u00022m_\u000e\\\u0017\nZ\u000b\u0002\u0017B\u0011Q\tT\u0005\u0003\u001b\u000e\u0012qA\u00117pG.LE-\u0001\u0005cY>\u001c7.\u00133!\u00031\u0019Ho\u001c:bO\u0016dUM^3m+\u0005\t\u0006CA#S\u0013\t\u00196E\u0001\u0007Ti>\u0014\u0018mZ3MKZ,G.A\u0007ti>\u0014\u0018mZ3MKZ,G\u000eI\u0001\b[\u0016l7+\u001b>f+\u00059\u0006C\u0001\u0018Y\u0013\tIvF\u0001\u0003M_:<\u0017\u0001C7f[NK'0\u001a\u0011\u0002\u0011\u0011L7o[*ju\u0016\f\u0011\u0002Z5tWNK'0\u001a\u0011\u0002\rqJg.\u001b;?)\u0019y\u0006-\u00192dIB\u0011Q\t\u0001\u0005\u0006\u0005.\u0001\r\u0001\u0012\u0005\u0006\u0013.\u0001\ra\u0013\u0005\u0006\u001f.\u0001\r!\u0015\u0005\u0006+.\u0001\ra\u0016\u0005\u00067.\u0001\raV\u0001\u0005G>\u0004\u0018\u0010\u0006\u0004`O\"L'n\u001b\u0005\b\u00052\u0001\n\u00111\u0001E\u0011\u001dIE\u0002%AA\u0002-Cqa\u0014\u0007\u0011\u0002\u0003\u0007\u0011\u000bC\u0004V\u0019A\u0005\t\u0019A,\t\u000fmc\u0001\u0013!a\u0001/\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00018+\u0005\u0011{7&\u00019\u0011\u0005E4X\"\u0001:\u000b\u0005M$\u0018!C;oG\",7m[3e\u0015\t)x&\u0001\u0006b]:|G/\u0019;j_:L!a\u001e:\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003iT#aS8\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\tQP\u000b\u0002R_\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\"TCAA\u0001U\t9v.\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tI\u0001\u0005\u0003\u0002\f\u0005UQBAA\u0007\u0015\u0011\ty!!\u0005\u0002\t1\fgn\u001a\u0006\u0003\u0003'\tAA[1wC&!\u0011qCA\u0007\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011Q\u0004\t\u0004]\u0005}\u0011bAA\u0011_\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011qEA\u0017!\rq\u0013\u0011F\u0005\u0004\u0003Wy#aA!os\"I\u0011q\u0006\u000b\u0002\u0002\u0003\u0007\u0011QD\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005U\u0002CBA\u001c\u0003{\t9#\u0004\u0002\u0002:)\u0019\u00111H\u0018\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002@\u0005e\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0012\u0002LA\u0019a&a\u0012\n\u0007\u0005%sFA\u0004C_>dW-\u00198\t\u0013\u0005=b#!AA\u0002\u0005\u001d\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0003\u0002R!I\u0011qF\f\u0002\u0002\u0003\u0007\u0011QD\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011QD\u0001\ti>\u001cFO]5oOR\u0011\u0011\u0011B\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u0015\u0013q\f\u0005\n\u0003_Q\u0012\u0011!a\u0001\u0003OA3\u0001AA2!\u0011\t)'!\u001b\u000e\u0005\u0005\u001d$BA;&\u0013\u0011\tY'a\u001a\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002!\tcwnY6Va\u0012\fG/\u001a3J]\u001a|\u0007CA#\u001d'\u0011aR&a\u001d\u0011\t\u0005U\u00141P\u0007\u0003\u0003oRA!!\u001f\u0002\u0012\u0005\u0011\u0011n\\\u0005\u0004\u0001\u0006]DCAA8\u0003\u0015\t\u0007\u000f\u001d7z)\ry\u00161\u0011\u0005\b\u0003\u000bs\u0002\u0019AAD\u0003=)\b\u000fZ1uK\ncwnY6J]\u001a|\u0007\u0003BAE\u0003?sA!a#\u0002\u001c:!\u0011QRAM\u001d\u0011\ty)a&\u000f\t\u0005E\u0015Q\u0013\b\u0004s\u0005M\u0015\"\u0001\u0016\n\u0005!J\u0013B\u0001\u0014(\u0013\t!S%C\u0002\u0002\u001e\u000e\nAC\u00117pG.l\u0015M\\1hKJlUm]:bO\u0016\u001c\u0018\u0002BAQ\u0003G\u0013q\"\u00169eCR,'\t\\8dW&sgm\u001c\u0006\u0004\u0003;\u001bCcC0\u0002(\u0006%\u00161VAW\u0003_CQAQ\u0010A\u0002\u0011CQ!S\u0010A\u0002-CQaT\u0010A\u0002ECQ!V\u0010A\u0002]CQaW\u0010A\u0002]\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u00026\u0006\u0005\u0007#\u0002\u0018\u00028\u0006m\u0016bAA]_\t1q\n\u001d;j_:\u0004\u0002BLA_\t.\u000bvkV\u0005\u0004\u0003\u007f{#A\u0002+va2,W\u0007\u0003\u0005\u0002D\u0002\n\t\u00111\u0001`\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0013\u0004B!a\u0003\u0002L&!\u0011QZA\u0007\u0005\u0019y%M[3di\u0002"
)
public class BlockUpdatedInfo implements Product, Serializable {
   private final BlockManagerId blockManagerId;
   private final BlockId blockId;
   private final StorageLevel storageLevel;
   private final long memSize;
   private final long diskSize;

   public static Option unapply(final BlockUpdatedInfo x$0) {
      return BlockUpdatedInfo$.MODULE$.unapply(x$0);
   }

   public static BlockUpdatedInfo apply(final BlockManagerId blockManagerId, final BlockId blockId, final StorageLevel storageLevel, final long memSize, final long diskSize) {
      return BlockUpdatedInfo$.MODULE$.apply(blockManagerId, blockId, storageLevel, memSize, diskSize);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public BlockManagerId blockManagerId() {
      return this.blockManagerId;
   }

   public BlockId blockId() {
      return this.blockId;
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

   public BlockUpdatedInfo copy(final BlockManagerId blockManagerId, final BlockId blockId, final StorageLevel storageLevel, final long memSize, final long diskSize) {
      return new BlockUpdatedInfo(blockManagerId, blockId, storageLevel, memSize, diskSize);
   }

   public BlockManagerId copy$default$1() {
      return this.blockManagerId();
   }

   public BlockId copy$default$2() {
      return this.blockId();
   }

   public StorageLevel copy$default$3() {
      return this.storageLevel();
   }

   public long copy$default$4() {
      return this.memSize();
   }

   public long copy$default$5() {
      return this.diskSize();
   }

   public String productPrefix() {
      return "BlockUpdatedInfo";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.blockManagerId();
         }
         case 1 -> {
            return this.blockId();
         }
         case 2 -> {
            return this.storageLevel();
         }
         case 3 -> {
            return BoxesRunTime.boxToLong(this.memSize());
         }
         case 4 -> {
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
      return x$1 instanceof BlockUpdatedInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "blockManagerId";
         }
         case 1 -> {
            return "blockId";
         }
         case 2 -> {
            return "storageLevel";
         }
         case 3 -> {
            return "memSize";
         }
         case 4 -> {
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
      var1 = Statics.mix(var1, Statics.anyHash(this.blockManagerId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.blockId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.storageLevel()));
      var1 = Statics.mix(var1, Statics.longHash(this.memSize()));
      var1 = Statics.mix(var1, Statics.longHash(this.diskSize()));
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof BlockUpdatedInfo) {
               BlockUpdatedInfo var4 = (BlockUpdatedInfo)x$1;
               if (this.memSize() == var4.memSize() && this.diskSize() == var4.diskSize()) {
                  label64: {
                     BlockManagerId var10000 = this.blockManagerId();
                     BlockManagerId var5 = var4.blockManagerId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label64;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label64;
                     }

                     BlockId var8 = this.blockId();
                     BlockId var6 = var4.blockId();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label64;
                        }
                     } else if (!var8.equals(var6)) {
                        break label64;
                     }

                     StorageLevel var9 = this.storageLevel();
                     StorageLevel var7 = var4.storageLevel();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label64;
                        }
                     } else if (!var9.equals(var7)) {
                        break label64;
                     }

                     if (var4.canEqual(this)) {
                        break label71;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public BlockUpdatedInfo(final BlockManagerId blockManagerId, final BlockId blockId, final StorageLevel storageLevel, final long memSize, final long diskSize) {
      this.blockManagerId = blockManagerId;
      this.blockId = blockId;
      this.storageLevel = storageLevel;
      this.memSize = memSize;
      this.diskSize = diskSize;
      Product.$init$(this);
   }
}
