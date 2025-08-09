package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.executor.TaskMetrics;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005md\u0001\u0002\u000f\u001e\t\u001aB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005}!A!\t\u0001BK\u0002\u0013\u00051\t\u0003\u0005H\u0001\tE\t\u0015!\u0003E\u0011!A\u0005A!f\u0001\n\u0003i\u0004\u0002C%\u0001\u0005#\u0005\u000b\u0011\u0002 \t\u000b)\u0003A\u0011A&\t\u000fE\u0003\u0011\u0011!C\u0001%\"9a\u000bAI\u0001\n\u00039\u0006b\u00022\u0001#\u0003%\ta\u0019\u0005\bK\u0002\t\n\u0011\"\u0001X\u0011\u001d1\u0007!!A\u0005B\u001dDq\u0001\u001d\u0001\u0002\u0002\u0013\u0005\u0011\u000fC\u0004v\u0001\u0005\u0005I\u0011\u0001<\t\u000fq\u0004\u0011\u0011!C!{\"I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00111\u0002\u0005\n\u0003+\u0001\u0011\u0011!C!\u0003/A\u0011\"a\u0007\u0001\u0003\u0003%\t%!\b\t\u0013\u0005}\u0001!!A\u0005B\u0005\u0005\u0002\"CA\u0012\u0001\u0005\u0005I\u0011IA\u0013\u000f\u001d\tI#\bE\u0005\u0003W1a\u0001H\u000f\t\n\u00055\u0002B\u0002&\u0017\t\u0003\tI\u0004C\u0004\u0002<Y!\t!!\u0010\t\u0013\u0005mb#!A\u0005\u0002\u0006e\u0003\"CA1-\u0005\u0005I\u0011QA2\u0011%\t\tHFA\u0001\n\u0013\t\u0019HA\tSk:$\u0018.\\3QKJ\u001cWM\u001c;bO\u0016T!AH\u0010\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001\u0011\"\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00113%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002I\u0005\u0019qN]4\u0004\u0001M!\u0001aJ\u00171!\tA3&D\u0001*\u0015\u0005Q\u0013!B:dC2\f\u0017B\u0001\u0017*\u0005\u0019\te.\u001f*fMB\u0011\u0001FL\u0005\u0003_%\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00022s9\u0011!g\u000e\b\u0003gYj\u0011\u0001\u000e\u0006\u0003k\u0015\na\u0001\u0010:p_Rt\u0014\"\u0001\u0016\n\u0005aJ\u0013a\u00029bG.\fw-Z\u0005\u0003um\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001O\u0015\u0002\u0017\u0015DXmY;u_J\u00046\r^\u000b\u0002}A\u0011\u0001fP\u0005\u0003\u0001&\u0012a\u0001R8vE2,\u0017\u0001D3yK\u000e,Ho\u001c:QGR\u0004\u0013\u0001\u00034fi\u000eD\u0007k\u0019;\u0016\u0003\u0011\u00032\u0001K#?\u0013\t1\u0015F\u0001\u0004PaRLwN\\\u0001\nM\u0016$8\r\u001b)di\u0002\nQa\u001c;iKJ\faa\u001c;iKJ\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003M\u001d>\u0003\u0006CA'\u0001\u001b\u0005i\u0002\"\u0002\u001f\b\u0001\u0004q\u0004\"\u0002\"\b\u0001\u0004!\u0005\"\u0002%\b\u0001\u0004q\u0014\u0001B2paf$B\u0001T*U+\"9A\b\u0003I\u0001\u0002\u0004q\u0004b\u0002\"\t!\u0003\u0005\r\u0001\u0012\u0005\b\u0011\"\u0001\n\u00111\u0001?\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u0017\u0016\u0003}e[\u0013A\u0017\t\u00037\u0002l\u0011\u0001\u0018\u0006\u0003;z\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005}K\u0013AC1o]>$\u0018\r^5p]&\u0011\u0011\r\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002I*\u0012A)W\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0001\u000e\u0005\u0002j]6\t!N\u0003\u0002lY\u0006!A.\u00198h\u0015\u0005i\u0017\u0001\u00026bm\u0006L!a\u001c6\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005\u0011\bC\u0001\u0015t\u0013\t!\u0018FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002xuB\u0011\u0001\u0006_\u0005\u0003s&\u00121!\u00118z\u0011\u001dYh\"!AA\u0002I\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001@\u0011\t}\f)a^\u0007\u0003\u0003\u0003Q1!a\u0001*\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u000f\t\tA\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0007\u0003'\u00012\u0001KA\b\u0013\r\t\t\"\u000b\u0002\b\u0005>|G.Z1o\u0011\u001dY\b#!AA\u0002]\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019\u0001.!\u0007\t\u000fm\f\u0012\u0011!a\u0001e\u0006A\u0001.Y:i\u0007>$W\rF\u0001s\u0003!!xn\u0015;sS:<G#\u00015\u0002\r\u0015\fX/\u00197t)\u0011\ti!a\n\t\u000fm$\u0012\u0011!a\u0001o\u0006\t\"+\u001e8uS6,\u0007+\u001a:dK:$\u0018mZ3\u0011\u0005532\u0003\u0002\f(\u0003_\u0001B!!\r\u000285\u0011\u00111\u0007\u0006\u0004\u0003ka\u0017AA5p\u0013\rQ\u00141\u0007\u000b\u0003\u0003W\tQ!\u00199qYf$R\u0001TA \u0003\u0013Bq!!\u0011\u0019\u0001\u0004\t\u0019%A\u0005u_R\fG\u000eV5nKB\u0019\u0001&!\u0012\n\u0007\u0005\u001d\u0013F\u0001\u0003M_:<\u0007bBA&1\u0001\u0007\u0011QJ\u0001\b[\u0016$(/[2t!\u0011\ty%!\u0016\u000e\u0005\u0005E#bAA*?\u0005AQ\r_3dkR|'/\u0003\u0003\u0002X\u0005E#a\u0003+bg.lU\r\u001e:jGN$r\u0001TA.\u0003;\ny\u0006C\u0003=3\u0001\u0007a\bC\u0003C3\u0001\u0007A\tC\u0003I3\u0001\u0007a(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u0015\u0014Q\u000e\t\u0005Q\u0015\u000b9\u0007\u0005\u0004)\u0003SrDIP\u0005\u0004\u0003WJ#A\u0002+va2,7\u0007\u0003\u0005\u0002pi\t\t\u00111\u0001M\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003k\u00022![A<\u0013\r\tIH\u001b\u0002\u0007\u001f\nTWm\u0019;"
)
public class RuntimePercentage implements Product, Serializable {
   private final double executorPct;
   private final Option fetchPct;
   private final double other;

