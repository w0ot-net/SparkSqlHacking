package org.apache.spark.mllib.recommendation;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.recommendation.ALS$Rating$mcI$sp;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Predef;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r}a\u0001B\"E\u0001=C\u0001\u0002\u001b\u0001\u0003\u0002\u0004%I!\u001b\u0005\t[\u0002\u0011\t\u0019!C\u0005]\"AA\u000f\u0001B\u0001B\u0003&!\u000e\u0003\u0005v\u0001\t\u0005\r\u0011\"\u0003j\u0011!1\bA!a\u0001\n\u00139\b\u0002C=\u0001\u0005\u0003\u0005\u000b\u0015\u00026\t\u0011i\u0004!\u00111A\u0005\n%D\u0001b\u001f\u0001\u0003\u0002\u0004%I\u0001 \u0005\t}\u0002\u0011\t\u0011)Q\u0005U\"Aq\u0010\u0001BA\u0002\u0013%\u0011\u000e\u0003\u0006\u0002\u0002\u0001\u0011\t\u0019!C\u0005\u0003\u0007A\u0011\"a\u0002\u0001\u0005\u0003\u0005\u000b\u0015\u00026\t\u0015\u0005%\u0001A!a\u0001\n\u0013\tY\u0001\u0003\u0006\u0002\u0014\u0001\u0011\t\u0019!C\u0005\u0003+A!\"!\u0007\u0001\u0005\u0003\u0005\u000b\u0015BA\u0007\u0011)\tY\u0002\u0001BA\u0002\u0013%\u0011Q\u0004\u0005\u000b\u0003K\u0001!\u00111A\u0005\n\u0005\u001d\u0002BCA\u0016\u0001\t\u0005\t\u0015)\u0003\u0002 !Q\u0011Q\u0006\u0001\u0003\u0002\u0004%I!a\u0003\t\u0015\u0005=\u0002A!a\u0001\n\u0013\t\t\u0004\u0003\u0006\u00026\u0001\u0011\t\u0011)Q\u0005\u0003\u001bA!\"a\u000e\u0001\u0005\u0003\u0007I\u0011BA\u001d\u0011)\t\t\u0005\u0001BA\u0002\u0013%\u00111\t\u0005\u000b\u0003\u000f\u0002!\u0011!Q!\n\u0005m\u0002bBA%\u0001\u0011%\u00111\n\u0005\b\u0003\u0013\u0002A\u0011AA1\u0011%\t)\b\u0001a\u0001\n\u0013\ti\u0002C\u0005\u0002x\u0001\u0001\r\u0011\"\u0003\u0002z!A\u0011Q\u0010\u0001!B\u0013\ty\u0002C\u0005\u0002\u0000\u0001\u0001\r\u0011\"\u0003\u0002\u0002\"I\u0011q\u0012\u0001A\u0002\u0013%\u0011\u0011\u0013\u0005\t\u0003+\u0003\u0001\u0015)\u0003\u0002\u0004\"I\u0011q\u0013\u0001A\u0002\u0013%\u0011\u0011\u0011\u0005\n\u00033\u0003\u0001\u0019!C\u0005\u00037C\u0001\"a(\u0001A\u0003&\u00111\u0011\u0005\t\u0003C\u0003\u0001\u0019!C\u0005S\"I\u00111\u0015\u0001A\u0002\u0013%\u0011Q\u0015\u0005\b\u0003S\u0003\u0001\u0015)\u0003k\u0011\u001d\tY\u000b\u0001C\u0001\u0003[Cq!a.\u0001\t\u0003\tI\fC\u0004\u0002D\u0002!\t!!2\t\u000f\u0005-\u0007\u0001\"\u0001\u0002N\"9\u00111\u001b\u0001\u0005\u0002\u0005U\u0007bBAn\u0001\u0011\u0005\u0011Q\u001c\u0005\b\u0003G\u0004A\u0011AAs\u0011\u001d\ty\u000f\u0001C\u0001\u0003cDq!a>\u0001\t\u0003\tI\u0010C\u0004\u0003\u0004\u0001!\tA!\u0002\t\u000f\t5\u0001\u0001\"\u0001\u0003\u0010!9!q\u0003\u0001\u0005\u0002\te\u0001b\u0002B\u0012\u0001\u0011\u0005!Q\u0005\u0005\b\u0005_\u0001A\u0011\u0001B\u0019\u0011\u001d\u0011y\u0003\u0001C\u0001\u0005#:qA!\u001bE\u0011\u0003\u0011YG\u0002\u0004D\t\"\u0005!Q\u000e\u0005\b\u0003\u0013:D\u0011\u0001B>\u0011\u001d\u0011ih\u000eC\u0001\u0005\u007fBqA! 8\t\u0003\u0011)\nC\u0004\u0003~]\"\tAa)\t\u000f\tut\u0007\"\u0001\u00030\"9!\u0011X\u001c\u0005\u0002\tm\u0006b\u0002B]o\u0011\u0005!Q\u001a\u0005\b\u0005s;D\u0011\u0001Bo\u0011\u001d\u0011Il\u000eC\u0001\u0005WD\u0011B!>8#\u0003%IAa>\t\u0013\r-q'!A\u0005\n\r5!aA!M'*\u0011QIR\u0001\u000fe\u0016\u001cw.\\7f]\u0012\fG/[8o\u0015\t9\u0005*A\u0003nY2L'M\u0003\u0002J\u0015\u0006)1\u000f]1sW*\u00111\nT\u0001\u0007CB\f7\r[3\u000b\u00035\u000b1a\u001c:h\u0007\u0001\u0019B\u0001\u0001)WEB\u0011\u0011\u000bV\u0007\u0002%*\t1+A\u0003tG\u0006d\u0017-\u0003\u0002V%\n1\u0011I\\=SK\u001a\u0004\"aV0\u000f\u0005akfBA-]\u001b\u0005Q&BA.O\u0003\u0019a$o\\8u}%\t1+\u0003\u0002_%\u00069\u0001/Y2lC\u001e,\u0017B\u00011b\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tq&\u000b\u0005\u0002dM6\tAM\u0003\u0002f\u0011\u0006A\u0011N\u001c;fe:\fG.\u0003\u0002hI\n9Aj\\4hS:<\u0017!\u00048v[V\u001bXM\u001d\"m_\u000e\\7/F\u0001k!\t\t6.\u0003\u0002m%\n\u0019\u0011J\u001c;\u0002#9,X.V:fe\ncwnY6t?\u0012*\u0017\u000f\u0006\u0002peB\u0011\u0011\u000b]\u0005\u0003cJ\u0013A!\u00168ji\"91OAA\u0001\u0002\u0004Q\u0017a\u0001=%c\u0005qa.^7Vg\u0016\u0014(\t\\8dWN\u0004\u0013\u0001\u00058v[B\u0013x\u000eZ;di\ncwnY6t\u0003QqW/\u001c)s_\u0012,8\r\u001e\"m_\u000e\\7o\u0018\u0013fcR\u0011q\u000e\u001f\u0005\bg\u0016\t\t\u00111\u0001k\u0003EqW/\u001c)s_\u0012,8\r\u001e\"m_\u000e\\7\u000fI\u0001\u0005e\u0006t7.\u0001\u0005sC:\\w\fJ3r)\tyW\u0010C\u0004t\u0011\u0005\u0005\t\u0019\u00016\u0002\u000bI\fgn\u001b\u0011\u0002\u0015%$XM]1uS>t7/\u0001\bji\u0016\u0014\u0018\r^5p]N|F%Z9\u0015\u0007=\f)\u0001C\u0004t\u0017\u0005\u0005\t\u0019\u00016\u0002\u0017%$XM]1uS>t7\u000fI\u0001\u0007Y\u0006l'\rZ1\u0016\u0005\u00055\u0001cA)\u0002\u0010%\u0019\u0011\u0011\u0003*\u0003\r\u0011{WO\u00197f\u0003)a\u0017-\u001c2eC~#S-\u001d\u000b\u0004_\u0006]\u0001\u0002C:\u000f\u0003\u0003\u0005\r!!\u0004\u0002\u000f1\fWN\u00193bA\u0005i\u0011.\u001c9mS\u000eLG\u000f\u0015:fMN,\"!a\b\u0011\u0007E\u000b\t#C\u0002\u0002$I\u0013qAQ8pY\u0016\fg.A\tj[Bd\u0017nY5u!J,gm]0%KF$2a\\A\u0015\u0011!\u0019\u0018#!AA\u0002\u0005}\u0011AD5na2L7-\u001b;Qe\u001647\u000fI\u0001\u0006C2\u0004\b.Y\u0001\nC2\u0004\b.Y0%KF$2a\\A\u001a\u0011!\u0019H#!AA\u0002\u00055\u0011AB1ma\"\f\u0007%\u0001\u0003tK\u0016$WCAA\u001e!\r\t\u0016QH\u0005\u0004\u0003\u007f\u0011&\u0001\u0002'p]\u001e\f\u0001b]3fI~#S-\u001d\u000b\u0004_\u0006\u0015\u0003\u0002C:\u0018\u0003\u0003\u0005\r!a\u000f\u0002\u000bM,W\r\u001a\u0011\u0002\rqJg.\u001b;?)I\ti%!\u0015\u0002T\u0005U\u0013qKA-\u00037\ni&a\u0018\u0011\u0007\u0005=\u0003!D\u0001E\u0011\u0015A\u0017\u00041\u0001k\u0011\u0015)\u0018\u00041\u0001k\u0011\u0015Q\u0018\u00041\u0001k\u0011\u0015y\u0018\u00041\u0001k\u0011\u001d\tI!\u0007a\u0001\u0003\u001bAq!a\u0007\u001a\u0001\u0004\ty\u0002C\u0004\u0002.e\u0001\r!!\u0004\t\u0013\u0005]\u0012\u0004%AA\u0002\u0005mBCAA'Q\u0015Q\u0012QMA9!\u0011\t9'!\u001c\u000e\u0005\u0005%$bAA6\u0011\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005=\u0014\u0011\u000e\u0002\u0006'&t7-Z\u0011\u0003\u0003g\nQ\u0001\r\u00189]A\n1B\\8o]\u0016<\u0017\r^5wK\u0006yan\u001c8oK\u001e\fG/\u001b<f?\u0012*\u0017\u000fF\u0002p\u0003wB\u0001b\u001d\u000f\u0002\u0002\u0003\u0007\u0011qD\u0001\r]>tg.Z4bi&4X\rI\u0001\u001cS:$XM]7fI&\fG/\u001a*E\tN#xN]1hK2+g/\u001a7\u0016\u0005\u0005\r\u0005\u0003BAC\u0003\u0017k!!a\"\u000b\u0007\u0005%\u0005*A\u0004ti>\u0014\u0018mZ3\n\t\u00055\u0015q\u0011\u0002\r'R|'/Y4f\u0019\u00164X\r\\\u0001 S:$XM]7fI&\fG/\u001a*E\tN#xN]1hK2+g/\u001a7`I\u0015\fHcA8\u0002\u0014\"A1oHA\u0001\u0002\u0004\t\u0019)\u0001\u000fj]R,'/\\3eS\u0006$XM\u0015#E'R|'/Y4f\u0019\u00164X\r\u001c\u0011\u0002)\u0019Lg.\u00197S\t\u0012\u001bFo\u001c:bO\u0016dUM^3m\u0003a1\u0017N\\1m%\u0012#5\u000b^8sC\u001e,G*\u001a<fY~#S-\u001d\u000b\u0004_\u0006u\u0005\u0002C:#\u0003\u0003\u0005\r!a!\u0002+\u0019Lg.\u00197S\t\u0012\u001bFo\u001c:bO\u0016dUM^3mA\u0005\u00112\r[3dWB|\u0017N\u001c;J]R,'O^1m\u0003Y\u0019\u0007.Z2la>Lg\u000e^%oi\u0016\u0014h/\u00197`I\u0015\fHcA8\u0002(\"91/JA\u0001\u0002\u0004Q\u0017aE2iK\u000e\\\u0007o\\5oi&sG/\u001a:wC2\u0004\u0013!C:fi\ncwnY6t)\u0011\ty+!-\u000e\u0003\u0001Aa!a-(\u0001\u0004Q\u0017!\u00038v[\ncwnY6tQ\u00159\u0013QMA9\u00035\u0019X\r^+tKJ\u0014En\\2lgR!\u0011qVA^\u0011\u0015A\u0007\u00061\u0001kQ\u0015A\u0013QMA`C\t\t\t-A\u00032]Er\u0003'\u0001\ttKR\u0004&o\u001c3vGR\u0014En\\2lgR!\u0011qVAd\u0011\u0015)\u0018\u00061\u0001kQ\u0015I\u0013QMA`\u0003\u001d\u0019X\r\u001e*b].$B!a,\u0002P\")!P\u000ba\u0001U\"*!&!\u001a\u0002r\u0005i1/\u001a;Ji\u0016\u0014\u0018\r^5p]N$B!a,\u0002X\")qp\u000ba\u0001U\"*1&!\u001a\u0002r\u0005I1/\u001a;MC6\u0014G-\u0019\u000b\u0005\u0003_\u000by\u000eC\u0004\u0002\n1\u0002\r!!\u0004)\u000b1\n)'!\u001d\u0002!M,G/S7qY&\u001c\u0017\u000e\u001e)sK\u001a\u001cH\u0003BAX\u0003ODq!a\u0007.\u0001\u0004\ty\u0002K\u0003.\u0003K\nY/\t\u0002\u0002n\u0006)\u0001G\f\u001d/c\u0005A1/\u001a;BYBD\u0017\r\u0006\u0003\u00020\u0006M\bbBA\u0017]\u0001\u0007\u0011Q\u0002\u0015\u0006]\u0005\u0015\u00141^\u0001\bg\u0016$8+Z3e)\u0011\ty+a?\t\u000f\u0005]r\u00061\u0001\u0002<!*q&!\u001a\u0002\u0000\u0006\u0012!\u0011A\u0001\u0006c9\u0002d\u0006M\u0001\u000fg\u0016$hj\u001c8oK\u001e\fG/\u001b<f)\u0011\tyKa\u0002\t\u000f\t%\u0001\u00071\u0001\u0002 \u0005\t!\rK\u00031\u0003K\ny,\u0001\u0010tKRLe\u000e^3s[\u0016$\u0017.\u0019;f%\u0012#5\u000b^8sC\u001e,G*\u001a<fYR!\u0011q\u0016B\t\u0011\u001d\u0011\u0019\"\ra\u0001\u0003\u0007\u000bAb\u001d;pe\u0006<W\rT3wK2DS!MA3\u0003\u007f\u000bqc]3u\r&t\u0017\r\u001c*E\tN#xN]1hK2+g/\u001a7\u0015\t\u0005=&1\u0004\u0005\b\u0005'\u0011\u0004\u0019AABQ\u0015\u0011\u0014Q\rB\u0010C\t\u0011\t#A\u00032]Mr\u0003'A\u000btKR\u001c\u0005.Z2la>Lg\u000e^%oi\u0016\u0014h/\u00197\u0015\t\u0005=&q\u0005\u0005\u0007\u0003C\u001b\u0004\u0019\u00016)\u000bM\n)Ga\u000b\"\u0005\t5\u0012!B\u0019/i9\u0002\u0014a\u0001:v]R!!1\u0007B\u001d!\u0011\tyE!\u000e\n\u0007\t]BI\u0001\rNCR\u0014\u0018\u000e\u001f$bGR|'/\u001b>bi&|g.T8eK2DqAa\u000f5\u0001\u0004\u0011i$A\u0004sCRLgnZ:\u0011\r\t}\"Q\tB%\u001b\t\u0011\tEC\u0002\u0003D!\u000b1A\u001d3e\u0013\u0011\u00119E!\u0011\u0003\u0007I#E\t\u0005\u0003\u0002P\t-\u0013b\u0001B'\t\n1!+\u0019;j]\u001eDS\u0001NA3\u0003c\"BAa\r\u0003T!9!1H\u001bA\u0002\tU\u0003C\u0002B,\u0005C\u0012I%\u0004\u0002\u0003Z)!!1\fB/\u0003\u0011Q\u0017M^1\u000b\u0007\t}\u0003*A\u0002ba&LAAa\u0019\u0003Z\t9!*\u0019<b%\u0012#\u0005&B\u001b\u0002f\t}\u0001&\u0002\u0001\u0002f\u0005E\u0014aA!M'B\u0019\u0011qJ\u001c\u0014\t]\u0002&q\u000e\t\u0005\u0005c\u0012I(\u0004\u0002\u0003t)!!Q\u000fB<\u0003\tIwN\u0003\u0002\u0003\\%\u0019\u0001Ma\u001d\u0015\u0005\t-\u0014!\u0002;sC&tGC\u0004B\u001a\u0005\u0003\u0013\u0019I!\"\u0003\b\n%%Q\u0012\u0005\b\u0005wI\u0004\u0019\u0001B\u001f\u0011\u0015Q\u0018\b1\u0001k\u0011\u0015y\u0018\b1\u0001k\u0011\u001d\tI!\u000fa\u0001\u0003\u001bAaAa#:\u0001\u0004Q\u0017A\u00022m_\u000e\\7\u000fC\u0004\u00028e\u0002\r!a\u000f)\u000be\n)G!%\"\u0005\tM\u0015!\u0002\u0019/s9\nD\u0003\u0004B\u001a\u0005/\u0013IJa'\u0003\u001e\n}\u0005b\u0002B\u001eu\u0001\u0007!Q\b\u0005\u0006uj\u0002\rA\u001b\u0005\u0006\u007fj\u0002\rA\u001b\u0005\b\u0003\u0013Q\u0004\u0019AA\u0007\u0011\u0019\u0011YI\u000fa\u0001U\"*!(!\u001a\u0002rQQ!1\u0007BS\u0005O\u0013IKa+\t\u000f\tm2\b1\u0001\u0003>!)!p\u000fa\u0001U\")qp\u000fa\u0001U\"9\u0011\u0011B\u001eA\u0002\u00055\u0001&B\u001e\u0002f\u0005ED\u0003\u0003B\u001a\u0005c\u0013\u0019L!.\t\u000f\tmB\b1\u0001\u0003>!)!\u0010\u0010a\u0001U\")q\u0010\u0010a\u0001U\"*A(!\u001a\u0002r\u0005iAO]1j]&k\u0007\u000f\\5dSR$\u0002Ca\r\u0003>\n}&\u0011\u0019Bb\u0005\u000b\u00149M!3\t\u000f\tmR\b1\u0001\u0003>!)!0\u0010a\u0001U\")q0\u0010a\u0001U\"9\u0011\u0011B\u001fA\u0002\u00055\u0001B\u0002BF{\u0001\u0007!\u000eC\u0004\u0002.u\u0002\r!!\u0004\t\u000f\u0005]R\b1\u0001\u0002<!*Q(!\u001a\u0002lRq!1\u0007Bh\u0005#\u0014\u0019N!6\u0003X\ne\u0007b\u0002B\u001e}\u0001\u0007!Q\b\u0005\u0006uz\u0002\rA\u001b\u0005\u0006\u007fz\u0002\rA\u001b\u0005\b\u0003\u0013q\u0004\u0019AA\u0007\u0011\u0019\u0011YI\u0010a\u0001U\"9\u0011Q\u0006 A\u0002\u00055\u0001&\u0002 \u0002f\u0005-H\u0003\u0004B\u001a\u0005?\u0014\tOa9\u0003f\n\u001d\bb\u0002B\u001e\u007f\u0001\u0007!Q\b\u0005\u0006u~\u0002\rA\u001b\u0005\u0006\u007f~\u0002\rA\u001b\u0005\b\u0003\u0013y\u0004\u0019AA\u0007\u0011\u001d\tic\u0010a\u0001\u0003\u001bASaPA3\u0003W$\u0002Ba\r\u0003n\n=(\u0011\u001f\u0005\b\u0005w\u0001\u0005\u0019\u0001B\u001f\u0011\u0015Q\b\t1\u0001k\u0011\u0015y\b\t1\u0001kQ\u0015\u0001\u0015QMAv\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%qU\u0011!\u0011 \u0016\u0005\u0003w\u0011Yp\u000b\u0002\u0003~B!!q`B\u0004\u001b\t\u0019\tA\u0003\u0003\u0004\u0004\r\u0015\u0011!C;oG\",7m[3e\u0015\r\tYGU\u0005\u0005\u0007\u0013\u0019\tAA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"aa\u0004\u0011\t\rE1qC\u0007\u0003\u0007'QAa!\u0006\u0003x\u0005!A.\u00198h\u0013\u0011\u0019Iba\u0005\u0003\r=\u0013'.Z2uQ\u00159\u0014QMA9Q\u00151\u0014QMA9\u0001"
)
public class ALS implements Serializable, Logging {
   private int numUserBlocks;
   private int numProductBlocks;
   private int rank;
   private int iterations;
   private double lambda;
   private boolean implicitPrefs;
   private double alpha;
   private long seed;
   private boolean nonnegative;
   private StorageLevel intermediateRDDStorageLevel;
   private StorageLevel finalRDDStorageLevel;
   private int checkpointInterval;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static MatrixFactorizationModel trainImplicit(final RDD ratings, final int rank, final int iterations) {
      return ALS$.MODULE$.trainImplicit(ratings, rank, iterations);
   }

