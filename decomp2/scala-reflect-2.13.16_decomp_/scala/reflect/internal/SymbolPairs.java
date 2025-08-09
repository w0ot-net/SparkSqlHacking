package scala.reflect.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.transform.Erasure;
import scala.reflect.internal.util.Collections;
import scala.reflect.internal.util.HashSet;
import scala.reflect.internal.util.HashSet$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.StripMarginInterpolator;
import scala.reflect.internal.util.package;
import scala.reflect.internal.util.package$;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tMf!B'O\u0003\u0003)\u0006\"\u0002.\u0001\t\u0003Y\u0006b\u00020\u0001\u0005\u00045\ta\u0018\u0004\u0005G\u0002\u0011E\r\u0003\u0005u\u0007\tU\r\u0011\"\u0001v\u0011!i8A!E!\u0002\u00131\b\u0002\u0003@\u0004\u0005+\u0007I\u0011A;\t\u0011}\u001c!\u0011#Q\u0001\nYD\u0011\"!\u0001\u0004\u0005+\u0007I\u0011A;\t\u0013\u0005\r1A!E!\u0002\u00131\bB\u0002.\u0004\t\u0003\t)\u0001\u0003\u0005\u0002\u0010\r\u0001\u000b\u0011BA\t\u0011\u001d\tYb\u0001C\u0001\u0003;Aq!!\u000b\u0004\t\u0003\tY\u0003C\u0004\u0002.\r!\t!a\u000b\t\u000f\u0005=2\u0001\"\u0001\u0002,!9\u0011\u0011G\u0002\u0005\u0002\u0005-\u0002bBA\u001a\u0007\u0011\u0005\u00111\u0006\u0005\b\u0003k\u0019A\u0011AA\u0016\u0011\u001d\t9d\u0001C\u0001\u0003WAq!!\u000f\u0004\t\u0003\tY\u0003C\u0004\u0002<\r!\t!!\u0010\t\u000f\u0005\u00153\u0001\"\u0001\u0002>!9\u0011qI\u0002\u0005\n\u0005%\u0003bBA(\u0007\u0011%\u0011\u0011\u000b\u0005\b\u0003W\u001aA\u0011BA7\u0011\u001d\t\th\u0001C\u0001\u0003gBq!!\u001e\u0004\t\u0003\t\u0019\bC\u0004\u0002x\r!\t%!\u001f\t\u0013\u0005m4!!A\u0005\u0002\u0005u\u0004\"CAC\u0007E\u0005I\u0011AAD\u0011%\tijAI\u0001\n\u0003\t9\tC\u0005\u0002 \u000e\t\n\u0011\"\u0001\u0002\b\"I\u0011\u0011U\u0002\u0002\u0002\u0013\u0005\u00131\u000f\u0005\n\u0003G\u001b\u0011\u0011!C\u0001\u0003KC\u0011\"!,\u0004\u0003\u0003%\t!a,\t\u0013\u0005m6!!A\u0005B\u0005u\u0006\"CAf\u0007\u0005\u0005I\u0011AAg\u0011%\t\tnAA\u0001\n\u0003\n\u0019\u000eC\u0005\u0002X\u000e\t\t\u0011\"\u0011\u0002Z\"I\u00111\\\u0002\u0002\u0002\u0013\u0005\u0013Q\\\u0004\n\u0003C\u0004\u0011\u0011!E\u0001\u0003G4\u0001b\u0019\u0001\u0002\u0002#\u0005\u0011Q\u001d\u0005\u00075*\"\t!!@\t\u0013\u0005]$&!A\u0005F\u0005e\u0004\"CA\u0000U\u0005\u0005I\u0011\u0011B\u0001\u0011%\u0011IAKA\u0001\n\u0003\u0013YAB\u0004\u0003\u001e\u0001\t\tAa\b\t\u0011Q|#Q1A\u0005\u0002UD\u0001\"`\u0018\u0003\u0002\u0003\u0006IA\u001e\u0005\u00075>\"\tA!\t\t\u0013\u0005=qF1A\u0005\u0006\u0005-\u0002\u0002\u0003B\u0014_\u0001\u0006i!!\u0005\t\u0011\t%r\u0006)A\u0005\u0005WA\u0001B!\u000e0A\u0003%\u0011q\u0015\u0005\b\u0005\u0003zc\u0011\u0003B\"\u0011\u001d\u00119e\fD\t\u0005\u0013BqA!\u00140\t#\u0011y\u0005C\u0004\u0003Z=\"\tBa\u0017\t\u0011\t\u0015t\u0006)A\u0005\u0005OB!B!\u001f0\u0001\u0004\u0005\t\u0015)\u0003w\u0011)\u0011Yh\fa\u0001\u0002\u0003\u0006KA\u001e\u0005\b\u0005{zC\u0011AA\u0016\u0011-\u0011yh\fa\u0001\u0002\u0003\u0006K!!\u0005\t\u0015\t\u0005u\u00061A\u0001B\u0003&a\u000f\u0003\u0005\u0003\u0004>\u0002\u000b\u0015\u0002B:\u0011!\u0011)i\fQ!\n\tM\u0004b\u0002BD_\u0011%!\u0011\u0012\u0005\n\u0005#{\u0003R1A\u0005\u0012UDqAa%0\t\u0013\u0011I\tC\u0004\u0003\u001e>\"IA!#\t\u000by|C\u0011A;\t\r\u0005\u0005q\u0006\"\u0001v\u0011\u001d\u0011\tk\fC\u0001\u0003{AqAa)0\t\u0003\u0011)\u000bC\u0004\u0003(>\"\tA!+\t\u000f\t=v\u0006\"\u0002\u0003\n\nY1+_7c_2\u0004\u0016-\u001b:t\u0015\ty\u0005+\u0001\u0005j]R,'O\\1m\u0015\t\t&+A\u0004sK\u001adWm\u0019;\u000b\u0003M\u000bQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001-B\u0011q\u000bW\u0007\u0002%&\u0011\u0011L\u0015\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005a\u0006CA/\u0001\u001b\u0005q\u0015AB4m_\n\fG.F\u0001a!\ti\u0016-\u0003\u0002c\u001d\nY1+_7c_2$\u0016M\u00197f\u0005)\u0019\u00160\u001c2pYB\u000b\u0017N]\n\u0005\u0007Y+\u0007\u000e\u0005\u0002XM&\u0011qM\u0015\u0002\b!J|G-^2u!\tI\u0017O\u0004\u0002k_:\u00111N\\\u0007\u0002Y*\u0011Q\u000eV\u0001\u0007yI|w\u000e\u001e \n\u0003MK!\u0001\u001d*\u0002\u000fA\f7m[1hK&\u0011!o\u001d\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003aJ\u000bAAY1tKV\ta\u000f\u0005\u0002xs:\u0011\u0001PA\u0007\u0002\u0001%\u0011!p\u001f\u0002\u0007'fl'm\u001c7\n\u0005qt%aB*z[\n|Gn]\u0001\u0006E\u0006\u001cX\rI\u0001\u0004Y><\u0018\u0001\u00027po\u0002\nA\u0001[5hQ\u0006)\u0001.[4iAQA\u0011qAA\u0005\u0003\u0017\ti\u0001\u0005\u0002y\u0007!)AO\u0003a\u0001m\")aP\u0003a\u0001m\"1\u0011\u0011\u0001\u0006A\u0002Y\fAa]3mMB\u0019q/a\u0005\n\t\u0005U\u0011q\u0003\u0002\u0005)f\u0004X-C\u0002\u0002\u001a9\u0013Q\u0001V=qKN\f1\u0001]8t+\t\ty\u0002E\u0002x\u0003CIA!a\t\u0002&\tA\u0001k\\:ji&|g.C\u0002\u0002(9\u0013\u0011\u0002U8tSRLwN\\:\u0002\u0011I|w\u000e\u001e+za\u0016,\"!!\u0005\u0002\u000f1|w\u000fV=qK\u0006IAn\\<Fe\u0006\u001cX\rZ\u0001\u000eY><8\t\\1tg\n{WO\u001c3\u0002\u0011!Lw\r\u001b+za\u0016\f\u0001\u0002[5hQ&sgm\\\u0001\u000bQ&<\u0007.\u0012:bg\u0016$\u0017A\u00045jO\"\u001cE.Y:t\u0005>,h\u000eZ\u0001\fSN,%O]8oK>,8/\u0006\u0002\u0002@A\u0019q+!\u0011\n\u0007\u0005\r#KA\u0004C_>dW-\u00198\u0002\u0011M\fW.Z&j]\u0012\f\u0001c\u00197bgN\u0014u.\u001e8e\u0003N\u001cV-\u001a8\u0015\t\u0005E\u00111\n\u0005\u0007\u0003\u001b:\u0002\u0019\u0001<\u0002\tQ\u001c\u00180\\\u0001\u0010[\u0016l'-\u001a:EK\u001a\u001cFO]5oOR1\u00111KA2\u0003O\u0002B!!\u0016\u0002`5\u0011\u0011q\u000b\u0006\u0005\u00033\nY&\u0001\u0003mC:<'BAA/\u0003\u0011Q\u0017M^1\n\t\u0005\u0005\u0014q\u000b\u0002\u0007'R\u0014\u0018N\\4\t\r\u0005\u0015\u0004\u00041\u0001w\u0003\r\u0019\u00180\u001c\u0005\b\u0003SB\u0002\u0019AA \u0003\u00159\b.\u001a:f\u0003-9\b.\u001a:f'R\u0014\u0018N\\4\u0015\t\u0005M\u0013q\u000e\u0005\u0007\u0003KJ\u0002\u0019\u0001<\u0002\u00131|wo\u0015;sS:<WCAA*\u0003)A\u0017n\u001a5TiJLgnZ\u0001\ti>\u001cFO]5oOR\u0011\u00111K\u0001\u0005G>\u0004\u0018\u0010\u0006\u0005\u0002\b\u0005}\u0014\u0011QAB\u0011\u001d!X\u0004%AA\u0002YDqA`\u000f\u0011\u0002\u0003\u0007a\u000f\u0003\u0005\u0002\u0002u\u0001\n\u00111\u0001w\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!#+\u0007Y\fYi\u000b\u0002\u0002\u000eB!\u0011qRAM\u001b\t\t\tJ\u0003\u0003\u0002\u0014\u0006U\u0015!C;oG\",7m[3e\u0015\r\t9JU\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAN\u0003#\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nabY8qs\u0012\"WMZ1vYR$3'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003O\u00032aVAU\u0013\r\tYK\u0015\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003c\u000b9\fE\u0002X\u0003gK1!!.S\u0005\r\te.\u001f\u0005\n\u0003s\u001b\u0013\u0011!a\u0001\u0003O\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA`!\u0019\t\t-a2\u000226\u0011\u00111\u0019\u0006\u0004\u0003\u000b\u0014\u0016AC2pY2,7\r^5p]&!\u0011\u0011ZAb\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005}\u0012q\u001a\u0005\n\u0003s+\u0013\u0011!a\u0001\u0003c\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u00111KAk\u0011%\tILJA\u0001\u0002\u0004\t9+\u0001\u0005iCND7i\u001c3f)\t\t9+\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u007f\ty\u000eC\u0005\u0002:\"\n\t\u00111\u0001\u00022\u0006Q1+_7c_2\u0004\u0016-\u001b:\u0011\u0005aT3#\u0002\u0016\u0002h\u0006M\b#CAu\u0003_4hO^A\u0004\u001b\t\tYOC\u0002\u0002nJ\u000bqA];oi&lW-\u0003\u0003\u0002r\u0006-(!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ogA!\u0011Q_A~\u001b\t\t9P\u0003\u0003\u0002z\u0006m\u0013AA5p\u0013\r\u0011\u0018q\u001f\u000b\u0003\u0003G\fQ!\u00199qYf$\u0002\"a\u0002\u0003\u0004\t\u0015!q\u0001\u0005\u0006i6\u0002\rA\u001e\u0005\u0006}6\u0002\rA\u001e\u0005\u0007\u0003\u0003i\u0003\u0019\u0001<\u0002\u000fUt\u0017\r\u001d9msR!!Q\u0002B\r!\u00159&q\u0002B\n\u0013\r\u0011\tB\u0015\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r]\u0013)B\u001e<w\u0013\r\u00119B\u0015\u0002\u0007)V\u0004H.Z\u001a\t\u0013\tma&!AA\u0002\u0005\u001d\u0011a\u0001=%a\t11)\u001e:t_J\u001c\"a\f,\u0015\t\t\r\"Q\u0005\t\u0003q>BQ\u0001\u001e\u001aA\u0002Y\fQa]3mM\u0002\nQ\u0001Z3dYN\u00042a\u001eB\u0017\u0013\u0011\u0011yC!\r\u0003\u000bM\u001bw\u000e]3\n\u0007\tMbJ\u0001\u0004TG>\u0004Xm]\u0001\u0005g&TX\rK\u00027\u0005s\u0001BAa\u000f\u0003>5\u0011\u0011QS\u0005\u0005\u0005\u007f\t)J\u0001\u0004v]V\u001cX\rZ\u0001\bKb\u001cG.\u001e3f)\u0011\tyD!\u0012\t\r\u0005\u0015t\u00071\u0001w\u0003\u001di\u0017\r^2iKN$B!a\u0010\u0003L!1\u0011\u0011\u0001\u001dA\u0002Y\fQb]6ja>;h.\u001a:QC&\u0014HCBA \u0005#\u0012)\u0006\u0003\u0004\u0003Te\u0002\rA^\u0001\tY><8\t\\1tg\"1!qK\u001dA\u0002Y\f\u0011\u0002[5hQ\u000ec\u0017m]:\u0002\u000b\t\f7/Z:\u0016\u0005\tu\u0003#\u0002B0\u0005C2hBA,p\u0013\r\u0011\u0019g\u001d\u0002\u0005\u0019&\u001cH/A\u0004wSNLG/\u001a3\u0011\r\t%$q\u000eB:\u001b\t\u0011YGC\u0002\u0003n9\u000bA!\u001e;jY&!!\u0011\u000fB6\u0005\u001dA\u0015m\u001d5TKR\u00042a\u001eB;\u0013\u0011\u00119H!\r\u0003\u0015M\u001bw\u000e]3F]R\u0014\u00180A\u0005m_^\u001c\u00160\u001c2pY\u0006Q\u0001.[4i'fl'm\u001c7\u0002\u001b1|w/T3nE\u0016\u0014H+\u001f9f\u0003Iawn^'f[\n,'\u000fV=qK\u000e\u000b7\r[3\u0002+1|w/T3nE\u0016\u0014H+\u001f9f\u0007\u0006\u001c\u0007.Z*z[\u0006A1-\u001e:F]R\u0014\u00180A\u0005oKb$XI\u001c;ss\u0006!\u0011N\\5u)\t\u0011Y\tE\u0002X\u0005\u001bK1Aa$S\u0005\u0011)f.\u001b;\u0002\u001d9|g\u000e\u0016:bSR\u0004\u0016M]3oi\u0006\u0001\u0012\r\u001a<b]\u000e,g*\u001a=u\u000b:$(/\u001f\u0015\u0004\u000b\n]\u0005\u0003\u0002B\u001e\u00053KAAa'\u0002\u0016\n9A/Y5me\u0016\u001c\u0017aD1em\u0006t7-Z\"ve\u0016sGO]=)\u0007\u0019\u00139*A\u0004iCNtU\r\u001f;\u0002\u0017\r,(O]3oiB\u000b\u0017N]\u000b\u0003\u0003\u000f\t\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0005W\u0003bAa\u0018\u0003.\u0006\u001d\u0011bAAeg\u0006!a.\u001a=uQ\ra%q\u0013"
)
public abstract class SymbolPairs {
   private volatile SymbolPair$ SymbolPair$module;

