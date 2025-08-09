package org.apache.spark.ui;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import java.util.Date;
import java.util.TimeZone;
import org.apache.spark.internal.Logging;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.xml.NodeSeq;

@ScalaSignature(
   bytes = "\u0006\u0005\r}xA\u0002 @\u0011\u0003\tuI\u0002\u0004J\u007f!\u0005\u0011I\u0013\u0005\u0006/\u0006!\t!\u0017\u0005\b5\u0006\u0011\r\u0011\"\u0001\\\u0011\u0019!\u0017\u0001)A\u00059\"9Q-\u0001b\u0001\n\u0003Y\u0006B\u00024\u0002A\u0003%A\fC\u0004h\u0003\t\u0007I\u0011A.\t\r!\f\u0001\u0015!\u0003]\u0011\u001dI\u0017A1A\u0005\n)Daa]\u0001!\u0002\u0013Y\u0007\"\u0002;\u0002\t\u0003)\bB\u0002;\u0002\t\u0003\t\t\u0002C\u0004\u0002\u001e\u0005!\t!a\b\t\u000f\u0005\u0015\u0012\u0001\"\u0001\u0002(!A\u0011QF\u0001C\u0002\u0013%!\u000eC\u0004\u00020\u0005\u0001\u000b\u0011B6\t\u0011\u0005E\u0012A1A\u0005\n)Dq!a\r\u0002A\u0003%1\u000eC\u0004\u00026\u0005!\t!a\u000e\t\u0013\u0005U\u0013!%A\u0005\u0002\u0005]\u0003\"CA7\u0003E\u0005I\u0011AA8\u0011\u001d\t\u0019(\u0001C\u0001\u0003kBq!!!\u0002\t\u0003\t\u0019\tC\u0004\u0002\u001e\u0006!\t!a(\t\u0013\u0005-\u0016!%A\u0005\u0002\u00055\u0006\"CAY\u0003E\u0005I\u0011AAW\u0011\u001d\t\u0019,\u0001C\u0001\u0003kCq!a6\u0002\t\u0003\tI\u000eC\u0004\u0002^\u0006!\t!a8\t\u000f\u0005\r\u0018\u0001\"\u0001\u0002f\"I!1C\u0001\u0012\u0002\u0013\u0005!Q\u0003\u0005\n\u00053\t\u0011\u0013!C\u0001\u0003/B\u0011Ba\u0007\u0002#\u0003%\t!a\u0016\t\u000f\tu\u0011\u0001\"\u0001\u0003 !I!\u0011F\u0001\u0012\u0002\u0013\u0005\u0011q\u000b\u0005\b\u0005W\tA\u0011\u0001B\u0017\u0011%\u0011Y(AI\u0001\n\u0003\u0011i\bC\u0005\u0003\u0002\u0006\t\n\u0011\"\u0001\u0003\u0004\"I!qQ\u0001\u0012\u0002\u0013\u0005!\u0011\u0012\u0005\n\u0005#\u000b\u0011\u0013!C\u0001\u0005'C\u0011Ba&\u0002#\u0003%\tA!'\t\u0013\tu\u0015!%A\u0005\u0002\t}\u0005b\u0002BT\u0003\u0011\u0005!\u0011\u0016\u0005\b\u0005\u001f\fA\u0011\u0001Bi\u0011\u001d\u0011I/\u0001C\u0001\u0005WDqa!\u0001\u0002\t\u0013\u0019\u0019\u0001C\u0004\u0004\f\u0005!\ta!\u0004\t\u000f\r]\u0011\u0001\"\u0001\u0004\u001a!I1QF\u0001\u0012\u0002\u0013\u0005\u0011q\u000b\u0005\b\u0007_\tA\u0011AB\u0019\u0011\u001d\u0019y#\u0001C\u0001\u0007oAqaa\u001a\u0002\t\u0003\u0019I\u0007C\u0004\u0004l\u0005!\ta!\u001c\t\u000f\re\u0014\u0001\"\u0001\u0004|!91\u0011T\u0001\u0005\u0002\rm\u0005bBB^\u0003\u0011\u00051Q\u0018\u0005\n\u0007\u000f\f!\u0019!C\u0007\u0007\u0013D\u0001b!7\u0002A\u0003511\u001a\u0005\b\u00077\fA\u0011ABo\u0011\u001d\u0019)/\u0001C\u0001\u0007ODqaa;\u0002\t\u0003\u0019i/A\u0004V\u0013V#\u0018\u000e\\:\u000b\u0005\u0001\u000b\u0015AA;j\u0015\t\u00115)A\u0003ta\u0006\u00148N\u0003\u0002E\u000b\u00061\u0011\r]1dQ\u0016T\u0011AR\u0001\u0004_J<\u0007C\u0001%\u0002\u001b\u0005y$aB+J+RLGn]\n\u0004\u0003-\u000b\u0006C\u0001'P\u001b\u0005i%\"\u0001(\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ak%AB!osJ+g\r\u0005\u0002S+6\t1K\u0003\u0002U\u0003\u0006A\u0011N\u001c;fe:\fG.\u0003\u0002W'\n9Aj\\4hS:<\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003\u001d\u000bq\u0003V!C\u0019\u0016{6\tT!T'~su\nV0T)JK\u0005+\u0012#\u0016\u0003q\u0003\"!\u00182\u000e\u0003yS!a\u00181\u0002\t1\fgn\u001a\u0006\u0002C\u0006!!.\u0019<b\u0013\t\u0019gL\u0001\u0004TiJLgnZ\u0001\u0019)\u0006\u0013E*R0D\u0019\u0006\u001b6k\u0018(P)~\u001bFKU%Q\u000b\u0012\u0003\u0013a\u0005+B\u00052+ul\u0011'B'N{6\u000b\u0016*J!\u0016#\u0015\u0001\u0006+B\u00052+ul\u0011'B'N{6\u000b\u0016*J!\u0016#\u0005%\u0001\u000fU\u0003\ncUiX\"M\u0003N\u001bvl\u0015+S\u0013B+EiX*P%R\u000b%\tT#\u0002;Q\u000b%\tT#`\u00072\u000b5kU0T)JK\u0005+\u0012#`'>\u0013F+\u0011\"M\u000b\u0002\n\u0011\u0003Z1uKRKW.\u001a$pe6\fG\u000f^3s+\u0005Y\u0007C\u00017r\u001b\u0005i'B\u00018p\u0003\u00191wN]7bi*\u0011\u0001\u000fY\u0001\u0005i&lW-\u0003\u0002s[\n\tB)\u0019;f)&lWMR8s[\u0006$H/\u001a:\u0002%\u0011\fG/\u001a+j[\u00164uN]7biR,'\u000fI\u0001\u000bM>\u0014X.\u0019;ECR,Gc\u0001<\u0002\u0002A\u0011qO \b\u0003qr\u0004\"!_'\u000e\u0003iT!a\u001f-\u0002\rq\u0012xn\u001c;?\u0013\tiX*\u0001\u0004Qe\u0016$WMZ\u0005\u0003G~T!!`'\t\u000f\u0005\r1\u00021\u0001\u0002\u0006\u0005!A-\u0019;f!\u0011\t9!!\u0004\u000e\u0005\u0005%!bAA\u0006A\u0006!Q\u000f^5m\u0013\u0011\ty!!\u0003\u0003\t\u0011\u000bG/\u001a\u000b\u0004m\u0006M\u0001bBA\u000b\u0019\u0001\u0007\u0011qC\u0001\ni&lWm\u001d;b[B\u00042\u0001TA\r\u0013\r\tY\"\u0014\u0002\u0005\u0019>tw-\u0001\bg_Jl\u0017\r\u001e#ve\u0006$\u0018n\u001c8\u0015\u0007Y\f\t\u0003C\u0004\u0002$5\u0001\r!a\u0006\u0002\u00195LG\u000e\\5tK\u000e|g\u000eZ:\u0002+\u0019|'/\\1u\tV\u0014\u0018\r^5p]Z+'OY8tKR\u0019a/!\u000b\t\u000f\u0005-b\u00021\u0001\u0002\u0018\u0005\u0011Qn]\u0001\u0010E\u0006$8\r\u001b+j[\u00164uN]7bi\u0006\u0001\"-\u0019;dQRKW.\u001a$pe6\fG\u000fI\u0001 E\u0006$8\r\u001b+j[\u00164uN]7bi^KG\u000f['jY2L7/Z2p]\u0012\u001c\u0018\u0001\t2bi\u000eDG+[7f\r>\u0014X.\u0019;XSRDW*\u001b7mSN,7m\u001c8eg\u0002\nqBZ8s[\u0006$()\u0019;dQRKW.\u001a\u000b\nm\u0006e\u0012QHA!\u0003\u0017Bq!a\u000f\u0014\u0001\u0004\t9\"A\u0005cCR\u001c\u0007\u000eV5nK\"9\u0011qH\nA\u0002\u0005]\u0011!\u00042bi\u000eD\u0017J\u001c;feZ\fG\u000eC\u0005\u0002DM\u0001\n\u00111\u0001\u0002F\u0005a1\u000f[8x3fK\u0016,T'T'B\u0019A*a\u0012\n\u0007\u0005%SJA\u0004C_>dW-\u00198\t\u0013\u000553\u0003%AA\u0002\u0005=\u0013\u0001\u0003;j[\u0016TxN\\3\u0011\t\u0005\u001d\u0011\u0011K\u0005\u0005\u0003'\nIA\u0001\u0005US6,'l\u001c8f\u0003e1wN]7bi\n\u000bGo\u00195US6,G\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005e#\u0006BA#\u00037Z#!!\u0018\u0011\t\u0005}\u0013\u0011N\u0007\u0003\u0003CRA!a\u0019\u0002f\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003Oj\u0015AC1o]>$\u0018\r^5p]&!\u00111NA1\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u001aM>\u0014X.\u0019;CCR\u001c\u0007\u000eV5nK\u0012\"WMZ1vYR$C'\u0006\u0002\u0002r)\"\u0011qJA.\u000311wN]7bi:+XNY3s)\r1\u0018q\u000f\u0005\b\u0003s2\u0002\u0019AA>\u0003\u001d\u0011XmY8sIN\u00042\u0001TA?\u0013\r\ty(\u0014\u0002\u0007\t>,(\r\\3\u0002\rUL'k\\8u)\r1\u0018Q\u0011\u0005\b\u0003\u000f;\u0002\u0019AAE\u0003\u001d\u0011X-];fgR\u0004B!a#\u0002\u001a6\u0011\u0011Q\u0012\u0006\u0005\u0003\u001f\u000b\t*\u0001\u0003iiR\u0004(\u0002BAJ\u0003+\u000bqa]3sm2,GO\u0003\u0002\u0002\u0018\u00069!.Y6beR\f\u0017\u0002BAN\u0003\u001b\u0013!\u0003\u0013;uaN+'O\u001e7fiJ+\u0017/^3ti\u0006q\u0001O]3qK:$')Y:f+JLGc\u0002<\u0002\"\u0006\r\u0016q\u0015\u0005\b\u0003\u000fC\u0002\u0019AAE\u0011!\t)\u000b\u0007I\u0001\u0002\u00041\u0018\u0001\u00032bg\u0016\u0004\u0016\r\u001e5\t\u0011\u0005%\u0006\u0004%AA\u0002Y\f\u0001B]3t_V\u00148-Z\u0001\u0019aJ,\u0007/\u001a8e\u0005\u0006\u001cX-\u0016:jI\u0011,g-Y;mi\u0012\u0012TCAAXU\r1\u00181L\u0001\u0019aJ,\u0007/\u001a8e\u0005\u0006\u001cX-\u0016:jI\u0011,g-Y;mi\u0012\u001a\u0014!E2p[6|g\u000eS3bI\u0016\u0014hj\u001c3fgR!\u0011qWAk!\u0019\tI,a1\u0002J:!\u00111XA`\u001d\rI\u0018QX\u0005\u0002\u001d&\u0019\u0011\u0011Y'\u0002\u000fA\f7m[1hK&!\u0011QYAd\u0005\r\u0019V-\u001d\u0006\u0004\u0003\u0003l\u0005\u0003BAf\u0003#l!!!4\u000b\u0007\u0005=W*A\u0002y[2LA!a5\u0002N\n!aj\u001c3f\u0011\u001d\t9i\u0007a\u0001\u0003\u0013\u000baB^5{\u0011\u0016\fG-\u001a:O_\u0012,7\u000f\u0006\u0003\u00028\u0006m\u0007bBAD9\u0001\u0007\u0011\u0011R\u0001\u0016I\u0006$\u0018\rV1cY\u0016\u001c\b*Z1eKJtu\u000eZ3t)\u0011\t9,!9\t\u000f\u0005\u001dU\u00041\u0001\u0002\n\u0006y\u0001.Z1eKJ\u001c\u0006/\u0019:l!\u0006<W\r\u0006\t\u00028\u0006\u001d\u0018\u0011^Aw\u0003o\u0014\tAa\u0003\u0003\u0010!9\u0011q\u0011\u0010A\u0002\u0005%\u0005BBAv=\u0001\u0007a/A\u0003uSRdW\r\u0003\u0005\u0002pz!\t\u0019AAy\u0003\u001d\u0019wN\u001c;f]R\u0004R\u0001TAz\u0003oK1!!>N\u0005!a$-\u001f8b[\u0016t\u0004bBA}=\u0001\u0007\u00111`\u0001\nC\u000e$\u0018N^3UC\n\u00042\u0001SA\u007f\u0013\r\typ\u0010\u0002\u000b'B\f'o[+J)\u0006\u0014\u0007\"\u0003B\u0002=A\u0005\t\u0019\u0001B\u0003\u0003!AW\r\u001c9UKb$\b\u0003\u0002'\u0003\bYL1A!\u0003N\u0005\u0019y\u0005\u000f^5p]\"I!Q\u0002\u0010\u0011\u0002\u0003\u0007\u0011QI\u0001\u0012g\"|wOV5tk\u0006d\u0017N_1uS>t\u0007\"\u0003B\t=A\u0005\t\u0019AA#\u00035)8/\u001a#bi\u0006$\u0016M\u00197fg\u0006I\u0002.Z1eKJ\u001c\u0006/\u0019:l!\u0006<W\r\n3fM\u0006,H\u000e\u001e\u00136+\t\u00119B\u000b\u0003\u0003\u0006\u0005m\u0013!\u00075fC\u0012,'o\u00159be.\u0004\u0016mZ3%I\u00164\u0017-\u001e7uIY\n\u0011\u0004[3bI\u0016\u00148\u000b]1sWB\u000bw-\u001a\u0013eK\u001a\fW\u000f\u001c;%o\u0005q!-Y:jGN\u0003\u0018M]6QC\u001e,GCCA\\\u0005C\u0011\u0019C!\n\u0003(!9\u0011q\u0011\u0012A\u0002\u0005%\u0005\u0002CAxE\u0011\u0005\r!!=\t\r\u0005-(\u00051\u0001w\u0011%\u0011\tB\tI\u0001\u0002\u0004\t)%\u0001\rcCNL7m\u00159be.\u0004\u0016mZ3%I\u00164\u0017-\u001e7uIQ\nA\u0002\\5ti&tw\rV1cY\u0016,BAa\f\u0003FQ!\u0012q\u0017B\u0019\u0005o\u00119F!\u0019\u0003f\t%$Q\u000eB9\u0005kBqAa\r%\u0001\u0004\u0011)$A\u0004iK\u0006$WM]:\u0011\u000b\u0005e\u00161\u0019<\t\u000f\teB\u00051\u0001\u0003<\u0005yq-\u001a8fe\u0006$X\rR1uCJ{w\u000fE\u0004M\u0005{\u0011\t%a.\n\u0007\t}RJA\u0005Gk:\u001cG/[8ocA!!1\tB#\u0019\u0001!qAa\u0012%\u0005\u0004\u0011IEA\u0001U#\u0011\u0011YE!\u0015\u0011\u00071\u0013i%C\u0002\u0003P5\u0013qAT8uQ&tw\rE\u0002M\u0005'J1A!\u0016N\u0005\r\te.\u001f\u0005\b\u00053\"\u0003\u0019\u0001B.\u0003\u0011!\u0017\r^1\u0011\r\u0005e&Q\fB!\u0013\u0011\u0011y&a2\u0003\u0011%#XM]1cY\u0016D\u0011Ba\u0019%!\u0003\u0005\r!!\u0012\u0002\u0015\u0019L\u00070\u001a3XS\u0012$\b\u000eC\u0005\u0003h\u0011\u0002\n\u00111\u0001\u0003\u0006\u0005\u0011\u0011\u000e\u001a\u0005\n\u0005W\"\u0003\u0013!a\u0001\u0005k\tQ\u0002[3bI\u0016\u00148\t\\1tg\u0016\u001c\b\"\u0003B8IA\u0005\t\u0019AA#\u0003E\u0019HO]5qKJ{wo],ji\"\u001c5o\u001d\u0005\n\u0005g\"\u0003\u0013!a\u0001\u0003\u000b\n\u0001b]8si\u0006\u0014G.\u001a\u0005\n\u0005o\"\u0003\u0013!a\u0001\u0005s\na\u0002^8pYRL\u0007\u000fS3bI\u0016\u00148\u000f\u0005\u0004\u0002:\u0006\r'QA\u0001\u0017Y&\u001cH/\u001b8h)\u0006\u0014G.\u001a\u0013eK\u001a\fW\u000f\u001c;%iU!\u0011q\u000bB@\t\u001d\u00119%\nb\u0001\u0005\u0013\na\u0003\\5ti&tw\rV1cY\u0016$C-\u001a4bk2$H%N\u000b\u0005\u0005+\u0011)\tB\u0004\u0003H\u0019\u0012\rA!\u0013\u0002-1L7\u000f^5oOR\u000b'\r\\3%I\u00164\u0017-\u001e7uIY*BAa#\u0003\u0010V\u0011!Q\u0012\u0016\u0005\u0005k\tY\u0006B\u0004\u0003H\u001d\u0012\rA!\u0013\u0002-1L7\u000f^5oOR\u000b'\r\\3%I\u00164\u0017-\u001e7uI]*B!a\u0016\u0003\u0016\u00129!q\t\u0015C\u0002\t%\u0013A\u00067jgRLgn\u001a+bE2,G\u0005Z3gCVdG\u000f\n\u001d\u0016\t\u0005]#1\u0014\u0003\b\u0005\u000fJ#\u0019\u0001B%\u0003Ya\u0017n\u001d;j]\u001e$\u0016M\u00197fI\u0011,g-Y;mi\u0012JT\u0003\u0002BQ\u0005K+\"Aa)+\t\te\u00141\f\u0003\b\u0005\u000fR#\u0019\u0001B%\u0003=i\u0017m[3Qe><'/Z:t\u0005\u0006\u0014HCDA\\\u0005W\u0013)L!/\u0003>\n\u0005'1\u001a\u0005\b\u0005[[\u0003\u0019\u0001BX\u0003\u001d\u0019H/\u0019:uK\u0012\u00042\u0001\u0014BY\u0013\r\u0011\u0019,\u0014\u0002\u0004\u0013:$\bb\u0002B\\W\u0001\u0007!qV\u0001\nG>l\u0007\u000f\\3uK\u0012DqAa/,\u0001\u0004\u0011y+\u0001\u0004gC&dW\r\u001a\u0005\b\u0005\u007f[\u0003\u0019\u0001BX\u0003\u001d\u00198.\u001b9qK\u0012DqAa1,\u0001\u0004\u0011)-A\tsK\u0006\u001cxN\u001c+p\u001dVl7*\u001b7mK\u0012\u0004ba\u001eBdm\n=\u0016b\u0001Be\u007f\n\u0019Q*\u00199\t\u000f\t57\u00061\u0001\u00030\u0006)Ao\u001c;bY\u0006\u00112\u000f[8x\t\u0006<g+\u001b>G_J\u001cF/Y4f)\u0019\t9La5\u0003X\"9!Q\u001b\u0017A\u0002\t=\u0016aB:uC\u001e,\u0017\n\u001a\u0005\b\u00053d\u0003\u0019\u0001Bn\u0003\u00159'/\u00199i!\u0015a%q\u0001Bo!\u0011\u0011yN!:\u000e\u0005\t\u0005(b\u0001Br\u007f\u0005)1oY8qK&!!q\u001dBq\u0005E\u0011F\tR(qKJ\fG/[8o\u000fJ\f\u0007\u000f[\u0001\u0011g\"|w\u000fR1h-&Thi\u001c:K_\n$bA!<\u0003x\nm\bC\u0002Bx\u0005k\fI-\u0004\u0002\u0003r*\u0019!1_'\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002F\nE\bb\u0002B}[\u0001\u0007!qV\u0001\u0006U>\u0014\u0017\n\u001a\u0005\b\u0005{l\u0003\u0019\u0001B\u0000\u0003\u00199'/\u00199igB1!q\u001eB{\u0005;\f!b\u001d5po\u0012\u000bwMV5{)\u0019\u0011io!\u0002\u0004\b!9!Q \u0018A\u0002\t}\bbBB\u0005]\u0001\u0007\u0011QI\u0001\u0007M>\u0014(j\u001c2\u0002\u000fQ|w\u000e\u001c;jaR1\u0011qWB\b\u0007'Aaa!\u00050\u0001\u00041\u0018\u0001\u0002;fqRDaa!\u00060\u0001\u00041\u0018\u0001\u00039pg&$\u0018n\u001c8\u0002\u001f5\f7.\u001a#fg\u000e\u0014\u0018\u000e\u001d;j_:$\u0002ba\u0007\u0004\"\r\u00152\u0011\u0006\t\u0005\u0003\u0017\u001ci\"\u0003\u0003\u0004 \u00055'a\u0002(pI\u0016\u001cV-\u001d\u0005\u0007\u0007G\u0001\u0004\u0019\u0001<\u0002\t\u0011,7o\u0019\u0005\u0007\u0007O\u0001\u0004\u0019\u0001<\u0002\u0017\t\f7/\u001a)bi\",&/\u001b\u0005\n\u0007W\u0001\u0004\u0013!a\u0001\u0003\u000b\n\u0011\u0002\u001d7bS:$V\r\u001f;\u000235\f7.\u001a#fg\u000e\u0014\u0018\u000e\u001d;j_:$C-\u001a4bk2$HeM\u0001\u0013I\u0016\u001cw\u000eZ3V%2\u0003\u0016M]1nKR,'\u000fF\u0002w\u0007gAaa!\u000e3\u0001\u00041\u0018\u0001C;sYB\u000b'/Y7\u0015\t\re2q\n\t\u0005\u0007w\u0019Y%\u0004\u0002\u0004>)!!1_B \u0015\u0011\tYa!\u0011\u000b\u0007Q\u001b\u0019E\u0003\u0003\u0004F\r\u001d\u0013A\u00026feN,\u0017PC\u0002\u0004J\u0015\u000b\u0011b\u001a7bgN4\u0017n\u001d5\n\t\r53Q\b\u0002\u0015\u001bVdG/\u001b<bYV,Gm\u0015;sS:<W*\u00199\t\u000f\rE3\u00071\u0001\u0004T\u00051\u0001/\u0019:b[N\u0004ba!\u0016\u0004dY4XBAB,\u0015\u0011\u0019Ifa\u0017\u0002\t\r|'/\u001a\u0006\u0005\u0007;\u001ay&\u0001\u0002sg*!1\u0011MAK\u0003\t98/\u0003\u0003\u0004f\r]#AD'vYRLg/\u00197vK\u0012l\u0015\r]\u0001\u0012O\u0016$H+[7f5>tWm\u00144gg\u0016$HC\u0001BX\u0003!i\u0017m[3Ie\u00164Gc\u0002<\u0004p\rM4Q\u000f\u0005\b\u0007c*\u0004\u0019AA#\u0003\u0015\u0001(o\u001c=z\u0011\u0019\u00119'\u000ea\u0001m\"11qO\u001bA\u0002Y\f\u0001b\u001c:jO\"\u0013XMZ\u0001\u0013EVLG\u000eZ#se>\u0014(+Z:q_:\u001cX\r\u0006\u0004\u0004~\r\r5Q\u0013\t\u0005\u0007+\u001ay(\u0003\u0003\u0004\u0002\u000e]#\u0001\u0003*fgB|gn]3\t\u000f\r\u0015e\u00071\u0001\u0004\b\u000611\u000f^1ukN\u0004Ba!#\u0004\u0010:!1QKBF\u0013\u0011\u0019iia\u0016\u0002\u0011I+7\u000f]8og\u0016LAa!%\u0004\u0014\n11\u000b^1ukNTAa!$\u0004X!11q\u0013\u001cA\u0002Y\f1!\\:h\u0003M!WO]1uS>tG)\u0019;b!\u0006$G-\u001b8h)\u0011\u0019ija+\u0011\u000b1\u001byja)\n\u0007\r\u0005VJA\u0003BeJ\f\u0017\u0010E\u0004M\u0007K\u000b9b!+\n\u0007\r\u001dVJ\u0001\u0004UkBdWM\r\t\u0007o\n\u001dg/a\u001f\t\u000f\r5v\u00071\u0001\u00040\u00061a/\u00197vKN\u0004R\u0001TBP\u0007c\u0003r\u0001TBS\u0003/\u0019\u0019\fE\u0004\u0002\b\rUfoa.\n\t\t%\u0017\u0011\u0002\t\u0004;\u000ee\u0016bAA\u000e=\u0006iA-\u001a;bS2\u001cX+\u0013(pI\u0016$b!a.\u0004@\u000e\r\u0007bBBaq\u0001\u0007\u0011QI\u0001\fSNlU\u000f\u001c;jY&tW\r\u0003\u0004\u0004Fb\u0002\rA^\u0001\b[\u0016\u001c8/Y4f\u0003E)%KU(S?\u000ec\u0015iU*`%\u0016;U\tW\u000b\u0003\u0007\u0017\u0004Ba!4\u0004V6\u00111q\u001a\u0006\u0005\u0007#\u001c\u0019.\u0001\u0005nCR\u001c\u0007.\u001b8h\u0015\r\tY!T\u0005\u0005\u0007/\u001cyMA\u0003SK\u001e,\u00070\u0001\nF%J{%kX\"M\u0003N\u001bvLU#H\u000bb\u0003\u0013\u0001D3se>\u00148+^7nCJLH\u0003BBp\u0007C\u0004b\u0001TBSm\u0006\u0015\u0003BBBrw\u0001\u0007a/\u0001\u0007feJ|'/T3tg\u0006<W-\u0001\tfeJ|'/T3tg\u0006<WmQ3mYR!\u0011qWBu\u0011\u0019\u0019\u0019\u000f\u0010a\u0001m\u00061bm\u001c:nCRLU\u000e]8si*\u000bg/Y*de&\u0004H\u000fF\u0004w\u0007_\u001c\tp!>\t\u000f\u0005\u001dU\b1\u0001\u0002\n\"111_\u001fA\u0002Y\f!b]8ve\u000e,g)\u001b7f\u0011\u001d\u001990\u0010a\u0001\u0007s\fq!\\3uQ>$7\u000f\u0005\u0003M\u0007w4\u0018bAB\u007f\u001b\nQAH]3qK\u0006$X\r\u001a "
)
public final class UIUtils {
   public static String formatImportJavaScript(final HttpServletRequest request, final String sourceFile, final Seq methods) {
      return UIUtils$.MODULE$.formatImportJavaScript(request, sourceFile, methods);
   }

