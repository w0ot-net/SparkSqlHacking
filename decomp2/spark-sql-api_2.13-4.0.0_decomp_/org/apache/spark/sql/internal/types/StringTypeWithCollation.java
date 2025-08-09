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
   bytes = "\u0006\u0005\u00055d\u0001B\u0010!\u00016B\u0001\u0002\u0012\u0001\u0003\u0016\u0004%\t!\u0012\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005\r\"A!\n\u0001BK\u0002\u0013\u0005Q\t\u0003\u0005L\u0001\tE\t\u0015!\u0003G\u0011!a\u0005A!f\u0001\n\u0003)\u0005\u0002C'\u0001\u0005#\u0005\u000b\u0011\u0002$\t\u000b9\u0003A\u0011A(\t\u000bQ\u0003A\u0011I+\t\u000fu\u0003\u0011\u0011!C\u0001=\"9!\rAI\u0001\n\u0003\u0019\u0007b\u00028\u0001#\u0003%\ta\u0019\u0005\b_\u0002\t\n\u0011\"\u0001d\u0011\u001d\u0001\b!!A\u0005BEDqA\u001f\u0001\u0002\u0002\u0013\u00051\u0010\u0003\u0005\u0000\u0001\u0005\u0005I\u0011AA\u0001\u0011%\ti\u0001AA\u0001\n\u0003\ny\u0001C\u0005\u0002\u001e\u0001\t\t\u0011\"\u0001\u0002 !I\u00111\u0005\u0001\u0002\u0002\u0013\u0005\u0013Q\u0005\u0005\n\u0003S\u0001\u0011\u0011!C!\u0003WA\u0011\"!\f\u0001\u0003\u0003%\t%a\f\t\u0013\u0005E\u0002!!A\u0005B\u0005MraBA\u001cA!\u0005\u0011\u0011\b\u0004\u0007?\u0001B\t!a\u000f\t\r9;B\u0011AA\u001f\u0011\u001d\tyd\u0006C\u0001\u0003\u0003B\u0001\"!\u0013\u0018#\u0003%\ta\u0019\u0005\t\u0003\u0017:\u0012\u0013!C\u0001G\"A\u0011QJ\f\u0012\u0002\u0013\u00051\rC\u0005\u0002P]\t\t\u0011\"!\u0002R!I\u00111M\f\u0002\u0002\u0013%\u0011Q\r\u0002\u0018'R\u0014\u0018N\\4UsB,w+\u001b;i\u0007>dG.\u0019;j_:T!!\t\u0012\u0002\u000bQL\b/Z:\u000b\u0005\r\"\u0013\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u00152\u0013aA:rY*\u0011q\u0005K\u0001\u0006gB\f'o\u001b\u0006\u0003S)\na!\u00199bG\",'\"A\u0016\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001q#\u0007\u000f\t\u0003_Aj\u0011\u0001I\u0005\u0003c\u0001\u0012!#\u00112tiJ\f7\r^*ue&tw\rV=qKB\u00111GN\u0007\u0002i)\tQ'A\u0003tG\u0006d\u0017-\u0003\u00028i\t9\u0001K]8ek\u000e$\bCA\u001dB\u001d\tQtH\u0004\u0002<}5\tAH\u0003\u0002>Y\u00051AH]8pizJ\u0011!N\u0005\u0003\u0001R\nq\u0001]1dW\u0006<W-\u0003\u0002C\u0007\na1+\u001a:jC2L'0\u00192mK*\u0011\u0001\tN\u0001\u0016gV\u0004\bo\u001c:ugR\u0013\u0018.\\\"pY2\fG/[8o+\u00051\u0005CA\u001aH\u0013\tAEGA\u0004C_>dW-\u00198\u0002-M,\b\u000f]8siN$&/[7D_2d\u0017\r^5p]\u0002\nQc];qa>\u0014Ho]\"bg\u0016\u001c\u0006/Z2jM&,'/\u0001\ftkB\u0004xN\u001d;t\u0007\u0006\u001cXm\u00159fG&4\u0017.\u001a:!\u0003]\u0019X\u000f\u001d9peR\u001c\u0018iY2f]R\u001c\u0006/Z2jM&,'/\u0001\rtkB\u0004xN\u001d;t\u0003\u000e\u001cWM\u001c;Ta\u0016\u001c\u0017NZ5fe\u0002\na\u0001P5oSRtD\u0003\u0002)R%N\u0003\"a\f\u0001\t\u000b\u0011;\u0001\u0019\u0001$\t\u000b);\u0001\u0019\u0001$\t\u000b1;\u0001\u0019\u0001$\u0002#\u0005\u001c7-\u001a9ugN#(/\u001b8h)f\u0004X\r\u0006\u0002G-\")q\u000b\u0003a\u00011\u0006)q\u000e\u001e5feB\u0011\u0011lW\u0007\u00025*\u0011\u0011\u0005J\u0005\u00039j\u0013!b\u0015;sS:<G+\u001f9f\u0003\u0011\u0019w\u000e]=\u0015\tA{\u0006-\u0019\u0005\b\t&\u0001\n\u00111\u0001G\u0011\u001dQ\u0015\u0002%AA\u0002\u0019Cq\u0001T\u0005\u0011\u0002\u0003\u0007a)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u0011T#AR3,\u0003\u0019\u0004\"a\u001a7\u000e\u0003!T!!\u001b6\u0002\u0013Ut7\r[3dW\u0016$'BA65\u0003)\tgN\\8uCRLwN\\\u0005\u0003[\"\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nabY8qs\u0012\"WMZ1vYR$3'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002eB\u00111\u000f_\u0007\u0002i*\u0011QO^\u0001\u0005Y\u0006twMC\u0001x\u0003\u0011Q\u0017M^1\n\u0005e$(AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001}!\t\u0019T0\u0003\u0002\u007fi\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u00111AA\u0005!\r\u0019\u0014QA\u0005\u0004\u0003\u000f!$aA!os\"A\u00111B\b\u0002\u0002\u0003\u0007A0A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003#\u0001b!a\u0005\u0002\u001a\u0005\rQBAA\u000b\u0015\r\t9\u0002N\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u000e\u0003+\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019a)!\t\t\u0013\u0005-\u0011#!AA\u0002\u0005\r\u0011A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2A]A\u0014\u0011!\tYAEA\u0001\u0002\u0004a\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003q\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002e\u00061Q-];bYN$2ARA\u001b\u0011%\tY!FA\u0001\u0002\u0004\t\u0019!A\fTiJLgn\u001a+za\u0016<\u0016\u000e\u001e5D_2d\u0017\r^5p]B\u0011qfF\n\u0003/A#\"!!\u000f\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000fA\u000b\u0019%!\u0012\u0002H!9A)\u0007I\u0001\u0002\u00041\u0005b\u0002&\u001a!\u0003\u0005\rA\u0012\u0005\b\u0019f\u0001\n\u00111\u0001G\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\n\u0014aD1qa2LH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIM\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002T\u0005}\u0003#B\u001a\u0002V\u0005e\u0013bAA,i\t1q\n\u001d;j_:\u0004baMA.\r\u001a3\u0015bAA/i\t1A+\u001e9mKNB\u0001\"!\u0019\u001e\u0003\u0003\u0005\r\u0001U\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA4!\r\u0019\u0018\u0011N\u0005\u0004\u0003W\"(AB(cU\u0016\u001cG\u000f"
)
public class StringTypeWithCollation extends AbstractStringType implements Product {
   private final boolean supportsTrimCollation;
   private final boolean supportsCaseSpecifier;
   private final boolean supportsAccentSpecifier;

