package scala.reflect.internal;

import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de!C$I!\u0003\r\taTA@\u0011\u0015I\u0006\u0001\"\u0001[\u000b\u0011q\u0006\u0001A0\t\u000f\t\u0004!\u0019!C\u0002G\")!\u000e\u0001C\u0002W\u001a!\u0011\u000f\u0001\u0003s\u0011!\u0001XA!A!\u0002\u0013y\u0006\"B:\u0006\t\u0003!\b\"B<\u0006\t\u0003A\bbB>\u0001\u0005\u0004%\t\u0001`\u0004\u0006{\u0002A\tA \u0004\u0007\u007f\u0002A\t!!\u0001\t\rM\\A\u0011AA\u0005\u0011!\tYa\u0003b\u0001\n\u0003a\bbBA\u0007\u0017\u0001\u0006I\u0001\u001b\u0005\t\u0003\u001fY!\u0019!C\u0001y\"9\u0011\u0011C\u0006!\u0002\u0013A\u0007\u0002CA\n\u0017\t\u0007I\u0011\u0001?\t\u000f\u0005U1\u0002)A\u0005Q\"A\u0011qC\u0006C\u0002\u0013\u0005A\u0010C\u0004\u0002\u001a-\u0001\u000b\u0011\u00025\t\u0011\u0005m1B1A\u0005\u0002qDq!!\b\fA\u0003%\u0001\u000e\u0003\u0005\u0002 -\u0011\r\u0011\"\u0001}\u0011\u001d\t\tc\u0003Q\u0001\n!D\u0001\"a\t\f\u0005\u0004%\t\u0001 \u0005\b\u0003KY\u0001\u0015!\u0003i\u0011!\t9c\u0003b\u0001\n\u0003a\bbBA\u0015\u0017\u0001\u0006I\u0001\u001b\u0005\t\u0003WY!\u0019!C\u0001y\"9\u0011QF\u0006!\u0002\u0013A\u0007\u0002CA\u0018\u0017\t\u0007I\u0011\u0001?\t\u000f\u0005E2\u0002)A\u0005Q\"A\u00111G\u0006C\u0002\u0013\u0005A\u0010C\u0004\u00026-\u0001\u000b\u0011\u00025\t\u0011\u0005]2B1A\u0005\u0002qDq!!\u000f\fA\u0003%\u0001\u000e\u0003\u0005\u0002<-\u0011\r\u0011\"\u0001}\u0011\u001d\tid\u0003Q\u0001\n!D\u0001\"a\u0010\f\u0005\u0004%\t\u0001 \u0005\b\u0003\u0003Z\u0001\u0015!\u0003i\u0011!\t\u0019e\u0003b\u0001\n\u0003a\bbBA#\u0017\u0001\u0006I\u0001\u001b\u0005\t\u0003\u000fZ!\u0019!C\u0001y\"9\u0011\u0011J\u0006!\u0002\u0013A\u0007\u0002CA&\u0017\t\u0007I\u0011\u0001?\t\u000f\u000553\u0002)A\u0005Q\"A\u0011qJ\u0006C\u0002\u0013\u0005A\u0010C\u0004\u0002R-\u0001\u000b\u0011\u00025\t\u0011\u0005M3B1A\u0005\u0002qDq!!\u0016\fA\u0003%\u0001\u000e\u0003\u0005\u0002X-\u0011\r\u0011\"\u0001}\u0011\u001d\tIf\u0003Q\u0001\n!D\u0001\"a\u0017\f\u0005\u0004%\t\u0001 \u0005\b\u0003;Z\u0001\u0015!\u0003i\u0011!\tyf\u0003b\u0001\n\u0003a\bbBA1\u0017\u0001\u0006I\u0001\u001b\u0005\t\u0003GZ!\u0019!C\u0001y\"9\u0011QM\u0006!\u0002\u0013A\u0007\u0002CA4\u0017\t\u0007I\u0011\u0001?\t\u000f\u0005%4\u0002)A\u0005Q\"A\u00111N\u0006C\u0002\u0013\u0005A\u0010C\u0004\u0002n-\u0001\u000b\u0011\u00025\t\u0011\u0005=4B1A\u0005\u0002qDq!!\u001d\fA\u0003%\u0001\u000e\u0003\u0005\u0002t-\u0011\r\u0011\"\u0001}\u0011\u001d\t)h\u0003Q\u0001\n!D\u0001\"a\u001e\f\u0005\u0004%\t\u0001 \u0005\b\u0003sZ\u0001\u0015!\u0003i\u0011!\tYh\u0003b\u0001\n\u0003a\bbBA?\u0017\u0001\u0006I\u0001\u001b\u0002\t\r2\fwmU3ug*\u0011\u0011JS\u0001\tS:$XM\u001d8bY*\u00111\nT\u0001\be\u00164G.Z2u\u0015\u0005i\u0015!B:dC2\f7\u0001A\n\u0004\u0001A#\u0006CA)S\u001b\u0005a\u0015BA*M\u0005\u0019\te.\u001f*fMB\u0011Q\u000bW\u0007\u0002-*\u0011qKS\u0001\u0004CBL\u0017BA$W\u0003\u0019!\u0013N\\5uIQ\t1\f\u0005\u0002R9&\u0011Q\f\u0014\u0002\u0005+:LGOA\u0004GY\u0006<7+\u001a;\u0011\u0005E\u0003\u0017BA1M\u0005\u0011auN\\4\u0002\u0015\u0019c\u0017mZ*fiR\u000bw-F\u0001e!\r)g\r[\u0007\u0002\u0015&\u0011qM\u0013\u0002\t\u00072\f7o\u001d+bOB\u0011\u0011NA\u0007\u0002\u0001\u0005Q\u0011\r\u001a3GY\u0006<w\n]:\u0015\u00051|\u0007CA5n\u0013\tq\u0007LA\u0004GY\u0006<w\n]:\t\u000bA$\u0001\u0019\u00015\u0002\t1,g\r\u001e\u0002\f\r2\fwm\u00149t\u00136\u0004HnE\u0002\u0006!2\fa\u0001P5oSRtDCA;w!\tIW\u0001C\u0003q\u000f\u0001\u0007q,\u0001\u0003%E\u0006\u0014HCA0z\u0011\u0015Q\b\u00021\u0001`\u0003\u0015\u0011\u0018n\u001a5u\u0003\u001dquN\u00127bON,\u0012\u0001[\u0001\u0005\r2\fw\r\u0005\u0002j\u0017\t!a\t\\1h'\u0011Y\u0001+a\u0001\u0011\u0007%\f)!C\u0002\u0002\ba\u0013!B\u00127bOZ\u000bG.^3t)\u0005q\u0018!\u0002+S\u0003&#\u0016A\u0002+S\u0003&#\u0006%A\u0005J\u001dR+%KR!D\u000b\u0006Q\u0011J\u0014+F%\u001a\u000b5)\u0012\u0011\u0002\u000f5+F+\u0011\"M\u000b\u0006AQ*\u0016+B\u00052+\u0005%A\u0003N\u0003\u000e\u0013v*\u0001\u0004N\u0003\u000e\u0013v\nI\u0001\t\t\u00163UI\u0015*F\t\u0006IA)\u0012$F%J+E\tI\u0001\t\u0003\n\u001bFKU!D)\u0006I\u0011IQ*U%\u0006\u001bE\u000bI\u0001\u0006\r&s\u0015\tT\u0001\u0007\r&s\u0015\t\u0014\u0011\u0002\rM+\u0015\tT#E\u0003\u001d\u0019V)\u0011'F\t\u0002\n\u0001\"S'Q\u0019&\u001b\u0015\nV\u0001\n\u00136\u0003F*S\"J)\u0002\nA\u0001T![3\u0006)A*\u0011.ZA\u0005AqJV#S%&#U)A\u0005P-\u0016\u0013&+\u0013#FA\u00059\u0001KU%W\u0003R+\u0015\u0001\u0003)S\u0013Z\u000bE+\u0012\u0011\u0002\u0013A\u0013v\nV#D)\u0016#\u0015A\u0003)S\u001fR+5\tV#EA\u0005)AjT\"B\u0019\u00061AjT\"B\u0019\u0002\nAaQ!T\u000b\u0006)1)Q*FA\u0005Y\u0011IQ*P-\u0016\u0013&+\u0013#F\u00031\t%iU(W\u000bJ\u0013\u0016\nR#!\u0003-\u0011\u0015LT!N\u000bB\u000b%+Q'\u0002\u0019\tKf*Q'F!\u0006\u0013\u0016)\u0014\u0011\u0002\u000bA\u000b%+Q'\u0002\rA\u000b%+Q'!\u0003%\u0019uJV!S\u0013\u0006sE+\u0001\u0006D\u001fZ\u000b%+S!O)\u0002\nQbQ(O)J\u000be+\u0011*J\u0003:#\u0016AD\"P\u001dR\u0013\u0016IV!S\u0013\u0006sE\u000bI\u0001\r\t\u00163\u0015)\u0016'U!\u0006\u0013\u0016)T\u0001\u000e\t\u00163\u0015)\u0016'U!\u0006\u0013\u0016)\u0014\u0011\u0002\u0011A\u0013ViU+Q\u000bJ\u000b\u0011\u0002\u0015*F'V\u0003VI\u0015\u0011\u0002\u0017\u0011+e)Q+M)&s\u0015\nV\u0001\r\t\u00163\u0015)\u0016'U\u0013:KE\u000bI\u0001\u0005\u000b:+V*A\u0003F\u001dVk\u0005%A\u0007Q\u0003J\u000bU*Q\"D\u000bN\u001bvJU\u0001\u000f!\u0006\u0013\u0016)T!D\u0007\u0016\u001b6k\u0014*!\u00031\u0019\u0015iU#B\u0007\u000e+5kU(S\u00035\u0019\u0015iU#B\u0007\u000e+5kU(SA\u0005I1+\u0017(U\u0011\u0016#\u0016jQ\u0001\u000b'fsE\u000bS#U\u0013\u000e\u0003\u0013\u0001C!S)&3\u0015i\u0011+\u0002\u0013\u0005\u0013F+\u0013$B\u0007R\u0003\u0013AB*U\u0003\ncU)A\u0004T)\u0006\u0013E*\u0012\u0011\u0011\t\u0005\u0005\u00151Q\u0007\u0002\u0011&\u0019\u0011Q\u0011%\u0003\u0017MKXNY8m)\u0006\u0014G.\u001a"
)
public interface FlagSets extends scala.reflect.api.FlagSets {
   Flag$ Flag();

