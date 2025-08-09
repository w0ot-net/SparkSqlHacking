package org.apache.spark.sql.types;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.sql.catalyst.util.CollationFactory;
import org.json4s.JString;
import org.json4s.JValue;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Md\u0001\u0002\r\u001a\u0001\u0012B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005{!)\u0011\t\u0001C\u0001\u0005\")Q\t\u0001C!y!)a\t\u0001C!\u000f\")\u0001\u000b\u0001C!#\")q\f\u0001C!A\"1\u0011\r\u0001C!;\tDqa\u0019\u0001\u0002\u0002\u0013\u0005A\rC\u0004g\u0001E\u0005I\u0011A4\t\u000fI\u0004\u0011\u0011!C!g\"91\u0010AA\u0001\n\u0003a\u0004b\u0002?\u0001\u0003\u0003%\t! \u0005\n\u0003\u000f\u0001\u0011\u0011!C!\u0003\u0013A\u0011\"a\u0006\u0001\u0003\u0003%\t!!\u0007\t\u0013\u0005\r\u0002!!A\u0005B\u0005\u0015r!CA\u001b3\u0005\u0005\t\u0012AA\u001c\r!A\u0012$!A\t\u0002\u0005e\u0002BB!\u0013\t\u0003\t\t\u0006\u0003\u0005`%\u0005\u0005IQIA*\u0011%\t)FEA\u0001\n\u0003\u000b9\u0006C\u0005\u0002\\I\t\t\u0011\"!\u0002^!I\u0011\u0011\u000e\n\u0002\u0002\u0013%\u00111\u000e\u0002\f-\u0006\u00148\r[1s)f\u0004XM\u0003\u0002\u001b7\u0005)A/\u001f9fg*\u0011A$H\u0001\u0004gFd'B\u0001\u0010 \u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0013%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002E\u0005\u0019qN]4\u0004\u0001M!\u0001!J\u00150!\t1s%D\u0001\u001a\u0013\tA\u0013D\u0001\u0006TiJLgn\u001a+za\u0016\u0004\"AK\u0017\u000e\u0003-R\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00021q9\u0011\u0011G\u000e\b\u0003eUj\u0011a\r\u0006\u0003i\r\na\u0001\u0010:p_Rt\u0014\"\u0001\u0017\n\u0005]Z\u0013a\u00029bG.\fw-Z\u0005\u0003si\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aN\u0016\u0002\r1,gn\u001a;i+\u0005i\u0004C\u0001\u0016?\u0013\ty4FA\u0002J]R\fq\u0001\\3oORD\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0007\u0012\u0003\"A\n\u0001\t\u000bm\u001a\u0001\u0019A\u001f\u0002\u0017\u0011,g-Y;miNK'0Z\u0001\tif\u0004XMT1nKV\t\u0001\n\u0005\u0002J\u001b:\u0011!j\u0013\t\u0003e-J!\u0001T\u0016\u0002\rA\u0013X\rZ3g\u0013\tquJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0019.\n\u0011B[:p]Z\u000bG.^3\u0016\u0003I\u0003\"a\u0015/\u000f\u0005QKfBA+X\u001d\t\u0011d+C\u0001#\u0013\tA\u0016%\u0001\u0004kg>tGg]\u0005\u00035n\u000bqAS:p]\u0006\u001bFK\u0003\u0002YC%\u0011QL\u0018\u0002\u0007\u0015Z\u000bG.^3\u000b\u0005i[\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003!\u000b!\"Y:Ok2d\u0017M\u00197f+\u0005\u0019\u0015\u0001B2paf$\"aQ3\t\u000fmJ\u0001\u0013!a\u0001{\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00015+\u0005uJ7&\u00016\u0011\u0005-\u0004X\"\u00017\u000b\u00055t\u0017!C;oG\",7m[3e\u0015\ty7&\u0001\u0006b]:|G/\u0019;j_:L!!\u001d7\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002iB\u0011QO_\u0007\u0002m*\u0011q\u000f_\u0001\u0005Y\u0006twMC\u0001z\u0003\u0011Q\u0017M^1\n\u000593\u0018\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004}\u0006\r\u0001C\u0001\u0016\u0000\u0013\r\t\ta\u000b\u0002\u0004\u0003:L\b\u0002CA\u0003\u001b\u0005\u0005\t\u0019A\u001f\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tY\u0001E\u0003\u0002\u000e\u0005Ma0\u0004\u0002\u0002\u0010)\u0019\u0011\u0011C\u0016\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0016\u0005=!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0007\u0002\"A\u0019!&!\b\n\u0007\u0005}1FA\u0004C_>dW-\u00198\t\u0011\u0005\u0015q\"!AA\u0002y\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019A/a\n\t\u0011\u0005\u0015\u0001#!AA\u0002uB3\u0001AA\u0016!\u0011\ti#!\r\u000e\u0005\u0005=\"BA8\u001e\u0013\u0011\t\u0019$a\f\u0003\u0019\u0015C\b/\u001a:j[\u0016tG/\u00197\u0002\u0017Y\u000b'o\u00195beRK\b/\u001a\t\u0003MI\u0019RAEA\u001e\u0003\u000f\u0002b!!\u0010\u0002Du\u001aUBAA \u0015\r\t\teK\u0001\beVtG/[7f\u0013\u0011\t)%a\u0010\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002J\u0005=SBAA&\u0015\r\ti\u0005_\u0001\u0003S>L1!OA&)\t\t9\u0004F\u0001u\u0003\u0015\t\u0007\u000f\u001d7z)\r\u0019\u0015\u0011\f\u0005\u0006wU\u0001\r!P\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ty&!\u001a\u0011\t)\n\t'P\u0005\u0004\u0003GZ#AB(qi&|g\u000e\u0003\u0005\u0002hY\t\t\u00111\u0001D\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003[\u00022!^A8\u0013\r\t\tH\u001e\u0002\u0007\u001f\nTWm\u0019;"
)
public class VarcharType extends StringType implements Product {
   private final int length;

   public static Option unapply(final VarcharType x$0) {
      return VarcharType$.MODULE$.unapply(x$0);
   }

   public static VarcharType apply(final int length) {
      return VarcharType$.MODULE$.apply(length);
   }

   public static Function1 andThen(final Function1 g) {
      return VarcharType$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return VarcharType$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int length() {
      return this.length;
   }

   public int defaultSize() {
      return this.length();
   }

   public String typeName() {
      return "varchar(" + this.length() + ")";
   }

   public JValue jsonValue() {
      return new JString(this.typeName());
   }

   public String toString() {
      return "VarcharType(" + this.length() + ")";
   }

   public VarcharType asNullable() {
      return this;
   }

   public VarcharType copy(final int length) {
      return new VarcharType(length);
   }

   public int copy$default$1() {
      return this.length();
   }

   public String productPrefix() {
      return "VarcharType";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.length());
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
      return x$1 instanceof VarcharType;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "length";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public VarcharType(final int length) {
      super(CollationFactory.UTF8_BINARY_COLLATION_ID, new MaxLength(length));
      this.length = length;
      Product.$init$(this);
      scala.Predef..MODULE$.require(length >= 0, () -> "The length of varchar type cannot be negative.");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
