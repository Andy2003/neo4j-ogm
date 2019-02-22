package org.neo4j.ogm.domain.cyclic;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class RefField {

    @Id @GeneratedValue
    private Long id;

    @Relationship(type = "HAS_FIELD", direction = Relationship.INCOMING)
    private List<CyclicNodeType> nodeTypes;

    public List<CyclicNodeType> getNodeTypes() {
        return nodeTypes;
    }

    public RefField setNodeTypes(List<CyclicNodeType> nodeTypes) {
        this.nodeTypes = nodeTypes;
        return this;
    }
}
