package org.apache.spark.ml.recommendation;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasBlockSize;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.StructType;
import org.json4s.DefaultFormats;
import org.json4s.JValue;
import org.sparkproject.guava.collect.Ordering;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r5b\u0001\u0002\u001b6\u0001\u0001C\u0001\u0002\u0015\u0001\u0003\u0006\u0004%\t%\u0015\u0005\tQ\u0002\u0011\t\u0011)A\u0005%\"A!\u000e\u0001BC\u0002\u0013\u00051\u000e\u0003\u0005r\u0001\t\u0005\t\u0015!\u0003m\u0011!\u0019\bA!b\u0001\n\u0003!\b\"CA\u0007\u0001\t\u0005\t\u0015!\u0003v\u0011%\t9\u0002\u0001BC\u0002\u0013\u0005A\u000fC\u0005\u0002\u001a\u0001\u0011\t\u0011)A\u0005k\"A\u0011Q\u0004\u0001\u0005\u0002]\ny\u0002\u0003\u0005\u0002\u001e\u0001!\taNA\u0017\u0011\u001d\ty\u0003\u0001C\u0001\u0003cAq!a\u000f\u0001\t\u0003\ti\u0004C\u0004\u0002D\u0001!\t!!\u0012\t\u000f\u0005=\u0003\u0001\"\u0001\u0002R!9\u00111\f\u0001\u0005\u0002\u0005u\u0003\"CA4\u0001\t\u0007I\u0011BA5\u0011!\t9\b\u0001Q\u0001\n\u0005-\u0004bBA=\u0001\u0011\u0005\u00131\u0010\u0005\b\u0003O\u0003A\u0011IAU\u0011\u001d\ti\f\u0001C!\u0003\u007fCq!a6\u0001\t\u0003\nI\u000eC\u0004\u0002h\u0002!\t%!;\t\u000f\u00055\b\u0001\"\u0001\u0002p\"9\u0011q\u001f\u0001\u0005\u0002\u0005e\bb\u0002B\b\u0001\u0011\u0005!\u0011\u0003\u0005\b\u00053\u0001A\u0011\u0001B\u000e\u0011\u001d\u0011i\u0003\u0001C\u0005\u0005_AqA!\u0012\u0001\t\u0013\u00119\u0005C\u0004\u0003b\u0001!IAa\u0019\b\u000f\t\u0015U\u0007#\u0001\u0003\b\u001a1A'\u000eE\u0001\u0005\u0013Cq!!\b \t\u0003\u00119\u000bC\u0005\u0003*~\u0011\r\u0011\"\u0003\u0003,\"A!qW\u0010!\u0002\u0013\u0011i\u000bC\u0005\u0003:~\u0011\r\u0011\"\u0003\u0003,\"A!1X\u0010!\u0002\u0013\u0011i\u000b\u0003\u0006\u0003>~\u0011\r\u0011\"\u00026\u0005\u007fC\u0001Ba1 A\u00035!\u0011\u0019\u0005\t\u0005\u000b|B\u0011A\u001b\u0003H\"9!q\\\u0010\u0005B\t\u0005\bb\u0002Bv?\u0011\u0005#Q\u001e\u0004\b\u0005k|\u0002a\bB|\u0011%\u0011IP\u000bB\u0001B\u0003%Q\tC\u0004\u0002\u001e)\"\tAa?\t\u000f\r\r!\u0006\"\u0015\u0004\u0006\u001911qB\u0010\u0005\u0007#Aq!!\b/\t\u0003\u0019\u0019\u0002C\u0005\u0004\u00189\u0012\r\u0011\"\u0003\u0003,\"A1\u0011\u0004\u0018!\u0002\u0013\u0011i\u000bC\u0004\u0003l:\"\tea\u0007\t\u0013\r}q$!A\u0005\n\r\u0005\"\u0001C!M'6{G-\u001a7\u000b\u0005Y:\u0014A\u0004:fG>lW.\u001a8eCRLwN\u001c\u0006\u0003qe\n!!\u001c7\u000b\u0005iZ\u0014!B:qCJ\\'B\u0001\u001f>\u0003\u0019\t\u0007/Y2iK*\ta(A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001\u0003\u001eS\u0005c\u0001\"D\u000b6\tq'\u0003\u0002Eo\t)Qj\u001c3fYB\u0011a\tA\u0007\u0002kA\u0011a\tS\u0005\u0003\u0013V\u0012a\"\u0011'T\u001b>$W\r\u001c)be\u0006l7\u000f\u0005\u0002L\u001d6\tAJ\u0003\u0002No\u0005!Q\u000f^5m\u0013\tyEJ\u0001\u0006N\u0019^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005\u0011\u0006CA*]\u001d\t!&\f\u0005\u0002V16\taK\u0003\u0002X\u007f\u00051AH]8pizR\u0011!W\u0001\u0006g\u000e\fG.Y\u0005\u00037b\u000ba\u0001\u0015:fI\u00164\u0017BA/_\u0005\u0019\u0019FO]5oO*\u00111\f\u0017\u0015\u0004\u0003\u00014\u0007CA1e\u001b\u0005\u0011'BA2:\u0003)\tgN\\8uCRLwN\\\u0005\u0003K\n\u0014QaU5oG\u0016\f\u0013aZ\u0001\u0006c9\"d\u0006M\u0001\u0005k&$\u0007\u0005K\u0002\u0003A\u001a\fAA]1oWV\tA\u000e\u0005\u0002n]6\t\u0001,\u0003\u0002p1\n\u0019\u0011J\u001c;)\u0007\r\u0001g-A\u0003sC:\\\u0007\u0005K\u0002\u0005A\u001a\f1\"^:fe\u001a\u000b7\r^8sgV\tQ\u000fE\u0002w\u0003\u000fq1a^A\u0001\u001d\tAhP\u0004\u0002z{:\u0011!\u0010 \b\u0003+nL\u0011AP\u0005\u0003yuJ!AO\u001e\n\u0005}L\u0014aA:rY&!\u00111AA\u0003\u0003\u001d\u0001\u0018mY6bO\u0016T!a`\u001d\n\t\u0005%\u00111\u0002\u0002\n\t\u0006$\u0018M\u0012:b[\u0016TA!a\u0001\u0002\u0006\u0005aQo]3s\r\u0006\u001cGo\u001c:tA!\u001aa!!\u0005\u0011\u00075\f\u0019\"C\u0002\u0002\u0016a\u0013\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u0017%$X-\u001c$bGR|'o]\u0001\rSR,WNR1di>\u00148\u000f\t\u0015\u0004\u0011\u0005E\u0011A\u0002\u001fj]&$h\bF\u0005F\u0003C\t)#!\u000b\u0002,!)\u0001+\u0003a\u0001%\"\"\u0011\u0011\u00051g\u0011\u0015Q\u0017\u00021\u0001mQ\u0011\t)\u0003\u00194\t\u000bML\u0001\u0019A;\t\r\u0005]\u0011\u00021\u0001v)\u0005)\u0015AC:fiV\u001bXM]\"pYR!\u00111GA\u001b\u001b\u0005\u0001\u0001BBA\u001c\u0017\u0001\u0007!+A\u0003wC2,X\rK\u0002\fA\u001a\f!b]3u\u0013R,WnQ8m)\u0011\t\u0019$a\u0010\t\r\u0005]B\u00021\u0001SQ\ra\u0001MZ\u0001\u0011g\u0016$\bK]3eS\u000e$\u0018n\u001c8D_2$B!a\r\u0002H!1\u0011qG\u0007A\u0002ICC!\u00041\u0002L\u0005\u0012\u0011QJ\u0001\u0006c9\u001ad\u0006M\u0001\u0015g\u0016$8i\u001c7e'R\f'\u000f^*ue\u0006$XmZ=\u0015\t\u0005M\u00121\u000b\u0005\u0007\u0003oq\u0001\u0019\u0001*)\t9\u0001\u0017qK\u0011\u0003\u00033\nQA\r\u00183]A\nAb]3u\u00052|7m[*ju\u0016$B!a\r\u0002`!1\u0011qG\bA\u00021DCa\u00041\u0002d\u0005\u0012\u0011QM\u0001\u0006g9\u0002d\u0006M\u0001\baJ,G-[2u+\t\tY\u0007\u0005\u0003\u0002n\u0005MTBAA8\u0015\u0011\t\t(!\u0002\u0002\u0017\u0015D\bO]3tg&|gn]\u0005\u0005\u0003k\nyGA\nVg\u0016\u0014H)\u001a4j]\u0016$g)\u001e8di&|g.\u0001\u0005qe\u0016$\u0017n\u0019;!\u0003%!(/\u00198tM>\u0014X\u000eF\u0002v\u0003{Bq!a \u0013\u0001\u0004\t\t)A\u0004eCR\f7/\u001a;1\t\u0005\r\u0015q\u0012\t\u0007\u0003\u000b\u000b9)a#\u000e\u0005\u0005\u0015\u0011\u0002BAE\u0003\u000b\u0011q\u0001R1uCN,G\u000f\u0005\u0003\u0002\u000e\u0006=E\u0002\u0001\u0003\r\u0003#\u000bi(!A\u0001\u0002\u000b\u0005\u00111\u0013\u0002\u0004?\u0012\u0012\u0014\u0003BAK\u00037\u00032!\\AL\u0013\r\tI\n\u0017\u0002\b\u001d>$\b.\u001b8h!\ri\u0017QT\u0005\u0004\u0003?C&aA!os\"\"!\u0003YARC\t\t)+A\u00033]Ar\u0003'A\bue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011\tY+a.\u0011\t\u00055\u00161W\u0007\u0003\u0003_SA!!-\u0002\u0006\u0005)A/\u001f9fg&!\u0011QWAX\u0005)\u0019FO];diRK\b/\u001a\u0005\b\u0003s\u001b\u0002\u0019AAV\u0003\u0019\u00198\r[3nC\"\"1\u0003YA&\u0003\u0011\u0019w\u000e]=\u0015\u0007\u0015\u000b\t\rC\u0004\u0002DR\u0001\r!!2\u0002\u000b\u0015DHO]1\u0011\t\u0005\u001d\u0017QZ\u0007\u0003\u0003\u0013T1!a38\u0003\u0015\u0001\u0018M]1n\u0013\u0011\ty-!3\u0003\u0011A\u000b'/Y7NCBDC\u0001\u00061\u0002T\u0006\u0012\u0011Q[\u0001\u0006c9*d\u0006M\u0001\u0006oJLG/Z\u000b\u0003\u00037\u00042aSAo\u0013\r\ty\u000e\u0014\u0002\t\u001b2;&/\u001b;fe\"\"Q\u0003YArC\t\t)/A\u00032]Yr\u0003'\u0001\u0005u_N#(/\u001b8h)\u0005\u0011\u0006\u0006\u0002\fa\u0003G\nAC]3d_6lWM\u001c3G_J\fE\u000e\\+tKJ\u001cHcA;\u0002r\"1\u00111_\fA\u00021\f\u0001B\\;n\u0013R,Wn\u001d\u0015\u0005/\u0001\f9&\u0001\fsK\u000e|W.\\3oI\u001a{'/V:feN+(m]3u)\u0015)\u00181 B\u0004\u0011\u001d\ty\b\u0007a\u0001\u0003{\u0004D!a@\u0003\u0004A1\u0011QQAD\u0005\u0003\u0001B!!$\u0003\u0004\u0011a!QAA~\u0003\u0003\u0005\tQ!\u0001\u0002\u0014\n\u0019q\fJ\u001a\t\r\u0005M\b\u00041\u0001mQ\u0011A\u0002Ma\u0003\"\u0005\t5\u0011!\u0002\u001a/g9\u0002\u0014\u0001\u0006:fG>lW.\u001a8e\r>\u0014\u0018\t\u001c7Ji\u0016l7\u000fF\u0002v\u0005'AaA!\u0006\u001a\u0001\u0004a\u0017\u0001\u00038v[V\u001bXM]:)\te\u0001\u0017qK\u0001\u0017e\u0016\u001cw.\\7f]\u00124uN]%uK6\u001cVOY:fiR)QO!\b\u0003*!9\u0011q\u0010\u000eA\u0002\t}\u0001\u0007\u0002B\u0011\u0005K\u0001b!!\"\u0002\b\n\r\u0002\u0003BAG\u0005K!ABa\n\u0003\u001e\u0005\u0005\t\u0011!B\u0001\u0003'\u00131a\u0018\u00135\u0011\u0019\u0011)B\u0007a\u0001Y\"\"!\u0004\u0019B\u0006\u0003U9W\r^*pkJ\u001cWMR1di>\u00148+\u001e2tKR$r!\u001eB\u0019\u0005{\u0011\t\u0005C\u0004\u0002\u0000m\u0001\rAa\r1\t\tU\"\u0011\b\t\u0007\u0003\u000b\u000b9Ia\u000e\u0011\t\u00055%\u0011\b\u0003\r\u0005w\u0011\t$!A\u0001\u0002\u000b\u0005\u00111\u0013\u0002\u0004?\u0012*\u0004B\u0002B 7\u0001\u0007Q/A\u0004gC\u000e$xN]:\t\r\t\r3\u00041\u0001S\u0003\u0019\u0019w\u000e\\;n]\u0006y!/Z2p[6,g\u000e\u001a$pe\u0006cG\u000eF\u0007v\u0005\u0013\u0012iE!\u0015\u0003V\te#Q\f\u0005\u0007\u0005\u0017b\u0002\u0019A;\u0002\u0015M\u00148MR1di>\u00148\u000f\u0003\u0004\u0003Pq\u0001\r!^\u0001\u000bIN$h)Y2u_J\u001c\bB\u0002B*9\u0001\u0007!+A\bte\u000e|U\u000f\u001e9vi\u000e{G.^7o\u0011\u0019\u00119\u0006\ba\u0001%\u0006yAm\u001d;PkR\u0004X\u000f^\"pYVlg\u000e\u0003\u0004\u0003\\q\u0001\r\u0001\\\u0001\u0004]Vl\u0007B\u0002B09\u0001\u0007A.A\u0005cY>\u001c7nU5{K\u0006A!\r\\8dW&4\u0017\u0010\u0006\u0004\u0003f\tm$\u0011\u0011\t\u0007\u0003\u000b\u000b9Ia\u001a\u0011\u000f5\u0014IG!\u001c\u0003t%\u0019!1\u000e-\u0003\rQ+\b\u000f\\33!\u0011i'q\u000e7\n\u0007\tE\u0004LA\u0003BeJ\f\u0017\u0010E\u0003n\u0005_\u0012)\bE\u0002n\u0005oJ1A!\u001fY\u0005\u00151En\\1u\u0011\u001d\u0011y$\ba\u0001\u0005{\u0002b!!\"\u0002\b\n}\u0004CB7\u0003j1\u0014\u0019\b\u0003\u0004\u0003`u\u0001\r\u0001\u001c\u0015\u0005\u0001\u0001\fY%\u0001\u0005B\u0019Nku\u000eZ3m!\t1udE\u0004 \u0005\u0017\u0013\tJa&\u0011\u00075\u0014i)C\u0002\u0003\u0010b\u0013a!\u00118z%\u00164\u0007\u0003B&\u0003\u0014\u0016K1A!&M\u0005)iEJU3bI\u0006\u0014G.\u001a\t\u0005\u00053\u0013\u0019+\u0004\u0002\u0003\u001c*!!Q\u0014BP\u0003\tIwN\u0003\u0002\u0003\"\u0006!!.\u0019<b\u0013\u0011\u0011)Ka'\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\t\u001d\u0015a\u0001(b\u001dV\u0011!Q\u0016\t\u0005\u0005_\u0013),\u0004\u0002\u00032*!!1\u0017BP\u0003\u0011a\u0017M\\4\n\u0007u\u0013\t,\u0001\u0003OC:\u0003\u0013\u0001\u0002#s_B\fQ\u0001\u0012:pa\u0002\nAd];qa>\u0014H/\u001a3D_2$7\u000b^1siN#(/\u0019;fO&,7/\u0006\u0002\u0003BB)QNa\u001c\u0003.\u0006i2/\u001e9q_J$X\rZ\"pY\u0012\u001cF/\u0019:u'R\u0014\u0018\r^3hS\u0016\u001c\b%A\u0007d_2dWm\u0019;`i>\u0004xl\u001b\u000b\t\u0005\u0013\u0014yMa5\u0003VB!\u0011Q\u0011Bf\u0013\u0011\u0011i-!\u0002\u0003\r\r{G.^7o\u0011\u001d\u0011\tn\na\u0001\u0005\u0013\f\u0011!\u001a\u0005\u0007\u00057:\u0003\u0019\u00017\t\u000f\t]w\u00051\u0001\u0003Z\u00069!/\u001a<feN,\u0007cA7\u0003\\&\u0019!Q\u001c-\u0003\u000f\t{w\u000e\\3b]\u0006!!/Z1e+\t\u0011\u0019\u000f\u0005\u0003L\u0005K,\u0015b\u0001Bt\u0019\nAQ\n\u0014*fC\u0012,'\u000f\u000b\u0003)A\u0006\r\u0018\u0001\u00027pC\u0012$2!\u0012Bx\u0011\u0019\u0011\t0\u000ba\u0001%\u0006!\u0001/\u0019;iQ\u0011I\u0003-a9\u0003\u001d\u0005c5+T8eK2<&/\u001b;feN\u0019!&a7\u0002\u0011%t7\u000f^1oG\u0016$BA!@\u0004\u0002A\u0019!q \u0016\u000e\u0003}AaA!?-\u0001\u0004)\u0015\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\r\u001d1Q\u0002\t\u0004[\u000e%\u0011bAB\u00061\n!QK\\5u\u0011\u0019\u0011\t0\fa\u0001%\nq\u0011\tT*N_\u0012,GNU3bI\u0016\u00148c\u0001\u0018\u0003dR\u00111Q\u0003\t\u0004\u0005\u007ft\u0013!C2mCN\u001ch*Y7f\u0003)\u0019G.Y:t\u001d\u0006lW\r\t\u000b\u0004\u000b\u000eu\u0001B\u0002Bye\u0001\u0007!+\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0004$A!!qVB\u0013\u0013\u0011\u00199C!-\u0003\r=\u0013'.Z2uQ\u0011y\u0002-a9)\ty\u0001\u00171\u001d"
)
public class ALSModel extends Model implements ALSModelParams, MLWritable {
   private final String uid;
   private final int rank;
   private final transient Dataset userFactors;
   private final transient Dataset itemFactors;
   private final UserDefinedFunction predict;
   private Param userCol;
   private Param itemCol;
   private Param coldStartStrategy;
   private IntParam blockSize;
   private Param predictionCol;

