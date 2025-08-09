package org.apache.spark.executor;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.hadoop.fs.FileSystem;
import org.apache.spark.metrics.source.Source;
import scala.Function1;
import scala.Option;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tub!B)S\u0001QS\u0006\u0002C5\u0001\u0005\u0003\u0005\u000b\u0011B6\t\u0011U\u0004!\u0011!Q\u0001\nYD!\"a\u0001\u0001\u0005\u0003\u0005\u000b\u0011BA\u0003\u0011\u001d\tY\u0001\u0001C\u0001\u0003\u001bAq!!\u0007\u0001\t\u0013\tY\u0002C\u0004\u0002@\u0001!I!!\u0011\t\u0013\u0005M\u0005A1A\u0005B\u0005U\u0005\u0002CAO\u0001\u0001\u0006I!a&\t\u0013\u0005}\u0005A1A\u0005B\u0005\u0005\u0006\u0002CAT\u0001\u0001\u0006I!a)\t\u0013\u0005%\u0006A1A\u0005\u0002\u0005-\u0006\u0002CAZ\u0001\u0001\u0006I!!,\t\u0013\u0005U\u0006A1A\u0005\u0002\u0005-\u0006\u0002CA\\\u0001\u0001\u0006I!!,\t\u0013\u0005e\u0006A1A\u0005\u0002\u0005-\u0006\u0002CA^\u0001\u0001\u0006I!!,\t\u0013\u0005u\u0006A1A\u0005\u0002\u0005-\u0006\u0002CA`\u0001\u0001\u0006I!!,\t\u0013\u0005\u0005\u0007A1A\u0005\u0002\u0005-\u0006\u0002CAb\u0001\u0001\u0006I!!,\t\u0013\u0005\u0015\u0007A1A\u0005\u0002\u0005-\u0006\u0002CAd\u0001\u0001\u0006I!!,\t\u0013\u0005%\u0007A1A\u0005\u0002\u0005-\u0006\u0002CAf\u0001\u0001\u0006I!!,\t\u0013\u00055\u0007A1A\u0005\u0002\u0005-\u0006\u0002CAh\u0001\u0001\u0006I!!,\t\u0013\u0005E\u0007A1A\u0005\u0002\u0005-\u0006\u0002CAj\u0001\u0001\u0006I!!,\t\u0013\u0005U\u0007A1A\u0005\u0002\u0005-\u0006\u0002CAl\u0001\u0001\u0006I!!,\t\u0013\u0005e\u0007A1A\u0005\u0002\u0005-\u0006\u0002CAn\u0001\u0001\u0006I!!,\t\u0013\u0005u\u0007A1A\u0005\u0002\u0005-\u0006\u0002CAp\u0001\u0001\u0006I!!,\t\u0013\u0005\u0005\bA1A\u0005\u0002\u0005-\u0006\u0002CAr\u0001\u0001\u0006I!!,\t\u0013\u0005\u0015\bA1A\u0005\u0002\u0005-\u0006\u0002CAt\u0001\u0001\u0006I!!,\t\u0013\u0005%\bA1A\u0005\u0002\u0005-\u0006\u0002CAv\u0001\u0001\u0006I!!,\t\u0013\u00055\bA1A\u0005\u0002\u0005-\u0006\u0002CAx\u0001\u0001\u0006I!!,\t\u0013\u0005E\bA1A\u0005\u0002\u0005-\u0006\u0002CAz\u0001\u0001\u0006I!!,\t\u0013\u0005U\bA1A\u0005\u0002\u0005-\u0006\u0002CA|\u0001\u0001\u0006I!!,\t\u0013\u0005e\bA1A\u0005\u0002\u0005-\u0006\u0002CA~\u0001\u0001\u0006I!!,\t\u0013\u0005u\bA1A\u0005\u0002\u0005-\u0006\u0002CA\u0000\u0001\u0001\u0006I!!,\t\u0013\t\u0005\u0001A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\u0002\u0001\u0001\u0006I!!,\t\u0013\t\u0015\u0001A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\u0004\u0001\u0001\u0006I!!,\t\u0013\t%\u0001A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\u0006\u0001\u0001\u0006I!!,\t\u0013\t5\u0001A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\b\u0001\u0001\u0006I!!,\t\u0013\tE\u0001A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\n\u0001\u0001\u0006I!!,\t\u0013\tU\u0001A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\f\u0001\u0001\u0006I!!,\t\u0013\te\u0001A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\u000e\u0001\u0001\u0006I!!,\t\u0013\tu\u0001A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\u0010\u0001\u0001\u0006I!!,\t\u0013\t\u0005\u0002A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\u0012\u0001\u0001\u0006I!!,\t\u0013\t\u0015\u0002A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\u0014\u0001\u0001\u0006I!!,\t\u0013\t%\u0002A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\u0016\u0001\u0001\u0006I!!,\t\u0013\t5\u0002A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\u0018\u0001\u0001\u0006I!!,\t\u0013\tE\u0002A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\u001a\u0001\u0001\u0006I!!,\t\u0013\tU\u0002A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\u001c\u0001\u0001\u0006I!!,\t\u0013\te\u0002A1A\u0005\u0002\u0005-\u0006\u0002\u0003B\u001e\u0001\u0001\u0006I!!,\u0003\u001d\u0015CXmY;u_J\u001cv.\u001e:dK*\u00111\u000bV\u0001\tKb,7-\u001e;pe*\u0011QKV\u0001\u0006gB\f'o\u001b\u0006\u0003/b\u000ba!\u00199bG\",'\"A-\u0002\u0007=\u0014xmE\u0002\u00017\u0006\u0004\"\u0001X0\u000e\u0003uS\u0011AX\u0001\u0006g\u000e\fG.Y\u0005\u0003Av\u0013a!\u00118z%\u00164\u0007C\u00012h\u001b\u0005\u0019'B\u00013f\u0003\u0019\u0019x.\u001e:dK*\u0011a\rV\u0001\b[\u0016$(/[2t\u0013\tA7M\u0001\u0004T_V\u00148-Z\u0001\u000bi\"\u0014X-\u00193Q_>d7\u0001\u0001\t\u0003YNl\u0011!\u001c\u0006\u0003]>\f!bY8oGV\u0014(/\u001a8u\u0015\t\u0001\u0018/\u0001\u0003vi&d'\"\u0001:\u0002\t)\fg/Y\u0005\u0003i6\u0014!\u0003\u00165sK\u0006$\u0007k\\8m\u000bb,7-\u001e;pe\u0006QQ\r_3dkR|'/\u00133\u0011\u0005]thB\u0001=}!\tIX,D\u0001{\u0015\tY(.\u0001\u0004=e>|GOP\u0005\u0003{v\u000ba\u0001\u0015:fI\u00164\u0017bA@\u0002\u0002\t11\u000b\u001e:j]\u001eT!!`/\u0002#\u0019LG.Z*zgR,WnU2iK6,7\u000f\u0005\u0003]\u0003\u000f1\u0018bAA\u0005;\n)\u0011I\u001d:bs\u00061A(\u001b8jiz\"\u0002\"a\u0004\u0002\u0014\u0005U\u0011q\u0003\t\u0004\u0003#\u0001Q\"\u0001*\t\u000b%$\u0001\u0019A6\t\u000bU$\u0001\u0019\u0001<\t\u000f\u0005\rA\u00011\u0001\u0002\u0006\u0005Ia-\u001b7f'R\fGo\u001d\u000b\u0005\u0003;\tY\u0004E\u0003]\u0003?\t\u0019#C\u0002\u0002\"u\u0013aa\u00149uS>t\u0007\u0003BA\u0013\u0003kqA!a\n\u000225\u0011\u0011\u0011\u0006\u0006\u0005\u0003W\ti#\u0001\u0002gg*\u0019\u0011q\u0006,\u0002\r!\fGm\\8q\u0013\u0011\t\u0019$!\u000b\u0002\u0015\u0019KG.Z*zgR,W.\u0003\u0003\u00028\u0005e\"AC*uCRL7\u000f^5dg*!\u00111GA\u0015\u0011\u0019\ti$\u0002a\u0001m\u000611o\u00195f[\u0016\faC]3hSN$XM\u001d$jY\u0016\u001c\u0016p\u001d;f[N#\u0018\r^\u000b\u0005\u0003\u0007\ni\u0007\u0006\u0006\u0002F\u0005}\u0014\u0011QAC\u0003\u001f\u0013b!a\u0012\u0002L\u0005]cABA%\r\u0001\t)E\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0003\u0002N\u0005MSBAA(\u0015\r\t\t&]\u0001\u0005Y\u0006tw-\u0003\u0003\u0002V\u0005=#AB(cU\u0016\u001cG\u000f\u0005\u0004\u0002Z\u0005\u0015\u0014\u0011N\u0007\u0003\u00037R1AZA/\u0015\u0011\ty&!\u0019\u0002\u0011\r|G-\u00195bY\u0016T!!a\u0019\u0002\u0007\r|W.\u0003\u0003\u0002h\u0005m#!B$bk\u001e,\u0007\u0003BA6\u0003[b\u0001\u0001B\u0004\u0002p\u0019\u0011\r!!\u001d\u0003\u0003Q\u000bB!a\u001d\u0002zA\u0019A,!\u001e\n\u0007\u0005]TLA\u0004O_RD\u0017N\\4\u0011\u0007q\u000bY(C\u0002\u0002~u\u00131!\u00118z\u0011\u0019\tiD\u0002a\u0001m\"1\u00111\u0011\u0004A\u0002Y\fAA\\1nK\"9\u0011q\u0011\u0004A\u0002\u0005%\u0015!\u00014\u0011\u000fq\u000bY)a\t\u0002j%\u0019\u0011QR/\u0003\u0013\u0019+hn\u0019;j_:\f\u0004bBAI\r\u0001\u0007\u0011\u0011N\u0001\rI\u00164\u0017-\u001e7u-\u0006dW/Z\u0001\u000f[\u0016$(/[2SK\u001eL7\u000f\u001e:z+\t\t9\n\u0005\u0003\u0002Z\u0005e\u0015\u0002BAN\u00037\u0012a\"T3ue&\u001c'+Z4jgR\u0014\u00180A\bnKR\u0014\u0018n\u0019*fO&\u001cHO]=!\u0003)\u0019x.\u001e:dK:\u000bW.Z\u000b\u0003\u0003G\u0003B!!\u0014\u0002&&\u0019q0a\u0014\u0002\u0017M|WO]2f\u001d\u0006lW\rI\u0001\u0010'V\u001b5)R#E\u000b\u0012{F+Q*L'V\u0011\u0011Q\u0016\t\u0005\u00033\ny+\u0003\u0003\u00022\u0006m#aB\"pk:$XM]\u0001\u0011'V\u001b5)R#E\u000b\u0012{F+Q*L'\u0002\nq\"T#U%&\u001bul\u0011)V?RKU*R\u0001\u0011\u001b\u0016#&+S\"`\u0007B+v\fV%N\u000b\u0002\nq\"T#U%&\u001buLU+O?RKU*R\u0001\u0011\u001b\u0016#&+S\"`%Vsu\fV%N\u000b\u0002\n!#T#U%&\u001buL\u0013,N?\u001e\u001bu\fV%N\u000b\u0006\u0019R*\u0012+S\u0013\u000e{&JV'`\u000f\u000e{F+S'FA\u00059R*\u0012+S\u0013\u000e{F)R*F%&\u000bE*\u0013.F?RKU*R\u0001\u0019\u001b\u0016#&+S\"`\t\u0016\u001bVIU%B\u0019&SVi\u0018+J\u001b\u0016\u0003\u0013aG'F)JK5i\u0018#F'\u0016\u0013\u0016*\u0011'J5\u0016{6\tU+`)&kU)\u0001\u000fN\u000bR\u0013\u0016jQ0E\u000bN+%+S!M\u0013j+ul\u0011)V?RKU*\u0012\u0011\u000295+EKU%D?J+5+\u0016'U?N+%+S!M\u0013j+u\fV%N\u000b\u0006iR*\u0012+S\u0013\u000e{&+R*V\u0019R{6+\u0012*J\u00032K%,R0U\u00136+\u0005%\u0001\u0010N\u000bR\u0013\u0016jQ0T\u0011V3e\tT#`\r\u0016#6\tS0X\u0003&#v\fV%N\u000b\u0006yR*\u0012+S\u0013\u000e{6\u000bS+G\r2+uLR#U\u0007\"{v+Q%U?RKU*\u0012\u0011\u000235+EKU%D?NCUK\u0012$M\u000b~;&+\u0013+F?RKU*R\u0001\u001b\u001b\u0016#&+S\"`'\"+fI\u0012'F?^\u0013\u0016\nV#`)&kU\tI\u0001 \u001b\u0016#&+S\"`'\"+fI\u0012'F?R{E+\u0011'`\u0005f#ViU0S\u000b\u0006#\u0015\u0001I'F)JK5iX*I+\u001a3E*R0U\u001fR\u000bEj\u0018\"Z)\u0016\u001bvLU#B\t\u0002\n\u0001%T#U%&\u001bul\u0015%V\r\u001acUi\u0018*F\u001b>#Vi\u0018\"Z)\u0016\u001bvLU#B\t\u0006\tS*\u0012+S\u0013\u000e{6\u000bS+G\r2+uLU#N\u001fR+uLQ-U\u000bN{&+R!EA\u0005AS*\u0012+S\u0013\u000e{6\u000bS+G\r2+uLU#N\u001fR+uLQ-U\u000bN{&+R!E?R{u\fR%T\u0017\u0006IS*\u0012+S\u0013\u000e{6\u000bS+G\r2+uLU#N\u001fR+uLQ-U\u000bN{&+R!E?R{u\fR%T\u0017\u0002\nq$T#U%&\u001bul\u0015%V\r\u001acUi\u0018'P\u0007\u0006cuLQ-U\u000bN{&+R!E\u0003\u0001jU\t\u0016*J\u0007~\u001b\u0006*\u0016$G\u0019\u0016{FjT\"B\u0019~\u0013\u0015\fV#T?J+\u0015\t\u0012\u0011\u000275+EKU%D?NCUK\u0012$M\u000b~\u0013ViQ(S\tN{&+R!E\u0003qiU\t\u0016*J\u0007~\u001b\u0006*\u0016$G\u0019\u0016{&+R\"P%\u0012\u001bvLU#B\t\u0002\nA%T#U%&\u001bul\u0015%V\r\u001acUi\u0018*F\u001b>#Vi\u0018\"M\u001f\u000e[5k\u0018$F)\u000eCU\tR\u0001&\u001b\u0016#&+S\"`'\"+fI\u0012'F?J+Uj\u0014+F?\ncujQ&T?\u001a+Ek\u0011%F\t\u0002\n1%T#U%&\u001bul\u0015%V\r\u001acUi\u0018'P\u0007\u0006cuL\u0011'P\u0007.\u001bvLR#U\u0007\"+E)\u0001\u0013N\u000bR\u0013\u0016jQ0T\u0011V3e\tT#`\u0019>\u001b\u0015\tT0C\u0019>\u001b5jU0G\u000bR\u001b\u0005*\u0012#!\u0003qiU\t\u0016*J\u0007~\u001b\u0006*\u0016$G\u0019\u0016{&)\u0017+F'~;&+\u0013+U\u000b:\u000bQ$T#U%&\u001bul\u0015%V\r\u001acUi\u0018\"Z)\u0016\u001bvl\u0016*J)R+e\nI\u0001\u001f\u001b\u0016#&+S\"`'\"+fI\u0012'F?J+5i\u0014*E'~;&+\u0013+U\u000b:\u000bq$T#U%&\u001bul\u0015%V\r\u001acUi\u0018*F\u0007>\u0013FiU0X%&#F+\u0012(!\u0003\rjU\t\u0016*J\u0007~\u001b\u0006*\u0016$G\u0019\u0016{&+R'P)\u0016{&+R)T?\u0012+&+\u0011+J\u001f:\u000bA%T#U%&\u001bul\u0015%V\r\u001acUi\u0018*F\u001b>#Vi\u0018*F#N{F)\u0016*B)&{e\nI\u00016\u001b\u0016#&+S\"`!V\u001b\u0006j\u0018\"B'\u0016#ul\u0015%V\r\u001acUiX\"P%J+\u0006\u000bV0N\u000bJ;U\tR0C\u0019>\u001b5jX\"I+:[5+\u0001\u001cN\u000bR\u0013\u0016jQ0Q+NCuLQ!T\u000b\u0012{6\u000bS+G\r2+ulQ(S%V\u0003FkX'F%\u001e+Ei\u0018\"M\u001f\u000e[ul\u0011%V\u001d.\u001b\u0006%A\u001bN\u000bR\u0013\u0016jQ0Q+NCuLQ!T\u000b\u0012{6\u000bS+G\r2+u,T#S\u000f\u0016#uLR#U\u0007\"{f)\u0011'M\u0005\u0006\u001b5jX\"P+:#\u0016AN'F)JK5i\u0018)V'\"{&)Q*F\t~\u001b\u0006*\u0016$G\u0019\u0016{V*\u0012*H\u000b\u0012{f)\u0012+D\u0011~3\u0015\t\u0014'C\u0003\u000e[ulQ(V\u001dR\u0003\u0013AN'F)JK5i\u0018)V'\"{&)Q*F\t~\u001b\u0006*\u0016$G\u0019\u0016{V*\u0012*H\u000b\u0012{&+R'P)\u0016{&\tT(D\u0017N{f)\u0012+D\u0011\u0016#\u0015aN'F)JK5i\u0018)V'\"{&)Q*F\t~\u001b\u0006*\u0016$G\u0019\u0016{V*\u0012*H\u000b\u0012{&+R'P)\u0016{&\tT(D\u0017N{f)\u0012+D\u0011\u0016#\u0005%A\u001bN\u000bR\u0013\u0016jQ0Q+NCuLQ!T\u000b\u0012{6\u000bS+G\r2+u,T#S\u000f\u0016#u\fT(D\u00032{&\tT(D\u0017N{f)\u0012+D\u0011\u0016#\u0015AN'F)JK5i\u0018)V'\"{&)Q*F\t~\u001b\u0006*\u0016$G\u0019\u0016{V*\u0012*H\u000b\u0012{FjT\"B\u0019~\u0013EjT\"L'~3U\tV\"I\u000b\u0012\u0003\u0013AN'F)JK5i\u0018)V'\"{&)Q*F\t~\u001b\u0006*\u0016$G\u0019\u0016{V*\u0012*H\u000b\u0012{&+R'P)\u0016{6\tS+O\u0017N{f)\u0012+D\u0011\u0016#\u0015aN'F)JK5i\u0018)V'\"{&)Q*F\t~\u001b\u0006*\u0016$G\u0019\u0016{V*\u0012*H\u000b\u0012{&+R'P)\u0016{6\tS+O\u0017N{f)\u0012+D\u0011\u0016#\u0005%A\u001bN\u000bR\u0013\u0016jQ0Q+NCuLQ!T\u000b\u0012{6\u000bS+G\r2+u,T#S\u000f\u0016#u\fT(D\u00032{6\tS+O\u0017N{f)\u0012+D\u0011\u0016#\u0015AN'F)JK5i\u0018)V'\"{&)Q*F\t~\u001b\u0006*\u0016$G\u0019\u0016{V*\u0012*H\u000b\u0012{FjT\"B\u0019~\u001b\u0005*\u0016(L'~3U\tV\"I\u000b\u0012\u0003\u0013AM'F)JK5i\u0018)V'\"{&)Q*F\t~\u001b\u0006*\u0016$G\u0019\u0016{V*\u0012*H\u000b\u0012{&+R'P)\u0016{&)\u0017+F'~\u0013V)\u0011#\u0002g5+EKU%D?B+6\u000bS0C\u0003N+EiX*I+\u001a3E*R0N\u000bJ;U\tR0S\u000b6{E+R0C3R+5k\u0018*F\u0003\u0012\u0003\u0013!M'F)JK5i\u0018)V'\"{&)Q*F\t~\u001b\u0006*\u0016$G\u0019\u0016{V*\u0012*H\u000b\u0012{FjT\"B\u0019~\u0013\u0015\fV#T?J+\u0015\tR\u00013\u001b\u0016#&+S\"`!V\u001b\u0006j\u0018\"B'\u0016#ul\u0015%V\r\u001acUiX'F%\u001e+Ei\u0018'P\u0007\u0006cuLQ-U\u000bN{&+R!EA\u0005)T*\u0012+S\u0013\u000e{\u0006+V*I?\n\u000b5+\u0012#`'\"+fI\u0012'F?6+%kR#E?J+Uj\u0014+F?J+\u0015kU0E+J\u000bE+S(O\u0003YjU\t\u0016*J\u0007~\u0003Vk\u0015%`\u0005\u0006\u001bV\tR0T\u0011V3e\tT#`\u001b\u0016\u0013v)\u0012#`%\u0016ku\nV#`%\u0016\u000b6k\u0018#V%\u0006#\u0016j\u0014(!\u0003]iU\t\u0016*J\u0007~Ke\nU+U?\nKF+R*`%\u0016\u000bE)\u0001\rN\u000bR\u0013\u0016jQ0J\u001dB+Fk\u0018\"Z)\u0016\u001bvLU#B\t\u0002\n\u0011$T#U%&\u001bu,\u0013(Q+R{&+R\"P%\u0012\u001bvLU#B\t\u0006QR*\u0012+S\u0013\u000e{\u0016J\u0014)V)~\u0013ViQ(S\tN{&+R!EA\u0005YR*\u0012+S\u0013\u000e{v*\u0016+Q+R{&)\u0017+F'~;&+\u0013+U\u000b:\u000bA$T#U%&\u001bulT+U!V#vLQ-U\u000bN{vKU%U)\u0016s\u0005%A\u000fN\u000bR\u0013\u0016jQ0P+R\u0003V\u000bV0S\u000b\u000e{%\u000bR*`/JKE\u000bV#O\u0003yiU\t\u0016*J\u0007~{U\u000b\u0016)V)~\u0013ViQ(S\tN{vKU%U)\u0016s\u0005%\u0001\nN\u000bR\u0013\u0016jQ0S\u000bN+F\nV0T\u0013j+\u0015aE'F)JK5i\u0018*F'VcEkX*J5\u0016\u0003\u0013!G'F)JK5i\u0018#J'.{&)\u0017+F'~\u001b\u0006+\u0013'M\u000b\u0012\u000b!$T#U%&\u001bu\fR%T\u0017~\u0013\u0015\fV#T?N\u0003\u0016\n\u0014'F\t\u0002\n1$T#U%&\u001bu,T#N\u001fJKvLQ-U\u000bN{6\u000bU%M\u0019\u0016#\u0015\u0001H'F)JK5iX'F\u001b>\u0013\u0016l\u0018\"Z)\u0016\u001bvl\u0015)J\u00192+E\t\t"
)
public class ExecutorSource implements Source {
   public final ThreadPoolExecutor org$apache$spark$executor$ExecutorSource$$threadPool;
   private final MetricRegistry metricRegistry;
   private final String sourceName;
   private final Counter SUCCEEDED_TASKS;
   private final Counter METRIC_CPU_TIME;
   private final Counter METRIC_RUN_TIME;
   private final Counter METRIC_JVM_GC_TIME;
   private final Counter METRIC_DESERIALIZE_TIME;
   private final Counter METRIC_DESERIALIZE_CPU_TIME;
   private final Counter METRIC_RESULT_SERIALIZE_TIME;
   private final Counter METRIC_SHUFFLE_FETCH_WAIT_TIME;
   private final Counter METRIC_SHUFFLE_WRITE_TIME;
   private final Counter METRIC_SHUFFLE_TOTAL_BYTES_READ;
   private final Counter METRIC_SHUFFLE_REMOTE_BYTES_READ;
   private final Counter METRIC_SHUFFLE_REMOTE_BYTES_READ_TO_DISK;
   private final Counter METRIC_SHUFFLE_LOCAL_BYTES_READ;
   private final Counter METRIC_SHUFFLE_RECORDS_READ;
   private final Counter METRIC_SHUFFLE_REMOTE_BLOCKS_FETCHED;
   private final Counter METRIC_SHUFFLE_LOCAL_BLOCKS_FETCHED;
   private final Counter METRIC_SHUFFLE_BYTES_WRITTEN;
   private final Counter METRIC_SHUFFLE_RECORDS_WRITTEN;
   private final Counter METRIC_SHUFFLE_REMOTE_REQS_DURATION;
   private final Counter METRIC_PUSH_BASED_SHUFFLE_CORRUPT_MERGED_BLOCK_CHUNKS;
   private final Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_FETCH_FALLBACK_COUNT;
   private final Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_BLOCKS_FETCHED;
   private final Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_BLOCKS_FETCHED;
   private final Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_CHUNKS_FETCHED;
   private final Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_CHUNKS_FETCHED;
   private final Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_BYTES_READ;
   private final Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_BYTES_READ;
   private final Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_REQS_DURATION;
   private final Counter METRIC_INPUT_BYTES_READ;
   private final Counter METRIC_INPUT_RECORDS_READ;
   private final Counter METRIC_OUTPUT_BYTES_WRITTEN;
   private final Counter METRIC_OUTPUT_RECORDS_WRITTEN;
   private final Counter METRIC_RESULT_SIZE;
   private final Counter METRIC_DISK_BYTES_SPILLED;
   private final Counter METRIC_MEMORY_BYTES_SPILLED;

