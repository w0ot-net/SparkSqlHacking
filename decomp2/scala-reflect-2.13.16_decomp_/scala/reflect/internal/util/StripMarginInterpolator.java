package scala.reflect.internal.util;

import scala.MatchError;
import scala.StringContext;
import scala.collection.IterableFactory;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005}2qAB\u0004\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u0016\u0001\u0011\u0005a\u0003C\u0003\u001b\u0001\u0019\u00051\u0004C\u0003 \u0001\u0011\u0015\u0001\u0005C\u00035\u0001\u00115Q\u0007C\u0003=\u0001\u0011\u0015QHA\fTiJL\u0007/T1sO&t\u0017J\u001c;feB|G.\u0019;pe*\u0011\u0001\"C\u0001\u0005kRLGN\u0003\u0002\u000b\u0017\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\r\u001b\u00059!/\u001a4mK\u000e$(\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001!\u0005\t\u0003%Mi\u0011!D\u0005\u0003)5\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0018!\t\u0011\u0002$\u0003\u0002\u001a\u001b\t!QK\\5u\u00035\u0019HO]5oO\u000e{g\u000e^3yiV\tA\u0004\u0005\u0002\u0013;%\u0011a$\u0004\u0002\u000e'R\u0014\u0018N\\4D_:$X\r\u001f;\u0002\u0005MlGCA\u0011-!\t\u0011\u0013F\u0004\u0002$OA\u0011A%D\u0007\u0002K)\u0011aeD\u0001\u0007yI|w\u000e\u001e \n\u0005!j\u0011A\u0002)sK\u0012,g-\u0003\u0002+W\t11\u000b\u001e:j]\u001eT!\u0001K\u0007\t\u000b5\u001a\u0001\u0019\u0001\u0018\u0002\t\u0005\u0014xm\u001d\t\u0004%=\n\u0014B\u0001\u0019\u000e\u0005)a$/\u001a9fCR,GM\u0010\t\u0003%IJ!aM\u0007\u0003\u0007\u0005s\u00170\u0001\u0003j[BdGcA\u00117w!)q\u0007\u0002a\u0001q\u0005\u00191/\u001a9\u0011\u0005II\u0014B\u0001\u001e\u000e\u0005\u0011\u0019\u0005.\u0019:\t\u000b5\"\u0001\u0019\u0001\u0018\u0002\u0005M\fHCA\u0011?\u0011\u0015iS\u00011\u0001/\u0001"
)
public interface StripMarginInterpolator {
   StringContext stringContext();

   // $FF: synthetic method
   static String sm$(final StripMarginInterpolator $this, final Seq args) {
      return $this.sm(args);
   }

   default String sm(final Seq args) {
      return this.impl('|', args);
   }

   private String impl(final char sep, final Seq args) {
      List var4 = this.stringContext().parts().toList();
      Object var25;
      if (var4 instanceof .colon.colon) {
         .colon.colon var5 = (.colon.colon)var4;
         String head = (String)var5.head();
         List tail = var5.next$access$1();
         String var8 = scala.collection.StringOps..MODULE$.stripMargin$extension(head, sep);
         if (tail == null) {
            throw null;
         }

         if (tail == scala.collection.immutable.Nil..MODULE$) {
            var25 = scala.collection.immutable.Nil..MODULE$;
         } else {
            .colon.colon map_h = new .colon.colon(stripTrailingPart$1((String)tail.head(), sep), scala.collection.immutable.Nil..MODULE$);
            .colon.colon map_t = map_h;

            for(List map_rest = (List)tail.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               .colon.colon map_nx = new .colon.colon(stripTrailingPart$1((String)map_rest.head(), sep), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var25 = map_h;
         }

         Object var20 = null;
         Object var21 = null;
         Object var22 = null;
         Object var23 = null;
         List $colon$colon_this = (List)var25;
         var25 = new .colon.colon(var8, $colon$colon_this);
         $colon$colon_this = null;
      } else {
         if (!scala.collection.immutable.Nil..MODULE$.equals(var4)) {
            throw new MatchError(var4);
         }

         var25 = scala.collection.immutable.Nil..MODULE$;
      }

      List stripped = (List)var25;
      StringContext sc = new StringContext(stripped);
      StringContext var26 = scala.StringContext..MODULE$;
      scala.collection.Seq var10001 = (scala.collection.Seq)IterableFactory.apply$(scala.collection.immutable.List..MODULE$, args);
      Seq standardInterpolator_parts = sc.parts();
      scala.collection.Seq standardInterpolator_args = var10001;
      var26.checkLengths(standardInterpolator_args, standardInterpolator_parts);
      Iterator standardInterpolator_pi = standardInterpolator_parts.iterator();
      Iterator standardInterpolator_ai = standardInterpolator_args.iterator();
      StringBuilder standardInterpolator_bldr = new StringBuilder((String)standardInterpolator_pi.next());

      while(standardInterpolator_ai.hasNext()) {
         standardInterpolator_bldr.append(standardInterpolator_ai.next());
         standardInterpolator_bldr.append((String)standardInterpolator_pi.next());
      }

      return standardInterpolator_bldr.toString();
   }

   // $FF: synthetic method
   static String sq$(final StripMarginInterpolator $this, final Seq args) {
      return $this.sq(args);
   }

   default String sq(final Seq args) {
      Iterator var10000 = scala.collection.StringOps..MODULE$.linesIterator$extension(this.impl('>', args));
      if (var10000 == null) {
         throw null;
      } else {
         IterableOnceOps mkString_this = var10000;
         String mkString_mkString_sep = "";
         return mkString_this.mkString("", mkString_mkString_sep, "");
      }
   }

   private static boolean isLineBreak$1(final char c) {
      return c == '\n' || c == '\f';
   }

   // $FF: synthetic method
   static boolean $anonfun$impl$1(final char c) {
      return !isLineBreak$1(c);
   }

   private static String stripTrailingPart$1(final String s, final char sep$1) {
      int span$extension_indexWhere$extension_from = 0;
      int span$extension_indexWhere$extension_len = s.length();
      int span$extension_indexWhere$extension_i = span$extension_indexWhere$extension_from;

      int var10000;
      while(true) {
         if (span$extension_indexWhere$extension_i >= span$extension_indexWhere$extension_len) {
            var10000 = -1;
            break;
         }

         if (!$anonfun$impl$1(s.charAt(span$extension_indexWhere$extension_i))) {
            var10000 = span$extension_indexWhere$extension_i;
            break;
         }

         ++span$extension_indexWhere$extension_i;
      }

      int var3 = var10000;
      String var8;
      String var10001;
      switch (var3) {
         case -1:
            var8 = s;
            var10001 = "";
            break;
         default:
            var8 = s.substring(0, var3);
            var10001 = s.substring(var3);
      }

      String var7 = var10001;
      String pre = var8;
      return (new StringBuilder(0)).append(pre).append(scala.collection.StringOps..MODULE$.stripMargin$extension(var7, sep$1)).toString();
   }

   // $FF: synthetic method
   static String $anonfun$impl$2(final char sep$1, final String s) {
      return stripTrailingPart$1(s, sep$1);
   }

   // $FF: synthetic method
   static String $anonfun$impl$3(final String x) {
      return (String)scala.Predef..MODULE$.identity(x);
   }

   static void $init$(final StripMarginInterpolator $this) {
   }

   // $FF: synthetic method
   static Object $anonfun$impl$1$adapted(final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$impl$1(BoxesRunTime.unboxToChar(c)));
   }
}
