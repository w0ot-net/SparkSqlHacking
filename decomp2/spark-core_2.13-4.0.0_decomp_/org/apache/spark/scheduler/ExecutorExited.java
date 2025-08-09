package org.apache.spark.scheduler;

import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Md!B\u000e\u001d\u0001z!\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\u0005\u0003!\u0011#Q\u0001\nyB\u0001B\u0011\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\u000f\u0002\u0011\t\u0012)A\u0005\t\"A\u0001\n\u0001BK\u0002\u0013\u0005\u0011\nC\u0005S\u0001\tE\t\u0015!\u0003K'\")Q\u000b\u0001C\u0001-\"91\fAA\u0001\n\u0003a\u0006b\u00021\u0001#\u0003%\t!\u0019\u0005\bY\u0002\t\n\u0011\"\u0001n\u0011\u001dy\u0007!%A\u0005\u0002ADqA\u001d\u0001\u0002\u0002\u0013\u00053\u000fC\u0004|\u0001\u0005\u0005I\u0011A\u001f\t\u000fq\u0004\u0011\u0011!C\u0001{\"I\u0011q\u0001\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0002\u0005\n\u0003/\u0001\u0011\u0011!C\u0001\u00033A\u0011\"!\b\u0001\u0003\u0003%\t%a\b\t\u0013\u0005\r\u0002!!A\u0005B\u0005\u0015\u0002\"CA\u0014\u0001\u0005\u0005I\u0011IA\u0015\u000f!\ti\u0003\bE\u0001=\u0005=baB\u000e\u001d\u0011\u0003q\u0012\u0011\u0007\u0005\u0007+V!\t!a\u0011\t\u000f\u0005\u0015S\u0003\"\u0001\u0002H!I\u0011QI\u000b\u0002\u0002\u0013\u0005\u0015Q\n\u0005\n\u0003+*\u0012\u0011!CA\u0003/B\u0011\"!\u001b\u0016\u0003\u0003%I!a\u001b\u0003\u001d\u0015CXmY;u_J,\u00050\u001b;fI*\u0011QDH\u0001\ng\u000eDW\rZ;mKJT!a\b\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0005\u0012\u0013AB1qC\u000eDWMC\u0001$\u0003\ry'oZ\n\u0005\u0001\u0015Js\u0006\u0005\u0002'O5\tA$\u0003\u0002)9\t\u0011R\t_3dkR|'\u000fT8tgJ+\u0017m]8o!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001M\u001d\u000f\u0005E:dB\u0001\u001a7\u001b\u0005\u0019$B\u0001\u001b6\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u0017\n\u0005aZ\u0013a\u00029bG.\fw-Z\u0005\u0003um\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001O\u0016\u0002\u0011\u0015D\u0018\u000e^\"pI\u0016,\u0012A\u0010\t\u0003U}J!\u0001Q\u0016\u0003\u0007%sG/A\u0005fq&$8i\u001c3fA\u0005yQ\r_5u\u0007\u0006,8/\u001a3Cs\u0006\u0003\b/F\u0001E!\tQS)\u0003\u0002GW\t9!i\\8mK\u0006t\u0017\u0001E3ySR\u001c\u0015-^:fI\nK\u0018\t\u001d9!\u0003\u0019\u0011X-Y:p]V\t!\n\u0005\u0002L\u001f:\u0011A*\u0014\t\u0003e-J!AT\u0016\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0016K\u0001\u0004TiJLgn\u001a\u0006\u0003\u001d.\nqA]3bg>t\u0007%\u0003\u0002UO\u00059Q.Z:tC\u001e,\u0017A\u0002\u001fj]&$h\b\u0006\u0003X1fS\u0006C\u0001\u0014\u0001\u0011\u0015at\u00011\u0001?\u0011\u0015\u0011u\u00011\u0001E\u0011\u0015Au\u00011\u0001K\u0003\u0011\u0019w\u000e]=\u0015\t]kfl\u0018\u0005\by!\u0001\n\u00111\u0001?\u0011\u001d\u0011\u0005\u0002%AA\u0002\u0011Cq\u0001\u0013\u0005\u0011\u0002\u0003\u0007!*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\tT#AP2,\u0003\u0011\u0004\"!\u001a6\u000e\u0003\u0019T!a\u001a5\u0002\u0013Ut7\r[3dW\u0016$'BA5,\u0003)\tgN\\8uCRLwN\\\u0005\u0003W\u001a\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012A\u001c\u0016\u0003\t\u000e\fabY8qs\u0012\"WMZ1vYR$3'F\u0001rU\tQ5-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002iB\u0011QO_\u0007\u0002m*\u0011q\u000f_\u0001\u0005Y\u0006twMC\u0001z\u0003\u0011Q\u0017M^1\n\u0005A3\u0018\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004}\u0006\r\u0001C\u0001\u0016\u0000\u0013\r\t\ta\u000b\u0002\u0004\u0003:L\b\u0002CA\u0003\u001d\u0005\u0005\t\u0019\u0001 \u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tY\u0001E\u0003\u0002\u000e\u0005Ma0\u0004\u0002\u0002\u0010)\u0019\u0011\u0011C\u0016\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0016\u0005=!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$2\u0001RA\u000e\u0011!\t)\u0001EA\u0001\u0002\u0004q\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2\u0001^A\u0011\u0011!\t)!EA\u0001\u0002\u0004q\u0014\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003y\na!Z9vC2\u001cHc\u0001#\u0002,!A\u0011QA\n\u0002\u0002\u0003\u0007a0\u0001\bFq\u0016\u001cW\u000f^8s\u000bbLG/\u001a3\u0011\u0005\u0019*2#B\u000b\u00024\u0005e\u0002c\u0001\u0016\u00026%\u0019\u0011qG\u0016\u0003\r\u0005s\u0017PU3g!\u0011\tY$!\u0011\u000e\u0005\u0005u\"bAA q\u0006\u0011\u0011n\\\u0005\u0004u\u0005uBCAA\u0018\u0003\u0015\t\u0007\u000f\u001d7z)\u00159\u0016\u0011JA&\u0011\u0015at\u00031\u0001?\u0011\u0015\u0011u\u00031\u0001E)\u001d9\u0016qJA)\u0003'BQ\u0001\u0010\rA\u0002yBQA\u0011\rA\u0002\u0011CQ\u0001\u0013\rA\u0002)\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002Z\u0005\u0015\u0004#\u0002\u0016\u0002\\\u0005}\u0013bAA/W\t1q\n\u001d;j_:\u0004bAKA1}\u0011S\u0015bAA2W\t1A+\u001e9mKNB\u0001\"a\u001a\u001a\u0003\u0003\u0005\raV\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA7!\r)\u0018qN\u0005\u0004\u0003c2(AB(cU\u0016\u001cG\u000f"
)
public class ExecutorExited extends ExecutorLossReason implements Product {
   private final int exitCode;
   private final boolean exitCausedByApp;

