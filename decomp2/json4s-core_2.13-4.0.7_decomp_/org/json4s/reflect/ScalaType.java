package org.json4s.reflect;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import org.json4s.JArray;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.Equals;
import scala.Option;
import scala.Symbol;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.util.Either;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0005r!\u00024h\u0011\u0003qg!\u00029h\u0011\u0003\t\b\"\u0002=\u0002\t\u0003I\bb\u0002>\u0002\u0005\u0004%Ia\u001f\u0005\b\u0003'\t\u0001\u0015!\u0003}\u0011%\u0011i&\u0001b\u0001\n\u0013\u0011y\u0006\u0003\u0005\u0003f\u0005\u0001\u000b\u0011\u0002B1\u0011\u001d\u00119'\u0001C\u0001\u0005SBqAa\u001a\u0002\t\u0003\u0011I\bC\u0005\u0003\n\u0006\t\n\u0011\"\u0001\u0003P!9!qM\u0001\u0005\u0002\t-\u0005\"\u0003BI\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011)*\u0001Q\u0001\n\u0005\r\u0002\"\u0003BL\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011I*\u0001Q\u0001\n\u0005\r\u0002\"\u0003BN\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011i*\u0001Q\u0001\n\u0005\r\u0002\"\u0003BP\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011\t+\u0001Q\u0001\n\u0005\r\u0002\"\u0003BR\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011)+\u0001Q\u0001\n\u0005\r\u0002\"\u0003BT\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011I+\u0001Q\u0001\n\u0005\r\u0002\"\u0003BV\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011i+\u0001Q\u0001\n\u0005\r\u0002\"\u0003BX\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011\t,\u0001Q\u0001\n\u0005\r\u0002\"\u0003BZ\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011),\u0001Q\u0001\n\u0005\r\u0002\"\u0003B\\\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011I,\u0001Q\u0001\n\u0005\r\u0002\"\u0003B^\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011i,\u0001Q\u0001\n\u0005\r\u0002\"\u0003B`\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011\t-\u0001Q\u0001\n\u0005\r\u0002\"\u0003Bb\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011)-\u0001Q\u0001\n\u0005\r\u0002\"\u0003Bd\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011I-\u0001Q\u0001\n\u0005\r\u0002\"\u0003Bf\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011i-\u0001Q\u0001\n\u0005\r\u0002\"\u0003Bh\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011\t.\u0001Q\u0001\n\u0005\r\u0002\"\u0003Bj\u0003\t\u0007I\u0011\u0002BJ\u0011!\u0011).\u0001Q\u0001\n\u0005\r\u0002B\u0003Bl\u0003\t\u0007I\u0011A5\u0003\u0014\"A!\u0011\\\u0001!\u0002\u0013\t\u0019\u0003\u0003\u0006\u0003\\\u0006\u0011\r\u0011\"\u0001j\u0005'C\u0001B!8\u0002A\u0003%\u00111\u0005\u0005\u000b\u0005?\f!\u0019!C\u0001S\nM\u0005\u0002\u0003Bq\u0003\u0001\u0006I!a\t\u0007\r\t\r\u0018\u0001\u0002Bs\u0011-\u0011yg\rB\u0001B\u0003%!q\u001d!\t\ra\u001cD\u0011\u0001By\u0011%\til\rb\u0001\n\u0003\nY\n\u0003\u0005\u0002@N\u0002\u000b\u0011BAO\r\u0019\u0019\t!\u0001\u0003\u0004\u0004!Y!q\u000e\u001d\u0003\u0002\u0003\u0006Ia!\u0002A\u0011)\t9\t\u000fB\u0001B\u0003&\u0011\u0011\u0012\u0005\u000b\u0003{C$Q1A\u0005B\u0005m\u0005BCA`q\t\u0005\t\u0015!\u0003\u0002\u001e\"1\u0001\u0010\u000fC\u0001\u0007\u001fAq!!&9\t\u0003\n9JB\u0003qO\u0002\t)\u0003\u0003\u0006\u0002.}\u0012)\u0019!C\u0001\u0003_A!\"!\u0014@\u0005\u0003\u0005\u000b\u0011BA\u0019\u0011\u0019Ax\b\"\u0001\u0002P!I\u00111L C\u0002\u0013\u0005\u0011Q\f\u0005\t\u0003[z\u0004\u0015!\u0003\u0002`!I\u0011qN C\u0002\u0013\u0005\u0011\u0011\u000f\u0005\t\u0003\u000b{\u0004\u0015!\u0003\u0002t!A\u0011qQ !B\u0013\tI\tC\u0004\u0002\u0016~\"\t!a&\t\u0013\u0005euH1A\u0005\u0002\u0005m\u0005\u0002CAR\u007f\u0001\u0006I!!(\t\u0011\u0005\u0015v\b)Q\u0005\u0003\u001fCq!a*@\t\u0003\tI\u000b\u0003\u0005\u0002,~\u0002\u000b\u0015BAH\u0011\u001d\tik\u0010C\u0001\u0003SC!\"a,@\u0011\u000b\u0007I\u0011AAU\u0011)\t\tl\u0010EC\u0002\u0013\u0005\u0011\u0011\u0016\u0005\u000b\u0003g{\u0004R1A\u0005\u0002\u0005U\u0006\"CA_\u007f\t\u0007I\u0011AAN\u0011!\tyl\u0010Q\u0001\n\u0005u\u0005bBAa\u007f\u0011\u0005\u00111\u0014\u0005\b\u0003\u0007|D\u0011AAN\u0011\u001d\t)m\u0010C\u0001\u00037Cq!a2@\t\u0003\tY\nC\u0004\u0002J~\"\t!a'\t\u000f\u0005-w\b\"\u0001\u0002N\"9\u00111[ \u0005\u0002\u0005U\u0007bBAm\u007f\u0011%\u00111\u001c\u0005\b\u0003k|D\u0011AAN\u0011\u001d\t9p\u0010C\u0001\u0003sDq!!@@\t\u0003\ny\u0010C\u0004\u0003\b}\"\tE!\u0003\t\u000f\t=q\b\"\u0001\u0003\u0012!9!QC \u0005\u0002\t]\u0001\"\u0003B\u0015\u007fE\u0005I\u0011\u0001B\u0016\u0011%\u0011iePI\u0001\n\u0003\u0011y\u0005C\u0005\u0003T}\n\n\u0011\"\u0001\u0003V!9!\u0011L \u0005B\tm\u0013!C*dC2\fG+\u001f9f\u0015\tA\u0017.A\u0004sK\u001adWm\u0019;\u000b\u0005)\\\u0017A\u00026t_:$4OC\u0001m\u0003\ry'oZ\u0002\u0001!\ty\u0017!D\u0001h\u0005%\u00196-\u00197b)f\u0004Xm\u0005\u0002\u0002eB\u00111O^\u0007\u0002i*\tQ/A\u0003tG\u0006d\u0017-\u0003\u0002xi\n1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u00018\u0002\u000bQL\b/Z:\u0016\u0003q\u0004Ra\\?\u0000\u0003GI!A`4\u0003\t5+Wn\u001c\u0019\u0005\u0003\u0003\ty\u0001\u0005\u0004\u0002\u0004\u0005\u001d\u00111B\u0007\u0003\u0003\u000bQ!\u0001\u001b;\n\t\u0005%\u0011Q\u0001\u0002\t\u001b\u0006t\u0017NZ3tiB!\u0011QBA\b\u0019\u0001!1\"!\u0005\u0005\u0003\u0003\u0005\tQ!\u0001\u0002\u0016\t\u0019q\fJ\u0019\u0002\rQL\b/Z:!#\u0011\t9\"!\b\u0011\u0007M\fI\"C\u0002\u0002\u001cQ\u0014qAT8uQ&tw\rE\u0002t\u0003?I1!!\tu\u0005\r\te.\u001f\t\u0003_~\u001aBa\u0010:\u0002(A\u00191/!\u000b\n\u0007\u0005-BO\u0001\u0004FcV\fGn]\u0001\t[\u0006t\u0017NZ3tiV\u0011\u0011\u0011\u0007\u0019\u0005\u0003g\tI\u0005\u0005\u0004\u00026\u0005\r\u0013q\t\b\u0005\u0003o\ty\u0004E\u0002\u0002:Ql!!a\u000f\u000b\u0007\u0005uR.\u0001\u0004=e>|GOP\u0005\u0004\u0003\u0003\"\u0018A\u0002)sK\u0012,g-\u0003\u0003\u0002\n\u0005\u0015#bAA!iB!\u0011QBA%\t-\tY%QA\u0001\u0002\u0003\u0015\t!!\u0006\u0003\u0007}#\u0013(A\u0005nC:Lg-Z:uAQ!\u00111EA)\u0011\u001d\tiC\u0011a\u0001\u0003'\u0002D!!\u0016\u0002ZA1\u0011QGA\"\u0003/\u0002B!!\u0004\u0002Z\u0011a\u00111JA)\u0003\u0003\u0005\tQ!\u0001\u0002\u0016\u00059QM]1tkJ,WCAA0a\u0011\t\t'!\u001b\u0011\r\u0005U\u00121MA4\u0013\u0011\t)'!\u0012\u0003\u000b\rc\u0017m]:\u0011\t\u00055\u0011\u0011\u000e\u0003\f\u0003W\"\u0015\u0011!A\u0001\u0006\u0003\t)B\u0001\u0003`IE\u0002\u0014\u0001C3sCN,(/\u001a\u0011\u0002\u0011QL\b/Z!sON,\"!a\u001d\u0011\r\u0005U\u0014qPA\u0012\u001d\u0011\t9(a\u001f\u000f\t\u0005e\u0012\u0011P\u0005\u0002k&\u0019\u0011Q\u0010;\u0002\u000fA\f7m[1hK&!\u0011\u0011QAB\u0005\r\u0019V-\u001d\u0006\u0004\u0003{\"\u0018!\u0003;za\u0016\f%oZ:!\u0003%yF/\u001f9f-\u0006\u00148\u000f\u0005\u0005\u00026\u0005-\u0015qRA\u0012\u0013\u0011\ti)!\u0012\u0003\u00075\u000b\u0007\u000f\u0005\u0003\u00026\u0005E\u0015\u0002BAJ\u0003\u000b\u0012aa\u0015;sS:<\u0017\u0001\u0003;za\u00164\u0016M]:\u0016\u0005\u0005%\u0015aB5t\u0003J\u0014\u0018-_\u000b\u0003\u0003;\u00032a]AP\u0013\r\t\t\u000b\u001e\u0002\b\u0005>|G.Z1o\u0003!I7/\u0011:sCf\u0004\u0013\u0001D0sC^4U\u000f\u001c7OC6,\u0017a\u0003:bo\u001a+H\u000e\u001c(b[\u0016,\"!a$\u0002\u001d}\u0013\u0018m^*j[BdWMT1nK\u0006i!/Y<TS6\u0004H.\u001a(b[\u0016\f!b]5na2,g*Y7f\u0003!1W\u000f\u001c7OC6,\u0017\u0001\u0003;za\u0016LeNZ8\u0016\u0005\u0005]\u0006cA8\u0002:&\u0019\u00111X4\u0003\u0011QK\b/Z%oM>\f1\"[:Qe&l\u0017\u000e^5wK\u0006a\u0011n\u001d)sS6LG/\u001b<fA\u0005)\u0011n]'ba\u0006a\u0011n]'vi\u0006\u0014G.Z'ba\u0006a\u0011n]\"pY2,7\r^5p]\u0006A\u0011n](qi&|g.\u0001\u0005jg\u0016KG\u000f[3s\u0003A!C.Z:tI\r|Gn\u001c8%Y\u0016\u001c8\u000f\u0006\u0003\u0002\u001e\u0006=\u0007bBAi3\u0002\u0007\u00111E\u0001\u0005i\"\fG/\u0001\f%OJ,\u0017\r^3sI\r|Gn\u001c8%OJ,\u0017\r^3s)\u0011\ti*a6\t\u000f\u0005E'\f1\u0001\u0002$\u0005q1/\u001b8hY\u0016$xN\u001c$jK2$WCAAo!\u0015\u0019\u0018q\\Ar\u0013\r\t\t\u000f\u001e\u0002\u0007\u001fB$\u0018n\u001c8\u0011\t\u0005\u0015\u0018\u0011_\u0007\u0003\u0003OT1\u0001[Au\u0015\u0011\tY/!<\u0002\t1\fgn\u001a\u0006\u0003\u0003_\fAA[1wC&!\u00111_At\u0005\u00151\u0015.\u001a7e\u0003-I7oU5oO2,Go\u001c8\u0002#MLgn\u001a7fi>t\u0017J\\:uC:\u001cW-\u0006\u0002\u0002|B!1/a8s\u0003!A\u0017m\u001d5D_\u0012,GC\u0001B\u0001!\r\u0019(1A\u0005\u0004\u0005\u000b!(aA%oi\u00061Q-];bYN$B!!(\u0003\f!9!QB0A\u0002\u0005u\u0011aA8cU\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u001e\nM\u0001bBAiA\u0002\u0007\u0011QD\u0001\u0005G>\u0004\u0018\u0010\u0006\u0005\u0002$\te!Q\u0005B\u0014\u0011%\tY&\u0019I\u0001\u0002\u0004\u0011Y\u0002\r\u0003\u0003\u001e\t\u0005\u0002CBA\u001b\u0003G\u0012y\u0002\u0005\u0003\u0002\u000e\t\u0005B\u0001\u0004B\u0012\u00053\t\t\u0011!A\u0003\u0002\u0005U!\u0001B0%eMB\u0011\"a\u001cb!\u0003\u0005\r!a\u001d\t\u0013\u0005U\u0015\r%AA\u0002\u0005%\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0005[\u0001DAa\f\u0003:)\"!\u0011\u0007B\u001e!\u0019\u0011\u0019D!\u000e\u000385\u0011\u0011\u0011^\u0005\u0005\u0003K\nI\u000f\u0005\u0003\u0002\u000e\teBa\u0003B\u0012E\u0006\u0005\t\u0011!B\u0001\u0003+Y#A!\u0010\u0011\t\t}\"\u0011J\u0007\u0003\u0005\u0003RAAa\u0011\u0003F\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0005\u000f\"\u0018AC1o]>$\u0018\r^5p]&!!1\nB!\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\u0011\tF\u000b\u0003\u0002t\tm\u0012AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0005/RC!!#\u0003<\u0005AAo\\*ue&tw\r\u0006\u0002\u0002\u0010\u0006\u00112/\u001b8hY\u0016$xN\u001c$jK2$g*Y7f+\t\u0011\t\u0007\u0005\u0003\u00034\t\r\u0014\u0002BAJ\u0003S\f1c]5oO2,Go\u001c8GS\u0016dGMT1nK\u0002\nQ!\u00199qYf,BAa\u001b\u0003vQ!\u00111\u0005B7\u0011\u001d\u0011yg\u0002a\u0001\u0005c\n!!\u001c4\u0011\r\u0005U\u00121\tB:!\u0011\tiA!\u001e\u0005\u000f\t]tA1\u0001\u0002\u0016\t\tA\u000b\u0006\u0004\u0002$\tm$q\u0011\u0005\b\u00037B\u0001\u0019\u0001B?a\u0011\u0011yHa!\u0011\r\u0005U\u00121\rBA!\u0011\tiAa!\u0005\u0019\t\u0015%1PA\u0001\u0002\u0003\u0015\t!!\u0006\u0003\u0007}##\u0007C\u0005\u0002p!\u0001\n\u00111\u0001\u0002t\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$#\u0007\u0006\u0003\u0002$\t5\u0005b\u0002BH\u0015\u0001\u0007\u0011qW\u0001\u0007i\u0006\u0014x-\u001a;\u0002\u000f%sG\u000fV=qKV\u0011\u00111E\u0001\t\u0013:$H+\u001f9fA\u0005Qa*^7cKJ$\u0016\u0010]3\u0002\u00179+XNY3s)f\u0004X\rI\u0001\t\u0019>tw\rV=qK\u0006IAj\u001c8h)f\u0004X\rI\u0001\t\u0005f$X\rV=qK\u0006I!)\u001f;f)f\u0004X\rI\u0001\n'\"|'\u000f\u001e+za\u0016\f!b\u00155peR$\u0016\u0010]3!\u0003-\u0011un\u001c7fC:$\u0016\u0010]3\u0002\u0019\t{w\u000e\\3b]RK\b/\u001a\u0011\u0002\u0013\u0019cw.\u0019;UsB,\u0017A\u0003$m_\u0006$H+\u001f9fA\u0005QAi\\;cY\u0016$\u0016\u0010]3\u0002\u0017\u0011{WO\u00197f)f\u0004X\rI\u0001\u000b'R\u0014\u0018N\\4UsB,\u0017aC*ue&tw\rV=qK\u0002\n!bU=nE>dG+\u001f9f\u0003-\u0019\u00160\u001c2pYRK\b/\u001a\u0011\u0002\u001d\tKw\rR3dS6\fG\u000eV=qK\u0006y!)[4EK\u000eLW.\u00197UsB,\u0007%\u0001\u0006CS\u001eLe\u000e\u001e+za\u0016\f1BQ5h\u0013:$H+\u001f9fA\u0005Q!JV1mk\u0016$\u0016\u0010]3\u0002\u0017)3\u0016\r\\;f)f\u0004X\rI\u0001\f\u0015>\u0013'.Z2u)f\u0004X-\u0001\u0007K\u001f\nTWm\u0019;UsB,\u0007%\u0001\u0006K\u0003J\u0014\u0018-\u001f+za\u0016\f1BS!se\u0006LH+\u001f9fA\u0005AA)\u0019;f)f\u0004X-A\u0005ECR,G+\u001f9fA\u0005iA+[7fgR\fW\u000e\u001d+za\u0016\fa\u0002V5nKN$\u0018-\u001c9UsB,\u0007%\u0001\u0006MSN$xJ\u00196fGR\f1\u0002T5ti>\u0013'.Z2uA\u00051qJ\u00196fGR\fqa\u00142kK\u000e$\b%A\bNCB\u001cFO]5oO>\u0013'.Z2u\u0003Ai\u0015\r]*ue&twm\u00142kK\u000e$\bE\u0001\nQe&l\u0017\u000e^5wKN\u001b\u0017\r\\1UsB,7cA\u001a\u0002$A\"!\u0011\u001eBw!\u0019\t)$a\u0011\u0003lB!\u0011Q\u0002Bw\t-\u0011y\u000fNA\u0001\u0002\u0003\u0015\t!!\u0006\u0003\u0007}#c\u0007\u0006\u0003\u0003t\n]\bc\u0001B{g5\t\u0011\u0001C\u0004\u0003pU\u0002\rA!?1\t\tm(q \t\u0007\u0003k\t\u0019E!@\u0011\t\u00055!q \u0003\r\u0005_\u001490!A\u0001\u0002\u000b\u0005\u0011Q\u0003\u0002\u0010\u0007>\u0004\u0018.\u001a3TG\u0006d\u0017\rV=qKN\u0019\u0001(a\t1\t\r\u001d11\u0002\t\u0007\u0003k\t\u0019e!\u0003\u0011\t\u0005511\u0002\u0003\f\u0007\u001bI\u0014\u0011!A\u0001\u0006\u0003\t)BA\u0002`I]\"\u0002b!\u0005\u0004\u0014\ru1q\u0004\t\u0004\u0005kD\u0004b\u0002B8{\u0001\u00071Q\u0003\u0019\u0005\u0007/\u0019Y\u0002\u0005\u0004\u00026\u0005\r3\u0011\u0004\t\u0005\u0003\u001b\u0019Y\u0002\u0002\u0007\u0004\u000e\rM\u0011\u0011!A\u0001\u0006\u0003\t)\u0002C\u0004\u0002\bv\u0002\r!!#\t\u000f\u0005uV\b1\u0001\u0002\u001e\u0002"
)
public class ScalaType implements Equals {
   private String simpleName;
   private String fullName;
   private TypeInfo typeInfo;
   private final Manifest manifest;
   private final Class erasure;
   private final Seq typeArgs;
   private Map _typeVars;
   private final boolean isArray;
   private String _rawFullName;
   private String _rawSimpleName;
   private final boolean isPrimitive;
   private volatile byte bitmap$0;

