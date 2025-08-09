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
   bytes = "\u0006\u0005\u0005ue!B\r\u001b\u0001j\u0011\u0003\u0002C\u001f\u0001\u0005+\u0007I\u0011\u0001 \t\u0011\u001d\u0003!\u0011#Q\u0001\n}B\u0001b\u0014\u0001\u0003\u0016\u0004%\t\u0001\u0015\u0005\t)\u0002\u0011\t\u0012)A\u0005#\")Q\u000b\u0001C\u0001-\"9a\fAA\u0001\n\u0003y\u0006b\u00022\u0001#\u0003%\ta\u0019\u0005\be\u0002\t\n\u0011\"\u0001t\u0011\u001d)\b!!A\u0005BYD\u0001b \u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0001\u0005\n\u0003\u0013\u0001\u0011\u0011!C\u0001\u0003\u0017A\u0011\"!\u0005\u0001\u0003\u0003%\t%a\u0005\t\u0013\u0005\u0005\u0002!!A\u0005\u0002\u0005\r\u0002\"CA\u0017\u0001\u0005\u0005I\u0011IA\u0018\u0011%\t\u0019\u0004AA\u0001\n\u0003\n)\u0004C\u0005\u00028\u0001\t\t\u0011\"\u0011\u0002:!I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0013QH\u0004\u000b\u0003\u0003R\u0012\u0011!E\u00015\u0005\rc!C\r\u001b\u0003\u0003E\tAGA#\u0011\u0019)6\u0003\"\u0001\u0002f!I\u0011qG\n\u0002\u0002\u0013\u0015\u0013\u0011\b\u0005\n\u0003O\u001a\u0012\u0011!CA\u0003SB\u0011\"a\u001e\u0014\u0003\u0003%\t)!\u001f\t\u0013\u0005M5#!A\u0005\n\u0005U%A\u0003\"fO&tWI^3oi*\u00111\u0004H\u0001\ng\u000eDW\rZ;mKJT!!\b\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0001\u0013AB1qC\u000eDWMC\u0001\"\u0003\ry'oZ\n\u0006\u0001\rJS\u0006\r\t\u0003I\u001dj\u0011!\n\u0006\u0002M\u0005)1oY1mC&\u0011\u0001&\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005)ZS\"\u0001\u000e\n\u00051R\"!\u0005#B\u000fN\u001b\u0007.\u001a3vY\u0016\u0014XI^3oiB\u0011AEL\u0005\u0003_\u0015\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00022u9\u0011!\u0007\u000f\b\u0003g]j\u0011\u0001\u000e\u0006\u0003kY\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002M%\u0011\u0011(J\u0001\ba\u0006\u001c7.Y4f\u0013\tYDH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002:K\u0005!A/Y:l+\u0005y\u0004G\u0001!F!\rQ\u0013iQ\u0005\u0003\u0005j\u0011A\u0001V1tWB\u0011A)\u0012\u0007\u0001\t%1%!!A\u0001\u0002\u000b\u0005\u0001JA\u0002`I]\nQ\u0001^1tW\u0002\n\"!\u0013'\u0011\u0005\u0011R\u0015BA&&\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001J'\n\u00059+#aA!os\u0006AA/Y:l\u0013:4w.F\u0001R!\tQ#+\u0003\u0002T5\tAA+Y:l\u0013:4w.A\u0005uCN\\\u0017J\u001c4pA\u00051A(\u001b8jiz\"2a\u0016-^!\tQ\u0003\u0001C\u0003>\u000b\u0001\u0007\u0011\f\r\u0002[9B\u0019!&Q.\u0011\u0005\u0011cF!\u0003$Y\u0003\u0003\u0005\tQ!\u0001I\u0011\u0015yU\u00011\u0001R\u0003\u0011\u0019w\u000e]=\u0015\u0007]\u0003\u0017\rC\u0004>\rA\u0005\t\u0019A-\t\u000f=3\u0001\u0013!a\u0001#\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u000131\u0005\u0015D'F\u00014j!\rQ\u0013i\u001a\t\u0003\t\"$\u0011BR\u0004\u0002\u0002\u0003\u0005)\u0011\u0001%,\u0003)\u0004\"a\u001b9\u000e\u00031T!!\u001c8\u0002\u0013Ut7\r[3dW\u0016$'BA8&\u0003)\tgN\\8uCRLwN\\\u0005\u0003c2\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012\u0001\u001e\u0016\u0003#&\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A<\u0011\u0005alX\"A=\u000b\u0005i\\\u0018\u0001\u00027b]\u001eT\u0011\u0001`\u0001\u0005U\u00064\u0018-\u0003\u0002\u007fs\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\u0001\u0011\u0007\u0011\n)!C\u0002\u0002\b\u0015\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2\u0001TA\u0007\u0011%\tyaCA\u0001\u0002\u0004\t\u0019!A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003+\u0001R!a\u0006\u0002\u001e1k!!!\u0007\u000b\u0007\u0005mQ%\u0001\u0006d_2dWm\u0019;j_:LA!a\b\u0002\u001a\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t)#a\u000b\u0011\u0007\u0011\n9#C\u0002\u0002*\u0015\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u00105\t\t\u00111\u0001M\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007]\f\t\u0004C\u0005\u0002\u00109\t\t\u00111\u0001\u0002\u0004\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0004\u0005AAo\\*ue&tw\rF\u0001x\u0003\u0019)\u0017/^1mgR!\u0011QEA \u0011!\ty!EA\u0001\u0002\u0004a\u0015A\u0003\"fO&tWI^3oiB\u0011!fE\n\u0006'\u0005\u001d\u00131\f\t\t\u0003\u0013\ny%a\u0015R/6\u0011\u00111\n\u0006\u0004\u0003\u001b*\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003#\nYEA\tBEN$(/Y2u\rVt7\r^5p]J\u0002D!!\u0016\u0002ZA!!&QA,!\r!\u0015\u0011\f\u0003\n\rN\t\t\u0011!A\u0003\u0002!\u0003B!!\u0018\u0002d5\u0011\u0011q\f\u0006\u0004\u0003CZ\u0018AA5p\u0013\rY\u0014q\f\u000b\u0003\u0003\u0007\nQ!\u00199qYf$RaVA6\u0003kBa!\u0010\fA\u0002\u00055\u0004\u0007BA8\u0003g\u0002BAK!\u0002rA\u0019A)a\u001d\u0005\u0015\u0019\u000bY'!A\u0001\u0002\u000b\u0005\u0001\nC\u0003P-\u0001\u0007\u0011+A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005m\u0014q\u0012\t\u0006I\u0005u\u0014\u0011Q\u0005\u0004\u0003\u007f*#AB(qi&|g\u000e\u0005\u0004%\u0003\u0007\u000b9)U\u0005\u0004\u0003\u000b+#A\u0002+va2,'\u0007\r\u0003\u0002\n\u00065\u0005\u0003\u0002\u0016B\u0003\u0017\u00032\u0001RAG\t%1u#!A\u0001\u0002\u000b\u0005\u0001\n\u0003\u0005\u0002\u0012^\t\t\u00111\u0001X\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003/\u00032\u0001_AM\u0013\r\tY*\u001f\u0002\u0007\u001f\nTWm\u0019;"
)
public class BeginEvent implements DAGSchedulerEvent, Product, Serializable {
   private final Task task;
   private final TaskInfo taskInfo;

