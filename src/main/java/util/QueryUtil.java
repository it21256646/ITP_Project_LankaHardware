package util;

import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class QueryUtil extends CommonUtil {

	public static String queryByID(String id) throws SAXException, IOException, ParserConfigurationException{

		NodeList nodeList;
		Element element = null;
		/*
		 * Read the EmployeeQuery.xml file and read each query node into node
		 * list. It refers tag name query
		 */
		nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("/Online food ordering system/src/main/java/util/Query.xml"))
				.getElementsByTagName(CommonConstants.TAG_NAME);

		/*
		 * Extract the node from node list using query id query id is taken from
		 * query node attribute
		 */
		for (int value = 0; value < nodeList.getLength(); value++) {
			element = (Element) nodeList.item(value);
			
			if (((DocumentBuilderFactory) element).getAttribute(CommonConstants.ATTRIB_ID).equals(id))
				break;
		}
		return ((Node) element).getTextContent().trim();
	}
}