   void scala$reflect$internal$FlagSets$_setter_$FlagSetTag_$eq(final ClassTag x$1);

   void scala$reflect$internal$FlagSets$_setter_$NoFlags_$eq(final long x$1);

   ClassTag FlagSetTag();

   // $FF: synthetic method
   static scala.reflect.api.FlagSets.FlagOps addFlagOps$(final FlagSets $this, final long left) {
      return $this.addFlagOps(left);
   }

   default scala.reflect.api.FlagSets.FlagOps addFlagOps(final long left) {
      return (SymbolTable)this.new FlagOpsImpl(left);
   }

   long NoFlags();

   static void $init$(final FlagSets $this) {
      $this.scala$reflect$internal$FlagSets$_setter_$FlagSetTag_$eq(.MODULE$.apply(Long.TYPE));
      $this.scala$reflect$internal$FlagSets$_setter_$NoFlags_$eq(0L);
   }

   private class FlagOpsImpl implements scala.reflect.api.FlagSets.FlagOps {
      private final long left;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public long $bar(final long right) {
         return this.left | right;
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$FlagSets$FlagOpsImpl$$$outer() {
         return this.$outer;
      }

      public FlagOpsImpl(final long left) {
         this.left = left;
         if (FlagSets.this == null) {
            throw null;
         } else {
            this.$outer = FlagSets.this;
            super();
         }
      }
   }

