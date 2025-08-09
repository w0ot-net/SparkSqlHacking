package breeze.io;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.nio.channels.FileChannel;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;
import spire.math.ULong;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011\u0005d\u0001\u00027n\u0001ID!\"!\u0004\u0001\u0005\u0003\u0005\u000b\u0011BA\b\u0011)\t)\u0002\u0001B\u0001B\u0003%\u0011q\u0003\u0005\u000b\u0003c\u0001!\u0011!Q\u0001\f\u0005M\u0002bBA\u001e\u0001\u0011\u0005\u0011Q\b\u0005\b\u0003w\u0001A\u0011AA%\u0011%\t)\u0006\u0001b\u0001\n\u0003\t9\u0006\u0003\u0005\u0002^\u0001\u0001\u000b\u0011BA-\u0011\u001d\ty\u0006\u0001C\u0003\u0003CBq!a\u0018\u0001\t\u000b\tY\bC\u0004\u0002\u0010\u0002!)!!%\t\u000f\u0005=\u0005\u0001\"\u0002\u0002 \"9\u0011Q\u0015\u0001\u0005F\u0005\u0005\u0004bBAS\u0001\u0011\u0015\u0011\u0011\u0016\u0005\b\u0003_\u0003AQAAY\u0011\u001d\ty\u000b\u0001C\u0003\u0003oCq!!0\u0001\t\u000b\ty\fC\u0004\u0002>\u0002!)!a1\t\u000f\u0005E\u0007\u0001\"\u0002\u0002T\"9\u0011\u0011\u001b\u0001\u0005\u0006\u0005m\u0007bBAr\u0001\u0011\u0015\u0013q\u0018\u0005\b\u0003G\u0004AQAAt\u0011\u001d\ti\u000f\u0001C\u0003\u0003_Dq!!<\u0001\t\u000b\t)\u0010C\u0004\u0002|\u0002!\t!!@\t\u000f\u0005m\b\u0001\"\u0002\u0003\u0002!9!q\u0001\u0001\u0005\u0006\t%\u0001b\u0002B\u0004\u0001\u0011\u0015!q\u0002\u0005\b\u0005+\u0001AQIA\u007f\u0011\u001d\u0011)\u0002\u0001C\u0003\u00053AqAa\b\u0001\t\u000b\u0011\t\u0003C\u0004\u0003 \u0001!)Ea\n\t\u000f\t}\u0001\u0001\"\u0002\u0003.!9!1\u0007\u0001\u0005\u0002\tU\u0002b\u0002B\u001a\u0001\u0011\u0015!q\b\u0005\b\u0005\u000f\u0002AQ\u0001B%\u0011\u001d\u00119\u0005\u0001C\u0003\u0005\u001fBqA!\u0016\u0001\t\u000b\ny\fC\u0004\u0003V\u0001!)A!\u0017\t\u000f\t\u0005\u0004\u0001\"\u0012\u00036!9!\u0011\r\u0001\u0005\u0006\t\u0015\u0004b\u0002B6\u0001\u0011\u0015!Q\u000e\u0005\b\u0005W\u0002AQ\u0001B:\u0011\u001d\u0011I\b\u0001C\u0003\u0005wBqA!\u001f\u0001\t\u000b\u0012\t\tC\u0004\u0003\b\u0002!)A!#\t\u000f\t=\u0005\u0001\"\u0002\u0002@\"9!q\u0012\u0001\u0005\u0006\tM\u0005b\u0002BM\u0001\u0011\u0015!1\u0014\u0005\b\u00053\u0003AQ\u0001BQ\u0011\u001d\u00119\u000b\u0001C#\u0003\u007fCqAa*\u0001\t\u000b\u0011Y\u000bC\u0004\u00032\u0002!)Aa-\t\u000f\tE\u0006\u0001\"\u0002\u0003:\"9!q\u0018\u0001\u0005\u0006\t\u0005\u0007b\u0002B`\u0001\u0011\u0015!1\u001a\u0005\b\u0005'\u0004AQ\u0001Bk\u0011\u001d\u0011\u0019\u000e\u0001C\u0003\u00057DqA!9\u0001\t\u0003\u0011\t\rC\u0004\u0003b\u0002!)A!:\t\u000f\t-\b\u0001\"\u0002\u0003n\"9!1\u001e\u0001\u0005\u0006\tM\bb\u0002B}\u0001\u0011\u0015#\u0011\u0019\u0005\b\u0005s\u0004AQ\u0001B\u007f\u0011\u001d\u0019\u0019\u0001\u0001C\u0003\u0007\u000bAqaa\u0001\u0001\t\u000b\u0019Y\u0001C\u0004\u0004\u0012\u0001!)aa\u0005\t\u000f\rE\u0001\u0001\"\u0002\u0004(!91q\u0006\u0001\u0005\u0006\rE\u0002bBB\u0018\u0001\u0011\u00151q\u0007\u0005\b\u0007{\u0001AQ\u0001Ba\u0011\u001d\u0019i\u0004\u0001C\u0003\u0007\u0003Bqaa\u0012\u0001\t\u000b\u0019I\u0005C\u0004\u0004H\u0001!)aa\u0014\t\u000f\rU\u0003\u0001\"\u0011\u0004X!91\u0011\r\u0001\u0005B\r\r\u0004bBB+\u0001\u0011\u00151Q\u000e\u0005\b\u0007C\u0002AQAB;\u0011\u001d\u0019i\b\u0001C\u0001\u0007\u007fBqa!\"\u0001\t\u0003\u00199\tC\u0004\u0004\u0006\u0002!\ta!$\t\u000f\ru\u0004\u0001\"\u0001\u0004\u0014\"91\u0011\u0014\u0001\u0005F\rm\u0005bBBR\u0001\u0011\u00053Q\u0015\u0005\b\u0007G\u0003A\u0011IBV\u0011\u001d\u00199\f\u0001C!\u0007sCqaa0\u0001\t\u0003\u001aI\fC\u0004\u0004B\u0002!)ea1\t\u000f\r\u001d\u0007\u0001\"\u0011\u0004J\"91Q\u001a\u0001\u0005B\r=\u0007bBBj\u0001\u0011\u00051Q\u001b\u0005\b\u00073\u0004A\u0011ABn\u0011\u001d\u0019i\u000e\u0001C\u0001\u0007?Dqa!:\u0001\t\u0003\u0019Y\u000eC\u0004\u0004h\u0002!\ta!;\t\u000f\r=\b\u0001\"\u0001\u0004r\"911\u001f\u0001\u0005\u0002\rU\bb\u0002C\u0004\u0001\u0011\u0005A\u0011\u0002\u0005\b\u0003_\u0003A\u0011\u0001C\t\u0011\u001d\ty\u000b\u0001C\u0001\t+Aq\u0001\"\b\u0001\t\u0003!y\u0002C\u0004\u0005$\u0001!\t\u0001\"\n\t\u000f\u0011%\u0002\u0001\"\u0001\u0005,\u001dIA\u0011G7\u0002\u0002#\u0005A1\u0007\u0004\tY6\f\t\u0011#\u0001\u00056!9\u00111\b5\u0005\u0002\u0011u\u0002\"\u0003C QF\u0005I\u0011\u0001C!\u0011%!9\u0006[I\u0001\n\u0003!IF\u0001\tSC:$w.\\!dG\u0016\u001c8OR5mK*\u0011an\\\u0001\u0003S>T\u0011\u0001]\u0001\u0007EJ,WM_3\u0004\u0001M9\u0001a]>\u0002\u0002\u0005\u001d\u0001C\u0001;z\u001b\u0005)(B\u0001<x\u0003\u0011a\u0017M\\4\u000b\u0003a\fAA[1wC&\u0011!0\u001e\u0002\u0007\u001f\nTWm\u0019;\u0011\u0005qtX\"A?\u000b\u00059<\u0018BA@~\u0005%!\u0015\r^1J]B,H\u000fE\u0002}\u0003\u0007I1!!\u0002~\u0005)!\u0015\r^1PkR\u0004X\u000f\u001e\t\u0004y\u0006%\u0011bAA\u0006{\nI1\t\\8tK\u0006\u0014G.Z\u0001\u0005M&dW\rE\u0002}\u0003#I1!a\u0005~\u0005\u00111\u0015\u000e\\3\u0002\t\u0005\u0014x\r\r\t\u0005\u00033\tYC\u0004\u0003\u0002\u001c\u0005\u001d\u0002\u0003BA\u000f\u0003Gi!!a\b\u000b\u0007\u0005\u0005\u0012/\u0001\u0004=e>|GO\u0010\u0006\u0003\u0003K\tQa]2bY\u0006LA!!\u000b\u0002$\u00051\u0001K]3eK\u001aLA!!\f\u00020\t11\u000b\u001e:j]\u001eTA!!\u000b\u0002$\u0005I1m\u001c8wKJ$XM\u001d\t\u0005\u0003k\t9$D\u0001n\u0013\r\tI$\u001c\u0002\u000e\u0005f$XmQ8om\u0016\u0014H/\u001a:\u0002\rqJg.\u001b;?)\u0019\ty$!\u0012\u0002HQ!\u0011\u0011IA\"!\r\t)\u0004\u0001\u0005\n\u0003c!\u0001\u0013!a\u0002\u0003gAq!!\u0004\u0005\u0001\u0004\ty\u0001C\u0005\u0002\u0016\u0011\u0001\n\u00111\u0001\u0002\u0018Q1\u00111JA(\u0003'\"B!!\u0011\u0002N!9\u0011\u0011G\u0003A\u0004\u0005M\u0002bBA)\u000b\u0001\u0007\u0011qC\u0001\tM&dWM\\1nK\"9\u0011QC\u0003A\u0002\u0005]\u0011A\u0002:bM>\u0013'.\u0006\u0002\u0002ZA\u0019A0a\u0017\n\u00051l\u0018a\u0002:bM>\u0013'\u000eI\u0001\te\u0016\fG-\u00138uqQ\u0011\u00111\r\t\u0005\u0003K\n9'\u0004\u0002\u0002$%!\u0011\u0011NA\u0012\u0005\u0011\u0011\u0015\u0010^3)\u000b!\ti'!\u001f\u0011\r\u0005\u0015\u0014qNA:\u0013\u0011\t\t(a\t\u0003\rQD'o\\<t!\ra\u0018QO\u0005\u0004\u0003oj(aC%P\u000bb\u001cW\r\u001d;j_:\u001c#!a\u001d\u0015\t\u0005u\u00141\u0011\t\u0007\u0003K\ny(a\u0019\n\t\u0005\u0005\u00151\u0005\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\b\u0003\u000bK\u0001\u0019AAD\u0003\u0005q\u0007\u0003BA3\u0003\u0013KA!a#\u0002$\t\u0019\u0011J\u001c;)\u000b%\ti'!\u001f\u0002\u0013]\u0014\u0018\u000e^3J]RDD\u0003BAJ\u00033\u0003B!!\u001a\u0002\u0016&!\u0011qSA\u0012\u0005\u0011)f.\u001b;\t\u000f\u0005m%\u00021\u0001\u0002d\u0005\ta\u000fK\u0003\u000b\u0003[\nI\b\u0006\u0003\u0002\u0014\u0006\u0005\u0006bBAN\u0017\u0001\u0007\u0011Q\u0010\u0015\u0006\u0017\u00055\u0014\u0011P\u0001\te\u0016\fGMQ=uK\"*A\"!\u001c\u0002zQ!\u0011QPAV\u0011\u001d\t))\u0004a\u0001\u0003\u000fCS!DA7\u0003s\nQa\u001e:ji\u0016$B!a%\u00024\"9\u00111\u0014\bA\u0002\u0005\r\u0004&\u0002\b\u0002n\u0005eD\u0003BAJ\u0003sCq!a'\u0010\u0001\u0004\ti\bK\u0003\u0010\u0003[\nI(A\u0005sK\u0006$W+\u00138uqQ\u0011\u0011q\u0011\u0015\u0006!\u00055\u0014\u0011\u0010\u000b\u0005\u0003\u000b\fi\r\u0005\u0004\u0002f\u0005}\u0014q\u0019\t\u0005\u0003K\nI-\u0003\u0003\u0002L\u0006\r\"!B*i_J$\bbBAC#\u0001\u0007\u0011q\u0011\u0015\u0006#\u00055\u0014\u0011P\u0001\u000boJLG/Z+J]RDD\u0003BAJ\u0003+Dq!a6\u0013\u0001\u0004\t9-A\u0003wC2,X\rK\u0003\u0013\u0003[\nI\b\u0006\u0003\u0002\u0014\u0006u\u0007bBAp'\u0001\u0007\u0011QY\u0001\u0007m\u0006dW/Z:)\u000bM\ti'!\u001f\u0002!I,\u0017\rZ+og&<g.\u001a3CsR,\u0007&\u0002\u000b\u0002n\u0005eD\u0003BAc\u0003SDq!!\"\u0016\u0001\u0004\t9\tK\u0003\u0016\u0003[\nI(A\txe&$X-\u00168tS\u001etW\r\u001a\"zi\u0016$B!a%\u0002r\"9\u0011q\u001b\fA\u0002\u0005\u001d\u0007&\u0002\f\u0002n\u0005eD\u0003BAJ\u0003oDq!a8\u0018\u0001\u0004\t)\rK\u0003\u0018\u0003[\nI(A\u0005sK\u0006$\u0017J\u001c;2mQ\u0011\u0011q\u0019\u0015\u00061\u00055\u0014\u0011\u0010\u000b\u0005\u0003\u000b\u0014\u0019\u0001C\u0004\u0002\u0006f\u0001\r!a\")\u000be\ti'!\u001f\u0002\u0015]\u0014\u0018\u000e^3J]R\fd\u0007\u0006\u0003\u0002\u0014\n-\u0001bBAN5\u0001\u0007\u0011q\u0019\u0015\u00065\u00055\u0014\u0011\u0010\u000b\u0005\u0003'\u0013\t\u0002C\u0004\u0002\u001cn\u0001\r!!2)\u000bm\ti'!\u001f\u0002\u0013I,\u0017\rZ*i_J$\b&\u0002\u000f\u0002n\u0005eD\u0003BAc\u00057Aq!!\"\u001e\u0001\u0004\t9\tK\u0003\u001e\u0003[\nI(\u0001\u0006xe&$Xm\u00155peR$B!a%\u0003$!9\u00111\u0014\u0010A\u0002\u0005\u001d\u0007&\u0002\u0010\u0002n\u0005eD\u0003BAJ\u0005SAq!a' \u0001\u0004\t9\tK\u0003 \u0003[\nI\b\u0006\u0003\u0002\u0014\n=\u0002bBANA\u0001\u0007\u0011Q\u0019\u0015\u0006A\u00055\u0014\u0011P\u0001\u000be\u0016\fG-V%oiF2DC\u0001B\u001c!\u0011\t)G!\u000f\n\t\tm\u00121\u0005\u0002\u0005\u0007\"\f'\u000fK\u0003\"\u0003[\nI\b\u0006\u0003\u0003B\t\r\u0003CBA3\u0003\u007f\u00129\u0004C\u0004\u0002\u0006\n\u0002\r!a\")\u000b\t\ni'!\u001f\u0002\u0017]\u0014\u0018\u000e^3V\u0013:$\u0018G\u000e\u000b\u0005\u0003'\u0013Y\u0005C\u0004\u0002\u001c\u000e\u0002\rAa\u000e)\u000b\r\ni'!\u001f\u0015\t\u0005M%\u0011\u000b\u0005\b\u00037#\u0003\u0019\u0001B!Q\u0015!\u0013QNA=\u0003E\u0011X-\u00193V]NLwM\\3e'\"|'\u000f\u001e\u0015\u0006K\u00055\u0014\u0011\u0010\u000b\u0005\u00057\u0012i\u0006\u0005\u0004\u0002f\u0005}\u0014q\u0011\u0005\b\u0003\u000b3\u0003\u0019AADQ\u00151\u0013QNA=\u0003!\u0011X-\u00193DQ\u0006\u0014\b&B\u0014\u0002n\u0005eD\u0003\u0002B!\u0005OBq!!\")\u0001\u0004\t9\tK\u0003)\u0003[\nI(\u0001\nxe&$X-\u00168tS\u001etW\rZ*i_J$H\u0003BAJ\u0005_Bq!a6*\u0001\u0004\t9\tK\u0003*\u0003[\nI\b\u0006\u0003\u0002\u0014\nU\u0004bBAlU\u0001\u0007!1\f\u0015\u0006U\u00055\u0014\u0011P\u0001\noJLG/Z\"iCJ$B!a%\u0003~!9\u0011q[\u0016A\u0002\t]\u0002&B\u0016\u0002n\u0005eD\u0003BAJ\u0005\u0007Cq!a'-\u0001\u0004\t9\tK\u0003-\u0003[\nI(A\u0005Xe&$Xm\u00115beR!\u00111\u0013BF\u0011\u001d\t9.\fa\u0001\u0005\u0003BS!LA7\u0003s\n\u0011B]3bI&sGo\r\u001a)\u000b9\ni'!\u001f\u0015\t\tm#Q\u0013\u0005\b\u0003\u000b{\u0003\u0019AADQ\u0015y\u0013QNA=\u0003)9(/\u001b;f\u0013:$8G\r\u000b\u0005\u0003'\u0013i\nC\u0004\u0002\u001cB\u0002\r!a\")\u000bA\ni'!\u001f\u0015\t\u0005M%1\u0015\u0005\b\u00037\u000b\u0004\u0019\u0001B.Q\u0015\t\u0014QNA=\u0003\u001d\u0011X-\u00193J]RDSAMA7\u0003s\"BAa\u0017\u0003.\"9\u0011QQ\u001aA\u0002\u0005\u001d\u0005&B\u001a\u0002n\u0005e\u0014\u0001C<sSR,\u0017J\u001c;\u0015\t\u0005M%Q\u0017\u0005\b\u0003/$\u0004\u0019AADQ\u0015!\u0014QNA=)\u0011\t\u0019Ja/\t\u000f\u0005]W\u00071\u0001\u0003\\!*Q'!\u001c\u0002z\u0005Q!/Z1e+&sGo\r\u001a\u0015\u0005\t\r\u0007\u0003BA3\u0005\u000bLAAa2\u0002$\t!Aj\u001c8hQ\u00151\u0014QNA=)\u0011\u0011iMa4\u0011\r\u0005\u0015\u0014q\u0010Bb\u0011\u001d\t)i\u000ea\u0001\u0003\u000fCSaNA7\u0003s\n1b\u001e:ji\u0016,\u0016J\u001c;4eQ!\u00111\u0013Bl\u0011\u001d\tY\n\u000fa\u0001\u0005\u0007DS\u0001OA7\u0003s\"B!a%\u0003^\"9\u00111T\u001dA\u0002\t5\u0007&B\u001d\u0002n\u0005e\u0014!\u0003:fC\u0012Le\u000e\u001e\u001c5Q\u0015Q\u0014QNA=)\u0011\u0011iMa:\t\u000f\u0005\u00155\b1\u0001\u0002\b\"*1(!\u001c\u0002z\u0005QqO]5uK&sGO\u000e\u001b\u0015\t\u0005M%q\u001e\u0005\b\u00037c\u0004\u0019\u0001BbQ\u0015a\u0014QNA=)\u0011\t\u0019J!>\t\u000f\u0005mU\b1\u0001\u0003N\"*Q(!\u001c\u0002z\u0005A!/Z1e\u0019>tw\rK\u0003?\u0003[\nI\b\u0006\u0003\u0003N\n}\bbBAC\u007f\u0001\u0007\u0011q\u0011\u0015\u0006\u007f\u00055\u0014\u0011P\u0001\noJLG/\u001a'p]\u001e$B!a%\u0004\b!9\u0011q\u001b!A\u0002\t\r\u0007&\u0002!\u0002n\u0005eD\u0003BAJ\u0007\u001bAq!a6B\u0001\u0004\u0011i\rK\u0003B\u0003[\nI(\u0001\u0006sK\u0006$W+\u00138umQ\"\"a!\u0006\u0011\t\r]1\u0011E\u0007\u0003\u00073QAaa\u0007\u0004\u001e\u0005!Q.\u0019;i\u0015\t\u0019y\"A\u0003ta&\u0014X-\u0003\u0003\u0004$\re!!B+M_:<\u0007&\u0002\"\u0002n\u0005eD\u0003BB\u0015\u0007W\u0001b!!\u001a\u0002\u0000\rU\u0001bBAC\u0007\u0002\u0007\u0011q\u0011\u0015\u0006\u0007\u00065\u0014\u0011P\u0001\foJLG/Z+J]R4D\u0007\u0006\u0003\u0002\u0014\u000eM\u0002bBAN\t\u0002\u00071Q\u0003\u0015\u0006\t\u00065\u0014\u0011\u0010\u000b\u0005\u0003'\u001bI\u0004C\u0004\u0002\u001c\u0016\u0003\ra!\u000b)\u000b\u0015\u000bi'!\u001f\u0002#I,\u0017\rZ+J]R4Dg\u00155jMR,G\rK\u0003G\u0003[\nI\b\u0006\u0003\u0003N\u000e\r\u0003bBAC\u000f\u0002\u0007\u0011q\u0011\u0015\u0006\u000f\u00065\u0014\u0011P\u0001\u0013oJLG/Z+J]R4Dg\u00155jMR,G\r\u0006\u0003\u0002\u0014\u000e-\u0003bBAN\u0011\u0002\u0007!1\u0019\u0015\u0006\u0011\u00065\u0014\u0011\u0010\u000b\u0005\u0003'\u001b\t\u0006C\u0004\u0002\u001c&\u0003\rA!4)\u000b%\u000bi'!\u001f\u0002\u0015I,\u0017\r\u001a#pk\ndW\r\u0006\u0002\u0004ZA!\u0011QMB.\u0013\u0011\u0019i&a\t\u0003\r\u0011{WO\u00197fQ\u0015Q\u0015QNA=\u0003%\u0011X-\u00193GY>\fG\u000f\u0006\u0002\u0004fA!\u0011QMB4\u0013\u0011\u0019I'a\t\u0003\u000b\u0019cw.\u0019;)\u000b-\u000bi'!\u001f\u0015\t\r=4\u0011\u000f\t\u0007\u0003K\nyh!\u0017\t\u000f\u0005\u0015E\n1\u0001\u0002\b\"*A*!\u001c\u0002zQ!1qOB=!\u0019\t)'a \u0004f!9\u0011QQ'A\u0002\u0005\u001d\u0005&B'\u0002n\u0005e\u0014AC<sSR,g\t\\8biR!\u00111SBA\u0011\u001d\tYJ\u0014a\u0001\u0007KBSATA7\u0003s\n1b\u001e:ji\u0016$u.\u001e2mKR!\u00111SBE\u0011\u001d\tYj\u0014a\u0001\u00073BSaTA7\u0003s\"B!a%\u0004\u0010\"9\u00111\u0014)A\u0002\r=\u0004&\u0002)\u0002n\u0005eD\u0003BAJ\u0007+Cq!a'R\u0001\u0004\u00199\bK\u0003R\u0003[\nI(A\u0006sK\u0006$'i\\8mK\u0006tGCABO!\u0011\t)ga(\n\t\r\u0005\u00161\u0005\u0002\b\u0005>|G.Z1o\u0003%\u0011X-\u00193Gk2d\u0017\u0010\u0006\u0003\u0002\u0014\u000e\u001d\u0006bBBU'\u0002\u0007\u0011QP\u0001\u0002ERA\u00111SBW\u0007_\u001b\u0019\fC\u0004\u0004*R\u0003\r!! \t\u000f\rEF\u000b1\u0001\u0002\b\u0006\u0019qN\u001a4\t\u000f\rUF\u000b1\u0001\u0002\b\u0006\u0019A.\u001a8\u0002\u0011I,\u0017\r\u001a'j]\u0016$\"aa/\u0011\u0007Q\u001ci,C\u0002\u0002.U\fqA]3bIV#f)\u0001\u0006xe&$Xm\u00115beN$B!a%\u0004F\"9\u0011q[,A\u0002\u0005]\u0011\u0001C<sSR,W\u000b\u0016$\u0015\t\u0005M51\u001a\u0005\b\u0003/D\u0006\u0019AA\f\u0003%\u00198.\u001b9CsR,7\u000f\u0006\u0003\u0002\b\u000eE\u0007bBAC3\u0002\u0007\u0011qQ\u0001\nUVl\u0007OQ=uKN$B!a%\u0004X\"9\u0011Q\u0011.A\u0002\u0005\u001d\u0015AD4fi\u001aKG.\u001a)pS:$XM]\u000b\u0003\u0005\u0007\fAa]3fWR!\u00111SBq\u0011\u001d\u0019\u0019\u000f\u0018a\u0001\u0005\u0007\f1\u0001]8t\u0003\u0019aWM\\4uQ\u0006I1/\u001a;MK:<G\u000f\u001b\u000b\u0005\u0003'\u001bY\u000fC\u0004\u0004nz\u0003\rAa1\u0002\u00139,w\u000fT3oORD\u0017!B2m_N,GCAAJ\u0003)9W\r^\"iC:tW\r\\\u000b\u0003\u0007o\u0004Ba!?\u0005\u00045\u001111 \u0006\u0005\u0007{\u001cy0\u0001\u0005dQ\u0006tg.\u001a7t\u0015\r!\ta^\u0001\u0004]&|\u0017\u0002\u0002C\u0003\u0007w\u00141BR5mK\u000eC\u0017M\u001c8fY\u0006)q-\u001a;G\tV\u0011A1\u0002\t\u0004y\u00125\u0011b\u0001C\b{\nqa)\u001b7f\t\u0016\u001c8M]5qi>\u0014H\u0003BAJ\t'Aqa!+c\u0001\u0004\t9\t\u0006\u0005\u0002\u0014\u0012]A\u0011\u0004C\u000e\u0011\u001d\u0019Ik\u0019a\u0001\u0003{Bqa!-d\u0001\u0004\t9\tC\u0004\u00046\u000e\u0004\r!a\"\u0002\u0019]\u0014\u0018\u000e^3C_>dW-\u00198\u0015\t\u0005ME\u0011\u0005\u0005\b\u00037#\u0007\u0019ABO\u0003%9(/\u001b;f\u0005f$X\r\u0006\u0003\u0002\u0014\u0012\u001d\u0002bBANK\u0002\u0007\u0011qQ\u0001\u000boJLG/\u001a\"zi\u0016\u001cH\u0003BAJ\t[Aq\u0001b\fg\u0001\u0004\t9\"A\u0001t\u0003A\u0011\u0016M\u001c3p[\u0006\u001b7-Z:t\r&dW\rE\u0002\u00026!\u001c2\u0001\u001bC\u001c!\u0011\t)\u0007\"\u000f\n\t\u0011m\u00121\u0005\u0002\u0007\u0003:L(+\u001a4\u0015\u0005\u0011M\u0012a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'\u0006\u0002\u0005D)\"\u0011q\u0003C#W\t!9\u0005\u0005\u0003\u0005J\u0011MSB\u0001C&\u0015\u0011!i\u0005b\u0014\u0002\u0013Ut7\r[3dW\u0016$'\u0002\u0002C)\u0003G\t!\"\u00198o_R\fG/[8o\u0013\u0011!)\u0006b\u0013\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$He\r\u000b\u0007\t7\"i\u0006b\u0018+\t\u0005MBQ\t\u0005\b\u0003\u001bY\u0007\u0019AA\b\u0011\u001d\t)b\u001ba\u0001\u0003/\u0001"
)
public class RandomAccessFile implements DataInput, DataOutput, Closeable {
   private final ByteConverter converter;
   private final java.io.RandomAccessFile rafObj;

