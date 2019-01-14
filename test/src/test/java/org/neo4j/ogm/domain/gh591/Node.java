package org.neo4j.ogm.domain.gh591;

import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Node extends BaseNodeEntity {

    @Relationship(type = "CHILD_OF", direction = Relationship.INCOMING)
    protected Set<BaseNodeEntity> childNodes;

    public Set<BaseNodeEntity> getChildNodes() {
        return childNodes;
    }

    public Node setChildNodes(Set<BaseNodeEntity> childNodes) {
        this.childNodes = childNodes;
        return this;
    }
}
