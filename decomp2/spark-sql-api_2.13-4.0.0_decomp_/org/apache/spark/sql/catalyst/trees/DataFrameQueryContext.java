package org.apache.spark.sql.catalyst.trees;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.QueryContext;
import org.apache.spark.QueryContextType;
import org.apache.spark.SparkUnsupportedOperationException.;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ef\u0001\u0002\u0013&\u0001JB\u0001\"\u0015\u0001\u0003\u0016\u0004%\tA\u0015\u0005\t3\u0002\u0011\t\u0012)A\u0005'\"A!\f\u0001BK\u0002\u0013\u00051\f\u0003\u0005k\u0001\tE\t\u0015!\u0003]\u0011\u0015Y\u0007\u0001\"\u0001m\u0011\u001d\t\bA1A\u0005BIDaA\u001e\u0001!\u0002\u0013\u0019\b\"B<\u0001\t\u0003B\b\"B=\u0001\t\u0003B\b\"\u0002>\u0001\t\u0003Z\b\"B@\u0001\t\u0003Z\b\"CA\u0001\u0001\t\u0007I\u0011IA\u0002\u0011\u001d\t)\u0001\u0001Q\u0001\n\tD\u0011\"a\u0002\u0001\u0005\u0004%\t%a\u0001\t\u000f\u0005%\u0001\u0001)A\u0005E\"Q\u00111\u0002\u0001\t\u0006\u0004%\t%a\u0001\t\u0013\u00055\u0001!!A\u0005\u0002\u0005=\u0001\"CA\u000b\u0001E\u0005I\u0011AA\f\u0011%\ti\u0003AI\u0001\n\u0003\ty\u0003C\u0005\u00024\u0001\t\t\u0011\"\u0011\u00026!I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0011Q\b\u0005\n\u0003\u007f\u0001\u0011\u0011!C\u0001\u0003\u0003B\u0011\"!\u0014\u0001\u0003\u0003%\t%a\u0014\t\u0013\u0005u\u0003!!A\u0005\u0002\u0005}\u0003\"CA5\u0001\u0005\u0005I\u0011IA6\u0011!\ty\u0007AA\u0001\n\u0003Z\b\"CA9\u0001\u0005\u0005I\u0011IA:\u0011%\t)\bAA\u0001\n\u0003\n9hB\u0005\u0002|\u0015\n\t\u0011#\u0001\u0002~\u0019AA%JA\u0001\u0012\u0003\ty\b\u0003\u0004l=\u0011\u0005\u0011q\u0013\u0005\n\u0003cr\u0012\u0011!C#\u0003gB\u0011\"!'\u001f\u0003\u0003%\t)a'\t\u0013\u0005\u0005f$!A\u0005\u0002\u0006\r\u0006\"CAW=\u0005\u0005I\u0011BAX\u0005U!\u0015\r^1Ge\u0006lW-U;fef\u001cuN\u001c;fqRT!AJ\u0014\u0002\u000bQ\u0014X-Z:\u000b\u0005!J\u0013\u0001C2bi\u0006d\u0017p\u001d;\u000b\u0005)Z\u0013aA:rY*\u0011A&L\u0001\u0006gB\f'o\u001b\u0006\u0003]=\na!\u00199bG\",'\"\u0001\u0019\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001\u00194hP#\u0011\u0005QJT\"A\u001b\u000b\u0005Y:\u0014\u0001\u00027b]\u001eT\u0011\u0001O\u0001\u0005U\u00064\u0018-\u0003\u0002;k\t1qJ\u00196fGR\u0004\"\u0001P\u001f\u000e\u0003-J!AP\u0016\u0003\u0019E+XM]=D_:$X\r\u001f;\u0011\u0005\u0001\u001bU\"A!\u000b\u0003\t\u000bQa]2bY\u0006L!\u0001R!\u0003\u000fA\u0013x\u000eZ;diB\u0011aI\u0014\b\u0003\u000f2s!\u0001S&\u000e\u0003%S!AS\u0019\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0011\u0015BA'B\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0014)\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u00055\u000b\u0015AC:uC\u000e\\GK]1dKV\t1\u000bE\u0002G)ZK!!\u0016)\u0003\u0007M+\u0017\u000f\u0005\u00025/&\u0011\u0001,\u000e\u0002\u0012'R\f7m\u001b+sC\u000e,W\t\\3nK:$\u0018aC:uC\u000e\\GK]1dK\u0002\n1\u0003]=ta\u0006\u00148.\u0012:s_J\u001cuN\u001c;fqR,\u0012\u0001\u0018\t\u0004\u0001v{\u0016B\u00010B\u0005\u0019y\u0005\u000f^5p]B!\u0001\t\u00192c\u0013\t\t\u0017I\u0001\u0004UkBdWM\r\t\u0003G\u001et!\u0001Z3\u0011\u0005!\u000b\u0015B\u00014B\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001.\u001b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0019\f\u0015\u0001\u00069zgB\f'o[#se>\u00148i\u001c8uKb$\b%\u0001\u0004=S:LGO\u0010\u000b\u0004[>\u0004\bC\u00018\u0001\u001b\u0005)\u0003\"B)\u0006\u0001\u0004\u0019\u0006\"\u0002.\u0006\u0001\u0004a\u0016aC2p]R,\u0007\u0010\u001e+za\u0016,\u0012a\u001d\t\u0003yQL!!^\u0016\u0003!E+XM]=D_:$X\r\u001f;UsB,\u0017\u0001D2p]R,\u0007\u0010\u001e+za\u0016\u0004\u0013AC8cU\u0016\u001cG\u000fV=qKR\t!-\u0001\u0006pE*,7\r\u001e(b[\u0016\f!b\u001d;beRLe\u000eZ3y)\u0005a\bC\u0001!~\u0013\tq\u0018IA\u0002J]R\f\u0011b\u001d;pa&sG-\u001a=\u0002\u0011\u0019\u0014\u0018mZ7f]R,\u0012AY\u0001\nMJ\fw-\\3oi\u0002\n\u0001bY1mYNKG/Z\u0001\nG\u0006dGnU5uK\u0002\nqa];n[\u0006\u0014\u00180\u0001\u0003d_BLH#B7\u0002\u0012\u0005M\u0001bB)\u0012!\u0003\u0005\ra\u0015\u0005\b5F\u0001\n\u00111\u0001]\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!\u0007+\u0007M\u000bYb\u000b\u0002\u0002\u001eA!\u0011qDA\u0015\u001b\t\t\tC\u0003\u0003\u0002$\u0005\u0015\u0012!C;oG\",7m[3e\u0015\r\t9#Q\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0016\u0003C\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!!\r+\u0007q\u000bY\"A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003o\u00012\u0001NA\u001d\u0013\tAW'\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001}\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0011\u0002JA\u0019\u0001)!\u0012\n\u0007\u0005\u001d\u0013IA\u0002B]fD\u0001\"a\u0013\u0017\u0003\u0003\u0005\r\u0001`\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005E\u0003CBA*\u00033\n\u0019%\u0004\u0002\u0002V)\u0019\u0011qK!\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\\\u0005U#\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0019\u0002hA\u0019\u0001)a\u0019\n\u0007\u0005\u0015\u0014IA\u0004C_>dW-\u00198\t\u0013\u0005-\u0003$!AA\u0002\u0005\r\u0013A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a\u000e\u0002n!A\u00111J\r\u0002\u0002\u0003\u0007A0\u0001\u0005iCND7i\u001c3f\u0003!!xn\u0015;sS:<GCAA\u001c\u0003\u0019)\u0017/^1mgR!\u0011\u0011MA=\u0011%\tY\u0005HA\u0001\u0002\u0004\t\u0019%A\u000bECR\fgI]1nKF+XM]=D_:$X\r\u001f;\u0011\u00059t2#\u0002\u0010\u0002\u0002\u00065\u0005cBAB\u0003\u0013\u001bF,\\\u0007\u0003\u0003\u000bS1!a\"B\u0003\u001d\u0011XO\u001c;j[\u0016LA!a#\u0002\u0006\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005=\u0015QS\u0007\u0003\u0003#S1!a%8\u0003\tIw.C\u0002P\u0003##\"!! \u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b5\fi*a(\t\u000bE\u000b\u0003\u0019A*\t\u000bi\u000b\u0003\u0019\u0001/\u0002\u000fUt\u0017\r\u001d9msR!\u0011QUAU!\u0011\u0001U,a*\u0011\t\u0001\u00037\u000b\u0018\u0005\t\u0003W\u0013\u0013\u0011!a\u0001[\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003M\u0002"
)
public class DataFrameQueryContext implements QueryContext, Product, Serializable {
   private String summary;
   private final Seq stackTrace;
   private final Option pysparkErrorContext;
   private final QueryContextType contextType;
   private final String fragment;
   private final String callSite;
   private volatile boolean bitmap$0;

