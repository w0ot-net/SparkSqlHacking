package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.TaskEndReason;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t%d!B\u0013'\u0001\u001ar\u0003\u0002C%\u0001\u0005+\u0007I\u0011\u0001&\t\u0011M\u0003!\u0011#Q\u0001\n-C\u0001b\u0017\u0001\u0003\u0016\u0004%\t\u0001\u0018\u0005\tC\u0002\u0011\t\u0012)A\u0005;\"A!\r\u0001BK\u0002\u0013\u00051\r\u0003\u0005e\u0001\tE\t\u0015!\u0003Y\u0011!)\u0007A!f\u0001\n\u00031\u0007\u0002\u0003;\u0001\u0005#\u0005\u000b\u0011B4\t\u0011a\u0004!Q3A\u0005\u0002eD\u0011\"!\u0001\u0001\u0005#\u0005\u000b\u0011\u0002>\t\u0015\u0005\r\u0001A!f\u0001\n\u0003\t)\u0001\u0003\u0006\u0002\u000e\u0001\u0011\t\u0012)A\u0005\u0003\u000fAq!a\u0004\u0001\t\u0003\t\t\u0002C\u0005\u00028\u0001\t\t\u0011\"\u0001\u0002:!I\u0011q\t\u0001\u0012\u0002\u0013\u0005\u0011\u0011\n\u0005\n\u0003O\u0002\u0011\u0013!C\u0001\u0003SB\u0011\"!\u001c\u0001#\u0003%\t!a\u001c\t\u0013\u0005M\u0004!%A\u0005\u0002\u0005U\u0004\"CA=\u0001E\u0005I\u0011AA>\u0011%\ty\bAI\u0001\n\u0003\t\t\tC\u0005\u0002\u0006\u0002\t\t\u0011\"\u0011\u0002\b\"I\u0011\u0011\u0014\u0001\u0002\u0002\u0013\u0005\u00111\u0014\u0005\n\u0003G\u0003\u0011\u0011!C\u0001\u0003KC\u0011\"a+\u0001\u0003\u0003%\t%!,\t\u0013\u0005m\u0006!!A\u0005\u0002\u0005u\u0006\"CAd\u0001\u0005\u0005I\u0011IAe\u0011%\ti\rAA\u0001\n\u0003\ny\rC\u0005\u0002R\u0002\t\t\u0011\"\u0011\u0002T\"I\u0011Q\u001b\u0001\u0002\u0002\u0013\u0005\u0013q[\u0004\u000b\u000374\u0013\u0011!E\u0001M\u0005ug!C\u0013'\u0003\u0003E\tAJAp\u0011\u001d\tya\bC\u0001\u0005\u001bA\u0011\"!5 \u0003\u0003%)%a5\t\u0013\t=q$!A\u0005\u0002\nE\u0001\"\u0003B\u001b?\u0005\u0005I\u0011\u0011B\u001c\u0011%\u0011yfHA\u0001\n\u0013\u0011\tGA\bD_6\u0004H.\u001a;j_:,e/\u001a8u\u0015\t9\u0003&A\u0005tG\",G-\u001e7fe*\u0011\u0011FK\u0001\u0006gB\f'o\u001b\u0006\u0003W1\na!\u00199bG\",'\"A\u0017\u0002\u0007=\u0014xmE\u0003\u0001_UJD\b\u0005\u00021g5\t\u0011GC\u00013\u0003\u0015\u00198-\u00197b\u0013\t!\u0014G\u0001\u0004B]f\u0014VM\u001a\t\u0003m]j\u0011AJ\u0005\u0003q\u0019\u0012\u0011\u0003R!H'\u000eDW\rZ;mKJ,e/\u001a8u!\t\u0001$(\u0003\u0002<c\t9\u0001K]8ek\u000e$\bCA\u001fG\u001d\tqDI\u0004\u0002@\u00076\t\u0001I\u0003\u0002B\u0005\u00061AH]8piz\u001a\u0001!C\u00013\u0013\t)\u0015'A\u0004qC\u000e\\\u0017mZ3\n\u0005\u001dC%\u0001D*fe&\fG.\u001b>bE2,'BA#2\u0003\u0011!\u0018m]6\u0016\u0003-\u0003$\u0001T)\u0011\u0007Yju*\u0003\u0002OM\t!A+Y:l!\t\u0001\u0016\u000b\u0004\u0001\u0005\u0013I\u0013\u0011\u0011!A\u0001\u0006\u0003!&aA0%q\u0005)A/Y:lAE\u0011Q\u000b\u0017\t\u0003aYK!aV\u0019\u0003\u000f9{G\u000f[5oOB\u0011\u0001'W\u0005\u00035F\u00121!\u00118z\u0003\u0019\u0011X-Y:p]V\tQ\f\u0005\u0002_?6\t\u0001&\u0003\u0002aQ\tiA+Y:l\u000b:$'+Z1t_:\fqA]3bg>t\u0007%\u0001\u0004sKN,H\u000e^\u000b\u00021\u00069!/Z:vYR\u0004\u0013\u0001D1dGVlW\u000b\u001d3bi\u0016\u001cX#A4\u0011\u0007uB'.\u0003\u0002j\u0011\n\u00191+Z91\u0007-\u0014h\u000f\u0005\u0003m_F,X\"A7\u000b\u00059D\u0013\u0001B;uS2L!\u0001]7\u0003\u001b\u0005\u001b7-^7vY\u0006$xN\u001d,3!\t\u0001&\u000fB\u0005t\u0011\u0005\u0005\t\u0011!B\u0001)\n\u0019q\fJ\u001d\u0002\u001b\u0005\u001c7-^7Va\u0012\fG/Z:!!\t\u0001f\u000fB\u0005x\u0011\u0005\u0005\t\u0011!B\u0001)\n!q\fJ\u00191\u0003-iW\r\u001e:jGB+\u0017m[:\u0016\u0003i\u00042\u0001M>~\u0013\ta\u0018GA\u0003BeJ\f\u0017\u0010\u0005\u00021}&\u0011q0\r\u0002\u0005\u0019>tw-\u0001\u0007nKR\u0014\u0018n\u0019)fC.\u001c\b%\u0001\u0005uCN\\\u0017J\u001c4p+\t\t9\u0001E\u00027\u0003\u0013I1!a\u0003'\u0005!!\u0016m]6J]\u001a|\u0017!\u0003;bg.LeNZ8!\u0003\u0019a\u0014N\\5u}Qq\u00111CA\u000b\u0003?\t\t#a\t\u00024\u0005U\u0002C\u0001\u001c\u0001\u0011\u0019IU\u00021\u0001\u0002\u0018A\"\u0011\u0011DA\u000f!\u00111T*a\u0007\u0011\u0007A\u000bi\u0002\u0002\u0006S\u0003+\t\t\u0011!A\u0003\u0002QCQaW\u0007A\u0002uCQAY\u0007A\u0002aCa!Z\u0007A\u0002\u0005\u0015\u0002\u0003B\u001fi\u0003O\u0001d!!\u000b\u0002.\u0005E\u0002C\u00027p\u0003W\ty\u0003E\u0002Q\u0003[!!b]A\u0012\u0003\u0003\u0005\tQ!\u0001U!\r\u0001\u0016\u0011\u0007\u0003\u000bo\u0006\r\u0012\u0011!A\u0001\u0006\u0003!\u0006\"\u0002=\u000e\u0001\u0004Q\bbBA\u0002\u001b\u0001\u0007\u0011qA\u0001\u0005G>\u0004\u0018\u0010\u0006\b\u0002\u0014\u0005m\u0012QHA \u0003\u0003\n\u0019%!\u0012\t\u0011%s\u0001\u0013!a\u0001\u0003/Aqa\u0017\b\u0011\u0002\u0003\u0007Q\fC\u0004c\u001dA\u0005\t\u0019\u0001-\t\u0011\u0015t\u0001\u0013!a\u0001\u0003KAq\u0001\u001f\b\u0011\u0002\u0003\u0007!\u0010C\u0005\u0002\u00049\u0001\n\u00111\u0001\u0002\b\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA&a\u0011\ti%a\u0015+\t\u0005=\u0013Q\u000b\t\u0005m5\u000b\t\u0006E\u0002Q\u0003'\"\u0011BU\b\u0002\u0002\u0003\u0005)\u0011\u0001+,\u0005\u0005]\u0003\u0003BA-\u0003Gj!!a\u0017\u000b\t\u0005u\u0013qL\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u00192\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003K\nYFA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002l)\u001aQ,!\u0016\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011\u0011\u000f\u0016\u00041\u0006U\u0013AD2paf$C-\u001a4bk2$H\u0005N\u000b\u0003\u0003oR3aZA+\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU*\"!! +\u0007i\f)&\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001c\u0016\u0005\u0005\r%\u0006BA\u0004\u0003+\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAAE!\u0011\tY)!&\u000e\u0005\u00055%\u0002BAH\u0003#\u000bA\u0001\\1oO*\u0011\u00111S\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u0018\u00065%AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u001eB\u0019\u0001'a(\n\u0007\u0005\u0005\u0016GA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002Y\u0003OC\u0011\"!+\u0018\u0003\u0003\u0005\r!!(\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\u000bE\u0003\u00022\u0006]\u0006,\u0004\u0002\u00024*\u0019\u0011QW\u0019\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002:\u0006M&\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a0\u0002FB\u0019\u0001'!1\n\u0007\u0005\r\u0017GA\u0004C_>dW-\u00198\t\u0011\u0005%\u0016$!AA\u0002a\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011\u0011RAf\u0011%\tIKGA\u0001\u0002\u0004\ti*\u0001\u0005iCND7i\u001c3f)\t\ti*\u0001\u0005u_N#(/\u001b8h)\t\tI)\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u007f\u000bI\u000e\u0003\u0005\u0002*v\t\t\u00111\u0001Y\u0003=\u0019u.\u001c9mKRLwN\\#wK:$\bC\u0001\u001c '\u0015y\u0012\u0011\u001dB\u0002!=\t\u0019/!;\u0002nvC\u0016Q\u001f>\u0002\b\u0005MQBAAs\u0015\r\t9/M\u0001\beVtG/[7f\u0013\u0011\tY/!:\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tg\u0007\r\u0003\u0002p\u0006M\b\u0003\u0002\u001cN\u0003c\u00042\u0001UAz\t%\u0011v$!A\u0001\u0002\u000b\u0005A\u000b\u0005\u0003>Q\u0006]\bGBA}\u0003{\u0014\t\u0001\u0005\u0004m_\u0006m\u0018q \t\u0004!\u0006uH!C: \u0003\u0003\u0005\tQ!\u0001U!\r\u0001&\u0011\u0001\u0003\no~\t\t\u0011!A\u0003\u0002Q\u0003BA!\u0002\u0003\f5\u0011!q\u0001\u0006\u0005\u0005\u0013\t\t*\u0001\u0002j_&\u0019qIa\u0002\u0015\u0005\u0005u\u0017!B1qa2LHCDA\n\u0005'\u0011iBa\b\u0003\"\tE\"1\u0007\u0005\u0007\u0013\n\u0002\rA!\u00061\t\t]!1\u0004\t\u0005m5\u0013I\u0002E\u0002Q\u00057!!B\u0015B\n\u0003\u0003\u0005\tQ!\u0001U\u0011\u0015Y&\u00051\u0001^\u0011\u0015\u0011'\u00051\u0001Y\u0011\u0019)'\u00051\u0001\u0003$A!Q\b\u001bB\u0013a\u0019\u00119Ca\u000b\u00030A1An\u001cB\u0015\u0005[\u00012\u0001\u0015B\u0016\t)\u0019(\u0011EA\u0001\u0002\u0003\u0015\t\u0001\u0016\t\u0004!\n=BAC<\u0003\"\u0005\u0005\t\u0011!B\u0001)\")\u0001P\ta\u0001u\"9\u00111\u0001\u0012A\u0002\u0005\u001d\u0011aB;oCB\u0004H.\u001f\u000b\u0005\u0005s\u0011Y\u0006E\u00031\u0005w\u0011y$C\u0002\u0003>E\u0012aa\u00149uS>t\u0007\u0003\u0004\u0019\u0003B\t\u0015S\f\u0017B'u\u0006\u001d\u0011b\u0001B\"c\t1A+\u001e9mKZ\u0002DAa\u0012\u0003LA!a'\u0014B%!\r\u0001&1\n\u0003\n%\u000e\n\t\u0011!A\u0003\u0002Q\u0003B!\u00105\u0003PA2!\u0011\u000bB+\u00053\u0002b\u0001\\8\u0003T\t]\u0003c\u0001)\u0003V\u0011I1oIA\u0001\u0002\u0003\u0015\t\u0001\u0016\t\u0004!\neC!C<$\u0003\u0003\u0005\tQ!\u0001U\u0011%\u0011ifIA\u0001\u0002\u0004\t\u0019\"A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa\u0019\u0011\t\u0005-%QM\u0005\u0005\u0005O\niI\u0001\u0004PE*,7\r\u001e"
)
public class CompletionEvent implements DAGSchedulerEvent, Product, Serializable {
   private final Task task;
   private final TaskEndReason reason;
   private final Object result;
   private final Seq accumUpdates;
   private final long[] metricPeaks;
   private final TaskInfo taskInfo;

