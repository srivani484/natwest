package com.dxc.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.dxc.core.utils.LinkUtil;

/**
 * The Sling Model for Link List Component to retrieve multifield values.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinkListMultifieldModel {

	/** The resource resolver. */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The title. */
	@ValueMapValue
	private String title;

	/** The url. */
	@ValueMapValue
	private String url;

	/** The target. */
	@ValueMapValue
	private String target;

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return LinkUtil.getFormattedLink(url, resourceResolver);
	}

	/**
	 * Gets the target.
	 *
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}
}
