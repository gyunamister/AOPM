package org.processmining.AIS;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class readAIS {

   public static void main(String[] args) {

      try {
         File inputFile = new File("/Users/GYUNAM/Documents/action-instance-stream/aisExample.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("action-instance");
         System.out.println("----------------------------");
         
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("Action formula : " 
                  + eElement.getAttribute("action-formula"));
               System.out.println("Operation : " 
                  + eElement
                  .getElementsByTagName("operation")
                  .item(0)
                  .getTextContent());
               System.out.println("Timestamp: " 
                  + eElement
                  .getElementsByTagName("timestamp")
                  .item(0)
                  .getTextContent());
               Element omap = (Element) eElement.getElementsByTagName("parameter-mapping").item(0);
               for(int i=0;i<omap.getElementsByTagName("parameter").getLength();i++) {
            	   Element oc = (Element) omap.getElementsByTagName("parameter").item(i);
            	   System.out.println(oc.getAttribute("name"));
            	   for(int j=0;j<oc.getElementsByTagName("value").getLength();j++) {
            		   System.out.println("Value : " + oc.getElementsByTagName("value").item(j).getTextContent());
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
