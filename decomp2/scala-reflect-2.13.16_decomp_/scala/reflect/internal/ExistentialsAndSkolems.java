package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Some;
import scala.Tuple2;
import scala.collection.MapFactory;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Collections;
import scala.reflect.internal.util.Position;
import scala.runtime.Nothing;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005e4\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005\u0011#\u001e\u0005\u0006-\u0001!\ta\u0006\u0005\u00067\u0001!\t\u0001\b\u0005\u0006Y\u0001!\t!\f\u0005\u0006g\u0001!I\u0001\u000e\u0005\u0006\u000f\u0002!)\u0001\u0013\u0005\bC\u0002\t\n\u0011\"\u0002c\u0011\u0015y\u0007\u0001\"\u0002q\u0011\u001d!\b!%A\u0005\u0006\r\u0014a#\u0012=jgR,g\u000e^5bYN\fe\u000eZ*l_2,Wn\u001d\u0006\u0003\u00171\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u001b9\tqA]3gY\u0016\u001cGOC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\n\u0011\u0005M!R\"\u0001\b\n\u0005Uq!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u00021A\u00111#G\u0005\u000359\u0011A!\u00168ji\u0006\u0011B-\u001a:jm\u00164%/Z:i'.|G.Z7t)\ti\"\u0006E\u0002\u001fC\u0011r!aE\u0010\n\u0005\u0001r\u0011a\u00029bG.\fw-Z\u0005\u0003E\r\u0012A\u0001T5ti*\u0011\u0001E\u0004\t\u0003K\u0019j\u0011\u0001A\u0005\u0003O!\u0012aaU=nE>d\u0017BA\u0015\u000b\u0005\u001d\u0019\u00160\u001c2pYNDQa\u000b\u0002A\u0002u\tq\u0001\u001e9be\u0006l7/\u0001\bjgJ\u000bw\u000fU1sC6,G/\u001a:\u0015\u00059\n\u0004CA\n0\u0013\t\u0001dBA\u0004C_>dW-\u00198\t\u000bI\u001a\u0001\u0019\u0001\u0013\u0002\u0007MLX.\u0001\u0011fq&\u001cH/\u001a8uS\u0006d'i\\;oIN,\u0005p\u00197vI&tw\rS5eI\u0016tGCA\u001bF!\u00111T\b\n!\u000f\u0005]Z\u0004C\u0001\u001d\u000f\u001b\u0005I$B\u0001\u001e\u0011\u0003\u0019a$o\\8u}%\u0011AHD\u0001\u0007!J,G-\u001a4\n\u0005yz$aA'ba*\u0011AH\u0004\t\u0003K\u0005K!AQ\"\u0003\tQK\b/Z\u0005\u0003\t*\u0011Q\u0001V=qKNDQA\u0012\u0003A\u0002u\ta\u0001[5eI\u0016t\u0017\u0001F3ySN$XM\u001c;jC2$&/\u00198tM>\u0014X.\u0006\u0002J\u001bR!!jW/`)\tYe\u000b\u0005\u0002M\u001b2\u0001A!\u0002(\u0006\u0005\u0004y%!\u0001+\u0012\u0005A\u001b\u0006CA\nR\u0013\t\u0011fBA\u0004O_RD\u0017N\\4\u0011\u0005M!\u0016BA+\u000f\u0005\r\te.\u001f\u0005\u0006/\u0016\u0001\r\u0001W\u0001\bGJ,\u0017\r^8s!\u0015\u0019\u0012,\b!L\u0013\tQfBA\u0005Gk:\u001cG/[8oe!)A,\u0002a\u0001;\u00059!/Y<Ts6\u001c\b\"\u00020\u0006\u0001\u0004\u0001\u0015A\u0001;q\u0011\u001d\u0001W\u0001%AA\u0002\u0011\n\u0001B]1x\u001f^tWM]\u0001\u001fKbL7\u000f^3oi&\fG\u000e\u0016:b]N4wN]7%I\u00164\u0017-\u001e7uIM*\"a\u00198\u0016\u0003\u0011T#\u0001J3,\u0003\u0019\u0004\"a\u001a7\u000e\u0003!T!!\u001b6\u0002\u0013Ut7\r[3dW\u0016$'BA6\u000f\u0003)\tgN\\8uCRLwN\\\u0005\u0003[\"\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0015qeA1\u0001P\u0003-\u0001\u0018mY6Ts6\u0014w\u000e\\:\u0015\t\u0001\u000b(o\u001d\u0005\u0006\r\u001e\u0001\r!\b\u0005\u0006=\u001e\u0001\r\u0001\u0011\u0005\bA\u001e\u0001\n\u00111\u0001%\u0003U\u0001\u0018mY6Ts6\u0014w\u000e\\:%I\u00164\u0017-\u001e7uIM\u0002\"A^<\u000e\u0003)I!\u0001\u001f\u0006\u0003\u0017MKXNY8m)\u0006\u0014G.\u001a"
)
public interface ExistentialsAndSkolems {
   // $FF: synthetic method
   static List deriveFreshSkolems$(final ExistentialsAndSkolems $this, final List tparams) {
      return $this.deriveFreshSkolems(tparams);
   }

