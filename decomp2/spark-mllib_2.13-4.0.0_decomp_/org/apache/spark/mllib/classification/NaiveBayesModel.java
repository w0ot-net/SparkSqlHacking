package org.apache.spark.mllib.classification;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkException;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.mllib.util.Saveable;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.json4s.JValue;
import scala.MatchError;
import scala.Option;
import scala.Tuple1;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\reg\u0001B6m\u0001]D!\"!\u000b\u0001\u0005\u000b\u0007I\u0011AA\u0016\u0011)\tY\u0005\u0001B\u0001B\u0003%\u0011Q\u0006\u0005\u000b\u0003\u001f\u0002!Q1A\u0005\u0002\u0005-\u0002BCA,\u0001\t\u0005\t\u0015!\u0003\u0002.!Q\u00111\f\u0001\u0003\u0006\u0004%\t!!\u0018\t\u0015\u0005\r\u0004A!A!\u0002\u0013\ty\u0006\u0003\u0006\u0002h\u0001\u0011)\u0019!C\u0001\u0003SB!\"!!\u0001\u0005\u0003\u0005\u000b\u0011BA6\u0011!\t)\t\u0001C\u0001a\u0006\u001d\u0005\"CAN\u0001\t\u0007I\u0011BAO\u0011!\tY\u000b\u0001Q\u0001\n\u0005}\u0005\"CAW\u0001\t\u0007I\u0011BAX\u0011!\t9\f\u0001Q\u0001\n\u0005E\u0006\u0002CAC\u0001\u0011\u0005a.!/\t\u0011\u0005\u0015\u0005\u0001\"\u0001o\u0003\u0003DA\"a7\u0001!\u0003\u0005\u0019\u0011)A\u0005\u0003;D\u0011\"a;\u0001\u0005\u0004%I!!<\t\u0011\u0005=\b\u0001)A\u0005\u0003GD\u0011\"!=\u0001\u0005\u0004%I!a=\t\u0011\u0005U\b\u0001)A\u0005\u0003SDq!a>\u0001\t\u0003\nI\u0010C\u0004\u0002x\u0002!\tE!\u0006\t\u000f\tm\u0001\u0001\"\u0001\u0003\u001e!9!1\u0004\u0001\u0005\u0002\t\u001d\u0002b\u0002B\u0017\u0001\u0011%!q\u0006\u0005\b\u0005g\u0001A\u0011\u0002B\u001b\u0011\u001d\u0011I\u0004\u0001C\u0005\u0005wAqA!\u0011\u0001\t\u0003\u0012\u0019eB\u0004\u0003d1D\tA!\u001a\u0007\r-d\u0007\u0012\u0001B4\u0011\u001d\t)I\bC\u0001\u0005s:\u0001Ba\u001f\u001f\u0011\u0003q'Q\u0010\u0004\t\u0005\u0003s\u0002\u0012\u00018\u0003\u0004\"9\u0011QQ\u0011\u0005\u0002\t\u0015\u0005b\u0002BDC\u0011\u0005\u0011\u0011\u000e\u0005\b\u0005\u0013\u000bC\u0011AA5\r\u0019\u0011Y)\t!\u0003\u000e\"Q\u0011\u0011F\u0013\u0003\u0016\u0004%\t!a\u000b\t\u0015\u0005-SE!E!\u0002\u0013\ti\u0003\u0003\u0006\u0002P\u0015\u0012)\u001a!C\u0001\u0003WA!\"a\u0016&\u0005#\u0005\u000b\u0011BA\u0017\u0011)\tY&\nBK\u0002\u0013\u0005\u0011Q\f\u0005\u000b\u0003G*#\u0011#Q\u0001\n\u0005}\u0003BCA4K\tU\r\u0011\"\u0001\u0002j!Q\u0011\u0011Q\u0013\u0003\u0012\u0003\u0006I!a\u001b\t\u000f\u0005\u0015U\u0005\"\u0001\u0003\u0016\"I!1U\u0013\u0002\u0002\u0013\u0005!Q\u0015\u0005\n\u0005_+\u0013\u0013!C\u0001\u0005cC\u0011B!2&#\u0003%\tA!-\t\u0013\t\u001dW%%A\u0005\u0002\t%\u0007\"\u0003BgKE\u0005I\u0011\u0001Bh\u0011%\u0011\u0019.JA\u0001\n\u0003\u0012)\u000eC\u0005\u0003\\\u0016\n\t\u0011\"\u0001\u0003^\"I!Q]\u0013\u0002\u0002\u0013\u0005!q\u001d\u0005\n\u0005g,\u0013\u0011!C!\u0005kD\u0011ba\u0001&\u0003\u0003%\ta!\u0002\t\u0013\r=Q%!A\u0005B\rE\u0001\"CB\u000bK\u0005\u0005I\u0011IB\f\u0011%\u0019I\"JA\u0001\n\u0003\u001aY\u0002C\u0005\u0004\u001e\u0015\n\t\u0011\"\u0011\u0004 \u001dI11E\u0011\u0002\u0002#\u00051Q\u0005\u0004\n\u0005\u0017\u000b\u0013\u0011!E\u0001\u0007OAq!!\"?\t\u0003\u0019)\u0004C\u0005\u0004\u001ay\n\t\u0011\"\u0012\u0004\u001c!I1q\u0007 \u0002\u0002\u0013\u00055\u0011\b\u0005\n\u0007\u0007r\u0014\u0011!CA\u0007\u000bB\u0011ba\u0015?\u0003\u0003%Ia!\u0016\t\u000f\t\u0005\u0013\u0005\"\u0001\u0004^!91qM\u0011\u0005\u0002\r%t\u0001CB9=!\u0005ana\u001d\u0007\u0011\rUd\u0004#\u0001o\u0007oBq!!\"H\t\u0003\u0019I\bC\u0004\u0003\b\u001e#\t!!\u001b\t\u000f\t%u\t\"\u0001\u0002j\u00191!1R$A\u0007wB!\"!\u000bL\u0005+\u0007I\u0011AA\u0016\u0011)\tYe\u0013B\tB\u0003%\u0011Q\u0006\u0005\u000b\u0003\u001fZ%Q3A\u0005\u0002\u0005-\u0002BCA,\u0017\nE\t\u0015!\u0003\u0002.!Q\u00111L&\u0003\u0016\u0004%\t!!\u0018\t\u0015\u0005\r4J!E!\u0002\u0013\ty\u0006C\u0004\u0002\u0006.#\ta! \t\u0013\t\r6*!A\u0005\u0002\r%\u0005\"\u0003BX\u0017F\u0005I\u0011\u0001BY\u0011%\u0011)mSI\u0001\n\u0003\u0011\t\fC\u0005\u0003H.\u000b\n\u0011\"\u0001\u0003J\"I!1[&\u0002\u0002\u0013\u0005#Q\u001b\u0005\n\u00057\\\u0015\u0011!C\u0001\u0005;D\u0011B!:L\u0003\u0003%\ta!%\t\u0013\tM8*!A\u0005B\tU\b\"CB\u0002\u0017\u0006\u0005I\u0011ABK\u0011%\u0019yaSA\u0001\n\u0003\u001aI\nC\u0005\u0004\u0016-\u000b\t\u0011\"\u0011\u0004\u0018!I1\u0011D&\u0002\u0002\u0013\u000531\u0004\u0005\n\u0007;Y\u0015\u0011!C!\u0007;;\u0011ba\tH\u0003\u0003E\ta!)\u0007\u0013\t-u)!A\t\u0002\r\r\u0006bBACC\u0012\u000511\u0016\u0005\n\u00073\t\u0017\u0011!C#\u00077A\u0011ba\u000eb\u0003\u0003%\ti!,\t\u0013\r\r\u0013-!A\u0005\u0002\u000eU\u0006\"CB*C\u0006\u0005I\u0011BB+\u0011\u001d\u0011\te\u0012C\u0001\u0007\u0003Dqaa\u001aH\t\u0003\u0019I\rC\u0004\u0004hy!\tea4\t\u0013\rMc$!A\u0005\n\rU#a\u0004(bSZ,')Y=fg6{G-\u001a7\u000b\u00055t\u0017AD2mCN\u001c\u0018NZ5dCRLwN\u001c\u0006\u0003_B\fQ!\u001c7mS\nT!!\u001d:\u0002\u000bM\u0004\u0018M]6\u000b\u0005M$\u0018AB1qC\u000eDWMC\u0001v\u0003\ry'oZ\u0002\u0001'\u001d\u0001\u0001P`A\u0003\u0003;\u0001\"!\u001f?\u000e\u0003iT\u0011a_\u0001\u0006g\u000e\fG.Y\u0005\u0003{j\u0014a!\u00118z%\u00164\u0007cA@\u0002\u00025\tA.C\u0002\u0002\u00041\u00141c\u00117bgNLg-[2bi&|g.T8eK2\u0004B!a\u0002\u0002\u00189!\u0011\u0011BA\n\u001d\u0011\tY!!\u0005\u000e\u0005\u00055!bAA\bm\u00061AH]8pizJ\u0011a_\u0005\u0004\u0003+Q\u0018a\u00029bG.\fw-Z\u0005\u0005\u00033\tYB\u0001\u0007TKJL\u0017\r\\5{C\ndWMC\u0002\u0002\u0016i\u0004B!a\b\u0002&5\u0011\u0011\u0011\u0005\u0006\u0004\u0003Gq\u0017\u0001B;uS2LA!a\n\u0002\"\tA1+\u0019<fC\ndW-\u0001\u0004mC\n,Gn]\u000b\u0003\u0003[\u0001R!_A\u0018\u0003gI1!!\r{\u0005\u0015\t%O]1z!\rI\u0018QG\u0005\u0004\u0003oQ(A\u0002#pk\ndW\rK\u0003\u0002\u0003w\t9\u0005\u0005\u0003\u0002>\u0005\rSBAA \u0015\r\t\t\u0005]\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA#\u0003\u007f\u0011QaU5oG\u0016\f#!!\u0013\u0002\u000bEr\u0003G\f\u0019\u0002\u000f1\f'-\u001a7tA!*!!a\u000f\u0002H\u0005\u0011\u0001/\u001b\u0015\u0006\u0007\u0005m\u00121K\u0011\u0003\u0003+\nQ\u0001\r\u0018:]A\n1\u0001]5!Q\u0015!\u00111HA*\u0003\u0015!\b.\u001a;b+\t\ty\u0006E\u0003z\u0003_\ti\u0003K\u0003\u0006\u0003w\t\u0019&\u0001\u0004uQ\u0016$\u0018\r\t\u0015\u0006\r\u0005m\u00121K\u0001\n[>$W\r\u001c+za\u0016,\"!a\u001b\u0011\t\u00055\u0014Q\u000f\b\u0005\u0003_\n\t\bE\u0002\u0002\fiL1!a\u001d{\u0003\u0019\u0001&/\u001a3fM&!\u0011qOA=\u0005\u0019\u0019FO]5oO*\u0019\u00111\u000f>)\u000b\u001d\tY$! \"\u0005\u0005}\u0014!B\u0019/i9\u0002\u0014AC7pI\u0016dG+\u001f9fA!*\u0001\"a\u000f\u0002~\u00051A(\u001b8jiz\"\"\"!#\u0002\f\u0006=\u00151SAL!\ty\b\u0001C\u0004\u0002*%\u0001\r!!\f)\r\u0005-\u00151HA$\u0011\u001d\ty%\u0003a\u0001\u0003[Ac!a$\u0002<\u0005M\u0003bBA.\u0013\u0001\u0007\u0011q\f\u0015\u0007\u0003'\u000bY$a\u0015\t\u000f\u0005\u001d\u0014\u00021\u0001\u0002l!2\u0011qSA\u001e\u0003{\n\u0001\u0002]5WK\u000e$xN]\u000b\u0003\u0003?\u0003B!!)\u0002(6\u0011\u00111\u0015\u0006\u0004\u0003Ks\u0017A\u00027j]\u0006dw-\u0003\u0003\u0002*\u0006\r&a\u0003#f]N,g+Z2u_J\f\u0011\u0002]5WK\u000e$xN\u001d\u0011\u0002\u0017QDW\r^1NCR\u0014\u0018\u000e_\u000b\u0003\u0003c\u0003B!!)\u00024&!\u0011QWAR\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0002\u0019QDW\r^1NCR\u0014\u0018\u000e\u001f\u0011\u0015\u0011\u0005%\u00151XA_\u0003\u007fCq!!\u000b\u000f\u0001\u0004\ti\u0003C\u0004\u0002P9\u0001\r!!\f\t\u000f\u0005mc\u00021\u0001\u0002`QA\u0011\u0011RAb\u0003+\f9\u000eC\u0004\u0002*=\u0001\r!!2\u0011\r\u0005\u001d\u0017\u0011[A\u001a\u001b\t\tIM\u0003\u0003\u0002L\u00065\u0017\u0001\u00027b]\u001eT!!a4\u0002\t)\fg/Y\u0005\u0005\u0003'\fIM\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0011\u001d\tye\u0004a\u0001\u0003\u000bDq!a\u0017\u0010\u0001\u0004\tI\u000e\u0005\u0004\u0002H\u0006E\u0017QY\u0001\u0004q\u0012\u0012\u0004cB=\u0002`\u0006\r\u0018\u0011^\u0005\u0004\u0003CT(A\u0002+va2,'\u0007E\u0003z\u0003K\f\t,C\u0002\u0002hj\u0014aa\u00149uS>t\u0007#B=\u0002f\u0006}\u0015A\u0005;iKR\fW*\u001b8vg:+w\r\u00165fi\u0006,\"!a9\u0002'QDW\r^1NS:,8OT3h)\",G/\u0019\u0011\u0002\u00179,w\r\u00165fi\u0006\u001cV/\\\u000b\u0003\u0003S\fAB\\3h)\",G/Y*v[\u0002\nq\u0001\u001d:fI&\u001cG\u000f\u0006\u0003\u0002|\n\u001d\u0001CBA\u007f\u0005\u0007\t\u0019$\u0004\u0002\u0002\u0000*\u0019!\u0011\u00019\u0002\u0007I$G-\u0003\u0003\u0003\u0006\u0005}(a\u0001*E\t\"9!\u0011B\u000bA\u0002\t-\u0011\u0001\u0003;fgR$\u0015\r^1\u0011\r\u0005u(1\u0001B\u0007!\u0011\t\tKa\u0004\n\t\tE\u00111\u0015\u0002\u0007-\u0016\u001cGo\u001c:)\u000bU\tY$a\u0012\u0015\t\u0005M\"q\u0003\u0005\b\u0005\u00131\u0002\u0019\u0001B\u0007Q\u00151\u00121HA$\u0003Q\u0001(/\u001a3jGR\u0004&o\u001c2bE&d\u0017\u000e^5fgR!!1\u0002B\u0010\u0011\u001d\u0011Ia\u0006a\u0001\u0005\u0017ASaFA\u001e\u0005G\t#A!\n\u0002\u000bErSG\f\u0019\u0015\t\t5!\u0011\u0006\u0005\b\u0005\u0013A\u0002\u0019\u0001B\u0007Q\u0015A\u00121\bB\u0012\u0003YiW\u000f\u001c;j]>l\u0017.\u00197DC2\u001cW\u000f\\1uS>tG\u0003BAP\u0005cAqA!\u0003\u001a\u0001\u0004\u0011i!\u0001\u000bcKJtw.\u001e7mS\u000e\u000bGnY;mCRLwN\u001c\u000b\u0005\u0003?\u00139\u0004C\u0004\u0003\ni\u0001\rA!\u0004\u0002-A|7\u000f^3sS>\u0014\bK]8cC\nLG.\u001b;jKN$B!a(\u0003>!9!qH\u000eA\u0002\u0005}\u0015a\u00027pOB\u0013xNY\u0001\u0005g\u00064X\r\u0006\u0004\u0003F\t-#q\u000b\t\u0004s\n\u001d\u0013b\u0001B%u\n!QK\\5u\u0011\u001d\u0011i\u0005\ba\u0001\u0005\u001f\n!a]2\u0011\t\tE#1K\u0007\u0002a&\u0019!Q\u000b9\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\t\u000f\teC\u00041\u0001\u0002l\u0005!\u0001/\u0019;iQ\u0015a\u00121\bB/C\t\u0011y&A\u00032]Mr\u0003\u0007K\u0003\u0001\u0003w\t\u0019&A\bOC&4XMQ1zKNlu\u000eZ3m!\tyhd\u0005\u0004\u001fq\n%$q\u000e\t\u0007\u0003?\u0011Y'!#\n\t\t5\u0014\u0011\u0005\u0002\u0007\u0019>\fG-\u001a:\u0011\t\tE$qO\u0007\u0003\u0005gRAA!\u001e\u0002N\u0006\u0011\u0011n\\\u0005\u0005\u00033\u0011\u0019\b\u0006\u0002\u0003f\u0005a1+\u0019<f\u0019>\fGM\u0016\u001a`aA\u0019!qP\u0011\u000e\u0003y\u0011AbU1wK2{\u0017\r\u001a,3?B\u001a\"!\t=\u0015\u0005\tu\u0014!\u0005;iSN4uN]7biZ+'o]5p]\u0006iA\u000f[5t\u00072\f7o\u001d(b[\u0016\u0014A\u0001R1uCN1Q\u0005\u001fBH\u0003\u000b\u00012!\u001fBI\u0013\r\u0011\u0019J\u001f\u0002\b!J|G-^2u))\u00119Ja'\u0003\u001e\n}%\u0011\u0015\t\u0004\u00053+S\"A\u0011\t\u000f\u0005%b\u00061\u0001\u0002.!9\u0011q\n\u0018A\u0002\u00055\u0002bBA.]\u0001\u0007\u0011q\f\u0005\b\u0003Or\u0003\u0019AA6\u0003\u0011\u0019w\u000e]=\u0015\u0015\t]%q\u0015BU\u0005W\u0013i\u000bC\u0005\u0002*=\u0002\n\u00111\u0001\u0002.!I\u0011qJ\u0018\u0011\u0002\u0003\u0007\u0011Q\u0006\u0005\n\u00037z\u0003\u0013!a\u0001\u0003?B\u0011\"a\u001a0!\u0003\u0005\r!a\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!1\u0017\u0016\u0005\u0003[\u0011)l\u000b\u0002\u00038B!!\u0011\u0018Ba\u001b\t\u0011YL\u0003\u0003\u0003>\n}\u0016!C;oG\",7m[3e\u0015\r\t\tE_\u0005\u0005\u0005\u0007\u0014YLA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\t-'\u0006BA0\u0005k\u000babY8qs\u0012\"WMZ1vYR$C'\u0006\u0002\u0003R*\"\u00111\u000eB[\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!q\u001b\t\u0005\u0003\u000f\u0014I.\u0003\u0003\u0002x\u0005%\u0017\u0001\u00049s_\u0012,8\r^!sSRLXC\u0001Bp!\rI(\u0011]\u0005\u0004\u0005GT(aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003\u0002Bu\u0005_\u00042!\u001fBv\u0013\r\u0011iO\u001f\u0002\u0004\u0003:L\b\"\u0003Bym\u0005\u0005\t\u0019\u0001Bp\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011!q\u001f\t\u0007\u0005s\u0014yP!;\u000e\u0005\tm(b\u0001B\u007fu\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\r\u0005!1 \u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0004\b\r5\u0001cA=\u0004\n%\u001911\u0002>\u0003\u000f\t{w\u000e\\3b]\"I!\u0011\u001f\u001d\u0002\u0002\u0003\u0007!\u0011^\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0003X\u000eM\u0001\"\u0003Bys\u0005\u0005\t\u0019\u0001Bp\u0003!A\u0017m\u001d5D_\u0012,GC\u0001Bp\u0003!!xn\u0015;sS:<GC\u0001Bl\u0003\u0019)\u0017/^1mgR!1qAB\u0011\u0011%\u0011\t\u0010PA\u0001\u0002\u0004\u0011I/\u0001\u0003ECR\f\u0007c\u0001BM}M)ah!\u000b\u0003pAq11FB\u0019\u0003[\ti#a\u0018\u0002l\t]UBAB\u0017\u0015\r\u0019yC_\u0001\beVtG/[7f\u0013\u0011\u0019\u0019d!\f\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tG\u0007\u0006\u0002\u0004&\u0005)\u0011\r\u001d9msRQ!qSB\u001e\u0007{\u0019yd!\u0011\t\u000f\u0005%\u0012\t1\u0001\u0002.!9\u0011qJ!A\u0002\u00055\u0002bBA.\u0003\u0002\u0007\u0011q\f\u0005\b\u0003O\n\u0005\u0019AA6\u0003\u001d)h.\u00199qYf$Baa\u0012\u0004PA)\u00110!:\u0004JAY\u0011pa\u0013\u0002.\u00055\u0012qLA6\u0013\r\u0019iE\u001f\u0002\u0007)V\u0004H.\u001a\u001b\t\u0013\rE#)!AA\u0002\t]\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u00111q\u000b\t\u0005\u0003\u000f\u001cI&\u0003\u0003\u0004\\\u0005%'AB(cU\u0016\u001cG\u000f\u0006\u0005\u0003F\r}3\u0011MB2\u0011\u001d\u0011i\u0005\u0012a\u0001\u0005\u001fBqA!\u0017E\u0001\u0004\tY\u0007C\u0004\u0004f\u0011\u0003\rAa&\u0002\t\u0011\fG/Y\u0001\u0005Y>\fG\r\u0006\u0004\u0002\n\u000e-4Q\u000e\u0005\b\u0005\u001b*\u0005\u0019\u0001B(\u0011\u001d\u0011I&\u0012a\u0001\u0003WBS!RA\u001e\u0005;\nAbU1wK2{\u0017\r\u001a,2?B\u00022Aa H\u00051\u0019\u0016M^3M_\u0006$g+M01'\t9\u0005\u0010\u0006\u0002\u0004tM11\n\u001fBH\u0003\u000b!\u0002ba \u0004\u0004\u000e\u00155q\u0011\t\u0004\u0007\u0003[U\"A$\t\u000f\u0005%\"\u000b1\u0001\u0002.!9\u0011q\n*A\u0002\u00055\u0002bBA.%\u0002\u0007\u0011q\f\u000b\t\u0007\u007f\u001aYi!$\u0004\u0010\"I\u0011\u0011F*\u0011\u0002\u0003\u0007\u0011Q\u0006\u0005\n\u0003\u001f\u001a\u0006\u0013!a\u0001\u0003[A\u0011\"a\u0017T!\u0003\u0005\r!a\u0018\u0015\t\t%81\u0013\u0005\n\u0005cL\u0016\u0011!a\u0001\u0005?$Baa\u0002\u0004\u0018\"I!\u0011_.\u0002\u0002\u0003\u0007!\u0011\u001e\u000b\u0005\u0005/\u001cY\nC\u0005\u0003rr\u000b\t\u00111\u0001\u0003`R!1qABP\u0011%\u0011\tpXA\u0001\u0002\u0004\u0011I\u000fE\u0002\u0004\u0002\u0006\u001cR!YBS\u0005_\u0002Bba\u000b\u0004(\u00065\u0012QFA0\u0007\u007fJAa!+\u0004.\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0015\u0005\r\u0005F\u0003CB@\u0007_\u001b\tla-\t\u000f\u0005%B\r1\u0001\u0002.!9\u0011q\n3A\u0002\u00055\u0002bBA.I\u0002\u0007\u0011q\f\u000b\u0005\u0007o\u001by\fE\u0003z\u0003K\u001cI\fE\u0005z\u0007w\u000bi#!\f\u0002`%\u00191Q\u0018>\u0003\rQ+\b\u000f\\34\u0011%\u0019\t&ZA\u0001\u0002\u0004\u0019y\b\u0006\u0005\u0003F\r\r7QYBd\u0011\u001d\u0011ie\u001aa\u0001\u0005\u001fBqA!\u0017h\u0001\u0004\tY\u0007C\u0004\u0004f\u001d\u0004\raa \u0015\r\u0005%51ZBg\u0011\u001d\u0011i\u0005\u001ba\u0001\u0005\u001fBqA!\u0017i\u0001\u0004\tY\u0007\u0006\u0004\u0002\n\u000eE71\u001b\u0005\b\u0005\u001bJ\u0007\u0019\u0001B(\u0011\u001d\u0011I&\u001ba\u0001\u0003WBSAHA\u001e\u0005;BS!HA\u001e\u0005;\u0002"
)
public class NaiveBayesModel implements ClassificationModel, Saveable {
   private final double[] labels;
   private final double[] pi;
   private final double[][] theta;
   private final String modelType;
   private final DenseVector piVector;
   private final DenseMatrix thetaMatrix;
   // $FF: synthetic field
   private final Tuple2 x$2;
   private final Option thetaMinusNegTheta;
   private final Option negThetaSum;

