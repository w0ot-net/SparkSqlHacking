package scala.reflect.internal.transform;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Definitions;
import scala.reflect.internal.ExistentialsAndSkolems;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.StdAttachments;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.TypeMaps;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EfaB\u0011#!\u0003\r\ta\u000b\u0005\u0006a\u0001!\t!\r\u0005\bk\u0001\u0011\rQ\"\u00017\r\u0011Y\u0004\u0001\u0011\u001f\t\u00111\u001b!Q3A\u0005\u00025C\u0001\"V\u0002\u0003\u0012\u0003\u0006IA\u0014\u0005\u0006-\u000e!\ta\u0016\u0005\b5\u000e\t\t\u0011\"\u0001\\\u0011\u001di6!%A\u0005\u0002yCq![\u0002\u0002\u0002\u0013\u0005#\u000eC\u0004t\u0007\u0005\u0005I\u0011\u0001;\t\u000fa\u001c\u0011\u0011!C\u0001s\"AqpAA\u0001\n\u0003\n\t\u0001C\u0005\u0002\u0010\r\t\t\u0011\"\u0001\u0002\u0012!I\u00111D\u0002\u0002\u0002\u0013\u0005\u0013Q\u0004\u0005\n\u0003C\u0019\u0011\u0011!C!\u0003GA\u0011\"!\n\u0004\u0003\u0003%\t%a\n\t\u0013\u0005%2!!A\u0005B\u0005-r!CA\u0018\u0001\u0005\u0005\t\u0012AA\u0019\r!Y\u0004!!A\t\u0002\u0005M\u0002B\u0002,\u0014\t\u0003\tY\u0005C\u0005\u0002&M\t\t\u0011\"\u0012\u0002(!I\u0011QJ\n\u0002\u0002\u0013\u0005\u0015q\n\u0005\n\u0003'\u001a\u0012\u0011!CA\u0003+Bq!!\u0019\u0001\t\u0013\t\u0019\u0007C\u0005\u0002t\u0001\u0011\r\u0011\"\u0001\u0002v\u001d9\u0011Q\u0011\u0001\t\u0002\u0005\u001deaBAE\u0001!\u0005\u00111\u0012\u0005\u0007-n!\t!!$\t\u000f\u0005M3\u0004\"\u0001\u0002\u0010\"I\u0011Q\u0013\u0001CB\u0013%\u0011Q\u000f\u0005\b\u0003/\u0003A\u0011BAM\u0011\u001d\t9\u000b\u0001C\u0001\u0003S\u0013q!\u00168DkJ\u0014\u0018P\u0003\u0002$I\u0005IAO]1og\u001a|'/\u001c\u0006\u0003K\u0019\n\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003O!\nqA]3gY\u0016\u001cGOC\u0001*\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0017\u0011\u00055rS\"\u0001\u0015\n\u0005=B#AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002eA\u0011QfM\u0005\u0003i!\u0012A!\u00168ji\u00061q\r\\8cC2,\u0012a\u000e\t\u0003qej\u0011\u0001J\u0005\u0003u\u0011\u00121bU=nE>dG+\u00192mK\n9b+\u0019:be\u001e\u001c8+_7c_2\fE\u000f^1dQ6,g\u000e^\n\u0005\u00071j\u0004\t\u0005\u0002.}%\u0011q\b\u000b\u0002\b!J|G-^2u!\t\t\u0015J\u0004\u0002C\u000f:\u00111IR\u0007\u0002\t*\u0011QIK\u0001\u0007yI|w\u000e\u001e \n\u0003%J!\u0001\u0013\u0015\u0002\u000fA\f7m[1hK&\u0011!j\u0013\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0011\"\nAB^1sCJ<W*\u001a;i_\u0012,\u0012A\u0014\t\u0003\u001fFs!\u0001\u0015\u0002\u000e\u0003\u0001I!AU*\u0003\rMKXNY8m\u0013\t!FEA\u0004Ts6\u0014w\u000e\\:\u0002\u001bY\f'/\u0019:h\u001b\u0016$\bn\u001c3!\u0003\u0019a\u0014N\\5u}Q\u0011\u0001,\u0017\t\u0003!\u000eAQ\u0001\u0014\u0004A\u00029\u000bAaY8qsR\u0011\u0001\f\u0018\u0005\b\u0019\u001e\u0001\n\u00111\u0001O\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u0018\u0016\u0003\u001d\u0002\\\u0013!\u0019\t\u0003E\u001el\u0011a\u0019\u0006\u0003I\u0016\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0019D\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001n\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001l!\ta\u0017/D\u0001n\u0015\tqw.\u0001\u0003mC:<'\"\u00019\u0002\t)\fg/Y\u0005\u0003e6\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A;\u0011\u000552\u0018BA<)\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\tQX\u0010\u0005\u0002.w&\u0011A\u0010\u000b\u0002\u0004\u0003:L\bb\u0002@\f\u0003\u0003\u0005\r!^\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\r\u0001#BA\u0003\u0003\u0017QXBAA\u0004\u0015\r\tI\u0001K\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u0007\u0003\u000f\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111CA\r!\ri\u0013QC\u0005\u0004\u0003/A#a\u0002\"p_2,\u0017M\u001c\u0005\b}6\t\t\u00111\u0001{\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007-\fy\u0002C\u0004\u007f\u001d\u0005\u0005\t\u0019A;\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!^\u0001\ti>\u001cFO]5oOR\t1.\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003'\ti\u0003C\u0004\u007f#\u0005\u0005\t\u0019\u0001>\u0002/Y\u000b'/\u0019:hgNKXNY8m\u0003R$\u0018m\u00195nK:$\bC\u0001)\u0014'\u0015\u0019\u0012QGA!!\u0019\t9$!\u0010O16\u0011\u0011\u0011\b\u0006\u0004\u0003wA\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u007f\tIDA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!a\u0011\u0002J5\u0011\u0011Q\t\u0006\u0004\u0003\u000fz\u0017AA5p\u0013\rQ\u0015Q\t\u000b\u0003\u0003c\tQ!\u00199qYf$2\u0001WA)\u0011\u0015ae\u00031\u0001O\u0003\u001d)h.\u00199qYf$B!a\u0016\u0002^A!Q&!\u0017O\u0013\r\tY\u0006\u000b\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005}s#!AA\u0002a\u000b1\u0001\u001f\u00131\u0003-)\u0007\u0010]1oI\u0006c\u0017.Y:\u0015\t\u0005\u0015\u0014q\u000e\t\u0004\u001f\u0006\u001d\u0014\u0002BA5\u0003W\u0012A\u0001V=qK&\u0019\u0011Q\u000e\u0013\u0003\u000bQK\b/Z:\t\u000f\u0005E\u0004\u00041\u0001\u0002f\u0005\u0011A\u000f]\u0001\bk:\u001cWO\u001d:z+\t\t9\bE\u0002P\u0003sJA!a\u001f\u0002~\t9A+\u001f9f\u001b\u0006\u0004\u0018\u0002BA@\u0003\u0003\u0013\u0001\u0002V=qK6\u000b\u0007o\u001d\u0006\u0004\u0003\u0007#\u0013a\u0001;qK\u00061B)Z:vO\u0006\u0014X\r\u001a)be\u0006lW\r^3s)f\u0004X\r\u0005\u0002Q7\t1B)Z:vO\u0006\u0014X\r\u001a)be\u0006lW\r^3s)f\u0004Xm\u0005\u0002\u001cYQ\u0011\u0011q\u0011\u000b\u0005\u0003#\u000b\u0019\nE\u0003.\u00033\n)\u0007C\u0004\u0002\u0004v\u0001\r!!\u001a\u0002\u0017Ut7-\u001e:ssRK\b/Z\u0001\u0013m\u0006\u0014\u0018M]4G_J<\u0018M\u001d3feNKX\u000eF\u0004O\u00037\u000by*a)\t\r\u0005uu\u00041\u0001O\u00031\u0019WO\u001d:f]R\u001cE.Y:t\u0011\u0019\t\tk\ba\u0001\u001d\u00069qN]5h'fl\u0007bBAS?\u0001\u0007\u0011QM\u0001\b]\u0016<\u0018J\u001c4p\u00035!(/\u00198tM>\u0014X.\u00138g_R1\u0011QMAV\u0003_Ca!!,!\u0001\u0004q\u0015aA:z[\"9\u0011\u0011\u000f\u0011A\u0002\u0005\u0015\u0004"
)
public interface UnCurry {
   VarargsSymbolAttachment$ VarargsSymbolAttachment();

