package org.apache.datasketches.req;

import org.apache.datasketches.common.Util;

public class ReqSketchBuilder {
   private static final int DEFAULT_K = 12;
   private int bK = 12;
   private boolean bHRA;
   private ReqDebug bReqDebug;

   public ReqSketchBuilder() {
      this.bK = 12;
      this.bHRA = true;
      this.bReqDebug = null;
   }

   public ReqSketch build() {
      ReqSketch sk = new ReqSketch(this.bK, this.bHRA, this.bReqDebug);
      return sk;
   }

   public boolean getHighRankAccuracy() {
      return this.bHRA;
   }

   public int getK() {
      return this.bK;
   }

   public ReqDebug getReqDebug() {
      return this.bReqDebug;
   }

   public ReqSketchBuilder setHighRankAccuracy(boolean hra) {
      this.bHRA = hra;
      return this;
   }

   public ReqSketchBuilder setK(int k) {
      this.bK = k;
      return this;
   }

   public ReqSketchBuilder setReqDebug(ReqDebug reqDebug) {
      this.bReqDebug = reqDebug;
      return this;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("ReqSketchBuilder configuration:").append(Util.LS);
      sb.append("K:").append('\t').append(this.bK).append(Util.LS);
      sb.append("HRA:").append('\t').append(this.bHRA).append(Util.LS);
      String valid = this.bReqDebug != null ? "valid" : "invalid";
      sb.append("ReqDebug:").append('\t').append(valid).append(Util.LS);
      return sb.toString();
   }
}
