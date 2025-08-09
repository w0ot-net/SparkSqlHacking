package scala.reflect.internal.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import scala.collection.AbstractIterable;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.immutable.WrappedString;
import scala.collection.mutable.ArraySeq;
import scala.reflect.io.AbstractFile;
import scala.runtime.BoxesRunTime;

public final class ScriptSourceFile$ {
   public static final ScriptSourceFile$ MODULE$ = new ScriptSourceFile$();

   public int headerLength(final char[] cs) {
      Pattern headerPattern = Pattern.compile("((?m)^(::)?!#.*|^.*/env .*)(\\r|\\n|\\r\\n)");
      List exists_these = new .colon.colon("#!", new .colon.colon("::#!", scala.collection.immutable.Nil..MODULE$));

      boolean var10000;
      while(true) {
         if (exists_these.isEmpty()) {
            var10000 = false;
            break;
         }

         String var9 = (String)exists_these.head();
         if ($anonfun$headerLength$1(cs, var9)) {
            var10000 = true;
            break;
         }

         exists_these = (List)exists_these.tail();
      }

      exists_these = null;
      if (var10000) {
         ArraySeq.ofChar var10001 = scala.Predef..MODULE$.wrapCharArray(cs);
         if (var10001 == null) {
            throw null;
         } else {
            AbstractIterable mkString_this = var10001;
            String mkString_mkString_sep = "";
            String mkString_end = "";
            String mkString_start = "";
            String var15 = IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_mkString_sep, mkString_end);
            Object var13 = null;
            Object var14 = null;
            Object var12 = null;
            mkString_this = null;
            Matcher matcher = headerPattern.matcher(var15);
            if (matcher.find()) {
               return matcher.end();
            } else {
               throw new IOException("script file does not close its header with !# or ::!#");
            }
         }
      } else {
         return 0;
      }
   }

   public ScriptSourceFile apply(final AbstractFile file, final char[] content) {
      BatchSourceFile underlying = new BatchSourceFile(file, content);
      int headerLen = this.headerLength(content);
      return new ScriptSourceFile(underlying, (char[])scala.collection.ArrayOps..MODULE$.drop$extension(content, headerLen), headerLen);
   }

   public ScriptSourceFile apply(final BatchSourceFile underlying) {
      int headerLen = this.headerLength(underlying.content());
      return new ScriptSourceFile(underlying, (char[])scala.collection.ArrayOps..MODULE$.drop$extension(underlying.content(), headerLen), headerLen);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$headerLength$1(final char[] cs$1, final String x$1) {
      WrappedString x$1 = scala.Predef..MODULE$.wrapString(x$1);
      int x$2 = 0;
      return scala.collection.ArrayOps..MODULE$.startsWith$extension(cs$1, x$1, x$2);
   }

   private ScriptSourceFile$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$headerLength$1$adapted(final char[] cs$1, final String x$1) {
      return BoxesRunTime.boxToBoolean($anonfun$headerLength$1(cs$1, x$1));
   }
}
