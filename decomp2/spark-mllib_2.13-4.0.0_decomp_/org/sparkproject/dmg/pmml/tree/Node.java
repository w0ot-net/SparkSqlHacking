package org.sparkproject.dmg.pmml.tree;

import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.EmbeddedModel;
import org.sparkproject.dmg.pmml.Entity;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasPredicate;
import org.sparkproject.dmg.pmml.HasRecordCount;
import org.sparkproject.dmg.pmml.HasScoreDistributions;
import org.sparkproject.dmg.pmml.Partition;
import org.sparkproject.dmg.pmml.ScoreDistribution;
import org.sparkproject.dmg.pmml.adapters.NodeAdapter;

@XmlTransient
@XmlJavaTypeAdapter(NodeAdapter.class)
public abstract class Node extends Entity implements HasPredicate, HasRecordCount, HasScoreDistributions {
   public ComplexNode toComplexNode() {
      return new ComplexNode(this);
   }

   public Object getId() {
      return null;
   }

   public Node setId(Object id) {
      throw new UnsupportedOperationException();
   }

   public Object getScore() {
      return null;
   }

   public Node setScore(Object score) {
      throw new UnsupportedOperationException();
   }

   public Number getRecordCount() {
      return null;
   }

   public Node setRecordCount(Number recordCount) {
      throw new UnsupportedOperationException();
   }

   public Object requireDefaultChild() {
      throw new UnsupportedOperationException();
   }

   public Object getDefaultChild() {
      return null;
   }

   public Node setDefaultChild(Object defaultChild) {
      throw new UnsupportedOperationException();
   }

   public boolean hasExtensions() {
      return false;
   }

   public List getExtensions() {
      throw new UnsupportedOperationException();
   }

   public Node addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Partition getPartition() {
      return null;
   }

   public Node setPartition(Partition partition) {
      throw new UnsupportedOperationException();
   }

   public boolean hasScoreDistributions() {
      return false;
   }

   public List getScoreDistributions() {
      throw new UnsupportedOperationException();
   }

   public Node addScoreDistributions(ScoreDistribution... scoreDistributions) {
      this.getScoreDistributions().addAll(Arrays.asList(scoreDistributions));
      return this;
   }

   public boolean hasNodes() {
      return false;
   }

   public List getNodes() {
      throw new UnsupportedOperationException();
   }

   public Node addNodes(Node first) {
      List<Node> nodes = this.getNodes();
      nodes.add(first);
      return this;
   }

   public Node addNodes(Node first, Node second) {
      List<Node> nodes = this.getNodes();
      nodes.add(first);
      nodes.add(second);
      return this;
   }

   public Node addNodes(Node... nodes) {
      this.getNodes().addAll(Arrays.asList(nodes));
      return this;
   }

   public EmbeddedModel getEmbeddedModel() {
      return null;
   }

   public Node setEmbeddedModel(EmbeddedModel embeddedModel) {
      throw new UnsupportedOperationException();
   }
}