   public static Option unapply(final DataFrameQueryContext x$0) {
      return DataFrameQueryContext$.MODULE$.unapply(x$0);
   }

   public static DataFrameQueryContext apply(final Seq stackTrace, final Option pysparkErrorContext) {
      return DataFrameQueryContext$.MODULE$.apply(stackTrace, pysparkErrorContext);
   }

   public static Function1 tupled() {
      return DataFrameQueryContext$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return DataFrameQueryContext$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq stackTrace() {
      return this.stackTrace;
   }

   public Option pysparkErrorContext() {
      return this.pysparkErrorContext;
   }

   public QueryContextType contextType() {
      return this.contextType;
   }

   public String objectType() {
      throw .MODULE$.apply();
   }

   public String objectName() {
      throw .MODULE$.apply();
   }

   public int startIndex() {
      throw .MODULE$.apply();
   }

   public int stopIndex() {
      throw .MODULE$.apply();
   }

   public String fragment() {
      return this.fragment;
   }

   public String callSite() {
      return this.callSite;
   }

   private String summary$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            StringBuilder builder = new StringBuilder();
            builder.$plus$plus$eq("== DataFrame ==\n");
            builder.$plus$plus$eq("\"");
            builder.$plus$plus$eq(this.fragment());
            builder.$plus$plus$eq("\"");
            builder.$plus$plus$eq(" was called from\n");
            builder.$plus$plus$eq(this.callSite());
            builder.$plus$eq(BoxesRunTime.boxToCharacter('\n'));
            this.summary = builder.result();
            this.bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.summary;
   }

