package org.apache.spark.deploy.yarn;

import java.io.Serializable;
import java.net.URI;
import org.apache.hadoop.yarn.api.records.LocalResourceType;
import org.apache.hadoop.yarn.api.records.LocalResourceVisibility;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015g\u0001\u0002\u0012$\t:B\u0001\u0002\u0012\u0001\u0003\u0016\u0004%\t!\u0012\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\r\"Aq\n\u0001BK\u0002\u0013\u0005\u0001\u000b\u0003\u0005U\u0001\tE\t\u0015!\u0003R\u0011!)\u0006A!f\u0001\n\u0003\u0001\u0006\u0002\u0003,\u0001\u0005#\u0005\u000b\u0011B)\t\u0011]\u0003!Q3A\u0005\u0002aC\u0001\u0002\u001a\u0001\u0003\u0012\u0003\u0006I!\u0017\u0005\tK\u0002\u0011)\u001a!C\u0001M\"A!\u000e\u0001B\tB\u0003%q\rC\u0003l\u0001\u0011\u0005A\u000eC\u0004u\u0001\u0005\u0005I\u0011A;\t\u000fm\u0004\u0011\u0013!C\u0001y\"I\u0011q\u0002\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0003\u0005\n\u0003+\u0001\u0011\u0013!C\u0001\u0003#A\u0011\"a\u0006\u0001#\u0003%\t!!\u0007\t\u0013\u0005u\u0001!%A\u0005\u0002\u0005}\u0001\"CA\u0012\u0001\u0005\u0005I\u0011IA\u0013\u0011%\t\u0019\u0004AA\u0001\n\u0003\t)\u0004C\u0005\u0002>\u0001\t\t\u0011\"\u0001\u0002@!I\u00111\n\u0001\u0002\u0002\u0013\u0005\u0013Q\n\u0005\n\u00037\u0002\u0011\u0011!C\u0001\u0003;B\u0011\"a\u001a\u0001\u0003\u0003%\t%!\u001b\t\u0013\u00055\u0004!!A\u0005B\u0005=\u0004\"CA9\u0001\u0005\u0005I\u0011IA:\u0011%\t)\bAA\u0001\n\u0003\n9hB\u0005\u0002|\r\n\t\u0011#\u0003\u0002~\u0019A!eIA\u0001\u0012\u0013\ty\b\u0003\u0004l9\u0011\u0005\u0011q\u0013\u0005\n\u0003cb\u0012\u0011!C#\u0003gB\u0011\"!'\u001d\u0003\u0003%\t)a'\t\u0013\u0005\u001dF$!A\u0005\u0002\u0006%\u0006\"CA^9\u0005\u0005I\u0011BA_\u0005)\u0019\u0015m\u00195f\u000b:$(/\u001f\u0006\u0003I\u0015\nA!_1s]*\u0011aeJ\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005!J\u0013!B:qCJ\\'B\u0001\u0016,\u0003\u0019\t\u0007/Y2iK*\tA&A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001_UB\u0004C\u0001\u00194\u001b\u0005\t$\"\u0001\u001a\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\n$AB!osJ+g\r\u0005\u00021m%\u0011q'\r\u0002\b!J|G-^2u!\tI\u0014I\u0004\u0002;\u007f9\u00111HP\u0007\u0002y)\u0011Q(L\u0001\u0007yI|w\u000e\u001e \n\u0003IJ!\u0001Q\u0019\u0002\u000fA\f7m[1hK&\u0011!i\u0011\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0001F\n1!\u001e:j+\u00051\u0005CA$M\u001b\u0005A%BA%K\u0003\rqW\r\u001e\u0006\u0002\u0017\u0006!!.\u0019<b\u0013\ti\u0005JA\u0002V%&\u000bA!\u001e:jA\u0005!1/\u001b>f+\u0005\t\u0006C\u0001\u0019S\u0013\t\u0019\u0016G\u0001\u0003M_:<\u0017!B:ju\u0016\u0004\u0013aB7pIRKW.Z\u0001\t[>$G+[7fA\u0005Qa/[:jE&d\u0017\u000e^=\u0016\u0003e\u0003\"A\u00172\u000e\u0003mS!\u0001X/\u0002\u000fI,7m\u001c:eg*\u0011alX\u0001\u0004CBL'B\u0001\u0013a\u0015\t\t\u0017&\u0001\u0004iC\u0012|w\u000e]\u0005\u0003Gn\u0013q\u0003T8dC2\u0014Vm]8ve\u000e,g+[:jE&d\u0017\u000e^=\u0002\u0017YL7/\u001b2jY&$\u0018\u0010I\u0001\be\u0016\u001cH+\u001f9f+\u00059\u0007C\u0001.i\u0013\tI7LA\tM_\u000e\fGNU3t_V\u00148-\u001a+za\u0016\f\u0001B]3t)f\u0004X\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\r5|\u0007/\u001d:t!\tq\u0007!D\u0001$\u0011\u0015!5\u00021\u0001G\u0011\u0015y5\u00021\u0001R\u0011\u0015)6\u00021\u0001R\u0011\u001596\u00021\u0001Z\u0011\u0015)7\u00021\u0001h\u0003\u0011\u0019w\u000e]=\u0015\r54x\u000f_={\u0011\u001d!E\u0002%AA\u0002\u0019Cqa\u0014\u0007\u0011\u0002\u0003\u0007\u0011\u000bC\u0004V\u0019A\u0005\t\u0019A)\t\u000f]c\u0001\u0013!a\u00013\"9Q\r\u0004I\u0001\u0002\u00049\u0017AD2paf$C-\u001a4bk2$H%M\u000b\u0002{*\u0012aI`\u0016\u0002\u007fB!\u0011\u0011AA\u0006\u001b\t\t\u0019A\u0003\u0003\u0002\u0006\u0005\u001d\u0011!C;oG\",7m[3e\u0015\r\tI!M\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0007\u0003\u0007\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!a\u0005+\u0005Es\u0018AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\tYB\u000b\u0002Z}\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012*TCAA\u0011U\t9g0A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003O\u0001B!!\u000b\u000205\u0011\u00111\u0006\u0006\u0004\u0003[Q\u0015\u0001\u00027b]\u001eLA!!\r\u0002,\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\u000e\u0011\u0007A\nI$C\u0002\u0002<E\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u0011\u0002HA\u0019\u0001'a\u0011\n\u0007\u0005\u0015\u0013GA\u0002B]fD\u0011\"!\u0013\u0015\u0003\u0003\u0005\r!a\u000e\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\u0005\u0005\u0004\u0002R\u0005]\u0013\u0011I\u0007\u0003\u0003'R1!!\u00162\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u00033\n\u0019F\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA0\u0003K\u00022\u0001MA1\u0013\r\t\u0019'\r\u0002\b\u0005>|G.Z1o\u0011%\tIEFA\u0001\u0002\u0004\t\t%\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u0014\u0003WB\u0011\"!\u0013\u0018\u0003\u0003\u0005\r!a\u000e\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u000e\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\n\u0002\r\u0015\fX/\u00197t)\u0011\ty&!\u001f\t\u0013\u0005%#$!AA\u0002\u0005\u0005\u0013AC\"bG\",WI\u001c;ssB\u0011a\u000eH\n\u00069\u0005\u0005\u0015Q\u0012\t\u000b\u0003\u0007\u000bIIR)R3\u001elWBAAC\u0015\r\t9)M\u0001\beVtG/[7f\u0013\u0011\tY)!\"\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tW\u0007\u0005\u0003\u0002\u0010\u0006UUBAAI\u0015\r\t\u0019JS\u0001\u0003S>L1AQAI)\t\ti(A\u0003baBd\u0017\u0010F\u0006n\u0003;\u000by*!)\u0002$\u0006\u0015\u0006\"\u0002# \u0001\u00041\u0005\"B( \u0001\u0004\t\u0006\"B+ \u0001\u0004\t\u0006\"B, \u0001\u0004I\u0006\"B3 \u0001\u00049\u0017aB;oCB\u0004H.\u001f\u000b\u0005\u0003W\u000b9\fE\u00031\u0003[\u000b\t,C\u0002\u00020F\u0012aa\u00149uS>t\u0007\u0003\u0003\u0019\u00024\u001a\u000b\u0016+W4\n\u0007\u0005U\u0016G\u0001\u0004UkBdW-\u000e\u0005\t\u0003s\u0003\u0013\u0011!a\u0001[\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005}\u0006\u0003BA\u0015\u0003\u0003LA!a1\u0002,\t1qJ\u00196fGR\u0004"
)
public class CacheEntry implements Product, Serializable {
   private final URI uri;
   private final long size;
   private final long modTime;
   private final LocalResourceVisibility visibility;
   private final LocalResourceType resType;

