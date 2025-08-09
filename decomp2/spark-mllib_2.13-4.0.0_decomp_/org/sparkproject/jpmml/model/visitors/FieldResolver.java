package org.sparkproject.jpmml.model.visitors;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.sparkproject.dmg.pmml.DataDictionary;
import org.sparkproject.dmg.pmml.DefineFunction;
import org.sparkproject.dmg.pmml.Field;
import org.sparkproject.dmg.pmml.LocalTransformations;
import org.sparkproject.dmg.pmml.Model;
import org.sparkproject.dmg.pmml.Output;
import org.sparkproject.dmg.pmml.PMML;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.ResultField;
import org.sparkproject.dmg.pmml.TransformationDictionary;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.mining.Segment;
import org.sparkproject.dmg.pmml.mining.Segmentation;
import org.sparkproject.dmg.pmml.regression.Regression;
import org.sparkproject.dmg.pmml.tree.DecisionTree;

public class FieldResolver extends AbstractVisitor implements Resettable {
   private Map scopes = new IdentityHashMap();
   private Map customScopes = Collections.emptyMap();

   public void reset() {
      this.scopes.clear();
      this.customScopes = Collections.emptyMap();
   }

   public PMMLObject popParent() {
      PMMLObject parent = super.popParent();
      if (parent instanceof Field) {
         Field<?> field = (Field)parent;
         parent = this.getParent();
         List<Field<?>> customScope = (List)this.customScopes.get(parent);
         if (customScope != null) {
            customScope.add(field);
         }
      } else if (parent instanceof TransformationDictionary) {
         PMML pmml = (PMML)this.getParent();
         this.declareGlobalFields(pmml, true);
         this.customScopes = Collections.emptyMap();
      } else if (parent instanceof LocalTransformations) {
         Model model = (Model)this.getParent();
         this.declareLocalFields(model, true);
         this.customScopes = Collections.emptyMap();
      } else {
         List<Field<?>> customScope = (List)this.customScopes.get(parent);
         if (customScope != null) {
            this.customScopes = Collections.emptyMap();
         }
      }

      return parent;
   }

   public VisitorAction visit(Model model) {
      this.declareLocalFields(model, true);
      return super.visit(model);
   }

   public VisitorAction visit(DecisionTree decisionTree) {
      throw new UnsupportedOperationException();
   }

   public VisitorAction visit(DefineFunction defineFunction) {
      this.declareFields(defineFunction, defineFunction.hasParameterFields() ? defineFunction.getParameterFields() : Collections.emptyList());
      return super.visit(defineFunction);
   }

   public VisitorAction visit(LocalTransformations localTransformations) {
      Model model = (Model)this.getParent();
      if (localTransformations.hasDerivedFields()) {
         this.declareLocalFields(model, false);
         this.suppressFields(localTransformations);
      }

      return super.visit(localTransformations);
   }

   public VisitorAction visit(Output output) {
      if (output.hasOutputFields()) {
         this.declareFields(output, output.getOutputFields());
         this.suppressFields(output);
      }

      return super.visit(output);
   }

   public VisitorAction visit(PMML pmml) {
      this.declareGlobalFields(pmml, true);
      return super.visit(pmml);
   }

   public VisitorAction visit(Regression regression) {
      throw new UnsupportedOperationException();
   }

   public VisitorAction visit(ResultField resultField) {
      throw new UnsupportedOperationException();
   }

   public VisitorAction visit(TransformationDictionary transformationDictionary) {
      PMML pmml = (PMML)this.getParent();
      if (transformationDictionary.hasDerivedFields()) {
         this.declareGlobalFields(pmml, false);
         this.suppressFields(transformationDictionary);
      }

      return super.visit(transformationDictionary);
   }

   public Collection getFields() {
      Deque<PMMLObject> parents = this.getParents();
      return this.getFields(parents);
   }

   public Collection getFields(PMMLObject... virtualParents) {
      Deque<PMMLObject> parents = new ArrayDeque(this.getParents());

      for(PMMLObject virtualParent : virtualParents) {
         parents.push(virtualParent);
      }

      return this.getFields(parents);
   }

   private Collection getFields(Deque parents) {
      List<Field<?>> result = new ArrayList();
      PMMLObject prevParent = null;

      for(PMMLObject parent : parents) {
         List<Field<?>> scope = this.getScope(parent);
         if (scope != null && !scope.isEmpty()) {
            result.addAll(scope);
         }

         if (parent instanceof DefineFunction) {
            break;
         }

         if (parent instanceof Segmentation && (prevParent == null || prevParent instanceof Segment)) {
            for(Output output : getEarlierOutputs((Segmentation)parent, (Segment)prevParent)) {
               List<Field<?>> scope = this.getScope(output);
               if (scope != null && !scope.isEmpty()) {
                  result.addAll(scope);
               }
            }
         }

         prevParent = parent;
      }

      return result;
   }

   private List getScope(PMMLObject object) {
      if (!this.customScopes.isEmpty()) {
         List<Field<?>> customScope = (List)this.customScopes.get(object);
         if (customScope != null) {
            return customScope;
         }
      }

      return (List)this.scopes.get(object);
   }

   private void declareGlobalFields(PMML pmml, boolean transformations) {
      List<Field<?>> scope = (List)this.scopes.get(pmml);
      if (scope != null) {
         scope.clear();
      }

      DataDictionary dataDictionary = pmml.requireDataDictionary();
      if (dataDictionary.hasDataFields()) {
         this.declareFields(pmml, dataDictionary.getDataFields());
      }

      TransformationDictionary transformationDictionary = pmml.getTransformationDictionary();
      if (transformations && transformationDictionary != null && transformationDictionary.hasDerivedFields()) {
         this.declareFields(pmml, transformationDictionary.getDerivedFields());
      }

   }

   private void declareLocalFields(Model model, boolean transformations) {
      List<Field<?>> scope = (List)this.scopes.get(model);
      if (scope != null) {
         scope.clear();
      }

      LocalTransformations localTransformations = model.getLocalTransformations();
      if (transformations && localTransformations != null && localTransformations.hasDerivedFields()) {
         this.declareFields(model, localTransformations.getDerivedFields());
      }

   }

   private void declareFields(PMMLObject object, Collection fields) {
      List<Field<?>> scope = (List)this.scopes.get(object);
      if (scope == null) {
         scope = new ArrayList(fields.size());
         this.scopes.put(object, scope);
      }

      scope.addAll(fields);
   }

   private void suppressFields(PMMLObject object) {
      this.customScopes = Collections.singletonMap(object, new ArrayList());
   }

   private static List getEarlierOutputs(Segmentation segmentation, Segment targetSegment) {
      List<Output> result = new ArrayList();
      Segmentation.MultipleModelMethod multipleModelMethod = segmentation.requireMultipleModelMethod();
      switch (multipleModelMethod) {
         case MODEL_CHAIN:
         case MULTI_MODEL_CHAIN:
            for(Segment segment : segmentation.requireSegments()) {
               Model model = segment.requireModel();
               if (targetSegment != null && Objects.equals(segment, targetSegment)) {
                  break;
               }

               Output output = model.getOutput();
               if (output != null) {
                  result.add(output);
               }
            }

            return result;
         default:
            return Collections.emptyList();
      }
   }
}
