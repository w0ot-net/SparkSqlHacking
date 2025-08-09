package spire.macros;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function3;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tMf!\u0002\u001b6\u0001VJ\u0004\u0002C)\u0001\u0005+\u0007I\u0011\u0001*\t\u0011\u0019\u0004!\u0011#Q\u0001\nMCQa\u001a\u0001\u0005\u0002!DQ\u0001\u001c\u0001\u0005\u00025Dq!!\u000b\u0001\t\u0003\tY\u0003C\u0004\u0002@\u0001!\t!!\u0011\t\u000f\u0005-\u0003\u0001\"\u0001\u0002N\u00191\u0011\u0011\r\u0001\u0001\u0003GB!\"a\u001a\t\u0005\u000b\u0007I\u0011AA5\u0011%\tY\u0007\u0003B\u0001B\u0003%\u0001\u000f\u0003\u0006\u0002(!\u0011)\u0019!C\u0001\u0003[B!\"a\u001c\t\u0005\u0003\u0005\u000b\u0011BA,\u0011)\t\t\b\u0003B\u0001B\u0003-\u00111\u000f\u0005\u0007O\"!\t!!\u001f\t\u0013\u0005\u0015\u0005B1A\u0005\u0002\u0005\u001d\u0005\u0002CAJ\u0011\u0001\u0006I!!#\t\u0013\u0005U\u0005B1A\u0005\u0002\u00055\u0004\u0002CAL\u0011\u0001\u0006I!a\u0016\t\u0013\u0005e\u0005B1A\u0005\u0002\u00055\u0004\u0002CAN\u0011\u0001\u0006I!a\u0016\t\u0013\u0005u\u0005B1A\u0005\u0002\u00055\u0004\u0002CAP\u0011\u0001\u0006I!a\u0016\t\u0013\u0005\u0005\u0006B1A\u0005\u0002\u00055\u0004\u0002CAR\u0011\u0001\u0006I!a\u0016\t\u0013\u0005\u0015\u0006B1A\u0005\u0002\u00055\u0004\u0002CAT\u0011\u0001\u0006I!a\u0016\t\u0013\u0005%\u0006B1A\u0005\u0002\u00055\u0004\u0002CAV\u0011\u0001\u0006I!a\u0016\t\u000f\u00055\u0006\u0002\"\u0001\u00020\"9\u0011\u0011\u0018\u0005\u0005\u0002\u0005m\u0006bBA`\u0011\u0011\u0005\u0011\u0011\u0019\u0005\b\u0003/DA\u0011AAm\u0011\u001d\ty\u000f\u0003C\u0001\u0003cD\u0011\"a?\u0001\u0003\u0003%\t!!@\t\u0013\t%\u0001!%A\u0005\u0002\t-\u0001\"\u0003B\u0013\u0001\u0005\u0005I\u0011\tB\u0014\u0011%\u0011I\u0004AA\u0001\n\u0003\u0011Y\u0004C\u0005\u0003D\u0001\t\t\u0011\"\u0001\u0003F!I!1\n\u0001\u0002\u0002\u0013\u0005#Q\n\u0005\n\u00057\u0002\u0011\u0011!C\u0001\u0005;B\u0011B!\u0019\u0001\u0003\u0003%\tEa\u0019\t\u0013\t\u001d\u0004!!A\u0005B\t%\u0004\"\u0003B6\u0001\u0005\u0005I\u0011\tB7\u0011%\u0011y\u0007AA\u0001\n\u0003\u0012\th\u0002\u0006\u0003vU\n\t\u0011#\u00016\u0005o2\u0011\u0002N\u001b\u0002\u0002#\u0005QG!\u001f\t\r\u001dtC\u0011\u0001BC\u0011%\u0011YGLA\u0001\n\u000b\u0012i\u0007C\u0005\u0002p:\n\t\u0011\"!\u0003\b\"I!1\u0013\u0018\u0002\u0002\u0013\u0005%Q\u0013\u0005\n\u0005Ss\u0013\u0011!C\u0005\u0005W\u0013qb\u00115fG.,GMU3xe&$XM\u001d\u0006\u0003m]\na!\\1de>\u001c(\"\u0001\u001d\u0002\u000bM\u0004\u0018N]3\u0016\u0005i*6\u0003\u0002\u0001<\u0003\u0012\u0003\"\u0001P \u000e\u0003uR\u0011AP\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0001v\u0012a!\u00118z%\u00164\u0007C\u0001\u001fC\u0013\t\u0019UHA\u0004Qe>$Wo\u0019;\u0011\u0005\u0015seB\u0001$M\u001d\t95*D\u0001I\u0015\tI%*\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005q\u0014BA'>\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0014)\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u00055k\u0014!A2\u0016\u0003M\u0003\"\u0001V+\r\u0001\u0011)a\u000b\u0001b\u0001/\n\t1)\u0005\u0002Y7B\u0011A(W\u0005\u00035v\u0012qAT8uQ&tw\r\u0005\u0002]G:\u0011Q,\u0019\b\u0003=\u0002t!aR0\n\u0003aJ!AN\u001c\n\u0005\t,\u0014AB2p[B\fG/\u0003\u0002eK\n91i\u001c8uKb$(B\u000126\u0003\t\u0019\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003S.\u00042A\u001b\u0001T\u001b\u0005)\u0004\"B)\u0004\u0001\u0004\u0019\u0016a\u0003:foJLG/Z*bM\u0016,2A\\A\u000b)\u0015y\u0017\u0011EA\u0013)\r\u0001\u0018Q\u0001\t\u0003crt!A\u001d;\u000f\u0005M\fQ\"\u0001\u0001\n\u0005U4\u0018\u0001C;oSZ,'o]3\n\u0005\u0011<(B\u0001=z\u0003!\u0011G.Y2lE>D(B\u0001\u001c{\u0015\tYX(A\u0004sK\u001adWm\u0019;\n\u0005ut(\u0001\u0002+sK\u0016L1a`A\u0001\u0005\u0015!&/Z3t\u0015\r\t\u0019A_\u0001\u0004CBL\u0007\"CA\u0004\t\u0005\u0005\t9AA\u0005\u0003))g/\u001b3f]\u000e,G%\u000e\t\u0006e\u0006-\u00111C\u0005\u0005\u0003\u001b\tyAA\u0006XK\u0006\\G+\u001f9f)\u0006<\u0017bAA\ts\n9\u0011\t\\5bg\u0016\u001c\bc\u0001+\u0002\u0016\u00119\u0011q\u0003\u0003C\u0002\u0005e!!A!\u0012\u0007a\u000bY\u0002E\u0002=\u0003;I1!a\b>\u0005\r\te.\u001f\u0005\u0007\u0003G!\u0001\u0019\u00019\u0002\tQ\u0014X-\u001a\u0005\u0007\u0003O!\u0001\u0019\u00019\u0002\u0011\u0019\fG\u000e\u001c2bG.\f1B]3xe&$XMR1tiV!\u0011QFA\u001d)\u0019\ty#a\u000f\u0002>Q\u0019\u0001/!\r\t\u0013\u0005MR!!AA\u0004\u0005U\u0012AC3wS\u0012,gnY3%mA)!/a\u0003\u00028A\u0019A+!\u000f\u0005\u000f\u0005]QA1\u0001\u0002\u001a!1\u00111E\u0003A\u0002ADa!a\n\u0006\u0001\u0004\u0001\u0018\u0001E<be:|enU5na2,GK]3f)\u0011\t\u0019%!\u0013\u0011\u0007q\n)%C\u0002\u0002Hu\u0012A!\u00168ji\"1\u00111\u0005\u0004A\u0002A\fA\"\\1lKJ+wO]5uKJ$B!a\u0014\u0002VA\u0019\u0011/!\u0015\n\u0007\u0005McPA\u0006Ue\u0006t7OZ8s[\u0016\u0014\bbBA\u0014\u000f\u0001\u0007\u0011q\u000b\t\u0004c\u0006e\u0013\u0002BA.\u0003;\u0012\u0001\u0002V3s[:\u000bW.Z\u0005\u0005\u0003?\n\tAA\u0003OC6,7O\u0001\u0005SK^\u0014\u0018\u000e^3s+\u0011\t)'a\u001e\u0014\u0005!Y\u0014\u0001C7j]Z\u000bG.^3\u0016\u0003A\f\u0011\"\\5o-\u0006dW/\u001a\u0011\u0016\u0005\u0005]\u0013!\u00034bY2\u0014\u0017mY6!\u0003\u001d!\u0018\u0010]3UC\u001e\u0004RA]A\u0006\u0003k\u00022\u0001VA<\t\u001d\t9\u0002\u0003b\u0001\u00033!b!a\u001f\u0002\u0002\u0006\rE\u0003BA?\u0003\u007f\u0002Ba\u001d\u0005\u0002v!9\u0011\u0011\u000f\bA\u0004\u0005M\u0004BBA4\u001d\u0001\u0007\u0001\u000fC\u0004\u0002(9\u0001\r!a\u0016\u0002\u0007Q\u0004X-\u0006\u0002\u0002\nB\u0019\u0011/a#\n\t\u00055\u0015q\u0012\u0002\u0005)f\u0004X-\u0003\u0003\u0002\u0012\u0006\u0005!!\u0002+za\u0016\u001c\u0018\u0001\u0002;qK\u0002\naAT3hCR,\u0017a\u0002(fO\u0006$X\rI\u0001\u0005!2,8/A\u0003QYV\u001c\b%A\u0003NS:,8/\u0001\u0004NS:,8\u000fI\u0001\u0006)&lWm]\u0001\u0007)&lWm\u001d\u0011\u0002\u0007\u0011Kg/\u0001\u0003ESZ\u0004\u0013aA'pI\u0006!Qj\u001c3!\u0003\u001d\u0011\u0017N\\8q\u001f.$B!!-\u00028B\u0019A(a-\n\u0007\u0005UVHA\u0004C_>dW-\u00198\t\r\u0005\rR\u00041\u0001q\u0003!I7oU5na2,G\u0003BAY\u0003{Ca!a\t\u001f\u0001\u0004\u0001\u0018\u0001\u0003:v]^KG\u000f\u001b-\u0015\r\u0005\r\u0017qZAj)\r\u0001\u0018Q\u0019\u0005\b\u0003\u000f|\u0002\u0019AAe\u0003\u00051\u0007#\u0002\u001f\u0002LB\u0004\u0018bAAg{\tIa)\u001e8di&|g.\r\u0005\b\u0003#|\u0002\u0019AAe\u0003\u001d\u0011Xm\u001e:ji\u0016Da!!6 \u0001\u0004\u0001\u0018aA:vE\u0006Q!/\u001e8XSRD\u0007,\u0017.\u0015\u0011\u0005m\u0017Q]At\u0003W$2\u0001]Ao\u0011\u001d\t9\r\ta\u0001\u0003?\u0004r\u0001PAqaB\u0004\b/C\u0002\u0002dv\u0012\u0011BR;oGRLwN\\\u001a\t\u000f\u0005E\u0007\u00051\u0001\u0002J\"1\u0011\u0011\u001e\u0011A\u0002A\f1\u0001\u001c5t\u0011\u0019\ti\u000f\ta\u0001a\u0006\u0019!\u000f[:\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005M\u0018\u0011 \t\u0006y\u0005U\b\u000f]\u0005\u0004\u0003ol$a\u0004)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\t\u000f\u0005E\u0017\u00051\u0001\u0002J\u0006!1m\u001c9z+\u0011\tyP!\u0002\u0015\t\t\u0005!q\u0001\t\u0005U\u0002\u0011\u0019\u0001E\u0002U\u0005\u000b!QA\u0016\u0012C\u0002]C\u0001\"\u0015\u0012\u0011\u0002\u0003\u0007!1A\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0011\u0011iAa\t\u0016\u0005\t=!fA*\u0003\u0012-\u0012!1\u0003\t\u0005\u0005+\u0011y\"\u0004\u0002\u0003\u0018)!!\u0011\u0004B\u000e\u0003%)hn\u00195fG.,GMC\u0002\u0003\u001eu\n!\"\u00198o_R\fG/[8o\u0013\u0011\u0011\tCa\u0006\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003WG\t\u0007q+A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005S\u0001BAa\u000b\u000365\u0011!Q\u0006\u0006\u0005\u0005_\u0011\t$\u0001\u0003mC:<'B\u0001B\u001a\u0003\u0011Q\u0017M^1\n\t\t]\"Q\u0006\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\tu\u0002c\u0001\u001f\u0003@%\u0019!\u0011I\u001f\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005m!q\t\u0005\n\u0005\u00132\u0013\u0011!a\u0001\u0005{\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001B(!\u0019\u0011\tFa\u0016\u0002\u001c5\u0011!1\u000b\u0006\u0004\u0005+j\u0014AC2pY2,7\r^5p]&!!\u0011\fB*\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005E&q\f\u0005\n\u0005\u0013B\u0013\u0011!a\u0001\u00037\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!\u0011\u0006B3\u0011%\u0011I%KA\u0001\u0002\u0004\u0011i$\u0001\u0005iCND7i\u001c3f)\t\u0011i$\u0001\u0005u_N#(/\u001b8h)\t\u0011I#\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003c\u0013\u0019\bC\u0005\u0003J1\n\t\u00111\u0001\u0002\u001c\u0005y1\t[3dW\u0016$'+Z<sSR,'\u000f\u0005\u0002k]M!af\u000fB>!\u0011\u0011iHa!\u000e\u0005\t}$\u0002\u0002BA\u0005c\t!![8\n\u0007=\u0013y\b\u0006\u0002\u0003xU!!\u0011\u0012BH)\u0011\u0011YI!%\u0011\t)\u0004!Q\u0012\t\u0004)\n=E!\u0002,2\u0005\u00049\u0006BB)2\u0001\u0004\u0011i)A\u0004v]\u0006\u0004\b\u000f\\=\u0016\t\t]%\u0011\u0015\u000b\u0005\u00053\u0013\u0019\u000bE\u0003=\u00057\u0013y*C\u0002\u0003\u001ev\u0012aa\u00149uS>t\u0007c\u0001+\u0003\"\u0012)aK\rb\u0001/\"I!Q\u0015\u001a\u0002\u0002\u0003\u0007!qU\u0001\u0004q\u0012\u0002\u0004\u0003\u00026\u0001\u0005?\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"A!,\u0011\t\t-\"qV\u0005\u0005\u0005c\u0013iC\u0001\u0004PE*,7\r\u001e"
)
public class CheckedRewriter implements Product, Serializable {
   private final Context c;

