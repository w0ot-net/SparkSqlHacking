package org.apache.spark.sql.internal.types;

import org.apache.spark.sql.types.StringType;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b\u0001\u0002\f\u0018\u0001\u0012B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005{!)\u0011\t\u0001C\u0001\u0005\")Q\t\u0001C!\r\"9a\nAA\u0001\n\u0003y\u0005bB)\u0001#\u0003%\tA\u0015\u0005\b;\u0002\t\t\u0011\"\u0011_\u0011\u001d9\u0007!!A\u0005\u0002!Dq\u0001\u001c\u0001\u0002\u0002\u0013\u0005Q\u000eC\u0004t\u0001\u0005\u0005I\u0011\t;\t\u000fm\u0004\u0011\u0011!C\u0001y\"9a\u0010AA\u0001\n\u0003z\b\"CA\u0002\u0001\u0005\u0005I\u0011IA\u0003\u0011%\t9\u0001AA\u0001\n\u0003\nI\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e\u001d9\u0011\u0011C\f\t\u0002\u0005MaA\u0002\f\u0018\u0011\u0003\t)\u0002\u0003\u0004B#\u0011\u0005\u0011q\u0003\u0005\b\u00033\tB\u0011AA\u000e\u0011%\ty\"EA\u0001\n\u0003\u000b\t\u0003C\u0005\u0002.E\t\t\u0011\"\u0003\u00020\tQ2\u000b\u001e:j]\u001e$\u0016\u0010]3O_:\u001c5+Q%D_2d\u0017\r^5p]*\u0011\u0001$G\u0001\u0006if\u0004Xm\u001d\u0006\u00035m\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u00039u\t1a]9m\u0015\tqr$A\u0003ta\u0006\u00148N\u0003\u0002!C\u00051\u0011\r]1dQ\u0016T\u0011AI\u0001\u0004_J<7\u0001A\n\u0005\u0001\u0015Js\u0006\u0005\u0002'O5\tq#\u0003\u0002)/\t\u0011\u0012IY:ue\u0006\u001cGo\u0015;sS:<G+\u001f9f!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\r\u001d\u000f\u0005E2dB\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b$\u0003\u0019a$o\\8u}%\tA&\u0003\u00028W\u00059\u0001/Y2lC\u001e,\u0017BA\u001d;\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t94&A\u000btkB\u0004xN\u001d;t)JLWnQ8mY\u0006$\u0018n\u001c8\u0016\u0003u\u0002\"A\u000b \n\u0005}Z#a\u0002\"p_2,\u0017M\\\u0001\u0017gV\u0004\bo\u001c:ugR\u0013\u0018.\\\"pY2\fG/[8oA\u00051A(\u001b8jiz\"\"a\u0011#\u0011\u0005\u0019\u0002\u0001\"B\u001e\u0004\u0001\u0004i\u0014!E1dG\u0016\u0004Ho]*ue&tw\rV=qKR\u0011Qh\u0012\u0005\u0006\u0011\u0012\u0001\r!S\u0001\u0006_RDWM\u001d\t\u0003\u00152k\u0011a\u0013\u0006\u00031mI!!T&\u0003\u0015M#(/\u001b8h)f\u0004X-\u0001\u0003d_BLHCA\"Q\u0011\u001dYT\u0001%AA\u0002u\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001TU\tiDkK\u0001V!\t16,D\u0001X\u0015\tA\u0016,A\u0005v]\u000eDWmY6fI*\u0011!lK\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001/X\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003}\u0003\"\u0001Y3\u000e\u0003\u0005T!AY2\u0002\t1\fgn\u001a\u0006\u0002I\u0006!!.\u0019<b\u0013\t1\u0017M\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002SB\u0011!F[\u0005\u0003W.\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"A\\9\u0011\u0005)z\u0017B\u00019,\u0005\r\te.\u001f\u0005\be&\t\t\u00111\u0001j\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tQ\u000fE\u0002ws:l\u0011a\u001e\u0006\u0003q.\n!bY8mY\u0016\u001cG/[8o\u0013\tQxO\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA\u001f~\u0011\u001d\u00118\"!AA\u00029\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019q,!\u0001\t\u000fId\u0011\u0011!a\u0001S\u0006A\u0001.Y:i\u0007>$W\rF\u0001j\u0003!!xn\u0015;sS:<G#A0\u0002\r\u0015\fX/\u00197t)\ri\u0014q\u0002\u0005\be>\t\t\u00111\u0001o\u0003i\u0019FO]5oORK\b/\u001a(p]\u000e\u001b\u0016)S\"pY2\fG/[8o!\t1\u0013c\u0005\u0002\u0012\u0007R\u0011\u00111C\u0001\u0006CB\u0004H.\u001f\u000b\u0004\u0007\u0006u\u0001\"B\u001e\u0014\u0001\u0004i\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003G\tI\u0003\u0005\u0003+\u0003Ki\u0014bAA\u0014W\t1q\n\u001d;j_:D\u0001\"a\u000b\u0015\u0003\u0003\u0005\raQ\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u0019!\r\u0001\u00171G\u0005\u0004\u0003k\t'AB(cU\u0016\u001cG\u000f"
)
public class StringTypeNonCSAICollation extends AbstractStringType implements Product {
   private final boolean supportsTrimCollation;

   public static Option unapply(final StringTypeNonCSAICollation x$0) {
      return StringTypeNonCSAICollation$.MODULE$.unapply(x$0);
   }

   public static StringTypeNonCSAICollation apply(final boolean supportsTrimCollation) {
      return StringTypeNonCSAICollation$.MODULE$.apply(supportsTrimCollation);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean supportsTrimCollation() {
      return this.supportsTrimCollation;
   }

   public boolean acceptsStringType(final StringType other) {
      return other.isCaseInsensitive() || !other.isAccentInsensitive();
   }

   public StringTypeNonCSAICollation copy(final boolean supportsTrimCollation) {
      return new StringTypeNonCSAICollation(supportsTrimCollation);
   }

   public boolean copy$default$1() {
      return this.supportsTrimCollation();
   }

   public String productPrefix() {
      return "StringTypeNonCSAICollation";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToBoolean(this.supportsTrimCollation());
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
      return x$1 instanceof StringTypeNonCSAICollation;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "supportsTrimCollation";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.supportsTrimCollation() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof StringTypeNonCSAICollation) {
               StringTypeNonCSAICollation var4 = (StringTypeNonCSAICollation)x$1;
               if (this.supportsTrimCollation() == var4.supportsTrimCollation() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public StringTypeNonCSAICollation(final boolean supportsTrimCollation) {
      super(supportsTrimCollation);
      this.supportsTrimCollation = supportsTrimCollation;
      Product.$init$(this);
   }
}
