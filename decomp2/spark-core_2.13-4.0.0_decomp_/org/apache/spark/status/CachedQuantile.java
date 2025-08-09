package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.spark.util.kvstore.KVIndex;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tEc!\u0002-Z\u0001m\u000b\u0007\u0002\u00035\u0001\u0005\u000b\u0007I\u0011\u00016\t\u00119\u0004!\u0011!Q\u0001\n-D\u0001b\u001c\u0001\u0003\u0006\u0004%\tA\u001b\u0005\ta\u0002\u0011\t\u0011)A\u0005W\"A\u0011\u000f\u0001BC\u0002\u0013\u0005!\u000f\u0003\u0005\u007f\u0001\t\u0005\t\u0015!\u0003t\u0011%y\bA!b\u0001\n\u0003\t\t\u0001\u0003\u0006\u0002\n\u0001\u0011\t\u0011)A\u0005\u0003\u0007A!\"a\u0003\u0001\u0005\u000b\u0007I\u0011AA\u0007\u0011)\t)\u0002\u0001B\u0001B\u0003%\u0011q\u0002\u0005\u000b\u0003/\u0001!Q1A\u0005\u0002\u00055\u0001BCA\r\u0001\t\u0005\t\u0015!\u0003\u0002\u0010!Q\u00111\u0004\u0001\u0003\u0006\u0004%\t!!\u0004\t\u0015\u0005u\u0001A!A!\u0002\u0013\ty\u0001\u0003\u0006\u0002 \u0001\u0011)\u0019!C\u0001\u0003\u001bA!\"!\t\u0001\u0005\u0003\u0005\u000b\u0011BA\b\u0011)\t\u0019\u0003\u0001BC\u0002\u0013\u0005\u0011Q\u0002\u0005\u000b\u0003K\u0001!\u0011!Q\u0001\n\u0005=\u0001BCA\u0014\u0001\t\u0015\r\u0011\"\u0001\u0002\u000e!Q\u0011\u0011\u0006\u0001\u0003\u0002\u0003\u0006I!a\u0004\t\u0015\u0005-\u0002A!b\u0001\n\u0003\ti\u0001\u0003\u0006\u0002.\u0001\u0011\t\u0011)A\u0005\u0003\u001fA!\"a\f\u0001\u0005\u000b\u0007I\u0011AA\u0007\u0011)\t\t\u0004\u0001B\u0001B\u0003%\u0011q\u0002\u0005\u000b\u0003g\u0001!Q1A\u0005\u0002\u00055\u0001BCA\u001b\u0001\t\u0005\t\u0015!\u0003\u0002\u0010!Q\u0011q\u0007\u0001\u0003\u0006\u0004%\t!!\u0004\t\u0015\u0005e\u0002A!A!\u0002\u0013\ty\u0001\u0003\u0006\u0002<\u0001\u0011)\u0019!C\u0001\u0003\u001bA!\"!\u0010\u0001\u0005\u0003\u0005\u000b\u0011BA\b\u0011)\ty\u0004\u0001BC\u0002\u0013\u0005\u0011Q\u0002\u0005\u000b\u0003\u0003\u0002!\u0011!Q\u0001\n\u0005=\u0001BCA\"\u0001\t\u0015\r\u0011\"\u0001\u0002\u000e!Q\u0011Q\t\u0001\u0003\u0002\u0003\u0006I!a\u0004\t\u0015\u0005\u001d\u0003A!b\u0001\n\u0003\ti\u0001\u0003\u0006\u0002J\u0001\u0011\t\u0011)A\u0005\u0003\u001fA!\"a\u0013\u0001\u0005\u000b\u0007I\u0011AA\u0007\u0011)\ti\u0005\u0001B\u0001B\u0003%\u0011q\u0002\u0005\u000b\u0003\u001f\u0002!Q1A\u0005\u0002\u00055\u0001BCA)\u0001\t\u0005\t\u0015!\u0003\u0002\u0010!Q\u00111\u000b\u0001\u0003\u0006\u0004%\t!!\u0004\t\u0015\u0005U\u0003A!A!\u0002\u0013\ty\u0001\u0003\u0006\u0002X\u0001\u0011)\u0019!C\u0001\u0003\u001bA!\"!\u0017\u0001\u0005\u0003\u0005\u000b\u0011BA\b\u0011)\tY\u0006\u0001BC\u0002\u0013\u0005\u0011Q\u0002\u0005\u000b\u0003;\u0002!\u0011!Q\u0001\n\u0005=\u0001BCA0\u0001\t\u0015\r\u0011\"\u0001\u0002\u000e!Q\u0011\u0011\r\u0001\u0003\u0002\u0003\u0006I!a\u0004\t\u0015\u0005\r\u0004A!b\u0001\n\u0003\ti\u0001\u0003\u0006\u0002f\u0001\u0011\t\u0011)A\u0005\u0003\u001fA!\"a\u001a\u0001\u0005\u000b\u0007I\u0011AA\u0007\u0011)\tI\u0007\u0001B\u0001B\u0003%\u0011q\u0002\u0005\u000b\u0003W\u0002!Q1A\u0005\u0002\u00055\u0001BCA7\u0001\t\u0005\t\u0015!\u0003\u0002\u0010!Q\u0011q\u000e\u0001\u0003\u0006\u0004%\t!!\u0004\t\u0015\u0005E\u0004A!A!\u0002\u0013\ty\u0001\u0003\u0006\u0002t\u0001\u0011)\u0019!C\u0001\u0003\u001bA!\"!\u001e\u0001\u0005\u0003\u0005\u000b\u0011BA\b\u0011)\t9\b\u0001BC\u0002\u0013\u0005\u0011Q\u0002\u0005\u000b\u0003s\u0002!\u0011!Q\u0001\n\u0005=\u0001BCA>\u0001\t\u0015\r\u0011\"\u0001\u0002\u000e!Q\u0011Q\u0010\u0001\u0003\u0002\u0003\u0006I!a\u0004\t\u0015\u0005}\u0004A!b\u0001\n\u0003\ti\u0001\u0003\u0006\u0002\u0002\u0002\u0011\t\u0011)A\u0005\u0003\u001fA!\"a!\u0001\u0005\u000b\u0007I\u0011AA\u0007\u0011)\t)\t\u0001B\u0001B\u0003%\u0011q\u0002\u0005\u000b\u0003\u000f\u0003!Q1A\u0005\u0002\u00055\u0001BCAE\u0001\t\u0005\t\u0015!\u0003\u0002\u0010!Q\u00111\u0012\u0001\u0003\u0006\u0004%\t!!\u0004\t\u0015\u00055\u0005A!A!\u0002\u0013\ty\u0001\u0003\u0006\u0002\u0010\u0002\u0011)\u0019!C\u0001\u0003\u001bA!\"!%\u0001\u0005\u0003\u0005\u000b\u0011BA\b\u0011)\t\u0019\n\u0001BC\u0002\u0013\u0005\u0011Q\u0002\u0005\u000b\u0003+\u0003!\u0011!Q\u0001\n\u0005=\u0001BCAL\u0001\t\u0015\r\u0011\"\u0001\u0002\u000e!Q\u0011\u0011\u0014\u0001\u0003\u0002\u0003\u0006I!a\u0004\t\u0015\u0005m\u0005A!b\u0001\n\u0003\ti\u0001\u0003\u0006\u0002\u001e\u0002\u0011\t\u0011)A\u0005\u0003\u001fA!\"a(\u0001\u0005\u000b\u0007I\u0011AA\u0007\u0011)\t\t\u000b\u0001B\u0001B\u0003%\u0011q\u0002\u0005\u000b\u0003G\u0003!Q1A\u0005\u0002\u00055\u0001BCAS\u0001\t\u0005\t\u0015!\u0003\u0002\u0010!Q\u0011q\u0015\u0001\u0003\u0006\u0004%\t!!\u0004\t\u0015\u0005%\u0006A!A!\u0002\u0013\ty\u0001C\u0004\u0002,\u0002!\t!!,\t\u000f\t\u001d\u0001\u0001\"\u0001\u0003\n!9!1\t\u0001\u0005\u0002\t\u0015#AD\"bG\",G-U;b]RLG.\u001a\u0006\u00035n\u000baa\u001d;biV\u001c(B\u0001/^\u0003\u0015\u0019\b/\u0019:l\u0015\tqv,\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002A\u0006\u0019qN]4\u0014\u0005\u0001\u0011\u0007CA2g\u001b\u0005!'\"A3\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u001d$'AB!osJ+g-A\u0004ti\u0006<W-\u00133\u0004\u0001U\t1\u000e\u0005\u0002dY&\u0011Q\u000e\u001a\u0002\u0004\u0013:$\u0018\u0001C:uC\u001e,\u0017\n\u001a\u0011\u0002\u001dM$\u0018mZ3BiR,W\u000e\u001d;JI\u0006y1\u000f^1hK\u0006#H/Z7qi&#\u0007%\u0001\u0005rk\u0006tG/\u001b7f+\u0005\u0019\bC\u0001;|\u001d\t)\u0018\u0010\u0005\u0002wI6\tqO\u0003\u0002yS\u00061AH]8pizJ!A\u001f3\u0002\rA\u0013X\rZ3g\u0013\taXP\u0001\u0004TiJLgn\u001a\u0006\u0003u\u0012\f\u0011\"];b]RLG.\u001a\u0011\u0002\u0013Q\f7o[\"pk:$XCAA\u0002!\r\u0019\u0017QA\u0005\u0004\u0003\u000f!'\u0001\u0002'p]\u001e\f!\u0002^1tW\u000e{WO\u001c;!\u0003!!WO]1uS>tWCAA\b!\r\u0019\u0017\u0011C\u0005\u0004\u0003'!'A\u0002#pk\ndW-A\u0005ekJ\fG/[8oA\u00059R\r_3dkR|'\u000fR3tKJL\u0017\r\\5{KRKW.Z\u0001\u0019Kb,7-\u001e;pe\u0012+7/\u001a:jC2L'0\u001a+j[\u0016\u0004\u0013AG3yK\u000e,Ho\u001c:EKN,'/[1mSj,7\t];US6,\u0017aG3yK\u000e,Ho\u001c:EKN,'/[1mSj,7\t];US6,\u0007%A\bfq\u0016\u001cW\u000f^8s%VtG+[7f\u0003A)\u00070Z2vi>\u0014(+\u001e8US6,\u0007%A\bfq\u0016\u001cW\u000f^8s\u0007B,H+[7f\u0003A)\u00070Z2vi>\u00148\t];US6,\u0007%\u0001\u0006sKN,H\u000e^*ju\u0016\f1B]3tk2$8+\u001b>fA\u0005I!N^7HGRKW.Z\u0001\u000bUZlwi\u0019+j[\u0016\u0004\u0013a\u0006:fgVdGoU3sS\u0006d\u0017N_1uS>tG+[7f\u0003a\u0011Xm];miN+'/[1mSj\fG/[8o)&lW\rI\u0001\u0012O\u0016$H/\u001b8h%\u0016\u001cX\u000f\u001c;US6,\u0017AE4fiRLgn\u001a*fgVdG\u000fV5nK\u0002\nab]2iK\u0012,H.\u001a:EK2\f\u00170A\btG\",G-\u001e7fe\u0012+G.Y=!\u0003M\u0001X-Y6Fq\u0016\u001cW\u000f^5p]6+Wn\u001c:z\u0003Q\u0001X-Y6Fq\u0016\u001cW\u000f^5p]6+Wn\u001c:zA\u0005\u0011R.Z7pef\u0014\u0015\u0010^3t'BLG\u000e\\3e\u0003MiW-\\8ss\nKH/Z:Ta&dG.\u001a3!\u0003A!\u0017n]6CsR,7o\u00159jY2,G-A\teSN\\')\u001f;fgN\u0003\u0018\u000e\u001c7fI\u0002\n\u0011BY=uKN\u0014V-\u00193\u0002\u0015\tLH/Z:SK\u0006$\u0007%A\u0006sK\u000e|'\u000fZ:SK\u0006$\u0017\u0001\u0004:fG>\u0014Hm\u001d*fC\u0012\u0004\u0013\u0001\u00042zi\u0016\u001cxK]5ui\u0016t\u0017!\u00042zi\u0016\u001cxK]5ui\u0016t\u0007%\u0001\bsK\u000e|'\u000fZ:Xe&$H/\u001a8\u0002\u001fI,7m\u001c:eg^\u0013\u0018\u000e\u001e;f]\u0002\n\u0001c\u001d5vM\u001adWMU3bI\nKH/Z:\u0002#MDWO\u001a4mKJ+\u0017\r\u001a\"zi\u0016\u001c\b%\u0001\ntQV4g\r\\3SK\u000e|'\u000fZ:SK\u0006$\u0017aE:ik\u001a4G.\u001a*fG>\u0014Hm\u001d*fC\u0012\u0004\u0013AG:ik\u001a4G.\u001a*f[>$XM\u00117pG.\u001ch)\u001a;dQ\u0016$\u0017aG:ik\u001a4G.\u001a*f[>$XM\u00117pG.\u001ch)\u001a;dQ\u0016$\u0007%A\rtQV4g\r\\3M_\u000e\fGN\u00117pG.\u001ch)\u001a;dQ\u0016$\u0017AG:ik\u001a4G.\u001a'pG\u0006d'\t\\8dWN4U\r^2iK\u0012\u0004\u0013\u0001F:ik\u001a4G.\u001a$fi\u000eDw+Y5u)&lW-A\u000btQV4g\r\\3GKR\u001c\u0007nV1jiRKW.\u001a\u0011\u0002-MDWO\u001a4mKJ+Wn\u001c;f\u0005f$Xm\u001d*fC\u0012\fqc\u001d5vM\u001adWMU3n_R,')\u001f;fgJ+\u0017\r\u001a\u0011\u00029MDWO\u001a4mKJ+Wn\u001c;f\u0005f$Xm\u001d*fC\u0012$v\u000eR5tW\u0006i2\u000f[;gM2,'+Z7pi\u0016\u0014\u0015\u0010^3t%\u0016\fG\rV8ESN\\\u0007%A\rtQV4g\r\\3U_R\fGN\u00117pG.\u001ch)\u001a;dQ\u0016$\u0017AG:ik\u001a4G.\u001a+pi\u0006d'\t\\8dWN4U\r^2iK\u0012\u0004\u0013aH:ik\u001a4G.Z\"peJ,\b\u000f^'fe\u001e,GM\u00117pG.\u001c\u0005.\u001e8lg\u0006\u00013\u000f[;gM2,7i\u001c:skB$X*\u001a:hK\u0012\u0014En\\2l\u0007\",hn[:!\u0003}\u0019\b.\u001e4gY\u0016lUM]4fI\u001a+Go\u00195GC2d'-Y2l\u0007>,h\u000e^\u0001!g\",hM\u001a7f\u001b\u0016\u0014x-\u001a3GKR\u001c\u0007NR1mY\n\f7m[\"pk:$\b%\u0001\u0011tQV4g\r\\3NKJ<W\r\u001a*f[>$XM\u00117pG.\u001ch)\u001a;dQ\u0016$\u0017!I:ik\u001a4G.Z'fe\u001e,GMU3n_R,'\t\\8dWN4U\r^2iK\u0012\u0004\u0013aH:ik\u001a4G.Z'fe\u001e,G\rT8dC2\u0014En\\2lg\u001a+Go\u00195fI\u0006\u00013\u000f[;gM2,W*\u001a:hK\u0012dunY1m\u00052|7m[:GKR\u001c\u0007.\u001a3!\u0003\u0001\u001a\b.\u001e4gY\u0016lUM]4fIJ+Wn\u001c;f\u0007\",hn[:GKR\u001c\u0007.\u001a3\u0002CMDWO\u001a4mK6+'oZ3e%\u0016lw\u000e^3DQVt7n\u001d$fi\u000eDW\r\u001a\u0011\u0002?MDWO\u001a4mK6+'oZ3e\u0019>\u001c\u0017\r\\\"ik:\\7OR3uG\",G-\u0001\u0011tQV4g\r\\3NKJ<W\r\u001a'pG\u0006d7\t[;oWN4U\r^2iK\u0012\u0004\u0013\u0001H:ik\u001a4G.Z'fe\u001e,GMU3n_R,')\u001f;fgJ+\u0017\rZ\u0001\u001eg\",hM\u001a7f\u001b\u0016\u0014x-\u001a3SK6|G/\u001a\"zi\u0016\u001c(+Z1eA\u0005Y2\u000f[;gM2,W*\u001a:hK\u0012dunY1m\u0005f$Xm\u001d*fC\u0012\fAd\u001d5vM\u001adW-T3sO\u0016$Gj\\2bY\nKH/Z:SK\u0006$\u0007%A\rtQV4g\r\\3SK6|G/\u001a*fcN$UO]1uS>t\u0017AG:ik\u001a4G.\u001a*f[>$XMU3rg\u0012+(/\u0019;j_:\u0004\u0013aH:ik\u001a4G.Z'fe\u001e,GMU3n_R,'+Z9t\tV\u0014\u0018\r^5p]\u0006\u00013\u000f[;gM2,W*\u001a:hK\u0012\u0014V-\\8uKJ+\u0017o\u001d#ve\u0006$\u0018n\u001c8!\u0003E\u0019\b.\u001e4gY\u0016<&/\u001b;f\u0005f$Xm]\u0001\u0013g\",hM\u001a7f/JLG/\u001a\"zi\u0016\u001c\b%A\ntQV4g\r\\3Xe&$XMU3d_J$7/\u0001\u000btQV4g\r\\3Xe&$XMU3d_J$7\u000fI\u0001\u0011g\",hM\u001a7f/JLG/\u001a+j[\u0016\f\u0011c\u001d5vM\u001adWm\u0016:ji\u0016$\u0016.\\3!\u0003\u0019a\u0014N\\5u}Q1\u0016qVAZ\u0003k\u000b9,!/\u0002<\u0006u\u0016qXAa\u0003\u0007\f)-a2\u0002J\u0006-\u0017QZAh\u0003#\f\u0019.!6\u0002X\u0006e\u00171\\Ao\u0003?\f\t/a9\u0002f\u0006\u001d\u0018\u0011^Av\u0003[\fy/!=\u0002t\u0006U\u0018q_A}\u0003w\fi0a@\u0003\u0002\t\r!Q\u0001\t\u0004\u0003c\u0003Q\"A-\t\u000b!,\u0006\u0019A6\t\u000b=,\u0006\u0019A6\t\u000bE,\u0006\u0019A:\t\r},\u0006\u0019AA\u0002\u0011\u001d\tY!\u0016a\u0001\u0003\u001fAq!a\u0006V\u0001\u0004\ty\u0001C\u0004\u0002\u001cU\u0003\r!a\u0004\t\u000f\u0005}Q\u000b1\u0001\u0002\u0010!9\u00111E+A\u0002\u0005=\u0001bBA\u0014+\u0002\u0007\u0011q\u0002\u0005\b\u0003W)\u0006\u0019AA\b\u0011\u001d\ty#\u0016a\u0001\u0003\u001fAq!a\rV\u0001\u0004\ty\u0001C\u0004\u00028U\u0003\r!a\u0004\t\u000f\u0005mR\u000b1\u0001\u0002\u0010!9\u0011qH+A\u0002\u0005=\u0001bBA\"+\u0002\u0007\u0011q\u0002\u0005\b\u0003\u000f*\u0006\u0019AA\b\u0011\u001d\tY%\u0016a\u0001\u0003\u001fAq!a\u0014V\u0001\u0004\ty\u0001C\u0004\u0002TU\u0003\r!a\u0004\t\u000f\u0005]S\u000b1\u0001\u0002\u0010!9\u00111L+A\u0002\u0005=\u0001bBA0+\u0002\u0007\u0011q\u0002\u0005\b\u0003G*\u0006\u0019AA\b\u0011\u001d\t9'\u0016a\u0001\u0003\u001fAq!a\u001bV\u0001\u0004\ty\u0001C\u0004\u0002pU\u0003\r!a\u0004\t\u000f\u0005MT\u000b1\u0001\u0002\u0010!9\u0011qO+A\u0002\u0005=\u0001bBA>+\u0002\u0007\u0011q\u0002\u0005\b\u0003\u007f*\u0006\u0019AA\b\u0011\u001d\t\u0019)\u0016a\u0001\u0003\u001fAq!a\"V\u0001\u0004\ty\u0001C\u0004\u0002\fV\u0003\r!a\u0004\t\u000f\u0005=U\u000b1\u0001\u0002\u0010!9\u00111S+A\u0002\u0005=\u0001bBAL+\u0002\u0007\u0011q\u0002\u0005\b\u00037+\u0006\u0019AA\b\u0011\u001d\ty*\u0016a\u0001\u0003\u001fAq!a)V\u0001\u0004\ty\u0001C\u0004\u0002(V\u0003\r!a\u0004\u0002\u0005%$WC\u0001B\u0006!\u0015\u0019'Q\u0002B\t\u0013\r\u0011y\u0001\u001a\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004G\nM\u0011b\u0001B\u000bI\n\u0019\u0011I\\=)\u0007Y\u0013I\u0002\u0005\u0003\u0003\u001c\t\u0015RB\u0001B\u000f\u0015\u0011\u0011yB!\t\u0002\u000f-48\u000f^8sK*\u0019!1E.\u0002\tU$\u0018\u000e\\\u0005\u0005\u0005O\u0011iBA\u0004L-&sG-\u001a=)\u0007Y\u0013Y\u0003\u0005\u0003\u0003.\t}RB\u0001B\u0018\u0015\u0011\u0011\tDa\r\u0002\u0015\u0005tgn\u001c;bi&|gN\u0003\u0003\u00036\t]\u0012a\u00026bG.\u001cxN\u001c\u0006\u0005\u0005s\u0011Y$A\u0005gCN$XM\u001d=nY*\u0011!QH\u0001\u0004G>l\u0017\u0002\u0002B!\u0005_\u0011!BS:p]&;gn\u001c:f\u0003\u0015\u0019H/Y4f+\t\u00119\u0005\u0005\u0003d\u0005\u001bY\u0007fB,\u0003\u001a\t-#QJ\u0001\u0006m\u0006dW/Z\u0011\u0003\u0005\u0007B3a\u0016B\u0016\u0001"
)
public class CachedQuantile {
   private final int stageId;
   private final int stageAttemptId;
   private final String quantile;
   private final long taskCount;
   private final double duration;
   private final double executorDeserializeTime;
   private final double executorDeserializeCpuTime;
   private final double executorRunTime;
   private final double executorCpuTime;
   private final double resultSize;
   private final double jvmGcTime;
   private final double resultSerializationTime;
   private final double gettingResultTime;
   private final double schedulerDelay;
   private final double peakExecutionMemory;
   private final double memoryBytesSpilled;
   private final double diskBytesSpilled;
   private final double bytesRead;
   private final double recordsRead;
   private final double bytesWritten;
   private final double recordsWritten;
   private final double shuffleReadBytes;
   private final double shuffleRecordsRead;
   private final double shuffleRemoteBlocksFetched;
   private final double shuffleLocalBlocksFetched;
   private final double shuffleFetchWaitTime;
   private final double shuffleRemoteBytesRead;
   private final double shuffleRemoteBytesReadToDisk;
   private final double shuffleTotalBlocksFetched;
   private final double shuffleCorruptMergedBlockChunks;
   private final double shuffleMergedFetchFallbackCount;
   private final double shuffleMergedRemoteBlocksFetched;
   private final double shuffleMergedLocalBlocksFetched;
   private final double shuffleMergedRemoteChunksFetched;
   private final double shuffleMergedLocalChunksFetched;
   private final double shuffleMergedRemoteBytesRead;
   private final double shuffleMergedLocalBytesRead;
   private final double shuffleRemoteReqsDuration;
   private final double shuffleMergedRemoteReqsDuration;
   private final double shuffleWriteBytes;
   private final double shuffleWriteRecords;
   private final double shuffleWriteTime;

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public String quantile() {
      return this.quantile;
   }

