package scala.xml;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015g\u0001\u0002\u0012$\u0001\"B\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005\u0005\"A1\n\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005M\u0001\tE\t\u0015!\u0003C\u0011!i\u0005A!f\u0001\n\u0003q\u0005\u0002\u0003)\u0001\u0005#\u0005\u000b\u0011B(\t\u000bE\u0003A\u0011\u0001*\t\u000bY\u0003A\u0011A,\t\u000be\u0003A\u0011\u0001.\t\u000bq\u0003A\u0011I/\t\u000by\u0003A\u0011B0\t\u000b\t\u0004A\u0011I2\t\u000b1\u0004A\u0011I7\t\u000b=\u0004A\u0011\t9\t\u000b]\u0004A\u0011\u0001=\t\u000b]\u0004A\u0011\u0001>\t\u000f\u0005-\u0001\u0001\"\u0003\u0002\u000e!I\u00111\u0003\u0001\u0002\u0002\u0013\u0005\u0011Q\u0003\u0005\n\u0003;\u0001\u0011\u0013!C\u0001\u0003?A\u0011\"!\u000e\u0001#\u0003%\t!a\b\t\u0013\u0005]\u0002!%A\u0005\u0002\u0005e\u0002\"CA\u001f\u0001\u0005\u0005I\u0011IA \u0011%\ty\u0005AA\u0001\n\u0003\t\t\u0006C\u0005\u0002Z\u0001\t\t\u0011\"\u0001\u0002\\!I\u0011\u0011\r\u0001\u0002\u0002\u0013\u0005\u00131\r\u0005\n\u0003W\u0002\u0011\u0011!C!\u0003[:\u0011\"! $\u0003\u0003E\t!a \u0007\u0011\t\u001a\u0013\u0011!E\u0001\u0003\u0003Ca!\u0015\u000f\u0005\u0002\u0005e\u0005\u0002\u0003/\u001d\u0003\u0003%)%a'\t\u0013\u0005uE$!A\u0005\u0002\u0006}\u0005\"CAT9\u0005\u0005I\u0011QAU\u0011%\tY\fHA\u0001\n\u0013\tiL\u0001\tOC6,7\u000f]1dK\nKg\u000eZ5oO*\u0011A%J\u0001\u0004q6d'\"\u0001\u0014\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M)\u0001!K\u00172iA\u0011!fK\u0007\u0002K%\u0011A&\n\u0002\u0007\u0003:L(+\u001a4\u0011\u00059zS\"A\u0012\n\u0005A\u001a#\u0001C#rk\u0006d\u0017\u000e^=\u0011\u0005)\u0012\u0014BA\u001a&\u0005\u001d\u0001&o\u001c3vGR\u0004\"!N\u001f\u000f\u0005YZdBA\u001c;\u001b\u0005A$BA\u001d(\u0003\u0019a$o\\8u}%\ta%\u0003\u0002=K\u00059\u0001/Y2lC\u001e,\u0017B\u0001 @\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\taT%\u0001\u0004qe\u00164\u0017\u000e_\u000b\u0002\u0005B\u00111i\u0012\b\u0003\t\u0016\u0003\"aN\u0013\n\u0005\u0019+\u0013A\u0002)sK\u0012,g-\u0003\u0002I\u0013\n11\u000b\u001e:j]\u001eT!AR\u0013\u0002\u000fA\u0014XMZ5yA\u0005\u0019QO]5\u0002\tU\u0014\u0018\u000eI\u0001\u0007a\u0006\u0014XM\u001c;\u0016\u0003=\u0003\"A\f\u0001\u0002\u000fA\f'/\u001a8uA\u00051A(\u001b8jiz\"BaT*U+\")\u0001i\u0002a\u0001\u0005\")1j\u0002a\u0001\u0005\")Qj\u0002a\u0001\u001f\u00061q-\u001a;V%&#\"A\u0011-\t\u000b\u0001C\u0001\u0019\u0001\"\u0002\u0013\u001d,G\u000f\u0015:fM&DHC\u0001\"\\\u0011\u0015Y\u0015\u00021\u0001C\u0003!!xn\u0015;sS:<G#\u0001\"\u0002\u001fMD\u0017\rZ8x%\u0016$WMZ5oK\u0012$\"a\u00141\t\u000b\u0005\\\u0001\u0019A(\u0002\tM$x\u000e]\u0001\tG\u0006tW)];bYR\u0011Am\u001a\t\u0003U\u0015L!AZ\u0013\u0003\u000f\t{w\u000e\\3b]\")\u0001\u000e\u0004a\u0001S\u0006)q\u000e\u001e5feB\u0011!F[\u0005\u0003W\u0016\u00121!\u00118z\u00035\u0019HO]5di~#S-\u001d\u0013fcR\u0011AM\u001c\u0005\u0006Q6\u0001\r!L\u0001\u0011E\u0006\u001c\u0018n\u001d$pe\"\u000b7\u000f[\"pI\u0016,\u0012!\u001d\t\u0004eVLW\"A:\u000b\u0005Q,\u0013AC2pY2,7\r^5p]&\u0011ao\u001d\u0002\u0004'\u0016\f\u0018a\u00032vS2$7\u000b\u001e:j]\u001e$\"AQ=\t\u000b\u0005|\u0001\u0019A(\u0015\tmt\u0018\u0011\u0002\t\u0003UqL!!`\u0013\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u007fB\u0001\r!!\u0001\u0002\u0005M\u0014\u0007\u0003BA\u0002\u0003\u000bq!AK\u001e\n\u0007\u0005\u001dqHA\u0007TiJLgn\u001a\"vS2$WM\u001d\u0005\u0006CB\u0001\raT\u0001\u000eI>\u0014U/\u001b7e'R\u0014\u0018N\\4\u0015\u000bm\fy!!\u0005\t\r}\f\u0002\u0019AA\u0001\u0011\u0015\t\u0017\u00031\u0001P\u0003\u0011\u0019w\u000e]=\u0015\u000f=\u000b9\"!\u0007\u0002\u001c!9\u0001I\u0005I\u0001\u0002\u0004\u0011\u0005bB&\u0013!\u0003\u0005\rA\u0011\u0005\b\u001bJ\u0001\n\u00111\u0001P\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!\t+\u0007\t\u000b\u0019c\u000b\u0002\u0002&A!\u0011qEA\u0019\u001b\t\tIC\u0003\u0003\u0002,\u00055\u0012!C;oG\",7m[3e\u0015\r\ty#J\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u001a\u0003S\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002<)\u001aq*a\t\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t\t\u0005\u0005\u0003\u0002D\u00055SBAA#\u0015\u0011\t9%!\u0013\u0002\t1\fgn\u001a\u0006\u0003\u0003\u0017\nAA[1wC&\u0019\u0001*!\u0012\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005M\u0003c\u0001\u0016\u0002V%\u0019\u0011qK\u0013\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007%\fi\u0006C\u0005\u0002`a\t\t\u00111\u0001\u0002T\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u001a\u0011\tI\f9'[\u0005\u0004\u0003S\u001a(\u0001C%uKJ\fGo\u001c:\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003\u0003\ny\u0007C\u0005\u0002`i\t\t\u00111\u0001\u0002T!:\u0001!a\u001d\u0002z\u0005m\u0004c\u0001\u0016\u0002v%\u0019\u0011qO\u0013\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g\u0004Co\fuh\\-m]^\u0002!9\u000bW.Z:qC\u000e,')\u001b8eS:<\u0007C\u0001\u0018\u001d'\u0015a\u00121QAH!!\t))a#C\u0005>{UBAAD\u0015\r\tI)J\u0001\beVtG/[7f\u0013\u0011\ti)a\"\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\u0005\u0003\u0002\u0012\u0006]UBAAJ\u0015\u0011\t)*!\u0013\u0002\u0005%|\u0017b\u0001 \u0002\u0014R\u0011\u0011q\u0010\u000b\u0003\u0003\u0003\nQ!\u00199qYf$raTAQ\u0003G\u000b)\u000bC\u0003A?\u0001\u0007!\tC\u0003L?\u0001\u0007!\tC\u0003N?\u0001\u0007q*A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005-\u0016q\u0017\t\u0006U\u00055\u0016\u0011W\u0005\u0004\u0003_+#AB(qi&|g\u000e\u0005\u0004+\u0003g\u0013%iT\u0005\u0004\u0003k+#A\u0002+va2,7\u0007\u0003\u0005\u0002:\u0002\n\t\u00111\u0001P\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u007f\u0003B!a\u0011\u0002B&!\u00111YA#\u0005\u0019y%M[3di\u0002"
)
public class NamespaceBinding implements Equality, Product, Serializable {
   private static final long serialVersionUID = -2518644165573446725L;
   private final String prefix;
   private final String uri;
   private final NamespaceBinding parent;

