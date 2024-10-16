package com.dxc.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;

import com.adobe.cq.export.json.ExporterConstants;

/**
 * The Sling Model for Link List component.
 */
@Model(adaptables = Resource.class, resourceType = {
		LinkListModel.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class LinkListModel {

	/** The Constant RESOURCE_TYPE. */
	public static final String RESOURCE_TYPE = "dxc/components/content/linklist";

	/** The footer links. */
	@Inject
	private List<LinkListMultifieldModel> links;

	/**
	 * Gets the links.
	 *
	 * @return the links
	 */
	public List<LinkListMultifieldModel> getLinks() {
		if (Objects.nonNull(links)) {
			return new ArrayList<>(links);
		}
		return Collections.emptyList();
	}

}
