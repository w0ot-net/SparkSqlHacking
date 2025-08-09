package org.apache.spark;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Network$;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.network.sasl.SecretKeyHolder;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tmh!B,Y\u0001as\u0006\u0002C:\u0001\u0005\u0003\u0005\u000b\u0011B;\t\u0011e\u0004!Q1A\u0005\u0002iD\u0011\"!\u0003\u0001\u0005\u0003\u0005\u000b\u0011B>\t\u0015\u0005-\u0001A!A!\u0002\u0013\ti\u0001C\u0004\u00022\u0001!\t!a\r\t\u0013\u0005u\u0002A1A\u0005\n\u0005}\u0002\u0002CA(\u0001\u0001\u0006I!!\u0011\t\u0013\u0005E\u0003A1A\u0005\n\u0005M\u0003\u0002CA.\u0001\u0001\u0006I!!\u0016\t\u0013\u0005u\u0003\u00011A\u0005\n\u0005M\u0003\"CA0\u0001\u0001\u0007I\u0011BA1\u0011!\ti\u0007\u0001Q!\n\u0005U\u0003\"CA8\u0001\u0001\u0007I\u0011BA9\u0011%\tI\b\u0001a\u0001\n\u0013\tY\b\u0003\u0005\u0002\u0000\u0001\u0001\u000b\u0015BA:\u0011%\t\t\t\u0001a\u0001\n\u0013\t\t\bC\u0005\u0002\u0004\u0002\u0001\r\u0011\"\u0003\u0002\u0006\"A\u0011\u0011\u0012\u0001!B\u0013\t\u0019\bC\u0006\u0002\f\u0002\u0001\r\u00111A\u0005\n\u0005E\u0004bCAG\u0001\u0001\u0007\t\u0019!C\u0005\u0003\u001fC1\"a%\u0001\u0001\u0004\u0005\t\u0015)\u0003\u0002t!Y\u0011Q\u0013\u0001A\u0002\u0003\u0007I\u0011BA9\u0011-\t9\n\u0001a\u0001\u0002\u0004%I!!'\t\u0017\u0005u\u0005\u00011A\u0001B\u0003&\u00111\u000f\u0005\f\u0003?\u0003\u0001\u0019!a\u0001\n\u0013\t\t\bC\u0006\u0002\"\u0002\u0001\r\u00111A\u0005\n\u0005\r\u0006bCAT\u0001\u0001\u0007\t\u0011)Q\u0005\u0003gB1\"!+\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0002r!Y\u00111\u0016\u0001A\u0002\u0003\u0007I\u0011BAW\u0011-\t\t\f\u0001a\u0001\u0002\u0003\u0006K!a\u001d\t\u0013\u0005M\u0006A1A\u0005\n\u0005U\u0006\u0002CAc\u0001\u0001\u0006I!a.\t\u0017\u0005\u001d\u0007\u00011AA\u0002\u0013%\u0011\u0011\u001a\u0005\f\u0003\u0017\u0004\u0001\u0019!a\u0001\n\u0013\ti\rC\u0006\u0002R\u0002\u0001\r\u0011!Q!\n\u0005m\u0001\"CAj\u0001\t\u0007I\u0011BA*\u0011!\t)\u000e\u0001Q\u0001\n\u0005U\u0003\"CAl\u0001\t\u0007I\u0011BAm\u0011!\tY\u000f\u0001Q\u0001\n\u0005m\u0007\"CAw\u0001\t\u0007I\u0011BAx\u0011!\t9\u0010\u0001Q\u0001\n\u0005E\b\"CA}\u0001\t\u0007I\u0011BAx\u0011!\tY\u0010\u0001Q\u0001\n\u0005E\bbBA\u007f\u0001\u0011\u0005\u0011q \u0005\b\u0005\u000b\u0001A\u0011\u0001B\u0004\u0011\u001d\u0011)\u0001\u0001C\u0001\u0005GAqAa\u000b\u0001\t\u0003\u0011i\u0003C\u0004\u00034\u0001!\t!!3\t\u000f\tU\u0002\u0001\"\u0001\u0002J\"9!q\u0007\u0001\u0005\u0002\te\u0002b\u0002B \u0001\u0011\u0005!\u0011\t\u0005\b\u0005\u000b\u0002A\u0011AAe\u0011\u001d\u00119\u0005\u0001C\u0001\u0003\u0013DqA!\u0013\u0001\t\u0003\u0011Y\u0005C\u0004\u0003R\u0001!\tAa\u0015\t\u000f\te\u0003\u0001\"\u0001\u0003\\!9!\u0011\r\u0001\u0005\u0002\t\r\u0004b\u0002B3\u0001\u0011\u0005!q\r\u0005\b\u0005S\u0002A\u0011\u0001B6\u0011\u001d\u0011\t\b\u0001C\u0001\u0005gBqAa\u001e\u0001\t\u0003\u0011I\bC\u0004\u0003~\u0001!\tAa\u001a\t\u000f\t}\u0004\u0001\"\u0001\u0003h!9!\u0011\u0011\u0001\u0005\u0002\t\u001d\u0004b\u0002BB\u0001\u0011\u0005!Q\u0011\u0005\b\u0005\u000f\u0003A\u0011\u0001BE\u0011\u001d\u0011Y\t\u0001C\u0001\u0005\u0013CqA!$\u0001\t\u0003\u0011y\tC\u0004\u0003\u0012\u0002!IAa%\t\u000f\tU\u0005\u0001\"\u0003\u0003\u0018\"9!q\u0011\u0001\u0005B\t\r\u0006b\u0002BF\u0001\u0011\u0005#\u0011\u0016\u0005\b\u0005[\u0003A\u0011\u0001BX\u000f!\u00119\f\u0017E\u00011\nefaB,Y\u0011\u0003A&1\u0018\u0005\b\u0003cYE\u0011\u0001B_\u0011%\u0011yl\u0013b\u0001\n\u0003\tI\r\u0003\u0005\u0003B.\u0003\u000b\u0011BA\u000e\u0011%\u0011\u0019m\u0013b\u0001\n\u0003\tI\r\u0003\u0005\u0003F.\u0003\u000b\u0011BA\u000e\u0011%\u00119m\u0013b\u0001\n\u0003\ty\u0004\u0003\u0005\u0003J.\u0003\u000b\u0011BA!\u0011%\u0011Ym\u0013b\u0001\n\u0003\u0011i\r\u0003\u0005\u0003\\.\u0003\u000b\u0011\u0002Bh\u0011%\u0011inSI\u0001\n\u0003\u0011y\u000eC\u0005\u0003v.\u000b\n\u0011\"\u0001\u0003x\ny1+Z2ve&$\u00180T1oC\u001e,'O\u0003\u0002Z5\u0006)1\u000f]1sW*\u00111\fX\u0001\u0007CB\f7\r[3\u000b\u0003u\u000b1a\u001c:h'\u0011\u0001q,Z6\u0011\u0005\u0001\u001cW\"A1\u000b\u0003\t\fQa]2bY\u0006L!\u0001Z1\u0003\r\u0005s\u0017PU3g!\t1\u0017.D\u0001h\u0015\tA\u0007,\u0001\u0005j]R,'O\\1m\u0013\tQwMA\u0004M_\u001e<\u0017N\\4\u0011\u00051\fX\"A7\u000b\u00059|\u0017\u0001B:bg2T!\u0001\u001d-\u0002\u000f9,Go^8sW&\u0011!/\u001c\u0002\u0010'\u0016\u001c'/\u001a;LKfDu\u000e\u001c3fe\u0006I1\u000f]1sW\u000e{gNZ\u0002\u0001!\t1x/D\u0001Y\u0013\tA\bLA\u0005Ta\u0006\u00148nQ8oM\u0006y\u0011n\\#oGJL\b\u000f^5p].+\u00170F\u0001|!\r\u0001GP`\u0005\u0003{\u0006\u0014aa\u00149uS>t\u0007\u0003\u00021\u0000\u0003\u0007I1!!\u0001b\u0005\u0015\t%O]1z!\r\u0001\u0017QA\u0005\u0004\u0003\u000f\t'\u0001\u0002\"zi\u0016\f\u0001#[8F]\u000e\u0014\u0018\u0010\u001d;j_:\\U-\u001f\u0011\u0002%\u0005,H\u000f[*fGJ,GOR5mK\u000e{gN\u001a\t\u0007\u0003\u001f\t)\"!\u0007\u000e\u0005\u0005E!bAA\nO\u000611m\u001c8gS\u001eLA!a\u0006\u0002\u0012\tY1i\u001c8gS\u001e,e\u000e\u001e:z!\u0011\u0001G0a\u0007\u0011\t\u0005u\u00111\u0006\b\u0005\u0003?\t9\u0003E\u0002\u0002\"\u0005l!!a\t\u000b\u0007\u0005\u0015B/\u0001\u0004=e>|GOP\u0005\u0004\u0003S\t\u0017A\u0002)sK\u0012,g-\u0003\u0003\u0002.\u0005=\"AB*ue&twMC\u0002\u0002*\u0005\fa\u0001P5oSRtD\u0003CA\u001b\u0003o\tI$a\u000f\u0011\u0005Y\u0004\u0001\"B:\u0006\u0001\u0004)\bbB=\u0006!\u0003\u0005\ra\u001f\u0005\n\u0003\u0017)\u0001\u0013!a\u0001\u0003\u001b\tAbV%M\t\u000e\u000b%\u000bR0B\u00072+\"!!\u0011\u0011\t\u0005\r\u0013QJ\u0007\u0003\u0003\u000bRA!a\u0012\u0002J\u0005!A.\u00198h\u0015\t\tY%\u0001\u0003kCZ\f\u0017\u0002BA\u0017\u0003\u000b\nQbV%M\t\u000e\u000b%\u000bR0B\u00072\u0003\u0013AB1vi\"|e.\u0006\u0002\u0002VA\u0019\u0001-a\u0016\n\u0007\u0005e\u0013MA\u0004C_>dW-\u00198\u0002\u000f\u0005,H\u000f[(oA\u00051\u0011m\u00197t\u001f:\f!\"Y2mg>sw\fJ3r)\u0011\t\u0019'!\u001b\u0011\u0007\u0001\f)'C\u0002\u0002h\u0005\u0014A!\u00168ji\"I\u00111N\u0006\u0002\u0002\u0003\u0007\u0011QK\u0001\u0004q\u0012\n\u0014aB1dYN|e\u000eI\u0001\nC\u0012l\u0017N\\!dYN,\"!a\u001d\u0011\r\u0005u\u0011QOA\u000e\u0013\u0011\t9(a\f\u0003\u0007M+G/A\u0007bI6Lg.Q2mg~#S-\u001d\u000b\u0005\u0003G\ni\bC\u0005\u0002l9\t\t\u00111\u0001\u0002t\u0005Q\u0011\rZ7j]\u0006\u001bGn\u001d\u0011\u0002\u001f\u0005$W.\u001b8BG2\u001cxI]8vaN\f1#\u00193nS:\f5\r\\:He>,\bo]0%KF$B!a\u0019\u0002\b\"I\u00111N\t\u0002\u0002\u0003\u0007\u00111O\u0001\u0011C\u0012l\u0017N\\!dYN<%o\\;qg\u0002\n\u0001B^5fo\u0006\u001bGn]\u0001\rm&,w/Q2mg~#S-\u001d\u000b\u0005\u0003G\n\t\nC\u0005\u0002lQ\t\t\u00111\u0001\u0002t\u0005Ia/[3x\u0003\u000ed7\u000fI\u0001\u000fm&,w/Q2mg\u001e\u0013x.\u001e9t\u0003I1\u0018.Z<BG2\u001cxI]8vaN|F%Z9\u0015\t\u0005\r\u00141\u0014\u0005\n\u0003W:\u0012\u0011!a\u0001\u0003g\nqB^5fo\u0006\u001bGn]$s_V\u00048\u000fI\u0001\u000b[>$\u0017NZ=BG2\u001c\u0018AD7pI&4\u00170Q2mg~#S-\u001d\u000b\u0005\u0003G\n)\u000bC\u0005\u0002li\t\t\u00111\u0001\u0002t\u0005YQn\u001c3jMf\f5\r\\:!\u0003Aiw\u000eZ5gs\u0006\u001bGn]$s_V\u00048/\u0001\u000bn_\u0012Lg-_!dYN<%o\\;qg~#S-\u001d\u000b\u0005\u0003G\ny\u000bC\u0005\u0002lu\t\t\u00111\u0001\u0002t\u0005\tRn\u001c3jMf\f5\r\\:He>,\bo\u001d\u0011\u0002\u001f\u0011,g-Y;mi\u0006\u001bG.V:feN,\"!a.\u0011\r\u0005e\u00161YA\u000e\u001b\t\tYL\u0003\u0003\u0002>\u0006}\u0016!C5n[V$\u0018M\u00197f\u0015\r\t\t-Y\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA<\u0003w\u000b\u0001\u0003Z3gCVdG/Q2m+N,'o\u001d\u0011\u0002\u0013M,7M]3u\u0017\u0016LXCAA\u000e\u00035\u0019Xm\u0019:fi.+\u0017p\u0018\u0013fcR!\u00111MAh\u0011%\tYGIA\u0001\u0002\u0004\tY\"\u0001\u0006tK\u000e\u0014X\r^&fs\u0002\nQb]:m%B\u001cWI\\1cY\u0016$\u0017AD:tYJ\u00038-\u00128bE2,G\rI\u0001\u000bQ\u0006$wn\u001c9D_:4WCAAn!\u0011\ti.a:\u000e\u0005\u0005}'\u0002BAq\u0003G\fAaY8oM*\u0019\u0011Q\u001d.\u0002\r!\fGm\\8q\u0013\u0011\tI/a8\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0003-A\u0017\rZ8pa\u000e{gN\u001a\u0011\u0002#\u0011,g-Y;miN\u001bFj\u00149uS>t7/\u0006\u0002\u0002rB\u0019a/a=\n\u0007\u0005U\bL\u0001\u0006T'2{\u0005\u000f^5p]N\f!\u0003Z3gCVdGoU*M\u001fB$\u0018n\u001c8tA\u0005i!\u000f]2T'2{\u0005\u000f^5p]N\faB\u001d9d'Ncu\n\u001d;j_:\u001c\b%A\u0007hKR\u001c6\u000bT(qi&|gn\u001d\u000b\u0005\u0003c\u0014\t\u0001C\u0004\u0003\u00041\u0002\r!a\u0007\u0002\r5|G-\u001e7f\u0003-\u0019X\r\u001e,jK^\f5\r\\:\u0015\r\u0005\r$\u0011\u0002B\u0007\u0011\u001d\u0011Y!\fa\u0001\u0003g\nA\u0002Z3gCVdG/V:feNDqAa\u0004.\u0001\u0004\u0011\t\"\u0001\u0007bY2|w/\u001a3Vg\u0016\u00148\u000f\u0005\u0004\u0003\u0014\tu\u00111\u0004\b\u0005\u0005+\u0011IB\u0004\u0003\u0002\"\t]\u0011\"\u00012\n\u0007\tm\u0011-A\u0004qC\u000e\\\u0017mZ3\n\t\t}!\u0011\u0005\u0002\u0004'\u0016\f(b\u0001B\u000eCR1\u00111\rB\u0013\u0005SAqAa\n/\u0001\u0004\tY\"A\u0006eK\u001a\fW\u000f\u001c;Vg\u0016\u0014\bb\u0002B\b]\u0001\u0007!\u0011C\u0001\u0012g\u0016$h+[3x\u0003\u000ed7o\u0012:pkB\u001cH\u0003BA2\u0005_AqA!\r0\u0001\u0004\u0011\t\"A\tbY2|w/\u001a3Vg\u0016\u0014xI]8vaN\f1bZ3u-&,w/Q2mg\u0006\tr-\u001a;WS\u0016<\u0018i\u00197t\u000fJ|W\u000f]:\u0002\u001bM,G/T8eS\u001aL\u0018i\u00197t)\u0019\t\u0019Ga\u000f\u0003>!9!1\u0002\u001aA\u0002\u0005M\u0004b\u0002B\be\u0001\u0007!\u0011C\u0001\u0014g\u0016$Xj\u001c3jMf\f5\r\\:He>,\bo\u001d\u000b\u0005\u0003G\u0012\u0019\u0005C\u0004\u00032M\u0002\rA!\u0005\u0002\u001b\u001d,G/T8eS\u001aL\u0018i\u00197t\u0003M9W\r^'pI&4\u00170Q2mg\u001e\u0013x.\u001e9t\u00031\u0019X\r^!e[&t\u0017i\u00197t)\u0011\t\u0019G!\u0014\t\u000f\t=c\u00071\u0001\u0003\u0012\u0005Q\u0011\rZ7j]V\u001bXM]:\u0002%M,G/\u00113nS:\f5\r\\:He>,\bo\u001d\u000b\u0005\u0003G\u0012)\u0006C\u0004\u0003X]\u0002\rA!\u0005\u0002\u001f\u0005$W.\u001b8Vg\u0016\u0014xI]8vaN\fqa]3u\u0003\u000ed7\u000f\u0006\u0003\u0002d\tu\u0003b\u0002B0q\u0001\u0007\u0011QK\u0001\u000bC\u000ed7+\u001a;uS:<\u0017AE4fi&{UI\\2ssB$\u0018n\u001c8LKf$\u0012a_\u0001\fC\u000ed7/\u00128bE2,G\r\u0006\u0002\u0002V\u0005)2\r[3dW\u0006#W.\u001b8QKJl\u0017n]:j_:\u001cH\u0003BA+\u0005[BqAa\u001c<\u0001\u0004\tY\"\u0001\u0003vg\u0016\u0014\u0018AF2iK\u000e\\W+\u0013,jK^\u0004VM]7jgNLwN\\:\u0015\t\u0005U#Q\u000f\u0005\b\u0005_b\u0004\u0019AA\u000e\u0003Y\u0019\u0007.Z2l\u001b>$\u0017NZ=QKJl\u0017n]:j_:\u001cH\u0003BA+\u0005wBqAa\u001c>\u0001\u0004\tY\"A\fjg\u0006+H\u000f[3oi&\u001c\u0017\r^5p]\u0016s\u0017M\u00197fI\u0006\u0019\u0012n]#oGJL\b\u000f^5p]\u0016s\u0017M\u00197fI\u0006y\u0011n]*tYJ\u00038-\u00128bE2,G-\u0001\thKR\u0014\u0006oY*T\u0019>\u0003H/[8ogR\u0011\u0011\u0011_\u0001\fO\u0016$8+Y:m+N,'\u000f\u0006\u0002\u0002\u001c\u0005aq-\u001a;TK\u000e\u0014X\r^&fs\u0006q\u0011N\\5uS\u0006d\u0017N_3BkRDGCAA2\u0003E\u0019Xm\u0019:fi.+\u0017P\u0012:p[\u001aKG.\u001a\u000b\u0003\u00033\t1\"[:Vg\u0016\u0014\u0018J\\!D\u0019RA\u0011Q\u000bBM\u00057\u0013y\nC\u0004\u0003p\u0019\u0003\r!a\u0007\t\u000f\tue\t1\u0001\u0002t\u0005A\u0011m\u00197Vg\u0016\u00148\u000fC\u0004\u0003\"\u001a\u0003\r!a\u001d\u0002\u0013\u0005\u001cGn\u0012:pkB\u001cH\u0003BA\u000e\u0005KCqAa*H\u0001\u0004\tY\"A\u0003baBLE\r\u0006\u0003\u0002\u001c\t-\u0006b\u0002BT\u0011\u0002\u0007\u00111D\u0001!O\u0016$XI\u001c<je>tW.\u001a8u\r>\u00148k\u001d7Sa\u000e\u0004\u0016m]:x_J$7/\u0006\u0002\u00032BA\u0011Q\u0004BZ\u00037\tY\"\u0003\u0003\u00036\u0006=\"aA'ba\u0006y1+Z2ve&$\u00180T1oC\u001e,'\u000f\u0005\u0002w\u0017N\u00111j\u0018\u000b\u0003\u0005s\u000bqb\u0015)B%.{\u0016)\u0016+I?\u000e{eJR\u0001\u0011'B\u000b%kS0B+RCulQ(O\r\u0002\nac\u0015)B%.{\u0016)\u0016+I?N+5IU#U?\u000e{eJR\u0001\u0018'B\u000b%kS0B+RCulU#D%\u0016#vlQ(O\r\u0002\nq\"\u0012(W?\u0006+F\u000bS0T\u000b\u000e\u0013V\tV\u0001\u0011\u000b:3v,Q+U\u0011~\u001bVi\u0011*F)\u0002\n\u0011cU#D%\u0016#v\fT(P\u0017V\u0003vlS#Z+\t\u0011y\r\u0005\u0003\u0003R\n]WB\u0001Bj\u0015\u0011\u0011).a9\u0002\u0005%|\u0017\u0002\u0002Bm\u0005'\u0014A\u0001V3yi\u0006\u00112+R\"S\u000bR{FjT(L+B{6*R-!\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%eU\u0011!\u0011\u001d\u0016\u0004w\n\r8F\u0001Bs!\u0011\u00119O!=\u000e\u0005\t%(\u0002\u0002Bv\u0005[\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\t=\u0018-\u0001\u0006b]:|G/\u0019;j_:LAAa=\u0003j\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134+\t\u0011IP\u000b\u0003\u0002\u000e\t\r\b"
)
public class SecurityManager implements Logging, SecretKeyHolder {
   private final SparkConf sparkConf;
   private final Option ioEncryptionKey;
   private final ConfigEntry authSecretFileConf;
   private final String WILDCARD_ACL;
   private final boolean authOn;
   private boolean aclsOn;
   private Set adminAcls;
   private Set adminAclsGroups;
   private Set viewAcls;
   private Set viewAclsGroups;
   private Set modifyAcls;
   private Set modifyAclsGroups;
   private final Set defaultAclUsers;
   private String secretKey;
   private final boolean sslRpcEnabled;
   private final Configuration hadoopConf;
   private final SSLOptions defaultSSLOptions;
   private final SSLOptions rpcSSLOptions;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static ConfigEntry $lessinit$greater$default$3() {
      return SecurityManager$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return SecurityManager$.MODULE$.$lessinit$greater$default$2();
   }