   public static MatrixFactorizationModel trainImplicit(final RDD ratings, final int rank, final int iterations, final double lambda, final double alpha) {
      return ALS$.MODULE$.trainImplicit(ratings, rank, iterations, lambda, alpha);
   }

   public static MatrixFactorizationModel trainImplicit(final RDD ratings, final int rank, final int iterations, final double lambda, final int blocks, final double alpha) {
      return ALS$.MODULE$.trainImplicit(ratings, rank, iterations, lambda, blocks, alpha);
   }

   public static MatrixFactorizationModel trainImplicit(final RDD ratings, final int rank, final int iterations, final double lambda, final int blocks, final double alpha, final long seed) {
      return ALS$.MODULE$.trainImplicit(ratings, rank, iterations, lambda, blocks, alpha, seed);
   }

   public static MatrixFactorizationModel train(final RDD ratings, final int rank, final int iterations) {
      return ALS$.MODULE$.train(ratings, rank, iterations);
   }

   public static MatrixFactorizationModel train(final RDD ratings, final int rank, final int iterations, final double lambda) {
      return ALS$.MODULE$.train(ratings, rank, iterations, lambda);
   }

   public static MatrixFactorizationModel train(final RDD ratings, final int rank, final int iterations, final double lambda, final int blocks) {
      return ALS$.MODULE$.train(ratings, rank, iterations, lambda, blocks);
   }

