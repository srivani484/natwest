package com.dxc.core.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dxc.core.beans.SearchBean;
import com.dxc.core.services.DXCQueryService;
import com.google.gson.Gson;

/**
 * The Servlet class for Search.
 */
@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=DXC - Search servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.resourceTypes=" + "dxc/components/content/searchlisting", "sling.servlet.extensions=" + "json" })
public class SearchServlet extends SlingSafeMethodsServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3358734373793626875L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchServlet.class);

	/** The query result service. */
	@Reference
	private transient DXCQueryService queryService;

	/**
	 * Do get.
	 *
	 * @param request  the request
	 * @param response the response
	 */
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		try {
			SearchBean resources = queryService.getSearchResults(request);
		
			Gson gson = new Gson();
			String responseJSON = "not-set";
			if (Objects.nonNull(resources)) {
				responseJSON = gson.toJson(resources);
				LOGGER.debug("Search result JSON object : {}", responseJSON);
			}
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(responseJSON);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error while decoding the query term.", e);
		} catch (IOException e) {
			LOGGER.error("Error while writing the response object.", e);
		}

	}

}
