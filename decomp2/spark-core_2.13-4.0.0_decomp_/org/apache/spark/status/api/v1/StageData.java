package org.apache.spark.status.api.v1;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import scala.Option;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u001dfaBA\u0003\u0003\u000f\u0001\u0011\u0011\u0005\u0005\u000b\u0003#\u0001!Q1A\u0005\u0002\u0005=\u0002BCA\u001d\u0001\t\u0005\t\u0015!\u0003\u00022!Q\u00111\b\u0001\u0003\u0006\u0004%\t!!\u0010\t\u0015\u0005\u0015\u0003A!A!\u0002\u0013\ty\u0004\u0003\u0006\u0002H\u0001\u0011)\u0019!C\u0001\u0003{A!\"!\u0013\u0001\u0005\u0003\u0005\u000b\u0011BA \u0011)\tY\u0005\u0001BC\u0002\u0013\u0005\u0011Q\b\u0005\u000b\u0003\u001b\u0002!\u0011!Q\u0001\n\u0005}\u0002BCA(\u0001\t\u0015\r\u0011\"\u0001\u0002>!Q\u0011\u0011\u000b\u0001\u0003\u0002\u0003\u0006I!a\u0010\t\u0015\u0005M\u0003A!b\u0001\n\u0003\ti\u0004\u0003\u0006\u0002V\u0001\u0011\t\u0011)A\u0005\u0003\u007fA!\"a\u0016\u0001\u0005\u000b\u0007I\u0011AA\u001f\u0011)\tI\u0006\u0001B\u0001B\u0003%\u0011q\b\u0005\u000b\u00037\u0002!Q1A\u0005\u0002\u0005u\u0002BCA/\u0001\t\u0005\t\u0015!\u0003\u0002@!Q\u0011q\f\u0001\u0003\u0006\u0004%\t!!\u0010\t\u0015\u0005\u0005\u0004A!A!\u0002\u0013\ty\u0004\u0003\u0006\u0002d\u0001\u0011)\u0019!C\u0001\u0003KB!\"! \u0001\u0005\u0003\u0005\u000b\u0011BA4\u0011)\ty\b\u0001BC\u0002\u0013\u0005\u0011Q\r\u0005\u000b\u0003\u0003\u0003!\u0011!Q\u0001\n\u0005\u001d\u0004BCAB\u0001\t\u0015\r\u0011\"\u0001\u0002f!Q\u0011Q\u0011\u0001\u0003\u0002\u0003\u0006I!a\u001a\t\u0015\u0005\u001d\u0005A!b\u0001\n\u0003\tI\t\u0003\u0006\u0002$\u0002\u0011\t\u0011)A\u0005\u0003\u0017C!\"!*\u0001\u0005\u000b\u0007I\u0011AAT\u0011)\ty\u000b\u0001B\u0001B\u0003%\u0011\u0011\u0016\u0005\u000b\u0003c\u0003!Q1A\u0005\u0002\u0005\u001d\u0006BCAZ\u0001\t\u0005\t\u0015!\u0003\u0002*\"Q\u0011Q\u0017\u0001\u0003\u0006\u0004%\t!a*\t\u0015\u0005]\u0006A!A!\u0002\u0013\tI\u000b\u0003\u0006\u0002:\u0002\u0011)\u0019!C\u0001\u0003OC!\"a/\u0001\u0005\u0003\u0005\u000b\u0011BAU\u0011)\ti\f\u0001BC\u0002\u0013\u0005\u0011q\u0015\u0005\u000b\u0003\u007f\u0003!\u0011!Q\u0001\n\u0005%\u0006BCAa\u0001\t\u0015\r\u0011\"\u0001\u0002(\"Q\u00111\u0019\u0001\u0003\u0002\u0003\u0006I!!+\t\u0015\u0005\u0015\u0007A!b\u0001\n\u0003\t9\u000b\u0003\u0006\u0002H\u0002\u0011\t\u0011)A\u0005\u0003SC!\"!3\u0001\u0005\u000b\u0007I\u0011AAT\u0011)\tY\r\u0001B\u0001B\u0003%\u0011\u0011\u0016\u0005\u000b\u0003\u001b\u0004!Q1A\u0005\u0002\u0005\u001d\u0006BCAh\u0001\t\u0005\t\u0015!\u0003\u0002*\"Q\u0011\u0011\u001b\u0001\u0003\u0006\u0004%\t!a*\t\u0015\u0005M\u0007A!A!\u0002\u0013\tI\u000b\u0003\u0006\u0002V\u0002\u0011)\u0019!C\u0001\u0003OC!\"a6\u0001\u0005\u0003\u0005\u000b\u0011BAU\u0011)\tI\u000e\u0001BC\u0002\u0013\u0005\u0011q\u0015\u0005\u000b\u00037\u0004!\u0011!Q\u0001\n\u0005%\u0006BCAo\u0001\t\u0015\r\u0011\"\u0001\u0002(\"Q\u0011q\u001c\u0001\u0003\u0002\u0003\u0006I!!+\t\u0015\u0005\u0005\bA!b\u0001\n\u0003\t9\u000b\u0003\u0006\u0002d\u0002\u0011\t\u0011)A\u0005\u0003SC!\"!:\u0001\u0005\u000b\u0007I\u0011AAT\u0011)\t9\u000f\u0001B\u0001B\u0003%\u0011\u0011\u0016\u0005\u000b\u0003S\u0004!Q1A\u0005\u0002\u0005\u001d\u0006BCAv\u0001\t\u0005\t\u0015!\u0003\u0002*\"Q\u0011Q\u001e\u0001\u0003\u0006\u0004%\t!a*\t\u0015\u0005=\bA!A!\u0002\u0013\tI\u000b\u0003\u0006\u0002r\u0002\u0011)\u0019!C\u0001\u0003OC!\"a=\u0001\u0005\u0003\u0005\u000b\u0011BAU\u0011)\t)\u0010\u0001BC\u0002\u0013\u0005\u0011q\u0015\u0005\u000b\u0003o\u0004!\u0011!Q\u0001\n\u0005%\u0006BCA}\u0001\t\u0015\r\u0011\"\u0001\u0002(\"Q\u00111 \u0001\u0003\u0002\u0003\u0006I!!+\t\u0015\u0005u\bA!b\u0001\n\u0003\t9\u000b\u0003\u0006\u0002\u0000\u0002\u0011\t\u0011)A\u0005\u0003SC!B!\u0001\u0001\u0005\u000b\u0007I\u0011AAT\u0011)\u0011\u0019\u0001\u0001B\u0001B\u0003%\u0011\u0011\u0016\u0005\u000b\u0005\u000b\u0001!Q1A\u0005\u0002\u0005\u001d\u0006B\u0003B\u0004\u0001\t\u0005\t\u0015!\u0003\u0002*\"Q!\u0011\u0002\u0001\u0003\u0006\u0004%\t!a*\t\u0015\t-\u0001A!A!\u0002\u0013\tI\u000b\u0003\u0006\u0003\u000e\u0001\u0011)\u0019!C\u0001\u0003OC!Ba\u0004\u0001\u0005\u0003\u0005\u000b\u0011BAU\u0011)\u0011\t\u0002\u0001BC\u0002\u0013\u0005\u0011q\u0015\u0005\u000b\u0005'\u0001!\u0011!Q\u0001\n\u0005%\u0006B\u0003B\u000b\u0001\t\u0015\r\u0011\"\u0001\u0002(\"Q!q\u0003\u0001\u0003\u0002\u0003\u0006I!!+\t\u0015\te\u0001A!b\u0001\n\u0003\t9\u000b\u0003\u0006\u0003\u001c\u0001\u0011\t\u0011)A\u0005\u0003SC!B!\b\u0001\u0005\u000b\u0007I\u0011AAT\u0011)\u0011y\u0002\u0001B\u0001B\u0003%\u0011\u0011\u0016\u0005\u000b\u0005C\u0001!Q1A\u0005\u0002\u0005\u001d\u0006B\u0003B\u0012\u0001\t\u0005\t\u0015!\u0003\u0002*\"Q!Q\u0005\u0001\u0003\u0006\u0004%\t!a*\t\u0015\t\u001d\u0002A!A!\u0002\u0013\tI\u000b\u0003\u0006\u0003*\u0001\u0011)\u0019!C\u0001\u0003OC!Ba\u000b\u0001\u0005\u0003\u0005\u000b\u0011BAU\u0011)\u0011i\u0003\u0001BC\u0002\u0013\u0005\u0011q\u0015\u0005\u000b\u0005_\u0001!\u0011!Q\u0001\n\u0005%\u0006B\u0003B\u0019\u0001\t\u0015\r\u0011\"\u0001\u0002(\"Q!1\u0007\u0001\u0003\u0002\u0003\u0006I!!+\t\u0015\tU\u0002A!b\u0001\n\u0003\t9\u000b\u0003\u0006\u00038\u0001\u0011\t\u0011)A\u0005\u0003SC!B!\u000f\u0001\u0005\u000b\u0007I\u0011\u0001B\u001e\u0011)\u0011i\u0004\u0001B\u0001B\u0003%\u0011Q\u0012\u0005\u000b\u0005\u007f\u0001!Q1A\u0005\u0002\u0005%\u0005B\u0003B!\u0001\t\u0005\t\u0015!\u0003\u0002\f\"Q!1\t\u0001\u0003\u0006\u0004%\tAa\u000f\t\u0015\t\u0015\u0003A!A!\u0002\u0013\ti\t\u0003\u0006\u0003H\u0001\u0011)\u0019!C\u0001\u0005wA!B!\u0013\u0001\u0005\u0003\u0005\u000b\u0011BAG\u0011)\u0011Y\u0005\u0001BC\u0002\u0013\u0005!Q\n\u0005\u000b\u00057\u0002!\u0011!Q\u0001\n\t=\u0003B\u0003B/\u0001\t\u0015\r\u0011\"\u0001\u0003`!Q!\u0011\u000e\u0001\u0003\u0002\u0003\u0006IA!\u0019\t\u0015\t-\u0004A!b\u0001\n\u0003\u0011i\u0007\u0003\u0006\u0003~\u0001\u0011\t\u0011)A\u0005\u0005_B!Ba \u0001\u0005\u000b\u0007I\u0011\u0001BA\u0011)\u0011i\t\u0001B\u0001B\u0003%!1\u0011\u0005\u000b\u0005\u001f\u0003!Q1A\u0005\u0002\tE\u0005B\u0003BN\u0001\t\u0005\t\u0015!\u0003\u0003\u0014\"Q!Q\u0014\u0001\u0003\u0006\u0004%\tAa(\t\u0015\t\r\u0006A!A!\u0002\u0013\u0011\t\u000b\u0003\u0006\u0003&\u0002\u0011)\u0019!C\u0001\u0003{A!Ba*\u0001\u0005\u0003\u0005\u000b\u0011BA \u0011)\u0011I\u000b\u0001BC\u0002\u0013\u0005!1\u0016\u0005\u000b\u0005w\u0003!\u0011!Q\u0001\n\t5\u0006B\u0003B_\u0001\t\u0015\r\u0011\"\u0001\u0003@\"Q!\u0011\u001a\u0001\u0003\u0002\u0003\u0006IA!1\t\u0015\t-\u0007A!b\u0001\n\u0003\u0011i\r\u0003\u0006\u0003X\u0002\u0011\t\u0011)A\u0005\u0005\u001fD!B!7\u0001\u0005\u000b\u0007I\u0011\u0001Bn\u0011)\u0011\u0019\u000f\u0001B\u0001B\u0003%!Q\u001c\u0005\u000b\u0005K\u0004!Q1A\u0005\u0002\u0005u\u0002B\u0003Bt\u0001\t\u0005\t\u0015!\u0003\u0002@!I!\u0011\u001e\u0001\u0005\u0002\u0005M!1\u001e\u0002\n'R\fw-\u001a#bi\u0006TA!!\u0003\u0002\f\u0005\u0011a/\r\u0006\u0005\u0003\u001b\ty!A\u0002ba&TA!!\u0005\u0002\u0014\u000511\u000f^1ukNTA!!\u0006\u0002\u0018\u0005)1\u000f]1sW*!\u0011\u0011DA\u000e\u0003\u0019\t\u0007/Y2iK*\u0011\u0011QD\u0001\u0004_J<7\u0001A\n\u0004\u0001\u0005\r\u0002\u0003BA\u0013\u0003Wi!!a\n\u000b\u0005\u0005%\u0012!B:dC2\f\u0017\u0002BA\u0017\u0003O\u0011a!\u00118z%\u00164WCAA\u0019!\u0011\t\u0019$!\u000e\u000e\u0005\u0005\u001d\u0011\u0002BA\u001c\u0003\u000f\u00111b\u0015;bO\u0016\u001cF/\u0019;vg\u000691\u000f^1ukN\u0004\u0013aB:uC\u001e,\u0017\nZ\u000b\u0003\u0003\u007f\u0001B!!\n\u0002B%!\u00111IA\u0014\u0005\rIe\u000e^\u0001\tgR\fw-Z%eA\u0005I\u0011\r\u001e;f[B$\u0018\nZ\u0001\u000bCR$X-\u001c9u\u0013\u0012\u0004\u0013\u0001\u00038v[R\u000b7o[:\u0002\u00139,X\u000eV1tWN\u0004\u0013A\u00048v[\u0006\u001bG/\u001b<f)\u0006\u001c8n]\u0001\u0010]Vl\u0017i\u0019;jm\u0016$\u0016m]6tA\u0005\u0001b.^7D_6\u0004H.\u001a;f)\u0006\u001c8n]\u0001\u0012]Vl7i\\7qY\u0016$X\rV1tWN\u0004\u0013A\u00048v[\u001a\u000b\u0017\u000e\\3e)\u0006\u001c8n]\u0001\u0010]Vlg)Y5mK\u0012$\u0016m]6tA\u0005qa.^7LS2dW\r\u001a+bg.\u001c\u0018a\u00048v[.KG\u000e\\3e)\u0006\u001c8n\u001d\u0011\u0002'9,XnQ8na2,G/\u001a3J]\u0012L7-Z:\u0002)9,XnQ8na2,G/\u001a3J]\u0012L7-Z:!\u00039\u0019XOY7jgNLwN\u001c+j[\u0016,\"!a\u001a\u0011\r\u0005\u0015\u0012\u0011NA7\u0013\u0011\tY'a\n\u0003\r=\u0003H/[8o!\u0011\ty'!\u001f\u000e\u0005\u0005E$\u0002BA:\u0003k\nA!\u001e;jY*\u0011\u0011qO\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002|\u0005E$\u0001\u0002#bi\u0016\fqb];c[&\u001c8/[8o)&lW\rI\u0001\u0016M&\u00148\u000f\u001e+bg.d\u0015-\u001e8dQ\u0016$G+[7f\u0003Y1\u0017N]:u)\u0006\u001c8\u000eT1v]\u000eDW\r\u001a+j[\u0016\u0004\u0013AD2p[BdW\r^5p]RKW.Z\u0001\u0010G>l\u0007\u000f\\3uS>tG+[7fA\u0005ia-Y5mkJ,'+Z1t_:,\"!a#\u0011\r\u0005\u0015\u0012\u0011NAG!\u0011\ty)!(\u000f\t\u0005E\u0015\u0011\u0014\t\u0005\u0003'\u000b9#\u0004\u0002\u0002\u0016*!\u0011qSA\u0010\u0003\u0019a$o\\8u}%!\u00111TA\u0014\u0003\u0019\u0001&/\u001a3fM&!\u0011qTAQ\u0005\u0019\u0019FO]5oO*!\u00111TA\u0014\u000391\u0017-\u001b7ve\u0016\u0014V-Y:p]\u0002\nq#\u001a=fGV$xN\u001d#fg\u0016\u0014\u0018.\u00197ju\u0016$\u0016.\\3\u0016\u0005\u0005%\u0006\u0003BA\u0013\u0003WKA!!,\u0002(\t!Aj\u001c8h\u0003a)\u00070Z2vi>\u0014H)Z:fe&\fG.\u001b>f)&lW\rI\u0001\u001bKb,7-\u001e;pe\u0012+7/\u001a:jC2L'0Z\"qkRKW.Z\u0001\u001cKb,7-\u001e;pe\u0012+7/\u001a:jC2L'0Z\"qkRKW.\u001a\u0011\u0002\u001f\u0015DXmY;u_J\u0014VO\u001c+j[\u0016\f\u0001#\u001a=fGV$xN\u001d*v]RKW.\u001a\u0011\u0002\u001f\u0015DXmY;u_J\u001c\u0005/\u001e+j[\u0016\f\u0001#\u001a=fGV$xN]\"qkRKW.\u001a\u0011\u0002\u0015I,7/\u001e7u'&TX-A\u0006sKN,H\u000e^*ju\u0016\u0004\u0013!\u00036w[\u001e\u001bG+[7f\u0003)Qg/\\$d)&lW\rI\u0001\u0018e\u0016\u001cX\u000f\u001c;TKJL\u0017\r\\5{CRLwN\u001c+j[\u0016\f\u0001D]3tk2$8+\u001a:jC2L'0\u0019;j_:$\u0016.\\3!\u0003IiW-\\8ss\nKH/Z:Ta&dG.\u001a3\u0002'5,Wn\u001c:z\u0005f$Xm]*qS2dW\r\u001a\u0011\u0002!\u0011L7o\u001b\"zi\u0016\u001c8\u000b]5mY\u0016$\u0017!\u00053jg.\u0014\u0015\u0010^3t'BLG\u000e\\3eA\u0005\u0019\u0002/Z1l\u000bb,7-\u001e;j_:lU-\\8ss\u0006!\u0002/Z1l\u000bb,7-\u001e;j_:lU-\\8ss\u0002\n!\"\u001b8qkR\u0014\u0015\u0010^3t\u0003-Ig\u000e];u\u0005f$Xm\u001d\u0011\u0002\u0019%t\u0007/\u001e;SK\u000e|'\u000fZ:\u0002\u001b%t\u0007/\u001e;SK\u000e|'\u000fZ:!\u0003-yW\u000f\u001e9vi\nKH/Z:\u0002\u0019=,H\u000f];u\u0005f$Xm\u001d\u0011\u0002\u001b=,H\u000f];u%\u0016\u001cwN\u001d3t\u00039yW\u000f\u001e9viJ+7m\u001c:eg\u0002\n!d\u001d5vM\u001adWMU3n_R,'\t\\8dWN4U\r^2iK\u0012\f1d\u001d5vM\u001adWMU3n_R,'\t\\8dWN4U\r^2iK\u0012\u0004\u0013!G:ik\u001a4G.\u001a'pG\u0006d'\t\\8dWN4U\r^2iK\u0012\f!d\u001d5vM\u001adW\rT8dC2\u0014En\\2lg\u001a+Go\u00195fI\u0002\nAc\u001d5vM\u001adWMR3uG\"<\u0016-\u001b;US6,\u0017!F:ik\u001a4G.\u001a$fi\u000eDw+Y5u)&lW\rI\u0001\u0017g\",hM\u001a7f%\u0016lw\u000e^3CsR,7OU3bI\u000692\u000f[;gM2,'+Z7pi\u0016\u0014\u0015\u0010^3t%\u0016\fG\rI\u0001\u001dg\",hM\u001a7f%\u0016lw\u000e^3CsR,7OU3bIR{G)[:l\u0003u\u0019\b.\u001e4gY\u0016\u0014V-\\8uK\nKH/Z:SK\u0006$Gk\u001c#jg.\u0004\u0013!F:ik\u001a4G.\u001a'pG\u0006d')\u001f;fgJ+\u0017\rZ\u0001\u0017g\",hM\u001a7f\u0019>\u001c\u0017\r\u001c\"zi\u0016\u001c(+Z1eA\u0005\u00012\u000f[;gM2,'+Z1e\u0005f$Xm]\u0001\u0012g\",hM\u001a7f%\u0016\fGMQ=uKN\u0004\u0013AE:ik\u001a4G.\u001a*fC\u0012\u0014VmY8sIN\f1c\u001d5vM\u001adWMU3bIJ+7m\u001c:eg\u0002\nqd\u001d5vM\u001adWmQ8seV\u0004H/T3sO\u0016$'\t\\8dW\u000eCWO\\6t\u0003\u0001\u001a\b.\u001e4gY\u0016\u001cuN\u001d:vaRlUM]4fI\ncwnY6DQVt7n\u001d\u0011\u0002?MDWO\u001a4mK6+'oZ3e\r\u0016$8\r\u001b$bY2\u0014\u0017mY6D_VtG/\u0001\u0011tQV4g\r\\3NKJ<W\r\u001a$fi\u000eDg)\u00197mE\u0006\u001c7nQ8v]R\u0004\u0013\u0001I:ik\u001a4G.Z'fe\u001e,GMU3n_R,'\t\\8dWN4U\r^2iK\u0012\f\u0011e\u001d5vM\u001adW-T3sO\u0016$'+Z7pi\u0016\u0014En\\2lg\u001a+Go\u00195fI\u0002\nqd\u001d5vM\u001adW-T3sO\u0016$Gj\\2bY\ncwnY6t\r\u0016$8\r[3e\u0003\u0001\u001a\b.\u001e4gY\u0016lUM]4fI2{7-\u00197CY>\u001c7n\u001d$fi\u000eDW\r\u001a\u0011\u0002AMDWO\u001a4mK6+'oZ3e%\u0016lw\u000e^3DQVt7n\u001d$fi\u000eDW\rZ\u0001\"g\",hM\u001a7f\u001b\u0016\u0014x-\u001a3SK6|G/Z\"ik:\\7OR3uG\",G\rI\u0001 g\",hM\u001a7f\u001b\u0016\u0014x-\u001a3M_\u000e\fGn\u00115v].\u001ch)\u001a;dQ\u0016$\u0017\u0001I:ik\u001a4G.Z'fe\u001e,G\rT8dC2\u001c\u0005.\u001e8lg\u001a+Go\u00195fI\u0002\nAd\u001d5vM\u001adW-T3sO\u0016$'+Z7pi\u0016\u0014\u0015\u0010^3t%\u0016\fG-A\u000ftQV4g\r\\3NKJ<W\r\u001a*f[>$XMQ=uKN\u0014V-\u00193!\u0003m\u0019\b.\u001e4gY\u0016lUM]4fI2{7-\u00197CsR,7OU3bI\u0006a2\u000f[;gM2,W*\u001a:hK\u0012dunY1m\u0005f$Xm\u001d*fC\u0012\u0004\u0013!G:ik\u001a4G.\u001a*f[>$XMU3rg\u0012+(/\u0019;j_:\f!d\u001d5vM\u001adWMU3n_R,'+Z9t\tV\u0014\u0018\r^5p]\u0002\nqd\u001d5vM\u001adW-T3sO\u0016$'+Z7pi\u0016\u0014V-]:EkJ\fG/[8o\u0003\u0001\u001a\b.\u001e4gY\u0016lUM]4fIJ+Wn\u001c;f%\u0016\f8\u000fR;sCRLwN\u001c\u0011\u0002#MDWO\u001a4mK^\u0013\u0018\u000e^3CsR,7/\u0001\ntQV4g\r\\3Xe&$XMQ=uKN\u0004\u0013\u0001E:ik\u001a4G.Z,sSR,G+[7f\u0003E\u0019\b.\u001e4gY\u0016<&/\u001b;f)&lW\rI\u0001\u0014g\",hM\u001a7f/JLG/\u001a*fG>\u0014Hm]\u0001\u0015g\",hM\u001a7f/JLG/\u001a*fG>\u0014Hm\u001d\u0011\u0002\t9\fW.Z\u000b\u0003\u0003\u001b\u000bQA\\1nK\u0002\n1\u0002Z3tGJL\u0007\u000f^5p]\u0006aA-Z:de&\u0004H/[8oA\u00059A-\u001a;bS2\u001c\u0018\u0001\u00033fi\u0006LGn\u001d\u0011\u0002\u001dM\u001c\u0007.\u001a3vY&tw\rU8pY\u0006y1o\u00195fIVd\u0017N\\4Q_>d\u0007%\u0001\u0004sI\u0012LEm]\u000b\u0003\u0005\u001f\u0002bA!\u0015\u0003X\u0005}RB\u0001B*\u0015\u0011\u0011)&a\n\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003Z\tM#aA*fc\u00069!\u000f\u001a3JIN\u0004\u0013AE1dGVlW\u000f\\1u_J,\u0006\u000fZ1uKN,\"A!\u0019\u0011\r\tE#q\u000bB2!\u0011\t\u0019D!\u001a\n\t\t\u001d\u0014q\u0001\u0002\u0010\u0003\u000e\u001cW/\\;mC\ndW-\u00138g_\u0006\u0019\u0012mY2v[Vd\u0017\r^8s+B$\u0017\r^3tA\u0005)A/Y:lgV\u0011!q\u000e\t\u0007\u0003K\tIG!\u001d\u0011\u0011\u0005=%1OAU\u0005oJAA!\u001e\u0002\"\n\u0019Q*\u00199\u0011\t\u0005M\"\u0011P\u0005\u0005\u0005w\n9A\u0001\u0005UCN\\G)\u0019;b\u0003\u0019!\u0018m]6tA\u0005yQ\r_3dkR|'oU;n[\u0006\u0014\u00180\u0006\u0002\u0003\u0004B1\u0011QEA5\u0005\u000b\u0003\u0002\"a$\u0003t\u00055%q\u0011\t\u0005\u0003g\u0011I)\u0003\u0003\u0003\f\u0006\u001d!\u0001F#yK\u000e,Ho\u001c:Ti\u0006<WmU;n[\u0006\u0014\u00180\u0001\tfq\u0016\u001cW\u000f^8s'VlW.\u0019:zA\u0005\u00112\u000f]3dk2\fG/[8o'VlW.\u0019:z+\t\u0011\u0019\n\u0005\u0004\u0002&\u0005%$Q\u0013\t\u0005\u0003g\u00119*\u0003\u0003\u0003\u001a\u0006\u001d!aF*qK\u000e,H.\u0019;j_:\u001cF/Y4f'VlW.\u0019:z\u0003M\u0019\b/Z2vY\u0006$\u0018n\u001c8Tk6l\u0017M]=!\u0003IY\u0017\u000e\u001c7fIR\u000b7o[:Tk6l\u0017M]=\u0016\u0005\t\u0005\u0006\u0003CAH\u0005g\ni)a\u0010\u0002'-LG\u000e\\3e)\u0006\u001c8n]*v[6\f'/\u001f\u0011\u0002#I,7o\\;sG\u0016\u0004&o\u001c4jY\u0016LE-\u0001\nsKN|WO]2f!J|g-\u001b7f\u0013\u0012\u0004\u0013a\u00059fC.,\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001cXC\u0001BW!\u0019\t)#!\u001b\u00030B!!\u0011\u0017B\\\u001b\t\u0011\u0019L\u0003\u0003\u00036\u0006M\u0011\u0001C3yK\u000e,Ho\u001c:\n\t\te&1\u0017\u0002\u0010\u000bb,7-\u001e;pe6+GO]5dg\u0006!\u0002/Z1l\u000bb,7-\u001e;pe6+GO]5dg\u0002\n\u0001\u0004^1tW6+GO]5dg\u0012K7\u000f\u001e:jEV$\u0018n\u001c8t+\t\u0011\t\r\u0005\u0004\u0002&\u0005%$1\u0019\t\u0005\u0003g\u0011)-\u0003\u0003\u0003H\u0006\u001d!a\u0006+bg.lU\r\u001e:jG\u0012K7\u000f\u001e:jEV$\u0018n\u001c8t\u0003e!\u0018m]6NKR\u0014\u0018nY:ESN$(/\u001b2vi&|gn\u001d\u0011\u00029\u0015DXmY;u_JlU\r\u001e:jGN$\u0015n\u001d;sS\n,H/[8ogV\u0011!q\u001a\t\u0007\u0003K\tIG!5\u0011\t\u0005M\"1[\u0005\u0005\u0005+\f9A\u0001\u000fFq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2t\t&\u001cHO]5ckRLwN\\:\u0002;\u0015DXmY;u_JlU\r\u001e:jGN$\u0015n\u001d;sS\n,H/[8og\u0002\nA#[:TQV4g\r\\3QkNDWI\\1cY\u0016$WC\u0001Bo!\u0011\t)Ca8\n\t\t\u0005\u0018q\u0005\u0002\b\u0005>|G.Z1o\u0003UI7o\u00155vM\u001adW\rU;tQ\u0016s\u0017M\u00197fI\u0002\n1c\u001d5vM\u001adW-T3sO\u0016\u00148oQ8v]R\fAc\u001d5vM\u001adW-T3sO\u0016\u00148oQ8v]R\u0004\u0013A\u0002\u001fj]&$h\bFA\u0003\u0005[\u0014yO!=\u0003t\nU(q\u001fB}\u0005w\u0014iPa@\u0004\u0002\r\r1QAB\u0004\u0007\u0013\u0019Ya!\u0004\u0004\u0010\rE11CB\u000b\u0007/\u0019Iba\u0007\u0004\u001e\r}1\u0011EB\u0012\u0007K\u00199c!\u000b\u0004,\r52qFB\u0019\u0007g\u0019)da\u000e\u0004:\rm2QHB \u0007\u0003\u001a\u0019e!\u0012\u0004H\r%31JB'\u0007\u001f\u001a\tfa\u0015\u0004V\r]3\u0011LB.\u0007;\u001ayf!\u0019\u0004d\r\u00154qTBQ\u0007G\u001b)\u000bE\u0002\u00024\u0001A\u0001\"!\u0005\u0002\u0004\u0001\u0007\u0011\u0011\u0007\u0005\t\u0003w\t\u0019\u00011\u0001\u0002@!A\u0011qIA\u0002\u0001\u0004\ty\u0004\u0003\u0005\u0002L\u0005\r\u0001\u0019AA \u0011!\ty%a\u0001A\u0002\u0005}\u0002\u0002CA*\u0003\u0007\u0001\r!a\u0010\t\u0011\u0005]\u00131\u0001a\u0001\u0003\u007fA\u0001\"a\u0017\u0002\u0004\u0001\u0007\u0011q\b\u0005\t\u0003?\n\u0019\u00011\u0001\u0002@!A\u00111MA\u0002\u0001\u0004\t9\u0007\u0003\u0005\u0002\u0000\u0005\r\u0001\u0019AA4\u0011!\t\u0019)a\u0001A\u0002\u0005\u001d\u0004\u0002CAD\u0003\u0007\u0001\r!a#\t\u0011\u0005\u0015\u00161\u0001a\u0001\u0003SC\u0001\"!-\u0002\u0004\u0001\u0007\u0011\u0011\u0016\u0005\t\u0003k\u000b\u0019\u00011\u0001\u0002*\"A\u0011\u0011XA\u0002\u0001\u0004\tI\u000b\u0003\u0005\u0002>\u0006\r\u0001\u0019AAU\u0011!\t\t-a\u0001A\u0002\u0005%\u0006\u0002CAc\u0003\u0007\u0001\r!!+\t\u0011\u0005%\u00171\u0001a\u0001\u0003SC\u0001\"!4\u0002\u0004\u0001\u0007\u0011\u0011\u0016\u0005\t\u0003#\f\u0019\u00011\u0001\u0002*\"A\u0011Q[A\u0002\u0001\u0004\tI\u000b\u0003\u0005\u0002Z\u0006\r\u0001\u0019AAU\u0011!\ti.a\u0001A\u0002\u0005%\u0006\u0002CAq\u0003\u0007\u0001\r!!+\t\u0011\u0005\u0015\u00181\u0001a\u0001\u0003SC\u0001\"!;\u0002\u0004\u0001\u0007\u0011\u0011\u0016\u0005\t\u0003[\f\u0019\u00011\u0001\u0002*\"A\u0011\u0011_A\u0002\u0001\u0004\tI\u000b\u0003\u0005\u0002v\u0006\r\u0001\u0019AAU\u0011!\tI0a\u0001A\u0002\u0005%\u0006\u0002CA\u007f\u0003\u0007\u0001\r!!+\t\u0011\t\u0005\u00111\u0001a\u0001\u0003SC\u0001B!\u0002\u0002\u0004\u0001\u0007\u0011\u0011\u0016\u0005\t\u0005\u0013\t\u0019\u00011\u0001\u0002*\"A!QBA\u0002\u0001\u0004\tI\u000b\u0003\u0005\u0003\u0012\u0005\r\u0001\u0019AAU\u0011!\u0011)\"a\u0001A\u0002\u0005%\u0006\u0002\u0003B\r\u0003\u0007\u0001\r!!+\t\u0011\tu\u00111\u0001a\u0001\u0003SC\u0001B!\t\u0002\u0004\u0001\u0007\u0011\u0011\u0016\u0005\t\u0005K\t\u0019\u00011\u0001\u0002*\"A!\u0011FA\u0002\u0001\u0004\tI\u000b\u0003\u0005\u0003.\u0005\r\u0001\u0019AAU\u0011!\u0011\t$a\u0001A\u0002\u0005%\u0006\u0002\u0003B\u001b\u0003\u0007\u0001\r!!+\t\u0011\te\u00121\u0001a\u0001\u0003\u001bC\u0001Ba\u0010\u0002\u0004\u0001\u0007\u00111\u0012\u0005\t\u0005\u0007\n\u0019\u00011\u0001\u0002\u000e\"A!qIA\u0002\u0001\u0004\ti\t\u0003\u0005\u0003L\u0005\r\u0001\u0019\u0001B(\u0011!\u0011i&a\u0001A\u0002\t\u0005\u0004\u0002\u0003B6\u0003\u0007\u0001\rAa\u001c\t\u0011\t}\u00141\u0001a\u0001\u0005\u0007C\u0001Ba$\u0002\u0004\u0001\u0007!1\u0013\u0005\t\u0005;\u000b\u0019\u00011\u0001\u0003\"\"A!QUA\u0002\u0001\u0004\ty\u0004\u0003\u0005\u0003*\u0006\r\u0001\u0019\u0001BWQ!\u0019)g!\u001b\u0004\u0006\u000e\u001d\u0005\u0003BB6\u0007\u0003k!a!\u001c\u000b\t\r=4\u0011O\u0001\u000bC:tw\u000e^1uS>t'\u0002BB:\u0007k\n\u0001\u0002Z1uC\nLg\u000e\u001a\u0006\u0005\u0007o\u001aI(A\u0004kC\u000e\\7o\u001c8\u000b\t\rm4QP\u0001\nM\u0006\u001cH/\u001a:y[2T!aa \u0002\u0007\r|W.\u0003\u0003\u0004\u0004\u000e5$!\u0004&t_:\u001cVM]5bY&TX-A\u0003vg&twm\t\u0002\u0004\nB!\u00111GBF\u0013\u0011\u0019i)a\u0002\u0003;\u0015CXmY;u_JlU\r\u001e:jGNT5o\u001c8TKJL\u0017\r\\5{KJD\u0003b!\u001a\u0004\u0012\u000e\u00155q\u0013\t\u0005\u0007W\u001a\u0019*\u0003\u0003\u0004\u0016\u000e5$a\u0004&t_:$Um]3sS\u0006d\u0017N_3$\u0005\re\u0005\u0003BA\u001a\u00077KAa!(\u0002\b\tyR\t_3dkR|'/T3ue&\u001c7OS:p]\u0012+7/\u001a:jC2L'0\u001a:\t\u0011\tu\u00161\u0001a\u0001\u0005\u0003D\u0001Ba3\u0002\u0004\u0001\u0007!q\u001a\u0005\t\u00053\f\u0019\u00011\u0001\u0003^\"A!Q]A\u0002\u0001\u0004\ty\u0004"
)
public class StageData {
   private final StageStatus status;
   private final int stageId;
   private final int attemptId;
   private final int numTasks;
   private final int numActiveTasks;
   private final int numCompleteTasks;
   private final int numFailedTasks;
   private final int numKilledTasks;
   private final int numCompletedIndices;
   private final Option submissionTime;
   private final Option firstTaskLaunchedTime;
   private final Option completionTime;
   private final Option failureReason;
   private final long executorDeserializeTime;
   private final long executorDeserializeCpuTime;
   private final long executorRunTime;
   private final long executorCpuTime;
   private final long resultSize;
   private final long jvmGcTime;
   private final long resultSerializationTime;
   private final long memoryBytesSpilled;
   private final long diskBytesSpilled;
   private final long peakExecutionMemory;
   private final long inputBytes;
   private final long inputRecords;
   private final long outputBytes;
   private final long outputRecords;
   private final long shuffleRemoteBlocksFetched;
   private final long shuffleLocalBlocksFetched;
   private final long shuffleFetchWaitTime;
   private final long shuffleRemoteBytesRead;
   private final long shuffleRemoteBytesReadToDisk;
   private final long shuffleLocalBytesRead;
   private final long shuffleReadBytes;
   private final long shuffleReadRecords;
   private final long shuffleCorruptMergedBlockChunks;
   private final long shuffleMergedFetchFallbackCount;
   private final long shuffleMergedRemoteBlocksFetched;
   private final long shuffleMergedLocalBlocksFetched;
   private final long shuffleMergedRemoteChunksFetched;
   private final long shuffleMergedLocalChunksFetched;
   private final long shuffleMergedRemoteBytesRead;
   private final long shuffleMergedLocalBytesRead;
   private final long shuffleRemoteReqsDuration;
   private final long shuffleMergedRemoteReqsDuration;
   private final long shuffleWriteBytes;
   private final long shuffleWriteTime;
   private final long shuffleWriteRecords;
   private final String name;
   private final Option description;
   private final String details;
   private final String schedulingPool;
   private final Seq rddIds;
   private final Seq accumulatorUpdates;
   private final Option tasks;
   private final Option executorSummary;
   private final Option speculationSummary;
   private final Map killedTasksSummary;
   private final int resourceProfileId;
   private final Option peakExecutorMetrics;
   private final Option taskMetricsDistributions;
   private final Option executorMetricsDistributions;
   private final boolean isShufflePushEnabled;
   private final int shuffleMergersCount;

