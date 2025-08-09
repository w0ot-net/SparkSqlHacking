package scala.reflect.api;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015a!B\u0007\u000f\u0003\u0003)\u0002\"B\u000e\u0001\t\u0003a\u0002b\u0002\u0019\u0001\u0005\u00045\t!\r\u0005\u0006e\u00011\ta\r\u0005\u0006s\u00011\tA\u000f\u0005\u0006}\u00011\ta\r\u0005\u0006\u007f\u00011\tA\u000f\u0005\u0006\u0001\u00021\t!\u0011\u0005\u0006\u001f\u00021\t\u0001\u0015\u0005\u0006%\u00021\ta\u0015\u0005\u0006+\u0002!\tA\u0016\u0005\u0006[\u0002!\tA\u001c\u0005\u0006o\u00021\t\u0001\u001f\u0002\u0007\u001b&\u0014(o\u001c:\u000b\u0005=\u0001\u0012aA1qS*\u0011\u0011CE\u0001\be\u00164G.Z2u\u0015\u0005\u0019\u0012!B:dC2\f7\u0001A\u000b\u0003-\u0005\u001a\"\u0001A\f\u0011\u0005aIR\"\u0001\n\n\u0005i\u0011\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002;A\u0019a\u0004A\u0010\u000e\u00039\u0001\"\u0001I\u0011\r\u0001\u0011)!\u0005\u0001b\u0001G\t\tQ+\u0005\u0002%OA\u0011\u0001$J\u0005\u0003MI\u0011qAT8uQ&twME\u0002)U52A!\u000b\u0001\u0001O\taAH]3gS:,W.\u001a8u}A\u0011adK\u0005\u0003Y9\u0011\u0001\"\u00168jm\u0016\u00148/\u001a\t\u000319J!a\f\n\u0003\u0013MKgn\u001a7fi>t\u0017\u0001C;oSZ,'o]3\u0016\u0003}\t\u0011BU8pi\u000ec\u0017m]:\u0016\u0003Q\u0002\"aH\u001b\n\u0005Y:$aC\"mCN\u001c8+_7c_2L!\u0001\u000f\b\u0003\u000fMKXNY8mg\u0006Y!k\\8u!\u0006\u001c7.Y4f+\u0005Y\u0004CA\u0010=\u0013\titG\u0001\u0007N_\u0012,H.Z*z[\n|G.A\tF[B$\u0018\u0010U1dW\u0006<Wm\u00117bgN\fA\"R7qif\u0004\u0016mY6bO\u0016\f1b\u001d;bi&\u001c7\t\\1tgR\u0011AG\u0011\u0005\u0006\u0007\u001e\u0001\r\u0001R\u0001\tMVdGNT1nKB\u0011Q\t\u0014\b\u0003\r*\u0003\"a\u0012\n\u000e\u0003!S!!\u0013\u000b\u0002\rq\u0012xn\u001c;?\u0013\tY%#\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001b:\u0013aa\u0015;sS:<'BA&\u0013\u00031\u0019H/\u0019;jG6{G-\u001e7f)\tY\u0014\u000bC\u0003D\u0011\u0001\u0007A)A\u0007ti\u0006$\u0018n\u0019)bG.\fw-\u001a\u000b\u0003wQCQaQ\u0005A\u0002\u0011\u000b!b^3bWRK\b/Z(g+\t9v\r\u0006\u0002Y;B\u0011q$W\u0005\u00035n\u0013A\u0001V=qK&\u0011AL\u0004\u0002\u0006)f\u0004Xm\u001d\u0005\b=*\t\t\u0011q\u0001`\u0003))g/\u001b3f]\u000e,G%\r\t\u0004A\n4gBA1\u0003\u001b\u0005\u0001\u0011BA2e\u0005-9V-Y6UsB,G+Y4\n\u0005\u0015t!\u0001\u0003+za\u0016$\u0016mZ:\u0011\u0005\u0001:G!\u00025\u000b\u0005\u0004I'!\u0001+\u0012\u0005\u0011R\u0007C\u0001\rl\u0013\ta'CA\u0002B]f\fa\u0001^=qK>3WCA8w)\tA\u0006\u000fC\u0004r\u0017\u0005\u0005\t9\u0001:\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0002agVL!\u0001\u001e3\u0003\u000fQK\b/\u001a+bOB\u0011\u0001E\u001e\u0003\u0006Q.\u0011\r![\u0001\tgfl'm\u001c7PMV\u0019\u00110a\u0001\u0015\u0005il\bCA\u0010|\u0013\taxG\u0001\u0006UsB,7+_7c_2DqA \u0007\u0002\u0002\u0003\u000fq0\u0001\u0006fm&$WM\\2fIM\u0002B\u0001\u00192\u0002\u0002A\u0019\u0001%a\u0001\u0005\u000b!d!\u0019A5"
)
public abstract class Mirror {
   public abstract Universe universe();

   public abstract Symbols.ClassSymbolApi RootClass();

   public abstract Symbols.ModuleSymbolApi RootPackage();

   public abstract Symbols.ClassSymbolApi EmptyPackageClass();

   public abstract Symbols.ModuleSymbolApi EmptyPackage();

   public abstract Symbols.ClassSymbolApi staticClass(final String fullName);

   public abstract Symbols.ModuleSymbolApi staticModule(final String fullName);

   public abstract Symbols.ModuleSymbolApi staticPackage(final String fullName);

   public Types.TypeApi weakTypeOf(final TypeTags.WeakTypeTag evidence$1) {
      return this.universe().weakTypeTag(evidence$1).in(this).tpe();
   }

   public Types.TypeApi typeOf(final TypeTags.TypeTag evidence$2) {
      return this.universe().typeTag(evidence$2).in(this).tpe();
   }

   public abstract Symbols.TypeSymbolApi symbolOf(final TypeTags.WeakTypeTag evidence$3);
}
