package org.apache.spark.sql.errors;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.spark.QueryContext;
import org.apache.spark.SparkArithmeticException;
import org.apache.spark.SparkDateTimeException;
import org.apache.spark.SparkException;
import org.apache.spark.SparkRuntimeException;
import org.apache.spark.SparkUnsupportedOperationException;
import org.apache.spark.SparkUpgradeException;
import org.apache.spark.sql.catalyst.WalkedTypePath;
import org.apache.spark.sql.internal.SqlApiConf$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DoubleType$;
import org.apache.spark.sql.types.StringType$;
import org.apache.spark.sql.types.UserDefinedType;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t=f\u0001C\u0013'!\u0003\r\t\u0001\u000b\u0019\t\u000bm\u0002A\u0011A\u001f\t\u000b\u0005\u0003A\u0011\u0001\"\t\u000b\u0001\u0004A\u0011A1\t\u000bu\u0004A\u0011\u0001@\t\u000f\u0005\u0015\u0001\u0001\"\u0001\u0002\b!9\u0011q\u0002\u0001\u0005\u0002\u0005E\u0001bBA\f\u0001\u0011\u0005\u0011\u0011\u0004\u0005\n\u0003?\u0001\u0011\u0013!C\u0001\u0003CAq!a\u000e\u0001\t\u0003\tI\u0004C\u0004\u00028\u0001!\t!a\u001a\t\u000f\u0005U\u0004\u0001\"\u0005\u0002x!9\u0011Q\u0011\u0001\u0005\u0002\u0005\u001d\u0005\"CAP\u0001E\u0005I\u0011AA\u0011\u0011%\t\t\u000bAI\u0001\n\u0003\t\u0019\u000bC\u0004\u0002(\u0002!\t!!+\t\u000f\u0005M\u0006\u0001\"\u0001\u00026\"9\u0011q\u001b\u0001\u0005\u0002\u0005e\u0007bBAs\u0001\u0011\u0005\u0011q\u001d\u0005\b\u0003W\u0004A\u0011AAw\u0011\u001d\u0011\t\u0002\u0001C\u0001\u0005'AqAa\u0006\u0001\t\u0003\u0011I\u0002C\u0004\u0003.\u0001!\tAa\f\t\u000f\tU\u0002\u0001\"\u0001\u00038!9!Q\b\u0001\u0005\u0002\t}\u0002b\u0002B+\u0001\u0011\u0005!q\u000b\u0005\b\u0005O\u0002A\u0011\u0001B5\u0011\u0019\u0011I\b\u0001C\u0001}\"9!1\u0010\u0001\u0005\u0002\tu\u0004b\u0002BB\u0001\u0011\u0005!Q\u0011\u0005\b\u0005\u000f\u0003A\u0011\u0001BC\u0011\u001d\u0011I\t\u0001C\u0001\u0005\u000bCqAa#\u0001\t\u0003\u0011i\tC\u0004\u0003\u0014\u0002!\tA!&\b\u0011\t\rf\u0005#\u0001)\u0005K3q!\n\u0014\t\u0002!\u00129\u000bC\u0004\u0003,\u000e\"\tA!,\u0003\u001f\u0015CXmY;uS>tWI\u001d:peNT!a\n\u0015\u0002\r\u0015\u0014(o\u001c:t\u0015\tI#&A\u0002tc2T!a\u000b\u0017\u0002\u000bM\u0004\u0018M]6\u000b\u00055r\u0013AB1qC\u000eDWMC\u00010\u0003\ry'oZ\n\u0004\u0001E:\u0004C\u0001\u001a6\u001b\u0005\u0019$\"\u0001\u001b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u001a$AB!osJ+g\r\u0005\u00029s5\ta%\u0003\u0002;M\t\u0011B)\u0019;b)f\u0004X-\u0012:s_J\u001c()Y:f\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001 \u0011\u0005Iz\u0014B\u0001!4\u0005\u0011)f.\u001b;\u0002K\u0019LW\r\u001c3ES\u001a4WM]:Ge>lG)\u001a:jm\u0016$Gj\\2bY\u0012\u000bG/Z#se>\u0014H#B\"H'bS\u0006C\u0001#F\u001b\u0005Q\u0013B\u0001$+\u0005Y\u0019\u0006/\u0019:l\t\u0006$X\rV5nK\u0016C8-\u001a9uS>t\u0007\"\u0002%\u0003\u0001\u0004I\u0015!\u00024jK2$\u0007C\u0001&R\u001b\u0005Y%B\u0001'N\u0003!!X-\u001c9pe\u0006d'B\u0001(P\u0003\u0011!\u0018.\\3\u000b\u0003A\u000bAA[1wC&\u0011!k\u0013\u0002\f\u0007\"\u0014xN\\8GS\u0016dG\rC\u0003U\u0005\u0001\u0007Q+\u0001\u0004bGR,\u0018\r\u001c\t\u0003eYK!aV\u001a\u0003\u0007%sG\u000fC\u0003Z\u0005\u0001\u0007Q+\u0001\u0005fqB,7\r^3e\u0011\u0015Y&\u00011\u0001]\u0003%\u0019\u0017M\u001c3jI\u0006$X\r\u0005\u0002^=6\tQ*\u0003\u0002`\u001b\nIAj\\2bY\u0012\u000bG/Z\u0001$M\u0006LG\u000eV8QCJ\u001cX\rR1uKRKW.Z%o\u001d\u0016<\b+\u0019:tKJ,%O]8s)\r\u0011WM\u001d\t\u0003\t\u000eL!\u0001\u001a\u0016\u0003+M\u0003\u0018M]6Va\u001e\u0014\u0018\rZ3Fq\u000e,\u0007\u000f^5p]\")am\u0001a\u0001O\u0006\t1\u000f\u0005\u0002i_:\u0011\u0011.\u001c\t\u0003UNj\u0011a\u001b\u0006\u0003Yr\na\u0001\u0010:p_Rt\u0014B\u000184\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001/\u001d\u0002\u0007'R\u0014\u0018N\\4\u000b\u00059\u001c\u0004\"B:\u0004\u0001\u0004!\u0018!A3\u0011\u0005UThB\u0001<y\u001d\tQw/C\u00015\u0013\tI8'A\u0004qC\u000e\\\u0017mZ3\n\u0005md(!\u0003+ie><\u0018M\u00197f\u0015\tI8'\u0001\u0010ti\u0006$Xm\u0015;pe\u0016D\u0015M\u001c3mK:{G/\u00138ji&\fG.\u001b>fIR\tq\u0010E\u0002E\u0003\u0003I1!a\u0001+\u0005U\u0019\u0006/\u0019:l%VtG/[7f\u000bb\u001cW\r\u001d;j_:\fqEZ1jYR{'+Z2pO:L'0\u001a)biR,'O\\!gi\u0016\u0014X\u000b]4sC\u0012,WI\u001d:peR)!-!\u0003\u0002\u000e!1\u00111B\u0003A\u0002\u001d\fq\u0001]1ui\u0016\u0014h\u000eC\u0003t\u000b\u0001\u0007A/A\u000egC&dGk\u001c*fG><g.\u001b>f!\u0006$H/\u001a:o\u000bJ\u0014xN\u001d\u000b\u0006\u007f\u0006M\u0011Q\u0003\u0005\u0007\u0003\u00171\u0001\u0019A4\t\u000bM4\u0001\u0019\u0001;\u0002!Ut'/Z1dQ\u0006\u0014G.Z#se>\u0014HcA@\u0002\u001c!A\u0011QD\u0004\u0011\u0002\u0003\u0007q-A\u0002feJ\f!$\u001e8sK\u0006\u001c\u0007.\u00192mK\u0016\u0013(o\u001c:%I\u00164\u0017-\u001e7uIE*\"!a\t+\u0007\u001d\f)c\u000b\u0002\u0002(A!\u0011\u0011FA\u001a\u001b\t\tYC\u0003\u0003\u0002.\u0005=\u0012!C;oG\",7m[3e\u0015\r\t\tdM\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u001b\u0003W\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003\u0005JgN^1mS\u0012Le\u000e];u\u0013:\u001c\u0015m\u001d;U_\u0012\u000bG/\u001a;j[\u0016,%O]8s)\u001d\u0019\u00151HA(\u0003;Bq!!\u0010\n\u0001\u0004\ty$A\u0003wC2,X\r\u0005\u0003\u0002B\u0005-SBAA\"\u0015\u0011\t)%a\u0012\u0002\u000bQL\b/Z:\u000b\u0007\u0005%#&\u0001\u0004v]N\fg-Z\u0005\u0005\u0003\u001b\n\u0019E\u0001\u0006V)\u001aC4\u000b\u001e:j]\u001eDq!!\u0015\n\u0001\u0004\t\u0019&\u0001\u0002u_B!\u0011QKA-\u001b\t\t9FC\u0002\u0002F!JA!a\u0017\u0002X\tAA)\u0019;b)f\u0004X\rC\u0004\u0002`%\u0001\r!!\u0019\u0002\u000f\r|g\u000e^3yiB\u0019A)a\u0019\n\u0007\u0005\u0015$F\u0001\u0007Rk\u0016\u0014\u0018pQ8oi\u0016DH\u000fF\u0004D\u0003S\n\t(a\u001d\t\u000f\u0005u\"\u00021\u0001\u0002lA\u0019!'!\u001c\n\u0007\u0005=4G\u0001\u0004E_V\u0014G.\u001a\u0005\b\u0003#R\u0001\u0019AA*\u0011\u001d\tyF\u0003a\u0001\u0003C\n\u0011&\u001b8wC2LG-\u00138qkRLenQ1tiR{G)\u0019;fi&lW-\u0012:s_JLe\u000e^3s]\u0006dG#C\"\u0002z\u0005u\u0014\u0011QAB\u0011\u0019\tYh\u0003a\u0001O\u0006A1/\u001d7WC2,X\rC\u0004\u0002\u0000-\u0001\r!a\u0015\u0002\t\u0019\u0014x.\u001c\u0005\b\u0003#Z\u0001\u0019AA*\u0011\u001d\tyf\u0003a\u0001\u0003C\nq#\u0019:ji\"lW\r^5d\u001fZ,'O\u001a7po\u0016\u0013(o\u001c:\u0015\u0011\u0005%\u0015QSAM\u0003;\u0003B!a#\u0002\u00126\u0011\u0011Q\u0012\u0006\u0004\u0003\u001f{\u0015\u0001\u00027b]\u001eLA!a%\u0002\u000e\n\u0019\u0012I]5uQ6,G/[2Fq\u000e,\u0007\u000f^5p]\"1\u0011q\u0013\u0007A\u0002\u001d\fq!\\3tg\u0006<W\r\u0003\u0005\u0002\u001c2\u0001\n\u00111\u0001h\u00035\u0019XoZ4fgR,GMR;oG\"I\u0011q\f\u0007\u0011\u0002\u0003\u0007\u0011\u0011M\u0001\"CJLG\u000f[7fi&\u001cwJ^3sM2|w/\u0012:s_J$C-\u001a4bk2$HEM\u0001\"CJLG\u000f[7fi&\u001cwJ^3sM2|w/\u0012:s_J$C-\u001a4bk2$HeM\u000b\u0003\u0003KSC!!\u0019\u0002&\u0005\u00013-\u00198o_R\u0004\u0016M]:f'R\u0014\u0018N\\4Bg\u0012\u000bG/\u0019+za\u0016,%O]8s)\u001d!\u00181VAW\u0003_Ca!a\u0003\u0010\u0001\u00049\u0007BBA\u001f\u001f\u0001\u0007q\rC\u0004\u00022>\u0001\r!a\u0015\u0002\u0011\u0011\fG/\u0019+za\u0016\f\u0011$\u001e8tkB\u0004xN\u001d;fI\u0006\u0013(o\\<UsB,WI\u001d:peR!\u0011qWA_!\r!\u0015\u0011X\u0005\u0004\u0003wS#AI*qCJ\\WK\\:vaB|'\u000f^3e\u001fB,'/\u0019;j_:,\u0005pY3qi&|g\u000eC\u0004\u0002@B\u0001\r!!1\u0002\u0011QL\b/\u001a(b[\u0016\u0004B!a1\u0002T6\u0011\u0011Q\u0019\u0006\u0005\u0003\u000f\fI-\u0001\u0003q_*|'\u0002BA#\u0003\u0017TA!!4\u0002P\u00061a/Z2u_JT1!!5-\u0003\u0015\t'O]8x\u0013\u0011\t).!2\u0003\u0013\u0005\u0013(o\\<UsB,\u0017!\n3va2L7-\u0019;fI\u001aKW\r\u001c3OC6,\u0017J\\!se><8\u000b\u001e:vGR,%O]8s)\u0011\t9,a7\t\u000f\u0005u\u0017\u00031\u0001\u0002`\u0006Qa-[3mI:\u000bW.Z:\u0011\tU\f\toZ\u0005\u0004\u0003Gd(aA*fc\u0006ARO\\:vaB|'\u000f^3e\t\u0006$\u0018\rV=qK\u0016\u0013(o\u001c:\u0015\t\u0005]\u0016\u0011\u001e\u0005\b\u0003\u007f\u0013\u0002\u0019AA*\u00035*8/\u001a:EK\u001aLg.\u001a3UsB,gj\u001c;B]:|G/\u0019;fI\u0006sGMU3hSN$XM]3e\u000bJ\u0014xN\u001d\u000b\u0004i\u0006=\bbBAy'\u0001\u0007\u00111_\u0001\u0004k\u0012$\b\u0007BA{\u0003\u007f\u0004b!!\u0016\u0002x\u0006m\u0018\u0002BA}\u0003/\u0012q\"V:fe\u0012+g-\u001b8fIRK\b/\u001a\t\u0005\u0003{\fy\u0010\u0004\u0001\u0005\u0019\t\u0005\u0011q^A\u0001\u0002\u0003\u0015\tAa\u0001\u0003\u0007}#\u0013'\u0005\u0003\u0003\u0006\t-\u0001c\u0001\u001a\u0003\b%\u0019!\u0011B\u001a\u0003\u000f9{G\u000f[5oOB\u0019!G!\u0004\n\u0007\t=1GA\u0002B]f\fQdY1o]>$h)\u001b8e\u000b:\u001cw\u000eZ3s\r>\u0014H+\u001f9f\u000bJ\u0014xN\u001d\u000b\u0005\u0003o\u0013)\u0002\u0003\u0004\u0002@R\u0001\raZ\u0001-G\u0006tgn\u001c;ICZ,7)\u001b:dk2\f'OU3gKJ,gnY3t\u0013:\u0014U-\u00198DY\u0006\u001c8/\u0012:s_J$B!a.\u0003\u001c!9!QD\u000bA\u0002\t}\u0011!B2mCjT\b\u0007\u0002B\u0011\u0005S\u0001R\u0001\u001bB\u0012\u0005OI1A!\nr\u0005\u0015\u0019E.Y:t!\u0011\tiP!\u000b\u0005\u0019\t-\"1DA\u0001\u0002\u0003\u0015\tAa\u0001\u0003\u0007}##'A\u0011dC:tw\u000e\u001e$j]\u0012\u001cuN\\:ueV\u001cGo\u001c:G_J$\u0016\u0010]3FeJ|'\u000f\u0006\u0003\u00028\nE\u0002B\u0002B\u001a-\u0001\u0007q-A\u0002ua\u0016\f\u0001fY1o]>$\b*\u0019<f\u0007&\u00148-\u001e7beJ+g-\u001a:f]\u000e,7/\u00138DY\u0006\u001c8/\u0012:s_J$B!a.\u0003:!1!1H\fA\u0002\u001d\f\u0011\u0001^\u0001/G\u0006tgn\u001c;Vg\u0016LeN^1mS\u0012T\u0015M^1JI\u0016tG/\u001b4jKJ\f5OR5fY\u0012t\u0015-\\3FeJ|'\u000f\u0006\u0004\u00028\n\u0005#Q\t\u0005\u0007\u0005\u0007B\u0002\u0019A4\u0002\u0013\u0019LW\r\u001c3OC6,\u0007b\u0002B$1\u0001\u0007!\u0011J\u0001\u000fo\u0006d7.\u001a3UsB,\u0007+\u0019;i!\u0011\u0011YE!\u0015\u000e\u0005\t5#b\u0001B(Q\u0005A1-\u0019;bYf\u001cH/\u0003\u0003\u0003T\t5#AD,bY.,G\rV=qKB\u000bG\u000f[\u0001 aJLW.\u0019:z\u0007>t7\u000f\u001e:vGR|'OT8u\r>,h\u000eZ#se>\u0014HcA@\u0003Z!9!1L\rA\u0002\tu\u0013aA2mgB\"!q\fB2!\u0015A'1\u0005B1!\u0011\tiPa\u0019\u0005\u0019\t\u0015$\u0011LA\u0001\u0002\u0003\u0015\tAa\u0001\u0003\u0007}#3'A\u0014dC:tw\u000e^$fi>+H/\u001a:Q_&tG/\u001a:G_JLeN\\3s\u00072\f7o]#se>\u0014HcA@\u0003l!9!Q\u000e\u000eA\u0002\t=\u0014\u0001C5o]\u0016\u00148\t\\:1\t\tE$Q\u000f\t\u0006Q\n\r\"1\u000f\t\u0005\u0003{\u0014)\b\u0002\u0007\u0003x\t-\u0014\u0011!A\u0001\u0006\u0003\u0011\u0019AA\u0002`IQ\n!dY1o]>$Xk]3Lef|7+\u001a:jC2L'0\u0019;j_:\f1C\\8u!V\u0014G.[2DY\u0006\u001c8/\u0012:s_J$B!a.\u0003\u0000!1!\u0011\u0011\u000fA\u0002\u001d\fAA\\1nK\u0006y\u0002O]5nSRLg/\u001a+za\u0016\u001chj\u001c;TkB\u0004xN\u001d;fI\u0016\u0013(o\u001c:\u0015\u0005\u0005]\u0016aH3mK6,g\u000e^:PMR+\b\u000f\\3Fq\u000e,W\r\u001a'j[&$XI\u001d:pe\u0006YR-\u001c9usR+\b\u000f\\3O_R\u001cV\u000f\u001d9peR,G-\u0012:s_J\f1$\u001b8wC2LG-Q4o_N$\u0018nY#oG>$WM]#se>\u0014Hc\u0001;\u0003\u0010\"1!\u0011\u0013\u0011A\u0002E\nq!\u001a8d_\u0012,'/A\b{_:,wJ\u001a4tKR,%O]8s)\u0015\u0019%q\u0013BN\u0011\u0019\u0011I*\ta\u0001O\u0006AA/[7f5>tW\r\u0003\u0004tC\u0001\u0007!Q\u0014\t\u0004;\n}\u0015b\u0001BQ\u001b\n\tB)\u0019;f)&lW-\u0012=dKB$\u0018n\u001c8\u0002\u001f\u0015CXmY;uS>tWI\u001d:peN\u0004\"\u0001O\u0012\u0014\t\r\n$\u0011\u0016\t\u0003q\u0001\ta\u0001P5oSRtDC\u0001BS\u0001"
)
public interface ExecutionErrors extends DataTypeErrorsBase {
   // $FF: synthetic method
   static SparkDateTimeException fieldDiffersFromDerivedLocalDateError$(final ExecutionErrors $this, final ChronoField field, final int actual, final int expected, final LocalDate candidate) {
      return $this.fieldDiffersFromDerivedLocalDateError(field, actual, expected, candidate);
   }

