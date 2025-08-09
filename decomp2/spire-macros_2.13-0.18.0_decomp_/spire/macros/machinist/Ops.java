package spire.macros.machinist;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Internals;
import scala.reflect.api.Mirror;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;
import scala.reflect.macros.blackbox.Context;

@ScalaSignature(
   bytes = "\u0006\u0005\t5fa\u0002\f\u0018!\u0003\r\tA\b\u0005\u0006K\u0001!\tA\n\u0005\u0006U\u0001!\ta\u000b\u0005\u0006\u0013\u0002!\tA\u0013\u0005\u0006#\u0002!\tA\u0015\u0005\u0006A\u0002!\t!\u0019\u0005\u0006_\u0002!\t\u0001\u001d\u0005\u0006}\u0002!\ta \u0005\b\u00033\u0001A\u0011AA\u000e\u0011\u001d\tI\u0003\u0001C\u0001\u0003WAq!!\u000f\u0001\t\u0003\tY\u0004C\u0004\u0002f\u0001!\t!a\u001a\t\u000f\u0005}\u0004\u0001\"\u0001\u0002\u0002\"9\u0011Q\u0014\u0001\u0005\u0002\u0005}\u0005bBAa\u0001\u0011\u0005\u00111\u0019\u0005\b\u0003K\u0004A\u0011AAt\u0011\u001d\u0011)\u0002\u0001C\u0001\u0005/AqA!\u000f\u0001\t\u0003\u0011Y\u0004C\u0004\u0003T\u0001!\tA!\u0016\t\u000f\t\r\u0005\u0001\"\u0001\u0003\u0006\"9!q\u0012\u0001\u0005\u0002\tE\u0005b\u0002BR\u0001\u0019\u0005!Q\u0015\u0002\u0004\u001fB\u001c(B\u0001\r\u001a\u0003%i\u0017m\u00195j]&\u001cHO\u0003\u0002\u001b7\u00051Q.Y2s_NT\u0011\u0001H\u0001\u0006gBL'/Z\u0002\u0001'\t\u0001q\u0004\u0005\u0002!G5\t\u0011EC\u0001#\u0003\u0015\u00198-\u00197b\u0013\t!\u0013E\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u001d\u0002\"\u0001\t\u0015\n\u0005%\n#\u0001B+oSR\fA!\u001e8paV\u0011A\u0006\u0011\u000b\u0003[A\u00022AL\u001e@\u001d\ty\u0003\u0007\u0004\u0001\t\u000bE\u0012\u0001\u0019\u0001\u001a\u0002\u0003\r\u0004\"aM\u001d\u000e\u0003QR!!\u000e\u001c\u0002\u0011\td\u0017mY6c_bT!AG\u001c\u000b\u0005a\n\u0013a\u0002:fM2,7\r^\u0005\u0003uQ\u0012qaQ8oi\u0016DH/\u0003\u0002={\t!Q\t\u001f9s\u0013\tqdGA\u0004BY&\f7/Z:\u0011\u0005=\u0002E!B!\u0003\u0005\u0004\u0011%!\u0001*\u0012\u0005\r3\u0005C\u0001\u0011E\u0013\t)\u0015EA\u0004O_RD\u0017N\\4\u0011\u0005\u0001:\u0015B\u0001%\"\u0005\r\te._\u0001\u0006k:|\u0007\u000fM\u000b\u0003\u0017B#\"\u0001\u0014(\u0011\u00075[tJ\u0004\u00020\u001d\")\u0011g\u0001a\u0001eA\u0011q\u0006\u0015\u0003\u0006\u0003\u000e\u0011\rAQ\u0001\u000bk:|\u0007oV5uQ\u00163XcA*_3R\u0011Ak\u0016\u000b\u0003+j\u00032AV\u001eY\u001d\tys\u000bC\u00032\t\u0001\u0007!\u0007\u0005\u000203\u0012)\u0011\t\u0002b\u0001\u0005\")1\f\u0002a\u00019\u0006\u0011QM\u001e\t\u0004-nj\u0006CA\u0018_\t\u0015yFA1\u0001C\u0005\t)e/A\u0006v]>\u0004x+\u001b;i\u000bZ\u0014Tc\u00012nQR\u00111M\u001a\u000b\u0003I&\u00042!Z\u001eh\u001d\tyc\rC\u00032\u000b\u0001\u0007!\u0007\u0005\u00020Q\u0012)\u0011)\u0002b\u0001\u0005\")!.\u0002a\u0001W\u0006\u0019QM^\u0019\u0011\u0007\u0015\\D\u000e\u0005\u00020[\u0012)a.\u0002b\u0001\u0005\n\u0019QI^\u0019\u0002\u000b\tLgn\u001c9\u0016\u0007Edx\u000f\u0006\u0002skR\u00111\u000f\u001f\t\u0004in2hBA\u0018v\u0011\u0015\td\u00011\u00013!\tys\u000fB\u0003B\r\t\u0007!\tC\u0003z\r\u0001\u0007!0A\u0002sQN\u00042\u0001^\u001e|!\tyC\u0010B\u0003~\r\t\u0007!IA\u0001B\u0003\u0019\u0011(-\u001b8paV1\u0011\u0011AA\f\u0003\u001b!B!a\u0001\u0002\nQ!\u0011QAA\b!\u0015\t9aOA\u0006\u001d\ry\u0013\u0011\u0002\u0005\u0006c\u001d\u0001\rA\r\t\u0004_\u00055A!B!\b\u0005\u0004\u0011\u0005bBA\t\u000f\u0001\u0007\u00111C\u0001\u0004Y\"\u001c\b#BA\u0004w\u0005U\u0001cA\u0018\u0002\u0018\u0011)Qp\u0002b\u0001\u0005\u0006qQO\\8q/&$\bnU2bY\u0006\u0014X\u0003BA\u000f\u0003O!B!a\b\u0002$A)\u0011\u0011E\u001e\u0002&9\u0019q&a\t\t\u000bEB\u0001\u0019\u0001\u001a\u0011\u0007=\n9\u0003B\u0003B\u0011\t\u0007!)A\bv]>\u0004x+\u001b;i'\u000e\fG.\u0019:1+\u0011\ti#a\u000e\u0015\t\u0005=\u00121\u0007\t\u0006\u0003cY\u0014Q\u0007\b\u0004_\u0005M\u0002\"B\u0019\n\u0001\u0004\u0011\u0004cA\u0018\u00028\u0011)\u0011)\u0003b\u0001\u0005\u0006\u0019\u0002.\u00198eY\u0016,fn\u001c9XSRD7\t[5mIV!\u0011QHA%)\u0011\ty$!\u0012\u0015\t\u0005\u0005\u00131\n\t\u0006\u0003\u0007Z\u0014q\t\b\u0004_\u0005\u0015\u0003\"B\u0019\u000b\u0001\u0004\u0011\u0004cA\u0018\u0002J\u0011)\u0011I\u0003b\u0001\u0005\"9\u0011Q\n\u0006A\u0002\u0005=\u0013!C2iS2$g*Y7f!\u0011\t\t&a\u0018\u000f\t\u0005M\u00131\f\t\u0004\u0003+\nSBAA,\u0015\r\tI&H\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005u\u0013%\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003C\n\u0019G\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003;\n\u0013a\u00042j]>\u0004x+\u001b;i'\u000e\fG.\u0019:\u0016\r\u0005%\u0014QPA;)\u0011\tY'!\u001d\u0015\t\u00055\u0014q\u000f\t\u0006\u0003_Z\u00141\u000f\b\u0004_\u0005E\u0004\"B\u0019\f\u0001\u0004\u0011\u0004cA\u0018\u0002v\u0011)\u0011i\u0003b\u0001\u0005\"1\u0011p\u0003a\u0001\u0003s\u0002R!a\u001c<\u0003w\u00022aLA?\t\u0015i8B1\u0001C\u0003QA\u0017M\u001c3mK\nKgn\u001c9XSRD7\t[5mIV1\u00111QAN\u0003##B!!\"\u0002\u000eR!\u0011qQAK)\u0011\tI)a%\u0011\u000b\u0005-5(a$\u000f\u0007=\ni\tC\u00032\u0019\u0001\u0007!\u0007E\u00020\u0003##Q!\u0011\u0007C\u0002\tCq!!\u0014\r\u0001\u0004\ty\u0005\u0003\u0004z\u0019\u0001\u0007\u0011q\u0013\t\u0006\u0003\u0017[\u0014\u0011\u0014\t\u0004_\u0005mE!B?\r\u0005\u0004\u0011\u0015a\u00032j]>\u0004x+\u001b;i\u000bZ,\u0002\"!)\u0002@\u0006]\u0016q\u0016\u000b\u0005\u0003G\u000bY\u000b\u0006\u0003\u0002&\u0006eF\u0003BAT\u0003c\u0003R!!+<\u0003[s1aLAV\u0011\u0015\tT\u00021\u00013!\ry\u0013q\u0016\u0003\u0006\u00036\u0011\rA\u0011\u0005\u000776\u0001\r!a-\u0011\u000b\u0005%6(!.\u0011\u0007=\n9\fB\u0003`\u001b\t\u0007!\t\u0003\u0004z\u001b\u0001\u0007\u00111\u0018\t\u0006\u0003S[\u0014Q\u0018\t\u0004_\u0005}F!B?\u000e\u0005\u0004\u0011\u0015\u0001\u0004:cS:|\u0007oV5uQ\u00163X\u0003CAc\u0003G\fY.a5\u0015\t\u0005\u001d\u0017q\u001a\u000b\u0005\u0003\u0013\fi\u000e\u0006\u0003\u0002L\u0006U\u0007#BAgw\u0005EgbA\u0018\u0002P\")\u0011G\u0004a\u0001eA\u0019q&a5\u0005\u000b\u0005s!\u0019\u0001\"\t\rms\u0001\u0019AAl!\u0015\timOAm!\ry\u00131\u001c\u0003\u0006?:\u0011\rA\u0011\u0005\b\u0003#q\u0001\u0019AAp!\u0015\timOAq!\ry\u00131\u001d\u0003\u0006{:\u0011\rAQ\u0001\u000eE&tw\u000e],ji\"d\u0015N\u001a;\u0016\u0011\u0005%(q\u0001B\b\u0003s$B!a;\u0002vR!\u0011Q\u001eB\t)\u0011\tyO!\u0003\u0015\t\u0005E\u00181 \t\u0006\u0003g\\\u0014q\u001f\b\u0004_\u0005U\b\"B\u0019\u0010\u0001\u0004\u0011\u0004cA\u0018\u0002z\u0012)\u0011i\u0004b\u0001\u0005\"I\u0011Q`\b\u0002\u0002\u0003\u000f\u0011q`\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004CBAz\u0005\u0003\u0011)!C\u0002\u0003\u0004u\u00121bV3bWRK\b/\u001a+bOB\u0019qFa\u0002\u0005\u000bu|!\u0019\u0001\"\t\r)|\u0001\u0019\u0001B\u0006!\u0015\t\u0019p\u000fB\u0007!\ry#q\u0002\u0003\u0006?>\u0011\rA\u0011\u0005\u0007s>\u0001\rAa\u0005\u0011\u000b\u0005M8H!\u0002\u0002#\tLgn\u001c9XSRD7+\u001a7g\u0019&4G/\u0006\u0005\u0003\u001a\tE\"q\u0007B\u0014)\u0011\u0011YBa\t\u0015\t\tu!1\u0007\u000b\u0005\u0005?\u0011I\u0003E\u0003\u0003\"m\u0012)CD\u00020\u0005GAQ!\r\tA\u0002I\u00022a\fB\u0014\t\u0015\t\u0005C1\u0001C\u0011%\u0011Y\u0003EA\u0001\u0002\b\u0011i#\u0001\u0006fm&$WM\\2fII\u0002bA!\t\u0003\u0002\t=\u0002cA\u0018\u00032\u0011)Q\u0010\u0005b\u0001\u0005\"1\u0011\u0010\u0005a\u0001\u0005k\u0001RA!\t<\u0005_!Qa\u0018\tC\u0002\t\u000bAA\u001a7jaV1!Q\bB)\u0005\u0013\"BAa\u0010\u0003FQ!!\u0011\tB&!\u0015\u0011\u0019e\u000fB$\u001d\ry#Q\t\u0005\u0006cE\u0001\rA\r\t\u0004_\t%C!B!\u0012\u0005\u0004\u0011\u0005BB=\u0012\u0001\u0004\u0011i\u0005E\u0003\u0003Dm\u0012y\u0005E\u00020\u0005#\"Q!`\tC\u0002\t\u000ba!\u001e8qC\u000e\\WC\u0002B,\u0005o\u0012\t\t\u0006\u0003\u0003Z\t\u0015\u0004c\u0002\u0011\u0003\\\t}#qL\u0005\u0004\u0005;\n#A\u0002+va2,'\u0007\u0005\u0003\u0003b\t-d\u0002\u0002B2\u0005Or1a\fB3\u0011\u0015\t$\u00031\u00013\u0013\r\u0011I'O\u0001\tk:Lg/\u001a:tK&!!Q\u000eB8\u0005\u0011!&/Z3\n\t\tE$1\u000f\u0002\u0006)J,Wm\u001d\u0006\u0004\u0005k:\u0014aA1qS\u00129!\u0011\u0010\nC\u0002\tm$!\u0001+\u0016\u0007\t\u0013i\bB\u0004\u0003\u0000\t]$\u0019\u0001\"\u0003\t}#C%\r\u0003\u0006{J\u0011\rAQ\u0001\u0010k:\u0004\u0018mY6XSRDw.\u001e;FmR!!q\u0011BG!\u0011\u0011IIa\u001b\u000f\t\t-%q\r\b\u0004_\t5\u0005\"B\u0019\u0014\u0001\u0004\u0011\u0014A\u00044j]\u0012lU\r\u001e5pI:\u000bW.\u001a\u000b\u0005\u0005'\u0013I\n\u0005\u0003\u0003\u0016\nme\u0002\u0002BL\u0005Or1a\fBM\u0011\u0015\tD\u00031\u00013\u0013\u0011\u0011iJa(\u0003\u0011Q+'/\u001c(b[\u0016LAA!)\u0003t\t)a*Y7fg\u0006iq\u000e]3sCR|'OT1nKN,\"Aa*\u0011\u0011\u0005E#\u0011VA(\u0003\u001fJAAa+\u0002d\t\u0019Q*\u00199"
)
public interface Ops {
   // $FF: synthetic method
   static Exprs.Expr unop$(final Ops $this, final Context c) {
      return $this.unop(c);
   }

