package com.responsive.cer.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.responsive.ccvar.model.CCVarConfigBean;

/**
 * Sling model for the RTE Emoji Configuration component. Path of
 * component:responsive/components/configs/emojiconfig
 *
 * @author muppas
 *
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = "responsive/components/configs/emojiconfig")
public class RTEEmojiConfigModel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RTEEmojiConfigModel.class);

	/** The {@link List} of configs authored. */
	@ChildResource
	private List<RTEEmojiConfigBean> emojiConfigs;

	/**
	 * Gets the list of the Emoji variables authored. Each item in the list has the
	 * Emoji Name and Value to get display in content fragment RTE plugin
	 *
	 * @return {@link List} of type {@link CCVarConfigBean}
	 */
	public List<RTEEmojiConfigBean> getEmojiConfigs() {
		if (emojiConfigs != null) {

			return new ArrayList<>(emojiConfigs);
		}
		return Collections.emptyList();
	}
}
