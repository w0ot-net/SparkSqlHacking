package scala.reflect.internal.util;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ubaB\n\u0015!\u0003\r\t!\b\u0005\u0006E\u0001!\ta\t\u0005\u0006O\u0001!\t\u0001\u000b\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u001d\u0002!\ta\u0014\u0005\u0006%\u0002!\ta\u0015\u0005\u0006+\u0002!\tA\u0016\u0005\u0006=\u0002!\ta\u0018\u0005\u0006C\u0002!\tA\u0019\u0005\bi\u0002\t\n\u0011\"\u0001v\u0011\u001d\t\t\u0001\u0001C\u0001\u0003\u0007Aq!!\u0005\u0001\t\u0003\t\u0019\u0002\u0003\u0005\u0002\u001c\u0001\t\n\u0011\"\u0001v\u0011\u001d\ti\u0002\u0001C\u0001\u0003?Aq!!\u000b\u0001\t\u0003\tYcB\u0004\u00020QA\t!!\r\u0007\rM!\u0002\u0012AA\u001b\u0011\u001d\tI$\u0005C\u0001\u0003w\u0011\u0011b\u0015;sS:<w\n]:\u000b\u0005U1\u0012\u0001B;uS2T!a\u0006\r\u0002\u0011%tG/\u001a:oC2T!!\u0007\u000e\u0002\u000fI,g\r\\3di*\t1$A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001q\u0002CA\u0010!\u001b\u0005Q\u0012BA\u0011\u001b\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012\u0001\n\t\u0003?\u0015J!A\n\u000e\u0003\tUs\u0017\u000e^\u0001\u0007_\u0016l\u0007\u000f^=\u0015\u0005%b\u0004c\u0001\u00160c5\t1F\u0003\u0002-[\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003]i\t!bY8mY\u0016\u001cG/[8o\u0013\t\u00014FA\u0002TKF\u0004\"AM\u001d\u000f\u0005M:\u0004C\u0001\u001b\u001b\u001b\u0005)$B\u0001\u001c\u001d\u0003\u0019a$o\\8u}%\u0011\u0001HG\u0001\u0007!J,G-\u001a4\n\u0005iZ$AB*ue&twM\u0003\u000295!)QH\u0001a\u0001}\u0005\u0011\u0001p\u001d\t\u0004?}\n\u0014B\u0001!\u001b\u0005)a$/\u001a9fCR,GMP\u0001\u0006_*|\u0017N\u001c\u000b\u0003c\rCQ!P\u0002A\u0002y\n1\u0003\\8oO\u0016\u001cHoQ8n[>t\u0007K]3gSb$\"!\r$\t\u000bu\"\u0001\u0019A$\u0011\u0007![\u0015G\u0004\u0002 \u0013&\u0011!JG\u0001\ba\u0006\u001c7.Y4f\u0013\taUJ\u0001\u0003MSN$(B\u0001&\u001b\u0003E!(/[7Ue\u0006LG.\u001b8h'B\f7-\u001a\u000b\u0003cACQ!U\u0003A\u0002E\n\u0011a]\u0001\u0015iJLW.\u00117m)J\f\u0017\u000e\\5oON\u0003\u0018mY3\u0015\u0005E\"\u0006\"B)\u0007\u0001\u0004\t\u0014!\u00033fG>l\u0007o\\:f)\r9u+\u0017\u0005\u00061\u001e\u0001\r!M\u0001\u0004gR\u0014\b\"\u0002.\b\u0001\u0004Y\u0016aA:faB\u0011q\u0004X\u0005\u0003;j\u0011Aa\u00115be\u0006)qo\u001c:egR\u0011q\t\u0019\u0005\u00061\"\u0001\r!M\u0001\u000bgBd\u0017\u000e^,iKJ,G\u0003B2jUJ\u00042a\b3g\u0013\t)'D\u0001\u0004PaRLwN\u001c\t\u0005?\u001d\f\u0014'\u0003\u0002i5\t1A+\u001e9mKJBQ\u0001W\u0005A\u0002EBQa[\u0005A\u00021\f\u0011A\u001a\t\u0005?5\\v.\u0003\u0002o5\tIa)\u001e8di&|g.\r\t\u0003?AL!!\u001d\u000e\u0003\u000f\t{w\u000e\\3b]\"91/\u0003I\u0001\u0002\u0004y\u0017a\u00033p\tJ|\u0007/\u00138eKb\fAc\u001d9mSR<\u0006.\u001a:fI\u0011,g-Y;mi\u0012\u001aT#\u0001<+\u0005=<8&\u0001=\u0011\u0005etX\"\u0001>\u000b\u0005md\u0018!C;oG\",7m[3e\u0015\ti($\u0001\u0006b]:|G/\u0019;j_:L!a >\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0006ta2LG/\u0011:pk:$G#B2\u0002\u0006\u0005\u001d\u0001\"\u0002-\f\u0001\u0004\t\u0004bBA\u0005\u0017\u0001\u0007\u00111B\u0001\u0004S\u0012D\bcA\u0010\u0002\u000e%\u0019\u0011q\u0002\u000e\u0003\u0007%sG/A\u0004ta2LG/\u0011;\u0015\u000f\r\f)\"a\u0006\u0002\u001a!)\u0001\f\u0004a\u0001c!9\u0011\u0011\u0002\u0007A\u0002\u0005-\u0001bB:\r!\u0003\u0005\ra\\\u0001\u0012gBd\u0017\u000e^!uI\u0011,g-Y;mi\u0012\u001a\u0014!F2pk:$X\t\\3nK:$8/Q:TiJLgn\u001a\u000b\u0006c\u0005\u0005\u0012Q\u0005\u0005\b\u0003Gq\u0001\u0019AA\u0006\u0003\u0005q\u0007BBA\u0014\u001d\u0001\u0007\u0011'A\u0004fY\u0016lWM\u001c;\u0002\u001b\r|WO\u001c;BgN#(/\u001b8h)\r\t\u0014Q\u0006\u0005\b\u0003Gy\u0001\u0019AA\u0006\u0003%\u0019FO]5oO>\u00038\u000fE\u0002\u00024Ei\u0011\u0001F\n\u0005#y\t9\u0004E\u0002\u00024\u0001\ta\u0001P5oSRtDCAA\u0019\u0001"
)
public interface StringOps {
   // $FF: synthetic method
   static Seq oempty$(final StringOps $this, final Seq xs) {
      return $this.oempty(xs);
   }

