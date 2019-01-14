package org.neo4j.ogm.domain.gh591;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class LeafNode extends BaseNodeEntity {

    @Relationship(type = "BELONGS_TO")
    protected Node belongsTo;

    public Node getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(Node company) {
        this.belongsTo = company;
    }

}
