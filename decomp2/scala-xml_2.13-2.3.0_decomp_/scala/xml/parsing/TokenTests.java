package scala.xml.parsing;

import java.lang.invoke.SerializedLambda;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005m3q!\u0004\b\u0011\u0002\u0007\u0005Q\u0003C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0011\u0015\u0001\u0005C\u0003 \u0001\u0011\u0015\u0011\u0006C\u00033\u0001\u0011\u00051\u0007C\u00037\u0001\u0011\u0005q\u0007C\u0003:\u0001\u0011\u0005!\bC\u0003=\u0001\u0011\u0005Q\bC\u0003@\u0001\u0011\u0005\u0001\tC\u0003O\u0001\u0011\u0005q\nC\u0003R\u0001\u0011\u0005!\u000bC\u0003V\u0001\u0011\u0005a\u000bC\u0003Y\u0001\u0011\u0005\u0011L\u0001\u0006U_.,g\u000eV3tiNT!a\u0004\t\u0002\u000fA\f'o]5oO*\u0011\u0011CE\u0001\u0004q6d'\"A\n\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u0006\t\u0003/ai\u0011AE\u0005\u00033I\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u001d!\t9R$\u0003\u0002\u001f%\t!QK\\5u\u0003\u001dI7o\u00159bG\u0016$\"!\t\u0013\u0011\u0005]\u0011\u0013BA\u0012\u0013\u0005\u001d\u0011un\u001c7fC:DQ!\n\u0002A\u0002\u0019\n!a\u00195\u0011\u0005]9\u0013B\u0001\u0015\u0013\u0005\u0011\u0019\u0005.\u0019:\u0015\u0005\u0005R\u0003\"B\u0016\u0004\u0001\u0004a\u0013AA2t!\ri\u0003GJ\u0007\u0002])\u0011qFE\u0001\u000bG>dG.Z2uS>t\u0017BA\u0019/\u0005\r\u0019V-]\u0001\bSN\fE\u000e\u001d5b)\t\tC\u0007C\u00036\t\u0001\u0007a%A\u0001d\u00031I7/\u00117qQ\u0006$\u0015nZ5u)\t\t\u0003\bC\u00036\u000b\u0001\u0007a%\u0001\u0006jg:\u000bW.Z\"iCJ$\"!I\u001e\t\u000b\u00152\u0001\u0019\u0001\u0014\u0002\u0017%\u001ch*Y7f'R\f'\u000f\u001e\u000b\u0003CyBQ!J\u0004A\u0002\u0019\na![:OC6,GCA\u0011B\u0011\u0015\u0011\u0005\u00021\u0001D\u0003\u0005\u0019\bC\u0001#L\u001d\t)\u0015\n\u0005\u0002G%5\tqI\u0003\u0002I)\u00051AH]8pizJ!A\u0013\n\u0002\rA\u0013X\rZ3g\u0013\taUJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0015J\t1\"[:Qk\nLEi\u00115beR\u0011\u0011\u0005\u0015\u0005\u0006K%\u0001\rAJ\u0001\u0014SN4\u0016\r\\5e\u0013\u0006s\u0015)\u00128d_\u0012Lgn\u001a\u000b\u0003CMCQ\u0001\u0016\u0006A\u00021\nA\"[1oC\u0016s7m\u001c3j]\u001e\f!b\u00195fG.\u001c\u0016p]%E)\t\ts\u000bC\u0003C\u0017\u0001\u00071)\u0001\u0006dQ\u0016\u001c7\u000eU;c\u0013\u0012#\"!\t.\t\u000b\tc\u0001\u0019A\""
)
public interface TokenTests {
   // $FF: synthetic method
   static boolean isSpace$(final TokenTests $this, final char ch) {
      return $this.isSpace(ch);
   }

   default boolean isSpace(final char ch) {
      switch (ch) {
         case '\t':
         case '\n':
         case '\r':
         case ' ':
            return true;
         default:
            return false;
      }
   }

   // $FF: synthetic method
   static boolean isSpace$(final TokenTests $this, final Seq cs) {
      return $this.isSpace(cs);
   }

   default boolean isSpace(final Seq cs) {
      return cs.nonEmpty() && cs.forall((ch) -> BoxesRunTime.boxToBoolean($anonfun$isSpace$1(this, BoxesRunTime.unboxToChar(ch))));
   }

   // $FF: synthetic method
   static boolean isAlpha$(final TokenTests $this, final char c) {
      return $this.isAlpha(c);
   }

   default boolean isAlpha(final char c) {
      return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
   }

   // $FF: synthetic method
   static boolean isAlphaDigit$(final TokenTests $this, final char c) {
      return $this.isAlphaDigit(c);
   }