   public static Option unapply(final CompletionEvent x$0) {
      return CompletionEvent$.MODULE$.unapply(x$0);
   }

   public static CompletionEvent apply(final Task task, final TaskEndReason reason, final Object result, final Seq accumUpdates, final long[] metricPeaks, final TaskInfo taskInfo) {
      return CompletionEvent$.MODULE$.apply(task, reason, result, accumUpdates, metricPeaks, taskInfo);
   }

   public static Function1 tupled() {
      return CompletionEvent$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return CompletionEvent$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Task task() {
      return this.task;
   }

   public TaskEndReason reason() {
      return this.reason;
   }

   public Object result() {
      return this.result;
   }

   public Seq accumUpdates() {
      return this.accumUpdates;
   }

   public long[] metricPeaks() {
      return this.metricPeaks;
   }

   public TaskInfo taskInfo() {
      return this.taskInfo;
   }

   public CompletionEvent copy(final Task task, final TaskEndReason reason, final Object result, final Seq accumUpdates, final long[] metricPeaks, final TaskInfo taskInfo) {
      return new CompletionEvent(task, reason, result, accumUpdates, metricPeaks, taskInfo);
   }

   public Task copy$default$1() {
      return this.task();
   }

   public TaskEndReason copy$default$2() {
      return this.reason();
   }

   public Object copy$default$3() {
      return this.result();
   }

   public Seq copy$default$4() {
      return this.accumUpdates();
   }

   public long[] copy$default$5() {
      return this.metricPeaks();
   }

   public TaskInfo copy$default$6() {
      return this.taskInfo();
   }

   public String productPrefix() {
      return "CompletionEvent";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.task();
         }
         case 1 -> {
            return this.reason();
         }
         case 2 -> {
            return this.result();
         }
         case 3 -> {
            return this.accumUpdates();
         }
         case 4 -> {
            return this.metricPeaks();
         }
         case 5 -> {
            return this.taskInfo();
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
      return x$1 instanceof CompletionEvent;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "task";
         }
         case 1 -> {
            return "reason";
         }
         case 2 -> {
            return "result";
         }
         case 3 -> {
            return "accumUpdates";
         }
         case 4 -> {
            return "metricPeaks";
         }
         case 5 -> {
            return "taskInfo";
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
         label79: {
            if (x$1 instanceof CompletionEvent) {
               label70: {
                  CompletionEvent var4 = (CompletionEvent)x$1;
                  Task var10000 = this.task();
                  Task var5 = var4.task();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label70;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label70;
                  }

                  TaskEndReason var9 = this.reason();
                  TaskEndReason var6 = var4.reason();
                  if (var9 == null) {
                     if (var6 != null) {
                        break label70;
                     }
                  } else if (!var9.equals(var6)) {
                     break label70;
                  }

                  if (BoxesRunTime.equals(this.result(), var4.result())) {
                     label71: {
                        Seq var10 = this.accumUpdates();
                        Seq var7 = var4.accumUpdates();
                        if (var10 == null) {
                           if (var7 != null) {
                              break label71;
                           }
                        } else if (!var10.equals(var7)) {
                           break label71;
                        }

                        if (this.metricPeaks() == var4.metricPeaks()) {
                           label72: {
                              TaskInfo var11 = this.taskInfo();
                              TaskInfo var8 = var4.taskInfo();
                              if (var11 == null) {
                                 if (var8 != null) {
                                    break label72;
                                 }
                              } else if (!var11.equals(var8)) {
                                 break label72;
                              }

                              if (var4.canEqual(this)) {
                                 break label79;
                              }
                           }
                        }
                     }
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

   public CompletionEvent(final Task task, final TaskEndReason reason, final Object result, final Seq accumUpdates, final long[] metricPeaks, final TaskInfo taskInfo) {
      this.task = task;
      this.reason = reason;
      this.result = result;
      this.accumUpdates = accumUpdates;
      this.metricPeaks = metricPeaks;
      this.taskInfo = taskInfo;
      Product.$init$(this);
   }
}
