package org.apache.spark.ml.tree;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.sql.SparkSession;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple9;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u001dqAB)S\u0011\u0003!FL\u0002\u0004_%\"\u0005Ak\u0018\u0005\u0006M\u0006!\t\u0001\u001b\u0004\u0005S\u0006\u0001%\u000e\u0003\u0005{\u0007\tU\r\u0011\"\u0001|\u0011!y8A!E!\u0002\u0013a\bBCA\u0001\u0007\tU\r\u0011\"\u0001\u0002\u0004!Q\u0011\u0011C\u0002\u0003\u0012\u0003\u0006I!!\u0002\t\u0013\u0005M1A!f\u0001\n\u0003Y\b\"CA\u000b\u0007\tE\t\u0015!\u0003}\u0011\u001917\u0001\"\u0001\u0002\u0018!9\u00111E\u0002\u0005\u0002\u0005\u0015\u0002\"CA\u0017\u0007\u0005\u0005I\u0011AA\u0018\u0011%\t9dAI\u0001\n\u0003\tI\u0004C\u0005\u0002P\r\t\n\u0011\"\u0001\u0002R!I\u0011QK\u0002\u0012\u0002\u0013\u0005\u0011\u0011\b\u0005\n\u0003/\u001a\u0011\u0011!C!\u00033B\u0001\"a\u001b\u0004\u0003\u0003%\ta\u001f\u0005\n\u0003[\u001a\u0011\u0011!C\u0001\u0003_B\u0011\"a\u001f\u0004\u0003\u0003%\t%! \t\u0013\u0005-5!!A\u0005\u0002\u00055\u0005\"CAL\u0007\u0005\u0005I\u0011IAM\u0011%\tijAA\u0001\n\u0003\ny\nC\u0005\u0002\"\u000e\t\t\u0011\"\u0011\u0002$\"I\u0011QU\u0002\u0002\u0002\u0013\u0005\u0013qU\u0004\b\u0003W\u000b\u0001\u0012AAW\r\u0019I\u0017\u0001#\u0001\u00020\"1aM\u0007C\u0001\u0003wCq!!0\u001b\t\u0003\ty\fC\u0005\u0002>j\t\t\u0011\"!\u0002F\"I\u0011Q\u001a\u000e\u0002\u0002\u0013\u0005\u0015q\u001a\u0005\n\u0003CT\u0012\u0011!C\u0005\u0003G4a!a;\u0002\u0001\u00065\b\"CAxA\tU\r\u0011\"\u0001|\u0011%\t\t\u0010\tB\tB\u0003%A\u0010\u0003\u0006\u0002t\u0002\u0012)\u001a!C\u0001\u0003kD!\"a>!\u0005#\u0005\u000b\u0011BA\u0006\u0011)\tI\u0010\tBK\u0002\u0013\u0005\u0011Q\u001f\u0005\u000b\u0003w\u0004#\u0011#Q\u0001\n\u0005-\u0001BCA\u007fA\tU\r\u0011\"\u0001\u0002\u0004!Q\u0011q \u0011\u0003\u0012\u0003\u0006I!!\u0002\t\u0015\t\u0005\u0001E!f\u0001\n\u0003\u0011\u0019\u0001\u0003\u0006\u0003\f\u0001\u0012\t\u0012)A\u0005\u0005\u000bA!B!\u0004!\u0005+\u0007I\u0011AA{\u0011)\u0011y\u0001\tB\tB\u0003%\u00111\u0002\u0005\n\u0005#\u0001#Q3A\u0005\u0002mD\u0011Ba\u0005!\u0005#\u0005\u000b\u0011\u0002?\t\u0013\tU\u0001E!f\u0001\n\u0003Y\b\"\u0003B\fA\tE\t\u0015!\u0003}\u0011)\t\u0019\r\tBK\u0002\u0013\u0005!\u0011\u0004\u0005\u000b\u00057\u0001#\u0011#Q\u0001\n\u0005e\u0001B\u00024!\t\u0003\u0011i\u0002C\u0005\u0002.\u0001\n\t\u0011\"\u0001\u00034!I\u0011q\u0007\u0011\u0012\u0002\u0013\u0005\u0011\u0011\b\u0005\n\u0003\u001f\u0002\u0013\u0013!C\u0001\u0005\u000fB\u0011\"!\u0016!#\u0003%\tAa\u0012\t\u0013\t-\u0003%%A\u0005\u0002\u0005E\u0003\"\u0003B'AE\u0005I\u0011\u0001B(\u0011%\u0011\u0019\u0006II\u0001\n\u0003\u00119\u0005C\u0005\u0003V\u0001\n\n\u0011\"\u0001\u0002:!I!q\u000b\u0011\u0012\u0002\u0013\u0005\u0011\u0011\b\u0005\n\u00053\u0002\u0013\u0013!C\u0001\u00057B\u0011\"a\u0016!\u0003\u0003%\t%!\u0017\t\u0011\u0005-\u0004%!A\u0005\u0002mD\u0011\"!\u001c!\u0003\u0003%\tAa\u0018\t\u0013\u0005m\u0004%!A\u0005B\u0005u\u0004\"CAFA\u0005\u0005I\u0011\u0001B2\u0011%\t9\nIA\u0001\n\u0003\u00129\u0007C\u0005\u0002\u001e\u0002\n\t\u0011\"\u0011\u0002 \"I\u0011\u0011\u0015\u0011\u0002\u0002\u0013\u0005\u00131\u0015\u0005\n\u0003K\u0003\u0013\u0011!C!\u0005W:qAa\u001c\u0002\u0011\u0003\u0011\tHB\u0004\u0002l\u0006A\tAa\u001d\t\r\u0019DE\u0011\u0001B;\u0011\u001d\u00119\b\u0013C\u0001\u0005sBqAa%I\t\u0003\u0011)\nC\u0005\u0002>\"\u000b\t\u0011\"!\u0003\u001c\"I\u0011Q\u001a%\u0002\u0002\u0013\u0005%q\u0016\u0005\n\u0003CD\u0015\u0011!C\u0005\u0003GDqAa/\u0002\t\u0003\u0011i\fC\u0004\u0003z\u0006!\tAa?\u00025\u0011+7-[:j_:$&/Z3N_\u0012,GNU3bI^\u0013\u0018\u000e^3\u000b\u0005M#\u0016\u0001\u0002;sK\u0016T!!\u0016,\u0002\u00055d'BA,Y\u0003\u0015\u0019\b/\u0019:l\u0015\tI&,\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00027\u0006\u0019qN]4\u0011\u0005u\u000bQ\"\u0001*\u00035\u0011+7-[:j_:$&/Z3N_\u0012,GNU3bI^\u0013\u0018\u000e^3\u0014\u0005\u0005\u0001\u0007CA1e\u001b\u0005\u0011'\"A2\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0014'AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005a&!C*qY&$H)\u0019;b'\u0011\u0019\u0001m\u001b8\u0011\u0005\u0005d\u0017BA7c\u0005\u001d\u0001&o\u001c3vGR\u0004\"a\\<\u000f\u0005A,hBA9u\u001b\u0005\u0011(BA:h\u0003\u0019a$o\\8u}%\t1-\u0003\u0002wE\u00069\u0001/Y2lC\u001e,\u0017B\u0001=z\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t1(-\u0001\u0007gK\u0006$XO]3J]\u0012,\u00070F\u0001}!\t\tW0\u0003\u0002\u007fE\n\u0019\u0011J\u001c;\u0002\u001b\u0019,\u0017\r^;sK&sG-\u001a=!\u0003eaWM\u001a;DCR,wm\u001c:jKN|%\u000f\u00165sKNDw\u000e\u001c3\u0016\u0005\u0005\u0015\u0001#B1\u0002\b\u0005-\u0011bAA\u0005E\n)\u0011I\u001d:bsB\u0019\u0011-!\u0004\n\u0007\u0005=!M\u0001\u0004E_V\u0014G.Z\u0001\u001bY\u00164GoQ1uK\u001e|'/[3t\u001fJ$\u0006N]3tQ>dG\rI\u0001\u000e]Vl7)\u0019;fO>\u0014\u0018.Z:\u0002\u001d9,XnQ1uK\u001e|'/[3tAQA\u0011\u0011DA\u000f\u0003?\t\t\u0003E\u0002\u0002\u001c\ri\u0011!\u0001\u0005\u0006u*\u0001\r\u0001 \u0005\b\u0003\u0003Q\u0001\u0019AA\u0003\u0011\u0019\t\u0019B\u0003a\u0001y\u0006Aq-\u001a;Ta2LG/\u0006\u0002\u0002(A\u0019Q,!\u000b\n\u0007\u0005-\"KA\u0003Ta2LG/\u0001\u0003d_BLH\u0003CA\r\u0003c\t\u0019$!\u000e\t\u000fid\u0001\u0013!a\u0001y\"I\u0011\u0011\u0001\u0007\u0011\u0002\u0003\u0007\u0011Q\u0001\u0005\t\u0003'a\u0001\u0013!a\u0001y\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA\u001eU\ra\u0018QH\u0016\u0003\u0003\u007f\u0001B!!\u0011\u0002L5\u0011\u00111\t\u0006\u0005\u0003\u000b\n9%A\u0005v]\u000eDWmY6fI*\u0019\u0011\u0011\n2\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002N\u0005\r#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA*U\u0011\t)!!\u0010\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!a\u0017\u0011\t\u0005u\u0013qM\u0007\u0003\u0003?RA!!\u0019\u0002d\u0005!A.\u00198h\u0015\t\t)'\u0001\u0003kCZ\f\u0017\u0002BA5\u0003?\u0012aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003c\n9\bE\u0002b\u0003gJ1!!\u001ec\u0005\r\te.\u001f\u0005\t\u0003s\u0012\u0012\u0011!a\u0001y\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a \u0011\r\u0005\u0005\u0015qQA9\u001b\t\t\u0019IC\u0002\u0002\u0006\n\f!bY8mY\u0016\u001cG/[8o\u0013\u0011\tI)a!\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u001f\u000b)\nE\u0002b\u0003#K1!a%c\u0005\u001d\u0011un\u001c7fC:D\u0011\"!\u001f\u0015\u0003\u0003\u0005\r!!\u001d\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u00037\nY\n\u0003\u0005\u0002zU\t\t\u00111\u0001}\u0003!A\u0017m\u001d5D_\u0012,G#\u0001?\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0017\u0002\r\u0015\fX/\u00197t)\u0011\ty)!+\t\u0013\u0005e\u0004$!AA\u0002\u0005E\u0014!C*qY&$H)\u0019;b!\r\tYBG\n\u00055\u0001\f\t\f\u0005\u0003\u00024\u0006eVBAA[\u0015\u0011\t9,a\u0019\u0002\u0005%|\u0017b\u0001=\u00026R\u0011\u0011QV\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u00033\t\t\rC\u0004\u0002Dr\u0001\r!a\n\u0002\u000bM\u0004H.\u001b;\u0015\u0011\u0005e\u0011qYAe\u0003\u0017DQA_\u000fA\u0002qDq!!\u0001\u001e\u0001\u0004\t)\u0001\u0003\u0004\u0002\u0014u\u0001\r\u0001`\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\t.!8\u0011\u000b\u0005\f\u0019.a6\n\u0007\u0005U'M\u0001\u0004PaRLwN\u001c\t\bC\u0006eG0!\u0002}\u0013\r\tYN\u0019\u0002\u0007)V\u0004H.Z\u001a\t\u0013\u0005}g$!AA\u0002\u0005e\u0011a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u001d\t\u0005\u0003;\n9/\u0003\u0003\u0002j\u0006}#AB(cU\u0016\u001cGO\u0001\u0005O_\u0012,G)\u0019;b'\u0011\u0001\u0003m\u001b8\u0002\u0005%$\u0017aA5eA\u0005Q\u0001O]3eS\u000e$\u0018n\u001c8\u0016\u0005\u0005-\u0011a\u00039sK\u0012L7\r^5p]\u0002\n\u0001\"[7qkJLG/_\u0001\nS6\u0004XO]5us\u0002\nQ\"[7qkJLG/_*uCR\u001c\u0018AD5naV\u0014\u0018\u000e^=Ti\u0006$8\u000fI\u0001\te\u0006<8i\\;oiV\u0011!Q\u0001\t\u0004C\n\u001d\u0011b\u0001B\u0005E\n!Aj\u001c8h\u0003%\u0011\u0018m^\"pk:$\b%\u0001\u0003hC&t\u0017!B4bS:\u0004\u0013!\u00037fMR\u001c\u0005.\u001b7e\u0003)aWM\u001a;DQ&dG\rI\u0001\u000be&<\u0007\u000e^\"iS2$\u0017a\u0003:jO\"$8\t[5mI\u0002*\"!!\u0007\u0002\rM\u0004H.\u001b;!)Q\u0011yB!\t\u0003$\t\u0015\"q\u0005B\u0015\u0005W\u0011iCa\f\u00032A\u0019\u00111\u0004\u0011\t\r\u0005=8\u00071\u0001}\u0011\u001d\t\u0019p\ra\u0001\u0003\u0017Aq!!?4\u0001\u0004\tY\u0001C\u0004\u0002~N\u0002\r!!\u0002\t\u000f\t\u00051\u00071\u0001\u0003\u0006!9!QB\u001aA\u0002\u0005-\u0001B\u0002B\tg\u0001\u0007A\u0010\u0003\u0004\u0003\u0016M\u0002\r\u0001 \u0005\b\u0003\u0007\u001c\u0004\u0019AA\r)Q\u0011yB!\u000e\u00038\te\"1\bB\u001f\u0005\u007f\u0011\tEa\u0011\u0003F!A\u0011q\u001e\u001b\u0011\u0002\u0003\u0007A\u0010C\u0005\u0002tR\u0002\n\u00111\u0001\u0002\f!I\u0011\u0011 \u001b\u0011\u0002\u0003\u0007\u00111\u0002\u0005\n\u0003{$\u0004\u0013!a\u0001\u0003\u000bA\u0011B!\u00015!\u0003\u0005\rA!\u0002\t\u0013\t5A\u0007%AA\u0002\u0005-\u0001\u0002\u0003B\tiA\u0005\t\u0019\u0001?\t\u0011\tUA\u0007%AA\u0002qD\u0011\"a15!\u0003\u0005\r!!\u0007\u0016\u0005\t%#\u0006BA\u0006\u0003{\tabY8qs\u0012\"WMZ1vYR$C'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\tE#\u0006\u0002B\u0003\u0003{\tabY8qs\u0012\"WMZ1vYR$c'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%q\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012JTC\u0001B/U\u0011\tI\"!\u0010\u0015\t\u0005E$\u0011\r\u0005\t\u0003s\u0002\u0015\u0011!a\u0001yR!\u0011q\u0012B3\u0011%\tIHQA\u0001\u0002\u0004\t\t\b\u0006\u0003\u0002\\\t%\u0004\u0002CA=\u0007\u0006\u0005\t\u0019\u0001?\u0015\t\u0005=%Q\u000e\u0005\n\u0003s2\u0015\u0011!a\u0001\u0003c\n\u0001BT8eK\u0012\u000bG/\u0019\t\u0004\u00037A5\u0003\u0002%a\u0003c#\"A!\u001d\u0002\u000b\t,\u0018\u000e\u001c3\u0015\r\tm$q\u0011BI!\u0019\t'Q\u0010BAy&\u0019!q\u00102\u0003\rQ+\b\u000f\\33!\u0015y'1\u0011B\u0010\u0013\r\u0011))\u001f\u0002\u0004'\u0016\f\bb\u0002BE\u0015\u0002\u0007!1R\u0001\u0005]>$W\rE\u0002^\u0005\u001bK1Aa$S\u0005\u0011qu\u000eZ3\t\r\u0005=(\n1\u0001}\u0003IIgNZ3s\u001dVl\u0007+\u0019:uSRLwN\\:\u0015\u0007q\u00149\nC\u0004\u0003\u001a.\u0003\rA!\u0002\u0002\u00119,XNT8eKN$BCa\b\u0003\u001e\n}%\u0011\u0015BR\u0005K\u00139K!+\u0003,\n5\u0006BBAx\u0019\u0002\u0007A\u0010C\u0004\u0002t2\u0003\r!a\u0003\t\u000f\u0005eH\n1\u0001\u0002\f!9\u0011Q 'A\u0002\u0005\u0015\u0001b\u0002B\u0001\u0019\u0002\u0007!Q\u0001\u0005\b\u0005\u001ba\u0005\u0019AA\u0006\u0011\u0019\u0011\t\u0002\u0014a\u0001y\"1!Q\u0003'A\u0002qDq!a1M\u0001\u0004\tI\u0002\u0006\u0003\u00032\ne\u0006#B1\u0002T\nM\u0006CE1\u00036r\fY!a\u0003\u0002\u0006\t\u0015\u00111\u0002?}\u00033I1Aa.c\u0005\u0019!V\u000f\u001d7fs!I\u0011q\\'\u0002\u0002\u0003\u0007!qD\u0001\u000eY>\fG\r\u0016:fK:{G-Z:\u0015\u0011\t-%q\u0018Bi\u0005SDqA!1P\u0001\u0004\u0011\u0019-\u0001\u0003qCRD\u0007\u0003\u0002Bc\u0005\u001btAAa2\u0003JB\u0011\u0011OY\u0005\u0004\u0005\u0017\u0014\u0017A\u0002)sK\u0012,g-\u0003\u0003\u0002j\t='b\u0001BfE\"9!1[(A\u0002\tU\u0017\u0001C7fi\u0006$\u0017\r^1\u0011\t\t]'1\u001d\b\u0005\u00053\u0014y.\u0004\u0002\u0003\\*\u0019!Q\u001c+\u0002\tU$\u0018\u000e\\\u0005\u0005\u0005C\u0014Y.A\nEK\u001a\fW\u000f\u001c;QCJ\fWn\u001d*fC\u0012,'/\u0003\u0003\u0003f\n\u001d(\u0001C'fi\u0006$\u0017\r^1\u000b\t\t\u0005(1\u001c\u0005\b\u0005W|\u0005\u0019\u0001Bw\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o!\u0011\u0011yO!>\u000e\u0005\tE(b\u0001Bz-\u0006\u00191/\u001d7\n\t\t](\u0011\u001f\u0002\r'B\f'o[*fgNLwN\\\u0001\u0013EVLG\u000e\u001a+sK\u00164%o\\7O_\u0012,7\u000f\u0006\u0004\u0003\f\nu81\u0001\u0005\b\u0005\u007f\u0004\u0006\u0019AB\u0001\u0003\u0011!\u0017\r^1\u0011\u000b\u0005\f9Aa\b\t\u000f\r\u0015\u0001\u000b1\u0001\u0003D\u0006a\u0011.\u001c9ve&$\u0018\u0010V=qK\u0002"
)
public final class DecisionTreeModelReadWrite {
   public static Node buildTreeFromNodes(final NodeData[] data, final String impurityType) {
      return DecisionTreeModelReadWrite$.MODULE$.buildTreeFromNodes(data, impurityType);
   }

