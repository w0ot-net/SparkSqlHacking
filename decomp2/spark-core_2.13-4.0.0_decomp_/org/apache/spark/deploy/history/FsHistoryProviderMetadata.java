package org.apache.spark.deploy.history;

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
   bytes = "\u0006\u0005\u0005md!\u0002\u000f\u001e\u0001\u0006:\u0003\u0002\u0003 \u0001\u0005+\u0007I\u0011A \t\u0011\r\u0003!\u0011#Q\u0001\n\u0001C\u0001\u0002\u0012\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0001\"Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005Q\u0001\tE\t\u0015!\u0003I\u0011\u0015\t\u0006\u0001\"\u0001S\u0011\u001dA\u0006!!A\u0005\u0002eCq!\u0018\u0001\u0012\u0002\u0013\u0005a\fC\u0004j\u0001E\u0005I\u0011\u00010\t\u000f)\u0004\u0011\u0013!C\u0001W\"9Q\u000eAA\u0001\n\u0003r\u0007b\u0002<\u0001\u0003\u0003%\ta\u001e\u0005\bw\u0002\t\t\u0011\"\u0001}\u0011%\t)\u0001AA\u0001\n\u0003\n9\u0001C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0001\u0002\u0018!I\u0011\u0011\u0005\u0001\u0002\u0002\u0013\u0005\u00131\u0005\u0005\n\u0003O\u0001\u0011\u0011!C!\u0003SA\u0011\"a\u000b\u0001\u0003\u0003%\t%!\f\t\u0013\u0005=\u0002!!A\u0005B\u0005ErACA\u001b;\u0005\u0005\t\u0012A\u0011\u00028\u0019IA$HA\u0001\u0012\u0003\t\u0013\u0011\b\u0005\u0007#Z!\t!!\u0015\t\u0013\u0005-b#!A\u0005F\u00055\u0002\"CA*-\u0005\u0005I\u0011QA+\u0011%\tiFFA\u0001\n\u0003\u000by\u0006C\u0005\u0002rY\t\t\u0011\"\u0003\u0002t\tIbi\u001d%jgR|'/\u001f)s_ZLG-\u001a:NKR\fG-\u0019;b\u0015\tqr$A\u0004iSN$xN]=\u000b\u0005\u0001\n\u0013A\u00023fa2|\u0017P\u0003\u0002#G\u0005)1\u000f]1sW*\u0011A%J\u0001\u0007CB\f7\r[3\u000b\u0003\u0019\n1a\u001c:h'\u0011\u0001\u0001FL\u0019\u0011\u0005%bS\"\u0001\u0016\u000b\u0003-\nQa]2bY\u0006L!!\f\u0016\u0003\r\u0005s\u0017PU3g!\tIs&\u0003\u00021U\t9\u0001K]8ek\u000e$\bC\u0001\u001a<\u001d\t\u0019\u0014H\u0004\u00025q5\tQG\u0003\u00027o\u00051AH]8piz\u001a\u0001!C\u0001,\u0013\tQ$&A\u0004qC\u000e\\\u0017mZ3\n\u0005qj$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001e+\u0003\u001d1XM]:j_:,\u0012\u0001\u0011\t\u0003S\u0005K!A\u0011\u0016\u0003\t1{gnZ\u0001\tm\u0016\u00148/[8oA\u0005IQ/\u001b,feNLwN\\\u0001\u000bk&4VM]:j_:\u0004\u0013A\u00027pO\u0012K'/F\u0001I!\tIUJ\u0004\u0002K\u0017B\u0011AGK\u0005\u0003\u0019*\na\u0001\u0015:fI\u00164\u0017B\u0001(P\u0005\u0019\u0019FO]5oO*\u0011AJK\u0001\bY><G)\u001b:!\u0003\u0019a\u0014N\\5u}Q!1+\u0016,X!\t!\u0006!D\u0001\u001e\u0011\u0015qt\u00011\u0001A\u0011\u0015!u\u00011\u0001A\u0011\u00151u\u00011\u0001I\u0003\u0011\u0019w\u000e]=\u0015\tMS6\f\u0018\u0005\b}!\u0001\n\u00111\u0001A\u0011\u001d!\u0005\u0002%AA\u0002\u0001CqA\u0012\u0005\u0011\u0002\u0003\u0007\u0001*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003}S#\u0001\u00111,\u0003\u0005\u0004\"AY4\u000e\u0003\rT!\u0001Z3\u0002\u0013Ut7\r[3dW\u0016$'B\u00014+\u0003)\tgN\\8uCRLwN\\\u0005\u0003Q\u000e\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nabY8qs\u0012\"WMZ1vYR$3'F\u0001mU\tA\u0005-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002_B\u0011\u0001/^\u0007\u0002c*\u0011!o]\u0001\u0005Y\u0006twMC\u0001u\u0003\u0011Q\u0017M^1\n\u00059\u000b\u0018\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001=\u0011\u0005%J\u0018B\u0001>+\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\ri\u0018\u0011\u0001\t\u0003SyL!a \u0016\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002\u00049\t\t\u00111\u0001y\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0002\t\u0006\u0003\u0017\t\t\"`\u0007\u0003\u0003\u001bQ1!a\u0004+\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003'\tiA\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\r\u0003?\u00012!KA\u000e\u0013\r\tiB\u000b\u0002\b\u0005>|G.Z1o\u0011!\t\u0019\u0001EA\u0001\u0002\u0004i\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2a\\A\u0013\u0011!\t\u0019!EA\u0001\u0002\u0004A\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003a\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002_\u00061Q-];bYN$B!!\u0007\u00024!A\u00111\u0001\u000b\u0002\u0002\u0003\u0007Q0A\rGg\"K7\u000f^8ssB\u0013xN^5eKJlU\r^1eCR\f\u0007C\u0001+\u0017'\u00151\u00121HA$!!\ti$a\u0011A\u0001\"\u001bVBAA \u0015\r\t\tEK\u0001\beVtG/[7f\u0013\u0011\t)%a\u0010\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\u0005\u0003\u0002J\u0005=SBAA&\u0015\r\tie]\u0001\u0003S>L1\u0001PA&)\t\t9$A\u0003baBd\u0017\u0010F\u0004T\u0003/\nI&a\u0017\t\u000byJ\u0002\u0019\u0001!\t\u000b\u0011K\u0002\u0019\u0001!\t\u000b\u0019K\u0002\u0019\u0001%\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011MA7!\u0015I\u00131MA4\u0013\r\t)G\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r%\nI\u0007\u0011!I\u0013\r\tYG\u000b\u0002\u0007)V\u0004H.Z\u001a\t\u0011\u0005=$$!AA\u0002M\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\bE\u0002q\u0003oJ1!!\u001fr\u0005\u0019y%M[3di\u0002"
)
public class FsHistoryProviderMetadata implements Product, Serializable {
   private final long version;
   private final long uiVersion;
   private final String logDir;

