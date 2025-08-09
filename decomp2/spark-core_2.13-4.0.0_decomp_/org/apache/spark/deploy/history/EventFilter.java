package org.apache.spark.deploy.history;

import java.io.Serializable;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.internal.Logging;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple6;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tEc\u0001\u0003\u0017.!\u0003\r\n!M\u001c\t\u000by\u0002a\u0011\u0001!\t\u000f\t\u001d\u0003A\"\u0001\u0003J\u001d11+\fE\u0001cQ3a\u0001L\u0017\t\u0002E2\u0006\"B/\u0005\t\u0003qf\u0001B0\u0005\u0001\u0002D\u0001\"\u001c\u0004\u0003\u0016\u0004%\tA\u001c\u0005\te\u001a\u0011\t\u0012)A\u0005_\"A1O\u0002BK\u0002\u0013\u0005a\u000e\u0003\u0005u\r\tE\t\u0015!\u0003p\u0011!)hA!f\u0001\n\u0003q\u0007\u0002\u0003<\u0007\u0005#\u0005\u000b\u0011B8\t\u0011]4!Q3A\u0005\u00029D\u0001\u0002\u001f\u0004\u0003\u0012\u0003\u0006Ia\u001c\u0005\ts\u001a\u0011)\u001a!C\u0001]\"A!P\u0002B\tB\u0003%q\u000e\u0003\u0005|\r\tU\r\u0011\"\u0001o\u0011!ahA!E!\u0002\u0013y\u0007\"B/\u0007\t\u0003i\b\"CA\u0007\r\u0005\u0005I\u0011AA\b\u0011%\tiBBI\u0001\n\u0003\ty\u0002C\u0005\u00026\u0019\t\n\u0011\"\u0001\u0002 !I\u0011q\u0007\u0004\u0012\u0002\u0013\u0005\u0011q\u0004\u0005\n\u0003s1\u0011\u0013!C\u0001\u0003?A\u0011\"a\u000f\u0007#\u0003%\t!a\b\t\u0013\u0005ub!%A\u0005\u0002\u0005}\u0001\"CA \r\u0005\u0005I\u0011IA!\u0011%\t\u0019FBA\u0001\n\u0003\t)\u0006C\u0005\u0002^\u0019\t\t\u0011\"\u0001\u0002`!I\u00111\u000e\u0004\u0002\u0002\u0013\u0005\u0013Q\u000e\u0005\n\u0003w2\u0011\u0011!C\u0001\u0003{B\u0011\"a\"\u0007\u0003\u0003%\t%!#\t\u0013\u00055e!!A\u0005B\u0005=\u0005\"CAI\r\u0005\u0005I\u0011IAJ\u0011%\t)JBA\u0001\n\u0003\n9jB\u0005\u0002\u001c\u0012\t\t\u0011#\u0001\u0002\u001e\u001aAq\fBA\u0001\u0012\u0003\ty\n\u0003\u0004^K\u0011\u0005\u0011q\u0017\u0005\n\u0003#+\u0013\u0011!C#\u0003'C\u0011\"!/&\u0003\u0003%\t)a/\t\u0013\u0005%W%!A\u0005\u0002\u0006-\u0007\"CAmK\u0005\u0005I\u0011BAn\u0011\u001d\t\u0019\u000f\u0002C\u0001\u0003K\u00141\"\u0012<f]R4\u0015\u000e\u001c;fe*\u0011afL\u0001\bQ&\u001cHo\u001c:z\u0015\t\u0001\u0014'\u0001\u0004eKBdw.\u001f\u0006\u0003eM\nQa\u001d9be.T!\u0001N\u001b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0014aA8sON\u0011\u0001\u0001\u000f\t\u0003sqj\u0011A\u000f\u0006\u0002w\u0005)1oY1mC&\u0011QH\u000f\u0002\u0007\u0003:L(+\u001a4\u0002\u0015M$\u0018\r^5ti&\u001c7o\u0001\u0001\u0015\u0003\u0005\u00032!\u000f\"E\u0013\t\u0019%H\u0001\u0004PaRLwN\u001c\t\u0003\u000b\u001aq!AR\u0002\u000f\u0005\u001d\u0013fB\u0001%R\u001d\tI\u0005K\u0004\u0002K\u001f:\u00111JT\u0007\u0002\u0019*\u0011QjP\u0001\u0007yI|w\u000e\u001e \n\u0003YJ!\u0001N\u001b\n\u0005I\u001a\u0014B\u0001\u00192\u0013\tqs&A\u0006Fm\u0016tGOR5mi\u0016\u0014\bCA+\u0005\u001b\u0005i3c\u0001\u00039/B\u0011\u0001lW\u0007\u00023*\u0011!,M\u0001\tS:$XM\u001d8bY&\u0011A,\u0017\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}Q\tAK\u0001\tGS2$XM]*uCRL7\u000f^5dgN!a\u0001O1e!\tI$-\u0003\u0002du\t9\u0001K]8ek\u000e$\bCA3k\u001d\t1\u0007N\u0004\u0002LO&\t1(\u0003\u0002ju\u00059\u0001/Y2lC\u001e,\u0017BA6m\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tI'(A\u0005u_R\fGNS8cgV\tq\u000e\u0005\u0002:a&\u0011\u0011O\u000f\u0002\u0005\u0019>tw-\u0001\u0006u_R\fGNS8cg\u0002\n\u0001\u0002\\5wK*{'m]\u0001\nY&4XMS8cg\u0002\n1\u0002^8uC2\u001cF/Y4fg\u0006aAo\u001c;bYN#\u0018mZ3tA\u0005QA.\u001b<f'R\fw-Z:\u0002\u00171Lg/Z*uC\u001e,7\u000fI\u0001\u000bi>$\u0018\r\u001c+bg.\u001c\u0018a\u0003;pi\u0006dG+Y:lg\u0002\n\u0011\u0002\\5wKR\u000b7o[:\u0002\u00151Lg/\u001a+bg.\u001c\b\u0005F\u0007\u007f\u0003\u0003\t\u0019!!\u0002\u0002\b\u0005%\u00111\u0002\t\u0003\u007f\u001ai\u0011\u0001\u0002\u0005\u0006[N\u0001\ra\u001c\u0005\u0006gN\u0001\ra\u001c\u0005\u0006kN\u0001\ra\u001c\u0005\u0006oN\u0001\ra\u001c\u0005\u0006sN\u0001\ra\u001c\u0005\u0006wN\u0001\ra\\\u0001\u0005G>\u0004\u0018\u0010F\u0007\u007f\u0003#\t\u0019\"!\u0006\u0002\u0018\u0005e\u00111\u0004\u0005\b[R\u0001\n\u00111\u0001p\u0011\u001d\u0019H\u0003%AA\u0002=Dq!\u001e\u000b\u0011\u0002\u0003\u0007q\u000eC\u0004x)A\u0005\t\u0019A8\t\u000fe$\u0002\u0013!a\u0001_\"91\u0010\u0006I\u0001\u0002\u0004y\u0017AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003CQ3a\\A\u0012W\t\t)\u0003\u0005\u0003\u0002(\u0005ERBAA\u0015\u0015\u0011\tY#!\f\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0018u\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005M\u0012\u0011\u0006\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ\nabY8qs\u0012\"WMZ1vYR$S'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001c\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t\u0019\u0005\u0005\u0003\u0002F\u0005=SBAA$\u0015\u0011\tI%a\u0013\u0002\t1\fgn\u001a\u0006\u0003\u0003\u001b\nAA[1wC&!\u0011\u0011KA$\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011q\u000b\t\u0004s\u0005e\u0013bAA.u\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011MA4!\rI\u00141M\u0005\u0004\u0003KR$aA!os\"I\u0011\u0011N\u000f\u0002\u0002\u0003\u0007\u0011qK\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005=\u0004CBA9\u0003o\n\t'\u0004\u0002\u0002t)\u0019\u0011Q\u000f\u001e\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002z\u0005M$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a \u0002\u0006B\u0019\u0011(!!\n\u0007\u0005\r%HA\u0004C_>dW-\u00198\t\u0013\u0005%t$!AA\u0002\u0005\u0005\u0014A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a\u0011\u0002\f\"I\u0011\u0011\u000e\u0011\u0002\u0002\u0003\u0007\u0011qK\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011qK\u0001\ti>\u001cFO]5oOR\u0011\u00111I\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005}\u0014\u0011\u0014\u0005\n\u0003S\u001a\u0013\u0011!a\u0001\u0003C\n\u0001CR5mi\u0016\u00148\u000b^1uSN$\u0018nY:\u0011\u0005},3#B\u0013\u0002\"\u00065\u0006cCAR\u0003S{wn\\8p_zl!!!*\u000b\u0007\u0005\u001d&(A\u0004sk:$\u0018.\\3\n\t\u0005-\u0016Q\u0015\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:4\u0004\u0003BAX\u0003kk!!!-\u000b\t\u0005M\u00161J\u0001\u0003S>L1a[AY)\t\ti*A\u0003baBd\u0017\u0010F\u0007\u007f\u0003{\u000by,!1\u0002D\u0006\u0015\u0017q\u0019\u0005\u0006[\"\u0002\ra\u001c\u0005\u0006g\"\u0002\ra\u001c\u0005\u0006k\"\u0002\ra\u001c\u0005\u0006o\"\u0002\ra\u001c\u0005\u0006s\"\u0002\ra\u001c\u0005\u0006w\"\u0002\ra\\\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti-!6\u0011\te\u0012\u0015q\u001a\t\ns\u0005Ewn\\8p_>L1!a5;\u0005\u0019!V\u000f\u001d7fm!A\u0011q[\u0015\u0002\u0002\u0003\u0007a0A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!8\u0011\t\u0005\u0015\u0013q\\\u0005\u0005\u0003C\f9E\u0001\u0004PE*,7\r^\u0001\u0012CB\u0004H.\u001f$jYR,'\u000fV8GS2,GCDAt\u0003[\fyPa\u0003\u0003\u0016\te\"Q\b\t\u0004s\u0005%\u0018bAAvu\t!QK\\5u\u0011\u001d\tyo\u000ba\u0001\u0003c\f!AZ:\u0011\t\u0005M\u00181`\u0007\u0003\u0003kTA!a<\u0002x*\u0019\u0011\u0011`\u001a\u0002\r!\fGm\\8q\u0013\u0011\ti0!>\u0003\u0015\u0019KG.Z*zgR,W\u000eC\u0004\u0003\u0002-\u0002\rAa\u0001\u0002\u000f\u0019LG\u000e^3sgB)QM!\u0002\u0003\n%\u0019!q\u00017\u0003\u0007M+\u0017\u000f\u0005\u0002V\u0001!9!QB\u0016A\u0002\t=\u0011\u0001\u00029bi\"\u0004B!a=\u0003\u0012%!!1CA{\u0005\u0011\u0001\u0016\r\u001e5\t\u000f\t]1\u00061\u0001\u0003\u001a\u0005QqN\\!dG\u0016\u0004H/\u001a3\u0011\u0013e\u0012YBa\b\u0003.\u0005\u001d\u0018b\u0001B\u000fu\tIa)\u001e8di&|gN\r\t\u0005\u0005C\u0011IC\u0004\u0003\u0003$\t\u0015\u0002CA&;\u0013\r\u00119CO\u0001\u0007!J,G-\u001a4\n\t\u0005E#1\u0006\u0006\u0004\u0005OQ\u0004\u0003\u0002B\u0018\u0005ki!A!\r\u000b\u0007\tM\u0012'A\u0005tG\",G-\u001e7fe&!!q\u0007B\u0019\u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\t\u000f\tm2\u00061\u0001\u0003\u001a\u0005QqN\u001c*fU\u0016\u001cG/\u001a3\t\u000f\t}2\u00061\u0001\u0003B\u0005qqN\\+oS\u0012,g\u000e^5gS\u0016$\u0007cB\u001d\u0003D\t}\u0011q]\u0005\u0004\u0005\u000bR$!\u0003$v]\u000e$\u0018n\u001c82\u0003!\t7mY3qi\u001asGC\u0001B&!\u001dI$Q\nB\u0017\u0003\u007fJ1Aa\u0014;\u0005=\u0001\u0016M\u001d;jC24UO\\2uS>t\u0007"
)
public interface EventFilter {
   static void applyFilterToFile(final FileSystem fs, final Seq filters, final Path path, final Function2 onAccepted, final Function2 onRejected, final Function1 onUnidentified) {
      EventFilter$.MODULE$.applyFilterToFile(fs, filters, path, onAccepted, onRejected, onUnidentified);
   }

