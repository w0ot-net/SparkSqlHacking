package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005I!AQ\b\u0001BK\u0002\u0013\u0005a\b\u0003\u0005F\u0001\tE\t\u0015!\u0003@\u0011\u00151\u0005\u0001\"\u0001H\u0011\u001dY\u0005!!A\u0005\u00021Cqa\u0014\u0001\u0012\u0002\u0013\u0005\u0001\u000bC\u0004\\\u0001E\u0005I\u0011\u0001/\t\u000fy\u0003\u0011\u0011!C!?\"9\u0001\u000eAA\u0001\n\u0003I\u0007bB7\u0001\u0003\u0003%\tA\u001c\u0005\bi\u0002\t\t\u0011\"\u0011v\u0011\u001da\b!!A\u0005\u0002uD\u0011\"!\u0002\u0001\u0003\u0003%\t%a\u0002\t\u0013\u0005-\u0001!!A\u0005B\u00055\u0001\"CA\b\u0001\u0005\u0005I\u0011IA\t\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)bB\u0005\u0002\u001ai\t\t\u0011#\u0001\u0002\u001c\u0019A\u0011DGA\u0001\u0012\u0003\ti\u0002\u0003\u0004G'\u0011\u0005\u0011Q\u0007\u0005\n\u0003\u001f\u0019\u0012\u0011!C#\u0003#A\u0011\"a\u000e\u0014\u0003\u0003%\t)!\u000f\t\u0013\u0005}2#!A\u0005\u0002\u0006\u0005\u0003\"CA*'\u0005\u0005I\u0011BA+\u0005)iU\r\u001e5pIRK\b/\u001a\u0006\u00037q\t\u0001b]2bY\u0006\u001c\u0018n\u001a\u0006\u0003;y\taa]2bY\u0006\u0004(BA\u0010!\u0003\u0019Q7o\u001c85g*\t\u0011%A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001I!r\u0003CA\u0013'\u001b\u0005Q\u0012BA\u0014\u001b\u0005\u0011!\u0016\u0010]3\u0011\u0005%bS\"\u0001\u0016\u000b\u0003-\nQa]2bY\u0006L!!\f\u0016\u0003\u000fA\u0013x\u000eZ;diB\u0011qf\u000e\b\u0003aUr!!\r\u001b\u000e\u0003IR!a\r\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005Y\u0013B\u0001\u001c+\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001O\u001d\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005YR\u0013A\u0003:fgVdG\u000fV=qKV\tA%A\u0006sKN,H\u000e\u001e+za\u0016\u0004\u0013\u0001\u00049be\u0006l7+_7c_2\u001cX#A \u0011\u0007=\u0002%)\u0003\u0002Bs\t\u00191+Z9\u0011\u0005\u0015\u001a\u0015B\u0001#\u001b\u0005\u0019\u0019\u00160\u001c2pY\u0006i\u0001/\u0019:b[NKXNY8mg\u0002\na\u0001P5oSRtDc\u0001%J\u0015B\u0011Q\u0005\u0001\u0005\u0006u\u0015\u0001\r\u0001\n\u0005\u0006{\u0015\u0001\raP\u0001\u0005G>\u0004\u0018\u0010F\u0002I\u001b:CqA\u000f\u0004\u0011\u0002\u0003\u0007A\u0005C\u0004>\rA\u0005\t\u0019A \u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t\u0011K\u000b\u0002%%.\n1\u000b\u0005\u0002U36\tQK\u0003\u0002W/\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u00031*\n!\"\u00198o_R\fG/[8o\u0013\tQVKA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001^U\ty$+A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002AB\u0011\u0011MZ\u0007\u0002E*\u00111\rZ\u0001\u0005Y\u0006twMC\u0001f\u0003\u0011Q\u0017M^1\n\u0005\u001d\u0014'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001k!\tI3.\u0003\u0002mU\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011qN\u001d\t\u0003SAL!!\u001d\u0016\u0003\u0007\u0005s\u0017\u0010C\u0004t\u0017\u0005\u0005\t\u0019\u00016\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u00051\bcA<{_6\t\u0001P\u0003\u0002zU\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005mD(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$2A`A\u0002!\tIs0C\u0002\u0002\u0002)\u0012qAQ8pY\u0016\fg\u000eC\u0004t\u001b\u0005\u0005\t\u0019A8\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004A\u0006%\u0001bB:\u000f\u0003\u0003\u0005\rA[\u0001\tQ\u0006\u001c\bnQ8eKR\t!.\u0001\u0005u_N#(/\u001b8h)\u0005\u0001\u0017AB3rk\u0006d7\u000fF\u0002\u007f\u0003/Aqa]\t\u0002\u0002\u0003\u0007q.\u0001\u0006NKRDw\u000e\u001a+za\u0016\u0004\"!J\n\u0014\u000bM\ty\"a\u000b\u0011\u000f\u0005\u0005\u0012q\u0005\u0013@\u00116\u0011\u00111\u0005\u0006\u0004\u0003KQ\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003S\t\u0019CA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!!\f\u000245\u0011\u0011q\u0006\u0006\u0004\u0003c!\u0017AA5p\u0013\rA\u0014q\u0006\u000b\u0003\u00037\tQ!\u00199qYf$R\u0001SA\u001e\u0003{AQA\u000f\fA\u0002\u0011BQ!\u0010\fA\u0002}\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002D\u0005=\u0003#B\u0015\u0002F\u0005%\u0013bAA$U\t1q\n\u001d;j_:\u0004R!KA&I}J1!!\u0014+\u0005\u0019!V\u000f\u001d7fe!A\u0011\u0011K\f\u0002\u0002\u0003\u0007\u0001*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0016\u0011\u0007\u0005\fI&C\u0002\u0002\\\t\u0014aa\u00142kK\u000e$\b"
)
public class MethodType extends Type implements Product, Serializable {
   private final Type resultType;
   private final Seq paramSymbols;

   public static Option unapply(final MethodType x$0) {
      return MethodType$.MODULE$.unapply(x$0);
   }

   public static MethodType apply(final Type resultType, final Seq paramSymbols) {
      return MethodType$.MODULE$.apply(resultType, paramSymbols);
   }

   public static Function1 tupled() {
      return MethodType$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return MethodType$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Type resultType() {
      return this.resultType;
   }

   public Seq paramSymbols() {
      return this.paramSymbols;
   }

   public MethodType copy(final Type resultType, final Seq paramSymbols) {
      return new MethodType(resultType, paramSymbols);
   }

   public Type copy$default$1() {
      return this.resultType();
   }

   public Seq copy$default$2() {
      return this.paramSymbols();
   }

   public String productPrefix() {
      return "MethodType";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.resultType();
            break;
         case 1:
            var10000 = this.paramSymbols();
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
      return x$1 instanceof MethodType;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "resultType";
            break;
         case 1:
            var10000 = "paramSymbols";
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
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof MethodType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     MethodType var4 = (MethodType)x$1;
                     Type var10000 = this.resultType();
                     Type var5 = var4.resultType();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     Seq var7 = this.paramSymbols();
                     Seq var6 = var4.paramSymbols();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var7.equals(var6)) {
                        break label54;
                     }

                     if (var4.canEqual(this)) {
                        var9 = true;
                        break label45;
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label63;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public MethodType(final Type resultType, final Seq paramSymbols) {
      this.resultType = resultType;
      this.paramSymbols = paramSymbols;
      Product.$init$(this);
   }
}
