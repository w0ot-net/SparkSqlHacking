package org.apache.spark.util;

import java.io.PrintStream;
import java.io.Serializable;
import java.net.URI;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.spark.internal.Logging;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0015rAB\u001d;\u0011\u0003a$I\u0002\u0004Eu!\u0005A(\u0012\u0005\u0006%\u0006!\t\u0001\u0016\u0005\b+\u0006\u0011\r\u0011\"\u0001W\u0011\u0019\u0011\u0017\u0001)A\u0005/\"91-\u0001b\u0001\n\u0003!\u0007B\u00028\u0002A\u0003%QMB\u0003p\u0003\u0001c\u0004\u000f\u0003\u0005x\u000f\tU\r\u0011\"\u0001W\u0011!AxA!E!\u0002\u00139\u0006\u0002C=\b\u0005+\u0007I\u0011\u0001,\t\u0011i<!\u0011#Q\u0001\n]C\u0001b_\u0004\u0003\u0016\u0004%\tA\u0016\u0005\ty\u001e\u0011\t\u0012)A\u0005/\")!k\u0002C\u0001{\"9\u0011qA\u0004\u0005B\u0005%\u0001\"CA\u0006\u000f\u0005\u0005I\u0011AA\u0007\u0011%\t)bBI\u0001\n\u0003\t9\u0002C\u0005\u0002.\u001d\t\n\u0011\"\u0001\u0002\u0018!I\u0011qF\u0004\u0012\u0002\u0013\u0005\u0011q\u0003\u0005\n\u0003c9\u0011\u0011!C!\u0003gA\u0011\"a\u0011\b\u0003\u0003%\t!!\u0012\t\u0013\u00055s!!A\u0005\u0002\u0005=\u0003\"CA.\u000f\u0005\u0005I\u0011IA/\u0011%\tYgBA\u0001\n\u0003\ti\u0007C\u0005\u0002x\u001d\t\t\u0011\"\u0011\u0002z!I\u0011QP\u0004\u0002\u0002\u0013\u0005\u0013q\u0010\u0005\n\u0003\u0003;\u0011\u0011!C!\u0003\u0007;!\"a\"\u0002\u0003\u0003E\t\u0001PAE\r%y\u0017!!A\t\u0002q\nY\t\u0003\u0004S;\u0011\u0005\u00111\u0015\u0005\n\u0003\u000fi\u0012\u0011!C#\u0003KC\u0011\"a*\u001e\u0003\u0003%\t)!+\t\u0013\u0005EV$!A\u0005\u0002\u0006M\u0006\"CAc;\u0005\u0005I\u0011BAd\u0011\u001d\ty-\u0001C\u0001\u0003#D\u0001\"!7\u0002\t\u0003Q\u00141\u001c\u0005\t\u0003G\fA\u0011\u0001\u001e\u0002f\"Q!1A\u0001\u0012\u0002\u0013\u0005!H!\u0002\t\u0011\t%\u0011\u0001\"\u0001;\u0005\u0017A\u0001Ba\u0007\u0002\t\u0003Q$Q\u0004\u0005\b\u0005\u001f\nA\u0011\u0002B)\u0011\u001d\u00119'\u0001C\u0001\u0005SB\u0011Ba\u001f\u0002#\u0003%\tA!\u0002\t\u000f\tu\u0014\u0001\"\u0001\u0003\u0000!A!QR\u0001\u0005\u0002i\u0012y\tC\u0004\u0003\u0016\u0006!IAa&\t\u0011\t\u0005\u0016\u0001\"\u0001;\u0005GCqA!*\u0002\t\u0013\u00119\u000bC\u0004\u0003@\u0006!IA!1\t\u000f\t\u001d\u0017\u0001\"\u0001\u0003J\"I!Q]\u0001\u0012\u0002\u0013\u0005!q\u001d\u0005\n\u0005W\f\u0011\u0013!C\u0001\u0005[D\u0011B!=\u0002#\u0003%\tA!\u0002\t\u0011\tM\u0018\u0001\"\u0001;\u0005kDqa!\u0002\u0002\t\u0013\u00199\u0001C\u0004\u0004\u0010\u0005!\ta!\u0005\u0002\u00155\u000bg/\u001a8Vi&d7O\u0003\u0002<y\u0005!Q\u000f^5m\u0015\tid(A\u0003ta\u0006\u00148N\u0003\u0002@\u0001\u00061\u0011\r]1dQ\u0016T\u0011!Q\u0001\u0004_J<\u0007CA\"\u0002\u001b\u0005Q$AC'bm\u0016tW\u000b^5mgN\u0019\u0011A\u0012'\u0011\u0005\u001dSU\"\u0001%\u000b\u0003%\u000bQa]2bY\u0006L!a\u0013%\u0003\r\u0005s\u0017PU3g!\ti\u0005+D\u0001O\u0015\tyE(\u0001\u0005j]R,'O\\1m\u0013\t\tfJA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012AQ\u0001\u0019\u0015\u0006\u0013v,\u0013,Z?N+E\u000bV%O\u000f~\u0003\u0016\t\u0016%`\u0017\u0016KV#A,\u0011\u0005a{fBA-^!\tQ\u0006*D\u0001\\\u0015\ta6+\u0001\u0004=e>|GOP\u0005\u0003=\"\u000ba\u0001\u0015:fI\u00164\u0017B\u00011b\u0005\u0019\u0019FO]5oO*\u0011a\fS\u0001\u001a\u0015\u0006\u0013v,\u0013,Z?N+E\u000bV%O\u000f~\u0003\u0016\t\u0016%`\u0017\u0016K\u0006%\u0001\u000bJ-f{F)\u0012$B+2#v,\u0012-D\u0019V#UiU\u000b\u0002KB\u0019am[,\u000f\u0005\u001dLgB\u0001.i\u0013\u0005I\u0015B\u00016I\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001\\7\u0003\u0007M+\u0017O\u0003\u0002k\u0011\u0006)\u0012JV-`\t\u00163\u0015)\u0016'U?\u0016C6\tT+E\u000bN\u0003#aD'bm\u0016t7i\\8sI&t\u0017\r^3\u0014\t\u001d1\u0015\u000f\u001e\t\u0003\u000fJL!a\u001d%\u0003\u000fA\u0013x\u000eZ;diB\u0011a-^\u0005\u0003m6\u0014AbU3sS\u0006d\u0017N_1cY\u0016\fqa\u001a:pkBLE-\u0001\u0005he>,\b/\u00133!\u0003)\t'\u000f^5gC\u000e$\u0018\nZ\u0001\fCJ$\u0018NZ1di&#\u0007%A\u0004wKJ\u001c\u0018n\u001c8\u0002\u0011Y,'o]5p]\u0002\"rA`A\u0001\u0003\u0007\t)\u0001\u0005\u0002\u0000\u000f5\t\u0011\u0001C\u0003x\u001d\u0001\u0007q\u000bC\u0003z\u001d\u0001\u0007q\u000bC\u0003|\u001d\u0001\u0007q+\u0001\u0005u_N#(/\u001b8h)\u00059\u0016\u0001B2paf$rA`A\b\u0003#\t\u0019\u0002C\u0004x!A\u0005\t\u0019A,\t\u000fe\u0004\u0002\u0013!a\u0001/\"91\u0010\u0005I\u0001\u0002\u00049\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u00033Q3aVA\u000eW\t\ti\u0002\u0005\u0003\u0002 \u0005%RBAA\u0011\u0015\u0011\t\u0019#!\n\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0014\u0011\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005-\u0012\u0011\u0005\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011Q\u0007\t\u0005\u0003o\t\t%\u0004\u0002\u0002:)!\u00111HA\u001f\u0003\u0011a\u0017M\\4\u000b\u0005\u0005}\u0012\u0001\u00026bm\u0006L1\u0001YA\u001d\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t9\u0005E\u0002H\u0003\u0013J1!a\u0013I\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t\t&a\u0016\u0011\u0007\u001d\u000b\u0019&C\u0002\u0002V!\u00131!\u00118z\u0011%\tIFFA\u0001\u0002\u0004\t9%A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003?\u0002b!!\u0019\u0002h\u0005ESBAA2\u0015\r\t)\u0007S\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA5\u0003G\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011qNA;!\r9\u0015\u0011O\u0005\u0004\u0003gB%a\u0002\"p_2,\u0017M\u001c\u0005\n\u00033B\u0012\u0011!a\u0001\u0003#\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011QGA>\u0011%\tI&GA\u0001\u0002\u0004\t9%\u0001\u0005iCND7i\u001c3f)\t\t9%\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003_\n)\tC\u0005\u0002Zm\t\t\u00111\u0001\u0002R\u0005yQ*\u0019<f]\u000e{wN\u001d3j]\u0006$X\r\u0005\u0002\u0000;M)Q$!$\u0002\u001aBA\u0011qRAK/^;f0\u0004\u0002\u0002\u0012*\u0019\u00111\u0013%\u0002\u000fI,h\u000e^5nK&!\u0011qSAI\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u00037\u000b\t+\u0004\u0002\u0002\u001e*!\u0011qTA\u001f\u0003\tIw.C\u0002w\u0003;#\"!!#\u0015\u0005\u0005U\u0012!B1qa2LHc\u0002@\u0002,\u00065\u0016q\u0016\u0005\u0006o\u0002\u0002\ra\u0016\u0005\u0006s\u0002\u0002\ra\u0016\u0005\u0006w\u0002\u0002\raV\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t),!1\u0011\u000b\u001d\u000b9,a/\n\u0007\u0005e\u0006J\u0001\u0004PaRLwN\u001c\t\u0007\u000f\u0006uvkV,\n\u0007\u0005}\u0006J\u0001\u0004UkBdWm\r\u0005\t\u0003\u0007\f\u0013\u0011!a\u0001}\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005%\u0007\u0003BA\u001c\u0003\u0017LA!!4\u0002:\t1qJ\u00196fGR\fq#\u001a=ue\u0006\u001cG/T1wK:\u001cun\u001c:eS:\fG/Z:\u0015\t\u0005M\u0017Q\u001b\t\u0004M.t\bBBAlG\u0001\u0007q+A\u0006d_>\u0014H-\u001b8bi\u0016\u001c\u0018AB73!\u0006$\b.\u0006\u0002\u0002^B!\u00111TAp\u0013\u0011\t\t/!(\u0003\t\u0019KG.Z\u0001\u0014GJ,\u0017\r^3SKB|'+Z:pYZ,'o\u001d\u000b\u0007\u0003O\fY0a@\u0011\t\u0005%\u0018q_\u0007\u0003\u0003WTA!!<\u0002p\u0006A!/Z:pYZ,'O\u0003\u0003\u0002r\u0006M\u0018a\u00029mk\u001eLgn\u001d\u0006\u0004\u0003kt\u0014aA5ws&!\u0011\u0011`Av\u00055\u0019\u0005.Y5o%\u0016\u001cx\u000e\u001c<fe\"9\u0011Q`\u0013A\u0002\u0005u\u0017!\u00053fM\u0006,H\u000e^%wsV\u001bXM\u001d#je\"I!\u0011A\u0013\u0011\u0002\u0003\u0007\u0011qN\u0001\u0012kN,Gj\\2bY6\u0013\u0014i]\"bG\",\u0017!H2sK\u0006$XMU3q_J+7o\u001c7wKJ\u001cH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\t\u001d!\u0006BA8\u00037\taC]3t_24X\rR3qK:$WM\\2z!\u0006$\bn\u001d\u000b\u0006K\n5!q\u0003\u0005\b\u0005\u001f9\u0003\u0019\u0001B\t\u0003%\t'\u000f^5gC\u000e$8\u000f\u0005\u0003H\u0005'1\u0015b\u0001B\u000b\u0011\n)\u0011I\u001d:bs\"9!\u0011D\u0014A\u0002\u0005u\u0017AD2bG\",G)\u001b:fGR|'/_\u0001\u0015C\u0012$G)\u001a9f]\u0012,gnY5fgR{\u0017J^=\u0015\u0011\t}!\u0011\u0007B%\u0005\u0017\"BA!\t\u0003(A\u0019qIa\t\n\u0007\t\u0015\u0002J\u0001\u0003V]&$\bb\u0002B\u0015Q\u0001\u000f!1F\u0001\faJLg\u000e^*ue\u0016\fW\u000e\u0005\u0003\u0002\u001c\n5\u0012\u0002\u0002B\u0018\u0003;\u00131\u0002\u0015:j]R\u001cFO]3b[\"9!1\u0007\u0015A\u0002\tU\u0012AA7e!\u0011\u00119D!\u0012\u000e\u0005\te\"\u0002\u0002B\u001e\u0005{\t!\u0002Z3tGJL\u0007\u000f^8s\u0015\u0011\u0011yD!\u0011\u0002\r5|G-\u001e7f\u0015\u0011\u0011\u0019%a=\u0002\t\r|'/Z\u0005\u0005\u0005\u000f\u0012IDA\fEK\u001a\fW\u000f\u001c;N_\u0012,H.\u001a#fg\u000e\u0014\u0018\u000e\u001d;pe\"9!q\u0002\u0015A\u0002\u0005M\u0007B\u0002B'Q\u0001\u0007q+A\u0006jmf\u001cuN\u001c4OC6,\u0017!E1eI\u0016C8\r\\;tS>t'+\u001e7fgRA!\u0011\u0005B*\u0005G\u0012)\u0007C\u0004\u0003V%\u0002\rAa\u0016\u0002\u0017%4\u0018pU3ui&twm\u001d\t\u0005\u00053\u0012y&\u0004\u0002\u0003\\)!!Q\fB!\u0003!\u0019X\r\u001e;j]\u001e\u001c\u0018\u0002\u0002B1\u00057\u00121\"\u0013<z'\u0016$H/\u001b8hg\"1!QJ\u0015A\u0002]CqAa\r*\u0001\u0004\u0011)$\u0001\tck&dG-\u0013<z'\u0016$H/\u001b8hgRA!1\u000eB8\u0005k\u0012I\b\u0006\u0003\u0003X\t5\u0004b\u0002B\u0015U\u0001\u000f!1\u0006\u0005\b\u0005cR\u0003\u0019\u0001B:\u0003-\u0011X-\\8uKJ+\u0007o\\:\u0011\t\u001d\u000b9l\u0016\u0005\b\u0005oR\u0003\u0019\u0001B:\u0003\u001dIg/\u001f)bi\"D\u0011B!\u0001+!\u0003\u0005\r!a\u001c\u00025\t,\u0018\u000e\u001c3Jmf\u001cV\r\u001e;j]\u001e\u001cH\u0005Z3gCVdG\u000fJ\u001a\u0002\u001f1|\u0017\rZ%wsN+G\u000f^5oON$\u0002B!!\u0003\u0006\n%%1\u0012\u000b\u0005\u0005/\u0012\u0019\tC\u0004\u0003*1\u0002\u001dAa\u000b\t\r\t\u001dE\u00061\u0001X\u00031\u0019X\r\u001e;j]\u001e\u001ch)\u001b7f\u0011\u001d\u0011\t\b\fa\u0001\u0005gBqAa\u001e-\u0001\u0004\u0011\u0019(A\tqe>\u001cWm]:Jmf\u0004\u0016\r\u001e5Be\u001e$bA!\t\u0003\u0012\nM\u0005b\u0002B+[\u0001\u0007!q\u000b\u0005\b\u0005oj\u0003\u0019\u0001B:\u0003Q\u0001(o\\2fgN\u0014V-\\8uKJ+\u0007o\\!sOR1!\u0011\u0014BO\u0005?#BA!\t\u0003\u001c\"9!\u0011\u0006\u0018A\u0004\t-\u0002b\u0002B+]\u0001\u0007!q\u000b\u0005\b\u0005cr\u0003\u0019\u0001B:\u0003M9W\r^'pIVdW\rR3tGJL\u0007\u000f^8s+\t\u0011)$A\fdY\u0016\f'/\u0013<z%\u0016\u001cx\u000e\\;uS>tg)\u001b7fgRA!\u0011\u0005BU\u0005s\u0013i\fC\u0004\u0003,B\u0002\rA!,\u0002\t5$\u0017\n\u001a\t\u0005\u0005_\u0013),\u0004\u0002\u00032*!!1\u0017B\u001f\u0003\tIG-\u0003\u0003\u00038\nE&\u0001E'pIVdWMU3wSNLwN\\%e\u0011\u001d\u0011Y\f\ra\u0001\u0003;\f\u0001\u0003Z3gCVdGoQ1dQ\u00164\u0015\u000e\\3\t\r\t5\u0003\u00071\u0001X\u0003e\u0019G.Z1s\u0013:4\u0018\r\\5e\u0013ZL8)Y2iK\u001aKG.Z:\u0015\r\t\u0005\"1\u0019Bc\u0011\u001d\u0011Y+\ra\u0001\u0005[CqAa/2\u0001\u0004\ti.A\fsKN|GN^3NCZ,gnQ8pe\u0012Lg.\u0019;fgRq!1\u001aBh\u0005#\u0014\u0019N!7\u0003^\n\u0005HcA3\u0003N\"9!\u0011\u0006\u001aA\u0004\t-\u0002BBAle\u0001\u0007q\u000bC\u0004\u0003VI\u0002\rAa\u0016\t\u0013\tU'\u0007%AA\u0002\t]\u0017A\u00058p\u0007\u0006\u001c\u0007.Z%wsN+G\u000f^5oON\u0004RaRA\\\u0005/BqAa73\u0001\u0004\ty'\u0001\u0006ue\u0006t7/\u001b;jm\u0016D\u0001Ba83!\u0003\u0005\r!Z\u0001\u000bKb\u001cG.^:j_:\u001c\b\"\u0003BreA\u0005\t\u0019AA8\u0003\u0019I7\u000fV3ti\u0006\t#/Z:pYZ,W*\u0019<f]\u000e{wN\u001d3j]\u0006$Xm\u001d\u0013eK\u001a\fW\u000f\u001c;%gU\u0011!\u0011\u001e\u0016\u0005\u0005/\fY\"A\u0011sKN|GN^3NCZ,gnQ8pe\u0012Lg.\u0019;fg\u0012\"WMZ1vYR$S'\u0006\u0002\u0003p*\u001aQ-a\u0007\u0002CI,7o\u001c7wK6\u000bg/\u001a8D_>\u0014H-\u001b8bi\u0016\u001cH\u0005Z3gCVdG\u000f\n\u001c\u0002\u001f\r\u0014X-\u0019;f\u000bb\u001cG.^:j_:$\u0002Ba>\u0003~\u000e\u000511\u0001\t\u0005\u0005o\u0011I0\u0003\u0003\u0003|\ne\"aC#yG2,H-\u001a*vY\u0016DaAa@7\u0001\u00049\u0016AB2p_J$7\u000fC\u0004\u0003VY\u0002\rAa\u0016\t\r\t5c\u00071\u0001X\u0003QI7/\u00138wC2LG-U;fef\u001cFO]5oOR!\u0011qNB\u0005\u0011\u001d\u0019Ya\u000ea\u0001\u0007\u001b\ta\u0001^8lK:\u001c\b\u0003B$\u0003\u0014]\u000b\u0001\u0003]1sg\u0016\fV/\u001a:z!\u0006\u0014\u0018-\\:\u0015\t\rM1Q\u0003\t\b\u000f\u0006u\u0016qN,X\u0011\u001d\u00199\u0002\u000fa\u0001\u00073\t1!\u001e:j!\u0011\u0019Yb!\t\u000e\u0005\ru!\u0002BB\u0010\u0003{\t1A\\3u\u0013\u0011\u0019\u0019c!\b\u0003\u0007U\u0013\u0016\n"
)
public final class MavenUtils {
   public static Tuple3 parseQueryParams(final URI uri) {
      return MavenUtils$.MODULE$.parseQueryParams(uri);
   }

