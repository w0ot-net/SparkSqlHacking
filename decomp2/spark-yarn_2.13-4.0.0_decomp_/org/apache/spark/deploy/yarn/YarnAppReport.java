package org.apache.spark.deploy.yarn;

import java.io.Serializable;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ee!\u0002\u000f\u001e\u0001\u0006:\u0003\u0002\u0003 \u0001\u0005+\u0007I\u0011A \t\u0011-\u0003!\u0011#Q\u0001\n\u0001C\u0001\u0002\u0014\u0001\u0003\u0016\u0004%\t!\u0014\u0005\t#\u0002\u0011\t\u0012)A\u0005\u001d\"A!\u000b\u0001BK\u0002\u0013\u00051\u000b\u0003\u0005`\u0001\tE\t\u0015!\u0003U\u0011\u0015\u0001\u0007\u0001\"\u0001b\u0011\u001d9\u0007!!A\u0005\u0002!Dq\u0001\u001c\u0001\u0012\u0002\u0013\u0005Q\u000eC\u0004y\u0001E\u0005I\u0011A=\t\u000fm\u0004\u0011\u0013!C\u0001y\"9a\u0010AA\u0001\n\u0003z\b\"CA\b\u0001\u0005\u0005I\u0011AA\t\u0011%\tI\u0002AA\u0001\n\u0003\tY\u0002C\u0005\u0002(\u0001\t\t\u0011\"\u0011\u0002*!I\u0011q\u0007\u0001\u0002\u0002\u0013\u0005\u0011\u0011\b\u0005\n\u0003\u0007\u0002\u0011\u0011!C!\u0003\u000bB\u0011\"!\u0013\u0001\u0003\u0003%\t%a\u0013\t\u0013\u00055\u0003!!A\u0005B\u0005=\u0003\"CA)\u0001\u0005\u0005I\u0011IA*\u000f)\t9&HA\u0001\u0012\u0003\t\u0013\u0011\f\u0004\n9u\t\t\u0011#\u0001\"\u00037Ba\u0001\u0019\f\u0005\u0002\u0005M\u0004\"CA'-\u0005\u0005IQIA(\u0011%\t)HFA\u0001\n\u0003\u000b9\bC\u0005\u0002\u0000Y\t\t\u0011\"!\u0002\u0002\"I\u0011q\u0012\f\u0002\u0002\u0013%\u0011\u0011\u0013\u0002\u000e3\u0006\u0014h.\u00119q%\u0016\u0004xN\u001d;\u000b\u0005yy\u0012\u0001B=be:T!\u0001I\u0011\u0002\r\u0011,\u0007\u000f\\8z\u0015\t\u00113%A\u0003ta\u0006\u00148N\u0003\u0002%K\u00051\u0011\r]1dQ\u0016T\u0011AJ\u0001\u0004_J<7\u0003\u0002\u0001)]E\u0002\"!\u000b\u0017\u000e\u0003)R\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[)\u0012a!\u00118z%\u00164\u0007CA\u00150\u0013\t\u0001$FA\u0004Qe>$Wo\u0019;\u0011\u0005IZdBA\u001a:\u001d\t!\u0004(D\u00016\u0015\t1t'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Y\u0013B\u0001\u001e+\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001P\u001f\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005iR\u0013\u0001C1qaN#\u0018\r^3\u0016\u0003\u0001\u0003\"!Q%\u000e\u0003\tS!a\u0011#\u0002\u000fI,7m\u001c:eg*\u0011QIR\u0001\u0004CBL'B\u0001\u0010H\u0015\tA5%\u0001\u0004iC\u0012|w\u000e]\u0005\u0003\u0015\n\u0013A#W1s]\u0006\u0003\b\u000f\\5dCRLwN\\*uCR,\u0017!C1qaN#\u0018\r^3!\u0003)1\u0017N\\1m'R\fG/Z\u000b\u0002\u001dB\u0011\u0011iT\u0005\u0003!\n\u0013aCR5oC2\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8Ti\u0006$Xo]\u0001\fM&t\u0017\r\\*uCR,\u0007%A\u0006eS\u0006<gn\\:uS\u000e\u001cX#\u0001+\u0011\u0007%*v+\u0003\u0002WU\t1q\n\u001d;j_:\u0004\"\u0001\u0017/\u000f\u0005eS\u0006C\u0001\u001b+\u0013\tY&&\u0001\u0004Qe\u0016$WMZ\u0005\u0003;z\u0013aa\u0015;sS:<'BA.+\u00031!\u0017.Y4o_N$\u0018nY:!\u0003\u0019a\u0014N\\5u}Q!!\rZ3g!\t\u0019\u0007!D\u0001\u001e\u0011\u0015qt\u00011\u0001A\u0011\u0015au\u00011\u0001O\u0011\u0015\u0011v\u00011\u0001U\u0003\u0011\u0019w\u000e]=\u0015\t\tL'n\u001b\u0005\b}!\u0001\n\u00111\u0001A\u0011\u001da\u0005\u0002%AA\u00029CqA\u0015\u0005\u0011\u0002\u0003\u0007A+\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u00039T#\u0001Q8,\u0003A\u0004\"!\u001d<\u000e\u0003IT!a\u001d;\u0002\u0013Ut7\r[3dW\u0016$'BA;+\u0003)\tgN\\8uCRLwN\\\u0005\u0003oJ\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012A\u001f\u0016\u0003\u001d>\fabY8qs\u0012\"WMZ1vYR$3'F\u0001~U\t!v.A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003\u0003\u0001B!a\u0001\u0002\u000e5\u0011\u0011Q\u0001\u0006\u0005\u0003\u000f\tI!\u0001\u0003mC:<'BAA\u0006\u0003\u0011Q\u0017M^1\n\u0007u\u000b)!\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u0014A\u0019\u0011&!\u0006\n\u0007\u0005]!FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u001e\u0005\r\u0002cA\u0015\u0002 %\u0019\u0011\u0011\u0005\u0016\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002&9\t\t\u00111\u0001\u0002\u0014\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u000b\u0011\r\u00055\u00121GA\u000f\u001b\t\tyCC\u0002\u00022)\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t)$a\f\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003w\t\t\u0005E\u0002*\u0003{I1!a\u0010+\u0005\u001d\u0011un\u001c7fC:D\u0011\"!\n\u0011\u0003\u0003\u0005\r!!\b\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003\u0003\t9\u0005C\u0005\u0002&E\t\t\u00111\u0001\u0002\u0014\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0014\u0005AAo\\*ue&tw\r\u0006\u0002\u0002\u0002\u00051Q-];bYN$B!a\u000f\u0002V!I\u0011Q\u0005\u000b\u0002\u0002\u0003\u0007\u0011QD\u0001\u000e3\u0006\u0014h.\u00119q%\u0016\u0004xN\u001d;\u0011\u0005\r42#\u0002\f\u0002^\u0005%\u0004\u0003CA0\u0003K\u0002e\n\u00162\u000e\u0005\u0005\u0005$bAA2U\u00059!/\u001e8uS6,\u0017\u0002BA4\u0003C\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0011\tY'!\u001d\u000e\u0005\u00055$\u0002BA8\u0003\u0013\t!![8\n\u0007q\ni\u0007\u0006\u0002\u0002Z\u0005)\u0011\r\u001d9msR9!-!\u001f\u0002|\u0005u\u0004\"\u0002 \u001a\u0001\u0004\u0001\u0005\"\u0002'\u001a\u0001\u0004q\u0005\"\u0002*\u001a\u0001\u0004!\u0016aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u0007\u000bY\t\u0005\u0003*+\u0006\u0015\u0005CB\u0015\u0002\b\u0002sE+C\u0002\u0002\n*\u0012a\u0001V;qY\u0016\u001c\u0004\u0002CAG5\u0005\u0005\t\u0019\u00012\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0014B!\u00111AAK\u0013\u0011\t9*!\u0002\u0003\r=\u0013'.Z2u\u0001"
)
public class YarnAppReport implements Product, Serializable {
   private final YarnApplicationState appState;
   private final FinalApplicationStatus finalState;
   private final Option diagnostics;