   public static ByteConverter $lessinit$greater$default$3(final File file, final String arg0) {
      return RandomAccessFile$.MODULE$.$lessinit$greater$default$3(file, arg0);
   }

   public static String $lessinit$greater$default$2() {
      return RandomAccessFile$.MODULE$.$lessinit$greater$default$2();
   }

   public java.io.RandomAccessFile rafObj() {
      return this.rafObj;
   }

   public final byte readInt8() throws IOException {
      return this.rafObj().readByte();
   }

   public final byte[] readInt8(final int n) throws IOException {
      byte[] tempret = new byte[n];
      this.rafObj().readFully(tempret);
      return tempret;
   }

   public final void writeInt8(final byte v) throws IOException {
      this.rafObj().write(v);
   }

   public final void writeInt8(final byte[] v) throws IOException {
      this.rafObj().write(v);
   }

   public final byte readByte() throws IOException {
      return this.readInt8();
   }

   public final byte[] readByte(final int n) throws IOException {
      return this.readInt8(n);
   }

   public final void write(final byte v) throws IOException {
      this.writeInt8(v);
   }

   public final void write(final byte[] v) throws IOException {
      this.writeInt8(v);
   }

   public final int readUInt8() throws IOException {
      return this.rafObj().readUnsignedByte();
   }

