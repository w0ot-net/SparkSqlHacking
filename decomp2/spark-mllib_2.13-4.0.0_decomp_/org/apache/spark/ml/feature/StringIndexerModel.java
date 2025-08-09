package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkException;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.attribute.NominalAttribute$;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.collection.OpenHashMap;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\red\u0001B\u001f?\u0001%C\u0001\"\u0017\u0001\u0003\u0006\u0004%\tE\u0017\u0005\tc\u0002\u0011\t\u0011)A\u00057\"A1\u000f\u0001BC\u0002\u0013\u0005A\u000f\u0003\u0005~\u0001\t\u0005\t\u0015!\u0003v\u0011\u0019y\b\u0001\"\u0001\u0002\u0002!1q\u0010\u0001C\u0001\u0003\u0017Aaa \u0001\u0005\u0002\u0005e\u0001BB@\u0001\t\u0003\ty\u0002C\u0004\u0000\u0001\u0011\u0005\u0001)!\n\t\u000f\u0005E\u0001\u0001\"\u0001\u0002(!I\u00111\b\u0001C\u0002\u0013%\u0011Q\b\u0005\t\u0003+\u0002\u0001\u0015!\u0003\u0002@!9\u0011q\u000b\u0001\u0005\u0002\u0005e\u0003bBA4\u0001\u0011\u0005\u0011\u0011\u000e\u0005\b\u0003_\u0002A\u0011AA9\u0011\u001d\t9\b\u0001C\u0001\u0003sBq!a \u0001\t\u0003\t\t\tC\u0004\u0002\b\u0002!I!!#\t\u000f\u0005M\u0007\u0001\"\u0003\u0002V\"9\u0011\u0011\u001e\u0001\u0005B\u0005-\bb\u0002B\u000e\u0001\u0011\u0005#Q\u0004\u0005\b\u0005c\u0001A\u0011\tB\u001a\u0011\u001d\u0011Y\u0005\u0001C!\u0005\u001bBqAa@\u0001\t\u0003\u001a\u0019hB\u0004\u0003TyB\tA!\u0016\u0007\rur\u0004\u0012\u0001B,\u0011\u0019y(\u0004\"\u0001\u0003v\u00199!q\u000f\u000e\u00015\te\u0004\"\u0003BA9\t\u0005\t\u0015!\u0003O\u0011\u0019yH\u0004\"\u0001\u0003\u0004\u001a1!1\u0012\u000fE\u0005\u001bC\u0001b]\u0010\u0003\u0016\u0004%\t\u0001\u001e\u0005\t{~\u0011\t\u0012)A\u0005k\"1qp\bC\u0001\u00053C\u0011B!\r \u0003\u0003%\tA!)\t\u0013\t\u0015v$%A\u0005\u0002\t\u001d\u0006\"\u0003B^?\u0005\u0005I\u0011\tB_\u0011%\u0011ImHA\u0001\n\u0003\u0011Y\rC\u0005\u0003T~\t\t\u0011\"\u0001\u0003V\"I!1\\\u0010\u0002\u0002\u0013\u0005#Q\u001c\u0005\n\u0005S|\u0012\u0011!C\u0001\u0005WD\u0011B!> \u0003\u0003%\tEa>\t\u0013\tmx$!A\u0005B\tu\b\"\u0003B\u0000?\u0005\u0005I\u0011IB\u0001\u0011%\u0019\u0019aHA\u0001\n\u0003\u001a)aB\u0005\u0004\nq\t\t\u0011#\u0003\u0004\f\u0019I!1\u0012\u000f\u0002\u0002#%1Q\u0002\u0005\u0007\u007f>\"\taa\u0007\t\u0013\t}x&!A\u0005F\r\u0005\u0001\"CB\u000f_\u0005\u0005I\u0011QB\u0010\u0011%\u0019\u0019cLA\u0001\n\u0003\u001b)\u0003C\u0004\u00042q!\tfa\r\u0007\r\r}\"\u0004BB!\u0011\u0019yX\u0007\"\u0001\u0004J!I1QJ\u001bC\u0002\u0013%!Q\u0018\u0005\t\u0007\u001f*\u0004\u0015!\u0003\u0003@\"91\u0011K\u001b\u0005B\rM\u0003bBB,5\u0011\u00053\u0011\f\u0005\b\u0007#RB\u0011IB/\u0011%\u0019\u0019GGA\u0001\n\u0013\u0019)G\u0001\nTiJLgnZ%oI\u0016DXM]'pI\u0016d'BA A\u0003\u001d1W-\u0019;ve\u0016T!!\u0011\"\u0002\u00055d'BA\"E\u0003\u0015\u0019\b/\u0019:l\u0015\t)e)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u000f\u0006\u0019qN]4\u0004\u0001M!\u0001A\u0013)T!\rYEJT\u0007\u0002\u0001&\u0011Q\n\u0011\u0002\u0006\u001b>$W\r\u001c\t\u0003\u001f\u0002i\u0011A\u0010\t\u0003\u001fFK!A\u0015 \u0003#M#(/\u001b8h\u0013:$W\r_3s\u0005\u0006\u001cX\r\u0005\u0002U/6\tQK\u0003\u0002W\u0001\u0006!Q\u000f^5m\u0013\tAVK\u0001\u0006N\u0019^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005Y\u0006C\u0001/f\u001d\ti6\r\u0005\u0002_C6\tqL\u0003\u0002a\u0011\u00061AH]8pizR\u0011AY\u0001\u0006g\u000e\fG.Y\u0005\u0003I\u0006\fa\u0001\u0015:fI\u00164\u0017B\u00014h\u0005\u0019\u0019FO]5oO*\u0011A-\u0019\u0015\u0004\u0003%|\u0007C\u00016n\u001b\u0005Y'B\u00017C\u0003)\tgN\\8uCRLwN\\\u0005\u0003].\u0014QaU5oG\u0016\f\u0013\u0001]\u0001\u0006c9\"d\u0006M\u0001\u0005k&$\u0007\u0005K\u0002\u0003S>\f1\u0002\\1cK2\u001c\u0018I\u001d:bsV\tQ\u000fE\u0002wofl\u0011!Y\u0005\u0003q\u0006\u0014Q!\u0011:sCf\u00042A^<\\Q\r\u0019\u0011n_\u0011\u0002y\u0006)1G\f\u0019/a\u0005aA.\u00192fYN\f%O]1zA!\u001aA![>\u0002\rqJg.\u001b;?)\u0015q\u00151AA\u0004\u0011\u0015IV\u00011\u0001\\Q\u0011\t\u0019![8\t\u000bM,\u0001\u0019A;)\t\u0005\u001d\u0011n\u001f\u000b\u0006\u001d\u00065\u0011q\u0002\u0005\u00063\u001a\u0001\ra\u0017\u0005\u0007\u0003#1\u0001\u0019A=\u0002\r1\f'-\u001a7tQ\u00111\u0011.!\u0006\"\u0005\u0005]\u0011!B\u0019/k9\u0002Dc\u0001(\u0002\u001c!1\u0011\u0011C\u0004A\u0002eDCaB5\u0002\u0016Q\u0019a*!\t\t\u000bMD\u0001\u0019A;)\u0007!I7\u0010F\u0001O+\u0005I\bF\u0003\u0006\u0002,\u0005E\u00121GA\u001cwB\u0019a/!\f\n\u0007\u0005=\u0012M\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-\t\u0002\u00026\u0005y\u0005\r\\1cK2\u001c\b\rI5tA\u0011,\u0007O]3dCR,G\rI1oI\u0002:\u0018\u000e\u001c7!E\u0016\u0004#/Z7pm\u0016$\u0007%\u001b8!g9\nd\u0006\r\u0018!+N,\u0007\u0005\u00197bE\u0016d7/\u0011:sCf\u0004\u0007%\u001b8ti\u0016\fGML\u0001\u0006g&t7-\u001a\u0015\u0005\u0015%\f)\"\u0001\nmC\n,Gn\u001d+p\u0013:$W\r_!se\u0006LXCAA !\u00111x/!\u0011\u0011\u000f\u0005\r\u00131J.\u0002P5\u0011\u0011Q\t\u0006\u0005\u0003\u000f\nI%\u0001\u0006d_2dWm\u0019;j_:T!A\u0016\"\n\t\u00055\u0013Q\t\u0002\f\u001fB,g\u000eS1tQ6\u000b\u0007\u000fE\u0002w\u0003#J1!a\u0015b\u0005\u0019!u.\u001e2mK\u0006\u0019B.\u00192fYN$v.\u00138eKb\f%O]1zA\u0005\u00012/\u001a;IC:$G.Z%om\u0006d\u0017\u000e\u001a\u000b\u0005\u00037\ni&D\u0001\u0001\u0011\u0019\ty&\u0004a\u00017\u0006)a/\u00197vK\"\"Q\"[A2C\t\t)'A\u00032]Yr\u0003'A\u0006tKRLe\u000e];u\u0007>dG\u0003BA.\u0003WBa!a\u0018\u000f\u0001\u0004Y\u0006f\u0001\bj_\u0006a1/\u001a;PkR\u0004X\u000f^\"pYR!\u00111LA:\u0011\u0019\tyf\u0004a\u00017\"\u001aq\"[8\u0002\u0019M,G/\u00138qkR\u001cu\u000e\\:\u0015\t\u0005m\u00131\u0010\u0005\u0007\u0003?\u0002\u0002\u0019A=)\u0007AI70A\u0007tKR|U\u000f\u001e9vi\u000e{Gn\u001d\u000b\u0005\u00037\n\u0019\t\u0003\u0004\u0002`E\u0001\r!\u001f\u0015\u0004#%\\\u0018!\u00054jYR,'/\u00138wC2LG\rR1uCR1\u00111RAX\u0003{\u0003D!!$\u0002\u001eB1\u0011qRAK\u00033k!!!%\u000b\u0007\u0005M%)A\u0002tc2LA!a&\u0002\u0012\n9A)\u0019;bg\u0016$\b\u0003BAN\u0003;c\u0001\u0001B\u0006\u0002 J\t\t\u0011!A\u0003\u0002\u0005\u0005&aA0%mE!\u00111UAU!\r1\u0018QU\u0005\u0004\u0003O\u000b'a\u0002(pi\"Lgn\u001a\t\u0004m\u0006-\u0016bAAWC\n\u0019\u0011I\\=\t\u000f\u0005E&\u00031\u0001\u00024\u00069A-\u0019;bg\u0016$\b\u0007BA[\u0003s\u0003b!a$\u0002\u0016\u0006]\u0006\u0003BAN\u0003s#A\"a/\u00020\u0006\u0005\t\u0011!B\u0001\u0003C\u00131a\u0018\u00136\u0011\u001d\tyL\u0005a\u0001\u0003\u0003\fQ\"\u001b8qkR\u001cu\u000e\u001c(b[\u0016\u001c\b#BAb\u0003\u001b\\f\u0002BAc\u0003\u0013t1AXAd\u0013\u0005\u0011\u0017bAAfC\u00069\u0001/Y2lC\u001e,\u0017\u0002BAh\u0003#\u00141aU3r\u0015\r\tY-Y\u0001\u000bO\u0016$\u0018J\u001c3fq\u0016\u0014HCBAl\u0003G\f)\u000f\u0005\u0003\u0002Z\u0006}WBAAn\u0015\u0011\ti.!%\u0002\u0017\u0015D\bO]3tg&|gn]\u0005\u0005\u0003C\fYNA\nVg\u0016\u0014H)\u001a4j]\u0016$g)\u001e8di&|g\u000eC\u0004\u0002\u0012M\u0001\r!!1\t\u000f\u0005\u001d8\u00031\u0001\u0002B\u0005aA.\u00192fYR{\u0017J\u001c3fq\u0006IAO]1og\u001a|'/\u001c\u000b\u0005\u0003[\u0014I\u0001\u0005\u0003\u0002p\n\ra\u0002BAy\u0005\u0003qA!a=\u0002\u0000:!\u0011Q_A\u007f\u001d\u0011\t90a?\u000f\u0007y\u000bI0C\u0001H\u0013\t)e)\u0003\u0002D\t&\u0019\u00111\u0013\"\n\t\u0005-\u0017\u0011S\u0005\u0005\u0005\u000b\u00119AA\u0005ECR\fgI]1nK*!\u00111ZAI\u0011\u001d\t\t\f\u0006a\u0001\u0005\u0017\u0001DA!\u0004\u0003\u0012A1\u0011qRAK\u0005\u001f\u0001B!a'\u0003\u0012\u0011a!1\u0003B\u0005\u0003\u0003\u0005\tQ!\u0001\u0002\"\n\u0019q\fJ\u001c)\tQI'qC\u0011\u0003\u00053\tQA\r\u00181]A\nq\u0002\u001e:b]N4wN]7TG\",W.\u0019\u000b\u0005\u0005?\u0011Y\u0003\u0005\u0003\u0003\"\t\u001dRB\u0001B\u0012\u0015\u0011\u0011)#!%\u0002\u000bQL\b/Z:\n\t\t%\"1\u0005\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007b\u0002B\u0017+\u0001\u0007!qD\u0001\u0007g\u000eDW-\\1)\u0007UIw.\u0001\u0003d_BLHc\u0001(\u00036!9!q\u0007\fA\u0002\te\u0012!B3yiJ\f\u0007\u0003\u0002B\u001e\u0005\u0003j!A!\u0010\u000b\u0007\t}\u0002)A\u0003qCJ\fW.\u0003\u0003\u0003D\tu\"\u0001\u0003)be\u0006lW*\u00199)\tYI'qI\u0011\u0003\u0005\u0013\nQ!\r\u00185]E\nQa\u001e:ji\u0016,\"Aa\u0014\u0011\u0007\tECD\u0004\u0002P3\u0005\u00112\u000b\u001e:j]\u001eLe\u000eZ3yKJlu\u000eZ3m!\ty%dE\u0004\u001b\u00053\u0012yF!\u001a\u0011\u0007Y\u0014Y&C\u0002\u0003^\u0005\u0014a!\u00118z%\u00164\u0007\u0003\u0002+\u0003b9K1Aa\u0019V\u0005)iEJU3bI\u0006\u0014G.\u001a\t\u0005\u0005O\u0012\t(\u0004\u0002\u0003j)!!1\u000eB7\u0003\tIwN\u0003\u0002\u0003p\u0005!!.\u0019<b\u0013\u0011\u0011\u0019H!\u001b\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\tU#AF*ue&tw-\u00138eKblu\u000eZ3m/JLG/\u001a:\u0014\u0007q\u0011Y\bE\u0002U\u0005{J1Aa V\u0005!iEj\u0016:ji\u0016\u0014\u0018\u0001C5ogR\fgnY3\u0015\t\t\u0015%\u0011\u0012\t\u0004\u0005\u000fcR\"\u0001\u000e\t\r\t\u0005e\u00041\u0001O\u0005\u0011!\u0015\r^1\u0014\u000f}\u0011IFa$\u0003\u0016B\u0019aO!%\n\u0007\tM\u0015MA\u0004Qe>$Wo\u0019;\u0011\t\u0005\r'qS\u0005\u0005\u0005g\n\t\u000e\u0006\u0003\u0003\u001c\n}\u0005c\u0001BO?5\tA\u0004C\u0003tE\u0001\u0007Q\u000f\u0006\u0003\u0003\u001c\n\r\u0006bB:$!\u0003\u0005\r!^\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\u0011IKK\u0002v\u0005W[#A!,\u0011\t\t=&qW\u0007\u0003\u0005cSAAa-\u00036\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003Y\u0006LAA!/\u00032\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\u0011y\f\u0005\u0003\u0003B\n\u001dWB\u0001Bb\u0015\u0011\u0011)M!\u001c\u0002\t1\fgnZ\u0005\u0004M\n\r\u0017\u0001\u00049s_\u0012,8\r^!sSRLXC\u0001Bg!\r1(qZ\u0005\u0004\u0005#\f'aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAU\u0005/D\u0011B!7(\u0003\u0003\u0005\rA!4\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011y\u000e\u0005\u0004\u0003b\n\u0015\u0018\u0011V\u0007\u0003\u0005GT1!a\u0012b\u0013\u0011\u00119Oa9\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0005[\u0014\u0019\u0010E\u0002w\u0005_L1A!=b\u0005\u001d\u0011un\u001c7fC:D\u0011B!7*\u0003\u0003\u0005\r!!+\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005\u007f\u0013I\u0010C\u0005\u0003Z*\n\t\u00111\u0001\u0003N\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0003N\u0006AAo\\*ue&tw\r\u0006\u0002\u0003@\u00061Q-];bYN$BA!<\u0004\b!I!\u0011\\\u0017\u0002\u0002\u0003\u0007\u0011\u0011V\u0001\u0005\t\u0006$\u0018\rE\u0002\u0003\u001e>\u001aRaLB\b\u0005K\u0002ra!\u0005\u0004\u0018U\u0014Y*\u0004\u0002\u0004\u0014)\u00191QC1\u0002\u000fI,h\u000e^5nK&!1\u0011DB\n\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\u000b\u0003\u0007\u0017\tQ!\u00199qYf$BAa'\u0004\"!)1O\ra\u0001k\u00069QO\\1qa2LH\u0003BB\u0014\u0007[\u0001BA^B\u0015k&\u001911F1\u0003\r=\u0003H/[8o\u0011%\u0019ycMA\u0001\u0002\u0004\u0011Y*A\u0002yIA\n\u0001b]1wK&k\u0007\u000f\u001c\u000b\u0005\u0007k\u0019Y\u0004E\u0002w\u0007oI1a!\u000fb\u0005\u0011)f.\u001b;\t\r\ruB\u00071\u0001\\\u0003\u0011\u0001\u0018\r\u001e5\u00031M#(/\u001b8h\u0013:$W\r_3s\u001b>$W\r\u001c*fC\u0012,'oE\u00026\u0007\u0007\u0002B\u0001VB#\u001d&\u00191qI+\u0003\u00115c%+Z1eKJ$\"aa\u0013\u0011\u0007\t\u001dU'A\u0005dY\u0006\u001c8OT1nK\u0006Q1\r\\1tg:\u000bW.\u001a\u0011\u0002\t1|\u0017\r\u001a\u000b\u0004\u001d\u000eU\u0003BBB\u001fs\u0001\u00071,\u0001\u0003sK\u0006$WCAB\"Q\u0011Q\u0014.a\u0019\u0015\u00079\u001by\u0006\u0003\u0004\u0004>m\u0002\ra\u0017\u0015\u0005w%\f\u0019'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0004hA!!\u0011YB5\u0013\u0011\u0019YGa1\u0003\r=\u0013'.Z2uQ\u0011Q\u0012.a\u0019)\teI\u00171\r\u0015\u0005/%\f\u0019\u0007F\u0001\\Q\rA\u0012n\u001f\u0015\u0004\u0001%|\u0007"
)
public class StringIndexerModel extends Model implements StringIndexerBase, MLWritable {
   private final String uid;
   private final String[][] labelsArray;
   private final OpenHashMap[] labelsToIndexArray;
   private Param handleInvalid;
   private Param stringOrderType;
   private StringArrayParam outputCols;
   private StringArrayParam inputCols;
   private Param outputCol;
   private Param inputCol;

