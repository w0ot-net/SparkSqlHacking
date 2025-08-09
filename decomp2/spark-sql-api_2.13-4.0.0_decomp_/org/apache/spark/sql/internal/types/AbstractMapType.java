package org.apache.spark.sql.internal.types;

import java.io.Serializable;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.MapType;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005e\u0001\u0002\u000f\u001e\u0001*B\u0001B\u0011\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\t\u0002\u0011\t\u0012)A\u0005W!AQ\t\u0001BK\u0002\u0013\u00051\t\u0003\u0005G\u0001\tE\t\u0015!\u0003,\u0011\u00159\u0005\u0001\"\u0001I\u0011\u0019i\u0005\u0001\"\u0011\"\u001d\"1!\u000b\u0001C!CMCa!\u0017\u0001\u0005B\rR\u0006bB2\u0001\u0003\u0003%\t\u0001\u001a\u0005\bO\u0002\t\n\u0011\"\u0001i\u0011\u001d\u0019\b!%A\u0005\u0002!Dq\u0001\u001e\u0001\u0002\u0002\u0013\u0005S\u000fC\u0004~\u0001\u0005\u0005I\u0011\u0001@\t\u0013\u0005\u0015\u0001!!A\u0005\u0002\u0005\u001d\u0001\"CA\n\u0001\u0005\u0005I\u0011IA\u000b\u0011%\t\u0019\u0003AA\u0001\n\u0003\t)\u0003C\u0005\u0002*\u0001\t\t\u0011\"\u0011\u0002,!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003g\u0001\u0011\u0011!C!\u0003kA\u0011\"a\u000e\u0001\u0003\u0003%\t%!\u000f\b\u0013\u0005uR$!A\t\u0002\u0005}b\u0001\u0003\u000f\u001e\u0003\u0003E\t!!\u0011\t\r\u001d3B\u0011AA-\u0011%\t\u0019DFA\u0001\n\u000b\n)\u0004C\u0005\u0002\\Y\t\t\u0011\"!\u0002^!I\u00111\r\f\u0002\u0002\u0013\u0005\u0015Q\r\u0005\n\u0003o2\u0012\u0011!C\u0005\u0003s\u0012q\"\u00112tiJ\f7\r^'baRK\b/\u001a\u0006\u0003=}\tQ\u0001^=qKNT!\u0001I\u0011\u0002\u0011%tG/\u001a:oC2T!AI\u0012\u0002\u0007M\fHN\u0003\u0002%K\u0005)1\u000f]1sW*\u0011aeJ\u0001\u0007CB\f7\r[3\u000b\u0003!\n1a\u001c:h\u0007\u0001\u0019B\u0001A\u00161mA\u0011AFL\u0007\u0002[)\u0011a$I\u0005\u0003_5\u0012\u0001#\u00112tiJ\f7\r\u001e#bi\u0006$\u0016\u0010]3\u0011\u0005E\"T\"\u0001\u001a\u000b\u0003M\nQa]2bY\u0006L!!\u000e\u001a\u0003\u000fA\u0013x\u000eZ;diB\u0011qg\u0010\b\u0003qur!!\u000f\u001f\u000e\u0003iR!aO\u0015\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0014B\u0001 3\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001Q!\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005y\u0012\u0014aB6fsRK\b/Z\u000b\u0002W\u0005A1.Z=UsB,\u0007%A\u0005wC2,X\rV=qK\u0006Qa/\u00197vKRK\b/\u001a\u0011\u0002\rqJg.\u001b;?)\rI5\n\u0014\t\u0003\u0015\u0002i\u0011!\b\u0005\u0006\u0005\u0016\u0001\ra\u000b\u0005\u0006\u000b\u0016\u0001\raK\u0001\u0014I\u00164\u0017-\u001e7u\u0007>t7M]3uKRK\b/Z\u000b\u0002\u001fB\u0011A\u0006U\u0005\u0003#6\u0012\u0001\u0002R1uCRK\b/Z\u0001\fC\u000e\u001cW\r\u001d;t)f\u0004X\r\u0006\u0002U/B\u0011\u0011'V\u0005\u0003-J\u0012qAQ8pY\u0016\fg\u000eC\u0003Y\u000f\u0001\u0007q*A\u0003pi\",'/\u0001\u0007tS6\u0004H.Z*ue&tw-F\u0001\\!\ta\u0006M\u0004\u0002^=B\u0011\u0011HM\u0005\u0003?J\na\u0001\u0015:fI\u00164\u0017BA1c\u0005\u0019\u0019FO]5oO*\u0011qLM\u0001\u0005G>\u0004\u0018\u0010F\u0002JK\u001aDqAQ\u0005\u0011\u0002\u0003\u00071\u0006C\u0004F\u0013A\u0005\t\u0019A\u0016\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t\u0011N\u000b\u0002,U.\n1\u000e\u0005\u0002mc6\tQN\u0003\u0002o_\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003aJ\n!\"\u00198o_R\fG/[8o\u0013\t\u0011XNA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002mB\u0011q\u000f`\u0007\u0002q*\u0011\u0011P_\u0001\u0005Y\u0006twMC\u0001|\u0003\u0011Q\u0017M^1\n\u0005\u0005D\u0018\u0001\u00049s_\u0012,8\r^!sSRLX#A@\u0011\u0007E\n\t!C\u0002\u0002\u0004I\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u0003\u0002\u0010A\u0019\u0011'a\u0003\n\u0007\u00055!GA\u0002B]fD\u0001\"!\u0005\u000f\u0003\u0003\u0005\ra`\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005]\u0001CBA\r\u0003?\tI!\u0004\u0002\u0002\u001c)\u0019\u0011Q\u0004\u001a\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\"\u0005m!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$2\u0001VA\u0014\u0011%\t\t\u0002EA\u0001\u0002\u0004\tI!\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u0001<\u0002.!A\u0011\u0011C\t\u0002\u0002\u0003\u0007q0\u0001\u0005iCND7i\u001c3f)\u0005y\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003Y\fa!Z9vC2\u001cHc\u0001+\u0002<!I\u0011\u0011\u0003\u000b\u0002\u0002\u0003\u0007\u0011\u0011B\u0001\u0010\u0003\n\u001cHO]1di6\u000b\u0007\u000fV=qKB\u0011!JF\n\u0006-\u0005\r\u0013q\n\t\b\u0003\u000b\nYeK\u0016J\u001b\t\t9EC\u0002\u0002JI\nqA];oi&lW-\u0003\u0003\u0002N\u0005\u001d#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011\u0011KA,\u001b\t\t\u0019FC\u0002\u0002Vi\f!![8\n\u0007\u0001\u000b\u0019\u0006\u0006\u0002\u0002@\u0005)\u0011\r\u001d9msR)\u0011*a\u0018\u0002b!)!)\u0007a\u0001W!)Q)\u0007a\u0001W\u00059QO\\1qa2LH\u0003BA4\u0003g\u0002R!MA5\u0003[J1!a\u001b3\u0005\u0019y\u0005\u000f^5p]B)\u0011'a\u001c,W%\u0019\u0011\u0011\u000f\u001a\u0003\rQ+\b\u000f\\33\u0011!\t)HGA\u0001\u0002\u0004I\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0010\t\u0004o\u0006u\u0014bAA@q\n1qJ\u00196fGR\u0004"
)
public class AbstractMapType extends AbstractDataType implements Product, Serializable {
   private final AbstractDataType keyType;
   private final AbstractDataType valueType;