   public static Option unapply(final ExecutorExited x$0) {
      return ExecutorExited$.MODULE$.unapply(x$0);
   }

   public static ExecutorExited apply(final int exitCode, final boolean exitCausedByApp, final String reason) {
      return ExecutorExited$.MODULE$.apply(exitCode, exitCausedByApp, reason);
   }

   public static ExecutorExited apply(final int exitCode, final boolean exitCausedByApp) {
      return ExecutorExited$.MODULE$.apply(exitCode, exitCausedByApp);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int exitCode() {
      return this.exitCode;
   }

   public boolean exitCausedByApp() {
      return this.exitCausedByApp;
   }

   public String reason() {
      return super.message();
   }

   public ExecutorExited copy(final int exitCode, final boolean exitCausedByApp, final String reason) {
      return new ExecutorExited(exitCode, exitCausedByApp, reason);
   }

   public int copy$default$1() {
      return this.exitCode();
   }

   public boolean copy$default$2() {
      return this.exitCausedByApp();
   }

   public String copy$default$3() {
      return this.reason();
   }

   public String productPrefix() {
      return "ExecutorExited";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.exitCode());
         }
         case 1 -> {
            return BoxesRunTime.boxToBoolean(this.exitCausedByApp());
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
      return x$1 instanceof ExecutorExited;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "exitCode";
         }
         case 1 -> {
            return "exitCausedByApp";
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
      var1 = Statics.mix(var1, this.exitCode());
      var1 = Statics.mix(var1, this.exitCausedByApp() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.reason()));
      return Statics.finalizeHash(var1, 3);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof ExecutorExited) {
               ExecutorExited var4 = (ExecutorExited)x$1;
               if (this.exitCode() == var4.exitCode() && this.exitCausedByApp() == var4.exitCausedByApp()) {
                  label48: {
                     String var10000 = this.reason();
                     String var5 = var4.reason();
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

   public ExecutorExited(final int exitCode, final boolean exitCausedByApp, final String reason) {
      super(reason);
      this.exitCode = exitCode;
      this.exitCausedByApp = exitCausedByApp;
      Product.$init$(this);
   }
}
