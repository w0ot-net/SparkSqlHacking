package org.apache.spark.deploy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.security.PrivilegedExceptionAction;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.UserGroupInformation.AuthenticationMethod;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.security.token.TokenIdentifier;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenIdentifier;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.matching.Regex;
import scala.util.matching.UnanchoredRegex;

@ScalaSignature(
   bytes = "\u0006\u0005\r]c!\u0002%J\u0001-\u000b\u0006\"\u00020\u0001\t\u0003\u0001\u0007bB2\u0001\u0005\u0004%I\u0001\u001a\u0005\u0007S\u0002\u0001\u000b\u0011B3\t\u000f)\u0004!\u0019!C\u0001W\"11\u000f\u0001Q\u0001\n1DQ\u0001\u001e\u0001\u0005\u0002UDQA \u0001\u0005\u0002}Dq!!\u0004\u0001\t\u0003\ty\u0001C\u0004\u0002\u001a\u0001!\t!a\u0007\t\u000f\u0005\r\u0002\u0001\"\u0001\u0002&!9\u00111\u0005\u0001\u0005\u0002\u0005-\u0002bBA1\u0001\u0011\u0005\u00111\r\u0005\b\u0003S\u0002A\u0011AA6\u0011\u001d\ty\u0007\u0001C\u0001\u0003cBq!!!\u0001\t\u0003\t\u0019\tC\u0004\u0002\u0010\u0002!\t!!%\t\u0011\u0005m\u0005\u0001\"\u0001L\u0003;C\u0001\"!-\u0001\t\u0003Y\u00151\u0017\u0005\t\u0003{\u0003A\u0011A&\u00024\"9\u0011q\u0018\u0001\u0005\u0002\u0005\u0005\u0007bBA`\u0001\u0011\u0005\u00111\u001f\u0005\b\u0003w\u0004A\u0011AA\u007f\u0011\u001d\u0011I\u0001\u0001C\u0001\u0005\u0017AqA!\u0003\u0001\t\u0003\u0011\t\u0002C\u0004\u0003\u0018\u0001!\tA!\u0007\t\u000f\t]\u0001\u0001\"\u0001\u0003\u001e!I!1\u0005\u0001C\u0002\u0013%!Q\u0005\u0005\t\u0005o\u0001\u0001\u0015!\u0003\u0003(!9!\u0011\b\u0001\u0005\u0002\tm\u0002\u0002\u0003B\"\u0001\u0011\u00051J!\u0012\t\u0011\tE\u0003\u0001\"\u0001L\u0005'BqAa\u001f\u0001\t\u0003\u0011i\bC\u0004\u0003\u0002\u0002!\tAa!\t\u000f\t%\u0005\u0001\"\u0001\u0003\f\u001eA!\u0011S%\t\u0002-\u0013\u0019JB\u0004I\u0013\"\u00051J!&\t\ry#C\u0011\u0001BL\u0011)\u0011I\n\nEC\u0002\u0013%!1\u0014\u0005\u000b\u0005;##\u0019!C\u0001\u0017\n}\u0005\u0002\u0003BTI\u0001\u0006IA!)\t\u0015\t%FE1A\u0005\u0002-\u0013Y\u000b\u0003\u0005\u0003<\u0012\u0002\u000b\u0011\u0002BW\u0011)\u0011i\f\nb\u0001\n\u0003I%1\u0016\u0005\t\u0005\u007f#\u0003\u0015!\u0003\u0003.\"Q!\u0011\u0019\u0013C\u0002\u0013\u00051Ja+\t\u0011\t\rG\u0005)A\u0005\u0005[C!B!2%\u0005\u0004%\t!\u0013BV\u0011!\u00119\r\nQ\u0001\n\t5\u0006B\u0003BeI\t\u0007I\u0011A%\u0003,\"A!1\u001a\u0013!\u0002\u0013\u0011i\u000b\u0003\u0006\u0003N\u0012\u0012\r\u0011\"\u0001J\u0005WC\u0001Ba4%A\u0003%!Q\u0016\u0005\u000b\u0005#$#\u0019!C\u0001\u0013\n-\u0006\u0002\u0003BjI\u0001\u0006IA!,\t\u0015\tUGE1A\u0005\u0002%\u0013Y\u000b\u0003\u0005\u0003X\u0012\u0002\u000b\u0011\u0002BW\u0011)\u0011I\u000e\nb\u0001\n\u0003I%1\u0016\u0005\t\u00057$\u0003\u0015!\u0003\u0003.\"Q!Q\u001c\u0013C\u0002\u0013\u0005\u0011Ja+\t\u0011\t}G\u0005)A\u0005\u0005[CqA!9%\t\u0003\u0011Y\n\u0003\u0005\u0002j\u0011\"\ta\u0013Br\u0011\u001d\tI\u0002\nC\u0005\u0005OD\u0001B!<%\t\u0003I%q\u001e\u0005\u000b\u0007\u0007!\u0003R1A\u0005\n\r\u0015\u0001bBB\u0010I\u0011%1\u0011\u0005\u0005\b\u0003G!C\u0011BB\u0013\u0011\u001d\t\t\u0007\nC\u0005\u0007WAqa!\r%\t\u0003\u0019\u0019\u0004C\u0004\u0004<\u0011\"\ta!\u0010\t\u000f\r=C\u0005\"\u0001\u0004R\ty1\u000b]1sW\"\u000bGm\\8q+RLGN\u0003\u0002K\u0017\u00061A-\u001a9m_fT!\u0001T'\u0002\u000bM\u0004\u0018M]6\u000b\u00059{\u0015AB1qC\u000eDWMC\u0001Q\u0003\ry'oZ\n\u0004\u0001IC\u0006CA*W\u001b\u0005!&\"A+\u0002\u000bM\u001c\u0017\r\\1\n\u0005]#&AB!osJ+g\r\u0005\u0002Z96\t!L\u0003\u0002\\\u0017\u0006A\u0011N\u001c;fe:\fG.\u0003\u0002^5\n9Aj\\4hS:<\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003\u0005\u0004\"A\u0019\u0001\u000e\u0003%\u000b\u0011b\u001d9be.\u001cuN\u001c4\u0016\u0003\u0015\u0004\"AZ4\u000e\u0003-K!\u0001[&\u0003\u0013M\u0003\u0018M]6D_:4\u0017AC:qCJ\\7i\u001c8gA\u0005!1m\u001c8g+\u0005a\u0007CA7r\u001b\u0005q'B\u00016p\u0015\t\u0001X*\u0001\u0004iC\u0012|w\u000e]\u0005\u0003e:\u0014QbQ8oM&<WO]1uS>t\u0017!B2p]\u001a\u0004\u0013A\u0004:v]\u0006\u001b8\u000b]1sWV\u001bXM\u001d\u000b\u0003mf\u0004\"aU<\n\u0005a$&\u0001B+oSRDQA\u001f\u0004A\u0002m\fAAZ;oGB\u00191\u000b <\n\u0005u$&!\u0003$v]\u000e$\u0018n\u001c81\u0003=\u0019'/Z1uKN\u0003\u0018M]6Vg\u0016\u0014HCAA\u0001!\u0011\t\u0019!!\u0003\u000e\u0005\u0005\u0015!bAA\u0004_\u0006A1/Z2ve&$\u00180\u0003\u0003\u0002\f\u0005\u0015!\u0001F+tKJ<%o\\;q\u0013:4wN]7bi&|g.A\nue\u0006t7OZ3s\u0007J,G-\u001a8uS\u0006d7\u000fF\u0003w\u0003#\t)\u0002C\u0004\u0002\u0014!\u0001\r!!\u0001\u0002\rM|WO]2f\u0011\u001d\t9\u0002\u0003a\u0001\u0003\u0003\tA\u0001Z3ti\u0006A\u0013\r\u001d9f]\u0012\u001c6'\u00118e'B\f'o\u001b%bI>|\u0007\u000fS5wK\u000e{gNZ5hkJ\fG/[8ogR)a/!\b\u0002 !)!.\u0003a\u0001K\"1\u0011\u0011E\u0005A\u00021\f!\u0002[1e_>\u00048i\u001c8g\u0003a\t\u0007\u000f]3oIN\u0003\u0018M]6IC\u0012|w\u000e]\"p]\u001aLwm\u001d\u000b\u0006m\u0006\u001d\u0012\u0011\u0006\u0005\u0006U*\u0001\r!\u001a\u0005\u0007\u0003CQ\u0001\u0019\u00017\u0015\u000bY\fi#!\u0014\t\u000f\u0005=2\u00021\u0001\u00022\u000511O]2NCB\u0004\u0002\"a\r\u0002B\u0005\u001d\u0013q\t\b\u0005\u0003k\ti\u0004E\u0002\u00028Qk!!!\u000f\u000b\u0007\u0005mr,\u0001\u0004=e>|GOP\u0005\u0004\u0003\u007f!\u0016A\u0002)sK\u0012,g-\u0003\u0003\u0002D\u0005\u0015#aA'ba*\u0019\u0011q\b+\u0011\t\u0005M\u0012\u0011J\u0005\u0005\u0003\u0017\n)E\u0001\u0004TiJLgn\u001a\u0005\b\u0003\u001fZ\u0001\u0019AA)\u0003\u001d!Wm\u001d;NCB\u0004\u0002\"a\u0015\u0002^\u0005\u001d\u0013qI\u0007\u0003\u0003+RA!a\u0016\u0002Z\u00059Q.\u001e;bE2,'bAA.)\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005}\u0013Q\u000b\u0002\b\u0011\u0006\u001c\b.T1q\u0003Y\t\u0007\u000f]3oIN\u0003\u0018M]6ISZ,7i\u001c8gS\u001e\u001cH#\u0002<\u0002f\u0005\u001d\u0004bBA\u0018\u0019\u0001\u0007\u0011\u0011\u0007\u0005\b\u0003\u001fb\u0001\u0019AA)\u0003AqWm^\"p]\u001aLw-\u001e:bi&|g\u000eF\u0002m\u0003[BQA[\u0007A\u0002\u0015\fa\"\u00193e\u0007J,G-\u001a8uS\u0006d7\u000fF\u0002w\u0003gBaA\u001b\bA\u0002\u0005U\u0004\u0003BA<\u0003{j!!!\u001f\u000b\u0007\u0005mt.\u0001\u0004nCB\u0014X\rZ\u0005\u0005\u0003\u007f\nIHA\u0004K_\n\u001cuN\u001c4\u00023\u0005$GmQ;se\u0016tG/V:fe\u000e\u0013X\rZ3oi&\fGn\u001d\u000b\u0004m\u0006\u0015\u0005bBAD\u001f\u0001\u0007\u0011\u0011R\u0001\u0006GJ,Gm\u001d\t\u0005\u0003\u0007\tY)\u0003\u0003\u0002\u000e\u0006\u0015!aC\"sK\u0012,g\u000e^5bYN\f1\u0003\\8hS:,6/\u001a:Ge>l7*Z=uC\n$RA^AJ\u0003/Cq!!&\u0011\u0001\u0004\t9%A\u0007qe&t7-\u001b9bY:\u000bW.\u001a\u0005\b\u00033\u0003\u0002\u0019AA$\u00039YW-\u001f;bE\u001aKG.\u001a8b[\u0016\f1#\u00193e\t\u0016dWmZ1uS>tGk\\6f]N$RA^AP\u0003_Cq!!)\u0012\u0001\u0004\t\u0019+\u0001\u0004u_.,gn\u001d\t\u0006'\u0006\u0015\u0016\u0011V\u0005\u0004\u0003O#&!B!se\u0006L\bcA*\u0002,&\u0019\u0011Q\u0016+\u0003\t\tKH/\u001a\u0005\u0006GF\u0001\r!Z\u0001\u001fO\u0016$hi\u0015\"zi\u0016\u001c(+Z1e\u001f:$\u0006N]3bI\u000e\u000bG\u000e\u001c2bG.$\"!!.\u0011\tMc\u0018q\u0017\t\u0004'\u0006e\u0016bAA^)\n!Aj\u001c8h\u0003\u0005:W\r\u001e$T\u0005f$Xm],sSR$XM\\(o)\"\u0014X-\u00193DC2d'-Y2l\u0003Aa\u0017n\u001d;MK\u000647\u000b^1ukN,7\u000f\u0006\u0004\u0002D\u0006\u0005\u0018\u0011\u001e\t\u0007\u0003\u000b\fy-!6\u000f\t\u0005\u001d\u00171\u001a\b\u0005\u0003o\tI-C\u0001V\u0013\r\ti\rV\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\t.a5\u0003\u0007M+\u0017OC\u0002\u0002NR\u0003B!a6\u0002^6\u0011\u0011\u0011\u001c\u0006\u0004\u00037|\u0017A\u00014t\u0013\u0011\ty.!7\u0003\u0015\u0019KG.Z*uCR,8\u000fC\u0004\u0002\\R\u0001\r!a9\u0011\t\u0005]\u0017Q]\u0005\u0005\u0003O\fIN\u0001\u0006GS2,7+_:uK6Dq!a;\u0015\u0001\u0004\ti/\u0001\u0005cCN,\u0007+\u0019;i!\u0011\t9.a<\n\t\u0005E\u0018\u0011\u001c\u0002\u0005!\u0006$\b\u000e\u0006\u0004\u0002D\u0006U\u0018q\u001f\u0005\b\u00037,\u0002\u0019AAr\u0011\u001d\tI0\u0006a\u0001\u0003+\f!BY1tKN#\u0018\r^;t\u0003)I7o\u00127pEB\u000bG\u000f\u001b\u000b\u0005\u0003\u007f\u0014)\u0001E\u0002T\u0005\u0003I1Aa\u0001U\u0005\u001d\u0011un\u001c7fC:DqAa\u0002\u0017\u0001\u0004\ti/A\u0004qCR$XM\u001d8\u0002\u0011\u001ddwN\u0019)bi\"$BA!\u0004\u0003\u0010A1\u0011QYAh\u0003[DqAa\u0002\u0018\u0001\u0004\ti\u000f\u0006\u0004\u0003\u000e\tM!Q\u0003\u0005\b\u00037D\u0002\u0019AAr\u0011\u001d\u00119\u0001\u0007a\u0001\u0003[\f1c\u001a7pEB\u000bG\u000f[%g\u001d\u0016\u001cWm]:bef$BA!\u0004\u0003\u001c!9!qA\rA\u0002\u00055HC\u0002B\u0007\u0005?\u0011\t\u0003C\u0004\u0002\\j\u0001\r!a9\t\u000f\t\u001d!\u00041\u0001\u0002n\u0006\u0019\u0002*\u0011#P\u001fB{6i\u0014(G?B\u000bE\u000bV#S\u001dV\u0011!q\u0005\t\u0005\u0005S\u0011\u0019$\u0004\u0002\u0003,)!!Q\u0006B\u0018\u0003!i\u0017\r^2iS:<'b\u0001B\u0019)\u0006!Q\u000f^5m\u0013\u0011\u0011)Da\u000b\u0003\u001fUs\u0017M\\2i_J,GMU3hKb\fA\u0003S!E\u001f>\u0003vlQ(O\r~\u0003\u0016\t\u0016+F%:\u0003\u0013!G:vEN$\u0018\u000e^;uK\"\u000bGm\\8q-\u0006\u0014\u0018.\u00192mKN$b!a\u0012\u0003>\t\u0005\u0003b\u0002B ;\u0001\u0007\u0011qI\u0001\u0005i\u0016DH\u000f\u0003\u0004\u0002\"u\u0001\r\u0001\\\u0001\u000bIVl\u0007\u000fV8lK:\u001cH\u0003\u0002B$\u0005\u001b\u0002b!!2\u0003J\u0005\u001d\u0013\u0002\u0002B&\u0003'\u0014\u0001\"\u0013;fe\u0006\u0014G.\u001a\u0005\b\u0005\u001fr\u0002\u0019AAE\u0003-\u0019'/\u001a3f]RL\u0017\r\\:\u0002\u001bQ|7.\u001a8U_N#(/\u001b8h)\u0011\t9E!\u0016\t\u000f\t]s\u00041\u0001\u0003Z\u0005)Ao\\6f]B\"!1\fB5!\u0019\u0011iF!\u0019\u0003f5\u0011!q\f\u0006\u0005\u0005/\n)!\u0003\u0003\u0003d\t}#!\u0002+pW\u0016t\u0007\u0003\u0002B4\u0005Sb\u0001\u0001\u0002\u0007\u0003l\tU\u0013\u0011!A\u0001\u0006\u0003\u0011iGA\u0002`IE\nBAa\u001c\u0003vA\u00191K!\u001d\n\u0007\tMDKA\u0004O_RD\u0017N\\4\u0011\t\tu#qO\u0005\u0005\u0005s\u0012yFA\bU_.,g.\u00133f]RLg-[3s\u0003%\u0019XM]5bY&TX\r\u0006\u0003\u0002$\n}\u0004bBADA\u0001\u0007\u0011\u0011R\u0001\fI\u0016\u001cXM]5bY&TX\r\u0006\u0003\u0002\n\n\u0015\u0005b\u0002BDC\u0001\u0007\u00111U\u0001\u000bi>\\WM\u001c\"zi\u0016\u001c\u0018aC5t!J|\u00070_+tKJ$B!a@\u0003\u000e\"9!q\u0012\u0012A\u0002\u0005\u0005\u0011aA;hS\u0006y1\u000b]1sW\"\u000bGm\\8q+RLG\u000e\u0005\u0002cIM\u0019AE\u0015-\u0015\u0005\tM\u0015\u0001C5ogR\fgnY3\u0016\u0003\u0005\fQ%\u0016)E\u0003R+u,\u0013(Q+R{V*\u0012+S\u0013\u000e\u001bv,\u0013(U\u000bJ3\u0016\tT0S\u000b\u000e{%\u000bR*\u0016\u0005\t\u0005\u0006cA*\u0003$&\u0019!Q\u0015+\u0003\u0007%sG/\u0001\u0014V!\u0012\u000bE+R0J\u001dB+FkX'F)JK5iU0J\u001dR+%KV!M?J+5i\u0014*E'\u0002\nac\u0015)B%.{\u0006*\u0011#P\u001fB{6i\u0014(G?\u001aKE*R\u000b\u0003\u0005[\u0003BAa,\u0003:6\u0011!\u0011\u0017\u0006\u0005\u0005g\u0013),\u0001\u0003mC:<'B\u0001B\\\u0003\u0011Q\u0017M^1\n\t\u0005-#\u0011W\u0001\u0018'B\u000b%kS0I\u0003\u0012{u\nU0D\u001f:3uLR%M\u000b\u0002\n\u0001cU(V%\u000e+u\fS%W\u000b~\u001b\u0016\nV#\u0002#M{UKU\"F?\"Ke+R0T\u0013R+\u0005%\u0001\u0007T\u001fV\u00136)R0T!\u0006\u00136*A\u0007T\u001fV\u00136)R0T!\u0006\u00136\nI\u0001\u0014'>+&kQ#`'B\u000b%kS0I\u0003\u0012{u\nU\u0001\u0015'>+&kQ#`'B\u000b%kS0I\u0003\u0012{u\n\u0015\u0011\u00021\u0015sek\u0018,B%~\u000bukU0F\u001d\u0012\u0003v*\u0013(U?V\u0013F*A\rF\u001dZ{f+\u0011*`\u0003^\u001bv,\u0012(E!>Ke\nV0V%2\u0003\u0013AF#O-~3\u0016IU0B/N{\u0016iQ\"F'N{6*R-\u0002/\u0015sek\u0018,B%~\u000bukU0B\u0007\u000e+5kU0L\u000bf\u0003\u0013AF#O-~3\u0016IU0B/N{6+R\"S\u000bR{6*R-\u0002/\u0015sek\u0018,B%~\u000bukU0T\u000b\u000e\u0013V\tV0L\u000bf\u0003\u0013!G#O-~3\u0016IU0B/N{6+R*T\u0013>su\fV(L\u000b:\u000b!$\u0012(W?Z\u000b%kX!X'~\u001bViU*J\u001f:{FkT&F\u001d\u0002\n\u0011cU(V%\u000e+ul\u0015)B%.{\u0006*\u0013,F\u0003I\u0019v*\u0016*D\u000b~\u001b\u0006+\u0011*L?\"Ke+\u0012\u0011\u0002+M+Ek\u0018+P?\u0012+e)Q+M)~3\u0016\tT+F'\u000612+\u0012+`)>{F)\u0012$B+2#vLV!M+\u0016\u001b\u0006%A\u0002hKR$2\u0001\u001cBs\u0011\u0015Qg\b1\u0001f)\u00151(\u0011\u001eBv\u0011\u0015Qw\b1\u0001f\u0011\u0019\t\tc\u0010a\u0001Y\u0006\u0011\u0013\r\u001d9f]\u0012\u001c6g\u0011:fI\u0016tG/[1mg\u001a\u0013x.\\#om&\u0014xN\\7f]R$2B\u001eBy\u0005g\u00149Pa?\u0003\u0000\"1\u0011\u0011\u0005!A\u00021DqA!>A\u0001\u0004\t9%A\u0006f]\u0012\u0004x.\u001b8u+Jd\u0007b\u0002B}\u0001\u0002\u0007\u0011qI\u0001\u0006W\u0016L\u0018\n\u001a\u0005\b\u0005{\u0004\u0005\u0019AA$\u0003%\t7mY3tg.+\u0017\u0010C\u0004\u0004\u0002\u0001\u0003\r!a\u0012\u0002\u0019M,7o]5p]R{7.\u001a8\u0002\u0019!Lg/Z\"p]\u001a\\U-_:\u0016\u0005\r\u001d\u0001CBB\u0005\u0007\u001f\u0019\t\"\u0004\u0002\u0004\f)!1QBA-\u0003%IW.\\;uC\ndW-\u0003\u0003\u0002R\u000e-\u0001\u0003CB\n\u00077\u0011iK!,\u000e\u0005\rU!\u0002BB\f\u00073\t1!T1q\u0015\u0011\u0011\tD!.\n\t\ru1Q\u0003\u0002\u0006\u000b:$(/_\u0001\u0012CB\u0004XM\u001c3ISZ,7i\u001c8gS\u001e\u001cHc\u0001<\u0004$!1\u0011\u0011\u0005\"A\u00021$RA^B\u0014\u0007SAQA[\"A\u0002\u0015Da!!\tD\u0001\u0004aG#\u0002<\u0004.\r=\u0002\"\u00026E\u0001\u0004)\u0007BBA\u0011\t\u0002\u0007A.A\bqe>\u0004XM\u001d;z'>,(oY3t)\u0019\t9e!\u000e\u00048!1\u0011\u0011E#A\u00021Dqa!\u000fF\u0001\u0004\t9%A\u0002lKf\f!b\u0019:fCR,g)\u001b7f)!\u0019yd!\u0012\u0004H\r-\u0003\u0003BAl\u0007\u0003JAaa\u0011\u0002Z\n\u0011bi\u0015#bi\u0006|U\u000f\u001e9viN#(/Z1n\u0011\u001d\tYN\u0012a\u0001\u0003GDqa!\u0013G\u0001\u0004\ti/\u0001\u0003qCRD\u0007bBB'\r\u0002\u0007\u0011q`\u0001\bC2dwn^#D\u0003\u0019I7OR5mKR1\u0011q`B*\u0007+Bq!a7H\u0001\u0004\t\u0019\u000fC\u0004\u0004J\u001d\u0003\r!!<"
)
public class SparkHadoopUtil implements Logging {
   private final SparkConf sparkConf;
   private final Configuration conf;
   private final UnanchoredRegex HADOOP_CONF_PATTERN;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean isFile(final FileSystem fs, final Path path) {
      return SparkHadoopUtil$.MODULE$.isFile(fs, path);
   }