   public long taskCount() {
      return this.taskCount;
   }

   public double duration() {
      return this.duration;
   }

   public double executorDeserializeTime() {
      return this.executorDeserializeTime;
   }

   public double executorDeserializeCpuTime() {
      return this.executorDeserializeCpuTime;
   }

   public double executorRunTime() {
      return this.executorRunTime;
   }

   public double executorCpuTime() {
      return this.executorCpuTime;
   }

   public double resultSize() {
      return this.resultSize;
   }

   public double jvmGcTime() {
      return this.jvmGcTime;
   }

   public double resultSerializationTime() {
      return this.resultSerializationTime;
   }

   public double gettingResultTime() {
      return this.gettingResultTime;
   }

   public double schedulerDelay() {
      return this.schedulerDelay;
   }

   public double peakExecutionMemory() {
      return this.peakExecutionMemory;
   }

   public double memoryBytesSpilled() {
      return this.memoryBytesSpilled;
   }

   public double diskBytesSpilled() {
      return this.diskBytesSpilled;
   }

   public double bytesRead() {
      return this.bytesRead;
   }

   public double recordsRead() {
      return this.recordsRead;
   }

   public double bytesWritten() {
      return this.bytesWritten;
   }

   public double recordsWritten() {
      return this.recordsWritten;
   }

