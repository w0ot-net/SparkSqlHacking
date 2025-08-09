package org.apache.spark.sql;

import java.io.Closeable;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.annotation.ClassicOnly;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.catalog.Catalog;
import org.apache.spark.sql.internal.SessionState;
import org.apache.spark.sql.internal.SharedState;
import org.apache.spark.sql.sources.BaseRelation;
import org.apache.spark.sql.streaming.DataStreamReader;
import org.apache.spark.sql.streaming.StreamingQueryManager;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.util.ExecutionListenerManager;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019%b!B4i\u0003\u0003\t\bbBA\r\u0001\u0011\u0005\u00111\u0004\u0005\b\u0003C\u0001a\u0011AA\u0012\u0011\u001d\tY\u0004\u0001D\u0001\u0003{Aq!a\u0014\u0001\r\u0003\t\t\u0006C\u0004\u0002r\u00011\t!a\u001d\t\u000f\u0005\u0005\u0005A\"\u0001\u0002\u0004\"9\u0011Q\u0012\u0001\u0007\u0002\u0005=\u0005bBAL\u0001\u0019\u0005\u0011\u0011\u0014\u0005\b\u0003S\u0003a\u0011AAV\u0011\u001d\ty\f\u0001D\u0001\u0003\u0003Dq!!3\u0001\r\u0003\tY\rC\u0004\u0002\\\u00021\t!a\u0007\t\u000f\u0005u\u0007A\"\u0001\u0002`\"9\u0011q\u001e\u0001\u0007\u0002\u0005E\bbBAx\u0001\u0019\u0005!Q\t\u0005\b\u0003_\u0004a\u0011\u0001B:\u0011\u001d\ty\u000f\u0001D\u0001\u00057Cq!a<\u0001\r\u0003\u0011Y\fC\u0004\u0002p\u00021\tA!3\t\u000f\u0005=\bA\"\u0001\u0003`\"9\u0011q\u001e\u0001\u0007\u0002\tm\bbBB\f\u0001\u0019\u00051\u0011\u0004\u0005\b\u0007[\u0001a\u0011AB\u0018\u0011\u001d\u0019I\u0005\u0001D\u0001\u0007\u0017Bqa!\u0013\u0001\r\u0003\u0019\t\u0007C\u0004\u0004J\u00011\taa\u001e\t\u000f\r=\u0005A\"\u0001\u0004\u0012\"91q\u0012\u0001\u0007\u0002\r%\u0006bBBH\u0001\u0019\u00051\u0011\u0017\u0005\b\u0007\u001f\u0003a\u0011AB^\u0011\u001d\u0019i\r\u0001D\u0001\u0007\u001fDqaa7\u0001\r\u0003\u0019i\u000e\u0003\u0004j\u0001\u0019\u000511\u001d\u0005\u0007S\u00021\taa?\t\r%\u0004A\u0011\u0001C\u0004\u0011\u0019I\u0007\u0001\"\u0001\u0005\u0012!9AQ\u0003\u0001\u0007\u0002\u0011]\u0001b\u0002C\u0015\u0001\u0019\u0005A1\u0006\u0005\b\tS\u0001a\u0011\u0001C\u001d\u0011\u001d!I\u0003\u0001D\u0001\t\u001bBq\u0001\"\u000b\u0001\r\u0003!\t\u0007C\u0004\u0005l\u00011\t\u0001\"\u001c\t\u000f\u0011\u0015\u0005A\"\u0001\u0005\b\"9AQ\u0012\u0001\u0007\u0002\u0011=\u0005b\u0002CJ\u0001\u0019\u0005AQ\u0013\u0005\b\t;\u0003a\u0011\u0001CP\u0011\u001d!\t\u000b\u0001D\u0001\tGCq\u0001b*\u0001\r\u0003!I\u000bC\u0004\u0005.\u00021\t\u0001b,\t\u000f\u0011U\u0006A\"\u0001\u00058\"9Aq\u0018\u0001\u0007\u0002\u0011\u0005\u0007b\u0002Ce\u0001\u0019\u0005A1\u001a\u0005\n\t'\u0004!\u0019!D\u0001\t+Dq\u0001\"8\u0001\t\u0003!y\u000eC\u0004\u0005r\u0002!\t\u0001b(\t\u0011\u0011M\bA\"\u0001i\tkDq\u0001\"@\u0001\t\u0003!ypB\u0004\u0006\u0010!D\t!\"\u0005\u0007\r\u001dD\u0007\u0012AC\n\u0011\u001d\tIb\u000fC\u0001\u000b?)a!\"\t<\u0001\u0005u\u0001BCC\u0012w!\u0015\r\u0011\"\u0003\u0006&!QQqE\u001e\t\u0006\u0004%I!\"\n\t\u000f\u0015%2\b\"\u0003\u0006&!AQ1F\u001e!\n\u0013)i\u0003C\u0004\u00064m\"\t%\"\u000e\t\u000f\u0019\r1\b\"\u0011\u0007\u0006!9a1B\u001e\u0005B\u00195\u0001b\u0002D\tw\u0011\u0005c1\u0003\u0005\b\r/YD\u0011\tD\n\u0011\u001d1Ib\u000fC)\r71a!b\u000f<\u0001\u0015u\u0002bBA\r\u0011\u0012\u0005QQ\u0007\u0005\n\u000b\u000bB%\u0019!C\u0005\u000b\u000fB\u0001\"\"\u001aIA\u0003%Q\u0011\n\u0005\n\u000bOB\u0005\u0019!C\u0005\u000bSB\u0011\"\"\u001dI\u0001\u0004%I!b\u001d\t\u0011\u0015e\u0004\n)Q\u0005\u000bWB\u0011\"b\u001fI\u0001\u0004%I!\"\n\t\u0013\u0015u\u0004\n1A\u0005\n\u0015}\u0004\u0002CCB\u0011\u0002\u0006K!\"\u0006\t\u000f\u0015\u0015\u0005\n\"\u0011\u0006\b\"9Qq\u0012%\u0005B\u0015E\u0005bBCL\u0011\u0012\u0005S\u0011\u0014\u0005\b\u000b;CE\u0011ICP\u0011\u001d)i\n\u0013C!\u000bSCq!\"(I\t\u0003*y\u000bC\u0004\u0006\u001e\"#\t%b/\t\u000f\u0015u\u0005\n\"\u0011\u0006B\"9QQ\u0014%\u0005B\u0015\u001d\u0007bBCO\u0011\u0012\u0005S1\u001a\u0005\b\u000b+DE\u0011ICl\u0011\u001d)i\u000e\u0013C!\u000b?D\u0001\"!\tI\t\u0003RWQ\u001d\u0005\b\u000bWDE\u0011ACM\u0011\u001d)i\u000f\u0013C\u0001\u000b3Cq!b<I\t\u0013)\t\u0010C\u0004\u0006v\"#\t%a\u0007\t\u000f\u0015]\b\n\"\u0011\u0002\u001c!9Q\u0011 %\u0005R\u0015m\bbBC\u001a\u0011\u0012%a\u0011\u0001\u0005\n\r?Y\u0014\u0011!C\u0005\rC\u0011Ab\u00159be.\u001cVm]:j_:T!!\u001b6\u0002\u0007M\fHN\u0003\u0002lY\u0006)1\u000f]1sW*\u0011QN\\\u0001\u0007CB\f7\r[3\u000b\u0003=\f1a\u001c:h\u0007\u0001\u0019R\u0001\u0001:y\u0003\u0013\u0001\"a\u001d<\u000e\u0003QT\u0011!^\u0001\u0006g\u000e\fG.Y\u0005\u0003oR\u0014a!\u00118z%\u00164\u0007cA=\u0002\u00049\u0011!p \b\u0003wzl\u0011\u0001 \u0006\u0003{B\fa\u0001\u0010:p_Rt\u0014\"A;\n\u0007\u0005\u0005A/A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\u0015\u0011q\u0001\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0004\u0003\u0003!\b\u0003BA\u0006\u0003+i!!!\u0004\u000b\t\u0005=\u0011\u0011C\u0001\u0003S>T!!a\u0005\u0002\t)\fg/Y\u0005\u0005\u0003/\tiAA\u0005DY>\u001cX-\u00192mK\u00061A(\u001b8jiz\"\"!!\b\u0011\u0007\u0005}\u0001!D\u0001i\u00031\u0019\b/\u0019:l\u0007>tG/\u001a=u+\t\t)\u0003\u0005\u0003\u0002(\u0005%R\"\u00016\n\u0007\u0005-\"N\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH\u000fK\u0002\u0003\u0003_\u0001B!!\r\u000285\u0011\u00111\u0007\u0006\u0004\u0003kQ\u0017AC1o]>$\u0018\r^5p]&!\u0011\u0011HA\u001a\u0005-\u0019E.Y:tS\u000e|e\u000e\\=\u0002\u000fY,'o]5p]V\u0011\u0011q\b\t\u0005\u0003\u0003\nIE\u0004\u0003\u0002D\u0005\u0015\u0003CA>u\u0013\r\t9\u0005^\u0001\u0007!J,G-\u001a4\n\t\u0005-\u0013Q\n\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005\u001dC/A\u0006tQ\u0006\u0014X\rZ*uCR,WCAA*!\u0011\t)&a\u0017\u000e\u0005\u0005]#bAA-Q\u0006A\u0011N\u001c;fe:\fG.\u0003\u0003\u0002^\u0005]#aC*iCJ,Gm\u0015;bi\u0016D3\u0001BA\u0018Q\r!\u00111\r\t\u0005\u0003c\t)'\u0003\u0003\u0002h\u0005M\"\u0001C+ogR\f'\r\\3)\u0007\u0011\tY\u0007E\u0002t\u0003[J1!a\u001cu\u0005%!(/\u00198tS\u0016tG/\u0001\u0007tKN\u001c\u0018n\u001c8Ti\u0006$X-\u0006\u0002\u0002vA!\u0011QKA<\u0013\u0011\tI(a\u0016\u0003\u0019M+7o]5p]N#\u0018\r^3)\u0007\u0015\ty\u0003K\u0002\u0006\u0003GB3!BA6\u0003)\u0019\u0018\u000f\\\"p]R,\u0007\u0010^\u000b\u0003\u0003\u000b\u0003B!a\b\u0002\b&\u0019\u0011\u0011\u00125\u0003\u0015M\u000bFjQ8oi\u0016DH\u000fK\u0002\u0007\u0003W\nAaY8oMV\u0011\u0011\u0011\u0013\t\u0005\u0003?\t\u0019*C\u0002\u0002\u0016\"\u0014QBU;oi&lWmQ8oM&<\u0017a\u00047jgR,g.\u001a:NC:\fw-\u001a:\u0016\u0005\u0005m\u0005\u0003BAO\u0003Gk!!a(\u000b\u0007\u0005\u0005\u0006.\u0001\u0003vi&d\u0017\u0002BAS\u0003?\u0013\u0001$\u0012=fGV$\u0018n\u001c8MSN$XM\\3s\u001b\u0006t\u0017mZ3sQ\rA\u0011qF\u0001\rKb\u0004XM]5nK:$\u0018\r\\\u000b\u0003\u0003[\u0003B!a\b\u00020&\u0019\u0011\u0011\u00175\u0003'\u0015C\b/\u001a:j[\u0016tG/\u00197NKRDw\u000eZ:)\u0007%\ty\u0003K\u0002\n\u0003o\u0003B!!\r\u0002:&!\u00111XA\u001a\u00051)\u0005\u0010]3sS6,g\u000e^1mQ\rI\u00111M\u0001\u0004k\u00124WCAAb!\u0011\ty\"!2\n\u0007\u0005\u001d\u0007NA\bV\t\u001a\u0013VmZ5tiJ\fG/[8o\u0003\u001d\u0019HO]3b[N,\"!!4\u0011\t\u0005=\u0017Q[\u0007\u0003\u0003#T1!a5i\u0003%\u0019HO]3b[&tw-\u0003\u0003\u0002X\u0006E'!F*ue\u0016\fW.\u001b8h#V,'/_'b]\u0006<WM\u001d\u0015\u0004\u0017\u0005\r\u0014A\u00038foN+7o]5p]\u0006qQ-\u001c9us\u0012\u000bG/\u0019$sC6,WCAAq!\u0011\t\u0019/a:\u000f\t\u0005}\u0011Q]\u0005\u0004\u0003\u0003A\u0017\u0002BAu\u0003W\u0014\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u0007\u0005\u0005\u0001\u000eK\u0002\u000e\u0003W\nqb\u0019:fCR,G)\u0019;b\rJ\fW.Z\u000b\u0005\u0003g\u0014I\u0003\u0006\u0003\u0002v\nmB\u0003BAq\u0003oD\u0011\"!?\u000f\u0003\u0003\u0005\u001d!a?\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u0002~\ne!Q\u0005\b\u0005\u0003\u007f\u0014\u0019B\u0004\u0003\u0003\u0002\t=a\u0002\u0002B\u0002\u0005\u0013q1A\u001fB\u0003\u0013\r\u00119\u0001^\u0001\be\u00164G.Z2u\u0013\u0011\u0011YA!\u0004\u0002\u000fI,h\u000e^5nK*\u0019!q\u0001;\n\t\u0005\u0005!\u0011\u0003\u0006\u0005\u0005\u0017\u0011i!\u0003\u0003\u0003\u0016\t]\u0011\u0001C;oSZ,'o]3\u000b\t\u0005\u0005!\u0011C\u0005\u0005\u00057\u0011iBA\u0004UsB,G+Y4\n\t\t}!\u0011\u0005\u0002\t)f\u0004X\rV1hg*!!1\u0005B\u0007\u0003\r\t\u0007/\u001b\t\u0005\u0005O\u0011I\u0003\u0004\u0001\u0005\u000f\t-bB1\u0001\u0003.\t\t\u0011)\u0005\u0003\u00030\tU\u0002cA:\u00032%\u0019!1\u0007;\u0003\u000f9{G\u000f[5oOB\u00191Oa\u000e\n\u0007\teBOA\u0004Qe>$Wo\u0019;\t\u000f\tub\u00021\u0001\u0003@\u0005!A-\u0019;b!\u0015I(\u0011\tB\u0013\u0013\u0011\u0011\u0019%a\u0002\u0003\u0007M+\u0017\u000f\u0006\u0004\u0002b\n\u001d#1\f\u0005\b\u0005\u0013z\u0001\u0019\u0001B&\u0003\u0011\u0011xn^:\u0011\r\t5#\u0011\u000bB+\u001b\t\u0011yE\u0003\u0003\u0002\"\u0006E\u0011\u0002\u0002B*\u0005\u001f\u0012A\u0001T5tiB!\u0011q\u0004B,\u0013\r\u0011I\u0006\u001b\u0002\u0004%><\bb\u0002B/\u001f\u0001\u0007!qL\u0001\u0007g\u000eDW-\\1\u0011\t\t\u0005$qM\u0007\u0003\u0005GR1A!\u001ai\u0003\u0015!\u0018\u0010]3t\u0013\u0011\u0011IGa\u0019\u0003\u0015M#(/^2u)f\u0004X\rK\u0002\u0010\u0005[\u0002B!!\r\u0003p%!!\u0011OA\u001a\u00051!UM^3m_B,'/\u00119j)\u0019\t\tO!\u001e\u0003\n\"9!Q\b\tA\u0002\t]\u0004\u0007\u0002B=\u0005{\u0002bA!\u0014\u0003R\tm\u0004\u0003\u0002B\u0014\u0005{\"ABa \u0003v\u0005\u0005\t\u0011!B\u0001\u0005\u0003\u00131a\u0018\u00132#\u0011\u0011yCa!\u0011\u0007M\u0014))C\u0002\u0003\bR\u00141!\u00118z\u0011\u001d\u0011Y\t\u0005a\u0001\u0005\u001b\u000b\u0011BY3b]\u000ec\u0017m]:1\t\t=%q\u0013\t\u0007\u0003\u0003\u0012\tJ!&\n\t\tM\u0015Q\n\u0002\u0006\u00072\f7o\u001d\t\u0005\u0005O\u00119\n\u0002\u0007\u0003\u001a\n%\u0015\u0011!A\u0001\u0006\u0003\u0011\tIA\u0002`II*BA!(\u0003*R!!q\u0014BV)\u0011\t\tO!)\t\u0013\t\r\u0016#!AA\u0004\t\u0015\u0016AC3wS\u0012,gnY3%eA1\u0011Q B\r\u0005O\u0003BAa\n\u0003*\u00129!1F\tC\u0002\t5\u0002b\u0002BW#\u0001\u0007!qV\u0001\u0004e\u0012$\u0007C\u0002BY\u0005k\u00139+\u0004\u0002\u00034*\u0019!Q\u00166\n\t\t]&1\u0017\u0002\u0004%\u0012#\u0005fA\t\u00020Q1\u0011\u0011\u001dB_\u0005\u0007DqAa0\u0013\u0001\u0004\u0011\t-\u0001\u0004s_^\u0014F\t\u0012\t\u0007\u0005c\u0013)L!\u0016\t\u000f\tu#\u00031\u0001\u0003`!\u001a!#a\f)\u0007I\u0011i\u0007\u0006\u0004\u0002b\n-'\u0011\u001c\u0005\b\u0005\u007f\u001b\u0002\u0019\u0001Bg!\u0019\u0011yM!6\u0003V5\u0011!\u0011\u001b\u0006\u0005\u0003'\u0011\u0019NC\u0002\u0003$)LAAa6\u0003R\n9!*\u0019<b%\u0012#\u0005b\u0002B/'\u0001\u0007!q\f\u0015\u0004'\u0005=\u0002fA\n\u0003nQ1\u0011\u0011\u001dBq\u0005[DqA!,\u0015\u0001\u0004\u0011\u0019\u000f\r\u0003\u0003f\n%\bC\u0002BY\u0005k\u00139\u000f\u0005\u0003\u0003(\t%H\u0001\u0004Bv\u0005C\f\t\u0011!A\u0003\u0002\t\u0005%aA0%g!9!1\u0012\u000bA\u0002\t=\b\u0007\u0002By\u0005k\u0004b!!\u0011\u0003\u0012\nM\b\u0003\u0002B\u0014\u0005k$ABa>\u0003n\u0006\u0005\t\u0011!B\u0001\u0005\u0003\u00131a\u0018\u00135Q\r!\u0012q\u0006\u000b\u0007\u0003C\u0014ip!\u0003\t\u000f\t5V\u00031\u0001\u0003\u0000B\"1\u0011AB\u0003!\u0019\u0011yM!6\u0004\u0004A!!qEB\u0003\t1\u00199A!@\u0002\u0002\u0003\u0005)\u0011\u0001BA\u0005\ryF%\u000e\u0005\b\u0005\u0017+\u0002\u0019AB\u0006a\u0011\u0019ia!\u0005\u0011\r\u0005\u0005#\u0011SB\b!\u0011\u00119c!\u0005\u0005\u0019\rM1\u0011BA\u0001\u0002\u0003\u0015\tA!!\u0003\u0007}#c\u0007K\u0002\u0016\u0003_\tqCY1tKJ+G.\u0019;j_:$v\u000eR1uC\u001a\u0013\u0018-\\3\u0015\t\u0005\u000581\u0004\u0005\b\u0007;1\u0002\u0019AB\u0010\u00031\u0011\u0017m]3SK2\fG/[8o!\u0011\u0019\tca\n\u000e\u0005\r\r\"bAB\u0013Q\u000691o\\;sG\u0016\u001c\u0018\u0002BB\u0015\u0007G\u0011ABQ1tKJ+G.\u0019;j_:D3AFA\u0018\u00031)W\u000e\u001d;z\t\u0006$\u0018m]3u+\u0011\u0019\tda\u000f\u0015\t\rM2q\b\t\u0007\u0003?\u0019)d!\u000f\n\u0007\r]\u0002NA\u0004ECR\f7/\u001a;\u0011\t\t\u001d21\b\u0003\b\u0007{9\"\u0019\u0001BA\u0005\u0005!\u0006\"CB!/\u0005\u0005\t9AB\"\u0003))g/\u001b3f]\u000e,Ge\r\t\u0007\u0003?\u0019)e!\u000f\n\u0007\r\u001d\u0003NA\u0004F]\u000e|G-\u001a:\u0002\u001b\r\u0014X-\u0019;f\t\u0006$\u0018m]3u+\u0011\u0019ie!\u0016\u0015\t\r=3Q\f\u000b\u0005\u0007#\u001a9\u0006\u0005\u0004\u0002 \rU21\u000b\t\u0005\u0005O\u0019)\u0006B\u0004\u0004>a\u0011\rA!!\t\u0013\re\u0003$!AA\u0004\rm\u0013AC3wS\u0012,gnY3%iA1\u0011qDB#\u0007'BqA!\u0010\u0019\u0001\u0004\u0019y\u0006E\u0003z\u0005\u0003\u001a\u0019&\u0006\u0003\u0004d\r-D\u0003BB3\u0007g\"Baa\u001a\u0004nA1\u0011qDB\u001b\u0007S\u0002BAa\n\u0004l\u001191QH\rC\u0002\t\u0005\u0005\"CB83\u0005\u0005\t9AB9\u0003))g/\u001b3f]\u000e,G%\u000e\t\u0007\u0003?\u0019)e!\u001b\t\u000f\tu\u0012\u00041\u0001\u0004vA1!Q\nB)\u0007S*Ba!\u001f\u0004\u0002R!11PBE)\u0011\u0019iha!\u0011\r\u0005}1QGB@!\u0011\u00119c!!\u0005\u000f\ru\"D1\u0001\u0003\u0002\"I1Q\u0011\u000e\u0002\u0002\u0003\u000f1qQ\u0001\u000bKZLG-\u001a8dK\u00122\u0004CBA\u0010\u0007\u000b\u001ay\bC\u0004\u0003>i\u0001\raa#\u0011\r\tE&QWB@Q\rQ\u0012qF\u0001\u0006e\u0006tw-\u001a\u000b\u0005\u0007'\u001b\t\u000b\u0005\u0004\u0002 \rU2Q\u0013\t\u0005\u0007/\u001bi*\u0004\u0002\u0004\u001a*!11TA\t\u0003\u0011a\u0017M\\4\n\t\r}5\u0011\u0014\u0002\u0005\u0019>tw\rC\u0004\u0004$n\u0001\ra!*\u0002\u0007\u0015tG\rE\u0002t\u0007OK1aa(u)\u0019\u0019\u0019ja+\u00040\"91Q\u0016\u000fA\u0002\r\u0015\u0016!B:uCJ$\bbBBR9\u0001\u00071Q\u0015\u000b\t\u0007'\u001b\u0019l!.\u00048\"91QV\u000fA\u0002\r\u0015\u0006bBBR;\u0001\u00071Q\u0015\u0005\b\u0007sk\u0002\u0019ABS\u0003\u0011\u0019H/\u001a9\u0015\u0015\rM5QXB`\u0007\u0003\u001c\u0019\rC\u0004\u0004.z\u0001\ra!*\t\u000f\r\rf\u00041\u0001\u0004&\"91\u0011\u0018\u0010A\u0002\r\u0015\u0006bBBc=\u0001\u00071qY\u0001\u000e]Vl\u0007+\u0019:uSRLwN\\:\u0011\u0007M\u001cI-C\u0002\u0004LR\u00141!\u00138u\u0003\u001d\u0019\u0017\r^1m_\u001e,\"a!5\u0011\t\rM7q[\u0007\u0003\u0007+T1a!4i\u0013\u0011\u0019In!6\u0003\u000f\r\u000bG/\u00197pO\u0006)A/\u00192mKR!\u0011\u0011]Bp\u0011\u001d\u0019\t\u000f\ta\u0001\u0003\u007f\t\u0011\u0002^1cY\u0016t\u0015-\\3\u0015\r\u0005\u00058Q]Bu\u0011\u001d\u00199/\ta\u0001\u0003\u007f\tqa]9m)\u0016DH\u000fC\u0004\u0004l\u0006\u0002\ra!<\u0002\t\u0005\u0014xm\u001d\u0019\u0005\u0007_\u001c9\u0010E\u0003t\u0007c\u001c)0C\u0002\u0004tR\u0014Q!\u0011:sCf\u0004BAa\n\u0004x\u0012a1\u0011`Bu\u0003\u0003\u0005\tQ!\u0001\u0003\u0002\n\u0019q\fJ\u001c\u0015\r\u0005\u00058Q`B\u0000\u0011\u001d\u00199O\ta\u0001\u0003\u007fAqaa;#\u0001\u0004!\t\u0001\u0005\u0005\u0002B\u0011\r\u0011q\bBB\u0013\u0011!)!!\u0014\u0003\u00075\u000b\u0007\u000f\u0006\u0004\u0002b\u0012%A1\u0002\u0005\b\u0007O\u001c\u0003\u0019AA \u0011\u001d\u0019Yo\ta\u0001\t\u001b\u0001\u0002B!\u0014\u0005\u0010\u0005}\"1Q\u0005\u0005\t\u000b\u0011y\u0005\u0006\u0003\u0002b\u0012M\u0001bBBtI\u0001\u0007\u0011qH\u0001\u000fKb,7-\u001e;f\u0007>lW.\u00198e)!\t\t\u000f\"\u0007\u0005\u001e\u0011\u0005\u0002b\u0002C\u000eK\u0001\u0007\u0011qH\u0001\u0007eVtg.\u001a:\t\u000f\u0011}Q\u00051\u0001\u0002@\u000591m\\7nC:$\u0007b\u0002C\u0012K\u0001\u0007AQE\u0001\b_B$\u0018n\u001c8t!!\t\t\u0005b\u0001\u0002@\u0005}\u0002fA\u0013\u0002d\u0005Y\u0011\r\u001a3BeRLg-Y2u)\u0011!i\u0003b\r\u0011\u0007M$y#C\u0002\u00052Q\u0014A!\u00168ji\"9AQ\u0007\u0014A\u0002\u0005}\u0012\u0001\u00029bi\"D3AJA\\)\u0011!i\u0003b\u000f\t\u000f\u0011ur\u00051\u0001\u0005@\u0005\u0019QO]5\u0011\t\u0011\u0005CqI\u0007\u0003\t\u0007RA\u0001\"\u0012\u0002\u0012\u0005\u0019a.\u001a;\n\t\u0011%C1\t\u0002\u0004+JK\u0005fA\u0014\u00028R1AQ\u0006C(\t7Bq\u0001\"\u0015)\u0001\u0004!\u0019&A\u0003csR,7\u000fE\u0003t\u0007c$)\u0006E\u0002t\t/J1\u0001\"\u0017u\u0005\u0011\u0011\u0015\u0010^3\t\u000f\u0011u\u0003\u00061\u0001\u0002@\u00051A/\u0019:hKRD3\u0001KA\\)\u0019!i\u0003b\u0019\u0005h!9AQM\u0015A\u0002\u0005}\u0012AB:pkJ\u001cW\rC\u0004\u0005^%\u0002\r!a\u0010)\u0007%\n9,\u0001\u0007bI\u0012\f%\u000f^5gC\u000e$8\u000f\u0006\u0003\u0005.\u0011=\u0004b\u0002C\u001fU\u0001\u0007A\u0011\u000f\t\u0006g\u0012MDqH\u0005\u0004\tk\"(A\u0003\u001fsKB,\u0017\r^3e}!\u001a!&a.)\u0007)\"Y\b\u0005\u0003\u0005~\u0011\u0005UB\u0001C@\u0015\r\t)\u0004^\u0005\u0005\t\u0007#yHA\u0004wCJ\f'oZ:\u0002\r\u0005$G\rV1h)\u0011!i\u0003\"#\t\u000f\u0011-5\u00061\u0001\u0002@\u0005\u0019A/Y4\u0002\u0013I,Wn\u001c<f)\u0006<G\u0003\u0002C\u0017\t#Cq\u0001b#-\u0001\u0004\ty$A\u0004hKR$\u0016mZ:\u0015\u0005\u0011]\u0005CBA!\t3\u000by$\u0003\u0003\u0005\u001c\u00065#aA*fi\u0006I1\r\\3beR\u000bwm\u001d\u000b\u0003\t[\tA\"\u001b8uKJ\u0014X\u000f\u001d;BY2$\"\u0001\"*\u0011\u000be\u0014\t%a\u0010\u0002\u0019%tG/\u001a:skB$H+Y4\u0015\t\u0011\u0015F1\u0016\u0005\b\t\u0017\u0003\u0004\u0019AA \u0003IIg\u000e^3seV\u0004Ho\u00149fe\u0006$\u0018n\u001c8\u0015\t\u0011\u0015F\u0011\u0017\u0005\b\tg\u000b\u0004\u0019AA \u0003-y\u0007/\u001a:bi&|g.\u00133\u0002\tI,\u0017\rZ\u000b\u0003\ts\u0003B!a\b\u0005<&\u0019AQ\u00185\u0003\u001f\u0011\u000bG/\u0019$sC6,'+Z1eKJ\f!B]3bIN#(/Z1n+\t!\u0019\r\u0005\u0003\u0002P\u0012\u0015\u0017\u0002\u0002Cd\u0003#\u0014\u0001\u0003R1uCN#(/Z1n%\u0016\fG-\u001a:\u0002\u0007Q4h-\u0006\u0002\u0005NB!\u0011q\u0004Ch\u0013\r!\t\u000e\u001b\u0002\u0014)\u0006\u0014G.\u001a,bYV,GMR;oGRLwN\\\u0001\nS6\u0004H.[2jiN,\"\u0001b6\u0011\t\u0005}A\u0011\\\u0005\u0004\t7D'\u0001D*R\u0019&k\u0007\u000f\\5dSR\u001c\u0018\u0001\u0002;j[\u0016,B\u0001\"9\u0005fR!A1\u001dCt!\u0011\u00119\u0003\":\u0005\u000f\rubG1\u0001\u0003\u0002\"AA\u0011\u001e\u001c\u0005\u0002\u0004!Y/A\u0001g!\u0015\u0019HQ\u001eCr\u0013\r!y\u000f\u001e\u0002\ty\tLh.Y7f}\u0005!1\u000f^8q\u0003!I7/V:bE2,WC\u0001C|!\r\u0019H\u0011`\u0005\u0004\tw$(a\u0002\"p_2,\u0017M\\\u0001\u000bo&$\b.Q2uSZ,W\u0003BC\u0001\u000b\u000b!B!b\u0001\u0006\bA!!qEC\u0003\t\u001d\u0019i$\u000fb\u0001\u0005\u0003C\u0001\"\"\u0003:\t\u0003\u0007Q1B\u0001\u0006E2|7m\u001b\t\u0006g\u00125X1\u0001\u0015\u0004s\t5\u0014\u0001D*qCJ\\7+Z:tS>t\u0007cAA\u0010wM)1(\"\u0006\u0006\u001cA!\u0011qDC\f\u0013\r)I\u0002\u001b\u0002\u0016'B\f'o[*fgNLwN\\\"p[B\fg.[8o!\u0011\tY!\"\b\n\t\u0005\u0015\u0011Q\u0002\u000b\u0003\u000b#\u0011qaU3tg&|g.A\tD\u0019\u0006\u001b6+S\"`\u0007>k\u0005+\u0011(J\u001f:+\"!\"\u0006\u0002#\r{eJT#D)~\u001bu*\u0014)B\u001d&{e*A\tE\u000b\u001a\u000bU\u000b\u0014+`\u0007>k\u0005+\u0011(J\u001f:\u000bq\u0002\\8pWV\u00048i\\7qC:LwN\u001c\u000b\u0005\u000b+)y\u0003C\u0004\u00062\u0005\u0003\r!a\u0010\u0002\t9\fW.Z\u0001\bEVLG\u000eZ3s)\t)9\u0004E\u0002\u0006:!k\u0011a\u000f\u0002\b\u0005VLG\u000eZ3s'\rAUq\b\t\u0005\u0003?)\t%C\u0002\u0006D!\u00141c\u00159be.\u001cVm]:j_:\u0014U/\u001b7eKJ\fa#\u001a=uK:\u001c\u0018n\u001c8N_\u0012Lg-[2bi&|gn]\u000b\u0003\u000b\u0013\u0002b!b\u0013\u0006V\u0015eSBAC'\u0015\u0011)y%\"\u0015\u0002\u000f5,H/\u00192mK*\u0019Q1\u000b;\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0006X\u00155#A\u0002\"vM\u001a,'\u000fE\u0004t\u000b7*y\u0006\"\f\n\u0007\u0015uCOA\u0005Gk:\u001cG/[8ocA!\u0011qDC1\u0013\r)\u0019\u0007\u001b\u0002\u0017'B\f'o[*fgNLwN\\#yi\u0016t7/[8og\u00069R\r\u001f;f]NLwN\\'pI&4\u0017nY1uS>t7\u000fI\u0001\u0003g\u000e,\"!b\u001b\u0011\u000bM,i'!\n\n\u0007\u0015=DO\u0001\u0004PaRLwN\\\u0001\u0007g\u000e|F%Z9\u0015\t\u00115RQ\u000f\u0005\n\u000boj\u0015\u0011!a\u0001\u000bW\n1\u0001\u001f\u00132\u0003\r\u00198\rI\u0001\nG>l\u0007/\u00198j_:\fQbY8na\u0006t\u0017n\u001c8`I\u0015\fH\u0003\u0002C\u0017\u000b\u0003C\u0011\"b\u001eQ\u0003\u0003\u0005\r!\"\u0006\u0002\u0015\r|W\u000e]1oS>t\u0007%A\u0004baBt\u0015-\\3\u0015\t\u0015%U1R\u0007\u0002\u0011\"9Q\u0011\u0007*A\u0002\u0005}\u0002f\u0001*\u00020\u00051Q.Y:uKJ$B!\"#\u0006\u0014\"9QqR*A\u0002\u0005}\u0002fA*\u00020\u0005\tRM\\1cY\u0016D\u0015N^3TkB\u0004xN\u001d;\u0015\u0005\u0015%\u0005f\u0001+\u00020\u000511m\u001c8gS\u001e$b!\"#\u0006\"\u0016\u0015\u0006bBCR+\u0002\u0007\u0011qH\u0001\u0004W\u0016L\bbBCT+\u0002\u0007\u0011qH\u0001\u0006m\u0006dW/\u001a\u000b\u0007\u000b\u0013+Y+\",\t\u000f\u0015\rf\u000b1\u0001\u0002@!9Qq\u0015,A\u0002\r\u0015FCBCE\u000bc+\u0019\fC\u0004\u0006$^\u0003\r!a\u0010\t\u000f\u0015\u001dv\u000b1\u0001\u00066B\u00191/b.\n\u0007\u0015eFO\u0001\u0004E_V\u0014G.\u001a\u000b\u0007\u000b\u0013+i,b0\t\u000f\u0015\r\u0006\f1\u0001\u0002@!9Qq\u0015-A\u0002\u0011]H\u0003BCE\u000b\u0007Dq!\"2Z\u0001\u0004!\t!A\u0002nCB$B!\"#\u0006J\"9QQ\u0019.A\u0002\u00115A\u0003BCE\u000b\u001bDq!!$\\\u0001\u0004)y\r\u0005\u0003\u0002(\u0015E\u0017bACjU\nI1\u000b]1sW\u000e{gNZ\u0001\u0007e\u0016lw\u000e^3\u0015\t\u0015%U\u0011\u001c\u0005\b\u000b7d\u0006\u0019AA \u0003A\u0019wN\u001c8fGRLwN\\*ue&tw-\u0001\bxSRDW\t\u001f;f]NLwN\\:\u0015\t\u0015%U\u0011\u001d\u0005\b\tSl\u0006\u0019AC-Q\ri\u0016q\u0006\u000b\u0005\u000b\u0013+9\u000fC\u0004\u0002\"y\u0003\r!!\n)\u0007y\u000by#A\u0004dY\u0006\u001c8/[2\u0002\u000f\r|gN\\3di\u0006!Qn\u001c3f)\u0011)I)b=\t\u000f\u0015m\u0014\r1\u0001\u0006\u0016\u0005Yq-\u001a;Pe\u000e\u0013X-\u0019;f\u0003\u0019\u0019'/Z1uK\u0006\u0019\u0002.\u00198eY\u0016\u0014U/\u001b7eKJ\u001cuN\u001c4jOR1Aq_C\u007f\u000b\u007fDq!b)e\u0001\u0004\ty\u0004C\u0004\u0006(\u0012\u0004\r!a\u0010\u0015\u0005\u0015}\u0012\u0001E:fi\u0006\u001bG/\u001b<f'\u0016\u001c8/[8o)\u0011!iCb\u0002\t\u000f\u0019%1\t1\u0001\u0002\u001e\u000591/Z:tS>t\u0017!E:fi\u0012+g-Y;miN+7o]5p]R!AQ\u0006D\b\u0011\u001d1I\u0001\u0012a\u0001\u0003;\t\u0001cZ3u\u0003\u000e$\u0018N^3TKN\u001c\u0018n\u001c8\u0016\u0005\u0019U\u0001#B:\u0006n\u0005u\u0011!E4fi\u0012+g-Y;miN+7o]5p]\u00069BO]=DCN$Hk\\%na2,W.\u001a8uCRLwN\u001c\u000b\u0005\r+1i\u0002C\u0004\u0007\n\u001d\u0003\r!!\b\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0019\r\u0002\u0003BBL\rKIAAb\n\u0004\u001a\n1qJ\u00196fGR\u0004"
)
public abstract class SparkSession implements Serializable, Closeable {
   public static Option getDefaultSession() {
      return SparkSession$.MODULE$.getDefaultSession();
   }