   public static Option unapply(final CacheEntry x$0) {
      return CacheEntry$.MODULE$.unapply(x$0);
   }

   public static CacheEntry apply(final URI uri, final long size, final long modTime, final LocalResourceVisibility visibility, final LocalResourceType resType) {
      return CacheEntry$.MODULE$.apply(uri, size, modTime, visibility, resType);
   }

   public static Function1 tupled() {
      return CacheEntry$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return CacheEntry$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public URI uri() {
      return this.uri;
   }

   public long size() {
      return this.size;
   }

   public long modTime() {
      return this.modTime;
   }

   public LocalResourceVisibility visibility() {
      return this.visibility;
   }

   public LocalResourceType resType() {
      return this.resType;
   }

   public CacheEntry copy(final URI uri, final long size, final long modTime, final LocalResourceVisibility visibility, final LocalResourceType resType) {
      return new CacheEntry(uri, size, modTime, visibility, resType);
   }

   public URI copy$default$1() {
      return this.uri();
   }

   public long copy$default$2() {
      return this.size();
   }

   public long copy$default$3() {
      return this.modTime();
   }

   public LocalResourceVisibility copy$default$4() {
      return this.visibility();
   }

   public LocalResourceType copy$default$5() {
      return this.resType();
   }

   public String productPrefix() {
      return "CacheEntry";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.uri();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.size());
         }
         case 2 -> {
            return BoxesRunTime.boxToLong(this.modTime());
         }
         case 3 -> {
            return this.visibility();
         }
         case 4 -> {
            return this.resType();
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
      return x$1 instanceof CacheEntry;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "uri";
         }
         case 1 -> {
            return "size";
         }
         case 2 -> {
            return "modTime";
         }
         case 3 -> {
            return "visibility";
         }
         case 4 -> {
            return "resType";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.uri()));
      var1 = Statics.mix(var1, Statics.longHash(this.size()));
      var1 = Statics.mix(var1, Statics.longHash(this.modTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.visibility()));
      var1 = Statics.mix(var1, Statics.anyHash(this.resType()));
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof CacheEntry) {
               CacheEntry var4 = (CacheEntry)x$1;
               if (this.size() == var4.size() && this.modTime() == var4.modTime()) {
                  label64: {
                     URI var10000 = this.uri();
                     URI var5 = var4.uri();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label64;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label64;
                     }

                     LocalResourceVisibility var8 = this.visibility();
                     LocalResourceVisibility var6 = var4.visibility();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label64;
                        }
                     } else if (!var8.equals(var6)) {
                        break label64;
                     }

                     LocalResourceType var9 = this.resType();
                     LocalResourceType var7 = var4.resType();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label64;
                        }
                     } else if (!var9.equals(var7)) {
                        break label64;
                     }

                     if (var4.canEqual(this)) {
                        break label71;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public CacheEntry(final URI uri, final long size, final long modTime, final LocalResourceVisibility visibility, final LocalResourceType resType) {
      this.uri = uri;
      this.size = size;
      this.modTime = modTime;
      this.visibility = visibility;
      this.resType = resType;
      Product.$init$(this);
   }
}
