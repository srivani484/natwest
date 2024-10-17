package com.carnival.core.servlets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.servlet.ServletException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.servlethelpers.MockSlingHttpServletRequest;
import org.apache.sling.servlethelpers.MockSlingHttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.carnival.core.beans.DeckFeaturesBean;
import com.carnival.core.beans.DeckSectionBean;
import com.carnival.core.beans.ShipDeckBean;
import com.carnival.core.beans.ShipDeckDetailsBean;
import com.carnival.core.beans.ShipSectionBean;
import com.carnival.core.beans.ShipSectionDetailsBean;
import com.carnival.core.utils.QueryBuilderUtils;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

@ExtendWith({ MockitoExtension.class, AemContextExtension.class })
class DeckSectionDetailsServletTest {

	private DeckSectionDetailsServlet deckSectionDetailsServlet = new DeckSectionDetailsServlet();

	@Mock
	private MockSlingHttpServletRequest request;

	@Mock
	private MockSlingHttpServletResponse response;

	@Mock
	private Page page;

	@Mock
	private PageManager pageManager;

	@Mock
	private Iterator iterator;

	ResourceResolver resourceResolver;

	private final String CURRENTPAGE_PATH = "/content/hal/global/en/cruise-ships/eurodam/0";
	private final String SHIP_ID = "EO";
	private final String SHIP_VERSION = "2";

	/**
	 * Setup method to set DeckSectionDetailsServlet method properties
	 * 
	 * @param context
	 * @throws Exception
	 */
	@BeforeEach
	public void setup(AemContext context) throws Exception {
		request = mock(MockSlingHttpServletRequest.class);
		response = mock(MockSlingHttpServletResponse.class);
		resourceResolver = mock(ResourceResolver.class);
		page = mock(Page.class);
		lenient().when(request.getResourceResolver()).thenReturn(resourceResolver);
		lenient().when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
	}

	/**
	 * Method to test the doGet method with mock data.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 * @throws RepositoryException
	 * @throws NoSuchFieldException
	 */
	@Test
	void testDoGet() throws IOException, ServletException, RepositoryException, NoSuchFieldException {
		String deckPath = "/content/hal/global/en/cruise-ships/eurodam/0/deck";
		when(request.getParameter("path")).thenReturn(CURRENTPAGE_PATH);
		when(request.getParameter("shipId")).thenReturn(SHIP_ID);
		when(request.getParameter("shipVersion")).thenReturn(SHIP_VERSION);
		Resource resource = mock(Resource.class);
		when(QueryBuilderUtils.searchShipDetailsPage(resourceResolver, CURRENTPAGE_PATH, SHIP_ID, SHIP_VERSION)).thenReturn(page);
		assertNotNull(page);
		when(resourceResolver.getResource(page.getPath())).thenReturn(resource);
		Resource deckResource = mock(Resource.class);
		when(resource.getChild(anyString())).thenReturn(deckResource);
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		when(deckResource.getPath()).thenReturn(deckPath);
		deckSectionDetailsServlet.doGet(request, response);
		Assertions.assertNotNull(response);

	}

	/**
	 * Method the test the deck details by passing test data for the respective bean
	 * classes.
	 * 
	 * @throws NullPointerException
	 * @throws RepositoryException
	 */
	@Test
	void addDeckDetailsTest() throws NullPointerException, RepositoryException {
		Resource deckResource = mock(Resource.class);
		String deckPath = "/content/hal/global/en/cruise-ships/eurodam/0/deck";
		when(deckResource.getPath()).thenReturn(deckPath);
		Resource deckContainer = mock(Resource.class);
		when(resourceResolver.getResource(anyString())).thenReturn(deckContainer);
		Resource testResource = mock(Resource.class);
		ValueMap valueMap = mock(ValueMap.class);
		when(testResource.getValueMap()).thenReturn(valueMap);
		when(valueMap.containsKey(anyString())).thenReturn(true).thenReturn(false);
		lenient().when(valueMap.get("deckId", Integer.class)).thenReturn(1);
		lenient().when(valueMap.get("deckId", String.class)).thenReturn("1");						
		List<Resource> resList = new ArrayList<>();
		resList.add(testResource);
		Iterator<Resource> iterator = resList.iterator();
		when(deckContainer.hasChildren()).thenReturn(true);
		when(deckContainer.getChildren()).thenReturn(mock(Iterable.class));
		when(deckContainer.getChildren().iterator()).thenReturn(iterator);
		ShipDeckBean shipDeckBean = deckSectionDetailsServlet.addDeckDetails(deckResource, resourceResolver);
		Assertions.assertNotNull(shipDeckBean);
	}