   public class Flag$ implements scala.reflect.api.FlagSets.FlagValues {
      private final long TRAIT = 33554432L;
      private final long INTERFACE = 128L;
      private final long MUTABLE = 4096L;
      private final long MACRO = 32768L;
      private final long DEFERRED = 16L;
      private final long ABSTRACT = 8L;
      private final long FINAL = 32L;
      private final long SEALED = 1024L;
      private final long IMPLICIT = 512L;
      private final long LAZY = 2147483648L;
      private final long OVERRIDE = 2L;
      private final long PRIVATE = 4L;
      private final long PROTECTED = 1L;
      private final long LOCAL = 524288L;
      private final long CASE = 2048L;
      private final long ABSOVERRIDE = 262144L;
      private final long BYNAMEPARAM = 65536L;
      private final long PARAM = 8192L;
      private final long COVARIANT = 65536L;
      private final long CONTRAVARIANT = 131072L;
      private final long DEFAULTPARAM = 33554432L;
      private final long PRESUPER = 137438953472L;
      private final long DEFAULTINIT = 2199023255552L;
      private final long ENUM = 281474976710656L;
      private final long PARAMACCESSOR = 536870912L;
      private final long CASEACCESSOR = 16777216L;
      private final long SYNTHETIC = 2097152L;
      private final long ARTIFACT = 70368744177664L;
      private final long STABLE = 4194304L;

