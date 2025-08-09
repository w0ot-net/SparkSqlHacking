package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t%a!\u0002\u00192\u0001NZ\u0004\u0002\u0003,\u0001\u0005+\u0007I\u0011A,\t\u0011\u0001\u0004!\u0011#Q\u0001\naC\u0001\"\u0019\u0001\u0003\u0016\u0004%\tA\u0019\u0005\tM\u0002\u0011\t\u0012)A\u0005G\"Aq\r\u0001BK\u0002\u0013\u0005\u0001\u000e\u0003\u0005m\u0001\tE\t\u0015!\u0003j\u0011!i\u0007A!f\u0001\n\u0003A\u0007\u0002\u00038\u0001\u0005#\u0005\u000b\u0011B5\t\u0011=\u0004!Q3A\u0005\u0002!D\u0001\u0002\u001d\u0001\u0003\u0012\u0003\u0006I!\u001b\u0005\tc\u0002\u0011)\u001a!C!e\"A1\u0010\u0001B\tB\u0003%1\u000fC\u0003}\u0001\u0011\u0005Q\u0010\u0003\u0005\u0002\f\u0001!\t%MA\u0007\u0011\u0015!\u0004\u0001\"\u0011X\u0011!\ty\u0001\u0001C!c\u0005E\u0001\"CA\u000e\u0001\u0005\u0005I\u0011AA\u000f\u0011%\tY\u0003AI\u0001\n\u0003\ti\u0003C\u0005\u0002D\u0001\t\n\u0011\"\u0001\u0002F!I\u0011\u0011\n\u0001\u0012\u0002\u0013\u0005\u00111\n\u0005\n\u0003\u001f\u0002\u0011\u0013!C\u0001\u0003\u0017B\u0011\"!\u0015\u0001#\u0003%\t!a\u0013\t\u0013\u0005M\u0003!%A\u0005\u0002\u0005U\u0003\"CA-\u0001\u0005\u0005I\u0011IA.\u0011%\tY\u0007AA\u0001\n\u0003\ti\u0007C\u0005\u0002v\u0001\t\t\u0011\"\u0001\u0002x!I\u00111\u0011\u0001\u0002\u0002\u0013\u0005\u0013Q\u0011\u0005\n\u0003'\u0003\u0011\u0011!C\u0001\u0003+C\u0011\"!'\u0001\u0003\u0003%\t%a'\t\u0013\u0005}\u0005!!A\u0005B\u0005\u0005\u0006\"CAR\u0001\u0005\u0005I\u0011IAS\u0011%\t9\u000bAA\u0001\n\u0003\nIk\u0002\u0006\u0002.F\n\t\u0011#\u00014\u0003_3\u0011\u0002M\u0019\u0002\u0002#\u00051'!-\t\rq\u0014C\u0011AAe\u0011%\t\u0019KIA\u0001\n\u000b\n)\u000bC\u0005\u0002L\n\n\t\u0011\"!\u0002N\"I\u00111\u001c\u0012\u0012\u0002\u0013\u0005\u00111\n\u0005\n\u0003;\u0014\u0013\u0013!C\u0001\u0003\u0017B\u0011\"a8##\u0003%\t!a\u0013\t\u0013\u0005\u0005(%%A\u0005\u0002\u0005U\u0003\"CArE\u0005\u0005I\u0011QAs\u0011%\t9PII\u0001\n\u0003\tY\u0005C\u0005\u0002z\n\n\n\u0011\"\u0001\u0002L!I\u00111 \u0012\u0012\u0002\u0013\u0005\u00111\n\u0005\n\u0003{\u0014\u0013\u0013!C\u0001\u0003+B\u0011\"a@#\u0003\u0003%IA!\u0001\u0003%Us'/Z:pYZ,GMR;oGRLwN\u001c\u0006\u0003eM\n\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003iU\n1a]9m\u0015\t1t'A\u0003ta\u0006\u00148N\u0003\u00029s\u00051\u0011\r]1dQ\u0016T\u0011AO\u0001\u0004_J<7#\u0002\u0001=\u0005\u001aK\u0005CA\u001fA\u001b\u0005q$\"A \u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005s$AB!osJ+g\r\u0005\u0002D\t6\t\u0011'\u0003\u0002Fc\tQ1i\u001c7v[:tu\u000eZ3\u0011\u0005u:\u0015B\u0001%?\u0005\u001d\u0001&o\u001c3vGR\u0004\"AS*\u000f\u0005-\u000bfB\u0001'Q\u001b\u0005i%B\u0001(P\u0003\u0019a$o\\8u}\r\u0001\u0011\"A \n\u0005Is\u0014a\u00029bG.\fw-Z\u0005\u0003)V\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!A\u0015 \u0002\u0019\u0019,hn\u0019;j_:t\u0015-\\3\u0016\u0003a\u0003\"!W/\u000f\u0005i[\u0006C\u0001'?\u0013\taf(\u0001\u0004Qe\u0016$WMZ\u0005\u0003=~\u0013aa\u0015;sS:<'B\u0001/?\u000351WO\\2uS>tg*Y7fA\u0005I\u0011M]4v[\u0016tGo]\u000b\u0002GB\u0019!\n\u001a\"\n\u0005\u0015,&aA*fc\u0006Q\u0011M]4v[\u0016tGo\u001d\u0011\u0002\u0015%\u001cH)[:uS:\u001cG/F\u0001j!\ti$.\u0003\u0002l}\t9!i\\8mK\u0006t\u0017aC5t\t&\u001cH/\u001b8di\u0002\nQ#[:Vg\u0016\u0014H)\u001a4j]\u0016$g)\u001e8di&|g.\u0001\fjgV\u001bXM\u001d#fM&tW\r\u001a$v]\u000e$\u0018n\u001c8!\u0003)I7/\u00138uKJt\u0017\r\\\u0001\fSNLe\u000e^3s]\u0006d\u0007%\u0001\u0004pe&<\u0017N\\\u000b\u0002gB\u0011A/_\u0007\u0002k*\u0011ao^\u0001\u0006iJ,Wm\u001d\u0006\u0003qN\n\u0001bY1uC2L8\u000f^\u0005\u0003uV\u0014aa\u0014:jO&t\u0017aB8sS\u001eLg\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0019y|\u0018\u0011AA\u0002\u0003\u000b\t9!!\u0003\u0011\u0005\r\u0003\u0001\"\u0002,\u000e\u0001\u0004A\u0006\"B1\u000e\u0001\u0004\u0019\u0007bB4\u000e!\u0003\u0005\r!\u001b\u0005\b[6\u0001\n\u00111\u0001j\u0011\u001dyW\u0002%AA\u0002%Dq!]\u0007\u0011\u0002\u0003\u00071/A\u0005o_Jl\u0017\r\\5{KR\ta0\u0001\u0005dQ&dGM]3o+\t\t\u0019\u0002\u0005\u0003KI\u0006U\u0001cA\"\u0002\u0018%\u0019\u0011\u0011D\u0019\u0003\u001d\r{G.^7o\u001d>$W\rT5lK\u0006!1m\u001c9z)5q\u0018qDA\u0011\u0003G\t)#a\n\u0002*!9a+\u0005I\u0001\u0002\u0004A\u0006bB1\u0012!\u0003\u0005\ra\u0019\u0005\bOF\u0001\n\u00111\u0001j\u0011\u001di\u0017\u0003%AA\u0002%Dqa\\\t\u0011\u0002\u0003\u0007\u0011\u000eC\u0004r#A\u0005\t\u0019A:\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\u0006\u0016\u00041\u0006E2FAA\u001a!\u0011\t)$a\u0010\u000e\u0005\u0005]\"\u0002BA\u001d\u0003w\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005ub(\u0001\u0006b]:|G/\u0019;j_:LA!!\u0011\u00028\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011q\t\u0016\u0004G\u0006E\u0012AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003\u001bR3![A\u0019\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ\nabY8qs\u0012\"WMZ1vYR$S'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001c\u0016\u0005\u0005]#fA:\u00022\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u0018\u0011\t\u0005}\u0013\u0011N\u0007\u0003\u0003CRA!a\u0019\u0002f\u0005!A.\u00198h\u0015\t\t9'\u0001\u0003kCZ\f\u0017b\u00010\u0002b\u0005a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011q\u000e\t\u0004{\u0005E\u0014bAA:}\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011PA@!\ri\u00141P\u0005\u0004\u0003{r$aA!os\"I\u0011\u0011\u0011\u000e\u0002\u0002\u0003\u0007\u0011qN\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\u001d\u0005CBAE\u0003\u001f\u000bI(\u0004\u0002\u0002\f*\u0019\u0011Q\u0012 \u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0012\u0006-%\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$2![AL\u0011%\t\t\tHA\u0001\u0002\u0004\tI(\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA/\u0003;C\u0011\"!!\u001e\u0003\u0003\u0005\r!a\u001c\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u001c\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u0018\u0002\r\u0015\fX/\u00197t)\rI\u00171\u0016\u0005\n\u0003\u0003\u0003\u0013\u0011!a\u0001\u0003s\n!#\u00168sKN|GN^3e\rVt7\r^5p]B\u00111II\n\u0006E\u0005M\u0016q\u0018\t\f\u0003k\u000bY\fW2jS&\u001ch0\u0004\u0002\u00028*\u0019\u0011\u0011\u0018 \u0002\u000fI,h\u000e^5nK&!\u0011QXA\\\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\u000e\t\u0005\u0003\u0003\f9-\u0004\u0002\u0002D*!\u0011QYA3\u0003\tIw.C\u0002U\u0003\u0007$\"!a,\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u001by\fy-!5\u0002T\u0006U\u0017q[Am\u0011\u00151V\u00051\u0001Y\u0011\u0015\tW\u00051\u0001d\u0011\u001d9W\u0005%AA\u0002%Dq!\\\u0013\u0011\u0002\u0003\u0007\u0011\u000eC\u0004pKA\u0005\t\u0019A5\t\u000fE,\u0003\u0013!a\u0001g\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$3'A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00135\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012*\u0014aD1qa2LH\u0005Z3gCVdG\u000f\n\u001c\u0002\u000fUt\u0017\r\u001d9msR!\u0011q]Az!\u0015i\u0014\u0011^Aw\u0013\r\tYO\u0010\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0013u\ny\u000fW2jS&\u001c\u0018bAAy}\t1A+\u001e9mKZB\u0001\"!>+\u0003\u0003\u0005\rA`\u0001\u0004q\u0012\u0002\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005N\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001b\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00137\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011\u0019\u0001\u0005\u0003\u0002`\t\u0015\u0011\u0002\u0002B\u0004\u0003C\u0012aa\u00142kK\u000e$\b"
)
public class UnresolvedFunction implements ColumnNode, Product, Serializable {
   private final String functionName;
   private final Seq arguments;
   private final boolean isDistinct;
   private final boolean isUserDefinedFunction;
   private final boolean isInternal;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$6() {
      return UnresolvedFunction$.MODULE$.$lessinit$greater$default$6();
   }