   public SymbolPair$ SymbolPair() {
      if (this.SymbolPair$module == null) {
         this.SymbolPair$lzycompute$1();
      }

      return this.SymbolPair$module;
   }

   public abstract SymbolTable global();

   private final void SymbolPair$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SymbolPair$module == null) {
            this.SymbolPair$module = new SymbolPair$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   public final class SymbolPair implements Product, Serializable {
      private final Symbols.Symbol base;
      private final Symbols.Symbol low;
      private final Symbols.Symbol high;
      private final Types.Type self;
      // $FF: synthetic field
      private final SymbolPairs $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Symbols.Symbol base() {
         return this.base;
      }

      public Symbols.Symbol low() {
         return this.low;
      }

      public Symbols.Symbol high() {
         return this.high;
      }

      public Position pos() {
         Symbols.Symbol var10000 = this.low().owner();
         Symbols.Symbol var1 = this.base();
         if (var10000 == null) {
            if (var1 == null) {
               return this.low().pos();
            }
         } else if (var10000.equals(var1)) {
            return this.low().pos();
         }

         var10000 = this.high().owner();
         Symbols.Symbol var2 = this.base();
         if (var10000 == null) {
            if (var2 == null) {
               return this.high().pos();
            }
         } else if (var10000.equals(var2)) {
            return this.high().pos();
         }

         return this.base().pos();
      }

      public Types.Type rootType() {
         return this.self;
      }

      public Types.Type lowType() {
         return this.self.memberType(this.low());
      }

      public Types.Type lowErased() {
         Erasure var10000 = this.$outer.global().erasure();
         Symbols.Symbol var10001 = this.low();
         Symbols.Symbol var10002 = this.low();
         if (var10002 == null) {
            throw null;
         } else {
            return var10000.specialErasure(var10001, var10002.tpe_$times());
         }
      }

      public Types.Type lowClassBound() {
         Symbols.Symbol var10001 = this.low();
         if (var10001 == null) {
            throw null;
         } else {
            return this.classBoundAsSeen(var10001.tpe_$times().typeSymbol());
         }
      }

      public Types.Type highType() {
         return this.self.memberType(this.high());
      }

      public Types.Type highInfo() {
         return this.self.memberInfo(this.high());
      }

      public Types.Type highErased() {
         Erasure var10000 = this.$outer.global().erasure();
         Symbols.Symbol var10001 = this.high();
         Symbols.Symbol var10002 = this.high();
         if (var10002 == null) {
            throw null;
         } else {
            return var10000.specialErasure(var10001, var10002.tpe_$times());
         }
      }

      public Types.Type highClassBound() {
         Symbols.Symbol var10001 = this.high();
         if (var10001 == null) {
            throw null;
         } else {
            return this.classBoundAsSeen(var10001.tpe_$times().typeSymbol());
         }
      }

      public boolean isErroneous() {
         Symbols.Symbol var10000 = this.low();
         if (var10000 == null) {
            throw null;
         } else {
            if (!var10000.tpe_$times().isErroneous()) {
               var10000 = this.high();
               if (var10000 == null) {
                  throw null;
               }

               if (!var10000.tpe_$times().isErroneous()) {
                  return false;
               }
            }

            return true;
         }
      }

      public boolean sameKind() {
         SymbolTable var10000 = this.$outer.global();
         List var10001 = this.low().typeParams();
         List sameLength_xs2 = this.high().typeParams();
         List sameLength_xs1 = var10001;
         if (var10000 == null) {
            throw null;
         } else {
            return Collections.sameLength$(var10000, sameLength_xs1, sameLength_xs2);
         }
      }

      private Types.Type classBoundAsSeen(final Symbols.Symbol tsym) {
         return tsym.classBound().asSeenFrom(this.rootType(), tsym.owner());
      }

      private String memberDefString(final Symbols.Symbol sym, final boolean where) {
         String def_s = sym.isConstructor() ? (new StringBuilder(2)).append(sym).append(": ").append(this.self.memberType(sym)).toString() : sym.defStringSeenAs(this.self.memberType(sym));
         return (new StringBuilder(0)).append(def_s).append(this.whereString(sym)).toString();
      }

      private String whereString(final Symbols.Symbol sym) {
         Symbols.Symbol var10000 = sym.owner();
         Symbols.Symbol var2 = this.base();
         if (var10000 == null) {
            if (var2 == null) {
               return (new StringBuilder(9)).append(" at line ").append(sym.pos().line()).toString();
            }
         } else if (var10000.equals(var2)) {
            return (new StringBuilder(9)).append(" at line ").append(sym.pos().line()).toString();
         }

         return sym.locationString();
      }

      public String lowString() {
         return this.memberDefString(this.low(), true);
      }

      public String highString() {
         return this.memberDefString(this.high(), true);
      }

      public String toString() {
         package$ var10000 = package$.MODULE$;
         StringContext StringContextStripMarginOps_stringContext = new StringContext(.MODULE$.wrapRefArray(new String[]{"\n      |Cursor(in ", ") {\n      |   high  ", "\n      | erased  ", "\n      |  infos  ", "\n      |    low  ", "\n      | erased  ", "\n      |  infos  ", "\n      |}"}));
         package.StringContextStripMarginOps var3 = new package.StringContextStripMarginOps(StringContextStripMarginOps_stringContext);
         Object var2 = null;
         return StripMarginInterpolator.sm$(var3, .MODULE$.genericWrapArray(new Object[]{this.base(), this.highString(), this.highErased(), this.high().infosString(), this.lowString(), this.lowErased(), this.low().infosString()})).trim();
      }

      public SymbolPair copy(final Symbols.Symbol base, final Symbols.Symbol low, final Symbols.Symbol high) {
         return this.$outer.new SymbolPair(base, low, high);
      }

      public Symbols.Symbol copy$default$1() {
         return this.base();
      }

      public Symbols.Symbol copy$default$2() {
         return this.low();
      }

      public Symbols.Symbol copy$default$3() {
         return this.high();
      }

      public String productPrefix() {
         return "SymbolPair";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.base();
            case 1:
               return this.low();
            case 2:
               return this.high();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof SymbolPair;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "base";
            case 1:
               return "low";
            case 2:
               return "high";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof SymbolPair) {
               SymbolPair var2 = (SymbolPair)x$1;
               Symbols.Symbol var10000 = this.base();
               Symbols.Symbol var3 = var2.base();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               var10000 = this.low();
               Symbols.Symbol var4 = var2.low();
               if (var10000 == null) {
                  if (var4 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var4)) {
                  return false;
               }

               var10000 = this.high();
               Symbols.Symbol var5 = var2.high();
               if (var10000 == null) {
                  if (var5 == null) {
                     return true;
                  }
               } else if (var10000.equals(var5)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      public SymbolPair(final Symbols.Symbol base, final Symbols.Symbol low, final Symbols.Symbol high) {
         this.base = base;
         this.low = low;
         this.high = high;
         if (SymbolPairs.this == null) {
            throw null;
         } else {
            this.$outer = SymbolPairs.this;
            super();
            this.self = base.thisType();
         }
      }
   }

   public class SymbolPair$ extends AbstractFunction3 implements Serializable {
      // $FF: synthetic field
      private final SymbolPairs $outer;

      public final String toString() {
         return "SymbolPair";
      }

      public SymbolPair apply(final Symbols.Symbol base, final Symbols.Symbol low, final Symbols.Symbol high) {
         return this.$outer.new SymbolPair(base, low, high);
      }

      public Option unapply(final SymbolPair x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.base(), x$0.low(), x$0.high())));
      }

      public SymbolPair$() {
         if (SymbolPairs.this == null) {
            throw null;
         } else {
            this.$outer = SymbolPairs.this;
            super();
         }
      }
   }

   public abstract class Cursor {
      private Symbols.Symbol nonTraitParent;
      private final Symbols.Symbol base;
      private final Types.Type self;
      private final Scopes.Scope decls;
      private final int size;
      private final HashSet visited;
      private Symbols.Symbol lowSymbol;
      private Symbols.Symbol highSymbol;
      private Types.Type lowMemberTypeCache;
      private Symbols.Symbol lowMemberTypeCacheSym;
      private Scopes.ScopeEntry curEntry;
      private Scopes.ScopeEntry nextEntry;
      private volatile boolean bitmap$0;
      // $FF: synthetic field
      public final SymbolPairs $outer;

      public Symbols.Symbol base() {
         return this.base;
      }

      public final Types.Type self() {
         return this.self;
      }

      public abstract boolean exclude(final Symbols.Symbol sym);

      public abstract boolean matches(final Symbols.Symbol high);

      public boolean skipOwnerPair(final Symbols.Symbol lowClass, final Symbols.Symbol highClass) {
         return false;
      }

      public List bases() {
         return this.base().info().baseClasses();
      }

      public Types.Type lowMemberType() {
         if (this.lowSymbol != this.lowMemberTypeCacheSym) {
            this.lowMemberTypeCache = this.self().memberType(this.lowSymbol);
            this.lowMemberTypeCacheSym = this.lowSymbol;
         }

         return this.lowMemberTypeCache;
      }

      private void init() {
         this.fillDecls$1(this.bases(), true);
         this.fillDecls$1(this.bases(), false);
      }

      private Symbols.Symbol nonTraitParent$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.nonTraitParent = this.base().info().firstParent().typeSymbol().filter((sym) -> BoxesRunTime.boxToBoolean($anonfun$nonTraitParent$1(this, sym)));
               this.bitmap$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.nonTraitParent;
      }

      public Symbols.Symbol nonTraitParent() {
         return !this.bitmap$0 ? this.nonTraitParent$lzycompute() : this.nonTraitParent;
      }

      private void advanceNextEntry() {
         while(true) {
            if (this.nextEntry != null) {
               this.nextEntry = this.decls.lookupNextEntry(this.nextEntry);
               if (this.nextEntry != null) {
                  Symbols.Symbol high = this.nextEntry.sym();
                  boolean var10000;
                  if (this.matches(high)) {
                     this.visited.addEntry(this.nextEntry);
                     var10000 = true;
                  } else {
                     var10000 = false;
                  }

                  if (!var10000 || this.skipOwnerPair(this.low().owner(), high.owner())) {
                     continue;
                  }

                  this.highSymbol = high;
                  return;
               }
            }

            return;
         }
      }

      private void advanceCurEntry() {
         while(true) {
            if (this.curEntry != null) {
               this.curEntry = this.curEntry.next();
               if (this.curEntry != null) {
                  if (this.visited.apply(this.curEntry) || this.exclude(this.curEntry.sym())) {
                     continue;
                  }

                  this.nextEntry = this.curEntry;
                  return;
               }
            }

            return;
         }
      }

      public Symbols.Symbol low() {
         return this.lowSymbol;
      }

      public Symbols.Symbol high() {
         return this.highSymbol;
      }

      public boolean hasNext() {
         return this.curEntry != null;
      }

      public SymbolPair currentPair() {
         return this.scala$reflect$internal$SymbolPairs$Cursor$$$outer().new SymbolPair(this.base(), this.low(), this.high());
      }

      public Iterator iterator() {
         return new AbstractIterator() {
            // $FF: synthetic field
            private final Cursor $outer;

            public boolean hasNext() {
               return this.$outer.hasNext();
            }

            public SymbolPair next() {
               SymbolPair var10000;
               try {
                  var10000 = this.$outer.currentPair();
               } finally {
                  this.$outer.next();
               }

               return var10000;
            }

            public {
               if (Cursor.this == null) {
                  throw null;
               } else {
                  this.$outer = Cursor.this;
               }
            }
         };
      }

      public final void next() {
         while(true) {
            if (this.curEntry != null) {
               this.lowSymbol = this.curEntry.sym();
               this.advanceNextEntry();
               if (this.nextEntry == null) {
                  this.advanceCurEntry();
                  continue;
               }
            }

            return;
         }
      }

      // $FF: synthetic method
      public SymbolPairs scala$reflect$internal$SymbolPairs$Cursor$$$outer() {
         return this.$outer;
      }

      private final void fillDecls$1(final List bcs, final boolean deferred) {
         if (!bcs.isEmpty()) {
            this.fillDecls$1((List)bcs.tail(), deferred);

            for(Scopes.ScopeEntry e = ((Symbols.Symbol)bcs.head()).info().decls().elems(); e != null; e = e.next()) {
               if (e.sym().initialize().isDeferred() == deferred && !this.exclude(e.sym())) {
                  this.decls.enter(e.sym());
               }
            }

         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$nonTraitParent$1(final Cursor $this, final Symbols.Symbol sym) {
         return !sym.isTrait() && !sym.equals($this.scala$reflect$internal$SymbolPairs$Cursor$$$outer().global().definitions().ObjectClass());
      }

      public Cursor(final Symbols.Symbol base) {
         this.base = base;
         if (SymbolPairs.this == null) {
            throw null;
         } else {
            this.$outer = SymbolPairs.this;
            super();
            this.self = base.thisType();
            this.decls = SymbolPairs.this.global().newScope();
            this.size = this.bases().length();
            HashSet$ var10001 = HashSet$.MODULE$;
            byte apply_initialCapacity = 64;
            String apply_label = "visited";
            HashSet var6 = new HashSet(apply_label, apply_initialCapacity);
            Object var5 = null;
            this.visited = var6;
            this.init();
            this.curEntry = this.decls.elems();
            this.nextEntry = this.curEntry;
            this.next();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
