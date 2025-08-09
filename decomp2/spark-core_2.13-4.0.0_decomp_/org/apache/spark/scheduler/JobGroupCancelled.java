package org.apache.spark.scheduler;

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
   bytes = "\u0006\u0005\u0005\u001de!\u0002\u0010 \u0001~9\u0003\u0002\u0003\"\u0001\u0005+\u0007I\u0011A\"\t\u00111\u0003!\u0011#Q\u0001\n\u0011C\u0001\"\u0014\u0001\u0003\u0016\u0004%\tA\u0014\u0005\t%\u0002\u0011\t\u0012)A\u0005\u001f\"A1\u000b\u0001BK\u0002\u0013\u0005A\u000b\u0003\u0005Y\u0001\tE\t\u0015!\u0003V\u0011\u0015I\u0006\u0001\"\u0001[\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dq\u0001\u001a\u0001\u0012\u0002\u0013\u0005Q\rC\u0004q\u0001E\u0005I\u0011A9\t\u000fM\u0004\u0011\u0013!C\u0001i\"9a\u000fAA\u0001\n\u0003:\b\u0002C@\u0001\u0003\u0003%\t!!\u0001\t\u0013\u0005%\u0001!!A\u0005\u0002\u0005-\u0001\"CA\f\u0001\u0005\u0005I\u0011IA\r\u0011%\t9\u0003AA\u0001\n\u0003\tI\u0003C\u0005\u0002.\u0001\t\t\u0011\"\u0011\u00020!I\u00111\u0007\u0001\u0002\u0002\u0013\u0005\u0013Q\u0007\u0005\n\u0003o\u0001\u0011\u0011!C!\u0003sA\u0011\"a\u000f\u0001\u0003\u0003%\t%!\u0010\b\u0015\u0005\u0005s$!A\t\u0002}\t\u0019EB\u0005\u001f?\u0005\u0005\t\u0012A\u0010\u0002F!1\u0011L\u0006C\u0001\u0003;B\u0011\"a\u000e\u0017\u0003\u0003%)%!\u000f\t\u0013\u0005}c#!A\u0005\u0002\u0006\u0005\u0004\u0002CA5-E\u0005I\u0011A9\t\u0013\u0005-d#!A\u0005\u0002\u00065\u0004\u0002CA>-E\u0005I\u0011A9\t\u0013\u0005ud#!A\u0005\n\u0005}$!\u0005&pE\u001e\u0013x.\u001e9DC:\u001cW\r\u001c7fI*\u0011\u0001%I\u0001\ng\u000eDW\rZ;mKJT!AI\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0011*\u0013AB1qC\u000eDWMC\u0001'\u0003\ry'oZ\n\u0006\u0001!r#'\u000e\t\u0003S1j\u0011A\u000b\u0006\u0002W\u0005)1oY1mC&\u0011QF\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005=\u0002T\"A\u0010\n\u0005Ez\"!\u0005#B\u000fN\u001b\u0007.\u001a3vY\u0016\u0014XI^3oiB\u0011\u0011fM\u0005\u0003i)\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00027\u007f9\u0011q'\u0010\b\u0003qqj\u0011!\u000f\u0006\u0003um\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002W%\u0011aHK\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0015I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002?U\u00059qM]8va&#W#\u0001#\u0011\u0005\u0015KeB\u0001$H!\tA$&\u0003\u0002IU\u00051\u0001K]3eK\u001aL!AS&\u0003\rM#(/\u001b8h\u0015\tA%&\u0001\u0005he>,\b/\u00133!\u0003A\u0019\u0017M\\2fY\u001a+H/\u001e:f\u0015>\u00147/F\u0001P!\tI\u0003+\u0003\u0002RU\t9!i\\8mK\u0006t\u0017!E2b]\u000e,GNR;ukJ,'j\u001c2tA\u00051!/Z1t_:,\u0012!\u0016\t\u0004SY#\u0015BA,+\u0005\u0019y\u0005\u000f^5p]\u00069!/Z1t_:\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003\\9vs\u0006CA\u0018\u0001\u0011\u0015\u0011u\u00011\u0001E\u0011\u001diu\u0001%AA\u0002=CQaU\u0004A\u0002U\u000bAaY8qsR!1,\u00192d\u0011\u001d\u0011\u0005\u0002%AA\u0002\u0011Cq!\u0014\u0005\u0011\u0002\u0003\u0007q\nC\u0004T\u0011A\u0005\t\u0019A+\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\taM\u000b\u0002EO.\n\u0001\u000e\u0005\u0002j]6\t!N\u0003\u0002lY\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003[*\n!\"\u00198o_R\fG/[8o\u0013\ty'NA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001sU\tyu-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0003UT#!V4\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005A\bCA=\u007f\u001b\u0005Q(BA>}\u0003\u0011a\u0017M\\4\u000b\u0003u\fAA[1wC&\u0011!J_\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003\u0007\u00012!KA\u0003\u0013\r\t9A\u000b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u001b\t\u0019\u0002E\u0002*\u0003\u001fI1!!\u0005+\u0005\r\te.\u001f\u0005\n\u0003+q\u0011\u0011!a\u0001\u0003\u0007\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u000e!\u0019\ti\"a\t\u0002\u000e5\u0011\u0011q\u0004\u0006\u0004\u0003CQ\u0013AC2pY2,7\r^5p]&!\u0011QEA\u0010\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007=\u000bY\u0003C\u0005\u0002\u0016A\t\t\u00111\u0001\u0002\u000e\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rA\u0018\u0011\u0007\u0005\n\u0003+\t\u0012\u0011!a\u0001\u0003\u0007\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u0007\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002q\u00061Q-];bYN$2aTA \u0011%\t)\u0002FA\u0001\u0002\u0004\ti!A\tK_\n<%o\\;q\u0007\u0006t7-\u001a7mK\u0012\u0004\"a\f\f\u0014\u000bY\t9%a\u0015\u0011\u0011\u0005%\u0013q\n#P+nk!!a\u0013\u000b\u0007\u00055#&A\u0004sk:$\u0018.\\3\n\t\u0005E\u00131\n\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003BA+\u00037j!!a\u0016\u000b\u0007\u0005eC0\u0001\u0002j_&\u0019\u0001)a\u0016\u0015\u0005\u0005\r\u0013!B1qa2LHcB.\u0002d\u0005\u0015\u0014q\r\u0005\u0006\u0005f\u0001\r\u0001\u0012\u0005\b\u001bf\u0001\n\u00111\u0001P\u0011\u0015\u0019\u0016\u00041\u0001V\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u0012\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003_\n9\b\u0005\u0003*-\u0006E\u0004CB\u0015\u0002t\u0011{U+C\u0002\u0002v)\u0012a\u0001V;qY\u0016\u001c\u0004\u0002CA=7\u0005\u0005\t\u0019A.\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0003\u00032!_AB\u0013\r\t)I\u001f\u0002\u0007\u001f\nTWm\u0019;"
)
public class JobGroupCancelled implements DAGSchedulerEvent, Product, Serializable {
   private final String groupId;
   private final boolean cancelFutureJobs;
   private final Option reason;

