package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.util.Map;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext$;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.deploy.history.EventLogFileWriter;
import org.apache.spark.deploy.history.EventLogFileWriter$;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.JsonProtocol$;
import org.apache.spark.util.JsonProtocolOptions;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\rUd!\u0002\"D\u0001\u0015[\u0005\u0002\u0003,\u0001\u0005\u0003\u0005\u000b\u0011\u0002-\t\u0011\u0015\u0004!\u0011!Q\u0001\n\u0019D\u0001B\u001b\u0001\u0003\u0002\u0003\u0006Ia\u001b\u0005\tg\u0002\u0011\t\u0011)A\u0005i\"A\u0001\u0010\u0001B\u0001B\u0003%\u0011\u0010C\u0004\u0002\u0004\u0001!\t!!\u0002\t\u000f\u0005\r\u0001\u0001\"\u0001\u0002\u0014!Q\u0011Q\u0004\u0001C\u0002\u0013\u00051)a\b\t\u0011\u0005E\u0002\u0001)A\u0005\u0003CA!\"a\r\u0001\u0005\u0004%\taQA\u001b\u0011!\t9\u0005\u0001Q\u0001\n\u0005]\u0002\"CA%\u0001\t\u0007I\u0011BA&\u0011!\t\u0019\u0006\u0001Q\u0001\n\u00055\u0003\"CA+\u0001\t\u0007I\u0011BA&\u0011!\t9\u0006\u0001Q\u0001\n\u00055\u0003\"CA-\u0001\t\u0007I\u0011BA&\u0011!\tY\u0006\u0001Q\u0001\n\u00055\u0003\"CA/\u0001\t\u0007I\u0011BA0\u0011!\t\t\t\u0001Q\u0001\n\u0005\u0005\u0004\u0002CAB\u0001\u0001\u0006I!!\"\t\u000f\u0005E\u0005\u0001\"\u0001\u0002\u0014\"9\u00111\u0014\u0001\u0005\n\u0005M\u0005bBAO\u0001\u0011%\u0011q\u0014\u0005\n\u0003_\u0003\u0011\u0013!C\u0005\u0003cCq!a2\u0001\t\u0003\nI\rC\u0004\u0002T\u0002!\t%!6\t\u000f\u0005}\u0007\u0001\"\u0011\u0002b\"9\u00111\u001e\u0001\u0005B\u00055\bbBA|\u0001\u0011\u0005\u0013\u0011 \u0005\b\u0005\u0007\u0001A\u0011\tB\u0003\u0011\u001d\u0011y\u0001\u0001C!\u0005#AqAa\u0007\u0001\t\u0003\u0012i\u0002C\u0004\u0003(\u0001!\tE!\u000b\t\u000f\tM\u0002\u0001\"\u0011\u00036!9!q\b\u0001\u0005B\t\u0005\u0003b\u0002B&\u0001\u0011\u0005#Q\n\u0005\b\u0005/\u0002A\u0011\tB-\u0011\u001d\u0011\u0019\u0007\u0001C!\u0005KBqAa\u001c\u0001\t\u0003\u0012\t\bC\u0004\u0003|\u0001!\tE! \t\u000f\t\u001d\u0005\u0001\"\u0011\u0003\n\"9!1\u0013\u0001\u0005B\tU\u0005b\u0002BP\u0001\u0011\u0005#\u0011\u0015\u0005\b\u0005W\u0003A\u0011\tBW\u0011\u001d\u00119\f\u0001C!\u0005sCqAa1\u0001\t\u0003\u0012)\rC\u0004\u0003P\u0002!\tE!5\t\u000f\tm\u0007\u0001\"\u0011\u0003^\"9!q\u001d\u0001\u0005B\t%\bb\u0002Bz\u0001\u0011\u0005#Q\u001f\u0005\b\u0005\u007f\u0004A\u0011IB\u0001\u0011\u001d\u0019Y\u0001\u0001C!\u0007\u001bAqaa\u0006\u0001\t\u0003\u001aI\u0002C\u0004\u0004$\u0001!\te!\n\t\u000f\r=\u0002\u0001\"\u0011\u00042!91Q\u0007\u0001\u0005\u0002\u0005M\u0005bBB\u001c\u0001\u0011%1\u0011H\u0004\t\u0007\u0013\u001a\u0005\u0012A#\u0004L\u00199!i\u0011E\u0001\u000b\u000e5\u0003bBA\u0002w\u0011\u00051Q\u000b\u0005\n\u0007/Z$\u0019!C\u0001\u00073B\u0001b!\u001a<A\u0003%11\f\u0005\n\u0007OZ$\u0019!C\u0001\u0007SB\u0001ba\u001b<A\u0003%\u0011q\r\u0005\t\u0007[ZD\u0011A#\u0004p\t!RI^3oi2{wmZ5oO2K7\u000f^3oKJT!\u0001R#\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001$H\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0015*\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0015\u0006\u0019qN]4\u0014\u0007\u0001a\u0005\u000b\u0005\u0002N\u001d6\t1)\u0003\u0002P\u0007\ni1\u000b]1sW2K7\u000f^3oKJ\u0004\"!\u0015+\u000e\u0003IS!aU#\u0002\u0011%tG/\u001a:oC2L!!\u0016*\u0003\u000f1{wmZ5oO\u0006)\u0011\r\u001d9JI\u000e\u0001\u0001CA-c\u001d\tQ\u0006\r\u0005\u0002\\=6\tAL\u0003\u0002^/\u00061AH]8pizR\u0011aX\u0001\u0006g\u000e\fG.Y\u0005\u0003Cz\u000ba\u0001\u0015:fI\u00164\u0017BA2e\u0005\u0019\u0019FO]5oO*\u0011\u0011MX\u0001\rCB\u0004\u0018\t\u001e;f[B$\u0018\n\u001a\t\u0004O\"DV\"\u00010\n\u0005%t&AB(qi&|g.\u0001\u0006m_\u001e\u0014\u0015m]3ESJ\u0004\"\u0001\\9\u000e\u00035T!A\\8\u0002\u00079,GOC\u0001q\u0003\u0011Q\u0017M^1\n\u0005Il'aA+S\u0013\u0006I1\u000f]1sW\u000e{gN\u001a\t\u0003kZl\u0011!R\u0005\u0003o\u0016\u0013\u0011b\u00159be.\u001cuN\u001c4\u0002\u0015!\fGm\\8q\u0007>tg\r\u0005\u0002{\u007f6\t1P\u0003\u0002}{\u0006!1m\u001c8g\u0015\tqx)\u0001\u0004iC\u0012|w\u000e]\u0005\u0004\u0003\u0003Y(!D\"p]\u001aLw-\u001e:bi&|g.\u0001\u0004=S:LGO\u0010\u000b\r\u0003\u000f\tI!a\u0003\u0002\u000e\u0005=\u0011\u0011\u0003\t\u0003\u001b\u0002AQA\u0016\u0004A\u0002aCQ!\u001a\u0004A\u0002\u0019DQA\u001b\u0004A\u0002-DQa\u001d\u0004A\u0002QDQ\u0001\u001f\u0004A\u0002e$\"\"a\u0002\u0002\u0016\u0005]\u0011\u0011DA\u000e\u0011\u00151v\u00011\u0001Y\u0011\u0015)w\u00011\u0001g\u0011\u0015Qw\u00011\u0001l\u0011\u0015\u0019x\u00011\u0001u\u0003%awnZ,sSR,'/\u0006\u0002\u0002\"A!\u00111EA\u0017\u001b\t\t)C\u0003\u0003\u0002(\u0005%\u0012a\u00025jgR|'/\u001f\u0006\u0004\u0003W)\u0015A\u00023fa2|\u00170\u0003\u0003\u00020\u0005\u0015\"AE#wK:$Hj\\4GS2,wK]5uKJ\f!\u0002\\8h/JLG/\u001a:!\u00031awnZ4fI\u00163XM\u001c;t+\t\t9\u0004E\u0003\u0002:\u0005\r\u0003,\u0004\u0002\u0002<)!\u0011QHA \u0003\u001diW\u000f^1cY\u0016T1!!\u0011_\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u000b\nYDA\u0006BeJ\f\u0017PQ;gM\u0016\u0014\u0018!\u00047pO\u001e,G-\u0012<f]R\u001c\b%A\u000btQ>,H\u000e\u001a'pO\ncwnY6Va\u0012\fG/Z:\u0016\u0005\u00055\u0003cA4\u0002P%\u0019\u0011\u0011\u000b0\u0003\u000f\t{w\u000e\\3b]\u000612\u000f[8vY\u0012dun\u001a\"m_\u000e\\W\u000b\u001d3bi\u0016\u001c\b%A\u000ftQ>,H\u000e\u001a'pON#\u0018mZ3Fq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2t\u0003y\u0019\bn\\;mI2{wm\u0015;bO\u0016,\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001c\b%A\u0004uKN$\u0018N\\4\u0002\u0011Q,7\u000f^5oO\u0002\n\u0001\u0004\\5wKN#\u0018mZ3Fq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2t+\t\t\t\u0007\u0005\u0005\u0002:\u0005\r\u0014qMA:\u0013\u0011\t)'a\u000f\u0003\u000f!\u000b7\u000f['baB9q-!\u001b\u0002n\u00055\u0014bAA6=\n1A+\u001e9mKJ\u00022aZA8\u0013\r\t\tH\u0018\u0002\u0004\u0013:$\bcBA\u001d\u0003GB\u0016Q\u000f\t\u0005\u0003o\ni(\u0004\u0002\u0002z)\u0019\u00111P#\u0002\u0011\u0015DXmY;u_JLA!a \u0002z\tyQ\t_3dkR|'/T3ue&\u001c7/A\rmSZ,7\u000b^1hK\u0016CXmY;u_JlU\r\u001e:jGN\u0004\u0013a\u00056t_:\u0004&o\u001c;pG>dw\n\u001d;j_:\u001c\b\u0003BAD\u0003\u001bk!!!#\u000b\u0007\u0005-U)\u0001\u0003vi&d\u0017\u0002BAH\u0003\u0013\u00131CS:p]B\u0013x\u000e^8d_2|\u0005\u000f^5p]N\fQa\u001d;beR$\"!!&\u0011\u0007\u001d\f9*C\u0002\u0002\u001az\u0013A!\u00168ji\u0006a\u0011N\\5u\u000bZ,g\u000e\u001e'pO\u0006AAn\\4Fm\u0016tG\u000f\u0006\u0004\u0002\u0016\u0006\u0005\u00161\u0016\u0005\b\u0003G;\u0002\u0019AAS\u0003\u0015)g/\u001a8u!\ri\u0015qU\u0005\u0004\u0003S\u001b%AE*qCJ\\G*[:uK:,'/\u0012<f]RD\u0011\"!,\u0018!\u0003\u0005\r!!\u0014\u0002\u0017\u0019dWo\u001d5M_\u001e<WM]\u0001\u0013Y><WI^3oi\u0012\"WMZ1vYR$#'\u0006\u0002\u00024*\"\u0011QJA[W\t\t9\f\u0005\u0003\u0002:\u0006\rWBAA^\u0015\u0011\ti,a0\u0002\u0013Ut7\r[3dW\u0016$'bAAa=\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u0015\u00171\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001E8o'R\fw-Z*vE6LG\u000f^3e)\u0011\t)*a3\t\u000f\u0005\r\u0016\u00041\u0001\u0002NB\u0019Q*a4\n\u0007\u0005E7IA\u000eTa\u0006\u00148\u000eT5ti\u0016tWM]*uC\u001e,7+\u001e2nSR$X\rZ\u0001\f_:$\u0016m]6Ti\u0006\u0014H\u000f\u0006\u0003\u0002\u0016\u0006]\u0007bBAR5\u0001\u0007\u0011\u0011\u001c\t\u0004\u001b\u0006m\u0017bAAo\u0007\n12\u000b]1sW2K7\u000f^3oKJ$\u0016m]6Ti\u0006\u0014H/A\np]R\u000b7o[$fiRLgn\u001a*fgVdG\u000f\u0006\u0003\u0002\u0016\u0006\r\bbBAR7\u0001\u0007\u0011Q\u001d\t\u0004\u001b\u0006\u001d\u0018bAAu\u0007\nq2\u000b]1sW2K7\u000f^3oKJ$\u0016m]6HKR$\u0018N\\4SKN,H\u000e^\u0001\n_:$\u0016m]6F]\u0012$B!!&\u0002p\"9\u00111\u0015\u000fA\u0002\u0005E\bcA'\u0002t&\u0019\u0011Q_\"\u0003)M\u0003\u0018M]6MSN$XM\\3s)\u0006\u001c8.\u00128e\u0003Myg.\u00128wSJ|g.\\3oiV\u0003H-\u0019;f)\u0011\t)*a?\t\u000f\u0005\rV\u00041\u0001\u0002~B\u0019Q*a@\n\u0007\t\u00051I\u0001\u0010Ta\u0006\u00148\u000eT5ti\u0016tWM]#om&\u0014xN\\7f]R,\u0006\u000fZ1uK\u0006\u0001rN\\*uC\u001e,7i\\7qY\u0016$X\r\u001a\u000b\u0005\u0003+\u00139\u0001C\u0004\u0002$z\u0001\rA!\u0003\u0011\u00075\u0013Y!C\u0002\u0003\u000e\r\u00131d\u00159be.d\u0015n\u001d;f]\u0016\u00148\u000b^1hK\u000e{W\u000e\u001d7fi\u0016$\u0017AC8o\u0015>\u00147\u000b^1siR!\u0011Q\u0013B\n\u0011\u001d\t\u0019k\ba\u0001\u0005+\u00012!\u0014B\f\u0013\r\u0011Ib\u0011\u0002\u0016'B\f'o\u001b'jgR,g.\u001a:K_\n\u001cF/\u0019:u\u0003!ygNS8c\u000b:$G\u0003BAK\u0005?Aq!a)!\u0001\u0004\u0011\t\u0003E\u0002N\u0005GI1A!\nD\u0005M\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe*{'-\u00128e\u0003MygN\u00117pG.l\u0015M\\1hKJ\fE\rZ3e)\u0011\t)Ja\u000b\t\u000f\u0005\r\u0016\u00051\u0001\u0003.A\u0019QJa\f\n\u0007\tE2I\u0001\u0010Ta\u0006\u00148\u000eT5ti\u0016tWM\u001d\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\t\u001a3fI\u0006)rN\u001c\"m_\u000e\\W*\u00198bO\u0016\u0014(+Z7pm\u0016$G\u0003BAK\u0005oAq!a)#\u0001\u0004\u0011I\u0004E\u0002N\u0005wI1A!\u0010D\u0005\u0001\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\ncwnY6NC:\fw-\u001a:SK6|g/\u001a3\u0002\u001d=tWK\u001c9feNL7\u000f\u001e*E\tR!\u0011Q\u0013B\"\u0011\u001d\t\u0019k\ta\u0001\u0005\u000b\u00022!\u0014B$\u0013\r\u0011Ie\u0011\u0002\u001a'B\f'o\u001b'jgR,g.\u001a:V]B,'o]5tiJ#E)\u0001\np]\u0006\u0003\b\u000f\\5dCRLwN\\*uCJ$H\u0003BAK\u0005\u001fBq!a)%\u0001\u0004\u0011\t\u0006E\u0002N\u0005'J1A!\u0016D\u0005u\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0006\u0003\b\u000f\\5dCRLwN\\*uCJ$\u0018\u0001E8o\u0003B\u0004H.[2bi&|g.\u00128e)\u0011\t)Ja\u0017\t\u000f\u0005\rV\u00051\u0001\u0003^A\u0019QJa\u0018\n\u0007\t\u00054IA\u000eTa\u0006\u00148\u000eT5ti\u0016tWM]!qa2L7-\u0019;j_:,e\u000eZ\u0001\u0010_:,\u00050Z2vi>\u0014\u0018\t\u001a3fIR!\u0011Q\u0013B4\u0011\u001d\t\u0019K\na\u0001\u0005S\u00022!\u0014B6\u0013\r\u0011ig\u0011\u0002\u001b'B\f'o\u001b'jgR,g.\u001a:Fq\u0016\u001cW\u000f^8s\u0003\u0012$W\rZ\u0001\u0012_:,\u00050Z2vi>\u0014(+Z7pm\u0016$G\u0003BAK\u0005gBq!a)(\u0001\u0004\u0011)\bE\u0002N\u0005oJ1A!\u001fD\u0005q\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0016CXmY;u_J\u0014V-\\8wK\u0012\fQc\u001c8Fq\u0016\u001cW\u000f^8s\u00052\f7m\u001b7jgR,G\r\u0006\u0003\u0002\u0016\n}\u0004bBARQ\u0001\u0007!\u0011\u0011\t\u0004\u001b\n\r\u0015b\u0001BC\u0007\n\u00013\u000b]1sW2K7\u000f^3oKJ,\u00050Z2vi>\u0014(\t\\1dW2L7\u000f^3e\u0003Iyg.\u0012=fGV$xN]#yG2,H-\u001a3\u0015\t\u0005U%1\u0012\u0005\b\u0003GK\u0003\u0019\u0001BG!\ri%qR\u0005\u0004\u0005#\u001b%!H*qCJ\\G*[:uK:,'/\u0012=fGV$xN]#yG2,H-\u001a3\u0002;=tW\t_3dkR|'O\u00117bG.d\u0017n\u001d;fI\u001a{'o\u0015;bO\u0016$B!!&\u0003\u0018\"9\u00111\u0015\u0016A\u0002\te\u0005cA'\u0003\u001c&\u0019!QT\"\u0003QM\u0003\u0018M]6MSN$XM\\3s\u000bb,7-\u001e;pe\nc\u0017mY6mSN$X\r\u001a$peN#\u0018mZ3\u00025=tW\t_3dkR|'/\u0012=dYV$W\r\u001a$peN#\u0018mZ3\u0015\t\u0005U%1\u0015\u0005\b\u0003G[\u0003\u0019\u0001BS!\ri%qU\u0005\u0004\u0005S\u001b%!J*qCJ\\G*[:uK:,'/\u0012=fGV$xN]#yG2,H-\u001a3G_J\u001cF/Y4f\u0003eygNT8eK\nc\u0017mY6mSN$X\r\u001a$peN#\u0018mZ3\u0015\t\u0005U%q\u0016\u0005\b\u0003Gc\u0003\u0019\u0001BY!\ri%1W\u0005\u0004\u0005k\u001b%\u0001J*qCJ\\G*[:uK:,'OT8eK\nc\u0017mY6mSN$X\r\u001a$peN#\u0018mZ3\u0002-=tgj\u001c3f\u000bb\u001cG.\u001e3fI\u001a{'o\u0015;bO\u0016$B!!&\u0003<\"9\u00111U\u0017A\u0002\tu\u0006cA'\u0003@&\u0019!\u0011Y\"\u0003CM\u0003\u0018M]6MSN$XM\\3s\u001d>$W-\u0012=dYV$W\r\u001a$peN#\u0018mZ3\u0002/=tW\t_3dkR|'/\u00168cY\u0006\u001c7\u000e\\5ti\u0016$G\u0003BAK\u0005\u000fDq!a)/\u0001\u0004\u0011I\rE\u0002N\u0005\u0017L1A!4D\u0005\t\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0016CXmY;u_J,fN\u00197bG.d\u0017n\u001d;fI\u0006!rN\\#yK\u000e,Ho\u001c:V]\u0016D8\r\\;eK\u0012$B!!&\u0003T\"9\u00111U\u0018A\u0002\tU\u0007cA'\u0003X&\u0019!\u0011\\\"\u0003?M\u0003\u0018M]6MSN$XM\\3s\u000bb,7-\u001e;peVsW\r_2mk\u0012,G-A\tp]:{G-\u001a\"mC\u000e\\G.[:uK\u0012$B!!&\u0003`\"9\u00111\u0015\u0019A\u0002\t\u0005\bcA'\u0003d&\u0019!Q]\"\u00039M\u0003\u0018M]6MSN$XM\\3s\u001d>$WM\u00117bG.d\u0017n\u001d;fI\u0006qqN\u001c(pI\u0016,\u0005p\u00197vI\u0016$G\u0003BAK\u0005WDq!a)2\u0001\u0004\u0011i\u000fE\u0002N\u0005_L1A!=D\u0005e\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe:{G-Z#yG2,H-\u001a3\u0002'=tgj\u001c3f+:\u0014G.Y2lY&\u001cH/\u001a3\u0015\t\u0005U%q\u001f\u0005\b\u0003G\u0013\u0004\u0019\u0001B}!\ri%1`\u0005\u0004\u0005{\u001c%AH*qCJ\\G*[:uK:,'OT8eKVs'\r\\1dW2L7\u000f^3e\u0003AygNT8eKVsW\r_2mk\u0012,G\r\u0006\u0003\u0002\u0016\u000e\r\u0001bBARg\u0001\u00071Q\u0001\t\u0004\u001b\u000e\u001d\u0011bAB\u0005\u0007\nY2\u000b]1sW2K7\u000f^3oKJtu\u000eZ3V]\u0016D8\r\\;eK\u0012\fab\u001c8CY>\u001c7.\u00169eCR,G\r\u0006\u0003\u0002\u0016\u000e=\u0001bBARi\u0001\u00071\u0011\u0003\t\u0004\u001b\u000eM\u0011bAB\u000b\u0007\nI2\u000b]1sW2K7\u000f^3oKJ\u0014En\\2l+B$\u0017\r^3e\u0003]yg.\u0012=fGV$xN]'fiJL7m]+qI\u0006$X\r\u0006\u0003\u0002\u0016\u000em\u0001bBARk\u0001\u00071Q\u0004\t\u0004\u001b\u000e}\u0011bAB\u0011\u0007\n\u00113\u000b]1sW2K7\u000f^3oKJ,\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001cX\u000b\u001d3bi\u0016\fac\u001c8SKN|WO]2f!J|g-\u001b7f\u0003\u0012$W\r\u001a\u000b\u0005\u0003+\u001b9\u0003C\u0004\u0002$Z\u0002\ra!\u000b\u0011\u00075\u001bY#C\u0002\u0004.\r\u0013\u0011e\u00159be.d\u0015n\u001d;f]\u0016\u0014(+Z:pkJ\u001cW\r\u0015:pM&dW-\u00113eK\u0012\fAb\u001c8Pi\",'/\u0012<f]R$B!!&\u00044!9\u00111U\u001cA\u0002\u0005\u0015\u0016\u0001B:u_B\f\u0001C]3eC\u000e$\bK]8qKJ$\u0018.Z:\u0015\t\rm2Q\t\t\u0005\u0007{\u0019\t%\u0004\u0002\u0004@)\u0019\u00111R8\n\t\r\r3q\b\u0002\u000b!J|\u0007/\u001a:uS\u0016\u001c\bbBB$s\u0001\u000711H\u0001\u000baJ|\u0007/\u001a:uS\u0016\u001c\u0018\u0001F#wK:$Hj\\4hS:<G*[:uK:,'\u000f\u0005\u0002NwM!1ha\u0014Q!\r97\u0011K\u0005\u0004\u0007'r&AB!osJ+g\r\u0006\u0002\u0004L\u0005yA)\u0012$B+2#v\fT(H?\u0012K%+\u0006\u0002\u0004\\A!1QLB2\u001b\t\u0019yFC\u0002\u0004b=\fA\u0001\\1oO&\u00191ma\u0018\u0002!\u0011+e)Q+M)~cujR0E\u0013J\u0003\u0013\u0001\u0005#S\u0013Z+%kX*U\u0003\u001e+ulS#Z+\t\t9'A\tE%&3VIU0T)\u0006;UiX&F3\u0002\n1B]3eC\u000e$XI^3oiR1\u0011Q`B9\u0007gBQa]!A\u0002QDq!a)B\u0001\u0004\ti\u0010"
)
public class EventLoggingListener extends SparkListener implements Logging {
   private final SparkConf sparkConf;
   private final EventLogFileWriter logWriter;
   private final ArrayBuffer loggedEvents;
   private final boolean shouldLogBlockUpdates;
   private final boolean shouldLogStageExecutorMetrics;
   private final boolean testing;
   private final HashMap liveStageExecutorMetrics;
   private final JsonProtocolOptions jsonProtocolOptions;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Tuple2 DRIVER_STAGE_KEY() {
      return EventLoggingListener$.MODULE$.DRIVER_STAGE_KEY();
   }