   public static Node loadTreeNodes(final String path, final DefaultParamsReader.Metadata metadata, final SparkSession sparkSession) {
      return DecisionTreeModelReadWrite$.MODULE$.loadTreeNodes(path, metadata, sparkSession);
   }

   public static class SplitData implements Product, Serializable {
      private final int featureIndex;
      private final double[] leftCategoriesOrThreshold;
      private final int numCategories;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int featureIndex() {
         return this.featureIndex;
      }

      public double[] leftCategoriesOrThreshold() {
         return this.leftCategoriesOrThreshold;
      }

      public int numCategories() {
         return this.numCategories;
      }

      public Split getSplit() {
         if (this.numCategories() != -1) {
            return new CategoricalSplit(this.featureIndex(), this.leftCategoriesOrThreshold(), this.numCategories());
         } else {
            .MODULE$.assert(this.leftCategoriesOrThreshold().length == 1, () -> {
               ArraySeq.ofDouble var10000 = .MODULE$.wrapDoubleArray(this.leftCategoriesOrThreshold());
               return "DecisionTree split data expected 1 threshold for ContinuousSplit, but found thresholds: " + var10000.mkString(", ");
            });
            return new ContinuousSplit(this.featureIndex(), this.leftCategoriesOrThreshold()[0]);
         }
      }

