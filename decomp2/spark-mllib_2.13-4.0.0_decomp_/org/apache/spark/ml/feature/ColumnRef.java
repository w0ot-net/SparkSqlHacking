package org.apache.spark.ml.feature;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c!\u0002\f\u0018\u0001f\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\u0019\u0003!\u0011#Q\u0001\nyBQa\u0012\u0001\u0005\u0002!Cqa\u0013\u0001\u0002\u0002\u0013\u0005A\nC\u0004O\u0001E\u0005I\u0011A(\t\u000fi\u0003\u0011\u0011!C!7\"91\rAA\u0001\n\u0003!\u0007b\u00025\u0001\u0003\u0003%\t!\u001b\u0005\b_\u0002\t\t\u0011\"\u0011q\u0011\u001d9\b!!A\u0005\u0002aDq! \u0001\u0002\u0002\u0013\u0005c\u0010C\u0005\u0002\u0002\u0001\t\t\u0011\"\u0011\u0002\u0004!I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\n\u0003\u0013\u0001\u0011\u0011!C!\u0003\u00179!\"a\u0004\u0018\u0003\u0003E\t!GA\t\r%1r#!A\t\u0002e\t\u0019\u0002\u0003\u0004H!\u0011\u0005\u00111\u0006\u0005\n\u0003\u000b\u0001\u0012\u0011!C#\u0003\u000fA\u0011\"!\f\u0011\u0003\u0003%\t)a\f\t\u0013\u0005M\u0002#!A\u0005\u0002\u0006U\u0002\"CA!!\u0005\u0005I\u0011BA\"\u0005%\u0019u\u000e\\;n]J+gM\u0003\u0002\u00193\u00059a-Z1ukJ,'B\u0001\u000e\u001c\u0003\tiGN\u0003\u0002\u001d;\u0005)1\u000f]1sW*\u0011adH\u0001\u0007CB\f7\r[3\u000b\u0003\u0001\n1a\u001c:h'\u0015\u0001!\u0005\u000b\u00170!\t\u0019c%D\u0001%\u0015\u0005)\u0013!B:dC2\f\u0017BA\u0014%\u0005\u0019\te.\u001f*fMB\u0011\u0011FK\u0007\u0002/%\u00111f\u0006\u0002\u0011\u0013:$XM]1di\u0006\u0014G.\u001a+fe6\u0004\"aI\u0017\n\u00059\"#a\u0002)s_\u0012,8\r\u001e\t\u0003aer!!M\u001c\u000f\u0005I2T\"A\u001a\u000b\u0005Q*\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\u0015J!\u0001\u000f\u0013\u0002\u000fA\f7m[1hK&\u0011!h\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003q\u0011\nQA^1mk\u0016,\u0012A\u0010\t\u0003\u007f\rs!\u0001Q!\u0011\u0005I\"\u0013B\u0001\"%\u0003\u0019\u0001&/\u001a3fM&\u0011A)\u0012\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\t#\u0013A\u0002<bYV,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0013*\u0003\"!\u000b\u0001\t\u000bq\u001a\u0001\u0019\u0001 \u0002\t\r|\u0007/\u001f\u000b\u0003\u00136Cq\u0001\u0010\u0003\u0011\u0002\u0003\u0007a(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003AS#AP),\u0003I\u0003\"a\u0015-\u000e\u0003QS!!\u0016,\u0002\u0013Ut7\r[3dW\u0016$'BA,%\u0003)\tgN\\8uCRLwN\\\u0005\u00033R\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tA\f\u0005\u0002^E6\taL\u0003\u0002`A\u0006!A.\u00198h\u0015\u0005\t\u0017\u0001\u00026bm\u0006L!\u0001\u00120\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u0015\u0004\"a\t4\n\u0005\u001d$#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00016n!\t\u00193.\u0003\u0002mI\t\u0019\u0011I\\=\t\u000f9D\u0011\u0011!a\u0001K\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001d\t\u0004eVTW\"A:\u000b\u0005Q$\u0013AC2pY2,7\r^5p]&\u0011ao\u001d\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002zyB\u00111E_\u0005\u0003w\u0012\u0012qAQ8pY\u0016\fg\u000eC\u0004o\u0015\u0005\u0005\t\u0019\u00016\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u00039~DqA\\\u0006\u0002\u0002\u0003\u0007Q-\u0001\u0005iCND7i\u001c3f)\u0005)\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003q\u000ba!Z9vC2\u001cHcA=\u0002\u000e!9aNDA\u0001\u0002\u0004Q\u0017!C\"pYVlgNU3g!\tI\u0003cE\u0003\u0011\u0003+\t\t\u0003\u0005\u0004\u0002\u0018\u0005ua(S\u0007\u0003\u00033Q1!a\u0007%\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\b\u0002\u001a\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005\r\u0012\u0011F\u0007\u0003\u0003KQ1!a\na\u0003\tIw.C\u0002;\u0003K!\"!!\u0005\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007%\u000b\t\u0004C\u0003='\u0001\u0007a(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005]\u0012Q\b\t\u0005G\u0005eb(C\u0002\u0002<\u0011\u0012aa\u00149uS>t\u0007\u0002CA )\u0005\u0005\t\u0019A%\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002FA\u0019Q,a\u0012\n\u0007\u0005%cL\u0001\u0004PE*,7\r\u001e"
)
public class ColumnRef implements InteractableTerm, Product, Serializable {
   private final String value;

   public static Option unapply(final ColumnRef x$0) {
      return ColumnRef$.MODULE$.unapply(x$0);
   }

   public static ColumnRef apply(final String value) {
      return ColumnRef$.MODULE$.apply(value);
   }

   public static Function1 andThen(final Function1 g) {
      return ColumnRef$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ColumnRef$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ColumnInteraction asInteraction() {
      return InteractableTerm.asInteraction$(this);
   }

   public Term interact(final Term other) {
      return InteractableTerm.interact$(this, other);
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

   public String value() {
      return this.value;
   }

   public ColumnRef copy(final String value) {
      return new ColumnRef(value);
   }

   public String copy$default$1() {
      return this.value();
   }

   public String productPrefix() {
      return "ColumnRef";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.value();
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
      return x$1 instanceof ColumnRef;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "value";
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
            if (x$1 instanceof ColumnRef) {
               label40: {
                  ColumnRef var4 = (ColumnRef)x$1;
                  String var10000 = this.value();
                  String var5 = var4.value();
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

   public ColumnRef(final String value) {
      this.value = value;
      Term.$init$(this);
      InteractableTerm.$init$(this);
      Product.$init$(this);
   }
}