   public static String DEFAULT_LOG_DIR() {
      return EventLoggingListener$.MODULE$.DEFAULT_LOG_DIR();
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

   public EventLogFileWriter logWriter() {
      return this.logWriter;
   }

   public ArrayBuffer loggedEvents() {
      return this.loggedEvents;
   }

   private boolean shouldLogBlockUpdates() {
      return this.shouldLogBlockUpdates;
   }

   private boolean shouldLogStageExecutorMetrics() {
      return this.shouldLogStageExecutorMetrics;
   }

   private boolean testing() {
      return this.testing;
   }

   private HashMap liveStageExecutorMetrics() {
      return this.liveStageExecutorMetrics;
   }

   public void start() {
      this.logWriter().start();
      this.initEventLog();
   }

   private void initEventLog() {
      SparkListenerLogStart metadata = new SparkListenerLogStart(org.apache.spark.package$.MODULE$.SPARK_VERSION());
      String eventJson = JsonProtocol$.MODULE$.sparkEventToJsonString(metadata, this.jsonProtocolOptions);
      this.logWriter().writeEvent(eventJson, true);
      if (this.testing() && this.loggedEvents() != null) {
         this.loggedEvents().$plus$eq(eventJson);
      }
   }

   private void logEvent(final SparkListenerEvent event, final boolean flushLogger) {
      String eventJson = JsonProtocol$.MODULE$.sparkEventToJsonString(event, this.jsonProtocolOptions);
      this.logWriter().writeEvent(eventJson, flushLogger);
      if (this.testing()) {
         this.loggedEvents().$plus$eq(eventJson);
      }
   }

   private boolean logEvent$default$2() {
      return false;
   }

   public void onStageSubmitted(final SparkListenerStageSubmitted event) {
      Properties x$1 = this.redactProperties(event.properties());
      StageInfo x$2 = event.copy$default$1();
      this.logEvent(event.copy(x$2, x$1), this.logEvent$default$2());
      if (this.shouldLogStageExecutorMetrics()) {
         this.liveStageExecutorMetrics().put(new Tuple2.mcII.sp(event.stageInfo().stageId(), event.stageInfo().attemptNumber()), .MODULE$.empty());
      }
   }

   public void onTaskStart(final SparkListenerTaskStart event) {
      this.logEvent(event, this.logEvent$default$2());
   }

   public void onTaskGettingResult(final SparkListenerTaskGettingResult event) {
      this.logEvent(event, this.logEvent$default$2());
   }

   public void onTaskEnd(final SparkListenerTaskEnd event) {
      this.logEvent(event, this.logEvent$default$2());
      if (this.shouldLogStageExecutorMetrics()) {
         Tuple2 stageKey = new Tuple2.mcII.sp(event.stageId(), event.stageAttemptId());
         this.liveStageExecutorMetrics().get(stageKey).map((metricsPerExecutor) -> BoxesRunTime.boxToBoolean($anonfun$onTaskEnd$1(event, metricsPerExecutor)));
      }
   }

   public void onEnvironmentUpdate(final SparkListenerEnvironmentUpdate event) {
      this.logEvent(EventLoggingListener$.MODULE$.redactEvent(this.sparkConf, event), this.logEvent$default$2());
   }

   public void onStageCompleted(final SparkListenerStageCompleted event) {
      if (this.shouldLogStageExecutorMetrics()) {
         int prevAttemptId = event.stageInfo().attemptNumber() - 1;
         scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), prevAttemptId).foreach((attemptId) -> $anonfun$onStageCompleted$1(this, event, BoxesRunTime.unboxToInt(attemptId)));
         Option executorOpt = this.liveStageExecutorMetrics().remove(new Tuple2.mcII.sp(event.stageInfo().stageId(), event.stageInfo().attemptNumber()));
         executorOpt.foreach((execMap) -> {
            $anonfun$onStageCompleted$2(this, event, execMap);
            return BoxedUnit.UNIT;
         });
      }

