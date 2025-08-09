package spire.macros;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Tuple2;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Internals;
import scala.reflect.api.Mirror;
import scala.reflect.api.Names;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.BoxesRunTime;
import spire.macros.compat.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001df!\u0002\t\u0012\u0003\u00031\u0002\"B\u000f\u0001\t\u0003q\u0002bB\u0011\u0001\u0005\u00045\tA\t\u0005\u0006c\u0001!\tA\r\u0005\b+\u0002\t\n\u0011\"\u0001W\u0011\u0015\u0019\u0007\u0001\"\u0001e\u0011\u001di\u0007!%A\u0005\u00029Dq\u0001\u001d\u0001\u0012\u0002\u0013\u0005\u0011\u000fC\u0003t\u0001\u0011\u0005A\u000fC\u0005\u0002 \u0001\t\n\u0011\"\u0001\u0002\"!I\u0011Q\u0005\u0001\u0012\u0002\u0013\u0005\u0011q\u0005\u0005\b\u0003W\u0001A\u0011AA\u0017\u0011%\t)\u0005AI\u0001\n\u0003\t9\u0005C\u0004\u0002L\u0001!\t!!\u0014\t\u000f\u0005E\u0004\u0001\"\u0001\u0002t!9\u00111\u0014\u0001\u0005\u0002\u0005u%aB!vi>|\u0005o\u001d\u0006\u0003%M\ta!\\1de>\u001c(\"\u0001\u000b\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001a\u0006\t\u00031mi\u0011!\u0007\u0006\u00025\u0005)1oY1mC&\u0011A$\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005y\u0002C\u0001\u0011\u0001\u001b\u0005\t\u0012!A2\u0016\u0003\r\u0002\"\u0001\n\u0018\u000f\u0005\u0015bcB\u0001\u0014,\u001d\t9#&D\u0001)\u0015\tIS#\u0001\u0004=e>|GOP\u0005\u0002)%\u0011!cE\u0005\u0003[E\taaY8na\u0006$\u0018BA\u00181\u0005\u001d\u0019uN\u001c;fqRT!!L\t\u0002\tUtw\u000e]\u000b\u0003g\u0001#2\u0001N%T!\r)tG\u0010\b\u0003m\ti\u0011\u0001A\u0005\u0003qe\u0012A!\u0012=qe&\u0011!h\u000f\u0002\b\u00032L\u0017m]3t\u0015\t\u0011BH\u0003\u0002>3\u00059!/\u001a4mK\u000e$\bCA A\u0019\u0001!Q!Q\u0002C\u0002\t\u0013\u0011!Q\t\u0003\u0007\u001a\u0003\"\u0001\u0007#\n\u0005\u0015K\"a\u0002(pi\"Lgn\u001a\t\u00031\u001dK!\u0001S\r\u0003\u0007\u0005s\u0017\u0010C\u0003K\u0007\u0001\u00071*\u0001\u0003oC6,\u0007C\u0001'Q\u001d\tie\n\u0005\u0002(3%\u0011q*G\u0001\u0007!J,G-\u001a4\n\u0005E\u0013&AB*ue&twM\u0003\u0002P3!9Ak\u0001I\u0001\u0002\u0004Y\u0015!\u0001=\u0002\u001dUtw\u000e\u001d\u0013eK\u001a\fW\u000f\u001c;%eU\u0011qKY\u000b\u00021*\u00121*W\u0016\u00025B\u00111\fY\u0007\u00029*\u0011QLX\u0001\nk:\u001c\u0007.Z2lK\u0012T!aX\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002b9\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b\u0005#!\u0019\u0001\"\u0002\u000b\tLgn\u001c9\u0016\u0005\u0015DG\u0003\u00024jU.\u00042!N\u001ch!\ty\u0004\u000eB\u0003B\u000b\t\u0007!\tC\u0003K\u000b\u0001\u00071\nC\u0004U\u000bA\u0005\t\u0019A&\t\u000f1,\u0001\u0013!a\u0001\u0017\u0006\t\u00110A\bcS:|\u0007\u000f\n3fM\u0006,H\u000e\u001e\u00133+\t9v\u000eB\u0003B\r\t\u0007!)A\bcS:|\u0007\u000f\n3fM\u0006,H\u000e\u001e\u00134+\t9&\u000fB\u0003B\u000f\t\u0007!)A\u0006cS:|\u0007oU3be\u000eDWCA;})\u001d1\u0018QAA\u000e\u0003;!\"a^?\u0011\u0007aA(0\u0003\u0002z3\t1q\n\u001d;j_:\u00042!N\u001c|!\tyD\u0010B\u0003B\u0011\t\u0007!\tC\u0004\u007f\u0011\u0005\u0005\t9A@\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u00036\u0003\u0003Y\u0018bAA\u0002s\tYq+Z1l)f\u0004X\rV1h\u0011\u001d\t9\u0001\u0003a\u0001\u0003\u0013\tQA\\1nKN\u0004R!a\u0003\u0002\u0016-sA!!\u0004\u0002\u00129\u0019q%a\u0004\n\u0003iI1!a\u0005\u001a\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u0006\u0002\u001a\t!A*[:u\u0015\r\t\u0019\"\u0007\u0005\b)\"\u0001\n\u00111\u0001L\u0011\u001da\u0007\u0002%AA\u0002-\u000bQCY5o_B\u001cV-\u0019:dQ\u0012\"WMZ1vYR$#'F\u0002X\u0003G!Q!Q\u0005C\u0002\t\u000bQCY5o_B\u001cV-\u0019:dQ\u0012\"WMZ1vYR$3'F\u0002X\u0003S!Q!\u0011\u0006C\u0002\t\u000b!\"\u001e8paN+\u0017M]2i+\u0011\ty#!\u000f\u0015\r\u0005E\u0012\u0011IA\")\u0011\t\u0019$a\u000f\u0011\taA\u0018Q\u0007\t\u0005k]\n9\u0004E\u0002@\u0003s!Q!Q\u0006C\u0002\tC\u0011\"!\u0010\f\u0003\u0003\u0005\u001d!a\u0010\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u00036\u0003\u0003\t9\u0004C\u0004\u0002\b-\u0001\r!!\u0003\t\u000fQ[\u0001\u0013!a\u0001\u0017\u0006!RO\\8q'\u0016\f'o\u00195%I\u00164\u0017-\u001e7uII*2aVA%\t\u0015\tEB1\u0001C\u0003)A\u0017m]'fi\"|G\rM\u000b\u0007\u0003\u001f\n\t'a\u001b\u0015\t\u0005E\u0013q\u000e\u000b\u0007\u0003'\nI&a\u0019\u0011\u0007a\t)&C\u0002\u0002Xe\u0011qAQ8pY\u0016\fg\u000eC\u0005\u0002\\5\t\t\u0011q\u0001\u0002^\u0005QQM^5eK:\u001cW\rJ\u001a\u0011\u000bU\n\t!a\u0018\u0011\u0007}\n\t\u0007B\u0003B\u001b\t\u0007!\tC\u0005\u0002f5\t\t\u0011q\u0001\u0002h\u0005QQM^5eK:\u001cW\r\n\u001b\u0011\u000bU\n\t!!\u001b\u0011\u0007}\nY\u0007\u0002\u0004\u0002n5\u0011\rA\u0011\u0002\u0002\u0005\")!*\u0004a\u0001\u0017\u0006Q\u0001.Y:NKRDw\u000eZ\u0019\u0016\u0011\u0005U\u0014\u0011QAF\u0003+#B!a\u001e\u0002\u001aRA\u00111KA=\u0003\u0007\u000bi\tC\u0005\u0002|9\t\t\u0011q\u0001\u0002~\u0005QQM^5eK:\u001cW\rJ\u001b\u0011\u000bU\n\t!a \u0011\u0007}\n\t\tB\u0003B\u001d\t\u0007!\tC\u0005\u0002\u0006:\t\t\u0011q\u0001\u0002\b\u0006QQM^5eK:\u001cW\r\n\u001c\u0011\u000bU\n\t!!#\u0011\u0007}\nY\t\u0002\u0004\u0002n9\u0011\rA\u0011\u0005\n\u0003\u001fs\u0011\u0011!a\u0002\u0003#\u000b!\"\u001a<jI\u0016t7-\u001a\u00138!\u0015)\u0014\u0011AAJ!\ry\u0014Q\u0013\u0003\u0007\u0003/s!\u0019\u0001\"\u0003\u0003\rCQA\u0013\bA\u0002-\u000bABZ1jY\u0016$7+Z1sG\"$b!a(\u0002\"\u0006\r\u0006cA\u001b8\u0007\")!j\u0004a\u0001\u0017\"1\u0011QU\bA\u0002-\u000b!a\u001c9"
)
public abstract class AutoOps {
   public abstract Context c();