   public static boolean resolveMavenCoordinates$default$6() {
      return MavenUtils$.MODULE$.resolveMavenCoordinates$default$6();
   }

   public static Seq resolveMavenCoordinates$default$5() {
      return MavenUtils$.MODULE$.resolveMavenCoordinates$default$5();
   }

   public static Option resolveMavenCoordinates$default$3() {
      return MavenUtils$.MODULE$.resolveMavenCoordinates$default$3();
   }

   public static Seq resolveMavenCoordinates(final String coordinates, final IvySettings ivySettings, final Option noCacheIvySettings, final boolean transitive, final Seq exclusions, final boolean isTest, final PrintStream printStream) {
      return MavenUtils$.MODULE$.resolveMavenCoordinates(coordinates, ivySettings, noCacheIvySettings, transitive, exclusions, isTest, printStream);
   }

   public static IvySettings loadIvySettings(final String settingsFile, final Option remoteRepos, final Option ivyPath, final PrintStream printStream) {
      return MavenUtils$.MODULE$.loadIvySettings(settingsFile, remoteRepos, ivyPath, printStream);
   }

   public static boolean buildIvySettings$default$3() {
      return MavenUtils$.MODULE$.buildIvySettings$default$3();
   }

   public static IvySettings buildIvySettings(final Option remoteRepos, final Option ivyPath, final boolean useLocalM2AsCache, final PrintStream printStream) {
      return MavenUtils$.MODULE$.buildIvySettings(remoteRepos, ivyPath, useLocalM2AsCache, printStream);
   }

