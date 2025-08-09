package org.apache.spark.ml.regression;

import breeze.stats.distributions.Binomial;
import breeze.stats.distributions.Gamma;
import breeze.stats.distributions.Gaussian;
import breeze.stats.distributions.Poisson;
import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.SparkException;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.feature.OffsetInstance;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.optim.IterativelyReweightedLeastSquares;
import org.apache.spark.ml.optim.IterativelyReweightedLeastSquaresModel;
import org.apache.spark.ml.optim.WeightedLeastSquares;
import org.apache.spark.ml.optim.WeightedLeastSquares$;
import org.apache.spark.ml.optim.WeightedLeastSquaresModel;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.OptionalInstrumentation;
import org.apache.spark.ml.util.OptionalInstrumentation$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019\u0005daBAR\u0003K\u0003\u00111\u0018\u0005\u000b\u0003o\u0004!Q1A\u0005B\u0005e\bB\u0003B\u0014\u0001\t\u0005\t\u0015!\u0003\u0002|\"9!1\u0006\u0001\u0005\u0002\t5\u0002b\u0002B\u0016\u0001\u0011\u0005!Q\u0007\u0005\b\u0005s\u0001A\u0011\u0001B\u001e\u0011\u001d\u0011)\u0005\u0001C\u0001\u0005\u000fBqA!\u0017\u0001\t\u0003\u0011Y\u0006C\u0004\u0003b\u0001!\tAa\u0019\t\u000f\t%\u0004\u0001\"\u0001\u0003l!9!q\u000f\u0001\u0005\u0002\te\u0004b\u0002BC\u0001\u0011\u0005!q\u0011\u0005\b\u0005\u001b\u0003A\u0011\u0001BH\u0011\u001d\u0011)\n\u0001C\u0001\u0005/CqA!(\u0001\t\u0003\u0011y\nC\u0004\u0003*\u0002!\tAa+\t\u000f\tE\u0006\u0001\"\u0001\u00034\"9!\u0011\u0018\u0001\u0005\u0002\tm\u0006b\u0002Bc\u0001\u0011E#q\u0019\u0005\b\u0005c\u0004A\u0011\tBz\u000f!\u0019I!!*\t\u0002\r-a\u0001CAR\u0003KC\ta!\u0004\t\u000f\t-R\u0003\"\u0001\u0004,!91QF\u000b\u0005B\r=\u0002\u0002DB\u001c+!\u0015\r\u0011\"\u0001\u0002&\u000ee\u0002bCBn+\t\u0007I\u0011AAS\u0007;D\u0001b!;\u0016A\u0003%1q\u001c\u0005\f\u0007W,\"\u0019!C\u0001\u0003K\u001bi\u000f\u0003\u0005\u0004vV\u0001\u000b\u0011BBx\u00111\u001990\u0006EC\u0002\u0013\u0005\u0011QUB}\u00111\u0019i0\u0006EC\u0002\u0013\u0005\u0011QUB}\u0011-\u0019y0\u0006b\u0001\n\u0003\t)\u000b\"\u0001\t\u0011\u0011\rQ\u0003)A\u0005\u0005\u0017B\u0011\u0002\"\u0002\u0016\t\u0003\t)\u000bb\u0002\u0007\u0011\u00115Q\u0003AAS\t\u001fA!\u0002\"\u0005#\u0005\u000b\u0007I\u0011\u0001C\n\u0011)!)B\tB\u0001B\u0003%1\u0011\u000b\u0005\u000b\u0007\u007f\u0012#Q1A\u0005\u0002\rM\u0004B\u0003C\fE\t\u0005\t\u0015!\u0003\u0004v!9!1\u0006\u0012\u0005\u0002\u0011e\u0001b\u0002C\u0011E\u0011\u0005A1\u0005\u0005\b\tO\u0011C\u0011\u0001C\u0015\u0011\u001d\u0019)J\tC\u0001\t[A\u0011\u0002b\u0019##\u0003%\t\u0001\"\u001a\t\u0013\u0011e$%%A\u0005\u0002\u0011m\u0004b\u0002C@E\u0011\u0005A\u0011Q\u0004\n\t\u001b+\u0002\u0012AAS\t\u001f3\u0011\u0002\"\u0004\u0016\u0011\u0003\t)\u000b\"%\t\u000f\t-r\u0006\"\u0001\u0005\u0014\"9AQS\u0018\u0005\u0002\u0011]\u0005\"\u0003CO_\u0005\u0005I\u0011\u0002CP\r%\u0019)&FA\u0001\u0003K\u001b9\u0006\u0003\u0006\u0004jM\u0012)\u0019!C\u0001\u0003sD!ba\u001b4\u0005\u0003\u0005\u000b\u0011BA~\u0011\u001d\u0011Yc\rC\u0001\u0007[B\u0011b!\u001d4\u0005\u00045\taa\u001d\t\u000f\rU5G\"\u0001\u0004\u0018\"91\u0011U\u001a\u0007\u0002\r\r\u0006bBBTg\u0019\u00051\u0011\u0016\u0005\b\u0007c\u001bd\u0011ABZ\u0011\u001d\u0019)n\rC\u0001\u0007/<\u0011\u0002b*\u0016\u0011\u0003\t)\u000b\"+\u0007\u0013\rUS\u0003#\u0001\u0002&\u0012-\u0006b\u0002B\u0016}\u0011\u0005AQ\u0016\u0005\b\t_sD\u0011\u0001CY\u0011%!iJPA\u0001\n\u0013!yJ\u0002\u0005\u00056V\u0001\u0011Q\u0015C\\\u0011)!IL\u0011BC\u0002\u0013\u0005A\u0011\u0001\u0005\u000b\tw\u0013%\u0011!Q\u0001\n\t-\u0003b\u0002B\u0016\u0005\u0012\u0005AQ\u0018\u0005\n\u0007c\u0012%\u0019!C!\u0007gB\u0001\u0002b1CA\u0003%1Q\u000f\u0005\b\u0007+\u0013E\u0011\tCc\u0011\u001d\u0019\tK\u0011C!\t\u0017Dq\u0001b4C\t\u0013!\t\u000eC\u0004\u0004(\n#\t\u0005b7\t\u000f\rE&\t\"\u0011\u0005d\"91Q\u001b\"\u0005B\u00115x!\u0003Cy+!\u0005\u0011Q\u0015Cz\r%!),\u0006E\u0001\u0003K#)\u0010C\u0004\u0003,=#\t\u0001b>\t\u0013\u0011exJ1A\u0005\u0002\u0011\u0005\u0001\u0002\u0003C~\u001f\u0002\u0006IAa\u0013\t\u0013\u0011uu*!A\u0005\n\u0011}u!\u0003C\u007f+!\u0005\u0011Q\u0015C\u0000\r%)\t!\u0006E\u0001\u0003K+\u0019\u0001C\u0004\u0003,U#\t!\"\u0002\t\u0013\r%TK1A\u0005B\u0005e\b\u0002CB6+\u0002\u0006I!a?\t\u0013\rETK1A\u0005B\rM\u0004\u0002\u0003Cb+\u0002\u0006Ia!\u001e\t\u000f\rUU\u000b\"\u0011\u0006\b!91\u0011U+\u0005B\u00155\u0001bBBT+\u0012\u0005S\u0011\u0003\u0005\b\u0007c+F\u0011IC\r\u0011\u001d\u0019).\u0016C!\u000bGA\u0011\u0002\"(V\u0003\u0003%I\u0001b(\b\u0013\u0015\u001dR\u0003#\u0001\u0002&\u0016%b!CC\u0016+!\u0005\u0011QUC\u0017\u0011\u001d\u0011YC\u0019C\u0001\u000b_A\u0011b!\u001dc\u0005\u0004%\taa\u001d\t\u0011\u0011\r'\r)A\u0005\u0007kBqa!&c\t\u0003*\t\u0004C\u0004\u0004\"\n$\t%b\u000e\t\u000f\r\u001d&\r\"\u0011\u0006<!91\u0011\u00172\u0005B\u0015\r\u0003bBBkE\u0012\u0005SQ\n\u0005\n\t;\u0013\u0017\u0011!C\u0005\t?;\u0011\"\"\u0015\u0016\u0011\u0003\t)+b\u0015\u0007\u0013\u0015US\u0003#\u0001\u0002&\u0016]\u0003b\u0002B\u0016[\u0012\u0005Q\u0011\f\u0005\n\u0007Sj'\u0019!C!\u0003sD\u0001ba\u001bnA\u0003%\u00111 \u0005\n\u0007cj'\u0019!C!\u0007gB\u0001\u0002b1nA\u0003%1Q\u000f\u0005\b\u0007+kG\u0011IC.\u0011\u001d\u0019\t+\u001cC!\u000bCBqaa*n\t\u0003*)\u0007C\u0004\u000426$\t%\"\u001c\t\u0013\u0011uU.!A\u0005\n\u0011}u!CC<+!\u0005\u0011QUC=\r%)Y(\u0006E\u0001\u0003K+i\bC\u0004\u0003,e$\t!b \t\u0013\r%\u0014P1A\u0005B\u0005e\b\u0002CB6s\u0002\u0006I!a?\t\u0013\rE\u0014P1A\u0005B\rM\u0004\u0002\u0003Cbs\u0002\u0006Ia!\u001e\t\u000f\rU\u0015\u0010\"\u0011\u0006\u0002\"91\u0011U=\u0005B\u0015\u001d\u0005bBBTs\u0012\u0005S1\u0012\u0005\b\u0007cKH\u0011ICJ\u0011%!i*_A\u0001\n\u0013!yJB\u0005\u0004xU\t\t!!*\u0004z!Y1\u0011NA\u0005\u0005\u000b\u0007I\u0011AA}\u0011-\u0019Y'!\u0003\u0003\u0002\u0003\u0006I!a?\t\u0011\t-\u0012\u0011\u0002C\u0001\u0007wB\u0001ba \u0002\n\u0019\u00051\u0011\u0011\u0005\t\u0007\u000f\u000bIA\"\u0001\u0004\n\"A1QRA\u0005\r\u0003\u0019yiB\u0005\u0006\u001eVA\t!!*\u0006 \u001aI1qO\u000b\t\u0002\u0005\u0015V\u0011\u0015\u0005\t\u0005W\tI\u0002\"\u0001\u0006$\"AAqVA\r\t\u0003))\u000b\u0003\u0006\u0005\u001e\u0006e\u0011\u0011!C\u0005\t?3\u0001\"\"+\u0016\u0001\u0005\u0015V1\u0016\u0005\f\u000b[\u000b\tC!b\u0001\n\u0003!\t\u0001C\u0006\u00060\u0006\u0005\"\u0011!Q\u0001\n\t-\u0003\u0002\u0003B\u0016\u0003C!\t!\"-\t\u0011\r}\u0014\u0011\u0005C!\u000boC\u0001ba\"\u0002\"\u0011\u0005S1\u0018\u0005\t\u0007\u001b\u000b\t\u0003\"\u0011\u0006@\u001eIQ1Y\u000b\t\u0002\u0005\u0015VQ\u0019\u0004\n\u000b\u000f,\u0002\u0012AAS\u000b\u0013D\u0001Ba\u000b\u00022\u0011\u0005Q1\u001a\u0005\u000b\u0007S\n\tD1A\u0005B\u0005e\b\"CB6\u0003c\u0001\u000b\u0011BA~\u0011!\u0019y(!\r\u0005B\u00155\u0007\u0002CBD\u0003c!\t%\"5\t\u0011\r5\u0015\u0011\u0007C!\u000b+D!\u0002\"(\u00022\u0005\u0005I\u0011\u0002CP\u000f%)I.\u0006E\u0001\u0003K+YNB\u0005\u0006^VA\t!!*\u0006`\"A!1FA\"\t\u0003)\t\u000f\u0003\u0005\u0004\u0000\u0005\rC\u0011ICr\u0011!\u00199)a\u0011\u0005B\u0015\u001d\b\u0002CBG\u0003\u0007\"\t%b;\t\u0015\u0011u\u00151IA\u0001\n\u0013!yjB\u0005\u0006pVA\t!!*\u0006r\u001aIQ1_\u000b\t\u0002\u0005\u0015VQ\u001f\u0005\t\u0005W\t\t\u0006\"\u0001\u0006x\"Q1\u0011NA)\u0005\u0004%\t%!?\t\u0013\r-\u0014\u0011\u000bQ\u0001\n\u0005m\b\u0002CB@\u0003#\"\t%\"?\t\u0011\r\u001d\u0015\u0011\u000bC!\u000b{D\u0001b!$\u0002R\u0011\u0005c\u0011\u0001\u0005\u000b\t;\u000b\t&!A\u0005\n\u0011}u!\u0003D\u0003+!\u0005\u0011Q\u0015D\u0004\r%1I!\u0006E\u0001\u0003K3Y\u0001\u0003\u0005\u0003,\u0005\rD\u0011\u0001D\u0007\u0011)\u0019I'a\u0019C\u0002\u0013\u0005\u0013\u0011 \u0005\n\u0007W\n\u0019\u0007)A\u0005\u0003wD\u0001ba \u0002d\u0011\u0005cq\u0002\u0005\t\u0007\u000f\u000b\u0019\u0007\"\u0011\u0007\u0014!A1QRA2\t\u000329\u0002\u0003\u0006\u0005\u001e\u0006\r\u0014\u0011!C\u0005\t?;\u0011Bb\u0007\u0016\u0011\u0003\t)K\"\b\u0007\u0013\u0019}Q\u0003#\u0001\u0002&\u001a\u0005\u0002\u0002\u0003B\u0016\u0003k\"\tAb\t\t\u0011\r}\u0014Q\u000fC!\rKA\u0001ba\"\u0002v\u0011\u0005c\u0011\u0006\u0005\t\u0007\u001b\u000b)\b\"\u0011\u0007.!QAQTA;\u0003\u0003%I\u0001b(\b\u0013\u0019ER\u0003#\u0001\u0002&\u001aMb!\u0003D\u001b+!\u0005\u0011Q\u0015D\u001c\u0011!\u0011Y#a!\u0005\u0002\u0019e\u0002\u0002CB@\u0003\u0007#\tEb\u000f\t\u0011\r\u001d\u00151\u0011C!\r\u007fA\u0001b!$\u0002\u0004\u0012\u0005c1\t\u0005\u000b\t;\u000b\u0019)!A\u0005\n\u0011}u!\u0003D$+!\u0005\u0011Q\u0015D%\r%1Y%\u0006E\u0001\u0003K3i\u0005\u0003\u0005\u0003,\u0005EE\u0011\u0001D(\u0011)\u0019I'!%C\u0002\u0013\u0005\u0013\u0011 \u0005\n\u0007W\n\t\n)A\u0005\u0003wD\u0001ba \u0002\u0012\u0012\u0005c\u0011\u000b\u0005\t\u0007\u000f\u000b\t\n\"\u0011\u0007V!A1QRAI\t\u00032I\u0006\u0003\u0006\u0005\u001e\u0006E\u0015\u0011!C\u0005\t?C\u0011\u0002\"(\u0016\u0003\u0003%I\u0001b(\u00037\u001d+g.\u001a:bY&TX\r\u001a'j]\u0016\f'OU3he\u0016\u001c8/[8o\u0015\u0011\t9+!+\u0002\u0015I,wM]3tg&|gN\u0003\u0003\u0002,\u00065\u0016AA7m\u0015\u0011\ty+!-\u0002\u000bM\u0004\u0018M]6\u000b\t\u0005M\u0016QW\u0001\u0007CB\f7\r[3\u000b\u0005\u0005]\u0016aA8sO\u000e\u00011#\u0003\u0001\u0002>\u0006e\u0017q\\Av!)\ty,!1\u0002F\u0006E\u00171[\u0007\u0003\u0003KKA!a1\u0002&\nI!+Z4sKN\u001cxN\u001d\t\u0005\u0003\u000f\fi-\u0004\u0002\u0002J*!\u00111ZAU\u0003\u0019a\u0017N\\1mO&!\u0011qZAe\u0005\u00191Vm\u0019;peB\u0019\u0011q\u0018\u0001\u0011\t\u0005}\u0016Q[\u0005\u0005\u0003/\f)K\u0001\u0011HK:,'/\u00197ju\u0016$G*\u001b8fCJ\u0014Vm\u001a:fgNLwN\\'pI\u0016d\u0007\u0003BA`\u00037LA!!8\u0002&\nyr)\u001a8fe\u0006d\u0017N_3e\u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:\u0014\u0015m]3\u0011\t\u0005\u0005\u0018q]\u0007\u0003\u0003GTA!!:\u0002*\u0006!Q\u000f^5m\u0013\u0011\tI/a9\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mKB!\u0011Q^Az\u001b\t\tyO\u0003\u0003\u0002r\u00065\u0016\u0001C5oi\u0016\u0014h.\u00197\n\t\u0005U\u0018q\u001e\u0002\b\u0019><w-\u001b8h\u0003\r)\u0018\u000eZ\u000b\u0003\u0003w\u0004B!!@\u0003\u00109!\u0011q B\u0006!\u0011\u0011\tAa\u0002\u000e\u0005\t\r!\u0002\u0002B\u0003\u0003s\u000ba\u0001\u0010:p_Rt$B\u0001B\u0005\u0003\u0015\u00198-\u00197b\u0013\u0011\u0011iAa\u0002\u0002\rA\u0013X\rZ3g\u0013\u0011\u0011\tBa\u0005\u0003\rM#(/\u001b8h\u0015\u0011\u0011iAa\u0002)\u000b\u0005\u00119Ba\t\u0011\t\te!qD\u0007\u0003\u00057QAA!\b\u0002.\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t\u0005\"1\u0004\u0002\u0006'&t7-Z\u0011\u0003\u0005K\tQA\r\u00181]A\nA!^5eA!*!Aa\u0006\u0003$\u00051A(\u001b8jiz\"B!!5\u00030!9\u0011q_\u0002A\u0002\u0005m\bF\u0002B\u0018\u0005/\u0011\u0019\u0003K\u0003\u0004\u0005/\u0011\u0019\u0003\u0006\u0002\u0002R\"*AAa\u0006\u0003$\u0005I1/\u001a;GC6LG.\u001f\u000b\u0005\u0005{\u0011y$D\u0001\u0001\u0011\u001d\u0011\t%\u0002a\u0001\u0003w\fQA^1mk\u0016DS!\u0002B\f\u0005G\t\u0001c]3u-\u0006\u0014\u0018.\u00198dKB{w/\u001a:\u0015\t\tu\"\u0011\n\u0005\b\u0005\u00032\u0001\u0019\u0001B&!\u0011\u0011iEa\u0014\u000e\u0005\t\u001d\u0011\u0002\u0002B)\u0005\u000f\u0011a\u0001R8vE2,\u0007&\u0002\u0004\u0003\u0018\tU\u0013E\u0001B,\u0003\u0015\u0011dF\r\u00181\u00031\u0019X\r\u001e'j].\u0004vn^3s)\u0011\u0011iD!\u0018\t\u000f\t\u0005s\u00011\u0001\u0003L!*qAa\u0006\u0003V\u000591/\u001a;MS:\\G\u0003\u0002B\u001f\u0005KBqA!\u0011\t\u0001\u0004\tY\u0010K\u0003\t\u0005/\u0011\u0019#A\btKR4\u0015\u000e^%oi\u0016\u00148-\u001a9u)\u0011\u0011iD!\u001c\t\u000f\t\u0005\u0013\u00021\u0001\u0003pA!!Q\nB9\u0013\u0011\u0011\u0019Ha\u0002\u0003\u000f\t{w\u000e\\3b]\"*\u0011Ba\u0006\u0003$\u0005Q1/\u001a;NCbLE/\u001a:\u0015\t\tu\"1\u0010\u0005\b\u0005\u0003R\u0001\u0019\u0001B?!\u0011\u0011iEa \n\t\t\u0005%q\u0001\u0002\u0004\u0013:$\b&\u0002\u0006\u0003\u0018\t\r\u0012AB:fiR{G\u000e\u0006\u0003\u0003>\t%\u0005b\u0002B!\u0017\u0001\u0007!1\n\u0015\u0006\u0017\t]!1E\u0001\fg\u0016$(+Z4QCJ\fW\u000e\u0006\u0003\u0003>\tE\u0005b\u0002B!\u0019\u0001\u0007!1\n\u0015\u0006\u0019\t]!1E\u0001\rg\u0016$x+Z5hQR\u001cu\u000e\u001c\u000b\u0005\u0005{\u0011I\nC\u0004\u0003B5\u0001\r!a?)\u000b5\u00119Ba\t\u0002\u0019M,Go\u00144gg\u0016$8i\u001c7\u0015\t\tu\"\u0011\u0015\u0005\b\u0005\u0003r\u0001\u0019AA~Q\u0015q!q\u0003BSC\t\u00119+A\u00033]Mr\u0003'A\u0005tKR\u001cv\u000e\u001c<feR!!Q\bBW\u0011\u001d\u0011\te\u0004a\u0001\u0003wDSa\u0004B\f\u0005G\tAc]3u\u0019&t7\u000e\u0015:fI&\u001cG/[8o\u0007>dG\u0003\u0002B\u001f\u0005kCqA!\u0011\u0011\u0001\u0004\tY\u0010K\u0003\u0011\u0005/\u0011\u0019#A\ntKR\fum\u001a:fO\u0006$\u0018n\u001c8EKB$\b\u000e\u0006\u0003\u0003>\tu\u0006b\u0002B!#\u0001\u0007!Q\u0010\u0015\u0006#\t]!\u0011Y\u0011\u0003\u0005\u0007\fQa\r\u00181]A\nQ\u0001\u001e:bS:$B!a5\u0003J\"9!1\u001a\nA\u0002\t5\u0017a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0005\u001f\u0014y\u000e\u0005\u0004\u0003R\n]'1\\\u0007\u0003\u0005'TAA!6\u0002.\u0006\u00191/\u001d7\n\t\te'1\u001b\u0002\b\t\u0006$\u0018m]3u!\u0011\u0011iNa8\r\u0001\u0011a!\u0011\u001dBe\u0003\u0003\u0005\tQ!\u0001\u0003d\n\u0019q\fJ\u0019\u0012\t\t\u0015(1\u001e\t\u0005\u0005\u001b\u00129/\u0003\u0003\u0003j\n\u001d!a\u0002(pi\"Lgn\u001a\t\u0005\u0005\u001b\u0012i/\u0003\u0003\u0003p\n\u001d!aA!os\u0006!1m\u001c9z)\u0011\t\tN!>\t\u000f\t]8\u00031\u0001\u0003z\u0006)Q\r\u001f;sCB!!1`B\u0001\u001b\t\u0011iP\u0003\u0003\u0003\u0000\u0006%\u0016!\u00029be\u0006l\u0017\u0002BB\u0002\u0005{\u0014\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0006'\t]!1\u0005\u0015\u0006\u0001\t]!1E\u0001\u001c\u000f\u0016tWM]1mSj,G\rT5oK\u0006\u0014(+Z4sKN\u001c\u0018n\u001c8\u0011\u0007\u0005}VcE\u0004\u0016\u0007\u001f\u0019)ba\u0007\u0011\t\t53\u0011C\u0005\u0005\u0007'\u00119A\u0001\u0004B]f\u0014VM\u001a\t\u0007\u0003C\u001c9\"!5\n\t\re\u00111\u001d\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\u0019iba\n\u000e\u0005\r}!\u0002BB\u0011\u0007G\t!![8\u000b\u0005\r\u0015\u0012\u0001\u00026bm\u0006LAa!\u000b\u0004 \ta1+\u001a:jC2L'0\u00192mKR\u001111B\u0001\u0005Y>\fG\r\u0006\u0003\u0002R\u000eE\u0002bBB\u001a/\u0001\u0007\u00111`\u0001\u0005a\u0006$\b\u000eK\u0003\u0018\u0005/\u0011\u0019#A\u000etkB\u0004xN\u001d;fI\u001a\u000bW.\u001b7z\u0003:$G*\u001b8l!\u0006L'o]\u000b\u0003\u0007w\u0001ba!\u0010\u0004H\r-SBAB \u0015\u0011\u0019\tea\u0011\u0002\u0013%lW.\u001e;bE2,'\u0002BB#\u0005\u000f\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0019Iea\u0010\u0003\u0007M+G\u000f\u0005\u0005\u0003N\r53\u0011KB;\u0013\u0011\u0019yEa\u0002\u0003\rQ+\b\u000f\\33!\r\u0019\u0019fM\u0007\u0002+\t1a)Y7jYf\u001cRaMB\b\u00073\u0002Baa\u0017\u0004f9!1QLB1\u001d\u0011\u0011\taa\u0018\n\u0005\t%\u0011\u0002BB2\u0005\u000f\tq\u0001]1dW\u0006<W-\u0003\u0003\u0004*\r\u001d$\u0002BB2\u0005\u000f\tAA\\1nK\u0006)a.Y7fAQ!1\u0011KB8\u0011\u001d\u0019IG\u000ea\u0001\u0003w\f1\u0002Z3gCVdG\u000fT5oWV\u00111Q\u000f\t\u0005\u0007'\nIA\u0001\u0003MS:\\7CBA\u0005\u0007\u001f\u0019I\u0006\u0006\u0003\u0004v\ru\u0004\u0002CB5\u0003\u001f\u0001\r!a?\u0002\t1Lgn\u001b\u000b\u0005\u0005\u0017\u001a\u0019\t\u0003\u0005\u0004\u0006\u0006E\u0001\u0019\u0001B&\u0003\tiW/A\u0003eKJLg\u000f\u0006\u0003\u0003L\r-\u0005\u0002CBC\u0003'\u0001\rAa\u0013\u0002\rUtG.\u001b8l)\u0011\u0011Ye!%\t\u0011\rM\u0015Q\u0003a\u0001\u0005\u0017\n1!\u001a;b\u0003)Ig.\u001b;jC2L'0\u001a\u000b\u0007\u0005\u0017\u001aIj!(\t\u000f\rm\u0005\b1\u0001\u0003L\u0005\t\u0011\u0010C\u0004\u0004 b\u0002\rAa\u0013\u0002\r],\u0017n\u001a5u\u0003!1\u0018M]5b]\u000e,G\u0003\u0002B&\u0007KCqa!\":\u0001\u0004\u0011Y%\u0001\u0005eKZL\u0017M\\2f)!\u0011Yea+\u0004.\u000e=\u0006bBBNu\u0001\u0007!1\n\u0005\b\u0007\u000bS\u0004\u0019\u0001B&\u0011\u001d\u0019yJ\u000fa\u0001\u0005\u0017\n1!Y5d))\u0011Ye!.\u0004L\u000e57\u0011\u001b\u0005\b\u0007o[\u0004\u0019AB]\u0003-\u0001(/\u001a3jGRLwN\\:\u0011\r\rm6\u0011YBc\u001b\t\u0019iL\u0003\u0003\u0004@\u00065\u0016a\u0001:eI&!11YB_\u0005\r\u0011F\t\u0012\t\u000b\u0005\u001b\u001a9Ma\u0013\u0003L\t-\u0013\u0002BBe\u0005\u000f\u0011a\u0001V;qY\u0016\u001c\u0004bBBTw\u0001\u0007!1\n\u0005\b\u0007\u001f\\\u0004\u0019\u0001B&\u00031qW/\\%ogR\fgnY3t\u0011\u001d\u0019\u0019n\u000fa\u0001\u0005\u0017\n\u0011b^3jO\"$8+^7\u0002\u000fA\u0014xN[3diR!!1JBm\u0011\u001d\u0019)\t\u0010a\u0001\u0005\u0017\nA!\u0013*M'V\u00111q\u001c\t\u0005\u0007C\u001c9/\u0004\u0002\u0004d*!1Q]B\u0012\u0003\u0011a\u0017M\\4\n\t\tE11]\u0001\u0006\u0013Jc5\u000bI\u0001\u0011gV\u0004\bo\u001c:uK\u0012\u001cv\u000e\u001c<feN,\"aa<\u0011\r\t53\u0011_Bp\u0013\u0011\u0019\u0019Pa\u0002\u0003\u000b\u0005\u0013(/Y=\u0002#M,\b\u000f]8si\u0016$7k\u001c7wKJ\u001c\b%\u0001\u000btkB\u0004xN\u001d;fI\u001a\u000bW.\u001b7z\u001d\u0006lWm]\u000b\u0003\u0007w\u0004bA!\u0014\u0004r\u0006m\u0018AE:vaB|'\u000f^3e\u0019&t7NT1nKN\fq!\u001a9tS2|g.\u0006\u0002\u0003L\u0005AQ\r]:jY>t\u0007%A\u0003zY><\u0017\u0010\u0006\u0004\u0003L\u0011%A1\u0002\u0005\b\u00077\u000b\u0003\u0019\u0001B&\u0011\u001d\u0019))\ta\u0001\u0005\u0017\u0012QBR1nS2L\u0018I\u001c3MS:\\7#\u0002\u0012\u0004\u0010\re\u0013A\u00024b[&d\u00170\u0006\u0002\u0004R\u00059a-Y7jYf\u0004\u0013!\u00027j].\u0004CC\u0002C\u000e\t;!y\u0002E\u0002\u0004T\tBq\u0001\"\u0005(\u0001\u0004\u0019\t\u0006C\u0004\u0004\u0000\u001d\u0002\ra!\u001e\u0002\u000fA\u0014X\rZ5diR!!1\nC\u0013\u0011\u001d\u0019)\t\u000ba\u0001\u0005\u0017\naAZ5ui\u0016$G\u0003\u0002B&\tWAqaa%*\u0001\u0004\u0011Y\u0005\u0006\u0007\u00050\u0011mBQ\nC)\t+\"y\u0006\u0005\u0003\u00052\u0011]RB\u0001C\u001a\u0015\u0011!)$!+\u0002\u000b=\u0004H/[7\n\t\u0011eB1\u0007\u0002\u001a/\u0016Lw\r\u001b;fI2+\u0017m\u001d;TcV\f'/Z:N_\u0012,G\u000eC\u0004\u0005>)\u0002\r\u0001b\u0010\u0002\u0013%t7\u000f^1oG\u0016\u001c\bCBB^\u0007\u0003$\t\u0005\u0005\u0003\u0005D\u0011%SB\u0001C#\u0015\u0011!9%!+\u0002\u000f\u0019,\u0017\r^;sK&!A1\nC#\u00059yeMZ:fi&s7\u000f^1oG\u0016Dq\u0001b\u0014+\u0001\u0004\u0011y'\u0001\u0007gSRLe\u000e^3sG\u0016\u0004H\u000fC\u0004\u0005T)\u0002\rAa\u0013\u0002\u0011I,w\rU1sC6D\u0011\u0002b\u0016+!\u0003\u0005\r\u0001\"\u0017\u0002\u000b%t7\u000f\u001e:\u0011\t\u0005\u0005H1L\u0005\u0005\t;\n\u0019OA\fPaRLwN\\1m\u0013:\u001cHO];nK:$\u0018\r^5p]\"IA\u0011\r\u0016\u0011\u0002\u0003\u0007!QP\u0001\u0006I\u0016\u0004H\u000f[\u0001\u0015S:LG/[1mSj,G\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0011\u001d$\u0006\u0002C-\tSZ#\u0001b\u001b\u0011\t\u00115DQO\u0007\u0003\t_RA\u0001\"\u001d\u0005t\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0005\u0005;\u00119!\u0003\u0003\u0005x\u0011=$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006!\u0012N\\5uS\u0006d\u0017N_3%I\u00164\u0017-\u001e7uIU*\"\u0001\" +\t\tuD\u0011N\u0001\re\u0016<X-[4ii\u001a+hn\u0019\u000b\u0007\t\u0007#)\t\"#\u0011\u0011\t53Q\nB&\u0005\u0017Bq\u0001b\".\u0001\u0004!\t%\u0001\u0005j]N$\u0018M\\2f\u0011\u001d!Y)\fa\u0001\t_\tQ!\\8eK2\fQBR1nS2L\u0018I\u001c3MS:\\\u0007cAB*_M)qfa\u0004\u0004\u001cQ\u0011AqR\u0001\u0006CB\u0004H.\u001f\u000b\u0005\t7!I\nC\u0004\u0005\u001cF\u0002\r!!7\u0002\rA\f'/Y7t\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t!\t\u000b\u0005\u0003\u0004b\u0012\r\u0016\u0002\u0002CS\u0007G\u0014aa\u00142kK\u000e$\u0018A\u0002$b[&d\u0017\u0010E\u0002\u0004Ty\u001aRAPB\b\u00077!\"\u0001\"+\u0002\u0015\u0019\u0014x.\u001c)be\u0006l7\u000f\u0006\u0003\u0004R\u0011M\u0006b\u0002CN\u0001\u0002\u0007\u0011\u0011\u001c\u0002\b)^,W\rZ5f'\r\u00115\u0011K\u0001\u000em\u0006\u0014\u0018.\u00198dKB{w/\u001a:\u0002\u001dY\f'/[1oG\u0016\u0004vn^3sAQ!Aq\u0018Ca!\r\u0019\u0019F\u0011\u0005\b\ts+\u0005\u0019\u0001B&\u00031!WMZ1vYRd\u0015N\\6!)\u0019\u0011Y\u0005b2\u0005J\"911\u0014%A\u0002\t-\u0003bBBP\u0011\u0002\u0007!1\n\u000b\u0005\u0005\u0017\"i\rC\u0004\u0004\u0006&\u0003\rAa\u0013\u0002\u0005e\u0004H\u0003\u0003B&\t'$)\u000eb6\t\u000f\rm%\n1\u0001\u0003L!91Q\u0011&A\u0002\t-\u0003b\u0002Cm\u0015\u0002\u0007!1J\u0001\u0002aRA!1\nCo\t?$\t\u000fC\u0004\u0004\u001c.\u0003\rAa\u0013\t\u000f\r\u00155\n1\u0001\u0003L!91qT&A\u0002\t-CC\u0003B&\tK$9\u000f\";\u0005l\"91q\u0017'A\u0002\re\u0006bBBT\u0019\u0002\u0007!1\n\u0005\b\u0007\u001fd\u0005\u0019\u0001B&\u0011\u001d\u0019\u0019\u000e\u0014a\u0001\u0005\u0017\"BAa\u0013\u0005p\"91QQ'A\u0002\t-\u0013a\u0002+xK\u0016$\u0017.\u001a\t\u0004\u0007'z5#B(\u0004\u0010\rmAC\u0001Cz\u0003\u0015!W\r\u001c;b\u0003\u0019!W\r\u001c;bA\u0005Aq)Y;tg&\fg\u000eE\u0002\u0004TU\u0013\u0001bR1vgNL\u0017M\\\n\u0004+\u0012}FC\u0001C\u0000)\u0019\u0011Y%\"\u0003\u0006\f!911T.A\u0002\t-\u0003bBBP7\u0002\u0007!1\n\u000b\u0005\u0005\u0017*y\u0001C\u0004\u0004\u0006r\u0003\rAa\u0013\u0015\u0011\t-S1CC\u000b\u000b/Aqaa'^\u0001\u0004\u0011Y\u0005C\u0004\u0004\u0006v\u0003\rAa\u0013\t\u000f\r}U\f1\u0001\u0003LQQ!1JC\u000e\u000b;)y\"\"\t\t\u000f\r]f\f1\u0001\u0004:\"91q\u00150A\u0002\t-\u0003bBBh=\u0002\u0007!1\n\u0005\b\u0007't\u0006\u0019\u0001B&)\u0011\u0011Y%\"\n\t\u000f\r\u0015u\f1\u0001\u0003L\u0005A!)\u001b8p[&\fG\u000eE\u0002\u0004T\t\u0014\u0001BQ5o_6L\u0017\r\\\n\u0004E\u000eECCAC\u0015)\u0019\u0011Y%b\r\u00066!911\u00144A\u0002\t-\u0003bBBPM\u0002\u0007!1\n\u000b\u0005\u0005\u0017*I\u0004C\u0004\u0004\u0006\u001e\u0004\rAa\u0013\u0015\u0011\t-SQHC \u000b\u0003Bqaa'i\u0001\u0004\u0011Y\u0005C\u0004\u0004\u0006\"\u0004\rAa\u0013\t\u000f\r}\u0005\u000e1\u0001\u0003LQQ!1JC#\u000b\u000f*I%b\u0013\t\u000f\r]\u0016\u000e1\u0001\u0004:\"91qU5A\u0002\t-\u0003bBBhS\u0002\u0007!1\n\u0005\b\u0007'L\u0007\u0019\u0001B&)\u0011\u0011Y%b\u0014\t\u000f\r\u0015%\u000e1\u0001\u0003L\u00059\u0001k\\5tg>t\u0007cAB*[\n9\u0001k\\5tg>t7cA7\u0005@R\u0011Q1\u000b\u000b\u0007\u0005\u0017*i&b\u0018\t\u000f\rm5\u000f1\u0001\u0003L!91qT:A\u0002\t-C\u0003\u0002B&\u000bGBqa!\"u\u0001\u0004\u0011Y\u0005\u0006\u0005\u0003L\u0015\u001dT\u0011NC6\u0011\u001d\u0019Y*\u001ea\u0001\u0005\u0017Bqa!\"v\u0001\u0004\u0011Y\u0005C\u0004\u0004 V\u0004\rAa\u0013\u0015\u0015\t-SqNC9\u000bg*)\bC\u0004\u00048Z\u0004\ra!/\t\u000f\r\u001df\u000f1\u0001\u0003L!91q\u001a<A\u0002\t-\u0003bBBjm\u0002\u0007!1J\u0001\u0006\u000f\u0006lW.\u0019\t\u0004\u0007'J(!B$b[6\f7cA=\u0005@R\u0011Q\u0011\u0010\u000b\u0007\u0005\u0017*\u0019)\"\"\t\u000f\rmu\u00101\u0001\u0003L!91qT@A\u0002\t-C\u0003\u0002B&\u000b\u0013C\u0001b!\"\u0002\u0002\u0001\u0007!1\n\u000b\t\u0005\u0017*i)b$\u0006\u0012\"A11TA\u0002\u0001\u0004\u0011Y\u0005\u0003\u0005\u0004\u0006\u0006\r\u0001\u0019\u0001B&\u0011!\u0019y*a\u0001A\u0002\t-CC\u0003B&\u000b++9*\"'\u0006\u001c\"A1qWA\u0003\u0001\u0004\u0019I\f\u0003\u0005\u0004(\u0006\u0015\u0001\u0019\u0001B&\u0011!\u0019y-!\u0002A\u0002\t-\u0003\u0002CBj\u0003\u000b\u0001\rAa\u0013\u0002\t1Kgn\u001b\t\u0005\u0007'\nIb\u0005\u0004\u0002\u001a\r=11\u0004\u000b\u0003\u000b?#Ba!\u001e\u0006(\"AA1TA\u000f\u0001\u0004\tINA\u0003Q_^,'o\u0005\u0003\u0002\"\rU\u0014!\u00037j].\u0004vn^3s\u0003)a\u0017N\\6Q_^,'\u000f\t\u000b\u0005\u000bg+)\f\u0005\u0003\u0004T\u0005\u0005\u0002\u0002CCW\u0003O\u0001\rAa\u0013\u0015\t\t-S\u0011\u0018\u0005\t\u0007\u000b\u000bI\u00031\u0001\u0003LQ!!1JC_\u0011!\u0019))a\u000bA\u0002\t-C\u0003\u0002B&\u000b\u0003D\u0001ba%\u0002.\u0001\u0007!1J\u0001\t\u0013\u0012,g\u000e^5usB!11KA\u0019\u0005!IE-\u001a8uSRL8\u0003BA\u0019\u000bg#\"!\"2\u0015\t\t-Sq\u001a\u0005\t\u0007\u000b\u000bI\u00041\u0001\u0003LQ!!1JCj\u0011!\u0019))a\u000fA\u0002\t-C\u0003\u0002B&\u000b/D\u0001ba%\u0002>\u0001\u0007!1J\u0001\u0006\u0019><\u0017\u000e\u001e\t\u0005\u0007'\n\u0019EA\u0003M_\u001eLGo\u0005\u0003\u0002D\rUDCACn)\u0011\u0011Y%\":\t\u0011\r\u0015\u0015q\ta\u0001\u0005\u0017\"BAa\u0013\u0006j\"A1QQA%\u0001\u0004\u0011Y\u0005\u0006\u0003\u0003L\u00155\b\u0002CBJ\u0003\u0017\u0002\rAa\u0013\u0002\u00071{w\r\u0005\u0003\u0004T\u0005E#a\u0001'pON!\u0011\u0011KCZ)\t)\t\u0010\u0006\u0003\u0003L\u0015m\b\u0002CBC\u00033\u0002\rAa\u0013\u0015\t\t-Sq \u0005\t\u0007\u000b\u000bY\u00061\u0001\u0003LQ!!1\nD\u0002\u0011!\u0019\u0019*!\u0018A\u0002\t-\u0013aB%om\u0016\u00148/\u001a\t\u0005\u0007'\n\u0019GA\u0004J]Z,'o]3\u0014\t\u0005\rT1\u0017\u000b\u0003\r\u000f!BAa\u0013\u0007\u0012!A1QQA6\u0001\u0004\u0011Y\u0005\u0006\u0003\u0003L\u0019U\u0001\u0002CBC\u0003[\u0002\rAa\u0013\u0015\t\t-c\u0011\u0004\u0005\t\u0007'\u000by\u00071\u0001\u0003L\u00051\u0001K]8cSR\u0004Baa\u0015\u0002v\t1\u0001K]8cSR\u001cB!!\u001e\u0004vQ\u0011aQ\u0004\u000b\u0005\u0005\u001729\u0003\u0003\u0005\u0004\u0006\u0006e\u0004\u0019\u0001B&)\u0011\u0011YEb\u000b\t\u0011\r\u0015\u00151\u0010a\u0001\u0005\u0017\"BAa\u0013\u00070!A11SA?\u0001\u0004\u0011Y%A\u0004D\u0019><Gj\\4\u0011\t\rM\u00131\u0011\u0002\b\u00072{w\rT8h'\u0011\t\u0019i!\u001e\u0015\u0005\u0019MB\u0003\u0002B&\r{A\u0001b!\"\u0002\b\u0002\u0007!1\n\u000b\u0005\u0005\u00172\t\u0005\u0003\u0005\u0004\u0006\u0006%\u0005\u0019\u0001B&)\u0011\u0011YE\"\u0012\t\u0011\rM\u00151\u0012a\u0001\u0005\u0017\nAaU9siB!11KAI\u0005\u0011\u0019\u0016O\u001d;\u0014\t\u0005EU1\u0017\u000b\u0003\r\u0013\"BAa\u0013\u0007T!A1QQAM\u0001\u0004\u0011Y\u0005\u0006\u0003\u0003L\u0019]\u0003\u0002CBC\u00037\u0003\rAa\u0013\u0015\t\t-c1\f\u0005\t\u0007'\u000bi\n1\u0001\u0003L!*QCa\u0006\u0003$!*ACa\u0006\u0003$\u0001"
)
public class GeneralizedLinearRegression extends Regressor implements GeneralizedLinearRegressionBase, DefaultParamsWritable {
   private final String uid;
   private Param family;
   private DoubleParam variancePower;
   private Param link;
   private DoubleParam linkPower;
   private Param linkPredictionCol;
   private Param offsetCol;
   private Param solver;
   private IntParam aggregationDepth;
   private Param weightCol;
   private DoubleParam regParam;
   private DoubleParam tol;
   private IntParam maxIter;
   private BooleanParam fitIntercept;

