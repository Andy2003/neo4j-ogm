package org.neo4j.ogm.domain.gh591;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public abstract class BaseNodeEntity extends BaseEntity {

    @Index(unique = true)
    private String nodeId;

    @Relationship(type = "CHILD_OF")
    private Node childOf;

    public BaseNodeEntity() {
    }

    public String getNodeId() {
        return nodeId;
    }

    public BaseNodeEntity setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public Node getChildOf() {
        return childOf;
    }

    public BaseNodeEntity setChildOf(Node parentNode) {
        this.childOf = parentNode;
        return this;
    }
}
