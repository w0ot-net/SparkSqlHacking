package org.sparkproject.dmg.pmml;

import jakarta.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class Field extends PMMLObject implements HasDisplayName, HasRequiredName, HasRequiredType {
}