   default List deriveFreshSkolems(final List tparams) {
      int saved = ((Types)this).skolemizationLevel();
      ((Types)this).skolemizationLevel_$eq(0);

      List var10000;
      try {
         class Deskolemizer$1 extends Types.LazyType {
            private final List typeParams;
            private final List typeSkolems;

            public List typeParams() {
               return this.typeParams;
            }

            public List typeSkolems() {
               return this.typeSkolems;
            }

            public void complete(final Symbols.Symbol sym) {
               sym.setInfo(sym.deSkolemize().info().substSym(this.typeParams(), this.typeSkolems()));
            }

            // $FF: synthetic method
            public static final Symbols.TypeSkolem $anonfun$typeSkolems$1(final Deskolemizer$1 $this, final Symbols.Symbol x$1) {
               return (Symbols.TypeSkolem)x$1.newTypeSkolem().setInfo($this);
            }

            public Deskolemizer$1(final List tparams$1) {
               this.typeParams = tparams$1;
               List var10001 = this.typeParams();
               if (var10001 == null) {
                  throw null;
               } else {
                  List map_this = var10001;
                  Object var10;
                  if (map_this == .MODULE$) {
                     var10 = .MODULE$;
                  } else {
                     Symbols.Symbol var8 = (Symbols.Symbol)map_this.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$typeSkolems$1(this, var8), .MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                        var8 = (Symbols.Symbol)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$typeSkolems$1(this, var8), .MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var10 = map_h;
                  }

                  this.typeSkolems = (List)var10;
               }
            }
         }

         var10000 = (new Deskolemizer$1(tparams)).typeSkolems();
      } finally {
         ((Types)this).skolemizationLevel_$eq(saved);
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean isRawParameter$(final ExistentialsAndSkolems $this, final Symbols.Symbol sym) {
      return $this.isRawParameter(sym);
   }

   default boolean isRawParameter(final Symbols.Symbol sym) {
      return sym.isTypeParameter() && sym.owner().isJavaDefined();
   }

   private Map existentialBoundsExcludingHidden(final List hidden) {
      Collections var10000 = (Collections)this;
      Map var15 = scala.Predef..MODULE$.Map();
      if (hidden == null) {
         throw null;
      } else {
         Object var10001;
         if (hidden == .MODULE$) {
            var10001 = .MODULE$;
         } else {
            Object var7 = hidden.head();
            Symbols.Symbol var8 = (Symbols.Symbol)var7;
            scala.collection.immutable..colon.colon mapFrom_map_h = new scala.collection.immutable..colon.colon(new Tuple2(var7, $anonfun$existentialBoundsExcludingHidden$2(this, hidden, var8)), .MODULE$);
            scala.collection.immutable..colon.colon mapFrom_map_t = mapFrom_map_h;

            for(List mapFrom_map_rest = (List)hidden.tail(); mapFrom_map_rest != .MODULE$; mapFrom_map_rest = (List)mapFrom_map_rest.tail()) {
               var7 = mapFrom_map_rest.head();
               var8 = (Symbols.Symbol)var7;
               scala.collection.immutable..colon.colon mapFrom_map_nx = new scala.collection.immutable..colon.colon(new Tuple2(var7, $anonfun$existentialBoundsExcludingHidden$2(this, hidden, var8)), .MODULE$);
               mapFrom_map_t.next_$eq(mapFrom_map_nx);
               mapFrom_map_t = mapFrom_map_nx;
            }

            Statics.releaseFence();
            var10001 = mapFrom_map_h;
         }

         Object var9 = null;
         Object var10 = null;
         Object var11 = null;
         Object var12 = null;
         Object mapFrom_apply_elems = var10001;
         if (var15 == null) {
            throw null;
         } else {
            return (Map)MapFactory.apply$(var15, (Seq)mapFrom_apply_elems);
         }
      }
   }

   // $FF: synthetic method
   static Object existentialTransform$(final ExistentialsAndSkolems $this, final List rawSyms, final Types.Type tp, final Symbols.Symbol rawOwner, final Function2 creator) {
      return $this.existentialTransform(rawSyms, tp, rawOwner, creator);
   }

   default Object existentialTransform(final List rawSyms, final Types.Type tp, final Symbols.Symbol rawOwner, final Function2 creator) {
      Map allBounds = this.existentialBoundsExcludingHidden(rawSyms);
      if (rawSyms == null) {
         throw null;
      } else {
         Object var10000;
         if (rawSyms == .MODULE$) {
            var10000 = .MODULE$;
         } else {
            Symbols.Symbol var17 = (Symbols.Symbol)rawSyms.head();
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$existentialTransform$1(this, rawOwner, allBounds, var17), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)rawSyms.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               var17 = (Symbols.Symbol)map_rest.head();
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$existentialTransform$1(this, rawOwner, allBounds, var17), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10000 = map_h;
         }

         Object var20 = null;
         Object var21 = null;
         Object var22 = null;
         Object var23 = null;
         List typeParams = (List)var10000;
         if (typeParams == .MODULE$) {
            var10000 = .MODULE$;
         } else {
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Symbols.Symbol)typeParams.head()).tpeHK(), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)typeParams.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Symbols.Symbol)map_rest.head()).tpeHK(), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10000 = map_h;
         }

         Object var24 = null;
         Object var25 = null;
         Object var26 = null;
         Object var27 = null;
         List typeParamTypes = (List)var10000;

         for(List foreach_these = typeParams; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            Symbols.Symbol var18 = (Symbols.Symbol)foreach_these.head();
            $anonfun$existentialTransform$4(rawSyms, typeParamTypes, var18);
         }

         Object var19 = null;
         return creator.apply(typeParams, tp.subst(rawSyms, typeParamTypes));
      }
   }

   // $FF: synthetic method
   static Symbols.Symbol existentialTransform$default$3$(final ExistentialsAndSkolems $this) {
      return $this.existentialTransform$default$3();
   }

   default Symbols.Symbol existentialTransform$default$3() {
      return ((Symbols)this).NoSymbol();
   }

   // $FF: synthetic method
   static Types.Type packSymbols$(final ExistentialsAndSkolems $this, final List hidden, final Types.Type tp, final Symbols.Symbol rawOwner) {
      return $this.packSymbols(hidden, tp, rawOwner);
   }

   default Types.Type packSymbols(final List hidden, final Types.Type tp, final Symbols.Symbol rawOwner) {
      if (hidden.isEmpty()) {
         return tp;
      } else {
         Map existentialTransform_allBounds = this.existentialBoundsExcludingHidden(hidden);
         Object var10000;
         if (hidden == .MODULE$) {
            var10000 = .MODULE$;
         } else {
            Symbols.Symbol var18 = (Symbols.Symbol)hidden.head();
            scala.collection.immutable..colon.colon existentialTransform_map_h = new scala.collection.immutable..colon.colon($anonfun$existentialTransform$1(this, rawOwner, existentialTransform_allBounds, var18), .MODULE$);
            scala.collection.immutable..colon.colon existentialTransform_map_t = existentialTransform_map_h;

            for(List existentialTransform_map_rest = (List)hidden.tail(); existentialTransform_map_rest != .MODULE$; existentialTransform_map_rest = (List)existentialTransform_map_rest.tail()) {
               var18 = (Symbols.Symbol)existentialTransform_map_rest.head();
               scala.collection.immutable..colon.colon existentialTransform_map_nx = new scala.collection.immutable..colon.colon($anonfun$existentialTransform$1(this, rawOwner, existentialTransform_allBounds, var18), .MODULE$);
               existentialTransform_map_t.next_$eq(existentialTransform_map_nx);
               existentialTransform_map_t = existentialTransform_map_nx;
            }

            Statics.releaseFence();
            var10000 = existentialTransform_map_h;
         }

         Object var21 = null;
         Object var22 = null;
         Object var23 = null;
         Object var24 = null;
         List existentialTransform_typeParams = (List)var10000;
         if (existentialTransform_typeParams == .MODULE$) {
            var10000 = .MODULE$;
         } else {
            scala.collection.immutable..colon.colon existentialTransform_map_h = new scala.collection.immutable..colon.colon(((Symbols.Symbol)existentialTransform_typeParams.head()).tpeHK(), .MODULE$);
            scala.collection.immutable..colon.colon existentialTransform_map_t = existentialTransform_map_h;

            for(List existentialTransform_map_rest = (List)existentialTransform_typeParams.tail(); existentialTransform_map_rest != .MODULE$; existentialTransform_map_rest = (List)existentialTransform_map_rest.tail()) {
               scala.collection.immutable..colon.colon existentialTransform_map_nx = new scala.collection.immutable..colon.colon(((Symbols.Symbol)existentialTransform_map_rest.head()).tpeHK(), .MODULE$);
               existentialTransform_map_t.next_$eq(existentialTransform_map_nx);
               existentialTransform_map_t = existentialTransform_map_nx;
            }

            Statics.releaseFence();
            var10000 = existentialTransform_map_h;
         }

         Object var25 = null;
         Object var26 = null;
         Object var27 = null;
         Object var28 = null;
         List existentialTransform_typeParamTypes = (List)var10000;

         for(List existentialTransform_foreach_these = existentialTransform_typeParams; !existentialTransform_foreach_these.isEmpty(); existentialTransform_foreach_these = (List)existentialTransform_foreach_these.tail()) {
            Symbols.Symbol var19 = (Symbols.Symbol)existentialTransform_foreach_these.head();
            $anonfun$existentialTransform$4(hidden, existentialTransform_typeParamTypes, var19);
         }

         Object var20 = null;
         Types.Type var17 = tp.subst(hidden, existentialTransform_typeParamTypes);
         List var16 = existentialTransform_typeParams;
         return $anonfun$packSymbols$1(this, var16, var17);
      }
   }

   // $FF: synthetic method
   static Symbols.Symbol packSymbols$default$3$(final ExistentialsAndSkolems $this) {
      return $this.packSymbols$default$3();
   }

   default Symbols.Symbol packSymbols$default$3() {
      return ((Symbols)this).NoSymbol();
   }

   private Types.Type safeBound$1(final Types.Type t, final List hidden$1) {
      while(hidden$1.contains(t.typeSymbol())) {
         SymbolTable var10000 = (SymbolTable)this;
         t = t.typeSymbol().existentialBound().upperBound();
         this = var10000;
      }

      return t;
   }

   // $FF: synthetic method
   static Types.Type $anonfun$existentialBoundsExcludingHidden$1(final ExistentialsAndSkolems $this, final List hidden$1, final Types.Type t) {
      return $this.safeBound$1(t, hidden$1);
   }

   private Types.Type hiBound$1(final Symbols.Symbol s, final List hidden$1) {
      Types.Type var3 = this.safeBound$1(s.existentialBound().upperBound(), hidden$1).resultType();
      if (!(var3 instanceof Types.RefinedType)) {
         return var3;
      } else {
         Types.RefinedType var4 = (Types.RefinedType)var3;
         List parents = var4.parents();
         Scopes.Scope decls = var4.decls();
         if (parents == null) {
            throw null;
         } else {
            List mapConserve_loop$3_pending = parents;
            List mapConserve_loop$3_unchanged = parents;
            scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast = null;
            List mapConserve_loop$3_mappedHead = null;

            while(!mapConserve_loop$3_pending.isEmpty()) {
               Object mapConserve_loop$3_head0 = mapConserve_loop$3_pending.head();
               Types.Type var21 = (Types.Type)mapConserve_loop$3_head0;
               Object mapConserve_loop$3_head1 = $anonfun$existentialBoundsExcludingHidden$1(this, hidden$1, var21);
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
                     scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_xc.head(), .MODULE$);
                     if (mapConserve_loop$3_mappedHead1 == null) {
                        mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                     }

                     if (mapConserve_loop$3_mappedLast1 != null) {
                        mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                     }

                     mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_next;
                  }

                  scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_head1, .MODULE$);
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
            Object var24 = null;
            Object var25 = null;
            Object var26 = null;
            Object var27 = null;
            Object var28 = null;
            Object var29 = null;
            Object var30 = null;
            Object var31 = null;
            Object var32 = null;
            Object var33 = null;
            Object var34 = null;
            List mapConserve_result = (List)var10000;
            Statics.releaseFence();
            var10000 = mapConserve_result;
            mapConserve_result = null;
            List parents1 = (List)var10000;
            if (parents == parents1) {
               return var4;
            } else {
               return ((Types)this).copyRefinedType(var4, parents1, decls, ((Types)this).copyRefinedType$default$4());
            }
         }
      }
   }

   // $FF: synthetic method
   static Types.Type $anonfun$existentialBoundsExcludingHidden$2(final ExistentialsAndSkolems $this, final List hidden$1, final Symbols.Symbol s) {
      Types.Type var3 = s.existentialBound();
      if (var3 != null) {
         Some var4 = ((Types)$this).GenPolyType().unapply(var3);
         if (!var4.isEmpty()) {
            List tparams = (List)((Tuple2)var4.value())._1();
            Types.Type var6 = (Types.Type)((Tuple2)var4.value())._2();
            if (var6 instanceof Types.TypeBounds) {
               Types.Type lo = ((Types.TypeBounds)var6).lo();
               return ((Types)$this).genPolyType(tparams, ((Types)$this).TypeBounds().apply(lo, $this.hiBound$1(s, hidden$1)));
            }
         }
      }

      return $this.hiBound$1(s, hidden$1);
   }

   // $FF: synthetic method
   static Nothing $anonfun$existentialTransform$2(final ExistentialsAndSkolems $this, final Symbols.Symbol sym$1) {
      return ((Reporting)$this).abort((new StringBuilder(64)).append("no owner provided for existential transform over raw parameter: ").append(sym$1).toString());
   }

   private Symbols.Symbol rawOwner0$1(final Symbols.Symbol rawOwner$1, final Symbols.Symbol sym$1) {
      if (rawOwner$1 == null) {
         throw null;
      } else if (rawOwner$1 != rawOwner$1.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
         return rawOwner$1;
      } else {
         throw $anonfun$existentialTransform$2(this, sym$1);
      }
   }

   // $FF: synthetic method
   static Symbols.TypeSymbol $anonfun$existentialTransform$1(final ExistentialsAndSkolems $this, final Symbols.Symbol rawOwner$1, final Map allBounds$1, final Symbols.Symbol sym) {
      Names.Name var5 = sym.name();
      Names.TypeName name = var5 instanceof Names.TypeName ? (Names.TypeName)var5 : ((StdNames)$this).tpnme().singletonName(var5);
      Types.Type bound = (Types.Type)allBounds$1.apply(sym);
      Symbols.Symbol sowner = $this.isRawParameter(sym) ? $this.rawOwner0$1(rawOwner$1, sym) : sym.owner();
      Position var10000 = sym.pos();
      if (sowner == null) {
         throw null;
      } else {
         long newExistential_newFlags = 0L;
         Position newExistential_pos = var10000;
         long newExistential_newAbstractType_newFlags = 34359738368L | newExistential_newFlags;
         Symbols.AbstractTypeSymbol var15 = sowner.createAbstractTypeSymbol(name, newExistential_pos, 16L | newExistential_newAbstractType_newFlags);
         newExistential_pos = null;
         Symbols.TypeSymbol quantified = var15;
         return (Symbols.TypeSymbol)quantified.setInfo(bound.cloneInfo(quantified));
      }
   }

   // $FF: synthetic method
   static Types.Type $anonfun$existentialTransform$3(final Symbols.Symbol x$2) {
      return x$2.tpeHK();
   }

   private static Types.Type doSubst$1(final Types.Type info, final List rawSyms$1, final List typeParamTypes$1) {
      return info.subst(rawSyms$1, typeParamTypes$1);
   }

   // $FF: synthetic method
   static Symbols.Symbol $anonfun$existentialTransform$4(final List rawSyms$1, final List typeParamTypes$1, final Symbols.Symbol x$3) {
      return x$3.modifyInfo((info) -> doSubst$1(info, rawSyms$1, typeParamTypes$1));
   }

   // $FF: synthetic method
   static Types.Type $anonfun$packSymbols$1(final ExistentialsAndSkolems $this, final List x$4, final Types.Type x$5) {
      return ((Types)$this).existentialAbstraction(x$4, x$5, ((Types)$this).existentialAbstraction$default$3());
   }

   static void $init$(final ExistentialsAndSkolems $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
