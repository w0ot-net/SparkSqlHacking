package org.apache.spark.sql.catalyst.analysis;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.catalyst.util.QuotingUtils$;
import org.apache.spark.sql.connector.catalog.Identifier;
import scala.Option;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054A!\u0003\u0006\u0001/!AA\u0004\u0001B\u0001B\u0003%Q\u0004\u0003\u0005&\u0001\t\u0005\t\u0015!\u0003'\u0011\u0015A\u0004\u0001\"\u0001:\u000f\u001dq$\"!A\t\u0002}2q!\u0003\u0006\u0002\u0002#\u0005\u0001\tC\u00039\u000b\u0011\u0005A\nC\u0004N\u000bE\u0005I\u0011\u0001(\t\u000fe+\u0011\u0011!C\u00055\n\u00113)\u00198o_R\u0014V\r\u001d7bG\u0016l\u0015n]:j]\u001e$\u0016M\u00197f\u000bb\u001cW\r\u001d;j_:T!a\u0003\u0007\u0002\u0011\u0005t\u0017\r\\=tSNT!!\u0004\b\u0002\u0011\r\fG/\u00197zgRT!a\u0004\t\u0002\u0007M\fHN\u0003\u0002\u0012%\u0005)1\u000f]1sW*\u00111\u0003F\u0001\u0007CB\f7\r[3\u000b\u0003U\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\r\u0011\u0005eQR\"\u0001\b\n\u0005mq!!E!oC2L8/[:Fq\u000e,\u0007\u000f^5p]\u0006yA/\u00192mK&#WM\u001c;jM&,'\u000f\u0005\u0002\u001fG5\tqD\u0003\u0002!C\u000591-\u0019;bY><'B\u0001\u0012\u000f\u0003%\u0019wN\u001c8fGR|'/\u0003\u0002%?\tQ\u0011\nZ3oi&4\u0017.\u001a:\u0002\u000b\r\fWo]3\u0011\u0007\u001dRC&D\u0001)\u0015\u0005I\u0013!B:dC2\f\u0017BA\u0016)\u0005\u0019y\u0005\u000f^5p]B\u0011Q&\u000e\b\u0003]Mr!a\f\u001a\u000e\u0003AR!!\r\f\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0013B\u0001\u001b)\u0003\u001d\u0001\u0018mY6bO\u0016L!AN\u001c\u0003\u0013QC'o\\<bE2,'B\u0001\u001b)\u0003\u0019a\u0014N\\5u}Q\u0019!\bP\u001f\u0011\u0005m\u0002Q\"\u0001\u0006\t\u000bq\u0019\u0001\u0019A\u000f\t\u000f\u0015\u001a\u0001\u0013!a\u0001M\u0005\u00113)\u00198o_R\u0014V\r\u001d7bG\u0016l\u0015n]:j]\u001e$\u0016M\u00197f\u000bb\u001cW\r\u001d;j_:\u0004\"aO\u0003\u0014\u0007\u0015\tE\t\u0005\u0002(\u0005&\u00111\t\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0015SU\"\u0001$\u000b\u0005\u001dC\u0015AA5p\u0015\u0005I\u0015\u0001\u00026bm\u0006L!a\u0013$\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0003}\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012T#A(+\u0005\u0019\u00026&A)\u0011\u0005I;V\"A*\u000b\u0005Q+\u0016!C;oG\",7m[3e\u0015\t1\u0006&\u0001\u0006b]:|G/\u0019;j_:L!\u0001W*\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001\\!\tav,D\u0001^\u0015\tq\u0006*\u0001\u0003mC:<\u0017B\u00011^\u0005\u0019y%M[3di\u0002"
)
public class CannotReplaceMissingTableException extends AnalysisException {
   public static Option $lessinit$greater$default$2() {
      return CannotReplaceMissingTableException$.MODULE$.$lessinit$greater$default$2();
   }

   public CannotReplaceMissingTableException(final Identifier tableIdentifier, final Option cause) {
      super("TABLE_OR_VIEW_NOT_FOUND", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("relationName"), QuotingUtils$.MODULE$.quoteNameParts(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.refArrayOps((Object[])tableIdentifier.namespace()), tableIdentifier.name(), scala.reflect.ClassTag..MODULE$.apply(String.class))).toImmutableArraySeq()))}))), cause);
   }
}
