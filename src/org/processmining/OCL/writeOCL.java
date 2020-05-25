package org.processmining.OCL;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class writeOCL {
 
    public static final String xmlFilePath = "/Users/GYUNAM/Documents/object-centric-log/writeExample.xml";
 
    public static void main(String argv[]) {
 
        try {
 
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
 
            Document document = documentBuilder.newDocument();
 
            // root element
            Element root = document.createElement("event");
            root.setAttribute("event-id", "1");
            document.appendChild(root);
 
            // process-id element
            Element pid = document.createElement("process-id");
            pid.appendChild(document.createTextNode("OH"));
            root.appendChild(pid);
            
            Element aid = document.createElement("activity-id");
            aid.appendChild(document.createTextNode("place_order"));
            root.appendChild(aid);
            
            Element rid = document.createElement("resource-id");
            rid.appendChild(document.createTextNode("Jane"));
            root.appendChild(rid);
 
            Element timestamp = document.createElement("timestamp");
            timestamp.appendChild(document.createTextNode("1"));
            root.appendChild(timestamp);
            
            Element omap = document.createElement("object-mapping");
            root.appendChild(omap);
 
            // firstname element
            Element oc = document.createElement("object-class");
            oc.appendChild(document.createTextNode("Order"));
            Element oi = document.createElement("object-id");
            oi.appendChild(document.createTextNode("o1"));
            oc.appendChild(oi);
            omap.appendChild(oc);
 
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
 
            // If you use
            StreamResult result =  new StreamResult(System.out);
            transformer.transform(domSource, result);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 
 
            transformer.transform(domSource, streamResult);
 
            System.out.println("Done creating XML File");
 
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}