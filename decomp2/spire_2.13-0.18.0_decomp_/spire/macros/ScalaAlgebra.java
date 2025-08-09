package spire.macros;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Mirror;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tEc\u0001B\u0011#\u0001\u001eB\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t)\u0002\u0011\t\u0012)A\u0005\u0003\")Q\u000b\u0001C\u0001-\")\u0011\f\u0001C\u00015\")Q\u000e\u0001C\u0001]\")\u0001\u0010\u0001C\u0001s\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\u000b\u0001\u0011\u0005\u0011q\u0003\u0005\b\u0003O\u0001A\u0011AA\u0015\u0011\u001d\ty\u0004\u0001C\u0001\u0003\u0003Bq!!\u0015\u0001\t\u0003\t\u0019\u0006C\u0004\u0002d\u0001!\t!!\u001a\t\u000f\u0005\u0005\u0005\u0001\"\u0001\u0002\u0004\"9\u0011Q\u0012\u0001\u0005\u0002\u0005=\u0005\"CAM\u0001\u0005\u0005I\u0011AAN\u0011%\t9\u000bAI\u0001\n\u0003\tI\u000bC\u0005\u0002D\u0002\t\t\u0011\"\u0011\u0002F\"I\u0011q\u001b\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u001c\u0005\n\u00037\u0004\u0011\u0011!C\u0001\u0003;D\u0011\"a9\u0001\u0003\u0003%\t%!:\t\u0013\u0005M\b!!A\u0005\u0002\u0005U\b\"CA}\u0001\u0005\u0005I\u0011IA~\u0011%\ty\u0010AA\u0001\n\u0003\u0012\t\u0001C\u0005\u0003\u0004\u0001\t\t\u0011\"\u0011\u0003\u0006!I\u0011\u0011\u0011\u0001\u0002\u0002\u0013\u0005#qA\u0004\n\u0005\u0017\u0011\u0013\u0011!E\u0001\u0005\u001b1\u0001\"\t\u0012\u0002\u0002#\u0005!q\u0002\u0005\u0007+n!\tA!\t\t\u0013\t\r1$!A\u0005F\t\u0015\u0001\"\u0003B\u00127\u0005\u0005I\u0011\u0011B\u0013\u0011%\u0011\tdGA\u0001\n\u0003\u0013\u0019\u0004C\u0005\u0003Hm\t\t\u0011\"\u0003\u0003J\ta1kY1mC\u0006cw-\u001a2sC*\u00111\u0005J\u0001\u0007[\u0006\u001c'o\\:\u000b\u0003\u0015\nQa\u001d9je\u0016\u001c\u0001!\u0006\u0002)\u0007N!\u0001!K\u00174!\tQ3&D\u0001#\u0013\ta#EA\u0006BkR|\u0017\t\\4fEJ\f\u0007C\u0001\u00182\u001b\u0005y#\"\u0001\u0019\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iz#a\u0002)s_\u0012,8\r\u001e\t\u0003iqr!!\u000e\u001e\u000f\u0005YJT\"A\u001c\u000b\u0005a2\u0013A\u0002\u001fs_>$h(C\u00011\u0013\tYt&A\u0004qC\u000e\\\u0017mZ3\n\u0005ur$\u0001D*fe&\fG.\u001b>bE2,'BA\u001e0\u0003\u0005\u0019W#A!\u0011\u0005\t\u001bE\u0002\u0001\u0003\u0006\t\u0002\u0011\r!\u0012\u0002\u0002\u0007F\u0011a)\u0013\t\u0003]\u001dK!\u0001S\u0018\u0003\u000f9{G\u000f[5oOB\u0011!*\u0015\b\u0003\u0017>s!\u0001\u0014(\u000f\u0005Yj\u0015\"A\u0013\n\u0005\r\"\u0013B\u0001)#\u0003\u0019\u0019w.\u001c9bi&\u0011!k\u0015\u0002\b\u0007>tG/\u001a=u\u0015\t\u0001&%\u0001\u0002dA\u00051A(\u001b8jiz\"\"a\u0016-\u0011\u0007)\u0002\u0011\tC\u0003@\u0007\u0001\u0007\u0011)\u0001\u0005qYV\u001c\b\u000f\\;t+\tYv-F\u0001]!\rivL\u001a\b\u0003=\u0006i\u0011\u0001A\u0005\u0003A\u0006\u0014A!\u0012=qe&\u0011!m\u0019\u0002\b\u00032L\u0017m]3t\u0015\t\u0019CM\u0003\u0002f_\u00059!/\u001a4mK\u000e$\bC\u0001\"h\t\u0015AGA1\u0001j\u0005\u0005\t\u0015C\u0001$k!\tq3.\u0003\u0002m_\t\u0019\u0011I\\=\u0002\tAdWo]\u000b\u0003_J$\"\u0001]:\u0011\u0007u{\u0016\u000f\u0005\u0002Ce\u0012)\u0001.\u0002b\u0001S\"9A/BA\u0001\u0002\b)\u0018aC3wS\u0012,gnY3%eQ\u00022!\u0018<r\u0013\t9\u0018MA\u0006XK\u0006\\G+\u001f9f)\u0006<\u0017!B7j]V\u001cXC\u0001>~)\tYh\u0010E\u0002^?r\u0004\"AQ?\u0005\u000b!4!\u0019A5\t\u0011}4\u0011\u0011!a\u0002\u0003\u0003\t1\"\u001a<jI\u0016t7-\u001a\u00133kA\u0019QL\u001e?\u0002\u000bQLW.Z:\u0016\t\u0005\u001d\u0011Q\u0002\u000b\u0005\u0003\u0013\ty\u0001\u0005\u0003^?\u0006-\u0001c\u0001\"\u0002\u000e\u0011)\u0001n\u0002b\u0001S\"I\u0011\u0011C\u0004\u0002\u0002\u0003\u000f\u00111C\u0001\fKZLG-\u001a8dK\u0012\u0012d\u0007\u0005\u0003^m\u0006-\u0011A\u00028fO\u0006$X-\u0006\u0003\u0002\u001a\u0005}A\u0003BA\u000e\u0003C\u0001B!X0\u0002\u001eA\u0019!)a\b\u0005\u000b!D!\u0019A5\t\u0013\u0005\r\u0002\"!AA\u0004\u0005\u0015\u0012aC3wS\u0012,gnY3%e]\u0002B!\u0018<\u0002\u001e\u0005\tR-^2mS\u0012,\u0017M\u001c$v]\u000e$\u0018n\u001c8\u0016\t\u0005-\u0012Q\b\u000b\u0005\u0003[\t)\u0004\u0005\u0003^?\u0006=\u0002c\u0001\u001b\u00022%\u0019\u00111\u0007 \u0003\r\tKw-\u00138u\u0011%\t9$CA\u0001\u0002\b\tI$A\u0006fm&$WM\\2fIIB\u0004\u0003B/w\u0003w\u00012AQA\u001f\t\u0015A\u0017B1\u0001j\u0003\u0011\tXo\u001c;\u0016\t\u0005\r\u0013\u0011\n\u000b\u0005\u0003\u000b\nY\u0005\u0005\u0003^?\u0006\u001d\u0003c\u0001\"\u0002J\u0011)\u0001N\u0003b\u0001S\"I\u0011Q\n\u0006\u0002\u0002\u0003\u000f\u0011qJ\u0001\fKZLG-\u001a8dK\u0012\u0012\u0014\b\u0005\u0003^m\u0006\u001d\u0013a\u00013jmV!\u0011QKA.)\u0011\t9&!\u0018\u0011\tu{\u0016\u0011\f\t\u0004\u0005\u0006mC!\u00025\f\u0005\u0004I\u0007\"CA0\u0017\u0005\u0005\t9AA1\u0003-)g/\u001b3f]\u000e,Ge\r\u0019\u0011\tu3\u0018\u0011L\u0001\u0004[>$W\u0003BA4\u0003_\"B!!\u001b\u0002xQ!\u00111NA9!\u0011iv,!\u001c\u0011\u0007\t\u000by\u0007B\u0003i\u0019\t\u0007\u0011\u000eC\u0005\u0002t1\t\t\u0011q\u0001\u0002v\u0005YQM^5eK:\u001cW\rJ\u001a2!\u0011if/!\u001c\t\u0013\u0005eD\u0002%CA\u0002\u0005m\u0014\u0001B:uk\n\u0004RALA?\u0003WJ1!a 0\u0005!a$-\u001f8b[\u0016t\u0014AB3rk\u0006d7/\u0006\u0002\u0002\u0006B!QlXAD!\rq\u0013\u0011R\u0005\u0004\u0003\u0017{#a\u0002\"p_2,\u0017M\\\u0001\bG>l\u0007/\u0019:f+\t\t\t\n\u0005\u0003^?\u0006M\u0005c\u0001\u0018\u0002\u0016&\u0019\u0011qS\u0018\u0003\u0007%sG/\u0001\u0003d_BLX\u0003BAO\u0003G#B!a(\u0002&B!!\u0006AAQ!\r\u0011\u00151\u0015\u0003\u0006\t>\u0011\r!\u0012\u0005\t\u007f=\u0001\n\u00111\u0001\u0002\"\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT\u0003BAV\u0003\u0003,\"!!,+\u0007\u0005\u000byk\u000b\u0002\u00022B!\u00111WA_\u001b\t\t)L\u0003\u0003\u00028\u0006e\u0016!C;oG\",7m[3e\u0015\r\tYlL\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA`\u0003k\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0015!\u0005C1\u0001F\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011q\u0019\t\u0005\u0003\u0013\f\u0019.\u0004\u0002\u0002L*!\u0011QZAh\u0003\u0011a\u0017M\\4\u000b\u0005\u0005E\u0017\u0001\u00026bm\u0006LA!!6\u0002L\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a%\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019!.a8\t\u0013\u0005\u00058#!AA\u0002\u0005M\u0015a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002hB)\u0011\u0011^AxU6\u0011\u00111\u001e\u0006\u0004\u0003[|\u0013AC2pY2,7\r^5p]&!\u0011\u0011_Av\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\u001d\u0015q\u001f\u0005\t\u0003C,\u0012\u0011!a\u0001U\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t9-!@\t\u0013\u0005\u0005h#!AA\u0002\u0005M\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005M\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005\u001dG\u0003BAD\u0005\u0013A\u0001\"!9\u001a\u0003\u0003\u0005\rA[\u0001\r'\u000e\fG.Y!mO\u0016\u0014'/\u0019\t\u0003Um\u0019Ra\u0007B\t\u0005/\u00012A\fB\n\u0013\r\u0011)b\f\u0002\u0007\u0003:L(+\u001a4\u0011\t\te!qD\u0007\u0003\u00057QAA!\b\u0002P\u0006\u0011\u0011n\\\u0005\u0004{\tmAC\u0001B\u0007\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\u00119C!\f\u0015\t\t%\"q\u0006\t\u0005U\u0001\u0011Y\u0003E\u0002C\u0005[!Q\u0001\u0012\u0010C\u0002\u0015Caa\u0010\u0010A\u0002\t-\u0012aB;oCB\u0004H._\u000b\u0005\u0005k\u0011y\u0004\u0006\u0003\u00038\t\u0005\u0003#\u0002\u0018\u0003:\tu\u0012b\u0001B\u001e_\t1q\n\u001d;j_:\u00042A\u0011B \t\u0015!uD1\u0001F\u0011%\u0011\u0019eHA\u0001\u0002\u0004\u0011)%A\u0002yIA\u0002BA\u000b\u0001\u0003>\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!1\n\t\u0005\u0003\u0013\u0014i%\u0003\u0003\u0003P\u0005-'AB(cU\u0016\u001cG\u000f"
)
public class ScalaAlgebra extends AutoAlgebra implements Product, Serializable {
   private final Context c;