      this.logEvent(event, true);
   }

   public void onJobStart(final SparkListenerJobStart event) {
      Properties x$1 = this.redactProperties(event.properties());
      int x$2 = event.copy$default$1();
      long x$3 = event.copy$default$2();
      Seq x$4 = event.copy$default$3();
      this.logEvent(event.copy(x$2, x$3, x$4, x$1), true);
   }

   public void onJobEnd(final SparkListenerJobEnd event) {
      this.logEvent(event, true);
   }

   public void onBlockManagerAdded(final SparkListenerBlockManagerAdded event) {
      this.logEvent(event, true);
   }

   public void onBlockManagerRemoved(final SparkListenerBlockManagerRemoved event) {
      this.logEvent(event, true);
   }

   public void onUnpersistRDD(final SparkListenerUnpersistRDD event) {
      this.logEvent(event, true);
   }

   public void onApplicationStart(final SparkListenerApplicationStart event) {
      this.logEvent(event, true);
   }

   public void onApplicationEnd(final SparkListenerApplicationEnd event) {
      this.logEvent(event, true);
   }

   public void onExecutorAdded(final SparkListenerExecutorAdded event) {
      this.logEvent(event, true);
   }

   public void onExecutorRemoved(final SparkListenerExecutorRemoved event) {
      this.logEvent(event, true);
   }

   public void onExecutorBlacklisted(final SparkListenerExecutorBlacklisted event) {
      this.logEvent(event, true);
   }

   public void onExecutorExcluded(final SparkListenerExecutorExcluded event) {
      this.logEvent(event, true);
   }

   public void onExecutorBlacklistedForStage(final SparkListenerExecutorBlacklistedForStage event) {
      this.logEvent(event, true);
   }

   public void onExecutorExcludedForStage(final SparkListenerExecutorExcludedForStage event) {
      this.logEvent(event, true);
   }

   public void onNodeBlacklistedForStage(final SparkListenerNodeBlacklistedForStage event) {
      this.logEvent(event, true);
   }

   public void onNodeExcludedForStage(final SparkListenerNodeExcludedForStage event) {
      this.logEvent(event, true);
   }

   public void onExecutorUnblacklisted(final SparkListenerExecutorUnblacklisted event) {
      this.logEvent(event, true);
   }

   public void onExecutorUnexcluded(final SparkListenerExecutorUnexcluded event) {
      this.logEvent(event, true);
   }

   public void onNodeBlacklisted(final SparkListenerNodeBlacklisted event) {
      this.logEvent(event, true);
   }

   public void onNodeExcluded(final SparkListenerNodeExcluded event) {
      this.logEvent(event, true);
   }

   public void onNodeUnblacklisted(final SparkListenerNodeUnblacklisted event) {
      this.logEvent(event, true);
   }

   public void onNodeUnexcluded(final SparkListenerNodeUnexcluded event) {
      this.logEvent(event, true);
   }

   public void onBlockUpdated(final SparkListenerBlockUpdated event) {
      if (this.shouldLogBlockUpdates()) {
         this.logEvent(event, true);
      }
   }

   public void onExecutorMetricsUpdate(final SparkListenerExecutorMetricsUpdate event) {
      if (this.shouldLogStageExecutorMetrics()) {
         label17: {
            String var10000 = event.execId();
            String var2 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
            if (var10000 == null) {
               if (var2 != null) {
                  break label17;
               }
            } else if (!var10000.equals(var2)) {
               break label17;
            }

            this.logEvent(event, this.logEvent$default$2());
         }

         event.executorUpdates().foreach((x0$1) -> {
            $anonfun$onExecutorMetricsUpdate$1(this, event, x0$1);
            return BoxedUnit.UNIT;
         });
      }
   }

   public void onResourceProfileAdded(final SparkListenerResourceProfileAdded event) {
      this.logEvent(event, true);
   }

   public void onOtherEvent(final SparkListenerEvent event) {
      if (event.logEvent()) {
         this.logEvent(event, true);
      }
   }

   public void stop() {
      this.logWriter().stop();
   }

   private Properties redactProperties(final Properties properties) {
      if (properties == null) {
         return properties;
      } else {
         Properties redactedProperties = new Properties();
         Tuple2 var5 = scala.jdk.CollectionConverters..MODULE$.PropertiesHasAsScala(properties).asScala().toSeq().partition((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$redactProperties$1(this, x0$1)));
         if (var5 != null) {
            Seq globalProperties = (Seq)var5._1();
            Seq localProperties = (Seq)var5._2();
            Tuple2 var4 = new Tuple2(globalProperties, localProperties);
            Seq globalProperties = (Seq)var4._1();
            Seq localProperties = (Seq)var4._2();
            ((IterableOnceOps)Utils$.MODULE$.redact((SparkConf)this.sparkConf, (scala.collection.Seq)globalProperties).$plus$plus(localProperties)).foreach((x0$2) -> {
               if (x0$2 != null) {
                  String key = (String)x0$2._1();
                  String value = (String)x0$2._2();
                  return redactedProperties.setProperty(key, value);
               } else {
                  throw new MatchError(x0$2);
               }
            });
            return redactedProperties;
         } else {
            throw new MatchError(var5);
         }
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onTaskEnd$1(final SparkListenerTaskEnd event$1, final HashMap metricsPerExecutor) {
      ExecutorMetrics metrics = (ExecutorMetrics)metricsPerExecutor.getOrElseUpdate(event$1.taskInfo().executorId(), () -> new ExecutorMetrics());
      return metrics.compareAndUpdatePeakValues(event$1.taskExecutorMetrics());
   }

   // $FF: synthetic method
   public static final Option $anonfun$onStageCompleted$1(final EventLoggingListener $this, final SparkListenerStageCompleted event$2, final int attemptId) {
      return $this.liveStageExecutorMetrics().remove(new Tuple2.mcII.sp(event$2.stageInfo().stageId(), attemptId));
   }

   // $FF: synthetic method
   public static final void $anonfun$onStageCompleted$3(final EventLoggingListener $this, final SparkListenerStageCompleted event$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String executorId = (String)x0$1._1();
         ExecutorMetrics peakExecutorMetrics = (ExecutorMetrics)x0$1._2();
         $this.logEvent(new SparkListenerStageExecutorMetrics(executorId, event$2.stageInfo().stageId(), event$2.stageInfo().attemptNumber(), peakExecutorMetrics), $this.logEvent$default$2());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onStageCompleted$2(final EventLoggingListener $this, final SparkListenerStageCompleted event$2, final HashMap execMap) {
      execMap.foreach((x0$1) -> {
         $anonfun$onStageCompleted$3($this, event$2, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$onExecutorMetricsUpdate$1(final EventLoggingListener $this, final SparkListenerExecutorMetricsUpdate event$3, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 stageKey1 = (Tuple2)x0$1._1();
         ExecutorMetrics newPeaks = (ExecutorMetrics)x0$1._2();
         $this.liveStageExecutorMetrics().foreach((x0$2) -> {
            if (x0$2 == null) {
               throw new MatchError(x0$2);
            } else {
               HashMap metricsPerExecutor;
               label31: {
                  Tuple2 stageKey2 = (Tuple2)x0$2._1();
                  metricsPerExecutor = (HashMap)x0$2._2();
                  Tuple2 var8 = EventLoggingListener$.MODULE$.DRIVER_STAGE_KEY();
                  if (stageKey1 == null) {
                     if (var8 == null) {
                        break label31;
                     }
                  } else if (stageKey1.equals(var8)) {
                     break label31;
                  }

                  if (stageKey1 == null) {
                     if (stageKey2 == null) {
                        break label31;
                     }
                  } else if (stageKey1.equals(stageKey2)) {
                     break label31;
                  }

                  return BoxedUnit.UNIT;
               }

               ExecutorMetrics metrics = (ExecutorMetrics)metricsPerExecutor.getOrElseUpdate(event$3.execId(), () -> new ExecutorMetrics());
               return BoxesRunTime.boxToBoolean(metrics.compareAndUpdatePeakValues(newPeaks));
            }
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$redactProperties$1(final EventLoggingListener $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String key = (String)x0$1._1();
         return $this.sparkConf.contains(key);
      } else {
         throw new MatchError(x0$1);
      }
   }

   public EventLoggingListener(final String appId, final Option appAttemptId, final URI logBaseDir, final SparkConf sparkConf, final Configuration hadoopConf) {
      this.sparkConf = sparkConf;
      Logging.$init$(this);
      this.logWriter = EventLogFileWriter$.MODULE$.apply(appId, appAttemptId, logBaseDir, sparkConf, hadoopConf);
      this.loggedEvents = new ArrayBuffer();
      this.shouldLogBlockUpdates = BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.EVENT_LOG_BLOCK_UPDATES()));
      this.shouldLogStageExecutorMetrics = BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.EVENT_LOG_STAGE_EXECUTOR_METRICS()));
      this.testing = BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.EVENT_LOG_TESTING()));
      this.liveStageExecutorMetrics = .MODULE$.empty();
      this.jsonProtocolOptions = new JsonProtocolOptions(sparkConf);
   }

   public EventLoggingListener(final String appId, final Option appAttemptId, final URI logBaseDir, final SparkConf sparkConf) {
      this(appId, appAttemptId, logBaseDir, sparkConf, SparkHadoopUtil$.MODULE$.get().newConfiguration(sparkConf));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