   public StageStatus status() {
      return this.status;
   }

   public int stageId() {
      return this.stageId;
   }

   public int attemptId() {
      return this.attemptId;
   }

   public int numTasks() {
      return this.numTasks;
   }

   public int numActiveTasks() {
      return this.numActiveTasks;
   }

   public int numCompleteTasks() {
      return this.numCompleteTasks;
   }

   public int numFailedTasks() {
      return this.numFailedTasks;
   }

   public int numKilledTasks() {
      return this.numKilledTasks;
   }

   public int numCompletedIndices() {
      return this.numCompletedIndices;
   }

   public Option submissionTime() {
      return this.submissionTime;
   }

   public Option firstTaskLaunchedTime() {
      return this.firstTaskLaunchedTime;
   }

   public Option completionTime() {
      return this.completionTime;
   }

   public Option failureReason() {
      return this.failureReason;
   }

   public long executorDeserializeTime() {
      return this.executorDeserializeTime;
   }

   public long executorDeserializeCpuTime() {
      return this.executorDeserializeCpuTime;
   }

   public long executorRunTime() {
      return this.executorRunTime;
   }

   public long executorCpuTime() {
      return this.executorCpuTime;
   }

   public long resultSize() {
      return this.resultSize;
   }

   public long jvmGcTime() {
      return this.jvmGcTime;
   }