   public static Seq errorMessageCell(final String errorMessage) {
      return UIUtils$.MODULE$.errorMessageCell(errorMessage);
   }

   public static Tuple2 errorSummary(final String errorMessage) {
      return UIUtils$.MODULE$.errorSummary(errorMessage);
   }

   public static Seq detailsUINode(final boolean isMultiline, final String message) {
      return UIUtils$.MODULE$.detailsUINode(isMultiline, message);
   }

   public static Tuple2[] durationDataPadding(final Tuple2[] values) {
      return UIUtils$.MODULE$.durationDataPadding(values);
   }

   public static Response buildErrorResponse(final Response.Status status, final String msg) {
      return UIUtils$.MODULE$.buildErrorResponse(status, msg);
   }

   public static String makeHref(final boolean proxy, final String id, final String origHref) {
      return UIUtils$.MODULE$.makeHref(proxy, id, origHref);
   }

   public static int getTimeZoneOffset() {
      return UIUtils$.MODULE$.getTimeZoneOffset();
   }

   public static MultivaluedStringMap decodeURLParameter(final MultivaluedMap params) {
      return UIUtils$.MODULE$.decodeURLParameter(params);
   }

   public static String decodeURLParameter(final String urlParam) {
      return UIUtils$.MODULE$.decodeURLParameter(urlParam);
   }

