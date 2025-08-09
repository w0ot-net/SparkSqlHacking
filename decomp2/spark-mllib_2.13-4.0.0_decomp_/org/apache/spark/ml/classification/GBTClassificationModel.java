package org.apache.spark.ml.classification;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasCheckpointInterval;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.param.shared.HasValidationIndicatorCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.regression.DecisionTreeRegressionModel;
import org.apache.spark.ml.tree.DecisionTreeModel;
import org.apache.spark.ml.tree.DecisionTreeParams;
import org.apache.spark.ml.tree.EnsembleModelReadWrite$;
import org.apache.spark.ml.tree.GBTClassifierParams;
import org.apache.spark.ml.tree.GBTParams;
import org.apache.spark.ml.tree.HasVarianceImpurity;
import org.apache.spark.ml.tree.Node;
import org.apache.spark.ml.tree.TreeEnsembleClassifierParams;
import org.apache.spark.ml.tree.TreeEnsembleModel;
import org.apache.spark.ml.tree.TreeEnsembleModel$;
import org.apache.spark.ml.tree.TreeEnsembleParams;
import org.apache.spark.ml.tree.impl.GradientBoostedTrees$;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.BoostingStrategy;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.mllib.tree.loss.ClassificationLoss;
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.json4s.DefaultFormats;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.Enumeration;
import scala.Function1;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.StringOps.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\reb\u0001B\u001d;\u0001\u0015C\u0001\u0002\u001e\u0001\u0003\u0006\u0004%\t%\u001e\u0005\n\u0003\u001f\u0001!\u0011!Q\u0001\nYD!\"a\u0005\u0001\u0005\u000b\u0007I\u0011BA\u000b\u0011)\ty\u0002\u0001B\u0001B\u0003%\u0011q\u0003\u0005\u000b\u0003C\u0001!Q1A\u0005\n\u0005\r\u0002BCA\u0017\u0001\t\u0005\t\u0015!\u0003\u0002&!Q\u0011q\u0006\u0001\u0003\u0006\u0004%\t%!\r\t\u0015\u0005m\u0002A!A!\u0002\u0013\t\u0019\u0004\u0003\u0006\u0002@\u0001\u0011)\u0019!C!\u0003cA!\"a\u0012\u0001\u0005\u0003\u0005\u000b\u0011BA\u001a\u0011!\tY\u0005\u0001C\u0001y\u00055\u0003\u0002CA&\u0001\u0011\u0005A(a\u0018\t\u000f\u0005-\u0003\u0001\"\u0001\u0002j!A\u00111\n\u0001\u0005\u0002q\n\u0019\bC\u0004\u0002v\u0001!\t%!\u0006\t\u0013\u0005u\u0004A1A\u0005\u0002\u0005E\u0002\u0002CAC\u0001\u0001\u0006I!a\r\t\u000f\u0005%\u0005\u0001\"\u0011\u0002$!9\u0011Q\u0012\u0001\u0005B\u0005=\u0005bBAT\u0001\u0011\u0005\u0013\u0011\u0016\u0005\b\u0003W\u0004A\u0011IAw\u0011\u001d\t\u0019\u0010\u0001C!\u0003kDq!a@\u0001\t#\u0012\t\u0001C\u0004\u0003\b\u0001!\tE!\u0003\t\u000f\tu\u0001\u0001\"\u0011\u0003 !Q!1\u0005\u0001\t\u0006\u0004%\tA!\n\t\u000f\t%\u0002\u0001\"\u0003\u0003,!A!q\u0006\u0001\u0005\u0002q\u0012\t\u0004C\u0005\u0003F\u0001\u0011\r\u0011\"\u0003\u0003H!A!1\u000b\u0001!\u0002\u0013\u0011I\u0005C\u0004\u0003V\u0001!\tAa\u0016\t\u000f\t-\u0004\u0001\"\u0011\u0003n\u001d9!\u0011\u0010\u001e\t\u0002\tmdAB\u001d;\u0011\u0003\u0011i\bC\u0004\u0002L\t\"\tA!'\t\u0011\tm%E1A\u0005\nUDqA!(#A\u0003%a\u000f\u0003\u0005\u0003 \n\u0012\r\u0011\"\u0003v\u0011\u001d\u0011\tK\tQ\u0001\nYDqAa)#\t\u0003\u0012)\u000bC\u0004\u00030\n\"\tE!-\u0007\u000f\te&\u0005\u0001\u0012\u0003<\"I!Q\u0018\u0016\u0003\u0002\u0003\u0006I\u0001\u0015\u0005\b\u0003\u0017RC\u0011\u0001B`\u0011\u001d\u00119M\u000bC)\u0005\u00134aAa5#\t\tU\u0007bBA&]\u0011\u0005!q\u001b\u0005\n\u00057t#\u0019!C\u0005\u0005;D\u0001B!;/A\u0003%!q\u001c\u0005\n\u0005Wt#\u0019!C\u0005\u0005;D\u0001B!</A\u0003%!q\u001c\u0005\b\u0005_sC\u0011\tBx\u0011!\u0011\u0019P\tC\u0001y\tU\bBCB\nEE\u0005I\u0011\u0001\u001f\u0004\u0016!Q1\u0011\u0006\u0012\u0012\u0002\u0013\u0005Ah!\u0006\t\u0013\r-\"%!A\u0005\n\r5\"AF$C)\u000ec\u0017m]:jM&\u001c\u0017\r^5p]6{G-\u001a7\u000b\u0005mb\u0014AD2mCN\u001c\u0018NZ5dCRLwN\u001c\u0006\u0003{y\n!!\u001c7\u000b\u0005}\u0002\u0015!B:qCJ\\'BA!C\u0003\u0019\t\u0007/Y2iK*\t1)A\u0002pe\u001e\u001c\u0001a\u0005\u0004\u0001\rF;\u0006M\u001a\t\u0005\u000f\"S\u0005+D\u0001;\u0013\tI%H\u0001\u0011Qe>\u0014\u0017MY5mSN$\u0018nY\"mCN\u001c\u0018NZ5dCRLwN\\'pI\u0016d\u0007CA&O\u001b\u0005a%BA'=\u0003\u0019a\u0017N\\1mO&\u0011q\n\u0014\u0002\u0007-\u0016\u001cGo\u001c:\u0011\u0005\u001d\u0003\u0001C\u0001*V\u001b\u0005\u0019&B\u0001+=\u0003\u0011!(/Z3\n\u0005Y\u001b&aE$C)\u000ec\u0017m]:jM&,'\u000fU1sC6\u001c\bc\u0001*Y5&\u0011\u0011l\u0015\u0002\u0012)J,W-\u00128tK6\u0014G.Z'pI\u0016d\u0007CA._\u001b\u0005a&BA/=\u0003)\u0011Xm\u001a:fgNLwN\\\u0005\u0003?r\u00131\u0004R3dSNLwN\u001c+sK\u0016\u0014Vm\u001a:fgNLwN\\'pI\u0016d\u0007CA1e\u001b\u0005\u0011'BA2=\u0003\u0011)H/\u001b7\n\u0005\u0015\u0014'AC'M/JLG/\u00192mKB\u0011q-\u001d\b\u0003Q:t!!\u001b7\u000e\u0003)T!a\u001b#\u0002\rq\u0012xn\u001c;?\u0013\u0005i\u0017!B:dC2\f\u0017BA8q\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011!\\\u0005\u0003eN\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!a\u001c9\u0002\u0007ULG-F\u0001w!\t98P\u0004\u0002ysB\u0011\u0011\u000e]\u0005\u0003uB\fa\u0001\u0015:fI\u00164\u0017B\u0001?~\u0005\u0019\u0019FO]5oO*\u0011!\u0010\u001d\u0015\u0005\u0003}\fY\u0001\u0005\u0003\u0002\u0002\u0005\u001dQBAA\u0002\u0015\r\t)AP\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0005\u0003\u0007\u0011QaU5oG\u0016\f#!!\u0004\u0002\u000bErcG\f\u0019\u0002\tULG\r\t\u0015\u0005\u0005}\fY!\u0001\u0004`iJ,Wm]\u000b\u0003\u0003/\u0001R!!\u0007\u0002\u001cik\u0011\u0001]\u0005\u0004\u0003;\u0001(!B!se\u0006L\u0018aB0ue\u0016,7\u000fI\u0001\r?R\u0014X-Z,fS\u001eDGo]\u000b\u0003\u0003K\u0001b!!\u0007\u0002\u001c\u0005\u001d\u0002\u0003BA\r\u0003SI1!a\u000bq\u0005\u0019!u.\u001e2mK\u0006iq\f\u001e:fK^+\u0017n\u001a5ug\u0002\n1B\\;n\r\u0016\fG/\u001e:fgV\u0011\u00111\u0007\t\u0005\u00033\t)$C\u0002\u00028A\u00141!\u00138uQ\u00119q0a\u0003\u0002\u00199,XNR3biV\u0014Xm\u001d\u0011)\t!y\u00181B\u0001\u000b]Vl7\t\\1tg\u0016\u001c\b\u0006B\u0005\u0000\u0003\u0007\n#!!\u0012\u0002\u000bIr#G\f\u0019\u0002\u00179,Xn\u00117bgN,7\u000f\t\u0015\u0005\u0015}\f\u0019%\u0001\u0004=S:LGO\u0010\u000b\f!\u0006=\u00131KA+\u0003/\nY\u0006C\u0003u\u0017\u0001\u0007a\u000fK\u0003\u0002P}\fY\u0001C\u0004\u0002\u0014-\u0001\r!a\u0006\t\u000f\u0005\u00052\u00021\u0001\u0002&!9\u0011qF\u0006A\u0002\u0005M\u0002&BA,\u007f\u0006-\u0001bBA \u0017\u0001\u0007\u00111\u0007\u0015\u0006\u00037z\u00181\t\u000b\n!\u0006\u0005\u00141MA3\u0003OBQ\u0001\u001e\u0007A\u0002YDq!a\u0005\r\u0001\u0004\t9\u0002C\u0004\u0002\"1\u0001\r!!\n\t\u000f\u0005=B\u00021\u0001\u00024Q9\u0001+a\u001b\u0002n\u0005=\u0004\"\u0002;\u000e\u0001\u00041\bbBA\n\u001b\u0001\u0007\u0011q\u0003\u0005\b\u0003Ci\u0001\u0019AA\u0013Q\u0011iq0a\u0003\u0015\u0003A\u000bQ\u0001\u001e:fKNDCaD@\u0002z\u0005\u0012\u00111P\u0001\u0006c9\"d\u0006M\u0001\fO\u0016$h*^7Ue\u0016,7\u000f\u000b\u0003\u0011\u007f\u0006\u0005\u0015EAAB\u0003\u0015\u0011d\u0006\r\u00181\u000319W\r\u001e(v[R\u0013X-Z:!Q\u0011\tr0!!\u0002\u0017Q\u0014X-Z,fS\u001eDGo\u001d\u0015\u0005%}\fI(A\bue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011\t\t*!)\u0011\t\u0005M\u0015QT\u0007\u0003\u0003+SA!a&\u0002\u001a\u0006)A/\u001f9fg*\u0019\u00111\u0014 \u0002\u0007M\fH.\u0003\u0003\u0002 \u0006U%AC*ueV\u001cG\u000fV=qK\"9\u00111U\nA\u0002\u0005E\u0015AB:dQ\u0016l\u0017\r\u000b\u0003\u0014\u007f\u0006-\u0011!\u0003;sC:\u001chm\u001c:n)\u0011\tY+a2\u0011\t\u00055\u0016\u0011\u0019\b\u0005\u0003_\u000byL\u0004\u0003\u00022\u0006uf\u0002BAZ\u0003wsA!!.\u0002::\u0019\u0011.a.\n\u0003\rK!!\u0011\"\n\u0005}\u0002\u0015bAAN}%\u0019q.!'\n\t\u0005\r\u0017Q\u0019\u0002\n\t\u0006$\u0018M\u0012:b[\u0016T1a\\AM\u0011\u001d\tI\r\u0006a\u0001\u0003\u0017\fq\u0001Z1uCN,G\u000f\r\u0003\u0002N\u0006e\u0007CBAh\u0003#\f).\u0004\u0002\u0002\u001a&!\u00111[AM\u0005\u001d!\u0015\r^1tKR\u0004B!a6\u0002Z2\u0001A\u0001DAn\u0003\u000f\f\t\u0011!A\u0003\u0002\u0005u'aA0%eE!\u0011q\\As!\u0011\tI\"!9\n\u0007\u0005\r\bOA\u0004O_RD\u0017N\\4\u0011\t\u0005e\u0011q]\u0005\u0004\u0003S\u0004(aA!os\u00069\u0001O]3eS\u000e$H\u0003BA\u0014\u0003_Da!!=\u0016\u0001\u0004Q\u0015\u0001\u00034fCR,(/Z:\u0002\u0015A\u0014X\rZ5diJ\u000bw\u000fF\u0002K\u0003oDa!!=\u0017\u0001\u0004Q\u0005\u0006\u0002\f\u0000\u0003w\f#!!@\u0002\u000bMr\u0003G\f\u0019\u0002-I\fwO\r9s_\n\f'-\u001b7jifLe\u000e\u00157bG\u0016$2A\u0013B\u0002\u0011\u0019\u0011)a\u0006a\u0001\u0015\u0006i!/Y<Qe\u0016$\u0017n\u0019;j_:\fAaY8qsR\u0019\u0001Ka\u0003\t\u000f\t5\u0001\u00041\u0001\u0003\u0010\u0005)Q\r\u001f;sCB!!\u0011\u0003B\f\u001b\t\u0011\u0019BC\u0002\u0003\u0016q\nQ\u0001]1sC6LAA!\u0007\u0003\u0014\tA\u0001+\u0019:b[6\u000b\u0007\u000f\u000b\u0003\u0019\u007f\u0006e\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003YDC!G@\u0002z\u0005\u0011b-Z1ukJ,\u0017*\u001c9peR\fgnY3t+\u0005Q\u0005\u0006\u0002\u000e\u0000\u0003\u0003\u000ba!\\1sO&tG\u0003BA\u0014\u0005[Aa!!=\u001c\u0001\u0004Q\u0015!\u0002;p\u001f2$WC\u0001B\u001a!\u0011\u0011)D!\u0011\u000e\u0005\t]\"\u0002\u0002B\u001d\u0005w\tQ!\\8eK2T1\u0001\u0016B\u001f\u0015\r\u0011yDP\u0001\u0006[2d\u0017NY\u0005\u0005\u0005\u0007\u00129DA\rHe\u0006$\u0017.\u001a8u\u0005>|7\u000f^3e)J,Wm]'pI\u0016d\u0017\u0001\u00027pgN,\"A!\u0013\u0011\t\t-#qJ\u0007\u0003\u0005\u001bRAA!\u0012\u0003<%!!\u0011\u000bB'\u0005I\u0019E.Y:tS\u001aL7-\u0019;j_:dun]:\u0002\u000b1|7o\u001d\u0011\u0002+\u00154\u0018\r\\;bi\u0016,\u0015m\u00195Ji\u0016\u0014\u0018\r^5p]R!\u0011Q\u0005B-\u0011\u001d\tIm\ba\u0001\u00057\u0002DA!\u0018\u0003bA1\u0011qZAi\u0005?\u0002B!a6\u0003b\u0011a!1\rB-\u0003\u0003\u0005\tQ!\u0001\u0002^\n\u0019q\fJ\u001a)\t}y(qM\u0011\u0003\u0005S\nQA\r\u00185]A\nQa\u001e:ji\u0016,\"Aa\u001c\u0011\u0007\u0005\u0014\t(C\u0002\u0003t\t\u0014\u0001\"\u0014'Xe&$XM\u001d\u0015\u0005A}\f\t\t\u000b\u0003\u0001\u007f\u0006-\u0011AF$C)\u000ec\u0017m]:jM&\u001c\u0017\r^5p]6{G-\u001a7\u0011\u0005\u001d\u00133c\u0002\u0012\u0003\u0000\t\u0015%1\u0012\t\u0005\u00033\u0011\t)C\u0002\u0003\u0004B\u0014a!\u00118z%\u00164\u0007\u0003B1\u0003\bBK1A!#c\u0005)iEJU3bI\u0006\u0014G.\u001a\t\u0005\u0005\u001b\u00139*\u0004\u0002\u0003\u0010*!!\u0011\u0013BJ\u0003\tIwN\u0003\u0002\u0003\u0016\u0006!!.\u0019<b\u0013\r\u0011(q\u0012\u000b\u0003\u0005w\naB\\;n\r\u0016\fG/\u001e:fg.+\u00170A\bok64U-\u0019;ve\u0016\u001c8*Z=!\u0003-qW/\u001c+sK\u0016\u001c8*Z=\u0002\u00199,X\u000e\u0016:fKN\\U-\u001f\u0011\u0002\tI,\u0017\rZ\u000b\u0003\u0005O\u0003B!\u0019BU!&\u0019!1\u00162\u0003\u00115c%+Z1eKJDC\u0001K@\u0002\u0002\u0006!An\\1e)\r\u0001&1\u0017\u0005\u0007\u0005kK\u0003\u0019\u0001<\u0002\tA\fG\u000f\u001b\u0015\u0005S}\f\tI\u0001\u000fH\u0005R\u001bE.Y:tS\u001aL7-\u0019;j_:lu\u000eZ3m/JLG/\u001a:\u0014\u0007)\u0012y'\u0001\u0005j]N$\u0018M\\2f)\u0011\u0011\tM!2\u0011\u0007\t\r'&D\u0001#\u0011\u0019\u0011i\f\fa\u0001!\u0006A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0003L\nE\u0007\u0003BA\r\u0005\u001bL1Aa4q\u0005\u0011)f.\u001b;\t\r\tUV\u00061\u0001w\u0005q9%\tV\"mCN\u001c\u0018NZ5dCRLwN\\'pI\u0016d'+Z1eKJ\u001c2A\fBT)\t\u0011I\u000eE\u0002\u0003D:\n\u0011b\u00197bgNt\u0015-\\3\u0016\u0005\t}\u0007\u0003\u0002Bq\u0005Ol!Aa9\u000b\t\t\u0015(1S\u0001\u0005Y\u0006tw-C\u0002}\u0005G\f!b\u00197bgNt\u0015-\\3!\u00035!(/Z3DY\u0006\u001c8OT1nK\u0006qAO]3f\u00072\f7o\u001d(b[\u0016\u0004Cc\u0001)\u0003r\"1!Q\u0017\u001bA\u0002Y\fqA\u001a:p[>cG\rF\u0006Q\u0005o\u0014Yp!\u0002\u0004\u0010\rE\u0001b\u0002B}k\u0001\u0007!1G\u0001\t_2$Wj\u001c3fY\"9!Q`\u001bA\u0002\t}\u0018A\u00029be\u0016tG\u000fE\u0002H\u0007\u0003I1aa\u0001;\u000559%\tV\"mCN\u001c\u0018NZ5fe\"91qA\u001bA\u0002\r%\u0011aE2bi\u0016<wN]5dC24U-\u0019;ve\u0016\u001c\bcB<\u0004\f\u0005M\u00121G\u0005\u0004\u0007\u001bi(aA'ba\"I\u0011qF\u001b\u0011\u0002\u0003\u0007\u00111\u0007\u0005\n\u0003\u007f)\u0004\u0013!a\u0001\u0003g\t\u0011C\u001a:p[>cG\r\n3fM\u0006,H\u000e\u001e\u00135+\t\u00199B\u000b\u0003\u00024\re1FAB\u000e!\u0011\u0019ib!\n\u000e\u0005\r}!\u0002BB\u0011\u0007G\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0015\u0001/\u0003\u0003\u0004(\r}!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006\tbM]8n\u001f2$G\u0005Z3gCVdG\u000fJ\u001b\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\r=\u0002\u0003\u0002Bq\u0007cIAaa\r\u0003d\n1qJ\u00196fGRDCAI@\u0002\u0002\"\"\u0011e`AA\u0001"
)
public class GBTClassificationModel extends ProbabilisticClassificationModel implements GBTClassifierParams, TreeEnsembleModel, MLWritable {
   private Vector featureImportances;
   private final String uid;
   private final DecisionTreeRegressionModel[] _trees;
   private final double[] _treeWeights;
   private final int numFeatures;
   private final int numClasses;
   private final int getNumTrees;
   private final ClassificationLoss loss;
   private int totalNumNodes;
   private Param lossType;
   private Param impurity;
   private DoubleParam validationTol;
   private DoubleParam stepSize;
   private Param validationIndicatorCol;
   private IntParam maxIter;
   private DoubleParam subsamplingRate;
   private Param featureSubsetStrategy;
   private Param leafCol;
   private IntParam maxDepth;
   private IntParam maxBins;
   private IntParam minInstancesPerNode;
   private DoubleParam minWeightFractionPerNode;
   private DoubleParam minInfoGain;
   private IntParam maxMemoryInMB;
   private BooleanParam cacheNodeIds;
   private Param weightCol;
   private LongParam seed;
   private IntParam checkpointInterval;
   private volatile byte bitmap$0;