   public long resultSerializationTime() {
      return this.resultSerializationTime;
   }

   public long memoryBytesSpilled() {
      return this.memoryBytesSpilled;
   }

   public long diskBytesSpilled() {
      return this.diskBytesSpilled;
   }

   public long peakExecutionMemory() {
      return this.peakExecutionMemory;
   }

   public long inputBytes() {
      return this.inputBytes;
   }

   public long inputRecords() {
      return this.inputRecords;
   }

   public long outputBytes() {
      return this.outputBytes;
   }

   public long outputRecords() {
      return this.outputRecords;
   }

   public long shuffleRemoteBlocksFetched() {
      return this.shuffleRemoteBlocksFetched;
   }

   public long shuffleLocalBlocksFetched() {
      return this.shuffleLocalBlocksFetched;
   }

   public long shuffleFetchWaitTime() {
      return this.shuffleFetchWaitTime;
   }

   public long shuffleRemoteBytesRead() {
      return this.shuffleRemoteBytesRead;
   }

   public long shuffleRemoteBytesReadToDisk() {
      return this.shuffleRemoteBytesReadToDisk;
   }

   public long shuffleLocalBytesRead() {
      return this.shuffleLocalBytesRead;
   }

   public long shuffleReadBytes() {
      return this.shuffleReadBytes;
   }