   public static Seq extractMavenCoordinates(final String coordinates) {
      return MavenUtils$.MODULE$.extractMavenCoordinates(coordinates);
   }

   public static Seq IVY_DEFAULT_EXCLUDES() {
      return MavenUtils$.MODULE$.IVY_DEFAULT_EXCLUDES();
   }

   public static String JAR_IVY_SETTING_PATH_KEY() {
      return MavenUtils$.MODULE$.JAR_IVY_SETTING_PATH_KEY();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return MavenUtils$.MODULE$.LogStringContext(sc);
   }

   public static class MavenCoordinate implements Product, Serializable {
      private final String groupId;
      private final String artifactId;
      private final String version;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String groupId() {
         return this.groupId;
      }

      public String artifactId() {
         return this.artifactId;
      }

      public String version() {
         return this.version;
      }

      public String toString() {
         String var10000 = this.groupId();
         return var10000 + ":" + this.artifactId() + ":" + this.version();
      }

      public MavenCoordinate copy(final String groupId, final String artifactId, final String version) {
         return new MavenCoordinate(groupId, artifactId, version);
      }

      public String copy$default$1() {
         return this.groupId();
      }

      public String copy$default$2() {
         return this.artifactId();
      }

      public String copy$default$3() {
         return this.version();
      }