   public double shuffleReadBytes() {
      return this.shuffleReadBytes;
   }

   public double shuffleRecordsRead() {
      return this.shuffleRecordsRead;
   }

   public double shuffleRemoteBlocksFetched() {
      return this.shuffleRemoteBlocksFetched;
   }

   public double shuffleLocalBlocksFetched() {
      return this.shuffleLocalBlocksFetched;
   }

   public double shuffleFetchWaitTime() {
      return this.shuffleFetchWaitTime;
   }

   public double shuffleRemoteBytesRead() {
      return this.shuffleRemoteBytesRead;
   }

   public double shuffleRemoteBytesReadToDisk() {
      return this.shuffleRemoteBytesReadToDisk;
   }

   public double shuffleTotalBlocksFetched() {
      return this.shuffleTotalBlocksFetched;
   }

   public double shuffleCorruptMergedBlockChunks() {
      return this.shuffleCorruptMergedBlockChunks;
   }

   public double shuffleMergedFetchFallbackCount() {
      return this.shuffleMergedFetchFallbackCount;
   }

   public double shuffleMergedRemoteBlocksFetched() {
      return this.shuffleMergedRemoteBlocksFetched;
   }

   public double shuffleMergedLocalBlocksFetched() {
      return this.shuffleMergedLocalBlocksFetched;
   }