   public Option org$apache$spark$executor$ExecutorSource$$fileStats(final String scheme) {
      return .MODULE$.ListHasAsScala(FileSystem.getAllStatistics()).asScala().find((s) -> BoxesRunTime.boxToBoolean($anonfun$fileStats$1(scheme, s)));
   }

   private Gauge registerFileSystemStat(final String scheme, final String name, final Function1 f, final Object defaultValue) {
      return (Gauge)this.metricRegistry().register(MetricRegistry.name("filesystem", new String[]{scheme, name}), new Gauge(scheme, f, defaultValue) {
         // $FF: synthetic field
         private final ExecutorSource $outer;
         private final String scheme$2;
         private final Function1 f$1;
         private final Object defaultValue$1;

         public Object getValue() {
            return this.$outer.org$apache$spark$executor$ExecutorSource$$fileStats(this.scheme$2).map(this.f$1).getOrElse(() -> this.defaultValue$1);
         }

         public {
            if (ExecutorSource.this == null) {
               throw null;
            } else {
               this.$outer = ExecutorSource.this;
               this.scheme$2 = scheme$2;
               this.f$1 = f$1;
               this.defaultValue$1 = defaultValue$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public String sourceName() {
      return this.sourceName;
   }

   public Counter SUCCEEDED_TASKS() {
      return this.SUCCEEDED_TASKS;
   }

   public Counter METRIC_CPU_TIME() {
      return this.METRIC_CPU_TIME;
   }

   public Counter METRIC_RUN_TIME() {
      return this.METRIC_RUN_TIME;
   }

   public Counter METRIC_JVM_GC_TIME() {
      return this.METRIC_JVM_GC_TIME;
   }

   public Counter METRIC_DESERIALIZE_TIME() {
      return this.METRIC_DESERIALIZE_TIME;
   }

   public Counter METRIC_DESERIALIZE_CPU_TIME() {
      return this.METRIC_DESERIALIZE_CPU_TIME;
   }

   public Counter METRIC_RESULT_SERIALIZE_TIME() {
      return this.METRIC_RESULT_SERIALIZE_TIME;
   }

   public Counter METRIC_SHUFFLE_FETCH_WAIT_TIME() {
      return this.METRIC_SHUFFLE_FETCH_WAIT_TIME;
   }

   public Counter METRIC_SHUFFLE_WRITE_TIME() {
      return this.METRIC_SHUFFLE_WRITE_TIME;
   }

   public Counter METRIC_SHUFFLE_TOTAL_BYTES_READ() {
      return this.METRIC_SHUFFLE_TOTAL_BYTES_READ;
   }

   public Counter METRIC_SHUFFLE_REMOTE_BYTES_READ() {
      return this.METRIC_SHUFFLE_REMOTE_BYTES_READ;
   }

   public Counter METRIC_SHUFFLE_REMOTE_BYTES_READ_TO_DISK() {
      return this.METRIC_SHUFFLE_REMOTE_BYTES_READ_TO_DISK;
   }

   public Counter METRIC_SHUFFLE_LOCAL_BYTES_READ() {
      return this.METRIC_SHUFFLE_LOCAL_BYTES_READ;
   }

   public Counter METRIC_SHUFFLE_RECORDS_READ() {
      return this.METRIC_SHUFFLE_RECORDS_READ;
   }

   public Counter METRIC_SHUFFLE_REMOTE_BLOCKS_FETCHED() {
      return this.METRIC_SHUFFLE_REMOTE_BLOCKS_FETCHED;
   }

   public Counter METRIC_SHUFFLE_LOCAL_BLOCKS_FETCHED() {
      return this.METRIC_SHUFFLE_LOCAL_BLOCKS_FETCHED;
   }

   public Counter METRIC_SHUFFLE_BYTES_WRITTEN() {
      return this.METRIC_SHUFFLE_BYTES_WRITTEN;
   }

   public Counter METRIC_SHUFFLE_RECORDS_WRITTEN() {
      return this.METRIC_SHUFFLE_RECORDS_WRITTEN;
   }

   public Counter METRIC_SHUFFLE_REMOTE_REQS_DURATION() {
      return this.METRIC_SHUFFLE_REMOTE_REQS_DURATION;
   }

   public Counter METRIC_PUSH_BASED_SHUFFLE_CORRUPT_MERGED_BLOCK_CHUNKS() {
      return this.METRIC_PUSH_BASED_SHUFFLE_CORRUPT_MERGED_BLOCK_CHUNKS;
   }

   public Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_FETCH_FALLBACK_COUNT() {
      return this.METRIC_PUSH_BASED_SHUFFLE_MERGED_FETCH_FALLBACK_COUNT;
   }

   public Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_BLOCKS_FETCHED() {
      return this.METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_BLOCKS_FETCHED;
   }

   public Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_BLOCKS_FETCHED() {
      return this.METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_BLOCKS_FETCHED;
   }

   public Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_CHUNKS_FETCHED() {
      return this.METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_CHUNKS_FETCHED;
   }

   public Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_CHUNKS_FETCHED() {
      return this.METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_CHUNKS_FETCHED;
   }

   public Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_BYTES_READ() {
      return this.METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_BYTES_READ;
   }

   public Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_BYTES_READ() {
      return this.METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_BYTES_READ;
   }

   public Counter METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_REQS_DURATION() {
      return this.METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_REQS_DURATION;
   }

   public Counter METRIC_INPUT_BYTES_READ() {
      return this.METRIC_INPUT_BYTES_READ;
   }

   public Counter METRIC_INPUT_RECORDS_READ() {
      return this.METRIC_INPUT_RECORDS_READ;
   }

   public Counter METRIC_OUTPUT_BYTES_WRITTEN() {
      return this.METRIC_OUTPUT_BYTES_WRITTEN;
   }

   public Counter METRIC_OUTPUT_RECORDS_WRITTEN() {
      return this.METRIC_OUTPUT_RECORDS_WRITTEN;
   }

   public Counter METRIC_RESULT_SIZE() {
      return this.METRIC_RESULT_SIZE;
   }

   public Counter METRIC_DISK_BYTES_SPILLED() {
      return this.METRIC_DISK_BYTES_SPILLED;
   }

   public Counter METRIC_MEMORY_BYTES_SPILLED() {
      return this.METRIC_MEMORY_BYTES_SPILLED;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fileStats$1(final String scheme$1, final FileSystem.Statistics s) {
      return s.getScheme().equals(scheme$1);
   }

   // $FF: synthetic method
   public static final long $anonfun$new$2(final FileSystem.Statistics x$1) {
      return x$1.getBytesRead();
   }

   // $FF: synthetic method
   public static final long $anonfun$new$3(final FileSystem.Statistics x$2) {
      return x$2.getBytesWritten();
   }

   // $FF: synthetic method
   public static final int $anonfun$new$4(final FileSystem.Statistics x$3) {
      return x$3.getReadOps();
   }

   // $FF: synthetic method
   public static final int $anonfun$new$5(final FileSystem.Statistics x$4) {
      return x$4.getLargeReadOps();
   }

   // $FF: synthetic method
   public static final int $anonfun$new$6(final FileSystem.Statistics x$5) {
      return x$5.getWriteOps();
   }

   public ExecutorSource(final ThreadPoolExecutor threadPool, final String executorId, final String[] fileSystemSchemes) {
      this.org$apache$spark$executor$ExecutorSource$$threadPool = threadPool;
      this.metricRegistry = new MetricRegistry();
      this.sourceName = "executor";
      this.metricRegistry().register(MetricRegistry.name("threadpool", new String[]{"activeTasks"}), new Gauge() {
         // $FF: synthetic field
         private final ExecutorSource $outer;

         public int getValue() {
            return this.$outer.org$apache$spark$executor$ExecutorSource$$threadPool.getActiveCount();
         }

         public {
            if (ExecutorSource.this == null) {
               throw null;
            } else {
               this.$outer = ExecutorSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("threadpool", new String[]{"completeTasks"}), new Gauge() {
         // $FF: synthetic field
         private final ExecutorSource $outer;

         public long getValue() {
            return this.$outer.org$apache$spark$executor$ExecutorSource$$threadPool.getCompletedTaskCount();
         }

         public {
            if (ExecutorSource.this == null) {
               throw null;
            } else {
               this.$outer = ExecutorSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("threadpool", new String[]{"startedTasks"}), new Gauge() {
         // $FF: synthetic field
         private final ExecutorSource $outer;

         public long getValue() {
            return this.$outer.org$apache$spark$executor$ExecutorSource$$threadPool.getTaskCount();
         }

         public {
            if (ExecutorSource.this == null) {
               throw null;
            } else {
               this.$outer = ExecutorSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("threadpool", new String[]{"currentPool_size"}), new Gauge() {
         // $FF: synthetic field
         private final ExecutorSource $outer;

         public int getValue() {
            return this.$outer.org$apache$spark$executor$ExecutorSource$$threadPool.getPoolSize();
         }

         public {
            if (ExecutorSource.this == null) {
               throw null;
            } else {
               this.$outer = ExecutorSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("threadpool", new String[]{"maxPool_size"}), new Gauge() {
         // $FF: synthetic field
         private final ExecutorSource $outer;

         public int getValue() {
            return this.$outer.org$apache$spark$executor$ExecutorSource$$threadPool.getMaximumPoolSize();
         }

         public {
            if (ExecutorSource.this == null) {
               throw null;
            } else {
               this.$outer = ExecutorSource.this;
            }
         }
      });
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])fileSystemSchemes), (scheme) -> {
         this.registerFileSystemStat(scheme, "read_bytes", (x$1) -> BoxesRunTime.boxToLong($anonfun$new$2(x$1)), BoxesRunTime.boxToLong(0L));
         this.registerFileSystemStat(scheme, "write_bytes", (x$2) -> BoxesRunTime.boxToLong($anonfun$new$3(x$2)), BoxesRunTime.boxToLong(0L));
         this.registerFileSystemStat(scheme, "read_ops", (x$3) -> BoxesRunTime.boxToInteger($anonfun$new$4(x$3)), BoxesRunTime.boxToInteger(0));
         this.registerFileSystemStat(scheme, "largeRead_ops", (x$4) -> BoxesRunTime.boxToInteger($anonfun$new$5(x$4)), BoxesRunTime.boxToInteger(0));
         return this.registerFileSystemStat(scheme, "write_ops", (x$5) -> BoxesRunTime.boxToInteger($anonfun$new$6(x$5)), BoxesRunTime.boxToInteger(0));
      });
      this.SUCCEEDED_TASKS = this.metricRegistry().counter(MetricRegistry.name("succeededTasks", new String[0]));
      this.METRIC_CPU_TIME = this.metricRegistry().counter(MetricRegistry.name("cpuTime", new String[0]));
      this.METRIC_RUN_TIME = this.metricRegistry().counter(MetricRegistry.name("runTime", new String[0]));
      this.METRIC_JVM_GC_TIME = this.metricRegistry().counter(MetricRegistry.name("jvmGCTime", new String[0]));
      this.METRIC_DESERIALIZE_TIME = this.metricRegistry().counter(MetricRegistry.name("deserializeTime", new String[0]));
      this.METRIC_DESERIALIZE_CPU_TIME = this.metricRegistry().counter(MetricRegistry.name("deserializeCpuTime", new String[0]));
      this.METRIC_RESULT_SERIALIZE_TIME = this.metricRegistry().counter(MetricRegistry.name("resultSerializationTime", new String[0]));
      this.METRIC_SHUFFLE_FETCH_WAIT_TIME = this.metricRegistry().counter(MetricRegistry.name("shuffleFetchWaitTime", new String[0]));
      this.METRIC_SHUFFLE_WRITE_TIME = this.metricRegistry().counter(MetricRegistry.name("shuffleWriteTime", new String[0]));
      this.METRIC_SHUFFLE_TOTAL_BYTES_READ = this.metricRegistry().counter(MetricRegistry.name("shuffleTotalBytesRead", new String[0]));
      this.METRIC_SHUFFLE_REMOTE_BYTES_READ = this.metricRegistry().counter(MetricRegistry.name("shuffleRemoteBytesRead", new String[0]));
      this.METRIC_SHUFFLE_REMOTE_BYTES_READ_TO_DISK = this.metricRegistry().counter(MetricRegistry.name("shuffleRemoteBytesReadToDisk", new String[0]));
      this.METRIC_SHUFFLE_LOCAL_BYTES_READ = this.metricRegistry().counter(MetricRegistry.name("shuffleLocalBytesRead", new String[0]));
      this.METRIC_SHUFFLE_RECORDS_READ = this.metricRegistry().counter(MetricRegistry.name("shuffleRecordsRead", new String[0]));
      this.METRIC_SHUFFLE_REMOTE_BLOCKS_FETCHED = this.metricRegistry().counter(MetricRegistry.name("shuffleRemoteBlocksFetched", new String[0]));
      this.METRIC_SHUFFLE_LOCAL_BLOCKS_FETCHED = this.metricRegistry().counter(MetricRegistry.name("shuffleLocalBlocksFetched", new String[0]));
      this.METRIC_SHUFFLE_BYTES_WRITTEN = this.metricRegistry().counter(MetricRegistry.name("shuffleBytesWritten", new String[0]));
      this.METRIC_SHUFFLE_RECORDS_WRITTEN = this.metricRegistry().counter(MetricRegistry.name("shuffleRecordsWritten", new String[0]));
      this.METRIC_SHUFFLE_REMOTE_REQS_DURATION = this.metricRegistry().counter(MetricRegistry.name("shuffleRemoteReqsDuration", new String[0]));
      this.METRIC_PUSH_BASED_SHUFFLE_CORRUPT_MERGED_BLOCK_CHUNKS = this.metricRegistry().counter(MetricRegistry.name("shuffleCorruptMergedBlockChunks", new String[0]));
      this.METRIC_PUSH_BASED_SHUFFLE_MERGED_FETCH_FALLBACK_COUNT = this.metricRegistry().counter(MetricRegistry.name("shuffleMergedFetchFallbackCount", new String[0]));
      this.METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_BLOCKS_FETCHED = this.metricRegistry().counter(MetricRegistry.name("shuffleMergedRemoteBlocksFetched", new String[0]));
      this.METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_BLOCKS_FETCHED = this.metricRegistry().counter(MetricRegistry.name("shuffleMergedLocalBlocksFetched", new String[0]));
      this.METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_CHUNKS_FETCHED = this.metricRegistry().counter(MetricRegistry.name("shuffleMergedRemoteChunksFetched", new String[0]));
      this.METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_CHUNKS_FETCHED = this.metricRegistry().counter(MetricRegistry.name("shuffleMergedLocalChunksFetched", new String[0]));
      this.METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_BYTES_READ = this.metricRegistry().counter(MetricRegistry.name("shuffleMergedRemoteBytesRead", new String[0]));
      this.METRIC_PUSH_BASED_SHUFFLE_MERGED_LOCAL_BYTES_READ = this.metricRegistry().counter(MetricRegistry.name("shuffleMergedLocalBytesRead", new String[0]));
      this.METRIC_PUSH_BASED_SHUFFLE_MERGED_REMOTE_REQS_DURATION = this.metricRegistry().counter(MetricRegistry.name("shuffleMergedRemoteReqsDuration", new String[0]));
      this.METRIC_INPUT_BYTES_READ = this.metricRegistry().counter(MetricRegistry.name("bytesRead", new String[0]));
      this.METRIC_INPUT_RECORDS_READ = this.metricRegistry().counter(MetricRegistry.name("recordsRead", new String[0]));
      this.METRIC_OUTPUT_BYTES_WRITTEN = this.metricRegistry().counter(MetricRegistry.name("bytesWritten", new String[0]));
      this.METRIC_OUTPUT_RECORDS_WRITTEN = this.metricRegistry().counter(MetricRegistry.name("recordsWritten", new String[0]));
      this.METRIC_RESULT_SIZE = this.metricRegistry().counter(MetricRegistry.name("resultSize", new String[0]));
      this.METRIC_DISK_BYTES_SPILLED = this.metricRegistry().counter(MetricRegistry.name("diskBytesSpilled", new String[0]));
      this.METRIC_MEMORY_BYTES_SPILLED = this.metricRegistry().counter(MetricRegistry.name("memoryBytesSpilled", new String[0]));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