   public static Option unapply(final StringTypeWithCollation x$0) {
      return StringTypeWithCollation$.MODULE$.unapply(x$0);
   }

   public static boolean apply$default$3() {
      return StringTypeWithCollation$.MODULE$.apply$default$3();
   }

   public static boolean apply$default$2() {
      return StringTypeWithCollation$.MODULE$.apply$default$2();
   }

   public static boolean apply$default$1() {
      return StringTypeWithCollation$.MODULE$.apply$default$1();
   }

   public static StringTypeWithCollation apply(final boolean supportsTrimCollation, final boolean supportsCaseSpecifier, final boolean supportsAccentSpecifier) {
      return StringTypeWithCollation$.MODULE$.apply(supportsTrimCollation, supportsCaseSpecifier, supportsAccentSpecifier);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean supportsTrimCollation() {
      return this.supportsTrimCollation;
   }

   public boolean supportsCaseSpecifier() {
      return this.supportsCaseSpecifier;
   }

   public boolean supportsAccentSpecifier() {
      return this.supportsAccentSpecifier;
   }

   public boolean acceptsStringType(final StringType other) {
      return (this.supportsCaseSpecifier() || !other.isCaseInsensitive()) && (this.supportsAccentSpecifier() || !other.isAccentInsensitive());
   }

   public StringTypeWithCollation copy(final boolean supportsTrimCollation, final boolean supportsCaseSpecifier, final boolean supportsAccentSpecifier) {
      return new StringTypeWithCollation(supportsTrimCollation, supportsCaseSpecifier, supportsAccentSpecifier);
   }

   public boolean copy$default$1() {
      return this.supportsTrimCollation();
   }

   public boolean copy$default$2() {
      return this.supportsCaseSpecifier();
   }

   public boolean copy$default$3() {
      return this.supportsAccentSpecifier();
   }

   public String productPrefix() {
      return "StringTypeWithCollation";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToBoolean(this.supportsTrimCollation());
         }
         case 1 -> {
            return BoxesRunTime.boxToBoolean(this.supportsCaseSpecifier());
         }
         case 2 -> {
            return BoxesRunTime.boxToBoolean(this.supportsAccentSpecifier());
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
      return x$1 instanceof StringTypeWithCollation;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "supportsTrimCollation";
         }
         case 1 -> {
            return "supportsCaseSpecifier";
         }
         case 2 -> {
            return "supportsAccentSpecifier";
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
      var1 = Statics.mix(var1, this.supportsCaseSpecifier() ? 1231 : 1237);
      var1 = Statics.mix(var1, this.supportsAccentSpecifier() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label40: {
            if (x$1 instanceof StringTypeWithCollation) {
               StringTypeWithCollation var4 = (StringTypeWithCollation)x$1;
               if (this.supportsTrimCollation() == var4.supportsTrimCollation() && this.supportsCaseSpecifier() == var4.supportsCaseSpecifier() && this.supportsAccentSpecifier() == var4.supportsAccentSpecifier() && var4.canEqual(this)) {
                  break label40;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public StringTypeWithCollation(final boolean supportsTrimCollation, final boolean supportsCaseSpecifier, final boolean supportsAccentSpecifier) {
      super(supportsTrimCollation);
      this.supportsTrimCollation = supportsTrimCollation;
      this.supportsCaseSpecifier = supportsCaseSpecifier;
      this.supportsAccentSpecifier = supportsAccentSpecifier;
      Product.$init$(this);
   }
}