   public double shuffleMergedRemoteChunksFetched() {
      return this.shuffleMergedRemoteChunksFetched;
   }

   public double shuffleMergedLocalChunksFetched() {
      return this.shuffleMergedLocalChunksFetched;
   }

   public double shuffleMergedRemoteBytesRead() {
      return this.shuffleMergedRemoteBytesRead;
   }

   public double shuffleMergedLocalBytesRead() {
      return this.shuffleMergedLocalBytesRead;
   }

   public double shuffleRemoteReqsDuration() {
      return this.shuffleRemoteReqsDuration;
   }

   public double shuffleMergedRemoteReqsDuration() {
      return this.shuffleMergedRemoteReqsDuration;
   }

   public double shuffleWriteBytes() {
      return this.shuffleWriteBytes;
   }

   public double shuffleWriteRecords() {
      return this.shuffleWriteRecords;
   }

   public double shuffleWriteTime() {
      return this.shuffleWriteTime;
   }

   @KVIndex
   @JsonIgnore
   public Object[] id() {
      return new Object[]{BoxesRunTime.boxToInteger(this.stageId()), BoxesRunTime.boxToInteger(this.stageAttemptId()), this.quantile()};
   }

   @KVIndex("stage")
   @JsonIgnore
   public int[] stage() {
      return new int[]{this.stageId(), this.stageAttemptId()};
   }

