package scala.reflect.internal;

public abstract class Kinds$inferKind$InferKind {
   // $FF: synthetic field
   public final Kinds.inferKind$ $outer;

   public abstract Kinds.Kind infer(final Types.Type tpe, final Symbols.Symbol owner, final boolean topLevel);

   public Kinds.Kind infer(final Symbols.Symbol sym, final boolean topLevel) {
      return this.infer(sym.tpeHK(), sym.owner(), topLevel);
   }

   public Kinds.Kind apply(final Symbols.Symbol sym) {
      return this.infer(sym, true);
   }

   public Kinds.Kind apply(final Types.Type tpe, final Symbols.Symbol owner) {
      return this.infer(tpe, owner, true);
   }

   // $FF: synthetic method
   public Kinds.inferKind$ scala$reflect$internal$Kinds$inferKind$InferKind$$$outer() {
      return this.$outer;
   }

   public Kinds$inferKind$InferKind(final Kinds.inferKind$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