   public static NaiveBayesModel load(final SparkContext sc, final String path) {
      return NaiveBayesModel$.MODULE$.load(sc, path);
   }

   public JavaRDD predict(final JavaRDD testData) {
      return ClassificationModel.predict$(this, testData);
   }

   public double[] labels() {
      return this.labels;
   }

   public double[] pi() {
      return this.pi;
   }

   public double[][] theta() {
      return this.theta;
   }

   public String modelType() {
      return this.modelType;
   }

   private DenseVector piVector() {
      return this.piVector;
   }

   private DenseMatrix thetaMatrix() {
      return this.thetaMatrix;
   }

   private Option thetaMinusNegTheta() {
      return this.thetaMinusNegTheta;
   }

   private Option negThetaSum() {
      return this.negThetaSum;
   }

   public RDD predict(final RDD testData) {
      Broadcast bcModel = testData.context().broadcast(this, .MODULE$.apply(NaiveBayesModel.class));
      return testData.mapPartitions((iter) -> {
         NaiveBayesModel model = (NaiveBayesModel)bcModel.value();
         return iter.map((testData) -> BoxesRunTime.boxToDouble($anonfun$predict$2(model, testData)));
      }, testData.mapPartitions$default$2(), .MODULE$.Double());
   }

   public double predict(final Vector testData) {
      String var4 = this.modelType();
      String var10000 = NaiveBayes$.MODULE$.Multinomial();
      if (var10000 == null) {
         if (var4 == null) {
            return this.labels()[this.multinomialCalculation(testData).argmax()];
         }
      } else if (var10000.equals(var4)) {
         return this.labels()[this.multinomialCalculation(testData).argmax()];
      }

      var10000 = NaiveBayes$.MODULE$.Bernoulli();
      if (var10000 == null) {
         if (var4 == null) {
            return this.labels()[this.bernoulliCalculation(testData).argmax()];
         }
      } else if (var10000.equals(var4)) {
         return this.labels()[this.bernoulliCalculation(testData).argmax()];
      }

      throw new MatchError(var4);
   }

