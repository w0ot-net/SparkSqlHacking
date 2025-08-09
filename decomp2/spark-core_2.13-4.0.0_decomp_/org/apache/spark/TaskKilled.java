package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\t}c\u0001\u0002\u0015*\u0001BB\u0001B\u0013\u0001\u0003\u0016\u0004%\ta\u0013\u0005\t)\u0002\u0011\t\u0012)A\u0005\u0019\"AQ\u000b\u0001BK\u0002\u0013\u0005a\u000b\u0003\u0005a\u0001\tE\t\u0015!\u0003X\u0011%\t\u0007A!b\u0001\n\u0003I#\r\u0003\u0005p\u0001\tE\t\u0015!\u0003d\u0011!Q\bA!f\u0001\n\u0003Y\b\"CA\u0001\u0001\tE\t\u0015!\u0003}\u0011\u001d\t\u0019\u0001\u0001C\u0001\u0003\u000bAa!a\b\u0001\t\u0003Z\u0005bBA\u0011\u0001\u0011\u0005\u00131\u0005\u0005\n\u0003W\u0001\u0011\u0011!C\u0001\u0003[A\u0011\"a\u000e\u0001#\u0003%\t!!\u000f\t\u0013\u0005=\u0003!%A\u0005\u0002\u0005E\u0003\"CA+\u0001E\u0005I\u0011AA,\u0011%\tY\u0006AI\u0001\n\u0003\ti\u0006\u0003\u0005\u0002b\u0001Y\t\u0011\"\u0001c\u0011%\t\u0019\u0007AA\u0001\n\u0003\n)\u0007C\u0005\u0002v\u0001\t\t\u0011\"\u0001\u0002x!I\u0011q\u0010\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0011\u0005\n\u0003\u000f\u0003\u0011\u0011!C!\u0003\u0013C\u0011\"a&\u0001\u0003\u0003%\t!!'\t\u0013\u0005u\u0005!!A\u0005B\u0005}\u0005\"CAR\u0001\u0005\u0005I\u0011IAS\u0011%\t9\u000bAA\u0001\n\u0003\nI\u000bC\u0005\u0002,\u0002\t\t\u0011\"\u0011\u0002.\u001eI\u0011QX\u0015\u0002\u0002#\u0005\u0011q\u0018\u0004\tQ%\n\t\u0011#\u0001\u0002B\"9\u00111\u0001\u000f\u0005\u0002\u0005\u001d\b\"CAT9\u0005\u0005IQIAU\u0011%\tI\u000fHA\u0001\n\u0003\u000bY\u000fC\u0005\u0003\u0004q\t\n\u0011\"\u0001\u0002R!I!Q\u0001\u000f\u0012\u0002\u0013\u0005!q\u0001\u0005\n\u00053a\u0012\u0013!C\u0001\u0003;B\u0011Ba\u0007\u001d\u0003\u0003%\tI!\b\t\u0013\tuB$%A\u0005\u0002\u0005E\u0003\"\u0003B 9E\u0005I\u0011\u0001B!\u0011%\u0011\u0019\u0006HI\u0001\n\u0003\ti\u0006C\u0005\u0003Vq\t\t\u0011\"\u0003\u0003X\tQA+Y:l\u0017&dG.\u001a3\u000b\u0005)Z\u0013!B:qCJ\\'B\u0001\u0017.\u0003\u0019\t\u0007/Y2iK*\ta&A\u0002pe\u001e\u001c\u0001aE\u0003\u0001c]Zd\b\u0005\u00023k5\t1GC\u00015\u0003\u0015\u00198-\u00197b\u0013\t14G\u0001\u0004B]f\u0014VM\u001a\t\u0003qej\u0011!K\u0005\u0003u%\u0012\u0001\u0003V1tW\u001a\u000b\u0017\u000e\\3e%\u0016\f7o\u001c8\u0011\u0005Ib\u0014BA\u001f4\u0005\u001d\u0001&o\u001c3vGR\u0004\"aP$\u000f\u0005\u0001+eBA!E\u001b\u0005\u0011%BA\"0\u0003\u0019a$o\\8u}%\tA'\u0003\u0002Gg\u00059\u0001/Y2lC\u001e,\u0017B\u0001%J\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t15'\u0001\u0004sK\u0006\u001cxN\\\u000b\u0002\u0019B\u0011Q*\u0015\b\u0003\u001d>\u0003\"!Q\u001a\n\u0005A\u001b\u0014A\u0002)sK\u0012,g-\u0003\u0002S'\n11\u000b\u001e:j]\u001eT!\u0001U\u001a\u0002\u000fI,\u0017m]8oA\u0005a\u0011mY2v[V\u0003H-\u0019;fgV\tq\u000bE\u0002@1jK!!W%\u0003\u0007M+\u0017\u000f\u0005\u0002\\=6\tAL\u0003\u0002^S\u0005I1o\u00195fIVdWM]\u0005\u0003?r\u0013q\"Q2dk6,H.\u00192mK&sgm\\\u0001\u000eC\u000e\u001cW/\\+qI\u0006$Xm\u001d\u0011\u0002\r\u0005\u001c7-^7t+\u0005\u0019\u0007cA YIB\u001aQ-\u001c=\u0011\t\u0019L7n^\u0007\u0002O*\u0011\u0001.K\u0001\u0005kRLG.\u0003\u0002kO\ni\u0011iY2v[Vd\u0017\r^8s-J\u0002\"\u0001\\7\r\u0001\u0011IaNBA\u0001\u0002\u0003\u0015\t\u0001\u001d\u0002\u0004?\u0012*\u0014aB1dGVl7\u000fI\t\u0003cR\u0004\"A\r:\n\u0005M\u001c$a\u0002(pi\"Lgn\u001a\t\u0003eUL!A^\u001a\u0003\u0007\u0005s\u0017\u0010\u0005\u0002mq\u0012I\u0011PBA\u0001\u0002\u0003\u0015\t\u0001\u001d\u0002\u0004?\u00122\u0014aC7fiJL7\rU3bWN,\u0012\u0001 \t\u0004\u007fak\bC\u0001\u001a\u007f\u0013\ty8G\u0001\u0003M_:<\u0017\u0001D7fiJL7\rU3bWN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0006\u0002\b\u0005%\u00111BA\u0007\u0003;\u0001\"\u0001\u000f\u0001\t\u000b)K\u0001\u0019\u0001'\t\u000fUK\u0001\u0013!a\u0001/\"A\u0011-\u0003I\u0001\u0002\u0004\ty\u0001\u0005\u0003@1\u0006E\u0001GBA\n\u0003/\tY\u0002\u0005\u0004gS\u0006U\u0011\u0011\u0004\t\u0004Y\u0006]AA\u00038\u0002\u000e\u0005\u0005\t\u0011!B\u0001aB\u0019A.a\u0007\u0005\u0015e\fi!!A\u0001\u0002\u000b\u0005\u0001\u000fC\u0004{\u0013A\u0005\t\u0019\u0001?\u0002\u001bQ|WI\u001d:peN#(/\u001b8h\u0003a\u0019w.\u001e8u)><\u0018M\u001d3t)\u0006\u001c8NR1jYV\u0014Xm]\u000b\u0003\u0003K\u00012AMA\u0014\u0013\r\tIc\r\u0002\b\u0005>|G.Z1o\u0003\u0011\u0019w\u000e]=\u0015\u0015\u0005\u001d\u0011qFA\u0019\u0003g\t)\u0004C\u0004K\u0019A\u0005\t\u0019\u0001'\t\u000fUc\u0001\u0013!a\u0001/\"A\u0011\r\u0004I\u0001\u0002\u0004\ty\u0001C\u0004{\u0019A\u0005\t\u0019\u0001?\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u00111\b\u0016\u0004\u0019\u0006u2FAA !\u0011\t\t%a\u0013\u000e\u0005\u0005\r#\u0002BA#\u0003\u000f\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005%3'\u0001\u0006b]:|G/\u0019;j_:LA!!\u0014\u0002D\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\u000b\u0016\u0004/\u0006u\u0012AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u00033R3aYA\u001f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*\"!a\u0018+\u0007q\fi$A\bbG\u000e,Xn\u001d\u0013bG\u000e,7o\u001d\u00133\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011q\r\t\u0005\u0003S\n\u0019(\u0004\u0002\u0002l)!\u0011QNA8\u0003\u0011a\u0017M\\4\u000b\u0005\u0005E\u0014\u0001\u00026bm\u0006L1AUA6\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\tI\bE\u00023\u0003wJ1!! 4\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\r!\u00181\u0011\u0005\n\u0003\u000b#\u0012\u0011!a\u0001\u0003s\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAF!\u0015\ti)a%u\u001b\t\tyIC\u0002\u0002\u0012N\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t)*a$\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003K\tY\n\u0003\u0005\u0002\u0006Z\t\t\u00111\u0001u\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005\u001d\u0014\u0011\u0015\u0005\n\u0003\u000b;\u0012\u0011!a\u0001\u0003s\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003s\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003O\na!Z9vC2\u001cH\u0003BA\u0013\u0003_C\u0001\"!\"\u001b\u0003\u0003\u0005\r\u0001\u001e\u0015\u0004\u0001\u0005M\u0006\u0003BA[\u0003sk!!a.\u000b\u0007\u0005%\u0013&\u0003\u0003\u0002<\u0006]&\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017A\u0003+bg.\\\u0015\u000e\u001c7fIB\u0011\u0001\bH\n\u00069\u0005\r\u0017Q\u001c\t\f\u0003\u000b\fY\rT,\u0002Pr\f9!\u0004\u0002\u0002H*\u0019\u0011\u0011Z\u001a\u0002\u000fI,h\u000e^5nK&!\u0011QZAd\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g\u000e\u000e\t\u0005\u007fa\u000b\t\u000e\r\u0004\u0002T\u0006]\u00171\u001c\t\u0007M&\f).!7\u0011\u00071\f9\u000eB\u0005o9\u0005\u0005\t\u0011!B\u0001aB\u0019A.a7\u0005\u0013ed\u0012\u0011!A\u0001\u0006\u0003\u0001\b\u0003BAp\u0003Kl!!!9\u000b\t\u0005\r\u0018qN\u0001\u0003S>L1\u0001SAq)\t\ty,A\u0003baBd\u0017\u0010\u0006\u0006\u0002\b\u00055\u0018q^Ay\u0005\u0003AQAS\u0010A\u00021Cq!V\u0010\u0011\u0002\u0003\u0007q\u000b\u0003\u0005b?A\u0005\t\u0019AAz!\u0011y\u0004,!>1\r\u0005]\u00181`A\u0000!\u00191\u0017.!?\u0002~B\u0019A.a?\u0005\u00159\f\t0!A\u0001\u0002\u000b\u0005\u0001\u000fE\u0002m\u0003\u007f$!\"_Ay\u0003\u0003\u0005\tQ!\u0001q\u0011\u001dQx\u0004%AA\u0002q\fq\"\u00199qYf$C-\u001a4bk2$HEM\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011!\u0011\u0002\u0016\u0005\u0005\u0017\ti\u0004\u0005\u0003@1\n5\u0001G\u0002B\b\u0005'\u00119\u0002\u0005\u0004gS\nE!Q\u0003\t\u0004Y\nMA!\u00038\"\u0003\u0003\u0005\tQ!\u0001q!\ra'q\u0003\u0003\ns\u0006\n\t\u0011!A\u0003\u0002A\fq\"\u00199qYf$C-\u001a4bk2$H\u0005N\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011yB!\u000f\u0011\u000bI\u0012\tC!\n\n\u0007\t\r2G\u0001\u0004PaRLwN\u001c\t\te\t\u001dBj\u0016B\u0016y&\u0019!\u0011F\u001a\u0003\rQ+\b\u000f\\35!\u0011y\u0004L!\f1\r\t=\"1\u0007B\u001c!\u00191\u0017N!\r\u00036A\u0019ANa\r\u0005\u00139\u001c\u0013\u0011!A\u0001\u0006\u0003\u0001\bc\u00017\u00038\u0011I\u0011pIA\u0001\u0002\u0003\u0015\t\u0001\u001d\u0005\n\u0005w\u0019\u0013\u0011!a\u0001\u0003\u000f\t1\u0001\u001f\u00131\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%e\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM*\"Aa\u0011+\t\t\u0015\u0013Q\b\t\u0005\u007fa\u00139\u0005\r\u0004\u0003J\t5#\u0011\u000b\t\u0007M&\u0014YEa\u0014\u0011\u00071\u0014i\u0005B\u0005oK\u0005\u0005\t\u0011!B\u0001aB\u0019AN!\u0015\u0005\u0013e,\u0013\u0011!A\u0001\u0006\u0003\u0001\u0018a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003ZA!\u0011\u0011\u000eB.\u0013\u0011\u0011i&a\u001b\u0003\r=\u0013'.Z2u\u0001"
)
public class TaskKilled implements TaskFailedReason, Product, Serializable {
   private final String reason;
   private final Seq accumUpdates;
   private final Seq accums;
   private final Seq metricPeaks;