   public static boolean makeDescription$default$3() {
      return UIUtils$.MODULE$.makeDescription$default$3();
   }

   public static NodeSeq makeDescription(final String desc, final String basePathUri, final boolean plainText) {
      return UIUtils$.MODULE$.makeDescription(desc, basePathUri, plainText);
   }

   public static Seq tooltip(final String text, final String position) {
      return UIUtils$.MODULE$.tooltip(text, position);
   }

   public static scala.collection.Seq showDagVizForJob(final int jobId, final scala.collection.Seq graphs) {
      return UIUtils$.MODULE$.showDagVizForJob(jobId, graphs);
   }

   public static Seq showDagVizForStage(final int stageId, final Option graph) {
      return UIUtils$.MODULE$.showDagVizForStage(stageId, graph);
   }

   public static Seq makeProgressBar(final int started, final int completed, final int failed, final int skipped, final Map reasonToNumKilled, final int total) {
      return UIUtils$.MODULE$.makeProgressBar(started, completed, failed, skipped, reasonToNumKilled, total);
   }

   public static Seq listingTable$default$9() {
      return UIUtils$.MODULE$.listingTable$default$9();
   }

   public static boolean listingTable$default$8() {
      return UIUtils$.MODULE$.listingTable$default$8();
   }

   public static boolean listingTable$default$7() {
      return UIUtils$.MODULE$.listingTable$default$7();
   }