   public long shuffleReadRecords() {
      return this.shuffleReadRecords;
   }

   public long shuffleCorruptMergedBlockChunks() {
      return this.shuffleCorruptMergedBlockChunks;
   }

   public long shuffleMergedFetchFallbackCount() {
      return this.shuffleMergedFetchFallbackCount;
   }

   public long shuffleMergedRemoteBlocksFetched() {
      return this.shuffleMergedRemoteBlocksFetched;
   }

   public long shuffleMergedLocalBlocksFetched() {
      return this.shuffleMergedLocalBlocksFetched;
   }

   public long shuffleMergedRemoteChunksFetched() {
      return this.shuffleMergedRemoteChunksFetched;
   }

   public long shuffleMergedLocalChunksFetched() {
      return this.shuffleMergedLocalChunksFetched;
   }

   public long shuffleMergedRemoteBytesRead() {
      return this.shuffleMergedRemoteBytesRead;
   }

   public long shuffleMergedLocalBytesRead() {
      return this.shuffleMergedLocalBytesRead;
   }

   public long shuffleRemoteReqsDuration() {
      return this.shuffleRemoteReqsDuration;
   }

   public long shuffleMergedRemoteReqsDuration() {
      return this.shuffleMergedRemoteReqsDuration;
   }

   public long shuffleWriteBytes() {
      return this.shuffleWriteBytes;
   }

