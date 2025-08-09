package scala.reflect.api;

import scala.Function0;
import scala.Function1;
import scala.ScalaReflectionException;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\rMd\u0001DA\b\u0003#\u0001\n1!\u0001\u0002 \r-\u0004bBA\u0015\u0001\u0011\u0005\u00111\u0006\u0003\b\u0003g\u0001!\u0011AA\u001b\t\u001d\ty\n\u0001B\u0001\u0003C#qAa\u0001\u0001\u0005\u0003\u0011)\u0001B\u0004\u0003H\u0001\u0011\tA!\u0013\u0005\u000f\tM\u0004A!\u0001\u0003v\u00119!Q\u0012\u0001\u0003\u0002\t=\u0005\"CB5\u0001\t\u0007i\u0011AA'\r-\t9\u0005\u0001I\u0001\u0004\u0003\tI%a\u0014\t\u000f\u0005%\u0012\u0002\"\u0001\u0002,!9\u00111J\u0005\u0007\u0002\u00055CaBA)\u0013\t\u0005\u00111\u000b\u0005\b\u0003?Ja\u0011AA1\u0011\u001d\t9'\u0003D\u0001\u0003SBq!!!\n\r\u0003\t\u0019\tC\u0004\u0002\u0010&!\t!!%\t\u000f\u0005e\u0015\u0002\"\u0001\u0002\u001c\"9\u00111`\u0005\u0005\u0002\u0005E\u0005bBA\u007f\u0013\u0011\u0005\u0011q \u0005\b\u0005{IA\u0011AAI\u0011\u001d\u0011y$\u0003D\u0001\u0003#CqA!\u0011\n\t\u0003\u0011\u0019\u0005C\u0004\u0003.%!\t\"!%\t\u000f\t-\u0014\u0002\"\u0001\u0002\u0012\"9!QN\u0005\u0005\u0002\t=\u0004b\u0002BB\u0013\u0011\u0005\u0011\u0011\u0013\u0005\b\u0005\u000bKA\u0011AAI\u0011\u001d\u00119)\u0003C\u0001\u0005\u0013CqAa2\n\r\u0003\u0011I\rC\u0004\u0003^&1\tAa8\t\u000f\t5\u0018B\"\u0001\u0002N!9!Q_\u0005\u0007\u0002\u00055\u0003b\u0002B|\u0013\u0019\u0005!\u0011 \u0005\b\u0005{La\u0011\u0001B\u0000\u0011\u001d\u0019\u0019!\u0003D\u0001\u0003kCqa!\u0002\n\r\u0003\t)\fC\u0004\u0004\b%1\t!a;\t\u000f\r=\u0011B\"\u0001\u0002l\"91\u0011C\u0005\u0007\u0002\u0005-\bbBB\n\u0013\u0019\u0005\u0011\u0011\u0013\u0005\b\u0007+Ia\u0011AAI\u0011\u001d\u00199\"\u0003D\u0001\u0003#Cqa!\u0007\n\r\u0003\t\t\nC\u0004\u0004\u001c%1\t!!%\t\u000f\ru\u0011B\"\u0001\u0002\u0012\"91qD\u0005\u0007\u0002\u0005E\u0005bBB\u0011\u0013\u0019\u0005\u0011Q\n\u0005\b\u0007GIa\u0011AAI\u0011\u001d\u0019)#\u0003D\u0001\u0003#Cqaa\n\n\r\u0003\t\t\nC\u0004\u0004*%1\t!!%\t\u000f\r-\u0012B\"\u0001\u0002\u0012\"91QF\u0005\u0007\u0002\u0005E\u0005bBB\u0018\u0013\u0019\u0005\u0011\u0011\u0013\u0005\b\u0007cIa\u0011AAI\u0011\u001d\u0019\u0019$\u0003D\u0001\u0003#Cqa!\u000e\n\r\u0003\t\t\nC\u0004\u00048%1\t!!%\t\u000f\re\u0012B\"\u0001\u0002\u0012\"911H\u0005\u0007\u0002\u0005E\u0005bBB\u001f\u0013\u0019\u00051q\b\u0005\b\u0007\u0017Ja\u0011AB'\u0011\u001d\u0019I&\u0003D\u0001\u00077Bqaa\u0019\n\r\u0003\u0019)GB\u0006\u0003\u000e\u0001\u0001\n1!\u0001\u0003\u0010\te\u0001bBA\u0015\u0003\u0012\u0005\u00111F\u0003\u0007\u0003#\n%A!\u0005\t\u000f\u0005m\u0018\t\"\u0012\u0002\u0012\"9\u0011Q`!\u0005F\t]\u0001b\u0002B\u000f\u0003\u001a\u0005\u0011\u0011\u0013\u0005\b\u0005?\te\u0011AAI\u0011\u001d\u0011\t#\u0011D\u0001\u0003#CqAa\tB\r\u0003\t\t\nC\u0004\u0003&\u00053\t!!%\t\u000f\t\u001d\u0012I\"\u0001\u0002\u0012\"9!\u0011F!\u0007\u0002\u0005E\u0005b\u0002B\u0016\u0003\u001a\u0005\u0011\u0011\u0013\u0005\b\u0005[\tE\u0011KAI\u0011\u001d\u0011y#\u0011D\u0001\u0003\u001bBqA!\rB\r\u0003\ti\u0005C\u0004\u00034\u00053\t!!\u0014\t\u000f\tU\u0012I\"\u0001\u0002\u0012\"9!qG!\u0007\u0002\u0005E\u0005b\u0002B\u001d\u0003\u001a\u0005\u0011\u0011\u0013\u0005\b\u0005w\te\u0011AAI\r-\tI\u000b\u0001I\u0001\u0004\u0003\tY+!(\t\u000f\u0005%b\u000b\"\u0001\u0002,\u00151\u0011\u0011\u000b,\u0003\u0003[Cq!a-W\r\u0003\t)\fC\u0004\u0002BZ3\t!a1\t\u000f\u0005%gK\"\u0001\u00026\"9\u0011q\u0012,\u0005F\u0005E\u0005bBAM-\u0012\u0015\u00131\u0014\u0005\b\u0003\u00174f\u0011AAI\u0011\u001d\tiM\u0016D\u0001\u0003#Cq!a4W\r\u0003\t\t\nC\u0004\u0002RZ3\t!!%\t\u000f\u0005\u001dhK\"\u0001\u0002\u0012\"9\u0011\u0011\u001e,\u0007\u0002\u0005-ha\u0003B)\u0001A\u0005\u0019\u0011\u0001B*\u0005\u000bBq!!\u000be\t\u0003\tY\u0003C\u0004\u0003>\u0011$)%!%\t\u000f\t\u0005C\r\"\u0012\u0003D!9!Q\u000b3\u0007\u0002\u0005E\u0005bBAuI\u001a\u0005\u00111\u001e\u0005\b\u0005/\"g\u0011\u0001B-\u0011\u001d\u0011\u0019\u0007\u001aD\u0001\u00053BqA!\u001ae\r\u0003\t\t\nC\u0004\u0003h\u00114\t!!.\t\u000f\t%DM\"\u0001\u0002l\u001aY!Q\u0010\u0001\u0011\u0002\u0007\u0005!q\u0010B9\u0011\u001d\tIc\u001cC\u0001\u0003WAqA!!p\r\u0003\ti\u0005C\u0004\u0003l=$)%!%\t\u000f\t5t\u000e\"\u0012\u0003p\u0019Y!q\u0013\u0001\u0011\u0002\u0007\u0005!\u0011\u0014BF\u0011\u001d\tI\u0003\u001eC\u0001\u0003WAqAa!u\t\u000b\n\t\nC\u0004\u0003\bR$)E!#\t\u000f\tmEO\"\u0001\u0002\u0012\"9!Q\u0014;\u0007\u0002\u0005E\u0005b\u0002BPi\u001a\u0005\u0011\u0011\u0013\u0005\b\u0005C#h\u0011AAI\u0011\u001d\u0011\u0019\u000b\u001eD\u0001\u0003#CqAa*u\r\u0003\t\t\nC\u0004\u0003*R4\t!!%\t\u000f\t-FO\"\u0001\u0003.\"9!Q\u0017;\u0007\u0002\u0005-\bb\u0002B\\i\u001a\u0005\u0011Q\n\u0005\b\u0005s#h\u0011AA[\u0011\u001d\u0011Y\f\u001eD\u0001\u0003kCqA!0u\r\u0003\u0011y\fC\u0004\u0002jR4\t!a;\t\u000f\t\u0015GO\"\u0001\u0002N\t91+_7c_2\u001c(\u0002BA\n\u0003+\t1!\u00199j\u0015\u0011\t9\"!\u0007\u0002\u000fI,g\r\\3di*\u0011\u00111D\u0001\u0006g\u000e\fG.Y\u0002\u0001'\r\u0001\u0011\u0011\u0005\t\u0005\u0003G\t)#\u0004\u0002\u0002\u001a%!\u0011qEA\r\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\"!!\f\u0011\t\u0005\r\u0012qF\u0005\u0005\u0003c\tIB\u0001\u0003V]&$(AB*z[\n|G.\u0005\u0003\u00028\u0005u\u0002\u0003BA\u0012\u0003sIA!a\u000f\u0002\u001a\t!a*\u001e7m%\u0019\ty$!\t\u0002D\u00191\u0011\u0011\t\u0001\u0001\u0003{\u0011A\u0002\u0010:fM&tW-\\3oiz\u00022!!\u0012\n\u001b\u0005\u0001!!C*z[\n|G.\u00119j'\rI\u0011\u0011E\u0001\u0006_^tWM]\u000b\u0003\u0003\u001f\u00022!!\u0012\u0003\u0005!q\u0015-\\3UsB,\u0017\u0003BA\u001c\u0003+\u0002B!!\u0012\u0002X%!\u0011\u0011LA.\u0005\u0011q\u0015-\\3\n\t\u0005u\u0013\u0011\u0003\u0002\u0006\u001d\u0006lWm]\u0001\u0005]\u0006lW-\u0006\u0002\u0002dA\u0019\u0011Q\r\u0007\u000e\u0003%\t\u0001BZ;mY:\u000bW.Z\u000b\u0003\u0003W\u0002B!!\u001c\u0002|9!\u0011qNA<!\u0011\t\t(!\u0007\u000e\u0005\u0005M$\u0002BA;\u0003;\ta\u0001\u0010:p_Rt\u0014\u0002BA=\u00033\ta\u0001\u0015:fI\u00164\u0017\u0002BA?\u0003\u007f\u0012aa\u0015;sS:<'\u0002BA=\u00033\t1\u0001]8t+\t\t)\t\u0005\u0003\u0002F\u0005\u001d\u0015\u0002BAE\u0003\u0017\u0013\u0001\u0002U8tSRLwN\\\u0005\u0005\u0003\u001b\u000b\tBA\u0005Q_NLG/[8og\u00061\u0011n\u001d+za\u0016,\"!a%\u0011\t\u0005\r\u0012QS\u0005\u0005\u0003/\u000bIBA\u0004C_>dW-\u00198\u0002\r\u0005\u001cH+\u001f9f+\t\ti\nE\u0002\u0002F\r\u0011!\u0002V=qKNKXNY8m#\u0011\t9$a)\u0013\r\u0005\u0015\u0016qUA(\r\u0019\t\t\u0005\u0001\u0001\u0002$B\u0019\u0011Q\t,\u0003\u001bQK\b/Z*z[\n|G.\u00119j'\u00151\u0016\u0011EA\"!\u0011\t)%a,\n\t\u0005E\u00161\f\u0002\t)f\u0004XMT1nK\u0006\tBo\u001c+za\u0016\u001cuN\\:ueV\u001cGo\u001c:\u0016\u0005\u0005]\u0006\u0003BA#\u0003sKA!a/\u0002>\n!A+\u001f9f\u0013\u0011\ty,!\u0005\u0003\u000bQK\b/Z:\u0002\u0011Q|G+\u001f9f\u0013:$B!a.\u0002F\"9\u0011q\u0019.A\u0002\u0005]\u0016\u0001B:ji\u0016\fa\u0001^8UsB,\u0017aD5t\u0007>tGO]1wCJL\u0017M\u001c;\u0002\u0017%\u001c8i\u001c<be&\fg\u000e^\u0001\fSN\fE.[1t)f\u0004X-\u0001\bjg\u0006\u00137\u000f\u001e:bGR$\u0016\u0010]3)\u0017\u0005\f).a7\u0002^\u0006\u0005\u00181\u001d\t\u0005\u0003G\t9.\u0003\u0003\u0002Z\u0006e!A\u00033faJ,7-\u0019;fI\u00069Q.Z:tC\u001e,\u0017EAAp\u0003Y)8/\u001a\u0011jg\u0006\u00137\u000f\u001e:bGR\u0004\u0013N\\:uK\u0006$\u0017!B:j]\u000e,\u0017EAAs\u0003\u0019\u0011d&M\u0019/a\u0005i\u0011n]#ySN$XM\u001c;jC2\f!\u0002^=qKB\u000b'/Y7t+\t\ti\u000f\u0005\u0004\u0002p\u0006U\u0018q\n\b\u0005\u0003G\t\t0\u0003\u0003\u0002t\u0006e\u0011a\u00029bG.\fw-Z\u0005\u0005\u0003o\fIP\u0001\u0003MSN$(\u0002BAz\u00033\ta![:UKJl\u0017AB1t)\u0016\u0014X.\u0006\u0002\u0003\u0002A\u0019\u0011Q\t\u0003\u0003\u0015Q+'/\\*z[\n|G.\u0005\u0003\u00028\t\u001d!C\u0002B\u0005\u0005\u0017\tyE\u0002\u0004\u0002B\u0001\u0001!q\u0001\t\u0004\u0003\u000b\n%!\u0004+fe6\u001c\u00160\u001c2pY\u0006\u0003\u0018nE\u0003B\u0003C\t\u0019\u0005\u0005\u0003\u0002F\tM\u0011\u0002\u0002B\u000b\u00037\u0012\u0001\u0002V3s[:\u000bW.Z\u000b\u0003\u00053\u0011bAa\u0007\u0003\u0002\t-aABA!\u0001\u0001\u0011I\"A\u0003jgZ\u000bG.\u0001\u0005jgN#\u0018M\u00197f\u0003\u0015I7OV1s\u0003)I7/Q2dKN\u001cxN]\u0001\tSN<U\r\u001e;fe\u0006A\u0011n]*fiR,'/\u0001\u0007jg>3XM\u001d7pC\u0012,G-\u0001\u0004jg2\u000b'0_\u0001\u0013SN|e/\u001a:m_\u0006$W\rZ'fi\"|G-\u0001\u0005bG\u000e,7o]3e\u0003\u00199W\r\u001e;fe\u000611/\u001a;uKJ\fq\"[:QCJ\fW.Q2dKN\u001cxN]\u0001\u000fSN\u001c\u0015m]3BG\u000e,7o]8s\u0003II7\u000fU1sC6<\u0016\u000e\u001e5EK\u001a\fW\u000f\u001c;\u0002\u001b%\u001c()\u001f(b[\u0016\u0004\u0016M]1n\u0003!I7/T3uQ>$\u0017!D5t\u0007>t7\u000f\u001e:vGR|'/\u0001\u0005bg6+G\u000f[8e+\t\u0011)\u0005E\u0002\u0002F\u0015\u0011A\"T3uQ>$7+_7c_2\fB!a\u000e\u0003LI1!Q\nB(\u0005\u00031a!!\u0011\u0001\u0001\t-\u0003cAA#I\nyQ*\u001a;i_\u0012\u001c\u00160\u001c2pY\u0006\u0003\u0018nE\u0003e\u0003C\u0011Y!\u0001\u000bjgB\u0013\u0018.\\1ss\u000e{gn\u001d;sk\u000e$xN]\u0001\ba\u0006\u0014\u0018-\\:t+\t\u0011Y\u0006\u0005\u0004\u0002p\u0006U\u0018Q\u001e\u0015\fU\u0006U\u00171\u001cB0\u0003C\f\u0019/\t\u0002\u0003b\u0005ARo]3!AB\f'/Y7MSN$8\u000f\u0019\u0011j]N$X-\u00193\u0002\u0015A\f'/Y7MSN$8/A\u0005jgZ\u000b'/\u0019:hg\u0006Q!/\u001a;ve:$\u0016\u0010]3\u0002\u0015\u0015D8-\u001a9uS>t7/\u0001\u0005jg6{G-\u001e7f\u0003!\t7/T8ek2,WC\u0001B9!\r\t)E\u0002\u0002\r\u001b>$W\u000f\\3Ts6\u0014w\u000e\\\t\u0005\u0003o\u00119H\u0005\u0004\u0003z\tm$\u0011\u0001\u0004\u0007\u0003\u0003\u0002\u0001Aa\u001e\u0011\u0007\u0005\u0015sNA\bN_\u0012,H.Z*z[\n|G.\u00119j'\u0015y\u0017\u0011\u0005B\u0006\u0003-iw\u000eZ;mK\u000ec\u0017m]:\u0002\u000f%\u001c8\t\\1tg\u0006i\u0011n]'pIVdWm\u00117bgN\fq!Y:DY\u0006\u001c8/\u0006\u0002\u0003\fB\u0019\u0011QI\u0004\u0003\u0017\rc\u0017m]:Ts6\u0014w\u000e\\\t\u0005\u0003o\u0011\tJ\u0005\u0004\u0003\u0014\nU\u0015Q\u0014\u0004\u0007\u0003\u0003\u0002\u0001A!%\u0011\u0007\u0005\u0015CO\u0001\bDY\u0006\u001c8oU=nE>d\u0017\t]5\u0014\u000bQ\f\t#a*\u0002\u0017%\u001c\bK]5nSRLg/Z\u0001\nSNtU/\\3sS\u000e\f1#[:EKJLg/\u001a3WC2,Xm\u00117bgN\fq![:Ue\u0006LG/A\bjg\u0006\u00137\u000f\u001e:bGR\u001cE.Y:tQ-a\u0018Q[An\u0003;\f\t/a9\u0002\u0017%\u001c8)Y:f\u00072\f7o]\u0001\tSN\u001cV-\u00197fI\u0006)2N\\8x]\u0012K'/Z2u'V\u00147\r\\1tg\u0016\u001cXC\u0001BX!\u0019\tiG!-\u0002P%!!1WA@\u0005\r\u0019V\r^\u0001\fE\u0006\u001cXm\u00117bgN,7/\u0001\u0004n_\u0012,H.Z\u0001\tg\u0016dg\rV=qK\u0006QA\u000f[5t!J,g-\u001b=\u0002\u0017M,\b/\u001a:Qe\u00164\u0017\u000e\u001f\u000b\u0005\u0003o\u0013\t\r\u0003\u0005\u0003D\u0006%\u0001\u0019AA\\\u0003!\u0019X\u000f]3siB,\u0017A\u00059sS6\f'/_\"p]N$(/^2u_J\fa\"Y:t_\u000eL\u0017\r^3e\r&dW-\u0006\u0002\u0003LB!!Q\u001aBj\u001b\t\u0011yM\u0003\u0003\u0003R\u0006U\u0011AA5p\u0013\u0011\u0011)Na4\u0003\u0019\u0005\u00137\u000f\u001e:bGR4\u0015\u000e\\3)\u0017u\t).a7\u0003Z\u0006\u0005\u00181]\u0011\u0003\u00057\fQ$^:fA\u0001\u0004xn\u001d\u0018t_V\u00148-\u001a\u0018gS2,\u0007\rI5ogR,\u0017\rZ\u0001\fC:tw\u000e^1uS>t7/\u0006\u0002\u0003bB1\u0011q^A{\u0005G\u0004B!!\u0012\u0003f&!!q\u001dBu\u0005)\teN\\8uCRLwN\\\u0005\u0005\u0005W\f\tBA\u0006B]:|G/\u0019;j_:\u001c\u0018aD2p[B\fg.[8o'fl'm\u001c7)\u0017}\t).a7\u0003r\u0006\u0005\u00181]\u0011\u0003\u0005g\f1)^:fA\u0001\u001cw.\u001c9b]&|g\u000e\u0019\u0011j]N$X-\u00193-A\t,H\u000f\t2fo\u0006\u0014X\rI8gAA|7o]5cY\u0016\u00043\r[1oO\u0016\u001c\b%\u001b8!E\u0016D\u0017M^5pe\u0006I1m\\7qC:LwN\\\u0001\u0010if\u0004XmU5h]\u0006$XO]3J]R!\u0011q\u0017B~\u0011\u001d\t9-\ta\u0001\u0003o\u000ba!\u001b8g_&sG\u0003BA\\\u0007\u0003Aq!a2#\u0001\u0004\t9,A\u0007usB,7+[4oCR,(/Z\u0001\u0005S:4w.\u0001\u000bbY2|e/\u001a:sS\u0012$WM\\*z[\n|Gn\u001d\u0015\fK\u0005U\u00171\\B\u0006\u0003C\f\u0019/\t\u0002\u0004\u000e\u00059Ro]3!A>4XM\u001d:jI\u0016\u001c\b\rI5ogR,\u0017\rZ\u0001\n_Z,'O]5eKN\fA\"\u00197uKJt\u0017\r^5wKN\f1\"[:Ts:$\b.\u001a;jG\u0006A\u0012n]%na2,W.\u001a8uCRLwN\\!si&4\u0017m\u0019;\u0002\u001b%\u001c\bK]5wCR,G\u000b[5t\u0003%I7\u000f\u0015:jm\u0006$X-A\bjgB\u0013x\u000e^3di\u0016$G\u000b[5t\u0003-I7\u000f\u0015:pi\u0016\u001cG/\u001a3\u0002\u0011%\u001c\b+\u001e2mS\u000e\fQ\u0002\u001d:jm\u0006$XmV5uQ&t\u0017!C5t!\u0006\u001c7.Y4f\u00039I7\u000fU1dW\u0006<Wm\u00117bgN\f\u0001\"[:Ti\u0006$\u0018nY\u0001\bSN4\u0015N\\1m\u0003)I7/\u00112tiJ\f7\r^\u0001\u0013SN\f%m\u001d;sC\u000e$xJ^3se&$W-A\u0004jg6\u000b7M]8\u0002\u0017%\u001c\b+\u0019:b[\u0016$XM]\u0001\u000eSN\u001c\u0006/Z2jC2L'0\u001a3\u0002\r%\u001c(*\u0019<b\u0003)I7/S7qY&\u001c\u0017\u000e^\u0001\u000bSNT\u0015M^1F]Vl\u0017\u0001E5t\u0015\u00064\u0018-\u00118o_R\fG/[8o\u0003\u0019y'/\u00127tKR!\u0011qJB!\u0011!\u0019\u0019%\u0010CA\u0002\r\u0015\u0013aA1miB1\u00111EB$\u0003\u001fJAa!\u0013\u0002\u001a\tAAHY=oC6,g(\u0001\u0004gS2$XM\u001d\u000b\u0005\u0003\u001f\u001ay\u0005C\u0004\u0004Ry\u0002\raa\u0015\u0002\t\r|g\u000e\u001a\t\t\u0003G\u0019)&a\u0014\u0002\u0014&!1qKA\r\u0005%1UO\\2uS>t\u0017'A\u0002nCB$B!a\u0014\u0004^!91qL A\u0002\r\u0005\u0014!\u00014\u0011\u0011\u0005\r2QKA(\u0003\u001f\n\u0001b];dQRC\u0017\r\u001e\u000b\u0005\u0003\u001f\u001a9\u0007C\u0004\u0004R\u0001\u0003\raa\u0015\u0002\u00119{7+_7c_2\u0004Ba!\u001c\u0004p5\u0011\u0011\u0011C\u0005\u0005\u0007c\n\tB\u0001\u0005V]&4XM]:f\u0001"
)
public interface Symbols {
   SymbolApi NoSymbol();

