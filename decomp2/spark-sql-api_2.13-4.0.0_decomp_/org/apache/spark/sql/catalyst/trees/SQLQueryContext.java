package org.apache.spark.sql.catalyst.trees;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.QueryContext;
import org.apache.spark.QueryContextType;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t5a\u0001\u0002\u001c8\u0001\u0012C\u0001b\u0019\u0001\u0003\u0016\u0004%\t\u0001\u001a\u0005\tW\u0002\u0011\t\u0012)A\u0005K\"AA\u000e\u0001BK\u0002\u0013\u0005A\r\u0003\u0005n\u0001\tE\t\u0015!\u0003f\u0011!q\u0007A!f\u0001\n\u0003!\u0007\u0002C8\u0001\u0005#\u0005\u000b\u0011B3\t\u0011A\u0004!Q3A\u0005\u0002\u0011D\u0001\"\u001d\u0001\u0003\u0012\u0003\u0006I!\u001a\u0005\te\u0002\u0011)\u001a!C\u0001g\"AQ\u0010\u0001B\tB\u0003%A\u000f\u0003\u0005\u007f\u0001\tU\r\u0011\"\u0001t\u0011!y\bA!E!\u0002\u0013!\b\"CA\u0001\u0001\tU\r\u0011\"\u0001t\u0011%\t\u0019\u0001\u0001B\tB\u0003%A\u000fC\u0004\u0002\u0006\u0001!\t!a\u0002\t\u0013\u0005m\u0001A1A\u0005B\u0005u\u0001\u0002CA\u0013\u0001\u0001\u0006I!a\b\t\u0013\u0005\u001d\u0002A1A\u0005B\u0005%\u0002bBA\u0016\u0001\u0001\u0006I!\u001e\u0005\n\u0003[\u0001!\u0019!C!\u0003SAq!a\f\u0001A\u0003%Q\u000fC\u0005\u00022\u0001\u0011\r\u0011\"\u0011\u00024!9\u0011Q\u0007\u0001!\u0002\u0013A\u0007\"CA\u001c\u0001\t\u0007I\u0011IA\u001a\u0011\u001d\tI\u0004\u0001Q\u0001\n!D!\"a\u000f\u0001\u0011\u000b\u0007I\u0011IA\u0015\u0011)\ti\u0004\u0001EC\u0002\u0013\u0005\u0011\u0011\u0006\u0005\b\u0003\u007f\u0001A\u0011AA!\u0011\u001d\tI\u0005\u0001C!\u0003\u0017B\u0011\"!\u0014\u0001\u0003\u0003%\t!a\u0014\t\u0013\u0005}\u0003!%A\u0005\u0002\u0005\u0005\u0004\"CA<\u0001E\u0005I\u0011AA1\u0011%\tI\bAI\u0001\n\u0003\t\t\u0007C\u0005\u0002|\u0001\t\n\u0011\"\u0001\u0002b!I\u0011Q\u0010\u0001\u0012\u0002\u0013\u0005\u0011q\u0010\u0005\n\u0003\u0007\u0003\u0011\u0013!C\u0001\u0003\u007fB\u0011\"!\"\u0001#\u0003%\t!a \t\u0013\u0005\u001d\u0005!!A\u0005B\u0005%\u0005\"CAH\u0001\u0005\u0005I\u0011AA\u001a\u0011%\t\t\nAA\u0001\n\u0003\t\u0019\nC\u0005\u0002 \u0002\t\t\u0011\"\u0011\u0002\"\"I\u0011q\u0016\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0017\u0005\n\u0003k\u0003\u0011\u0011!C!\u0003oC\u0011\"a/\u0001\u0003\u0003%\t%!0\t\u0013\u0005}\u0006!!A\u0005B\u0005\u0005\u0007\"CAb\u0001\u0005\u0005I\u0011IAc\u000f%\tImNA\u0001\u0012\u0003\tYM\u0002\u00057o\u0005\u0005\t\u0012AAg\u0011\u001d\t)\u0001\rC\u0001\u0003KD\u0011\"a01\u0003\u0003%)%!1\t\u0013\u0005\u001d\b'!A\u0005\u0002\u0006%\b\"CA}a\u0005\u0005I\u0011QA~\u0011%\u0011I\u0001MA\u0001\n\u0013\u0011YAA\bT#2\u000bV/\u001a:z\u0007>tG/\u001a=u\u0015\tA\u0014(A\u0003ue\u0016,7O\u0003\u0002;w\u0005A1-\u0019;bYf\u001cHO\u0003\u0002={\u0005\u00191/\u001d7\u000b\u0005yz\u0014!B:qCJ\\'B\u0001!B\u0003\u0019\t\u0007/Y2iK*\t!)A\u0002pe\u001e\u001c\u0001aE\u0003\u0001\u000b6\u000bv\u000b\u0005\u0002G\u00176\tqI\u0003\u0002I\u0013\u0006!A.\u00198h\u0015\u0005Q\u0015\u0001\u00026bm\u0006L!\u0001T$\u0003\r=\u0013'.Z2u!\tqu*D\u0001>\u0013\t\u0001VH\u0001\u0007Rk\u0016\u0014\u0018pQ8oi\u0016DH\u000f\u0005\u0002S+6\t1KC\u0001U\u0003\u0015\u00198-\u00197b\u0013\t16KA\u0004Qe>$Wo\u0019;\u0011\u0005a\u0003gBA-_\u001d\tQV,D\u0001\\\u0015\ta6)\u0001\u0004=e>|GOP\u0005\u0002)&\u0011qlU\u0001\ba\u0006\u001c7.Y4f\u0013\t\t'M\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002`'\u0006!A.\u001b8f+\u0005)\u0007c\u0001*gQ&\u0011qm\u0015\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005IK\u0017B\u00016T\u0005\rIe\u000e^\u0001\u0006Y&tW\rI\u0001\u000egR\f'\u000f\u001e)pg&$\u0018n\u001c8\u0002\u001dM$\u0018M\u001d;Q_NLG/[8oA\u0005\u0001rN]5hS:\u001cF/\u0019:u\u0013:$W\r_\u0001\u0012_JLw-\u001b8Ti\u0006\u0014H/\u00138eKb\u0004\u0013aD8sS\u001eLgn\u0015;pa&sG-\u001a=\u0002!=\u0014\u0018nZ5o'R|\u0007/\u00138eKb\u0004\u0013aB:rYR+\u0007\u0010^\u000b\u0002iB\u0019!KZ;\u0011\u0005YThBA<y!\tQ6+\u0003\u0002z'\u00061\u0001K]3eK\u001aL!a\u001f?\u0003\rM#(/\u001b8h\u0015\tI8+\u0001\u0005tc2$V\r\u001f;!\u0003Ay'/[4j]>\u0013'.Z2u)f\u0004X-A\tpe&<\u0017N\\(cU\u0016\u001cG\u000fV=qK\u0002\n\u0001c\u001c:jO&twJ\u00196fGRt\u0015-\\3\u0002#=\u0014\u0018nZ5o\u001f\nTWm\u0019;OC6,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0011\u0003\u0013\ti!a\u0004\u0002\u0012\u0005M\u0011QCA\f\u00033\u00012!a\u0003\u0001\u001b\u00059\u0004\"B2\u0010\u0001\u0004)\u0007\"\u00027\u0010\u0001\u0004)\u0007\"\u00028\u0010\u0001\u0004)\u0007\"\u00029\u0010\u0001\u0004)\u0007\"\u0002:\u0010\u0001\u0004!\b\"\u0002@\u0010\u0001\u0004!\bBBA\u0001\u001f\u0001\u0007A/A\u0006d_:$X\r\u001f;UsB,WCAA\u0010!\rq\u0015\u0011E\u0005\u0004\u0003Gi$\u0001E)vKJL8i\u001c8uKb$H+\u001f9f\u00031\u0019wN\u001c;fqR$\u0016\u0010]3!\u0003)y'M[3diRK\b/Z\u000b\u0002k\u0006YqN\u00196fGR$\u0016\u0010]3!\u0003)y'M[3di:\u000bW.Z\u0001\f_\nTWm\u0019;OC6,\u0007%\u0001\u0006ti\u0006\u0014H/\u00138eKb,\u0012\u0001[\u0001\fgR\f'\u000f^%oI\u0016D\b%A\u0005ti>\u0004\u0018J\u001c3fq\u0006Q1\u000f^8q\u0013:$W\r\u001f\u0011\u0002\u000fM,X.\\1ss\u0006AaM]1h[\u0016tG/A\u0004jgZ\u000bG.\u001b3\u0016\u0005\u0005\r\u0003c\u0001*\u0002F%\u0019\u0011qI*\u0003\u000f\t{w\u000e\\3b]\u0006A1-\u00197m'&$X\rF\u0001v\u0003\u0011\u0019w\u000e]=\u0015!\u0005%\u0011\u0011KA*\u0003+\n9&!\u0017\u0002\\\u0005u\u0003bB2\u001f!\u0003\u0005\r!\u001a\u0005\bYz\u0001\n\u00111\u0001f\u0011\u001dqg\u0004%AA\u0002\u0015Dq\u0001\u001d\u0010\u0011\u0002\u0003\u0007Q\rC\u0004s=A\u0005\t\u0019\u0001;\t\u000fyt\u0002\u0013!a\u0001i\"A\u0011\u0011\u0001\u0010\u0011\u0002\u0003\u0007A/\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\r$fA3\u0002f-\u0012\u0011q\r\t\u0005\u0003S\n\u0019(\u0004\u0002\u0002l)!\u0011QNA8\u0003%)hn\u00195fG.,GMC\u0002\u0002rM\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\t)(a\u001b\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\"\u0014AD2paf$C-\u001a4bk2$H%N\u000b\u0003\u0003\u0003S3\u0001^A3\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIY\nabY8qs\u0012\"WMZ1vYR$s'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003\u0017\u00032ARAG\u0013\tYx)\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005U\u00151\u0014\t\u0004%\u0006]\u0015bAAM'\n\u0019\u0011I\\=\t\u0011\u0005u\u0005&!AA\u0002!\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAR!\u0019\t)+a+\u0002\u00166\u0011\u0011q\u0015\u0006\u0004\u0003S\u001b\u0016AC2pY2,7\r^5p]&!\u0011QVAT\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\r\u00131\u0017\u0005\n\u0003;S\u0013\u0011!a\u0001\u0003+\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u00111RA]\u0011!\tijKA\u0001\u0002\u0004A\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003!\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u0017\u000ba!Z9vC2\u001cH\u0003BA\"\u0003\u000fD\u0011\"!(/\u0003\u0003\u0005\r!!&\u0002\u001fM\u000bF*U;fef\u001cuN\u001c;fqR\u00042!a\u00031'\u0015\u0001\u0014qZAn!5\t\t.a6fK\u0016,G\u000f\u001e;\u0002\n5\u0011\u00111\u001b\u0006\u0004\u0003+\u001c\u0016a\u0002:v]RLW.Z\u0005\u0005\u00033\f\u0019NA\tBEN$(/Y2u\rVt7\r^5p]^\u0002B!!8\u0002d6\u0011\u0011q\u001c\u0006\u0004\u0003CL\u0015AA5p\u0013\r\t\u0017q\u001c\u000b\u0003\u0003\u0017\fQ!\u00199qYf$\u0002#!\u0003\u0002l\u00065\u0018q^Ay\u0003g\f)0a>\t\u000b\r\u001c\u0004\u0019A3\t\u000b1\u001c\u0004\u0019A3\t\u000b9\u001c\u0004\u0019A3\t\u000bA\u001c\u0004\u0019A3\t\u000bI\u001c\u0004\u0019\u0001;\t\u000by\u001c\u0004\u0019\u0001;\t\r\u0005\u00051\u00071\u0001u\u0003\u001d)h.\u00199qYf$B!!@\u0003\u0006A!!KZA\u0000!)\u0011&\u0011A3fK\u0016$H\u000f^\u0005\u0004\u0005\u0007\u0019&A\u0002+va2,w\u0007C\u0005\u0003\bQ\n\t\u00111\u0001\u0002\n\u0005\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003\u0015\u0003"
)
public class SQLQueryContext implements QueryContext, Product, Serializable {
   private String summary;
   private String fragment;
   private final Option line;
   private final Option startPosition;
   private final Option originStartIndex;
   private final Option originStopIndex;
   private final Option sqlText;
   private final Option originObjectType;
   private final Option originObjectName;
   private final QueryContextType contextType;
   private final String objectType;
   private final String objectName;
   private final int startIndex;
   private final int stopIndex;
   private volatile byte bitmap$0;