	/**
	 * Method the test the ship section details by passing test data for the
	 * respective bean classes.
	 * 
	 * @throws NullPointerException
	 * @throws RepositoryException
	 */
	@Test
	void addShipSectionDetailsTest() throws NullPointerException, RepositoryException {
		Resource sectionResource = mock(Resource.class);
		String sectionPath = "/content/hal/global/en/cruise-ships/eurodam/0/section";
		when(sectionResource.getPath()).thenReturn(sectionPath);
		Resource container = mock(Resource.class);
		when(resourceResolver.getResource(anyString())).thenReturn(container);
		Resource testResource = mock(Resource.class);
		ValueMap valueMap = mock(ValueMap.class);
		when(testResource.getValueMap()).thenReturn(valueMap);
		when(valueMap.containsKey(anyString())).thenReturn(true).thenReturn(false);
		lenient().when(valueMap.get("sectionId", Integer.class)).thenReturn(1);
		lenient().when(valueMap.get("sectionId", String.class)).thenReturn("A");		
		List<Resource> resList = new ArrayList<>();
		resList.add(testResource);
		Iterator<Resource> iterator = resList.iterator();
		when(container.hasChildren()).thenReturn(true);
		when(container.getChildren()).thenReturn(mock(Iterable.class));
		when(container.getChildren().iterator()).thenReturn(iterator);
		ShipSectionBean shipSectionBean = deckSectionDetailsServlet.addShipSectionDetails(sectionResource,
				resourceResolver);
		Assertions.assertNotNull(shipSectionBean);
	}

	/**
	 * Method the test the deck features by passing test data for the respective
	 * bean classes.
	 * 
	 * @throws NoSuchFieldException
	 */
	@Test
	void addDeckFeaturesTest() throws NoSuchFieldException {
		Resource deckResource = mock(Resource.class);
		Resource featureResource = mock(Resource.class);
		Resource featureHit = mock(Resource.class);
		iterator = mock(Iterator.class);
		when(deckResource.getChild("features")).thenReturn(featureResource);
		when(featureResource.hasChildren()).thenReturn(true);
		when(featureResource.getChildren()).thenReturn(mock(Iterable.class));
		when(featureResource.getChildren().iterator()).thenReturn(iterator);
		when(iterator.hasNext()).thenReturn(true, false);
		when(iterator.next()).thenReturn(featureHit);
		ValueMap valueMap = mock(ValueMap.class);
		when(featureHit.getValueMap()).thenReturn(valueMap);
		when(valueMap.containsKey(anyString())).thenReturn(true);
		List<DeckFeaturesBean> deckFeaturesList = new ArrayList<>();
		DeckFeaturesBean featureBean = new DeckFeaturesBean();
		PrivateAccessor.setField(featureBean, "featureTitle", "test");
		deckFeaturesList.add(featureBean);
		List<DeckFeaturesBean> featuresList = deckSectionDetailsServlet.addDeckFeatures(deckResource);
		Assertions.assertNotNull(featuresList);
	}

	/**
	 * Method the test the deck section details by passing test data for the
	 * respective bean classes.
	 * 
	 * @throws NoSuchFieldException
	 */
	@Test
	void addDeckSectionDetailsTest() throws NoSuchFieldException {
		Resource resource = mock(Resource.class);
		Resource sectionResource = mock(Resource.class);
		Resource sectionHit = mock(Resource.class);
		iterator = mock(Iterator.class);
		when(resource.getChild("sectionImageAndJson")).thenReturn(sectionResource);
		when(sectionResource.hasChildren()).thenReturn(true);
		when(sectionResource.getChildren()).thenReturn(mock(Iterable.class));
		when(sectionResource.getChildren().iterator()).thenReturn(iterator);
		when(iterator.hasNext()).thenReturn(true, false);
		when(iterator.next()).thenReturn(sectionHit);
		ValueMap valueMap = mock(ValueMap.class);
		when(sectionHit.getValueMap()).thenReturn(valueMap);
		when(valueMap.containsKey(anyString())).thenReturn(true);
		List<DeckSectionBean> decSectionList = new ArrayList<>();
		DeckSectionBean sectionBean = new DeckSectionBean();
		PrivateAccessor.setField(sectionBean, "sectionId", "sectionId");
		PrivateAccessor.setField(sectionBean, "sectionImage", "sectionImage");
		decSectionList.add(sectionBean);
		List<DeckSectionBean> sectionList = deckSectionDetailsServlet.addDeckSectionDetails(resource);
		Assertions.assertNotNull(sectionList);
	}