   static void $init$(final Symbols $this) {
   }

   public interface SymbolApi {
      SymbolApi owner();

      Names.NameApi name();

      String fullName();

      Position pos();

      // $FF: synthetic method
      static boolean isType$(final SymbolApi $this) {
         return $this.isType();
      }

      default boolean isType() {
         return false;
      }

      // $FF: synthetic method
      static TypeSymbolApi asType$(final SymbolApi $this) {
         return $this.asType();
      }

      default TypeSymbolApi asType() {
         throw new ScalaReflectionException((new StringBuilder(14)).append(this).append(" is not a type").toString());
      }

      // $FF: synthetic method
      static boolean isTerm$(final SymbolApi $this) {
         return $this.isTerm();
      }

      default boolean isTerm() {
         return false;
      }

      // $FF: synthetic method
      static TermSymbolApi asTerm$(final SymbolApi $this) {
         return $this.asTerm();
      }

      default TermSymbolApi asTerm() {
         throw new ScalaReflectionException((new StringBuilder(14)).append(this).append(" is not a term").toString());
      }

      // $FF: synthetic method
      static boolean isMethod$(final SymbolApi $this) {
         return $this.isMethod();
      }

      default boolean isMethod() {
         return false;
      }

      boolean isConstructor();

      // $FF: synthetic method
      static MethodSymbolApi asMethod$(final SymbolApi $this) {
         return $this.asMethod();
      }