   public static Option unapply(final CheckedRewriter x$0) {
      return CheckedRewriter$.MODULE$.unapply(x$0);
   }

   public static CheckedRewriter apply(final Context c) {
      return CheckedRewriter$.MODULE$.apply(c);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Context c() {
      return this.c;
   }

   public Trees.TreeApi rewriteSafe(final Trees.TreeApi tree, final Trees.TreeApi fallback, final TypeTags.WeakTypeTag evidence$5) {
      this.warnOnSimpleTree(tree);
      Types.TypeApi A = this.c().universe().weakTypeOf(evidence$5);
      Names.TermNameApi aname = compat$.MODULE$.freshTermName(this.c(), "checked$attempt$");
      Names.TermNameApi fname = compat$.MODULE$.freshTermName(this.c(), "checked$fallback$");
      Trees.TreeApi attempt = this.makeRewriter(fname).transform(tree);
      return this.c().universe().internal().reificationSupport().SyntacticBlock().apply((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticDefDef().apply(this.c().universe().NoMods(), fname, scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$, this.c().universe().Liftable().liftType().apply(A), fallback), new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticDefDef().apply(this.c().universe().NoMods(), aname, scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$, this.c().universe().Liftable().liftType().apply(A), attempt), new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(aname, false), scala.collection.immutable.Nil..MODULE$)))));
   }

   public Trees.TreeApi rewriteFast(final Trees.TreeApi tree, final Trees.TreeApi fallback, final TypeTags.WeakTypeTag evidence$6) {
      this.warnOnSimpleTree(tree);
      Types.TypeApi A = this.c().universe().weakTypeOf(evidence$6);
      Names.TermNameApi fname = compat$.MODULE$.freshTermName(this.c(), "checked$fallback$");
      Trees.TreeApi attempt = this.makeRewriter(fname).transform(tree);
      return this.c().universe().internal().reificationSupport().SyntacticBlock().apply((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticDefDef().apply(this.c().universe().NoMods(), fname, scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$, this.c().universe().Liftable().liftType().apply(A), fallback), new .colon.colon(attempt, scala.collection.immutable.Nil..MODULE$))));
   }

   public void warnOnSimpleTree(final Trees.TreeApi tree) {
      if (tree != null) {
         Option var4 = this.c().universe().LiteralTag().unapply(tree);
         if (!var4.isEmpty()) {
            Trees.LiteralApi var5 = (Trees.LiteralApi)var4.get();
            if (var5 != null) {
               Option var6 = this.c().universe().Literal().unapply(var5);
               if (!var6.isEmpty()) {
                  this.c().warning(tree.pos(), "checked used with literal");
                  BoxedUnit var11 = BoxedUnit.UNIT;
                  return;
               }
            }
         }
      }

      if (tree != null) {
         Option var7 = this.c().universe().IdentTag().unapply(tree);
         if (!var7.isEmpty()) {
            Trees.IdentApi var8 = (Trees.IdentApi)var7.get();
            if (var8 != null) {
               Option var9 = this.c().universe().Ident().unapply(var8);
               if (!var9.isEmpty()) {
                  this.c().warning(tree.pos(), "checked used with simple identifier");
                  BoxedUnit var10 = BoxedUnit.UNIT;
                  return;
               }
            }
         }
      }

      BoxedUnit var2 = BoxedUnit.UNIT;
   }

   public Trees.Transformer makeRewriter(final Names.TermNameApi fallback) {
      Rewriter LongRewriter = new Rewriter(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("Long"), false), this.c().universe().TermName().apply("MinValue")), fallback, this.c().universe().WeakTypeTag().Long());
      Rewriter IntRewriter = new Rewriter(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("Int"), false), this.c().universe().TermName().apply("MinValue")), fallback, this.c().universe().WeakTypeTag().Int());
      return new Trees.Transformer(IntRewriter, LongRewriter) {
         private final PartialFunction f;

         private PartialFunction f() {
            return this.f;
         }

         public Trees.TreeApi transform(final Trees.TreeApi tree) {
            return this.f().isDefinedAt(tree) ? (Trees.TreeApi)this.f().apply(tree) : super.transform(tree);
         }

         public {
            this.f = IntRewriter$1.apply((tree) -> this.transform(tree)).orElse(LongRewriter$1.apply((tree) -> this.transform(tree)));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CheckedRewriter copy(final Context c) {
      return new CheckedRewriter(c);
   }

   public Context copy$default$1() {
      return this.c();
   }

   public String productPrefix() {
      return "CheckedRewriter";
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
      return x$1 instanceof CheckedRewriter;
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
            if (x$1 instanceof CheckedRewriter) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     CheckedRewriter var4 = (CheckedRewriter)x$1;
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

   public CheckedRewriter(final Context c) {
      this.c = c;
      Product.$init$(this);
   }

   public class Rewriter {
      private final Trees.TreeApi minValue;
      private final Names.TermNameApi fallback;
      private final Types.TypeApi tpe;
      private final Names.TermNameApi Negate;
      private final Names.TermNameApi Plus;
      private final Names.TermNameApi Minus;
      private final Names.TermNameApi Times;
      private final Names.TermNameApi Div;
      private final Names.TermNameApi Mod;
      // $FF: synthetic field
      public final CheckedRewriter $outer;

      public Trees.TreeApi minValue() {
         return this.minValue;
      }

      public Names.TermNameApi fallback() {
         return this.fallback;
      }

      public Types.TypeApi tpe() {
         return this.tpe;
      }

      public Names.TermNameApi Negate() {
         return this.Negate;
      }

      public Names.TermNameApi Plus() {
         return this.Plus;
      }

      public Names.TermNameApi Minus() {
         return this.Minus;
      }

      public Names.TermNameApi Times() {
         return this.Times;
      }

      public Names.TermNameApi Div() {
         return this.Div;
      }

      public Names.TermNameApi Mod() {
         return this.Mod;
      }

      public boolean binopOk(final Trees.TreeApi tree) {
         boolean var2;
         if (tree != null) {
            Option var4 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().ApplyTag().unapply(tree);
            if (!var4.isEmpty()) {
               Trees.ApplyApi var5 = (Trees.ApplyApi)var4.get();
               if (var5 != null) {
                  Option var6 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Apply().unapply(var5);
                  if (!var6.isEmpty()) {
                     Trees.TreeApi var7 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                     List var8 = (List)((Tuple2)var6.get())._2();
                     if (var7 != null) {
                        Option var9 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(var7);
                        if (!var9.isEmpty()) {
                           Trees.SelectApi var10 = (Trees.SelectApi)var9.get();
                           if (var10 != null) {
                              Option var11 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var10);
                              if (!var11.isEmpty()) {
                                 Trees.TreeApi lhs = (Trees.TreeApi)((Tuple2)var11.get())._1();
                                 if (var8 instanceof .colon.colon) {
                                    label93: {
                                       .colon.colon var13 = (.colon.colon)var8;
                                       Trees.TreeApi rhs = (Trees.TreeApi)var13.head();
                                       List var15 = var13.next$access$1();
                                       Nil var10000 = scala.package..MODULE$.Nil();
                                       if (var10000 == null) {
                                          if (var15 != null) {
                                             break label93;
                                          }
                                       } else if (!var10000.equals(var15)) {
                                          break label93;
                                       }

                                       Types.TypeApi lt = lhs.tpe().widen();
                                       Types.TypeApi rt = rhs.tpe().widen();
                                       var2 = lt.weak_$less$colon$less(this.tpe()) && rt.$less$colon$less(this.tpe()) || lt.$less$colon$less(this.tpe()) && rt.weak_$less$colon$less(this.tpe());
                                       return var2;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         var2 = false;
         return var2;
      }

      public boolean isSimple(final Trees.TreeApi tree) {
         boolean var3;
         label44: {
            if (tree != null) {
               Option var5 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().LiteralTag().unapply(tree);
               if (!var5.isEmpty()) {
                  Trees.LiteralApi var6 = (Trees.LiteralApi)var5.get();
                  if (var6 != null) {
                     Option var7 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Literal().unapply(var6);
                     if (!var7.isEmpty()) {
                        var3 = true;
                        break label44;
                     }
                  }
               }
            }

            if (tree != null) {
               Option var8 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().IdentTag().unapply(tree);
               if (!var8.isEmpty()) {
                  Trees.IdentApi var9 = (Trees.IdentApi)var8.get();
                  if (var9 != null) {
                     Option var10 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Ident().unapply(var9);
                     if (!var10.isEmpty()) {
                        var3 = true;
                        break label44;
                     }
                  }
               }
            }

            var3 = false;
         }

         boolean var2;
         if (var3) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public Trees.TreeApi runWithX(final Function1 rewrite, final Trees.TreeApi sub, final Function1 f) {
         Trees.TreeApi var10000;
         if (this.isSimple(sub)) {
            var10000 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply((List)(new .colon.colon((Trees.TreeApi)f.apply(sub), scala.collection.immutable.Nil..MODULE$)));
         } else {
            Names.TermNameApi x = compat$.MODULE$.freshTermName(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c(), "x$");
            var10000 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply((List)(new .colon.colon(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticValDef().apply(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().NoMods(), x, this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), (Trees.TreeApi)rewrite.apply(sub)), new .colon.colon((Trees.TreeApi)f.apply(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(x, false)), scala.collection.immutable.Nil..MODULE$))));
         }

         return var10000;
      }

      public Trees.TreeApi runWithXYZ(final Function1 rewrite, final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Function3 f) {
         Names.TermNameApi z = compat$.MODULE$.freshTermName(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c(), "z$");
         Trees.TreeApi var10000;
         if (this.isSimple(lhs) && this.isSimple(rhs)) {
            Trees.TreeApi t = (Trees.TreeApi)f.apply(lhs, rhs, this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(z, false));
            var10000 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply((List)(new .colon.colon(t, scala.collection.immutable.Nil..MODULE$)));
         } else if (this.isSimple(lhs)) {
            Names.TermNameApi y = compat$.MODULE$.freshTermName(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c(), "y$");
            Trees.TreeApi t = (Trees.TreeApi)f.apply(lhs, this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(y, false), this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(z, false));
            var10000 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply((List)(new .colon.colon(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticValDef().apply(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().NoMods(), y, this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), (Trees.TreeApi)rewrite.apply(rhs)), new .colon.colon(t, scala.collection.immutable.Nil..MODULE$))));
         } else if (this.isSimple(rhs)) {
            Names.TermNameApi x = compat$.MODULE$.freshTermName(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c(), "x$");
            Trees.TreeApi t = (Trees.TreeApi)f.apply(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(x, false), rhs, this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(z, false));
            var10000 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply((List)(new .colon.colon(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticValDef().apply(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().NoMods(), x, this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), (Trees.TreeApi)rewrite.apply(lhs)), new .colon.colon(t, scala.collection.immutable.Nil..MODULE$))));
         } else {
            Names.TermNameApi x = compat$.MODULE$.freshTermName(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c(), "x$");
            Names.TermNameApi y = compat$.MODULE$.freshTermName(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c(), "y$");
            Trees.TreeApi t = (Trees.TreeApi)f.apply(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(x, false), this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(y, false), this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(z, false));
            var10000 = this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply((List)(new .colon.colon(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticValDef().apply(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().NoMods(), x, this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), (Trees.TreeApi)rewrite.apply(lhs)), new .colon.colon(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticValDef().apply(this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().NoMods(), y, this.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), (Trees.TreeApi)rewrite.apply(rhs)), new .colon.colon(t, scala.collection.immutable.Nil..MODULE$)))));
         }

         return var10000;
      }

      public PartialFunction apply(final Function1 rewrite) {
         return new Serializable(rewrite) {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final Rewriter $outer;
            private final Function1 rewrite$1;

            public final Object applyOrElse(final Trees.TreeApi x1, final Function1 default) {
               Object var3;
               if (x1 != null) {
                  Option var5 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(x1);
                  if (!var5.isEmpty()) {
                     Trees.SelectApi var6 = (Trees.SelectApi)var5.get();
                     if (var6 != null) {
                        Option var7 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var6);
                        if (!var7.isEmpty()) {
                           label282: {
                              Trees.TreeApi sub = (Trees.TreeApi)((Tuple2)var7.get())._1();
                              Names.NameApi var9 = (Names.NameApi)((Tuple2)var7.get())._2();
                              Names.TermNameApi var10000 = this.$outer.Negate();
                              if (var10000 == null) {
                                 if (var9 != null) {
                                    break label282;
                                 }
                              } else if (!var10000.equals(var9)) {
                                 break label282;
                              }

                              if (sub.tpe().widen().$less$colon$less(this.$outer.tpe())) {
                                 var3 = this.$outer.runWithX(this.rewrite$1, sub, (x) -> this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().If().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$eq$eq")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.minValue(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Return().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.$outer.fallback(), false)), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply(scala.collection.immutable.Nil..MODULE$)), new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("unary_$minus")), scala.collection.immutable.Nil..MODULE$)))));
                                 return var3;
                              }
                           }
                        }
                     }
                  }
               }

               if (x1 != null) {
                  Option var11 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().ApplyTag().unapply(x1);
                  if (!var11.isEmpty()) {
                     Trees.ApplyApi var12 = (Trees.ApplyApi)var11.get();
                     if (var12 != null) {
                        Option var13 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Apply().unapply(var12);
                        if (!var13.isEmpty()) {
                           Trees.TreeApi var14 = (Trees.TreeApi)((Tuple2)var13.get())._1();
                           List var15 = (List)((Tuple2)var13.get())._2();
                           if (var14 != null) {
                              Option var16 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(var14);
                              if (!var16.isEmpty()) {
                                 Trees.SelectApi var17 = (Trees.SelectApi)var16.get();
                                 if (var17 != null) {
                                    Option var18 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var17);
                                    if (!var18.isEmpty()) {
                                       label266: {
                                          Trees.TreeApi lhs = (Trees.TreeApi)((Tuple2)var18.get())._1();
                                          Names.NameApi var20 = (Names.NameApi)((Tuple2)var18.get())._2();
                                          Names.TermNameApi var86 = this.$outer.Plus();
                                          if (var86 == null) {
                                             if (var20 != null) {
                                                break label266;
                                             }
                                          } else if (!var86.equals(var20)) {
                                             break label266;
                                          }

                                          if (var15 instanceof .colon.colon) {
                                             label260: {
                                                .colon.colon var22 = (.colon.colon)var15;
                                                Trees.TreeApi rhs = (Trees.TreeApi)var22.head();
                                                List var24 = var22.next$access$1();
                                                Nil var87 = scala.package..MODULE$.Nil();
                                                if (var87 == null) {
                                                   if (var24 != null) {
                                                      break label260;
                                                   }
                                                } else if (!var87.equals(var24)) {
                                                   break label260;
                                                }

                                                if (this.$outer.binopOk(x1)) {
                                                   var3 = this.$outer.runWithXYZ(this.rewrite$1, lhs, rhs, (x, y, z) -> this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply((List)this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticPatDef().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().NoMods(), z, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(y, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)))).$plus$plus((IterableOnce)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().If().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$up")), (List)(new .colon.colon((List)(new .colon.colon(y, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("unary_$tilde")), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$amp")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$up")), (List)(new .colon.colon((List)(new .colon.colon(z, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$less")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Literal().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Constant().apply(BoxesRunTime.boxToInteger(0))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Return().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.$outer.fallback(), false)), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply(scala.collection.immutable.Nil..MODULE$)), new .colon.colon(z, scala.collection.immutable.Nil..MODULE$))))));
                                                   return var3;
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (x1 != null) {
                  Option var26 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().ApplyTag().unapply(x1);
                  if (!var26.isEmpty()) {
                     Trees.ApplyApi var27 = (Trees.ApplyApi)var26.get();
                     if (var27 != null) {
                        Option var28 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Apply().unapply(var27);
                        if (!var28.isEmpty()) {
                           Trees.TreeApi var29 = (Trees.TreeApi)((Tuple2)var28.get())._1();
                           List var30 = (List)((Tuple2)var28.get())._2();
                           if (var29 != null) {
                              Option var31 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(var29);
                              if (!var31.isEmpty()) {
                                 Trees.SelectApi var32 = (Trees.SelectApi)var31.get();
                                 if (var32 != null) {
                                    Option var33 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var32);
                                    if (!var33.isEmpty()) {
                                       label244: {
                                          Trees.TreeApi lhs = (Trees.TreeApi)((Tuple2)var33.get())._1();
                                          Names.NameApi var35 = (Names.NameApi)((Tuple2)var33.get())._2();
                                          Names.TermNameApi var88 = this.$outer.Minus();
                                          if (var88 == null) {
                                             if (var35 != null) {
                                                break label244;
                                             }
                                          } else if (!var88.equals(var35)) {
                                             break label244;
                                          }

                                          if (var30 instanceof .colon.colon) {
                                             label238: {
                                                .colon.colon var37 = (.colon.colon)var30;
                                                Trees.TreeApi rhs = (Trees.TreeApi)var37.head();
                                                List var39 = var37.next$access$1();
                                                Nil var89 = scala.package..MODULE$.Nil();
                                                if (var89 == null) {
                                                   if (var39 != null) {
                                                      break label238;
                                                   }
                                                } else if (!var89.equals(var39)) {
                                                   break label238;
                                                }

                                                if (this.$outer.binopOk(x1)) {
                                                   var3 = this.$outer.runWithXYZ(this.rewrite$1, lhs, rhs, (x, y, z) -> this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply((List)this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticPatDef().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().NoMods(), z, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$minus")), (List)(new .colon.colon((List)(new .colon.colon(y, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)))).$plus$plus((IterableOnce)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().If().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$up")), (List)(new .colon.colon((List)(new .colon.colon(y, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$amp")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$up")), (List)(new .colon.colon((List)(new .colon.colon(z, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$less")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Literal().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Constant().apply(BoxesRunTime.boxToLong(0L))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Return().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.$outer.fallback(), false)), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply(scala.collection.immutable.Nil..MODULE$)), new .colon.colon(z, scala.collection.immutable.Nil..MODULE$))))));
                                                   return var3;
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (x1 != null) {
                  Option var41 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().ApplyTag().unapply(x1);
                  if (!var41.isEmpty()) {
                     Trees.ApplyApi var42 = (Trees.ApplyApi)var41.get();
                     if (var42 != null) {
                        Option var43 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Apply().unapply(var42);
                        if (!var43.isEmpty()) {
                           Trees.TreeApi var44 = (Trees.TreeApi)((Tuple2)var43.get())._1();
                           List var45 = (List)((Tuple2)var43.get())._2();
                           if (var44 != null) {
                              Option var46 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(var44);
                              if (!var46.isEmpty()) {
                                 Trees.SelectApi var47 = (Trees.SelectApi)var46.get();
                                 if (var47 != null) {
                                    Option var48 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var47);
                                    if (!var48.isEmpty()) {
                                       label222: {
                                          Trees.TreeApi lhs = (Trees.TreeApi)((Tuple2)var48.get())._1();
                                          Names.NameApi var50 = (Names.NameApi)((Tuple2)var48.get())._2();
                                          Names.TermNameApi var90 = this.$outer.Times();
                                          if (var90 == null) {
                                             if (var50 != null) {
                                                break label222;
                                             }
                                          } else if (!var90.equals(var50)) {
                                             break label222;
                                          }

                                          if (var45 instanceof .colon.colon) {
                                             label216: {
                                                .colon.colon var52 = (.colon.colon)var45;
                                                Trees.TreeApi rhs = (Trees.TreeApi)var52.head();
                                                List var54 = var52.next$access$1();
                                                Nil var91 = scala.package..MODULE$.Nil();
                                                if (var91 == null) {
                                                   if (var54 != null) {
                                                      break label216;
                                                   }
                                                } else if (!var91.equals(var54)) {
                                                   break label216;
                                                }

                                                if (this.$outer.binopOk(x1)) {
                                                   var3 = this.$outer.runWithXYZ(this.rewrite$1, lhs, rhs, (x, y, z) -> this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply((List)this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticPatDef().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().NoMods(), z, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$times")), (List)(new .colon.colon((List)(new .colon.colon(y, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)))).$plus$plus((IterableOnce)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().If().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$eq$eq")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Literal().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Constant().apply(BoxesRunTime.boxToInteger(0))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$bar$bar")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(y, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$eq$eq")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(z, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$div")), (List)(new .colon.colon((List)(new .colon.colon(x, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$amp$amp")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$eq$eq")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Literal().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Constant().apply(BoxesRunTime.boxToInteger(-1))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$amp$amp")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(y, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$eq$eq")), (List)scala.collection.immutable.List..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new List[]{(List)scala.collection.immutable.List..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{this.$outer.minValue()})))})))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("unary_$bang")), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), z, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Return().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.$outer.fallback(), false))), scala.collection.immutable.Nil..MODULE$)))));
                                                   return var3;
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (x1 != null) {
                  Option var56 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().ApplyTag().unapply(x1);
                  if (!var56.isEmpty()) {
                     Trees.ApplyApi var57 = (Trees.ApplyApi)var56.get();
                     if (var57 != null) {
                        Option var58 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Apply().unapply(var57);
                        if (!var58.isEmpty()) {
                           Trees.TreeApi var59 = (Trees.TreeApi)((Tuple2)var58.get())._1();
                           List var60 = (List)((Tuple2)var58.get())._2();
                           if (var59 != null) {
                              Option var61 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(var59);
                              if (!var61.isEmpty()) {
                                 Trees.SelectApi var62 = (Trees.SelectApi)var61.get();
                                 if (var62 != null) {
                                    Option var63 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var62);
                                    if (!var63.isEmpty()) {
                                       label200: {
                                          Trees.TreeApi lhs = (Trees.TreeApi)((Tuple2)var63.get())._1();
                                          Names.NameApi var65 = (Names.NameApi)((Tuple2)var63.get())._2();
                                          Names.TermNameApi var92 = this.$outer.Div();
                                          if (var92 == null) {
                                             if (var65 != null) {
                                                break label200;
                                             }
                                          } else if (!var92.equals(var65)) {
                                             break label200;
                                          }

                                          if (var60 instanceof .colon.colon) {
                                             label194: {
                                                .colon.colon var67 = (.colon.colon)var60;
                                                Trees.TreeApi rhs = (Trees.TreeApi)var67.head();
                                                List var69 = var67.next$access$1();
                                                Nil var93 = scala.package..MODULE$.Nil();
                                                if (var93 == null) {
                                                   if (var69 != null) {
                                                      break label194;
                                                   }
                                                } else if (!var93.equals(var69)) {
                                                   break label194;
                                                }

                                                if (this.$outer.binopOk(x1)) {
                                                   var3 = this.$outer.runWithXYZ(this.rewrite$1, lhs, rhs, (x, y, z) -> this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply((List)this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticPatDef().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().NoMods(), z, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$div")), (List)(new .colon.colon((List)(new .colon.colon(y, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)))).$plus$plus((IterableOnce)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().If().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(y, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$eq$eq")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Literal().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Constant().apply(BoxesRunTime.boxToInteger(-1))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$amp$amp")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$eq$eq")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.minValue(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Return().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.$outer.fallback(), false)), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply(scala.collection.immutable.Nil..MODULE$)), new .colon.colon(z, scala.collection.immutable.Nil..MODULE$))))));
                                                   return var3;
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (x1 != null) {
                  Option var71 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().ApplyTag().unapply(x1);
                  if (!var71.isEmpty()) {
                     Trees.ApplyApi var72 = (Trees.ApplyApi)var71.get();
                     if (var72 != null) {
                        Option var73 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Apply().unapply(var72);
                        if (!var73.isEmpty()) {
                           Trees.TreeApi var74 = (Trees.TreeApi)((Tuple2)var73.get())._1();
                           List var75 = (List)((Tuple2)var73.get())._2();
                           if (var74 != null) {
                              Option var76 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(var74);
                              if (!var76.isEmpty()) {
                                 Trees.SelectApi var77 = (Trees.SelectApi)var76.get();
                                 if (var77 != null) {
                                    Option var78 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var77);
                                    if (!var78.isEmpty()) {
                                       label178: {
                                          Trees.TreeApi lhs = (Trees.TreeApi)((Tuple2)var78.get())._1();
                                          Names.NameApi var80 = (Names.NameApi)((Tuple2)var78.get())._2();
                                          Names.TermNameApi var94 = this.$outer.Mod();
                                          if (var94 == null) {
                                             if (var80 != null) {
                                                break label178;
                                             }
                                          } else if (!var94.equals(var80)) {
                                             break label178;
                                          }

                                          if (var75 instanceof .colon.colon) {
                                             label172: {
                                                .colon.colon var82 = (.colon.colon)var75;
                                                Trees.TreeApi rhs = (Trees.TreeApi)var82.head();
                                                List var84 = var82.next$access$1();
                                                Nil var95 = scala.package..MODULE$.Nil();
                                                if (var95 == null) {
                                                   if (var84 != null) {
                                                      break label172;
                                                   }
                                                } else if (!var95.equals(var84)) {
                                                   break label172;
                                                }

                                                if (this.$outer.binopOk(x1)) {
                                                   var3 = this.$outer.runWithXYZ(this.rewrite$1, lhs, rhs, (x, y, z) -> this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply((List)this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticPatDef().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().NoMods(), z, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$percent")), (List)(new .colon.colon((List)(new .colon.colon(y, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)))).$plus$plus((IterableOnce)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().If().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(y, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$eq$eq")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Literal().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Constant().apply(BoxesRunTime.boxToInteger(-1))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$amp$amp")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(x, this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().TermName().apply("$eq$eq")), (List)(new .colon.colon((List)(new .colon.colon(this.$outer.minValue(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Return().apply(this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.$outer.fallback(), false)), this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().internal().reificationSupport().SyntacticBlock().apply(scala.collection.immutable.Nil..MODULE$)), new .colon.colon(z, scala.collection.immutable.Nil..MODULE$))))));
                                                   return var3;
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               var3 = default.apply(x1);
               return var3;
            }

            public final boolean isDefinedAt(final Trees.TreeApi x1) {
               boolean var2;
               if (x1 != null) {
                  Option var4 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(x1);
                  if (!var4.isEmpty()) {
                     Trees.SelectApi var5 = (Trees.SelectApi)var4.get();
                     if (var5 != null) {
                        Option var6 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var5);
                        if (!var6.isEmpty()) {
                           label282: {
                              Trees.TreeApi sub = (Trees.TreeApi)((Tuple2)var6.get())._1();
                              Names.NameApi var8 = (Names.NameApi)((Tuple2)var6.get())._2();
                              Names.TermNameApi var10000 = this.$outer.Negate();
                              if (var10000 == null) {
                                 if (var8 != null) {
                                    break label282;
                                 }
                              } else if (!var10000.equals(var8)) {
                                 break label282;
                              }

                              if (sub.tpe().widen().$less$colon$less(this.$outer.tpe())) {
                                 var2 = true;
                                 return var2;
                              }
                           }
                        }
                     }
                  }
               }

               if (x1 != null) {
                  Option var10 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().ApplyTag().unapply(x1);
                  if (!var10.isEmpty()) {
                     Trees.ApplyApi var11 = (Trees.ApplyApi)var10.get();
                     if (var11 != null) {
                        Option var12 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Apply().unapply(var11);
                        if (!var12.isEmpty()) {
                           Trees.TreeApi var13 = (Trees.TreeApi)((Tuple2)var12.get())._1();
                           List var14 = (List)((Tuple2)var12.get())._2();
                           if (var13 != null) {
                              Option var15 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(var13);
                              if (!var15.isEmpty()) {
                                 Trees.SelectApi var16 = (Trees.SelectApi)var15.get();
                                 if (var16 != null) {
                                    Option var17 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var16);
                                    if (!var17.isEmpty()) {
                                       label266: {
                                          Names.NameApi var18 = (Names.NameApi)((Tuple2)var17.get())._2();
                                          Names.TermNameApi var75 = this.$outer.Plus();
                                          if (var75 == null) {
                                             if (var18 != null) {
                                                break label266;
                                             }
                                          } else if (!var75.equals(var18)) {
                                             break label266;
                                          }

                                          if (var14 instanceof .colon.colon) {
                                             label260: {
                                                .colon.colon var20 = (.colon.colon)var14;
                                                List var21 = var20.next$access$1();
                                                Nil var76 = scala.package..MODULE$.Nil();
                                                if (var76 == null) {
                                                   if (var21 != null) {
                                                      break label260;
                                                   }
                                                } else if (!var76.equals(var21)) {
                                                   break label260;
                                                }

                                                if (this.$outer.binopOk(x1)) {
                                                   var2 = true;
                                                   return var2;
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (x1 != null) {
                  Option var23 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().ApplyTag().unapply(x1);
                  if (!var23.isEmpty()) {
                     Trees.ApplyApi var24 = (Trees.ApplyApi)var23.get();
                     if (var24 != null) {
                        Option var25 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Apply().unapply(var24);
                        if (!var25.isEmpty()) {
                           Trees.TreeApi var26 = (Trees.TreeApi)((Tuple2)var25.get())._1();
                           List var27 = (List)((Tuple2)var25.get())._2();
                           if (var26 != null) {
                              Option var28 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(var26);
                              if (!var28.isEmpty()) {
                                 Trees.SelectApi var29 = (Trees.SelectApi)var28.get();
                                 if (var29 != null) {
                                    Option var30 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var29);
                                    if (!var30.isEmpty()) {
                                       label244: {
                                          Names.NameApi var31 = (Names.NameApi)((Tuple2)var30.get())._2();
                                          Names.TermNameApi var77 = this.$outer.Minus();
                                          if (var77 == null) {
                                             if (var31 != null) {
                                                break label244;
                                             }
                                          } else if (!var77.equals(var31)) {
                                             break label244;
                                          }

                                          if (var27 instanceof .colon.colon) {
                                             label238: {
                                                .colon.colon var33 = (.colon.colon)var27;
                                                List var34 = var33.next$access$1();
                                                Nil var78 = scala.package..MODULE$.Nil();
                                                if (var78 == null) {
                                                   if (var34 != null) {
                                                      break label238;
                                                   }
                                                } else if (!var78.equals(var34)) {
                                                   break label238;
                                                }

                                                if (this.$outer.binopOk(x1)) {
                                                   var2 = true;
                                                   return var2;
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (x1 != null) {
                  Option var36 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().ApplyTag().unapply(x1);
                  if (!var36.isEmpty()) {
                     Trees.ApplyApi var37 = (Trees.ApplyApi)var36.get();
                     if (var37 != null) {
                        Option var38 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Apply().unapply(var37);
                        if (!var38.isEmpty()) {
                           Trees.TreeApi var39 = (Trees.TreeApi)((Tuple2)var38.get())._1();
                           List var40 = (List)((Tuple2)var38.get())._2();
                           if (var39 != null) {
                              Option var41 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(var39);
                              if (!var41.isEmpty()) {
                                 Trees.SelectApi var42 = (Trees.SelectApi)var41.get();
                                 if (var42 != null) {
                                    Option var43 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var42);
                                    if (!var43.isEmpty()) {
                                       label222: {
                                          Names.NameApi var44 = (Names.NameApi)((Tuple2)var43.get())._2();
                                          Names.TermNameApi var79 = this.$outer.Times();
                                          if (var79 == null) {
                                             if (var44 != null) {
                                                break label222;
                                             }
                                          } else if (!var79.equals(var44)) {
                                             break label222;
                                          }

                                          if (var40 instanceof .colon.colon) {
                                             label216: {
                                                .colon.colon var46 = (.colon.colon)var40;
                                                List var47 = var46.next$access$1();
                                                Nil var80 = scala.package..MODULE$.Nil();
                                                if (var80 == null) {
                                                   if (var47 != null) {
                                                      break label216;
                                                   }
                                                } else if (!var80.equals(var47)) {
                                                   break label216;
                                                }

                                                if (this.$outer.binopOk(x1)) {
                                                   var2 = true;
                                                   return var2;
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (x1 != null) {
                  Option var49 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().ApplyTag().unapply(x1);
                  if (!var49.isEmpty()) {
                     Trees.ApplyApi var50 = (Trees.ApplyApi)var49.get();
                     if (var50 != null) {
                        Option var51 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Apply().unapply(var50);
                        if (!var51.isEmpty()) {
                           Trees.TreeApi var52 = (Trees.TreeApi)((Tuple2)var51.get())._1();
                           List var53 = (List)((Tuple2)var51.get())._2();
                           if (var52 != null) {
                              Option var54 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(var52);
                              if (!var54.isEmpty()) {
                                 Trees.SelectApi var55 = (Trees.SelectApi)var54.get();
                                 if (var55 != null) {
                                    Option var56 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var55);
                                    if (!var56.isEmpty()) {
                                       label200: {
                                          Names.NameApi var57 = (Names.NameApi)((Tuple2)var56.get())._2();
                                          Names.TermNameApi var81 = this.$outer.Div();
                                          if (var81 == null) {
                                             if (var57 != null) {
                                                break label200;
                                             }
                                          } else if (!var81.equals(var57)) {
                                             break label200;
                                          }

                                          if (var53 instanceof .colon.colon) {
                                             label194: {
                                                .colon.colon var59 = (.colon.colon)var53;
                                                List var60 = var59.next$access$1();
                                                Nil var82 = scala.package..MODULE$.Nil();
                                                if (var82 == null) {
                                                   if (var60 != null) {
                                                      break label194;
                                                   }
                                                } else if (!var82.equals(var60)) {
                                                   break label194;
                                                }

                                                if (this.$outer.binopOk(x1)) {
                                                   var2 = true;
                                                   return var2;
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (x1 != null) {
                  Option var62 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().ApplyTag().unapply(x1);
                  if (!var62.isEmpty()) {
                     Trees.ApplyApi var63 = (Trees.ApplyApi)var62.get();
                     if (var63 != null) {
                        Option var64 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Apply().unapply(var63);
                        if (!var64.isEmpty()) {
                           Trees.TreeApi var65 = (Trees.TreeApi)((Tuple2)var64.get())._1();
                           List var66 = (List)((Tuple2)var64.get())._2();
                           if (var65 != null) {
                              Option var67 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().SelectTag().unapply(var65);
                              if (!var67.isEmpty()) {
                                 Trees.SelectApi var68 = (Trees.SelectApi)var67.get();
                                 if (var68 != null) {
                                    Option var69 = this.$outer.spire$macros$CheckedRewriter$Rewriter$$$outer().c().universe().Select().unapply(var68);
                                    if (!var69.isEmpty()) {
                                       label178: {
                                          Names.NameApi var70 = (Names.NameApi)((Tuple2)var69.get())._2();
                                          Names.TermNameApi var83 = this.$outer.Mod();
                                          if (var83 == null) {
                                             if (var70 != null) {
                                                break label178;
                                             }
                                          } else if (!var83.equals(var70)) {
                                             break label178;
                                          }

                                          if (var66 instanceof .colon.colon) {
                                             label172: {
                                                .colon.colon var72 = (.colon.colon)var66;
                                                List var73 = var72.next$access$1();
                                                Nil var84 = scala.package..MODULE$.Nil();
                                                if (var84 == null) {
                                                   if (var73 != null) {
                                                      break label172;
                                                   }
                                                } else if (!var84.equals(var73)) {
                                                   break label172;
                                                }

                                                if (this.$outer.binopOk(x1)) {
                                                   var2 = true;
                                                   return var2;
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               var2 = false;
               return var2;
            }

            public {
               if (Rewriter.this == null) {
                  throw null;
               } else {
                  this.$outer = Rewriter.this;
                  this.rewrite$1 = rewrite$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         };
      }

      // $FF: synthetic method
      public CheckedRewriter spire$macros$CheckedRewriter$Rewriter$$$outer() {
         return this.$outer;
      }

      public Rewriter(final Trees.TreeApi minValue, final Names.TermNameApi fallback, final TypeTags.WeakTypeTag typeTag) {
         this.minValue = minValue;
         this.fallback = fallback;
         if (CheckedRewriter.this == null) {
            throw null;
         } else {
            this.$outer = CheckedRewriter.this;
            super();
            this.tpe = typeTag.tpe();
            this.Negate = compat$.MODULE$.termName(CheckedRewriter.this.c(), "unary_$minus");
            this.Plus = compat$.MODULE$.termName(CheckedRewriter.this.c(), "$plus");
            this.Minus = compat$.MODULE$.termName(CheckedRewriter.this.c(), "$minus");
            this.Times = compat$.MODULE$.termName(CheckedRewriter.this.c(), "$times");
            this.Div = compat$.MODULE$.termName(CheckedRewriter.this.c(), "$div");
            this.Mod = compat$.MODULE$.termName(CheckedRewriter.this.c(), "$percent");
         }
      }
   }
}
