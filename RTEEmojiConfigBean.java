package com.responsive.cer.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Model class to hold the multifield values of RTE Emoji Config Component
 *
 * @author muppas
 *
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RTEEmojiConfigBean {

	@ValueMapValue
	 private String emojiName;

	@ValueMapValue
	 private String emojiValue;

	public String getEmojiName() {
		return emojiName;
	}

	public String getEmojiValue() {
		return emojiValue;
	}

}
