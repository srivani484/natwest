package com.dxc.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
public class LinkListModelTest {

	/** The Constant RESOURCE_CONTENT. */
	private static final String RESOURCE_CONTENT = "/linklist/test-content.json";

	/** The Constant TEST_CONTENT_ROOT. */
	private static final String TEST_CONTENT_ROOT = "/content/dxc/us/en";

	/** The Constant RESOURCE. */
	private static final String RESOURCE = TEST_CONTENT_ROOT + "/jcr:content/root/container/linklist";

	/** The model. */
	private LinkListModel model;

	/** The resource. */
	private Resource resource;

	/**
	 * Sets the up.
	 *
	 * @param aemContext the new up
	 */
	@BeforeEach
	public void setup(AemContext aemContext) {
		Class<LinkListModel> modelClass = LinkListModel.class;
		MockSlingHttpServletRequest request = aemContext.request();
		aemContext.load().json(RESOURCE_CONTENT, TEST_CONTENT_ROOT);
		aemContext.addModelsForClasses(modelClass);
		aemContext.request().setPathInfo(TEST_CONTENT_ROOT);
		request.setResource(aemContext.resourceResolver().getResource(RESOURCE));
		resource = aemContext.currentResource(RESOURCE);
		model = resource.adaptTo(modelClass);

	}

	/**
	 * Simple load and getter test.
	 */
	@Test
	public void simpleLoadAndGetterTest() {
		assertEquals(2, model.getLinks().size());
		LinkListMultifieldModel bean = model.getLinks().get(0);
		assertEquals("Carrers", bean.getTitle());
		assertEquals("/content/dxc.html", bean.getUrl());
		assertEquals("_self", bean.getTarget());	
	}

}
