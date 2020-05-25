package org.processmining.OCL;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class testOCL {

   public static void main(String[] args) {

      try {
         File inputFile = new File("/Users/GYUNAM/Documents/object-centric-log/testExample.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("event");
         System.out.println("----------------------------");
         
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("Event id : " 
                  + eElement.getAttribute("event-id"));
               System.out.println("Process id : " 
                       + eElement
                       .getElementsByTagName("process-id")
                       .item(0)
                       .getTextContent());
               System.out.println("Activity id : " 
                  + eElement
                  .getElementsByTagName("activity-id")
                  .item(0)
                  .getTextContent());
               System.out.println("Resource id : " 
                  + eElement
                  .getElementsByTagName("resource-id")
                  .item(0)
                  .getTextContent());
               Element omap = (Element) eElement.getElementsByTagName("object-mapping").item(0);
               for(int i=0;i<omap.getElementsByTagName("object-class").getLength();i++) {
            	   Element oc = (Element) omap.getElementsByTagName("object-class").item(i);
            	   System.out.println(oc.getAttribute("type"));
            	   for(int j=0;j<oc.getElementsByTagName("object-id").getLength();j++) {
            		   System.out.println("Object id : " + oc.getElementsByTagName("object-id").item(j).getTextContent());
            	   }
//            	   Node oc = eElement.getElementsByTagName("object-mapping").item(i);
//            	   for(int j=0;j<oc.getChildNodes().getLength();j++) {
//            		   Node oi = oc.getChildNodes().item(j);
//            		   System.out.println("Order id : " + oi.getTextContent());
//            	   }
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
