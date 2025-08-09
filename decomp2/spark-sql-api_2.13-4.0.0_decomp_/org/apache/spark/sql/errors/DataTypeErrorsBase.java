package org.apache.spark.sql.errors;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.QueryContext;
import org.apache.spark.sql.catalyst.util.AttributeNameParser$;
import org.apache.spark.sql.catalyst.util.QuotingUtils$;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.TypeCollection$;
import org.apache.spark.sql.types.UserDefinedType;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Option;
import scala.collection.IterableOnceOps;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055b\u0001C\n\u0015!\u0003\r\tA\u0006\u0010\t\u000b\u0015\u0002A\u0011A\u0014\t\u000b-\u0002A\u0011\u0001\u0017\t\u000b-\u0002A\u0011\u0001\u001e\t\u000b\u0015\u0003A\u0011\u0001$\t\u000b%\u0003A\u0011\u0001&\t\u000b5\u0003A\u0011\u0001(\t\u000b5\u0003A\u0011\u0001)\t\u000be\u0003A\u0011\u0001.\t\u000be\u0003A\u0011A/\t\u000be\u0003A\u0011\u00014\t\u000be\u0003A\u0011A6\t\u000be\u0003A\u0011\u00019\t\u000be\u0003A\u0011A;\t\u000be\u0003A\u0011\u0001>\t\r}\u0004A\u0011CA\u0001\u0011\u001d\t9\u0001\u0001C\u0001\u0003\u0013Aq!a\u0006\u0001\t\u0003\tI\u0002C\u0004\u0002&\u0001!\t!a\n\u0003%\u0011\u000bG/\u0019+za\u0016,%O]8sg\n\u000b7/\u001a\u0006\u0003+Y\ta!\u001a:s_J\u001c(BA\f\u0019\u0003\r\u0019\u0018\u000f\u001c\u0006\u00033i\tQa\u001d9be.T!a\u0007\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0012aA8sON\u0011\u0001a\b\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012\u0001\u000b\t\u0003A%J!AK\u0011\u0003\tUs\u0017\u000e^\u0001\bi>\u001c\u0016\u000bT%e)\ti\u0003\b\u0005\u0002/k9\u0011qf\r\t\u0003a\u0005j\u0011!\r\u0006\u0003e\u0019\na\u0001\u0010:p_Rt\u0014B\u0001\u001b\"\u0003\u0019\u0001&/\u001a3fM&\u0011ag\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Q\n\u0003\"B\u001d\u0003\u0001\u0004i\u0013!\u00029beR\u001cHCA\u0017<\u0011\u0015I4\u00011\u0001=!\ri$)\f\b\u0003}\u0001s!\u0001M \n\u0003\tJ!!Q\u0011\u0002\u000fA\f7m[1hK&\u00111\t\u0012\u0002\u0004'\u0016\f(BA!\"\u0003%!xnU)M'RlG\u000f\u0006\u0002.\u000f\")\u0001\n\u0002a\u0001[\u0005!A/\u001a=u\u0003%!xnU)M\u0007>tg\r\u0006\u0002.\u0017\")A*\u0002a\u0001[\u0005!1m\u001c8g\u0003%!xnU)M)f\u0004X\r\u0006\u0002.\u001f\")\u0001J\u0002a\u0001[Q\u0011Q&\u0015\u0005\u0006%\u001e\u0001\raU\u0001\u0002iB\u0011AkV\u0007\u0002+*\u0011aKF\u0001\u0006if\u0004Xm]\u0005\u00031V\u0013\u0001#\u00112tiJ\f7\r\u001e#bi\u0006$\u0016\u0010]3\u0002\u0015Q|7+\u0015'WC2,X\r\u0006\u0002.7\")A\f\u0003a\u0001[\u0005)a/\u00197vKR\u0011QF\u0018\u0005\u00069&\u0001\ra\u0018\t\u0003A\u0012l\u0011!\u0019\u0006\u0003-\nT!a\u0019\r\u0002\rUt7/\u00194f\u0013\t)\u0017M\u0001\u0006V)\u001aC4\u000b\u001e:j]\u001e$\"!L4\t\u000bqS\u0001\u0019\u00015\u0011\u0005\u0001J\u0017B\u00016\"\u0005\u0015\u0019\u0006n\u001c:u)\tiC\u000eC\u0003]\u0017\u0001\u0007Q\u000e\u0005\u0002!]&\u0011q.\t\u0002\u0004\u0013:$HCA\u0017r\u0011\u0015aF\u00021\u0001s!\t\u00013/\u0003\u0002uC\t!Aj\u001c8h)\tic\u000fC\u0003]\u001b\u0001\u0007q\u000f\u0005\u0002!q&\u0011\u00110\t\u0002\u0006\r2|\u0017\r\u001e\u000b\u0003[mDQ\u0001\u0018\bA\u0002q\u0004\"\u0001I?\n\u0005y\f#A\u0002#pk\ndW-\u0001\brk>$XMQ=EK\u001a\fW\u000f\u001c;\u0015\u00075\n\u0019\u0001\u0003\u0004\u0002\u0006=\u0001\r!L\u0001\u0005K2,W.\u0001\u0006hKR\u001cV/\\7bef$2!LA\u0006\u0011\u001d\ti\u0001\u0005a\u0001\u0003\u001f\t!b]9m\u0007>tG/\u001a=u!\u0011\t\t\"a\u0005\u000e\u0003aI1!!\u0006\u0019\u00051\tV/\u001a:z\u0007>tG/\u001a=u\u0003=9W\r^)vKJL8i\u001c8uKb$H\u0003BA\u000e\u0003C\u0001R\u0001IA\u000f\u0003\u001fI1!a\b\"\u0005\u0015\t%O]1z\u0011\u001d\t\u0019#\u0005a\u0001\u0003\u001f\tqaY8oi\u0016DH/\u0001\u0006u_\u0012\u001bv\n\u001d;j_:$2!LA\u0015\u0011\u0019\tYC\u0005a\u0001[\u00051q\u000e\u001d;j_:\u0004"
)
public interface DataTypeErrorsBase {
   // $FF: synthetic method
   static String toSQLId$(final DataTypeErrorsBase $this, final String parts) {
      return $this.toSQLId(parts);
   }