   public String summary() {
      return !this.bitmap$0 ? this.summary$lzycompute() : this.summary;
   }

   public DataFrameQueryContext copy(final Seq stackTrace, final Option pysparkErrorContext) {
      return new DataFrameQueryContext(stackTrace, pysparkErrorContext);
   }

   public Seq copy$default$1() {
      return this.stackTrace();
   }

   public Option copy$default$2() {
      return this.pysparkErrorContext();
   }

   public String productPrefix() {
      return "DataFrameQueryContext";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.stackTrace();
         }
         case 1 -> {
            return this.pysparkErrorContext();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof DataFrameQueryContext;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stackTrace";
         }
         case 1 -> {
            return "pysparkErrorContext";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof DataFrameQueryContext) {
               label48: {
                  DataFrameQueryContext var4 = (DataFrameQueryContext)x$1;
                  Seq var10000 = this.stackTrace();
                  Seq var5 = var4.stackTrace();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Option var7 = this.pysparkErrorContext();
                  Option var6 = var4.pysparkErrorContext();
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

   public DataFrameQueryContext(final Seq stackTrace, final Option pysparkErrorContext) {
      this.stackTrace = stackTrace;
      this.pysparkErrorContext = pysparkErrorContext;
      Product.$init$(this);
      this.contextType = QueryContextType.DataFrame;
      this.fragment = (String)pysparkErrorContext.map((x$2) -> (String)x$2._1()).getOrElse(() -> (String)this.stackTrace().headOption().map((firstElem) -> {
            String methodName = firstElem.getMethodName();
            return methodName.length() > 1 && scala.collection.StringOps..MODULE$.apply$extension(scala.Predef..MODULE$.augmentString(methodName), 0) == '$' ? methodName.substring(1) : methodName;
         }).getOrElse(() -> ""));
      this.callSite = (String)pysparkErrorContext.map((x$3) -> (String)x$3._2()).getOrElse(() -> ((IterableOnceOps)this.stackTrace().tail()).mkString("\n"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
