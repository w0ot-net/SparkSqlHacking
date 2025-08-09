package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005y!A\u0001\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005F\u0001\tE\t\u0015!\u0003C\u0011\u00151\u0005\u0001\"\u0001H\u0011\u001dY\u0005!!A\u0005\u00021Cqa\u0014\u0001\u0012\u0002\u0013\u0005\u0001\u000bC\u0004\\\u0001E\u0005I\u0011\u0001/\t\u000fy\u0003\u0011\u0011!C!?\"9\u0001\u000eAA\u0001\n\u0003I\u0007bB7\u0001\u0003\u0003%\tA\u001c\u0005\bi\u0002\t\t\u0011\"\u0011v\u0011\u001da\b!!A\u0005\u0002uD\u0011\"!\u0002\u0001\u0003\u0003%\t%a\u0002\t\u0013\u0005-\u0001!!A\u0005B\u00055\u0001\"CA\b\u0001\u0005\u0005I\u0011IA\t\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)bB\u0005\u0002\u001ai\t\t\u0011#\u0001\u0002\u001c\u0019A\u0011DGA\u0001\u0012\u0003\ti\u0002\u0003\u0004G'\u0011\u0005\u0011Q\u0007\u0005\n\u0003\u001f\u0019\u0012\u0011!C#\u0003#A\u0011\"a\u000e\u0014\u0003\u0003%\t)!\u000f\t\u0013\u0005}2#!A\u0005\u0002\u0006\u0005\u0003\"CA*'\u0005\u0005I\u0011BA+\u0005-\u0011VMZ5oK\u0012$\u0016\u0010]3\u000b\u0005ma\u0012\u0001C:dC2\f7/[4\u000b\u0005uq\u0012AB:dC2\f\u0007O\u0003\u0002 A\u00051!n]8oiMT\u0011!I\u0001\u0004_J<7\u0001A\n\u0005\u0001\u0011Bc\u0006\u0005\u0002&M5\t!$\u0003\u0002(5\t!A+\u001f9f!\tIC&D\u0001+\u0015\u0005Y\u0013!B:dC2\f\u0017BA\u0017+\u0005\u001d\u0001&o\u001c3vGR\u0004\"aL\u001c\u000f\u0005A*dBA\u00195\u001b\u0005\u0011$BA\u001a#\u0003\u0019a$o\\8u}%\t1&\u0003\u00027U\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001d:\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t1$&\u0001\u0005dY\u0006\u001c8oU=n+\u0005a\u0004CA\u0013>\u0013\tq$D\u0001\u0004Ts6\u0014w\u000e\\\u0001\nG2\f7o]*z[\u0002\n\u0001\u0002^=qKJ+gm]\u000b\u0002\u0005B\u0019qf\u0011\u0013\n\u0005\u0011K$\u0001\u0002'jgR\f\u0011\u0002^=qKJ+gm\u001d\u0011\u0002\rqJg.\u001b;?)\rA\u0015J\u0013\t\u0003K\u0001AQAO\u0003A\u0002qBQ\u0001Q\u0003A\u0002\t\u000bAaY8qsR\u0019\u0001*\u0014(\t\u000fi2\u0001\u0013!a\u0001y!9\u0001I\u0002I\u0001\u0002\u0004\u0011\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002#*\u0012AHU\u0016\u0002'B\u0011A+W\u0007\u0002+*\u0011akV\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u0017\u0016\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002[+\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\tQL\u000b\u0002C%\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u0019\t\u0003C\u001al\u0011A\u0019\u0006\u0003G\u0012\fA\u0001\\1oO*\tQ-\u0001\u0003kCZ\f\u0017BA4c\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t!\u000e\u0005\u0002*W&\u0011AN\u000b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003_J\u0004\"!\u000b9\n\u0005ET#aA!os\"91oCA\u0001\u0002\u0004Q\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001w!\r9(p\\\u0007\u0002q*\u0011\u0011PK\u0001\u000bG>dG.Z2uS>t\u0017BA>y\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007y\f\u0019\u0001\u0005\u0002*\u007f&\u0019\u0011\u0011\u0001\u0016\u0003\u000f\t{w\u000e\\3b]\"91/DA\u0001\u0002\u0004y\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2\u0001YA\u0005\u0011\u001d\u0019h\"!AA\u0002)\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002U\u0006AAo\\*ue&tw\rF\u0001a\u0003\u0019)\u0017/^1mgR\u0019a0a\u0006\t\u000fM\f\u0012\u0011!a\u0001_\u0006Y!+\u001a4j]\u0016$G+\u001f9f!\t)3cE\u0003\u0014\u0003?\tY\u0003E\u0004\u0002\"\u0005\u001dBH\u0011%\u000e\u0005\u0005\r\"bAA\u0013U\u00059!/\u001e8uS6,\u0017\u0002BA\u0015\u0003G\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\ti#a\r\u000e\u0005\u0005=\"bAA\u0019I\u0006\u0011\u0011n\\\u0005\u0004q\u0005=BCAA\u000e\u0003\u0015\t\u0007\u000f\u001d7z)\u0015A\u00151HA\u001f\u0011\u0015Qd\u00031\u0001=\u0011\u0015\u0001e\u00031\u0001C\u0003\u001d)h.\u00199qYf$B!a\u0011\u0002PA)\u0011&!\u0012\u0002J%\u0019\u0011q\t\u0016\u0003\r=\u0003H/[8o!\u0015I\u00131\n\u001fC\u0013\r\tiE\u000b\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005Es#!AA\u0002!\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\u0006E\u0002b\u00033J1!a\u0017c\u0005\u0019y%M[3di\u0002"
)
public class RefinedType extends Type implements Product, Serializable {
   private final Symbol classSym;
   private final List typeRefs;

   public static Option unapply(final RefinedType x$0) {
      return RefinedType$.MODULE$.unapply(x$0);
   }

   public static RefinedType apply(final Symbol classSym, final List typeRefs) {
      return RefinedType$.MODULE$.apply(classSym, typeRefs);
   }

   public static Function1 tupled() {
      return RefinedType$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return RefinedType$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Symbol classSym() {
      return this.classSym;
   }

   public List typeRefs() {
      return this.typeRefs;
   }

   public RefinedType copy(final Symbol classSym, final List typeRefs) {
      return new RefinedType(classSym, typeRefs);
   }

   public Symbol copy$default$1() {
      return this.classSym();
   }

   public List copy$default$2() {
      return this.typeRefs();
   }

   public String productPrefix() {
      return "RefinedType";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.classSym();
            break;
         case 1:
            var10000 = this.typeRefs();
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
      return x$1 instanceof RefinedType;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "classSym";
            break;
         case 1:
            var10000 = "typeRefs";
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
            if (x$1 instanceof RefinedType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     RefinedType var4 = (RefinedType)x$1;
                     Symbol var10000 = this.classSym();
                     Symbol var5 = var4.classSym();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     List var7 = this.typeRefs();
                     List var6 = var4.typeRefs();
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

   public RefinedType(final Symbol classSym, final List typeRefs) {
      this.classSym = classSym;
      this.typeRefs = typeRefs;
      Product.$init$(this);
   }
}