   public static Option unapply(final ScalaAlgebra x$0) {
      return ScalaAlgebra$.MODULE$.unapply(x$0);
   }

   public static ScalaAlgebra apply(final Context c) {
      return ScalaAlgebra$.MODULE$.apply(c);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Context c() {
      return this.c;
   }

   public Exprs.Expr plusplus() {
      return this.binop("$plus$plus", this.binop$default$2(), this.binop$default$3());
   }

   public Exprs.Expr plus(final TypeTags.WeakTypeTag evidence$24) {
      return this.binop("$plus", this.binop$default$2(), this.binop$default$3());
   }

   public Exprs.Expr minus(final TypeTags.WeakTypeTag evidence$25) {
      return this.binop("$minus", this.binop$default$2(), this.binop$default$3());
   }

   public Exprs.Expr times(final TypeTags.WeakTypeTag evidence$26) {
      return this.binop("$times", this.binop$default$2(), this.binop$default$3());
   }

   public Exprs.Expr negate(final TypeTags.WeakTypeTag evidence$27) {
      return this.unop("unary_$minus", this.unop$default$2());
   }

   public Exprs.Expr euclideanFunction(final TypeTags.WeakTypeTag evidence$28) {
      Context var10000 = this.c();
      Trees.SelectApi var10001 = this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("x"), false), this.c().universe().TermName().apply("toBigInt")), this.c().universe().TermName().apply("abs"));
      Universe $u = this.c().universe();
      Mirror $m = this.c().universe().rootMirror();

      final class $typecreator1$3 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "BigInt"), .MODULE$);
         }

         public $typecreator1$3() {
         }
      }

      return var10000.Expr(var10001, $u.TypeTag().apply($m, new $typecreator1$3()));
   }

   public Exprs.Expr quot(final TypeTags.WeakTypeTag evidence$29) {
      return (Exprs.Expr)this.binopSearch(scala.package..MODULE$.Nil().$colon$colon("$div").$colon$colon("quot"), this.binopSearch$default$2(), this.binopSearch$default$3(), evidence$29).getOrElse(() -> this.failedSearch("quot", "/~"));
   }

   public Exprs.Expr div(final TypeTags.WeakTypeTag evidence$30) {
      return this.binop("$div", this.binop$default$2(), this.binop$default$3());
   }

   public Exprs.Expr mod(final Function0 stub, final TypeTags.WeakTypeTag evidence$31) {
      return this.binop("$percent", this.binop$default$2(), this.binop$default$3());
   }

   public Exprs.Expr equals() {
      return this.binop("$eq$eq", this.binop$default$2(), this.binop$default$3());
   }

   public Exprs.Expr compare() {
      return this.binop("compare", this.binop$default$2(), this.binop$default$3());
   }

   public ScalaAlgebra copy(final Context c) {
      return new ScalaAlgebra(c);
   }

   public Context copy$default$1() {
      return this.c();
   }

   public String productPrefix() {
      return "ScalaAlgebra";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.c();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ScalaAlgebra;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "c";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof ScalaAlgebra) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     ScalaAlgebra var4 = (ScalaAlgebra)x$1;
                     Context var10000 = this.c();
                     Context var5 = var4.c();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public ScalaAlgebra(final Context c) {
      this.c = c;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
