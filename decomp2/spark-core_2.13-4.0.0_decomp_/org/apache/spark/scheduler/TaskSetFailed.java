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
   bytes = "\u0006\u0005\u0005-e!\u0002\u000f\u001e\u0001v)\u0003\u0002\u0003!\u0001\u0005+\u0007I\u0011A!\t\u0011\u0015\u0003!\u0011#Q\u0001\n\tC\u0001B\u0012\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t!\u0002\u0011\t\u0012)A\u0005\u0011\"A\u0011\u000b\u0001BK\u0002\u0013\u0005!\u000b\u0003\u0005Z\u0001\tE\t\u0015!\u0003T\u0011\u0015Q\u0006\u0001\"\u0001\\\u0011\u001d\u0001\u0007!!A\u0005\u0002\u0005Dq!\u001a\u0001\u0012\u0002\u0013\u0005a\rC\u0004r\u0001E\u0005I\u0011\u0001:\t\u000fQ\u0004\u0011\u0013!C\u0001k\"9q\u000fAA\u0001\n\u0003B\b\"CA\u0001\u0001\u0005\u0005I\u0011AA\u0002\u0011%\tY\u0001AA\u0001\n\u0003\ti\u0001C\u0005\u0002\u001a\u0001\t\t\u0011\"\u0011\u0002\u001c!I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00111\u0006\u0005\n\u0003k\u0001\u0011\u0011!C!\u0003oA\u0011\"a\u000f\u0001\u0003\u0003%\t%!\u0010\t\u0013\u0005}\u0002!!A\u0005B\u0005\u0005\u0003\"CA\"\u0001\u0005\u0005I\u0011IA#\u000f)\tI%HA\u0001\u0012\u0003i\u00121\n\u0004\n9u\t\t\u0011#\u0001\u001e\u0003\u001bBaA\u0017\f\u0005\u0002\u0005\u0015\u0004\"CA -\u0005\u0005IQIA!\u0011%\t9GFA\u0001\n\u0003\u000bI\u0007C\u0005\u0002rY\t\t\u0011\"!\u0002t!I\u0011\u0011\u0011\f\u0002\u0002\u0013%\u00111\u0011\u0002\u000e)\u0006\u001c8nU3u\r\u0006LG.\u001a3\u000b\u0005yy\u0012!C:dQ\u0016$W\u000f\\3s\u0015\t\u0001\u0013%A\u0003ta\u0006\u00148N\u0003\u0002#G\u00051\u0011\r]1dQ\u0016T\u0011\u0001J\u0001\u0004_J<7#\u0002\u0001'YA\u001a\u0004CA\u0014+\u001b\u0005A#\"A\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005-B#AB!osJ+g\r\u0005\u0002.]5\tQ$\u0003\u00020;\t\tB)Q$TG\",G-\u001e7fe\u00163XM\u001c;\u0011\u0005\u001d\n\u0014B\u0001\u001a)\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001N\u001f\u000f\u0005UZdB\u0001\u001c;\u001b\u00059$B\u0001\u001d:\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0015\n\u0005qB\u0013a\u00029bG.\fw-Z\u0005\u0003}}\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u0010\u0015\u0002\u000fQ\f7o[*fiV\t!\t\u0005\u0002.\u0007&\u0011A)\b\u0002\b)\u0006\u001c8nU3u\u0003!!\u0018m]6TKR\u0004\u0013A\u0002:fCN|g.F\u0001I!\tIUJ\u0004\u0002K\u0017B\u0011a\u0007K\u0005\u0003\u0019\"\na\u0001\u0015:fI\u00164\u0017B\u0001(P\u0005\u0019\u0019FO]5oO*\u0011A\nK\u0001\be\u0016\f7o\u001c8!\u0003%)\u0007pY3qi&|g.F\u0001T!\r9CKV\u0005\u0003+\"\u0012aa\u00149uS>t\u0007C\u0001\u001bX\u0013\tAvHA\u0005UQJ|w/\u00192mK\u0006QQ\r_2faRLwN\u001c\u0011\u0002\rqJg.\u001b;?)\u0011aVLX0\u0011\u00055\u0002\u0001\"\u0002!\b\u0001\u0004\u0011\u0005\"\u0002$\b\u0001\u0004A\u0005\"B)\b\u0001\u0004\u0019\u0016\u0001B2paf$B\u0001\u00182dI\"9\u0001\t\u0003I\u0001\u0002\u0004\u0011\u0005b\u0002$\t!\u0003\u0005\r\u0001\u0013\u0005\b#\"\u0001\n\u00111\u0001T\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u001a\u0016\u0003\u0005\"\\\u0013!\u001b\t\u0003U>l\u0011a\u001b\u0006\u0003Y6\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u00059D\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001o\u001b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002g*\u0012\u0001\n[\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\u00051(FA*i\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0011\u0010\u0005\u0002{\u007f6\t1P\u0003\u0002}{\u0006!A.\u00198h\u0015\u0005q\u0018\u0001\u00026bm\u0006L!AT>\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u0015\u0001cA\u0014\u0002\b%\u0019\u0011\u0011\u0002\u0015\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005=\u0011Q\u0003\t\u0004O\u0005E\u0011bAA\nQ\t\u0019\u0011I\\=\t\u0013\u0005]a\"!AA\u0002\u0005\u0015\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u001eA1\u0011qDA\u0013\u0003\u001fi!!!\t\u000b\u0007\u0005\r\u0002&\u0001\u0006d_2dWm\u0019;j_:LA!a\n\u0002\"\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ti#a\r\u0011\u0007\u001d\ny#C\u0002\u00022!\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\u0018A\t\t\u00111\u0001\u0002\u0010\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rI\u0018\u0011\b\u0005\n\u0003/\t\u0012\u0011!a\u0001\u0003\u000b\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u000b\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002s\u00061Q-];bYN$B!!\f\u0002H!I\u0011q\u0003\u000b\u0002\u0002\u0003\u0007\u0011qB\u0001\u000e)\u0006\u001c8nU3u\r\u0006LG.\u001a3\u0011\u0005522#\u0002\f\u0002P\u0005m\u0003\u0003CA)\u0003/\u0012\u0005j\u0015/\u000e\u0005\u0005M#bAA+Q\u00059!/\u001e8uS6,\u0017\u0002BA-\u0003'\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0011\ti&a\u0019\u000e\u0005\u0005}#bAA1{\u0006\u0011\u0011n\\\u0005\u0004}\u0005}CCAA&\u0003\u0015\t\u0007\u000f\u001d7z)\u001da\u00161NA7\u0003_BQ\u0001Q\rA\u0002\tCQAR\rA\u0002!CQ!U\rA\u0002M\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002v\u0005u\u0004\u0003B\u0014U\u0003o\u0002baJA=\u0005\"\u001b\u0016bAA>Q\t1A+\u001e9mKNB\u0001\"a \u001b\u0003\u0003\u0005\r\u0001X\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAC!\rQ\u0018qQ\u0005\u0004\u0003\u0013[(AB(cU\u0016\u001cG\u000f"
)
public class TaskSetFailed implements DAGSchedulerEvent, Product, Serializable {
   private final TaskSet taskSet;
   private final String reason;
   private final Option exception;

