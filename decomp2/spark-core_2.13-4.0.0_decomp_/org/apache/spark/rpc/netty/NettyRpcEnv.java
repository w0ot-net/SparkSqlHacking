package org.apache.spark.rpc.netty;

import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Network$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.network.TransportContext;
import org.apache.spark.network.client.StreamCallback;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.client.TransportClientBootstrap;
import org.apache.spark.network.client.TransportClientFactory;
import org.apache.spark.network.crypto.AuthClientBootstrap;
import org.apache.spark.network.crypto.AuthServerBootstrap;
import org.apache.spark.network.netty.SparkTransportConf$;
import org.apache.spark.network.server.NoOpRpcHandler;
import org.apache.spark.network.server.TransportServer;
import org.apache.spark.network.server.TransportServerBootstrap;
import org.apache.spark.network.util.TransportConf;
import org.apache.spark.rpc.AbortableRpcFuture;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcAddress$;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointAddress;
import org.apache.spark.rpc.RpcEndpointAddress$;
import org.apache.spark.rpc.RpcEndpointNotFoundException;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcEnvFileServer;
import org.apache.spark.rpc.RpcEnvStoppedException;
import org.apache.spark.rpc.RpcTimeout;
import org.apache.spark.serializer.JavaSerializerInstance;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.concurrent.Future;
import scala.concurrent.Promise;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011-c!\u0002*T\u0001Mk\u0006\u0002\u00035\u0001\u0005\u000b\u0007I\u0011\u00016\t\u0011=\u0004!\u0011!Q\u0001\n-D\u0001\u0002\u001d\u0001\u0003\u0002\u0003\u0006I!\u001d\u0005\to\u0002\u0011\t\u0011)A\u0005q\"Q\u00111\u0002\u0001\u0003\u0002\u0003\u0006I!!\u0004\t\u0015\u0005M\u0001A!A!\u0002\u0013\t)\u0002C\u0004\u0002\u001e\u0001!\t!a\b\t\u0013\u0005=\u0002A1A\u0005\u0002\u0005E\u0002\u0002CA$\u0001\u0001\u0006I!a\r\t\u0015\u0005%\u0003A1A\u0005\u0002M\u000bY\u0005\u0003\u0005\u0002^\u0001\u0001\u000b\u0011BA'\u0011%\ty\u0006\u0001b\u0001\n\u0013\t\t\u0007\u0003\u0005\u0002j\u0001\u0001\u000b\u0011BA2\u0011%\tY\u0007\u0001b\u0001\n\u0013\ti\u0007\u0003\u0005\u0002v\u0001\u0001\u000b\u0011BA8\u0011%\t9\b\u0001b\u0001\n\u0013\tI\b\u0003\u0005\u0002\u0004\u0002\u0001\u000b\u0011BA>\u0011\u001d\t)\t\u0001C\u0005\u0003\u000fC\u0011\"a(\u0001\u0005\u0004%I!!)\t\u0011\u0005%\u0006\u0001)A\u0005\u0003GC1\"a+\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0002\"\"Y\u0011Q\u0016\u0001A\u0002\u0003\u0007I\u0011BAX\u0011-\tY\f\u0001a\u0001\u0002\u0003\u0006K!a)\t\u0013\u0005\u0015\u0007A1A\u0005\u0002\u0005\u001d\u0007\u0002CAk\u0001\u0001\u0006I!!3\t\u0015\u0005]\u0007A1A\u0005\u0002M\u000bI\u000e\u0003\u0005\u0002b\u0002\u0001\u000b\u0011BAn\u0011-\t\u0019\u000f\u0001a\u0001\u0002\u0004%I!!:\t\u0017\u0005E\b\u00011AA\u0002\u0013%\u00111\u001f\u0005\f\u0003o\u0004\u0001\u0019!A!B\u0013\t9\u000fC\u0005\u0002|\u0002\u0011\r\u0011\"\u0003\u0002~\"A!1\u0002\u0001!\u0002\u0013\ty\u0010C\u0005\u0003\u000e\u0001\u0011\r\u0011\"\u0003\u0003\u0010!A!1\u0005\u0001!\u0002\u0013\u0011\t\u0002\u0003\u0005\u0003&\u0001!\ta\u0015B\u0014\u0011\u001d\u0011i\u0003\u0001C\u0001\u0005_A!Ba\u000b\u0001\u0011\u000b\u0007I\u0011\tB\u001d\u0011\u001d\u0011i\u0005\u0001C!\u0005\u001fBqA!\u001a\u0001\t\u0003\u00119\u0007C\u0004\u0003x\u0001!\tE!\u001f\t\u000f\t}\u0004\u0001\"\u0003\u0003\u0002\"A!q\u0013\u0001\u0005\u0002M\u0013I\n\u0003\u0005\u0003$\u0002!\ta\u0015BS\u0011!\u0011y\u000b\u0001C\u0001'\nE\u0006\u0002\u0003Bx\u0001\u0011\u00051K!=\t\u0011\r\u001d\u0001\u0001\"\u0001T\u0007\u0013A\u0001ba\u0007\u0001\t\u0003\u00196Q\u0004\u0005\t\u0007k\u0001A\u0011A*\u00048!9!Q\u0010\u0001\u0005B\r5\u0003bBB)\u0001\u0011\u000531\u000b\u0005\b\u0007+\u0002A\u0011IB*\u0011\u001d\u00199\u0006\u0001C\u0005\u0007'Bqa!\u000e\u0001\t\u0003\u001aI\u0006C\u0004\u0004l\u0001!\te!\u001c\t\u000f\rU\u0004\u0001\"\u0011\u0004x!91q\u0011\u0001\u0005\n\r%eABBH\u0001\u0011\u0019\t\n\u0003\u0006\u0004\u001af\u0012\t\u0011)A\u0005\u00077Cq!!\b:\t\u0003\u0019I\u000bC\u0006\u00042f\u0002\r\u00111A\u0005\n\rM\u0006bCBds\u0001\u0007\t\u0019!C\u0005\u0007\u0013D1b!4:\u0001\u0004\u0005\t\u0015)\u0003\u00046\"91\u0011[\u001d\u0005\u0002\rM\u0007bBBms\u0011\u000531\u001c\u0005\b\u0007CLD\u0011IB*\u0011\u001d\u0019\u0019/\u000fC!\u0007K4aa!<\u0001\t\r=\bBCB|\u0007\n\u0005\t\u0015!\u0003\u0004z\"Q1\u0011T\"\u0003\u0002\u0003\u0006Iaa+\t\u0015\u0005e5I!A!\u0002\u0013\u00119\u000bC\u0004\u0002\u001e\r#\taa@\t\u000f\u0011%1\t\"\u0011\u0005\f!9AQC\"\u0005B\u0011]\u0001b\u0002C\u000e\u0007\u0012\u0005CQD\u0004\t\tK\u0019\u0006\u0012A*\u0005(\u00199!k\u0015E\u0001'\u0012%\u0002bBA\u000f\u0019\u0012\u0005A\u0011\u0007\u0005\u000b\tga%\u0019!C\u0001'\u0012U\u0002\u0002\u0003C!\u0019\u0002\u0006I\u0001b\u000e\t\u0015\u0011\rCJ1A\u0005\u0002M#)\u0005\u0003\u0005\u0005J1\u0003\u000b\u0011\u0002C$\u0005-qU\r\u001e;z%B\u001cWI\u001c<\u000b\u0005Q+\u0016!\u00028fiRL(B\u0001,X\u0003\r\u0011\bo\u0019\u0006\u00031f\u000bQa\u001d9be.T!AW.\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0016aA8sON\u0019\u0001A\u00182\u0011\u0005}\u0003W\"A+\n\u0005\u0005,&A\u0002*qG\u0016sg\u000f\u0005\u0002dM6\tAM\u0003\u0002f/\u0006A\u0011N\u001c;fe:\fG.\u0003\u0002hI\n9Aj\\4hS:<\u0017\u0001B2p]\u001a\u001c\u0001!F\u0001l!\taW.D\u0001X\u0013\tqwKA\u0005Ta\u0006\u00148nQ8oM\u0006)1m\u001c8gA\u00051\".\u0019<b'\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018J\\:uC:\u001cW\r\u0005\u0002sk6\t1O\u0003\u0002u/\u0006Q1/\u001a:jC2L'0\u001a:\n\u0005Y\u001c(A\u0006&bm\u0006\u001cVM]5bY&TXM]%ogR\fgnY3\u0002\t!|7\u000f\u001e\t\u0004s\u0006\u0015ab\u0001>\u0002\u0002A\u00111P`\u0007\u0002y*\u0011Q0[\u0001\u0007yI|w\u000e\u001e \u000b\u0003}\fQa]2bY\u0006L1!a\u0001\u007f\u0003\u0019\u0001&/\u001a3fM&!\u0011qAA\u0005\u0005\u0019\u0019FO]5oO*\u0019\u00111\u0001@\u0002\u001fM,7-\u001e:jifl\u0015M\\1hKJ\u00042\u0001\\A\b\u0013\r\t\tb\u0016\u0002\u0010'\u0016\u001cWO]5us6\u000bg.Y4fe\u0006qa.^7Vg\u0006\u0014G.Z\"pe\u0016\u001c\b\u0003BA\f\u00033i\u0011A`\u0005\u0004\u00037q(aA%oi\u00061A(\u001b8jiz\"B\"!\t\u0002&\u0005\u001d\u0012\u0011FA\u0016\u0003[\u00012!a\t\u0001\u001b\u0005\u0019\u0006\"\u00025\b\u0001\u0004Y\u0007\"\u00029\b\u0001\u0004\t\b\"B<\b\u0001\u0004A\bbBA\u0006\u000f\u0001\u0007\u0011Q\u0002\u0005\b\u0003'9\u0001\u0019AA\u000b\u0003\u0011\u0011x\u000e\\3\u0016\u0005\u0005M\u0002CBA\f\u0003k\tI$C\u0002\u00028y\u0014aa\u00149uS>t\u0007\u0003BA\u001e\u0003\u000bj!!!\u0010\u000b\t\u0005}\u0012\u0011I\u0001\u0005Y\u0006twM\u0003\u0002\u0002D\u0005!!.\u0019<b\u0013\u0011\t9!!\u0010\u0002\u000bI|G.\u001a\u0011\u0002\u001bQ\u0014\u0018M\\:q_J$8i\u001c8g+\t\ti\u0005\u0005\u0003\u0002P\u0005eSBAA)\u0015\u0011\t\u0019&!\u0016\u0002\tU$\u0018\u000e\u001c\u0006\u0004\u0003/:\u0016a\u00028fi^|'o[\u0005\u0005\u00037\n\tFA\u0007Ue\u0006t7\u000f]8si\u000e{gNZ\u0001\u000fiJ\fgn\u001d9peR\u001cuN\u001c4!\u0003)!\u0017n\u001d9bi\u000eDWM]\u000b\u0003\u0003G\u0002B!a\t\u0002f%\u0019\u0011qM*\u0003\u0015\u0011K7\u000f]1uG\",'/A\u0006eSN\u0004\u0018\r^2iKJ\u0004\u0013!D:ue\u0016\fW.T1oC\u001e,'/\u0006\u0002\u0002pA!\u00111EA9\u0013\r\t\u0019h\u0015\u0002\u0013\u001d\u0016$H/_*ue\u0016\fW.T1oC\u001e,'/\u0001\btiJ,\u0017-\\'b]\u0006<WM\u001d\u0011\u0002!Q\u0014\u0018M\\:q_J$8i\u001c8uKb$XCAA>!\u0011\ti(a \u000e\u0005\u0005U\u0013\u0002BAA\u0003+\u0012\u0001\u0003\u0016:b]N\u0004xN\u001d;D_:$X\r\u001f;\u0002#Q\u0014\u0018M\\:q_J$8i\u001c8uKb$\b%\u0001\fde\u0016\fG/Z\"mS\u0016tGOQ8piN$(/\u00199t)\t\tI\t\u0005\u0004\u0002\f\u0006=\u00151S\u0007\u0003\u0003\u001bSA!a\u0015\u0002B%!\u0011\u0011SAG\u0005\u0011a\u0015n\u001d;\u0011\t\u0005U\u00151T\u0007\u0003\u0003/SA!!'\u0002V\u000511\r\\5f]RLA!!(\u0002\u0018\nABK]1ogB|'\u000f^\"mS\u0016tGOQ8piN$(/\u00199\u0002\u001b\rd\u0017.\u001a8u\r\u0006\u001cGo\u001c:z+\t\t\u0019\u000b\u0005\u0003\u0002\u0016\u0006\u0015\u0016\u0002BAT\u0003/\u0013a\u0003\u0016:b]N\u0004xN\u001d;DY&,g\u000e\u001e$bGR|'/_\u0001\u000fG2LWM\u001c;GC\u000e$xN]=!\u0003M1\u0017\u000e\\3E_^tGn\\1e\r\u0006\u001cGo\u001c:z\u0003]1\u0017\u000e\\3E_^tGn\\1e\r\u0006\u001cGo\u001c:z?\u0012*\u0017\u000f\u0006\u0003\u00022\u0006]\u0006\u0003BA\f\u0003gK1!!.\u007f\u0005\u0011)f.\u001b;\t\u0013\u0005ef#!AA\u0002\u0005\r\u0016a\u0001=%c\u0005!b-\u001b7f\t><h\u000e\\8bI\u001a\u000b7\r^8ss\u0002B3aFA`!\u0011\t9\"!1\n\u0007\u0005\rgP\u0001\u0005w_2\fG/\u001b7f\u0003A!\u0018.\\3pkR\u001c6\r[3ek2,'/\u0006\u0002\u0002JB!\u00111ZAi\u001b\t\tiM\u0003\u0003\u0002P\u00065\u0015AC2p]\u000e,(O]3oi&!\u00111[Ag\u0005a\u00196\r[3ek2,G-\u0012=fGV$xN]*feZL7-Z\u0001\u0012i&lWm\\;u'\u000eDW\rZ;mKJ\u0004\u0013\u0001G2mS\u0016tGoQ8o]\u0016\u001cG/[8o\u000bb,7-\u001e;peV\u0011\u00111\u001c\t\u0005\u0003\u0017\fi.\u0003\u0003\u0002`\u00065'A\u0005+ie\u0016\fG\rU8pY\u0016CXmY;u_J\f\u0011d\u00197jK:$8i\u001c8oK\u000e$\u0018n\u001c8Fq\u0016\u001cW\u000f^8sA\u000511/\u001a:wKJ,\"!a:\u0011\t\u0005%\u0018Q^\u0007\u0003\u0003WTA!a9\u0002V%!\u0011q^Av\u0005=!&/\u00198ta>\u0014HoU3sm\u0016\u0014\u0018AC:feZ,'o\u0018\u0013fcR!\u0011\u0011WA{\u0011%\tI,HA\u0001\u0002\u0004\t9/A\u0004tKJ4XM\u001d\u0011)\u0007y\ty,A\u0004ti>\u0004\b/\u001a3\u0016\u0005\u0005}\b\u0003\u0002B\u0001\u0005\u000fi!Aa\u0001\u000b\t\t\u0015\u0011QZ\u0001\u0007CR|W.[2\n\t\t%!1\u0001\u0002\u000e\u0003R|W.[2C_>dW-\u00198\u0002\u0011M$x\u000e\u001d9fI\u0002\n\u0001b\\;uE>DXm]\u000b\u0003\u0005#\u0001\u0002\"a3\u0003\u0014\t]!QD\u0005\u0005\u0005+\tiMA\tD_:\u001cWO\u001d:f]RD\u0015m\u001d5NCB\u00042a\u0018B\r\u0013\r\u0011Y\"\u0016\u0002\u000b%B\u001c\u0017\t\u001a3sKN\u001c\b\u0003BA\u0012\u0005?I1A!\tT\u0005\u0019yU\u000f\u001e2pq\u0006Iq.\u001e;c_b,7\u000fI\u0001\re\u0016lwN^3PkR\u0014w\u000e\u001f\u000b\u0005\u0003c\u0013I\u0003C\u0004\u0003,\r\u0002\rAa\u0006\u0002\u000f\u0005$GM]3tg\u0006Y1\u000f^1siN+'O^3s)\u0019\t\tL!\r\u00036!1!1\u0007\u0013A\u0002a\f1BY5oI\u0006#GM]3tg\"9!q\u0007\u0013A\u0002\u0005U\u0011\u0001\u00029peR,\"Aa\u0006)\u0007\u0015\u0012i\u0004\u0005\u0003\u0003@\t%SB\u0001B!\u0015\u0011\u0011\u0019E!\u0012\u0002\u0015\u0005tgn\u001c;bi&|gN\u0003\u0002\u0003H\u0005)!.\u0019<bq&!!1\nB!\u0005!qU\u000f\u001c7bE2,\u0017!D:fiV\u0004XI\u001c3q_&tG\u000f\u0006\u0004\u0003R\t]#1\f\t\u0004?\nM\u0013b\u0001B++\nq!\u000b]2F]\u0012\u0004x.\u001b8u%\u00164\u0007B\u0002B-M\u0001\u0007\u00010\u0001\u0003oC6,\u0007b\u0002B/M\u0001\u0007!qL\u0001\tK:$\u0007o\\5oiB\u0019qL!\u0019\n\u0007\t\rTKA\u0006Sa\u000e,e\u000e\u001a9pS:$\u0018AG1ts:\u001c7+\u001a;va\u0016sG\r]8j]R\u0014VM\u001a\"z+JKE\u0003\u0002B5\u0005g\u0002bAa\u001b\u0003p\tESB\u0001B7\u0015\r\tyM`\u0005\u0005\u0005c\u0012iG\u0001\u0004GkR,(/\u001a\u0005\u0007\u0005k:\u0003\u0019\u0001=\u0002\u0007U\u0014\u0018.\u0001\u0003ti>\u0004H\u0003BAY\u0005wBqA! )\u0001\u0004\u0011\t&A\u0006f]\u0012\u0004x.\u001b8u%\u00164\u0017\u0001\u00049pgR$vnT;uE>DHCBAY\u0005\u0007\u0013i\tC\u0004\u0003\u0006&\u0002\rAa\"\u0002\u0011I,7-Z5wKJ\u0004B!a\t\u0003\n&\u0019!1R*\u0003'9+G\u000f^=Sa\u000e,e\u000e\u001a9pS:$(+\u001a4\t\u000f\t=\u0015\u00061\u0001\u0003\u0012\u00069Q.Z:tC\u001e,\u0007\u0003BA\u0012\u0005'K1A!&T\u00055yU\u000f\u001e2pq6+7o]1hK\u0006!1/\u001a8e)\u0011\t\tLa'\t\u000f\t=%\u00061\u0001\u0003\u001eB!\u00111\u0005BP\u0013\r\u0011\tk\u0015\u0002\u000f%\u0016\fX/Z:u\u001b\u0016\u001c8/Y4f\u00031\u0019'/Z1uK\u000ec\u0017.\u001a8u)\u0011\u00119K!,\u0011\t\u0005U%\u0011V\u0005\u0005\u0005W\u000b9JA\bUe\u0006t7\u000f]8si\u000ec\u0017.\u001a8u\u0011\u001d\u0011Yc\u000ba\u0001\u0005/\tA\"Y:l\u0003\n|'\u000f^1cY\u0016,BAa-\u0003BR1!Q\u0017Br\u0005K$BAa.\u0003TB)qL!/\u0003>&\u0019!1X+\u0003%\u0005\u0013wN\u001d;bE2,'\u000b]2GkR,(/\u001a\t\u0005\u0005\u007f\u0013\t\r\u0004\u0001\u0005\u000f\t\rGF1\u0001\u0003F\n\tA+\u0005\u0003\u0003H\n5\u0007\u0003BA\f\u0005\u0013L1Aa3\u007f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004B!a\u0006\u0003P&\u0019!\u0011\u001b@\u0003\u0007\u0005s\u0017\u0010C\u0005\u0003V2\n\t\u0011q\u0001\u0003X\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\r\te'q\u001cB_\u001b\t\u0011YNC\u0002\u0003^z\fqA]3gY\u0016\u001cG/\u0003\u0003\u0003b\nm'\u0001C\"mCN\u001cH+Y4\t\u000f\t=E\u00061\u0001\u0003\u001e\"9!q\u001d\u0017A\u0002\t%\u0018a\u0002;j[\u0016|W\u000f\u001e\t\u0004?\n-\u0018b\u0001Bw+\nQ!\u000b]2US6,w.\u001e;\u0002\u0007\u0005\u001c8.\u0006\u0003\u0003t\nmHC\u0002B{\u0007\u0007\u0019)\u0001\u0006\u0003\u0003x\nu\bC\u0002B6\u0005_\u0012I\u0010\u0005\u0003\u0003@\nmHa\u0002Bb[\t\u0007!Q\u0019\u0005\n\u0005\u007fl\u0013\u0011!a\u0002\u0007\u0003\t!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\u0011INa8\u0003z\"9!qR\u0017A\u0002\tu\u0005b\u0002Bt[\u0001\u0007!\u0011^\u0001\ng\u0016\u0014\u0018.\u00197ju\u0016$Baa\u0003\u0004\u0018A!1QBB\n\u001b\t\u0019yA\u0003\u0003\u0004\u0012\u0005\u0005\u0013a\u00018j_&!1QCB\b\u0005)\u0011\u0015\u0010^3Ck\u001a4WM\u001d\u0005\b\u00073q\u0003\u0019\u0001Bg\u0003\u001d\u0019wN\u001c;f]R\fqb]3sS\u0006d\u0017N_3TiJ,\u0017-\u001c\u000b\u0005\u0007?\u0019)\u0003E\u0002s\u0007CI1aa\tt\u0005M\u0019VM]5bY&T\u0018\r^5p]N#(/Z1n\u0011\u001d\u00199c\fa\u0001\u0007S\t1a\\;u!\u0011\u0019Yc!\r\u000e\u0005\r5\"\u0002BB\u0018\u0003\u0003\n!![8\n\t\rM2Q\u0006\u0002\r\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\fI\u0016\u001cXM]5bY&TX-\u0006\u0003\u0004:\r}BCBB\u001e\u0007\u000f\u001aI\u0005\u0006\u0003\u0004>\r\u0005\u0003\u0003\u0002B`\u0007\u007f!qAa11\u0005\u0004\u0011)\rC\u0005\u0004DA\n\t\u0011q\u0001\u0004F\u0005QQM^5eK:\u001cW\rJ\u001a\u0011\r\te'q\\B\u001f\u0011\u001d\tI\n\ra\u0001\u0005OCqaa\u00131\u0001\u0004\u0019Y!A\u0003csR,7\u000f\u0006\u0003\u0003R\r=\u0003b\u0002B/c\u0001\u0007!qL\u0001\tg\",H\u000fZ8x]R\u0011\u0011\u0011W\u0001\u0011C^\f\u0017\u000e\u001e+fe6Lg.\u0019;j_:\fqa\u00197fC:,\b/\u0006\u0003\u0004\\\r}C\u0003BB/\u0007C\u0002BAa0\u0004`\u00119!1Y\u001bC\u0002\t\u0015\u0007bBB2k\u0001\u00071QM\u0001\u0016I\u0016\u001cXM]5bY&T\u0018\r^5p]\u0006\u001bG/[8o!\u0019\t9ba\u001a\u0004^%\u00191\u0011\u000e@\u0003\u0013\u0019+hn\u0019;j_:\u0004\u0014A\u00034jY\u0016\u001cVM\u001d<feV\u00111q\u000e\t\u0004?\u000eE\u0014bAB:+\n\u0001\"\u000b]2F]Z4\u0015\u000e\\3TKJ4XM]\u0001\f_B,gn\u00115b]:,G\u000e\u0006\u0003\u0004z\r\u0015\u0005\u0003BB>\u0007\u0003k!a! \u000b\t\r}4qB\u0001\tG\"\fgN\\3mg&!11QB?\u0005M\u0011V-\u00193bE2,')\u001f;f\u0007\"\fgN\\3m\u0011\u0019\u0011)h\u000ea\u0001q\u0006qAm\\<oY>\fGm\u00117jK:$HC\u0002BT\u0007\u0017\u001bi\tC\u0003xq\u0001\u0007\u0001\u0010C\u0004\u00038a\u0002\r!!\u0006\u0003'\u0019KG.\u001a#po:dw.\u00193DQ\u0006tg.\u001a7\u0014\u000be\u001a\u0019j!\u001f\u0011\t\u0005m2QS\u0005\u0005\u0007/\u000biD\u0001\u0004PE*,7\r^\u0001\u0007g>,(oY3\u0011\t\ru51\u0015\b\u0005\u0007w\u001ay*\u0003\u0003\u0004\"\u000eu\u0014\u0001\u0002)ja\u0016LAa!*\u0004(\ni1k\\;sG\u0016\u001c\u0005.\u00198oK2TAa!)\u0004~Q!11VBX!\r\u0019i+O\u0007\u0002\u0001!91\u0011T\u001eA\u0002\rm\u0015!B3se>\u0014XCAB[!\u0011\u00199l!1\u000f\t\re6Q\u0018\b\u0004w\u000em\u0016\"A@\n\u0007\r}f0A\u0004qC\u000e\\\u0017mZ3\n\t\r\r7Q\u0019\u0002\n)\"\u0014xn^1cY\u0016T1aa0\u007f\u0003%)'O]8s?\u0012*\u0017\u000f\u0006\u0003\u00022\u000e-\u0007\"CA]{\u0005\u0005\t\u0019AB[\u0003\u0019)'O]8sA!\u001aa(a0\u0002\u0011M,G/\u0012:s_J$B!!-\u0004V\"91q[ A\u0002\rU\u0016!A3\u0002\tI,\u0017\r\u001a\u000b\u0005\u0003+\u0019i\u000eC\u0004\u0004`\u0002\u0003\raa\u0003\u0002\u0007\u0011\u001cH/A\u0003dY>\u001cX-\u0001\u0004jg>\u0003XM\u001c\u000b\u0003\u0007O\u0004B!a\u0006\u0004j&\u001911\u001e@\u0003\u000f\t{w\u000e\\3b]\n!b)\u001b7f\t><h\u000e\\8bI\u000e\u000bG\u000e\u001c2bG.\u001cRaQBJ\u0007c\u0004B!!&\u0004t&!1Q_AL\u00059\u0019FO]3b[\u000e\u000bG\u000e\u001c2bG.\fAa]5oWB!11PB~\u0013\u0011\u0019ip! \u0003']\u0013\u0018\u000e^1cY\u0016\u0014\u0015\u0010^3DQ\u0006tg.\u001a7\u0015\u0011\u0011\u0005A1\u0001C\u0003\t\u000f\u00012a!,D\u0011\u001d\u00199p\u0012a\u0001\u0007sDqa!'H\u0001\u0004\u0019Y\u000bC\u0004\u0002\u001a\u001e\u0003\rAa*\u0002\r=tG)\u0019;b)\u0019\t\t\f\"\u0004\u0005\u0012!1Aq\u0002%A\u0002a\f\u0001b\u001d;sK\u0006l\u0017\n\u001a\u0005\b\t'A\u0005\u0019AB\u0006\u0003\r\u0011WOZ\u0001\u000b_:\u001cu.\u001c9mKR,G\u0003BAY\t3Aa\u0001b\u0004J\u0001\u0004A\u0018!C8o\r\u0006LG.\u001e:f)\u0019\t\t\fb\b\u0005\"!1Aq\u0002&A\u0002aDq\u0001b\tK\u0001\u0004\u0019),A\u0003dCV\u001cX-A\u0006OKR$\u0018P\u00159d\u000b:4\bcAA\u0012\u0019N!A\nb\u000bc!\u0011\t9\u0002\"\f\n\u0007\u0011=bP\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\tO\t!bY;se\u0016tG/\u00128w+\t!9\u0004\u0005\u0004\u0005:\u0011u\u0012\u0011E\u0007\u0003\twQ1!a\u0015\u007f\u0013\u0011!y\u0004b\u000f\u0003\u001f\u0011Kh.Y7jGZ\u000b'/[1cY\u0016\f1bY;se\u0016tG/\u00128wA\u0005i1-\u001e:sK:$8\t\\5f]R,\"\u0001b\u0012\u0011\r\u0011eBQ\bBT\u00039\u0019WO\u001d:f]R\u001cE.[3oi\u0002\u0002"
)
public class NettyRpcEnv extends RpcEnv implements Logging {
   @Nullable
   private RpcAddress address;
   private final SparkConf conf;
   private final JavaSerializerInstance javaSerializerInstance;
   private String host;
   private final SecurityManager securityManager;
   private final int numUsableCores;
   private final Option role;
   private final TransportConf transportConf;
   private final Dispatcher dispatcher;
   private final NettyStreamManager streamManager;
   private final TransportContext transportContext;
   private final TransportClientFactory clientFactory;
   private volatile TransportClientFactory fileDownloadFactory;
   private final ScheduledExecutorService timeoutScheduler;
   private final ThreadPoolExecutor clientConnectionExecutor;
   private volatile TransportServer server;
   private final AtomicBoolean stopped;
   private final ConcurrentHashMap outboxes;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

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