   public static Option getActiveSession() {
      return SparkSession$.MODULE$.getActiveSession();
   }

   public static void setDefaultSession(final SparkSession session) {
      SparkSession$.MODULE$.setDefaultSession(session);
   }

   public static void setActiveSession(final SparkSession session) {
      SparkSession$.MODULE$.setActiveSession(session);
   }

   public static Builder builder() {
      return SparkSession$.MODULE$.builder();
   }

   public static SparkSession active() {
      return SparkSession$.MODULE$.active();
   }

   public static void clearDefaultSession() {
      SparkSession$.MODULE$.clearDefaultSession();
   }

   public static void clearActiveSession() {
      SparkSession$.MODULE$.clearActiveSession();
   }

   public void addArtifacts(final URI... uri) {
      this.addArtifacts((Seq).MODULE$.wrapRefArray((Object[])uri));
   }

   @ClassicOnly
   public abstract SparkContext sparkContext();

   public abstract String version();

   @ClassicOnly
   @Unstable
   public abstract SharedState sharedState();

   @ClassicOnly
   @Unstable
   public abstract SessionState sessionState();

   public abstract SQLContext sqlContext();

   public abstract RuntimeConfig conf();

   @ClassicOnly
   public abstract ExecutionListenerManager listenerManager();

   @ClassicOnly
   @Experimental
   @Unstable
   public abstract ExperimentalMethods experimental();