   DesugaredParameterType$ DesugaredParameterType();

   void scala$reflect$internal$transform$UnCurry$_setter_$uncurry_$eq(final TypeMaps.TypeMap x$1);

   void scala$reflect$internal$transform$UnCurry$_setter_$scala$reflect$internal$transform$UnCurry$$uncurryType_$eq(final TypeMaps.TypeMap x$1);

   SymbolTable global();

   // $FF: synthetic method
   static Types.Type scala$reflect$internal$transform$UnCurry$$expandAlias$(final UnCurry $this, final Types.Type tp) {
      return $this.scala$reflect$internal$transform$UnCurry$$expandAlias(tp);
   }

   default Types.Type scala$reflect$internal$transform$UnCurry$$expandAlias(final Types.Type tp) {
      return !tp.isHigherKinded() ? tp.normalize() : tp;
   }

   TypeMaps.TypeMap uncurry();

   TypeMaps.TypeMap scala$reflect$internal$transform$UnCurry$$uncurryType();

   // $FF: synthetic method
   static Symbols.Symbol scala$reflect$internal$transform$UnCurry$$varargForwarderSym$(final UnCurry $this, final Symbols.Symbol currentClass, final Symbols.Symbol origSym, final Types.Type newInfo) {
      return $this.scala$reflect$internal$transform$UnCurry$$varargForwarderSym(currentClass, origSym, newInfo);
   }