   public static Option unapply(final TaskSetFailed x$0) {
      return TaskSetFailed$.MODULE$.unapply(x$0);
   }

   public static TaskSetFailed apply(final TaskSet taskSet, final String reason, final Option exception) {
      return TaskSetFailed$.MODULE$.apply(taskSet, reason, exception);
   }

   public static Function1 tupled() {
      return TaskSetFailed$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return TaskSetFailed$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public TaskSet taskSet() {
      return this.taskSet;
   }

   public String reason() {
      return this.reason;
   }

   public Option exception() {
      return this.exception;
   }

   public TaskSetFailed copy(final TaskSet taskSet, final String reason, final Option exception) {
      return new TaskSetFailed(taskSet, reason, exception);
   }

   public TaskSet copy$default$1() {
      return this.taskSet();
   }

   public String copy$default$2() {
      return this.reason();
   }

   public Option copy$default$3() {
      return this.exception();
   }

   public String productPrefix() {
      return "TaskSetFailed";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.taskSet();
         }
         case 1 -> {
            return this.reason();
         }
         case 2 -> {
            return this.exception();
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
      return x$1 instanceof TaskSetFailed;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "taskSet";
         }
         case 1 -> {
            return "reason";
         }
         case 2 -> {
            return "exception";
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
            if (x$1 instanceof TaskSetFailed) {
               label56: {
                  TaskSetFailed var4 = (TaskSetFailed)x$1;
                  TaskSet var10000 = this.taskSet();
                  TaskSet var5 = var4.taskSet();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  String var8 = this.reason();
                  String var6 = var4.reason();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  Option var9 = this.exception();
                  Option var7 = var4.exception();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var9.equals(var7)) {
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

   public TaskSetFailed(final TaskSet taskSet, final String reason, final Option exception) {
      this.taskSet = taskSet;
      this.reason = reason;
      this.exception = exception;
      Product.$init$(this);
   }
}
