package org.apache.spark.ui.storage;

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
   bytes = "\u0006\u0005\u0005}e!\u0002\u0012$\u0001\u0016j\u0003\u0002\u0003#\u0001\u0005+\u0007I\u0011A#\t\u00119\u0003!\u0011#Q\u0001\n\u0019C\u0001b\u0014\u0001\u0003\u0016\u0004%\t!\u0012\u0005\t!\u0002\u0011\t\u0012)A\u0005\r\"A\u0011\u000b\u0001BK\u0002\u0013\u0005!\u000b\u0003\u0005W\u0001\tE\t\u0015!\u0003T\u0011!9\u0006A!f\u0001\n\u0003\u0011\u0006\u0002\u0003-\u0001\u0005#\u0005\u000b\u0011B*\t\u0011e\u0003!Q3A\u0005\u0002\u0015C\u0001B\u0017\u0001\u0003\u0012\u0003\u0006IA\u0012\u0005\u00067\u0002!\t\u0001\u0018\u0005\bI\u0002\t\t\u0011\"\u0001f\u0011\u001dY\u0007!%A\u0005\u00021Dqa\u001e\u0001\u0012\u0002\u0013\u0005A\u000eC\u0004y\u0001E\u0005I\u0011A=\t\u000fm\u0004\u0011\u0013!C\u0001s\"9A\u0010AI\u0001\n\u0003a\u0007bB?\u0001\u0003\u0003%\tE \u0005\n\u0003\u001b\u0001\u0011\u0011!C\u0001\u0003\u001fA\u0011\"a\u0006\u0001\u0003\u0003%\t!!\u0007\t\u0013\u0005\u0015\u0002!!A\u0005B\u0005\u001d\u0002\"CA\u001b\u0001\u0005\u0005I\u0011AA\u001c\u0011%\t\t\u0005AA\u0001\n\u0003\n\u0019\u0005C\u0005\u0002H\u0001\t\t\u0011\"\u0011\u0002J!I\u00111\n\u0001\u0002\u0002\u0013\u0005\u0013Q\n\u0005\n\u0003\u001f\u0002\u0011\u0011!C!\u0003#:!\"!\u0016$\u0003\u0003E\t!JA,\r%\u00113%!A\t\u0002\u0015\nI\u0006\u0003\u0004\\9\u0011\u0005\u0011\u0011\u000f\u0005\n\u0003\u0017b\u0012\u0011!C#\u0003\u001bB\u0011\"a\u001d\u001d\u0003\u0003%\t)!\u001e\t\u0013\u0005\u0005E$!A\u0005\u0002\u0006\r\u0005\"CAK9\u0005\u0005I\u0011BAL\u0005E\u0011En\\2l)\u0006\u0014G.\u001a*po\u0012\u000bG/\u0019\u0006\u0003I\u0015\nqa\u001d;pe\u0006<WM\u0003\u0002'O\u0005\u0011Q/\u001b\u0006\u0003Q%\nQa\u001d9be.T!AK\u0016\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0013aA8sON!\u0001A\f\u001b8!\ty#'D\u00011\u0015\u0005\t\u0014!B:dC2\f\u0017BA\u001a1\u0005\u0019\te.\u001f*fMB\u0011q&N\u0005\u0003mA\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00029\u0003:\u0011\u0011h\u0010\b\u0003uyj\u0011a\u000f\u0006\u0003yu\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002c%\u0011\u0001\tM\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00115I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Aa\u0005I!\r\\8dW:\u000bW.Z\u000b\u0002\rB\u0011qi\u0013\b\u0003\u0011&\u0003\"A\u000f\u0019\n\u0005)\u0003\u0014A\u0002)sK\u0012,g-\u0003\u0002M\u001b\n11\u000b\u001e:j]\u001eT!A\u0013\u0019\u0002\u0015\tdwnY6OC6,\u0007%\u0001\u0007ti>\u0014\u0018mZ3MKZ,G.A\u0007ti>\u0014\u0018mZ3MKZ,G\u000eI\u0001\u000b[\u0016lwN]=Vg\u0016$W#A*\u0011\u0005=\"\u0016BA+1\u0005\u0011auN\\4\u0002\u00175,Wn\u001c:z+N,G\rI\u0001\tI&\u001c8.V:fI\u0006IA-[:l+N,G\rI\u0001\nKb,7-\u001e;peN\f!\"\u001a=fGV$xN]:!\u0003\u0019a\u0014N\\5u}Q1Ql\u00181bE\u000e\u0004\"A\u0018\u0001\u000e\u0003\rBQ\u0001R\u0006A\u0002\u0019CQaT\u0006A\u0002\u0019CQ!U\u0006A\u0002MCQaV\u0006A\u0002MCQ!W\u0006A\u0002\u0019\u000bAaY8qsR1QLZ4iS*Dq\u0001\u0012\u0007\u0011\u0002\u0003\u0007a\tC\u0004P\u0019A\u0005\t\u0019\u0001$\t\u000fEc\u0001\u0013!a\u0001'\"9q\u000b\u0004I\u0001\u0002\u0004\u0019\u0006bB-\r!\u0003\u0005\rAR\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005i'F\u0001$oW\u0005y\u0007C\u00019v\u001b\u0005\t(B\u0001:t\u0003%)hn\u00195fG.,GM\u0003\u0002ua\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Y\f(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u000b\u0002u*\u00121K\\\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A@\u0011\t\u0005\u0005\u00111B\u0007\u0003\u0003\u0007QA!!\u0002\u0002\b\u0005!A.\u00198h\u0015\t\tI!\u0001\u0003kCZ\f\u0017b\u0001'\u0002\u0004\u0005a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011\u0011\u0003\t\u0004_\u0005M\u0011bAA\u000ba\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u00111DA\u0011!\ry\u0013QD\u0005\u0004\u0003?\u0001$aA!os\"I\u00111\u0005\u000b\u0002\u0002\u0003\u0007\u0011\u0011C\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005%\u0002CBA\u0016\u0003c\tY\"\u0004\u0002\u0002.)\u0019\u0011q\u0006\u0019\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u00024\u00055\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u000f\u0002@A\u0019q&a\u000f\n\u0007\u0005u\u0002GA\u0004C_>dW-\u00198\t\u0013\u0005\rb#!AA\u0002\u0005m\u0011A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2a`A#\u0011%\t\u0019cFA\u0001\u0002\u0004\t\t\"\u0001\u0005iCND7i\u001c3f)\t\t\t\"\u0001\u0005u_N#(/\u001b8h)\u0005y\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002:\u0005M\u0003\"CA\u00125\u0005\u0005\t\u0019AA\u000e\u0003E\u0011En\\2l)\u0006\u0014G.\u001a*po\u0012\u000bG/\u0019\t\u0003=r\u0019R\u0001HA.\u0003O\u0002\"\"!\u0018\u0002d\u001935k\u0015$^\u001b\t\tyFC\u0002\u0002bA\nqA];oi&lW-\u0003\u0003\u0002f\u0005}#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8okA!\u0011\u0011NA8\u001b\t\tYG\u0003\u0003\u0002n\u0005\u001d\u0011AA5p\u0013\r\u0011\u00151\u000e\u000b\u0003\u0003/\nQ!\u00199qYf$2\"XA<\u0003s\nY(! \u0002\u0000!)Ai\ba\u0001\r\")qj\ba\u0001\r\")\u0011k\ba\u0001'\")qk\ba\u0001'\")\u0011l\ba\u0001\r\u00069QO\\1qa2LH\u0003BAC\u0003#\u0003RaLAD\u0003\u0017K1!!#1\u0005\u0019y\u0005\u000f^5p]BAq&!$G\rN\u001bf)C\u0002\u0002\u0010B\u0012a\u0001V;qY\u0016,\u0004\u0002CAJA\u0005\u0005\t\u0019A/\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u001aB!\u0011\u0011AAN\u0013\u0011\ti*a\u0001\u0003\r=\u0013'.Z2u\u0001"
)
public class BlockTableRowData implements Product, Serializable {
   private final String blockName;
   private final String storageLevel;
   private final long memoryUsed;
   private final long diskUsed;
   private final String executors;

