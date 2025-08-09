package spire.macros;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Mirror;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rc\u0001\u0002\u0011\"\u0001\u001aB\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t'\u0002\u0011\t\u0012)A\u0005\u0001\")A\u000b\u0001C\u0001+\")\u0001\f\u0001C\u00013\")\u0011\u000f\u0001C\u0001e\")!\u0010\u0001C\u0001w\"9\u0011q\u0001\u0001\u0005\u0002\u0005%\u0001bBA\r\u0001\u0011\u0005\u00111\u0004\u0005\b\u0003W\u0001A\u0011AA\u0017\u0011\u001d\t\u0019\u0005\u0001C\u0001\u0003\u000bBq!!\u0016\u0001\t\u0003\t9\u0006C\u0004\u0002t\u0001!\t!!\u001e\t\u000f\u0005}\u0004\u0001\"\u0001\u0002\u0002\"I\u00111\u0012\u0001\u0002\u0002\u0013\u0005\u0011Q\u0012\u0005\n\u00033\u0003\u0011\u0013!C\u0001\u00037C\u0011\"!.\u0001\u0003\u0003%\t%a.\t\u0013\u0005%\u0007!!A\u0005\u0002\u0005-\u0007\"CAg\u0001\u0005\u0005I\u0011AAh\u0011%\t)\u000eAA\u0001\n\u0003\n9\u000eC\u0005\u0002f\u0002\t\t\u0011\"\u0001\u0002h\"I\u00111\u001e\u0001\u0002\u0002\u0013\u0005\u0013Q\u001e\u0005\n\u0003c\u0004\u0011\u0011!C!\u0003gD\u0011\"!>\u0001\u0003\u0003%\t%a>\t\u0013\u0005M\u0004!!A\u0005B\u0005ex!CA\u007fC\u0005\u0005\t\u0012AA\u0000\r!\u0001\u0013%!A\t\u0002\t\u0005\u0001B\u0002+\u001b\t\u0003\u0011\u0019\u0002C\u0005\u0002vj\t\t\u0011\"\u0012\u0002x\"I!Q\u0003\u000e\u0002\u0002\u0013\u0005%q\u0003\u0005\n\u0005GQ\u0012\u0011!CA\u0005KA\u0011B!\u000f\u001b\u0003\u0003%IAa\u000f\u0003\u0017)\u000bg/Y!mO\u0016\u0014'/\u0019\u0006\u0003E\r\na!\\1de>\u001c(\"\u0001\u0013\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011qEQ\n\u0005\u0001!b#\u0007\u0005\u0002*U5\t\u0011%\u0003\u0002,C\tY\u0011)\u001e;p\u00032<WM\u0019:b!\ti\u0003'D\u0001/\u0015\u0005y\u0013!B:dC2\f\u0017BA\u0019/\u0005\u001d\u0001&o\u001c3vGR\u0004\"aM\u001e\u000f\u0005QJdBA\u001b9\u001b\u00051$BA\u001c&\u0003\u0019a$o\\8u}%\tq&\u0003\u0002;]\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001f>\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tQd&A\u0001d+\u0005\u0001\u0005CA!C\u0019\u0001!Qa\u0011\u0001C\u0002\u0011\u0013\u0011aQ\t\u0003\u000b\"\u0003\"!\f$\n\u0005\u001ds#a\u0002(pi\"Lgn\u001a\t\u0003\u0013Bs!A\u0013(\u000f\u0005-keBA\u001bM\u0013\u0005!\u0013B\u0001\u0012$\u0013\ty\u0015%\u0001\u0004d_6\u0004\u0018\r^\u0005\u0003#J\u0013qaQ8oi\u0016DHO\u0003\u0002PC\u0005\u00111\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005Y;\u0006cA\u0015\u0001\u0001\")ah\u0001a\u0001\u0001\u0006!\u0001\u000f\\;t+\tQf\r\u0006\u0002\\YB\u0019ALX3\u000f\u0005u\u000bQ\"\u0001\u0001\n\u0005}\u0003'\u0001B#yaJL!!\u00192\u0003\u000f\u0005c\u0017.Y:fg*\u0011!e\u0019\u0006\u0003I:\nqA]3gY\u0016\u001cG\u000f\u0005\u0002BM\u0012)q\r\u0002b\u0001Q\n\t\u0011)\u0005\u0002FSB\u0011QF[\u0005\u0003W:\u00121!\u00118z\u0011\u001diG!!AA\u00049\f1\"\u001a<jI\u0016t7-\u001a\u00134eA\u0019Al\\3\n\u0005A\u0004'aC,fC.$\u0016\u0010]3UC\u001e\fQ!\\5okN,\"a\u001d<\u0015\u0005Q<\bc\u0001/_kB\u0011\u0011I\u001e\u0003\u0006O\u0016\u0011\r\u0001\u001b\u0005\bq\u0016\t\t\u0011q\u0001z\u0003-)g/\u001b3f]\u000e,GeM\u001a\u0011\u0007q{W/A\u0003uS6,7/\u0006\u0002}\u007fR\u0019Q0!\u0001\u0011\u0007qsf\u0010\u0005\u0002B\u007f\u0012)qM\u0002b\u0001Q\"I\u00111\u0001\u0004\u0002\u0002\u0003\u000f\u0011QA\u0001\fKZLG-\u001a8dK\u0012\u001aD\u0007E\u0002]_z\f1\u0001Z5w+\u0011\tY!!\u0005\u0015\t\u00055\u00111\u0003\t\u00059z\u000by\u0001E\u0002B\u0003#!QaZ\u0004C\u0002!D\u0011\"!\u0006\b\u0003\u0003\u0005\u001d!a\u0006\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$3'\u000e\t\u00059>\fy!\u0001\u0004oK\u001e\fG/Z\u000b\u0005\u0003;\t\u0019\u0003\u0006\u0003\u0002 \u0005\u0015\u0002\u0003\u0002/_\u0003C\u00012!QA\u0012\t\u00159\u0007B1\u0001i\u0011%\t9\u0003CA\u0001\u0002\b\tI#A\u0006fm&$WM\\2fIM2\u0004\u0003\u0002/p\u0003C\t\u0011#Z;dY&$W-\u00198Gk:\u001cG/[8o+\u0011\ty#!\u0011\u0015\t\u0005E\u0012\u0011\b\t\u00059z\u000b\u0019\u0004E\u00024\u0003kI1!a\u000e>\u0005\u0019\u0011\u0015nZ%oi\"I\u00111H\u0005\u0002\u0002\u0003\u000f\u0011QH\u0001\fKZLG-\u001a8dK\u0012\u001at\u0007\u0005\u0003]_\u0006}\u0002cA!\u0002B\u0011)q-\u0003b\u0001Q\u0006!\u0011/^8u+\u0011\t9%!\u0014\u0015\t\u0005%\u0013q\n\t\u00059z\u000bY\u0005E\u0002B\u0003\u001b\"Qa\u001a\u0006C\u0002!D\u0011\"!\u0015\u000b\u0003\u0003\u0005\u001d!a\u0015\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$3\u0007\u000f\t\u00059>\fY%A\u0002n_\u0012,B!!\u0017\u0002bQ!\u00111LA5)\u0011\ti&a\u0019\u0011\tqs\u0016q\f\t\u0004\u0003\u0006\u0005D!B4\f\u0005\u0004A\u0007\"CA3\u0017\u0005\u0005\t9AA4\u0003-)g/\u001b3f]\u000e,GeM\u001d\u0011\tq{\u0017q\f\u0005\n\u0003WZ\u0001\u0013\"a\u0001\u0003[\nAa\u001d;vEB)Q&a\u001c\u0002^%\u0019\u0011\u0011\u000f\u0018\u0003\u0011q\u0012\u0017P\\1nKz\na!Z9vC2\u001cXCAA<!\u0011af,!\u001f\u0011\u00075\nY(C\u0002\u0002~9\u0012qAQ8pY\u0016\fg.A\u0004d_6\u0004\u0018M]3\u0016\u0005\u0005\r\u0005\u0003\u0002/_\u0003\u000b\u00032!LAD\u0013\r\tII\f\u0002\u0004\u0013:$\u0018\u0001B2paf,B!a$\u0002\u0016R!\u0011\u0011SAL!\u0011I\u0003!a%\u0011\u0007\u0005\u000b)\nB\u0003D\u001d\t\u0007A\t\u0003\u0005?\u001dA\u0005\t\u0019AAJ\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*B!!(\u00024V\u0011\u0011q\u0014\u0016\u0004\u0001\u0006\u00056FAAR!\u0011\t)+a,\u000e\u0005\u0005\u001d&\u0002BAU\u0003W\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u00055f&\u0001\u0006b]:|G/\u0019;j_:LA!!-\u0002(\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b\r{!\u0019\u0001#\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tI\f\u0005\u0003\u0002<\u0006\u0015WBAA_\u0015\u0011\ty,!1\u0002\t1\fgn\u001a\u0006\u0003\u0003\u0007\fAA[1wC&!\u0011qYA_\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011QQ\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\rI\u0017\u0011\u001b\u0005\n\u0003'\u0014\u0012\u0011!a\u0001\u0003\u000b\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAm!\u0015\tY.!9j\u001b\t\tiNC\u0002\u0002`:\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\u0019/!8\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003s\nI\u000f\u0003\u0005\u0002TR\t\t\u00111\u0001j\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005e\u0016q\u001e\u0005\n\u0003',\u0012\u0011!a\u0001\u0003\u000b\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u000b\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003s#B!!\u001f\u0002|\"A\u00111\u001b\r\u0002\u0002\u0003\u0007\u0011.A\u0006KCZ\f\u0017\t\\4fEJ\f\u0007CA\u0015\u001b'\u0015Q\"1\u0001B\u0005!\ri#QA\u0005\u0004\u0005\u000fq#AB!osJ+g\r\u0005\u0003\u0003\f\tEQB\u0001B\u0007\u0015\u0011\u0011y!!1\u0002\u0005%|\u0017b\u0001\u001f\u0003\u000eQ\u0011\u0011q`\u0001\u0006CB\u0004H._\u000b\u0005\u00053\u0011y\u0002\u0006\u0003\u0003\u001c\t\u0005\u0002\u0003B\u0015\u0001\u0005;\u00012!\u0011B\u0010\t\u0015\u0019UD1\u0001E\u0011\u0019qT\u00041\u0001\u0003\u001e\u00059QO\\1qa2LX\u0003\u0002B\u0014\u0005c!BA!\u000b\u00034A)QFa\u000b\u00030%\u0019!Q\u0006\u0018\u0003\r=\u0003H/[8o!\r\t%\u0011\u0007\u0003\u0006\u0007z\u0011\r\u0001\u0012\u0005\n\u0005kq\u0012\u0011!a\u0001\u0005o\t1\u0001\u001f\u00131!\u0011I\u0003Aa\f\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tu\u0002\u0003BA^\u0005\u007fIAA!\u0011\u0002>\n1qJ\u00196fGR\u0004"
)
public class JavaAlgebra extends AutoAlgebra implements Product, Serializable {
   private final Context c;

