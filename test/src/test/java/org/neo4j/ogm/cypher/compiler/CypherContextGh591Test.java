/*
 * Copyright (c) 2002-2019 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with
 * separate copyright notices and license terms. Your use of the source
 * code for these subcomponents is subject to the terms and
 *  conditions of the subcomponent's license, as noted in the LICENSE file.
 */
package org.neo4j.ogm.cypher.compiler;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.domain.gh591.BaseNodeEntity;
import org.neo4j.ogm.domain.gh591.LeafNode;
import org.neo4j.ogm.domain.gh591.Node;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.testutil.MultiDriverTestClass;
import org.neo4j.ogm.testutil.TestUtils;

public class CypherContextGh591Test extends MultiDriverTestClass {

    private static String createTestDataStatement = TestUtils.readCQLFile("org/neo4j/ogm/cql/gh591.cql")
        .toString();
    private static SessionFactory factory;
    private Session session;

    @BeforeClass
    public static void initSesssionFactory() {
        factory = new SessionFactory(driver, "org.neo4j.ogm.domain.gh591");
    }

    @Before
    public void init() throws IOException {
        session = factory.openSession();
        session.purgeDatabase();
        session.query(createTestDataStatement, Collections.emptyMap());
        session.clear();
    }

    @Test
    public void test1() {
        // if this get commented out the test is green
        Node location1 = (Node) getBaseNodeEntity("location1");

        LeafNode meter1 = (LeafNode) getBaseNodeEntity("meter1");

        Node location2 = (Node) getBaseNodeEntity("location2");
        meter1.setChildOf(location2);

        // if updating the other side, the test is also green
        // location1.getChildNodes().remove(meter1);
        session.save(meter1);

        checkSubordinatedMeasurements("location1", 1);
        checkSubordinatedMeasurements("location2", 1);
    }

    @Test
    public void test2() {
        Node company1 = (Node) getBaseNodeEntity("company1");

        Node location1 = (Node) getBaseNodeEntity("location1");
        Node location2 = (Node) getBaseNodeEntity("location2");

        LeafNode meter3 = new LeafNode();
        meter3.setBelongsTo(company1);
        meter3.setChildOf(location1);
        meter3.setNodeId("meter3");
        session.save(meter3);

        checkSubordinatedMeasurements("location1", 3);
        checkSubordinatedMeasurements("location2", 0);

        LeafNode meter1 = (LeafNode) getBaseNodeEntity("meter1");

        meter1.setChildOf(location2);
        session.save(meter1);

        meter3.setChildOf(location2);
        session.save(meter3);

        checkSubordinatedMeasurements("location1", 1);
        checkSubordinatedMeasurements("location2", 2);
    }

    private void checkSubordinatedMeasurements(String nodeId, int expected) {
        Iterable<LeafNode> result135 = session.query(LeafNode.class,
            "MATCH (n:Node)<-[:CHILD_OF*]-(measurement:LeafNode) WHERE n.nodeId = {nodeId} RETURN measurement",
            Collections.singletonMap("nodeId", nodeId));
        Assert.assertEquals(expected, ((List) result135).size());
    }

    private BaseNodeEntity getBaseNodeEntity(String nodeId) {
        Filter filter = new Filter("nodeId", ComparisonOperator.EQUALS, nodeId);
        filter.setOwnerEntityType(BaseNodeEntity.class);
        Collection<BaseNodeEntity> entities = session.loadAll(BaseNodeEntity.class, new Filters(filter), 1);
        Assert.assertEquals(1, entities.size());
        return entities.stream().findFirst().orElse(null);
    }

}
