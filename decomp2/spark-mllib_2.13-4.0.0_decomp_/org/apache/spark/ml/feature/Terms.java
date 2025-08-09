package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc!\u0002\r\u001a\u0001n\u0019\u0003\u0002\u0003 \u0001\u0005+\u0007I\u0011A \t\u0011\r\u0003!\u0011#Q\u0001\n\u0001CQ\u0001\u0012\u0001\u0005\u0002\u0015CQ\u0001\u0013\u0001\u0005B%CQA\u0013\u0001\u0005B-CqA\u0014\u0001\u0002\u0002\u0013\u0005q\nC\u0004R\u0001E\u0005I\u0011\u0001*\t\u000fu\u0003\u0011\u0011!C!=\"9q\rAA\u0001\n\u0003A\u0007b\u00027\u0001\u0003\u0003%\t!\u001c\u0005\bg\u0002\t\t\u0011\"\u0011u\u0011\u001dY\b!!A\u0005\u0002qD\u0011\"a\u0001\u0001\u0003\u0003%\t%!\u0002\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\u0007\u0001\u0005\u0005I\u0011IA\b\u0011%\t\t\u0002AA\u0001\n\u0003\n\u0019b\u0002\u0006\u0002\u0018e\t\t\u0011#\u0001\u001c\u000331\u0011\u0002G\r\u0002\u0002#\u00051$a\u0007\t\r\u0011\u0013B\u0011AA\u001a\u0011%\tiAEA\u0001\n\u000b\ny\u0001C\u0005\u00026I\t\t\u0011\"!\u00028!I\u00111\b\n\u0002\u0002\u0013\u0005\u0015Q\b\u0005\n\u0003\u0013\u0012\u0012\u0011!C\u0005\u0003\u0017\u0012Q\u0001V3s[NT!AG\u000e\u0002\u000f\u0019,\u0017\r^;sK*\u0011A$H\u0001\u0003[2T!AH\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0001\n\u0013AB1qC\u000eDWMC\u0001#\u0003\ry'oZ\n\u0006\u0001\u0011Rc&\r\t\u0003K!j\u0011A\n\u0006\u0002O\u0005)1oY1mC&\u0011\u0011F\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005-bS\"A\r\n\u00055J\"\u0001\u0002+fe6\u0004\"!J\u0018\n\u0005A2#a\u0002)s_\u0012,8\r\u001e\t\u0003emr!aM\u001d\u000f\u0005QBT\"A\u001b\u000b\u0005Y:\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\u001dJ!A\u000f\u0014\u0002\u000fA\f7m[1hK&\u0011A(\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003u\u0019\nQ\u0001^3s[N,\u0012\u0001\u0011\t\u0004e\u0005S\u0013B\u0001\">\u0005\r\u0019V-]\u0001\u0007i\u0016\u0014Xn\u001d\u0011\u0002\rqJg.\u001b;?)\t1u\t\u0005\u0002,\u0001!)ah\u0001a\u0001\u0001\u00069\u0011m\u001d+fe6\u001cX#\u0001$\u0002\u0011%tG/\u001a:bGR$\"A\u000b'\t\u000b5+\u0001\u0019\u0001\u0016\u0002\u000b=$\b.\u001a:\u0002\t\r|\u0007/\u001f\u000b\u0003\rBCqA\u0010\u0004\u0011\u0002\u0003\u0007\u0001)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003MS#\u0001\u0011+,\u0003U\u0003\"AV.\u000e\u0003]S!\u0001W-\u0002\u0013Ut7\r[3dW\u0016$'B\u0001.'\u0003)\tgN\\8uCRLwN\\\u0005\u00039^\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tq\f\u0005\u0002aK6\t\u0011M\u0003\u0002cG\u0006!A.\u00198h\u0015\u0005!\u0017\u0001\u00026bm\u0006L!AZ1\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005I\u0007CA\u0013k\u0013\tYgEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002ocB\u0011Qe\\\u0005\u0003a\u001a\u00121!\u00118z\u0011\u001d\u0011(\"!AA\u0002%\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A;\u0011\u0007YLh.D\u0001x\u0015\tAh%\u0001\u0006d_2dWm\u0019;j_:L!A_<\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004{\u0006\u0005\u0001CA\u0013\u007f\u0013\tyhEA\u0004C_>dW-\u00198\t\u000fId\u0011\u0011!a\u0001]\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\ry\u0016q\u0001\u0005\be6\t\t\u00111\u0001j\u0003!A\u0017m\u001d5D_\u0012,G#A5\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012aX\u0001\u0007KF,\u0018\r\\:\u0015\u0007u\f)\u0002C\u0004s!\u0005\u0005\t\u0019\u00018\u0002\u000bQ+'/\\:\u0011\u0005-\u00122#\u0002\n\u0002\u001e\u0005%\u0002CBA\u0010\u0003K\u0001e)\u0004\u0002\u0002\")\u0019\u00111\u0005\u0014\u0002\u000fI,h\u000e^5nK&!\u0011qEA\u0011\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003W\t\t$\u0004\u0002\u0002.)\u0019\u0011qF2\u0002\u0005%|\u0017b\u0001\u001f\u0002.Q\u0011\u0011\u0011D\u0001\u0006CB\u0004H.\u001f\u000b\u0004\r\u0006e\u0002\"\u0002 \u0016\u0001\u0004\u0001\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u007f\t)\u0005\u0005\u0003&\u0003\u0003\u0002\u0015bAA\"M\t1q\n\u001d;j_:D\u0001\"a\u0012\u0017\u0003\u0003\u0005\rAR\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA'!\r\u0001\u0017qJ\u0005\u0004\u0003#\n'AB(cU\u0016\u001cG\u000f"
)
public class Terms implements Term, Product, Serializable {
   private final Seq terms;

   public static Option unapply(final Terms x$0) {
      return Terms$.MODULE$.unapply(x$0);
   }

   public static Terms apply(final Seq terms) {
      return Terms$.MODULE$.apply(terms);
   }

   public static Function1 andThen(final Function1 g) {
      return Terms$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return Terms$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Term add(final Term other) {
      return Term.add$(this, other);
   }

   public Term subtract(final Term other) {
      return Term.subtract$(this, other);
   }

   public Seq terms() {
      return this.terms;
   }

   public Terms asTerms() {
      return this;
   }

   public Term interact(final Term other) {
      Seq interactions = (Seq)this.terms().flatMap((left) -> (Seq)other.asTerms().terms().map((right) -> left.interact(right)));
      return new Terms(interactions);
   }

   public Terms copy(final Seq terms) {
      return new Terms(terms);
   }

   public Seq copy$default$1() {
      return this.terms();
   }

   public String productPrefix() {
      return "Terms";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.terms();
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
      return x$1 instanceof Terms;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "terms";
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
            if (x$1 instanceof Terms) {
               label40: {
                  Terms var4 = (Terms)x$1;
                  Seq var10000 = this.terms();
                  Seq var5 = var4.terms();
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

   public Terms(final Seq terms) {
      this.terms = terms;
      Term.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
