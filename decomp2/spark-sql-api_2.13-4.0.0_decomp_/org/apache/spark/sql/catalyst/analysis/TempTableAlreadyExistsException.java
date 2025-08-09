package org.apache.spark.sql.catalyst.analysis;

import org.apache.spark.QueryContext;
import org.apache.spark.SparkThrowableHelper.;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.AnalysisException$;
import org.apache.spark.sql.catalyst.util.AttributeNameParser$;
import org.apache.spark.sql.catalyst.util.QuotingUtils$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q4A!\u0004\b\u00017!A\u0001\u0005\u0001B\u0001B\u0003%\u0011\u0005\u0003\u0005/\u0001\t\u0005\t\u0015!\u00030\u0011!a\u0004A!A!\u0002\u0013i\u0004\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0011B \t\u000b\t\u0003A\u0011B\"\t\u000b\t\u0003A\u0011\u0001&\t\u000b\t\u0003A\u0011\u0001(\b\u000fEs\u0011\u0011!E\u0001%\u001a9QBDA\u0001\u0012\u0003\u0019\u0006\"\u0002\"\n\t\u0003y\u0006b\u00021\n#\u0003%\t!\u0019\u0005\bY&\t\t\u0011\"\u0003n\u0005}!V-\u001c9UC\ndW-\u00117sK\u0006$\u00170\u0012=jgR\u001cX\t_2faRLwN\u001c\u0006\u0003\u001fA\t\u0001\"\u00198bYf\u001c\u0018n\u001d\u0006\u0003#I\t\u0001bY1uC2L8\u000f\u001e\u0006\u0003'Q\t1a]9m\u0015\t)b#A\u0003ta\u0006\u00148N\u0003\u0002\u00181\u00051\u0011\r]1dQ\u0016T\u0011!G\u0001\u0004_J<7\u0001A\n\u0003\u0001q\u0001\"!\b\u0010\u000e\u0003II!a\b\n\u0003#\u0005s\u0017\r\\=tSN,\u0005pY3qi&|g.A\u0004nKN\u001c\u0018mZ3\u0011\u0005\tZcBA\u0012*!\t!s%D\u0001&\u0015\t1#$\u0001\u0004=e>|GO\u0010\u0006\u0002Q\u0005)1oY1mC&\u0011!fJ\u0001\u0007!J,G-\u001a4\n\u00051j#AB*ue&twM\u0003\u0002+O\u0005)1-Y;tKB\u0019\u0001'M\u001a\u000e\u0003\u001dJ!AM\u0014\u0003\r=\u0003H/[8o!\t!\u0014H\u0004\u00026o9\u0011AEN\u0005\u0002Q%\u0011\u0001hJ\u0001\ba\u0006\u001c7.Y4f\u0013\tQ4HA\u0005UQJ|w/\u00192mK*\u0011\u0001hJ\u0001\u000bKJ\u0014xN]\"mCN\u001c\bc\u0001\u00192C\u0005\tR.Z:tC\u001e,\u0007+\u0019:b[\u0016$XM]:\u0011\t\t\u0002\u0015%I\u0005\u0003\u00036\u00121!T1q\u0003\u0019a\u0014N\\5u}Q)AIR$I\u0013B\u0011Q\tA\u0007\u0002\u001d!)\u0001%\u0002a\u0001C!)a&\u0002a\u0001_!)A(\u0002a\u0001{!)a(\u0002a\u0001\u007fQ!Ai\u0013'N\u0011\u0015ad\u00011\u0001\"\u0011\u0015qd\u00011\u0001@\u0011\u001dqc\u0001%AA\u0002=\"\"\u0001R(\t\u000bA;\u0001\u0019A\u0011\u0002\u000bQ\f'\r\\3\u0002?Q+W\u000e\u001d+bE2,\u0017\t\u001c:fC\u0012LX\t_5tiN,\u0005pY3qi&|g\u000e\u0005\u0002F\u0013M\u0019\u0011\u0002V,\u0011\u0005A*\u0016B\u0001,(\u0005\u0019\te.\u001f*fMB\u0011\u0001,X\u0007\u00023*\u0011!lW\u0001\u0003S>T\u0011\u0001X\u0001\u0005U\u00064\u0018-\u0003\u0002_3\na1+\u001a:jC2L'0\u00192mKR\t!+A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u000b\u0002E*\u0012qfY\u0016\u0002IB\u0011QM[\u0007\u0002M*\u0011q\r[\u0001\nk:\u001c\u0007.Z2lK\u0012T!![\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002lM\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u00039\u0004\"a\u001c:\u000e\u0003AT!!].\u0002\t1\fgnZ\u0005\u0003gB\u0014aa\u00142kK\u000e$\b"
)
public class TempTableAlreadyExistsException extends AnalysisException {
   public static Option $lessinit$greater$default$3() {
      return TempTableAlreadyExistsException$.MODULE$.$lessinit$greater$default$3();
   }

   private TempTableAlreadyExistsException(final String message, final Option cause, final Option errorClass, final Map messageParameters) {
      Option x$5 = AnalysisException$.MODULE$.$lessinit$greater$default$2();
      Option x$6 = AnalysisException$.MODULE$.$lessinit$greater$default$3();
      QueryContext[] x$7 = AnalysisException$.MODULE$.$lessinit$greater$default$7();
      super(message, x$5, x$6, cause, errorClass, messageParameters, x$7);
   }

   public TempTableAlreadyExistsException(final String errorClass, final Map messageParameters, final Option cause) {
      this(.MODULE$.getMessage(errorClass, messageParameters), cause, new Some(errorClass), messageParameters);
   }

   public TempTableAlreadyExistsException(final String table) {
      this("TEMP_TABLE_OR_VIEW_ALREADY_EXISTS", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("relationName"), QuotingUtils$.MODULE$.quoteNameParts(AttributeNameParser$.MODULE$.parseAttributeName(table)))}))), TempTableAlreadyExistsException$.MODULE$.$lessinit$greater$default$3());
   }
}
