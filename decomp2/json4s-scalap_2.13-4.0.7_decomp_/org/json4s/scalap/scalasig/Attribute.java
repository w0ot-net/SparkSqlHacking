package org.json4s.scalap.scalasig;

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
   bytes = "\u0006\u0005\u0005Uc\u0001B\r\u001b\u0001\u000eB\u0001\"\u000f\u0001\u0003\u0016\u0004%\tA\u000f\u0005\t}\u0001\u0011\t\u0012)A\u0005w!Aq\b\u0001BK\u0002\u0013\u0005\u0001\t\u0003\u0005F\u0001\tE\t\u0015!\u0003B\u0011\u00151\u0005\u0001\"\u0001H\u0011\u001dY\u0005!!A\u0005\u00021Cqa\u0014\u0001\u0012\u0002\u0013\u0005\u0001\u000bC\u0004\\\u0001E\u0005I\u0011\u0001/\t\u000fy\u0003\u0011\u0011!C!?\"9\u0001\u000eAA\u0001\n\u0003Q\u0004bB5\u0001\u0003\u0003%\tA\u001b\u0005\ba\u0002\t\t\u0011\"\u0011r\u0011\u001dA\b!!A\u0005\u0002eDqA \u0001\u0002\u0002\u0013\u0005s\u0010C\u0005\u0002\u0004\u0001\t\t\u0011\"\u0011\u0002\u0006!I\u0011q\u0001\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0002\u0005\n\u0003\u0017\u0001\u0011\u0011!C!\u0003\u001b9\u0011\"!\u0005\u001b\u0003\u0003E\t!a\u0005\u0007\u0011eQ\u0012\u0011!E\u0001\u0003+AaAR\n\u0005\u0002\u00055\u0002\"CA\u0004'\u0005\u0005IQIA\u0005\u0011%\tycEA\u0001\n\u0003\u000b\t\u0004C\u0005\u00028M\t\t\u0011\"!\u0002:!I\u00111J\n\u0002\u0002\u0013%\u0011Q\n\u0002\n\u0003R$(/\u001b2vi\u0016T!a\u0007\u000f\u0002\u0011M\u001c\u0017\r\\1tS\u001eT!!\b\u0010\u0002\rM\u001c\u0017\r\\1q\u0015\ty\u0002%\u0001\u0004kg>tGg\u001d\u0006\u0002C\u0005\u0019qN]4\u0004\u0001M!\u0001\u0001\n\u0016.!\t)\u0003&D\u0001'\u0015\u00059\u0013!B:dC2\f\u0017BA\u0015'\u0005\u0019\te.\u001f*fMB\u0011QeK\u0005\u0003Y\u0019\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002/m9\u0011q\u0006\u000e\b\u0003aMj\u0011!\r\u0006\u0003e\t\na\u0001\u0010:p_Rt\u0014\"A\u0014\n\u0005U2\u0013a\u00029bG.\fw-Z\u0005\u0003oa\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!\u000e\u0014\u0002\u00139\fW.Z%oI\u0016DX#A\u001e\u0011\u0005\u0015b\u0014BA\u001f'\u0005\rIe\u000e^\u0001\u000b]\u0006lW-\u00138eKb\u0004\u0013\u0001\u00032zi\u0016\u001cu\u000eZ3\u0016\u0003\u0005\u0003\"AQ\"\u000e\u0003iI!\u0001\u0012\u000e\u0003\u0011\tKH/Z\"pI\u0016\f\u0011BY=uK\u000e{G-\u001a\u0011\u0002\rqJg.\u001b;?)\rA\u0015J\u0013\t\u0003\u0005\u0002AQ!O\u0003A\u0002mBQaP\u0003A\u0002\u0005\u000bAaY8qsR\u0019\u0001*\u0014(\t\u000fe2\u0001\u0013!a\u0001w!9qH\u0002I\u0001\u0002\u0004\t\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002#*\u00121HU\u0016\u0002'B\u0011A+W\u0007\u0002+*\u0011akV\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u0017\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002[+\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\tQL\u000b\u0002B%\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u0019\t\u0003C\u001al\u0011A\u0019\u0006\u0003G\u0012\fA\u0001\\1oO*\tQ-\u0001\u0003kCZ\f\u0017BA4c\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA6o!\t)C.\u0003\u0002nM\t\u0019\u0011I\\=\t\u000f=\\\u0011\u0011!a\u0001w\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012A\u001d\t\u0004gZ\\W\"\u0001;\u000b\u0005U4\u0013AC2pY2,7\r^5p]&\u0011q\u000f\u001e\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002{{B\u0011Qe_\u0005\u0003y\u001a\u0012qAQ8pY\u0016\fg\u000eC\u0004p\u001b\u0005\u0005\t\u0019A6\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004A\u0006\u0005\u0001bB8\u000f\u0003\u0003\u0005\raO\u0001\tQ\u0006\u001c\bnQ8eKR\t1(\u0001\u0005u_N#(/\u001b8h)\u0005\u0001\u0017AB3rk\u0006d7\u000fF\u0002{\u0003\u001fAqa\\\t\u0002\u0002\u0003\u00071.A\u0005BiR\u0014\u0018NY;uKB\u0011!iE\n\u0006'\u0005]\u00111\u0005\t\b\u00033\tybO!I\u001b\t\tYBC\u0002\u0002\u001e\u0019\nqA];oi&lW-\u0003\u0003\u0002\"\u0005m!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011QEA\u0016\u001b\t\t9CC\u0002\u0002*\u0011\f!![8\n\u0007]\n9\u0003\u0006\u0002\u0002\u0014\u0005)\u0011\r\u001d9msR)\u0001*a\r\u00026!)\u0011H\u0006a\u0001w!)qH\u0006a\u0001\u0003\u00069QO\\1qa2LH\u0003BA\u001e\u0003\u000f\u0002R!JA\u001f\u0003\u0003J1!a\u0010'\u0005\u0019y\u0005\u000f^5p]B)Q%a\u0011<\u0003&\u0019\u0011Q\t\u0014\u0003\rQ+\b\u000f\\33\u0011!\tIeFA\u0001\u0002\u0004A\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\n\t\u0004C\u0006E\u0013bAA*E\n1qJ\u00196fGR\u0004"
)
public class Attribute implements Product, Serializable {
   private final int nameIndex;
   private final ByteCode byteCode;

