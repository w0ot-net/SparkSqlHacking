package org.apache.spark.deploy.master;

import java.io.Serializable;
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
   bytes = "\u0006\u0005\u0005\u001de!\u0002\u0010 \u0001\u000eJ\u0003\u0002\u0003!\u0001\u0005+\u0007I\u0011A!\t\u0011!\u0003!\u0011#Q\u0001\n\tC\u0001\"\u0013\u0001\u0003\u0016\u0004%\tA\u0013\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u000b\"AA\n\u0001BK\u0002\u0013\u0005Q\n\u0003\u0005X\u0001\tE\t\u0015!\u0003O\u0011\u0015A\u0006\u0001\"\u0001Z\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dq\u0001\u001a\u0001\u0012\u0002\u0013\u0005Q\rC\u0004q\u0001E\u0005I\u0011A9\t\u000fM\u0004\u0011\u0013!C\u0001i\"9a\u000fAA\u0001\n\u0003:\b\u0002CA\u0001\u0001\u0005\u0005I\u0011\u0001&\t\u0013\u0005\r\u0001!!A\u0005\u0002\u0005\u0015\u0001\"CA\t\u0001\u0005\u0005I\u0011IA\n\u0011%\t\t\u0003AA\u0001\n\u0003\t\u0019\u0003C\u0005\u0002.\u0001\t\t\u0011\"\u0011\u00020!I\u00111\u0007\u0001\u0002\u0002\u0013\u0005\u0013Q\u0007\u0005\n\u0003o\u0001\u0011\u0011!C!\u0003sA\u0011\"a\u000f\u0001\u0003\u0003%\t%!\u0010\b\u0015\u0005\u0005s$!A\t\u0002\r\n\u0019EB\u0005\u001f?\u0005\u0005\t\u0012A\u0012\u0002F!1\u0001L\u0006C\u0001\u0003;B\u0011\"a\u000e\u0017\u0003\u0003%)%!\u000f\t\u0013\u0005}c#!A\u0005\u0002\u0006\u0005\u0004\u0002CA5-E\u0005I\u0011\u0001;\t\u0013\u0005-d#!A\u0005\u0002\u00065\u0004\u0002CA>-E\u0005I\u0011\u0001;\t\u0013\u0005ud#!A\u0005\n\u0005}$aG#yK\u000e,Ho\u001c:SKN|WO]2f\t\u0016\u001c8M]5qi&|gN\u0003\u0002!C\u00051Q.Y:uKJT!AI\u0012\u0002\r\u0011,\u0007\u000f\\8z\u0015\t!S%A\u0003ta\u0006\u00148N\u0003\u0002'O\u00051\u0011\r]1dQ\u0016T\u0011\u0001K\u0001\u0004_J<7\u0003\u0002\u0001+aM\u0002\"a\u000b\u0018\u000e\u00031R\u0011!L\u0001\u0006g\u000e\fG.Y\u0005\u0003_1\u0012a!\u00118z%\u00164\u0007CA\u00162\u0013\t\u0011DFA\u0004Qe>$Wo\u0019;\u0011\u0005QjdBA\u001b<\u001d\t1$(D\u00018\u0015\tA\u0014(\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005i\u0013B\u0001\u001f-\u0003\u001d\u0001\u0018mY6bO\u0016L!AP \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005qb\u0013\u0001E2pe\u0016\u001c\b+\u001a:Fq\u0016\u001cW\u000f^8s+\u0005\u0011\u0005cA\u0016D\u000b&\u0011A\t\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005-2\u0015BA$-\u0005\rIe\u000e^\u0001\u0012G>\u0014Xm\u001d)fe\u0016CXmY;u_J\u0004\u0013aE7f[>\u0014\u00180\u00142QKJ,\u00050Z2vi>\u0014X#A#\u0002)5,Wn\u001c:z\u001b\n\u0004VM]#yK\u000e,Ho\u001c:!\u0003i\u0019Wo\u001d;p[J+7o\\;sG\u0016\u001c\b+\u001a:Fq\u0016\u001cW\u000f^8s+\u0005q\u0005c\u0001\u001bP#&\u0011\u0001k\u0010\u0002\u0004'\u0016\f\bC\u0001*V\u001b\u0005\u0019&B\u0001+$\u0003!\u0011Xm]8ve\u000e,\u0017B\u0001,T\u0005M\u0011Vm]8ve\u000e,'+Z9vSJ,W.\u001a8u\u0003m\u0019Wo\u001d;p[J+7o\\;sG\u0016\u001c\b+\u001a:Fq\u0016\u001cW\u000f^8sA\u00051A(\u001b8jiz\"BA\u0017/^=B\u00111\fA\u0007\u0002?!)\u0001i\u0002a\u0001\u0005\")\u0011j\u0002a\u0001\u000b\"9Aj\u0002I\u0001\u0002\u0004q\u0015\u0001B2paf$BAW1cG\"9\u0001\t\u0003I\u0001\u0002\u0004\u0011\u0005bB%\t!\u0003\u0005\r!\u0012\u0005\b\u0019\"\u0001\n\u00111\u0001O\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u001a\u0016\u0003\u0005\u001e\\\u0013\u0001\u001b\t\u0003S:l\u0011A\u001b\u0006\u0003W2\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u00055d\u0013AC1o]>$\u0018\r^5p]&\u0011qN\u001b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002e*\u0012QiZ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\u0005)(F\u0001(h\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0001\u0010\u0005\u0002z}6\t!P\u0003\u0002|y\u0006!A.\u00198h\u0015\u0005i\u0018\u0001\u00026bm\u0006L!a >\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0002\u0002\u000eA\u00191&!\u0003\n\u0007\u0005-AFA\u0002B]fD\u0001\"a\u0004\u000f\u0003\u0003\u0005\r!R\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005U\u0001CBA\f\u0003;\t9!\u0004\u0002\u0002\u001a)\u0019\u00111\u0004\u0017\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002 \u0005e!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\n\u0002,A\u00191&a\n\n\u0007\u0005%BFA\u0004C_>dW-\u00198\t\u0013\u0005=\u0001#!AA\u0002\u0005\u001d\u0011A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2\u0001_A\u0019\u0011!\ty!EA\u0001\u0002\u0004)\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0015\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002q\u00061Q-];bYN$B!!\n\u0002@!I\u0011q\u0002\u000b\u0002\u0002\u0003\u0007\u0011qA\u0001\u001c\u000bb,7-\u001e;peJ+7o\\;sG\u0016$Um]2sSB$\u0018n\u001c8\u0011\u0005m32#\u0002\f\u0002H\u0005M\u0003\u0003CA%\u0003\u001f\u0012UI\u0014.\u000e\u0005\u0005-#bAA'Y\u00059!/\u001e8uS6,\u0017\u0002BA)\u0003\u0017\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0011\t)&a\u0017\u000e\u0005\u0005]#bAA-y\u0006\u0011\u0011n\\\u0005\u0004}\u0005]CCAA\"\u0003\u0015\t\u0007\u000f\u001d7z)\u001dQ\u00161MA3\u0003OBQ\u0001Q\rA\u0002\tCQ!S\rA\u0002\u0015Cq\u0001T\r\u0011\u0002\u0003\u0007a*A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00134\u0003\u001d)h.\u00199qYf$B!a\u001c\u0002xA!1fQA9!\u0019Y\u00131\u000f\"F\u001d&\u0019\u0011Q\u000f\u0017\u0003\rQ+\b\u000f\\34\u0011!\tIhGA\u0001\u0002\u0004Q\u0016a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!!\u0011\u0007e\f\u0019)C\u0002\u0002\u0006j\u0014aa\u00142kK\u000e$\b"
)
public class ExecutorResourceDescription implements Product, Serializable {
   private final Option coresPerExecutor;
   private final int memoryMbPerExecutor;
   private final Seq customResourcesPerExecutor;

