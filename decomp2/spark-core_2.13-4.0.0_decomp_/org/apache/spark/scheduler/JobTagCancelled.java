package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ee!\u0002\u000f\u001e\u0001v)\u0003\u0002\u0003!\u0001\u0005+\u0007I\u0011A!\t\u0011)\u0003!\u0011#Q\u0001\n\tC\u0001b\u0013\u0001\u0003\u0016\u0004%\t\u0001\u0014\u0005\t!\u0002\u0011\t\u0012)A\u0005\u001b\"A\u0011\u000b\u0001BK\u0002\u0013\u0005!\u000b\u0003\u0005a\u0001\tE\t\u0015!\u0003T\u0011\u0015\t\u0007\u0001\"\u0001c\u0011\u001d9\u0007!!A\u0005\u0002!Dq\u0001\u001c\u0001\u0012\u0002\u0013\u0005Q\u000eC\u0004y\u0001E\u0005I\u0011A=\t\u000fm\u0004\u0011\u0013!C\u0001y\"9a\u0010AA\u0001\n\u0003z\b\"CA\b\u0001\u0005\u0005I\u0011AA\t\u0011%\tI\u0002AA\u0001\n\u0003\tY\u0002C\u0005\u0002(\u0001\t\t\u0011\"\u0011\u0002*!I\u0011q\u0007\u0001\u0002\u0002\u0013\u0005\u0011\u0011\b\u0005\n\u0003\u0007\u0002\u0011\u0011!C!\u0003\u000bB\u0011\"!\u0013\u0001\u0003\u0003%\t%a\u0013\t\u0013\u00055\u0003!!A\u0005B\u0005=\u0003\"CA)\u0001\u0005\u0005I\u0011IA*\u000f)\t9&HA\u0001\u0012\u0003i\u0012\u0011\f\u0004\n9u\t\t\u0011#\u0001\u001e\u00037Ba!\u0019\f\u0005\u0002\u0005M\u0004\"CA'-\u0005\u0005IQIA(\u0011%\t)HFA\u0001\n\u0003\u000b9\bC\u0005\u0002\u0000Y\t\t\u0011\"!\u0002\u0002\"I\u0011q\u0012\f\u0002\u0002\u0013%\u0011\u0011\u0013\u0002\u0010\u0015>\u0014G+Y4DC:\u001cW\r\u001c7fI*\u0011adH\u0001\ng\u000eDW\rZ;mKJT!\u0001I\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\t\u001a\u0013AB1qC\u000eDWMC\u0001%\u0003\ry'oZ\n\u0006\u0001\u0019b\u0003g\r\t\u0003O)j\u0011\u0001\u000b\u0006\u0002S\u0005)1oY1mC&\u00111\u0006\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u00055rS\"A\u000f\n\u0005=j\"!\u0005#B\u000fN\u001b\u0007.\u001a3vY\u0016\u0014XI^3oiB\u0011q%M\u0005\u0003e!\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00025{9\u0011Qg\u000f\b\u0003mij\u0011a\u000e\u0006\u0003qe\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002S%\u0011A\bK\u0001\ba\u0006\u001c7.Y4f\u0013\tqtH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002=Q\u00059A/Y4OC6,W#\u0001\"\u0011\u0005\r;eB\u0001#F!\t1\u0004&\u0003\u0002GQ\u00051\u0001K]3eK\u001aL!\u0001S%\u0003\rM#(/\u001b8h\u0015\t1\u0005&\u0001\u0005uC\u001et\u0015-\\3!\u0003\u0019\u0011X-Y:p]V\tQ\nE\u0002(\u001d\nK!a\u0014\u0015\u0003\r=\u0003H/[8o\u0003\u001d\u0011X-Y:p]\u0002\nQbY1oG\u0016dG.\u001a3K_\n\u001cX#A*\u0011\u0007\u001drE\u000bE\u0002V1jk\u0011A\u0016\u0006\u0003/\"\n!bY8oGV\u0014(/\u001a8u\u0013\tIfKA\u0004Qe>l\u0017n]3\u0011\u0007QZV,\u0003\u0002]\u007f\t\u00191+Z9\u0011\u00055r\u0016BA0\u001e\u0005%\t5\r^5wK*{'-\u0001\bdC:\u001cW\r\u001c7fI*{'m\u001d\u0011\u0002\rqJg.\u001b;?)\u0011\u0019G-\u001a4\u0011\u00055\u0002\u0001\"\u0002!\b\u0001\u0004\u0011\u0005\"B&\b\u0001\u0004i\u0005\"B)\b\u0001\u0004\u0019\u0016\u0001B2paf$BaY5kW\"9\u0001\t\u0003I\u0001\u0002\u0004\u0011\u0005bB&\t!\u0003\u0005\r!\u0014\u0005\b#\"\u0001\n\u00111\u0001T\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u001c\u0016\u0003\u0005>\\\u0013\u0001\u001d\t\u0003cZl\u0011A\u001d\u0006\u0003gR\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005UD\u0013AC1o]>$\u0018\r^5p]&\u0011qO\u001d\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002u*\u0012Qj\\\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\u0005i(FA*p\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011\u0011\u0001\t\u0005\u0003\u0007\ti!\u0004\u0002\u0002\u0006)!\u0011qAA\u0005\u0003\u0011a\u0017M\\4\u000b\u0005\u0005-\u0011\u0001\u00026bm\u0006L1\u0001SA\u0003\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t\u0019\u0002E\u0002(\u0003+I1!a\u0006)\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\ti\"a\t\u0011\u0007\u001d\ny\"C\u0002\u0002\"!\u00121!\u00118z\u0011%\t)CDA\u0001\u0002\u0004\t\u0019\"A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003W\u0001b!!\f\u00024\u0005uQBAA\u0018\u0015\r\t\t\u0004K\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u001b\u0003_\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111HA!!\r9\u0013QH\u0005\u0004\u0003\u007fA#a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003K\u0001\u0012\u0011!a\u0001\u0003;\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011\u0011AA$\u0011%\t)#EA\u0001\u0002\u0004\t\u0019\"\u0001\u0005iCND7i\u001c3f)\t\t\u0019\"\u0001\u0005u_N#(/\u001b8h)\t\t\t!\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003w\t)\u0006C\u0005\u0002&Q\t\t\u00111\u0001\u0002\u001e\u0005y!j\u001c2UC\u001e\u001c\u0015M\\2fY2,G\r\u0005\u0002.-M)a#!\u0018\u0002jAA\u0011qLA3\u00056\u001b6-\u0004\u0002\u0002b)\u0019\u00111\r\u0015\u0002\u000fI,h\u000e^5nK&!\u0011qMA1\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u0003W\n\t(\u0004\u0002\u0002n)!\u0011qNA\u0005\u0003\tIw.C\u0002?\u0003[\"\"!!\u0017\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000f\r\fI(a\u001f\u0002~!)\u0001)\u0007a\u0001\u0005\")1*\u0007a\u0001\u001b\")\u0011+\u0007a\u0001'\u00069QO\\1qa2LH\u0003BAB\u0003\u0017\u0003Ba\n(\u0002\u0006B1q%a\"C\u001bNK1!!#)\u0005\u0019!V\u000f\u001d7fg!A\u0011Q\u0012\u000e\u0002\u0002\u0003\u00071-A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a%\u0011\t\u0005\r\u0011QS\u0005\u0005\u0003/\u000b)A\u0001\u0004PE*,7\r\u001e"
)
public class JobTagCancelled implements DAGSchedulerEvent, Product, Serializable {
   private final String tagName;
   private final Option reason;
   private final Option cancelledJobs;