   public static Seq $lessinit$greater$default$4() {
      return TaskKilled$.MODULE$.$lessinit$greater$default$4();
   }

   public static Seq $lessinit$greater$default$3() {
      return TaskKilled$.MODULE$.$lessinit$greater$default$3();
   }

   public static Seq $lessinit$greater$default$2() {
      return TaskKilled$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final TaskKilled x$0) {
      return TaskKilled$.MODULE$.unapply(x$0);
   }

   public static Seq apply$default$4() {
      return TaskKilled$.MODULE$.apply$default$4();
   }

   public static Seq apply$default$3() {
      return TaskKilled$.MODULE$.apply$default$3();
   }

   public static Seq apply$default$2() {
      return TaskKilled$.MODULE$.apply$default$2();
   }

   public static TaskKilled apply(final String reason, final Seq accumUpdates, final Seq accums, final Seq metricPeaks) {
      return TaskKilled$.MODULE$.apply(reason, accumUpdates, accums, metricPeaks);
   }

   public static Function1 tupled() {
      return TaskKilled$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return TaskKilled$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq accums$access$2() {
      return this.accums;
   }

   public String reason() {
      return this.reason;
   }

   public Seq accumUpdates() {
      return this.accumUpdates;
   }

   public Seq accums() {
      return this.accums;
   }

   public Seq metricPeaks() {
      return this.metricPeaks;
   }

   public String toErrorString() {
      return "TaskKilled (" + this.reason() + ")";
   }

   public boolean countTowardsTaskFailures() {
      return false;
   }

   public TaskKilled copy(final String reason, final Seq accumUpdates, final Seq accums, final Seq metricPeaks) {
      return new TaskKilled(reason, accumUpdates, accums, metricPeaks);
   }

   public String copy$default$1() {
      return this.reason();
   }

   public Seq copy$default$2() {
      return this.accumUpdates();
   }

   public Seq copy$default$3() {
      return this.accums();
   }

   public Seq copy$default$4() {
      return this.metricPeaks();
   }

   public String productPrefix() {
      return "TaskKilled";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.reason();
         }
         case 1 -> {
            return this.accumUpdates();
         }
         case 2 -> {
            return this.accums$access$2();
         }
         case 3 -> {
            return this.metricPeaks();
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
      return x$1 instanceof TaskKilled;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "reason";
         }
         case 1 -> {
            return "accumUpdates";
         }
         case 2 -> {
            return "accums";
         }
         case 3 -> {
            return "metricPeaks";
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
      boolean var12;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof TaskKilled) {
               label64: {
                  TaskKilled var4 = (TaskKilled)x$1;
                  String var10000 = this.reason();
                  String var5 = var4.reason();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label64;
                  }

                  Seq var9 = this.accumUpdates();
                  Seq var6 = var4.accumUpdates();
                  if (var9 == null) {
                     if (var6 != null) {
                        break label64;
                     }
                  } else if (!var9.equals(var6)) {
                     break label64;
                  }

                  var9 = this.accums$access$2();
                  Seq var7 = var4.accums$access$2();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label64;
                     }
                  } else if (!var9.equals(var7)) {
                     break label64;
                  }

                  var9 = this.metricPeaks();
                  Seq var8 = var4.metricPeaks();
                  if (var9 == null) {
                     if (var8 != null) {
                        break label64;
                     }
                  } else if (!var9.equals(var8)) {
                     break label64;
                  }

                  if (var4.canEqual(this)) {
                     break label71;
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   public TaskKilled(final String reason, final Seq accumUpdates, final Seq accums, final Seq metricPeaks) {
      this.reason = reason;
      this.accumUpdates = accumUpdates;
      this.accums = accums;
      this.metricPeaks = metricPeaks;
      TaskFailedReason.$init$(this);
      Product.$init$(this);
   }
}