   public static Seq $lessinit$greater$default$3() {
      return ExecutorResourceDescription$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final ExecutorResourceDescription x$0) {
      return ExecutorResourceDescription$.MODULE$.unapply(x$0);
   }

   public static Seq apply$default$3() {
      return ExecutorResourceDescription$.MODULE$.apply$default$3();
   }

   public static ExecutorResourceDescription apply(final Option coresPerExecutor, final int memoryMbPerExecutor, final Seq customResourcesPerExecutor) {
      return ExecutorResourceDescription$.MODULE$.apply(coresPerExecutor, memoryMbPerExecutor, customResourcesPerExecutor);
   }

   public static Function1 tupled() {
      return ExecutorResourceDescription$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ExecutorResourceDescription$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Option coresPerExecutor() {
      return this.coresPerExecutor;
   }

   public int memoryMbPerExecutor() {
      return this.memoryMbPerExecutor;
   }

   public Seq customResourcesPerExecutor() {
      return this.customResourcesPerExecutor;
   }

   public ExecutorResourceDescription copy(final Option coresPerExecutor, final int memoryMbPerExecutor, final Seq customResourcesPerExecutor) {
      return new ExecutorResourceDescription(coresPerExecutor, memoryMbPerExecutor, customResourcesPerExecutor);
   }

   public Option copy$default$1() {
      return this.coresPerExecutor();
   }

   public int copy$default$2() {
      return this.memoryMbPerExecutor();
   }

   public Seq copy$default$3() {
      return this.customResourcesPerExecutor();
   }

   public String productPrefix() {
      return "ExecutorResourceDescription";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.coresPerExecutor();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.memoryMbPerExecutor());
         }
         case 2 -> {
            return this.customResourcesPerExecutor();
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
      return x$1 instanceof ExecutorResourceDescription;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "coresPerExecutor";
         }
         case 1 -> {
            return "memoryMbPerExecutor";
         }
         case 2 -> {
            return "customResourcesPerExecutor";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.coresPerExecutor()));
      var1 = Statics.mix(var1, this.memoryMbPerExecutor());
      var1 = Statics.mix(var1, Statics.anyHash(this.customResourcesPerExecutor()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof ExecutorResourceDescription) {
               ExecutorResourceDescription var4 = (ExecutorResourceDescription)x$1;
               if (this.memoryMbPerExecutor() == var4.memoryMbPerExecutor()) {
                  label52: {
                     Option var10000 = this.coresPerExecutor();
                     Option var5 = var4.coresPerExecutor();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     Seq var7 = this.customResourcesPerExecutor();
                     Seq var6 = var4.customResourcesPerExecutor();
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

   public ExecutorResourceDescription(final Option coresPerExecutor, final int memoryMbPerExecutor, final Seq customResourcesPerExecutor) {
      this.coresPerExecutor = coresPerExecutor;
      this.memoryMbPerExecutor = memoryMbPerExecutor;
      this.customResourcesPerExecutor = customResourcesPerExecutor;
      Product.$init$(this);
   }
}
