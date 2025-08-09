package scala.reflect.internal.tpe;

import scala.Function1;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005I3\u0011b\u0003\u0007\u0011\u0002\u0007\u0005a\u0002\u0006(\t\u000be\u0001A\u0011A\u000e\t\r}\u0001A\u0011\u0003\b!\u0011\u0019y\u0002\u0001\"\u0005\u000f]!)\u0001\b\u0001C\ts\u0019!1\b\u0001\u0005=\u0011\u0015\u0011U\u0001\"\u0001D\u0011\u0015!U\u0001\"\u0001\u001c\u0011\u0015)U\u0001\"\u0003G\u0011\u0015IU\u0001\"\u0001K\u0011!i\u0005\u0001#b\u0001\n\u0013I$\u0001D\"p[6|gnT<oKJ\u001c(BA\u0007\u000f\u0003\r!\b/\u001a\u0006\u0003\u001fA\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003#I\tqA]3gY\u0016\u001cGOC\u0001\u0014\u0003\u0015\u00198-\u00197b'\t\u0001Q\u0003\u0005\u0002\u0017/5\t!#\u0003\u0002\u0019%\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u00029A\u0011a#H\u0005\u0003=I\u0011A!\u00168ji\u0006Y1m\\7n_:|uO\\3s)\t\ts\u0005\u0005\u0002#G5\t\u0001!\u0003\u0002%K\t11+_7c_2L!A\n\b\u0003\u000fMKXNY8mg\")\u0001F\u0001a\u0001S\u0005\tA\u000f\u0005\u0002#U%\u00111\u0006\f\u0002\u0005)f\u0004X-\u0003\u0002.\u001d\t)A+\u001f9fgR\u0011\u0011e\f\u0005\u0006a\r\u0001\r!M\u0001\u0004iB\u001c\bc\u0001\u001a6S9\u0011acM\u0005\u0003iI\tq\u0001]1dW\u0006<W-\u0003\u00027o\t!A*[:u\u0015\t!$#\u0001\bd_6lwN\\(x]\u0016\u0014X*\u00199\u0016\u0003i\u0002\"AI\u0003\u0003\u001d\r{W.\\8o\u001f^tWM]'baN\u0011Q!\u0010\t\u0004Ey\n\u0013BA A\u00055!\u0016\u0010]3D_2dWm\u0019;pe&\u0011\u0011\t\u0004\u0002\t)f\u0004X-T1qg\u00061A(\u001b8jiz\"\u0012AO\u0001\u0006G2,\u0017M]\u0001\te\u0016<\u0017n\u001d;feR\u0011Ad\u0012\u0005\u0006\u0011\"\u0001\r!I\u0001\u0004gfl\u0017!B1qa2LHC\u0001\u000fL\u0011\u0015a\u0015\u00021\u0001*\u0003\t!\b/A\td_6lwN\\(x]\u0016\u0014X*\u00199PE*\u0004\"a\u0014)\u000e\u00039I!!\u0015\b\u0003\u0017MKXNY8m)\u0006\u0014G.\u001a"
)
public interface CommonOwners {
   // $FF: synthetic method
   static Symbols.Symbol commonOwner$(final CommonOwners $this, final Types.Type t) {
      return $this.commonOwner(t);
   }

   default Symbols.Symbol commonOwner(final Types.Type t) {
      List $colon$colon_this = .MODULE$;
      scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(t, $colon$colon_this);
      $colon$colon_this = null;
      return this.commonOwner((List)var10001);
   }

   // $FF: synthetic method
   static Symbols.Symbol commonOwner$(final CommonOwners $this, final List tps) {
      return $this.commonOwner(tps);
   }

   default Symbols.Symbol commonOwner(final List tps) {
      if (tps.isEmpty()) {
         return ((Symbols)this).NoSymbol();
      } else {
         this.commonOwnerMap().clear();
         Function1 foreach_f = this.commonOwnerMap();

         for(List foreach_these = tps; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            foreach_f.apply(foreach_these.head());
         }

         foreach_f = null;
         Object var5 = null;
         return (Symbols.Symbol)(this.commonOwnerMap().result() != null ? (Symbols.Symbol)this.commonOwnerMap().result() : ((Symbols)this).NoSymbol());
      }
   }

   // $FF: synthetic method
   static CommonOwnerMap commonOwnerMap$(final CommonOwners $this) {
      return $this.commonOwnerMap();
   }

   default CommonOwnerMap commonOwnerMap() {
      return this.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj();
   }

   // $FF: synthetic method
   static CommonOwnerMap scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj$(final CommonOwners $this) {
      return $this.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj();
   }

   default CommonOwnerMap scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj() {
      return (SymbolTable)this.new CommonOwnerMap();
   }

   static void $init$(final CommonOwners $this) {
   }

   public class CommonOwnerMap extends TypeMaps.TypeCollector {
      // $FF: synthetic field
      public final SymbolTable $outer;

      public void clear() {
         this.result_$eq((Object)null);
      }

      private void register(final Symbols.Symbol sym) {
         if (this.result() != null && sym != this.scala$reflect$internal$tpe$CommonOwners$CommonOwnerMap$$$outer().NoSymbol()) {
            while(this.result() != this.scala$reflect$internal$tpe$CommonOwners$CommonOwnerMap$$$outer().NoSymbol() && this.result() != sym && !sym.isNestedIn((Symbols.Symbol)this.result())) {
               this.result_$eq(((Symbols.Symbol)this.result()).owner());
            }

         } else {
            this.result_$eq(sym);
         }
      }

      public void apply(final Types.Type tp) {
         Types.Type var2 = tp.normalize();
         if (var2 instanceof Types.ThisType) {
            Symbols.Symbol sym = ((Types.ThisType)var2).sym();
            this.register(sym);
         } else {
            if (var2 instanceof Types.TypeRef) {
               Types.TypeRef var4 = (Types.TypeRef)var2;
               Types.Type var5 = var4.pre();
               Symbols.Symbol sym = var4.sym();
               List args = var4.args();
               if (this.scala$reflect$internal$tpe$CommonOwners$CommonOwnerMap$$$outer().NoPrefix().equals(var5)) {
                  this.register(sym.owner());
                  if (args == null) {
                     throw null;
                  }

                  for(List foreach_these = args; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                     Types.Type var12 = (Types.Type)foreach_these.head();
                     this.apply(var12);
                  }

                  return;
               }
            }

            if (var2 instanceof Types.SingleType) {
               Types.SingleType var8 = (Types.SingleType)var2;
               Types.Type var9 = var8.pre();
               Symbols.Symbol sym = var8.sym();
               if (this.scala$reflect$internal$tpe$CommonOwners$CommonOwnerMap$$$outer().NoPrefix().equals(var9)) {
                  this.register(sym.owner());
                  return;
               }
            }

            tp.foldOver(this);
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$CommonOwners$CommonOwnerMap$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$apply$1(final CommonOwnerMap $this, final Types.Type tp) {
         $this.apply(tp);
      }

      public CommonOwnerMap() {
         if (CommonOwners.this == null) {
            throw null;
         } else {
            this.$outer = CommonOwners.this;
            super((Object)null);
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$apply$1$adapted(final CommonOwnerMap $this, final Types.Type tp) {
         $anonfun$apply$1($this, tp);
         return BoxedUnit.UNIT;
      }
   }
}