   public static Option unapply(final AbstractMapType x$0) {
      return AbstractMapType$.MODULE$.unapply(x$0);
   }

   public static AbstractMapType apply(final AbstractDataType keyType, final AbstractDataType valueType) {
      return AbstractMapType$.MODULE$.apply(keyType, valueType);
   }

   public static Function1 tupled() {
      return AbstractMapType$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return AbstractMapType$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public AbstractDataType keyType() {
      return this.keyType;
   }

   public AbstractDataType valueType() {
      return this.valueType;
   }

   public DataType defaultConcreteType() {
      return new MapType(this.keyType().defaultConcreteType(), this.valueType().defaultConcreteType(), true);
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof MapType && this.keyType().acceptsType(((MapType)other).keyType()) && this.valueType().acceptsType(((MapType)other).valueType());
   }

   public String simpleString() {
      String var10000 = this.keyType().simpleString();
      return "map<" + var10000 + ", " + this.valueType().simpleString() + ">";
   }

   public AbstractMapType copy(final AbstractDataType keyType, final AbstractDataType valueType) {
      return new AbstractMapType(keyType, valueType);
   }

   public AbstractDataType copy$default$1() {
      return this.keyType();
   }

   public AbstractDataType copy$default$2() {
      return this.valueType();
   }

   public String productPrefix() {
      return "AbstractMapType";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.keyType();
         }
         case 1 -> {
            return this.valueType();
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
      return x$1 instanceof AbstractMapType;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "keyType";
         }
         case 1 -> {
            return "valueType";
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
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof AbstractMapType) {
               label48: {
                  AbstractMapType var4 = (AbstractMapType)x$1;
                  AbstractDataType var10000 = this.keyType();
                  AbstractDataType var5 = var4.keyType();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  var10000 = this.valueType();
                  AbstractDataType var6 = var4.valueType();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var6)) {
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

   public AbstractMapType(final AbstractDataType keyType, final AbstractDataType valueType) {
      this.keyType = keyType;
      this.valueType = valueType;
      Product.$init$(this);
   }
}