   public static Option unapply(final SQLQueryContext x$0) {
      return SQLQueryContext$.MODULE$.unapply(x$0);
   }

   public static SQLQueryContext apply(final Option line, final Option startPosition, final Option originStartIndex, final Option originStopIndex, final Option sqlText, final Option originObjectType, final Option originObjectName) {
      return SQLQueryContext$.MODULE$.apply(line, startPosition, originStartIndex, originStopIndex, sqlText, originObjectType, originObjectName);
   }

   public static Function1 tupled() {
      return SQLQueryContext$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SQLQueryContext$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Option line() {
      return this.line;
   }

   public Option startPosition() {
      return this.startPosition;
   }

   public Option originStartIndex() {
      return this.originStartIndex;
   }

   public Option originStopIndex() {
      return this.originStopIndex;
   }

   public Option sqlText() {
      return this.sqlText;
   }

   public Option originObjectType() {
      return this.originObjectType;
   }

   public Option originObjectName() {
      return this.originObjectName;
   }

   public QueryContextType contextType() {
      return this.contextType;
   }

   public String objectType() {
      return this.objectType;
   }

   public String objectName() {
      return this.objectName;
   }

   public int startIndex() {
      return this.startIndex;
   }

   public int stopIndex() {
      return this.stopIndex;
   }

   private String summary$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            String var10001;
            if (!this.isValid()) {
               var10001 = "";
            } else {
               String positionContext = this.line().isDefined() && this.startPosition().isDefined() ? " (line " + this.line().get() + ", position " + (BoxesRunTime.unboxToInt(this.startPosition().get()) + 1) + ")" : "";
               String objectContext = this.originObjectType().isDefined() && this.originObjectName().isDefined() ? " of " + this.originObjectType().get() + " " + this.originObjectName().get() : "";
               StringBuilder builder = new StringBuilder();
               builder.$plus$plus$eq("== SQL" + objectContext + positionContext + " ==\n");
               String text = (String)this.sqlText().get();
               int start = .MODULE$.max(BoxesRunTime.unboxToInt(this.originStartIndex().get()), 0);
               int stop = .MODULE$.min(BoxesRunTime.unboxToInt(this.originStopIndex().getOrElse((JFunction0.mcI.sp)() -> text.length() - 1)), text.length() - 1);
               int maxExtraContextLength = 32;
               String truncatedText = "...";

               int lineStartIndex;
               for(lineStartIndex = start; lineStartIndex >= 0 && start - lineStartIndex <= maxExtraContextLength && text.charAt(lineStartIndex) != '\n'; --lineStartIndex) {
               }

               boolean startTruncated = start - lineStartIndex > maxExtraContextLength;
               IntRef currentIndex = IntRef.create(lineStartIndex);
               if (startTruncated) {
                  currentIndex.elem -= truncatedText.length();
               }

               int lineStopIndex;
               for(lineStopIndex = stop; lineStopIndex < text.length() && lineStopIndex - stop <= maxExtraContextLength && text.charAt(lineStopIndex) != '\n'; ++lineStopIndex) {
               }

               boolean stopTruncated = lineStopIndex - stop > maxExtraContextLength;
               String truncatedSubText = (startTruncated ? truncatedText : "") + text.substring(lineStartIndex + 1, lineStopIndex) + (stopTruncated ? truncatedText : "");
               String[] lines = truncatedSubText.split("\n");
               scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])lines), (lineText) -> {
                  builder.$plus$plus$eq(lineText + "\n");
                  ++currentIndex.elem;
                  scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), lineText.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(x$1) -> {
                     if (currentIndex.elem < start) {
                        builder.$plus$plus$eq(" ");
                     } else if (currentIndex.elem >= start && currentIndex.elem <= stop) {
                        builder.$plus$plus$eq("^");
                     } else {
                        BoxedUnit var10000 = BoxedUnit.UNIT;
                     }

                     ++currentIndex.elem;
                  });
                  return builder.$plus$plus$eq("\n");
               });
               var10001 = builder.result();
            }

            this.summary = var10001;
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var18) {
         throw var18;
      }

      return this.summary;
   }

   public String summary() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.summary$lzycompute() : this.summary;
   }

   private String fragment$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.fragment = !this.isValid() ? "" : ((String)this.sqlText().get()).substring(BoxesRunTime.unboxToInt(this.originStartIndex().get()), BoxesRunTime.unboxToInt(this.originStopIndex().get()) + 1);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.fragment;
   }

   public String fragment() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.fragment$lzycompute() : this.fragment;
   }

   public boolean isValid() {
      return this.sqlText().isDefined() && this.originStartIndex().isDefined() && this.originStopIndex().isDefined() && BoxesRunTime.unboxToInt(this.originStartIndex().get()) >= 0 && BoxesRunTime.unboxToInt(this.originStopIndex().get()) < ((String)this.sqlText().get()).length() && BoxesRunTime.unboxToInt(this.originStartIndex().get()) <= BoxesRunTime.unboxToInt(this.originStopIndex().get());
   }

   public String callSite() {
      throw org.apache.spark.SparkUnsupportedOperationException..MODULE$.apply();
   }

   public SQLQueryContext copy(final Option line, final Option startPosition, final Option originStartIndex, final Option originStopIndex, final Option sqlText, final Option originObjectType, final Option originObjectName) {
      return new SQLQueryContext(line, startPosition, originStartIndex, originStopIndex, sqlText, originObjectType, originObjectName);
   }

   public Option copy$default$1() {
      return this.line();
   }

   public Option copy$default$2() {
      return this.startPosition();
   }

   public Option copy$default$3() {
      return this.originStartIndex();
   }

   public Option copy$default$4() {
      return this.originStopIndex();
   }

   public Option copy$default$5() {
      return this.sqlText();
   }

   public Option copy$default$6() {
      return this.originObjectType();
   }

   public Option copy$default$7() {
      return this.originObjectName();
   }

   public String productPrefix() {
      return "SQLQueryContext";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.line();
         }
         case 1 -> {
            return this.startPosition();
         }
         case 2 -> {
            return this.originStartIndex();
         }
         case 3 -> {
            return this.originStopIndex();
         }
         case 4 -> {
            return this.sqlText();
         }
         case 5 -> {
            return this.originObjectType();
         }
         case 6 -> {
            return this.originObjectName();
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
      return x$1 instanceof SQLQueryContext;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "line";
         }
         case 1 -> {
            return "startPosition";
         }
         case 2 -> {
            return "originStartIndex";
         }
         case 3 -> {
            return "originStopIndex";
         }
         case 4 -> {
            return "sqlText";
         }
         case 5 -> {
            return "originObjectType";
         }
         case 6 -> {
            return "originObjectName";
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
      boolean var18;
      if (this != x$1) {
         label95: {
            if (x$1 instanceof SQLQueryContext) {
               label88: {
                  SQLQueryContext var4 = (SQLQueryContext)x$1;
                  Option var10000 = this.line();
                  Option var5 = var4.line();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label88;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label88;
                  }

                  var10000 = this.startPosition();
                  Option var6 = var4.startPosition();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label88;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label88;
                  }

                  var10000 = this.originStartIndex();
                  Option var7 = var4.originStartIndex();
                  if (var10000 == null) {
                     if (var7 != null) {
                        break label88;
                     }
                  } else if (!var10000.equals(var7)) {
                     break label88;
                  }

                  var10000 = this.originStopIndex();
                  Option var8 = var4.originStopIndex();
                  if (var10000 == null) {
                     if (var8 != null) {
                        break label88;
                     }
                  } else if (!var10000.equals(var8)) {
                     break label88;
                  }

                  var10000 = this.sqlText();
                  Option var9 = var4.sqlText();
                  if (var10000 == null) {
                     if (var9 != null) {
                        break label88;
                     }
                  } else if (!var10000.equals(var9)) {
                     break label88;
                  }

                  var10000 = this.originObjectType();
                  Option var10 = var4.originObjectType();
                  if (var10000 == null) {
                     if (var10 != null) {
                        break label88;
                     }
                  } else if (!var10000.equals(var10)) {
                     break label88;
                  }

                  var10000 = this.originObjectName();
                  Option var11 = var4.originObjectName();
                  if (var10000 == null) {
                     if (var11 != null) {
                        break label88;
                     }
                  } else if (!var10000.equals(var11)) {
                     break label88;
                  }

                  if (var4.canEqual(this)) {
                     break label95;
                  }
               }
            }

            var18 = false;
            return var18;
         }
      }

      var18 = true;
      return var18;
   }

   public SQLQueryContext(final Option line, final Option startPosition, final Option originStartIndex, final Option originStopIndex, final Option sqlText, final Option originObjectType, final Option originObjectName) {
      this.line = line;
      this.startPosition = startPosition;
      this.originStartIndex = originStartIndex;
      this.originStopIndex = originStopIndex;
      this.sqlText = sqlText;
      this.originObjectType = originObjectType;
      this.originObjectName = originObjectName;
      Product.$init$(this);
      this.contextType = QueryContextType.SQL;
      this.objectType = (String)originObjectType.getOrElse(() -> "");
      this.objectName = (String)originObjectName.getOrElse(() -> "");
      this.startIndex = BoxesRunTime.unboxToInt(originStartIndex.getOrElse((JFunction0.mcI.sp)() -> -1));
      this.stopIndex = BoxesRunTime.unboxToInt(originStopIndex.getOrElse((JFunction0.mcI.sp)() -> -1));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