   public static Text SECRET_LOOKUP_KEY() {
      return SecurityManager$.MODULE$.SECRET_LOOKUP_KEY();
   }

   public static String ENV_AUTH_SECRET() {
      return SecurityManager$.MODULE$.ENV_AUTH_SECRET();
   }

   public static String SPARK_AUTH_SECRET_CONF() {
      return SecurityManager$.MODULE$.SPARK_AUTH_SECRET_CONF();
   }

   public static String SPARK_AUTH_CONF() {
      return SecurityManager$.MODULE$.SPARK_AUTH_CONF();
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

   public Option ioEncryptionKey() {
      return this.ioEncryptionKey;
   }

   private String WILDCARD_ACL() {
      return this.WILDCARD_ACL;
   }

   private boolean authOn() {
      return this.authOn;
   }

   private boolean aclsOn() {
      return this.aclsOn;
   }

   private void aclsOn_$eq(final boolean x$1) {
      this.aclsOn = x$1;
   }

   private Set adminAcls() {
      return this.adminAcls;
   }

   private void adminAcls_$eq(final Set x$1) {
      this.adminAcls = x$1;
   }

   private Set adminAclsGroups() {
      return this.adminAclsGroups;
   }

   private void adminAclsGroups_$eq(final Set x$1) {
      this.adminAclsGroups = x$1;
   }

   private Set viewAcls() {
      return this.viewAcls;
   }

   private void viewAcls_$eq(final Set x$1) {
      this.viewAcls = x$1;
   }

   private Set viewAclsGroups() {
      return this.viewAclsGroups;
   }

   private void viewAclsGroups_$eq(final Set x$1) {
      this.viewAclsGroups = x$1;
   }

   private Set modifyAcls() {
      return this.modifyAcls;
   }

   private void modifyAcls_$eq(final Set x$1) {
      this.modifyAcls = x$1;
   }

   private Set modifyAclsGroups() {
      return this.modifyAclsGroups;
   }

   private void modifyAclsGroups_$eq(final Set x$1) {
      this.modifyAclsGroups = x$1;
   }

   private Set defaultAclUsers() {
      return this.defaultAclUsers;
   }

   private String secretKey() {
      return this.secretKey;
   }

   private void secretKey_$eq(final String x$1) {
      this.secretKey = x$1;
   }

   private boolean sslRpcEnabled() {
      return this.sslRpcEnabled;
   }

   private Configuration hadoopConf() {
      return this.hadoopConf;
   }

   private SSLOptions defaultSSLOptions() {
      return this.defaultSSLOptions;
   }

   private SSLOptions rpcSSLOptions() {
      return this.rpcSSLOptions;
   }

   public SSLOptions getSSLOptions(final String module) {
      SSLOptions opts = SSLOptions$.MODULE$.parse(this.sparkConf, this.hadoopConf(), "spark.ssl." + module, new Some(this.defaultSSLOptions()));
      this.logDebug((Function0)(() -> "Created SSL options for " + module + ": " + opts));
      return opts;
   }

   public void setViewAcls(final Set defaultUsers, final Seq allowedUsers) {
      this.viewAcls_$eq((Set)this.adminAcls().$plus$plus(defaultUsers).$plus$plus(allowedUsers));
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Changing view acls to: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.VIEW_ACLS..MODULE$, this.viewAcls().mkString(","))})))));
   }

   public void setViewAcls(final String defaultUser, final Seq allowedUsers) {
      this.setViewAcls((Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{defaultUser}))), allowedUsers);
   }

   public void setViewAclsGroups(final Seq allowedUserGroups) {
      this.viewAclsGroups_$eq((Set)this.adminAclsGroups().$plus$plus(allowedUserGroups));
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Changing view acls groups to: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.VIEW_ACLS..MODULE$, this.viewAcls().mkString(","))})))));
   }

   public String getViewAcls() {
      return this.viewAcls().contains(this.WILDCARD_ACL()) ? this.WILDCARD_ACL() : this.viewAcls().mkString(",");
   }

   public String getViewAclsGroups() {
      return this.viewAclsGroups().contains(this.WILDCARD_ACL()) ? this.WILDCARD_ACL() : this.viewAclsGroups().mkString(",");
   }

   public void setModifyAcls(final Set defaultUsers, final Seq allowedUsers) {
      this.modifyAcls_$eq((Set)this.adminAcls().$plus$plus(defaultUsers).$plus$plus(allowedUsers));
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Changing modify acls to: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MODIFY_ACLS..MODULE$, this.modifyAcls().mkString(","))})))));
   }

   public void setModifyAclsGroups(final Seq allowedUserGroups) {
      this.modifyAclsGroups_$eq((Set)this.adminAclsGroups().$plus$plus(allowedUserGroups));
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Changing modify acls groups to: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MODIFY_ACLS..MODULE$, this.modifyAcls().mkString(","))})))));
   }

   public String getModifyAcls() {
      return this.modifyAcls().contains(this.WILDCARD_ACL()) ? this.WILDCARD_ACL() : this.modifyAcls().mkString(",");
   }

   public String getModifyAclsGroups() {
      return this.modifyAclsGroups().contains(this.WILDCARD_ACL()) ? this.WILDCARD_ACL() : this.modifyAclsGroups().mkString(",");
   }

   public void setAdminAcls(final Seq adminUsers) {
      this.adminAcls_$eq(adminUsers.toSet());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Changing admin acls to: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ADMIN_ACLS..MODULE$, this.adminAcls().mkString(","))})))));
   }

   public void setAdminAclsGroups(final Seq adminUserGroups) {
      this.adminAclsGroups_$eq(adminUserGroups.toSet());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Changing admin acls groups to: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ADMIN_ACLS..MODULE$, this.adminAcls().mkString(","))})))));
   }

   public void setAcls(final boolean aclSetting) {
      this.aclsOn_$eq(aclSetting);
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Changing acls enabled to: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.AUTH_ENABLED..MODULE$, BoxesRunTime.boxToBoolean(this.aclsOn()))})))));
   }

   public Option getIOEncryptionKey() {
      return this.ioEncryptionKey();
   }

   public boolean aclsEnabled() {
      return this.aclsOn();
   }

   public boolean checkAdminPermissions(final String user) {
      return this.isUserInACL(user, this.adminAcls(), this.adminAclsGroups());
   }

   public boolean checkUIViewPermissions(final String user) {
      this.logDebug((Function0)(() -> "user=" + user + " aclsEnabled=" + this.aclsEnabled() + " viewAcls=" + this.viewAcls().mkString(",") + " viewAclsGroups=" + this.viewAclsGroups().mkString(",")));
      return this.isUserInACL(user, this.viewAcls(), this.viewAclsGroups());
   }

   public boolean checkModifyPermissions(final String user) {
      this.logDebug((Function0)(() -> "user=" + user + " aclsEnabled=" + this.aclsEnabled() + " modifyAcls=" + this.modifyAcls().mkString(",") + " modifyAclsGroups=" + this.modifyAclsGroups().mkString(",")));
      return this.isUserInACL(user, this.modifyAcls(), this.modifyAclsGroups());
   }

   public boolean isAuthenticationEnabled() {
      return this.authOn();
   }

   public boolean isEncryptionEnabled() {
      boolean encryptionEnabled = BoxesRunTime.unboxToBoolean(this.sparkConf.get(Network$.MODULE$.NETWORK_CRYPTO_ENABLED())) || BoxesRunTime.unboxToBoolean(this.sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.SASL_ENCRYPTION_ENABLED()));
      if (encryptionEnabled && this.sslRpcEnabled()) {
         this.logWarning((Function0)(() -> "Network encryption disabled as RPC SSL encryption is enabled"));
         return false;
      } else {
         return encryptionEnabled;
      }
   }

   public boolean isSslRpcEnabled() {
      return this.sslRpcEnabled();
   }

   public SSLOptions getRpcSSLOptions() {
      return this.rpcSSLOptions();
   }

   public String getSaslUser() {
      return "sparkSaslUser";
   }

   public String getSecretKey() {
      if (this.isAuthenticationEnabled()) {
         Credentials creds = UserGroupInformation.getCurrentUser().getCredentials();
         return (String)scala.Option..MODULE$.apply(creds.getSecretKey(SecurityManager$.MODULE$.SECRET_LOOKUP_KEY())).map((bytes) -> new String(bytes, StandardCharsets.UTF_8)).orElse(() -> scala.Option..MODULE$.apply(this.secretKey())).orElse(() -> scala.Option..MODULE$.apply(this.sparkConf.getenv(SecurityManager$.MODULE$.ENV_AUTH_SECRET()))).orElse(() -> this.sparkConf.getOption(SecurityManager$.MODULE$.SPARK_AUTH_SECRET_CONF())).orElse(() -> this.secretKeyFromFile()).getOrElse(() -> {
            throw new IllegalArgumentException("A secret key must be specified via the " + SecurityManager$.MODULE$.SPARK_AUTH_SECRET_CONF() + " config");
         });
      } else {
         return null;
      }
   }

   public void initializeAuth() {
      if (BoxesRunTime.unboxToBoolean(this.sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.NETWORK_AUTH_ENABLED()))) {
         String master = this.sparkConf.get("spark.master", "");
         boolean var10000;
         if ("yarn".equals(master)) {
            var10000 = true;
         } else if ("local".equals(master)) {
            var10000 = true;
         } else {
            label78: {
               if (master != null) {
                  Option var6 = SparkMasterRegex$.MODULE$.LOCAL_N_REGEX().unapplySeq(master);
                  if (!var6.isEmpty() && var6.get() != null && ((List)var6.get()).lengthCompare(1) == 0) {
                     var10000 = true;
                     break label78;
                  }
               }

               if (master != null) {
                  Option var7 = SparkMasterRegex$.MODULE$.LOCAL_N_FAILURES_REGEX().unapplySeq(master);
                  if (!var7.isEmpty() && var7.get() != null && ((List)var7.get()).lengthCompare(2) == 0) {
                     var10000 = true;
                     break label78;
                  }
               }

               var10000 = false;
            }
         }

         label79: {
            if (var10000) {
               var10000 = true;
            } else {
               if (master == null) {
                  break label79;
               }

               Option var8 = SparkMasterRegex$.MODULE$.KUBERNETES_REGEX().unapplySeq(master);
               if (var8.isEmpty() || var8.get() == null || ((List)var8.get()).lengthCompare(1) != 0) {
                  break label79;
               }

               var10000 = false;
            }

            boolean storeInUgi = var10000;
            if (((Option)this.sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.AUTH_SECRET_FILE_DRIVER())).isDefined() != ((Option)this.sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.AUTH_SECRET_FILE_EXECUTOR())).isDefined()) {
               throw new IllegalArgumentException("Invalid secret configuration: Secret files must be specified for both the driver and the executors, not only one or the other.");
            }

            this.secretKey_$eq((String)this.secretKeyFromFile().getOrElse(() -> Utils$.MODULE$.createSecret(this.sparkConf)));
            if (storeInUgi) {
               Credentials creds = new Credentials();
               creds.addSecretKey(SecurityManager$.MODULE$.SECRET_LOOKUP_KEY(), this.secretKey().getBytes(StandardCharsets.UTF_8));
               UserGroupInformation.getCurrentUser().addCredentials(creds);
               return;
            }

            return;
         }

         scala.Predef..MODULE$.require(this.sparkConf.contains(SecurityManager$.MODULE$.SPARK_AUTH_SECRET_CONF()), () -> "A secret key must be specified via the " + SecurityManager$.MODULE$.SPARK_AUTH_SECRET_CONF() + " config.");
      }
   }

   private Option secretKeyFromFile() {
      return ((Option)this.sparkConf.get(this.authSecretFileConf)).flatMap((secretFilePath) -> this.sparkConf.getOption("spark.master").map((x0$1) -> {
            if (x0$1 != null) {
               Option var4 = SparkMasterRegex$.MODULE$.KUBERNETES_REGEX().unapplySeq(x0$1);
               if (!var4.isEmpty() && var4.get() != null && ((List)var4.get()).lengthCompare(1) == 0) {
                  File secretFile = new File(secretFilePath);
                  scala.Predef..MODULE$.require(secretFile.isFile(), () -> "No file found containing the secret key at " + secretFilePath + ".");
                  String base64Key = Base64.getEncoder().encodeToString(Files.readAllBytes(secretFile.toPath()));
                  scala.Predef..MODULE$.require(!base64Key.isEmpty(), () -> "Secret key from file located at " + secretFilePath + " is empty.");
                  return base64Key;
               }
            }

            throw new IllegalArgumentException("Secret keys provided via files is only allowed in Kubernetes mode.");
         }));
   }

   private boolean isUserInACL(final String user, final Set aclUsers, final Set aclGroups) {
      if (user != null && this.aclsEnabled() && !aclUsers.contains(this.WILDCARD_ACL()) && !aclUsers.contains(user) && !aclGroups.contains(this.WILDCARD_ACL())) {
         Set userGroups = Utils$.MODULE$.getCurrentUserGroups(this.sparkConf, user);
         this.logDebug((Function0)(() -> "user " + user + " is in groups " + userGroups.mkString(",")));
         return aclGroups.exists((x$6) -> BoxesRunTime.boxToBoolean($anonfun$isUserInACL$2(userGroups, x$6)));
      } else {
         return true;
      }
   }

   public String getSaslUser(final String appId) {
      return this.getSaslUser();
   }

   public String getSecretKey(final String appId) {
      return this.getSecretKey();
   }

   public scala.collection.immutable.Map getEnvironmentForSslRpcPasswords() {
      if (this.rpcSSLOptions().enabled()) {
         scala.collection.mutable.Map map = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         this.rpcSSLOptions().keyPassword().foreach((password) -> (scala.collection.mutable.Map)map.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(SSLOptions$.MODULE$.ENV_RPC_SSL_KEY_PASSWORD()), password)));
         this.rpcSSLOptions().privateKeyPassword().foreach((password) -> (scala.collection.mutable.Map)map.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(SSLOptions$.MODULE$.ENV_RPC_SSL_PRIVATE_KEY_PASSWORD()), password)));
         this.rpcSSLOptions().keyStorePassword().foreach((password) -> (scala.collection.mutable.Map)map.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(SSLOptions$.MODULE$.ENV_RPC_SSL_KEY_STORE_PASSWORD()), password)));
         this.rpcSSLOptions().trustStorePassword().foreach((password) -> (scala.collection.mutable.Map)map.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(SSLOptions$.MODULE$.ENV_RPC_SSL_TRUST_STORE_PASSWORD()), password)));
         return map.toMap(scala..less.colon.less..MODULE$.refl());
      } else {
         return (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isUserInACL$2(final Set userGroups$1, final String x$6) {
      return userGroups$1.contains(x$6);
   }

   public SecurityManager(final SparkConf sparkConf, final Option ioEncryptionKey, final ConfigEntry authSecretFileConf) {
      this.sparkConf = sparkConf;
      this.ioEncryptionKey = ioEncryptionKey;
      this.authSecretFileConf = authSecretFileConf;
      Logging.$init$(this);
      this.WILDCARD_ACL = "*";
      this.authOn = BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.NETWORK_AUTH_ENABLED()));
      this.aclsOn = BoxesRunTime.unboxToBoolean(sparkConf.get(UI$.MODULE$.ACLS_ENABLE()));
      this.adminAcls = ((IterableOnceOps)sparkConf.get(UI$.MODULE$.ADMIN_ACLS())).toSet();
      this.adminAclsGroups = ((IterableOnceOps)sparkConf.get(UI$.MODULE$.ADMIN_ACLS_GROUPS())).toSet();
      this.defaultAclUsers = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{System.getProperty("user.name", ""), Utils$.MODULE$.getCurrentUserName()})));
      this.setViewAcls(this.defaultAclUsers(), (Seq)sparkConf.get(UI$.MODULE$.UI_VIEW_ACLS()));
      this.setModifyAcls(this.defaultAclUsers(), (Seq)sparkConf.get(UI$.MODULE$.MODIFY_ACLS()));
      this.setViewAclsGroups((Seq)sparkConf.get(UI$.MODULE$.UI_VIEW_ACLS_GROUPS()));
      this.setModifyAclsGroups((Seq)sparkConf.get(UI$.MODULE$.MODIFY_ACLS_GROUPS()));
      this.sslRpcEnabled = sparkConf.getBoolean("spark.ssl.rpc.enabled", false);
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"SecurityManager: authentication ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.AUTH_ENABLED..MODULE$, this.authOn() ? "enabled" : "disabled")}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"; ui acls ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UI_ACLS..MODULE$, this.aclsOn() ? "enabled" : "disabled")})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"; users with view permissions: ", " groups with view permissions: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.VIEW_ACLS..MODULE$, this.viewAcls().nonEmpty() ? this.viewAcls().mkString(", ") : "EMPTY"), new MDC(org.apache.spark.internal.LogKeys.VIEW_ACLS_GROUPS..MODULE$, this.viewAclsGroups().nonEmpty() ? this.viewAclsGroups().mkString(", ") : "EMPTY")})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"; users with modify permissions: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MODIFY_ACLS..MODULE$, this.modifyAcls().nonEmpty() ? this.modifyAcls().mkString(", ") : "EMPTY")})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"; groups with modify permissions: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MODIFY_ACLS_GROUPS..MODULE$, this.modifyAclsGroups().nonEmpty() ? this.modifyAclsGroups().mkString(", ") : "EMPTY")})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"; RPC SSL ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_SSL_ENABLED..MODULE$, this.sslRpcEnabled() ? "enabled" : "disabled")}))))));
      this.hadoopConf = SparkHadoopUtil$.MODULE$.get().newConfiguration(sparkConf);
      this.defaultSSLOptions = SSLOptions$.MODULE$.parse(sparkConf, this.hadoopConf(), "spark.ssl", scala.None..MODULE$);
      this.rpcSSLOptions = this.getSSLOptions("rpc");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
