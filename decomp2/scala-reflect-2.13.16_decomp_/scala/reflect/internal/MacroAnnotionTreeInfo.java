package scala.reflect.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple7;
import scala.Tuple9;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t%a!\u0003\u0014(!\u0003\r\tALA\u007f\u0011\u0015\u0019\u0004\u0001\"\u00015\u0011\u0015A\u0004\u0001\"\u0001:\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u0015\u0001\u0006\u0001\"\u0001R\r\u0011\u0019\u0006\u0001\u0011+\t\u0011\u0011,!Q3A\u0005\u0002\u0015D\u0001\"[\u0003\u0003\u0012\u0003\u0006IA\u001a\u0005\tU\u0016\u0011)\u001a!C\u0001K\"A1.\u0002B\tB\u0003%a\r\u0003\u0005m\u000b\tU\r\u0011\"\u0001f\u0011!iWA!E!\u0002\u00131\u0007\"\u00028\u0006\t\u0003y\u0007b\u0002;\u0006\u0003\u0003%\t!\u001e\u0005\bs\u0016\t\n\u0011\"\u0001{\u0011!\tI!BI\u0001\n\u0003Q\b\u0002CA\u0006\u000bE\u0005I\u0011\u0001>\t\u0013\u00055Q!!A\u0005B\u0005=\u0001\"CA\u0011\u000b\u0005\u0005I\u0011AA\u0012\u0011%\t)#BA\u0001\n\u0003\t9\u0003C\u0005\u00024\u0015\t\t\u0011\"\u0011\u00026!I\u00111I\u0003\u0002\u0002\u0013\u0005\u0011Q\t\u0005\n\u0003\u0013*\u0011\u0011!C!\u0003\u0017B\u0011\"a\u0014\u0006\u0003\u0003%\t%!\u0015\t\u0013\u0005MS!!A\u0005B\u0005U\u0003\"CA,\u000b\u0005\u0005I\u0011IA-\u000f%\ti\u0006AA\u0001\u0012\u0003\tyF\u0002\u0005T\u0001\u0005\u0005\t\u0012AA1\u0011\u0019q7\u0004\"\u0001\u0002z!I\u00111K\u000e\u0002\u0002\u0013\u0015\u0013Q\u000b\u0005\n\u0003wZ\u0012\u0011!CA\u0003{B\u0011\"!\"\u001c\u0003\u0003%\t)a\"\t\u000f\u0005e\u0005\u0001\"\u0001\u0002\u001c\u001e9\u0011q\u0015\u0001\t\n\u0005%faBAV\u0001!%\u0011Q\u0016\u0005\u0007]\n\"\t!a,\t\u000f\u0005m$\u0005\"\u0001\u00022\"9\u00111\u001e\u0001\u0005\u0006\u00055(!F'bGJ|\u0017I\u001c8pi&|g\u000e\u0016:fK&sgm\u001c\u0006\u0003Q%\n\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003U-\nqA]3gY\u0016\u001cGOC\u0001-\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001A\u0018\u0011\u0005A\nT\"A\u0016\n\u0005IZ#AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002kA\u0011\u0001GN\u0005\u0003o-\u0012A!\u00168ji\u00069\u0002O]5nCJL8i\u001c8tiJ,8\r^8s\u0003JLG/\u001f\u000b\u0003uu\u0002\"\u0001M\u001e\n\u0005qZ#aA%oi\")aH\u0001a\u0001\u007f\u0005!AO]3f!\t\u0001eI\u0004\u0002B\u00056\t\u0001!\u0003\u0002D\t\u00061q\r\\8cC2L!!R\u0014\u0003\u0011Q\u0013X-Z%oM>L!a\u0012%\u0003\u0011\rc\u0017m]:EK\u001aL!!S\u0014\u0003\u000bQ\u0013X-Z:\u00021\u0005t\u0017pQ8ogR\u0014Xo\u0019;pe\"\u000b7\u000fR3gCVdG\u000f\u0006\u0002M\u001fB\u0011\u0001'T\u0005\u0003\u001d.\u0012qAQ8pY\u0016\fg\u000eC\u0003?\u0007\u0001\u0007q(A\tjg6\u000b7M]8B]:|G/\u0019;j_:$\"\u0001\u0014*\t\u000by\"\u0001\u0019A \u0003!\u0005sgn\u001c;bi&|gNW5qa\u0016\u00148\u0003B\u00030+b\u0003\"\u0001\r,\n\u0005][#a\u0002)s_\u0012,8\r\u001e\t\u00033\u0006t!AW0\u000f\u0005msV\"\u0001/\u000b\u0005uk\u0013A\u0002\u001fs_>$h(C\u0001-\u0013\t\u00017&A\u0004qC\u000e\\\u0017mZ3\n\u0005\t\u001c'\u0001D*fe&\fG.\u001b>bE2,'B\u00011,\u0003)\tgN\\8uCRLwN\\\u000b\u0002MB\u0011\u0001iZ\u0005\u0003Q\"\u0013A\u0001\u0016:fK\u0006Y\u0011M\u001c8pi\u0006$\u0018n\u001c8!\u0003!\tgN\\8ui\u0016,\u0017!C1o]>$H/Z3!\u0003\u0015ywO\\3s\u0003\u0019ywO\\3sA\u00051A(\u001b8jiz\"B\u0001]9sgB\u0011\u0011)\u0002\u0005\u0006I2\u0001\rA\u001a\u0005\u0006U2\u0001\rA\u001a\u0005\u0006Y2\u0001\rAZ\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003qm^D\bb\u00023\u000e!\u0003\u0005\rA\u001a\u0005\bU6\u0001\n\u00111\u0001g\u0011\u001daW\u0002%AA\u0002\u0019\fabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001|U\t1GpK\u0001~!\rq\u0018QA\u0007\u0002\u007f*!\u0011\u0011AA\u0002\u0003%)hn\u00195fG.,GM\u0003\u0002eW%\u0019\u0011qA@\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u0005\u0011\t\u0005M\u0011QD\u0007\u0003\u0003+QA!a\u0006\u0002\u001a\u0005!A.\u00198h\u0015\t\tY\"\u0001\u0003kCZ\f\u0017\u0002BA\u0010\u0003+\u0011aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001\u001e\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011FA\u0018!\r\u0001\u00141F\u0005\u0004\u0003[Y#aA!os\"A\u0011\u0011G\n\u0002\u0002\u0003\u0007!(A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003o\u0001b!!\u000f\u0002@\u0005%RBAA\u001e\u0015\r\tidK\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA!\u0003w\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019A*a\u0012\t\u0013\u0005ER#!AA\u0002\u0005%\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0005\u0002N!A\u0011\u0011\u0007\f\u0002\u0002\u0003\u0007!(\u0001\u0005iCND7i\u001c3f)\u0005Q\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005E\u0011AB3rk\u0006d7\u000fF\u0002M\u00037B\u0011\"!\r\u001a\u0003\u0003\u0005\r!!\u000b\u0002!\u0005sgn\u001c;bi&|gNW5qa\u0016\u0014\bCA!\u001c'\u0015Y\u00121MA8!!\t)'a\u001bgM\u001a\u0004XBAA4\u0015\r\tIgK\u0001\beVtG/[7f\u0013\u0011\ti'a\u001a\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\u0005\u0003\u0002r\u0005]TBAA:\u0015\u0011\t)(!\u0007\u0002\u0005%|\u0017b\u00012\u0002tQ\u0011\u0011qL\u0001\u0006CB\u0004H.\u001f\u000b\ba\u0006}\u0014\u0011QAB\u0011\u0015!g\u00041\u0001g\u0011\u0015Qg\u00041\u0001g\u0011\u0015ag\u00041\u0001g\u0003\u001d)h.\u00199qYf$B!!#\u0002\u0016B)\u0001'a#\u0002\u0010&\u0019\u0011QR\u0016\u0003\r=\u0003H/[8o!\u0019\u0001\u0014\u0011\u00134gM&\u0019\u00111S\u0016\u0003\rQ+\b\u000f\\34\u0011!\t9jHA\u0001\u0002\u0004\u0001\u0018a\u0001=%a\u0005!r-\u001a;B]:|G/\u0019;j_:T\u0016\u000e\u001d9feN$B!!(\u0002&B)\u0011qTAQa:\u0011\u0001gX\u0005\u0004\u0003G\u001b'\u0001\u0002'jgRDQA\u0010\u0011A\u0002\u0019\f\u0001\u0004U1uG\",GmU=oi\u0006\u001cG/[2DY\u0006\u001c8\u000fR3g!\t\t%E\u0001\rQCR\u001c\u0007.\u001a3Ts:$\u0018m\u0019;jG\u000ec\u0017m]:EK\u001a\u001c\"AI\u0018\u0015\u0005\u0005%FcE \u00024\u0006u\u00161ZAi\u0003+\fY.a8\u0002d\u0006\u001d\bbBA[I\u0001\u0007\u0011qW\u0001\u0005[>$7\u000fE\u0002A\u0003sK1!a/I\u0005%iu\u000eZ5gS\u0016\u00148\u000fC\u0004\u0002@\u0012\u0002\r!!1\u0002\t9\fW.\u001a\t\u0004\u0001\u0006\r\u0017\u0002BAc\u0003\u000f\u0014\u0001\u0002V=qK:\u000bW.Z\u0005\u0004\u0003\u0013<#!\u0002(b[\u0016\u001c\bbBAgI\u0001\u0007\u0011qZ\u0001\biB\f'/Y7t!\u0015\ty*!)g\u0011\u001d\t\u0019\u000e\na\u0001\u0003o\u000b!bY8ogR\u0014Xj\u001c3t\u0011\u001d\t9\u000e\na\u0001\u00033\f\u0001B\u001e9be\u0006l7o\u001d\t\u0007\u0003?\u000b\t+a4\t\u000f\u0005uG\u00051\u0001\u0002P\u0006IQ-\u0019:ms\u0012+gm\u001d\u0005\b\u0003C$\u0003\u0019AAh\u0003\u001d\u0001\u0018M]3oiNDa!!:%\u0001\u00041\u0017\u0001C:fY\u001a$\u0016\u0010]3\t\u000f\u0005%H\u00051\u0001\u0002P\u0006!!m\u001c3z\u00031\u0019\b\u000f\\5u\u0003R\u001cV\u000f]3s)\u0019\ty/!>\u0002zB9\u0001'!=\u0002P\u0006=\u0017bAAzW\t1A+\u001e9mKJBq!a>&\u0001\u0004\ty-A\u0003ti\u0006$8\u000f\u0003\u0004\u0002|\u0016\u0002\r\u0001T\u0001\nG2\f7o](oYf\u0014b!a@\u0003\u0004\t\u001daA\u0002B\u0001\u0001\u0001\tiP\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u0002\u0003\u0006\u0001i\u0011a\n\t\u0004\u0005\u000b!\u0005"
)
public interface MacroAnnotionTreeInfo {
   AnnotationZipper$ AnnotationZipper();

