package com.fasterxml.jackson.module.scala.introspect;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%f!\u0002\r\u001a\u0001f)\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u00115\u0003!\u0011#Q\u0001\nuBQ!\u0016\u0001\u0005\u0002YCqa\u0018\u0001\u0002\u0002\u0013\u0005\u0001\rC\u0004c\u0001E\u0005I\u0011A2\t\u000f9\u0004\u0011\u0011!C!_\"9\u0001\u0010AA\u0001\n\u0003I\bbB?\u0001\u0003\u0003%\tA \u0005\n\u0003\u0007\u0001\u0011\u0011!C!\u0003\u000bA\u0011\"a\u0005\u0001\u0003\u0003%\t!!\u0006\t\u0013\u0005}\u0001!!A\u0005B\u0005\u0005\u0002\"CA\u0013\u0001\u0005\u0005I\u0011IA\u0014\u0011%\tI\u0003AA\u0001\n\u0003\nY\u0003C\u0005\u0002.\u0001\t\t\u0011\"\u0011\u00020\u001dQ\u00111G\r\u0002\u0002#\u0005\u0011$!\u000e\u0007\u0013aI\u0012\u0011!E\u00013\u0005]\u0002BB+\u0011\t\u0003\tI\u0006C\u0005\u0002*A\t\t\u0011\"\u0012\u0002,!I\u00111\f\t\u0002\u0002\u0013\u0005\u0015Q\f\u0005\n\u0003W\u0002\u0012\u0013!C\u0001\u0003[B\u0011\"a\u001f\u0011\u0003\u0003%\t)! \t\u0013\u0005=\u0005#%A\u0005\u0002\u0005E\u0005\"CAP!\u0005\u0005I\u0011BAQ\u0005-\u0019E.Y:t\u0011>dG-\u001a:\u000b\u0005iY\u0012AC5oiJ|7\u000f]3di*\u0011A$H\u0001\u0006g\u000e\fG.\u0019\u0006\u0003=}\ta!\\8ek2,'B\u0001\u0011\"\u0003\u001dQ\u0017mY6t_:T!AI\u0012\u0002\u0013\u0019\f7\u000f^3sq6d'\"\u0001\u0013\u0002\u0007\r|Wn\u0005\u0003\u0001M-r\u0003CA\u0014*\u001b\u0005A#\"\u0001\u000f\n\u0005)B#AB!osJ+g\r\u0005\u0002(Y%\u0011Q\u0006\u000b\u0002\b!J|G-^2u!\ty\u0003H\u0004\u00021m9\u0011\u0011'N\u0007\u0002e)\u00111\u0007N\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tA$\u0003\u00028Q\u00059\u0001/Y2lC\u001e,\u0017BA\u001d;\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t9\u0004&\u0001\u0006wC2,Xm\u00117bgN,\u0012!\u0010\t\u0004Oy\u0002\u0015BA )\u0005\u0019y\u0005\u000f^5p]B\u0012\u0011i\u0013\t\u0004\u0005\u001aKeBA\"E!\t\t\u0004&\u0003\u0002FQ\u00051\u0001K]3eK\u001aL!a\u0012%\u0003\u000b\rc\u0017m]:\u000b\u0005\u0015C\u0003C\u0001&L\u0019\u0001!\u0011\u0002\u0014\u0002\u0002\u0002\u0003\u0005)\u0011\u0001(\u0003\u0007}#\u0013'A\u0006wC2,Xm\u00117bgN\u0004\u0013CA(S!\t9\u0003+\u0003\u0002RQ\t9aj\u001c;iS:<\u0007CA\u0014T\u0013\t!\u0006FA\u0002B]f\fa\u0001P5oSRtDCA,Z!\tA\u0006!D\u0001\u001a\u0011\u001dY4\u0001%AA\u0002i\u00032a\n \\a\taf\fE\u0002C\rv\u0003\"A\u00130\u0005\u00131K\u0016\u0011!A\u0001\u0006\u0003q\u0015\u0001B2paf$\"aV1\t\u000fm\"\u0001\u0013!a\u00015\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00013+\u0005u*7&\u00014\u0011\u0005\u001ddW\"\u00015\u000b\u0005%T\u0017!C;oG\",7m[3e\u0015\tY\u0007&\u0001\u0006b]:|G/\u0019;j_:L!!\u001c5\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002aB\u0011\u0011O^\u0007\u0002e*\u00111\u000f^\u0001\u0005Y\u0006twMC\u0001v\u0003\u0011Q\u0017M^1\n\u0005]\u0014(AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001{!\t930\u0003\u0002}Q\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011!k \u0005\t\u0003\u0003A\u0011\u0011!a\u0001u\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0002\u0011\u000b\u0005%\u0011q\u0002*\u000e\u0005\u0005-!bAA\u0007Q\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005E\u00111\u0002\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0018\u0005u\u0001cA\u0014\u0002\u001a%\u0019\u00111\u0004\u0015\u0003\u000f\t{w\u000e\\3b]\"A\u0011\u0011\u0001\u0006\u0002\u0002\u0003\u0007!+\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00019\u0002$!A\u0011\u0011A\u0006\u0002\u0002\u0003\u0007!0\u0001\u0005iCND7i\u001c3f)\u0005Q\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003A\fa!Z9vC2\u001cH\u0003BA\f\u0003cA\u0001\"!\u0001\u000f\u0003\u0003\u0005\rAU\u0001\f\u00072\f7o\u001d%pY\u0012,'\u000f\u0005\u0002Y!M)\u0001#!\u000f\u0002PA9\u00111HA!\u0003\u000b:VBAA\u001f\u0015\r\ty\u0004K\u0001\beVtG/[7f\u0013\u0011\t\u0019%!\u0010\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003(}\u0005\u001d\u0003\u0007BA%\u0003\u001b\u0002BA\u0011$\u0002LA\u0019!*!\u0014\u0005\u00131\u0003\u0012\u0011!A\u0001\u0006\u0003q\u0005\u0003BA)\u0003/j!!a\u0015\u000b\u0007\u0005UC/\u0001\u0002j_&\u0019\u0011(a\u0015\u0015\u0005\u0005U\u0012!B1qa2LHcA,\u0002`!A1h\u0005I\u0001\u0002\u0004\t\t\u0007\u0005\u0003(}\u0005\r\u0004\u0007BA3\u0003S\u0002BA\u0011$\u0002hA\u0019!*!\u001b\u0005\u00151\u000by&!A\u0001\u0002\u000b\u0005a*A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tyGK\u0002\u0002r\u0015\u0004Ba\n \u0002tA\"\u0011QOA=!\u0011\u0011e)a\u001e\u0011\u0007)\u000bI\bB\u0005M)\u0005\u0005\t\u0011!B\u0001\u001d\u00069QO\\1qa2LH\u0003BA@\u0003\u0017\u0003Ba\n \u0002\u0002B!qEPABa\u0011\t))!#\u0011\t\t3\u0015q\u0011\t\u0004\u0015\u0006%E!\u0003'\u0016\u0003\u0003\u0005\tQ!\u0001O\u0011!\ti)FA\u0001\u0002\u00049\u0016a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE*\"!a%+\u0007\u0005UU\r\u0005\u0003(}\u0005]\u0005\u0007BAM\u0003;\u0003BA\u0011$\u0002\u001cB\u0019!*!(\u0005\u001313\u0012\u0011!A\u0001\u0006\u0003q\u0015\u0001D<sSR,'+\u001a9mC\u000e,GCAAR!\r\t\u0018QU\u0005\u0004\u0003O\u0013(AB(cU\u0016\u001cG\u000f"
)
public class ClassHolder implements Product, Serializable {
   private final Option valueClass;

   public static Option $lessinit$greater$default$1() {
      return ClassHolder$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final ClassHolder x$0) {
      return ClassHolder$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$1() {
      return ClassHolder$.MODULE$.apply$default$1();
   }

   public static ClassHolder apply(final Option valueClass) {
      return ClassHolder$.MODULE$.apply(valueClass);
   }

   public static Function1 andThen(final Function1 g) {
      return ClassHolder$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ClassHolder$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Option valueClass() {
      return this.valueClass;
   }

   public ClassHolder copy(final Option valueClass) {
      return new ClassHolder(valueClass);
   }

   public Option copy$default$1() {
      return this.valueClass();
   }

   public String productPrefix() {
      return "ClassHolder";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.valueClass();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ClassHolder;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "valueClass";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof ClassHolder) {
               label40: {
                  ClassHolder var4 = (ClassHolder)x$1;
                  Option var10000 = this.valueClass();
                  Option var5 = var4.valueClass();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
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

   public ClassHolder(final Option valueClass) {
      this.valueClass = valueClass;
      Product.$init$(this);
   }
}
