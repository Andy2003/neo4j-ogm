/*
 * Copyright (c) 2002-2019 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4j.ogm.response.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.neo4j.ogm.model.Edge;
import org.neo4j.ogm.model.GraphModel;
import org.neo4j.ogm.model.Node;

/**
 * The results of a query, modelled as graph data.
 *
 * @author Michal Bachman
 */
public class DefaultGraphModel implements GraphModel {

    private final Set<Node> nodes = new LinkedHashSet<>();
    private final Set<Node> projectedNodes = new HashSet<>();
    private final Set<Edge> relationships = new LinkedHashSet<>();

    @Override
    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(NodeModel[] nodes) {
        Collections.addAll(this.nodes, nodes);
    }

    @Override
    public Set<Node> getProjectedNodes() {
        return projectedNodes;
    }

    @Override
    public Set<Edge> getRelationships() {
        return relationships;
    }

    public void setRelationships(RelationshipModel[] relationships) {
        Collections.addAll(this.relationships, relationships);
    }
}