   public static Option unapply(final JobTagCancelled x$0) {
      return JobTagCancelled$.MODULE$.unapply(x$0);
   }

   public static JobTagCancelled apply(final String tagName, final Option reason, final Option cancelledJobs) {
      return JobTagCancelled$.MODULE$.apply(tagName, reason, cancelledJobs);
   }

   public static Function1 tupled() {
      return JobTagCancelled$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return JobTagCancelled$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String tagName() {
      return this.tagName;
   }

   public Option reason() {
      return this.reason;
   }

   public Option cancelledJobs() {
      return this.cancelledJobs;
   }

   public JobTagCancelled copy(final String tagName, final Option reason, final Option cancelledJobs) {
      return new JobTagCancelled(tagName, reason, cancelledJobs);
   }

   public String copy$default$1() {
      return this.tagName();
   }

   public Option copy$default$2() {
      return this.reason();
   }

   public Option copy$default$3() {
      return this.cancelledJobs();
   }

   public String productPrefix() {
      return "JobTagCancelled";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.tagName();
         }
         case 1 -> {
            return this.reason();
         }
         case 2 -> {
            return this.cancelledJobs();
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
      return x$1 instanceof JobTagCancelled;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "tagName";
         }
         case 1 -> {
            return "reason";
         }
         case 2 -> {
            return "cancelledJobs";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof JobTagCancelled) {
               label56: {
                  JobTagCancelled var4 = (JobTagCancelled)x$1;
                  String var10000 = this.tagName();
                  String var5 = var4.tagName();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  Option var8 = this.reason();
                  Option var6 = var4.reason();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  var8 = this.cancelledJobs();
                  Option var7 = var4.cancelledJobs();
                  if (var8 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
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

   public JobTagCancelled(final String tagName, final Option reason, final Option cancelledJobs) {
      this.tagName = tagName;
      this.reason = reason;
      this.cancelledJobs = cancelledJobs;
      Product.$init$(this);
   }
}