   public static FSDataOutputStream createFile(final FileSystem fs, final Path path, final boolean allowEC) {
      return SparkHadoopUtil$.MODULE$.createFile(fs, path, allowEC);
   }

   public static String propertySources(final Configuration hadoopConf, final String key) {
      return SparkHadoopUtil$.MODULE$.propertySources(hadoopConf, key);
   }

   public static SparkHadoopUtil get() {
      return SparkHadoopUtil$.MODULE$.get();
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

   private SparkConf sparkConf() {
      return this.sparkConf;
   }

   public Configuration conf() {
      return this.conf;
   }

   public void runAsSparkUser(final Function0 func) {
      this.createSparkUser().doAs(new PrivilegedExceptionAction(func) {
         private final Function0 func$1;

         public void run() {
            this.func$1.apply$mcV$sp();
         }

         public {
            this.func$1 = func$1;
         }
      });
   }

   public UserGroupInformation createSparkUser() {
      String user = org.apache.spark.util.Utils$.MODULE$.getCurrentUserName();
      this.logDebug((Function0)(() -> "creating UGI for user: " + user));
      UserGroupInformation ugi = UserGroupInformation.createRemoteUser(user);
      this.transferCredentials(UserGroupInformation.getCurrentUser(), ugi);
      return ugi;
   }

   public void transferCredentials(final UserGroupInformation source, final UserGroupInformation dest) {
      dest.addCredentials(source.getCredentials());
   }

   public void appendS3AndSparkHadoopHiveConfigurations(final SparkConf conf, final Configuration hadoopConf) {
      SparkHadoopUtil$.MODULE$.org$apache$spark$deploy$SparkHadoopUtil$$appendS3AndSparkHadoopHiveConfigurations(conf, hadoopConf);
   }

   public void appendSparkHadoopConfigs(final SparkConf conf, final Configuration hadoopConf) {
      SparkHadoopUtil$.MODULE$.org$apache$spark$deploy$SparkHadoopUtil$$appendSparkHadoopConfigs(conf, hadoopConf);
   }

   public void appendSparkHadoopConfigs(final scala.collection.immutable.Map srcMap, final HashMap destMap) {
      srcMap.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$appendSparkHadoopConfigs$1(check$ifrefutable$1))).withFilter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$appendSparkHadoopConfigs$2(x$1))).foreach((x$2) -> {
         if (x$2 != null) {
            String key = (String)x$2._1();
            String value = (String)x$2._2();
            return destMap.put(key.substring("spark.hadoop.".length()), value);
         } else {
            throw new MatchError(x$2);
         }
      });
   }

   public void appendSparkHiveConfigs(final scala.collection.immutable.Map srcMap, final HashMap destMap) {
      srcMap.withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$appendSparkHiveConfigs$1(check$ifrefutable$2))).withFilter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$appendSparkHiveConfigs$2(x$3))).foreach((x$4) -> {
         if (x$4 != null) {
            String key = (String)x$4._1();
            String value = (String)x$4._2();
            return destMap.put(key.substring("spark.".length()), value);
         } else {
            throw new MatchError(x$4);
         }
      });
   }

   public Configuration newConfiguration(final SparkConf conf) {
      Configuration hadoopConf = SparkHadoopUtil$.MODULE$.newConfiguration(conf);
      hadoopConf.addResource(SparkHadoopUtil$.MODULE$.SPARK_HADOOP_CONF_FILE());
      return hadoopConf;
   }

   public void addCredentials(final JobConf conf) {
      Credentials jobCreds = conf.getCredentials();
      jobCreds.mergeAll(UserGroupInformation.getCurrentUser().getCredentials());
   }

   public void addCurrentUserCredentials(final Credentials creds) {
      UserGroupInformation.getCurrentUser().addCredentials(creds);
   }

   public void loginUserFromKeytab(final String principalName, final String keytabFilename) {
      if (!(new File(keytabFilename)).exists()) {
         throw new SparkException("Keytab file: " + keytabFilename + " does not exist");
      } else {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Attempting to login to Kerberos using principal: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " and keytab: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PRINCIPAL..MODULE$, principalName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.KEYTAB..MODULE$, keytabFilename)}))))));
         UserGroupInformation.loginUserFromKeytab(principalName, keytabFilename);
      }
   }

   public void addDelegationTokens(final byte[] tokens, final SparkConf sparkConf) {
      UserGroupInformation.setConfiguration(this.newConfiguration(sparkConf));
      Credentials creds = this.deserialize(tokens);
      this.logInfo((Function0)(() -> "Updating delegation tokens for current user."));
      this.logDebug((Function0)(() -> {
         Iterable var10000 = this.dumpTokens(creds);
         return "Adding/updating delegation tokens " + var10000;
      }));
      this.addCurrentUserCredentials(creds);
   }

   public Function0 getFSBytesReadOnThreadCallback() {
      Function0 f = () -> BoxesRunTime.unboxToLong(((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(FileSystem.getAllStatistics()).asScala().map((x$5) -> BoxesRunTime.boxToLong($anonfun$getFSBytesReadOnThreadCallback$2(x$5)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
      Tuple2 baseline = new Tuple2.mcJJ.sp(Thread.currentThread().getId(), f.apply$mcJ$sp());
      return new Function0.mcJ.sp(f, baseline) {
         private final HashMap org$apache$spark$deploy$SparkHadoopUtil$$anon$$bytesReadMap;
         private final Function0 f$1;
         private final Tuple2 baseline$1;

         public boolean apply$mcZ$sp() {
            return Function0.apply$mcZ$sp$(this);
         }

         public byte apply$mcB$sp() {
            return Function0.apply$mcB$sp$(this);
         }

         public char apply$mcC$sp() {
            return Function0.apply$mcC$sp$(this);
         }

         public double apply$mcD$sp() {
            return Function0.apply$mcD$sp$(this);
         }

         public float apply$mcF$sp() {
            return Function0.apply$mcF$sp$(this);
         }

         public int apply$mcI$sp() {
            return Function0.apply$mcI$sp$(this);
         }

         public short apply$mcS$sp() {
            return Function0.apply$mcS$sp$(this);
         }

         public void apply$mcV$sp() {
            Function0.apply$mcV$sp$(this);
         }

         public String toString() {
            return Function0.toString$(this);
         }

         public HashMap org$apache$spark$deploy$SparkHadoopUtil$$anon$$bytesReadMap() {
            return this.org$apache$spark$deploy$SparkHadoopUtil$$anon$$bytesReadMap;
         }

         public long apply() {
            return this.apply$mcJ$sp();
         }

         public long apply$mcJ$sp() {
            synchronized(this.org$apache$spark$deploy$SparkHadoopUtil$$anon$$bytesReadMap()){}

            long var2;
            try {
               this.org$apache$spark$deploy$SparkHadoopUtil$$anon$$bytesReadMap().put(BoxesRunTime.boxToLong(Thread.currentThread().getId()), BoxesRunTime.boxToLong(this.f$1.apply$mcJ$sp()));
               var2 = BoxesRunTime.unboxToLong(((IterableOnceOps)this.org$apache$spark$deploy$SparkHadoopUtil$$anon$$bytesReadMap().map((x0$1) -> BoxesRunTime.boxToLong($anonfun$apply$1(this, x0$1)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
            } catch (Throwable var5) {
               throw var5;
            }

            return var2;
         }

         // $FF: synthetic method
         public static final long $anonfun$apply$1(final Object $this, final Tuple2 x0$1) {
            if (x0$1 != null) {
               long k = x0$1._1$mcJ$sp();
               long v = x0$1._2$mcJ$sp();
               return v - (k == $this.baseline$1._1$mcJ$sp() ? $this.baseline$1._2$mcJ$sp() : 0L);
            } else {
               throw new MatchError(x0$1);
            }
         }

         public {
            this.f$1 = f$1;
            this.baseline$1 = baseline$1;
            Function0.$init$(this);
            this.org$apache$spark$deploy$SparkHadoopUtil$$anon$$bytesReadMap = new HashMap();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public Function0 getFSBytesWrittenOnThreadCallback() {
      Buffer threadStats = (Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(FileSystem.getAllStatistics()).asScala().map((x$6) -> x$6.getThreadStatistics());
      Function0 f = () -> BoxesRunTime.unboxToLong(((IterableOnceOps)threadStats.map((x$7) -> BoxesRunTime.boxToLong($anonfun$getFSBytesWrittenOnThreadCallback$3(x$7)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
      long baselineBytesWritten = f.apply$mcJ$sp();
      return (JFunction0.mcJ.sp)() -> f.apply$mcJ$sp() - baselineBytesWritten;
   }

   public Seq listLeafStatuses(final FileSystem fs, final Path basePath) {
      return this.listLeafStatuses(fs, fs.getFileStatus(basePath));
   }

   public Seq listLeafStatuses(final FileSystem fs, final FileStatus baseStatus) {
      return (Seq)(baseStatus.isDirectory() ? this.recurse$1(baseStatus, fs) : new scala.collection.immutable..colon.colon(baseStatus, scala.collection.immutable.Nil..MODULE$));
   }

   public boolean isGlobPath(final Path pattern) {
      StringOps var10000 = scala.collection.StringOps..MODULE$;
      String var10001 = scala.Predef..MODULE$.augmentString(pattern.toString());
      Set var2 = scala.Predef..MODULE$.wrapString("{}[]*?\\").toSet();
      return var10000.exists$extension(var10001, (elem) -> BoxesRunTime.boxToBoolean($anonfun$isGlobPath$1(var2, elem)));
   }

   public Seq globPath(final Path pattern) {
      FileSystem fs = pattern.getFileSystem(this.conf());
      return this.globPath(fs, pattern);
   }

   public Seq globPath(final FileSystem fs, final Path pattern) {
      return (Seq)scala.Option..MODULE$.apply(fs.globStatus(pattern)).map((statuses) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])statuses), (x$10) -> x$10.getPath().makeQualified(fs.getUri(), fs.getWorkingDirectory()), scala.reflect.ClassTag..MODULE$.apply(Path.class))).toImmutableArraySeq()).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
   }

   public Seq globPathIfNecessary(final Path pattern) {
      return (Seq)(this.isGlobPath(pattern) ? this.globPath(pattern) : new scala.collection.immutable..colon.colon(pattern, scala.collection.immutable.Nil..MODULE$));
   }

   public Seq globPathIfNecessary(final FileSystem fs, final Path pattern) {
      return (Seq)(this.isGlobPath(pattern) ? this.globPath(fs, pattern) : new scala.collection.immutable..colon.colon(pattern, scala.collection.immutable.Nil..MODULE$));
   }

   private UnanchoredRegex HADOOP_CONF_PATTERN() {
      return this.HADOOP_CONF_PATTERN;
   }

   public String substituteHadoopVariables(final String text, final Configuration hadoopConf) {
      if (text != null) {
         Option var5 = ((Regex)this.HADOOP_CONF_PATTERN()).unapplySeq(text);
         if (!var5.isEmpty() && var5.get() != null && ((List)var5.get()).lengthCompare(1) == 0) {
            String matched = (String)((LinearSeqOps)var5.get()).apply(0);
            this.logDebug((Function0)(() -> text + " matched " + this.HADOOP_CONF_PATTERN()));
            String key = matched.substring(13, matched.length() - 1);
            Option eval = scala.Option..MODULE$.apply(hadoopConf.get(key)).map((value) -> {
               this.logDebug((Function0)(() -> "Substituted " + matched + " with " + value));
               return text.replace(matched, value);
            });
            if (eval.isEmpty()) {
               return text;
            }

            return this.substituteHadoopVariables((String)eval.get(), hadoopConf);
         }
      }

      this.logDebug((Function0)(() -> text + " didn't match " + this.HADOOP_CONF_PATTERN()));
      return text;
   }

   public Iterable dumpTokens(final Credentials credentials) {
      return credentials != null ? (Iterable)scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(credentials.getAllTokens()).asScala().map((token) -> this.tokenToString(token)) : (Iterable)scala.package..MODULE$.Seq().empty();
   }

   public String tokenToString(final Token token) {
      DateFormat df = DateFormat.getDateTimeInstance(3, 3, Locale.US);
      StringBuilder buffer = new StringBuilder(128);
      buffer.append(token.toString());

      try {
         TokenIdentifier ti = token.decodeIdentifier();
         buffer.append("; ").append(ti);
         if (ti instanceof AbstractDelegationTokenIdentifier var7) {
            buffer.append("; Renewer: ").append(var7.getRenewer());
            buffer.append("; Issued: ").append(df.format(new Date(var7.getIssueDate())));
            buffer.append("; Max Date: ").append(df.format(new Date(var7.getMaxDate())));
         } else {
            BoxedUnit var10 = BoxedUnit.UNIT;
         }
      } catch (IOException var9) {
         this.logDebug((Function0)(() -> "Failed to decode " + token + ": " + var9), var9);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return buffer.toString();
   }

   public byte[] serialize(final Credentials creds) {
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
      DataOutputStream dataStream = new DataOutputStream(byteStream);
      creds.writeTokenStorageToStream(dataStream);
      return byteStream.toByteArray();
   }

   public Credentials deserialize(final byte[] tokenBytes) {
      ByteArrayInputStream tokensBuf = new ByteArrayInputStream(tokenBytes);
      Credentials creds = new Credentials();
      creds.readTokenStorageStream(new DataInputStream(tokensBuf));
      return creds;
   }

   public boolean isProxyUser(final UserGroupInformation ugi) {
      boolean var3;
      label23: {
         UserGroupInformation.AuthenticationMethod var10000 = ugi.getAuthenticationMethod();
         UserGroupInformation.AuthenticationMethod var2 = AuthenticationMethod.PROXY;
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$appendSparkHadoopConfigs$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$appendSparkHadoopConfigs$2(final Tuple2 x$1) {
      if (x$1 != null) {
         String key = (String)x$1._1();
         return key.startsWith("spark.hadoop.");
      } else {
         throw new MatchError(x$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$appendSparkHiveConfigs$1(final Tuple2 check$ifrefutable$2) {
      return check$ifrefutable$2 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$appendSparkHiveConfigs$2(final Tuple2 x$3) {
      if (x$3 != null) {
         String key = (String)x$3._1();
         return key.startsWith("spark.hive.");
      } else {
         throw new MatchError(x$3);
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$getFSBytesReadOnThreadCallback$2(final FileSystem.Statistics x$5) {
      return x$5.getThreadStatistics().getBytesRead();
   }

   // $FF: synthetic method
   public static final long $anonfun$getFSBytesWrittenOnThreadCallback$3(final FileSystem.Statistics.StatisticsData x$7) {
      return x$7.getBytesWritten();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listLeafStatuses$1(final FileStatus x$8) {
      return x$8.isDirectory();
   }

   private final Seq recurse$1(final FileStatus status, final FileSystem fs$1) {
      Tuple2 var5 = scala.collection.ArrayOps..MODULE$.partition$extension(scala.Predef..MODULE$.refArrayOps((Object[])fs$1.listStatus(status.getPath())), (x$8) -> BoxesRunTime.boxToBoolean($anonfun$listLeafStatuses$1(x$8)));
      if (var5 != null) {
         FileStatus[] directories = (FileStatus[])var5._1();
         FileStatus[] leaves = (FileStatus[])var5._2();
         Tuple2 var4 = new Tuple2(directories, leaves);
         FileStatus[] directories = (FileStatus[])var4._1();
         FileStatus[] leaves = (FileStatus[])var4._2();
         return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])leaves), scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])directories), (f) -> this.listLeafStatuses(fs$1, f), scala.reflect.ClassTag..MODULE$.apply(FileStatus.class)), scala.reflect.ClassTag..MODULE$.apply(FileStatus.class))).toImmutableArraySeq();
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isGlobPath$1(final Set eta$0$1$1, final Object elem) {
      return eta$0$1$1.contains(elem);
   }

   public SparkHadoopUtil() {
      Logging.$init$(this);
      this.sparkConf = (new SparkConf(false)).loadFromSystemProperties(true);
      this.conf = this.newConfiguration(this.sparkConf());
      UserGroupInformation.setConfiguration(this.conf());
      this.HADOOP_CONF_PATTERN = scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("(\\$\\{hadoopconf-[^}$\\s]+})")).unanchored();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