   PatchedSyntacticClassDef$ scala$reflect$internal$MacroAnnotionTreeInfo$$PatchedSyntacticClassDef();

   // $FF: synthetic method
   static int primaryConstructorArity$(final MacroAnnotionTreeInfo $this, final Trees.ClassDef tree) {
      return $this.primaryConstructorArity(tree);
   }

   default int primaryConstructorArity(final Trees.ClassDef tree) {
      Trees.Tree var2 = ((TreeInfo)this).global().treeInfo().firstConstructor(tree.impl().body());
      if (var2 instanceof Trees.DefDef) {
         List var3 = ((Trees.DefDef)var2).vparamss();
         if (var3 instanceof .colon.colon) {
            return ((List)((.colon.colon)var3).head()).length();
         }
      }

      throw new MatchError(var2);
   }

   // $FF: synthetic method
   static boolean anyConstructorHasDefault$(final MacroAnnotionTreeInfo $this, final Trees.ClassDef tree) {
      return $this.anyConstructorHasDefault(tree);
   }

   default boolean anyConstructorHasDefault(final Trees.ClassDef tree) {
      List var10000 = tree.impl().body();
      if (var10000 == null) {
         throw null;
      } else {
         for(List exists_these = var10000; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
            Trees.Tree var3 = (Trees.Tree)exists_these.head();
            if ($anonfun$anyConstructorHasDefault$1(this, var3)) {
               return true;
            }
         }

         return false;
      }
   }

   // $FF: synthetic method
   static boolean isMacroAnnotation$(final MacroAnnotionTreeInfo $this, final Trees.ClassDef tree) {
      return $this.isMacroAnnotation(tree);
   }

   default boolean isMacroAnnotation(final Trees.ClassDef tree) {
      Symbols.Symbol clazz = tree.symbol();
      return clazz != null && this.isAnnotation$1(clazz) && this.hasMacroTransformMethod$1(clazz);
   }

   // $FF: synthetic method
   static List getAnnotationZippers$(final MacroAnnotionTreeInfo $this, final Trees.Tree tree) {
      return $this.getAnnotationZippers(tree);
   }

   default List getAnnotationZippers(final Trees.Tree tree) {
      return this.loop$4(tree, true);
   }

   // $FF: synthetic method
   static Tuple2 splitAtSuper$(final MacroAnnotionTreeInfo $this, final List stats, final boolean classOnly) {
      return $this.splitAtSuper(stats, classOnly);
   }