   public static GBTClassificationModel load(final String path) {
      return GBTClassificationModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return GBTClassificationModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public DecisionTreeModel getTree(final int i) {
      return TreeEnsembleModel.getTree$(this, i);
   }

   public Vector javaTreeWeights() {
      return TreeEnsembleModel.javaTreeWeights$(this);
   }

   public String toDebugString() {
      return TreeEnsembleModel.toDebugString$(this);
   }

   public Vector predictLeaf(final Vector features) {
      return TreeEnsembleModel.predictLeaf$(this, features);
   }

   public StructField getLeafField(final String leafCol) {
      return TreeEnsembleModel.getLeafField$(this, leafCol);
   }

   public String getLossType() {
      return GBTClassifierParams.getLossType$(this);
   }

   public ClassificationLoss getOldLossType() {
      return GBTClassifierParams.getOldLossType$(this);
   }

   public final String getImpurity() {
      return HasVarianceImpurity.getImpurity$(this);
   }

   public Impurity getOldImpurity() {
      return HasVarianceImpurity.getOldImpurity$(this);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$tree$TreeEnsembleClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ProbabilisticClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return TreeEnsembleClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   // $FF: synthetic method
   public Strategy org$apache$spark$ml$tree$GBTParams$$super$getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity) {
      return TreeEnsembleParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity);
   }