   public static Option unapply(final FsHistoryProviderMetadata x$0) {
      return FsHistoryProviderMetadata$.MODULE$.unapply(x$0);
   }

   public static FsHistoryProviderMetadata apply(final long version, final long uiVersion, final String logDir) {
      return FsHistoryProviderMetadata$.MODULE$.apply(version, uiVersion, logDir);
   }

   public static Function1 tupled() {
      return FsHistoryProviderMetadata$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return FsHistoryProviderMetadata$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long version() {
      return this.version;
   }

   public long uiVersion() {
      return this.uiVersion;
   }

   public String logDir() {
      return this.logDir;
   }

   public FsHistoryProviderMetadata copy(final long version, final long uiVersion, final String logDir) {
      return new FsHistoryProviderMetadata(version, uiVersion, logDir);
   }

   public long copy$default$1() {
      return this.version();
   }

   public long copy$default$2() {
      return this.uiVersion();
   }

   public String copy$default$3() {
      return this.logDir();
   }

   public String productPrefix() {
      return "FsHistoryProviderMetadata";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.version());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.uiVersion());
         }
         case 2 -> {
            return this.logDir();
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
      return x$1 instanceof FsHistoryProviderMetadata;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "version";
         }
         case 1 -> {
            return "uiVersion";
         }
         case 2 -> {
            return "logDir";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.version()));
      var1 = Statics.mix(var1, Statics.longHash(this.uiVersion()));
      var1 = Statics.mix(var1, Statics.anyHash(this.logDir()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof FsHistoryProviderMetadata) {
               FsHistoryProviderMetadata var4 = (FsHistoryProviderMetadata)x$1;
               if (this.version() == var4.version() && this.uiVersion() == var4.uiVersion()) {
                  label48: {
                     String var10000 = this.logDir();
                     String var5 = var4.logDir();
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

   public FsHistoryProviderMetadata(final long version, final long uiVersion, final String logDir) {
      this.version = version;
      this.uiVersion = uiVersion;
      this.logDir = logDir;
      Product.$init$(this);
   }
}
