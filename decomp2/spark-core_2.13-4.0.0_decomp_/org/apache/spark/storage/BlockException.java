package org.apache.spark.storage;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd!\u0002\r\u001a\u0001n\t\u0003\u0002\u0003\u001d\u0001\u0005+\u0007I\u0011A\u001d\t\u0011y\u0002!\u0011#Q\u0001\niB\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005\u0003\")!\n\u0001C\u0001\u0017\"9q\nAA\u0001\n\u0003\u0001\u0006bB*\u0001#\u0003%\t\u0001\u0016\u0005\b?\u0002\t\n\u0011\"\u0001a\u0011\u001d\u0011\u0007!!A\u0005B\rDqa\u001b\u0001\u0002\u0002\u0013\u0005A\u000eC\u0004q\u0001\u0005\u0005I\u0011A9\t\u000f]\u0004\u0011\u0011!C!q\"Aq\u0010AA\u0001\n\u0003\t\t\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e!I\u0011\u0011\u0003\u0001\u0002\u0002\u0013\u0005\u00131\u0003\u0005\n\u0003+\u0001\u0011\u0011!C!\u0003/9!\"a\u0007\u001a\u0003\u0003E\taGA\u000f\r%A\u0012$!A\t\u0002m\ty\u0002\u0003\u0004K%\u0011\u0005\u0011q\u0007\u0005\n\u0003s\u0011\u0012\u0011!C#\u0003wA\u0011\"!\u0010\u0013\u0003\u0003%\t)a\u0010\t\u0013\u0005\u0015##!A\u0005\u0002\u0006\u001d\u0003\"CA-%\u0005\u0005I\u0011BA.\u00059\u0011En\\2l\u000bb\u001cW\r\u001d;j_:T!AG\u000e\u0002\u000fM$xN]1hK*\u0011A$H\u0001\u0006gB\f'o\u001b\u0006\u0003=}\ta!\u00199bG\",'\"\u0001\u0011\u0002\u0007=\u0014xm\u0005\u0003\u0001EE*\u0004CA\u0012/\u001d\t!3F\u0004\u0002&S5\taE\u0003\u0002(Q\u00051AH]8piz\u001a\u0001!C\u0001+\u0003\u0015\u00198-\u00197b\u0013\taS&A\u0004qC\u000e\\\u0017mZ3\u000b\u0003)J!a\f\u0019\u0003\u0013\u0015C8-\u001a9uS>t'B\u0001\u0017.!\t\u00114'D\u0001.\u0013\t!TFA\u0004Qe>$Wo\u0019;\u0011\u0005\r2\u0014BA\u001c1\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u001d\u0011Gn\\2l\u0013\u0012,\u0012A\u000f\t\u0003wqj\u0011!G\u0005\u0003{e\u0011qA\u00117pG.LE-\u0001\u0005cY>\u001c7.\u00133!\u0003\u001diWm]:bO\u0016,\u0012!\u0011\t\u0003\u0005\u001as!a\u0011#\u0011\u0005\u0015j\u0013BA#.\u0003\u0019\u0001&/\u001a3fM&\u0011q\t\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0015k\u0013\u0001C7fgN\fw-\u001a\u0011\u0002\rqJg.\u001b;?)\raUJ\u0014\t\u0003w\u0001AQ\u0001O\u0003A\u0002iBQaP\u0003A\u0002\u0005\u000bAaY8qsR\u0019A*\u0015*\t\u000fa2\u0001\u0013!a\u0001u!9qH\u0002I\u0001\u0002\u0004\t\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002+*\u0012!HV\u0016\u0002/B\u0011\u0001,X\u0007\u00023*\u0011!lW\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001X\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002_3\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\t\u0011M\u000b\u0002B-\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u001a\t\u0003K*l\u0011A\u001a\u0006\u0003O\"\fA\u0001\\1oO*\t\u0011.\u0001\u0003kCZ\f\u0017BA$g\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005i\u0007C\u0001\u001ao\u0013\tyWFA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002skB\u0011!g]\u0005\u0003i6\u00121!\u00118z\u0011\u001d18\"!AA\u00025\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A=\u0011\u0007il(/D\u0001|\u0015\taX&\u0001\u0006d_2dWm\u0019;j_:L!A`>\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0007\tI\u0001E\u00023\u0003\u000bI1!a\u0002.\u0005\u001d\u0011un\u001c7fC:DqA^\u0007\u0002\u0002\u0003\u0007!/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00013\u0002\u0010!9aODA\u0001\u0002\u0004i\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u00035\fa!Z9vC2\u001cH\u0003BA\u0002\u00033AqA\u001e\t\u0002\u0002\u0003\u0007!/\u0001\bCY>\u001c7.\u0012=dKB$\u0018n\u001c8\u0011\u0005m\u00122#\u0002\n\u0002\"\u00055\u0002cBA\u0012\u0003SQ\u0014\tT\u0007\u0003\u0003KQ1!a\n.\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u000b\u0002&\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005=\u0012QG\u0007\u0003\u0003cQ1!a\ri\u0003\tIw.C\u00028\u0003c!\"!!\b\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001Z\u0001\u0006CB\u0004H.\u001f\u000b\u0006\u0019\u0006\u0005\u00131\t\u0005\u0006qU\u0001\rA\u000f\u0005\u0006\u007fU\u0001\r!Q\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tI%!\u0016\u0011\u000bI\nY%a\u0014\n\u0007\u00055SF\u0001\u0004PaRLwN\u001c\t\u0006e\u0005E#(Q\u0005\u0004\u0003'j#A\u0002+va2,'\u0007\u0003\u0005\u0002XY\t\t\u00111\u0001M\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003;\u00022!ZA0\u0013\r\t\tG\u001a\u0002\u0007\u001f\nTWm\u0019;"
)
public class BlockException extends Exception implements Product {
   private final BlockId blockId;
   private final String message;

   public static Option unapply(final BlockException x$0) {
      return BlockException$.MODULE$.unapply(x$0);
   }

   public static BlockException apply(final BlockId blockId, final String message) {
      return BlockException$.MODULE$.apply(blockId, message);
   }

   public static Function1 tupled() {
      return BlockException$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return BlockException$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public BlockId blockId() {
      return this.blockId;
   }

   public String message() {
      return this.message;
   }

   public BlockException copy(final BlockId blockId, final String message) {
      return new BlockException(blockId, message);
   }

   public BlockId copy$default$1() {
      return this.blockId();
   }

   public String copy$default$2() {
      return this.message();
   }

   public String productPrefix() {
      return "BlockException";
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
            return this.message();
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
      return x$1 instanceof BlockException;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "blockId";
         }
         case 1 -> {
            return "message";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof BlockException) {
               label48: {
                  BlockException var4 = (BlockException)x$1;
                  BlockId var10000 = this.blockId();
                  BlockId var5 = var4.blockId();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  String var7 = this.message();
                  String var6 = var4.message();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
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

   public BlockException(final BlockId blockId, final String message) {
      super(message);
      this.blockId = blockId;
      this.message = message;
      Product.$init$(this);
   }
}