   public static Option unapply(final BlockTableRowData x$0) {
      return BlockTableRowData$.MODULE$.unapply(x$0);
   }

   public static BlockTableRowData apply(final String blockName, final String storageLevel, final long memoryUsed, final long diskUsed, final String executors) {
      return BlockTableRowData$.MODULE$.apply(blockName, storageLevel, memoryUsed, diskUsed, executors);
   }

   public static Function1 tupled() {
      return BlockTableRowData$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return BlockTableRowData$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String blockName() {
      return this.blockName;
   }

   public String storageLevel() {
      return this.storageLevel;
   }

   public long memoryUsed() {
      return this.memoryUsed;
   }

   public long diskUsed() {
      return this.diskUsed;
   }

   public String executors() {
      return this.executors;
   }

   public BlockTableRowData copy(final String blockName, final String storageLevel, final long memoryUsed, final long diskUsed, final String executors) {
      return new BlockTableRowData(blockName, storageLevel, memoryUsed, diskUsed, executors);
   }

   public String copy$default$1() {
      return this.blockName();
   }

   public String copy$default$2() {
      return this.storageLevel();
   }

   public long copy$default$3() {
      return this.memoryUsed();
   }

   public long copy$default$4() {
      return this.diskUsed();
   }

   public String copy$default$5() {
      return this.executors();
   }

   public String productPrefix() {
      return "BlockTableRowData";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.blockName();
         }
         case 1 -> {
            return this.storageLevel();
         }
         case 2 -> {
            return BoxesRunTime.boxToLong(this.memoryUsed());
         }
         case 3 -> {
            return BoxesRunTime.boxToLong(this.diskUsed());
         }
         case 4 -> {
            return this.executors();
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
      return x$1 instanceof BlockTableRowData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "blockName";
         }
         case 1 -> {
            return "storageLevel";
         }
         case 2 -> {
            return "memoryUsed";
         }
         case 3 -> {
            return "diskUsed";
         }
         case 4 -> {
            return "executors";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.blockName()));
      var1 = Statics.mix(var1, Statics.anyHash(this.storageLevel()));
      var1 = Statics.mix(var1, Statics.longHash(this.memoryUsed()));
      var1 = Statics.mix(var1, Statics.longHash(this.diskUsed()));
      var1 = Statics.mix(var1, Statics.anyHash(this.executors()));
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof BlockTableRowData) {
               BlockTableRowData var4 = (BlockTableRowData)x$1;
               if (this.memoryUsed() == var4.memoryUsed() && this.diskUsed() == var4.diskUsed()) {
                  label64: {
                     String var10000 = this.blockName();
                     String var5 = var4.blockName();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label64;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label64;
                     }

                     var10000 = this.storageLevel();
                     String var6 = var4.storageLevel();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label64;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label64;
                     }

                     var10000 = this.executors();
                     String var7 = var4.executors();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label64;
                        }
                     } else if (!var10000.equals(var7)) {
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

   public BlockTableRowData(final String blockName, final String storageLevel, final long memoryUsed, final long diskUsed, final String executors) {
      this.blockName = blockName;
      this.storageLevel = storageLevel;
      this.memoryUsed = memoryUsed;
      this.diskUsed = diskUsed;
      this.executors = executors;
      Product.$init$(this);
   }
}