   default Seq oempty(final Seq xs) {
      return (Seq)xs.filterNot((x) -> BoxesRunTime.boxToBoolean($anonfun$oempty$1(x)));
   }

   // $FF: synthetic method
   static String ojoin$(final StringOps $this, final Seq xs) {
      return $this.ojoin(xs);
   }

   default String ojoin(final Seq xs) {
      Seq var10000 = this.oempty(xs);
      String mkString_sep = " ";
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.mkString("", mkString_sep, "");
      }
   }

   // $FF: synthetic method
   static String longestCommonPrefix$(final StringOps $this, final List xs) {
      return $this.longestCommonPrefix(xs);
   }

   default String longestCommonPrefix(final List xs) {
      if (.MODULE$.equals(xs)) {
         return "";
      } else {
         if (xs instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var2 = (scala.collection.immutable..colon.colon)xs;
            String w = (String)var2.head();
            List var4 = var2.next$access$1();
            if (.MODULE$.equals(var4)) {
               return w;
            }
         }

         return lcp$1(xs);
      }
   }

   // $FF: synthetic method
   static String trimTrailingSpace$(final StringOps $this, final String s) {
      return $this.trimTrailingSpace(s);
   }

   default String trimTrailingSpace(final String s) {
      int end;
      for(end = s.length(); end > 0; --end) {
         RichChar var10000 = scala.runtime.RichChar..MODULE$;
         if (!Character.isWhitespace(s.charAt(end - 1))) {
            break;
         }
      }

      return end == s.length() ? s : s.substring(0, end);
   }

   // $FF: synthetic method
   static String trimAllTrailingSpace$(final StringOps $this, final String s) {
      return $this.trimAllTrailingSpace(s);
   }

   default String trimAllTrailingSpace(final String s) {
      Iterator var10000 = scala.collection.StringOps..MODULE$.linesIterator$extension(s).map((sx) -> this.trimTrailingSpace(sx));
      String mkString_sep = System.lineSeparator();
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.mkString("", mkString_sep, "");
      }
   }

   // $FF: synthetic method
   static List decompose$(final StringOps $this, final String str, final char sep) {
      return $this.decompose(str, sep);
   }

   default List decompose(final String str, final char sep) {
      return this.ws$1(0, str, sep);
   }

   // $FF: synthetic method
   static List words$(final StringOps $this, final String str) {
      return $this.words(str);
   }

   default List words(final String str) {
      return this.decompose(str, ' ');
   }

   // $FF: synthetic method
   static Option splitWhere$(final StringOps $this, final String str, final Function1 f, final boolean doDropIndex) {
      return $this.splitWhere(str, f, doDropIndex);
   }

   default Option splitWhere(final String str, final Function1 f, final boolean doDropIndex) {
      int x$2 = 0;
      int indexWhere$extension_len = str.length();
      int indexWhere$extension_i = x$2;

      int var10002;
      while(true) {
         if (indexWhere$extension_i >= indexWhere$extension_len) {
            var10002 = -1;
            break;
         }

         if (BoxesRunTime.unboxToBoolean(f.apply(str.charAt(indexWhere$extension_i)))) {
            var10002 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      return this.splitAt(str, var10002, doDropIndex);
   }

   // $FF: synthetic method
   static boolean splitWhere$default$3$(final StringOps $this) {
      return $this.splitWhere$default$3();
   }

   default boolean splitWhere$default$3() {
      return false;
   }

   // $FF: synthetic method
   static Option splitAround$(final StringOps $this, final String str, final int idx) {
      return $this.splitAround(str, idx);
   }

   default Option splitAround(final String str, final int idx) {
      return this.splitAt(str, idx, true);
   }

   // $FF: synthetic method
   static Option splitAt$(final StringOps $this, final String str, final int idx, final boolean doDropIndex) {
      return $this.splitAt(str, idx, doDropIndex);
   }

   default Option splitAt(final String str, final int idx, final boolean doDropIndex) {
      return (Option)(idx == -1 ? scala.None..MODULE$ : new Some(new Tuple2(scala.collection.StringOps..MODULE$.take$extension(str, idx), scala.collection.StringOps..MODULE$.drop$extension(str, doDropIndex ? idx + 1 : idx))));
   }

   // $FF: synthetic method
   static boolean splitAt$default$3$(final StringOps $this) {
      return $this.splitAt$default$3();
   }

   default boolean splitAt$default$3() {
      return false;
   }

   // $FF: synthetic method
   static String countElementsAsString$(final StringOps $this, final int n, final String element) {
      return $this.countElementsAsString(n, element);
   }

   default String countElementsAsString(final int n, final String element) {
      switch (n) {
         case 0:
            return (new StringBuilder(4)).append("no ").append(element).append("s").toString();
         case 1:
            return (new StringBuilder(2)).append("1 ").append(element).toString();
         default:
            return (new StringBuilder(2)).append(this.countAsString(n)).append(" ").append(element).append("s").toString();
      }
   }

   // $FF: synthetic method
   static String countAsString$(final StringOps $this, final int n) {
      return $this.countAsString(n);
   }

   default String countAsString(final int n) {
      return Integer.toString(n);
   }

   // $FF: synthetic method
   static boolean $anonfun$oempty$1(final String x) {
      return x == null || x.equals("");
   }

   // $FF: synthetic method
   static boolean $anonfun$longestCommonPrefix$1(final String w$1, final String s) {
      String var2 = "";
      if (s != null) {
         if (s.equals(var2)) {
            return true;
         }
      }

      if (s.charAt(0) == w$1.charAt(0)) {
         return false;
      } else {
         return true;
      }
   }

   // $FF: synthetic method
   static String $anonfun$longestCommonPrefix$2(final String x$2) {
      return x$2.substring(1);
   }

   private static String lcp$1(final List ss) {
      if (!(ss instanceof scala.collection.immutable..colon.colon)) {
         throw new MatchError(ss);
      } else {
         scala.collection.immutable..colon.colon var1 = (scala.collection.immutable..colon.colon)ss;
         String w = (String)var1.head();
         List ws = var1.next$access$1();
         String var4 = "";
         if (w != null) {
            if (w.equals(var4)) {
               return "";
            }
         }

         if (ws == null) {
            throw null;
         } else {
            List exists_these = ws;

            boolean var10000;
            while(true) {
               if (exists_these.isEmpty()) {
                  var10000 = false;
                  break;
               }

               String var10 = (String)exists_these.head();
               if ($anonfun$longestCommonPrefix$1(w, var10)) {
                  var10000 = true;
                  break;
               }

               exists_these = (List)exists_these.tail();
            }

            Object var11 = null;
            if (var10000) {
               return "";
            } else {
               StringBuilder var16 = (new StringBuilder(0)).append(w.substring(0, 1));
               Object var10001;
               if (ss == .MODULE$) {
                  var10001 = .MODULE$;
               } else {
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((String)ss.head()).substring(1), .MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)ss.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((String)map_rest.head()).substring(1), .MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var10001 = map_h;
               }

               Object var12 = null;
               Object var13 = null;
               Object var14 = null;
               Object var15 = null;
               return var16.append(lcp$1((List)var10001)).toString();
            }
         }
      }
   }

   private List ws$1(final int start, final String str$1, final char sep$1) {
      while(start != str$1.length()) {
         if (str$1.charAt(start) != sep$1) {
            int end = str$1.indexOf(sep$1, start);
            if (end < 0) {
               return new scala.collection.immutable..colon.colon(str$1.substring(start), .MODULE$);
            }

            String var5 = str$1.substring(start, end);
            List var10000 = this.ws$1(end + 1, str$1, sep$1);
            if (var10000 == null) {
               throw null;
            }

            List $colon$colon_this = var10000;
            return new scala.collection.immutable..colon.colon(var5, $colon$colon_this);
         }

         ++start;
      }

      return .MODULE$;
   }

   static void $init$(final StringOps $this) {
   }

   // $FF: synthetic method
   static Object $anonfun$longestCommonPrefix$1$adapted(final String w$1, final String s) {
      return BoxesRunTime.boxToBoolean($anonfun$longestCommonPrefix$1(w$1, s));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