   public abstract UDFRegistration udf();

   @Unstable
   public abstract StreamingQueryManager streams();

   public abstract SparkSession newSession();

   public abstract Dataset emptyDataFrame();

   public abstract Dataset createDataFrame(final Seq data, final TypeTags.TypeTag evidence$1);

   @DeveloperApi
   public abstract Dataset createDataFrame(final List rows, final StructType schema);

   public abstract Dataset createDataFrame(final List data, final Class beanClass);

   @ClassicOnly
   public abstract Dataset createDataFrame(final RDD rdd, final TypeTags.TypeTag evidence$2);

   @ClassicOnly
   @DeveloperApi
   public abstract Dataset createDataFrame(final RDD rowRDD, final StructType schema);

   @ClassicOnly
   @DeveloperApi
   public abstract Dataset createDataFrame(final JavaRDD rowRDD, final StructType schema);

   @ClassicOnly
   public abstract Dataset createDataFrame(final RDD rdd, final Class beanClass);

   @ClassicOnly
   public abstract Dataset createDataFrame(final JavaRDD rdd, final Class beanClass);

   @ClassicOnly
   public abstract Dataset baseRelationToDataFrame(final BaseRelation baseRelation);

   public abstract Dataset emptyDataset(final Encoder evidence$3);

   public abstract Dataset createDataset(final Seq data, final Encoder evidence$4);

