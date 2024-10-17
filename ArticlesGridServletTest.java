package com.dsecom.webapp.core.servlets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dsecom.webapp.core.beans.SearchBean;
import com.dsecom.webapp.core.beans.SearchResultBean;
import com.dsecom.webapp.core.services.DSQueryService;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * The Class ArticlesGridServlet.
 */
@ExtendWith(MockitoExtension.class)
@ExtendWith(AemContextExtension.class)
class ArticlesGridServletTest {

    /** The Articles Grid Servlet */
    @InjectMocks
    ArticlesGridServlet searchServlet = new ArticlesGridServlet();

    /** The query service. */
    @Mock
    private DSQueryService queryService;

    /**
     * Sets the up.
     *
     * @param aemContext the new up
     */
    @Test
    void setup(AemContext aemContext) {
        MockSlingHttpServletRequest request = aemContext.request();
        MockSlingHttpServletResponse response = aemContext.response();
        aemContext.registerService(ArticlesGridServlet.class, searchServlet);
        aemContext.registerService(DSQueryService.class, queryService);
        aemContext.getService(ArticlesGridServlet.class);
        SearchBean searchBean = new SearchBean();
        searchBean.setTotalResults(1);
        List<Object> searchResultBeans = new ArrayList<>();
        SearchResultBean searchResultBean = new SearchResultBean();
        searchResultBean.setDate("30/5/2021");
        searchResultBean.setId("id");
        searchResultBean.setTitle("Blog");
        searchResultBeans.add(searchResultBean);
        searchBean.setSearchResults(searchResultBeans);
        Mockito.lenient().when(queryService.getSearchResults(request)).thenReturn(searchBean);

        searchServlet.doGet(request, response);
        assertEquals("application/json;charset=UTF-8", response.getContentType());
    }

}
