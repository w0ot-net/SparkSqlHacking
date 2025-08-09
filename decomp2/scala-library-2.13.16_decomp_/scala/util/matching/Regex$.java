package scala.util.matching;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import scala.Option;
import scala.Some;
import scala.package$;
import scala.collection.SeqOps;
import scala.collection.immutable.List$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Regex$ implements Serializable {
   public static final Regex$ MODULE$ = new Regex$();

   public Option scala$util$matching$Regex$$extractGroupsFromMatch(final Regex.Match m) {
      Some var10000 = new Some;
      List$ var10002 = package$.MODULE$.List();
      int tabulate_n = m.groupCount();
      if (var10002 == null) {
         throw null;
      } else {
         Builder tabulate_b = new ListBuffer();
         tabulate_b.sizeHint(tabulate_n);

         for(int tabulate_i = 0; tabulate_i < tabulate_n; ++tabulate_i) {
            Object tabulate_$plus$eq_elem = m.group(tabulate_i + 1);
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         SeqOps var9 = (SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var8 = null;
         var10000.<init>(var9);
         return var10000;
      }
   }

   public String quote(final String text) {
      return Pattern.quote(text);
   }

   public String quoteReplacement(final String text) {
      return Matcher.quoteReplacement(text);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Regex$.class);
   }

   // $FF: synthetic method
   public static final String $anonfun$extractGroupsFromMatch$1(final Regex.Match m$2, final int i) {
      return m$2.group(i + 1);
   }

   private Regex$() {
   }

   // $FF: synthetic method
   public static final String $anonfun$extractGroupsFromMatch$1$adapted(final Regex.Match m$2, final Object i) {
      return $anonfun$extractGroupsFromMatch$1(m$2, BoxesRunTime.unboxToInt(i));
   }
}