      default MethodSymbolApi asMethod() {
         String msg = this.isOverloadedMethod() ? "encapsulates multiple overloaded alternatives and cannot be treated as a method. Consider invoking `<offending symbol>.asTerm.alternatives` and manually picking the required method" : "is not a method";
         throw new ScalaReflectionException((new StringBuilder(1)).append(this).append(" ").append(msg).toString());
      }

      // $FF: synthetic method
      static boolean isOverloadedMethod$(final SymbolApi $this) {
         return $this.isOverloadedMethod();
      }

      default boolean isOverloadedMethod() {
         return false;
      }

      // $FF: synthetic method
      static boolean isModule$(final SymbolApi $this) {
         return $this.isModule();
      }

      default boolean isModule() {
         return false;
      }

      // $FF: synthetic method
      static ModuleSymbolApi asModule$(final SymbolApi $this) {
         return $this.asModule();
      }

      default ModuleSymbolApi asModule() {
         throw new ScalaReflectionException((new StringBuilder(16)).append(this).append(" is not a module").toString());
      }

      // $FF: synthetic method
      static boolean isClass$(final SymbolApi $this) {
         return $this.isClass();
      }

      default boolean isClass() {
         return false;
      }

      // $FF: synthetic method
      static boolean isModuleClass$(final SymbolApi $this) {
         return $this.isModuleClass();
      }