   public static Option unapply(final Attribute x$0) {
      return Attribute$.MODULE$.unapply(x$0);
   }

   public static Attribute apply(final int nameIndex, final ByteCode byteCode) {
      return Attribute$.MODULE$.apply(nameIndex, byteCode);
   }

   public static Function1 tupled() {
      return Attribute$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Attribute$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int nameIndex() {
      return this.nameIndex;
   }

   public ByteCode byteCode() {
      return this.byteCode;
   }

   public Attribute copy(final int nameIndex, final ByteCode byteCode) {
      return new Attribute(nameIndex, byteCode);
   }

   public int copy$default$1() {
      return this.nameIndex();
   }

   public ByteCode copy$default$2() {
      return this.byteCode();
   }

   public String productPrefix() {
      return "Attribute";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.nameIndex());
            break;
         case 1:
            var10000 = this.byteCode();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Attribute;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "nameIndex";
            break;
         case 1:
            var10000 = "byteCode";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.nameIndex());
      var1 = Statics.mix(var1, Statics.anyHash(this.byteCode()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label55: {
            boolean var2;
            if (x$1 instanceof Attribute) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label38: {
                  Attribute var4 = (Attribute)x$1;
                  if (this.nameIndex() == var4.nameIndex()) {
                     label36: {
                        ByteCode var10000 = this.byteCode();
                        ByteCode var5 = var4.byteCode();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label36;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label36;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label38;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label55;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public Attribute(final int nameIndex, final ByteCode byteCode) {
      this.nameIndex = nameIndex;
      this.byteCode = byteCode;
      Product.$init$(this);
   }
}