   public static Option unapply(final RuntimePercentage x$0) {
      return RuntimePercentage$.MODULE$.unapply(x$0);
   }

   public static RuntimePercentage apply(final double executorPct, final Option fetchPct, final double other) {
      return RuntimePercentage$.MODULE$.apply(executorPct, fetchPct, other);
   }

   public static RuntimePercentage apply(final long totalTime, final TaskMetrics metrics) {
      return RuntimePercentage$.MODULE$.apply(totalTime, metrics);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double executorPct() {
      return this.executorPct;
   }

   public Option fetchPct() {
      return this.fetchPct;
   }

   public double other() {
      return this.other;
   }

   public RuntimePercentage copy(final double executorPct, final Option fetchPct, final double other) {
      return new RuntimePercentage(executorPct, fetchPct, other);
   }

   public double copy$default$1() {
      return this.executorPct();
   }

   public Option copy$default$2() {
      return this.fetchPct();
   }

   public double copy$default$3() {
      return this.other();
   }

   public String productPrefix() {
      return "RuntimePercentage";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToDouble(this.executorPct());
         }
         case 1 -> {
            return this.fetchPct();
         }
         case 2 -> {
            return BoxesRunTime.boxToDouble(this.other());
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
      return x$1 instanceof RuntimePercentage;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "executorPct";
         }
         case 1 -> {
            return "fetchPct";
         }
         case 2 -> {
            return "other";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.executorPct()));
      var1 = Statics.mix(var1, Statics.anyHash(this.fetchPct()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.other()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof RuntimePercentage) {
               RuntimePercentage var4 = (RuntimePercentage)x$1;
               if (this.executorPct() == var4.executorPct() && this.other() == var4.other()) {
                  label48: {
                     Option var10000 = this.fetchPct();
                     Option var5 = var4.fetchPct();
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

   public RuntimePercentage(final double executorPct, final Option fetchPct, final double other) {
      this.executorPct = executorPct;
      this.fetchPct = fetchPct;
      this.other = other;
      Product.$init$(this);
   }
}