      default boolean isModuleClass() {
         return false;
      }

      // $FF: synthetic method
      static ClassSymbolApi asClass$(final SymbolApi $this) {
         return $this.asClass();
      }

      default ClassSymbolApi asClass() {
         throw new ScalaReflectionException((new StringBuilder(15)).append(this).append(" is not a class").toString());
      }

      /** @deprecated */
      AbstractFile associatedFile();

      List annotations();

      /** @deprecated */
      SymbolApi companionSymbol();

      SymbolApi companion();

      Types.TypeApi typeSignatureIn(final Types.TypeApi site);

      Types.TypeApi infoIn(final Types.TypeApi site);

      Types.TypeApi typeSignature();

      Types.TypeApi info();

      /** @deprecated */
      List allOverriddenSymbols();

      List overrides();

      List alternatives();

      boolean isSynthetic();

      boolean isImplementationArtifact();

      boolean isPrivateThis();

      boolean isPrivate();

      boolean isProtectedThis();

      boolean isProtected();

      boolean isPublic();

      SymbolApi privateWithin();

      boolean isPackage();

      boolean isPackageClass();

      boolean isStatic();

      boolean isFinal();

      boolean isAbstract();

      boolean isAbstractOverride();

      boolean isMacro();

      boolean isParameter();

      boolean isSpecialized();

