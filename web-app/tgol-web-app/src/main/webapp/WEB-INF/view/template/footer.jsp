<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <c:if test="${not empty configProperties['tanaguruVersion']}">
            <c:set var="tanaguruVersion">
                ${configProperties['tanaguruVersion']}
            </c:set>    
        </c:if>
        <footer>
            <jsp:useBean id="now" class="java.util.Date" scope="page" />
            <div id="footer-down">
                &copy; 2009-<fmt:formatDate value="${now}" pattern="yyyy" />
                <a href="http://www.tanaguru.com/" target="_blank" title="Tanaguru.com <fmt:message key="footer.newWindow"/> ">
                    Tanaguru.com
                </a> 
                - 
                <fmt:message key="footer.poweredBy"/> 
                <a href="https://github.com/Tanaguru/Tanaguru" target="_blank" title="Tanaguru v${tanaguruVersion} <fmt:message key="footer.newWindow"/> "  >
                    Tanaguru v${tanaguruVersion}
                </a>
                - 
                <fmt:message key="footer.availableUnder"/> 
                <a href="http://www.gnu.org/licenses/agpl-3.0.html" target="_blank" title="GNU AGPL v3 <fmt:message key="footer.newWindow"/> " >
                    GNU AGPL v3
                </a>
            </div>
        </footer>
        <%@include file="tracker.jsp" %>