   public final double getValidationTol() {
      return GBTParams.getValidationTol$(this);
   }

   public BoostingStrategy getOldBoostingStrategy(final Map categoricalFeatures, final Enumeration.Value oldAlgo) {
      return GBTParams.getOldBoostingStrategy$(this, categoricalFeatures, oldAlgo);
   }

   public final String getValidationIndicatorCol() {
      return HasValidationIndicatorCol.getValidationIndicatorCol$(this);
   }

   public final double getStepSize() {
      return HasStepSize.getStepSize$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   // $FF: synthetic method
   public Strategy org$apache$spark$ml$tree$TreeEnsembleParams$$super$getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity, final double subsamplingRate) {
      return DecisionTreeParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity, subsamplingRate);
   }

   public final double getSubsamplingRate() {
      return TreeEnsembleParams.getSubsamplingRate$(this);
   }

   public Strategy getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity) {
      return TreeEnsembleParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity);
   }

   public final String getFeatureSubsetStrategy() {
      return TreeEnsembleParams.getFeatureSubsetStrategy$(this);
   }

   public final DecisionTreeParams setLeafCol(final String value) {
      return DecisionTreeParams.setLeafCol$(this, value);
   }

   public final String getLeafCol() {
      return DecisionTreeParams.getLeafCol$(this);
   }

   public final int getMaxDepth() {
      return DecisionTreeParams.getMaxDepth$(this);
   }

   public final int getMaxBins() {
      return DecisionTreeParams.getMaxBins$(this);
   }

   public final int getMinInstancesPerNode() {
      return DecisionTreeParams.getMinInstancesPerNode$(this);
   }

   public final double getMinWeightFractionPerNode() {
      return DecisionTreeParams.getMinWeightFractionPerNode$(this);
   }

   public final double getMinInfoGain() {
      return DecisionTreeParams.getMinInfoGain$(this);
   }

   public final int getMaxMemoryInMB() {
      return DecisionTreeParams.getMaxMemoryInMB$(this);
   }

   public final boolean getCacheNodeIds() {
      return DecisionTreeParams.getCacheNodeIds$(this);
   }

   public Strategy getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity, final double subsamplingRate) {
      return DecisionTreeParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity, subsamplingRate);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final int getCheckpointInterval() {
      return HasCheckpointInterval.getCheckpointInterval$(this);
   }

   private int totalNumNodes$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.totalNumNodes = TreeEnsembleModel.totalNumNodes$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.totalNumNodes;
   }

   public int totalNumNodes() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.totalNumNodes$lzycompute() : this.totalNumNodes;
   }

   public Param lossType() {
      return this.lossType;
   }

   public void org$apache$spark$ml$tree$GBTClassifierParams$_setter_$lossType_$eq(final Param x$1) {
      this.lossType = x$1;
   }

   public final Param impurity() {
      return this.impurity;
   }

   public final void org$apache$spark$ml$tree$HasVarianceImpurity$_setter_$impurity_$eq(final Param x$1) {
      this.impurity = x$1;
   }

   public final DoubleParam validationTol() {
      return this.validationTol;
   }

   public final DoubleParam stepSize() {
      return this.stepSize;
   }

   public final void org$apache$spark$ml$tree$GBTParams$_setter_$validationTol_$eq(final DoubleParam x$1) {
      this.validationTol = x$1;
   }

   public final void org$apache$spark$ml$tree$GBTParams$_setter_$stepSize_$eq(final DoubleParam x$1) {
      this.stepSize = x$1;
   }

   public final Param validationIndicatorCol() {
      return this.validationIndicatorCol;
   }

   public final void org$apache$spark$ml$param$shared$HasValidationIndicatorCol$_setter_$validationIndicatorCol_$eq(final Param x$1) {
      this.validationIndicatorCol = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasStepSize$_setter_$stepSize_$eq(final DoubleParam x$1) {
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public final DoubleParam subsamplingRate() {
      return this.subsamplingRate;
   }

   public final Param featureSubsetStrategy() {
      return this.featureSubsetStrategy;
   }

   public final void org$apache$spark$ml$tree$TreeEnsembleParams$_setter_$subsamplingRate_$eq(final DoubleParam x$1) {
      this.subsamplingRate = x$1;
   }

   public final void org$apache$spark$ml$tree$TreeEnsembleParams$_setter_$featureSubsetStrategy_$eq(final Param x$1) {
      this.featureSubsetStrategy = x$1;
   }

   public final Param leafCol() {
      return this.leafCol;
   }

   public final IntParam maxDepth() {
      return this.maxDepth;
   }

   public final IntParam maxBins() {
      return this.maxBins;
   }

   public final IntParam minInstancesPerNode() {
      return this.minInstancesPerNode;
   }

   public final DoubleParam minWeightFractionPerNode() {
      return this.minWeightFractionPerNode;
   }

   public final DoubleParam minInfoGain() {
      return this.minInfoGain;
   }

   public final IntParam maxMemoryInMB() {
      return this.maxMemoryInMB;
   }

   public final BooleanParam cacheNodeIds() {
      return this.cacheNodeIds;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$leafCol_$eq(final Param x$1) {
      this.leafCol = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxDepth_$eq(final IntParam x$1) {
      this.maxDepth = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxBins_$eq(final IntParam x$1) {
      this.maxBins = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minInstancesPerNode_$eq(final IntParam x$1) {
      this.minInstancesPerNode = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minWeightFractionPerNode_$eq(final DoubleParam x$1) {
      this.minWeightFractionPerNode = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minInfoGain_$eq(final DoubleParam x$1) {
      this.minInfoGain = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxMemoryInMB_$eq(final IntParam x$1) {
      this.maxMemoryInMB = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$cacheNodeIds_$eq(final BooleanParam x$1) {
      this.cacheNodeIds = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public final IntParam checkpointInterval() {
      return this.checkpointInterval;
   }

   public final void org$apache$spark$ml$param$shared$HasCheckpointInterval$_setter_$checkpointInterval_$eq(final IntParam x$1) {
      this.checkpointInterval = x$1;
   }

   public String uid() {
      return this.uid;
   }

   private DecisionTreeRegressionModel[] _trees() {
      return this._trees;
   }

   private double[] _treeWeights() {
      return this._treeWeights;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public int numClasses() {
      return this.numClasses;
   }

   public DecisionTreeRegressionModel[] trees() {
      return this._trees();
   }

   public int getNumTrees() {
      return this.getNumTrees;
   }

   public double[] treeWeights() {
      return this._treeWeights();
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = super.transformSchema(schema);
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateField(outputSchema, this.getLeafField((String)this.$(this.leafCol())), SchemaUtils$.MODULE$.updateField$default$3());
      }

      return outputSchema;
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      Dataset outputData = super.transform(dataset);
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (features) -> this.predictLeaf(features);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GBTClassificationModel.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator1$1() {
            }
         }

         TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GBTClassificationModel.class.getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         UserDefinedFunction leafUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
         return outputData.withColumn((String)this.$(this.leafCol()), leafUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))}))), outputSchema.apply((String)this.$(this.leafCol())).metadata());
      } else {
         return outputData;
      }
   }

   public double predict(final Vector features) {
      if (this.isDefined(this.thresholds())) {
         return super.predict(features);
      } else {
         return this.margin(features) > (double)0.0F ? (double)1.0F : (double)0.0F;
      }
   }

   public Vector predictRaw(final Vector features) {
      double prediction = this.margin(features);
      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(new double[]{-prediction, prediction});
   }

   public Vector raw2probabilityInPlace(final Vector rawPrediction) {
      if (rawPrediction instanceof DenseVector var4) {
         var4.values()[0] = this.loss().computeProbability(var4.values()[0]);
         var4.values()[1] = (double)1.0F - var4.values()[0];
         return var4;
      } else if (rawPrediction instanceof SparseVector) {
         throw new RuntimeException("Unexpected error in GBTClassificationModel: raw2probabilityInPlace encountered SparseVector");
      } else {
         throw new MatchError(rawPrediction);
      }
   }

   public GBTClassificationModel copy(final ParamMap extra) {
      return (GBTClassificationModel)((Model)this.copyValues(new GBTClassificationModel(this.uid(), this._trees(), this._treeWeights(), this.numFeatures(), this.numClasses()), extra)).setParent(this.parent());
   }

   public String toString() {
      String var10000 = this.uid();
      return "GBTClassificationModel: uid = " + var10000 + ", numTrees=" + this.getNumTrees() + ", numClasses=" + this.numClasses() + ", numFeatures=" + this.numFeatures();
   }

   private Vector featureImportances$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.featureImportances = TreeEnsembleModel$.MODULE$.featureImportances(this.trees(), this.numFeatures(), false);
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.featureImportances;
   }

   public Vector featureImportances() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.featureImportances$lzycompute() : this.featureImportances;
   }

   private double margin(final Vector features) {
      double[] treePredictions = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this._trees()), (x$4) -> BoxesRunTime.boxToDouble($anonfun$margin$1(features, x$4)), scala.reflect.ClassTag..MODULE$.Double());
      return org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(this.getNumTrees(), treePredictions, 1, this._treeWeights(), 1);
   }

   public GradientBoostedTreesModel toOld() {
      return new GradientBoostedTreesModel(Algo$.MODULE$.Classification(), (org.apache.spark.mllib.tree.model.DecisionTreeModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this._trees()), (x$5) -> x$5.toOld(), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.tree.model.DecisionTreeModel.class)), this._treeWeights());
   }

   private ClassificationLoss loss() {
      return this.loss;
   }

   public double[] evaluateEachIteration(final Dataset dataset) {
      RDD data = DatasetUtils$.MODULE$.extractInstances(this, dataset, new Some(BoxesRunTime.boxToInteger(2)));
      return GradientBoostedTrees$.MODULE$.evaluateEachIteration(data, this.trees(), this.treeWeights(), this.loss(), Algo$.MODULE$.Classification());
   }

   public MLWriter write() {
      return new GBTClassificationModelWriter(this);
   }

   // $FF: synthetic method
   public static final double $anonfun$margin$1(final Vector features$1, final DecisionTreeRegressionModel x$4) {
      return x$4.rootNode().predictImpl(features$1).prediction();
   }

   public GBTClassificationModel(final String uid, final DecisionTreeRegressionModel[] _trees, final double[] _treeWeights, final int numFeatures, final int numClasses) {
      this.uid = uid;
      this._trees = _trees;
      this._treeWeights = _treeWeights;
      this.numFeatures = numFeatures;
      this.numClasses = numClasses;
      HasCheckpointInterval.$init$(this);
      HasSeed.$init$(this);
      HasWeightCol.$init$(this);
      DecisionTreeParams.$init$(this);
      TreeEnsembleParams.$init$(this);
      HasMaxIter.$init$(this);
      HasStepSize.$init$(this);
      HasValidationIndicatorCol.$init$(this);
      GBTParams.$init$(this);
      TreeEnsembleClassifierParams.$init$(this);
      HasVarianceImpurity.$init$(this);
      GBTClassifierParams.$init$(this);
      TreeEnsembleModel.$init$(this);
      MLWritable.$init$(this);
      scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps(_trees)), () -> "GBTClassificationModel requires at least 1 tree.");
      scala.Predef..MODULE$.require(_trees.length == _treeWeights.length, () -> {
         int var10000 = this._trees().length;
         return "GBTClassificationModel given trees, treeWeights of non-matching lengths (" + var10000 + ", " + this._treeWeights().length + ", respectively).";
      });
      this.getNumTrees = this.trees().length;
      this.loss = this.getOldLossType();
      Statics.releaseFence();
   }

   public GBTClassificationModel(final String uid, final DecisionTreeRegressionModel[] _trees, final double[] _treeWeights, final int numFeatures) {
      this(uid, _trees, _treeWeights, numFeatures, 2);
   }

   public GBTClassificationModel(final String uid, final DecisionTreeRegressionModel[] _trees, final double[] _treeWeights) {
      this(uid, _trees, _treeWeights, -1, 2);
   }

   public GBTClassificationModel() {
      this("", (DecisionTreeRegressionModel[])(new DecisionTreeRegressionModel[]{new DecisionTreeRegressionModel()}), new double[]{Double.NaN}, -1, -1);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class GBTClassificationModelWriter extends MLWriter {
      private final GBTClassificationModel instance;

      public void saveImpl(final String path) {
         JObject extraMetadata = org.json4s.JsonDSL..MODULE$.map2jvalue((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(GBTClassificationModel$.MODULE$.org$apache$spark$ml$classification$GBTClassificationModel$$numFeaturesKey()), BoxesRunTime.boxToInteger(this.instance.numFeatures())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(GBTClassificationModel$.MODULE$.org$apache$spark$ml$classification$GBTClassificationModel$$numTreesKey()), BoxesRunTime.boxToInteger(this.instance.getNumTrees()))}))), (x) -> $anonfun$saveImpl$1(BoxesRunTime.unboxToInt(x)));
         EnsembleModelReadWrite$.MODULE$.saveImpl(this.instance, path, this.sparkSession(), extraMetadata);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$1(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      public GBTClassificationModelWriter(final GBTClassificationModel instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class GBTClassificationModelReader extends MLReader {
      private final String className = GBTClassificationModel.class.getName();
      private final String treeClassName = DecisionTreeRegressionModel.class.getName();

      private String className() {
         return this.className;
      }

      private String treeClassName() {
         return this.treeClassName;
      }

      public GBTClassificationModel load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         Tuple3 var5 = EnsembleModelReadWrite$.MODULE$.loadImpl(path, this.sparkSession(), this.className(), this.treeClassName());
         if (var5 != null) {
            DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var5._1();
            Tuple2[] treesData = (Tuple2[])var5._2();
            double[] treeWeights = (double[])var5._3();
            if (metadata != null && treesData != null && treeWeights != null) {
               Tuple3 var4 = new Tuple3(metadata, treesData, treeWeights);
               DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var4._1();
               Tuple2[] treesData = (Tuple2[])var4._2();
               double[] treeWeights = (double[])var4._3();
               int numFeatures = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), GBTClassificationModel$.MODULE$.org$apache$spark$ml$classification$GBTClassificationModel$$numFeaturesKey())), format, scala.reflect.ManifestFactory..MODULE$.Int()));
               int numTrees = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), GBTClassificationModel$.MODULE$.org$apache$spark$ml$classification$GBTClassificationModel$$numTreesKey())), format, scala.reflect.ManifestFactory..MODULE$.Int()));
               DecisionTreeRegressionModel[] trees = (DecisionTreeRegressionModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])treesData), (x0$1) -> {
                  if (x0$1 != null) {
                     DefaultParamsReader.Metadata treeMetadata = (DefaultParamsReader.Metadata)x0$1._1();
                     Node root = (Node)x0$1._2();
                     DecisionTreeRegressionModel tree = new DecisionTreeRegressionModel(treeMetadata.uid(), root, numFeatures);
                     treeMetadata.getAndSetParams(tree, treeMetadata.getAndSetParams$default$2());
                     return tree;
                  } else {
                     throw new MatchError(x0$1);
                  }
               }, scala.reflect.ClassTag..MODULE$.apply(DecisionTreeRegressionModel.class));
               scala.Predef..MODULE$.require(numTrees == trees.length, () -> "GBTClassificationModel.load expected " + numTrees + " trees based on metadata but found " + trees.length + " trees.");
               GBTClassificationModel model = new GBTClassificationModel(metadata.uid(), trees, treeWeights, numFeatures);
               metadata.getAndSetParams(model, new Some(new scala.collection.immutable..colon.colon("impurity", scala.collection.immutable.Nil..MODULE$)));
               return model;
            }
         }

         throw new MatchError(var5);
      }

      public GBTClassificationModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
