package org.apache.spark.ml.feature;

import java.io.Serializable;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc!B\f\u0019\u0001j\u0011\u0003\u0002C\u001f\u0001\u0005+\u0007I\u0011\u0001 \t\u0011\u0015\u0003!\u0011#Q\u0001\n}BQA\u0012\u0001\u0005\u0002\u001dCQA\u0013\u0001\u0005B-CqA\u0014\u0001\u0002\u0002\u0013\u0005q\nC\u0004R\u0001E\u0005I\u0011\u0001*\t\u000fu\u0003\u0011\u0011!C!=\"9q\rAA\u0001\n\u0003A\u0007b\u00027\u0001\u0003\u0003%\t!\u001c\u0005\bg\u0002\t\t\u0011\"\u0011u\u0011\u001dY\b!!A\u0005\u0002qD\u0011\"a\u0001\u0001\u0003\u0003%\t%!\u0002\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\u0007\u0001\u0005\u0005I\u0011IA\b\u0011%\t\t\u0002AA\u0001\n\u0003\n\u0019b\u0002\u0006\u0002\u0018a\t\t\u0011#\u0001\u001b\u000331\u0011b\u0006\r\u0002\u0002#\u0005!$a\u0007\t\r\u0019\u000bB\u0011AA\u001a\u0011%\ti!EA\u0001\n\u000b\ny\u0001C\u0005\u00026E\t\t\u0011\"!\u00028!I\u00111H\t\u0002\u0002\u0013\u0005\u0015Q\b\u0005\n\u0003\u0013\n\u0012\u0011!C\u0005\u0003\u0017\u0012\u0011cQ8mk6t\u0017J\u001c;fe\u0006\u001cG/[8o\u0015\tI\"$A\u0004gK\u0006$XO]3\u000b\u0005ma\u0012AA7m\u0015\tib$A\u0003ta\u0006\u00148N\u0003\u0002 A\u00051\u0011\r]1dQ\u0016T\u0011!I\u0001\u0004_J<7#\u0002\u0001$S5\u0002\u0004C\u0001\u0013(\u001b\u0005)#\"\u0001\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005!*#AB!osJ+g\r\u0005\u0002+W5\t\u0001$\u0003\u0002-1\t!A+\u001a:n!\t!c&\u0003\u00020K\t9\u0001K]8ek\u000e$\bCA\u0019;\u001d\t\u0011\u0004H\u0004\u00024o5\tAG\u0003\u00026m\u00051AH]8piz\u001a\u0001!C\u0001'\u0013\tIT%A\u0004qC\u000e\\\u0017mZ3\n\u0005mb$\u0001D*fe&\fG.\u001b>bE2,'BA\u001d&\u0003\u0015!XM]7t+\u0005y\u0004cA\u0019A\u0005&\u0011\u0011\t\u0010\u0002\u0004'\u0016\f\bC\u0001\u0016D\u0013\t!\u0005D\u0001\tJ]R,'/Y2uC\ndW\rV3s[\u00061A/\u001a:ng\u0002\na\u0001P5oSRtDC\u0001%J!\tQ\u0003\u0001C\u0003>\u0007\u0001\u0007q(\u0001\u0005j]R,'/Y2u)\tIC\nC\u0003N\t\u0001\u0007\u0011&A\u0003pi\",'/\u0001\u0003d_BLHC\u0001%Q\u0011\u001diT\u0001%AA\u0002}\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001TU\tyDkK\u0001V!\t16,D\u0001X\u0015\tA\u0016,A\u0005v]\u000eDWmY6fI*\u0011!,J\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001/X\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003}\u0003\"\u0001Y3\u000e\u0003\u0005T!AY2\u0002\t1\fgn\u001a\u0006\u0002I\u0006!!.\u0019<b\u0013\t1\u0017M\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002SB\u0011AE[\u0005\u0003W\u0016\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"A\\9\u0011\u0005\u0011z\u0017B\u00019&\u0005\r\te.\u001f\u0005\be&\t\t\u00111\u0001j\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tQ\u000fE\u0002ws:l\u0011a\u001e\u0006\u0003q\u0016\n!bY8mY\u0016\u001cG/[8o\u0013\tQxO\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGcA?\u0002\u0002A\u0011AE`\u0005\u0003\u007f\u0016\u0012qAQ8pY\u0016\fg\u000eC\u0004s\u0017\u0005\u0005\t\u0019\u00018\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004?\u0006\u001d\u0001b\u0002:\r\u0003\u0003\u0005\r![\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011.\u0001\u0005u_N#(/\u001b8h)\u0005y\u0016AB3rk\u0006d7\u000fF\u0002~\u0003+AqA]\b\u0002\u0002\u0003\u0007a.A\tD_2,XN\\%oi\u0016\u0014\u0018m\u0019;j_:\u0004\"AK\t\u0014\u000bE\ti\"!\u000b\u0011\r\u0005}\u0011QE I\u001b\t\t\tCC\u0002\u0002$\u0015\nqA];oi&lW-\u0003\u0003\u0002(\u0005\u0005\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u00111FA\u0019\u001b\t\tiCC\u0002\u00020\r\f!![8\n\u0007m\ni\u0003\u0006\u0002\u0002\u001a\u0005)\u0011\r\u001d9msR\u0019\u0001*!\u000f\t\u000bu\"\u0002\u0019A \u0002\u000fUt\u0017\r\u001d9msR!\u0011qHA#!\u0011!\u0013\u0011I \n\u0007\u0005\rSE\u0001\u0004PaRLwN\u001c\u0005\t\u0003\u000f*\u0012\u0011!a\u0001\u0011\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u00055\u0003c\u00011\u0002P%\u0019\u0011\u0011K1\u0003\r=\u0013'.Z2u\u0001"
)
public class ColumnInteraction implements Term, Product, Serializable {
   private final Seq terms;

   public static Option unapply(final ColumnInteraction x$0) {
      return ColumnInteraction$.MODULE$.unapply(x$0);
   }

   public static ColumnInteraction apply(final Seq terms) {
      return ColumnInteraction$.MODULE$.apply(terms);
   }

   public static Function1 andThen(final Function1 g) {
      return ColumnInteraction$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ColumnInteraction$.MODULE$.compose(g);
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

   public Seq terms() {
      return this.terms;
   }

   public Term interact(final Term other) {
      if (other instanceof InteractableTerm var4) {
         return this.interact(var4.asInteraction());
      } else if (other instanceof ColumnInteraction var5) {
         return new ColumnInteraction((Seq)this.terms().$plus$plus(var5.terms()));
      } else if (other instanceof Terms var6) {
         return this.asTerms().interact(var6);
      } else if (other != null) {
         return other.interact(this);
      } else {
         throw new MatchError(other);
      }
   }

   public ColumnInteraction copy(final Seq terms) {
      return new ColumnInteraction(terms);
   }

   public Seq copy$default$1() {
      return this.terms();
   }

   public String productPrefix() {
      return "ColumnInteraction";
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
      return x$1 instanceof ColumnInteraction;
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
            if (x$1 instanceof ColumnInteraction) {
               label40: {
                  ColumnInteraction var4 = (ColumnInteraction)x$1;
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

   public ColumnInteraction(final Seq terms) {
      this.terms = terms;
      Term.$init$(this);
      Product.$init$(this);
   }
}