   default Symbols.Symbol scala$reflect$internal$transform$UnCurry$$varargForwarderSym(final Symbols.Symbol currentClass, final Symbols.Symbol origSym, final Types.Type newInfo) {
      Symbols.Symbol forwSym = origSym.cloneSymbol(currentClass, 8796095119360L | origSym.flags() & -17L, origSym.name().toTermName()).withoutAnnotations();
      SymbolTable var10000 = this.global();
      List var10001 = forwSym.paramss();
      List foreach2_xs2 = origSym.info().paramss();
      List foreach2_xs1 = var10001;
      if (var10000 == null) {
         throw null;
      } else {
         List foreach2_ys1 = foreach2_xs1;

         for(List foreach2_ys2 = foreach2_xs2; !foreach2_ys1.isEmpty() && !foreach2_ys2.isEmpty(); foreach2_ys2 = (List)foreach2_ys2.tail()) {
            var10000 = (SymbolTable)foreach2_ys1.head();
            List var10 = (List)foreach2_ys2.head();
            List var9 = (List)var10000;
            $anonfun$varargForwarderSym$1(this, var9, var10);
            foreach2_ys1 = (List)foreach2_ys1.tail();
         }

         Object var13 = null;
         Object var14 = null;
         Object var11 = null;
         Object var12 = null;
         origSym.updateAttachment(new VarargsSymbolAttachment(forwSym), .MODULE$.apply(VarargsSymbolAttachment.class));
         return forwSym;
      }
   }

   // $FF: synthetic method
   static Types.Type transformInfo$(final UnCurry $this, final Symbols.Symbol sym, final Types.Type tp) {
      return $this.transformInfo(sym, tp);
   }

   default Types.Type transformInfo(final Symbols.Symbol sym, final Types.Type tp) {
      if (sym.isType()) {
         return this.scala$reflect$internal$transform$UnCurry$$uncurryType().apply(tp);
      } else if (sym.hasFlag(256L) && !sym.isStatic()) {
         sym.setFlag(4194368L);
         return this.global().new MethodType(scala.collection.immutable.Nil..MODULE$, this.uncurry().apply(tp));
      } else {
         return this.uncurry().apply(tp);
      }
   }