   public CachedQuantile(final int stageId, final int stageAttemptId, final String quantile, final long taskCount, final double duration, final double executorDeserializeTime, final double executorDeserializeCpuTime, final double executorRunTime, final double executorCpuTime, final double resultSize, final double jvmGcTime, final double resultSerializationTime, final double gettingResultTime, final double schedulerDelay, final double peakExecutionMemory, final double memoryBytesSpilled, final double diskBytesSpilled, final double bytesRead, final double recordsRead, final double bytesWritten, final double recordsWritten, final double shuffleReadBytes, final double shuffleRecordsRead, final double shuffleRemoteBlocksFetched, final double shuffleLocalBlocksFetched, final double shuffleFetchWaitTime, final double shuffleRemoteBytesRead, final double shuffleRemoteBytesReadToDisk, final double shuffleTotalBlocksFetched, final double shuffleCorruptMergedBlockChunks, final double shuffleMergedFetchFallbackCount, final double shuffleMergedRemoteBlocksFetched, final double shuffleMergedLocalBlocksFetched, final double shuffleMergedRemoteChunksFetched, final double shuffleMergedLocalChunksFetched, final double shuffleMergedRemoteBytesRead, final double shuffleMergedLocalBytesRead, final double shuffleRemoteReqsDuration, final double shuffleMergedRemoteReqsDuration, final double shuffleWriteBytes, final double shuffleWriteRecords, final double shuffleWriteTime) {
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      this.quantile = quantile;
      this.taskCount = taskCount;
      this.duration = duration;
      this.executorDeserializeTime = executorDeserializeTime;
      this.executorDeserializeCpuTime = executorDeserializeCpuTime;
      this.executorRunTime = executorRunTime;
      this.executorCpuTime = executorCpuTime;
      this.resultSize = resultSize;
      this.jvmGcTime = jvmGcTime;
      this.resultSerializationTime = resultSerializationTime;
      this.gettingResultTime = gettingResultTime;
      this.schedulerDelay = schedulerDelay;
      this.peakExecutionMemory = peakExecutionMemory;
      this.memoryBytesSpilled = memoryBytesSpilled;
      this.diskBytesSpilled = diskBytesSpilled;
      this.bytesRead = bytesRead;
      this.recordsRead = recordsRead;
      this.bytesWritten = bytesWritten;
      this.recordsWritten = recordsWritten;
      this.shuffleReadBytes = shuffleReadBytes;
      this.shuffleRecordsRead = shuffleRecordsRead;
      this.shuffleRemoteBlocksFetched = shuffleRemoteBlocksFetched;
      this.shuffleLocalBlocksFetched = shuffleLocalBlocksFetched;
      this.shuffleFetchWaitTime = shuffleFetchWaitTime;
      this.shuffleRemoteBytesRead = shuffleRemoteBytesRead;
      this.shuffleRemoteBytesReadToDisk = shuffleRemoteBytesReadToDisk;
      this.shuffleTotalBlocksFetched = shuffleTotalBlocksFetched;
      this.shuffleCorruptMergedBlockChunks = shuffleCorruptMergedBlockChunks;
      this.shuffleMergedFetchFallbackCount = shuffleMergedFetchFallbackCount;
      this.shuffleMergedRemoteBlocksFetched = shuffleMergedRemoteBlocksFetched;
      this.shuffleMergedLocalBlocksFetched = shuffleMergedLocalBlocksFetched;
      this.shuffleMergedRemoteChunksFetched = shuffleMergedRemoteChunksFetched;
      this.shuffleMergedLocalChunksFetched = shuffleMergedLocalChunksFetched;
      this.shuffleMergedRemoteBytesRead = shuffleMergedRemoteBytesRead;
      this.shuffleMergedLocalBytesRead = shuffleMergedLocalBytesRead;
      this.shuffleRemoteReqsDuration = shuffleRemoteReqsDuration;
      this.shuffleMergedRemoteReqsDuration = shuffleMergedRemoteReqsDuration;
      this.shuffleWriteBytes = shuffleWriteBytes;
      this.shuffleWriteRecords = shuffleWriteRecords;
      this.shuffleWriteTime = shuffleWriteTime;
   }
}