   public static Seq listingTable$default$6() {
      return UIUtils$.MODULE$.listingTable$default$6();
   }

   public static Option listingTable$default$5() {
      return UIUtils$.MODULE$.listingTable$default$5();
   }

   public static boolean listingTable$default$4() {
      return UIUtils$.MODULE$.listingTable$default$4();
   }

   public static Seq listingTable(final Seq headers, final Function1 generateDataRow, final Iterable data, final boolean fixedWidth, final Option id, final Seq headerClasses, final boolean stripeRowsWithCss, final boolean sortable, final Seq tooltipHeaders) {
      return UIUtils$.MODULE$.listingTable(headers, generateDataRow, data, fixedWidth, id, headerClasses, stripeRowsWithCss, sortable, tooltipHeaders);
   }

   public static boolean basicSparkPage$default$4() {
      return UIUtils$.MODULE$.basicSparkPage$default$4();
   }

   public static Seq basicSparkPage(final HttpServletRequest request, final Function0 content, final String title, final boolean useDataTables) {
      return UIUtils$.MODULE$.basicSparkPage(request, content, title, useDataTables);
   }

   public static boolean headerSparkPage$default$7() {
      return UIUtils$.MODULE$.headerSparkPage$default$7();
   }

   public static boolean headerSparkPage$default$6() {
      return UIUtils$.MODULE$.headerSparkPage$default$6();
   }