   public static boolean $lessinit$greater$default$2() {
      return JobGroupCancelled$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final JobGroupCancelled x$0) {
      return JobGroupCancelled$.MODULE$.unapply(x$0);
   }

   public static boolean apply$default$2() {
      return JobGroupCancelled$.MODULE$.apply$default$2();
   }

   public static JobGroupCancelled apply(final String groupId, final boolean cancelFutureJobs, final Option reason) {
      return JobGroupCancelled$.MODULE$.apply(groupId, cancelFutureJobs, reason);
   }

   public static Function1 tupled() {
      return JobGroupCancelled$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return JobGroupCancelled$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String groupId() {
      return this.groupId;
   }

   public boolean cancelFutureJobs() {
      return this.cancelFutureJobs;
   }

   public Option reason() {
      return this.reason;
   }

   public JobGroupCancelled copy(final String groupId, final boolean cancelFutureJobs, final Option reason) {
      return new JobGroupCancelled(groupId, cancelFutureJobs, reason);
   }

   public String copy$default$1() {
      return this.groupId();
   }

   public boolean copy$default$2() {
      return this.cancelFutureJobs();
   }

   public Option copy$default$3() {
      return this.reason();
   }

   public String productPrefix() {
      return "JobGroupCancelled";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.groupId();
         }
         case 1 -> {
            return BoxesRunTime.boxToBoolean(this.cancelFutureJobs());
         }
         case 2 -> {
            return this.reason();
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
      return x$1 instanceof JobGroupCancelled;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "groupId";
         }
         case 1 -> {
            return "cancelFutureJobs";
         }
         case 2 -> {
            return "reason";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.groupId()));
      var1 = Statics.mix(var1, this.cancelFutureJobs() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.reason()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof JobGroupCancelled) {
               JobGroupCancelled var4 = (JobGroupCancelled)x$1;
               if (this.cancelFutureJobs() == var4.cancelFutureJobs()) {
                  label52: {
                     String var10000 = this.groupId();
                     String var5 = var4.groupId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     Option var7 = this.reason();
                     Option var6 = var4.reason();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var7.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public JobGroupCancelled(final String groupId, final boolean cancelFutureJobs, final Option reason) {
      this.groupId = groupId;
      this.cancelFutureJobs = cancelFutureJobs;
      this.reason = reason;
      Product.$init$(this);
   }
}