   public static StringIndexerModel load(final String path) {
      return StringIndexerModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return StringIndexerModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getStringOrderType() {
      return StringIndexerBase.getStringOrderType$(this);
   }

   public Tuple2 getInOutCols() {
      return StringIndexerBase.getInOutCols$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean skipNonExistsCol) {
      return StringIndexerBase.validateAndTransformSchema$(this, schema, skipNonExistsCol);
   }

   public boolean validateAndTransformSchema$default$2() {
      return StringIndexerBase.validateAndTransformSchema$default$2$(this);
   }

   public final String[] getOutputCols() {
      return HasOutputCols.getOutputCols$(this);
   }

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final String getHandleInvalid() {
      return HasHandleInvalid.getHandleInvalid$(this);
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public final Param stringOrderType() {
      return this.stringOrderType;
   }

   public void org$apache$spark$ml$feature$StringIndexerBase$_setter_$handleInvalid_$eq(final Param x$1) {
      this.handleInvalid = x$1;
   }

   public final void org$apache$spark$ml$feature$StringIndexerBase$_setter_$stringOrderType_$eq(final Param x$1) {
      this.stringOrderType = x$1;
   }

   public final StringArrayParam outputCols() {
      return this.outputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCols$_setter_$outputCols_$eq(final StringArrayParam x$1) {
      this.outputCols = x$1;
   }

   public final StringArrayParam inputCols() {
      return this.inputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(final StringArrayParam x$1) {
      this.inputCols = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
   }

   public String uid() {
      return this.uid;
   }

   public String[][] labelsArray() {
      return this.labelsArray;
   }

   /** @deprecated */
   public String[] labels() {
      .MODULE$.require(this.labelsArray().length == 1, () -> "This StringIndexerModel is fit on multiple columns. Call `labelsArray` instead.");
      return this.labelsArray()[0];
   }

   private OpenHashMap[] labelsToIndexArray() {
      return this.labelsToIndexArray;
   }

   public StringIndexerModel setHandleInvalid(final String value) {
      return (StringIndexerModel)this.set(this.handleInvalid(), value);
   }

   public StringIndexerModel setInputCol(final String value) {
      return (StringIndexerModel)this.set(this.inputCol(), value);
   }

   public StringIndexerModel setOutputCol(final String value) {
      return (StringIndexerModel)this.set(this.outputCol(), value);
   }

   public StringIndexerModel setInputCols(final String[] value) {
      return (StringIndexerModel)this.set(this.inputCols(), value);
   }

   public StringIndexerModel setOutputCols(final String[] value) {
      return (StringIndexerModel)this.set(this.outputCols(), value);
   }

   private Dataset filterInvalidData(final Dataset dataset, final Seq inputColNames) {
      Seq conditions = inputColNames.indices().map((i) -> $anonfun$filterInvalidData$1(this, inputColNames, dataset, BoxesRunTime.unboxToInt(i)));
      return dataset.na().drop((Seq)inputColNames.filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$filterInvalidData$3(dataset, x$4)))).where((Column)conditions.reduce((x$5, x$6) -> x$5.and(x$6)));
   }

   private UserDefinedFunction getIndexer(final Seq labels, final OpenHashMap labelToIndex) {
      boolean var7;
      label17: {
         label16: {
            String var10000 = this.getHandleInvalid();
            String var4 = StringIndexer$.MODULE$.KEEP_INVALID();
            if (var10000 == null) {
               if (var4 == null) {
                  break label16;
               }
            } else if (var10000.equals(var4)) {
               break label16;
            }

            var7 = false;
            break label17;
         }

         var7 = true;
      }

      boolean keepInvalid = var7;
      functions var8 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (label) -> BoxesRunTime.boxToDouble($anonfun$getIndexer$1(keepInvalid, labels, labelToIndex, label));
      TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(StringIndexerModel.class.getClassLoader());

      final class $typecreator1$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$2() {
         }
      }

      return var8.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).asNondeterministic();
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      Tuple2 var4 = this.getInOutCols();
      if (var4 == null) {
         throw new MatchError(var4);
      } else {
         String[] inputColNames;
         String[] outputColNames;
         Column[] outputColumns;
         Dataset var14;
         label31: {
            label30: {
               String[] inputColNames = (String[])var4._1();
               String[] outputColNames = (String[])var4._2();
               Tuple2 var3 = new Tuple2(inputColNames, outputColNames);
               inputColNames = (String[])var3._1();
               outputColNames = (String[])var3._2();
               outputColumns = new Column[outputColNames.length];
               String var10000 = this.getHandleInvalid();
               String var11 = StringIndexer$.MODULE$.SKIP_INVALID();
               if (var10000 == null) {
                  if (var11 == null) {
                     break label30;
                  }
               } else if (var10000.equals(var11)) {
                  break label30;
               }

               var14 = dataset;
               break label31;
            }

            var14 = this.filterInvalidData(dataset, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(inputColNames).toImmutableArraySeq());
         }

         Dataset filteredDataset = var14;
         scala.collection.ArrayOps..MODULE$.indices$extension(.MODULE$.refArrayOps((Object[])outputColNames)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            String inputColName = inputColNames[i];
            String outputColName = outputColNames[i];
            OpenHashMap labelToIndex = this.labelsToIndexArray()[i];
            String[] labels = this.labelsArray()[i];

            try {
               String[] var17;
               label24: {
                  label23: {
                     dataset.col(inputColName);
                     String var12 = this.getHandleInvalid();
                     var17 = StringIndexer$.MODULE$.KEEP_INVALID();
                     if (var17 == null) {
                        if (var12 == null) {
                           break label23;
                        }
                     } else if (var17.equals(var12)) {
                        break label23;
                     }

                     var17 = labels;
                     break label24;
                  }

                  var17 = (String[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.refArrayOps((Object[])labels), "__unknown", scala.reflect.ClassTag..MODULE$.apply(String.class));
               }

               String[] filteredLabels = var17;
               Metadata metadata = NominalAttribute$.MODULE$.defaultAttr().withName(outputColName).withValues(filteredLabels).toMetadata();
               UserDefinedFunction indexer = this.getIndexer(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(labels).toImmutableArraySeq(), labelToIndex);
               outputColumns[i] = indexer.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{dataset.apply(inputColName).cast(org.apache.spark.sql.types.StringType..MODULE$)}))).as(outputColName, metadata);
            } catch (AnalysisException var16) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Input column ", " does not exist "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COLUMN_NAME..MODULE$, inputColName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"during transformation. Skip StringIndexerModel for this column."})))).log(scala.collection.immutable.Nil..MODULE$))));
               outputColNames[i] = null;
            }

         });
         String[] filteredOutputColNames = (String[])scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps((Object[])outputColNames), (x$8) -> BoxesRunTime.boxToBoolean($anonfun$transform$3(x$8)));
         Column[] filteredOutputColumns = (Column[])scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps((Object[])outputColumns), (x$9) -> BoxesRunTime.boxToBoolean($anonfun$transform$4(x$9)));
         .MODULE$.require(filteredOutputColNames.length == filteredOutputColumns.length);
         return filteredOutputColNames.length > 0 ? filteredDataset.withColumns(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(filteredOutputColNames).toImmutableArraySeq(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(filteredOutputColumns).toImmutableArraySeq()) : filteredDataset.toDF();
      }
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema, true);
   }

   public StringIndexerModel copy(final ParamMap extra) {
      StringIndexerModel copied = new StringIndexerModel(this.uid(), this.labelsArray());
      return (StringIndexerModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public StringIndexModelWriter write() {
      return new StringIndexModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "StringIndexerModel: uid=" + var10000 + ", handleInvalid=" + this.$(this.handleInvalid()) + this.get(this.stringOrderType()).map((t) -> ", stringOrderType=" + t).getOrElse(() -> "") + this.get(this.inputCols()).map((c) -> ", numInputCols=" + c.length).getOrElse(() -> "") + this.get(this.outputCols()).map((c) -> ", numOutputCols=" + c.length).getOrElse(() -> "");
   }

   // $FF: synthetic method
   public static final void $anonfun$labelsToIndexArray$2(final OpenHashMap map$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String label = (String)x0$1._1();
         int idx = x0$1._2$mcI$sp();
         map$1.update$mcD$sp(label, (double)idx);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filterInvalidData$2(final OpenHashMap labelToIndex$1, final String label) {
      return labelToIndex$1.contains(label);
   }

   // $FF: synthetic method
   public static final Column $anonfun$filterInvalidData$1(final StringIndexerModel $this, final Seq inputColNames$1, final Dataset dataset$2, final int i) {
      String inputColName = (String)inputColNames$1.apply(i);
      OpenHashMap labelToIndex = $this.labelsToIndexArray()[i];
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (label) -> BoxesRunTime.boxToBoolean($anonfun$filterInvalidData$2(labelToIndex, label));
      TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Boolean();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(StringIndexerModel.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$1() {
         }
      }

      UserDefinedFunction filter = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()));
      return filter.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{dataset$2.apply(inputColName)})));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filterInvalidData$3(final Dataset dataset$2, final String x$4) {
      return scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps((Object[])dataset$2.schema().fieldNames()), x$4);
   }

   // $FF: synthetic method
   public static final double $anonfun$getIndexer$1(final boolean keepInvalid$1, final Seq labels$1, final OpenHashMap labelToIndex$2, final String label) {
      if (label == null) {
         if (keepInvalid$1) {
            return (double)labels$1.length();
         } else {
            throw new SparkException("StringIndexer encountered NULL value. To handle or skip NULLS, try setting StringIndexer.handleInvalid.");
         }
      } else if (labelToIndex$2.contains(label)) {
         return labelToIndex$2.apply$mcD$sp(label);
      } else if (keepInvalid$1) {
         return (double)labels$1.length();
      } else {
         throw new SparkException("Unseen label: " + label + ". To handle unseen labels, set Param handleInvalid to " + StringIndexer$.MODULE$.KEEP_INVALID() + ".");
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transform$3(final String x$8) {
      return x$8 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transform$4(final Column x$9) {
      return x$9 != null;
   }

   public StringIndexerModel(final String uid, final String[][] labelsArray) {
      this.uid = uid;
      this.labelsArray = labelsArray;
      HasHandleInvalid.$init$(this);
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasInputCols.$init$(this);
      HasOutputCols.$init$(this);
      StringIndexerBase.$init$(this);
      MLWritable.$init$(this);
      this.labelsToIndexArray = (OpenHashMap[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])labelsArray), (labels) -> {
         int n = labels.length;
         OpenHashMap map = new OpenHashMap.mcD.sp(n, scala.reflect.ClassTag..MODULE$.apply(String.class), scala.reflect.ClassTag..MODULE$.Double());
         scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(.MODULE$.refArrayOps((Object[])labels))), (x0$1) -> {
            $anonfun$labelsToIndexArray$2(map, x0$1);
            return BoxedUnit.UNIT;
         });
         return map;
      }, scala.reflect.ClassTag..MODULE$.apply(OpenHashMap.class));
      Statics.releaseFence();
   }

   public StringIndexerModel(final String uid, final String[] labels) {
      this(uid, (String[][])((Object[])(new String[][]{labels})));
   }

   public StringIndexerModel(final String[] labels) {
      this(Identifiable$.MODULE$.randomUID("strIdx"), (String[][])((Object[])(new String[][]{labels})));
   }

   public StringIndexerModel(final String[][] labelsArray) {
      this(Identifiable$.MODULE$.randomUID("strIdx"), labelsArray);
   }

   public StringIndexerModel() {
      this("", (String[][])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(String.class))));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class StringIndexModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final StringIndexerModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.labelsArray());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(StringIndexModelWriter.class.getClassLoader());

         final class $typecreator1$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.StringIndexerModel.StringIndexModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.StringIndexerModel.StringIndexModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$3() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3())).write().parquet(dataPath);
      }

      private final void Data$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Data$module == null) {
               this.Data$module = new Data$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public StringIndexModelWriter(final StringIndexerModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final String[][] labelsArray;
         // $FF: synthetic field
         public final StringIndexModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public String[][] labelsArray() {
            return this.labelsArray;
         }

         public Data copy(final String[][] labelsArray) {
            return this.org$apache$spark$ml$feature$StringIndexerModel$StringIndexModelWriter$Data$$$outer().new Data(labelsArray);
         }

         public String[][] copy$default$1() {
            return this.labelsArray();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 1;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.labelsArray();
               }
               default -> {
                  return Statics.ioobe(x$1);
               }
            }
         }

         public Iterator productIterator() {
            return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
         }

         public boolean canEqual(final Object x$1) {
            return x$1 instanceof Data;
         }

         public String productElementName(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return "labelsArray";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var10000;
            if (this != x$1) {
               label41: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$StringIndexerModel$StringIndexModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$StringIndexerModel$StringIndexModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.labelsArray() == var4.labelsArray() && var4.canEqual(this)) {
                        break label41;
                     }
                  }

                  var10000 = false;
                  return var10000;
               }
            }

            var10000 = true;
            return var10000;
         }

         // $FF: synthetic method
         public StringIndexModelWriter org$apache$spark$ml$feature$StringIndexerModel$StringIndexModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final String[][] labelsArray) {
            this.labelsArray = labelsArray;
            if (StringIndexModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = StringIndexModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final StringIndexModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final String[][] labelsArray) {
            return this.$outer.new Data(labelsArray);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.labelsArray()));
         }

         public Data$() {
            if (StringIndexModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = StringIndexModelWriter.this;
               super();
            }
         }
      }
   }

   private static class StringIndexerModelReader extends MLReader {
      private final String className = StringIndexerModel.class.getName();

      private String className() {
         return this.className;
      }

      public StringIndexerModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Tuple2 var6 = org.apache.spark.util.VersionUtils..MODULE$.majorMinorVersion(metadata.sparkVersion());
         if (var6 != null) {
            int majorVersion = var6._1$mcI$sp();
            int minorVersion = var6._2$mcI$sp();
            Tuple2.mcII.sp var5 = new Tuple2.mcII.sp(majorVersion, minorVersion);
            int majorVersion = ((Tuple2)var5)._1$mcI$sp();
            int var10 = ((Tuple2)var5)._2$mcI$sp();
            String[][] var10000;
            if (majorVersion < 3) {
               Row data = (Row)this.sparkSession().read().parquet(dataPath).select("labels", scala.collection.immutable.Nil..MODULE$).head();
               String[] labels = (String[])((IterableOnceOps)data.getAs(0)).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
               var10000 = (String[][])((Object[])(new String[][]{labels}));
            } else {
               Row data = (Row)this.sparkSession().read().parquet(dataPath).select("labelsArray", scala.collection.immutable.Nil..MODULE$).head();
               var10000 = (String[][])((IterableOnceOps)data.getSeq(0).map((x$11) -> (String[])x$11.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)))).toArray(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(String.class)));
            }

            String[][] labelsArray = var10000;
            StringIndexerModel model = new StringIndexerModel(metadata.uid(), labelsArray);
            metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
            return model;
         } else {
            throw new MatchError(var6);
         }
      }

      public StringIndexerModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