      public long TRAIT() {
         return this.TRAIT;
      }

      public long INTERFACE() {
         return this.INTERFACE;
      }

      public long MUTABLE() {
         return this.MUTABLE;
      }

      public long MACRO() {
         return this.MACRO;
      }

      public long DEFERRED() {
         return this.DEFERRED;
      }

      public long ABSTRACT() {
         return this.ABSTRACT;
      }

      public long FINAL() {
         return this.FINAL;
      }

      public long SEALED() {
         return this.SEALED;
      }

      public long IMPLICIT() {
         return this.IMPLICIT;
      }

      public long LAZY() {
         return this.LAZY;
      }

      public long OVERRIDE() {
         return this.OVERRIDE;
      }

      public long PRIVATE() {
         return this.PRIVATE;
      }

      public long PROTECTED() {
         return this.PROTECTED;
      }

      public long LOCAL() {
         return this.LOCAL;
      }

      public long CASE() {
         return this.CASE;
      }

      public long ABSOVERRIDE() {
         return this.ABSOVERRIDE;
      }

      public long BYNAMEPARAM() {
         return this.BYNAMEPARAM;
      }

      public long PARAM() {
         return this.PARAM;
      }

      public long COVARIANT() {
         return this.COVARIANT;
      }

      public long CONTRAVARIANT() {
         return this.CONTRAVARIANT;
      }

      public long DEFAULTPARAM() {
         return this.DEFAULTPARAM;
      }

      public long PRESUPER() {
         return this.PRESUPER;
      }

      public long DEFAULTINIT() {
         return this.DEFAULTINIT;
      }

      public long ENUM() {
         return this.ENUM;
      }

      public long PARAMACCESSOR() {
         return this.PARAMACCESSOR;
      }

      public long CASEACCESSOR() {
         return this.CASEACCESSOR;
      }

      public long SYNTHETIC() {
         return this.SYNTHETIC;
      }

      public long ARTIFACT() {
         return this.ARTIFACT;
      }

      public long STABLE() {
         return this.STABLE;
      }
   }
}