      boolean isJava();

      boolean isImplicit();

      boolean isJavaEnum();

      boolean isJavaAnnotation();

      SymbolApi orElse(final Function0 alt);

      SymbolApi filter(final Function1 cond);

      SymbolApi map(final Function1 f);

      SymbolApi suchThat(final Function1 cond);

      // $FF: synthetic method
      Symbols scala$reflect$api$Symbols$SymbolApi$$$outer();

      private static String overloadedMsg$1() {
         return "encapsulates multiple overloaded alternatives and cannot be treated as a method. Consider invoking `<offending symbol>.asTerm.alternatives` and manually picking the required method";
      }

      private static String vanillaMsg$1() {
         return "is not a method";
      }

      static void $init$(final SymbolApi $this) {
      }
   }

   public interface TermSymbolApi extends SymbolApi {
      // $FF: synthetic method
      static boolean isTerm$(final TermSymbolApi $this) {
         return $this.isTerm();
      }

      default boolean isTerm() {
         return true;
      }

      // $FF: synthetic method
      static TermSymbolApi asTerm$(final TermSymbolApi $this) {
         return $this.asTerm();
      }

      default TermSymbolApi asTerm() {
         return this;
      }

      boolean isVal();

      boolean isStable();

      boolean isVar();

      boolean isAccessor();

