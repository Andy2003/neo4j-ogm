CREATE (company1:BaseNodeEntity:Node {nodeId: 'company1'})
CREATE (location1:BaseNodeEntity:Node {nodeId: 'location1'})
CREATE (location2:BaseNodeEntity:Node {nodeId: 'location2'})
CREATE (meter1:BaseNodeEntity:LeafNode {nodeId: 'meter1'})
CREATE (meter2:BaseNodeEntity:LeafNode {nodeId: 'meter2'})

CREATE (meter1)-[:BELONGS_TO]->(company1)
CREATE (meter2)-[:BELONGS_TO]->(company1)
CREATE (location1)-[:CHILD_OF]->(company1)
CREATE (location2)-[:CHILD_OF]->(company1)
CREATE (meter1)-[:CHILD_OF]->(location1)
CREATE (meter2)-[:CHILD_OF]->(location1)