   default Tuple2 splitAtSuper(final List stats, final boolean classOnly) {
      if (stats == null) {
         throw null;
      } else {
         ListBuffer span_b = new ListBuffer();

         List span_these;
         for(span_these = stats; !span_these.isEmpty(); span_these = (List)span_these.tail()) {
            Trees.Tree var11 = (Trees.Tree)span_these.head();
            if (!$anonfun$splitAtSuper$1(this, classOnly, var11)) {
               break;
            }

            Object span_$plus$eq_elem = span_these.head();
            span_b.addOne(span_$plus$eq_elem);
            span_$plus$eq_elem = null;
         }

         List var10000 = span_b.toList();
         List var10001 = span_these;
         Object var15 = null;
         Object var16 = null;
         Object var18 = null;
         List var13 = var10001;
         List pre = var10000;
         ListBuffer span_b = new ListBuffer();

         List span_these;
         for(span_these = var13; !span_these.isEmpty(); span_these = (List)span_these.tail()) {
            Trees.Tree var12 = (Trees.Tree)span_these.head();
            if (!$anonfun$splitAtSuper$2(this, classOnly, var12)) {
               break;
            }

            Object span_$plus$eq_elem = span_these.head();
            span_b.addOne(span_$plus$eq_elem);
            span_$plus$eq_elem = null;
         }

         var10000 = span_b.toList();
         var10001 = span_these;
         Object var19 = null;
         Object var20 = null;
         Object var22 = null;
         List var14 = var10001;
         List supercalls = var10000;
         return new Tuple2(supercalls.$colon$colon$colon(pre), var14);
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$anyConstructorHasDefault$2(final Trees.ValDef x$10) {
      return x$10.mods().hasDefault();
   }

   // $FF: synthetic method
   static boolean $anonfun$anyConstructorHasDefault$1(final MacroAnnotionTreeInfo $this, final Trees.Tree x0$1) {
      if (x0$1 instanceof Trees.DefDef) {
         Trees.DefDef var2 = (Trees.DefDef)x0$1;
         Names.TermName var3 = var2.name();
         List paramss = var2.vparamss();
         Names.TermName var10000 = ((TreeInfo)$this).global().nme().CONSTRUCTOR();
         if (var10000 == null) {
            if (var3 != null) {
               return false;
            }
         } else if (!var10000.equals(var3)) {
            return false;
         }

         if (((TreeInfo)$this).global() == null) {
            throw null;
         } else if (paramss == null) {
            throw null;
         } else {
            for(List mexists_exists_these = paramss; !mexists_exists_these.isEmpty(); mexists_exists_these = (List)mexists_exists_these.tail()) {
               List var6 = (List)mexists_exists_these.head();
               if (var6 == null) {
                  throw null;
               }

               List exists_these = var6;

               while(true) {
                  if (exists_these.isEmpty()) {
                     var9 = false;
                     break;
                  }

                  if ($anonfun$anyConstructorHasDefault$2((Trees.ValDef)exists_these.head())) {
                     var9 = true;
                     break;
                  }

                  exists_these = (List)exists_these.tail();
               }

               Object var8 = null;
               if (var9) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return false;
      }
   }

   private boolean isAnnotation$1(final Symbols.Symbol clazz$1) {
      return clazz$1.isNonBottomSubClass(((TreeInfo)this).global().definitions().AnnotationClass());
   }

   private boolean hasMacroTransformMethod$1(final Symbols.Symbol clazz$1) {
      Symbols.Symbol var10000 = clazz$1.info().member((Names.Name)((TreeInfo)this).global().nme().macroTransform());
      Symbols.NoSymbol var2 = ((TreeInfo)this).global().NoSymbol();
      if (var10000 == null) {
         if (var2 != null) {
            return true;
         }
      } else if (!var10000.equals(var2)) {
         return true;
      }

      return false;
   }

   // $FF: synthetic method
   static AnnotationZipper $anonfun$getAnnotationZippers$1(final MacroAnnotionTreeInfo $this, final Trees.Modifiers mods$1, final Names.TypeName name$1, final List tparams$1, final Trees.Modifiers constrMods$1, final List vparamss$1, final List earlyDefs$1, final List parents$1, final Trees.ValDef selfdef$1, final List body$1, final Trees.Tree ann) {
      Trees.Modifiers mods1 = mods$1.mapAnnotations((x$11) -> {
         .colon.colon diff_that = new .colon.colon(ann, scala.collection.immutable.Nil..MODULE$);
         if (x$11 == null) {
            throw null;
         } else {
            return (List)StrictOptimizedSeqOps.diff$(x$11, diff_that);
         }
      });
      Trees.ClassDef annottee = $this.scala$reflect$internal$MacroAnnotionTreeInfo$$PatchedSyntacticClassDef().apply(mods1, name$1, tparams$1, constrMods$1, vparamss$1, earlyDefs$1, parents$1, selfdef$1, body$1);
      return (TreeInfo)$this.new AnnotationZipper(ann, annottee, annottee);
   }

   // $FF: synthetic method
   static boolean $anonfun$getAnnotationZippers$4(final AnnotationZipper check$ifrefutable$1) {
      return check$ifrefutable$1 != null && check$ifrefutable$1.annottee() instanceof Trees.TypeDef;
   }

   // $FF: synthetic method
   static AnnotationZipper $anonfun$getAnnotationZippers$6(final MacroAnnotionTreeInfo $this, final Trees.Modifiers mods$1, final Names.TypeName name$1, final Trees.Modifiers constrMods$1, final List vparamss$1, final List earlyDefs$1, final List parents$1, final Trees.ValDef selfdef$1, final List body$1, final Tuple2 x$14) {
      if (x$14 != null) {
         AnnotationZipper var10 = (AnnotationZipper)x$14._1();
         List tparams1 = (List)x$14._2();
         if (var10 != null) {
            Trees.Tree ann = var10.annotation();
            Trees.Tree tparam1 = var10.annottee();
            if (tparam1 instanceof Trees.TypeDef) {
               Trees.TypeDef var14 = (Trees.TypeDef)tparam1;
               return (TreeInfo)$this.new AnnotationZipper(ann, var14, $this.scala$reflect$internal$MacroAnnotionTreeInfo$$PatchedSyntacticClassDef().apply(mods$1, name$1, tparams1, constrMods$1, vparamss$1, earlyDefs$1, parents$1, selfdef$1, body$1));
            }
         }
      }

      throw new MatchError(x$14);
   }

   // $FF: synthetic method
   static List $anonfun$getAnnotationZippers$3(final MacroAnnotionTreeInfo $this, final List tparams$1, final Trees.Modifiers mods$1, final Names.TypeName name$1, final Trees.Modifiers constrMods$1, final List vparamss$1, final List earlyDefs$1, final List parents$1, final Trees.ValDef selfdef$1, final List body$1, final Trees.TypeDef tparam) {
      List var10000 = (List)$this.loop$4(tparam, false).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$getAnnotationZippers$4(check$ifrefutable$1))).map((x$13) -> {
         if (x$13 != null) {
            Trees.Tree tparam1 = x$13.annottee();
            if (tparam1 instanceof Trees.TypeDef) {
               Trees.TypeDef var4 = (Trees.TypeDef)tparam1;
               List tparams1 = tparams$1.updated(tparams$1.indexOf(tparam), var4);
               return new Tuple2(x$13, tparams1);
            }
         }

         throw new MatchError(x$13);
      });
      if (var10000 == null) {
         throw null;
      } else {
         List map_this = var10000;
         if (map_this == scala.collection.immutable.Nil..MODULE$) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            Tuple2 var16 = (Tuple2)map_this.head();
            .colon.colon map_h = new .colon.colon($anonfun$getAnnotationZippers$6($this, mods$1, name$1, constrMods$1, vparamss$1, earlyDefs$1, parents$1, selfdef$1, body$1, var16), scala.collection.immutable.Nil..MODULE$);
            .colon.colon map_t = map_h;

            for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               var16 = (Tuple2)map_rest.head();
               .colon.colon map_nx = new .colon.colon($anonfun$getAnnotationZippers$6($this, mods$1, name$1, constrMods$1, vparamss$1, earlyDefs$1, parents$1, selfdef$1, body$1, var16), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            return map_h;
         }
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$getAnnotationZippers$9(final AnnotationZipper check$ifrefutable$2) {
      return check$ifrefutable$2 != null && check$ifrefutable$2.annottee() instanceof Trees.ValDef;
   }

   // $FF: synthetic method
   static AnnotationZipper $anonfun$getAnnotationZippers$11(final MacroAnnotionTreeInfo $this, final Trees.Modifiers mods$1, final Names.TypeName name$1, final List tparams$1, final Trees.Modifiers constrMods$1, final List earlyDefs$1, final List parents$1, final Trees.ValDef selfdef$1, final List body$1, final Tuple3 x$17) {
      if (x$17 != null) {
         AnnotationZipper var10 = (AnnotationZipper)x$17._1();
         List vparamss1 = (List)x$17._3();
         if (var10 != null) {
            Trees.Tree ann = var10.annotation();
            Trees.Tree vparam1 = var10.annottee();
            if (vparam1 instanceof Trees.ValDef) {
               Trees.ValDef var14 = (Trees.ValDef)vparam1;
               return (TreeInfo)$this.new AnnotationZipper(ann, var14, $this.scala$reflect$internal$MacroAnnotionTreeInfo$$PatchedSyntacticClassDef().apply(mods$1, name$1, tparams$1, constrMods$1, vparamss1, earlyDefs$1, parents$1, selfdef$1, body$1));
            }
         }
      }

      throw new MatchError(x$17);
   }

   // $FF: synthetic method
   static List $anonfun$getAnnotationZippers$8(final MacroAnnotionTreeInfo $this, final List vparams$1, final List vparamss$1, final Trees.Modifiers mods$1, final Names.TypeName name$1, final List tparams$1, final Trees.Modifiers constrMods$1, final List earlyDefs$1, final List parents$1, final Trees.ValDef selfdef$1, final List body$1, final Trees.ValDef vparam) {
      List var10000 = (List)$this.loop$4(vparam, false).withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$getAnnotationZippers$9(check$ifrefutable$2))).map((x$16) -> {
         if (x$16 != null) {
            Trees.Tree vparam1 = x$16.annottee();
            if (vparam1 instanceof Trees.ValDef) {
               Trees.ValDef var5 = (Trees.ValDef)vparam1;
               List vparams1 = vparams$1.updated(vparams$1.indexOf(vparam), var5);
               List vparamss1 = vparamss$1.updated(vparamss$1.indexOf(vparams$1), vparams1);
               return new Tuple3(x$16, vparams1, vparamss1);
            }
         }

         throw new MatchError(x$16);
      });
      if (var10000 == null) {
         throw null;
      } else {
         List map_this = var10000;
         if (map_this == scala.collection.immutable.Nil..MODULE$) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            Tuple3 var17 = (Tuple3)map_this.head();
            .colon.colon map_h = new .colon.colon($anonfun$getAnnotationZippers$11($this, mods$1, name$1, tparams$1, constrMods$1, earlyDefs$1, parents$1, selfdef$1, body$1, var17), scala.collection.immutable.Nil..MODULE$);
            .colon.colon map_t = map_h;

            for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               var17 = (Tuple3)map_rest.head();
               .colon.colon map_nx = new .colon.colon($anonfun$getAnnotationZippers$11($this, mods$1, name$1, tparams$1, constrMods$1, earlyDefs$1, parents$1, selfdef$1, body$1, var17), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            return map_h;
         }
      }
   }

   // $FF: synthetic method
   static List $anonfun$getAnnotationZippers$7(final MacroAnnotionTreeInfo $this, final List vparamss$1, final Trees.Modifiers mods$1, final Names.TypeName name$1, final List tparams$1, final Trees.Modifiers constrMods$1, final List earlyDefs$1, final List parents$1, final Trees.ValDef selfdef$1, final List body$1, final List vparams) {
      if (vparams == null) {
         throw null;
      } else {
         List flatMap_rest = vparams;
         .colon.colon flatMap_h = null;

         for(.colon.colon flatMap_t = null; flatMap_rest != scala.collection.immutable.Nil..MODULE$; flatMap_rest = (List)flatMap_rest.tail()) {
            Trees.ValDef var16 = (Trees.ValDef)flatMap_rest.head();

            .colon.colon flatMap_nx;
            for(Iterator flatMap_it = $anonfun$getAnnotationZippers$8($this, vparams, vparamss$1, mods$1, name$1, tparams$1, constrMods$1, earlyDefs$1, parents$1, selfdef$1, body$1, var16).iterator(); flatMap_it.hasNext(); flatMap_t = flatMap_nx) {
               flatMap_nx = new .colon.colon(flatMap_it.next(), scala.collection.immutable.Nil..MODULE$);
               if (flatMap_t == null) {
                  flatMap_h = flatMap_nx;
               } else {
                  flatMap_t.next_$eq(flatMap_nx);
               }
            }
         }

         if (flatMap_h == null) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            Statics.releaseFence();
            return flatMap_h;
         }
      }
   }

   // $FF: synthetic method
   static AnnotationZipper $anonfun$getAnnotationZippers$12(final MacroAnnotionTreeInfo $this, final Trees.ClassDef tdef$1, final Trees.Modifiers mods$2, final Trees.Tree ann) {
      Trees.ClassDef annottee = tdef$1.copy(mods$2.mapAnnotations((x$18) -> {
         .colon.colon diff_that = new .colon.colon(ann, scala.collection.immutable.Nil..MODULE$);
         if (x$18 == null) {
            throw null;
         } else {
            return (List)StrictOptimizedSeqOps.diff$(x$18, diff_that);
         }
      }), tdef$1.copy$default$2(), tdef$1.copy$default$3(), tdef$1.copy$default$4());
      return (TreeInfo)$this.new AnnotationZipper(ann, annottee, annottee);
   }

   // $FF: synthetic method
   static boolean $anonfun$getAnnotationZippers$15(final AnnotationZipper check$ifrefutable$3) {
      return check$ifrefutable$3 != null && check$ifrefutable$3.annottee() instanceof Trees.TypeDef;
   }

   // $FF: synthetic method
   static AnnotationZipper $anonfun$getAnnotationZippers$17(final MacroAnnotionTreeInfo $this, final Trees.ClassDef tdef$1, final Tuple2 x$21) {
      if (x$21 != null) {
         AnnotationZipper var3 = (AnnotationZipper)x$21._1();
         List tparams1 = (List)x$21._2();
         if (var3 != null) {
            Trees.Tree ann = var3.annotation();
            Trees.Tree tparam1 = var3.annottee();
            if (tparam1 instanceof Trees.TypeDef) {
               Trees.TypeDef var7 = (Trees.TypeDef)tparam1;
               TreeInfo var10002 = (TreeInfo)$this;
               Trees.Modifiers x$2 = tdef$1.copy$default$1();
               Names.TypeName x$3 = tdef$1.copy$default$2();
               Trees.Template x$4 = tdef$1.copy$default$4();
               return var10002.new AnnotationZipper(ann, var7, tdef$1.copy(x$2, x$3, tparams1, x$4));
            }
         }
      }

      throw new MatchError(x$21);
   }

   // $FF: synthetic method
   static List $anonfun$getAnnotationZippers$14(final MacroAnnotionTreeInfo $this, final List tparams$2, final Trees.ClassDef tdef$1, final Trees.TypeDef tparam) {
      List var10000 = (List)$this.loop$4(tparam, false).withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$getAnnotationZippers$15(check$ifrefutable$3))).map((x$20) -> {
         if (x$20 != null) {
            Trees.Tree tparam1 = x$20.annottee();
            if (tparam1 instanceof Trees.TypeDef) {
               Trees.TypeDef var4 = (Trees.TypeDef)tparam1;
               List tparams1 = tparams$2.updated(tparams$2.indexOf(tparam), var4);
               return new Tuple2(x$20, tparams1);
            }
         }

         throw new MatchError(x$20);
      });
      if (var10000 == null) {
         throw null;
      } else {
         List map_this = var10000;
         if (map_this == scala.collection.immutable.Nil..MODULE$) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            Tuple2 var9 = (Tuple2)map_this.head();
            .colon.colon map_h = new .colon.colon($anonfun$getAnnotationZippers$17($this, tdef$1, var9), scala.collection.immutable.Nil..MODULE$);
            .colon.colon map_t = map_h;

            for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               var9 = (Tuple2)map_rest.head();
               .colon.colon map_nx = new .colon.colon($anonfun$getAnnotationZippers$17($this, tdef$1, var9), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            return map_h;
         }
      }
   }

   // $FF: synthetic method
   static AnnotationZipper $anonfun$getAnnotationZippers$18(final MacroAnnotionTreeInfo $this, final Trees.ModuleDef x4$2, final Trees.Modifiers mods$3, final Trees.Tree ann) {
      Trees.ModuleDef annottee = x4$2.copy(mods$3.mapAnnotations((x$22) -> {
         .colon.colon diff_that = new .colon.colon(ann, scala.collection.immutable.Nil..MODULE$);
         if (x$22 == null) {
            throw null;
         } else {
            return (List)StrictOptimizedSeqOps.diff$(x$22, diff_that);
         }
      }), x4$2.copy$default$2(), x4$2.copy$default$3());
      return (TreeInfo)$this.new AnnotationZipper(ann, annottee, annottee);
   }

   // $FF: synthetic method
   static AnnotationZipper $anonfun$getAnnotationZippers$20(final MacroAnnotionTreeInfo $this, final Trees.DefDef x7$1, final Trees.Modifiers mods$4, final Trees.Tree ann) {
      Trees.DefDef annottee = x7$1.copy(mods$4.mapAnnotations((x$23) -> {
         .colon.colon diff_that = new .colon.colon(ann, scala.collection.immutable.Nil..MODULE$);
         if (x$23 == null) {
            throw null;
         } else {
            return (List)StrictOptimizedSeqOps.diff$(x$23, diff_that);
         }
      }), x7$1.copy$default$2(), x7$1.copy$default$3(), x7$1.copy$default$4(), x7$1.copy$default$5(), x7$1.copy$default$6());
      return (TreeInfo)$this.new AnnotationZipper(ann, annottee, annottee);
   }

   // $FF: synthetic method
   static boolean $anonfun$getAnnotationZippers$23(final AnnotationZipper check$ifrefutable$4) {
      return check$ifrefutable$4 != null && check$ifrefutable$4.annottee() instanceof Trees.TypeDef;
   }

   // $FF: synthetic method
   static AnnotationZipper $anonfun$getAnnotationZippers$25(final MacroAnnotionTreeInfo $this, final Trees.DefDef x7$1, final Tuple2 x$26) {
      if (x$26 != null) {
         AnnotationZipper var3 = (AnnotationZipper)x$26._1();
         List tparams1 = (List)x$26._2();
         if (var3 != null) {
            Trees.Tree ann = var3.annotation();
            Trees.Tree tparam1 = var3.annottee();
            if (tparam1 instanceof Trees.TypeDef) {
               Trees.TypeDef var7 = (Trees.TypeDef)tparam1;
               TreeInfo var10002 = (TreeInfo)$this;
               Trees.Modifiers x$6 = x7$1.copy$default$1();
               Names.TermName x$7 = x7$1.copy$default$2();
               List x$8 = x7$1.copy$default$4();
               Trees.Tree x$9 = x7$1.copy$default$5();
               Trees.Tree x$10 = x7$1.copy$default$6();
               return var10002.new AnnotationZipper(ann, var7, x7$1.copy(x$6, x$7, tparams1, x$8, x$9, x$10));
            }
         }
      }

      throw new MatchError(x$26);
   }

   // $FF: synthetic method
   static List $anonfun$getAnnotationZippers$22(final MacroAnnotionTreeInfo $this, final List tparams$3, final Trees.DefDef x7$1, final Trees.TypeDef tparam) {
      List var10000 = (List)$this.loop$4(tparam, false).withFilter((check$ifrefutable$4) -> BoxesRunTime.boxToBoolean($anonfun$getAnnotationZippers$23(check$ifrefutable$4))).map((x$25) -> {
         if (x$25 != null) {
            Trees.Tree tparam1 = x$25.annottee();
            if (tparam1 instanceof Trees.TypeDef) {
               Trees.TypeDef var4 = (Trees.TypeDef)tparam1;
               List tparams1 = tparams$3.updated(tparams$3.indexOf(tparam), var4);
               return new Tuple2(x$25, tparams1);
            }
         }

         throw new MatchError(x$25);
      });
      if (var10000 == null) {
         throw null;
      } else {
         List map_this = var10000;
         if (map_this == scala.collection.immutable.Nil..MODULE$) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            Tuple2 var9 = (Tuple2)map_this.head();
            .colon.colon map_h = new .colon.colon($anonfun$getAnnotationZippers$25($this, x7$1, var9), scala.collection.immutable.Nil..MODULE$);
            .colon.colon map_t = map_h;

            for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               var9 = (Tuple2)map_rest.head();
               .colon.colon map_nx = new .colon.colon($anonfun$getAnnotationZippers$25($this, x7$1, var9), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            return map_h;
         }
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$getAnnotationZippers$28(final AnnotationZipper check$ifrefutable$5) {
      return check$ifrefutable$5 != null && check$ifrefutable$5.annottee() instanceof Trees.ValDef;
   }

   // $FF: synthetic method
   static AnnotationZipper $anonfun$getAnnotationZippers$30(final MacroAnnotionTreeInfo $this, final Trees.DefDef x7$1, final Tuple3 x$29) {
      if (x$29 != null) {
         AnnotationZipper var3 = (AnnotationZipper)x$29._1();
         List vparamss1 = (List)x$29._3();
         if (var3 != null) {
            Trees.Tree ann = var3.annotation();
            Trees.Tree vparam1 = var3.annottee();
            if (vparam1 instanceof Trees.ValDef) {
               Trees.ValDef var7 = (Trees.ValDef)vparam1;
               TreeInfo var10002 = (TreeInfo)$this;
               Trees.Modifiers x$12 = x7$1.copy$default$1();
               Names.TermName x$13 = x7$1.copy$default$2();
               List x$14 = x7$1.copy$default$3();
               Trees.Tree x$15 = x7$1.copy$default$5();
               Trees.Tree x$16 = x7$1.copy$default$6();
               return var10002.new AnnotationZipper(ann, var7, x7$1.copy(x$12, x$13, x$14, vparamss1, x$15, x$16));
            }
         }
      }

      throw new MatchError(x$29);
   }

   // $FF: synthetic method
   static List $anonfun$getAnnotationZippers$27(final MacroAnnotionTreeInfo $this, final List vparams$2, final List vparamss$2, final Trees.DefDef x7$1, final Trees.ValDef vparam) {
      List var10000 = (List)$this.loop$4(vparam, false).withFilter((check$ifrefutable$5) -> BoxesRunTime.boxToBoolean($anonfun$getAnnotationZippers$28(check$ifrefutable$5))).map((x$28) -> {
         if (x$28 != null) {
            Trees.Tree vparam1 = x$28.annottee();
            if (vparam1 instanceof Trees.ValDef) {
               Trees.ValDef var5 = (Trees.ValDef)vparam1;
               List vparams1 = vparams$2.updated(vparams$2.indexOf(vparam), var5);
               List vparamss1 = vparamss$2.updated(vparamss$2.indexOf(vparams$2), vparams1);
               return new Tuple3(x$28, vparams1, vparamss1);
            }
         }

         throw new MatchError(x$28);
      });
      if (var10000 == null) {
         throw null;
      } else {
         List map_this = var10000;
         if (map_this == scala.collection.immutable.Nil..MODULE$) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            Tuple3 var10 = (Tuple3)map_this.head();
            .colon.colon map_h = new .colon.colon($anonfun$getAnnotationZippers$30($this, x7$1, var10), scala.collection.immutable.Nil..MODULE$);
            .colon.colon map_t = map_h;

            for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               var10 = (Tuple3)map_rest.head();
               .colon.colon map_nx = new .colon.colon($anonfun$getAnnotationZippers$30($this, x7$1, var10), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            return map_h;
         }
      }
   }

   // $FF: synthetic method
   static List $anonfun$getAnnotationZippers$26(final MacroAnnotionTreeInfo $this, final List vparamss$2, final Trees.DefDef x7$1, final List vparams) {
      if (vparams == null) {
         throw null;
      } else {
         List flatMap_rest = vparams;
         .colon.colon flatMap_h = null;

         for(.colon.colon flatMap_t = null; flatMap_rest != scala.collection.immutable.Nil..MODULE$; flatMap_rest = (List)flatMap_rest.tail()) {
            Trees.ValDef var9 = (Trees.ValDef)flatMap_rest.head();

            .colon.colon flatMap_nx;
            for(Iterator flatMap_it = $anonfun$getAnnotationZippers$27($this, vparams, vparamss$2, x7$1, var9).iterator(); flatMap_it.hasNext(); flatMap_t = flatMap_nx) {
               flatMap_nx = new .colon.colon(flatMap_it.next(), scala.collection.immutable.Nil..MODULE$);
               if (flatMap_t == null) {
                  flatMap_h = flatMap_nx;
               } else {
                  flatMap_t.next_$eq(flatMap_nx);
               }
            }
         }

         if (flatMap_h == null) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            Statics.releaseFence();
            return flatMap_h;
         }
      }
   }

   // $FF: synthetic method
   static AnnotationZipper $anonfun$getAnnotationZippers$31(final MacroAnnotionTreeInfo $this, final Trees.ValDef x11$1, final Trees.Modifiers mods$5, final Trees.Tree ann) {
      Trees.ValDef annottee = x11$1.copy(mods$5.mapAnnotations((x$30) -> {
         .colon.colon diff_that = new .colon.colon(ann, scala.collection.immutable.Nil..MODULE$);
         if (x$30 == null) {
            throw null;
         } else {
            return (List)StrictOptimizedSeqOps.diff$(x$30, diff_that);
         }
      }), x11$1.copy$default$2(), x11$1.copy$default$3(), x11$1.copy$default$4());
      return (TreeInfo)$this.new AnnotationZipper(ann, annottee, annottee);
   }

   // $FF: synthetic method
   static AnnotationZipper $anonfun$getAnnotationZippers$33(final MacroAnnotionTreeInfo $this, final Trees.TypeDef x15$1, final Trees.Modifiers mods$6, final Trees.Tree ann) {
      Trees.TypeDef annottee = x15$1.copy(mods$6.mapAnnotations((x$31) -> {
         .colon.colon diff_that = new .colon.colon(ann, scala.collection.immutable.Nil..MODULE$);
         if (x$31 == null) {
            throw null;
         } else {
            return (List)StrictOptimizedSeqOps.diff$(x$31, diff_that);
         }
      }), x15$1.copy$default$2(), x15$1.copy$default$3(), x15$1.copy$default$4());
      return (TreeInfo)$this.new AnnotationZipper(ann, annottee, annottee);
   }

   // $FF: synthetic method
   static boolean $anonfun$getAnnotationZippers$36(final AnnotationZipper check$ifrefutable$6) {
      return check$ifrefutable$6 != null && check$ifrefutable$6.annottee() instanceof Trees.TypeDef;
   }

   // $FF: synthetic method
   static AnnotationZipper $anonfun$getAnnotationZippers$38(final MacroAnnotionTreeInfo $this, final Trees.TypeDef x15$1, final Tuple2 x$34) {
      if (x$34 != null) {
         AnnotationZipper var3 = (AnnotationZipper)x$34._1();
         List tparams1 = (List)x$34._2();
         if (var3 != null) {
            Trees.Tree ann = var3.annotation();
            Trees.Tree tparam1 = var3.annottee();
            if (tparam1 instanceof Trees.TypeDef) {
               Trees.TypeDef var7 = (Trees.TypeDef)tparam1;
               TreeInfo var10002 = (TreeInfo)$this;
               Trees.Modifiers x$18 = x15$1.copy$default$1();
               Names.TypeName x$19 = x15$1.copy$default$2();
               Trees.Tree x$20 = x15$1.copy$default$4();
               return var10002.new AnnotationZipper(ann, var7, x15$1.copy(x$18, x$19, tparams1, x$20));
            }
         }
      }

      throw new MatchError(x$34);
   }

   // $FF: synthetic method
   static List $anonfun$getAnnotationZippers$35(final MacroAnnotionTreeInfo $this, final List tparams$4, final Trees.TypeDef x15$1, final Trees.TypeDef tparam) {
      List var10000 = (List)$this.loop$4(tparam, false).withFilter((check$ifrefutable$6) -> BoxesRunTime.boxToBoolean($anonfun$getAnnotationZippers$36(check$ifrefutable$6))).map((x$33) -> {
         if (x$33 != null) {
            Trees.Tree tparam1 = x$33.annottee();
            if (tparam1 instanceof Trees.TypeDef) {
               Trees.TypeDef var4 = (Trees.TypeDef)tparam1;
               List tparams1 = tparams$4.updated(tparams$4.indexOf(tparam), var4);
               return new Tuple2(x$33, tparams1);
            }
         }

         throw new MatchError(x$33);
      });
      if (var10000 == null) {
         throw null;
      } else {
         List map_this = var10000;
         if (map_this == scala.collection.immutable.Nil..MODULE$) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            Tuple2 var9 = (Tuple2)map_this.head();
            .colon.colon map_h = new .colon.colon($anonfun$getAnnotationZippers$38($this, x15$1, var9), scala.collection.immutable.Nil..MODULE$);
            .colon.colon map_t = map_h;

            for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               var9 = (Tuple2)map_rest.head();
               .colon.colon map_nx = new .colon.colon($anonfun$getAnnotationZippers$38($this, x15$1, var9), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            return map_h;
         }
      }
   }

   private List loop$4(final Trees.Tree tree, final boolean deep) {
      if (tree != null) {
         Option var3 = ((TreeInfo)this).global().build().SyntacticClassDef().unapply(tree);
         if (!var3.isEmpty()) {
            Trees.Modifiers mods = (Trees.Modifiers)((Tuple9)var3.get())._1();
            Names.TypeName name = (Names.TypeName)((Tuple9)var3.get())._2();
            List tparams = (List)((Tuple9)var3.get())._3();
            Trees.Modifiers constrMods = (Trees.Modifiers)((Tuple9)var3.get())._4();
            List vparamss = (List)((Tuple9)var3.get())._5();
            List earlyDefs = (List)((Tuple9)var3.get())._6();
            List parents = (List)((Tuple9)var3.get())._7();
            Trees.ValDef selfdef = (Trees.ValDef)((Tuple9)var3.get())._8();
            List body = (List)((Tuple9)var3.get())._9();
            List var178 = mods.annotations();
            if (var178 == null) {
               throw null;
            }

            List map_this = var178;
            Object var179;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               var179 = scala.collection.immutable.Nil..MODULE$;
            } else {
               Trees.Tree var98 = (Trees.Tree)map_this.head();
               .colon.colon map_h = new .colon.colon($anonfun$getAnnotationZippers$1(this, mods, name, tparams, constrMods, vparamss, earlyDefs, parents, selfdef, body, var98), scala.collection.immutable.Nil..MODULE$);
               .colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var98 = (Trees.Tree)map_rest.head();
                  .colon.colon map_nx = new .colon.colon($anonfun$getAnnotationZippers$1(this, mods, name, tparams, constrMods, vparamss, earlyDefs, parents, selfdef, body, var98), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var179 = map_h;
            }

            Object var110 = null;
            Object var111 = null;
            Object var112 = null;
            Object var113 = null;
            Object var114 = null;
            List czippers = (List)var179;
            if (!deep) {
               return czippers;
            }

            if (tparams == null) {
               throw null;
            }

            List flatMap_rest = tparams;
            .colon.colon flatMap_h = null;

            for(.colon.colon flatMap_t = null; flatMap_rest != scala.collection.immutable.Nil..MODULE$; flatMap_rest = (List)flatMap_rest.tail()) {
               Trees.TypeDef var99 = (Trees.TypeDef)flatMap_rest.head();

               .colon.colon flatMap_nx;
               for(Iterator flatMap_it = $anonfun$getAnnotationZippers$3(this, tparams, mods, name, constrMods, vparamss, earlyDefs, parents, selfdef, body, var99).iterator(); flatMap_it.hasNext(); flatMap_t = flatMap_nx) {
                  flatMap_nx = new .colon.colon(flatMap_it.next(), scala.collection.immutable.Nil..MODULE$);
                  if (flatMap_t == null) {
                     flatMap_h = flatMap_nx;
                  } else {
                     flatMap_t.next_$eq(flatMap_nx);
                  }
               }
            }

            if (flatMap_h == null) {
               var179 = scala.collection.immutable.Nil..MODULE$;
            } else {
               Statics.releaseFence();
               var179 = flatMap_h;
            }

            Object var115 = null;
            Object var116 = null;
            Object var117 = null;
            Object var118 = null;
            Object var119 = null;
            List tzippers = (List)var179;
            if (vparamss == null) {
               throw null;
            }

            List flatMap_rest = vparamss;
            .colon.colon flatMap_h = null;

            for(.colon.colon flatMap_t = null; flatMap_rest != scala.collection.immutable.Nil..MODULE$; flatMap_rest = (List)flatMap_rest.tail()) {
               List var100 = (List)flatMap_rest.head();

               .colon.colon flatMap_nx;
               for(Iterator flatMap_it = $anonfun$getAnnotationZippers$7(this, vparamss, mods, name, tparams, constrMods, earlyDefs, parents, selfdef, body, var100).iterator(); flatMap_it.hasNext(); flatMap_t = flatMap_nx) {
                  flatMap_nx = new .colon.colon(flatMap_it.next(), scala.collection.immutable.Nil..MODULE$);
                  if (flatMap_t == null) {
                     flatMap_h = flatMap_nx;
                  } else {
                     flatMap_t.next_$eq(flatMap_nx);
                  }
               }
            }

            if (flatMap_h == null) {
               var179 = scala.collection.immutable.Nil..MODULE$;
            } else {
               Statics.releaseFence();
               var179 = flatMap_h;
            }

            Object var120 = null;
            Object var121 = null;
            Object var122 = null;
            Object var123 = null;
            Object var124 = null;
            List vzippers = (List)var179;
            IterableOps var182 = (IterableOps)czippers.concat(tzippers);
            if (var182 == null) {
               throw null;
            }

            return (List)var182.concat(vzippers);
         }
      }

      if (tree != null) {
         Option var16 = ((TreeInfo)this).global().build().SyntacticTraitDef().unapply(tree);
         if (!var16.isEmpty()) {
            Trees.Modifiers mods = (Trees.Modifiers)((Tuple7)var16.get())._1();
            List tparams = (List)((Tuple7)var16.get())._3();
            Trees.ClassDef tdef = (Trees.ClassDef)tree;
            List var175 = mods.annotations();
            if (var175 == null) {
               throw null;
            }

            List map_this = var175;
            Object var176;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               var176 = scala.collection.immutable.Nil..MODULE$;
            } else {
               Trees.Tree var101 = (Trees.Tree)map_this.head();
               .colon.colon map_h = new .colon.colon($anonfun$getAnnotationZippers$12(this, tdef, mods, var101), scala.collection.immutable.Nil..MODULE$);
               .colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var101 = (Trees.Tree)map_rest.head();
                  .colon.colon map_nx = new .colon.colon($anonfun$getAnnotationZippers$12(this, tdef, mods, var101), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var176 = map_h;
            }

            Object var125 = null;
            Object var126 = null;
            Object var127 = null;
            Object var128 = null;
            Object var129 = null;
            List czippers = (List)var176;
            if (!deep) {
               return czippers;
            }

            if (tparams == null) {
               throw null;
            }

            List flatMap_rest = tparams;
            .colon.colon flatMap_h = null;

            for(.colon.colon flatMap_t = null; flatMap_rest != scala.collection.immutable.Nil..MODULE$; flatMap_rest = (List)flatMap_rest.tail()) {
               Trees.TypeDef var102 = (Trees.TypeDef)flatMap_rest.head();

               .colon.colon flatMap_nx;
               for(Iterator flatMap_it = $anonfun$getAnnotationZippers$14(this, tparams, tdef, var102).iterator(); flatMap_it.hasNext(); flatMap_t = flatMap_nx) {
                  flatMap_nx = new .colon.colon(flatMap_it.next(), scala.collection.immutable.Nil..MODULE$);
                  if (flatMap_t == null) {
                     flatMap_h = flatMap_nx;
                  } else {
                     flatMap_t.next_$eq(flatMap_nx);
                  }
               }
            }

            if (flatMap_h == null) {
               var176 = scala.collection.immutable.Nil..MODULE$;
            } else {
               Statics.releaseFence();
               var176 = flatMap_h;
            }

            Object var130 = null;
            Object var131 = null;
            Object var132 = null;
            Object var133 = null;
            Object var134 = null;
            List tzippers = (List)var176;
            return (List)czippers.concat(tzippers);
         }
      }

      if (tree instanceof Trees.ModuleDef) {
         Trees.ModuleDef var22 = (Trees.ModuleDef)tree;
         Trees.Modifiers mods = var22.mods();
         List var174 = mods.annotations();
         if (var174 == null) {
            throw null;
         } else {
            List map_this = var174;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               return scala.collection.immutable.Nil..MODULE$;
            } else {
               Trees.Tree var103 = (Trees.Tree)map_this.head();
               .colon.colon map_h = new .colon.colon($anonfun$getAnnotationZippers$18(this, var22, mods, var103), scala.collection.immutable.Nil..MODULE$);
               .colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var103 = (Trees.Tree)map_rest.head();
                  .colon.colon map_nx = new .colon.colon($anonfun$getAnnotationZippers$18(this, var22, mods, var103), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               return map_h;
            }
         }
      } else if (tree instanceof Trees.DefDef) {
         Trees.DefDef var24 = (Trees.DefDef)tree;
         Trees.Modifiers mods = var24.mods();
         List tparams = var24.tparams();
         List vparamss = var24.vparamss();
         List var169 = mods.annotations();
         if (var169 == null) {
            throw null;
         } else {
            List map_this = var169;
            Object var170;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               var170 = scala.collection.immutable.Nil..MODULE$;
            } else {
               Trees.Tree var104 = (Trees.Tree)map_this.head();
               .colon.colon map_h = new .colon.colon($anonfun$getAnnotationZippers$20(this, var24, mods, var104), scala.collection.immutable.Nil..MODULE$);
               .colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var104 = (Trees.Tree)map_rest.head();
                  .colon.colon map_nx = new .colon.colon($anonfun$getAnnotationZippers$20(this, var24, mods, var104), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var170 = map_h;
            }

            Object var135 = null;
            Object var136 = null;
            Object var137 = null;
            Object var138 = null;
            Object var139 = null;
            List dzippers = (List)var170;
            if (!deep) {
               return dzippers;
            } else if (tparams == null) {
               throw null;
            } else {
               List flatMap_rest = tparams;
               .colon.colon flatMap_h = null;

               for(.colon.colon flatMap_t = null; flatMap_rest != scala.collection.immutable.Nil..MODULE$; flatMap_rest = (List)flatMap_rest.tail()) {
                  Trees.TypeDef var105 = (Trees.TypeDef)flatMap_rest.head();

                  .colon.colon flatMap_nx;
                  for(Iterator flatMap_it = $anonfun$getAnnotationZippers$22(this, tparams, var24, var105).iterator(); flatMap_it.hasNext(); flatMap_t = flatMap_nx) {
                     flatMap_nx = new .colon.colon(flatMap_it.next(), scala.collection.immutable.Nil..MODULE$);
                     if (flatMap_t == null) {
                        flatMap_h = flatMap_nx;
                     } else {
                        flatMap_t.next_$eq(flatMap_nx);
                     }
                  }
               }

               if (flatMap_h == null) {
                  var170 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Statics.releaseFence();
                  var170 = flatMap_h;
               }

               Object var140 = null;
               Object var141 = null;
               Object var142 = null;
               Object var143 = null;
               Object var144 = null;
               List tzippers = (List)var170;
               if (vparamss == null) {
                  throw null;
               } else {
                  List flatMap_rest = vparamss;
                  .colon.colon flatMap_h = null;

                  for(.colon.colon flatMap_t = null; flatMap_rest != scala.collection.immutable.Nil..MODULE$; flatMap_rest = (List)flatMap_rest.tail()) {
                     List var106 = (List)flatMap_rest.head();

                     .colon.colon flatMap_nx;
                     for(Iterator flatMap_it = $anonfun$getAnnotationZippers$26(this, vparamss, var24, var106).iterator(); flatMap_it.hasNext(); flatMap_t = flatMap_nx) {
                        flatMap_nx = new .colon.colon(flatMap_it.next(), scala.collection.immutable.Nil..MODULE$);
                        if (flatMap_t == null) {
                           flatMap_h = flatMap_nx;
                        } else {
                           flatMap_t.next_$eq(flatMap_nx);
                        }
                     }
                  }

                  if (flatMap_h == null) {
                     var170 = scala.collection.immutable.Nil..MODULE$;
                  } else {
                     Statics.releaseFence();
                     var170 = flatMap_h;
                  }

                  Object var145 = null;
                  Object var146 = null;
                  Object var147 = null;
                  Object var148 = null;
                  Object var149 = null;
                  List vzippers = (List)var170;
                  IterableOps var173 = (IterableOps)dzippers.concat(tzippers);
                  if (var173 == null) {
                     throw null;
                  } else {
                     return (List)var173.concat(vzippers);
                  }
               }
            }
         }
      } else if (tree instanceof Trees.ValDef) {
         Trees.ValDef var31 = (Trees.ValDef)tree;
         Trees.Modifiers mods = var31.mods();
         List var168 = mods.annotations();
         if (var168 == null) {
            throw null;
         } else {
            List map_this = var168;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               return scala.collection.immutable.Nil..MODULE$;
            } else {
               Trees.Tree var107 = (Trees.Tree)map_this.head();
               .colon.colon map_h = new .colon.colon($anonfun$getAnnotationZippers$31(this, var31, mods, var107), scala.collection.immutable.Nil..MODULE$);
               .colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var107 = (Trees.Tree)map_rest.head();
                  .colon.colon map_nx = new .colon.colon($anonfun$getAnnotationZippers$31(this, var31, mods, var107), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               return map_h;
            }
         }
      } else if (tree instanceof Trees.TypeDef) {
         Trees.TypeDef var33 = (Trees.TypeDef)tree;
         Trees.Modifiers mods = var33.mods();
         List tparams = var33.tparams();
         List var10000 = mods.annotations();
         if (var10000 == null) {
            throw null;
         } else {
            List map_this = var10000;
            Object var166;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               var166 = scala.collection.immutable.Nil..MODULE$;
            } else {
               Trees.Tree var108 = (Trees.Tree)map_this.head();
               .colon.colon map_h = new .colon.colon($anonfun$getAnnotationZippers$33(this, var33, mods, var108), scala.collection.immutable.Nil..MODULE$);
               .colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var108 = (Trees.Tree)map_rest.head();
                  .colon.colon map_nx = new .colon.colon($anonfun$getAnnotationZippers$33(this, var33, mods, var108), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var166 = map_h;
            }

            Object var150 = null;
            Object var151 = null;
            Object var152 = null;
            Object var153 = null;
            Object var154 = null;
            List tzippers = (List)var166;
            if (!deep) {
               return tzippers;
            } else if (tparams == null) {
               throw null;
            } else {
               List flatMap_rest = tparams;
               .colon.colon flatMap_h = null;

               for(.colon.colon flatMap_t = null; flatMap_rest != scala.collection.immutable.Nil..MODULE$; flatMap_rest = (List)flatMap_rest.tail()) {
                  Trees.TypeDef var109 = (Trees.TypeDef)flatMap_rest.head();

                  .colon.colon flatMap_nx;
                  for(Iterator flatMap_it = $anonfun$getAnnotationZippers$35(this, tparams, var33, var109).iterator(); flatMap_it.hasNext(); flatMap_t = flatMap_nx) {
                     flatMap_nx = new .colon.colon(flatMap_it.next(), scala.collection.immutable.Nil..MODULE$);
                     if (flatMap_t == null) {
                        flatMap_h = flatMap_nx;
                     } else {
                        flatMap_t.next_$eq(flatMap_nx);
                     }
                  }
               }

               if (flatMap_h == null) {
                  var166 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Statics.releaseFence();
                  var166 = flatMap_h;
               }

               Object var155 = null;
               Object var156 = null;
               Object var157 = null;
               Object var158 = null;
               Object var159 = null;
               List ttzippers = (List)var166;
               return (List)tzippers.concat(ttzippers);
            }
         }
      } else {
         return scala.collection.immutable.Nil..MODULE$;
      }
   }

   private boolean isConstr$1(final Trees.Tree tree, final boolean classOnly$1) {
      while(true) {
         boolean var3 = false;
         Trees.Apply var4 = null;
         if (!(tree instanceof Trees.Block)) {
            if (tree instanceof Trees.Apply) {
               var3 = true;
               var4 = (Trees.Apply)tree;
               Trees.Tree var6 = var4.fun();
               if (var6 instanceof Trees.Select && ((Trees.Select)var6).qualifier() instanceof Trees.New) {
                  return false;
               }
            }

            if (!var3) {
               return false;
            }

            Trees.Tree fun = var4.fun();
            if (fun.symbol() != null) {
               if (classOnly$1) {
                  if (fun.symbol().isClassConstructor()) {
                     return true;
                  }
               } else if (fun.symbol().isConstructor()) {
                  return true;
               }
            }

            return false;
         }

         Trees.Tree expr = ((Trees.Block)tree).expr();
         TreeInfo var10000 = (TreeInfo)this;
         tree = expr;
         this = var10000;
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$splitAtSuper$1(final MacroAnnotionTreeInfo $this, final boolean classOnly$1, final Trees.Tree x$37) {
      return !$this.isConstr$1(x$37, classOnly$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$splitAtSuper$2(final MacroAnnotionTreeInfo $this, final boolean classOnly$1, final Trees.Tree x$39) {
      return $this.isConstr$1(x$39, classOnly$1);
   }

   static void $init$(final MacroAnnotionTreeInfo $this) {
   }

   // $FF: synthetic method
   static Object $anonfun$anyConstructorHasDefault$1$adapted(final MacroAnnotionTreeInfo $this, final Trees.Tree x0$1) {
      return BoxesRunTime.boxToBoolean($anonfun$anyConstructorHasDefault$1($this, x0$1));
   }

   // $FF: synthetic method
   static Object $anonfun$splitAtSuper$1$adapted(final MacroAnnotionTreeInfo $this, final boolean classOnly$1, final Trees.Tree x$37) {
      return BoxesRunTime.boxToBoolean($anonfun$splitAtSuper$1($this, classOnly$1, x$37));
   }

   // $FF: synthetic method
   static Object $anonfun$splitAtSuper$2$adapted(final MacroAnnotionTreeInfo $this, final boolean classOnly$1, final Trees.Tree x$39) {
      return BoxesRunTime.boxToBoolean($anonfun$splitAtSuper$2($this, classOnly$1, x$39));
   }

   // $FF: synthetic method
   static Object $anonfun$anyConstructorHasDefault$2$adapted(final Trees.ValDef x$10) {
      return BoxesRunTime.boxToBoolean($anonfun$anyConstructorHasDefault$2(x$10));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class AnnotationZipper implements Product, Serializable {
      private final Trees.Tree annotation;
      private final Trees.Tree annottee;
      private final Trees.Tree owner;
      // $FF: synthetic field
      public final TreeInfo $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Trees.Tree annotation() {
         return this.annotation;
      }

      public Trees.Tree annottee() {
         return this.annottee;
      }

      public Trees.Tree owner() {
         return this.owner;
      }

      public AnnotationZipper copy(final Trees.Tree annotation, final Trees.Tree annottee, final Trees.Tree owner) {
         return this.scala$reflect$internal$MacroAnnotionTreeInfo$AnnotationZipper$$$outer().new AnnotationZipper(annotation, annottee, owner);
      }

      public Trees.Tree copy$default$1() {
         return this.annotation();
      }

      public Trees.Tree copy$default$2() {
         return this.annottee();
      }

      public Trees.Tree copy$default$3() {
         return this.owner();
      }

      public String productPrefix() {
         return "AnnotationZipper";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.annotation();
            case 1:
               return this.annottee();
            case 2:
               return this.owner();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof AnnotationZipper;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "annotation";
            case 1:
               return "annottee";
            case 2:
               return "owner";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof AnnotationZipper && ((AnnotationZipper)x$1).scala$reflect$internal$MacroAnnotionTreeInfo$AnnotationZipper$$$outer() == this.scala$reflect$internal$MacroAnnotionTreeInfo$AnnotationZipper$$$outer()) {
               AnnotationZipper var2 = (AnnotationZipper)x$1;
               Trees.Tree var10000 = this.annotation();
               Trees.Tree var3 = var2.annotation();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               var10000 = this.annottee();
               Trees.Tree var4 = var2.annottee();
               if (var10000 == null) {
                  if (var4 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var4)) {
                  return false;
               }

               var10000 = this.owner();
               Trees.Tree var5 = var2.owner();
               if (var10000 == null) {
                  if (var5 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var5)) {
                  return false;
               }

               if (var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public TreeInfo scala$reflect$internal$MacroAnnotionTreeInfo$AnnotationZipper$$$outer() {
         return this.$outer;
      }

      public AnnotationZipper(final Trees.Tree annotation, final Trees.Tree annottee, final Trees.Tree owner) {
         this.annotation = annotation;
         this.annottee = annottee;
         this.owner = owner;
         if (MacroAnnotionTreeInfo.this == null) {
            throw null;
         } else {
            this.$outer = MacroAnnotionTreeInfo.this;
            super();
         }
      }
   }

   public class AnnotationZipper$ extends AbstractFunction3 implements Serializable {
      // $FF: synthetic field
      private final TreeInfo $outer;

      public final String toString() {
         return "AnnotationZipper";
      }

      public AnnotationZipper apply(final Trees.Tree annotation, final Trees.Tree annottee, final Trees.Tree owner) {
         return this.$outer.new AnnotationZipper(annotation, annottee, owner);
      }

      public Option unapply(final AnnotationZipper x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.annotation(), x$0.annottee(), x$0.owner())));
      }

      public AnnotationZipper$() {
         if (MacroAnnotionTreeInfo.this == null) {
            throw null;
         } else {
            this.$outer = MacroAnnotionTreeInfo.this;
            super();
         }
      }
   }

   private class PatchedSyntacticClassDef$ {
      // $FF: synthetic field
      private final TreeInfo $outer;

      public Trees.ClassDef apply(final Trees.Modifiers mods, final Names.TypeName name, final List tparams, final Trees.Modifiers constrMods, final List vparamss, final List earlyDefs, final List parents, final Trees.Tree selfType, final List body) {
         ReificationSupport.ReificationSupportImpl.SyntacticClassDef$ var10000 = this.$outer.global().build().SyntacticClassDef();
         if (vparamss == null) {
            throw null;
         } else {
            Object var10005;
            if (vparamss == scala.collection.immutable.Nil..MODULE$) {
               var10005 = scala.collection.immutable.Nil..MODULE$;
            } else {
               .colon.colon map_h = new .colon.colon($anonfun$apply$1((List)vparamss.head()), scala.collection.immutable.Nil..MODULE$);
               .colon.colon map_t = map_h;

               for(List map_rest = (List)vparamss.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  .colon.colon map_nx = new .colon.colon($anonfun$apply$1((List)map_rest.head()), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10005 = map_h;
            }

            Object var14 = null;
            Object var15 = null;
            Object var16 = null;
            Object var17 = null;
            return var10000.apply((Trees.Modifiers)mods, (Names.TypeName)name, tparams, (Trees.Modifiers)constrMods, (List)var10005, earlyDefs, parents, (Trees.Tree)selfType, body);
         }
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$apply$2(final Trees.Tree x$36) {
         return x$36.duplicate();
      }

      // $FF: synthetic method
      public static final List $anonfun$apply$1(final List x$35) {
         if (x$35 == scala.collection.immutable.Nil..MODULE$) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            .colon.colon map_h = new .colon.colon(((Trees.Tree)x$35.head()).duplicate(), scala.collection.immutable.Nil..MODULE$);
            .colon.colon map_t = map_h;

            for(List map_rest = (List)x$35.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               .colon.colon map_nx = new .colon.colon(((Trees.Tree)map_rest.head()).duplicate(), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            return map_h;
         }
      }

      public PatchedSyntacticClassDef$() {
         if (MacroAnnotionTreeInfo.this == null) {
            throw null;
         } else {
            this.$outer = MacroAnnotionTreeInfo.this;
            super();
         }
      }
   }
}