      public SplitData copy(final int featureIndex, final double[] leftCategoriesOrThreshold, final int numCategories) {
         return new SplitData(featureIndex, leftCategoriesOrThreshold, numCategories);
      }

      public int copy$default$1() {
         return this.featureIndex();
      }

      public double[] copy$default$2() {
         return this.leftCategoriesOrThreshold();
      }

      public int copy$default$3() {
         return this.numCategories();
      }

      public String productPrefix() {
         return "SplitData";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.featureIndex());
            }
            case 1 -> {
               return this.leftCategoriesOrThreshold();
            }
            case 2 -> {
               return BoxesRunTime.boxToInteger(this.numCategories());
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
         return x$1 instanceof SplitData;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "featureIndex";
            }
            case 1 -> {
               return "leftCategoriesOrThreshold";
            }
            case 2 -> {
               return "numCategories";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.featureIndex());
         var1 = Statics.mix(var1, Statics.anyHash(this.leftCategoriesOrThreshold()));
         var1 = Statics.mix(var1, this.numCategories());
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label40: {
               if (x$1 instanceof SplitData) {
                  SplitData var4 = (SplitData)x$1;
                  if (this.featureIndex() == var4.featureIndex() && this.numCategories() == var4.numCategories() && this.leftCategoriesOrThreshold() == var4.leftCategoriesOrThreshold() && var4.canEqual(this)) {
                     break label40;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public SplitData(final int featureIndex, final double[] leftCategoriesOrThreshold, final int numCategories) {
         this.featureIndex = featureIndex;
         this.leftCategoriesOrThreshold = leftCategoriesOrThreshold;
         this.numCategories = numCategories;
         Product.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class SplitData$ implements Serializable {
      public static final SplitData$ MODULE$ = new SplitData$();

      public SplitData apply(final Split split) {
         if (split instanceof CategoricalSplit var4) {
            return new SplitData(var4.featureIndex(), var4.leftCategories(), var4.numCategories());
         } else if (split instanceof ContinuousSplit var5) {
            return new SplitData(var5.featureIndex(), new double[]{var5.threshold()}, -1);
         } else {
            throw new MatchError(split);
         }
      }

      public SplitData apply(final int featureIndex, final double[] leftCategoriesOrThreshold, final int numCategories) {
         return new SplitData(featureIndex, leftCategoriesOrThreshold, numCategories);
      }

      public Option unapply(final SplitData x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.featureIndex()), x$0.leftCategoriesOrThreshold(), BoxesRunTime.boxToInteger(x$0.numCategories()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SplitData$.class);
      }
   }

   public static class NodeData implements Product, Serializable {
      private final int id;
      private final double prediction;
      private final double impurity;
      private final double[] impurityStats;
      private final long rawCount;
      private final double gain;
      private final int leftChild;
      private final int rightChild;
      private final SplitData split;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int id() {
         return this.id;
      }

      public double prediction() {
         return this.prediction;
      }

      public double impurity() {
         return this.impurity;
      }

      public double[] impurityStats() {
         return this.impurityStats;
      }

      public long rawCount() {
         return this.rawCount;
      }

      public double gain() {
         return this.gain;
      }

      public int leftChild() {
         return this.leftChild;
      }

      public int rightChild() {
         return this.rightChild;
      }

      public SplitData split() {
         return this.split;
      }

      public NodeData copy(final int id, final double prediction, final double impurity, final double[] impurityStats, final long rawCount, final double gain, final int leftChild, final int rightChild, final SplitData split) {
         return new NodeData(id, prediction, impurity, impurityStats, rawCount, gain, leftChild, rightChild, split);
      }

      public int copy$default$1() {
         return this.id();
      }

      public double copy$default$2() {
         return this.prediction();
      }

      public double copy$default$3() {
         return this.impurity();
      }

      public double[] copy$default$4() {
         return this.impurityStats();
      }

      public long copy$default$5() {
         return this.rawCount();
      }

      public double copy$default$6() {
         return this.gain();
      }

      public int copy$default$7() {
         return this.leftChild();
      }

      public int copy$default$8() {
         return this.rightChild();
      }

      public SplitData copy$default$9() {
         return this.split();
      }

      public String productPrefix() {
         return "NodeData";
      }

      public int productArity() {
         return 9;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.id());
            }
            case 1 -> {
               return BoxesRunTime.boxToDouble(this.prediction());
            }
            case 2 -> {
               return BoxesRunTime.boxToDouble(this.impurity());
            }
            case 3 -> {
               return this.impurityStats();
            }
            case 4 -> {
               return BoxesRunTime.boxToLong(this.rawCount());
            }
            case 5 -> {
               return BoxesRunTime.boxToDouble(this.gain());
            }
            case 6 -> {
               return BoxesRunTime.boxToInteger(this.leftChild());
            }
            case 7 -> {
               return BoxesRunTime.boxToInteger(this.rightChild());
            }
            case 8 -> {
               return this.split();
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
         return x$1 instanceof NodeData;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "id";
            }
            case 1 -> {
               return "prediction";
            }
            case 2 -> {
               return "impurity";
            }
            case 3 -> {
               return "impurityStats";
            }
            case 4 -> {
               return "rawCount";
            }
            case 5 -> {
               return "gain";
            }
            case 6 -> {
               return "leftChild";
            }
            case 7 -> {
               return "rightChild";
            }
            case 8 -> {
               return "split";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.id());
         var1 = Statics.mix(var1, Statics.doubleHash(this.prediction()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.impurity()));
         var1 = Statics.mix(var1, Statics.anyHash(this.impurityStats()));
         var1 = Statics.mix(var1, Statics.longHash(this.rawCount()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.gain()));
         var1 = Statics.mix(var1, this.leftChild());
         var1 = Statics.mix(var1, this.rightChild());
         var1 = Statics.mix(var1, Statics.anyHash(this.split()));
         return Statics.finalizeHash(var1, 9);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label79: {
               if (x$1 instanceof NodeData) {
                  NodeData var4 = (NodeData)x$1;
                  if (this.id() == var4.id() && this.prediction() == var4.prediction() && this.impurity() == var4.impurity() && this.rawCount() == var4.rawCount() && this.gain() == var4.gain() && this.leftChild() == var4.leftChild() && this.rightChild() == var4.rightChild() && this.impurityStats() == var4.impurityStats()) {
                     label72: {
                        SplitData var10000 = this.split();
                        SplitData var5 = var4.split();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label72;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label72;
                        }

                        if (var4.canEqual(this)) {
                           break label79;
                        }
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      public NodeData(final int id, final double prediction, final double impurity, final double[] impurityStats, final long rawCount, final double gain, final int leftChild, final int rightChild, final SplitData split) {
         this.id = id;
         this.prediction = prediction;
         this.impurity = impurity;
         this.impurityStats = impurityStats;
         this.rawCount = rawCount;
         this.gain = gain;
         this.leftChild = leftChild;
         this.rightChild = rightChild;
         this.split = split;
         Product.$init$(this);
      }
   }

   public static class NodeData$ implements Serializable {
      public static final NodeData$ MODULE$ = new NodeData$();

      public Tuple2 build(final Node node, final int id) {
         if (node instanceof InternalNode var7) {
            Tuple2 var9 = this.build(var7.leftChild(), id + 1);
            if (var9 != null) {
               Seq leftNodeData = (Seq)var9._1();
               int leftIdx = var9._2$mcI$sp();
               Tuple2 var8 = new Tuple2(leftNodeData, BoxesRunTime.boxToInteger(leftIdx));
               Seq leftNodeData = (Seq)var8._1();
               int leftIdx = var8._2$mcI$sp();
               Tuple2 var15 = this.build(var7.rightChild(), leftIdx + 1);
               if (var15 != null) {
                  Seq rightNodeData = (Seq)var15._1();
                  int rightIdx = var15._2$mcI$sp();
                  Tuple2 var14 = new Tuple2(rightNodeData, BoxesRunTime.boxToInteger(rightIdx));
                  Seq rightNodeData = (Seq)var14._1();
                  int rightIdx = var14._2$mcI$sp();
                  NodeData thisNodeData = new NodeData(id, var7.prediction(), var7.impurity(), var7.impurityStats().stats(), var7.impurityStats().rawCount(), var7.gain(), ((NodeData)leftNodeData.head()).id(), ((NodeData)rightNodeData.head()).id(), DecisionTreeModelReadWrite.SplitData$.MODULE$.apply(var7.split()));
                  return new Tuple2(((SeqOps)leftNodeData.$plus$plus(rightNodeData)).$plus$colon(thisNodeData), BoxesRunTime.boxToInteger(rightIdx));
               } else {
                  throw new MatchError(var15);
               }
            } else {
               throw new MatchError(var9);
            }
         } else if (node instanceof LeafNode) {
            return new Tuple2(new scala.collection.immutable..colon.colon(new NodeData(id, node.prediction(), node.impurity(), node.impurityStats().stats(), node.impurityStats().rawCount(), (double)-1.0F, -1, -1, new SplitData(-1, scala.Array..MODULE$.emptyDoubleArray(), -1)), scala.collection.immutable.Nil..MODULE$), BoxesRunTime.boxToInteger(id));
         } else {
            throw new MatchError(node);
         }
      }

      public int inferNumPartitions(final long numNodes) {
         .MODULE$.require(numNodes > 0L);
         return (int)scala.runtime.RichDouble..MODULE$.ceil$extension(.MODULE$.doubleWrapper((double)numNodes / (double)7280000.0F));
      }

      public NodeData apply(final int id, final double prediction, final double impurity, final double[] impurityStats, final long rawCount, final double gain, final int leftChild, final int rightChild, final SplitData split) {
         return new NodeData(id, prediction, impurity, impurityStats, rawCount, gain, leftChild, rightChild, split);
      }

      public Option unapply(final NodeData x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple9(BoxesRunTime.boxToInteger(x$0.id()), BoxesRunTime.boxToDouble(x$0.prediction()), BoxesRunTime.boxToDouble(x$0.impurity()), x$0.impurityStats(), BoxesRunTime.boxToLong(x$0.rawCount()), BoxesRunTime.boxToDouble(x$0.gain()), BoxesRunTime.boxToInteger(x$0.leftChild()), BoxesRunTime.boxToInteger(x$0.rightChild()), x$0.split())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(NodeData$.class);
      }
   }
}
