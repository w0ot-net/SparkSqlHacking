package org.apache.spark.sql.hive;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.classic.SQLContext;
import org.apache.spark.sql.classic.SparkSession;
import org.apache.spark.sql.classic.SparkSession.;
import scala.reflect.ScalaSignature;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005m3Aa\u0002\u0005\u0001'!A\u0001\u0005\u0001B\u0001B\u0003%\u0011\u0005\u0003\u0004%\u0001\u0011\u0005\u0001\"\n\u0005\u0006I\u0001!\t!\u000b\u0005\u0006I\u0001!\t\u0001\r\u0005\u0006u\u0001!\te\u000f\u0005\u0006y\u0001!\t!\u0010\u0002\f\u0011&4XmQ8oi\u0016DHO\u0003\u0002\n\u0015\u0005!\u0001.\u001b<f\u0015\tYA\"A\u0002tc2T!!\u0004\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005=\u0001\u0012AB1qC\u000eDWMC\u0001\u0012\u0003\ry'oZ\u0002\u0001'\r\u0001AC\u0007\t\u0003+ai\u0011A\u0006\u0006\u0003/)\tqa\u00197bgNL7-\u0003\u0002\u001a-\tQ1+\u0015'D_:$X\r\u001f;\u0011\u0005mqR\"\u0001\u000f\u000b\u0005ua\u0011\u0001C5oi\u0016\u0014h.\u00197\n\u0005}a\"a\u0002'pO\u001eLgnZ\u0001\u000e?N\u0004\u0018M]6TKN\u001c\u0018n\u001c8\u0011\u0005U\u0011\u0013BA\u0012\u0017\u00051\u0019\u0006/\u0019:l'\u0016\u001c8/[8o\u0003\u0019a\u0014N\\5u}Q\u0011a\u0005\u000b\t\u0003O\u0001i\u0011\u0001\u0003\u0005\u0006A\t\u0001\r!\t\u000b\u0003M)BQaK\u0002A\u00021\n!a]2\u0011\u00055rS\"\u0001\u0007\n\u0005=b!\u0001D*qCJ\\7i\u001c8uKb$HC\u0001\u00142\u0011\u0015YC\u00011\u00013!\t\u0019\u0004(D\u00015\u0015\t)d'\u0001\u0003kCZ\f'BA\u001c\r\u0003\r\t\u0007/[\u0005\u0003sQ\u0012\u0001CS1wCN\u0003\u0018M]6D_:$X\r\u001f;\u0002\u00159,woU3tg&|g\u000eF\u0001'\u00031\u0011XM\u001a:fg\"$\u0016M\u00197f)\tqD\t\u0005\u0002@\u00056\t\u0001IC\u0001B\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0005I\u0001\u0003V]&$\b\"B#\u0007\u0001\u00041\u0015!\u0003;bE2,g*Y7f!\t9eJ\u0004\u0002I\u0019B\u0011\u0011\nQ\u0007\u0002\u0015*\u00111JE\u0001\u0007yI|w\u000e\u001e \n\u00055\u0003\u0015A\u0002)sK\u0012,g-\u0003\u0002P!\n11\u000b\u001e:j]\u001eT!!\u0014!)\r\u0001\u0011VK\u0016-Z!\ty4+\u0003\u0002U\u0001\nQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\nq+\u0001\u001aVg\u0016\u00043\u000b]1sWN+7o]5p]:\u0012W/\u001b7eKJtSM\\1cY\u0016D\u0015N^3TkB\u0004xN\u001d;!S:\u001cH/Z1e\u0003\u0015\u0019\u0018N\\2fC\u0005Q\u0016!\u0002\u001a/a9\u0002\u0004"
)
public class HiveContext extends SQLContext {
   public HiveContext newSession() {
      return new HiveContext(this.sparkSession().newSession());
   }

   public void refreshTable(final String tableName) {
      this.sparkSession().catalog().refreshTable(tableName);
   }

   public HiveContext(final SparkSession _sparkSession) {
      super(_sparkSession);
   }

   public HiveContext(final SparkContext sc) {
      this(.MODULE$.builder().enableHiveSupport().sparkContext(sc).getOrCreate());
   }

   public HiveContext(final JavaSparkContext sc) {
      this(sc.sc());
   }
}
