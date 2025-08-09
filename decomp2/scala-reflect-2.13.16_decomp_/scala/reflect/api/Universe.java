package scala.reflect.api;

import scala.Function1;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114Qa\u0001\u0003\u0002\u0002-AQ!\u0014\u0001\u0005\u00029Ca\u0001\u0015\u0001\u0003\n\u0003\t&\u0001C+oSZ,'o]3\u000b\u0005\u00151\u0011aA1qS*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7\u0001A\n\u0017\u00011\u0001Bc\u0006\u000e\u001eA\r2\u0013\u0006L\u00183kaZd(\u0011#H\u0015B\u0011QBD\u0007\u0002\u0011%\u0011q\u0002\u0003\u0002\u0007\u0003:L(+\u001a4\u0011\u0005E\u0011R\"\u0001\u0003\n\u0005M!!aB*z[\n|Gn\u001d\t\u0003#UI!A\u0006\u0003\u0003\u000bQK\b/Z:\u0011\u0005EA\u0012BA\r\u0005\u0005!1E.Y4TKR\u001c\bCA\t\u001c\u0013\taBA\u0001\u0004TG>\u0004Xm\u001d\t\u0003#yI!a\b\u0003\u0003\u000b9\u000bW.Z:\u0011\u0005E\t\u0013B\u0001\u0012\u0005\u0005\u0015!&/Z3t!\t\tB%\u0003\u0002&\t\tI1i\u001c8ti\u0006tGo\u001d\t\u0003#\u001dJ!\u0001\u000b\u0003\u0003\u0017\u0005sgn\u001c;bi&|gn\u001d\t\u0003#)J!a\u000b\u0003\u0003\u0013A{7/\u001b;j_:\u001c\bCA\t.\u0013\tqCAA\u0003FqB\u00148\u000f\u0005\u0002\u0012a%\u0011\u0011\u0007\u0002\u0002\t)f\u0004X\rV1hgB\u0011\u0011cM\u0005\u0003i\u0011\u0011A\"S7qY&\u001c\u0017\u000e\u001e+bON\u0004\"!\u0005\u001c\n\u0005]\"!aE*uC:$\u0017M\u001d3EK\u001aLg.\u001b;j_:\u001c\bCA\t:\u0013\tQDAA\u0007Ti\u0006tG-\u0019:e\u001d\u0006lWm\u001d\t\u0003#qJ!!\u0010\u0003\u0003#M#\u0018M\u001c3be\u0012d\u0015N\u001a;bE2,7\u000f\u0005\u0002\u0012\u007f%\u0011\u0001\t\u0002\u0002\b\u001b&\u0014(o\u001c:t!\t\t\")\u0003\u0002D\t\tA\u0001K]5oi\u0016\u00148\u000f\u0005\u0002\u0012\u000b&\u0011a\t\u0002\u0002\n\u0019&4G/\u00192mKN\u0004\"!\u0005%\n\u0005%#!aC)vCNL\u0017/^8uKN\u0004\"!E&\n\u00051#!!C%oi\u0016\u0014h.\u00197t\u0003\u0019a\u0014N\\5u}Q\tq\n\u0005\u0002\u0012\u0001\u0005)!/Z5gsV\u0011!+\u0017\u000b\u0003'\n\u00042\u0001V+X\u001b\u0005\u0001\u0011B\u0001,.\u0005\u0011)\u0005\u0010\u001d:\u0011\u0005aKF\u0002\u0001\u0003\u00065\n\u0011\ra\u0017\u0002\u0002)F\u0011Al\u0018\t\u0003\u001buK!A\u0018\u0005\u0003\u000f9{G\u000f[5oOB\u0011Q\u0002Y\u0005\u0003C\"\u00111!\u00118z\u0011\u0015\u0019'\u00011\u0001X\u0003\u0011)\u0007\u0010\u001d:"
)
public abstract class Universe implements Symbols, Types, FlagSets, Scopes, Names, Trees, Constants, Annotations, Positions, Exprs, TypeTags, ImplicitTags, StandardDefinitions, StandardNames, StandardLiftables, Mirrors, Printers, Liftables, Quasiquotes, Internals {
   private volatile Liftables.Liftable$ Liftable$module;
   private volatile Liftables.Unliftable$ Unliftable$module;
   private volatile Printers.BooleanFlag$ BooleanFlag$module;
   private volatile StandardLiftables.stdnme$ stdnme$module;
   private volatile TypeTags.WeakTypeTag$ WeakTypeTag$module;
   private volatile TypeTags.TypeTag$ TypeTag$module;
   private volatile Exprs.Expr$ Expr$module;
   private Trees.TreeCopierOps treeCopy;
   private Trees.ModifiersApi NoMods;
   private volatile boolean bitmap$0;

   /** @deprecated */
   public Internals.Importer mkImporter(final Universe from0) {
      return Internals.mkImporter$(this, from0);
   }

   public Quasiquotes.Quasiquote Quasiquote(final StringContext ctx) {
      return Quasiquotes.Quasiquote$(this, ctx);
   }

   public String render(final Object what, final Function1 mkPrinter, final Printers.BooleanFlag printTypes, final Printers.BooleanFlag printIds, final Printers.BooleanFlag printOwners, final Printers.BooleanFlag printKinds, final Printers.BooleanFlag printMirrors, final Printers.BooleanFlag printPositions) {
      return Printers.render$(this, what, mkPrinter, printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
   }

   public Printers.BooleanFlag render$default$3() {
      return Printers.render$default$3$(this);
   }

   public Printers.BooleanFlag render$default$4() {
      return Printers.render$default$4$(this);
   }

   public Printers.BooleanFlag render$default$5() {
      return Printers.render$default$5$(this);
   }

   public Printers.BooleanFlag render$default$6() {
      return Printers.render$default$6$(this);
   }

   public Printers.BooleanFlag render$default$7() {
      return Printers.render$default$7$(this);
   }

   public Printers.BooleanFlag render$default$8() {
      return Printers.render$default$8$(this);
   }

   public String treeToString(final Trees.TreeApi tree) {
      return Printers.treeToString$(this, tree);
   }

   public String show(final Object any, final Printers.BooleanFlag printTypes, final Printers.BooleanFlag printIds, final Printers.BooleanFlag printOwners, final Printers.BooleanFlag printKinds, final Printers.BooleanFlag printMirrors, final Printers.BooleanFlag printPositions) {
      return Printers.show$(this, any, printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
   }

   public Printers.BooleanFlag show$default$2() {
      return Printers.show$default$2$(this);
   }

   public Printers.BooleanFlag show$default$3() {
      return Printers.show$default$3$(this);
   }

   public Printers.BooleanFlag show$default$4() {
      return Printers.show$default$4$(this);
   }

   public Printers.BooleanFlag show$default$5() {
      return Printers.show$default$5$(this);
   }

   public Printers.BooleanFlag show$default$6() {
      return Printers.show$default$6$(this);
   }

   public Printers.BooleanFlag show$default$7() {
      return Printers.show$default$7$(this);
   }

   public String showCode(final Trees.TreeApi tree, final Printers.BooleanFlag printTypes, final Printers.BooleanFlag printIds, final Printers.BooleanFlag printOwners, final Printers.BooleanFlag printPositions, final boolean printRootPkg) {
      return Printers.showCode$(this, tree, printTypes, printIds, printOwners, printPositions, printRootPkg);
   }

   public Printers.BooleanFlag showCode$default$2() {
      return Printers.showCode$default$2$(this);
   }

   public Printers.BooleanFlag showCode$default$3() {
      return Printers.showCode$default$3$(this);
   }

   public Printers.BooleanFlag showCode$default$4() {
      return Printers.showCode$default$4$(this);
   }

   public Printers.BooleanFlag showCode$default$5() {
      return Printers.showCode$default$5$(this);
   }

   public boolean showCode$default$6() {
      return Printers.showCode$default$6$(this);
   }

   public String showRaw(final Object any, final Printers.BooleanFlag printTypes, final Printers.BooleanFlag printIds, final Printers.BooleanFlag printOwners, final Printers.BooleanFlag printKinds, final Printers.BooleanFlag printMirrors, final Printers.BooleanFlag printPositions) {
      return Printers.showRaw$(this, any, printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
   }

   public Printers.BooleanFlag showRaw$default$2() {
      return Printers.showRaw$default$2$(this);
   }

   public Printers.BooleanFlag showRaw$default$3() {
      return Printers.showRaw$default$3$(this);
   }

   public Printers.BooleanFlag showRaw$default$4() {
      return Printers.showRaw$default$4$(this);
   }

   public Printers.BooleanFlag showRaw$default$5() {
      return Printers.showRaw$default$5$(this);
   }

   public Printers.BooleanFlag showRaw$default$6() {
      return Printers.showRaw$default$6$(this);
   }

   public Printers.BooleanFlag showRaw$default$7() {
      return Printers.showRaw$default$7$(this);
   }

   public String showRaw(final Names.NameApi name) {
      return Printers.showRaw$(this, (Names.NameApi)name);
   }

   public String showRaw(final Object flags) {
      return Printers.showRaw$(this, (Object)flags);
   }

   public String showRaw(final Position position) {
      return Printers.showRaw$(this, (Position)position);
   }

   public TypeTags.TypeTag TypeTagImpl(final Mirror mirror, final TypeCreator tpec) {
      return TypeTags.TypeTagImpl$(this, mirror, tpec);
   }

   public TypeTags.WeakTypeTag weakTypeTag(final TypeTags.WeakTypeTag attag) {
      return TypeTags.weakTypeTag$(this, attag);
   }

   public TypeTags.TypeTag typeTag(final TypeTags.TypeTag ttag) {
      return TypeTags.typeTag$(this, ttag);
   }

   public Types.TypeApi weakTypeOf(final TypeTags.WeakTypeTag attag) {
      return TypeTags.weakTypeOf$(this, attag);
   }

   public Types.TypeApi typeOf(final TypeTags.TypeTag ttag) {
      return TypeTags.typeOf$(this, ttag);
   }

   /** @deprecated */
   public void itraverse(final Trees.Traverser traverser, final Trees.TreeApi tree) {
      Trees.itraverse$(this, traverser, tree);
   }

   /** @deprecated */
   public void xtraverse(final Trees.Traverser traverser, final Trees.TreeApi tree) {
      Trees.xtraverse$(this, traverser, tree);
   }

   /** @deprecated */
   public Trees.TreeApi itransform(final Trees.Transformer transformer, final Trees.TreeApi tree) {
      return Trees.itransform$(this, transformer, tree);
   }

   public Trees.TreeApi xtransform(final Trees.Transformer transformer, final Trees.TreeApi tree) {
      return Trees.xtransform$(this, transformer, tree);
   }

   public Trees.ModifiersApi Modifiers(final Object flags, final Names.NameApi privateWithin) {
      return Trees.Modifiers$(this, flags, privateWithin);
   }

   public Trees.ModifiersApi Modifiers(final Object flags) {
      return Trees.Modifiers$(this, flags);
   }

   /** @deprecated */
   public Names.TermNameApi stringToTermName(final String s) {
      return Names.stringToTermName$(this, s);
   }

   /** @deprecated */
   public Names.TypeNameApi stringToTypeName(final String s) {
      return Names.stringToTypeName$(this, s);
   }

   public Liftables.Liftable$ Liftable() {
      if (this.Liftable$module == null) {
         this.Liftable$lzycompute$1();
      }

      return this.Liftable$module;
   }

   public Liftables.Unliftable$ Unliftable() {
      if (this.Unliftable$module == null) {
         this.Unliftable$lzycompute$1();
      }

      return this.Unliftable$module;
   }

   public Printers.BooleanFlag$ BooleanFlag() {
      if (this.BooleanFlag$module == null) {
         this.BooleanFlag$lzycompute$1();
      }

      return this.BooleanFlag$module;
   }

   public StandardLiftables.stdnme$ scala$reflect$api$StandardLiftables$$stdnme() {
      if (this.stdnme$module == null) {
         this.scala$reflect$api$StandardLiftables$$stdnme$lzycompute$1();
      }

      return this.stdnme$module;
   }

   public TypeTags.WeakTypeTag$ WeakTypeTag() {
      if (this.WeakTypeTag$module == null) {
         this.WeakTypeTag$lzycompute$1();
      }

      return this.WeakTypeTag$module;
   }

   public TypeTags.TypeTag$ TypeTag() {
      if (this.TypeTag$module == null) {
         this.TypeTag$lzycompute$1();
      }

      return this.TypeTag$module;
   }

   public Exprs.Expr$ Expr() {
      if (this.Expr$module == null) {
         this.Expr$lzycompute$1();
      }

      return this.Expr$module;
   }

   public Trees.TreeCopierOps treeCopy() {
      return this.treeCopy;
   }

   private Trees.ModifiersApi NoMods$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.NoMods = Trees.NoMods$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.NoMods;
   }

   public Trees.ModifiersApi NoMods() {
      return !this.bitmap$0 ? this.NoMods$lzycompute() : this.NoMods;
   }

   public void scala$reflect$api$Trees$_setter_$treeCopy_$eq(final Trees.TreeCopierOps x$1) {
      this.treeCopy = x$1;
   }

   private final void Liftable$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Liftable$module == null) {
            this.Liftable$module = new Liftables.Liftable$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Unliftable$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Unliftable$module == null) {
            this.Unliftable$module = new Liftables.Unliftable$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void BooleanFlag$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.BooleanFlag$module == null) {
            this.BooleanFlag$module = new Printers.BooleanFlag$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void scala$reflect$api$StandardLiftables$$stdnme$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.stdnme$module == null) {
            this.stdnme$module = new StandardLiftables.stdnme$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void WeakTypeTag$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.WeakTypeTag$module == null) {
            this.WeakTypeTag$module = new TypeTags.WeakTypeTag$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypeTag$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeTag$module == null) {
            this.TypeTag$module = new TypeTags.TypeTag$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Expr$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Expr$module == null) {
            this.Expr$module = new Exprs.Expr$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   public Universe() {
      Trees.$init$(this);
      Statics.releaseFence();
   }
}