   public static Option unapply(final YarnAppReport x$0) {
      return YarnAppReport$.MODULE$.unapply(x$0);
   }

   public static YarnAppReport apply(final YarnApplicationState appState, final FinalApplicationStatus finalState, final Option diagnostics) {
      return YarnAppReport$.MODULE$.apply(appState, finalState, diagnostics);
   }

   public static Function1 tupled() {
      return YarnAppReport$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return YarnAppReport$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public YarnApplicationState appState() {
      return this.appState;
   }

   public FinalApplicationStatus finalState() {
      return this.finalState;
   }

   public Option diagnostics() {
      return this.diagnostics;
   }

   public YarnAppReport copy(final YarnApplicationState appState, final FinalApplicationStatus finalState, final Option diagnostics) {
      return new YarnAppReport(appState, finalState, diagnostics);
   }

   public YarnApplicationState copy$default$1() {
      return this.appState();
   }

   public FinalApplicationStatus copy$default$2() {
      return this.finalState();
   }

   public Option copy$default$3() {
      return this.diagnostics();
   }

   public String productPrefix() {
      return "YarnAppReport";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.appState();
         }
         case 1 -> {
            return this.finalState();
         }
         case 2 -> {
            return this.diagnostics();
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
      return x$1 instanceof YarnAppReport;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "appState";
         }
         case 1 -> {
            return "finalState";
         }
         case 2 -> {
            return "diagnostics";
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
            if (x$1 instanceof YarnAppReport) {
               label56: {
                  YarnAppReport var4 = (YarnAppReport)x$1;
                  YarnApplicationState var10000 = this.appState();
                  YarnApplicationState var5 = var4.appState();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  FinalApplicationStatus var8 = this.finalState();
                  FinalApplicationStatus var6 = var4.finalState();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  Option var9 = this.diagnostics();
                  Option var7 = var4.diagnostics();
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

   public YarnAppReport(final YarnApplicationState appState, final FinalApplicationStatus finalState, final Option diagnostics) {
      this.appState = appState;
      this.finalState = finalState;
      this.diagnostics = diagnostics;
      Product.$init$(this);
   }
}
