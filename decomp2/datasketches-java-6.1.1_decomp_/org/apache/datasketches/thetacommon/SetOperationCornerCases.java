package org.apache.datasketches.thetacommon;

import java.util.HashMap;
import java.util.Map;
import org.apache.datasketches.common.SketchesArgumentException;

public class SetOperationCornerCases {
   private static final long MAX = Long.MAX_VALUE;

   public static int createCornerCaseId(long thetaLongA, int countA, boolean emptyA, long thetaLongB, int countB, boolean emptyB) {
      return sketchStateId(emptyA, countA, thetaLongA) << 3 | sketchStateId(emptyB, countB, thetaLongB);
   }

   public static int sketchStateId(boolean isEmpty, int numRetained, long thetaLong) {
      return (thetaLong != Long.MAX_VALUE && !isEmpty ? 0 : 4) | (numRetained > 0 ? 2 : 0) | (isEmpty ? 1 : 0);
   }

   public static enum IntersectAction {
      DEGEN_MIN_0_F("D", "Degenerate{MinTheta, 0, F}"),
      EMPTY_1_0_T("E", "Empty{1.0, 0, T}"),
      FULL_INTERSECT("I", "Full Intersect");

      private String actionId;
      private String actionDescription;

      private IntersectAction(String actionId, String actionDescription) {
         this.actionId = actionId;
         this.actionDescription = actionDescription;
      }

      public String getActionId() {
         return this.actionId;
      }

      public String getActionDescription() {
         return this.actionDescription;
      }
   }

   public static enum AnotbAction {
      SKETCH_A("A", "Sketch A Exactly"),
      TRIM_A("TA", "Trim Sketch A by MinTheta"),
      DEGEN_MIN_0_F("D", "Degenerate{MinTheta, 0, F}"),
      DEGEN_THA_0_F("DA", "Degenerate{ThetaA, 0, F}"),
      EMPTY_1_0_T("E", "Empty{1.0, 0, T}"),
      FULL_ANOTB("N", "Full AnotB");

      private String actionId;
      private String actionDescription;

      private AnotbAction(String actionId, String actionDescription) {
         this.actionId = actionId;
         this.actionDescription = actionDescription;
      }

      public String getActionId() {
         return this.actionId;
      }

      public String getActionDescription() {
         return this.actionDescription;
      }
   }

   public static enum UnionAction {
      SKETCH_A("A", "Sketch A Exactly"),
      TRIM_A("TA", "Trim Sketch A by MinTheta"),
      SKETCH_B("B", "Sketch B Exactly"),
      TRIM_B("TB", "Trim Sketch B by MinTheta"),
      DEGEN_MIN_0_F("D", "Degenerate{MinTheta, 0, F}"),
      DEGEN_THA_0_F("DA", "Degenerate{ThetaA, 0, F}"),
      DEGEN_THB_0_F("DB", "Degenerate{ThetaB, 0, F}"),
      EMPTY_1_0_T("E", "Empty{1.0, 0, T}"),
      FULL_UNION("N", "Full Union");

      private String actionId;
      private String actionDescription;

      private UnionAction(String actionId, String actionDescription) {
         this.actionId = actionId;
         this.actionDescription = actionDescription;
      }

      public String getActionId() {
         return this.actionId;
      }

      public String getActionDescription() {
         return this.actionDescription;
      }
   }