   public RDD predictProbabilities(final RDD testData) {
      Broadcast bcModel = testData.context().broadcast(this, .MODULE$.apply(NaiveBayesModel.class));
      return testData.mapPartitions((iter) -> {
         NaiveBayesModel model = (NaiveBayesModel)bcModel.value();
         return iter.map((testData) -> model.predictProbabilities(testData));
      }, testData.mapPartitions$default$2(), .MODULE$.apply(Vector.class));
   }

   public Vector predictProbabilities(final Vector testData) {
      String var3 = this.modelType();
      String var10000 = NaiveBayes$.MODULE$.Multinomial();
      if (var10000 == null) {
         if (var3 == null) {
            return this.posteriorProbabilities(this.multinomialCalculation(testData));
         }
      } else if (var10000.equals(var3)) {
         return this.posteriorProbabilities(this.multinomialCalculation(testData));
      }

      var10000 = NaiveBayes$.MODULE$.Bernoulli();
      if (var10000 == null) {
         if (var3 == null) {
            return this.posteriorProbabilities(this.bernoulliCalculation(testData));
         }
      } else if (var10000.equals(var3)) {
         return this.posteriorProbabilities(this.bernoulliCalculation(testData));
      }

      throw new MatchError(var3);
   }

   private DenseVector multinomialCalculation(final Vector testData) {
      DenseVector prob = this.thetaMatrix().multiply(testData);
      BLAS$.MODULE$.axpy((double)1.0F, (Vector)this.piVector(), (Vector)prob);
      return prob;
   }

