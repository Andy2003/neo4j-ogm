package org.neo4j.ogm.domain.cyclic;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class CyclicNodeType {

    @Id @GeneratedValue
    private Long id;

    @Relationship(type = "HAS_FIELD", direction = Relationship.OUTGOING)
    private List<RefField> refFields;

    @Relationship(type = "SUBORDINATE", direction = Relationship.INCOMING)
    private List<CyclicNodeType> subordinateNodeTypes;

    public List<RefField> getRefFields() {
        return refFields;
    }

    public CyclicNodeType setRefFields(List<RefField> refFields) {
        this.refFields = refFields;
        return this;
    }

    public List<CyclicNodeType> getSubordinateNodeTypes() {
        return subordinateNodeTypes;
    }

    public CyclicNodeType setSubordinateNodeTypes(
        List<CyclicNodeType> subordinateNodeTypes) {
        this.subordinateNodeTypes = subordinateNodeTypes;
        return this;
    }
}
