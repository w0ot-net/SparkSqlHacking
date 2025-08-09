package org.apache.spark.sql.internal.types;

import java.io.Serializable;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%d\u0001B\r\u001b\u0001\u001eB\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005Q!)!\t\u0001C\u0001\u0007\"1q\t\u0001C!=!Ca\u0001\u0014\u0001\u0005Byi\u0005BB*\u0001\t\u0003\u0002C\u000bC\u0004^\u0001\u0005\u0005I\u0011\u00010\t\u000f\u0001\u0004\u0011\u0013!C\u0001C\"9A\u000eAA\u0001\n\u0003j\u0007bB;\u0001\u0003\u0003%\tA\u001e\u0005\bu\u0002\t\t\u0011\"\u0001|\u0011%\t\u0019\u0001AA\u0001\n\u0003\n)\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0001\u0002\u0016!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00131\u0004\u0005\n\u0003?\u0001\u0011\u0011!C!\u0003CA\u0011\"a\t\u0001\u0003\u0003%\t%!\n\t\u0013\u0005\u001d\u0002!!A\u0005B\u0005%r!CA\u00175\u0005\u0005\t\u0012AA\u0018\r!I\"$!A\t\u0002\u0005E\u0002B\u0002\"\u0014\t\u0003\tI\u0005C\u0005\u0002$M\t\t\u0011\"\u0012\u0002&!I\u00111J\n\u0002\u0002\u0013\u0005\u0015Q\n\u0005\n\u0003#\u001a\u0012\u0011!CA\u0003'B\u0011\"a\u0018\u0014\u0003\u0003%I!!\u0019\u0003#\u0005\u00137\u000f\u001e:bGR\f%O]1z)f\u0004XM\u0003\u0002\u001c9\u0005)A/\u001f9fg*\u0011QDH\u0001\tS:$XM\u001d8bY*\u0011q\u0004I\u0001\u0004gFd'BA\u0011#\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0019C%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002K\u0005\u0019qN]4\u0004\u0001M!\u0001\u0001K\u00174!\tI3&D\u0001+\u0015\tYb$\u0003\u0002-U\t\u0001\u0012IY:ue\u0006\u001cG\u000fR1uCRK\b/\u001a\t\u0003]Ej\u0011a\f\u0006\u0002a\u0005)1oY1mC&\u0011!g\f\u0002\b!J|G-^2u!\t!DH\u0004\u00026u9\u0011a'O\u0007\u0002o)\u0011\u0001HJ\u0001\u0007yI|w\u000e\u001e \n\u0003AJ!aO\u0018\u0002\u000fA\f7m[1hK&\u0011QH\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003w=\n1\"\u001a7f[\u0016tG\u000fV=qKV\t\u0001&\u0001\u0007fY\u0016lWM\u001c;UsB,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\t\u001a\u0003\"!\u0012\u0001\u000e\u0003iAQaP\u0002A\u0002!\n1\u0003Z3gCVdGoQ8oGJ,G/\u001a+za\u0016,\u0012!\u0013\t\u0003S)K!a\u0013\u0016\u0003\u0011\u0011\u000bG/\u0019+za\u0016\f1\"Y2dKB$8\u000fV=qKR\u0011a*\u0015\t\u0003]=K!\u0001U\u0018\u0003\u000f\t{w\u000e\\3b]\")!+\u0002a\u0001\u0013\u0006)q\u000e\u001e5fe\u0006a1/[7qY\u0016\u001cFO]5oOV\tQ\u000b\u0005\u0002W5:\u0011q\u000b\u0017\t\u0003m=J!!W\u0018\u0002\rA\u0013X\rZ3g\u0013\tYFL\u0001\u0004TiJLgn\u001a\u0006\u00033>\nAaY8qsR\u0011Ai\u0018\u0005\b\u007f\u001d\u0001\n\u00111\u0001)\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u0019\u0016\u0003Q\r\\\u0013\u0001\u001a\t\u0003K*l\u0011A\u001a\u0006\u0003O\"\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005%|\u0013AC1o]>$\u0018\r^5p]&\u00111N\u001a\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001o!\tyG/D\u0001q\u0015\t\t(/\u0001\u0003mC:<'\"A:\u0002\t)\fg/Y\u0005\u00037B\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012a\u001e\t\u0003]aL!!_\u0018\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005q|\bC\u0001\u0018~\u0013\tqxFA\u0002B]fD\u0001\"!\u0001\f\u0003\u0003\u0005\ra^\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\u001d\u0001#BA\u0005\u0003\u001faXBAA\u0006\u0015\r\tiaL\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\t\u0003\u0017\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019a*a\u0006\t\u0011\u0005\u0005Q\"!AA\u0002q\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019a.!\b\t\u0011\u0005\u0005a\"!AA\u0002]\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002o\u0006AAo\\*ue&tw\rF\u0001o\u0003\u0019)\u0017/^1mgR\u0019a*a\u000b\t\u0011\u0005\u0005\u0011#!AA\u0002q\f\u0011#\u00112tiJ\f7\r^!se\u0006LH+\u001f9f!\t)5cE\u0003\u0014\u0003g\ty\u0004\u0005\u0004\u00026\u0005m\u0002\u0006R\u0007\u0003\u0003oQ1!!\u000f0\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u0010\u00028\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005\u0005\u0013qI\u0007\u0003\u0003\u0007R1!!\u0012s\u0003\tIw.C\u0002>\u0003\u0007\"\"!a\f\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u0011\u000by\u0005C\u0003@-\u0001\u0007\u0001&A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005U\u00131\f\t\u0005]\u0005]\u0003&C\u0002\u0002Z=\u0012aa\u00149uS>t\u0007\u0002CA//\u0005\u0005\t\u0019\u0001#\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002dA\u0019q.!\u001a\n\u0007\u0005\u001d\u0004O\u0001\u0004PE*,7\r\u001e"
)
public class AbstractArrayType extends AbstractDataType implements Product, Serializable {
   private final AbstractDataType elementType;

   public static Option unapply(final AbstractArrayType x$0) {
      return AbstractArrayType$.MODULE$.unapply(x$0);
   }

   public static AbstractArrayType apply(final AbstractDataType elementType) {
      return AbstractArrayType$.MODULE$.apply(elementType);
   }

   public static Function1 andThen(final Function1 g) {
      return AbstractArrayType$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return AbstractArrayType$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public AbstractDataType elementType() {
      return this.elementType;
   }

   public DataType defaultConcreteType() {
      return new ArrayType(this.elementType().defaultConcreteType(), true);
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof ArrayType && this.elementType().acceptsType(((ArrayType)other).elementType());
   }

   public String simpleString() {
      return "array<" + this.elementType().simpleString() + ">";
   }

   public AbstractArrayType copy(final AbstractDataType elementType) {
      return new AbstractArrayType(elementType);
   }

   public AbstractDataType copy$default$1() {
      return this.elementType();
   }

   public String productPrefix() {
      return "AbstractArrayType";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.elementType();
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
      return x$1 instanceof AbstractArrayType;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "elementType";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
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
            if (x$1 instanceof AbstractArrayType) {
               label40: {
                  AbstractArrayType var4 = (AbstractArrayType)x$1;
                  AbstractDataType var10000 = this.elementType();
                  AbstractDataType var5 = var4.elementType();
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

   public AbstractArrayType(final AbstractDataType elementType) {
      this.elementType = elementType;
      Product.$init$(this);
   }
}
