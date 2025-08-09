package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb\u0001\u0002\f\u0018\u0001\u0002B\u0001b\u000e\u0001\u0003\u0016\u0004%\t\u0001\u000f\u0005\ts\u0001\u0011\t\u0012)A\u0005C!)!\b\u0001C\u0001w!9a\bAA\u0001\n\u0003y\u0004bB!\u0001#\u0003%\tA\u0011\u0005\b\u001b\u0002\t\t\u0011\"\u0011O\u0011\u001d9\u0006!!A\u0005\u0002aCq\u0001\u0018\u0001\u0002\u0002\u0013\u0005Q\fC\u0004d\u0001\u0005\u0005I\u0011\t3\t\u000f-\u0004\u0011\u0011!C\u0001Y\"9\u0011\u000fAA\u0001\n\u0003\u0012\bb\u0002;\u0001\u0003\u0003%\t%\u001e\u0005\bm\u0002\t\t\u0011\"\u0011x\u0011\u001dA\b!!A\u0005Be<qa_\f\u0002\u0002#\u0005APB\u0004\u0017/\u0005\u0005\t\u0012A?\t\ri\u0002B\u0011AA\n\u0011\u001d1\b#!A\u0005F]D\u0011\"!\u0006\u0011\u0003\u0003%\t)a\u0006\t\u0013\u0005m\u0001#!A\u0005\u0002\u0006u\u0001\"CA\u0015!\u0005\u0005I\u0011BA\u0016\u0005EqU\u000f\u001c7beflU\r\u001e5pIRK\b/\u001a\u0006\u00031e\t\u0001b]2bY\u0006\u001c\u0018n\u001a\u0006\u00035m\taa]2bY\u0006\u0004(B\u0001\u000f\u001e\u0003\u0019Q7o\u001c85g*\ta$A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001C\u0015Z\u0003C\u0001\u0012$\u001b\u00059\u0012B\u0001\u0013\u0018\u0005\u0011!\u0016\u0010]3\u0011\u0005\u0019JS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\u000fA\u0013x\u000eZ;diB\u0011A\u0006\u000e\b\u0003[Ir!AL\u0019\u000e\u0003=R!\u0001M\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0013BA\u001a(\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000e\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005M:\u0013A\u0003:fgVdG\u000fV=qKV\t\u0011%A\u0006sKN,H\u000e\u001e+za\u0016\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002={A\u0011!\u0005\u0001\u0005\u0006o\r\u0001\r!I\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002=\u0001\"9q\u0007\u0002I\u0001\u0002\u0004\t\u0013AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0007*\u0012\u0011\u0005R\u0016\u0002\u000bB\u0011aiS\u0007\u0002\u000f*\u0011\u0001*S\u0001\nk:\u001c\u0007.Z2lK\u0012T!AS\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002M\u000f\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005y\u0005C\u0001)V\u001b\u0005\t&B\u0001*T\u0003\u0011a\u0017M\\4\u000b\u0003Q\u000bAA[1wC&\u0011a+\u0015\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003e\u0003\"A\n.\n\u0005m;#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00010b!\t1s,\u0003\u0002aO\t\u0019\u0011I\\=\t\u000f\tD\u0011\u0011!a\u00013\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001a\t\u0004M&tV\"A4\u000b\u0005!<\u0013AC2pY2,7\r^5p]&\u0011!n\u001a\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002naB\u0011aE\\\u0005\u0003_\u001e\u0012qAQ8pY\u0016\fg\u000eC\u0004c\u0015\u0005\u0005\t\u0019\u00010\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003\u001fNDqAY\u0006\u0002\u0002\u0003\u0007\u0011,\u0001\u0005iCND7i\u001c3f)\u0005I\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003=\u000ba!Z9vC2\u001cHCA7{\u0011\u001d\u0011g\"!AA\u0002y\u000b\u0011CT;mY\u0006\u0014\u00180T3uQ>$G+\u001f9f!\t\u0011\u0003c\u0005\u0003\u0011}\u0006%\u0001#B@\u0002\u0006\u0005bTBAA\u0001\u0015\r\t\u0019aJ\u0001\beVtG/[7f\u0013\u0011\t9!!\u0001\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\f\u0005EQBAA\u0007\u0015\r\tyaU\u0001\u0003S>L1!NA\u0007)\u0005a\u0018!B1qa2LHc\u0001\u001f\u0002\u001a!)qg\u0005a\u0001C\u00059QO\\1qa2LH\u0003BA\u0010\u0003K\u0001BAJA\u0011C%\u0019\u00111E\u0014\u0003\r=\u0003H/[8o\u0011!\t9\u0003FA\u0001\u0002\u0004a\u0014a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u0006\t\u0004!\u0006=\u0012bAA\u0019#\n1qJ\u00196fGR\u0004"
)
public class NullaryMethodType extends Type implements Product, Serializable {
   private final Type resultType;

   public static Option unapply(final NullaryMethodType x$0) {
      return NullaryMethodType$.MODULE$.unapply(x$0);
   }

   public static NullaryMethodType apply(final Type resultType) {
      return NullaryMethodType$.MODULE$.apply(resultType);
   }

   public static Function1 andThen(final Function1 g) {
      return NullaryMethodType$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return NullaryMethodType$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Type resultType() {
      return this.resultType;
   }

   public NullaryMethodType copy(final Type resultType) {
      return new NullaryMethodType(resultType);
   }

   public Type copy$default$1() {
      return this.resultType();
   }

   public String productPrefix() {
      return "NullaryMethodType";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.resultType();
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
      return x$1 instanceof NullaryMethodType;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "resultType";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof NullaryMethodType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     NullaryMethodType var4 = (NullaryMethodType)x$1;
                     Type var10000 = this.resultType();
                     Type var5 = var4.resultType();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public NullaryMethodType(final Type resultType) {
      this.resultType = resultType;
      Product.$init$(this);
   }
}