   public static GeneralizedLinearRegression load(final String path) {
      return GeneralizedLinearRegression$.MODULE$.load(path);
   }

   public static MLReader read() {
      return GeneralizedLinearRegression$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public String getFamily() {
      return GeneralizedLinearRegressionBase.getFamily$(this);
   }

   public double getVariancePower() {
      return GeneralizedLinearRegressionBase.getVariancePower$(this);
   }

   public String getLink() {
      return GeneralizedLinearRegressionBase.getLink$(this);
   }

   public double getLinkPower() {
      return GeneralizedLinearRegressionBase.getLinkPower$(this);
   }

   public String getLinkPredictionCol() {
      return GeneralizedLinearRegressionBase.getLinkPredictionCol$(this);
   }

   public String getOffsetCol() {
      return GeneralizedLinearRegressionBase.getOffsetCol$(this);
   }

   public boolean hasWeightCol() {
      return GeneralizedLinearRegressionBase.hasWeightCol$(this);
   }

   public boolean hasOffsetCol() {
      return GeneralizedLinearRegressionBase.hasOffsetCol$(this);
   }

   public boolean hasLinkPredictionCol() {
      return GeneralizedLinearRegressionBase.hasLinkPredictionCol$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return GeneralizedLinearRegressionBase.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final int getAggregationDepth() {
      return HasAggregationDepth.getAggregationDepth$(this);
   }

   public final String getSolver() {
      return HasSolver.getSolver$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final double getRegParam() {
      return HasRegParam.getRegParam$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final boolean getFitIntercept() {
      return HasFitIntercept.getFitIntercept$(this);
   }

   public final Param family() {
      return this.family;
   }

   public final DoubleParam variancePower() {
      return this.variancePower;
   }

   public final Param link() {
      return this.link;
   }

   public final DoubleParam linkPower() {
      return this.linkPower;
   }

   public final Param linkPredictionCol() {
      return this.linkPredictionCol;
   }

   public final Param offsetCol() {
      return this.offsetCol;
   }

   public final Param solver() {
      return this.solver;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$family_$eq(final Param x$1) {
      this.family = x$1;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$variancePower_$eq(final DoubleParam x$1) {
      this.variancePower = x$1;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$link_$eq(final Param x$1) {
      this.link = x$1;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$linkPower_$eq(final DoubleParam x$1) {
      this.linkPower = x$1;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$linkPredictionCol_$eq(final Param x$1) {
      this.linkPredictionCol = x$1;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$offsetCol_$eq(final Param x$1) {
      this.offsetCol = x$1;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$solver_$eq(final Param x$1) {
      this.solver = x$1;
   }

   public final IntParam aggregationDepth() {
      return this.aggregationDepth;
   }

   public final void org$apache$spark$ml$param$shared$HasAggregationDepth$_setter_$aggregationDepth_$eq(final IntParam x$1) {
      this.aggregationDepth = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasSolver$_setter_$solver_$eq(final Param x$1) {
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final DoubleParam regParam() {
      return this.regParam;
   }

   public final void org$apache$spark$ml$param$shared$HasRegParam$_setter_$regParam_$eq(final DoubleParam x$1) {
      this.regParam = x$1;
   }

   public final DoubleParam tol() {
      return this.tol;
   }

   public final void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1) {
      this.tol = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public final BooleanParam fitIntercept() {
      return this.fitIntercept;
   }

   public final void org$apache$spark$ml$param$shared$HasFitIntercept$_setter_$fitIntercept_$eq(final BooleanParam x$1) {
      this.fitIntercept = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public GeneralizedLinearRegression setFamily(final String value) {
      return (GeneralizedLinearRegression)this.set(this.family(), value);
   }

   public GeneralizedLinearRegression setVariancePower(final double value) {
      return (GeneralizedLinearRegression)this.set(this.variancePower(), BoxesRunTime.boxToDouble(value));
   }

   public GeneralizedLinearRegression setLinkPower(final double value) {
      return (GeneralizedLinearRegression)this.set(this.linkPower(), BoxesRunTime.boxToDouble(value));
   }

   public GeneralizedLinearRegression setLink(final String value) {
      return (GeneralizedLinearRegression)this.set(this.link(), value);
   }

   public GeneralizedLinearRegression setFitIntercept(final boolean value) {
      return (GeneralizedLinearRegression)this.set(this.fitIntercept(), BoxesRunTime.boxToBoolean(value));
   }

   public GeneralizedLinearRegression setMaxIter(final int value) {
      return (GeneralizedLinearRegression)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public GeneralizedLinearRegression setTol(final double value) {
      return (GeneralizedLinearRegression)this.set(this.tol(), BoxesRunTime.boxToDouble(value));
   }

   public GeneralizedLinearRegression setRegParam(final double value) {
      return (GeneralizedLinearRegression)this.set(this.regParam(), BoxesRunTime.boxToDouble(value));
   }

   public GeneralizedLinearRegression setWeightCol(final String value) {
      return (GeneralizedLinearRegression)this.set(this.weightCol(), value);
   }

   public GeneralizedLinearRegression setOffsetCol(final String value) {
      return (GeneralizedLinearRegression)this.set(this.offsetCol(), value);
   }

   public GeneralizedLinearRegression setSolver(final String value) {
      return (GeneralizedLinearRegression)this.set(this.solver(), value);
   }

   public GeneralizedLinearRegression setLinkPredictionCol(final String value) {
      return (GeneralizedLinearRegression)this.set(this.linkPredictionCol(), value);
   }

   public GeneralizedLinearRegression setAggregationDepth(final int value) {
      return (GeneralizedLinearRegression)this.set(this.aggregationDepth(), BoxesRunTime.boxToInteger(value));
   }

   public GeneralizedLinearRegressionModel train(final Dataset dataset) {
      return (GeneralizedLinearRegressionModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         FamilyAndLink familyAndLink = GeneralizedLinearRegression.FamilyAndLink$.MODULE$.apply(this);
         instr.logPipelineStage(this);
         instr.logDataset(dataset);
         instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.featuresCol(), this.weightCol(), this.offsetCol(), this.predictionCol(), this.linkPredictionCol(), this.family(), this.solver(), this.fitIntercept(), this.link(), this.maxIter(), this.regParam(), this.tol(), this.aggregationDepth()}));
         int numFeatures = DatasetUtils$.MODULE$.getNumFeatures(dataset, (String)this.$(this.featuresCol()));
         instr.logNumFeatures((long)numFeatures);
         if (numFeatures > WeightedLeastSquares$.MODULE$.MAX_NUM_FEATURES()) {
            int var23 = WeightedLeastSquares$.MODULE$.MAX_NUM_FEATURES();
            String msg = "Currently, GeneralizedLinearRegression only supports number of features <= " + var23 + ". Found " + numFeatures + " in the input dataset.";
            throw new SparkException(msg);
         } else {
            GeneralizedLinearRegressionModel var22;
            label37: {
               Dataset validated;
               label36: {
                  label35: {
                     scala.Predef..MODULE$.require(numFeatures > 0 || BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), () -> "GeneralizedLinearRegression was given data with 0 features, and with Param fitIntercept set to false. To fit a model with 0 features, fitIntercept must be set to true.");
                     validated = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkRegressionLabels((String)this.$(this.labelCol())), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol())), !this.hasOffsetCol() ? org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)0.0F)) : DatasetUtils$.MODULE$.checkNonNanValues((String)this.$(this.offsetCol()), "Offsets"), DatasetUtils$.MODULE$.checkNonNanVectors((String)this.$(this.featuresCol()))})));
                     Family var10000 = familyAndLink.family();
                     Gaussian$ var8 = GeneralizedLinearRegression.Gaussian$.MODULE$;
                     if (var10000 == null) {
                        if (var8 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var8)) {
                        break label35;
                     }

                     Link var21 = familyAndLink.link();
                     Identity$ var9 = GeneralizedLinearRegression.Identity$.MODULE$;
                     if (var21 == null) {
                        if (var9 == null) {
                           break label36;
                        }
                     } else if (var21.equals(var9)) {
                        break label36;
                     }
                  }

                  RDD instances = validated.rdd().map((x0$2) -> {
                     if (x0$2 != null) {
                        Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$2);
                        if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(4) == 0) {
                           Object label = ((SeqOps)var3.get()).apply(0);
                           Object weight = ((SeqOps)var3.get()).apply(1);
                           Object offset = ((SeqOps)var3.get()).apply(2);
                           Object features = ((SeqOps)var3.get()).apply(3);
                           if (label instanceof Double) {
                              double var8 = BoxesRunTime.unboxToDouble(label);
                              if (weight instanceof Double) {
                                 double var10 = BoxesRunTime.unboxToDouble(weight);
                                 if (offset instanceof Double) {
                                    double var12 = BoxesRunTime.unboxToDouble(offset);
                                    if (features instanceof Vector) {
                                       Vector var14 = (Vector)features;
                                       return new OffsetInstance(var8, var10, var12, var14);
                                    }
                                 }
                              }
                           }
                        }
                     }

                     throw new MatchError(x0$2);
                  }, scala.reflect.ClassTag..MODULE$.apply(OffsetInstance.class));
                  WeightedLeastSquaresModel initialModel = familyAndLink.initialize(instances, BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), BoxesRunTime.unboxToDouble(this.$(this.regParam())), OptionalInstrumentation$.MODULE$.create(instr), BoxesRunTime.unboxToInt(this.$(this.aggregationDepth())));
                  IterativelyReweightedLeastSquares optimizer = new IterativelyReweightedLeastSquares(initialModel, (instance, modelxxx) -> familyAndLink.reweightFunc(instance, modelxxx), BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), BoxesRunTime.unboxToDouble(this.$(this.regParam())), BoxesRunTime.unboxToInt(this.$(this.maxIter())), BoxesRunTime.unboxToDouble(this.$(this.tol())));
                  IterativelyReweightedLeastSquaresModel irlsModel = optimizer.fit(instances, OptionalInstrumentation$.MODULE$.create(instr), optimizer.fit$default$3());
                  GeneralizedLinearRegressionModel model = (GeneralizedLinearRegressionModel)this.copyValues((new GeneralizedLinearRegressionModel(this.uid(), irlsModel.coefficients(), irlsModel.intercept())).setParent(this), this.copyValues$default$2());
                  GeneralizedLinearRegressionTrainingSummary trainingSummary = new GeneralizedLinearRegressionTrainingSummary(dataset, model, irlsModel.diagInvAtWA().toArray(), irlsModel.numIterations(), this.getSolver());
                  var22 = (GeneralizedLinearRegressionModel)model.setSummary(new Some(trainingSummary));
                  break label37;
               }

               RDD instances = validated.rdd().map((x0$1) -> {
                  if (x0$1 != null) {
                     Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
                     if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(4) == 0) {
                        Object label = ((SeqOps)var3.get()).apply(0);
                        Object weight = ((SeqOps)var3.get()).apply(1);
                        Object offset = ((SeqOps)var3.get()).apply(2);
                        Object features = ((SeqOps)var3.get()).apply(3);
                        if (label instanceof Double) {
                           double var8 = BoxesRunTime.unboxToDouble(label);
                           if (weight instanceof Double) {
                              double var10 = BoxesRunTime.unboxToDouble(weight);
                              if (offset instanceof Double) {
                                 double var12 = BoxesRunTime.unboxToDouble(offset);
                                 if (features instanceof Vector) {
                                    Vector var14 = (Vector)features;
                                    return new Instance(var8 - var12, var10, var14);
                                 }
                              }
                           }
                        }
                     }
                  }

                  throw new MatchError(x0$1);
               }, scala.reflect.ClassTag..MODULE$.apply(Instance.class));
               WeightedLeastSquares optimizer = new WeightedLeastSquares(BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), BoxesRunTime.unboxToDouble(this.$(this.regParam())), (double)0.0F, true, true, WeightedLeastSquares$.MODULE$.$lessinit$greater$default$6(), WeightedLeastSquares$.MODULE$.$lessinit$greater$default$7(), WeightedLeastSquares$.MODULE$.$lessinit$greater$default$8());
               WeightedLeastSquaresModel wlsModel = optimizer.fit(instances, OptionalInstrumentation$.MODULE$.create(instr), BoxesRunTime.unboxToInt(this.$(this.aggregationDepth())));
               GeneralizedLinearRegressionModel model = (GeneralizedLinearRegressionModel)this.copyValues((new GeneralizedLinearRegressionModel(this.uid(), wlsModel.coefficients(), wlsModel.intercept())).setParent(this), this.copyValues$default$2());
               GeneralizedLinearRegressionTrainingSummary trainingSummary = new GeneralizedLinearRegressionTrainingSummary(dataset, model, wlsModel.diagInvAtWA().toArray(), 1, this.getSolver());
               var22 = (GeneralizedLinearRegressionModel)model.setSummary(new Some(trainingSummary));
            }

            GeneralizedLinearRegressionModel model = var22;
            return model;
         }
      });
   }

   public GeneralizedLinearRegression copy(final ParamMap extra) {
      return (GeneralizedLinearRegression)this.defaultCopy(extra);
   }

   public GeneralizedLinearRegression(final String uid) {
      this.uid = uid;
      HasFitIntercept.$init$(this);
      HasMaxIter.$init$(this);
      HasTol.$init$(this);
      HasRegParam.$init$(this);
      HasWeightCol.$init$(this);
      HasSolver.$init$(this);
      HasAggregationDepth.$init$(this);
      GeneralizedLinearRegressionBase.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public GeneralizedLinearRegression() {
      this(Identifiable$.MODULE$.randomUID("glm"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class FamilyAndLink implements Serializable {
      private final Family family;
      private final Link link;

      public Family family() {
         return this.family;
      }

      public Link link() {
         return this.link;
      }

      public double predict(final double mu) {
         return this.link().link(this.family().project(mu));
      }

      public double fitted(final double eta) {
         return this.family().project(this.link().unlink(eta));
      }

      public WeightedLeastSquaresModel initialize(final RDD instances, final boolean fitIntercept, final double regParam, final OptionalInstrumentation instr, final int depth) {
         RDD newInstances = instances.map((instance) -> {
            double mu = this.family().initialize(instance.label(), instance.weight());
            double eta = this.predict(mu) - instance.offset();
            return new Instance(eta, instance.weight(), instance.features());
         }, scala.reflect.ClassTag..MODULE$.apply(Instance.class));
         WeightedLeastSquaresModel initialModel = (new WeightedLeastSquares(fitIntercept, regParam, (double)0.0F, true, true, WeightedLeastSquares$.MODULE$.$lessinit$greater$default$6(), WeightedLeastSquares$.MODULE$.$lessinit$greater$default$7(), WeightedLeastSquares$.MODULE$.$lessinit$greater$default$8())).fit(newInstances, instr, depth);
         return initialModel;
      }

      public OptionalInstrumentation initialize$default$4() {
         return OptionalInstrumentation$.MODULE$.create(GeneralizedLinearRegression.class);
      }

      public int initialize$default$5() {
         return 2;
      }

      public Tuple2 reweightFunc(final OffsetInstance instance, final WeightedLeastSquaresModel model) {
         double eta = model.predict(instance.features()) + instance.offset();
         double mu = this.fitted(eta);
         double newLabel = eta - instance.offset() + (instance.label() - mu) * this.link().deriv(mu);
         double newWeight = instance.weight() / (scala.math.package..MODULE$.pow(this.link().deriv(mu), (double)2.0F) * this.family().variance(mu));
         return new Tuple2.mcDD.sp(newLabel, newWeight);
      }

      public FamilyAndLink(final Family family, final Link link) {
         this.family = family;
         this.link = link;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class FamilyAndLink$ implements Serializable {
      public static final FamilyAndLink$ MODULE$ = new FamilyAndLink$();

      public FamilyAndLink apply(final GeneralizedLinearRegressionBase params) {
         Family familyObj;
         Link var7;
         label33: {
            label35: {
               label31: {
                  familyObj = GeneralizedLinearRegression.Family$.MODULE$.fromParams(params);
                  String var10000 = params.getFamily().toLowerCase(Locale.ROOT);
                  String var4 = "tweedie";
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label31;
                     }
                  } else if (var10000.equals(var4)) {
                     break label31;
                  }

                  if (params.isSet(params.link())) {
                     break label35;
                  }
               }

               label25: {
                  String var6 = params.getFamily().toLowerCase(Locale.ROOT);
                  String var5 = "tweedie";
                  if (var6 == null) {
                     if (var5 != null) {
                        break label25;
                     }
                  } else if (!var6.equals(var5)) {
                     break label25;
                  }

                  if (params.isSet(params.linkPower())) {
                     break label35;
                  }
               }

               var7 = familyObj.defaultLink();
               break label33;
            }

            var7 = GeneralizedLinearRegression.Link$.MODULE$.fromParams(params);
         }

         Link linkObj = var7;
         return new FamilyAndLink(familyObj, linkObj);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(FamilyAndLink$.class);
      }
   }

   public abstract static class Family implements Serializable {
      private final String name;

      public String name() {
         return this.name;
      }

      public abstract Link defaultLink();

      public abstract double initialize(final double y, final double weight);

      public abstract double variance(final double mu);

      public abstract double deviance(final double y, final double mu, final double weight);

      public abstract double aic(final RDD predictions, final double deviance, final double numInstances, final double weightSum);

      public double project(final double mu) {
         return mu;
      }

      public Family(final String name) {
         this.name = name;
      }
   }

   public static class Family$ implements Serializable {
      public static final Family$ MODULE$ = new Family$();

      public Family fromParams(final GeneralizedLinearRegressionBase params) {
         String var4 = params.getFamily().toLowerCase(Locale.ROOT);
         String var10000 = GeneralizedLinearRegression.Gaussian$.MODULE$.name();
         if (var10000 == null) {
            if (var4 == null) {
               return GeneralizedLinearRegression.Gaussian$.MODULE$;
            }
         } else if (var10000.equals(var4)) {
            return GeneralizedLinearRegression.Gaussian$.MODULE$;
         }

         var10000 = GeneralizedLinearRegression.Binomial$.MODULE$.name();
         if (var10000 == null) {
            if (var4 == null) {
               return GeneralizedLinearRegression.Binomial$.MODULE$;
            }
         } else if (var10000.equals(var4)) {
            return GeneralizedLinearRegression.Binomial$.MODULE$;
         }

         var10000 = GeneralizedLinearRegression.Poisson$.MODULE$.name();
         if (var10000 == null) {
            if (var4 == null) {
               return GeneralizedLinearRegression.Poisson$.MODULE$;
            }
         } else if (var10000.equals(var4)) {
            return GeneralizedLinearRegression.Poisson$.MODULE$;
         }

         var10000 = GeneralizedLinearRegression.Gamma$.MODULE$.name();
         if (var10000 == null) {
            if (var4 == null) {
               return GeneralizedLinearRegression.Gamma$.MODULE$;
            }
         } else if (var10000.equals(var4)) {
            return GeneralizedLinearRegression.Gamma$.MODULE$;
         }

         if ("tweedie".equals(var4)) {
            double var9 = params.getVariancePower();
            if ((double)0.0F == var9) {
               return GeneralizedLinearRegression.Gaussian$.MODULE$;
            } else if ((double)1.0F == var9) {
               return GeneralizedLinearRegression.Poisson$.MODULE$;
            } else if ((double)2.0F == var9) {
               return GeneralizedLinearRegression.Gamma$.MODULE$;
            } else {
               return new Tweedie(var9);
            }
         } else {
            throw new MatchError(var4);
         }
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Family$.class);
      }
   }

   public static class Tweedie extends Family {
      private final double variancePower;
      private final Link defaultLink;

      public double variancePower() {
         return this.variancePower;
      }

      public Link defaultLink() {
         return this.defaultLink;
      }

      public double initialize(final double y, final double weight) {
         if (this.variancePower() >= (double)1.0F && this.variancePower() < (double)2.0F) {
            scala.Predef..MODULE$.require(y >= (double)0.0F, () -> {
               String var10000 = this.name();
               return "The response variable of " + var10000 + "(" + this.variancePower() + ") family should be non-negative, but got " + y;
            });
         } else if (this.variancePower() >= (double)2.0F) {
            scala.Predef..MODULE$.require(y > (double)0.0F, () -> {
               String var10000 = this.name();
               return "The response variable of " + var10000 + "(" + this.variancePower() + ") family should be positive, but got " + y;
            });
         }

         return y == (double)0 ? GeneralizedLinearRegression.Tweedie$.MODULE$.delta() : y;
      }

      public double variance(final double mu) {
         return scala.math.package..MODULE$.pow(mu, this.variancePower());
      }

      private double yp(final double y, final double mu, final double p) {
         return p == (double)0 ? scala.math.package..MODULE$.log(y / mu) : (scala.math.package..MODULE$.pow(y, p) - scala.math.package..MODULE$.pow(mu, p)) / p;
      }

      public double deviance(final double y, final double mu, final double weight) {
         double y1 = this.variancePower() >= (double)1.0F && this.variancePower() < (double)2.0F ? scala.math.package..MODULE$.max(y, GeneralizedLinearRegression.Tweedie$.MODULE$.delta()) : y;
         return (double)2.0F * weight * (y * this.yp(y1, mu, (double)1.0F - this.variancePower()) - this.yp(y, mu, (double)2.0F - this.variancePower()));
      }

      public double aic(final RDD predictions, final double deviance, final double numInstances, final double weightSum) {
         throw new UnsupportedOperationException("No AIC available for the tweedie family");
      }

      public double project(final double mu) {
         if (mu < GeneralizedLinearRegression$.MODULE$.epsilon()) {
            return GeneralizedLinearRegression$.MODULE$.epsilon();
         } else {
            return scala.runtime.RichDouble..MODULE$.isInfinity$extension(scala.Predef..MODULE$.doubleWrapper(mu)) ? Double.MAX_VALUE : mu;
         }
      }

      public Tweedie(final double variancePower) {
         super("tweedie");
         this.variancePower = variancePower;
         this.defaultLink = new Power((double)1.0F - variancePower);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Tweedie$ implements Serializable {
      public static final Tweedie$ MODULE$ = new Tweedie$();
      private static final double delta = 0.1;

      public double delta() {
         return delta;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Tweedie$.class);
      }
   }

   public static class Gaussian$ extends Tweedie {
      public static final Gaussian$ MODULE$ = new Gaussian$();
      private static final String name = "gaussian";
      private static final Link defaultLink;

      static {
         defaultLink = GeneralizedLinearRegression.Identity$.MODULE$;
      }

      public String name() {
         return name;
      }

      public Link defaultLink() {
         return defaultLink;
      }

      public double initialize(final double y, final double weight) {
         return y;
      }

      public double variance(final double mu) {
         return (double)1.0F;
      }

      public double deviance(final double y, final double mu, final double weight) {
         return weight * (y - mu) * (y - mu);
      }

      public double aic(final RDD predictions, final double deviance, final double numInstances, final double weightSum) {
         double wt = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(predictions.map((x) -> BoxesRunTime.boxToDouble($anonfun$aic$1(x)), scala.reflect.ClassTag..MODULE$.Double())).sum();
         return numInstances * (scala.math.package..MODULE$.log(deviance / numInstances * (double)2.0F * Math.PI) + (double)1.0F) + (double)2.0F - wt;
      }

      public double project(final double mu) {
         if (scala.runtime.RichDouble..MODULE$.isNegInfinity$extension(scala.Predef..MODULE$.doubleWrapper(mu))) {
            return -Double.MAX_VALUE;
         } else {
            return scala.runtime.RichDouble..MODULE$.isPosInfinity$extension(scala.Predef..MODULE$.doubleWrapper(mu)) ? Double.MAX_VALUE : mu;
         }
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Gaussian$.class);
      }

      // $FF: synthetic method
      public static final double $anonfun$aic$1(final Tuple3 x) {
         return scala.math.package..MODULE$.log(BoxesRunTime.unboxToDouble(x._3()));
      }

      public Gaussian$() {
         super((double)0.0F);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Binomial$ extends Family {
      public static final Binomial$ MODULE$ = new Binomial$();
      private static final Link defaultLink;

      static {
         defaultLink = GeneralizedLinearRegression.Logit$.MODULE$;
      }

      public Link defaultLink() {
         return defaultLink;
      }

      public double initialize(final double y, final double weight) {
         double mu = (weight * y + (double)0.5F) / (weight + (double)1.0F);
         scala.Predef..MODULE$.require(mu > (double)0.0F && mu < (double)1.0F, () -> "The response variable of Binomial familyshould be in range (0, 1), but got " + mu);
         return mu;
      }

      public double variance(final double mu) {
         return mu * ((double)1.0F - mu);
      }

      public double deviance(final double y, final double mu, final double weight) {
         return (double)2.0F * weight * (GeneralizedLinearRegression$.MODULE$.ylogy(y, mu) + GeneralizedLinearRegression$.MODULE$.ylogy((double)1.0F - y, (double)1.0F - mu));
      }

      public double aic(final RDD predictions, final double deviance, final double numInstances, final double weightSum) {
         return (double)-2.0F * org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(predictions.map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$aic$2(x0$1)), scala.reflect.ClassTag..MODULE$.Double())).sum();
      }

      public double project(final double mu) {
         if (mu < GeneralizedLinearRegression$.MODULE$.epsilon()) {
            return GeneralizedLinearRegression$.MODULE$.epsilon();
         } else {
            return mu > (double)1.0F - GeneralizedLinearRegression$.MODULE$.epsilon() ? (double)1.0F - GeneralizedLinearRegression$.MODULE$.epsilon() : mu;
         }
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Binomial$.class);
      }

      // $FF: synthetic method
      public static final double $anonfun$aic$2(final Tuple3 x0$1) {
         if (x0$1 != null) {
            double y = BoxesRunTime.unboxToDouble(x0$1._1());
            double mu = BoxesRunTime.unboxToDouble(x0$1._2());
            double weight = BoxesRunTime.unboxToDouble(x0$1._3());
            if (true && true && true) {
               int wt = (int)scala.math.package..MODULE$.round(weight);
               if (wt == 0) {
                  return (double)0.0F;
               }

               return (new Binomial(wt, mu, breeze.stats.distributions.Rand.FixedSeed..MODULE$.randBasis())).logProbabilityOf((int)scala.math.package..MODULE$.round(y * weight));
            }
         }

         throw new MatchError(x0$1);
      }

      public Binomial$() {
         super("binomial");
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Poisson$ extends Tweedie {
      public static final Poisson$ MODULE$ = new Poisson$();
      private static final String name = "poisson";
      private static final Link defaultLink;

      static {
         defaultLink = GeneralizedLinearRegression.Log$.MODULE$;
      }

      public String name() {
         return name;
      }

      public Link defaultLink() {
         return defaultLink;
      }

      public double initialize(final double y, final double weight) {
         scala.Predef..MODULE$.require(y >= (double)0.0F, () -> "The response variable of Poisson family should be non-negative, but got " + y);
         return scala.math.package..MODULE$.max(y, GeneralizedLinearRegression.Tweedie$.MODULE$.delta());
      }

      public double variance(final double mu) {
         return mu;
      }

      public double deviance(final double y, final double mu, final double weight) {
         return (double)2.0F * weight * (GeneralizedLinearRegression$.MODULE$.ylogy(y, mu) - (y - mu));
      }

      public double aic(final RDD predictions, final double deviance, final double numInstances, final double weightSum) {
         return (double)-2.0F * org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(predictions.map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$aic$3(x0$1)), scala.reflect.ClassTag..MODULE$.Double())).sum();
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Poisson$.class);
      }

      // $FF: synthetic method
      public static final double $anonfun$aic$3(final Tuple3 x0$1) {
         if (x0$1 != null) {
            double y = BoxesRunTime.unboxToDouble(x0$1._1());
            double mu = BoxesRunTime.unboxToDouble(x0$1._2());
            double weight = BoxesRunTime.unboxToDouble(x0$1._3());
            if (true && true && true) {
               return weight * (new Poisson(mu, breeze.stats.distributions.Rand.FixedSeed..MODULE$.randBasis())).logProbabilityOf((int)y);
            }
         }

         throw new MatchError(x0$1);
      }

      public Poisson$() {
         super((double)1.0F);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Gamma$ extends Tweedie {
      public static final Gamma$ MODULE$ = new Gamma$();
      private static final String name = "gamma";
      private static final Link defaultLink;

      static {
         defaultLink = GeneralizedLinearRegression.Inverse$.MODULE$;
      }

      public String name() {
         return name;
      }

      public Link defaultLink() {
         return defaultLink;
      }

      public double initialize(final double y, final double weight) {
         scala.Predef..MODULE$.require(y > (double)0.0F, () -> "The response variable of Gamma family should be positive, but got " + y);
         return y;
      }

      public double variance(final double mu) {
         return mu * mu;
      }

      public double deviance(final double y, final double mu, final double weight) {
         return (double)-2.0F * weight * (scala.math.package..MODULE$.log(y / mu) - (y - mu) / mu);
      }

      public double aic(final RDD predictions, final double deviance, final double numInstances, final double weightSum) {
         double disp = deviance / weightSum;
         return (double)-2.0F * org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(predictions.map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$aic$4(disp, x0$1)), scala.reflect.ClassTag..MODULE$.Double())).sum() + (double)2.0F;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Gamma$.class);
      }

      // $FF: synthetic method
      public static final double $anonfun$aic$4(final double disp$1, final Tuple3 x0$1) {
         if (x0$1 != null) {
            double y = BoxesRunTime.unboxToDouble(x0$1._1());
            double mu = BoxesRunTime.unboxToDouble(x0$1._2());
            double weight = BoxesRunTime.unboxToDouble(x0$1._3());
            if (true && true && true) {
               return weight * (new Gamma((double)1.0F / disp$1, mu * disp$1, breeze.stats.distributions.Rand.FixedSeed..MODULE$.randBasis())).logPdf(BoxesRunTime.boxToDouble(y));
            }
         }

         throw new MatchError(x0$1);
      }

      public Gamma$() {
         super((double)2.0F);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public abstract static class Link implements Serializable {
      private final String name;

      public String name() {
         return this.name;
      }

      public abstract double link(final double mu);

      public abstract double deriv(final double mu);

      public abstract double unlink(final double eta);

      public Link(final String name) {
         this.name = name;
      }
   }

   public static class Link$ implements Serializable {
      public static final Link$ MODULE$ = new Link$();

      public Link fromParams(final GeneralizedLinearRegressionBase params) {
         label168: {
            String var10000 = params.getFamily().toLowerCase(Locale.ROOT);
            String var4 = "tweedie";
            if (var10000 == null) {
               if (var4 == null) {
                  break label168;
               }
            } else if (var10000.equals(var4)) {
               break label168;
            }

            String var7 = params.getLink().toLowerCase(Locale.ROOT);
            var10000 = GeneralizedLinearRegression.Identity$.MODULE$.name();
            if (var10000 == null) {
               if (var7 == null) {
                  return GeneralizedLinearRegression.Identity$.MODULE$;
               }
            } else if (var10000.equals(var7)) {
               return GeneralizedLinearRegression.Identity$.MODULE$;
            }

            var10000 = GeneralizedLinearRegression.Logit$.MODULE$.name();
            if (var10000 == null) {
               if (var7 == null) {
                  return GeneralizedLinearRegression.Logit$.MODULE$;
               }
            } else if (var10000.equals(var7)) {
               return GeneralizedLinearRegression.Logit$.MODULE$;
            }

            var10000 = GeneralizedLinearRegression.Log$.MODULE$.name();
            if (var10000 == null) {
               if (var7 == null) {
                  return GeneralizedLinearRegression.Log$.MODULE$;
               }
            } else if (var10000.equals(var7)) {
               return GeneralizedLinearRegression.Log$.MODULE$;
            }

            var10000 = GeneralizedLinearRegression.Inverse$.MODULE$.name();
            if (var10000 == null) {
               if (var7 == null) {
                  return GeneralizedLinearRegression.Inverse$.MODULE$;
               }
            } else if (var10000.equals(var7)) {
               return GeneralizedLinearRegression.Inverse$.MODULE$;
            }

            var10000 = GeneralizedLinearRegression.Probit$.MODULE$.name();
            if (var10000 == null) {
               if (var7 == null) {
                  return GeneralizedLinearRegression.Probit$.MODULE$;
               }
            } else if (var10000.equals(var7)) {
               return GeneralizedLinearRegression.Probit$.MODULE$;
            }

            var10000 = GeneralizedLinearRegression.CLogLog$.MODULE$.name();
            if (var10000 == null) {
               if (var7 == null) {
                  return GeneralizedLinearRegression.CLogLog$.MODULE$;
               }
            } else if (var10000.equals(var7)) {
               return GeneralizedLinearRegression.CLogLog$.MODULE$;
            }

            var10000 = GeneralizedLinearRegression.Sqrt$.MODULE$.name();
            if (var10000 == null) {
               if (var7 == null) {
                  return GeneralizedLinearRegression.Sqrt$.MODULE$;
               }
            } else if (var10000.equals(var7)) {
               return GeneralizedLinearRegression.Sqrt$.MODULE$;
            }

            throw new MatchError(var7);
         }

         double var5 = params.getLinkPower();
         if ((double)0.0F == var5) {
            return GeneralizedLinearRegression.Log$.MODULE$;
         } else if ((double)1.0F == var5) {
            return GeneralizedLinearRegression.Identity$.MODULE$;
         } else if ((double)-1.0F == var5) {
            return GeneralizedLinearRegression.Inverse$.MODULE$;
         } else {
            return (Link)((double)0.5F == var5 ? GeneralizedLinearRegression.Sqrt$.MODULE$ : new Power(var5));
         }
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Link$.class);
      }
   }

   public static class Power extends Link {
      private final double linkPower;

      public double linkPower() {
         return this.linkPower;
      }

      public double link(final double mu) {
         return this.linkPower() == (double)0.0F ? scala.math.package..MODULE$.log(mu) : scala.math.package..MODULE$.pow(mu, this.linkPower());
      }

      public double deriv(final double mu) {
         return this.linkPower() == (double)0.0F ? (double)1.0F / mu : this.linkPower() * scala.math.package..MODULE$.pow(mu, this.linkPower() - (double)1.0F);
      }

      public double unlink(final double eta) {
         return this.linkPower() == (double)0.0F ? scala.math.package..MODULE$.exp(eta) : scala.math.package..MODULE$.pow(eta, (double)1.0F / this.linkPower());
      }

      public Power(final double linkPower) {
         super("power");
         this.linkPower = linkPower;
      }
   }

   public static class Identity$ extends Power {
      public static final Identity$ MODULE$ = new Identity$();
      private static final String name = "identity";

      public String name() {
         return name;
      }

      public double link(final double mu) {
         return mu;
      }

      public double deriv(final double mu) {
         return (double)1.0F;
      }

      public double unlink(final double eta) {
         return eta;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Identity$.class);
      }

      public Identity$() {
         super((double)1.0F);
      }
   }

   public static class Logit$ extends Link {
      public static final Logit$ MODULE$ = new Logit$();

      public double link(final double mu) {
         return scala.math.package..MODULE$.log(mu / ((double)1.0F - mu));
      }

      public double deriv(final double mu) {
         return (double)1.0F / (mu * ((double)1.0F - mu));
      }

      public double unlink(final double eta) {
         return (double)1.0F / ((double)1.0F + scala.math.package..MODULE$.exp((double)-1.0F * eta));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Logit$.class);
      }

      public Logit$() {
         super("logit");
      }
   }

   public static class Log$ extends Power {
      public static final Log$ MODULE$ = new Log$();
      private static final String name = "log";

      public String name() {
         return name;
      }

      public double link(final double mu) {
         return scala.math.package..MODULE$.log(mu);
      }

      public double deriv(final double mu) {
         return (double)1.0F / mu;
      }

      public double unlink(final double eta) {
         return scala.math.package..MODULE$.exp(eta);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Log$.class);
      }

      public Log$() {
         super((double)0.0F);
      }
   }

   public static class Inverse$ extends Power {
      public static final Inverse$ MODULE$ = new Inverse$();
      private static final String name = "inverse";

      public String name() {
         return name;
      }

      public double link(final double mu) {
         return (double)1.0F / mu;
      }

      public double deriv(final double mu) {
         return (double)-1.0F * scala.math.package..MODULE$.pow(mu, (double)-2.0F);
      }

      public double unlink(final double eta) {
         return (double)1.0F / eta;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Inverse$.class);
      }

      public Inverse$() {
         super((double)-1.0F);
      }
   }

   public static class Probit$ extends Link {
      public static final Probit$ MODULE$ = new Probit$();

      public double link(final double mu) {
         return (new Gaussian((double)0.0F, (double)1.0F, breeze.stats.distributions.Rand.FixedSeed..MODULE$.randBasis())).inverseCdf(mu);
      }

      public double deriv(final double mu) {
         return (double)1.0F / (new Gaussian((double)0.0F, (double)1.0F, breeze.stats.distributions.Rand.FixedSeed..MODULE$.randBasis())).pdf(BoxesRunTime.boxToDouble((new Gaussian((double)0.0F, (double)1.0F, breeze.stats.distributions.Rand.FixedSeed..MODULE$.randBasis())).inverseCdf(mu)));
      }

      public double unlink(final double eta) {
         return (new Gaussian((double)0.0F, (double)1.0F, breeze.stats.distributions.Rand.FixedSeed..MODULE$.randBasis())).cdf(eta);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Probit$.class);
      }

      public Probit$() {
         super("probit");
      }
   }

   public static class CLogLog$ extends Link {
      public static final CLogLog$ MODULE$ = new CLogLog$();

      public double link(final double mu) {
         return scala.math.package..MODULE$.log(-scala.math.package..MODULE$.log1p(-mu));
      }

      public double deriv(final double mu) {
         return (double)1.0F / ((mu - (double)1.0F) * scala.math.package..MODULE$.log1p(-mu));
      }

      public double unlink(final double eta) {
         return (double)1.0F - scala.math.package..MODULE$.exp((double)-1.0F * scala.math.package..MODULE$.exp(eta));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(CLogLog$.class);
      }

      public CLogLog$() {
         super("cloglog");
      }
   }

   public static class Sqrt$ extends Power {
      public static final Sqrt$ MODULE$ = new Sqrt$();
      private static final String name = "sqrt";

      public String name() {
         return name;
      }

      public double link(final double mu) {
         return scala.math.package..MODULE$.sqrt(mu);
      }

      public double deriv(final double mu) {
         return (double)1.0F / ((double)2.0F * scala.math.package..MODULE$.sqrt(mu));
      }

      public double unlink(final double eta) {
         return eta * eta;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Sqrt$.class);
      }

      public Sqrt$() {
         super((double)0.5F);
      }
   }
}