   public final short[] readUInt8(final int n) throws IOException {
      short[] tr = new short[n];

      for(int c = 0; c < n; ++c) {
         tr[c] = (short)this.readUnsignedByte();
      }

      return tr;
   }

   public final void writeUInt8(final short value) throws IOException {
      this.rafObj().write(new byte[]{this.converter.uInt8ToByte(value)});
   }

   public final void writeUInt8(final short[] values) throws IOException {
      this.rafObj().write((byte[]).MODULE$.map$extension(scala.Predef..MODULE$.shortArrayOps(values), (x$1) -> BoxesRunTime.boxToByte($anonfun$writeUInt8$1(this, BoxesRunTime.unboxToShort(x$1))), scala.reflect.ClassTag..MODULE$.Byte()));
   }

   public final int readUnsignedByte() throws IOException {
      return this.readUInt8();
   }

   public final short[] readUnsignedByte(final int n) throws IOException {
      return this.readUInt8(n);
   }

   public final void writeUnsignedByte(final short value) throws IOException {
      this.writeUInt8(value);
   }

   public final void writeUnsignedByte(final short[] values) throws IOException {
      this.writeUInt8(values);
   }

   public short readInt16() throws IOException {
      byte[] ba = this.readByte(2);
      return this.converter.bytesToInt16(ba[0], ba[1]);
   }

