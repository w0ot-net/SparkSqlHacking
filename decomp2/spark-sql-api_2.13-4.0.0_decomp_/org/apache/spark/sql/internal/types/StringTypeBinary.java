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
   bytes = "\u0006\u0005\u0005]b\u0001\u0002\f\u0018\u0001\u0012B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005{!)\u0011\t\u0001C\u0001\u0005\")Q\t\u0001C!\r\"9a\nAA\u0001\n\u0003y\u0005bB)\u0001#\u0003%\tA\u0015\u0005\b;\u0002\t\t\u0011\"\u0011_\u0011\u001d9\u0007!!A\u0005\u0002!Dq\u0001\u001c\u0001\u0002\u0002\u0013\u0005Q\u000eC\u0004t\u0001\u0005\u0005I\u0011\t;\t\u000fm\u0004\u0011\u0011!C\u0001y\"9a\u0010AA\u0001\n\u0003z\b\"CA\u0002\u0001\u0005\u0005I\u0011IA\u0003\u0011%\t9\u0001AA\u0001\n\u0003\nI\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e\u001d9\u0011\u0011C\f\t\u0002\u0005MaA\u0002\f\u0018\u0011\u0003\t)\u0002\u0003\u0004B#\u0011\u0005\u0011q\u0003\u0005\b\u00033\tB\u0011AA\u000e\u0011%\ty\"EA\u0001\n\u0003\u000b\t\u0003C\u0005\u0002.E\t\t\u0011\"\u0003\u00020\t\u00012\u000b\u001e:j]\u001e$\u0016\u0010]3CS:\f'/\u001f\u0006\u00031e\tQ\u0001^=qKNT!AG\u000e\u0002\u0011%tG/\u001a:oC2T!\u0001H\u000f\u0002\u0007M\fHN\u0003\u0002\u001f?\u0005)1\u000f]1sW*\u0011\u0001%I\u0001\u0007CB\f7\r[3\u000b\u0003\t\n1a\u001c:h\u0007\u0001\u0019B\u0001A\u0013*_A\u0011aeJ\u0007\u0002/%\u0011\u0001f\u0006\u0002\u0013\u0003\n\u001cHO]1diN#(/\u001b8h)f\u0004X\r\u0005\u0002+[5\t1FC\u0001-\u0003\u0015\u00198-\u00197b\u0013\tq3FA\u0004Qe>$Wo\u0019;\u0011\u0005ABdBA\u00197\u001d\t\u0011T'D\u00014\u0015\t!4%\u0001\u0004=e>|GOP\u0005\u0002Y%\u0011qgK\u0001\ba\u0006\u001c7.Y4f\u0013\tI$H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00028W\u0005)2/\u001e9q_J$8\u000f\u0016:j[\u000e{G\u000e\\1uS>tW#A\u001f\u0011\u0005)r\u0014BA ,\u0005\u001d\u0011un\u001c7fC:\fac];qa>\u0014Ho\u001d+sS6\u001cu\u000e\u001c7bi&|g\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\r#\u0005C\u0001\u0014\u0001\u0011\u0015Y4\u00011\u0001>\u0003E\t7mY3qiN\u001cFO]5oORK\b/\u001a\u000b\u0003{\u001dCQ\u0001\u0013\u0003A\u0002%\u000bQa\u001c;iKJ\u0004\"A\u0013'\u000e\u0003-S!\u0001G\u000e\n\u00055[%AC*ue&tw\rV=qK\u0006!1m\u001c9z)\t\u0019\u0005\u000bC\u0004<\u000bA\u0005\t\u0019A\u001f\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t1K\u000b\u0002>).\nQ\u000b\u0005\u0002W76\tqK\u0003\u0002Y3\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u00035.\n!\"\u00198o_R\fG/[8o\u0013\tavKA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A0\u0011\u0005\u0001,W\"A1\u000b\u0005\t\u001c\u0017\u0001\u00027b]\u001eT\u0011\u0001Z\u0001\u0005U\u00064\u0018-\u0003\u0002gC\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u001b\t\u0003U)L!a[\u0016\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u00059\f\bC\u0001\u0016p\u0013\t\u00018FA\u0002B]fDqA]\u0005\u0002\u0002\u0003\u0007\u0011.A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002kB\u0019a/\u001f8\u000e\u0003]T!\u0001_\u0016\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002{o\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\tiT\u0010C\u0004s\u0017\u0005\u0005\t\u0019\u00018\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004?\u0006\u0005\u0001b\u0002:\r\u0003\u0003\u0005\r![\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011.\u0001\u0005u_N#(/\u001b8h)\u0005y\u0016AB3rk\u0006d7\u000fF\u0002>\u0003\u001fAqA]\b\u0002\u0002\u0003\u0007a.\u0001\tTiJLgn\u001a+za\u0016\u0014\u0015N\\1ssB\u0011a%E\n\u0003#\r#\"!a\u0005\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\r\u000bi\u0002C\u0003<'\u0001\u0007Q(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\r\u0012\u0011\u0006\t\u0005U\u0005\u0015R(C\u0002\u0002(-\u0012aa\u00149uS>t\u0007\u0002CA\u0016)\u0005\u0005\t\u0019A\"\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00022A\u0019\u0001-a\r\n\u0007\u0005U\u0012M\u0001\u0004PE*,7\r\u001e"
)
public class StringTypeBinary extends AbstractStringType implements Product {
   private final boolean supportsTrimCollation;

   public static Option unapply(final StringTypeBinary x$0) {
      return StringTypeBinary$.MODULE$.unapply(x$0);
   }

   public static StringTypeBinary apply(final boolean supportsTrimCollation) {
      return StringTypeBinary$.MODULE$.apply(supportsTrimCollation);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean supportsTrimCollation() {
      return this.supportsTrimCollation;
   }

   public boolean acceptsStringType(final StringType other) {
      return other.supportsBinaryEquality();
   }

   public StringTypeBinary copy(final boolean supportsTrimCollation) {
      return new StringTypeBinary(supportsTrimCollation);
   }

   public boolean copy$default$1() {
      return this.supportsTrimCollation();
   }

   public String productPrefix() {
      return "StringTypeBinary";
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
      return x$1 instanceof StringTypeBinary;
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
            if (x$1 instanceof StringTypeBinary) {
               StringTypeBinary var4 = (StringTypeBinary)x$1;
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

   public StringTypeBinary(final boolean supportsTrimCollation) {
      super(supportsTrimCollation);
      this.supportsTrimCollation = supportsTrimCollation;
      Product.$init$(this);
   }
}