   public static ALSModel load(final String path) {
      return ALSModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return ALSModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getUserCol() {
      return ALSModelParams.getUserCol$(this);
   }

   public String getItemCol() {
      return ALSModelParams.getItemCol$(this);
   }

   public Column checkIntegers(final Dataset dataset, final String colName) {
      return ALSModelParams.checkIntegers$(this, dataset, colName);
   }

   public String getColdStartStrategy() {
      return ALSModelParams.getColdStartStrategy$(this);
   }

   public final int getBlockSize() {
      return HasBlockSize.getBlockSize$(this);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public Param userCol() {
      return this.userCol;
   }

   public Param itemCol() {
      return this.itemCol;
   }

   public Param coldStartStrategy() {
      return this.coldStartStrategy;
   }

   public void org$apache$spark$ml$recommendation$ALSModelParams$_setter_$userCol_$eq(final Param x$1) {
      this.userCol = x$1;
   }

   public void org$apache$spark$ml$recommendation$ALSModelParams$_setter_$itemCol_$eq(final Param x$1) {
      this.itemCol = x$1;
   }

   public void org$apache$spark$ml$recommendation$ALSModelParams$_setter_$coldStartStrategy_$eq(final Param x$1) {
      this.coldStartStrategy = x$1;
   }

   public final IntParam blockSize() {
      return this.blockSize;
   }

   public final void org$apache$spark$ml$param$shared$HasBlockSize$_setter_$blockSize_$eq(final IntParam x$1) {
      this.blockSize = x$1;
   }

   public final Param predictionCol() {
      return this.predictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1) {
      this.predictionCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public int rank() {
      return this.rank;
   }

   public Dataset userFactors() {
      return this.userFactors;
   }

   public Dataset itemFactors() {
      return this.itemFactors;
   }

   public ALSModel setUserCol(final String value) {
      return (ALSModel)this.set(this.userCol(), value);
   }

   public ALSModel setItemCol(final String value) {
      return (ALSModel)this.set(this.itemCol(), value);
   }

   public ALSModel setPredictionCol(final String value) {
      return (ALSModel)this.set(this.predictionCol(), value);
   }

   public ALSModel setColdStartStrategy(final String value) {
      return (ALSModel)this.set(this.coldStartStrategy(), value);
   }

   public ALSModel setBlockSize(final int value) {
      return (ALSModel)this.set(this.blockSize(), BoxesRunTime.boxToInteger(value));
   }

   private UserDefinedFunction predict() {
      return this.predict;
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema());
      Column validatedUsers = this.checkIntegers(dataset, (String)this.$(this.userCol()));
      Column validatedItems = this.checkIntegers(dataset, (String)this.$(this.itemCol()));
      String validatedInputAlias = Identifiable$.MODULE$.randomUID("__als_validated_input");
      String itemFactorsAlias = Identifiable$.MODULE$.randomUID("__als_item_factors");
      String userFactorsAlias = Identifiable$.MODULE$.randomUID("__als_user_factors");
      Dataset predictions = dataset.withColumns(new .colon.colon((String)this.$(this.userCol()), new .colon.colon((String)this.$(this.itemCol()), scala.collection.immutable.Nil..MODULE$)), new .colon.colon(validatedUsers, new .colon.colon(validatedItems, scala.collection.immutable.Nil..MODULE$))).alias(validatedInputAlias).join(this.userFactors().alias(userFactorsAlias), org.apache.spark.sql.functions..MODULE$.col(validatedInputAlias + "." + this.$(this.userCol())).$eq$eq$eq(org.apache.spark.sql.functions..MODULE$.col(userFactorsAlias + ".id")), "left").join(this.itemFactors().alias(itemFactorsAlias), org.apache.spark.sql.functions..MODULE$.col(validatedInputAlias + "." + this.$(this.itemCol())).$eq$eq$eq(org.apache.spark.sql.functions..MODULE$.col(itemFactorsAlias + ".id")), "left").select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(validatedInputAlias + ".*"), this.predict().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(userFactorsAlias + ".features"), org.apache.spark.sql.functions..MODULE$.col(itemFactorsAlias + ".features")}))).alias((String)this.$(this.predictionCol()))})));
      String var9 = this.getColdStartStrategy();
      String var10000 = ALSModel$.MODULE$.org$apache$spark$ml$recommendation$ALSModel$$Drop();
      if (var10000 == null) {
         if (var9 == null) {
            return predictions.na().drop("all", new .colon.colon((String)this.$(this.predictionCol()), scala.collection.immutable.Nil..MODULE$));
         }
      } else if (var10000.equals(var9)) {
         return predictions.na().drop("all", new .colon.colon((String)this.$(this.predictionCol()), scala.collection.immutable.Nil..MODULE$));
      }

      var10000 = ALSModel$.MODULE$.org$apache$spark$ml$recommendation$ALSModel$$NaN();
      if (var10000 == null) {
         if (var9 == null) {
            return predictions;
         }
      } else if (var10000.equals(var9)) {
         return predictions;
      }

      throw new MatchError(var9);
   }

   public StructType transformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.userCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.itemCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.predictionCol()), org.apache.spark.sql.types.FloatType..MODULE$, SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   public ALSModel copy(final ParamMap extra) {
      ALSModel copied = new ALSModel(this.uid(), this.rank(), this.userFactors(), this.itemFactors());
      return (ALSModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new ALSModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "ALSModel: uid=" + var10000 + ", rank=" + this.rank();
   }

   public Dataset recommendForAllUsers(final int numItems) {
      return this.recommendForAll(this.userFactors(), this.itemFactors(), (String)this.$(this.userCol()), (String)this.$(this.itemCol()), numItems, BoxesRunTime.unboxToInt(this.$(this.blockSize())));
   }

   public Dataset recommendForUserSubset(final Dataset dataset, final int numItems) {
      Dataset srcFactorSubset = this.getSourceFactorSubset(dataset, this.userFactors(), (String)this.$(this.userCol()));
      return this.recommendForAll(srcFactorSubset, this.itemFactors(), (String)this.$(this.userCol()), (String)this.$(this.itemCol()), numItems, BoxesRunTime.unboxToInt(this.$(this.blockSize())));
   }

   public Dataset recommendForAllItems(final int numUsers) {
      return this.recommendForAll(this.itemFactors(), this.userFactors(), (String)this.$(this.itemCol()), (String)this.$(this.userCol()), numUsers, BoxesRunTime.unboxToInt(this.$(this.blockSize())));
   }

   public Dataset recommendForItemSubset(final Dataset dataset, final int numUsers) {
      Dataset srcFactorSubset = this.getSourceFactorSubset(dataset, this.itemFactors(), (String)this.$(this.itemCol()));
      return this.recommendForAll(srcFactorSubset, this.userFactors(), (String)this.$(this.itemCol()), (String)this.$(this.userCol()), numUsers, BoxesRunTime.unboxToInt(this.$(this.blockSize())));
   }

   private Dataset getSourceFactorSubset(final Dataset dataset, final Dataset factors, final String column) {
      return factors.join(dataset.select(column, scala.collection.immutable.Nil..MODULE$), factors.apply("id").$eq$eq$eq(dataset.apply(column)), "left_semi").select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{factors.apply("id"), factors.apply("features")})));
   }

   private Dataset recommendForAll(final Dataset srcFactors, final Dataset dstFactors, final String srcOutputColumn, final String dstOutputColumn, final int num, final int blockSize) {
      String ratingColumn = "rating";
      String recommendColumn = "recommendations";
      SQLImplicits var10002 = srcFactors.sparkSession().implicits();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ALSModel.class.getClassLoader());

      final class $typecreator5$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new .colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new .colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator5$1() {
         }
      }

      Dataset srcFactorsBlocked = this.blockify(srcFactors.as(var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1()))), blockSize);
      var10002 = srcFactors.sparkSession().implicits();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ALSModel.class.getClassLoader());

      final class $typecreator10$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new .colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new .colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator10$1() {
         }
      }

      Dataset dstFactorsBlocked = this.blockify(dstFactors.as(var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator10$1()))), blockSize);
      Dataset var10000 = srcFactorsBlocked.crossJoin(dstFactorsBlocked);
      SQLImplicits var10001 = srcFactors.sparkSession().implicits();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ALSModel.class.getClassLoader());

      final class $typecreator15$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple4"), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new .colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new .colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new .colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new .colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)))));
         }

         public $typecreator15$1() {
         }
      }

      var10000 = var10000.as(var10001.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator15$1())));
      Function1 var28 = (iter) -> {
         ObjectRef scores = ObjectRef.create((Object)null);
         ObjectRef idxOrd = ObjectRef.create((Object)null);
         return iter.flatMap((x0$1) -> {
            if (x0$1 != null) {
               int[] srcIds = (int[])x0$1._1();
               float[] srcMat = (float[])x0$1._2();
               int[] dstIds = (int[])x0$1._3();
               float[] dstMat = (float[])x0$1._4();
               scala.Predef..MODULE$.require(srcMat.length == srcIds.length * this.rank());
               scala.Predef..MODULE$.require(dstMat.length == dstIds.length * this.rank());
               int m = srcIds.length;
               int n = dstIds.length;
               if ((float[])scores.elem == null || ((float[])scores.elem).length < n) {
                  scores.elem = (float[])scala.Array..MODULE$.ofDim(n, scala.reflect.ClassTag..MODULE$.Float());
                  idxOrd.elem = new Ordering(scores) {
                     private final ObjectRef scores$1;

                     public int compare(final int left, final int right) {
                        return scala.package..MODULE$.Ordering().apply(scala.math.Ordering.DeprecatedFloatOrdering..MODULE$).compare(BoxesRunTime.boxToFloat(((float[])this.scores$1.elem)[left]), BoxesRunTime.boxToFloat(((float[])this.scores$1.elem)[right]));
                     }

                     public {
                        this.scores$1 = scores$1;
                     }
                  };
               }

               return scala.package..MODULE$.Iterator().range(0, m).flatMap((i) -> $anonfun$recommendForAll$3(this, n, dstMat, srcMat, scores, srcIds, idxOrd, num, dstIds, BoxesRunTime.unboxToInt(i)));
            } else {
               throw new MatchError(x0$1);
            }
         });
      };
      var10002 = srcFactors.sparkSession().implicits();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ALSModel.class.getClassLoader());

      final class $typecreator20$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple3"), new .colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new .colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new .colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$))));
         }

         public $typecreator20$1() {
         }
      }

      Dataset ratings = var10000.mapPartitions(var28, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator20$1()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{srcOutputColumn, dstOutputColumn, ratingColumn})));
      ArrayType arrayType = org.apache.spark.sql.types.ArrayType..MODULE$.apply((new StructType()).add(dstOutputColumn, org.apache.spark.sql.types.IntegerType..MODULE$).add(ratingColumn, org.apache.spark.sql.types.FloatType..MODULE$));
      var10000 = ratings.groupBy(srcOutputColumn, scala.collection.immutable.Nil..MODULE$).agg(ALSModel$.MODULE$.collect_top_k(org.apache.spark.sql.functions..MODULE$.struct(ratingColumn, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{dstOutputColumn}))), num, false), scala.collection.immutable.Nil..MODULE$);
      SQLImplicits var29 = srcFactors.sparkSession().implicits();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ALSModel.class.getClassLoader());

      final class $typecreator25$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new .colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new .colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), new .colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator25$1() {
         }
      }

      var10000 = var10000.as(var29.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator25$1())));
      Function1 var30 = (t) -> new Tuple2(BoxesRunTime.boxToInteger(t._1$mcI$sp()), ((IterableOps)t._2()).map((p) -> new Tuple2(BoxesRunTime.boxToInteger(p._2$mcI$sp()), p._1())));
      var10002 = srcFactors.sparkSession().implicits();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ALSModel.class.getClassLoader());

      final class $typecreator30$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new .colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala.collection.immutable").asModule().moduleClass()), $m$untyped.staticClass("scala.collection.immutable.Seq"), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new .colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new .colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator30$1() {
         }
      }

      return var10000.map(var30, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator30$1()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{srcOutputColumn, recommendColumn}))).withColumn(recommendColumn, org.apache.spark.sql.functions..MODULE$.col(recommendColumn).cast(arrayType));
   }

   private Dataset blockify(final Dataset factors, final int blockSize) {
      Function1 var10001 = (iter) -> iter.grouped(blockSize).map((block) -> new Tuple2(((IterableOnceOps)block.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$blockify$3(x$1)))).toArray(scala.reflect.ClassTag..MODULE$.Int()), ((IterableOnceOps)block.flatMap((x$2) -> scala.Predef..MODULE$.wrapFloatArray((float[])x$2._2()))).toArray(scala.reflect.ClassTag..MODULE$.Float())));
      SQLImplicits var10002 = factors.sparkSession().implicits();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ALSModel.class.getClassLoader());

      final class $typecreator5$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new .colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), new .colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new .colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator5$2() {
         }
      }

      return factors.mapPartitions(var10001, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$2())));
   }

   // $FF: synthetic method
   public static final float $anonfun$predict$1(final ALSModel $this, final Seq featuresA, final Seq featuresB) {
      if (featuresA != null && featuresB != null) {
         float dotProduct = 0.0F;

         for(int i = 0; i < $this.rank(); ++i) {
            dotProduct += BoxesRunTime.unboxToFloat(featuresA.apply(i)) * BoxesRunTime.unboxToFloat(featuresB.apply(i));
         }

         return dotProduct;
      } else {
         return Float.NaN;
      }
   }

   // $FF: synthetic method
   public static final Tuple3 $anonfun$recommendForAll$4(final int srcId$1, final int[] dstIds$1, final ObjectRef scores$1, final int j) {
      return new Tuple3(BoxesRunTime.boxToInteger(srcId$1), BoxesRunTime.boxToInteger(dstIds$1[j]), BoxesRunTime.boxToFloat(((float[])scores$1.elem)[j]));
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$recommendForAll$3(final ALSModel $this, final int n$1, final float[] dstMat$1, final float[] srcMat$1, final ObjectRef scores$1, final int[] srcIds$1, final ObjectRef idxOrd$1, final int num$1, final int[] dstIds$1, final int i) {
      org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().sgemv("T", $this.rank(), n$1, 1.0F, dstMat$1, 0, $this.rank(), srcMat$1, i * $this.rank(), 1, 0.0F, (float[])scores$1.elem, 0, 1);
      int srcId = srcIds$1[i];
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(((Ordering)idxOrd$1.elem).greatestOf(scala.jdk.CollectionConverters..MODULE$.IteratorHasAsJava(scala.package..MODULE$.Iterator().range(0, n$1)).asJava(), num$1)).asScala().iterator().map((j) -> $anonfun$recommendForAll$4(srcId, dstIds$1, scores$1, BoxesRunTime.unboxToInt(j)));
   }

   // $FF: synthetic method
   public static final int $anonfun$blockify$3(final Tuple2 x$1) {
      return x$1._1$mcI$sp();
   }

   public ALSModel(final String uid, final int rank, final Dataset userFactors, final Dataset itemFactors) {
      this.uid = uid;
      this.rank = rank;
      this.userFactors = userFactors;
      this.itemFactors = itemFactors;
      HasPredictionCol.$init$(this);
      HasBlockSize.$init$(this);
      ALSModelParams.$init$(this);
      MLWritable.$init$(this);
      functions var10001 = org.apache.spark.sql.functions..MODULE$;
      Function2 var10002 = (featuresA, featuresB) -> BoxesRunTime.boxToFloat($anonfun$predict$1(this, featuresA, featuresB));
      TypeTags.TypeTag var10003 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Float();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ALSModel.class.getClassLoader());
      TypeTags.TypeTag var10004 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, null.new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ALSModel.class.getClassLoader());
      this.predict = var10001.udf(var10002, var10003, var10004, ((TypeTags)$u).TypeTag().apply((Mirror)$m, null.new $typecreator2$1()));
      Statics.releaseFence();
   }

   public ALSModel() {
      this("", -1, (Dataset)null, (Dataset)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public final class $typecreator1$1 extends TypeCreator {
      public Types.TypeApi apply(final Mirror $m$untyped) {
         Universe $u = $m$untyped.universe();
         return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new .colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
      }
   }

   public final class $typecreator2$1 extends TypeCreator {
      public Types.TypeApi apply(final Mirror $m$untyped) {
         Universe $u = $m$untyped.universe();
         return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new .colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
      }
   }

   public static class ALSModelWriter extends MLWriter {
      private final ALSModel instance;

      public void saveImpl(final String path) {
         Tuple2 extraMetadata = scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rank"), BoxesRunTime.boxToInteger(this.instance.rank()));
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession(), new Some(org.json4s.JsonDSL..MODULE$.pair2jvalue(extraMetadata, (x) -> $anonfun$saveImpl$1(BoxesRunTime.unboxToInt(x)))));
         String userPath = (new Path(path, "userFactors")).toString();
         this.instance.userFactors().write().format("parquet").save(userPath);
         String itemPath = (new Path(path, "itemFactors")).toString();
         this.instance.itemFactors().write().format("parquet").save(itemPath);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$1(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      public ALSModelWriter(final ALSModel instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class ALSModelReader extends MLReader {
      private final String className = ALSModel.class.getName();

      private String className() {
         return this.className;
      }

      public ALSModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         int rank = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "rank")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
         String userPath = (new Path(path, "userFactors")).toString();
         Dataset userFactors = this.sparkSession().read().format("parquet").load(userPath);
         String itemPath = (new Path(path, "itemFactors")).toString();
         Dataset itemFactors = this.sparkSession().read().format("parquet").load(itemPath);
         ALSModel model = new ALSModel(metadata.uid(), rank, userFactors, itemFactors);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public ALSModelReader() {
      }
   }
}