   private DenseVector bernoulliCalculation(final Vector testData) {
      testData.foreachNonZero((JFunction2.mcVID.sp)(x$3, value) -> {
         if (value != (double)1.0F) {
            throw new SparkException("Bernoulli naive Bayes requires 0 or 1 feature values but found " + testData + ".");
         }
      });
      DenseVector prob = ((Matrix)this.thetaMinusNegTheta().get()).multiply(testData);
      BLAS$.MODULE$.axpy((double)1.0F, (Vector)this.piVector(), (Vector)prob);
      BLAS$.MODULE$.axpy((double)1.0F, (Vector)((Vector)this.negThetaSum().get()), (Vector)prob);
      return prob;
   }

   private DenseVector posteriorProbabilities(final DenseVector logProb) {
      double[] logProbArray = logProb.toArray();
      double maxLog = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(logProbArray).max(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
      double[] scaledProbs = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(logProbArray), (JFunction1.mcDD.sp)(lp) -> scala.math.package..MODULE$.exp(lp - maxLog), .MODULE$.Double());
      double probSum = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(scaledProbs).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      return new DenseVector((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(scaledProbs), (JFunction1.mcDD.sp)(x$4) -> x$4 / probSum, .MODULE$.Double()));
   }

   public void save(final SparkContext sc, final String path) {
      NaiveBayesModel$SaveLoadV2_0$Data data = new NaiveBayesModel$SaveLoadV2_0$Data(this.labels(), this.pi(), this.theta(), this.modelType());
      NaiveBayesModel.SaveLoadV2_0$.MODULE$.save(sc, path, data);
   }

