package com.dxc.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.export.json.ExporterConstants;

/**
 * The Sling Model for Information Matrix Component.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = InformationMatrixModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class InformationMatrixModel {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(InformationMatrixModel.class);
	/** The Constant RESOURCE_TYPE. */
	protected static final String RESOURCE_TYPE = "dxc/components/content/informationmatrix";
	@Inject
	private List<Resource> infoMatrixItems;
	/** The heading. */
	@ValueMapValue
	private String heading;
	private List<InformationMatrixMultifieldModel> matrixInfoLinks = Collections.emptyList();

	@PostConstruct
	protected void init() {
		log.debug("Information Matrix Model init()::");
		matrixInfoLinks = new ArrayList<>();
		if (null != infoMatrixItems && !infoMatrixItems.isEmpty()) {
			setMatrixInfo();
		}
	}

	private void setMatrixInfo() {
		for (Resource resource : infoMatrixItems) {
			if (null != resource) {
				final InformationMatrixMultifieldModel matrixLinks = resource
						.adaptTo(InformationMatrixMultifieldModel.class);
				matrixInfoLinks.add(matrixLinks);
			}
		}
	}

	/**
	 * @return the heading
	 */
	public String getHeading() {
		return heading;
	}

	/**
	 * Gets the matrixIconLinks.
	 * 
	 * @return the matrixIconLinks
	 */
	public List<InformationMatrixMultifieldModel> getMatrixInfoLinks() {
		return Collections.unmodifiableList(matrixInfoLinks);
	}
}