   public long shuffleWriteTime() {
      return this.shuffleWriteTime;
   }

   public long shuffleWriteRecords() {
      return this.shuffleWriteRecords;
   }

   public String name() {
      return this.name;
   }

   public Option description() {
      return this.description;
   }

   public String details() {
      return this.details;
   }

   public String schedulingPool() {
      return this.schedulingPool;
   }

   public Seq rddIds() {
      return this.rddIds;
   }

   public Seq accumulatorUpdates() {
      return this.accumulatorUpdates;
   }

   public Option tasks() {
      return this.tasks;
   }

   public Option executorSummary() {
      return this.executorSummary;
   }

   public Option speculationSummary() {
      return this.speculationSummary;
   }

   public Map killedTasksSummary() {
      return this.killedTasksSummary;
   }

   public int resourceProfileId() {
      return this.resourceProfileId;
   }

   public Option peakExecutorMetrics() {
      return this.peakExecutorMetrics;
   }

   public Option taskMetricsDistributions() {
      return this.taskMetricsDistributions;
   }

   public Option executorMetricsDistributions() {
      return this.executorMetricsDistributions;
   }

   public boolean isShufflePushEnabled() {
      return this.isShufflePushEnabled;
   }

   public int shuffleMergersCount() {
      return this.shuffleMergersCount;
   }