   public static Option unapply(final JavaAlgebra x$0) {
      return JavaAlgebra$.MODULE$.unapply(x$0);
   }

   public static JavaAlgebra apply(final Context c) {
      return JavaAlgebra$.MODULE$.apply(c);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Context c() {
      return this.c;
   }

   public Exprs.Expr plus(final TypeTags.WeakTypeTag evidence$32) {
      return (Exprs.Expr)this.binopSearch(.MODULE$.Nil().$colon$colon("plus").$colon$colon("add"), this.binopSearch$default$2(), this.binopSearch$default$3(), evidence$32).getOrElse(() -> this.failedSearch("plus", "+"));
   }

   public Exprs.Expr minus(final TypeTags.WeakTypeTag evidence$33) {
      return (Exprs.Expr)this.binopSearch(.MODULE$.Nil().$colon$colon("minus").$colon$colon("subtract"), this.binopSearch$default$2(), this.binopSearch$default$3(), evidence$33).getOrElse(() -> this.failedSearch("minus", "-"));
   }

   public Exprs.Expr times(final TypeTags.WeakTypeTag evidence$34) {
      return (Exprs.Expr)this.binopSearch(.MODULE$.Nil().$colon$colon("times").$colon$colon("multiply"), this.binopSearch$default$2(), this.binopSearch$default$3(), evidence$34).getOrElse(() -> this.failedSearch("times", "*"));
   }

   public Exprs.Expr div(final TypeTags.WeakTypeTag evidence$35) {
      return (Exprs.Expr)this.binopSearch(.MODULE$.Nil().$colon$colon("div").$colon$colon("divide"), this.binopSearch$default$2(), this.binopSearch$default$3(), evidence$35).getOrElse(() -> this.failedSearch("div", "/"));
   }

   public Exprs.Expr negate(final TypeTags.WeakTypeTag evidence$36) {
      return (Exprs.Expr)this.unopSearch(.MODULE$.Nil().$colon$colon("negative").$colon$colon("negate"), this.unopSearch$default$2(), evidence$36).getOrElse(() -> this.c().Expr(this.c().universe().Apply().apply(this.c().universe().Select().apply(this.c().universe().Ident().apply((Names.NameApi)spire.macros.compat..MODULE$.termName(this.c(), "zero")), (Names.NameApi)spire.macros.compat..MODULE$.termName(this.c(), "minus")), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{this.c().universe().Ident().apply((Names.NameApi)spire.macros.compat..MODULE$.termName(this.c(), "x"))})))), evidence$36));
   }

   public Exprs.Expr euclideanFunction(final TypeTags.WeakTypeTag evidence$37) {
      Context var10000 = this.c();
      Trees.SelectApi var10001 = this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("_root_"), false), this.c().universe().TermName().apply("scala")), this.c().universe().TermName().apply("BigInt")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("x"), false), this.c().universe().TermName().apply("toBigInteger")), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().TermName().apply("abs"));
      Universe $u = this.c().universe();
      Mirror $m = this.c().universe().rootMirror();

      final class $typecreator1$4 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "BigInt"), scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$4() {
         }
      }

      return var10000.Expr(var10001, $u.TypeTag().apply($m, new $typecreator1$4()));
   }

   public Exprs.Expr quot(final TypeTags.WeakTypeTag evidence$38) {
      return (Exprs.Expr)this.binopSearch(.MODULE$.Nil().$colon$colon("div").$colon$colon("divide").$colon$colon("quot"), this.binopSearch$default$2(), this.binopSearch$default$3(), evidence$38).getOrElse(() -> this.failedSearch("quot", "/~"));
   }

   public Exprs.Expr mod(final Function0 stub, final TypeTags.WeakTypeTag evidence$39) {
      return (Exprs.Expr)this.binopSearch(.MODULE$.Nil().$colon$colon("remainder").$colon$colon("mod"), this.binopSearch$default$2(), this.binopSearch$default$3(), evidence$39).getOrElse(stub);
   }

   public Exprs.Expr equals() {
      return this.binop("equals", this.binop$default$2(), this.binop$default$3());
   }

   public Exprs.Expr compare() {
      return this.binop("compareTo", this.binop$default$2(), this.binop$default$3());
   }

   public JavaAlgebra copy(final Context c) {
      return new JavaAlgebra(c);
   }

   public Context copy$default$1() {
      return this.c();
   }

   public String productPrefix() {
      return "JavaAlgebra";
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
      return x$1 instanceof JavaAlgebra;
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
            if (x$1 instanceof JavaAlgebra) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     JavaAlgebra var4 = (JavaAlgebra)x$1;
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

   public JavaAlgebra(final Context c) {
      this.c = c;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