   public static Option headerSparkPage$default$5() {
      return UIUtils$.MODULE$.headerSparkPage$default$5();
   }

   public static Seq headerSparkPage(final HttpServletRequest request, final String title, final Function0 content, final SparkUITab activeTab, final Option helpText, final boolean showVisualization, final boolean useDataTables) {
      return UIUtils$.MODULE$.headerSparkPage(request, title, content, activeTab, helpText, showVisualization, useDataTables);
   }

   public static Seq dataTablesHeaderNodes(final HttpServletRequest request) {
      return UIUtils$.MODULE$.dataTablesHeaderNodes(request);
   }

   public static Seq vizHeaderNodes(final HttpServletRequest request) {
      return UIUtils$.MODULE$.vizHeaderNodes(request);
   }

   public static Seq commonHeaderNodes(final HttpServletRequest request) {
      return UIUtils$.MODULE$.commonHeaderNodes(request);
   }

   public static String prependBaseUri$default$3() {
      return UIUtils$.MODULE$.prependBaseUri$default$3();
   }

   public static String prependBaseUri$default$2() {
      return UIUtils$.MODULE$.prependBaseUri$default$2();
   }

   public static String prependBaseUri(final HttpServletRequest request, final String basePath, final String resource) {
      return UIUtils$.MODULE$.prependBaseUri(request, basePath, resource);
   }