   public static boolean $lessinit$greater$default$5() {
      return UnresolvedFunction$.MODULE$.$lessinit$greater$default$5();
   }

   public static boolean $lessinit$greater$default$4() {
      return UnresolvedFunction$.MODULE$.$lessinit$greater$default$4();
   }

   public static boolean $lessinit$greater$default$3() {
      return UnresolvedFunction$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final UnresolvedFunction x$0) {
      return UnresolvedFunction$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$6() {
      return UnresolvedFunction$.MODULE$.apply$default$6();
   }

   public static boolean apply$default$5() {
      return UnresolvedFunction$.MODULE$.apply$default$5();
   }

   public static boolean apply$default$4() {
      return UnresolvedFunction$.MODULE$.apply$default$4();
   }

   public static boolean apply$default$3() {
      return UnresolvedFunction$.MODULE$.apply$default$3();
   }

   public static UnresolvedFunction apply(final String functionName, final Seq arguments, final boolean isDistinct, final boolean isUserDefinedFunction, final boolean isInternal, final Origin origin) {
      return UnresolvedFunction$.MODULE$.apply(functionName, arguments, isDistinct, isUserDefinedFunction, isInternal, origin);
   }

   public static Function1 tupled() {
      return UnresolvedFunction$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return UnresolvedFunction$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public void foreach(final Function1 f) {
      ColumnNodeLike.foreach$(this, f);
   }

   public Seq collect(final PartialFunction pf) {
      return ColumnNodeLike.collect$(this, pf);
   }

   private ColumnNode normalized$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.normalized = ColumnNode.normalized$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.normalized;
   }

   public ColumnNode normalized() {
      return !this.bitmap$0 ? this.normalized$lzycompute() : this.normalized;
   }

   public String functionName() {
      return this.functionName;
   }

   public Seq arguments() {
      return this.arguments;
   }

   public boolean isDistinct() {
      return this.isDistinct;
   }

   public boolean isUserDefinedFunction() {
      return this.isUserDefinedFunction;
   }

   public boolean isInternal() {
      return this.isInternal;
   }

   public Origin origin() {
      return this.origin;
   }

   public UnresolvedFunction normalize() {
      Seq x$1 = ColumnNode$.MODULE$.normalize(this.arguments());
      Origin x$2 = ColumnNode$.MODULE$.NO_ORIGIN();
      String x$3 = this.copy$default$1();
      boolean x$4 = this.copy$default$3();
      boolean x$5 = this.copy$default$4();
      boolean x$6 = this.copy$default$5();
      return this.copy(x$3, x$1, x$4, x$5, x$6, x$2);
   }

   public String sql() {
      String var10000 = this.functionName();
      return var10000 + ColumnNode$.MODULE$.argumentsToSql(this.arguments());
   }

   public Seq children() {
      return this.arguments();
   }

   public UnresolvedFunction copy(final String functionName, final Seq arguments, final boolean isDistinct, final boolean isUserDefinedFunction, final boolean isInternal, final Origin origin) {
      return new UnresolvedFunction(functionName, arguments, isDistinct, isUserDefinedFunction, isInternal, origin);
   }

   public String copy$default$1() {
      return this.functionName();
   }

   public Seq copy$default$2() {
      return this.arguments();
   }

   public boolean copy$default$3() {
      return this.isDistinct();
   }

   public boolean copy$default$4() {
      return this.isUserDefinedFunction();
   }

   public boolean copy$default$5() {
      return this.isInternal();
   }

   public Origin copy$default$6() {
      return this.origin();
   }

   public String productPrefix() {
      return "UnresolvedFunction";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.functionName();
         }
         case 1 -> {
            return this.arguments();
         }
         case 2 -> {
            return BoxesRunTime.boxToBoolean(this.isDistinct());
         }
         case 3 -> {
            return BoxesRunTime.boxToBoolean(this.isUserDefinedFunction());
         }
         case 4 -> {
            return BoxesRunTime.boxToBoolean(this.isInternal());
         }
         case 5 -> {
            return this.origin();
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
      return x$1 instanceof UnresolvedFunction;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "functionName";
         }
         case 1 -> {
            return "arguments";
         }
         case 2 -> {
            return "isDistinct";
         }
         case 3 -> {
            return "isUserDefinedFunction";
         }
         case 4 -> {
            return "isInternal";
         }
         case 5 -> {
            return "origin";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.functionName()));
      var1 = Statics.mix(var1, Statics.anyHash(this.arguments()));
      var1 = Statics.mix(var1, this.isDistinct() ? 1231 : 1237);
      var1 = Statics.mix(var1, this.isUserDefinedFunction() ? 1231 : 1237);
      var1 = Statics.mix(var1, this.isInternal() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.origin()));
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label75: {
            if (x$1 instanceof UnresolvedFunction) {
               UnresolvedFunction var4 = (UnresolvedFunction)x$1;
               if (this.isDistinct() == var4.isDistinct() && this.isUserDefinedFunction() == var4.isUserDefinedFunction() && this.isInternal() == var4.isInternal()) {
                  label68: {
                     String var10000 = this.functionName();
                     String var5 = var4.functionName();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label68;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label68;
                     }

                     Seq var8 = this.arguments();
                     Seq var6 = var4.arguments();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label68;
                        }
                     } else if (!var8.equals(var6)) {
                        break label68;
                     }

                     Origin var9 = this.origin();
                     Origin var7 = var4.origin();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label68;
                        }
                     } else if (!var9.equals(var7)) {
                        break label68;
                     }

                     if (var4.canEqual(this)) {
                        break label75;
                     }
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

   public UnresolvedFunction(final String functionName, final Seq arguments, final boolean isDistinct, final boolean isUserDefinedFunction, final boolean isInternal, final Origin origin) {
      this.functionName = functionName;
      this.arguments = arguments;
      this.isDistinct = isDistinct;
      this.isUserDefinedFunction = isUserDefinedFunction;
      this.isInternal = isInternal;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }
}