   default boolean isAlphaDigit(final char c) {
      return this.isAlpha(c) || c >= '0' && c <= '9';
   }

   // $FF: synthetic method
   static boolean isNameChar$(final TokenTests $this, final char ch) {
      return $this.isNameChar(ch);
   }

   default boolean isNameChar(final char ch) {
      boolean var3;
      if (!this.isNameStart(ch)) {
         byte var2 = (byte)Character.getType(ch);
         switch (var2) {
            case 4:
            case 6:
            case 7:
            case 8:
            case 9:
               var3 = true;
               break;
            case 5:
            default:
               var3 = .MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(".-:·"), ch);
         }

         if (!var3) {
            var3 = false;
            return var3;
         }
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   static boolean isNameStart$(final TokenTests $this, final char ch) {
      return $this.isNameStart(ch);
   }

   default boolean isNameStart(final char ch) {
      byte var2 = (byte)Character.getType(ch);
      switch (var2) {
         case 1:
         case 2:
         case 3:
         case 5:
         case 10:
            return true;
         default:
            return .MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(":_"), ch);
      }
   }

   // $FF: synthetic method
   static boolean isName$(final TokenTests $this, final String s) {
      return $this.isName(s);
   }

   default boolean isName(final String s) {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(s)) && this.isNameStart(.MODULE$.head$extension(scala.Predef..MODULE$.augmentString(s))) && .MODULE$.forall$extension(scala.Predef..MODULE$.augmentString(.MODULE$.tail$extension(scala.Predef..MODULE$.augmentString(s))), (ch) -> BoxesRunTime.boxToBoolean($anonfun$isName$1(this, BoxesRunTime.unboxToChar(ch))));
   }

   // $FF: synthetic method
   static boolean isPubIDChar$(final TokenTests $this, final char ch) {
      return $this.isPubIDChar(ch);
   }

   default boolean isPubIDChar(final char ch) {
      return this.isAlphaDigit(ch) || this.isSpace(ch) && ch != '\t' || .MODULE$.contains$extension(scala.Predef..MODULE$.augmentString("-\\()+,./:=?;!*#@$_%"), ch);
   }

   // $FF: synthetic method
   static boolean isValidIANAEncoding$(final TokenTests $this, final Seq ianaEncoding) {
      return $this.isValidIANAEncoding(ianaEncoding);
   }

   default boolean isValidIANAEncoding(final Seq ianaEncoding) {
      return ianaEncoding.nonEmpty() && this.isAlpha(BoxesRunTime.unboxToChar(ianaEncoding.head())) && ((IterableOnceOps)ianaEncoding.tail()).forall((c) -> BoxesRunTime.boxToBoolean($anonfun$isValidIANAEncoding$1(this, BoxesRunTime.unboxToChar(c))));
   }

   // $FF: synthetic method
   static boolean checkSysID$(final TokenTests $this, final String s) {
      return $this.checkSysID(s);
   }

   default boolean checkSysID(final String s) {
      return ((List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapCharArray(new char[]{'"', '\''}))).exists((c) -> BoxesRunTime.boxToBoolean($anonfun$checkSysID$1(s, BoxesRunTime.unboxToChar(c))));
   }

   // $FF: synthetic method
   static boolean checkPubID$(final TokenTests $this, final String s) {
      return $this.checkPubID(s);
   }

   default boolean checkPubID(final String s) {
      return .MODULE$.forall$extension(scala.Predef..MODULE$.augmentString(s), (ch) -> BoxesRunTime.boxToBoolean($anonfun$checkPubID$1(this, BoxesRunTime.unboxToChar(ch))));
   }

   // $FF: synthetic method
   static boolean $anonfun$isSpace$1(final TokenTests $this, final char ch) {
      return $this.isSpace(ch);
   }

   // $FF: synthetic method
   static boolean $anonfun$isName$1(final TokenTests $this, final char ch) {
      return $this.isNameChar(ch);
   }

   private boolean charOK$1(final char c) {
      return this.isAlphaDigit(c) || .MODULE$.contains$extension(scala.Predef..MODULE$.augmentString("._-"), c);
   }

   // $FF: synthetic method
   static boolean $anonfun$isValidIANAEncoding$1(final TokenTests $this, final char c) {
      return $this.charOK$1(c);
   }

   // $FF: synthetic method
   static boolean $anonfun$checkSysID$1(final String s$1, final char c) {
      return !.MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(s$1), c);
   }

   // $FF: synthetic method
   static boolean $anonfun$checkPubID$1(final TokenTests $this, final char ch) {
      return $this.isPubIDChar(ch);
   }

   static void $init$(final TokenTests $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
