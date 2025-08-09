package org.apache.spark.ml.feature;

import java.io.Serializable;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dc!B\f\u0019\u0001j\u0011\u0003\u0002C\u001f\u0001\u0005+\u0007I\u0011\u0001 \t\u0011}\u0002!\u0011#Q\u0001\n%BQ\u0001\u0011\u0001\u0005\u0002\u0005CQ\u0001\u0012\u0001\u0005B\u0015Cq\u0001\u0013\u0001\u0002\u0002\u0013\u0005\u0011\nC\u0004L\u0001E\u0005I\u0011\u0001'\t\u000f]\u0003\u0011\u0011!C!1\"9\u0011\rAA\u0001\n\u0003\u0011\u0007b\u00024\u0001\u0003\u0003%\ta\u001a\u0005\b[\u0002\t\t\u0011\"\u0011o\u0011\u001d)\b!!A\u0005\u0002YDqa\u001f\u0001\u0002\u0002\u0013\u0005C\u0010C\u0004\u007f\u0001\u0005\u0005I\u0011I@\t\u0013\u0005\u0005\u0001!!A\u0005B\u0005\r\u0001\"CA\u0003\u0001\u0005\u0005I\u0011IA\u0004\u000f)\tY\u0001GA\u0001\u0012\u0003Q\u0012Q\u0002\u0004\n/a\t\t\u0011#\u0001\u001b\u0003\u001fAa\u0001Q\t\u0005\u0002\u0005\u001d\u0002\"CA\u0001#\u0005\u0005IQIA\u0002\u0011%\tI#EA\u0001\n\u0003\u000bY\u0003C\u0005\u00020E\t\t\u0011\"!\u00022!I\u0011QH\t\u0002\u0002\u0013%\u0011q\b\u0002\t\t\u0016dW\r^5p]*\u0011\u0011DG\u0001\bM\u0016\fG/\u001e:f\u0015\tYB$\u0001\u0002nY*\u0011QDH\u0001\u0006gB\f'o\u001b\u0006\u0003?\u0001\na!\u00199bG\",'\"A\u0011\u0002\u0007=\u0014xmE\u0003\u0001G%j\u0003\u0007\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VM\u001a\t\u0003U-j\u0011\u0001G\u0005\u0003Ya\u0011A\u0001V3s[B\u0011AEL\u0005\u0003_\u0015\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00022u9\u0011!\u0007\u000f\b\u0003g]j\u0011\u0001\u000e\u0006\u0003kY\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002M%\u0011\u0011(J\u0001\ba\u0006\u001c7.Y4f\u0013\tYDH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002:K\u0005!A/\u001a:n+\u0005I\u0013!\u0002;fe6\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002C\u0007B\u0011!\u0006\u0001\u0005\u0006{\r\u0001\r!K\u0001\tS:$XM]1diR\u0011\u0011F\u0012\u0005\u0006\u000f\u0012\u0001\r!K\u0001\u0006_RDWM]\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002C\u0015\"9Q(\u0002I\u0001\u0002\u0004I\u0013AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u001b*\u0012\u0011FT\u0016\u0002\u001fB\u0011\u0001+V\u0007\u0002#*\u0011!kU\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001V\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002W#\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005I\u0006C\u0001.`\u001b\u0005Y&B\u0001/^\u0003\u0011a\u0017M\\4\u000b\u0003y\u000bAA[1wC&\u0011\u0001m\u0017\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\r\u0004\"\u0001\n3\n\u0005\u0015,#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00015l!\t!\u0013.\u0003\u0002kK\t\u0019\u0011I\\=\t\u000f1L\u0011\u0011!a\u0001G\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012a\u001c\t\u0004aNDW\"A9\u000b\u0005I,\u0013AC2pY2,7\r^5p]&\u0011A/\u001d\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002xuB\u0011A\u0005_\u0005\u0003s\u0016\u0012qAQ8pY\u0016\fg\u000eC\u0004m\u0017\u0005\u0005\t\u0019\u00015\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u00033vDq\u0001\u001c\u0007\u0002\u0002\u0003\u00071-\u0001\u0005iCND7i\u001c3f)\u0005\u0019\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003e\u000ba!Z9vC2\u001cHcA<\u0002\n!9AnDA\u0001\u0002\u0004A\u0017\u0001\u0003#fY\u0016$\u0018n\u001c8\u0011\u0005)\n2#B\t\u0002\u0012\u0005u\u0001CBA\n\u00033I#)\u0004\u0002\u0002\u0016)\u0019\u0011qC\u0013\u0002\u000fI,h\u000e^5nK&!\u00111DA\u000b\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003?\t)#\u0004\u0002\u0002\")\u0019\u00111E/\u0002\u0005%|\u0017bA\u001e\u0002\"Q\u0011\u0011QB\u0001\u0006CB\u0004H.\u001f\u000b\u0004\u0005\u00065\u0002\"B\u001f\u0015\u0001\u0004I\u0013aB;oCB\u0004H.\u001f\u000b\u0005\u0003g\tI\u0004\u0005\u0003%\u0003kI\u0013bAA\u001cK\t1q\n\u001d;j_:D\u0001\"a\u000f\u0016\u0003\u0003\u0005\rAQ\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA!!\rQ\u00161I\u0005\u0004\u0003\u000bZ&AB(cU\u0016\u001cG\u000f"
)
public class Deletion implements Term, Product, Serializable {
   private final Term term;

   public static Option unapply(final Deletion x$0) {
      return Deletion$.MODULE$.unapply(x$0);
   }

   public static Deletion apply(final Term term) {
      return Deletion$.MODULE$.apply(term);
   }

   public static Function1 andThen(final Function1 g) {
      return Deletion$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return Deletion$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Terms asTerms() {
      return Term.asTerms$(this);
   }

   public Term add(final Term other) {
      return Term.add$(this, other);
   }

   public Term subtract(final Term other) {
      return Term.subtract$(this, other);
   }

   public Term term() {
      return this.term;
   }

   public Term interact(final Term other) {
      if (other instanceof Deletion var4) {
         Term t = var4.term();
         return new Deletion(this.term().interact(t));
      } else if (other != null) {
         return new Deletion(this.term().interact(other));
      } else {
         throw new MatchError(other);
      }
   }

   public Deletion copy(final Term term) {
      return new Deletion(term);
   }

   public Term copy$default$1() {
      return this.term();
   }

   public String productPrefix() {
      return "Deletion";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.term();
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
      return x$1 instanceof Deletion;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "term";
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
            if (x$1 instanceof Deletion) {
               label40: {
                  Deletion var4 = (Deletion)x$1;
                  Term var10000 = this.term();
                  Term var5 = var4.term();
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

   public Deletion(final Term term) {
      this.term = term;
      Term.$init$(this);
      Product.$init$(this);
   }
}