      public String productPrefix() {
         return "MavenCoordinate";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.groupId();
            }
            case 1 -> {
               return this.artifactId();
            }
            case 2 -> {
               return this.version();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof MavenCoordinate;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "groupId";
            }
            case 1 -> {
               return "artifactId";
            }
            case 2 -> {
               return "version";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10;
         if (this != x$1) {
            label63: {
               if (x$1 instanceof MavenCoordinate) {
                  label56: {
                     MavenCoordinate var4 = (MavenCoordinate)x$1;
                     String var10000 = this.groupId();
                     String var5 = var4.groupId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label56;
                     }

                     var10000 = this.artifactId();
                     String var6 = var4.artifactId();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label56;
                     }

                     var10000 = this.version();
                     String var7 = var4.version();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label56;
                     }

                     if (var4.canEqual(this)) {
                        break label63;
                     }
                  }
               }

               var10 = false;
               return var10;
            }
         }

         var10 = true;
         return var10;
      }

      public MavenCoordinate(final String groupId, final String artifactId, final String version) {
         this.groupId = groupId;
         this.artifactId = artifactId;
         this.version = version;
         Product.$init$(this);
      }
   }

   public static class MavenCoordinate$ extends AbstractFunction3 implements Serializable {
      public static final MavenCoordinate$ MODULE$ = new MavenCoordinate$();

      public final String toString() {
         return "MavenCoordinate";
      }

      public MavenCoordinate apply(final String groupId, final String artifactId, final String version) {
         return new MavenCoordinate(groupId, artifactId, version);
      }

      public Option unapply(final MavenCoordinate x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.groupId(), x$0.artifactId(), x$0.version())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(MavenCoordinate$.class);
      }
   }
}