   public SparkConf conf() {
      return this.conf;
   }

   public Option role() {
      return this.role;
   }

   public TransportConf transportConf() {
      return this.transportConf;
   }

   private Dispatcher dispatcher() {
      return this.dispatcher;
   }

   private NettyStreamManager streamManager() {
      return this.streamManager;
   }

   private TransportContext transportContext() {
      return this.transportContext;
   }

   private List createClientBootstraps() {
      return this.securityManager.isAuthenticationEnabled() ? Arrays.asList((Object[])(new TransportClientBootstrap[]{new AuthClientBootstrap(this.transportConf(), this.securityManager.getSaslUser(), this.securityManager)})) : Collections.emptyList();
   }

   private TransportClientFactory clientFactory() {
      return this.clientFactory;
   }

   private TransportClientFactory fileDownloadFactory() {
      return this.fileDownloadFactory;
   }

   private void fileDownloadFactory_$eq(final TransportClientFactory x$1) {
      this.fileDownloadFactory = x$1;
   }

   public ScheduledExecutorService timeoutScheduler() {
      return this.timeoutScheduler;
   }

   public ThreadPoolExecutor clientConnectionExecutor() {
      return this.clientConnectionExecutor;
   }

   private TransportServer server() {
      return this.server;
   }

   private void server_$eq(final TransportServer x$1) {
      this.server = x$1;
   }