   // $FF: synthetic method
   public static final double $anonfun$predict$2(final NaiveBayesModel model$1, final Vector testData) {
      return model$1.predict(testData);
   }

   public NaiveBayesModel(final double[] labels, final double[] pi, final double[][] theta, final String modelType) {
      Tuple2 var17;
      label34: {
         label38: {
            this.labels = labels;
            this.pi = pi;
            this.theta = theta;
            this.modelType = modelType;
            super();
            ClassificationModel.$init$(this);
            this.piVector = new DenseVector(pi);
            this.thetaMatrix = new DenseMatrix(labels.length, theta[0].length, (double[])scala.collection.ArrayOps..MODULE$.flatten$extension(scala.Predef..MODULE$.refArrayOps((Object[])theta), (xs) -> scala.Predef..MODULE$.wrapDoubleArray(xs), .MODULE$.Double()), true);
            scala.Predef..MODULE$.require(NaiveBayes$.MODULE$.supportedModelTypes().contains(modelType), () -> {
               String var10000 = this.modelType();
               return "Invalid modelType " + var10000 + ". Supported modelTypes are " + NaiveBayes$.MODULE$.supportedModelTypes() + ".";
            });
            String var10001 = NaiveBayes$.MODULE$.Multinomial();
            if (var10001 == null) {
               if (modelType == null) {
                  break label38;
               }
            } else if (var10001.equals(modelType)) {
               break label38;
            }

            var10001 = NaiveBayes$.MODULE$.Bernoulli();
            if (var10001 == null) {
               if (modelType != null) {
                  throw new IllegalArgumentException("Invalid modelType: " + modelType + ".");
               }
            } else if (!var10001.equals(modelType)) {
               throw new IllegalArgumentException("Invalid modelType: " + modelType + ".");
            }

            DenseMatrix negTheta = this.thetaMatrix().map((JFunction1.mcDD.sp)(value) -> scala.math.package..MODULE$.log1p(-scala.math.package..MODULE$.exp(value)));
            DenseVector ones = new DenseVector((double[])scala.Array..MODULE$.fill(this.thetaMatrix().numCols(), (JFunction0.mcD.sp)() -> (double)1.0F, .MODULE$.Double()));
            DenseMatrix thetaMinusNegTheta = this.thetaMatrix().map((JFunction1.mcDD.sp)(value) -> value - scala.math.package..MODULE$.log1p(-scala.math.package..MODULE$.exp(value)));
            var17 = new Tuple2(scala.Option..MODULE$.apply(thetaMinusNegTheta), scala.Option..MODULE$.apply(negTheta.multiply(ones)));
            break label34;
         }

         var17 = new Tuple2(scala.None..MODULE$, scala.None..MODULE$);
      }

      Tuple2 var7 = var17;
      if (var7 != null) {
         Option thetaMinusNegTheta = (Option)var7._1();
         Option negThetaSum = (Option)var7._2();
         this.x$2 = new Tuple2(thetaMinusNegTheta, negThetaSum);
         this.thetaMinusNegTheta = (Option)this.x$2._1();
         this.negThetaSum = (Option)this.x$2._2();
      } else {
         throw new MatchError(var7);
      }
   }