	/**
	 * Method the get the deck details object by passing test data to the respective
	 * bean classes.
	 * 
	 * @throws NoSuchFieldException
	 */
	@Test
	void getDeckDetailsObjectTest() throws NoSuchFieldException {
		List<ShipDeckDetailsBean> deckDetailsList = new ArrayList<>();
		ShipDeckDetailsBean deckDetailsBean = new ShipDeckDetailsBean();
		PrivateAccessor.setField(deckDetailsBean, "id", "id");
		PrivateAccessor.setField(deckDetailsBean, "title", "title");
		PrivateAccessor.setField(deckDetailsBean, "description", "description");
		PrivateAccessor.setField(deckDetailsBean, "deckImage", "deckImage");
		deckDetailsList.add(deckDetailsBean);
		ShipDeckBean deckBean = new ShipDeckBean();
		PrivateAccessor.setField(deckBean, "decks", deckDetailsList);
		List<ShipDeckDetailsBean> deckList = deckBean.getDecks();
		Assertions.assertNotNull(deckList);
		JsonObject deckJson = deckSectionDetailsServlet.getDeckDetailsObject(deckBean);
		Assertions.assertNotNull(deckJson);
	}

	/**
	 * Method the get the section details object by passing test data to the
	 * respective bean classes.
	 * 
	 * @throws NoSuchFieldException
	 */
	@Test
	void getSectionDetailsObjectTest() throws NoSuchFieldException {
		List<ShipSectionDetailsBean> sectionDetailsList = new ArrayList<>();
		ShipSectionDetailsBean sectionDetailsBean = new ShipSectionDetailsBean();
		PrivateAccessor.setField(sectionDetailsBean, "sectionId", "sectionId");
		PrivateAccessor.setField(sectionDetailsBean, "title", "title");
		PrivateAccessor.setField(sectionDetailsBean, "description", "description");
		PrivateAccessor.setField(sectionDetailsBean, "image", "image");
		sectionDetailsList.add(sectionDetailsBean);
		ShipSectionBean sectionBean = new ShipSectionBean();
		PrivateAccessor.setField(sectionBean, "decks", sectionDetailsList);
		List<ShipSectionDetailsBean> sectionList = sectionBean.getDecks();
		Assertions.assertNotNull(sectionList);
		JsonObject deckJson = deckSectionDetailsServlet.getSectionDetailsObject(sectionBean);
		Assertions.assertNotNull(deckJson);
	}
	
	/**
	 * Method the get the deck features array by passing the test data to the
	 * respective bean classes.
	 * 
	 * @throws NoSuchFieldException
	 */
	@Test
	void getDeckFeaturesArrayTest() throws NoSuchFieldException {
		List<DeckFeaturesBean> deckFeaturesList = new ArrayList<>();
		DeckFeaturesBean featureBean = new DeckFeaturesBean();
		PrivateAccessor.setField(featureBean, "featureTitle", "test");
		deckFeaturesList.add(featureBean);
		JsonArray jsonArray = deckSectionDetailsServlet.getDeckFeaturesArray(deckFeaturesList);
		Assertions.assertNotNull(jsonArray);
	}
	
	/**
	 * Method the get the deck section data object by passing the test data to the
	 * respective bean classes.
	 * 
	 * @throws NoSuchFieldException
	 */
	@Test
	void getDeckSectionsObjectTest() throws NoSuchFieldException {
		List<DeckSectionBean> decSectionList = new ArrayList<>();
		DeckSectionBean sectionBean = new DeckSectionBean();
		PrivateAccessor.setField(sectionBean, "sectionId", "sectionId");
		PrivateAccessor.setField(sectionBean, "sectionImage", "sectionImage");
		decSectionList.add(sectionBean);
		JsonObject jsonObject = deckSectionDetailsServlet.getDeckSectionsObject(decSectionList);
		Assertions.assertNotNull(jsonObject);
	}

}
