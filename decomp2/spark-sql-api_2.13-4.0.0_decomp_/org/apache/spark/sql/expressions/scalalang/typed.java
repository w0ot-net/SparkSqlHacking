package org.apache.spark.sql.expressions.scalalang;

import org.apache.spark.sql.TypedColumn;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005<Qa\u0002\u0005\t\u0002U1Qa\u0006\u0005\t\u0002aAQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0002\tBQaO\u0001\u0005\u0002qBQAR\u0001\u0005\u0002\u001dCQAT\u0001\u0005\u0002=\u000bQ\u0001^=qK\u0012T!!\u0003\u0006\u0002\u0013M\u001c\u0017\r\\1mC:<'BA\u0006\r\u0003-)\u0007\u0010\u001d:fgNLwN\\:\u000b\u00055q\u0011aA:rY*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005Y\tQ\"\u0001\u0005\u0003\u000bQL\b/\u001a3\u0014\u0005\u0005I\u0002C\u0001\u000e\u001e\u001b\u0005Y\"\"\u0001\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005yY\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002+\u0005\u0019\u0011M^4\u0016\u0005\rRCC\u0001\u00137!\u0011)c\u0005K\u001a\u000e\u00031I!a\n\u0007\u0003\u0017QK\b/\u001a3D_2,XN\u001c\t\u0003S)b\u0001\u0001B\u0003,\u0007\t\u0007AF\u0001\u0002J\u001dF\u0011Q\u0006\r\t\u000359J!aL\u000e\u0003\u000f9{G\u000f[5oOB\u0011!$M\u0005\u0003em\u00111!\u00118z!\tQB'\u0003\u000267\t1Ai\\;cY\u0016DQaN\u0002A\u0002a\n\u0011A\u001a\t\u00055eB3'\u0003\u0002;7\tIa)\u001e8di&|g.M\u0001\u0006G>,h\u000e^\u000b\u0003{\u0001#\"A\u0010#\u0011\t\u00152s(\u0011\t\u0003S\u0001#Qa\u000b\u0003C\u00021\u0002\"A\u0007\"\n\u0005\r[\"\u0001\u0002'p]\u001eDQa\u000e\u0003A\u0002\u0015\u0003BAG\u001d@a\u0005\u00191/^7\u0016\u0005![ECA%M!\u0011)cES\u001a\u0011\u0005%ZE!B\u0016\u0006\u0005\u0004a\u0003\"B\u001c\u0006\u0001\u0004i\u0005\u0003\u0002\u000e:\u0015N\nqa];n\u0019>tw-\u0006\u0002Q'R\u0011\u0011\u000b\u0016\t\u0005K\u0019\u0012\u0016\t\u0005\u0002*'\u0012)1F\u0002b\u0001Y!)qG\u0002a\u0001+B!!$\u000f*BQ\u0019\tqKW.^=B\u0011!\u0004W\u0005\u00033n\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f\u0013\u0001X\u00010a2,\u0017m]3!kN,\u0007%\u001e8usB,G\r\t2vS2$\u0018N\u001c\u0011bO\u001e\u0014XmZ1uK\u00022WO\\2uS>t7OL\u0001\u0006g&t7-Z\u0011\u0002?\u0006)1G\f\u0019/a!2\u0001a\u0016.\\;z\u0003"
)
public final class typed {
   public static TypedColumn sumLong(final Function1 f) {
      return typed$.MODULE$.sumLong(f);
   }

   public static TypedColumn sum(final Function1 f) {
      return typed$.MODULE$.sum(f);
   }

   public static TypedColumn count(final Function1 f) {
      return typed$.MODULE$.count(f);
   }

   public static TypedColumn avg(final Function1 f) {
      return typed$.MODULE$.avg(f);
   }
}
