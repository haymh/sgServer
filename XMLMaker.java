import java.io.*;
import java.util.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Attribute;


public class XMLMaker{
	private Writer out;
	public XMLMaker(Writer out){
		this.out = out;
	}
	public boolean createGroupsXML(ArrayList<Group> groups){
		try{
			Element root = new Element("groups");
			Document doc = new Document(root);
			//doc.setRootElement(root);
			for(int i = 0; i < groups.size(); i++){
				Group g = groups.get(i);
				Element group = new Element("group");
				group.setAttribute(new Attribute("id", "" + i));
				group.addContent(new Element("id").setText("" + g.getId()));
				group.addContent(new Element("title").setText(g.getTitle()));
				group.addContent(new Element("subject").setText(g.getSubject()));
				group.addContent(new Element("address").setText(g.getAddress()));
				group.addContent(new Element("addressAddition").setText(g.getAddressAddition()));
				group.addContent(new Element("duration").setText("" + g.getDuration()));
				group.addContent(new Element("peopleIn").setText("" + g.getNumOfPeople()));
				group.addContent(new Element("checkIn").setText("" + g.getNumOfCheckin()));
				group.addContent(new Element("school").setText(g.getSchool()));
				group.addContent(new Element("creatorId").setText(g.getCreatorId()));
				group.addContent(new Element("creatorName").setText(g.getCreatorName()));
				
				doc.getRootElement().addContent(group);
			}
			

			XMLOutputter xmlOutput = new XMLOutputter();
			//xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, out);
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void setOut(Writer out){
		this.out = out;
	}
}