   public static ScalaType apply(final TypeInfo target) {
      return ScalaType$.MODULE$.apply(target);
   }

   public static Seq apply$default$2() {
      return ScalaType$.MODULE$.apply$default$2();
   }

   public static ScalaType apply(final Class erasure, final Seq typeArgs) {
      return ScalaType$.MODULE$.apply(erasure, typeArgs);
   }

   public static ScalaType apply(final Manifest mf) {
      return ScalaType$.MODULE$.apply(mf);
   }

   public Manifest manifest() {
      return this.manifest;
   }

   public Class erasure() {
      return this.erasure;
   }

   public Seq typeArgs() {
      return this.typeArgs;
   }

   public Map typeVars() {
      if (this._typeVars == null) {
         this._typeVars = (Map).MODULE$.Map().empty().$plus$plus(.MODULE$.wrapRefArray((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])this.erasure().getTypeParameters()), (x$6) -> x$6.getName(), scala.reflect.ClassTag..MODULE$.apply(String.class))), this.typeArgs())));
      }

      return this._typeVars;
   }

   public boolean isArray() {
      return this.isArray;
   }

   public String rawFullName() {
      if (this._rawFullName == null) {
         this._rawFullName = this.erasure().getName();
      }

      return this._rawFullName;
   }

   public String rawSimpleName() {
      if (this._rawSimpleName == null) {
         this._rawSimpleName = package$.MODULE$.safeSimpleName(this.erasure());
      }

      return this._rawSimpleName;
   }

   private String simpleName$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.simpleName = (new StringBuilder(0)).append(this.rawSimpleName()).append(this.typeArgs().nonEmpty() ? ((IterableOnceOps)this.typeArgs().map((x$7) -> x$7.simpleName())).mkString("[", ", ", "]") : (this.typeVars().nonEmpty() ? ((IterableOnceOps)this.typeVars().map((x$8) -> ((ScalaType)x$8._2()).simpleName())).mkString("[", ", ", "]") : "")).toString();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.simpleName;
   }

   public String simpleName() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.simpleName$lzycompute() : this.simpleName;
   }

   private String fullName$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.fullName = (new StringBuilder(0)).append(this.rawFullName()).append(this.typeArgs().nonEmpty() ? ((IterableOnceOps)this.typeArgs().map((x$9) -> x$9.fullName())).mkString("[", ", ", "]") : "").toString();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.fullName;
   }

   public String fullName() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.fullName$lzycompute() : this.fullName;
   }

   private TypeInfo typeInfo$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.typeInfo = new SourceType() {
               private final ScalaType scalaType = ScalaType.this;

               public ScalaType scalaType() {
                  return this.scalaType;
               }
            };
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.typeInfo;
   }

   public TypeInfo typeInfo() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.typeInfo$lzycompute() : this.typeInfo;
   }

   public boolean isPrimitive() {
      return this.isPrimitive;
   }

   public boolean isMap() {
      return Map.class.isAssignableFrom(this.erasure()) || scala.collection.Map.class.isAssignableFrom(this.erasure());
   }

   public boolean isMutableMap() {
      return scala.collection.mutable.Map.class.isAssignableFrom(this.erasure());
   }

   public boolean isCollection() {
      return this.erasure().isArray() || Iterable.class.isAssignableFrom(this.erasure()) || Collection.class.isAssignableFrom(this.erasure());
   }

   public boolean isOption() {
      return Option.class.isAssignableFrom(this.erasure());
   }

   public boolean isEither() {
      return Either.class.isAssignableFrom(this.erasure());
   }

   public boolean $less$colon$less(final ScalaType that) {
      return this.manifest().$less$colon$less(that.manifest());
   }

   public boolean $greater$colon$greater(final ScalaType that) {
      return this.manifest().$greater$colon$greater(that.manifest());
   }

   private Option singletonField() {
      return scala.collection.ArrayOps..MODULE$.find$extension(.MODULE$.refArrayOps((Object[])this.erasure().getFields()), (x$11) -> BoxesRunTime.boxToBoolean($anonfun$singletonField$1(x$11)));
   }

   public boolean isSingleton() {
      return this.singletonField().isDefined();
   }

   public Option singletonInstance() {
      return this.singletonField().map((x$12) -> x$12.get((Object)null));
   }

   public int hashCode() {
      return Statics.anyHash(this.manifest());
   }

   public boolean equals(final Object obj) {
      boolean var2;
      if (obj instanceof ScalaType) {
         boolean var6;
         label21: {
            label20: {
               ScalaType var4 = (ScalaType)obj;
               Manifest var10000 = this.manifest();
               Manifest var5 = var4.manifest();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label20;
                  }
               } else if (var10000.equals(var5)) {
                  break label20;
               }

               var6 = false;
               break label21;
            }

            var6 = true;
         }

         var2 = var6;
      } else {
         var2 = false;
      }

      return var2;
   }

   public boolean canEqual(final Object that) {
      boolean var2;
      if (that instanceof ScalaType) {
         ScalaType var4 = (ScalaType)that;
         var2 = this.manifest().canEqual(var4.manifest());
      } else {
         var2 = false;
      }

      return var2;
   }

   public ScalaType copy(final Class erasure, final Seq typeArgs, final Map typeVars) {
      Object var10000;
      label364: {
         Class var4 = Integer.TYPE;
         if (erasure == null) {
            if (var4 == null) {
               break label364;
            }
         } else if (erasure.equals(var4)) {
            break label364;
         }

         Class var5 = Integer.class;
         if (erasure == null) {
            if (var5 == null) {
               break label364;
            }
         } else if (erasure.equals(var5)) {
            break label364;
         }

         label365: {
            Class var6 = Long.TYPE;
            if (erasure == null) {
               if (var6 == null) {
                  break label365;
               }
            } else if (erasure.equals(var6)) {
               break label365;
            }

            Class var7 = Long.class;
            if (erasure == null) {
               if (var7 == null) {
                  break label365;
               }
            } else if (erasure.equals(var7)) {
               break label365;
            }

            label366: {
               Class var8 = Byte.TYPE;
               if (erasure == null) {
                  if (var8 == null) {
                     break label366;
                  }
               } else if (erasure.equals(var8)) {
                  break label366;
               }

               Class var9 = Byte.class;
               if (erasure == null) {
                  if (var9 == null) {
                     break label366;
                  }
               } else if (erasure.equals(var9)) {
                  break label366;
               }

               label367: {
                  Class var10 = Short.TYPE;
                  if (erasure == null) {
                     if (var10 == null) {
                        break label367;
                     }
                  } else if (erasure.equals(var10)) {
                     break label367;
                  }

                  Class var11 = Short.class;
                  if (erasure == null) {
                     if (var11 == null) {
                        break label367;
                     }
                  } else if (erasure.equals(var11)) {
                     break label367;
                  }

                  label368: {
                     Class var12 = Float.TYPE;
                     if (erasure == null) {
                        if (var12 == null) {
                           break label368;
                        }
                     } else if (erasure.equals(var12)) {
                        break label368;
                     }

                     Class var13 = Float.class;
                     if (erasure == null) {
                        if (var13 == null) {
                           break label368;
                        }
                     } else if (erasure.equals(var13)) {
                        break label368;
                     }

                     label369: {
                        Class var14 = Double.TYPE;
                        if (erasure == null) {
                           if (var14 == null) {
                              break label369;
                           }
                        } else if (erasure.equals(var14)) {
                           break label369;
                        }

                        Class var15 = Double.class;
                        if (erasure == null) {
                           if (var15 == null) {
                              break label369;
                           }
                        } else if (erasure.equals(var15)) {
                           break label369;
                        }

                        label370: {
                           Class var16 = BigInt.class;
                           if (erasure == null) {
                              if (var16 == null) {
                                 break label370;
                              }
                           } else if (erasure.equals(var16)) {
                              break label370;
                           }

                           Class var17 = BigInteger.class;
                           if (erasure == null) {
                              if (var17 == null) {
                                 break label370;
                              }
                           } else if (erasure.equals(var17)) {
                              break label370;
                           }

                           label371: {
                              Class var18 = BigDecimal.class;
                              if (erasure == null) {
                                 if (var18 == null) {
                                    break label371;
                                 }
                              } else if (erasure.equals(var18)) {
                                 break label371;
                              }

                              Class var19 = java.math.BigDecimal.class;
                              if (erasure == null) {
                                 if (var19 == null) {
                                    break label371;
                                 }
                              } else if (erasure.equals(var19)) {
                                 break label371;
                              }

                              label372: {
                                 Class var20 = Boolean.TYPE;
                                 if (erasure == null) {
                                    if (var20 == null) {
                                       break label372;
                                    }
                                 } else if (erasure.equals(var20)) {
                                    break label372;
                                 }

                                 Class var21 = Boolean.class;
                                 if (erasure == null) {
                                    if (var21 == null) {
                                       break label372;
                                    }
                                 } else if (erasure.equals(var21)) {
                                    break label372;
                                 }

                                 label373: {
                                    Class var22 = String.class;
                                    if (erasure == null) {
                                       if (var22 == null) {
                                          break label373;
                                       }
                                    } else if (erasure.equals(var22)) {
                                       break label373;
                                    }

                                    Class var23 = String.class;
                                    if (erasure == null) {
                                       if (var23 == null) {
                                          break label373;
                                       }
                                    } else if (erasure.equals(var23)) {
                                       break label373;
                                    }

                                    label374: {
                                       Class var24 = Date.class;
                                       if (erasure == null) {
                                          if (var24 == null) {
                                             break label374;
                                          }
                                       } else if (erasure.equals(var24)) {
                                          break label374;
                                       }

                                       label375: {
                                          Class var25 = Timestamp.class;
                                          if (erasure == null) {
                                             if (var25 == null) {
                                                break label375;
                                             }
                                          } else if (erasure.equals(var25)) {
                                             break label375;
                                          }

                                          label376: {
                                             Class var26 = Symbol.class;
                                             if (erasure == null) {
                                                if (var26 == null) {
                                                   break label376;
                                                }
                                             } else if (erasure.equals(var26)) {
                                                break label376;
                                             }

                                             label377: {
                                                Class var27 = Number.class;
                                                if (erasure == null) {
                                                   if (var27 == null) {
                                                      break label377;
                                                   }
                                                } else if (erasure.equals(var27)) {
                                                   break label377;
                                                }

                                                label378: {
                                                   Class var28 = JObject.class;
                                                   if (erasure == null) {
                                                      if (var28 == null) {
                                                         break label378;
                                                      }
                                                   } else if (erasure.equals(var28)) {
                                                      break label378;
                                                   }

                                                   label379: {
                                                      Class var29 = JArray.class;
                                                      if (erasure == null) {
                                                         if (var29 == null) {
                                                            break label379;
                                                         }
                                                      } else if (erasure.equals(var29)) {
                                                         break label379;
                                                      }

                                                      label380: {
                                                         Class var30 = JValue.class;
                                                         if (erasure == null) {
                                                            if (var30 == null) {
                                                               break label380;
                                                            }
                                                         } else if (erasure.equals(var30)) {
                                                            break label380;
                                                         }

                                                         Manifest mf = ManifestFactory$.MODULE$.manifestOf(erasure, (Seq)typeArgs.map((x$13) -> x$13.manifest()));
                                                         CopiedScalaType st = new CopiedScalaType(mf, typeVars, this.isPrimitive());
                                                         var10000 = typeArgs.isEmpty() ? (ScalaType)ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$types().replace(mf, st) : st;
                                                         return (ScalaType)var10000;
                                                      }

                                                      var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$JValueType();
                                                      return (ScalaType)var10000;
                                                   }

                                                   var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$JArrayType();
                                                   return (ScalaType)var10000;
                                                }

                                                var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$JObjectType();
                                                return (ScalaType)var10000;
                                             }

                                             var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$NumberType();
                                             return (ScalaType)var10000;
                                          }

                                          var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$SymbolType();
                                          return (ScalaType)var10000;
                                       }

                                       var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$TimestampType();
                                       return (ScalaType)var10000;
                                    }

                                    var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$DateType();
                                    return (ScalaType)var10000;
                                 }

                                 var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$StringType();
                                 return (ScalaType)var10000;
                              }

                              var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$BooleanType();
                              return (ScalaType)var10000;
                           }

                           var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$BigDecimalType();
                           return (ScalaType)var10000;
                        }

                        var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$BigIntType();
                        return (ScalaType)var10000;
                     }

                     var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$DoubleType();
                     return (ScalaType)var10000;
                  }

                  var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$FloatType();
                  return (ScalaType)var10000;
               }

               var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$ShortType();
               return (ScalaType)var10000;
            }

            var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$ByteType();
            return (ScalaType)var10000;
         }

         var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$LongType();
         return (ScalaType)var10000;
      }

      var10000 = ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$IntType();
      return (ScalaType)var10000;
   }

   public Class copy$default$1() {
      return this.erasure();
   }

   public Seq copy$default$2() {
      return this.typeArgs();
   }

   public Map copy$default$3() {
      return this._typeVars;
   }

   public String toString() {
      return this.simpleName();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$singletonField$1(final Field x$11) {
      return x$11.getName().equals(ScalaType$.MODULE$.org$json4s$reflect$ScalaType$$singletonFieldName());
   }

   public ScalaType(final Manifest manifest) {
      this.manifest = manifest;
      this.erasure = manifest.runtimeClass();
      this.typeArgs = (Seq)manifest.typeArguments().map((ta) -> Reflector$.MODULE$.scalaTypeOf(ta)).$plus$plus((IterableOnce)(this.erasure().isArray() ? (IterableOnce)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ScalaType[]{Reflector$.MODULE$.scalaTypeOf(this.erasure().getComponentType())})) : scala.package..MODULE$.Nil()));
      this._typeVars = null;
      this.isArray = this.erasure().isArray();
      this._rawFullName = null;
      this._rawSimpleName = null;
      this.isPrimitive = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class PrimitiveScalaType extends ScalaType {
      private final boolean isPrimitive = true;

      public boolean isPrimitive() {
         return this.isPrimitive;
      }

      public PrimitiveScalaType(final Manifest mf) {
         super(mf);
      }
   }

   private static class CopiedScalaType extends ScalaType {
      private Map _typeVars;
      private final boolean isPrimitive;

      public boolean isPrimitive() {
         return this.isPrimitive;
      }

      public Map typeVars() {
         if (this._typeVars == null) {
            this._typeVars = (Map).MODULE$.Map().empty().$plus$plus(.MODULE$.wrapRefArray((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])this.erasure().getTypeParameters()), (x$5) -> x$5.getName(), scala.reflect.ClassTag..MODULE$.apply(String.class))), this.typeArgs())));
         }

         return this._typeVars;
      }

      public CopiedScalaType(final Manifest mf, final Map _typeVars, final boolean isPrimitive) {
         this._typeVars = _typeVars;
         this.isPrimitive = isPrimitive;
         super(mf);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