   public NaiveBayesModel(final double[] labels, final double[] pi, final double[][] theta) {
      this(labels, pi, theta, NaiveBayes$.MODULE$.Multinomial());
   }

   public NaiveBayesModel(final Iterable labels, final Iterable pi, final Iterable theta) {
      this((double[])scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(labels).asScala().toArray(.MODULE$.Double()), (double[])scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(pi).asScala().toArray(.MODULE$.Double()), (double[][])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(theta).asScala().toArray(.MODULE$.apply(Iterable.class))), new Serializable() {
         private static final long serialVersionUID = 0L;

         public final double[] apply(final Iterable x$1) {
            return (double[])scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(x$1).asScala().toArray(.MODULE$.Double());
         }
      }, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE))));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class SaveLoadV2_0$ {
      public static final SaveLoadV2_0$ MODULE$ = new SaveLoadV2_0$();

      public String thisFormatVersion() {
         return "2.0";
      }

      public String thisClassName() {
         return "org.apache.spark.mllib.classification.NaiveBayesModel";
      }

      public void save(final SparkContext sc, final String path, final NaiveBayesModel$SaveLoadV2_0$Data data) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numFeatures"), BoxesRunTime.boxToInteger(data.theta()[0].length)), (x) -> $anonfun$save$4(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numClasses"), BoxesRunTime.boxToInteger(data.pi().length)), (x) -> $anonfun$save$5(BoxesRunTime.unboxToInt(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(Loader$.MODULE$.metadataPath(path));
         var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.classification.NaiveBayesModel.SaveLoadV2_0.Data").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public NaiveBayesModel load(final SparkContext sc, final String path) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         Dataset dataRDD = spark.read().parquet(Loader$.MODULE$.dataPath(path));
         Loader$ var10000 = Loader$.MODULE$;
         StructType var10001 = dataRDD.schema();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.classification.NaiveBayesModel.SaveLoadV2_0.Data").asType().toTypeConstructor();
            }

            public $typecreator1$2() {
            }
         }

         var10000.checkSchema(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
         Row[] dataArray = (Row[])dataRDD.select("labels", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"pi", "theta", "modelType"}))).take(1);
         scala.Predef..MODULE$.assert(dataArray.length == 1, () -> "Unable to load NaiveBayesModel data from: " + Loader$.MODULE$.dataPath(path));
         Row data = dataArray[0];
         double[] labels = (double[])((IterableOnceOps)data.getAs(0)).toArray(.MODULE$.Double());
         double[] pi = (double[])((IterableOnceOps)data.getAs(1)).toArray(.MODULE$.Double());
         double[][] theta = (double[][])((IterableOnceOps)data.getSeq(2).map((x$5) -> (double[])x$5.toArray(.MODULE$.Double()))).toArray(.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
         String modelType = data.getString(3);
         return new NaiveBayesModel(labels, pi, theta, modelType);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$4(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$5(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();

      public String thisFormatVersion() {
         return "1.0";
      }

      public String thisClassName() {
         return "org.apache.spark.mllib.classification.NaiveBayesModel";
      }

      public void save(final SparkContext sc, final String path, final NaiveBayesModel$SaveLoadV1_0$Data data) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numFeatures"), BoxesRunTime.boxToInteger(data.theta()[0].length)), (x) -> $anonfun$save$9(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numClasses"), BoxesRunTime.boxToInteger(data.pi().length)), (x) -> $anonfun$save$10(BoxesRunTime.unboxToInt(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$3() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3())).write().text(Loader$.MODULE$.metadataPath(path));
         var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.classification.NaiveBayesModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator2$2() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$2())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public NaiveBayesModel load(final SparkContext sc, final String path) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         Dataset dataRDD = spark.read().parquet(Loader$.MODULE$.dataPath(path));
         Loader$ var10000 = Loader$.MODULE$;
         StructType var10001 = dataRDD.schema();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$4 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.classification.NaiveBayesModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator1$4() {
            }
         }

         var10000.checkSchema(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$4()));
         Row[] dataArray = (Row[])dataRDD.select("labels", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"pi", "theta"}))).take(1);
         scala.Predef..MODULE$.assert(dataArray.length == 1, () -> "Unable to load NaiveBayesModel data from: " + Loader$.MODULE$.dataPath(path));
         Row data = dataArray[0];
         double[] labels = (double[])((IterableOnceOps)data.getAs(0)).toArray(.MODULE$.Double());
         double[] pi = (double[])((IterableOnceOps)data.getAs(1)).toArray(.MODULE$.Double());
         double[][] theta = (double[][])((IterableOnceOps)data.getSeq(2).map((x$6) -> (double[])x$6.toArray(.MODULE$.Double()))).toArray(.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
         return new NaiveBayesModel(labels, pi, theta);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$9(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$10(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