   public StageData(final StageStatus status, final int stageId, final int attemptId, final int numTasks, final int numActiveTasks, final int numCompleteTasks, final int numFailedTasks, final int numKilledTasks, final int numCompletedIndices, final Option submissionTime, final Option firstTaskLaunchedTime, final Option completionTime, final Option failureReason, final long executorDeserializeTime, final long executorDeserializeCpuTime, final long executorRunTime, final long executorCpuTime, final long resultSize, final long jvmGcTime, final long resultSerializationTime, final long memoryBytesSpilled, final long diskBytesSpilled, final long peakExecutionMemory, final long inputBytes, final long inputRecords, final long outputBytes, final long outputRecords, final long shuffleRemoteBlocksFetched, final long shuffleLocalBlocksFetched, final long shuffleFetchWaitTime, final long shuffleRemoteBytesRead, final long shuffleRemoteBytesReadToDisk, final long shuffleLocalBytesRead, final long shuffleReadBytes, final long shuffleReadRecords, final long shuffleCorruptMergedBlockChunks, final long shuffleMergedFetchFallbackCount, final long shuffleMergedRemoteBlocksFetched, final long shuffleMergedLocalBlocksFetched, final long shuffleMergedRemoteChunksFetched, final long shuffleMergedLocalChunksFetched, final long shuffleMergedRemoteBytesRead, final long shuffleMergedLocalBytesRead, final long shuffleRemoteReqsDuration, final long shuffleMergedRemoteReqsDuration, final long shuffleWriteBytes, final long shuffleWriteTime, final long shuffleWriteRecords, final String name, final Option description, final String details, final String schedulingPool, final Seq rddIds, final Seq accumulatorUpdates, final Option tasks, final Option executorSummary, final Option speculationSummary, final Map killedTasksSummary, final int resourceProfileId, @JsonSerialize(using = ExecutorMetricsJsonSerializer.class) @JsonDeserialize(using = ExecutorMetricsJsonDeserializer.class) final Option peakExecutorMetrics, final Option taskMetricsDistributions, final Option executorMetricsDistributions, final boolean isShufflePushEnabled, final int shuffleMergersCount) {
      this.status = status;
      this.stageId = stageId;
      this.attemptId = attemptId;
      this.numTasks = numTasks;
      this.numActiveTasks = numActiveTasks;
      this.numCompleteTasks = numCompleteTasks;
      this.numFailedTasks = numFailedTasks;
      this.numKilledTasks = numKilledTasks;
      this.numCompletedIndices = numCompletedIndices;
      this.submissionTime = submissionTime;
      this.firstTaskLaunchedTime = firstTaskLaunchedTime;
      this.completionTime = completionTime;
      this.failureReason = failureReason;
      this.executorDeserializeTime = executorDeserializeTime;
      this.executorDeserializeCpuTime = executorDeserializeCpuTime;
      this.executorRunTime = executorRunTime;
      this.executorCpuTime = executorCpuTime;
      this.resultSize = resultSize;
      this.jvmGcTime = jvmGcTime;
      this.resultSerializationTime = resultSerializationTime;
      this.memoryBytesSpilled = memoryBytesSpilled;
      this.diskBytesSpilled = diskBytesSpilled;
      this.peakExecutionMemory = peakExecutionMemory;
      this.inputBytes = inputBytes;
      this.inputRecords = inputRecords;
      this.outputBytes = outputBytes;
      this.outputRecords = outputRecords;
      this.shuffleRemoteBlocksFetched = shuffleRemoteBlocksFetched;
      this.shuffleLocalBlocksFetched = shuffleLocalBlocksFetched;
      this.shuffleFetchWaitTime = shuffleFetchWaitTime;
      this.shuffleRemoteBytesRead = shuffleRemoteBytesRead;
      this.shuffleRemoteBytesReadToDisk = shuffleRemoteBytesReadToDisk;
      this.shuffleLocalBytesRead = shuffleLocalBytesRead;
      this.shuffleReadBytes = shuffleReadBytes;
      this.shuffleReadRecords = shuffleReadRecords;
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
      this.shuffleWriteTime = shuffleWriteTime;
      this.shuffleWriteRecords = shuffleWriteRecords;
      this.name = name;
      this.description = description;
      this.details = details;
      this.schedulingPool = schedulingPool;
      this.rddIds = rddIds;
      this.accumulatorUpdates = accumulatorUpdates;
      this.tasks = tasks;
      this.executorSummary = executorSummary;
      this.speculationSummary = speculationSummary;
      this.killedTasksSummary = killedTasksSummary;
      this.resourceProfileId = resourceProfileId;
      this.peakExecutorMetrics = peakExecutorMetrics;
      this.taskMetricsDistributions = taskMetricsDistributions;
      this.executorMetricsDistributions = executorMetricsDistributions;
      this.isShufflePushEnabled = isShufflePushEnabled;
      this.shuffleMergersCount = shuffleMergersCount;
   }
}