   public static Option unapply(final BeginEvent x$0) {
      return BeginEvent$.MODULE$.unapply(x$0);
   }

   public static BeginEvent apply(final Task task, final TaskInfo taskInfo) {
      return BeginEvent$.MODULE$.apply(task, taskInfo);
   }

   public static Function1 tupled() {
      return BeginEvent$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return BeginEvent$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Task task() {
      return this.task;
   }

   public TaskInfo taskInfo() {
      return this.taskInfo;
   }

   public BeginEvent copy(final Task task, final TaskInfo taskInfo) {
      return new BeginEvent(task, taskInfo);
   }

   public Task copy$default$1() {
      return this.task();
   }

   public TaskInfo copy$default$2() {
      return this.taskInfo();
   }

   public String productPrefix() {
      return "BeginEvent";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.task();
         }
         case 1 -> {
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
      return x$1 instanceof BeginEvent;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "task";
         }
         case 1 -> {
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
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof BeginEvent) {
               label48: {
                  BeginEvent var4 = (BeginEvent)x$1;
                  Task var10000 = this.task();
                  Task var5 = var4.task();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  TaskInfo var7 = this.taskInfo();
                  TaskInfo var6 = var4.taskInfo();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
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

   public BeginEvent(final Task task, final TaskInfo taskInfo) {
      this.task = task;
      this.taskInfo = taskInfo;
      Product.$init$(this);
   }
}