      boolean isGetter();

      boolean isSetter();

      boolean isOverloaded();

      boolean isLazy();

      // $FF: synthetic method
      static boolean isOverloadedMethod$(final TermSymbolApi $this) {
         return $this.isOverloadedMethod();
      }

      default boolean isOverloadedMethod() {
         List var10000 = this.alternatives();
         if (var10000 == null) {
            throw null;
         } else {
            for(List exists_these = var10000; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
               if (((SymbolApi)exists_these.head()).isMethod()) {
                  return true;
               }
            }

            return false;
         }
      }

      SymbolApi accessed();

      SymbolApi getter();

      SymbolApi setter();

      boolean isParamAccessor();

      boolean isCaseAccessor();

      boolean isParamWithDefault();

      boolean isByNameParam();

      // $FF: synthetic method
      Symbols scala$reflect$api$Symbols$TermSymbolApi$$$outer();

      // $FF: synthetic method
      static boolean $anonfun$isOverloadedMethod$1(final SymbolApi x$1) {
         return x$1.isMethod();
      }

      static void $init$(final TermSymbolApi $this) {
      }

      // $FF: synthetic method
      static Object $anonfun$isOverloadedMethod$1$adapted(final SymbolApi x$1) {
         return BoxesRunTime.boxToBoolean($anonfun$isOverloadedMethod$1(x$1));
      }
   }