   public static MatrixFactorizationModel train(final RDD ratings, final int rank, final int iterations, final double lambda, final int blocks, final long seed) {
      return ALS$.MODULE$.train(ratings, rank, iterations, lambda, blocks, seed);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private int numUserBlocks() {
      return this.numUserBlocks;
   }

   private void numUserBlocks_$eq(final int x$1) {
      this.numUserBlocks = x$1;
   }

   private int numProductBlocks() {
      return this.numProductBlocks;
   }

   private void numProductBlocks_$eq(final int x$1) {
      this.numProductBlocks = x$1;
   }

   private int rank() {
      return this.rank;
   }

   private void rank_$eq(final int x$1) {
      this.rank = x$1;
   }

   private int iterations() {
      return this.iterations;
   }

   private void iterations_$eq(final int x$1) {
      this.iterations = x$1;
   }

   private double lambda() {
      return this.lambda;
   }

   private void lambda_$eq(final double x$1) {
      this.lambda = x$1;
   }

   private boolean implicitPrefs() {
      return this.implicitPrefs;
   }

   private void implicitPrefs_$eq(final boolean x$1) {
      this.implicitPrefs = x$1;
   }

   private double alpha() {
      return this.alpha;
   }

   private void alpha_$eq(final double x$1) {
      this.alpha = x$1;
   }

   private long seed() {
      return this.seed;
   }

   private void seed_$eq(final long x$1) {
      this.seed = x$1;
   }

   private boolean nonnegative() {
      return this.nonnegative;
   }

   private void nonnegative_$eq(final boolean x$1) {
      this.nonnegative = x$1;
   }

   private StorageLevel intermediateRDDStorageLevel() {
      return this.intermediateRDDStorageLevel;
   }

   private void intermediateRDDStorageLevel_$eq(final StorageLevel x$1) {
      this.intermediateRDDStorageLevel = x$1;
   }

   private StorageLevel finalRDDStorageLevel() {
      return this.finalRDDStorageLevel;
   }

   private void finalRDDStorageLevel_$eq(final StorageLevel x$1) {
      this.finalRDDStorageLevel = x$1;
   }

   private int checkpointInterval() {
      return this.checkpointInterval;
   }

   private void checkpointInterval_$eq(final int x$1) {
      this.checkpointInterval = x$1;
   }

   public ALS setBlocks(final int numBlocks) {
      .MODULE$.require(numBlocks == -1 || numBlocks > 0, () -> "Number of blocks must be -1 or positive but got " + numBlocks);
      this.numUserBlocks_$eq(numBlocks);
      this.numProductBlocks_$eq(numBlocks);
      return this;
   }

   public ALS setUserBlocks(final int numUserBlocks) {
      .MODULE$.require(numUserBlocks == -1 || numUserBlocks > 0, () -> "Number of blocks must be -1 or positive but got " + numUserBlocks);
      this.numUserBlocks_$eq(numUserBlocks);
      return this;
   }

   public ALS setProductBlocks(final int numProductBlocks) {
      .MODULE$.require(numProductBlocks == -1 || numProductBlocks > 0, () -> "Number of product blocks must be -1 or positive but got " + numProductBlocks);
      this.numProductBlocks_$eq(numProductBlocks);
      return this;
   }

   public ALS setRank(final int rank) {
      .MODULE$.require(rank > 0, () -> "Rank of the feature matrices must be positive but got " + rank);
      this.rank_$eq(rank);
      return this;
   }

   public ALS setIterations(final int iterations) {
      .MODULE$.require(iterations >= 0, () -> "Number of iterations must be nonnegative but got " + iterations);
      this.iterations_$eq(iterations);
      return this;
   }

   public ALS setLambda(final double lambda) {
      .MODULE$.require(lambda >= (double)0.0F, () -> "Regularization parameter must be nonnegative but got " + lambda);
      this.lambda_$eq(lambda);
      return this;
   }

   public ALS setImplicitPrefs(final boolean implicitPrefs) {
      this.implicitPrefs_$eq(implicitPrefs);
      return this;
   }

   public ALS setAlpha(final double alpha) {
      this.alpha_$eq(alpha);
      return this;
   }

   public ALS setSeed(final long seed) {
      this.seed_$eq(seed);
      return this;
   }

   public ALS setNonnegative(final boolean b) {
      this.nonnegative_$eq(b);
      return this;
   }

   public ALS setIntermediateRDDStorageLevel(final StorageLevel storageLevel) {
      Predef var10000;
      boolean var10001;
      label17: {
         label16: {
            var10000 = .MODULE$;
            StorageLevel var2 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (storageLevel == null) {
               if (var2 != null) {
                  break label16;
               }
            } else if (!storageLevel.equals(var2)) {
               break label16;
            }

            var10001 = false;
            break label17;
         }

         var10001 = true;
      }

      var10000.require(var10001, () -> "ALS is not designed to run without persisting intermediate RDDs.");
      this.intermediateRDDStorageLevel_$eq(storageLevel);
      return this;
   }

   public ALS setFinalRDDStorageLevel(final StorageLevel storageLevel) {
      this.finalRDDStorageLevel_$eq(storageLevel);
      return this;
   }

   public ALS setCheckpointInterval(final int checkpointInterval) {
      this.checkpointInterval_$eq(checkpointInterval);
      return this;
   }

   public MatrixFactorizationModel run(final RDD ratings) {
      .MODULE$.require(!ratings.isEmpty(), () -> "No ratings available from " + ratings);
      SparkContext sc = ratings.context();
      int numUserBlocks = this.numUserBlocks() == -1 ? scala.math.package..MODULE$.max(sc.defaultParallelism(), ratings.partitions().length / 2) : this.numUserBlocks();
      int numProductBlocks = this.numProductBlocks() == -1 ? scala.math.package..MODULE$.max(sc.defaultParallelism(), ratings.partitions().length / 2) : this.numProductBlocks();
      Tuple2 var7 = org.apache.spark.ml.recommendation.ALS$.MODULE$.train(ratings.map((r) -> new ALS$Rating$mcI$sp(r.user(), r.product(), (float)r.rating()), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.ml.recommendation.ALS.Rating.class)), this.rank(), numUserBlocks, numProductBlocks, this.iterations(), this.lambda(), this.implicitPrefs(), this.alpha(), this.nonnegative(), this.intermediateRDDStorageLevel(), org.apache.spark.storage.StorageLevel..MODULE$.NONE(), this.checkpointInterval(), this.seed(), scala.reflect.ClassTag..MODULE$.Int(), scala.math.Ordering.Int..MODULE$);
      if (var7 == null) {
         throw new MatchError(var7);
      } else {
         RDD userFactors;
         RDD prodFactors;
         label52: {
            RDD floatUserFactors = (RDD)var7._1();
            RDD floatProdFactors = (RDD)var7._2();
            Tuple2 var6 = new Tuple2(floatUserFactors, floatProdFactors);
            RDD floatUserFactors = (RDD)var6._1();
            RDD floatProdFactors = (RDD)var6._2();
            userFactors = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(floatUserFactors, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Float.TYPE)), scala.math.Ordering.Int..MODULE$).mapValues((x$2) -> (double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.floatArrayOps(x$2), (JFunction1.mcDF.sp)(x$3) -> (double)x$3, scala.reflect.ClassTag..MODULE$.Double())).setName("users").persist(this.finalRDDStorageLevel());
            prodFactors = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(floatProdFactors, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Float.TYPE)), scala.math.Ordering.Int..MODULE$).mapValues((x$4) -> (double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.floatArrayOps(x$4), (JFunction1.mcDF.sp)(x$5) -> (double)x$5, scala.reflect.ClassTag..MODULE$.Double())).setName("products").persist(this.finalRDDStorageLevel());
            StorageLevel var10000 = this.finalRDDStorageLevel();
            StorageLevel var14 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (var10000 == null) {
               if (var14 != null) {
                  break label52;
               }
            } else if (!var10000.equals(var14)) {
               break label52;
            }

            BoxedUnit var15 = BoxedUnit.UNIT;
            return new MatrixFactorizationModel(this.rank(), userFactors, prodFactors);
         }

         userFactors.count();
         BoxesRunTime.boxToLong(prodFactors.count());
         return new MatrixFactorizationModel(this.rank(), userFactors, prodFactors);
      }
   }

   public MatrixFactorizationModel run(final JavaRDD ratings) {
      return this.run(ratings.rdd());
   }

   public ALS(final int numUserBlocks, final int numProductBlocks, final int rank, final int iterations, final double lambda, final boolean implicitPrefs, final double alpha, final long seed) {
      this.numUserBlocks = numUserBlocks;
      this.numProductBlocks = numProductBlocks;
      this.rank = rank;
      this.iterations = iterations;
      this.lambda = lambda;
      this.implicitPrefs = implicitPrefs;
      this.alpha = alpha;
      this.seed = seed;
      super();
      Logging.$init$(this);
      this.nonnegative = false;
      this.intermediateRDDStorageLevel = org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK();
      this.finalRDDStorageLevel = org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK();
      this.checkpointInterval = 10;
   }

   public ALS() {
      this(-1, -1, 10, 10, 0.01, false, (double)1.0F, ALS$.MODULE$.org$apache$spark$mllib$recommendation$ALS$$$lessinit$greater$default$8());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