   default String toSQLId(final String parts) {
      return this.toSQLId(AttributeNameParser$.MODULE$.parseAttributeName(parts));
   }

   // $FF: synthetic method
   static String toSQLId$(final DataTypeErrorsBase $this, final Seq parts) {
      return $this.toSQLId(parts);
   }

   default String toSQLId(final Seq parts) {
      Seq var10000;
      label30: {
         Seq rest;
         label29: {
            if (parts != null) {
               SeqOps var5 = .MODULE$.Seq().unapplySeq(parts);
               if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var5) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var5)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var5), 1) >= 0) {
                  String var6 = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var5), 0);
                  rest = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.drop$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var5), 1);
                  if ("__auto_generated_subquery_name".equals(var6)) {
                     Nil var8 = scala.collection.immutable.Nil..MODULE$;
                     if (rest == null) {
                        if (var8 != null) {
                           break label29;
                        }
                     } else if (!rest.equals(var8)) {
                        break label29;
                     }
                  }
               }
            }

            var10000 = parts;
            break label30;
         }

         var10000 = rest;
      }

      Seq cleaned = var10000;
      return ((IterableOnceOps)cleaned.map((name) -> QuotingUtils$.MODULE$.quoteIdentifier(name))).mkString(".");
   }

   // $FF: synthetic method
   static String toSQLStmt$(final DataTypeErrorsBase $this, final String text) {
      return $this.toSQLStmt(text);
   }

   default String toSQLStmt(final String text) {
      return text.toUpperCase(Locale.ROOT);
   }

   // $FF: synthetic method
   static String toSQLConf$(final DataTypeErrorsBase $this, final String conf) {
      return $this.toSQLConf(conf);
   }

   default String toSQLConf(final String conf) {
      return QuotingUtils$.MODULE$.toSQLConf(conf);
   }

   // $FF: synthetic method
   static String toSQLType$(final DataTypeErrorsBase $this, final String text) {
      return $this.toSQLType(text);
   }

   default String toSQLType(final String text) {
      return this.quoteByDefault(text.toUpperCase(Locale.ROOT));
   }

   // $FF: synthetic method
   static String toSQLType$(final DataTypeErrorsBase $this, final AbstractDataType t) {
      return $this.toSQLType(t);
   }

   default String toSQLType(final AbstractDataType t) {
      if (t != null) {
         Option var4 = TypeCollection$.MODULE$.unapply(t);
         if (!var4.isEmpty()) {
            Seq types = (Seq)var4.get();
            return ((IterableOnceOps)types.map((tx) -> this.toSQLType(tx))).mkString("(", " or ", ")");
         }
      }

      if (t instanceof UserDefinedType var6) {
         String var10000 = this.toSQLType((AbstractDataType)var6.sqlType());
         return "UDT(" + var10000 + ")";
      } else if (t instanceof DataType var7) {
         return this.quoteByDefault(var7.sql());
      } else {
         return this.quoteByDefault(t.simpleString().toUpperCase(Locale.ROOT));
      }
   }

   // $FF: synthetic method
   static String toSQLValue$(final DataTypeErrorsBase $this, final String value) {
      return $this.toSQLValue(value);
   }

   default String toSQLValue(final String value) {
      if (value == null) {
         return "NULL";
      } else {
         String var10000 = value.replace("\\", "\\\\");
         return "'" + var10000.replace("'", "\\'") + "'";
      }
   }

   // $FF: synthetic method
   static String toSQLValue$(final DataTypeErrorsBase $this, final UTF8String value) {
      return $this.toSQLValue(value);
   }

   default String toSQLValue(final UTF8String value) {
      return this.toSQLValue(value.toString());
   }

   // $FF: synthetic method
   static String toSQLValue$(final DataTypeErrorsBase $this, final short value) {
      return $this.toSQLValue(value);
   }

   default String toSQLValue(final short value) {
      return String.valueOf(value) + "S";
   }

   // $FF: synthetic method
   static String toSQLValue$(final DataTypeErrorsBase $this, final int value) {
      return $this.toSQLValue(value);
   }

   default String toSQLValue(final int value) {
      return String.valueOf(value);
   }

   // $FF: synthetic method
   static String toSQLValue$(final DataTypeErrorsBase $this, final long value) {
      return $this.toSQLValue(value);
   }

   default String toSQLValue(final long value) {
      return String.valueOf(value) + "L";
   }

   // $FF: synthetic method
   static String toSQLValue$(final DataTypeErrorsBase $this, final float value) {
      return $this.toSQLValue(value);
   }

   default String toSQLValue(final float value) {
      if (Float.isNaN(value)) {
         return "NaN";
      } else if (scala.runtime.RichFloat..MODULE$.isPosInfinity$extension(scala.Predef..MODULE$.floatWrapper(value))) {
         return "Infinity";
      } else {
         return scala.runtime.RichFloat..MODULE$.isNegInfinity$extension(scala.Predef..MODULE$.floatWrapper(value)) ? "-Infinity" : Float.toString(value);
      }
   }

   // $FF: synthetic method
   static String toSQLValue$(final DataTypeErrorsBase $this, final double value) {
      return $this.toSQLValue(value);
   }

   default String toSQLValue(final double value) {
      if (Double.isNaN(value)) {
         return "NaN";
      } else if (scala.runtime.RichDouble..MODULE$.isPosInfinity$extension(scala.Predef..MODULE$.doubleWrapper(value))) {
         return "Infinity";
      } else {
         return scala.runtime.RichDouble..MODULE$.isNegInfinity$extension(scala.Predef..MODULE$.doubleWrapper(value)) ? "-Infinity" : Double.toString(value);
      }
   }

   // $FF: synthetic method
   static String quoteByDefault$(final DataTypeErrorsBase $this, final String elem) {
      return $this.quoteByDefault(elem);
   }

   default String quoteByDefault(final String elem) {
      return "\"" + elem + "\"";
   }

   // $FF: synthetic method
   static String getSummary$(final DataTypeErrorsBase $this, final QueryContext sqlContext) {
      return $this.getSummary(sqlContext);
   }

   default String getSummary(final QueryContext sqlContext) {
      return sqlContext == null ? "" : sqlContext.summary();
   }

   // $FF: synthetic method
   static QueryContext[] getQueryContext$(final DataTypeErrorsBase $this, final QueryContext context) {
      return $this.getQueryContext(context);
   }

   default QueryContext[] getQueryContext(final QueryContext context) {
      return context == null ? (QueryContext[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class)) : (QueryContext[])((Object[])(new QueryContext[]{context}));
   }

   // $FF: synthetic method
   static String toDSOption$(final DataTypeErrorsBase $this, final String option) {
      return $this.toDSOption(option);
   }

   default String toDSOption(final String option) {
      return this.quoteByDefault(option);
   }

   static void $init$(final DataTypeErrorsBase $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
