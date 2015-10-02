/*
 * Copyright (c) 2002-2015 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with
 * separate copyright notices and license terms. Your use of the source
 * code for these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 *
 */

package org.neo4j.ogm.cypher.query;

import org.neo4j.ogm.api.result.DriverStatistics;
import org.neo4j.ogm.driver.impl.model.StatisticsModel;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Luanne Misquitta
 */
public class DefaultQuery implements DriverStatistics {

	private Iterable<Map<String,Object>> result;
	private StatisticsModel queryStatistics;

	public DefaultQuery(Iterable<Map<String, Object>> result, StatisticsModel queryStatistics) {
		this.result = result;
		this.queryStatistics = queryStatistics;
	}

	@Override
	public Iterator<Map<String,Object>> iterator() {
		return result.iterator();
	}

	@Override
	public Iterable<Map<String,Object>> model() {
		return result;
	}

	@Override
	public StatisticsModel statistics() {
		return queryStatistics;
	}
}