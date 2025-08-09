package org.apache.spark.sql.catalyst.trees;

import java.util.regex.Pattern;
import org.apache.spark.sql.internal.SqlApiConf$;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.collection.ArrayOps.;
import scala.runtime.BoxesRunTime;

public final class CurrentOrigin$ {
   public static final CurrentOrigin$ MODULE$ = new CurrentOrigin$();
   private static final ThreadLocal value = new ThreadLocal() {
      public Origin initialValue() {
         return new Origin(Origin$.MODULE$.apply$default$1(), Origin$.MODULE$.apply$default$2(), Origin$.MODULE$.apply$default$3(), Origin$.MODULE$.apply$default$4(), Origin$.MODULE$.apply$default$5(), Origin$.MODULE$.apply$default$6(), Origin$.MODULE$.apply$default$7(), Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9());
      }
   };
   private static final Pattern sparkCodePattern = Pattern.compile("(org\\.apache\\.spark\\.sql\\.(?:(classic|connect)\\.)?(?:functions|Column|ColumnName|SQLImplicits|Dataset|DataFrameStatFunctions|DatasetHolder|SparkSession|ColumnNodeToProtoConverter)(?:|\\..*|\\$.*))|(scala\\.collection\\..*)");

   private ThreadLocal value() {
      return value;
   }

   public Origin get() {
      return (Origin)this.value().get();
   }

   public void set(final Origin o) {
      this.value().set(o);
   }

   public void reset() {
      this.value().set(new Origin(Origin$.MODULE$.apply$default$1(), Origin$.MODULE$.apply$default$2(), Origin$.MODULE$.apply$default$3(), Origin$.MODULE$.apply$default$4(), Origin$.MODULE$.apply$default$5(), Origin$.MODULE$.apply$default$6(), Origin$.MODULE$.apply$default$7(), Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9()));
   }

   public void setPosition(final int line, final int start) {
      ThreadLocal var10000 = this.value();
      Origin qual$1 = (Origin)this.value().get();
      Some x$1 = new Some(BoxesRunTime.boxToInteger(line));
      Some x$2 = new Some(BoxesRunTime.boxToInteger(start));
      Option x$3 = qual$1.copy$default$3();
      Option x$4 = qual$1.copy$default$4();
      Option x$5 = qual$1.copy$default$5();
      Option x$6 = qual$1.copy$default$6();
      Option x$7 = qual$1.copy$default$7();
      Option x$8 = qual$1.copy$default$8();
      Option x$9 = qual$1.copy$default$9();
      var10000.set(qual$1.copy(x$1, x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9));
   }

   public Object withOrigin(final Origin o, final Function0 f) {
      Origin previous = this.get();
      this.set(o);

      Object var10000;
      try {
         var10000 = f.apply();
      } finally {
         this.set(previous);
      }

      Object ret = var10000;
      return ret;
   }

   public Object withOrigin(final Function0 f) {
      if (!this.get().stackTrace().isDefined() && SqlApiConf$.MODULE$.get().dataFrameQueryContextEnabled()) {
         StackTraceElement[] st = Thread.currentThread().getStackTrace();

         int i;
         for(i = 0; i < st.length && !this.sparkCode(st[i]); ++i) {
         }

         while(i < st.length && this.sparkCode(st[i])) {
            ++i;
         }

         Some x$1 = new Some(.MODULE$.slice$extension(scala.Predef..MODULE$.refArrayOps((Object[])st), i - 1, i + SqlApiConf$.MODULE$.get().stackTracesInDataFrameContext()));
         Option x$2 = PySparkCurrentOrigin$.MODULE$.get();
         Option x$3 = Origin$.MODULE$.apply$default$1();
         Option x$4 = Origin$.MODULE$.apply$default$2();
         Option x$5 = Origin$.MODULE$.apply$default$3();
         Option x$6 = Origin$.MODULE$.apply$default$4();
         Option x$7 = Origin$.MODULE$.apply$default$5();
         Option x$8 = Origin$.MODULE$.apply$default$6();
         Option x$9 = Origin$.MODULE$.apply$default$7();
         Origin origin = new Origin(x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$1, x$2);
         return this.withOrigin(origin, f);
      } else {
         return f.apply();
      }
   }

   private Pattern sparkCodePattern() {
      return sparkCodePattern;
   }

   private boolean sparkCode(final StackTraceElement ste) {
      return this.sparkCodePattern().matcher(ste.getClassName()).matches();
   }

   private CurrentOrigin$() {
   }
}