   private Types.Type toArrayType$1(final Types.Type tp, final Symbols.Symbol newParam) {
      Types.Type arg = this.global().definitions().elementType(this.global().definitions().SeqClass(), tp);
      Types.Type var10000;
      if (arg.typeSymbol().isTypeParameterOrSkolem() && !arg.$less$colon$less(this.global().definitions().AnyRefTpe())) {
         newParam.updateAttachment(this.global().new TypeParamVarargsAttachment(arg), .MODULE$.apply(StdAttachments.TypeParamVarargsAttachment.class));
         var10000 = this.global().definitions().ObjectTpe();
      } else {
         var10000 = arg;
      }

      Types.Type elem = var10000;
      return this.global().definitions().arrayType(elem);
   }

   // $FF: synthetic method
   static void $anonfun$varargForwarderSym$2(final UnCurry $this, final Symbols.Symbol p, final Symbols.Symbol sym) {
      Definitions.definitions$ var10000 = $this.global().definitions();
      if (sym == null) {
         throw null;
      } else if (var10000.isRepeatedParamType(sym.tpe_$times())) {
         p.setInfo($this.toArrayType$1(p.info(), p));
      }
   }

   // $FF: synthetic method
   static void $anonfun$varargForwarderSym$1(final UnCurry $this, final List fsps, final List origPs) {
      if ($this.global() == null) {
         throw null;
      } else {
         List foreach2_ys1 = fsps;

         for(List foreach2_ys2 = origPs; !foreach2_ys1.isEmpty() && !foreach2_ys2.isEmpty(); foreach2_ys2 = (List)foreach2_ys2.tail()) {
            Object var10000 = foreach2_ys1.head();
            Symbols.Symbol var6 = (Symbols.Symbol)foreach2_ys2.head();
            Symbols.Symbol var5 = (Symbols.Symbol)var10000;
            $anonfun$varargForwarderSym$2($this, var5, var6);
            foreach2_ys1 = (List)foreach2_ys1.tail();
         }

      }
   }

