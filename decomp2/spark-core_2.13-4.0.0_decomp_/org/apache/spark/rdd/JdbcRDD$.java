package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.sql.ResultSet;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaSparkContext$;
import org.apache.spark.api.java.function.Function;
import scala.Function1;
import scala.Array.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JdbcRDD$ implements Serializable {
   public static final JdbcRDD$ MODULE$ = new JdbcRDD$();

   public Function1 $lessinit$greater$default$7() {
      return (rs) -> MODULE$.resultSetToObjectArray(rs);
   }

   public Object[] resultSetToObjectArray(final ResultSet rs) {
      return .MODULE$.tabulate(rs.getMetaData().getColumnCount(), (i) -> $anonfun$resultSetToObjectArray$1(rs, BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.Object());
   }

   public JavaRDD create(final JavaSparkContext sc, final JdbcRDD.ConnectionFactory connectionFactory, final String sql, final long lowerBound, final long upperBound, final int numPartitions, final Function mapRow) {
      JdbcRDD jdbcRDD = new JdbcRDD(sc.sc(), () -> connectionFactory.getConnection(), sql, lowerBound, upperBound, numPartitions, (resultSet) -> mapRow.call(resultSet), JavaSparkContext$.MODULE$.fakeClassTag());
      return new JavaRDD(jdbcRDD, JavaSparkContext$.MODULE$.fakeClassTag());
   }

   public JavaRDD create(final JavaSparkContext sc, final JdbcRDD.ConnectionFactory connectionFactory, final String sql, final long lowerBound, final long upperBound, final int numPartitions) {
      Function mapRow = new Function() {
         public Object[] call(final ResultSet resultSet) {
            return JdbcRDD$.MODULE$.resultSetToObjectArray(resultSet);
         }
      };
      return this.create(sc, connectionFactory, sql, lowerBound, upperBound, numPartitions, mapRow);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JdbcRDD$.class);
   }

   // $FF: synthetic method
   public static final Object $anonfun$resultSetToObjectArray$1(final ResultSet rs$1, final int i) {
      return rs$1.getObject(i + 1);
   }

   private JdbcRDD$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
