package org.apache.spark.util;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}d!\u0002\u000f\u001e\u0001~)\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\u0005\u0003!\u0011#Q\u0001\nyB\u0001B\u0011\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\u001f\u0002\u0011\t\u0012)A\u0005\t\"A\u0001\u000b\u0001BK\u0002\u0013\u0005\u0011\u000b\u0003\u0005V\u0001\tE\t\u0015!\u0003S\u0011\u00151\u0006\u0001\"\u0001X\u0011\u001di\u0006!!A\u0005\u0002yCqA\u0019\u0001\u0012\u0002\u0013\u00051\rC\u0004o\u0001E\u0005I\u0011A8\t\u000fE\u0004\u0011\u0013!C\u0001e\"9A\u000fAA\u0001\n\u0003*\bbB?\u0001\u0003\u0003%\tA \u0005\n\u0003\u000b\u0001\u0011\u0011!C\u0001\u0003\u000fA\u0011\"a\u0005\u0001\u0003\u0003%\t%!\u0006\t\u0013\u0005\r\u0002!!A\u0005\u0002\u0005\u0015\u0002\"CA\u0015\u0001\u0005\u0005I\u0011IA\u0016\u0011%\ty\u0003AA\u0001\n\u0003\n\t\u0004C\u0005\u00024\u0001\t\t\u0011\"\u0011\u00026!I\u0011q\u0007\u0001\u0002\u0002\u0013\u0005\u0013\u0011H\u0004\u000b\u0003{i\u0012\u0011!E\u0001?\u0005}b!\u0003\u000f\u001e\u0003\u0003E\taHA!\u0011\u00191f\u0003\"\u0001\u0002Z!I\u00111\u0007\f\u0002\u0002\u0013\u0015\u0013Q\u0007\u0005\n\u000372\u0012\u0011!CA\u0003;B\u0011\"!\u001a\u0017\u0003\u0003%\t)a\u001a\t\u0013\u0005Ud#!A\u0005\n\u0005]$aE!dGVlW\u000f\\1u_JlU\r^1eCR\f'B\u0001\u0010 \u0003\u0011)H/\u001b7\u000b\u0005\u0001\n\u0013!B:qCJ\\'B\u0001\u0012$\u0003\u0019\t\u0007/Y2iK*\tA%A\u0002pe\u001e\u001cB\u0001\u0001\u0014-sA\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t1\u0011I\\=SK\u001a\u0004\"!\f\u001c\u000f\u00059\"dBA\u00184\u001b\u0005\u0001$BA\u00193\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0015\n\u0005UB\u0013a\u00029bG.\fw-Z\u0005\u0003oa\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!\u000e\u0015\u0011\u0005\u001dR\u0014BA\u001e)\u0005\u001d\u0001&o\u001c3vGR\f!!\u001b3\u0016\u0003y\u0002\"aJ \n\u0005\u0001C#\u0001\u0002'p]\u001e\f1!\u001b3!\u0003\u0011q\u0017-\\3\u0016\u0003\u0011\u00032aJ#H\u0013\t1\u0005F\u0001\u0004PaRLwN\u001c\t\u0003\u00112s!!\u0013&\u0011\u0005=B\u0013BA&)\u0003\u0019\u0001&/\u001a3fM&\u0011QJ\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005-C\u0013!\u00028b[\u0016\u0004\u0013!E2pk:$h)Y5mK\u00124\u0016\r\\;fgV\t!\u000b\u0005\u0002('&\u0011A\u000b\u000b\u0002\b\u0005>|G.Z1o\u0003I\u0019w.\u001e8u\r\u0006LG.\u001a3WC2,Xm\u001d\u0011\u0002\rqJg.\u001b;?)\u0011A&l\u0017/\u0011\u0005e\u0003Q\"A\u000f\t\u000bq:\u0001\u0019\u0001 \t\u000b\t;\u0001\u0019\u0001#\t\u000bA;\u0001\u0019\u0001*\u0002\t\r|\u0007/\u001f\u000b\u00051~\u0003\u0017\rC\u0004=\u0011A\u0005\t\u0019\u0001 \t\u000f\tC\u0001\u0013!a\u0001\t\"9\u0001\u000b\u0003I\u0001\u0002\u0004\u0011\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0002I*\u0012a(Z\u0016\u0002MB\u0011q\r\\\u0007\u0002Q*\u0011\u0011N[\u0001\nk:\u001c\u0007.Z2lK\u0012T!a\u001b\u0015\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002nQ\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\t\u0001O\u000b\u0002EK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aT#A:+\u0005I+\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001w!\t9H0D\u0001y\u0015\tI(0\u0001\u0003mC:<'\"A>\u0002\t)\fg/Y\u0005\u0003\u001bb\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012a \t\u0004O\u0005\u0005\u0011bAA\u0002Q\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011BA\b!\r9\u00131B\u0005\u0004\u0003\u001bA#aA!os\"A\u0011\u0011\u0003\b\u0002\u0002\u0003\u0007q0A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003/\u0001b!!\u0007\u0002 \u0005%QBAA\u000e\u0015\r\ti\u0002K\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u0011\u00037\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019!+a\n\t\u0013\u0005E\u0001#!AA\u0002\u0005%\u0011A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2A^A\u0017\u0011!\t\t\"EA\u0001\u0002\u0004y\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003}\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002m\u00061Q-];bYN$2AUA\u001e\u0011%\t\t\u0002FA\u0001\u0002\u0004\tI!A\nBG\u000e,X.\u001e7bi>\u0014X*\u001a;bI\u0006$\u0018\r\u0005\u0002Z-M)a#a\u0011\u0002PAA\u0011QIA&}\u0011\u0013\u0006,\u0004\u0002\u0002H)\u0019\u0011\u0011\n\u0015\u0002\u000fI,h\u000e^5nK&!\u0011QJA$\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u0003#\n9&\u0004\u0002\u0002T)\u0019\u0011Q\u000b>\u0002\u0005%|\u0017bA\u001c\u0002TQ\u0011\u0011qH\u0001\u0006CB\u0004H.\u001f\u000b\b1\u0006}\u0013\u0011MA2\u0011\u0015a\u0014\u00041\u0001?\u0011\u0015\u0011\u0015\u00041\u0001E\u0011\u0015\u0001\u0016\u00041\u0001S\u0003\u001d)h.\u00199qYf$B!!\u001b\u0002rA!q%RA6!\u00199\u0013Q\u000e E%&\u0019\u0011q\u000e\u0015\u0003\rQ+\b\u000f\\34\u0011!\t\u0019HGA\u0001\u0002\u0004A\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u0010\t\u0004o\u0006m\u0014bAA?q\n1qJ\u00196fGR\u0004"
)
public class AccumulatorMetadata implements Serializable, Product {
   private final long id;
   private final Option name;
   private final boolean countFailedValues;

