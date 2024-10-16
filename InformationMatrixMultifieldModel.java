package com.dxc.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Sling Model for Information Matrix Component.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class InformationMatrixMultifieldModel {

	private static final Logger log = LoggerFactory.getLogger(InformationMatrixMultifieldModel.class);

	/** The icon. */
	@ValueMapValue
	private String icon;

	/** The Description. */
	@ValueMapValue
	private String iconId;

	@PostConstruct
	protected void init() {
		log.debug("Inside Information Matrix Multifield Model init()::");

	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @return the iconId
	 */
	public String getIconId() {
		return iconId;
	}
}