   default SparkDateTimeException fieldDiffersFromDerivedLocalDateError(final ChronoField field, final int actual, final int expected, final LocalDate candidate) {
      return new SparkDateTimeException("_LEGACY_ERROR_TEMP_2129", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("field"), field.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("actual"), Integer.toString(actual)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("expected"), Integer.toString(expected)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("candidate"), candidate.toString())}))), (QueryContext[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class)), "");
   }

   // $FF: synthetic method
   static SparkUpgradeException failToParseDateTimeInNewParserError$(final ExecutionErrors $this, final String s, final Throwable e) {
      return $this.failToParseDateTimeInNewParserError(s, e);
   }

   default SparkUpgradeException failToParseDateTimeInNewParserError(final String s, final Throwable e) {
      return new SparkUpgradeException("INCONSISTENT_BEHAVIOR_CROSS_VERSION.PARSE_DATETIME_BY_NEW_PARSER", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("datetime"), this.toSQLValue(s)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("config"), this.toSQLConf(SqlApiConf$.MODULE$.LEGACY_TIME_PARSER_POLICY_KEY()))}))), e);
   }

   // $FF: synthetic method
   static SparkRuntimeException stateStoreHandleNotInitialized$(final ExecutionErrors $this) {
      return $this.stateStoreHandleNotInitialized();
   }

   default SparkRuntimeException stateStoreHandleNotInitialized() {
      return new SparkRuntimeException("STATE_STORE_HANDLE_NOT_INITIALIZED", .MODULE$.Map().empty(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$3(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$5());
   }

   // $FF: synthetic method
   static SparkUpgradeException failToRecognizePatternAfterUpgradeError$(final ExecutionErrors $this, final String pattern, final Throwable e) {
      return $this.failToRecognizePatternAfterUpgradeError(pattern, e);
   }

   default SparkUpgradeException failToRecognizePatternAfterUpgradeError(final String pattern, final Throwable e) {
      return new SparkUpgradeException("INCONSISTENT_BEHAVIOR_CROSS_VERSION.DATETIME_PATTERN_RECOGNITION", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("pattern"), this.toSQLValue(pattern)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("config"), this.toSQLConf(SqlApiConf$.MODULE$.LEGACY_TIME_PARSER_POLICY_KEY())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("docroot"), org.apache.spark.SparkBuildInfo..MODULE$.spark_doc_root())}))), e);
   }

   // $FF: synthetic method
   static SparkRuntimeException failToRecognizePatternError$(final ExecutionErrors $this, final String pattern, final Throwable e) {
      return $this.failToRecognizePatternError(pattern, e);
   }

   default SparkRuntimeException failToRecognizePatternError(final String pattern, final Throwable e) {
      return new SparkRuntimeException("_LEGACY_ERROR_TEMP_2130", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("pattern"), this.toSQLValue(pattern)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("docroot"), org.apache.spark.SparkBuildInfo..MODULE$.spark_doc_root())}))), e, org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$5());
   }

   // $FF: synthetic method
   static SparkRuntimeException unreachableError$(final ExecutionErrors $this, final String err) {
      return $this.unreachableError(err);
   }

   default SparkRuntimeException unreachableError(final String err) {
      return new SparkRuntimeException("_LEGACY_ERROR_TEMP_2028", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("err"), err)}))), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$3(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$5());
   }

   // $FF: synthetic method
   static String unreachableError$default$1$(final ExecutionErrors $this) {
      return $this.unreachableError$default$1();
   }

   default String unreachableError$default$1() {
      return "";
   }

   // $FF: synthetic method
   static SparkDateTimeException invalidInputInCastToDatetimeError$(final ExecutionErrors $this, final UTF8String value, final DataType to, final QueryContext context) {
      return $this.invalidInputInCastToDatetimeError(value, to, context);
   }

   default SparkDateTimeException invalidInputInCastToDatetimeError(final UTF8String value, final DataType to, final QueryContext context) {
      return this.invalidInputInCastToDatetimeErrorInternal(this.toSQLValue(value), StringType$.MODULE$, to, context);
   }

   // $FF: synthetic method
   static SparkDateTimeException invalidInputInCastToDatetimeError$(final ExecutionErrors $this, final double value, final DataType to, final QueryContext context) {
      return $this.invalidInputInCastToDatetimeError(value, to, context);
   }

   default SparkDateTimeException invalidInputInCastToDatetimeError(final double value, final DataType to, final QueryContext context) {
      return this.invalidInputInCastToDatetimeErrorInternal(this.toSQLValue(value), DoubleType$.MODULE$, to, context);
   }

   // $FF: synthetic method
   static SparkDateTimeException invalidInputInCastToDatetimeErrorInternal$(final ExecutionErrors $this, final String sqlValue, final DataType from, final DataType to, final QueryContext context) {
      return $this.invalidInputInCastToDatetimeErrorInternal(sqlValue, from, to, context);
   }

   default SparkDateTimeException invalidInputInCastToDatetimeErrorInternal(final String sqlValue, final DataType from, final DataType to, final QueryContext context) {
      return new SparkDateTimeException("CAST_INVALID_INPUT", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("expression"), sqlValue), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("sourceType"), this.toSQLType(from)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("targetType"), this.toSQLType(to)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("ansiConfig"), this.toSQLConf("spark.sql.ansi.enabled"))}))), this.getQueryContext(context), this.getSummary(context));
   }

   // $FF: synthetic method
   static ArithmeticException arithmeticOverflowError$(final ExecutionErrors $this, final String message, final String suggestedFunc, final QueryContext context) {
      return $this.arithmeticOverflowError(message, suggestedFunc, context);
   }

   default ArithmeticException arithmeticOverflowError(final String message, final String suggestedFunc, final QueryContext context) {
      String alternative = scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(suggestedFunc)) ? " Use '" + suggestedFunc + "' to tolerate overflow and return NULL instead." : "";
      return new SparkArithmeticException("ARITHMETIC_OVERFLOW", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("message"), message), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("alternative"), alternative), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("config"), this.toSQLConf(SqlApiConf$.MODULE$.ANSI_ENABLED_KEY()))}))), this.getQueryContext(context), this.getSummary(context));
   }

   // $FF: synthetic method
   static String arithmeticOverflowError$default$2$(final ExecutionErrors $this) {
      return $this.arithmeticOverflowError$default$2();
   }

   default String arithmeticOverflowError$default$2() {
      return "";
   }

   // $FF: synthetic method
   static QueryContext arithmeticOverflowError$default$3$(final ExecutionErrors $this) {
      return $this.arithmeticOverflowError$default$3();
   }

   default QueryContext arithmeticOverflowError$default$3() {
      return null;
   }

   // $FF: synthetic method
   static Throwable cannotParseStringAsDataTypeError$(final ExecutionErrors $this, final String pattern, final String value, final DataType dataType) {
      return $this.cannotParseStringAsDataTypeError(pattern, value, dataType);
   }

   default Throwable cannotParseStringAsDataTypeError(final String pattern, final String value, final DataType dataType) {
      SparkException var10000 = org.apache.spark.SparkException..MODULE$;
      String var10001 = this.toSQLValue(value);
      return var10000.internalError("Cannot parse field value " + var10001 + " for pattern " + this.toSQLValue(pattern) + " as the target spark data type " + this.toSQLType(dataType) + ".");
   }

   // $FF: synthetic method
   static SparkUnsupportedOperationException unsupportedArrowTypeError$(final ExecutionErrors $this, final ArrowType typeName) {
      return $this.unsupportedArrowTypeError(typeName);
   }

   default SparkUnsupportedOperationException unsupportedArrowTypeError(final ArrowType typeName) {
      return new SparkUnsupportedOperationException("UNSUPPORTED_ARROWTYPE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("typeName"), typeName.toString())}))));
   }

   // $FF: synthetic method
   static SparkUnsupportedOperationException duplicatedFieldNameInArrowStructError$(final ExecutionErrors $this, final Seq fieldNames) {
      return $this.duplicatedFieldNameInArrowStructError(fieldNames);
   }

   default SparkUnsupportedOperationException duplicatedFieldNameInArrowStructError(final Seq fieldNames) {
      return new SparkUnsupportedOperationException("DUPLICATED_FIELD_NAME_IN_ARROW_STRUCT", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("fieldNames"), fieldNames.mkString("[", ", ", "]"))}))));
   }

   // $FF: synthetic method
   static SparkUnsupportedOperationException unsupportedDataTypeError$(final ExecutionErrors $this, final DataType typeName) {
      return $this.unsupportedDataTypeError(typeName);
   }

   default SparkUnsupportedOperationException unsupportedDataTypeError(final DataType typeName) {
      return new SparkUnsupportedOperationException("UNSUPPORTED_DATATYPE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("typeName"), this.toSQLType(typeName))}))));
   }

   // $FF: synthetic method
   static Throwable userDefinedTypeNotAnnotatedAndRegisteredError$(final ExecutionErrors $this, final UserDefinedType udt) {
      return $this.userDefinedTypeNotAnnotatedAndRegisteredError(udt);
   }

   default Throwable userDefinedTypeNotAnnotatedAndRegisteredError(final UserDefinedType udt) {
      return new SparkException("_LEGACY_ERROR_TEMP_2155", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("userClass"), udt.userClass().getName())}))), (Throwable)null);
   }

   // $FF: synthetic method
   static SparkUnsupportedOperationException cannotFindEncoderForTypeError$(final ExecutionErrors $this, final String typeName) {
      return $this.cannotFindEncoderForTypeError(typeName);
   }

   default SparkUnsupportedOperationException cannotFindEncoderForTypeError(final String typeName) {
      return new SparkUnsupportedOperationException("ENCODER_NOT_FOUND", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("typeName"), typeName), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("docroot"), org.apache.spark.SparkBuildInfo..MODULE$.spark_doc_root())}))));
   }

   // $FF: synthetic method
   static SparkUnsupportedOperationException cannotHaveCircularReferencesInBeanClassError$(final ExecutionErrors $this, final Class clazz) {
      return $this.cannotHaveCircularReferencesInBeanClassError(clazz);
   }

   default SparkUnsupportedOperationException cannotHaveCircularReferencesInBeanClassError(final Class clazz) {
      return new SparkUnsupportedOperationException("CIRCULAR_CLASS_REFERENCE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("t"), this.toSQLValue(clazz.toString()))}))));
   }

   // $FF: synthetic method
   static SparkUnsupportedOperationException cannotFindConstructorForTypeError$(final ExecutionErrors $this, final String tpe) {
      return $this.cannotFindConstructorForTypeError(tpe);
   }

   default SparkUnsupportedOperationException cannotFindConstructorForTypeError(final String tpe) {
      return new SparkUnsupportedOperationException("_LEGACY_ERROR_TEMP_2144", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("tpe"), tpe)}))));
   }

   // $FF: synthetic method
   static SparkUnsupportedOperationException cannotHaveCircularReferencesInClassError$(final ExecutionErrors $this, final String t) {
      return $this.cannotHaveCircularReferencesInClassError(t);
   }

   default SparkUnsupportedOperationException cannotHaveCircularReferencesInClassError(final String t) {
      return new SparkUnsupportedOperationException("CIRCULAR_CLASS_REFERENCE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("t"), this.toSQLValue(t))}))));
   }

   // $FF: synthetic method
   static SparkUnsupportedOperationException cannotUseInvalidJavaIdentifierAsFieldNameError$(final ExecutionErrors $this, final String fieldName, final WalkedTypePath walkedTypePath) {
      return $this.cannotUseInvalidJavaIdentifierAsFieldNameError(fieldName, walkedTypePath);
   }

   default SparkUnsupportedOperationException cannotUseInvalidJavaIdentifierAsFieldNameError(final String fieldName, final WalkedTypePath walkedTypePath) {
      return new SparkUnsupportedOperationException("INVALID_JAVA_IDENTIFIER_AS_FIELD_NAME", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("fieldName"), this.toSQLId(fieldName)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("walkedTypePath"), walkedTypePath.toString())}))));
   }

   // $FF: synthetic method
   static SparkRuntimeException primaryConstructorNotFoundError$(final ExecutionErrors $this, final Class cls) {
      return $this.primaryConstructorNotFoundError(cls);
   }

   default SparkRuntimeException primaryConstructorNotFoundError(final Class cls) {
      return new SparkRuntimeException("INTERNAL_ERROR", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("message"), "Couldn't find a primary constructor on " + cls.toString() + ".")}))), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$3(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$5());
   }

   // $FF: synthetic method
   static SparkRuntimeException cannotGetOuterPointerForInnerClassError$(final ExecutionErrors $this, final Class innerCls) {
      return $this.cannotGetOuterPointerForInnerClassError(innerCls);
   }

   default SparkRuntimeException cannotGetOuterPointerForInnerClassError(final Class innerCls) {
      return new SparkRuntimeException("_LEGACY_ERROR_TEMP_2154", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("innerCls"), innerCls.getName())}))), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$3(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$5());
   }

   // $FF: synthetic method
   static SparkRuntimeException cannotUseKryoSerialization$(final ExecutionErrors $this) {
      return $this.cannotUseKryoSerialization();
   }

   default SparkRuntimeException cannotUseKryoSerialization() {
      return new SparkRuntimeException("CANNOT_USE_KRYO", .MODULE$.Map().empty(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$3(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$5());
   }

   // $FF: synthetic method
   static SparkUnsupportedOperationException notPublicClassError$(final ExecutionErrors $this, final String name) {
      return $this.notPublicClassError(name);
   }

   default SparkUnsupportedOperationException notPublicClassError(final String name) {
      return new SparkUnsupportedOperationException("_LEGACY_ERROR_TEMP_2229", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("name"), name)}))));
   }

   // $FF: synthetic method
   static SparkUnsupportedOperationException primitiveTypesNotSupportedError$(final ExecutionErrors $this) {
      return $this.primitiveTypesNotSupportedError();
   }

   default SparkUnsupportedOperationException primitiveTypesNotSupportedError() {
      return new SparkUnsupportedOperationException("_LEGACY_ERROR_TEMP_2230");
   }

   // $FF: synthetic method
   static SparkUnsupportedOperationException elementsOfTupleExceedLimitError$(final ExecutionErrors $this) {
      return $this.elementsOfTupleExceedLimitError();
   }

   default SparkUnsupportedOperationException elementsOfTupleExceedLimitError() {
      return new SparkUnsupportedOperationException("TUPLE_SIZE_EXCEEDS_LIMIT");
   }

   // $FF: synthetic method
   static SparkUnsupportedOperationException emptyTupleNotSupportedError$(final ExecutionErrors $this) {
      return $this.emptyTupleNotSupportedError();
   }

   default SparkUnsupportedOperationException emptyTupleNotSupportedError() {
      return new SparkUnsupportedOperationException("TUPLE_IS_EMPTY");
   }

   // $FF: synthetic method
   static Throwable invalidAgnosticEncoderError$(final ExecutionErrors $this, final Object encoder) {
      return $this.invalidAgnosticEncoderError(encoder);
   }

   default Throwable invalidAgnosticEncoderError(final Object encoder) {
      return new SparkRuntimeException("INVALID_AGNOSTIC_ENCODER", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("encoderType"), encoder.getClass().getName()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("docroot"), org.apache.spark.SparkBuildInfo..MODULE$.spark_doc_root())}))), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$3(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$5());
   }

   // $FF: synthetic method
   static SparkDateTimeException zoneOffsetError$(final ExecutionErrors $this, final String timeZone, final DateTimeException e) {
      return $this.zoneOffsetError(timeZone, e);
   }

   default SparkDateTimeException zoneOffsetError(final String timeZone, final DateTimeException e) {
      return new SparkDateTimeException("INVALID_TIMEZONE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("timeZone"), timeZone)}))), (QueryContext[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class)), "", new Some(e));
   }

   static void $init$(final ExecutionErrors $this) {
   }
}