   static void $init$(final UnCurry $this) {
      $this.scala$reflect$internal$transform$UnCurry$_setter_$uncurry_$eq($this.new TypeMap() {
         // $FF: synthetic field
         private final UnCurry $outer;

         public Types.Type apply(final Types.Type tp0) {
            while(true) {
               Types.Type tp = this.$outer.scala$reflect$internal$transform$UnCurry$$expandAlias(tp0);
               boolean var3 = false;
               Types.MethodType var4 = null;
               if (tp instanceof Types.MethodType) {
                  var3 = true;
                  var4 = (Types.MethodType)tp;
                  List params = var4.params();
                  Types.Type var6 = var4.resultType();
                  if (var6 instanceof Types.MethodType) {
                     Types.MethodType var7 = (Types.MethodType)var6;
                     List params1 = var7.params();
                     Types.Type restpe = var7.resultType();
                     List existentiallyAbstractedParam1s = (new TypeMaps.TypeMap(params) {
                        // $FF: synthetic field
                        private final <undefinedtype> $outer;
                        private final List params$1;

                        public Types.Type apply(final Types.Type tp) {
                           SymbolTable var10000 = this.$outer.scala$reflect$internal$transform$UnCurry$$anon$$$outer().global();
                           List var10001 = this.params$1;
                           SymbolTable var10002 = this.$outer.scala$reflect$internal$transform$UnCurry$$anon$$$outer().global();
                           if (var10002 == null) {
                              throw null;
                           } else {
                              SymbolTable packSymbols$default$3_this = var10002;
                              Symbols.NoSymbol var6 = packSymbols$default$3_this.NoSymbol();
                              Object var5 = null;
                              Symbols.NoSymbol packSymbols_rawOwner = var6;
                              List packSymbols_hidden = var10001;
                              if (var10000 == null) {
                                 throw null;
                              } else {
                                 return ExistentialsAndSkolems.packSymbols$(var10000, packSymbols_hidden, tp, packSymbols_rawOwner);
                              }
                           }
                        }

                        public {
                           if (<VAR_NAMELESS_ENCLOSURE> == null) {
                              throw null;
                           } else {
                              this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                              this.params$1 = params$1;
                           }
                        }
                     }).mapOver(params1);
                     Types.Type substitutedResult = restpe.substSym(params1, existentiallyAbstractedParam1s);
                     tp0 = this.$outer.global().new MethodType(existentiallyAbstractedParam1s.$colon$colon$colon(params), substitutedResult);
                     continue;
                  }
               }

               if (var3) {
                  Types.Type var12 = var4.resultType();
                  if (var12 instanceof Types.ExistentialType && ((Types.ExistentialType)var12).underlying() instanceof Types.MethodType) {
                     throw this.$outer.global().abort("unexpected curried method types with intervening existential");
                  }
               }

               if (var3) {
                  List var13 = var4.params();
                  Types.Type restpe = var4.resultType();
                  if (var13 instanceof scala.collection.immutable..colon.colon) {
                     scala.collection.immutable..colon.colon var15 = (scala.collection.immutable..colon.colon)var13;
                     Symbols.Symbol h = (Symbols.Symbol)var15.head();
                     List t = var15.next$access$1();
                     if (h.isImplicit()) {
                        Types.MethodType var10000 = new Types.MethodType;
                        SymbolTable var10002 = this.$outer.global();
                        Symbols.Symbol var18 = h.cloneSymbol().resetFlag(512L);
                        if (t == null) {
                           throw null;
                        }

                        var10000.<init>(new scala.collection.immutable..colon.colon(var18, t), restpe);
                        tp0 = var10000;
                        continue;
                     }
                  }
               }

               if (tp instanceof Types.NullaryMethodType) {
                  Types.Type restpe = ((Types.NullaryMethodType)tp).resultType();
                  tp0 = this.$outer.global().new MethodType(scala.collection.immutable.Nil..MODULE$, restpe);
               } else {
                  if (tp != null) {
                     Option var20 = this.$outer.DesugaredParameterType().unapply(tp);
                     if (!var20.isEmpty()) {
                        tp0 = (Types.Type)var20.get();
                        continue;
                     }
                  }

                  return this.$outer.scala$reflect$internal$transform$UnCurry$$expandAlias(tp.mapOver(this));
               }
            }
         }

         // $FF: synthetic method
         public UnCurry scala$reflect$internal$transform$UnCurry$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (UnCurry.this == null) {
               throw null;
            } else {
               this.$outer = UnCurry.this;
            }
         }
      });
      $this.scala$reflect$internal$transform$UnCurry$_setter_$scala$reflect$internal$transform$UnCurry$$uncurryType_$eq($this.new TypeMap() {
         // $FF: synthetic field
         private final UnCurry $outer;

         public Types.Type apply(final Types.Type tp0) {
            Types.Type tp = this.$outer.scala$reflect$internal$transform$UnCurry$$expandAlias(tp0);
            if (tp instanceof Types.ClassInfoType) {
               Types.ClassInfoType var3 = (Types.ClassInfoType)tp;
               List parents = var3.parents();
               Scopes.Scope decls = var3.decls();
               Symbols.Symbol clazz = var3.typeSymbol();
               if (!clazz.isJavaDefined()) {
                  TypeMaps.TypeMap mapConserve_f = this.$outer.uncurry();
                  if (parents == null) {
                     throw null;
                  }

                  List mapConserve_loop$3_pending = parents;
                  List mapConserve_loop$3_unchanged = parents;
                  scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast = null;
                  List mapConserve_loop$3_mappedHead = null;

                  while(!mapConserve_loop$3_pending.isEmpty()) {
                     Object mapConserve_loop$3_head0 = mapConserve_loop$3_pending.head();
                     Object mapConserve_loop$3_head1 = mapConserve_f.apply(mapConserve_loop$3_head0);
                     if (mapConserve_loop$3_head1 == mapConserve_loop$3_head0) {
                        mapConserve_loop$3_pending = (List)mapConserve_loop$3_pending.tail();
                        mapConserve_loop$3_unchanged = mapConserve_loop$3_unchanged;
                        mapConserve_loop$3_mappedLast = mapConserve_loop$3_mappedLast;
                        mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead;
                     } else {
                        List mapConserve_loop$3_xc = mapConserve_loop$3_unchanged;
                        List mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_mappedHead;

                        scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast1;
                        for(mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_mappedLast; mapConserve_loop$3_xc != mapConserve_loop$3_pending; mapConserve_loop$3_xc = (List)mapConserve_loop$3_xc.tail()) {
                           scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_xc.head(), scala.collection.immutable.Nil..MODULE$);
                           if (mapConserve_loop$3_mappedHead1 == null) {
                              mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                           }

                           if (mapConserve_loop$3_mappedLast1 != null) {
                              mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                           }

                           mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_next;
                        }

                        scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_head1, scala.collection.immutable.Nil..MODULE$);
                        if (mapConserve_loop$3_mappedHead1 == null) {
                           mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                        }

                        if (mapConserve_loop$3_mappedLast1 != null) {
                           mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                        }

                        List mapConserve_loop$3_tail0 = (List)mapConserve_loop$3_pending.tail();
                        mapConserve_loop$3_pending = mapConserve_loop$3_tail0;
                        mapConserve_loop$3_unchanged = mapConserve_loop$3_tail0;
                        mapConserve_loop$3_mappedLast = mapConserve_loop$3_next;
                        mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead1;
                     }
                  }

                  Object var10000;
                  if (mapConserve_loop$3_mappedHead == null) {
                     var10000 = mapConserve_loop$3_unchanged;
                  } else {
                     mapConserve_loop$3_mappedLast.next_$eq(mapConserve_loop$3_unchanged);
                     var10000 = mapConserve_loop$3_mappedHead;
                  }

                  mapConserve_loop$3_mappedHead = null;
                  Object var27 = null;
                  Object var28 = null;
                  Object var29 = null;
                  Object var30 = null;
                  Object var31 = null;
                  Object var32 = null;
                  Object var33 = null;
                  Object var34 = null;
                  Object var35 = null;
                  Object var36 = null;
                  Object var37 = null;
                  List mapConserve_result = (List)var10000;
                  Statics.releaseFence();
                  var10000 = mapConserve_result;
                  Object var24 = null;
                  mapConserve_result = null;
                  List parents1 = (List)var10000;
                  ListBuffer var39 = scala.collection.mutable.ListBuffer..MODULE$;
                  ListBuffer varargOverloads = new ListBuffer();
                  decls.withFilter((decl) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(this, decl))).foreach((decl) -> {
                     SymbolTable var10000 = this.$outer.global();
                     List mexists_xss = decl.paramss();
                     if (var10000 == null) {
                        throw null;
                     } else if (mexists_xss == null) {
                        throw null;
                     } else {
                        List mexists_exists_these = mexists_xss;

                        while(true) {
                           if (mexists_exists_these.isEmpty()) {
                              var14 = false;
                              break;
                           }

                           List var7 = (List)mexists_exists_these.head();
                           if (var7 == null) {
                              throw null;
                           }

                           List exists_these = var7;

                           while(true) {
                              if (exists_these.isEmpty()) {
                                 var13 = false;
                                 break;
                              }

                              Symbols.Symbol var9 = (Symbols.Symbol)exists_these.head();
                              if ($anonfun$apply$4(this, var9)) {
                                 var13 = true;
                                 break;
                              }

                              exists_these = (List)exists_these.tail();
                           }

                           Object var12 = null;
                           if (var13) {
                              var14 = true;
                              break;
                           }

                           mexists_exists_these = (List)mexists_exists_these.tail();
                        }

                        Object var11 = null;
                        Object var10 = null;
                        if (var14) {
                           Symbols.Symbol $plus$eq_elem = this.$outer.scala$reflect$internal$transform$UnCurry$$varargForwarderSym(clazz, decl, (Types.Type)this.$outer.global().exitingPhase(this.$outer.global().phase(), () -> decl.info()));
                           if (varargOverloads == null) {
                              throw null;
                           } else {
                              return varargOverloads.addOne($plus$eq_elem);
                           }
                        } else {
                           return BoxedUnit.UNIT;
                        }
                     }
                  });
                  if (parents1 == parents && varargOverloads.isEmpty()) {
                     return tp;
                  }

                  Scopes.Scope newDecls = decls.cloneScope();
                  varargOverloads.foreach((x$2) -> newDecls.enter(x$2));
                  return this.$outer.global().new ClassInfoType(parents1, newDecls, clazz);
               }
            }