   public static Option unapply(final AccumulatorMetadata x$0) {
      return AccumulatorMetadata$.MODULE$.unapply(x$0);
   }

   public static AccumulatorMetadata apply(final long id, final Option name, final boolean countFailedValues) {
      return AccumulatorMetadata$.MODULE$.apply(id, name, countFailedValues);
   }

   public static Function1 tupled() {
      return AccumulatorMetadata$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return AccumulatorMetadata$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long id() {
      return this.id;
   }

   public Option name() {
      return this.name;
   }

   public boolean countFailedValues() {
      return this.countFailedValues;
   }

   public AccumulatorMetadata copy(final long id, final Option name, final boolean countFailedValues) {
      return new AccumulatorMetadata(id, name, countFailedValues);
   }

   public long copy$default$1() {
      return this.id();
   }

   public Option copy$default$2() {
      return this.name();
   }

   public boolean copy$default$3() {
      return this.countFailedValues();
   }

   public String productPrefix() {
      return "AccumulatorMetadata";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.id());
         }
         case 1 -> {
            return this.name();
         }
         case 2 -> {
            return BoxesRunTime.boxToBoolean(this.countFailedValues());
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
      return x$1 instanceof AccumulatorMetadata;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         case 1 -> {
            return "name";
         }
         case 2 -> {
            return "countFailedValues";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.id()));
      var1 = Statics.mix(var1, Statics.anyHash(this.name()));
      var1 = Statics.mix(var1, this.countFailedValues() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof AccumulatorMetadata) {
               AccumulatorMetadata var4 = (AccumulatorMetadata)x$1;
               if (this.id() == var4.id() && this.countFailedValues() == var4.countFailedValues()) {
                  label48: {
                     Option var10000 = this.name();
                     Option var5 = var4.name();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
                     }
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

   public AccumulatorMetadata(final long id, final Option name, final boolean countFailedValues) {
      this.id = id;
      this.name = name;
      this.countFailedValues = countFailedValues;
      Product.$init$(this);
   }
}