   private AtomicBoolean stopped() {
      return this.stopped;
   }

   private ConcurrentHashMap outboxes() {
      return this.outboxes;
   }

   public void removeOutbox(final RpcAddress address) {
      Outbox outbox = (Outbox)this.outboxes().remove(address);
      if (outbox != null) {
         outbox.stop();
      }
   }

   public void startServer(final String bindAddress, final int port) {
      List bootstraps = this.securityManager.isAuthenticationEnabled() ? Arrays.asList((Object[])(new TransportServerBootstrap[]{new AuthServerBootstrap(this.transportConf(), this.securityManager)})) : Collections.emptyList();
      this.server_$eq(this.transportContext().createServer(bindAddress, port, bootstraps));
      this.dispatcher().registerRpcEndpoint(RpcEndpointVerifier$.MODULE$.NAME(), new RpcEndpointVerifier(this, this.dispatcher()));
   }

   private RpcAddress address$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.address = this.server() != null ? RpcAddress$.MODULE$.apply(this.host, this.server().getPort()) : null;
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      this.host = null;
      return this.address;
   }

   public RpcAddress address() {
      return !this.bitmap$0 ? this.address$lzycompute() : this.address;
   }

   public RpcEndpointRef setupEndpoint(final String name, final RpcEndpoint endpoint) {
      return this.dispatcher().registerRpcEndpoint(name, endpoint);
   }

   public Future asyncSetupEndpointRefByURI(final String uri) {
      RpcEndpointAddress addr = RpcEndpointAddress$.MODULE$.apply(uri);
      NettyRpcEndpointRef endpointRef = new NettyRpcEndpointRef(this.conf(), addr, this);
      NettyRpcEndpointRef verifier = new NettyRpcEndpointRef(this.conf(), new RpcEndpointAddress(addr.rpcAddress(), RpcEndpointVerifier$.MODULE$.NAME()), this);
      return verifier.ask(new RpcEndpointVerifier.CheckExistence(endpointRef.name()), .MODULE$.Boolean()).flatMap((find) -> $anonfun$asyncSetupEndpointRefByURI$1(endpointRef, uri, BoxesRunTime.unboxToBoolean(find)), ThreadUtils$.MODULE$.sameThread());
   }

   public void stop(final RpcEndpointRef endpointRef) {
      scala.Predef..MODULE$.require(endpointRef instanceof NettyRpcEndpointRef);
      this.dispatcher().stop(endpointRef);
   }

   private void postToOutbox(final NettyRpcEndpointRef receiver, final OutboxMessage message) {
      if (receiver.client() != null) {
         message.sendWith(receiver.client());
      } else {
         scala.Predef..MODULE$.require(receiver.address() != null, () -> "Cannot send message to client endpoint with no listen address.");
         Outbox outbox = (Outbox)this.outboxes().get(receiver.address());
         Outbox var10000;
         if (outbox == null) {
            Outbox newOutbox = new Outbox(this, receiver.address());
            Outbox oldOutbox = (Outbox)this.outboxes().putIfAbsent(receiver.address(), newOutbox);
            var10000 = oldOutbox == null ? newOutbox : oldOutbox;
         } else {
            var10000 = outbox;
         }

         Outbox targetOutbox = var10000;
         if (this.stopped().get()) {
            this.outboxes().remove(receiver.address());
            targetOutbox.stop();
         } else {
            targetOutbox.send(message);
         }
      }
   }

   public void send(final RequestMessage message) {
      label23: {
         RpcAddress remoteAddr = message.receiver().address();
         RpcAddress var3 = this.address();
         if (remoteAddr == null) {
            if (var3 != null) {
               break label23;
            }
         } else if (!remoteAddr.equals(var3)) {
            break label23;
         }

         try {
            this.dispatcher().postOneWayMessage(message);
         } catch (RpcEnvStoppedException var5) {
            this.logDebug((Function0)(() -> var5.getMessage()));
         }

         return;
      }

      this.postToOutbox(message.receiver(), new OneWayOutboxMessage(message.serialize(this)));
   }

   public TransportClient createClient(final RpcAddress address) {
      return this.clientFactory().createClient(address.host(), address.port());
   }

   public AbortableRpcFuture askAbortable(final RequestMessage message, final RpcTimeout timeout, final ClassTag evidence$1) {
      Promise promise = scala.concurrent.Promise..MODULE$.apply();
      RpcAddress remoteAddr = message.receiver().address();
      ObjectRef rpcMsg = ObjectRef.create(scala.None..MODULE$);

      try {
         label29: {
            label28: {
               RpcAddress var8 = this.address();
               if (remoteAddr == null) {
                  if (var8 == null) {
                     break label28;
                  }
               } else if (remoteAddr.equals(var8)) {
                  break label28;
               }

               RpcOutboxMessage rpcMessage = new RpcOutboxMessage(message.serialize(this), (e) -> {
                  $anonfun$askAbortable$6(this, promise, e);
                  return BoxedUnit.UNIT;
               }, (client, response) -> {
                  $anonfun$askAbortable$7(this, promise, client, response);
                  return BoxedUnit.UNIT;
               });
               rpcMsg.elem = scala.Option..MODULE$.apply(rpcMessage);
               this.postToOutbox(message.receiver(), rpcMessage);
               promise.future().failed().foreach((x0$2) -> {
                  $anonfun$askAbortable$8(rpcMessage, x0$2);
                  return BoxedUnit.UNIT;
               }, ThreadUtils$.MODULE$.sameThread());
               break label29;
            }

            Promise p = scala.concurrent.Promise..MODULE$.apply();
            p.future().onComplete((x0$1) -> {
               $anonfun$askAbortable$5(this, promise, x0$1);
               return BoxedUnit.UNIT;
            }, ThreadUtils$.MODULE$.sameThread());
            this.dispatcher().postLocalMessage(message, p);
         }

         ScheduledFuture timeoutCancelable = this.timeoutScheduler().schedule(new Runnable(remoteAddr, message, timeout, promise) {
            // $FF: synthetic field
            private final NettyRpcEnv $outer;
            private final RpcAddress remoteAddr$1;
            private final RequestMessage message$1;
            private final RpcTimeout timeout$1;
            private final Promise promise$1;

            public void run() {
               Object remoteRecAddr = this.remoteAddr$1 == null ? scala.util.Try..MODULE$.apply(() -> this.message$1.receiver().client().getChannel().remoteAddress()).toOption().orNull(scala..less.colon.less..MODULE$.refl()) : this.remoteAddr$1;
               this.$outer.org$apache$spark$rpc$netty$NettyRpcEnv$$onFailure$1(new TimeoutException("Cannot receive any reply from " + remoteRecAddr + " in " + this.timeout$1.duration()), this.promise$1);
            }

            public {
               if (NettyRpcEnv.this == null) {
                  throw null;
               } else {
                  this.$outer = NettyRpcEnv.this;
                  this.remoteAddr$1 = remoteAddr$1;
                  this.message$1 = message$1;
                  this.timeout$1 = timeout$1;
                  this.promise$1 = promise$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         }, timeout.duration().toNanos(), TimeUnit.NANOSECONDS);
         promise.future().onComplete((v) -> BoxesRunTime.boxToBoolean($anonfun$askAbortable$9(timeoutCancelable, v)), ThreadUtils$.MODULE$.sameThread());
      } catch (Throwable var15) {
         if (var15 == null || !scala.util.control.NonFatal..MODULE$.apply(var15)) {
            throw var15;
         }

         this.org$apache$spark$rpc$netty$NettyRpcEnv$$onFailure$1(var15, promise);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return new AbortableRpcFuture(promise.future().mapTo(evidence$1).recover(timeout.addMessageIfTimeout(), ThreadUtils$.MODULE$.sameThread()), (t) -> {
         $anonfun$askAbortable$10(this, rpcMsg, promise, t);
         return BoxedUnit.UNIT;
      }, evidence$1);
   }

   public Future ask(final RequestMessage message, final RpcTimeout timeout, final ClassTag evidence$2) {
      return this.askAbortable(message, timeout, evidence$2).future();
   }

   public ByteBuffer serialize(final Object content) {
      return this.javaSerializerInstance.serialize(content, .MODULE$.Any());
   }

   public SerializationStream serializeStream(final OutputStream out) {
      return this.javaSerializerInstance.serializeStream(out);
   }

   public Object deserialize(final TransportClient client, final ByteBuffer bytes, final ClassTag evidence$3) {
      return NettyRpcEnv$.MODULE$.currentClient().withValue(client, () -> this.deserialize(() -> this.javaSerializerInstance.deserialize(bytes, evidence$3)));
   }

   public RpcEndpointRef endpointRef(final RpcEndpoint endpoint) {
      return this.dispatcher().getRpcEndpointRef(endpoint);
   }

   public void shutdown() {
      this.cleanup();
   }

   public void awaitTermination() {
      this.dispatcher().awaitTermination();
   }

   private void cleanup() {
      if (this.stopped().compareAndSet(false, true)) {
         for(Outbox outbox : this.outboxes().values()) {
            this.outboxes().remove(outbox.address());
            outbox.stop();
         }

         if (this.timeoutScheduler() != null) {
            this.timeoutScheduler().shutdownNow();
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         if (this.dispatcher() != null) {
            this.dispatcher().stop();
         }

         if (this.server() != null) {
            this.server().close();
         }

         if (this.clientFactory() != null) {
            this.clientFactory().close();
         }

         if (this.clientConnectionExecutor() != null) {
            this.clientConnectionExecutor().shutdownNow();
         } else {
            BoxedUnit var3 = BoxedUnit.UNIT;
         }

         if (this.fileDownloadFactory() != null) {
            this.fileDownloadFactory().close();
         }

         if (this.transportContext() != null) {
            this.transportContext().close();
         }
      }
   }

   public Object deserialize(final Function0 deserializationAction) {
      return NettyRpcEnv$.MODULE$.currentEnv().withValue(this, deserializationAction);
   }

   public RpcEnvFileServer fileServer() {
      return this.streamManager();
   }

   public ReadableByteChannel openChannel(final String uri) {
      URI parsedUri = new URI(uri);
      scala.Predef..MODULE$.require(parsedUri.getHost() != null, () -> "Host name must be defined.");
      scala.Predef..MODULE$.require(parsedUri.getPort() > 0, () -> "Port must be defined.");
      scala.Predef..MODULE$.require(parsedUri.getPath() != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(parsedUri.getPath())), () -> "Path must be defined.");
      Pipe pipe = Pipe.open();
      FileDownloadChannel source = new FileDownloadChannel(pipe.source());
      Function0 x$1 = () -> {
         TransportClient client = this.downloadClient(parsedUri.getHost(), parsedUri.getPort());
         FileDownloadCallback callback = this.new FileDownloadCallback(pipe.sink(), source, client);
         client.stream(parsedUri.getPath(), callback);
      };
      Function0 x$2 = () -> {
         pipe.sink().close();
         source.close();
      };
      Function0 x$3 = () -> Utils$.MODULE$.tryWithSafeFinallyAndFailureCallbacks$default$3(x$1);
      Utils$.MODULE$.tryWithSafeFinallyAndFailureCallbacks(x$1, x$2, x$3);
      return source;
   }

   private TransportClient downloadClient(final String host, final int port) {
      if (this.fileDownloadFactory() == null) {
         synchronized(this){}

         try {
            if (this.fileDownloadFactory() == null) {
               String module = "files";
               String prefix = "spark.rpc.io.";
               SparkConf clone = this.conf().clone();
               scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.conf().getAll()), (x0$1) -> {
                  if (x0$1 != null) {
                     String key = (String)x0$1._1();
                     String value = (String)x0$1._2();
                     if (key.startsWith(prefix)) {
                        String opt = key.substring(prefix.length());
                        return clone.setIfMissing("spark." + module + ".io." + opt, value);
                     } else {
                        return BoxedUnit.UNIT;
                     }
                  } else {
                     throw new MatchError(x0$1);
                  }
               });
               int ioThreads = clone.getInt("spark.files.io.threads", 1);
               Some x$4 = new Some(this.securityManager.getRpcSSLOptions());
               Option x$5 = SparkTransportConf$.MODULE$.fromSparkConf$default$4();
               TransportConf downloadConf = SparkTransportConf$.MODULE$.fromSparkConf(clone, module, ioThreads, x$5, x$4);
               TransportContext downloadContext = new TransportContext(downloadConf, new NoOpRpcHandler(), true);
               this.fileDownloadFactory_$eq(downloadContext.createClientFactory(this.createClientBootstraps()));
            }
         } catch (Throwable var16) {
            throw var16;
         }
      }

      return this.fileDownloadFactory().createClient(host, port);
   }

   // $FF: synthetic method
   public static final Future $anonfun$asyncSetupEndpointRefByURI$1(final NettyRpcEndpointRef endpointRef$1, final String uri$1, final boolean find) {
      return find ? scala.concurrent.Future..MODULE$.successful(endpointRef$1) : scala.concurrent.Future..MODULE$.failed(new RpcEndpointNotFoundException(uri$1));
   }

   public final void org$apache$spark$rpc$netty$NettyRpcEnv$$onFailure$1(final Throwable e, final Promise promise$1) {
      if (!promise$1.tryFailure(e)) {
         if (e instanceof RpcEnvStoppedException) {
            RpcEnvStoppedException var5 = (RpcEnvStoppedException)e;
            this.logDebug((Function0)(() -> "Ignored failure: " + var5));
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignored failure: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, e)})))));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }
   }

   private final void onSuccess$1(final Object reply, final Promise promise$1) {
      if (reply instanceof RpcFailure var5) {
         Throwable e = var5.e();
         this.org$apache$spark$rpc$netty$NettyRpcEnv$$onFailure$1(e, promise$1);
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else if (!promise$1.trySuccess(reply)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignored message: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, reply)})))));
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$askAbortable$4(final RpcOutboxMessage x$3) {
      x$3.onAbort();
   }

   private final void onAbort$1(final Throwable t, final ObjectRef rpcMsg$1, final Promise promise$1) {
      this.org$apache$spark$rpc$netty$NettyRpcEnv$$onFailure$1(t, promise$1);
      ((Option)rpcMsg$1.elem).foreach((x$3) -> {
         $anonfun$askAbortable$4(x$3);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$askAbortable$5(final NettyRpcEnv $this, final Promise promise$1, final Try x0$1) {
      if (x0$1 instanceof Success var5) {
         Object response = var5.value();
         $this.onSuccess$1(response, promise$1);
         BoxedUnit var9 = BoxedUnit.UNIT;
      } else if (x0$1 instanceof Failure var7) {
         Throwable e = var7.exception();
         $this.org$apache$spark$rpc$netty$NettyRpcEnv$$onFailure$1(e, promise$1);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$askAbortable$6(final NettyRpcEnv $this, final Promise promise$1, final Throwable e) {
      $this.org$apache$spark$rpc$netty$NettyRpcEnv$$onFailure$1(e, promise$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$askAbortable$7(final NettyRpcEnv $this, final Promise promise$1, final TransportClient client, final ByteBuffer response) {
      $this.onSuccess$1($this.deserialize(client, response, .MODULE$.Any()), promise$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$askAbortable$8(final RpcOutboxMessage rpcMessage$1, final Throwable x0$2) {
      if (x0$2 instanceof TimeoutException) {
         rpcMessage$1.onTimeout();
         BoxedUnit var4 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$askAbortable$9(final ScheduledFuture timeoutCancelable$1, final Try v) {
      return timeoutCancelable$1.cancel(true);
   }

   // $FF: synthetic method
   public static final void $anonfun$askAbortable$10(final NettyRpcEnv $this, final ObjectRef rpcMsg$1, final Promise promise$1, final Throwable t) {
      $this.onAbort$1(t, rpcMsg$1, promise$1);
   }

   public NettyRpcEnv(final SparkConf conf, final JavaSerializerInstance javaSerializerInstance, final String host, final SecurityManager securityManager, final int numUsableCores) {
      this.conf = conf;
      this.javaSerializerInstance = javaSerializerInstance;
      this.host = host;
      this.securityManager = securityManager;
      this.numUsableCores = numUsableCores;
      super(conf);
      Logging.$init$(this);
      this.role = ((Option)conf.get((ConfigEntry)package$.MODULE$.EXECUTOR_ID())).map((id) -> {
         String var1 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
         if (id == null) {
            if (var1 == null) {
               return "driver";
            }
         } else if (id.equals(var1)) {
            return "driver";
         }

         return "executor";
      });
      this.transportConf = SparkTransportConf$.MODULE$.fromSparkConf(conf.clone().set((ConfigEntry)Network$.MODULE$.RPC_IO_NUM_CONNECTIONS_PER_PEER(), (Object)BoxesRunTime.boxToInteger(1)), "rpc", BoxesRunTime.unboxToInt(((Option)conf.get((ConfigEntry)Network$.MODULE$.RPC_IO_THREADS())).getOrElse((JFunction0.mcI.sp)() -> this.numUsableCores)), this.role(), new Some(securityManager.getRpcSSLOptions()));
      this.dispatcher = new Dispatcher(this, numUsableCores);
      this.streamManager = new NettyStreamManager(this);
      this.transportContext = new TransportContext(this.transportConf(), new NettyRpcHandler(this.dispatcher(), this, this.streamManager()));
      this.clientFactory = this.transportContext().createClientFactory(this.createClientBootstraps());
      this.timeoutScheduler = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("netty-rpc-env-timeout");
      this.clientConnectionExecutor = ThreadUtils$.MODULE$.newDaemonCachedThreadPool("netty-rpc-connection", BoxesRunTime.unboxToInt(conf.get(Network$.MODULE$.RPC_CONNECT_THREADS())), ThreadUtils$.MODULE$.newDaemonCachedThreadPool$default$3());
      this.stopped = new AtomicBoolean(false);
      this.outboxes = new ConcurrentHashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class FileDownloadChannel implements ReadableByteChannel {
      private final Pipe.SourceChannel source;
      private volatile Throwable error;
      // $FF: synthetic field
      public final NettyRpcEnv $outer;

      private Throwable error() {
         return this.error;
      }

      private void error_$eq(final Throwable x$1) {
         this.error = x$1;
      }

      public void setError(final Throwable e) {
         this.error_$eq(e);
      }

      public int read(final ByteBuffer dst) {
         Try var3 = scala.util.Try..MODULE$.apply((JFunction0.mcI.sp)() -> this.source.read(dst));
         if (this.error() != null) {
            throw this.error();
         } else if (var3 instanceof Success) {
            Success var4 = (Success)var3;
            int bytesRead = BoxesRunTime.unboxToInt(var4.value());
            return bytesRead;
         } else if (var3 instanceof Failure) {
            Failure var6 = (Failure)var3;
            Throwable readErr = var6.exception();
            throw readErr;
         } else {
            throw new MatchError(var3);
         }
      }

      public void close() {
         this.source.close();
      }

      public boolean isOpen() {
         return this.source.isOpen();
      }

      // $FF: synthetic method
      public NettyRpcEnv org$apache$spark$rpc$netty$NettyRpcEnv$FileDownloadChannel$$$outer() {
         return this.$outer;
      }

      public FileDownloadChannel(final Pipe.SourceChannel source) {
         this.source = source;
         if (NettyRpcEnv.this == null) {
            throw null;
         } else {
            this.$outer = NettyRpcEnv.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private class FileDownloadCallback implements StreamCallback {
      private final WritableByteChannel sink;
      private final FileDownloadChannel source;
      // $FF: synthetic field
      public final NettyRpcEnv $outer;

      public void onData(final String streamId, final ByteBuffer buf) {
         while(buf.remaining() > 0) {
            this.sink.write(buf);
         }

      }

      public void onComplete(final String streamId) {
         this.sink.close();
      }

      public void onFailure(final String streamId, final Throwable cause) {
         this.source.setError(cause);
         this.sink.close();
      }

      // $FF: synthetic method
      public NettyRpcEnv org$apache$spark$rpc$netty$NettyRpcEnv$FileDownloadCallback$$$outer() {
         return this.$outer;
      }

      public FileDownloadCallback(final WritableByteChannel sink, final FileDownloadChannel source, final TransportClient client) {
         this.sink = sink;
         this.source = source;
         if (NettyRpcEnv.this == null) {
            throw null;
         } else {
            this.$outer = NettyRpcEnv.this;
            super();
         }
      }
   }
}