            return tp instanceof Types.PolyType ? tp.mapOver(this) : tp;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$2(final Object $this, final AnnotationInfos.AnnotationInfo x$1) {
            Symbols.Symbol var10000 = x$1.symbol();
            Symbols.ClassSymbol var2 = $this.$outer.global().definitions().VarargsClass();
            if (var10000 == null) {
               if (var2 == null) {
                  return true;
               }
            } else if (var10000.equals(var2)) {
               return true;
            }

            return false;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$1(final Object $this, final Symbols.Symbol decl) {
            List var10000 = decl.annotations();
            if (var10000 == null) {
               throw null;
            } else {
               for(List exists_these = var10000; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
                  AnnotationInfos.AnnotationInfo var3 = (AnnotationInfos.AnnotationInfo)exists_these.head();
                  if ($anonfun$apply$2($this, var3)) {
                     return true;
                  }
               }

               return false;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$4(final Object $this, final Symbols.Symbol sym) {
            Definitions.definitions$ var10000 = $this.$outer.global().definitions();
            if (sym == null) {
               throw null;
            } else {
               return var10000.isRepeatedParamType(sym.tpe_$times());
            }
         }

         public {
            if (UnCurry.this == null) {
               throw null;
            } else {
               this.$outer = UnCurry.this;
            }
         }

         // $FF: synthetic method
         public static final Object $anonfun$apply$2$adapted(final Object $this, final AnnotationInfos.AnnotationInfo x$1) {
            return BoxesRunTime.boxToBoolean($anonfun$apply$2($this, x$1));
         }

         // $FF: synthetic method
         public static final Object $anonfun$apply$4$adapted(final Object $this, final Symbols.Symbol sym) {
            return BoxesRunTime.boxToBoolean($anonfun$apply$4($this, sym));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   // $FF: synthetic method
   static Object $anonfun$varargForwarderSym$1$adapted(final UnCurry $this, final List fsps, final List origPs) {
      $anonfun$varargForwarderSym$1($this, fsps, origPs);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$varargForwarderSym$2$adapted(final UnCurry $this, final Symbols.Symbol p, final Symbols.Symbol sym) {
      $anonfun$varargForwarderSym$2($this, p, sym);
      return BoxedUnit.UNIT;
   }

   public class VarargsSymbolAttachment implements Product, Serializable {
      private final Symbols.Symbol varargMethod;
      // $FF: synthetic field
      public final UnCurry $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Symbols.Symbol varargMethod() {
         return this.varargMethod;
      }

      public VarargsSymbolAttachment copy(final Symbols.Symbol varargMethod) {
         return this.scala$reflect$internal$transform$UnCurry$VarargsSymbolAttachment$$$outer().new VarargsSymbolAttachment(varargMethod);
      }

      public Symbols.Symbol copy$default$1() {
         return this.varargMethod();
      }

      public String productPrefix() {
         return "VarargsSymbolAttachment";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.varargMethod();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof VarargsSymbolAttachment;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "varargMethod";
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
            if (x$1 instanceof VarargsSymbolAttachment && ((VarargsSymbolAttachment)x$1).scala$reflect$internal$transform$UnCurry$VarargsSymbolAttachment$$$outer() == this.scala$reflect$internal$transform$UnCurry$VarargsSymbolAttachment$$$outer()) {
               VarargsSymbolAttachment var2 = (VarargsSymbolAttachment)x$1;
               Symbols.Symbol var10000 = this.varargMethod();
               Symbols.Symbol var3 = var2.varargMethod();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
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
      public UnCurry scala$reflect$internal$transform$UnCurry$VarargsSymbolAttachment$$$outer() {
         return this.$outer;
      }

      public VarargsSymbolAttachment(final Symbols.Symbol varargMethod) {
         this.varargMethod = varargMethod;
         if (UnCurry.this == null) {
            throw null;
         } else {
            this.$outer = UnCurry.this;
            super();
         }
      }
   }

   public class VarargsSymbolAttachment$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final UnCurry $outer;

      public final String toString() {
         return "VarargsSymbolAttachment";
      }

      public VarargsSymbolAttachment apply(final Symbols.Symbol varargMethod) {
         return this.$outer.new VarargsSymbolAttachment(varargMethod);
      }

      public Option unapply(final VarargsSymbolAttachment x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.varargMethod()));
      }

      public VarargsSymbolAttachment$() {
         if (UnCurry.this == null) {
            throw null;
         } else {
            this.$outer = UnCurry.this;
            super();
         }
      }
   }

   public class DesugaredParameterType$ {
      // $FF: synthetic field
      private final UnCurry $outer;

      public Option unapply(final Types.Type tpe) {
         boolean var2 = false;
         Types.TypeRef var3 = null;
         if (tpe instanceof Types.TypeRef) {
            label58: {
               var2 = true;
               var3 = (Types.TypeRef)tpe;
               Symbols.Symbol var4 = var3.sym();
               List var5 = var3.args();
               Symbols.ClassSymbol var10000 = this.$outer.global().definitions().ByNameParamClass();
               if (var10000 == null) {
                  if (var4 != null) {
                     break label58;
                  }
               } else if (!var10000.equals(var4)) {
                  break label58;
               }

               if (var5 instanceof scala.collection.immutable..colon.colon) {
                  scala.collection.immutable..colon.colon var6 = (scala.collection.immutable..colon.colon)var5;
                  Types.Type arg = (Types.Type)var6.head();
                  List var8 = var6.next$access$1();
                  if (scala.collection.immutable.Nil..MODULE$.equals(var8)) {
                     return new Some(this.$outer.global().definitions().functionType(scala.collection.immutable.Nil..MODULE$, arg));
                  }
               }
            }
         }

         if (var2) {
            label51: {
               Symbols.Symbol var9 = var3.sym();
               List var10 = var3.args();
               Symbols.ClassSymbol var19 = this.$outer.global().definitions().RepeatedParamClass();
               if (var19 == null) {
                  if (var9 != null) {
                     break label51;
                  }
               } else if (!var19.equals(var9)) {
                  break label51;
               }

               if (var10 instanceof scala.collection.immutable..colon.colon) {
                  scala.collection.immutable..colon.colon var11 = (scala.collection.immutable..colon.colon)var10;
                  Types.Type arg = (Types.Type)var11.head();
                  List var13 = var11.next$access$1();
                  if (scala.collection.immutable.Nil..MODULE$.equals(var13)) {
                     return new Some(this.$outer.global().definitions().seqType(arg));
                  }
               }
            }
         }

         if (var2) {
            Symbols.Symbol var14 = var3.sym();
            List var15 = var3.args();
            Symbols.ClassSymbol var20 = this.$outer.global().definitions().JavaRepeatedParamClass();
            if (var20 == null) {
               if (var14 != null) {
                  return scala.None..MODULE$;
               }
            } else if (!var20.equals(var14)) {
               return scala.None..MODULE$;
            }

            if (var15 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var16 = (scala.collection.immutable..colon.colon)var15;
               Types.Type arg = (Types.Type)var16.head();
               List var18 = var16.next$access$1();
               if (scala.collection.immutable.Nil..MODULE$.equals(var18)) {
                  return new Some(this.$outer.global().definitions().arrayType((Types.Type)(this.$outer.global().isUnboundedGeneric(arg) ? this.$outer.global().definitions().ObjectTpeJava() : arg)));
               }
            }
         }

         return scala.None..MODULE$;
      }

      public DesugaredParameterType$() {
         if (UnCurry.this == null) {
            throw null;
         } else {
            this.$outer = UnCurry.this;
            super();
         }
      }
   }
}
