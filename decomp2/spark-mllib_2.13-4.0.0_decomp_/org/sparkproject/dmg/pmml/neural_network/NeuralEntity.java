package org.sparkproject.dmg.pmml.neural_network;

import jakarta.xml.bind.annotation.XmlTransient;
import org.sparkproject.dmg.pmml.Entity;

@XmlTransient
public abstract class NeuralEntity extends Entity {
   public abstract String requireId();
}