   public static enum CornerCase {
      Empty_Empty(45, "A{ 1.0, 0, T} ; B{ 1.0, 0, T}", SetOperationCornerCases.IntersectAction.EMPTY_1_0_T, SetOperationCornerCases.AnotbAction.EMPTY_1_0_T, SetOperationCornerCases.UnionAction.EMPTY_1_0_T),
      Empty_Exact(46, "A{ 1.0, 0, T} ; B{ 1.0,>0, F}", SetOperationCornerCases.IntersectAction.EMPTY_1_0_T, SetOperationCornerCases.AnotbAction.EMPTY_1_0_T, SetOperationCornerCases.UnionAction.SKETCH_B),
      Empty_Estimation(42, "A{ 1.0, 0, T} ; B{<1.0,>0, F", SetOperationCornerCases.IntersectAction.EMPTY_1_0_T, SetOperationCornerCases.AnotbAction.EMPTY_1_0_T, SetOperationCornerCases.UnionAction.SKETCH_B),
      Empty_Degen(40, "A{ 1.0, 0, T} ; B{<1.0, 0, F}", SetOperationCornerCases.IntersectAction.EMPTY_1_0_T, SetOperationCornerCases.AnotbAction.EMPTY_1_0_T, SetOperationCornerCases.UnionAction.DEGEN_THB_0_F),
      Exact_Empty(53, "A{ 1.0,>0, F} ; B{ 1.0, 0, T}", SetOperationCornerCases.IntersectAction.EMPTY_1_0_T, SetOperationCornerCases.AnotbAction.SKETCH_A, SetOperationCornerCases.UnionAction.SKETCH_A),
      Exact_Exact(54, "A{ 1.0,>0, F} ; B{ 1.0,>0, F}", SetOperationCornerCases.IntersectAction.FULL_INTERSECT, SetOperationCornerCases.AnotbAction.FULL_ANOTB, SetOperationCornerCases.UnionAction.FULL_UNION),
      Exact_Estimation(50, "A{ 1.0,>0, F} ; B{<1.0,>0, F}", SetOperationCornerCases.IntersectAction.FULL_INTERSECT, SetOperationCornerCases.AnotbAction.FULL_ANOTB, SetOperationCornerCases.UnionAction.FULL_UNION),
      Exact_Degen(48, "A{ 1.0,>0, F} ; B{<1.0, 0, F}", SetOperationCornerCases.IntersectAction.DEGEN_MIN_0_F, SetOperationCornerCases.AnotbAction.TRIM_A, SetOperationCornerCases.UnionAction.TRIM_A),
      Estimation_Empty(21, "A{<1.0,>0, F} ; B{ 1.0, 0, T}", SetOperationCornerCases.IntersectAction.EMPTY_1_0_T, SetOperationCornerCases.AnotbAction.SKETCH_A, SetOperationCornerCases.UnionAction.SKETCH_A),
      Estimation_Exact(22, "A{<1.0,>0, F} ; B{ 1.0,>0, F}", SetOperationCornerCases.IntersectAction.FULL_INTERSECT, SetOperationCornerCases.AnotbAction.FULL_ANOTB, SetOperationCornerCases.UnionAction.FULL_UNION),
      Estimation_Estimation(18, "A{<1.0,>0, F} ; B{<1.0,>0, F}", SetOperationCornerCases.IntersectAction.FULL_INTERSECT, SetOperationCornerCases.AnotbAction.FULL_ANOTB, SetOperationCornerCases.UnionAction.FULL_UNION),
      Estimation_Degen(16, "A{<1.0,>0, F} ; B{<1.0, 0, F}", SetOperationCornerCases.IntersectAction.DEGEN_MIN_0_F, SetOperationCornerCases.AnotbAction.TRIM_A, SetOperationCornerCases.UnionAction.TRIM_A),
      Degen_Empty(5, "A{<1.0, 0, F} ; B{ 1.0, 0, T}", SetOperationCornerCases.IntersectAction.EMPTY_1_0_T, SetOperationCornerCases.AnotbAction.DEGEN_THA_0_F, SetOperationCornerCases.UnionAction.DEGEN_THA_0_F),
      Degen_Exact(6, "A{<1.0, 0, F} ; B{ 1.0,>0, F}", SetOperationCornerCases.IntersectAction.DEGEN_MIN_0_F, SetOperationCornerCases.AnotbAction.DEGEN_THA_0_F, SetOperationCornerCases.UnionAction.TRIM_B),
      Degen_Estimation(2, "A{<1.0, 0, F} ; B{<1.0,>0, F}", SetOperationCornerCases.IntersectAction.DEGEN_MIN_0_F, SetOperationCornerCases.AnotbAction.DEGEN_MIN_0_F, SetOperationCornerCases.UnionAction.TRIM_B),
      Degen_Degen(0, "A{<1.0, 0, F} ; B{<1.0, 0, F}", SetOperationCornerCases.IntersectAction.DEGEN_MIN_0_F, SetOperationCornerCases.AnotbAction.DEGEN_MIN_0_F, SetOperationCornerCases.UnionAction.DEGEN_MIN_0_F);

      private static final Map caseIdToCornerCaseMap = new HashMap();
      private int caseId;
      private String caseDescription;
      private IntersectAction intersectAction;
      private AnotbAction anotbAction;
      private UnionAction unionAction;

      private CornerCase(int caseId, String caseDescription, IntersectAction intersectAction, AnotbAction anotbAction, UnionAction unionAction) {
         this.caseId = caseId;
         this.caseDescription = caseDescription;
         this.intersectAction = intersectAction;
         this.anotbAction = anotbAction;
         this.unionAction = unionAction;
      }

      public int getId() {
         return this.caseId;
      }

      public String getCaseDescription() {
         return this.caseDescription;
      }

      public IntersectAction getIntersectAction() {
         return this.intersectAction;
      }

      public AnotbAction getAnotbAction() {
         return this.anotbAction;
      }

      public UnionAction getUnionAction() {
         return this.unionAction;
      }

      public static CornerCase caseIdToCornerCase(int id) {
         CornerCase cc = (CornerCase)caseIdToCornerCaseMap.get(id);
         if (cc == null) {
            throw new SketchesArgumentException("Possible Corruption: Illegal CornerCase ID: " + Integer.toOctalString(id));
         } else {
            return cc;
         }
      }

      static {
         for(CornerCase cc : values()) {
            caseIdToCornerCaseMap.put(cc.getId(), cc);
         }

      }
   }
}