   public interface TypeSymbolApi extends SymbolApi {
      Types.TypeApi toTypeConstructor();

      Types.TypeApi toTypeIn(final Types.TypeApi site);

      Types.TypeApi toType();

      // $FF: synthetic method
      static boolean isType$(final TypeSymbolApi $this) {
         return $this.isType();
      }

      default boolean isType() {
         return true;
      }

      // $FF: synthetic method
      static TypeSymbolApi asType$(final TypeSymbolApi $this) {
         return $this.asType();
      }

      default TypeSymbolApi asType() {
         return this;
      }

      boolean isContravariant();

      boolean isCovariant();

      boolean isAliasType();

      /** @deprecated */
      boolean isAbstractType();

      boolean isExistential();

      List typeParams();

      // $FF: synthetic method
      Symbols scala$reflect$api$Symbols$TypeSymbolApi$$$outer();

      static void $init$(final TypeSymbolApi $this) {
      }
   }

   public interface MethodSymbolApi extends TermSymbolApi {
      // $FF: synthetic method
      static boolean isMethod$(final MethodSymbolApi $this) {
         return $this.isMethod();
      }

      default boolean isMethod() {
         return true;
      }

      // $FF: synthetic method
      static MethodSymbolApi asMethod$(final MethodSymbolApi $this) {
         return $this.asMethod();
      }