   public Exprs.Expr unop(final String name, final String x) {
      Context var10000 = this.c();
      Trees.SelectApi var10001 = this.c().universe().Select().apply(this.c().universe().Ident().apply((Names.NameApi).MODULE$.termName(this.c(), x)), (Names.NameApi).MODULE$.termName(this.c(), name));
      Universe $u = this.c().universe();
      Mirror $m = this.c().universe().rootMirror();

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            Internals.FreeTypeSymbolApi free$A1 = $u.internal().reificationSupport().newFreeType("A", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by unop in Auto.scala:61:12");
            $u.internal().reificationSupport().setInfo(free$A1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
            return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$A1, scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$1() {
         }
      }

      return var10000.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$1()));
   }

   public String unop$default$2() {
      return "x";
   }

   public Exprs.Expr binop(final String name, final String x, final String y) {
      Context var10000 = this.c();
      Trees.ApplyApi var10001 = this.c().universe().Apply().apply(this.c().universe().Select().apply(this.c().universe().Ident().apply((Names.NameApi).MODULE$.termName(this.c(), x)), (Names.NameApi).MODULE$.termName(this.c(), name)), (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{this.c().universe().Ident().apply((Names.NameApi).MODULE$.termName(this.c(), y))}))));
      Universe $u = this.c().universe();
      Mirror $m = this.c().universe().rootMirror();

      final class $typecreator1$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            Internals.FreeTypeSymbolApi free$A1 = $u.internal().reificationSupport().newFreeType("A", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by binop in Auto.scala:64:13");
            $u.internal().reificationSupport().setInfo(free$A1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
            return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$A1, scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$2() {
         }
      }

      return var10000.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$2()));
   }

   public String binop$default$2() {
      return "x";
   }

   public String binop$default$3() {
      return "y";
   }

   public Option binopSearch(final List names, final String x, final String y, final TypeTags.WeakTypeTag evidence$1) {
      return names.find((name) -> BoxesRunTime.boxToBoolean($anonfun$binopSearch$1(this, evidence$1, name))).map((x$1) -> this.binop(x$1, x, y));
   }

   public String binopSearch$default$2() {
      return "x";
   }

   public String binopSearch$default$3() {
      return "y";
   }

   public Option unopSearch(final List names, final String x, final TypeTags.WeakTypeTag evidence$2) {
      return names.find((name) -> BoxesRunTime.boxToBoolean($anonfun$unopSearch$1(this, evidence$2, name))).map((x$2) -> this.unop(x$2, x));
   }

   public String unopSearch$default$2() {
      return "x";
   }

   public boolean hasMethod0(final String name, final TypeTags.WeakTypeTag evidence$3, final TypeTags.WeakTypeTag evidence$4) {
      Types.TypeApi tpeA = this.c().weakTypeTag(evidence$3).tpe();
      Types.TypeApi tpeB = this.c().weakTypeTag(evidence$4).tpe();
      return tpeA.members().exists((m) -> BoxesRunTime.boxToBoolean($anonfun$hasMethod0$1(this, name, tpeB, m)));
   }

   public boolean hasMethod1(final String name, final TypeTags.WeakTypeTag evidence$5, final TypeTags.WeakTypeTag evidence$6, final TypeTags.WeakTypeTag evidence$7) {
      Types.TypeApi tpeA = this.c().weakTypeTag(evidence$5).tpe();
      Types.TypeApi tpeB = this.c().weakTypeTag(evidence$6).tpe();
      Types.TypeApi tpeC = this.c().weakTypeTag(evidence$7).tpe();
      return tpeA.members().exists((m) -> BoxesRunTime.boxToBoolean($anonfun$hasMethod1$1(this, name, tpeB, tpeC, m)));
   }

   public Exprs.Expr failedSearch(final String name, final String op) {
      throw this.c().abort(this.c().enclosingPosition(), scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Couldn't find matching method for op %s (%s)."), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{name, op})));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$binopSearch$1(final AutoOps $this, final TypeTags.WeakTypeTag evidence$1$1, final String name) {
      return $this.hasMethod1(name, evidence$1$1, evidence$1$1, evidence$1$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$unopSearch$1(final AutoOps $this, final TypeTags.WeakTypeTag evidence$2$1, final String name) {
      return $this.hasMethod0(name, evidence$2$1, evidence$2$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasMethod0$1(final AutoOps $this, final String name$1, final Types.TypeApi tpeB$1, final Symbols.SymbolApi m) {
      boolean var14;
      if (m.isMethod() && m.isPublic()) {
         label52: {
            String var10000 = m.name().encodedName().toString();
            if (var10000 == null) {
               if (name$1 != null) {
                  break label52;
               }
            } else if (!var10000.equals(name$1)) {
               break label52;
            }

            boolean var4;
            label41: {
               Types.TypeApi ret;
               label40: {
                  Types.TypeApi var6 = m.typeSignature();
                  if (var6 != null) {
                     Option var7 = $this.c().universe().MethodTypeTag().unapply(var6);
                     if (!var7.isEmpty()) {
                        Types.MethodTypeApi var8 = (Types.MethodTypeApi)var7.get();
                        if (var8 != null) {
                           Option var9 = $this.c().universe().MethodType().unapply(var8);
                           if (!var9.isEmpty()) {
                              List var10 = (List)((Tuple2)var9.get())._1();
                              ret = (Types.TypeApi)((Tuple2)var9.get())._2();
                              Nil var13 = scala.package..MODULE$.Nil();
                              if (var13 == null) {
                                 if (var10 == null) {
                                    break label40;
                                 }
                              } else if (var13.equals(var10)) {
                                 break label40;
                              }
                           }
                        }
                     }
                  }

                  var4 = false;
                  break label41;
               }

               var4 = ret.$eq$colon$eq(tpeB$1);
            }

            if (var4) {
               var14 = true;
               return var14;
            }
         }
      }

      var14 = false;
      return var14;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasMethod1$1(final AutoOps $this, final String name$2, final Types.TypeApi tpeB$2, final Types.TypeApi tpeC$1, final Symbols.SymbolApi m) {
      boolean var15;
      if (m.isMethod() && m.isPublic()) {
         label61: {
            String var10000 = m.name().encodedName().toString();
            if (var10000 == null) {
               if (name$2 != null) {
                  break label61;
               }
            } else if (!var10000.equals(name$2)) {
               break label61;
            }

            boolean var5;
            label50: {
               Types.TypeApi var7 = m.typeSignature();
               if (var7 != null) {
                  Option var8 = $this.c().universe().MethodTypeTag().unapply(var7);
                  if (!var8.isEmpty()) {
                     Types.MethodTypeApi var9 = (Types.MethodTypeApi)var8.get();
                     if (var9 != null) {
                        Option var10 = $this.c().universe().MethodType().unapply(var9);
                        if (!var10.isEmpty()) {
                           List var11 = (List)((Tuple2)var10.get())._1();
                           Types.TypeApi ret = (Types.TypeApi)((Tuple2)var10.get())._2();
                           if (var11 != null) {
                              SeqOps var13 = scala.package..MODULE$.List().unapplySeq(var11);
                              if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var13) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 1) == 0) {
                                 Symbols.SymbolApi param = (Symbols.SymbolApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 0);
                                 var5 = param.typeSignature().$eq$colon$eq(tpeB$2) && ret.$eq$colon$eq(tpeC$1);
                                 break label50;
                              }
                           }
                        }
                     }
                  }
               }

               var5 = false;
            }

            if (var5) {
               var15 = true;
               return var15;
            }
         }
      }

      var15 = false;
      return var15;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