   public static String uiRoot(final HttpServletRequest request) {
      return UIUtils$.MODULE$.uiRoot(request);
   }

   public static String formatNumber(final double records) {
      return UIUtils$.MODULE$.formatNumber(records);
   }

   public static TimeZone formatBatchTime$default$4() {
      return UIUtils$.MODULE$.formatBatchTime$default$4();
   }

   public static boolean formatBatchTime$default$3() {
      return UIUtils$.MODULE$.formatBatchTime$default$3();
   }

   public static String formatBatchTime(final long batchTime, final long batchInterval, final boolean showYYYYMMSS, final TimeZone timezone) {
      return UIUtils$.MODULE$.formatBatchTime(batchTime, batchInterval, showYYYYMMSS, timezone);
   }

   public static String formatDurationVerbose(final long ms) {
      return UIUtils$.MODULE$.formatDurationVerbose(ms);
   }

   public static String formatDuration(final long milliseconds) {
      return UIUtils$.MODULE$.formatDuration(milliseconds);
   }

   public static String formatDate(final long timestamp) {
      return UIUtils$.MODULE$.formatDate(timestamp);
   }

   public static String formatDate(final Date date) {
      return UIUtils$.MODULE$.formatDate(date);
   }

   public static String TABLE_CLASS_STRIPED_SORTABLE() {
      return UIUtils$.MODULE$.TABLE_CLASS_STRIPED_SORTABLE();
   }

   public static String TABLE_CLASS_STRIPED() {
      return UIUtils$.MODULE$.TABLE_CLASS_STRIPED();
   }

   public static String TABLE_CLASS_NOT_STRIPED() {
      return UIUtils$.MODULE$.TABLE_CLASS_NOT_STRIPED();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return UIUtils$.MODULE$.LogStringContext(sc);
   }
}