   public abstract Dataset createDataset(final List data, final Encoder evidence$5);

   @ClassicOnly
   public abstract Dataset createDataset(final RDD data, final Encoder evidence$6);

   public abstract Dataset range(final long end);

   public abstract Dataset range(final long start, final long end);

   public abstract Dataset range(final long start, final long end, final long step);

   public abstract Dataset range(final long start, final long end, final long step, final int numPartitions);

   public abstract Catalog catalog();

   public abstract Dataset table(final String tableName);

   public abstract Dataset sql(final String sqlText, final Object args);

   public abstract Dataset sql(final String sqlText, final Map args);

   public Dataset sql(final String sqlText, final java.util.Map args) {
      return this.sql(sqlText, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(args).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public Dataset sql(final String sqlText) {
      return this.sql(sqlText, scala.Predef..MODULE$.Map().empty());
   }

   @Unstable
   public abstract Dataset executeCommand(final String runner, final String command, final Map options);

   @Experimental
   public abstract void addArtifact(final String path);

   @Experimental
   public abstract void addArtifact(final URI uri);

   @Experimental
   public abstract void addArtifact(final byte[] bytes, final String target);

   @Experimental
   public abstract void addArtifact(final String source, final String target);

   @Experimental
   public abstract void addArtifacts(final Seq uri);

   public abstract void addTag(final String tag);

   public abstract void removeTag(final String tag);

   public abstract Set getTags();

   public abstract void clearTags();

   public abstract Seq interruptAll();

   public abstract Seq interruptTag(final String tag);

   public abstract Seq interruptOperation(final String operationId);

   public abstract DataFrameReader read();

   public abstract DataStreamReader readStream();

   public abstract TableValuedFunction tvf();

   public abstract SQLImplicits implicits();

   public Object time(final Function0 f) {
      long start = System.nanoTime();
      Object ret = f.apply();
      long end = System.nanoTime();
      scala.Predef..MODULE$.println("Time taken: " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
      return ret;
   }

   public void stop() {
      this.close();
   }

   public abstract boolean isUsable();

   @DeveloperApi
   public Object withActive(final Function0 block) {
      SparkSession old = (SparkSession)SparkSession$.MODULE$.getActiveSession().orNull(scala..less.colon.less..MODULE$.refl());
      SparkSession$.MODULE$.setActiveSession(this);

      Object var10000;
      try {
         var10000 = block.apply();
      } finally {
         SparkSession$.MODULE$.setActiveSession(old);
      }

      return var10000;
   }

   public static class Builder extends SparkSessionBuilder {
      private final Buffer extensionModifications;
      private Option sc;
      private SparkSessionCompanion companion;

      private Buffer extensionModifications() {
         return this.extensionModifications;
      }

      private Option sc() {
         return this.sc;
      }

      private void sc_$eq(final Option x$1) {
         this.sc = x$1;
      }

      private SparkSessionCompanion companion() {
         return this.companion;
      }

      private void companion_$eq(final SparkSessionCompanion x$1) {
         this.companion = x$1;
      }

      @ClassicOnly
      public Builder appName(final String name) {
         return (Builder)super.appName(name);
      }

      @ClassicOnly
      public Builder master(final String master) {
         return (Builder)super.master(master);
      }

      @ClassicOnly
      public Builder enableHiveSupport() {
         return (Builder)super.enableHiveSupport();
      }

      public Builder config(final String key, final String value) {
         return (Builder)super.config(key, value);
      }

      public Builder config(final String key, final long value) {
         return (Builder)super.config(key, value);
      }

      public Builder config(final String key, final double value) {
         return (Builder)super.config(key, value);
      }

      public Builder config(final String key, final boolean value) {
         return (Builder)super.config(key, value);
      }

      public Builder config(final Map map) {
         return (Builder)super.config(map);
      }

      public Builder config(final java.util.Map map) {
         return (Builder)super.config(map);
      }

      public Builder config(final SparkConf conf) {
         return (Builder)super.config(conf);
      }

      public Builder remote(final String connectionString) {
         return (Builder)super.remote(connectionString);
      }

      @ClassicOnly
      public synchronized Builder withExtensions(final Function1 f) {
         this.extensionModifications().$plus$eq(f);
         return this;
      }

      @ClassicOnly
      public synchronized Builder sparkContext(final SparkContext sparkContext) {
         this.sc_$eq(scala.Option..MODULE$.apply(sparkContext));
         return this;
      }

      public Builder classic() {
         return this.mode(SparkSession$.MODULE$.org$apache$spark$sql$SparkSession$$CONNECT_COMPANION());
      }

      public Builder connect() {
         return this.mode(SparkSession$.MODULE$.org$apache$spark$sql$SparkSession$$CONNECT_COMPANION());
      }

      private synchronized Builder mode(final SparkSessionCompanion companion) {
         this.companion_$eq(companion);
         return this;
      }

      public SparkSession getOrCreate() {
         return this.builder().getOrCreate();
      }

      public SparkSession create() {
         return this.builder().create();
      }

      public boolean handleBuilderConfig(final String key, final String value) {
         String var10000 = SparkSessionBuilder$.MODULE$.API_MODE_KEY();
         if (var10000 == null) {
            if (key != null) {
               return false;
            }
         } else if (!var10000.equals(key)) {
            return false;
         }

         SparkSessionCompanion var11;
         label36: {
            label46: {
               String var7 = value.toLowerCase(Locale.ROOT).trim();
               String var10001 = SparkSessionBuilder$.MODULE$.API_MODE_CLASSIC();
               if (var10001 == null) {
                  if (var7 == null) {
                     break label46;
                  }
               } else if (var10001.equals(var7)) {
                  break label46;
               }

               var10001 = SparkSessionBuilder$.MODULE$.API_MODE_CONNECT();
               if (var10001 == null) {
                  if (var7 != null) {
                     throw new IllegalArgumentException("Unknown API mode: " + var7);
                  }
               } else if (!var10001.equals(var7)) {
                  throw new IllegalArgumentException("Unknown API mode: " + var7);
               }

               var11 = SparkSession$.MODULE$.org$apache$spark$sql$SparkSession$$CONNECT_COMPANION();
               break label36;
            }

            var11 = SparkSession$.MODULE$.org$apache$spark$sql$SparkSession$$CLASSIC_COMPANION();
         }

         this.companion_$eq(var11);
         return true;
      }

      private synchronized SparkSessionBuilder builder() {
         SparkSessionBuilder builder = this.companion().builder();
         this.sc().foreach((sparkContext) -> builder.sparkContext(sparkContext));
         this.options().foreach((kv) -> builder.config((String)kv._1(), (String)kv._2()));
         this.extensionModifications().foreach((f) -> builder.withExtensions(f));
         return builder;
      }

      public Builder() {
         this.extensionModifications = (Buffer)scala.collection.mutable.Buffer..MODULE$.empty();
         this.sc = scala.None..MODULE$;
         this.companion = SparkSession$.MODULE$.org$apache$spark$sql$SparkSession$$DEFAULT_COMPANION();
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