   default Exprs.Expr unop(final Context c) {
      Tuple2 var4 = this.unpack(c);
      if (var4 != null) {
         Trees.TreeApi ev = (Trees.TreeApi)var4._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var4._2();
         Tuple2 var2 = new Tuple2(ev, lhs);
         Trees.TreeApi ev = (Trees.TreeApi)var2._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var2._2();
         Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Select().apply(ev, (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs}))));
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by unop in Ops.scala:55:12");
               $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
               return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$1() {
            }
         }

         return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$1()));
      } else {
         throw new MatchError(var4);
      }
   }

   // $FF: synthetic method
   static Exprs.Expr unop0$(final Ops $this, final Context c) {
      return $this.unop0(c);
   }

   default Exprs.Expr unop0(final Context c) {
      Tuple2 var4 = this.unpack(c);
      if (var4 != null) {
         Trees.TreeApi ev = (Trees.TreeApi)var4._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var4._2();
         Tuple2 var2 = new Tuple2(ev, lhs);
         Trees.TreeApi ev = (Trees.TreeApi)var2._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var2._2();
         Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Select().apply(ev, (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs}))));
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by unop0 in Ops.scala:66:13");
               $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
               return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$2() {
            }
         }

         return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$2()));
      } else {
         throw new MatchError(var4);
      }
   }

   // $FF: synthetic method
   static Exprs.Expr unopWithEv$(final Ops $this, final Context c, final Exprs.Expr ev) {
      return $this.unopWithEv(c, ev);
   }

   default Exprs.Expr unopWithEv(final Context c, final Exprs.Expr ev) {
      Trees.TreeApi lhs = this.unpackWithoutEv(c);
      Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Select().apply(ev.tree(), (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs}))));
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator1$3 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by unopWithEv in Ops.scala:95:22");
            $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
            return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$3() {
         }
      }

      return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$3()));
   }

   // $FF: synthetic method
   static Exprs.Expr unopWithEv2$(final Ops $this, final Context c, final Exprs.Expr ev1) {
      return $this.unopWithEv2(c, ev1);
   }

   default Exprs.Expr unopWithEv2(final Context c, final Exprs.Expr ev1) {
      Tuple2 var6 = this.unpack(c);
      if (var6 != null) {
         Trees.TreeApi ev = (Trees.TreeApi)var6._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var6._2();
         Tuple2 var3 = new Tuple2(ev, lhs);
         Trees.TreeApi ev = (Trees.TreeApi)var3._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var3._2();
         Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Apply().apply(c.universe().Select().apply(ev, (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs})))), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{ev1.tree()}))));
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $typecreator1$4 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by unopWithEv2 in Ops.scala:125:24");
               $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
               return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$4() {
            }
         }

         return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$4()));
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   static Exprs.Expr binop$(final Ops $this, final Context c, final Exprs.Expr rhs) {
      return $this.binop(c, rhs);
   }

   default Exprs.Expr binop(final Context c, final Exprs.Expr rhs) {
      Tuple2 var6 = this.unpack(c);
      if (var6 != null) {
         Trees.TreeApi ev = (Trees.TreeApi)var6._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var6._2();
         Tuple2 var3 = new Tuple2(ev, lhs);
         Trees.TreeApi ev = (Trees.TreeApi)var3._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var3._2();
         Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Select().apply(ev, (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs, rhs.tree()}))));
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $typecreator1$5 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by binop in Ops.scala:155:16");
               $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
               return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$5() {
            }
         }

         return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$5()));
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   static Exprs.Expr rbinop$(final Ops $this, final Context c, final Exprs.Expr lhs) {
      return $this.rbinop(c, lhs);
   }

   default Exprs.Expr rbinop(final Context c, final Exprs.Expr lhs) {
      Tuple2 var6 = this.unpack(c);
      if (var6 != null) {
         Trees.TreeApi ev = (Trees.TreeApi)var6._1();
         Trees.TreeApi rhs = (Trees.TreeApi)var6._2();
         Tuple2 var3 = new Tuple2(ev, rhs);
         Trees.TreeApi ev = (Trees.TreeApi)var3._1();
         Trees.TreeApi rhs = (Trees.TreeApi)var3._2();
         Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Select().apply(ev, (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs.tree(), rhs}))));
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $typecreator1$6 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by rbinop in Ops.scala:184:17");
               $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
               return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$6() {
            }
         }

         return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$6()));
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   static Exprs.Expr unopWithScalar$(final Ops $this, final Context c) {
      return $this.unopWithScalar(c);
   }

   default Exprs.Expr unopWithScalar(final Context c) {
      return this.handleUnopWithChild(c, "scalar");
   }

   // $FF: synthetic method
   static Exprs.Expr unopWithScalar0$(final Ops $this, final Context c) {
      return $this.unopWithScalar0(c);
   }

   default Exprs.Expr unopWithScalar0(final Context c) {
      return this.handleUnopWithChild(c, "scalar");
   }

   // $FF: synthetic method
   static Exprs.Expr handleUnopWithChild$(final Ops $this, final Context c, final String childName) {
      return $this.handleUnopWithChild(c, childName);
   }

   default Exprs.Expr handleUnopWithChild(final Context c, final String childName) {
      Tuple2 var5 = this.unpack(c);
      if (var5 != null) {
         Trees.TreeApi ev = (Trees.TreeApi)var5._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var5._2();
         Tuple2 var3 = new Tuple2(ev, lhs);
         Trees.TreeApi ev = (Trees.TreeApi)var3._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var3._2();
         Trees.SelectApi child = c.universe().Select().apply(ev, (Names.NameApi)c.universe().TermName().apply(childName));
         Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Select().apply(child, (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs}))));
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $typecreator1$7 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by handleUnopWithChild in Ops.scala:196:27");
               $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
               return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$7() {
            }
         }

         return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$7()));
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   static Exprs.Expr binopWithScalar$(final Ops $this, final Context c, final Exprs.Expr rhs) {
      return $this.binopWithScalar(c, rhs);
   }

   default Exprs.Expr binopWithScalar(final Context c, final Exprs.Expr rhs) {
      return this.handleBinopWithChild(c, rhs, "scalar");
   }

   // $FF: synthetic method
   static Exprs.Expr handleBinopWithChild$(final Ops $this, final Context c, final Exprs.Expr rhs, final String childName) {
      return $this.handleBinopWithChild(c, rhs, childName);
   }

   default Exprs.Expr handleBinopWithChild(final Context c, final Exprs.Expr rhs, final String childName) {
      Tuple2 var7 = this.unpack(c);
      if (var7 != null) {
         Trees.TreeApi ev = (Trees.TreeApi)var7._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var7._2();
         Tuple2 var4 = new Tuple2(ev, lhs);
         Trees.TreeApi ev = (Trees.TreeApi)var4._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var4._2();
         Trees.SelectApi child = c.universe().Select().apply(ev, (Names.NameApi)c.universe().TermName().apply(childName));
         Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Select().apply(child, (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs, rhs.tree()}))));
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $typecreator1$8 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by handleBinopWithChild in Ops.scala:234:31");
               $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
               return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$8() {
            }
         }

         return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$8()));
      } else {
         throw new MatchError(var7);
      }
   }

   // $FF: synthetic method
   static Exprs.Expr binopWithEv$(final Ops $this, final Context c, final Exprs.Expr rhs, final Exprs.Expr ev) {
      return $this.binopWithEv(c, rhs, ev);
   }

   default Exprs.Expr binopWithEv(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev) {
      Trees.TreeApi lhs = this.unpackWithoutEv(c);
      Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Select().apply(ev.tree(), (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs, rhs.tree()}))));
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator1$9 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by binopWithEv in Ops.scala:264:26");
            $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
            return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$9() {
         }
      }

      return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$9()));
   }

   // $FF: synthetic method
   static Exprs.Expr rbinopWithEv$(final Ops $this, final Context c, final Exprs.Expr lhs, final Exprs.Expr ev) {
      return $this.rbinopWithEv(c, lhs, ev);
   }

   default Exprs.Expr rbinopWithEv(final Context c, final Exprs.Expr lhs, final Exprs.Expr ev) {
      Trees.TreeApi rhs = this.unpackWithoutEv(c);
      Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Select().apply(ev.tree(), (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs.tree(), rhs}))));
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator1$10 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by rbinopWithEv in Ops.scala:293:27");
            $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
            return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$10() {
         }
      }

      return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$10()));
   }

   // $FF: synthetic method
   static Exprs.Expr binopWithLift$(final Ops $this, final Context c, final Exprs.Expr rhs, final Exprs.Expr ev1, final TypeTags.WeakTypeTag evidence$1) {
      return $this.binopWithLift(c, rhs, ev1, evidence$1);
   }

   default Exprs.Expr binopWithLift(final Context c, final Exprs.Expr rhs, final Exprs.Expr ev1, final TypeTags.WeakTypeTag evidence$1) {
      Tuple2 var10 = this.unpack(c);
      if (var10 != null) {
         Trees.TreeApi ev0 = (Trees.TreeApi)var10._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var10._2();
         Tuple2 var5 = new Tuple2(ev0, lhs);
         Trees.TreeApi ev0 = (Trees.TreeApi)var5._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var5._2();
         Names.NameApi typeName = c.universe().weakTypeOf(evidence$1).typeSymbol().name();
         Trees.ApplyApi rhs1 = c.universe().Apply().apply(c.universe().Select().apply(ev1.tree(), (Names.NameApi)c.universe().TermName().apply((new StringBuilder(4)).append("from").append(typeName).toString())), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{rhs.tree()}))));
         Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Select().apply(ev0, (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs, rhs1}))));
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $typecreator1$11 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by binopWithLift in Ops.scala:325:43");
               $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
               return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$11() {
            }
         }

         return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$11()));
      } else {
         throw new MatchError(var10);
      }
   }

   // $FF: synthetic method
   static Exprs.Expr binopWithSelfLift$(final Ops $this, final Context c, final Exprs.Expr rhs, final TypeTags.WeakTypeTag evidence$2) {
      return $this.binopWithSelfLift(c, rhs, evidence$2);
   }

   default Exprs.Expr binopWithSelfLift(final Context c, final Exprs.Expr rhs, final TypeTags.WeakTypeTag evidence$2) {
      Tuple2 var8 = this.unpack(c);
      if (var8 != null) {
         Trees.TreeApi ev0 = (Trees.TreeApi)var8._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var8._2();
         Tuple2 var4 = new Tuple2(ev0, lhs);
         Trees.TreeApi ev0 = (Trees.TreeApi)var4._1();
         Trees.TreeApi lhs = (Trees.TreeApi)var4._2();
         Names.NameApi typeName = c.universe().weakTypeOf(evidence$2).typeSymbol().name();
         Trees.ApplyApi rhs1 = c.universe().Apply().apply(c.universe().Select().apply(ev0, (Names.NameApi)c.universe().TermName().apply((new StringBuilder(4)).append("from").append(typeName).toString())), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{rhs.tree()}))));
         Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Select().apply(ev0, (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs, rhs1}))));
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $typecreator1$12 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by binopWithSelfLift in Ops.scala:356:47");
               $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
               return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$12() {
            }
         }

         return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$12()));
      } else {
         throw new MatchError(var8);
      }
   }

   // $FF: synthetic method
   static Exprs.Expr flip$(final Ops $this, final Context c, final Exprs.Expr rhs) {
      return $this.flip(c, rhs);
   }

   default Exprs.Expr flip(final Context c, final Exprs.Expr rhs) {
      Trees.TreeApi lhs = this.unpackWithoutEv(c);
      Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().Select().apply(rhs.tree(), (Names.NameApi)this.findMethodName(c)), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{lhs}))));
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator1$13 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            Internals.FreeTypeSymbolApi free$R1 = $u.internal().reificationSupport().newFreeType("R", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by flip in Ops.scala:382:15");
            $u.internal().reificationSupport().setInfo(free$R1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
            return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$R1, scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$13() {
         }
      }

      return c.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$13()));
   }

   // $FF: synthetic method
   static Tuple2 unpack$(final Ops $this, final Context c) {
      return $this.unpack(c);
   }

   default Tuple2 unpack(final Context c) {
      Trees.TreeApi var3 = c.prefix().tree();
      if (var3 != null) {
         Option var4 = c.universe().ApplyTag().unapply(var3);
         if (!var4.isEmpty()) {
            Trees.ApplyApi var5 = (Trees.ApplyApi)var4.get();
            if (var5 != null) {
               Option var6 = c.universe().Apply().unapply(var5);
               if (!var6.isEmpty()) {
                  Trees.TreeApi var7 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                  List var8 = (List)((Tuple2)var6.get())._2();
                  if (var7 != null) {
                     Option var9 = c.universe().ApplyTag().unapply(var7);
                     if (!var9.isEmpty()) {
                        Trees.ApplyApi var10 = (Trees.ApplyApi)var9.get();
                        if (var10 != null) {
                           Option var11 = c.universe().Apply().unapply(var10);
                           if (!var11.isEmpty()) {
                              Trees.TreeApi var12 = (Trees.TreeApi)((Tuple2)var11.get())._1();
                              List var13 = (List)((Tuple2)var11.get())._2();
                              if (var12 != null) {
                                 Option var14 = c.universe().TypeApplyTag().unapply(var12);
                                 if (!var14.isEmpty()) {
                                    Trees.TypeApplyApi var15 = (Trees.TypeApplyApi)var14.get();
                                    if (var15 != null) {
                                       Option var16 = c.universe().TypeApply().unapply(var15);
                                       if (!var16.isEmpty() && var13 != null) {
                                          SeqOps var17 = .MODULE$.List().unapplySeq(var13);
                                          if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var17) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var17)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var17), 1) == 0) {
                                             Trees.TreeApi x = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var17), 0);
                                             if (var8 != null) {
                                                SeqOps var19 = .MODULE$.List().unapplySeq(var8);
                                                if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var19) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var19)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var19), 1) == 0) {
                                                   Trees.TreeApi ev = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var19), 0);
                                                   Tuple2 var2 = new Tuple2(ev, x);
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
            }
         }
      }

      throw c.abort(c.enclosingPosition(), scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Cannot extract subject of operator (tree = %s)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var3})));
   }

   // $FF: synthetic method
   static Trees.TreeApi unpackWithoutEv$(final Ops $this, final Context c) {
      return $this.unpackWithoutEv(c);
   }

   default Trees.TreeApi unpackWithoutEv(final Context c) {
      Trees.TreeApi var3 = c.prefix().tree();
      if (var3 != null) {
         Option var4 = c.universe().ApplyTag().unapply(var3);
         if (!var4.isEmpty()) {
            Trees.ApplyApi var5 = (Trees.ApplyApi)var4.get();
            if (var5 != null) {
               Option var6 = c.universe().Apply().unapply(var5);
               if (!var6.isEmpty()) {
                  Trees.TreeApi var7 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                  List var8 = (List)((Tuple2)var6.get())._2();
                  if (var7 != null) {
                     Option var9 = c.universe().TypeApplyTag().unapply(var7);
                     if (!var9.isEmpty()) {
                        Trees.TypeApplyApi var10 = (Trees.TypeApplyApi)var9.get();
                        if (var10 != null) {
                           Option var11 = c.universe().TypeApply().unapply(var10);
                           if (!var11.isEmpty() && var8 != null) {
                              SeqOps var12 = .MODULE$.List().unapplySeq(var8);
                              if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var12) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var12)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var12), 1) == 0) {
                                 Trees.TreeApi lhs = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var12), 0);
                                 return lhs;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      throw c.abort(c.enclosingPosition(), scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Cannot extract subject of operator (tree = %s)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var3})));
   }

   // $FF: synthetic method
   static Names.TermNameApi findMethodName$(final Ops $this, final Context c) {
      return $this.findMethodName(c);
   }

   default Names.TermNameApi findMethodName(final Context c) {
      String s = c.macroApplication().symbol().name().toString();
      return c.universe().TermName().apply((String)this.operatorNames().getOrElse(s, () -> s));
   }

   Map operatorNames();

   static void $init$(final Ops $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