   static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return EventFilter$.MODULE$.LogStringContext(sc);
   }

   Option statistics();

   PartialFunction acceptFn();

   public static class FilterStatistics implements Product, Serializable {
      private final long totalJobs;
      private final long liveJobs;
      private final long totalStages;
      private final long liveStages;
      private final long totalTasks;
      private final long liveTasks;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public long totalJobs() {
         return this.totalJobs;
      }

      public long liveJobs() {
         return this.liveJobs;
      }

      public long totalStages() {
         return this.totalStages;
      }

      public long liveStages() {
         return this.liveStages;
      }

      public long totalTasks() {
         return this.totalTasks;
      }

      public long liveTasks() {
         return this.liveTasks;
      }

      public FilterStatistics copy(final long totalJobs, final long liveJobs, final long totalStages, final long liveStages, final long totalTasks, final long liveTasks) {
         return new FilterStatistics(totalJobs, liveJobs, totalStages, liveStages, totalTasks, liveTasks);
      }

      public long copy$default$1() {
         return this.totalJobs();
      }

      public long copy$default$2() {
         return this.liveJobs();
      }

      public long copy$default$3() {
         return this.totalStages();
      }

      public long copy$default$4() {
         return this.liveStages();
      }

      public long copy$default$5() {
         return this.totalTasks();
      }

      public long copy$default$6() {
         return this.liveTasks();
      }

      public String productPrefix() {
         return "FilterStatistics";
      }

      public int productArity() {
         return 6;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToLong(this.totalJobs());
            }
            case 1 -> {
               return BoxesRunTime.boxToLong(this.liveJobs());
            }
            case 2 -> {
               return BoxesRunTime.boxToLong(this.totalStages());
            }
            case 3 -> {
               return BoxesRunTime.boxToLong(this.liveStages());
            }
            case 4 -> {
               return BoxesRunTime.boxToLong(this.totalTasks());
            }
            case 5 -> {
               return BoxesRunTime.boxToLong(this.liveTasks());
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
         return x$1 instanceof FilterStatistics;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "totalJobs";
            }
            case 1 -> {
               return "liveJobs";
            }
            case 2 -> {
               return "totalStages";
            }
            case 3 -> {
               return "liveStages";
            }
            case 4 -> {
               return "totalTasks";
            }
            case 5 -> {
               return "liveTasks";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.longHash(this.totalJobs()));
         var1 = Statics.mix(var1, Statics.longHash(this.liveJobs()));
         var1 = Statics.mix(var1, Statics.longHash(this.totalStages()));
         var1 = Statics.mix(var1, Statics.longHash(this.liveStages()));
         var1 = Statics.mix(var1, Statics.longHash(this.totalTasks()));
         var1 = Statics.mix(var1, Statics.longHash(this.liveTasks()));
         return Statics.finalizeHash(var1, 6);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label46: {
               if (x$1 instanceof FilterStatistics) {
                  FilterStatistics var4 = (FilterStatistics)x$1;
                  if (this.totalJobs() == var4.totalJobs() && this.liveJobs() == var4.liveJobs() && this.totalStages() == var4.totalStages() && this.liveStages() == var4.liveStages() && this.totalTasks() == var4.totalTasks() && this.liveTasks() == var4.liveTasks() && var4.canEqual(this)) {
                     break label46;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public FilterStatistics(final long totalJobs, final long liveJobs, final long totalStages, final long liveStages, final long totalTasks, final long liveTasks) {
         this.totalJobs = totalJobs;
         this.liveJobs = liveJobs;
         this.totalStages = totalStages;
         this.liveStages = liveStages;
         this.totalTasks = totalTasks;
         this.liveTasks = liveTasks;
         Product.$init$(this);
      }
   }

   public static class FilterStatistics$ extends AbstractFunction6 implements Serializable {
      public static final FilterStatistics$ MODULE$ = new FilterStatistics$();

      public final String toString() {
         return "FilterStatistics";
      }

      public FilterStatistics apply(final long totalJobs, final long liveJobs, final long totalStages, final long liveStages, final long totalTasks, final long liveTasks) {
         return new FilterStatistics(totalJobs, liveJobs, totalStages, liveStages, totalTasks, liveTasks);
      }

      public Option unapply(final FilterStatistics x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple6(BoxesRunTime.boxToLong(x$0.totalJobs()), BoxesRunTime.boxToLong(x$0.liveJobs()), BoxesRunTime.boxToLong(x$0.totalStages()), BoxesRunTime.boxToLong(x$0.liveStages()), BoxesRunTime.boxToLong(x$0.totalTasks()), BoxesRunTime.boxToLong(x$0.liveTasks()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(FilterStatistics$.class);
      }
   }
}
