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
package org.opens.tanaguru.entity.dao.reference;

import java.util.HashMap;
import java.util.Map;
import static junit.framework.Assert.assertEquals;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditImpl;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.dao.test.AbstractDaoTestCase;
import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Test;

/**
 *
 * @author jkowalczyk
 */
public class TestDAOImplTest extends AbstractDaoTestCase {

    private static final String INPUT_DATA_SET_FILENAME = "processRemarkFlatXmlDataSet.xml";
    private TestDAO testDAO;
    private ReferenceDAO referenceDAO;
    private LevelDAO levelDAO;

    public TestDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(getInputDataFilePath() + INPUT_DATA_SET_FILENAME);
        testDAO = (TestDAO) springBeanFactory.getBean("testDAO");
        levelDAO = (LevelDAO) springBeanFactory.getBean("levelDAO");
        referenceDAO = (ReferenceDAO) springBeanFactory.getBean("referenceDAO");
    }

    public void testRetrieveAll_Reference() {
        Reference ref = referenceDAO.read(Long.valueOf(1));
        assertEquals(10, testDAO.retrieveAll(ref).size());
        ref = referenceDAO.read(Long.valueOf(2));
        assertEquals(5, testDAO.retrieveAll(ref).size());
    }

    public void testRetrieveAllByCode() {
        String[] testTab = new String[10];
        int testCode = 10101;
        for (int i = 0; i < 10; i++) {
            testTab[i] = String.valueOf(testCode);
            testCode++;
        }
        assertEquals(10, testDAO.retrieveAllByCode(testTab).size());

        testTab = new String[20];
        testCode = 10101;
        for (int i = 0; i < 20; i++) {
            testTab[i] = String.valueOf(testCode);
            testCode++;
        }
        assertEquals(15, testDAO.retrieveAllByCode(testTab).size());
    }

    public void testRetrieveAllByReferenceAndLevel() {
        Reference ref = referenceDAO.read(Long.valueOf(1));
        Level level = levelDAO.read(Long.valueOf(1)); // bronze level ref 1
        assertEquals(7, testDAO.retrieveAllByReferenceAndLevel(ref, level).size());
        level = levelDAO.read(Long.valueOf(2)); // silver level ref 1
        assertEquals(10, testDAO.retrieveAllByReferenceAndLevel(ref, level).size());
        level = levelDAO.read(Long.valueOf(3)); // gold level ref 1
        assertEquals(10, testDAO.retrieveAllByReferenceAndLevel(ref, level).size());
        ref = referenceDAO.read(Long.valueOf(2)); // gold level ref 2
        assertEquals(5, testDAO.retrieveAllByReferenceAndLevel(ref, level).size());
        level = levelDAO.read(Long.valueOf(2)); // silver level ref 2
        assertEquals(5, testDAO.retrieveAllByReferenceAndLevel(ref, level).size());
        level = levelDAO.read(Long.valueOf(1)); // bronze level ref 2
        assertEquals(3, testDAO.retrieveAllByReferenceAndLevel(ref, level).size());
    }

    public void testRetrieveTestFromAuditAndLabel() {
        Audit audit = new AuditImpl();
        audit.setId(1L);
        audit.setStatus(AuditStatus.COMPLETED);
        Test test = testDAO.retrieveTestFromAuditAndLabel(audit, "1.1.2");
        assertEquals(test.getLabel(), "1.1.2");
        audit.setId(2L);
        test = testDAO.retrieveTestFromAuditAndLabel(audit, "1.1.15");
        assertEquals(test.getLabel(), "1.1.15");
        test = testDAO.retrieveTestFromAuditAndLabel(audit, "2.1.5");
        assertNull(test);
    }
}