   public final short[] readInt16(final int n) throws IOException {
      byte[] ba = new byte[n * 2];
      this.rafObj().readFully(ba);
      short[] tr = new short[n];

      for(int c = 0; c < n; ++c) {
         tr[c] = this.converter.bytesToInt16(ba[c * 2], ba[c * 2 + 1]);
      }

      return tr;
   }

   public final void writeInt16(final short v) throws IOException {
      this.rafObj().write(this.converter.int16ToBytes(v));
   }

   public final void writeInt16(final short[] v) throws IOException {
      byte[] writeArr = new byte[v.length * 2];
      IntRef currIndex = IntRef.create(0);
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), v.length).foreach$mVc$sp((JFunction1.mcVI.sp)(cnt) -> {
         byte[] x = this.converter.int16ToBytes(v[cnt]);
         writeArr[currIndex.elem] = x[0];
         ++currIndex.elem;
         writeArr[currIndex.elem] = x[1];
         ++currIndex.elem;
      });
      this.rafObj().write(writeArr);
   }

   public final short readShort() throws IOException {
      return this.readInt16();
   }

   public final short[] readShort(final int n) throws IOException {
      return this.readInt16(n);
   }

   public final void writeShort(final short v) throws IOException {
      this.writeInt16(v);
   }

   public final void writeShort(final int v) throws IOException {
      this.writeInt16((short)v);
   }

   public final void writeShort(final short[] v) throws IOException {
      this.writeInt16(v);
   }

   public char readUInt16() throws IOException {
      byte[] ba = this.readByte(2);
      return this.converter.bytesToUInt16(ba[0], ba[1]);
   }

   public final char[] readUInt16(final int n) throws IOException {
      byte[] ba = new byte[n * 2];
      this.rafObj().readFully(ba);
      char[] tr = new char[n];

      for(int c = 0; c < n; ++c) {
         tr[c] = this.converter.bytesToUInt16(ba[c * 2], ba[c * 2 + 1]);
      }

      return tr;
   }

   public final void writeUInt16(final char v) throws IOException {
      this.rafObj().write(this.converter.uInt16ToBytes(v));
   }

   public final void writeUInt16(final char[] v) throws IOException {
      this.rafObj().write((byte[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.charArrayOps(v), (x$2) -> $anonfun$writeUInt16$1(this, BoxesRunTime.unboxToChar(x$2)), (xs) -> scala.Predef..MODULE$.wrapByteArray(xs), scala.reflect.ClassTag..MODULE$.Byte()));
   }

   public final int readUnsignedShort() throws IOException {
      return this.readUInt16();
   }

   public final int[] readUnsignedShort(final int n) throws IOException {
      return (int[]).MODULE$.map$extension(scala.Predef..MODULE$.charArrayOps(this.readUInt16(n)), (x$3) -> BoxesRunTime.boxToInteger($anonfun$readUnsignedShort$1(BoxesRunTime.unboxToChar(x$3))), scala.reflect.ClassTag..MODULE$.Int());
   }

   public final char readChar() throws IOException {
      return this.readUInt16();
   }

   public final char[] readChar(final int n) throws IOException {
      return this.readUInt16(n);
   }

   public final void writeUnsignedShort(final int value) throws IOException {
      this.writeUInt16((char)value);
   }

   public final void writeUnsignedShort(final int[] value) throws IOException {
      this.writeUInt16((char[]).MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(value), (x$4) -> BoxesRunTime.boxToCharacter($anonfun$writeUnsignedShort$1(BoxesRunTime.unboxToInt(x$4))), scala.reflect.ClassTag..MODULE$.Char()));
   }

   public final void writeChar(final char value) throws IOException {
      this.writeUInt16(value);
   }

   public final void writeChar(final int v) throws IOException {
      this.writeUInt16((char)v);
   }

   public final void WriteChar(final char[] value) throws IOException {
      this.writeUInt16(value);
   }

   public final int readInt32() throws IOException {
      byte[] ba = this.readByte(4);
      return this.converter.bytesToInt32(ba[0], ba[1], ba[2], ba[3]);
   }

   public final int[] readInt32(final int n) throws IOException {
      byte[] ba = new byte[n * 4];
      this.rafObj().readFully(ba);
      int[] tr = new int[n];

      for(int c = 0; c < n; ++c) {
         tr[c] = this.converter.bytesToInt32(ba[c * 4], ba[c * 4 + 1], ba[c * 4 + 2], ba[c * 4 + 3]);
      }

      return tr;
   }

   public final void writeInt32(final int v) throws IOException {
      this.rafObj().write(this.converter.int32ToBytes(v));
   }

   public final void writeInt32(final int[] v) throws IOException {
      this.rafObj().write((byte[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.intArrayOps(v), (x$5) -> $anonfun$writeInt32$1(this, BoxesRunTime.unboxToInt(x$5)), (xs) -> scala.Predef..MODULE$.wrapByteArray(xs), scala.reflect.ClassTag..MODULE$.Byte()));
   }

   public final int readInt() throws IOException {
      return this.readInt32();
   }

   public final int[] readInt(final int n) throws IOException {
      return this.readInt32(n);
   }

   public final void writeInt(final int value) throws IOException {
      this.writeInt32(value);
   }

   public final void writeInt(final int[] value) throws IOException {
      this.writeInt32(value);
   }

   public final long readUInt32() throws IOException {
      byte[] ba = this.readByte(4);
      return this.converter.bytesToUInt32(ba[0], ba[1], ba[2], ba[3]);
   }

   public final long[] readUInt32(final int n) throws IOException {
      byte[] ba = new byte[n * 4];
      this.rafObj().readFully(ba);
      long[] tr = new long[n];

      for(int c = 0; c < n; ++c) {
         tr[c] = this.converter.bytesToUInt32(ba[c * 4], ba[c * 4 + 1], ba[c * 4 + 2], ba[c * 4 + 3]);
      }

      return tr;
   }

   public final void writeUInt32(final long v) throws IOException {
      this.rafObj().write(this.converter.uInt32ToBytes(v));
   }

   public final void writeUInt32(final long[] v) throws IOException {
      this.rafObj().write((byte[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.longArrayOps(v), (x$6) -> $anonfun$writeUInt32$1(this, BoxesRunTime.unboxToLong(x$6)), (xs) -> scala.Predef..MODULE$.wrapByteArray(xs), scala.reflect.ClassTag..MODULE$.Byte()));
   }

   public long readInt64() throws IOException {
      byte[] ba = this.readByte(8);
      return this.converter.bytesToInt64(ba[0], ba[1], ba[2], ba[3], ba[4], ba[5], ba[6], ba[7]);
   }

   public final long[] readInt64(final int n) throws IOException {
      byte[] ba = new byte[n * 8];
      this.rafObj().readFully(ba);
      long[] tr = new long[n];

      for(int c = 0; c < n; ++c) {
         int c8 = c * 8;
         tr[c] = this.converter.bytesToInt64(ba[c8], ba[c8 + 1], ba[c8 + 2], ba[c8 + 3], ba[c8 + 4], ba[c8 + 5], ba[c8 + 6], ba[c8 + 7]);
      }

      return tr;
   }

   public final void writeInt64(final long v) throws IOException {
      this.rafObj().write(this.converter.int64ToBytes(v));
   }

   public final void writeInt64(final long[] v) throws IOException {
      this.rafObj().write((byte[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.longArrayOps(v), (x$7) -> $anonfun$writeInt64$1(this, BoxesRunTime.unboxToLong(x$7)), (xs) -> scala.Predef..MODULE$.wrapByteArray(xs), scala.reflect.ClassTag..MODULE$.Byte()));
   }

   public final long readLong() throws IOException {
      return this.readInt64();
   }

   public final long[] readLong(final int n) throws IOException {
      return this.readInt64(n);
   }

   public final void writeLong(final long value) throws IOException {
      this.writeInt64(value);
   }

   public final void writeLong(final long[] value) throws IOException {
      this.writeInt64(value);
   }

   public final long readUInt64() throws IOException {
      byte[] ba = this.readByte(8);
      return this.converter.bytesToUInt64(ba[0], ba[1], ba[2], ba[3], ba[4], ba[5], ba[6], ba[7]);
   }

   public final ULong[] readUInt64(final int n) throws IOException {
      byte[] ba = new byte[n * 8];
      this.rafObj().readFully(ba);
      ULong[] tr = new ULong[n];

      for(int c = 0; c < n; ++c) {
         int c8 = c * 8;
         tr[c] = new ULong(this.converter.bytesToUInt64(ba[c8], ba[c8 + 1], ba[c8 + 2], ba[c8 + 3], ba[c8 + 4], ba[c8 + 5], ba[c8 + 6], ba[c8 + 7]));
      }

      return tr;
   }

   public final void writeUInt64(final long v) throws IOException {
      this.rafObj().write(this.converter.uInt64ToBytes(v));
   }

   public final void writeUInt64(final ULong[] v) throws IOException {
      this.rafObj().write((byte[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.genericArrayOps(v), (x$8) -> $anonfun$writeUInt64$1(this, ((ULong)x$8).signed()), (xs) -> scala.Predef..MODULE$.wrapByteArray(xs), scala.reflect.ClassTag..MODULE$.Byte()));
   }

   public final long readUInt64Shifted() throws IOException {
      byte[] ba = this.readByte(8);
      return this.converter.bytesToUInt64Shifted(ba[0], ba[1], ba[2], ba[3], ba[4], ba[5], ba[6], ba[7]);
   }

   public final long[] readUInt64Shifted(final int n) throws IOException {
      byte[] ba = new byte[n * 8];
      this.rafObj().readFully(ba);
      long[] tr = new long[n];

      for(int c = 0; c < n; ++c) {
         int c8 = c * 8;
         tr[c] = this.converter.bytesToUInt64Shifted(ba[c8], ba[c8 + 1], ba[c8 + 2], ba[c8 + 3], ba[c8 + 4], ba[c8 + 5], ba[c8 + 6], ba[c8 + 7]);
      }

      return tr;
   }

   public final void writeUInt64Shifted(final long v) throws IOException {
      this.rafObj().write(this.converter.uInt64ShiftedToBytes(v));
   }

   public final void writeUInt64Shifted(final long[] v) throws IOException {
      this.rafObj().write((byte[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.longArrayOps(v), (x$9) -> $anonfun$writeUInt64Shifted$1(this, BoxesRunTime.unboxToLong(x$9)), (xs) -> scala.Predef..MODULE$.wrapByteArray(xs), scala.reflect.ClassTag..MODULE$.Byte()));
   }

   public double readDouble() throws IOException {
      return Double.longBitsToDouble(this.readLong());
   }

   public float readFloat() throws IOException {
      return Float.intBitsToFloat(this.readInt());
   }

   public final double[] readDouble(final int n) throws IOException {
      byte[] ba = new byte[n * 8];
      this.rafObj().readFully(ba);
      double[] tr = new double[n];

      for(int c = 0; c < n; ++c) {
         int c8 = c * 8;
         tr[c] = Double.longBitsToDouble(this.converter.bytesToInt64(ba[c8], ba[c8 + 1], ba[c8 + 2], ba[c8 + 3], ba[c8 + 4], ba[c8 + 5], ba[c8 + 6], ba[c8 + 7]));
      }

      return tr;
   }

   public final float[] readFloat(final int n) throws IOException {
      byte[] ba = new byte[n * 4];
      this.rafObj().readFully(ba);
      float[] tr = new float[n];

      for(int c = 0; c < n; ++c) {
         int c4 = c * 4;
         tr[c] = Float.intBitsToFloat(this.converter.bytesToInt32(ba[c4], ba[c4 + 1], ba[c4 + 2], ba[c4 + 3]));
      }

      return tr;
   }

   public void writeFloat(final float v) throws IOException {
      this.writeInt32(Float.floatToRawIntBits(v));
   }

   public void writeDouble(final double v) throws IOException {
      this.writeInt64(Double.doubleToRawLongBits(v));
   }

   public void writeDouble(final double[] v) throws IOException {
      long[] la = new long[v.length];

      for(int c = 0; c < v.length; ++c) {
         la[c] = Double.doubleToRawLongBits(v[c]);
      }

      this.writeLong(la);
   }

   public void writeFloat(final float[] v) throws IOException {
      int[] ia = new int[v.length];

      for(int c = 0; c < v.length; ++c) {
         ia[c] = Float.floatToRawIntBits(v[c]);
      }

      this.writeInt(ia);
   }

   public final boolean readBoolean() {
      return this.rafObj().readBoolean();
   }

   public void readFully(final byte[] b) {
      this.rafObj().readFully(b);
   }

   public void readFully(final byte[] b, final int off, final int len) {
      this.rafObj().readFully(b, off, len);
   }

   public String readLine() {
      return this.rafObj().readLine();
   }

   public String readUTF() {
      return this.rafObj().readUTF();
   }

   public final void writeChars(final String value) {
      this.rafObj().writeChars(value);
   }

   public void writeUTF(final String value) {
      this.rafObj().writeUTF(value);
   }

   public int skipBytes(final int n) {
      return this.rafObj().skipBytes(n);
   }

   public void jumpBytes(final int n) {
      this.rafObj().seek(this.rafObj().getFilePointer() + (long)n);
   }

   public long getFilePointer() {
      return this.rafObj().getFilePointer();
   }

   public void seek(final long pos) {
      this.rafObj().seek(pos);
   }

   public long length() {
      return this.rafObj().length();
   }

   public void setLength(final long newLength) {
      this.rafObj().setLength(newLength);
   }

   public void close() {
      this.rafObj().close();
   }

   public FileChannel getChannel() {
      return this.rafObj().getChannel();
   }

   public FileDescriptor getFD() {
      return this.rafObj().getFD();
   }

   public void write(final int b) {
      this.rafObj().write(b);
   }

   public void write(final byte[] b, final int off, final int len) {
      this.rafObj().write(b, off, len);
   }

   public void writeBoolean(final boolean v) {
      this.rafObj().writeBoolean(v);
   }

   public void writeByte(final int v) {
      this.rafObj().writeByte(v);
   }

   public void writeBytes(final String s) {
      this.rafObj().writeBytes(s);
   }

   // $FF: synthetic method
   public static final byte $anonfun$writeUInt8$1(final RandomAccessFile $this, final short x$1) {
      return $this.converter.uInt8ToByte(x$1);
   }

   // $FF: synthetic method
   public static final byte[] $anonfun$writeUInt16$1(final RandomAccessFile $this, final char x$2) {
      return $this.converter.uInt16ToBytes(x$2);
   }

   // $FF: synthetic method
   public static final int $anonfun$readUnsignedShort$1(final char x$3) {
      return x$3;
   }

   // $FF: synthetic method
   public static final char $anonfun$writeUnsignedShort$1(final int x$4) {
      return (char)x$4;
   }

   // $FF: synthetic method
   public static final byte[] $anonfun$writeInt32$1(final RandomAccessFile $this, final int x$5) {
      return $this.converter.int32ToBytes(x$5);
   }

   // $FF: synthetic method
   public static final byte[] $anonfun$writeUInt32$1(final RandomAccessFile $this, final long x$6) {
      return $this.converter.uInt32ToBytes(x$6);
   }

   // $FF: synthetic method
   public static final byte[] $anonfun$writeInt64$1(final RandomAccessFile $this, final long x$7) {
      return $this.converter.int64ToBytes(x$7);
   }

   // $FF: synthetic method
   public static final byte[] $anonfun$writeUInt64$1(final RandomAccessFile $this, final long x$8) {
      return $this.converter.uInt64ToBytes(x$8);
   }

   // $FF: synthetic method
   public static final byte[] $anonfun$writeUInt64Shifted$1(final RandomAccessFile $this, final long x$9) {
      return $this.converter.uInt64ShiftedToBytes(x$9);
   }

   public RandomAccessFile(final File file, final String arg0, final ByteConverter converter) {
      this.converter = converter;
      this.rafObj = new java.io.RandomAccessFile(file, arg0);
   }

   public RandomAccessFile(final String filename, final String arg0, final ByteConverter converter) {
      this(new File(filename), arg0, converter);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
