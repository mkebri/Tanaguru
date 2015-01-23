/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.entity.service.statistics;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.statistics.TestStatistics;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 *
 * @author jkowalczyk
 */
public interface TestStatisticsDataService
        extends GenericDataService<TestStatistics, Long>{

    /**
     * 
     * @param webResource
     * @param testSolution
     * @param test
     * @return 
     *      the number of webresource returning the given testSolution for the 
     * given test
     */
    Long getResultCountByResultTypeAndTest(
            WebResource webResource,
            TestSolution testSolution,
            Test test);

}