      default MethodSymbolApi asMethod() {
         return this;
      }

      boolean isPrimaryConstructor();

      List typeParams();

      /** @deprecated */
      List paramss();

      List paramLists();

      boolean isVarargs();

      Types.TypeApi returnType();

      List exceptions();

      // $FF: synthetic method
      Symbols scala$reflect$api$Symbols$MethodSymbolApi$$$outer();

      static void $init$(final MethodSymbolApi $this) {
      }
   }

   public interface ModuleSymbolApi extends TermSymbolApi {
      SymbolApi moduleClass();

      // $FF: synthetic method
      static boolean isModule$(final ModuleSymbolApi $this) {
         return $this.isModule();
      }

      default boolean isModule() {
         return true;
      }

      // $FF: synthetic method
      static ModuleSymbolApi asModule$(final ModuleSymbolApi $this) {
         return $this.asModule();
      }

      default ModuleSymbolApi asModule() {
         return this;
      }

      // $FF: synthetic method
      Symbols scala$reflect$api$Symbols$ModuleSymbolApi$$$outer();

      static void $init$(final ModuleSymbolApi $this) {
      }
   }

   public interface ClassSymbolApi extends TypeSymbolApi {
      // $FF: synthetic method
      static boolean isClass$(final ClassSymbolApi $this) {
         return $this.isClass();
      }

      default boolean isClass() {
         return true;
      }

      // $FF: synthetic method
      static ClassSymbolApi asClass$(final ClassSymbolApi $this) {
         return $this.asClass();
      }

      default ClassSymbolApi asClass() {
         return this;
      }

      boolean isPrimitive();

      boolean isNumeric();

      boolean isDerivedValueClass();

      boolean isTrait();

      /** @deprecated */
      boolean isAbstractClass();

      boolean isCaseClass();

      boolean isSealed();

      Set knownDirectSubclasses();

      List baseClasses();

      SymbolApi module();

      Types.TypeApi selfType();

      Types.TypeApi thisPrefix();

      Types.TypeApi superPrefix(final Types.TypeApi supertpe);

      List typeParams();

      SymbolApi primaryConstructor();

      // $FF: synthetic method
      Symbols scala$reflect$api$Symbols$ClassSymbolApi$$$outer();

      static void $init$(final ClassSymbolApi $this) {
      }
   }
}