   public static Option unapply(final NamespaceBinding x$0) {
      return NamespaceBinding$.MODULE$.unapply(x$0);
   }

   public static NamespaceBinding apply(final String prefix, final String uri, final NamespaceBinding parent) {
      return NamespaceBinding$.MODULE$.apply(prefix, uri, parent);
   }

   public static Function1 tupled() {
      return NamespaceBinding$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return NamespaceBinding$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean strict_$bang$eq(final Equality other) {
      return Equality.strict_$bang$eq$(this, other);
   }

   public int hashCode() {
      return Equality.hashCode$(this);
   }

   public boolean equals(final Object other) {
      return Equality.equals$(this, other);
   }

   public final boolean xml_$eq$eq(final Object other) {
      return Equality.xml_$eq$eq$(this, other);
   }

   public final boolean xml_$bang$eq(final Object other) {
      return Equality.xml_$bang$eq$(this, other);
   }

   public String prefix() {
      return this.prefix;
   }

   public String uri() {
      return this.uri;
   }

   public NamespaceBinding parent() {
      return this.parent;
   }

   public String getURI(final String prefix) {
      String var10000 = this.prefix();
      if (var10000 == null) {
         if (prefix == null) {
            return this.uri();
         }
      } else if (var10000.equals(prefix)) {
         return this.uri();
      }

      return this.parent().getURI(prefix);
   }

   public String getPrefix(final String uri) {
      String var2 = this.uri();
      if (uri == null) {
         if (var2 == null) {
            return this.prefix();
         }
      } else if (uri.equals(var2)) {
         return this.prefix();
      }

      return this.parent().getPrefix(uri);
   }

   public String toString() {
      return Utility$.MODULE$.sbToString((x$1) -> {
         $anonfun$toString$1(this, x$1);
         return BoxedUnit.UNIT;
      });
   }

   private NamespaceBinding shadowRedefined(final NamespaceBinding stop) {
      List ps0 = prefixList$1(this, stop).reverse();
      List ps = (List)ps0.distinct();
      return ps.size() == ps0.size() ? this : this.fromPrefixList$1(ps, stop);
   }

   public boolean canEqual(final Object other) {
      return other instanceof NamespaceBinding;
   }

   public boolean strict_$eq$eq(final Equality other) {
      if (!(other instanceof NamespaceBinding)) {
         return false;
      } else {
         boolean var10;
         label48: {
            label42: {
               NamespaceBinding var4 = (NamespaceBinding)other;
               String var10000 = this.prefix();
               String var5 = var4.prefix();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label42;
                  }
               } else if (!var10000.equals(var5)) {
                  break label42;
               }

               var10000 = this.uri();
               String var6 = var4.uri();
               if (var10000 == null) {
                  if (var6 != null) {
                     break label42;
                  }
               } else if (!var10000.equals(var6)) {
                  break label42;
               }

               NamespaceBinding var9 = this.parent();
               NamespaceBinding var7 = var4.parent();
               if (var9 == null) {
                  if (var7 == null) {
                     break label48;
                  }
               } else if (var9.equals(var7)) {
                  break label48;
               }
            }

            var10 = false;
            return var10;
         }

         var10 = true;
         return var10;
      }
   }

   public Seq basisForHashCode() {
      return new .colon.colon(this.prefix(), new .colon.colon(this.uri(), new .colon.colon(this.parent(), scala.collection.immutable.Nil..MODULE$)));
   }

   public String buildString(final NamespaceBinding stop) {
      return Utility$.MODULE$.sbToString((x$2) -> {
         $anonfun$buildString$1(this, stop, x$2);
         return BoxedUnit.UNIT;
      });
   }

   public void buildString(final StringBuilder sb, final NamespaceBinding stop) {
      this.shadowRedefined(stop).doBuildString(sb, stop);
   }

   private void doBuildString(final StringBuilder sb, final NamespaceBinding stop) {
      while(!(new .colon.colon((Object)null, new .colon.colon(stop, new .colon.colon(TopScope$.MODULE$, scala.collection.immutable.Nil..MODULE$)))).contains(this)) {
         String prefixStr = this.prefix() != null ? (new java.lang.StringBuilder(1)).append(":").append(this.prefix()).toString() : "";
         String uriStr = this.uri() != null ? this.uri() : "";
         NamespaceBinding var10000 = this.parent();
         StringBuilder var10001 = sb.append((new java.lang.StringBuilder(9)).append(" xmlns").append(prefixStr).append("=\"").append(uriStr).append("\"").toString());
         stop = stop;
         sb = var10001;
         this = var10000;
      }

   }

   public NamespaceBinding copy(final String prefix, final String uri, final NamespaceBinding parent) {
      return new NamespaceBinding(prefix, uri, parent);
   }

   public String copy$default$1() {
      return this.prefix();
   }

   public String copy$default$2() {
      return this.uri();
   }

   public NamespaceBinding copy$default$3() {
      return this.parent();
   }

   public String productPrefix() {
      return "NamespaceBinding";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.prefix();
         case 1:
            return this.uri();
         case 2:
            return this.parent();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "prefix";
         case 1:
            return "uri";
         case 2:
            return "parent";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$toString$1(final NamespaceBinding $this, final StringBuilder x$1) {
      $this.buildString(x$1, TopScope$.MODULE$);
   }

   private static final List prefixList$1(final NamespaceBinding x, final NamespaceBinding stop$1) {
      if (x != null && x != stop$1) {
         String var2 = x.prefix();
         return prefixList$1(x.parent(), stop$1).$colon$colon(var2);
      } else {
         return scala.collection.immutable.Nil..MODULE$;
      }
   }

   private final NamespaceBinding fromPrefixList$1(final List l, final NamespaceBinding stop$1) {
      if (scala.collection.immutable.Nil..MODULE$.equals(l)) {
         return stop$1;
      } else if (l instanceof .colon.colon) {
         .colon.colon var5 = (.colon.colon)l;
         String x = (String)var5.head();
         List xs = var5.next$access$1();
         return new NamespaceBinding(x, this.getURI(x), this.fromPrefixList$1(xs, stop$1));
      } else {
         throw new MatchError(l);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$buildString$1(final NamespaceBinding $this, final NamespaceBinding stop$2, final StringBuilder x$2) {
      $this.buildString(x$2, stop$2);
   }

   public NamespaceBinding(final String prefix, final String uri, final NamespaceBinding parent) {
      this.prefix = prefix;
      this.uri = uri;
      this.parent = parent;
      Equality.$init$(this);
      Product.$init$(this);
      String var4 = "";
      if (prefix == null) {
         if (var4 == null) {
            throw new IllegalArgumentException("zero length prefix not allowed");
         }
      } else if (prefix.equals(var4)) {
         throw new IllegalArgumentException("zero length prefix not allowed");
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
